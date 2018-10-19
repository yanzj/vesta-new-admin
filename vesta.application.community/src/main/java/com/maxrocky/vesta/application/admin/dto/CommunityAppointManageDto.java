package com.maxrocky.vesta.application.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/4/13
 * Time: 11:43
 * 后台系统预约管理页面
 */
public class CommunityAppointManageDto implements Serializable {

    private String id;              //
    private String url;             //大图路径地址
    private String communityName;     //社区项目名称
    private String communityId;     //社区项目Id
    private String houseName;       //房屋信息_用户输入
    private String content;         //业主输入内容
    private String payStaDate;         //集中交付开始
    private String payEndDate;         //集中交付结束
    private String appointStaDate;         //预约交付时间开始
    private String appointEndDate;         //预约交付时间结束
    private String status;                 //房屋预约交付状态  0，进行中， 1，已完成
    private Integer amOrPm;                   //0:上午，1：下午
    private String userName;                //业主姓名
    private String idCard;                  //业主身份证号
    private String mobile;                  //业主手机号码
    private String deliveryBatch;     //交付批次名称
    private String maxUser;     //每时间段最大人数
    private String description; //批次描述信息




    private List<CommunityAppointManageDto> communityHousePays = new ArrayList<CommunityAppointManageDto>(); //自关联

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPayStaDate() {
        return payStaDate;
    }

    public void setPayStaDate(String payStaDate) {
        this.payStaDate = payStaDate;
    }

    public String getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(String payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getAppointStaDate() {
        return appointStaDate;
    }

    public void setAppointStaDate(String appointStaDate) {
        this.appointStaDate = appointStaDate;
    }

    public String getAppointEndDate() {
        return appointEndDate;
    }

    public void setAppointEndDate(String appointEndDate) {
        this.appointEndDate = appointEndDate;
    }


    public List<CommunityAppointManageDto> getCommunityHousePays() {
        return communityHousePays;
    }

    public void setCommunityHousePays(List<CommunityAppointManageDto> communityHousePays) {
        this.communityHousePays = communityHousePays;
    }

    public Integer getAmOrPm() {
        return amOrPm;
    }

    public void setAmOrPm(Integer amOrPm) {
        this.amOrPm = amOrPm;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    public String getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(String maxUser) {
        this.maxUser = maxUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}
