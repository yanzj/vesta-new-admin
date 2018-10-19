package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/10.
 */
public class UserStaffDTO{
    private String staffIdB;   //员工ID
    private String staffNameB; //员工名
    private String userNameB;  //员工登录名
    private String mobileB;//电话
    private String emailB; //邮件
    private String sysNameB;//系统账号   系统登录账号
    private String modifyOnB;//修改时间
    private String agencyNameB;//所属机构
    private String agencyIdB;  //所属机构id
    private String isEnabledB;//是否启用  0 、未启用  1 、已启用
    private String agencyTypeB;//机构性质


    public String getStaffIdB() {
        return staffIdB;
    }

    public void setStaffIdB(String staffIdB) {
        this.staffIdB = staffIdB;
    }

    public String getStaffNameB() {
        return staffNameB;
    }

    public void setStaffNameB(String staffNameB) {
        this.staffNameB = staffNameB;
    }

    public String getUserNameB() {
        return userNameB;
    }

    public void setUserNameB(String userNameB) {
        this.userNameB = userNameB;
    }

    public String getMobileB() {
        return mobileB;
    }

    public void setMobileB(String mobileB) {
        this.mobileB = mobileB;
    }

    public String getEmailB() {
        return emailB;
    }

    public void setEmailB(String emailB) {
        this.emailB = emailB;
    }

    public String getSysNameB() {
        return sysNameB;
    }

    public void setSysNameB(String sysNameB) {
        this.sysNameB = sysNameB;
    }

    public String getModifyOnB() {
        return modifyOnB;
    }

    public void setModifyOnB(String modifyOnB) {
        this.modifyOnB = modifyOnB;
    }

    public String getAgencyNameB() {
        return agencyNameB;
    }

    public void setAgencyNameB(String agencyNameB) {
        this.agencyNameB = agencyNameB;
    }

    public String getAgencyIdB() {
        return agencyIdB;
    }

    public void setAgencyIdB(String agencyIdB) {
        this.agencyIdB = agencyIdB;
    }

    public String getIsEnabledB() {
        return isEnabledB;
    }

    public void setIsEnabledB(String isEnabledB) {
        this.isEnabledB = isEnabledB;
    }

    public String getAgencyTypeB() {
        return agencyTypeB;
    }

    public void setAgencyTypeB(String agencyTypeB) {
        this.agencyTypeB = agencyTypeB;
    }
}

