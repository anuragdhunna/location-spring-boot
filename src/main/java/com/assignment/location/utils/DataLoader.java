package com.assignment.location.utils;

import com.assignment.location.controllers.services.LocationServices;
import com.assignment.location.models.Geo;
import com.assignment.location.models.GeoAssoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author anuragdhunna
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);


    private LocationServices locationServices;

    public DataLoader(LocationServices locationServices) {
        this.locationServices = locationServices;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data...");

//        locationServices.storeGeo(new Geo("Punjab", Geo.Type.STATE, 50, null));
//        locationServices.storeGeo(new Geo("Haryana", Geo.Type.STATE, 80, null));
//
//        locationServices.storeGeo(new Geo("Patiala", Geo.Type.DISTRICT, 11, null));
//        locationServices.storeGeo(new Geo("Mohali", Geo.Type.DISTRICT, 65, null));
//        locationServices.storeGeo(new Geo("Kharar", Geo.Type.CITY, 67, "C.T."));
//
//        locationServices.storeGeoAssoc(new GeoAssoc(50, 11, GeoAssoc.RelationType.STATE_DIST));
//        locationServices.storeGeoAssoc(new GeoAssoc(50, 65, GeoAssoc.RelationType.STATE_DIST));
//        locationServices.storeGeoAssoc(new GeoAssoc(65, 67, GeoAssoc.RelationType.DIST_CITY));

        logger.info("Data Loaded Successfully");
    }
}
