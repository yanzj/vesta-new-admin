package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/20.
 */
public class UserManageDTO {

    private String staffIdW;   //员工ID
    private String staffNameW; //员工名
    private String mobileW;//电话
    private String emailW; //邮件
    private String sysNameW;//系统账号   系统登录账号
    private String modifyOnW;//修改时间
    private String agencyIdW;//所属机构Id
    private String agencyNameW;//所属机构
    private String statusW;//是否启用  0 、未启用  1 、已启用
    private String memoW;//备注
    private String temporaryW;//是否临时用户  0 、否  1 、是
    public UserManageDTO() {
        this.staffIdW = "";
        this.staffNameW = "";
        this.mobileW = "";
        this.emailW = "";
        this.sysNameW = "";
        this.modifyOnW = "";
        this.agencyIdW = "";
        this.agencyNameW = "";
        this.statusW = "";
        this.memoW = "";
        this.temporaryW="";
    }

    public String getStaffIdW() {
        return staffIdW;
    }

    public void setStaffIdW(String staffIdW) {
        this.staffIdW = staffIdW;
    }

    public String getStaffNameW() {
        return staffNameW;
    }

    public void setStaffNameW(String staffNameW) {
        this.staffNameW = staffNameW;
    }

    public String getMobileW() {
        return mobileW;
    }

    public void setMobileW(String mobileW) {
        this.mobileW = mobileW;
    }

    public String getEmailW() {
        return emailW;
    }

    public void setEmailW(String emailW) {
        this.emailW = emailW;
    }

    public String getSysNameW() {
        return sysNameW;
    }

    public void setSysNameW(String sysNameW) {
        this.sysNameW = sysNameW;
    }

    public String getModifyOnW() {
        return modifyOnW;
    }

    public void setModifyOnW(String modifyOnW) {
        this.modifyOnW = modifyOnW;
    }

    public String getAgencyIdW() {
        return agencyIdW;
    }

    public void setAgencyIdW(String agencyIdW) {
        this.agencyIdW = agencyIdW;
    }

    public String getAgencyNameW() {
        return agencyNameW;
    }

    public void setAgencyNameW(String agencyNameW) {
        this.agencyNameW = agencyNameW;
    }

    public String getStatusW() {
        return statusW;
    }

    public void setStatusW(String statusW) {
        this.statusW = statusW;
    }

    public String getMemoW() {
        return memoW;
    }

    public void setMemoW(String memoW) {
        this.memoW = memoW;
    }

    public String getTemporaryW() {
        return temporaryW;
    }

    public void setTemporaryW(String temporaryW) {
        this.temporaryW = temporaryW;
    }
}
