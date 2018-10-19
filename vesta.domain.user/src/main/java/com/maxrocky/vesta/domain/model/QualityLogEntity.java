package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 质检访问日志
 * Created by Talent on 2016/11/16.
 */
@Entity
@Table(name = "QUALITY_LOG")
public class QualityLogEntity {
    private String logId;//日志ID
    private String userName;//用户名
    private String staffName;//名称
    private String userMobile;//手机号
    private String sourceFrom;// 来源
    private String projectName;// 项目名
    private String projectId;//项目ID
    private Date sysTime;// 时间
    private String sysContent;//内容
    private String sysDocType;//单据类型
    private String sysDocNum;// 单据编号
    private String sysStatus;// 状态一类
    private String sysSection;// 版块
    private String sysFunction;// 功能
    private String ipAddress;//ip地址

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "STAFF_NAME", nullable = true, length = 50)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "USER_MOBILE", nullable = true, length = 50)
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @Basic
    @Column(name = "SOURCE_FROM", nullable = true, length = 50)
    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "PROJECT_ID", nullable = true, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "SYS_CONTENT", nullable = true, length = 100)
    public String getSysContent() {
        return sysContent;
    }

    public void setSysContent(String sysContent) {
        this.sysContent = sysContent;
    }

    @Basic
    @Column(name = "SYS_DOC_TYPE", nullable = true, length = 50)
    public String getSysDocType() {
        return sysDocType;
    }

    public void setSysDocType(String sysDocType) {
        this.sysDocType = sysDocType;
    }

    @Basic
    @Column(name = "SYS_DOC_NUM", nullable = true, length = 50)
    public String getSysDocNum() {
        return sysDocNum;
    }

    public void setSysDocNum(String sysDocNum) {
        this.sysDocNum = sysDocNum;
    }

    @Basic
    @Column(name = "SYS_STATUS", nullable = true, length = 50)
    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    @Basic
    @Column(name = "SYS_SECTION", nullable = true, length = 50)
    public String getSysSection() {
        return sysSection;
    }

    public void setSysSection(String sysSection) {
        this.sysSection = sysSection;
    }

    @Basic
    @Column(name = "SYS_FUNCTION", nullable = true, length = 50)
    public String getSysFunction() {
        return sysFunction;
    }

    public void setSysFunction(String sysFunction) {
        this.sysFunction = sysFunction;
    }

    @Basic
    @Column(name = "SYS_TIME", nullable = true)
    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    @Basic
    @Column(name = "IP_ADDRESS", nullable = true, length = 50)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
