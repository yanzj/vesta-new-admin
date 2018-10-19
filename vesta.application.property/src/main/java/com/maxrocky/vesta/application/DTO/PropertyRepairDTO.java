package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/14.
 * 业主端：物业报修单详情界面/管理端：详情页面
 */
public class PropertyRepairDTO {
    private String id;//报修单id
    private String content;//内容
    private String grade;//等级；分值
    private String userId;//对员工评价人
    private String evaluateContent;//评价内容
    private String repairWay;//来源
    private String userAddress;//业主地址
    private String userName;//业主姓名
    private List<PropertyImageDTO> imageList;//图片路径(创建报修单)
    private List<PropertyImageDTO> imagedList;//图片路径(维修完成后，image的过去分词imaged，代表完成后的)

    public PropertyRepairDTO() {
        this.id = "";
        this.content = "";
        this.grade="";
        this.evaluateContent="";
        this.userId="";
        this.repairWay="";
        this.userAddress="";
        this.userName="";
        this.imageList = new ArrayList<PropertyImageDTO>();
        this.imagedList=new ArrayList<PropertyImageDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PropertyImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<PropertyImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public List<PropertyImageDTO> getImagedList() {
        return imagedList;
    }

    public void setImagedList(List<PropertyImageDTO> imagedList) {
        this.imagedList = imagedList;
    }

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}