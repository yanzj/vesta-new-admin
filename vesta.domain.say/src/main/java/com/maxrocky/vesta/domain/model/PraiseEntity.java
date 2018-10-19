package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/20.
 * 表扬实体
 */
@Entity
@Table(name="isay_praise")
public class PraiseEntity {

    public final static String READSTATUS_YES = "1";//已读
    public final static String READSTATUS_NOT = "0";//未读

    public static final String STATUS_VALID="1";           //正常(状态)
    public static final String STATUS_INVALID="2";         //删除(状态)

    public static final String REPLY_YES="1";       //已回复(是否回复)
    public static final String REPLY_NO="2";        //未回复(是否回复)

    private String id;             //表扬ID
    private String userId;         //用户ID
    private String targetId;       //对象ID
    private String targetName;     //表扬对象
    private String projectId;      //项目Id
    private String projectName;    //项目名称
    private String codenum;        //单号
    private Date crtTime;          //时间
    private String content;        //内容
    private String level;          //星级
    private String readStatus;     //读取状态 0-未读，1-已读
    private String address;        //地址
    private String userName;       //用户名
    private String mobile;         //手机号
    private String status;         //状态 1正常 2删除
    private String reply;          //是否已回复 1 已回复 2 未回复

    @Basic
    @Column(name="CODE_NUM",length=32)
    public String getCodenum() {
        return codenum;
    }

    public void setCodenum(String codenum) {
        this.codenum = codenum;
    }

    @Basic
    @Column(name="CONTENT",length = 500)
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
    @Column(name="ID",length = 32,nullable = false,unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="LEVEL",length = 10)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name="TARGET_NAME",length = 30)
    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    @Basic
    @Column(name="USER_ID",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name="TARGET_ID",length = 32)
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Basic
    @Column(name="READSTATUS",length = 32)
    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    @Basic
    @Column(name="ADDRESS",length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name="MOBILE",length = 13)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name="USER_NAME",length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name="IS_REPLY",length = 3)
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Basic
    @Column(name="STATUS",length = 3)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name="PROJECT_ID",length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name="PROJECT_NAME",length = 20)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
