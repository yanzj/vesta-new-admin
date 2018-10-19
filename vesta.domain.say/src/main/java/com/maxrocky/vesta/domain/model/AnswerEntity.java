package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/21.
 * 反馈实体
 */

@Entity
@Table(name="isay_answer")
public class AnswerEntity {
    public final static String answer_consult="1";
    public final static String answer_complaint="2";
    public final static String answer_praise="3";
    public final static String answer_reply="4";

    private String id;             //回复ID
    private String consultId;      //关联ID
    private String userId;         //用户ID
    private String content;        //回复内容
    private Date crtTime;          //回复时间
    private String answerType;     //回复类型   1.咨询  2.投诉  3.表扬  4.随手拍

    @Basic
    @Column(name="CONSULT_ID",length = 100,nullable = false)
    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }

    @Basic
    @Column(name="CONTENT",length = 500,nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name="CREATE_TIME",length = 20)
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    @Id
    @Column(name="ID",nullable = false,unique = true,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="USER_ID",length = 32,nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name="ANSWER_TYPE",length=2,nullable = false)
    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }
}
