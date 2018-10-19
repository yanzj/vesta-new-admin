package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhanghj on 2016/2/16.
 */
public class StaffInfoDTO {

    private  String staffId;//员工Id

    private String staffName;//员工姓名

    private String sectionId;//部门id

    private String sectionName;//部门名称

    private String staffMobile;//员工手机号码

    private String staffLogo;//员工头像


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public String getStaffLogo() {
        return staffLogo;
    }

    public void setStaffLogo(String staffLogo) {
        this.staffLogo = staffLogo;
    }

}
