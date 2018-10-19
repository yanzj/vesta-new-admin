package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by sunmei on 2016/3/4.
 * 通知管理部门表
 */
@Entity
@Table(name = "message_department")
public class MessageDepartmentEntity {
    private String id;
    private String messageId;
    private String departmentId;
    @Id
    @Column(name = "ID",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "message_Id",length = 100)
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    @Basic
    @Column(name = "department_Id",length = 100)
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
