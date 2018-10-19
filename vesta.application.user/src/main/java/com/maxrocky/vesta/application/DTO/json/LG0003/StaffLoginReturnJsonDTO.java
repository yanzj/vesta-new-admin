package com.maxrocky.vesta.application.DTO.json.LG0003;

/**
 * Created by Tom on 2016/2/16 14:04.
 * Describe:员工登录返回信息
 */
public class StaffLoginReturnJsonDTO {

    private String token;//登录Id
    private String staffId;//员工Id
    private String roleId;//角色Id

    public StaffLoginReturnJsonDTO(){
        token = "";
        staffId = "";
        roleId = "";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
}
