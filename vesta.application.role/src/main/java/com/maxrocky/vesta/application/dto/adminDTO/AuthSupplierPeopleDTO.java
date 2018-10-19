package com.maxrocky.vesta.application.dto.adminDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxrocky on 2018/4/3.
 */
public class AuthSupplierPeopleDTO {
    private String userId1;//人员id
    private String userName1;//系统账号
    private String staffName1;//人员姓名
    private String phone;//手机号
    private String mailbox;//邮箱
    private String roleAgency;//角色信息
    private String updateTime;//修改时间
    private String status;//是否启用 0删除  1启用
    private String remarks;//备注

    private String roleAgencyId;//角色信息id

    private String supplierId;    //责任单位id
    private String supplierName;    //责任单位名称
    private String agencyId;//项目id
    private String agencyType;  //类型

    private List<AuthSupplierAgencyRoleDTO> roleList;



    private String userId;//人员id
    private String userName;//系统账号
    private String staffName;//人员姓名
    public AuthSupplierPeopleDTO(){

        this.userId="";//人员id
        this.userName="";//系统账号
        this.staffName="";//人员姓名

        this.userId1="";//人员id
        this.userName1="";//系统账号
        this.staffName1="";//人员姓名
        this.phone="";//手机号
        this.mailbox="";//邮箱
        this.roleAgency="";//角色信息
        this.updateTime="";//修改时间
        this.status="";//是否启用 0删除  1启用
        this.remarks="";//备注

        this.roleAgencyId="";//角色信息id

        this.supplierId=""; //责任单位id
        this.supplierName="";   //责任单位名称
        this.agencyId="";//项目id
        this.agencyType="";  //类型
        this.roleList=new ArrayList<>();
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getStaffName1() {
        return staffName1;
    }

    public void setStaffName1(String staffName1) {
        this.staffName1 = staffName1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getRoleAgency() {
        return roleAgency;
    }

    public void setRoleAgency(String roleAgency) {
        this.roleAgency = roleAgency;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRoleAgencyId() {
        return roleAgencyId;
    }

    public void setRoleAgencyId(String roleAgencyId) {
        this.roleAgencyId = roleAgencyId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<AuthSupplierAgencyRoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AuthSupplierAgencyRoleDTO> roleList) {
        this.roleList = roleList;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
