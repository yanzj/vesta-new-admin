package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/2.
 */
public class StaffDTO {
    private String staffId;   //员工ID
    private String staffName; //员工名
    private String nikeName;  //员工登录名
    private String mobile;

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

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
