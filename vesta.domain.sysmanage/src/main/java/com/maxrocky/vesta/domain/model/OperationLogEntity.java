package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunmei on 2016/2/18.
 * 后台核心日志
 */
@Entity
@Table(name = "operation_log")
public class OperationLogEntity {
    private String id;
    private String name;
    private String userName;
    private Date time;
    private String content;
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    private String projectId;//项目

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
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    @Basic
    @Column(name = "content",length = 100)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Transient
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Transient
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "projectId",length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
