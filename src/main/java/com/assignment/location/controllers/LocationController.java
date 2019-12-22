package com.assignment.location.controllers;

import com.assignment.location.controllers.services.LocationServices;
import com.assignment.location.models.Geo;
import com.assignment.location.models.responseModels.SearchDistrict;
import com.assignment.location.models.responseModels.SearchState;
import com.assignment.location.models.responseModels.SearchTown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author anuragdhunna
 */
@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    LocationServices locationServices;


    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public List<SearchState> searchState(@RequestParam String q) {
        return locationServices.searchState(q);
    }

    @RequestMapping(value = "/town", method = RequestMethod.GET)
    public List<SearchTown> searchTown(@RequestParam String q) {
        return locationServices.searchTown(q);
    }

    @RequestMapping(value = "/district", method = RequestMethod.GET)
    public List<SearchDistrict> searchDistrict(@RequestParam String q) {
        return locationServices.searchDistrict(q);
    }

    @RequestMapping(value = "/uploadLocationCsv", method = RequestMethod.POST)
    public String  uploadLocationCsv(@RequestParam("file") MultipartFile file) throws Exception{
        return locationServices.uploadLocationCsv(file);
    }

}
