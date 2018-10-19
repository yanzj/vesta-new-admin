package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/18.
 */
public class EnabledUserDTO {

    private String staffIdE;   //员工ID
    private String staffNameE; //员工名
    private String userNameE;  //员工登录名
    private String mobileE;//电话
    private String emailE; //邮件
    private String sysNameE;//系统账号   系统登录账号
    private String modifyOnE;//修改时间
    private String agencyNameE;//所属机构
    private String sourceFromE;//来源

    public EnabledUserDTO() {
        this.staffIdE = "";
        this.staffNameE = "";
        this.userNameE = "";
        this.mobileE = "";
        this.emailE = "";
        this.sysNameE = "";
        this.modifyOnE = "";
        this.agencyNameE = "";
        this.sourceFromE = "";

    }

    public String getStaffIdE() {
        return staffIdE;
    }

    public void setStaffIdE(String staffIdE) {
        this.staffIdE = staffIdE;
    }

    public String getStaffNameE() {
        return staffNameE;
    }

    public void setStaffNameE(String staffNameE) {
        this.staffNameE = staffNameE;
    }

    public String getUserNameE() {
        return userNameE;
    }

    public void setUserNameE(String userNameE) {
        this.userNameE = userNameE;
    }

    public String getMobileE() {
        return mobileE;
    }

    public void setMobileE(String mobileE) {
        this.mobileE = mobileE;
    }

    public String getEmailE() {
        return emailE;
    }

    public void setEmailE(String emailE) {
        this.emailE = emailE;
    }

    public String getSysNameE() {
        return sysNameE;
    }

    public void setSysNameE(String sysNameE) {
        this.sysNameE = sysNameE;
    }

    public String getModifyOnE() {
        return modifyOnE;
    }

    public void setModifyOnE(String modifyOnE) {
        this.modifyOnE = modifyOnE;
    }

    public String getAgencyNameE() {
        return agencyNameE;
    }

    public void setAgencyNameE(String agencyNameE) {
        this.agencyNameE = agencyNameE;
    }

    public String getSourceFromE() {
        return sourceFromE;
    }

    public void setSourceFromE(String sourceFromE) {
        this.sourceFromE = sourceFromE;
    }
}
