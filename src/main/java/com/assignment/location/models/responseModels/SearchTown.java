package com.assignment.location.models.responseModels;

/**
 * @author anuragdhunna
 */
public class SearchTown {

    private String town;
    private String state;
    private String district;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
