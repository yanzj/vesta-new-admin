package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/18.
 */
public class OuterUserExcelDTO {

    private String staffName; //姓名
    private String sysName;//系统账号
    private String mobile;//电话
    private String email; //邮件
    private String agencyName;//所属机构

    public OuterUserExcelDTO() {
        this.staffName = "";
        this.sysName = "";
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

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
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
