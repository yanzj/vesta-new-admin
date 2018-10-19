package com.maxrocky.vesta.application.AdminDTO;

/**
 * Created by chen on 2016/2/20.
 */
public class AnswerAdminDTO {
    private String id;             //回复ID
    private String consultId;      //关联ID
    private String userId;         //用户ID
    private String content;        //回复内容
    private String crtTime;        //回复时间
    private String answerType;     //回复类型   1.咨询  2.投诉  3.表扬

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
