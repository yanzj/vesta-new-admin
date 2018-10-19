package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * 项目角色人员 授权实体
 * Created by maxrocky on 2018/1/4.
 */
public class AuthAdminProjectListDTO {
    private String authRoleId;//角色id
    private String authRoleName;//角色名称
    private String userListName;//人员名称
    private String updaTime;//最后修改时间

    private String staffId;
    private String staffName;

    public String getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(String authRoleId) {
        this.authRoleId = authRoleId;
    }

    public String getAuthRoleName() {
        return authRoleName;
    }

    public void setAuthRoleName(String authRoleName) {
        this.authRoleName = authRoleName;
    }

    public String getUpdaTime() {
        return updaTime;
    }

    public void setUpdaTime(String updaTime) {
        this.updaTime = updaTime;
    }

    public String getUserListName() {
        return userListName;
    }

    public void setUserListName(String userListName) {
        this.userListName = userListName;
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
}
