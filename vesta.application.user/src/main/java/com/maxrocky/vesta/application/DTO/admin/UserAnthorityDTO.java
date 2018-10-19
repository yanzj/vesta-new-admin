package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by zhanghj on 2016/1/25.
 */
public class UserAnthorityDTO {

    private String userId;
    private String staffId;
    private String staffName;//名称
    private String setId;
    private String roledesc;        //角色描述

    private String appRoleSetId;//app角色Id
    private String appRoleSetName;//app角色名称

    public String getAppRoleSetId() {
        return appRoleSetId;
    }

    public void setAppRoleSetId(String appRoleSetId) {
        this.appRoleSetId = appRoleSetId;
    }

    public String getAppRoleSetName() {
        return appRoleSetName;
    }

    public void setAppRoleSetName(String appRoleSetName) {
        this.appRoleSetName = appRoleSetName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }
}
