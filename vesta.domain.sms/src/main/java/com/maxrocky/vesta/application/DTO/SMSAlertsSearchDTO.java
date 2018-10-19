package com.maxrocky.vesta.application.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/9/7.
 */
public class SMSAlertsSearchDTO {
    private String phone;//人员手机号
    private String name;//人员姓名
    private String model;//提醒模块
    private String cityNum;//城市编号
    private String projectNum;//项目编号
    private String projectName;//项目名称

    /* 新增查询字段(用户权限范围)*/
    private List<Map<String,Object>> roleScopeList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
