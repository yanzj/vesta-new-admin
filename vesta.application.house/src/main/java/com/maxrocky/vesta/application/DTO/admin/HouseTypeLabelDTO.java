package com.maxrocky.vesta.application.DTO.admin;

import java.math.BigDecimal;

/**
 * Created by mql on 2016/6/3.
 */
public class HouseTypeLabelDTO {

    private String id;
    private String name;
    private String typeId;//户型ID
    private String xNum1;//X位置1
    private String xNum2;//X位置2
    private String yNum1;//Y位置1
    private String yNum2;//Y位置2
    private String locationId;//位置ID

    public HouseTypeLabelDTO() {
        this.id = "";
        this.name = "";
        this.typeId = "";
        this.xNum1 = "";
        this.xNum2 = "";
        this.yNum1 = "";
        this.yNum2 = "";
        this.locationId = "";
    }

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getxNum1() {
        return xNum1;
    }

    public void setxNum1(String xNum1) {
        this.xNum1 = xNum1;
    }

    public String getxNum2() {
        return xNum2;
    }

    public void setxNum2(String xNum2) {
        this.xNum2 = xNum2;
    }

    public String getyNum1() {
        return yNum1;
    }

    public void setyNum1(String yNum1) {
        this.yNum1 = yNum1;
    }

    public String getyNum2() {
        return yNum2;
    }

    public void setyNum2(String yNum2) {
        this.yNum2 = yNum2;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
