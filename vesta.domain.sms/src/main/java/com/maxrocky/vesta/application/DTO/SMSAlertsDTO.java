package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/8/31.
 */
public class SMSAlertsDTO {

    private String id;//
    private String phone;//人员手机号
    private String name;//人员姓名
    private String model;//提醒模块
    private String modelList;//提醒模块列表
    private String content;//短信文本
    private String scope;//数据查看范围
    private String repairModelNum;//房屋报修管理模块编号
    private String repairContent;//房屋报修管理短信内容
    private String appealModelNum;//身份申诉管理模块编号
    private String appealContent;//身份申诉管理短信内容
    private String activityModelNum;//活动报名管理模块编号
    private String activityContent;//活动报名管理短信内容
    private String paymentModelNum;//物业缴费管理模块编号
    private String paymentContent;//物业缴费管理短信内容
    private String operator;//操作人
    private String cityNum;//城市编号
    private String projectNum;//项目编号

    private String cityIds;      //城市Id集合
    private String projectIds;  //项目Id集合

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getModelList() {
        return modelList;
    }

    public void setModelList(String modelList) {
        this.modelList = modelList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRepairModelNum() {
        return repairModelNum;
    }

    public void setRepairModelNum(String repairModelNum) {
        this.repairModelNum = repairModelNum;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public String getAppealModelNum() {
        return appealModelNum;
    }

    public void setAppealModelNum(String appealModelNum) {
        this.appealModelNum = appealModelNum;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    public String getActivityModelNum() {
        return activityModelNum;
    }

    public void setActivityModelNum(String activityModelNum) {
        this.activityModelNum = activityModelNum;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getPaymentModelNum() {
        return paymentModelNum;
    }

    public void setPaymentModelNum(String paymentModelNum) {
        this.paymentModelNum = paymentModelNum;
    }

    public String getPaymentContent() {
        return paymentContent;
    }

    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent;
    }
}
