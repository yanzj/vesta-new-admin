package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhanghj on 2016/2/16.
 */
public class StaffPasswordDTO {
    private String staffId;//员工Id
    private String oldPassword;//原密码
    private String newPassword_1;//新密码_1
    private String newPassword_2;//新密码_2

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword_1() {
        return newPassword_1;
    }

    public void setNewPassword_1(String newPassword_1) {
        this.newPassword_1 = newPassword_1;
    }

    public String getNewPassword_2() {
        return newPassword_2;
    }

    public void setNewPassword_2(String newPassword_2) {
        this.newPassword_2 = newPassword_2;
    }
}
