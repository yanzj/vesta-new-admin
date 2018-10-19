package com.maxrocky.vesta.application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/14.
 */
public class BuildingListTimeDTO {
    private String datetime;
    private List<BuildingListDTOJson> BuildingList;
    public BuildingListTimeDTO(){
        this.datetime="";
        this.BuildingList=new ArrayList<>();
    }
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<BuildingListDTOJson> getBuildingList() {
        return BuildingList;
    }

    public void setBuildingList(List<BuildingListDTOJson> buildingList) {
        BuildingList = buildingList;
    }
}
