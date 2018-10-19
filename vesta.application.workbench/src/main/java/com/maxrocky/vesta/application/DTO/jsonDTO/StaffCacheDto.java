package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhanghj on 2016/2/19.
 */
public class StaffCacheDto {

    private String staffId;//员工id
    private String roleId;//权限
    private String token;//token

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
