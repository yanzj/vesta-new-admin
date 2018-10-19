package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/6/1.
 * 组 人 关联关系表
 */
@Entity
@Table(name = "organize_usermap",uniqueConstraints = {@UniqueConstraint(columnNames = {"ORGANIZE_ID","STAFF_ID"})})
public class OrganizeUsermapEntity {
    private String id;               //主键
    private String staffId;          //员工ID
    private String staffType;        //类型 1员工 2组织机构  暂时不用此字段
    private String organizeId;       //组ID
    private String orgStatus;        //状态 0删除 1正常
    private Date modifyTime;       //修改时间

    @Id
    @Column(name = "ID",length = 60,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORGANIZE_ID",length = 60,nullable = false)
    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    @Basic
    @Column(name = "STAFF_ID",length = 60,nullable = false)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "ORG_STATUS",length = 2)
    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }

    @Basic
    @Column(name = "STAFF_TYPE",length = 2)
    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
