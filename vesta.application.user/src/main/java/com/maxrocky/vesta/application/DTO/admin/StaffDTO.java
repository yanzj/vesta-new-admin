package com.maxrocky.vesta.application.DTO.admin;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/12.
 * 被迫写的这个质检员工信息数据封装类，好名字都被用了，各种重名导致页面各种出问题。。。无奈ing
 */
public class StaffDTO {
    private String cnStaffId;
    private String cnUserName;
    private String cnStaffName;
    private String cnProjectId;
    private String cnRoleSetId;
    private String cnPassword;
    private String cnStaffSex;
    private String cnBirthDay;
    private String cnStaffMobile;
    private String cnStaffEmail;
    private String cnOfficePhone;
    private String cnBirthType;
    private String cnOpenMobile;
    private List<AgencyDTO> staffAgency;
    private String cnModifyTime;
    private Integer orderNum=0;
    private String memo;
    private String jinmaoIs;
    private String status="1";

    public String getCnBirthDay() {
        return cnBirthDay;
    }

    public void setCnBirthDay(String cnBirthDay) {
        this.cnBirthDay = cnBirthDay;
    }

    public String getCnBirthType() {
        return cnBirthType;
    }

    public void setCnBirthType(String cnBirthType) {
        this.cnBirthType = cnBirthType;
    }

    public String getCnOfficePhone() {
        return cnOfficePhone;
    }

    public void setCnOfficePhone(String cnOfficePhone) {
        this.cnOfficePhone = cnOfficePhone;
    }

    public String getCnOpenMobile() {
        return cnOpenMobile;
    }

    public void setCnOpenMobile(String cnOpenMobile) {
        this.cnOpenMobile = cnOpenMobile;
    }

    public String getCnPassword() {
        return cnPassword;
    }

    public void setCnPassword(String cnPassword) {
        this.cnPassword = cnPassword;
    }

    public String getCnProjectId() {
        return cnProjectId;
    }

    public void setCnProjectId(String cnProjectId) {
        this.cnProjectId = cnProjectId;
    }

    public String getCnRoleSetId() {
        return cnRoleSetId;
    }

    public void setCnRoleSetId(String cnRoleSetId) {
        this.cnRoleSetId = cnRoleSetId;
    }

    public String getCnStaffEmail() {
        return cnStaffEmail;
    }

    public void setCnStaffEmail(String cnStaffEmail) {
        this.cnStaffEmail = cnStaffEmail;
    }

    public String getCnStaffId() {
        return cnStaffId;
    }

    public void setCnStaffId(String cnStaffId) {
        this.cnStaffId = cnStaffId;
    }

    public String getCnStaffMobile() {
        return cnStaffMobile;
    }

    public void setCnStaffMobile(String cnStaffMobile) {
        this.cnStaffMobile = cnStaffMobile;
    }

    public String getCnStaffName() {
        return cnStaffName;
    }

    public void setCnStaffName(String cnStaffName) {
        this.cnStaffName = cnStaffName;
    }

    public String getCnStaffSex() {
        return cnStaffSex;
    }

    public void setCnStaffSex(String cnStaffSex) {
        this.cnStaffSex = cnStaffSex;
    }

    public String getCnUserName() {
        return cnUserName;
    }

    public void setCnUserName(String cnUserName) {
        this.cnUserName = cnUserName;
    }

    public List<AgencyDTO> getStaffAgency() {
        return staffAgency;
    }

    public void setStaffAgency(List<AgencyDTO> staffAgency) {
        this.staffAgency = staffAgency;
    }

    public String getCnModifyTime() {
        return cnModifyTime;
    }

    public void setCnModifyTime(String cnModifyTime) {
        this.cnModifyTime = cnModifyTime;
    }

    public String getJinmaoIs() {
        return jinmaoIs;
    }

    public void setJinmaoIs(String jinmaoIs) {
        this.jinmaoIs = jinmaoIs;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
