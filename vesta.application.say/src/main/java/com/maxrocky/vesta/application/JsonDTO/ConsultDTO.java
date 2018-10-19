package com.maxrocky.vesta.application.JsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/1/22.
 * 咨询 页面展示 封装类
 */
public class ConsultDTO {
    private String id;                 //咨询ID
    private String userId;             //用户ID
    private String houseInfoId;        //项目Id
    private String userName;           //用户名
    private String mobile;             //手机号
    private String content;            //咨询内容
    private String crtTime;            //咨询时间
    private List<ImageDTO> imageList;  //图片列表
    private String answerTime;         //回复时间
    private String answerUserId;       //回复人ID
    private String answerContent;      //回复内容
    private String address;            //地址
    private String isAnswer;           //是否已反馈

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(String answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getHouseInfoId() {
        return houseInfoId;
    }

    public void setHouseInfoId(String houseInfoId) {
        this.houseInfoId = houseInfoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }
}
