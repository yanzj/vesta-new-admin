package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/22.
 */
public class UserAndRoleRelationDTO {

    private String authRoleIds;//角色id
    private String authStaffId;//人员Id
    private String category;//服务气类型 1. 客关  2.工程   3. 安全

    public UserAndRoleRelationDTO() {
        this.authRoleIds = "";
        this.authStaffId = "";
        this.category="";
    }

    public String getAuthRoleIds() {
        return authRoleIds;
    }

    public void setAuthRoleIds(String authRoleIds) {
        this.authRoleIds = authRoleIds;
    }

    public String getAuthStaffId() {
        return authStaffId;
    }

    public void setAuthStaffId(String authStaffId) {
        this.authStaffId = authStaffId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
