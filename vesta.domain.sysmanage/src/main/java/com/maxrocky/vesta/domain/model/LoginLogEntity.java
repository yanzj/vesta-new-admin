package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunmei on 2016/2/18.
 * 登陆日志
 */
@Entity
@Table(name = "login_log")
public class LoginLogEntity {
    private String id;
    private String projectId;//项目id
    private String regionId;//区域id
    private String name;
    private String userName;
    private String project;
    private Date logintime;
    private String userIp;
    private String loginMsg;
    private String equip;
    private String accountType;
    private Date startDate;//开始时间
    private Date endDate;//结束时间

    @Id
    @Column(name = "ID",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "name",length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "user_Name",length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Basic
    @Column(name = "project",length = 100)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
    @Basic
    @Column(name = "login_time")
    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
    @Basic
    @Column(name = "user_Ip",length = 100)
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userId) {
        this.userIp = userId;
    }
    @Basic
    @Column(name = "login_msg",length = 100)
    public String getLoginMsg() {
        return loginMsg;
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
    }
    @Basic
    @Column(name = "equip",length = 100)
    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }
    @Basic
    @Column(name = "account_type",length = 100)
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    @Basic
    @Column(name = "project_Id",length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "region_Id",length = 100)
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }



    @Transient
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Transient
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
