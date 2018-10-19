package com.maxrocky.vesta.application.admin.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/4/14.
 * 用户预约信息
 */
public class UserAppointDto {

    private String appointId;//预约信息id

    private String appointUserId;//用户id

    private String appointHouseId;//预约房间信息id

    private String appointProjectId;//项目Id
    private String projectName;//项目名称

    private String appointUnitId;//单元Id
    private String unitName;//单元名称

    private String appointBuildId;//楼号id
    private String buildName;//楼号

    private String appointRoomId;//房间id
    private String roomName;//房间名称

    private String planName;//交付批次

    private String amOrPm;//时段

    private String userMobile;//用户手机号

    private String appointUserTime;//预约时间

    private String startTime;

    private String endTime;

    private String userName;//用户姓名

    private String idCard;//用户身份证

    /* 新增查询字段(用户权限范围)_2016-08-31_Wyd */
    private List<Map<String, Object>> roleScopeList;
    /* ------------------------------------- */
    /*(房屋地址)_2016-09-26_jzf */
    private String roomAddress;

    private String menuId;      //菜单ID
    private String scopeId;     //区域ID

    public String getAppointId() {
        return appointId;
    }

    public void setAppointId(String appointId) {
        this.appointId = appointId;
    }

    public String getAppointUserId() {
        return appointUserId;
    }

    public void setAppointUserId(String appointUserId) {
        this.appointUserId = appointUserId;
    }

    public String getAppointHouseId() {
        return appointHouseId;
    }

    public void setAppointHouseId(String appointHouseId) {
        this.appointHouseId = appointHouseId;
    }

    public String getAppointProjectId() {
        return appointProjectId;
    }

    public void setAppointProjectId(String appointProjectId) {
        this.appointProjectId = appointProjectId;
    }

    public String getAppointUnitId() {
        return appointUnitId;
    }

    public void setAppointUnitId(String appointUnitId) {
        this.appointUnitId = appointUnitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAppointBuildId() {
        return appointBuildId;
    }

    public void setAppointBuildId(String appointBuildId) {
        this.appointBuildId = appointBuildId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getAmOrPm() {
        return amOrPm;
    }

    public void setAmOrPm(String amOrPm) {
        this.amOrPm = amOrPm;
    }

    public String getAppointUserTime() {
        return appointUserTime;
    }

    public void setAppointUserTime(String appointUserTime) {
        this.appointUserTime = appointUserTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAppointRoomId() {
        return appointRoomId;
    }

    public void setAppointRoomId(String appointRoomId) {
        this.appointRoomId = appointRoomId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }


    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }
}
