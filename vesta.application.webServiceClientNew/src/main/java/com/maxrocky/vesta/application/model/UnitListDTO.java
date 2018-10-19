package com.maxrocky.vesta.application.model;

import java.util.List;

/**
 * Created by Magic on 2016/5/30.
 */
public class UnitListDTO {
    private String unitId;      //单元
    private String unitName;    //
    private String unitNum;
    private List floorList;
    private String projectNum;
    public UnitListDTO(String projectNum,String unitId,String unitName,String unitNum,List floorList){
        this.unitId=unitId;
        this.projectNum=projectNum;
        this.unitName=unitName;
        this.unitNum=unitNum;
        this.floorList=floorList;
    }
    public UnitListDTO(){
        unitId="";
        projectNum="";
        unitName="";
        unitNum="";
        floorList=null;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public List getFloorList() {
        return floorList;
    }

    public void setFloorList(List floorList) {
        this.floorList = floorList;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
