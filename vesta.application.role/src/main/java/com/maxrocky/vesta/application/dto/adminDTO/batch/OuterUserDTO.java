package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/18.
 */
public class OuterUserDTO {

    private String staffIdO;   //员工ID
    private String staffNameO; //员工名
    private String mobileO;//电话
    private String emailO; //邮件
    private String sysNameO;//系统账号   系统登录账号
    private String modifyOnO;//修改时间
    private String agencyNameO;//所属机构
    private String agencyIdO;//所属机构Id
    private String stateO; //启用状态

    public OuterUserDTO() {
        this.staffIdO = "";
        this.staffNameO = "";
        this.mobileO = "";
        this.emailO = "";
        this.sysNameO = "";
        this.modifyOnO = "";
        this.agencyNameO = "";
        this.agencyIdO = "";
        this.stateO = "";
    }

    public String getStaffIdO() {
        return staffIdO;
    }

    public void setStaffIdO(String staffIdO) {
        this.staffIdO = staffIdO;
    }

    public String getStaffNameO() {
        return staffNameO;
    }

    public void setStaffNameO(String staffNameO) {
        this.staffNameO = staffNameO;
    }

    public String getMobileO() {
        return mobileO;
    }

    public void setMobileO(String mobileO) {
        this.mobileO = mobileO;
    }

    public String getEmailO() {
        return emailO;
    }

    public void setEmailO(String emailO) {
        this.emailO = emailO;
    }

    public String getSysNameO() {
        return sysNameO;
    }

    public void setSysNameO(String sysNameO) {
        this.sysNameO = sysNameO;
    }

    public String getModifyOnO() {
        return modifyOnO;
    }

    public void setModifyOnO(String modifyOnO) {
        this.modifyOnO = modifyOnO;
    }

    public String getAgencyNameO() {
        return agencyNameO;
    }

    public void setAgencyNameO(String agencyNameO) {
        this.agencyNameO = agencyNameO;
    }

    public String getAgencyIdO() {
        return agencyIdO;
    }

    public void setAgencyIdO(String agencyIdO) {
        this.agencyIdO = agencyIdO;
    }

    public String getStateO() {
        return stateO;
    }

    public void setStateO(String stateO) {
        this.stateO = stateO;
    }
}
