package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luxinxin on 2016/7/27.
 */
@Entity
@Table(name = "info_Release_log")
public class InfoReleaseEntity {
    private String logId;           //主键
    private String userName;        //用户名
    private String userType;        //用户类型
    private String userMobile;      //手机号
    private String idCard;          //身份证
    private Date logTime;           //时间
    private String LogContent;      //内容
    private String thisType;        //操作类型或报修单类型 等 泛指日志自身里面的类型
    private String thisSection;     //版块
    private String thisFunction;    //功能
    private String projectId;       //项目ID
    private String sourceFrom;      //来源 1微信 2安卓 3IOS
    private String asscommunity;           //关联社区

    @Basic
    @Column(name = "ASS_COMMUNITY",length = 500)
    public String getAsscommunity() {
        return asscommunity;
    }

    public void setAsscommunity(String asscommunity) {
        this.asscommunity = asscommunity;
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
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "ID_CARD",length = 50)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Id
    @Column(name = "LOG_ID",length = 100)
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
    @Column(name = "THIS_TYPE",length = 30)
    public String getThisType() {
        return thisType;
    }

    public void setThisType(String thisType) {
        this.thisType = thisType;
    }
}
