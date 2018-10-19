package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/18.
 */
public class EnabledUserExcelDTO {

    private String staffName; //姓名
    private String userName;  //OA账号
    private String sysName;//系统账号
    private String sourceFrom;//来源
    private String mobile;//电话
    private String email; //邮件
    private String agencyName;//所属机构

    public EnabledUserExcelDTO() {
        this.staffName = "";
        this.userName = "";
        this.sysName = "";
        this.sourceFrom = "";
        this.mobile = "";
        this.email = "";
        this.agencyName = "";
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}
