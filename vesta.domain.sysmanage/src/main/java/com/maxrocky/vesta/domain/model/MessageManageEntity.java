package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunmei on 2016/3/3.
 * 通知管理
 */
@Entity
@Table(name = "message_manage")
public class MessageManageEntity {

    private String id;
    private String department;
    private String type;
    private String content;
    private String projectId;
    private String title;
    private String creatBy;
    private Date creatDate;
    @Basic
    @Column(name = "department",length = 100)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    @Basic
    @Column(name = "type",length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "content",length = 600)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Id
    @Column(name = "ID",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "projectId",length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "title",length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Basic
    @Column(name = "creatBy",length = 100)
    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }
    @Basic
    @Column(name = "CreatDate")
    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }
}
