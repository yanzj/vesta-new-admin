package com.maxrocky.vesta.application.AdminDTO;


import java.util.List;

/**
 * Created by chen on 2016/2/19.
 * 后台表扬数据封装类
 */
public class PraiseAdminDTO {
    /**搜索条件部分*/
    private String beginTime;  //开始时间
    private String endTime;    //结束时间

    private String id;             //表扬ID
    private String userId;         //用户ID
    private String targetId;       //对象ID
    private String targetName;     //表扬对象
    private String projectIds;      //项目Id
    private String projectName;    //项目名称
    private String codenum;        //单号
    private String crtTime;        //时间
    private String content;        //内容
    private String level;          //星级
    private String readStatus;     //读取状态 0-未读，1-已读
    private String address;        //地址
    private String userName;       //用户名
    private String mobile;         //手机号
    private String status;         //状态 1正常 2删除
    private String reply;          //是否已回复 1 已回复 2 未回复
    private String answerUser;     //回复人
    private String answerContent;  //回复内容
    private String answerTime;     //回复时间
    private List<ConsultImgDTO> imgList; // 表扬图片集合

    public List<ConsultImgDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ConsultImgDTO> imgList) {
        this.imgList = imgList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
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
}
