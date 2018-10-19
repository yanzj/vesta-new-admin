package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luxinxin on 2016/7/21.
 */
@Entity
@Table(name = "user_visit_log")
public class UserVisitLogEntity {
    private String logId;           //主键
    private String userName;        //用户名
    private String userType;        //用户类型
    private String userMobile;      //手机号
    private Date logTime;           //时间
    private String thisSection;     //版块
    private String thisFunction;    //功能
    private String LogContent;      //内容
    private String sourceFrom;      //来源 1微信 2安卓 3IOS
    private String projectId;       //项目ID
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
}
