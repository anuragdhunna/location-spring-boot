package com.assignment.location.models.responseModels;

/**
 * @author anuragdhunna
 */
public class SearchState {

    private String state;
    private String distName;
    private Integer distCode;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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
}
