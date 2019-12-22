package com.assignment.location.controllers.services;

import com.assignment.location.models.Geo;
import com.assignment.location.models.GeoAssoc;
import com.assignment.location.models.responseModels.SearchDistrict;
import com.assignment.location.models.responseModels.SearchState;
import com.assignment.location.models.responseModels.SearchTown;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author anuragdhunna
 */
public interface LocationServices {

    Geo storeGeo(Geo geo);

    GeoAssoc storeGeoAssoc(GeoAssoc geoAssoc);

    List<SearchState> searchState(String searchText);

    List<SearchTown> searchTown(String searchText);

    List<SearchDistrict> searchDistrict(String searchText);

    String uploadLocationCsv(MultipartFile file) throws IOException;
}
