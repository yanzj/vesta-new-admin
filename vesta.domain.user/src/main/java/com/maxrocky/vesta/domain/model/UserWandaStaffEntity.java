package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhanghj on 2016/2/14.
 */

/**
 * 万达内部员工表
 */
/*@Entity
@Table(name = "user_wandastaff")*/
public class UserWandaStaffEntity {

    public final static String State_On = "1";          //1-有效，0-无效
    public final static String State_Off = "0";
    public final static String Type_YES ="YES";     //已加入
    public final static String Type_NO = "NO";      //未加入

    private String staffId;//员工ID
    private String userName;//用户名
    private String password;//密码
    private String staffName;//名称
    private String staffState;//状态
    private String mobile;//手机
    private String companyId;//公司Id
    private String projectId;//项目Id
    private String departmentId;//部门Id
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String logo;//头像
    private String type;//员工状态  YES-已加入     NO-未加入


    @Id
    @Column(name = "STAFF_ID",nullable = false, length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "USERNAME", length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Basic
    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Basic
    @Column(name = "STAFFNAME", length = 50)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    @Basic
    @Column(name = "STAFFSTATE", length = 50)
    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }
    @Basic
    @Column(name = "STAFFMOBILE", length = 50)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @Basic
    @Column(name = "COMPANYID", length = 50)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Basic
    @Column(name = "PROJECTID", length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "DEPARTMENTID", length = 50)
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    @Basic
    @Column(name = "CREATEBY", length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATEON", length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "MODIFYID", length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIGYON", length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "LOGO", length = 50)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "STAFFTYPE", length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
