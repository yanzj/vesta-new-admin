package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/22.
 */
public class UserOrRoleDTO {

    private String outerStaffId;//人员ID
    private String outerStaffName;//人员姓名

    public UserOrRoleDTO(){
        this.outerStaffId= "";
        this.outerStaffName= "";
    }

    public String getOuterStaffId() {
        return outerStaffId;
    }

    public void setOuterStaffId(String outerStaffId) {
        this.outerStaffId = outerStaffId;
    }

    public String getOuterStaffName() {
        return outerStaffName;
    }

    public void setOuterStaffName(String outerStaffName) {
        this.outerStaffName = outerStaffName;
    }
}
