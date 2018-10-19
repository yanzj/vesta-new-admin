package com.maxrocky.vesta.domain.model;

/**
 * Created by zhanghj on 2016/1/21.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * 员工签到表
 */
@Entity
@Table(name = "staff_register")
public class StaffRegisterEntity {

    private String staffRegisterId;     //签到Id

    private String staffId;             //员工id

    private String staffName;           //员工姓名

    private String staffSectionId;      //员工部门id

    private String staffSection;        //员工部门

    private String staffRegisterStatus;     //0-未签订，1-已签到

    private Date registerDateTime;          //签到时间

    private String registerDate ;           //签到日期

    private String projectId;       //项目id

    private String mobile;          //员工电话

    @Id
    @Column(name = "STAFF_REGISTERID",insertable = true,nullable = false, length = 32)
    public String getStaffRegisterId() {
        return staffRegisterId;
    }

    public void setStaffRegisterId(String staffRegisterId) {
        this.staffRegisterId = staffRegisterId;
    }


    @Basic
    @Column(name = "STAFF_NAME", length = 32)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    @Basic
    @Column(name = "STAFF_SECTION", length = 32)
    public String getStaffSection() {
        return staffSection;
    }

    public void setStaffSection(String staffSection) {
        this.staffSection = staffSection;
    }

    @Basic
    @Column(name = "REGISTER_STATUS", length = 10)
    public String getStaffRegisterStatus() {
        return staffRegisterStatus;
    }

    public void setStaffRegisterStatus(String staffRegisterStatus) {
        this.staffRegisterStatus = staffRegisterStatus;
    }
    @Basic
    @Column(name = "REGISTER_DATETIME", length = 10)
    public Date getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(Date registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    @Basic
    @Column(name = "REGISTER_DATE", length = 10)
    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @javax.persistence.Column(name = "STAFF_SECTIONID",length = 32)
    public String getStaffSectionId() {
        return staffSectionId;
    }

    public void setStaffSectionId(String staffSectionId) {
        this.staffSectionId = staffSectionId;
    }

    @Basic
    @javax.persistence.Column(name = "project_Id",length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "MOBILE", length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
