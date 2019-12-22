package com.assignment.location.controllers.services.impl;

import com.assignment.location.controllers.repositories.GeoAssocRepo;
import com.assignment.location.controllers.repositories.GeoRepo;
import com.assignment.location.controllers.services.LocationServices;
import com.assignment.location.controllers.services.NextSeqServices;
import com.assignment.location.exceptionHandler.CustomException;
import com.assignment.location.models.Geo;
import com.assignment.location.models.GeoAssoc;
import com.assignment.location.models.responseModels.SearchDistrict;
import com.assignment.location.models.responseModels.SearchState;
import com.assignment.location.models.responseModels.SearchTown;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anuragdhunna
 */
@Service
public class LocationServicesImpl implements LocationServices {

    @Autowired
    GeoRepo geoRepo;


    @Autowired
    GeoAssocRepo geoAssocRepo;

    @Autowired
    NextSeqServices nextSeqServices;

    @Override
    public Geo storeGeo(Geo geo) {
        geo.setId(nextSeqServices.getNextSeqId("geo"));
        nextSeqServices.storeNextSeq("geo");
        return geoRepo.save(geo);
    }

    @Override
    public GeoAssoc storeGeoAssoc(GeoAssoc geoAssoc) {
        geoAssoc.setId(nextSeqServices.getNextSeqId("geo_assoc"));
        nextSeqServices.storeNextSeq("geo_assoc");
        return geoAssocRepo.save(geoAssoc);
    }

    @Override
    public List<SearchState> searchState(String searchText) {
        if (searchText.length() < 2) {
            throw new CustomException("Search input should have more than 2 characters");
        }

        List<SearchState> states = new ArrayList<>();
        List<Geo> geos = geoRepo.searchLocation(searchText, Geo.Type.STATE.toString());
        System.out.println("==========geos.size()======="+geos.size());
        geos.forEach(geo -> {

            System.out.println("==============stateId========="+geo.getId().intValue());
            List<GeoRepo.SearchStateDistricts> districtsList = geoRepo.findDistsByStateCode(geo.getId().intValue());

            System.out.println("=========districtsList======="+districtsList.size());

            districtsList.forEach(district -> {
                SearchState state = new SearchState();
                state.setState(geo.getName());
                state.setDistName(district.getName());
                state.setDistCode(district.getCode());
                states.add(state);
            });
        });

        return states;
    }

    @Override
    public List<SearchTown> searchTown(String searchText) {
        if (searchText.length() < 2) {
            throw new CustomException("Search input should have more than 2 characters");
        }

        List<Geo> geos = geoRepo.searchLocation(searchText, Geo.Type.CITY.toString());
        List<SearchTown> towns = new ArrayList<>();
        System.out.println("==========geos.size()======="+geos.size());

        geos.forEach(geo -> {
            // get district
            System.out.println("================geo.getId()========"+geo.getId());
            GeoRepo.SearchDistsByCityId district = geoRepo.findDistByCityId(geo.getId().intValue());
            SearchTown town = new SearchTown();
            town.setTown(geo.getName());

            if (district != null) {
                System.out.println("=====district.getId()=================" + district.getId());
                GeoRepo.Result state = geoRepo.findStateByDistId(district.getId().longValue());
                town.setState(state.getName());
                town.setDistrict(district.getName());

            }
            towns.add(town);
        });

        return towns;
    }

    @Override
    public List<SearchDistrict> searchDistrict(String searchText) {

        if (searchText.length() < 2) {
            throw new CustomException("Search input should have more than 2 characters");
        }

        List<SearchDistrict> districts = new ArrayList<>();
        List<Geo> geos = geoRepo.searchLocation(searchText, Geo.Type.DISTRICT.toString());
        System.out.println("==========geos.size()======="+geos.size());
        geos.forEach(geo -> {

            // TODO: Find District State
//            GeoRepo.Result stateByDistId = geoRepo.findStateByDistId(geo.getId());

            List<GeoRepo.SearchDistrict> cityList = geoRepo.findChildByParentId(geo.getId().intValue());
            System.out.println("==========cityList.size()======="+cityList.size());

            cityList.forEach(city -> {
                SearchDistrict district = new SearchDistrict();
                district.setDistName(geo.getName());
                district.setDistCode(geo.getCode());
                district.setTown(city.getName());
                district.setUrbanStatus(city.getUrbanstatus());
                districts.add(district);
            });
        });

        return districts;
    }

    @Override
    public String uploadLocationCsv(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        String[] input = csvReader.readNext();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        while ((input = csvReader.readNext()) != null) {
            if (StringUtils.isEmpty(input[0])) {
                continue;
            }

            System.out.println("==========================================================================================================="+geoRepo.findAll().size());
            // Store State
            int stateCode = Integer.parseInt(input[3]);
            String stateName = input[4].replace("*", "").trim();
            Geo stateGeo = geoRepo.findByCodeAndName(stateCode, stateName, Geo.Type.STATE.toString());
            System.out.println("=======stateGeo================="+stateGeo);
            if (stateGeo == null) {
                // Store State
                stateGeo =storeGeo(new Geo(stateName, Geo.Type.STATE, stateCode, null));
            }

            int distCode;
            try {
                distCode = Integer.parseInt(input[5]);
            } catch (NumberFormatException e){
                continue;
            }

            String districtName = input[6].trim();
            Geo distGeo = geoRepo.findByCodeAndName(distCode, districtName, Geo.Type.DISTRICT.toString());
            System.out.println(districtName+"=======distGeo================="+distGeo);
            if (distGeo == null) {
                distGeo = storeGeo(new Geo(districtName, Geo.Type.DISTRICT, distCode, null));

                // Check Relation Between State and District
                System.out.println(stateGeo.getId()+"======stateId============STATE_DIST=================distId===="+distGeo.getId()+"===getName==="+distGeo.getName());
                GeoAssoc relation = findRelation(stateGeo.getId().intValue(), distGeo.getId().intValue(), GeoAssoc.RelationType.STATE_DIST);
                System.out.println("=======relation======STATE_DIST==========="+relation);
                if (relation == null) {
                    // Create Relation
                    storeGeoAssoc(new GeoAssoc(stateGeo.getId().intValue(), distGeo.getId().intValue(), GeoAssoc.RelationType.STATE_DIST));
                }
            }

            int townCode = Integer.parseInt(input[0]);
            String townName = input[1].trim();
            Geo townGeo = geoRepo.findByCodeAndName(townCode, townName, Geo.Type.CITY.toString());
            if (townGeo == null) {
                // Add City/Town
                townGeo = storeGeo(new Geo(townName, Geo.Type.CITY, townCode, input[2]));

                // Create Relation
                System.out.println(townCode+"======townCode============DIST_CITY=================distCode===="+distCode);
                GeoAssoc distToTown = findRelation(distCode, townCode, GeoAssoc.RelationType.DIST_CITY);
                System.out.println("=======relation======DIST_CITY==========="+distToTown);
                if (distToTown == null) {
                    storeGeoAssoc(new GeoAssoc(distGeo.getId().intValue(), townGeo.getId().intValue(), GeoAssoc.RelationType.DIST_CITY));
                }
            }
        }
        return "File Uploaded Successfully.";
    }

    private GeoAssoc findRelation(int code, int codeTo, GeoAssoc.RelationType relationType) {
        return geoAssocRepo.findRelation(code, codeTo, relationType);
    }

}