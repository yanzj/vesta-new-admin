package com.maxrocky.vesta.application.DTO;

import java.util.Map;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 用户登录统计 DTO
 */
public class UserLoginStatisticDTO {
    private String city;//城市
    private String projectName;//项目小区
    private Integer registeredNumber = 0;//注册人数
    private Integer landingNumber = 0;//登录次数
    private String commonUser;//普通会员注册人数
    private String owner;//业主注册人数
    private String android;//安卓用户
    private String ios;//ios用户
    private String app;//app用户
    private String weChat;//微信用户
    private String male;//男性用户
    private String female;//女性用户
    private String total;//合计
    private String startTime;
    private String endTime;
    private String repairNum;//报修统计
    private String feedbackNum;//反馈数量
    private String visitorNum;//访客数量
    private String paymentNum;//缴费数量
    private String name;//名称
    private String clicks;//点击次数
    private String clickUserNum;//点击人数
    private Map<String,String> cityMap;//城市下拉框
    private Map<String,String> projectMap;//项目下拉框

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(Integer registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public Integer getLandingNumber() {
        return landingNumber;
    }

    public void setLandingNumber(Integer landingNumber) {
        this.landingNumber = landingNumber;
    }

    public String getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(String commonUser) {
        this.commonUser = commonUser;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getIos() {
        return ios;
    }

    public void setIos(String ios) {
        this.ios = ios;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(String repairNum) {
        this.repairNum = repairNum;
    }

    public String getFeedbackNum() {
        return feedbackNum;
    }

    public void setFeedbackNum(String feedbackNum) {
        this.feedbackNum = feedbackNum;
    }

    public String getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(String visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getClickUserNum() {
        return clickUserNum;
    }

    public void setClickUserNum(String clickUserNum) {
        this.clickUserNum = clickUserNum;
    }

    public Map<String, String> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, String> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, String> getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }
}
