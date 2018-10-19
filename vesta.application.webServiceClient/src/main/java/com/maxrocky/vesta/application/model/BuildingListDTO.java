package com.maxrocky.vesta.application.model;

import java.util.List;

/**
 * Created by Magic on 2016/5/30.
 */
public class BuildingListDTO {
    private String buildingId;
    private String buildingName;
    private String buildingNum;
    private List unitList;
    private String projectNum;
    public BuildingListDTO(String projectNum,String buildingId,String buildingName,String buildingNum,List unitList){
        this.buildingId=buildingId;
        this.projectNum=projectNum;
        this.buildingName=buildingName;
        this.buildingNum=buildingNum;
        this.unitList=unitList;
    }
    public BuildingListDTO(){
        buildingId="";
        projectNum="";
        buildingName="";
        buildingNum="";
        unitList=null;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
