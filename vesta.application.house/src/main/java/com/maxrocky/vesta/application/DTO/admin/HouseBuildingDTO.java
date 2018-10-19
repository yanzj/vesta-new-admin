package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;

/**
 * Created by Talent on 2016/11/18.
 */
public class HouseBuildingDTO {
    private String id;//主键  (buildingId)
    private String name;//别名
    private String street;//街道
    private String buildingRecord;//备案楼号
    private String buildingNum;//楼号编码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }
}
