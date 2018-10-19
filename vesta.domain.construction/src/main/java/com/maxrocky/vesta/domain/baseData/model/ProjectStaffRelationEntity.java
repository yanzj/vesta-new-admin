package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 人员查看项目权限
 * Created by yuanyn on 2017/8/29.
 */
@Entity
@Table(name = "project_staff_relation",uniqueConstraints = {@UniqueConstraint(columnNames = {"PROJECT_ID","STAFF_ID"})})
public class ProjectStaffRelationEntity {
    private long id;   //主键
    private String projectId; //项目或区域Id
    private String projectName;//项目名
    private String staffId; //人员或部门ID
    private String staffName;//人员姓名
    private String uType;// 1  人员   2 部门
    private String pType;// 1   区域  2 项目
    private String state;  //状态
    private Date modifyDate; // 修改时间

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "PROJECT_NAME",length = 200)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
    @Column(name = "STAFF_NAME",length = 200)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "UTYPE",length = 50)
    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    @Basic
    @Column(name = "PTYPE",length = 50)
    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    @Basic
    @Column(name = "STATE",length = 20)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "MODIFY_DATE")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
