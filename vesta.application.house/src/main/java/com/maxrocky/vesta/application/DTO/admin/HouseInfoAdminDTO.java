package com.maxrocky.vesta.application.DTO.admin;

import java.math.BigDecimal;

/**
 * Created by Tom on 2016/1/21 14:28.
 * Describe:HouseInfoEntity DTO for UI0002
 */
public class HouseInfoAdminDTO {

    private String id;//主键
    private String companyId;//公司Id
    private String companyName;//公司名称
    private String projectId;//项目Id
    private String projectName;//项目名称
    private String buildingId;//楼Id
    private String buildingName;//楼名称
    private String unitId;//单元Id
    private String unitName;//单元名称
    private String roomId;//房间Id
    private String roomName;//房间号
    private String street;//街道
    private String formatId;//业态Id
    private String formatName;//业态名称
    private String address;//地址
    private String houseType;//户型
    private BigDecimal buildingArea;//建筑面积
    private BigDecimal usableArea;//使用面积
    private int viewAppHouseId;//物业房产Id

    public HouseInfoAdminDTO() {
        this.id = "";
        this.companyId = "";
        this.companyName = "";
        this.projectId = "";
        this.projectName = "";
        this.buildingId = "";
        this.buildingName = "";
        this.unitId = "";
        this.unitName = "";
        this.roomId = "";
        this.roomName = "";
        this.street = "";
        this.formatId = "";
        this.formatName = "";
        this.address = "";
        this.houseType = "";
        this.buildingArea = BigDecimal.ZERO;
        this.usableArea = BigDecimal.ZERO;
        this.viewAppHouseId = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public BigDecimal getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(BigDecimal usableArea) {
        this.usableArea = usableArea;
    }

    public int getViewAppHouseId() {
        return viewAppHouseId;
    }

    public void setViewAppHouseId(int viewAppHouseId) {
        this.viewAppHouseId = viewAppHouseId;
    }
}
