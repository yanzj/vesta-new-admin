package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/20.
 * 投诉实体
 */

@Entity
@Table(name="isay_complaint")
public class ComplaintEntity {
    public static final String STATUS_VALID="1";           //正常(状态)
    public static final String STATUS_INVALID="2";         //删除(状态)

    private String id;          //投诉ID
    private String userId;      //用户ID
    private String userName;    //用户名
    private String content;     //投诉内容
    private Date crtTime;       //投诉时间
    private String bussinessId; //事物ID
    private String address;     //地址
    private String mobile;      //手机号码
    private String status;      //状态
    private String projectId;   //项目Id
    private String projectName; //项目名称
    private String codeNum;     //投诉单号

    @Basic
    @Column(name="BUSSINESS_ID",length = 32)
    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
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
    @Column(name = "ID",length = 32,unique = true,nullable = false)
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
    @Column(name = "PROJECT_ID",length = 32)
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

    @Basic
    @Column(name="STATUS",length = 3)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name="CODE_NUM",length = 32)
    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }
}
