package com.maxrocky.vesta.application.DTO;

import java.util.List;
import java.util.Map;

/**
 * 产权管理DTO
 * Created by WeiYangDong on 2017/6/21.
 */
public class PropertyManageDTO {

    private String menuId;   //菜单ID
    private List<Map<String,Object>> roleScopeList;     //权限列表

    private String scopeId;     //区域Id
    private String cityName;    //城市名称

    private String projectCode; //项目编码
    private String projectName; //项目名称

    private String blockCode;   //地块编码
    private String area;        //地块

    private String buildingNum;     //楼号编码
    private String buildingRecord;  //备案楼号
    private String buildingSale;    //预售楼号

    private String unitCode;        //单元编码
    private String unit;            //单元

    private String floorCode;       //楼层编码
    private String floor;           //楼层

    private String roomNum;         //房屋编码
    private String roomName;        //房屋号
    private String roomAddress;     //房屋地址
    private String ownerName;       //业主姓名

    private String propertyId;      //产权Id
    private String handleMode;      //办理方式
    private String handleProgress;  //办理进度
    private String handleStatus;    //办理状态(0,正常状态;1,待提交资料)


    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    public String getBuildingSale() {
        return buildingSale;
    }

    public void setBuildingSale(String buildingSale) {
        this.buildingSale = buildingSale;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getHandleMode() {
        return handleMode;
    }

    public void setHandleMode(String handleMode) {
        this.handleMode = handleMode;
    }

    public String getHandleProgress() {
        return handleProgress;
    }

    public void setHandleProgress(String handleProgress) {
        this.handleProgress = handleProgress;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }
}
