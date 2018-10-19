package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hp on 2017/12/7.
 * Describe:本系统中人员信息表
 */
@Entity
@Table(name = "user_information")
public class UserInformationEntity {

    private String staffId;//员工ID
    private String userName;//用户名 OA同步过来的账号
    private String sysName;//系统账号   系统登录账号
    private String password;//密码
    private String staffName;//名称
    private String staffState;//状态  0 停用  1 启用
    private String type;//类型
    private String mobile;//手机
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String logo;//头像
    private String sex;  //性别
    private java.sql.Date birthday;//生日
    private String email; //邮件
    private String officePhone; //工作电话
    private String jinmaoIs;  //金茂内部员工  0：外部合作单位 1：OA同步
    private Integer orderNum; //排序
    private String memo;      //备注
    private String userId;//OA同步使用，用户ID
    private String temporary;//是否为临时账号

    @Id
    @Column(name = "STAFF_ID",nullable = false, length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    @Basic
    @Column(name = "USER_NAME", length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Basic
    @Column(name = "SYSNAME" ,nullable = false, length = 50)
    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
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
    @Column(name = "STAFF_STATE", columnDefinition = "varchar(32)default 0")
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
    @Column(name = "STAFF_SEX",length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @Basic
    @Column(name = "BIRTHDAY")
    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
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
    @Column(name = "USER_ID",length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "TEMPORARY", columnDefinition = "varchar(32)default 0")
    public String getTemporary() {
        return temporary;
    }

    public void setTemporary(String temporary) {
        this.temporary = temporary;
    }



}
