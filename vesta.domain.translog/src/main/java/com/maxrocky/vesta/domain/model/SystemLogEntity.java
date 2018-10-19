package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/7/19.
 */
@Entity
@Table(name = "system_log")
public class SystemLogEntity {
    private String logId;           //主键
    private String userName;        //用户名
    private String userType;        //用户类型
    private String userMobile;      //手机号
    private String idCard;          //身份证
    private String sysVersion;      //系统版本
    private String sourceFrom;      //来源 1微信 2安卓 3IOS
    private String projectId;       //项目ID
    private String logType;         //日志类型 1新增用户 2用户访问 3用户单据 4信息发布
    private Date logTime;           //时间
    private String LogContent;      //内容
    private String thisType;        //操作类型或报修单类型 等 泛指日志自身里面的类型
    private String docNum;          //单据编号
    private String thisStatus;      //单据状态 什么的 泛指状态一类
    private String thisSection;     //版块
    private String thisFunction;    //功能


    @Basic
    @Column(name = "ID_CARD",length = 50)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Id
    @Column(name = "LOG_ID",nullable = false,unique = true,length = 100)
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "USER_NAME",length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "SOURCE_FROM",length = 50)
    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    @Basic
    @Column(name = "SYSTEM_VERSION",length = 50)
    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    @Basic
    @Column(name = "USER_MOBILE",length = 30)
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @Basic
    @Column(name = "USER_TYPE",length = 10)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "DOC_NUMBER",length = 50)
    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    @Basic
    @Column(name = "LOG_CONTENT",length = 800)
    public String getLogContent() {
        return LogContent;
    }

    public void setLogContent(String logContent) {
        LogContent = logContent;
    }

    @Basic
    @Column(name = "LOG_TIME")
    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Basic
    @Column(name = "LOG_TYPE",length = 10)
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "THIS_FUNCTION",length = 50)
    public String getThisFunction() {
        return thisFunction;
    }

    public void setThisFunction(String thisFunction) {
        this.thisFunction = thisFunction;
    }

    @Basic
    @Column(name = "THIS_SECTION",length = 50)
    public String getThisSection() {
        return thisSection;
    }

    public void setThisSection(String thisSection) {
        this.thisSection = thisSection;
    }

    @Basic
    @Column(name = "THIS_STATUS",length = 20)
    public String getThisStatus() {
        return thisStatus;
    }

    public void setThisStatus(String thisStatus) {
        this.thisStatus = thisStatus;
    }

    @Basic
    @Column(name = "THIS_TYPE",length = 30)
    public String getThisType() {
        return thisType;
    }

    public void setThisType(String thisType) {
        this.thisType = thisType;
    }
}
