package com.maxrocky.vesta.application.DTO;

/**
 * Created by 27978 on 2016/9/7.
 */
public class SMSPeopleAlertsDTO {
    private String phone;//人员手机号
    private String name;//人员姓名
    private String model;//提醒模块
    private String modelList;//提醒模块列表
    private String operator;//操作人
    private String cityIds;      //城市Id集合
    private String projectIds;  //项目Id集合
    private String cityNum;//城市编号
    private String projectNum;//项目编号

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

    public String getModelList() {
        return modelList;
    }

    public void setModelList(String modelList) {
        this.modelList = modelList;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
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
