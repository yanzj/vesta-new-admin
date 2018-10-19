package com.maxrocky.vesta.application.DTO;

/**
 * Created by 27978 on 2016/9/7.
 */
public class SMSEditDTO {
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
