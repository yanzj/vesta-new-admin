package com.maxrocky.vesta.application.DTO;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 门禁开门日志列表，搜索参数以及列表显示字段
 * Created by 27978 on 2016/11/16.
 */
public class PropertyDoorLogDTO {
    private String scopeId;         //区域编码

    private String projectCode;     //项目编码
    private String projectName;     //项目名称

    private String userName;       //用户名称
    private String userPhone;      //用户电话

    private String macName;        //设备名称
    private String macAddress;     //设备地址
    private String deviceDesc;     //设备描述

    private String startDate;         //日期设定-开门时间1
    private String endDate;           //日期设定-开门时间2

    private Date openDateTime;       //开门时间

    /* 数据区域范围权限 */
    private List<Map<String,Object>> roleScopeList;

    private String menuId;            //菜单id

    public String getMacName() {
        return macName;
    }

    public void setMacName(String macName) {
        this.macName = macName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Date getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(Date openDateTime) {
        this.openDateTime = openDateTime;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
