package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 人员启用状态实体
 * Created by hp on 2017/12/12.
 */
@Entity
@Table(name = "user_map",uniqueConstraints = {@UniqueConstraint(columnNames = {"STAFF_ID","PROJECT_MODULE"})})
public class UserMapEntity {
    private Long id; //自增长id
    private String staffId; //人员Id
    private String state;//启用状态   0  未启用   1 已启用
    private String sourceFrom;      //来源 OA:同步 external:本地创建
    private Date modifyOn;//修改时间
    private String projectModule;//所属模块  st  安全   qc 交付  es 工程



    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STAFF_ID",length = 50)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    @Basic
    @Column(name = "STATE",length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "SOURCE_FROM",length = 10)
    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }
    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "PROJECT_MODULE",length = 10)

    public String getProjectModule() {
        return projectModule;
    }

    public void setProjectModule(String projectModule) {
        this.projectModule = projectModule;
    }
}
