package com.maxrocky.vesta.application.JsonDTO;


import java.util.List;

/**
 * Created by chen on 2016/1/22.
 * 表扬前台页面数据DTO
 */
public class PraiseDTO {
    private String id;             //表扬ID
    private String userId;         //用户ID
    private String targetId;       //对象ID
    private String targetName;     //表扬对象
    private String codenum;        //单号
    private String crtTime;        //时间
    private String content;        //内容
    private String level;          //星级
    List<ImageDTO> imageList;      //图片列表
    private String address;        //地址
    private String userName;       //用户名
    private String mobile;         //手机号
    private String answerTime;     //回复时间
    private String answerUserId;   //回复人ID
    private String answerContent;  //回复内容
    private String isAnswer;       //是否已反馈

    public String getCodenum() {
        return codenum;
    }

    public void setCodenum(String codenum) {
        this.codenum = codenum;
    }

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
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

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }
}
