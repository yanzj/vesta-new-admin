package com.maxrocky.vesta.application.AdminDTO;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/1.
 * 物业咨询 DTO
 */
public class PropertyConsultDTO {
    private String id;                 //咨询ID
    private String userId;             //用户ID
    private String userName;           //用户名
    private String mobile;             //手机
    private String project;          //项目Id
    private String projectName;        //项目名称
    private String address;            //地址
    private String content;            //咨询内容
    private String crtTime;            //咨询时间
    private String answercCrtTime;     //回复时间
    private String crtTimeStart;       //咨询时间开始
    private String crtTimeEnd;         //咨询时间结束
    private String answerTime;         //回复时间
    private String answerUserId;       //回复人ID
    private String answerContent;      //回复内容
    private String replyStatus;        //是否回复
    private String queryScope;         //查询负责范围(模块条件)
    private List<ConsultImgDTO> imgList; // 咨询图片集合

    public List<ConsultImgDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ConsultImgDTO> imgList) {
        this.imgList = imgList;
    }

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }

    public String getAnswercCrtTime() {
        return answercCrtTime;
    }

    public void setAnswercCrtTime(String answercCrtTime) {
        this.answercCrtTime = answercCrtTime;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCrtTimeStart() {
        return crtTimeStart;
    }

    public void setCrtTimeStart(String crtTimeStart) {
        this.crtTimeStart = crtTimeStart;
    }

    public String getCrtTimeEnd() {
        return crtTimeEnd;
    }

    public void setCrtTimeEnd(String crtTimeEnd) {
        this.crtTimeEnd = crtTimeEnd;
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

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}
