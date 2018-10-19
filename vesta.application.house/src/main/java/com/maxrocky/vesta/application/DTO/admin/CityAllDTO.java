package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/5/19.
 */
public class CityAllDTO {

    // 城市列表
    private String city;
    //小区列表
    private List buildingList;

    public CityAllDTO() {
        this.buildingList = new ArrayList();
    }

    public List getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List buildingList) {
        this.buildingList = buildingList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
