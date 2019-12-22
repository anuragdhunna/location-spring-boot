package com.assignment.location.models.responseModels;

import com.assignment.location.models.Geo;

/**
 * @author anuragdhunna
 */
public class SearchDistrict {

    private String distName;
    private Integer distCode;
    private String town;
    private String urbanStatus;
    private Integer stateCode;
    private String state;


    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public Integer getDistCode() {
        return distCode;
    }

    public void setDistCode(Integer distCode) {
        this.distCode = distCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getUrbanStatus() {
        return urbanStatus;
    }

    public void setUrbanStatus(String urbanStatus) {
        this.urbanStatus = urbanStatus;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
