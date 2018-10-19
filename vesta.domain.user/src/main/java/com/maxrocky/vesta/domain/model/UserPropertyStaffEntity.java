package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/22 11:09.
 * Describe:员工信息表
 */
@Entity
@Table(name = "user_propertyStaff")
public class UserPropertyStaffEntity {

    /* 员工类型 */
    public final static String TYPE_OFF_STAFF = "OFF";//编外（不在万达系统内）自建
    public final static String TYPE_IN_STAFF = "IN";//编内（万达系统内）     引入

    public final static String State_On = "1";          //1-有效，0-无效
    public final static String State_Off = "0";

    private String staffId;//员工ID
    private String userName;//用户名
    private String password;//密码
    private String staffName;//名称
    private String staffState;//状态
    private String type;//类型                IN-引入，OFF-自建
    private String mobile;//手机
    private String openMobile;//是否公开手机号  0不公开 1公开
    private String companyId;//公司Id
    private String projectId;//项目Id
    private String departmentId;//部门Id
    private String roleSetId;//权限Id
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String logo;//头像
    private String sex;  //性别
    private java.sql.Date birthday;//生日
    private String birthdayType;//生日类型
    private String email; //邮件
    private String officePhone; //工作电话
    private String queryScope;//查询负责范围(模块条件)
    private String jinmaoIs;  //金茂内部员工   0不是 1是
    private Integer orderNum; //排序
    private String memo;      //备注

    /* 新增字段_2016-08-16_Wyd-会员数据权限开发 */
    private String scope;   //员工负责区域
    private String project;     //员工负责项目
    private String company;     //员工所在公司
    /* ------------------------------------ */

    @Id
    @Column(name = "STAFF_ID",nullable = false, length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "STAFF_NAME", length = 30)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "STAFF_STATE", length = 10)
    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    @Basic
    @Column(name = "TYPE", length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "MOBILE", length = 32)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "PROJECT_ID", length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "DEPARTMENT_ID", length = 32)
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "ROLE_SET_ID", length = 32)
    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = false, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = false, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = false)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "LOGO", length = 300)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "QUERY_SCOPE", length = 50)
    public String getQueryScope() {  return queryScope;  }

    public void setQueryScope(String queryScope) {  this.queryScope = queryScope;  }

    @Basic
    @Column(name = "BIRTHDAY")
    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "BIRTHDAY_TYPE",length = 2)
    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    @Basic
    @Column(name = "EMAIL",length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "OFFICE_PHONE",length = 100)
    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Basic
    @Column(name = "OPEN_MOBILE",length = 2)
    public String getOpenMobile() {
        return openMobile;
    }

    public void setOpenMobile(String openMobile) {
        this.openMobile = openMobile;
    }

    @Basic
    @Column(name = "STAFF_SEX",length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "JINMAO_IS",length = 2)
    public String getJinmaoIs() {
        return jinmaoIs;
    }

    public void setJinmaoIs(String jinmaoIs) {
        this.jinmaoIs = jinmaoIs;
    }

    @Basic
    @Column(name = "ORDER_NUMBER")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "STAFF_MEMO",length = 50)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "SCOPE",length = 100)
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "PROJECT",length = 255)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Basic
    @Column(name = "COMPANY",length = 100)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
