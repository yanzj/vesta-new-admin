package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/13.
 */
public class StaffNameDTO {
    private String staffId;
    private String staffName;
    private String userName;
    private String roleSetId;
    private String ckFlag;

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

    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    public String getCkFlag() {
        return ckFlag;
    }

    public void setCkFlag(String ckFlag) {
        this.ckFlag = ckFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
