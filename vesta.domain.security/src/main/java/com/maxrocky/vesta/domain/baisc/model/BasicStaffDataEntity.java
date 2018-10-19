package com.maxrocky.vesta.domain.baisc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础人员数据权限（为前端准备）
 * Created by 90544 on 2017/6/9.
 */
@Entity
@Table(name = "st_basic_staff" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"DATA_ID","DATA_TYPE","DATA_ROLE","STAFF_ID"})})
public class BasicStaffDataEntity {
    private int id;//自增ID
    private String dataId;//集团ID、区域ID、项目ID
    private String dataType;//1:部门；2：人员
//    private String dataName;//名称
    /**
     * 集团：1：HSE部门
     * 区域：2：项目安全员
     * 项目：3：项目全体成员
     * 4：甲方全全员
     * 5：甲方工程师
     * 6：总包
     * 7：监理
     */
    private String dataRole;
    private String staffId;//人员ID
//    private String staffName;//人员名称
    private String state;//状态 0删除 1正常
    private Date modifyTime;//修改时间

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DATA_ID", length = 50)
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    @Basic
    @Column(name = "DATA_TYPE", length = 6)
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
//    @Basic
//    @Column(name = "DATA_NAME", length = 50)
//    public String getDataName() {
//        return dataName;
//    }
//
//    public void setDataName(String dataName) {
//        this.dataName = dataName;
//    }

    @Basic
    @Column(name = "DATA_ROLE", length = 50)
    public String getDataRole() {
        return dataRole;
    }

    public void setDataRole(String dataRole) {
        this.dataRole = dataRole;
    }

    @Basic
    @Column(name = "STAFF_ID", length = 50)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

//    @Basic
//    @Column(name = "STAFF_NAME", length = 50)
//    public String getStaffName() {
//        return staffName;
//    }
//
//    public void setStaffName(String staffName) {
//        this.staffName = staffName;
//    }

    @Basic
    @Column(name = "STATE", length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
