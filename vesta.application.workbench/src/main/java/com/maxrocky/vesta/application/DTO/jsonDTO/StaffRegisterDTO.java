package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhanghj on 2016/1/21.
 */
public class StaffRegisterDTO {

    private String staffId;             //员工id

    private String staffName;           //员工姓名

    private String sectionId;           //部门id

    private String staffSection;        //员工部门

    private String staffRegisterStatus;     //0-未签订，1-已签到

    private String registerDateTime;          //签到时间

    private String registerDate ;           //签到日期


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

    public String getStaffSection() {
        return staffSection;
    }

    public void setStaffSection(String staffSection) {
        this.staffSection = staffSection;
    }

    public String getStaffRegisterStatus() {
        return staffRegisterStatus;
    }

    public void setStaffRegisterStatus(String staffRegisterStatus) {
        this.staffRegisterStatus = staffRegisterStatus;
    }

    public String getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(String registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
