package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by zhanghj on 2016/1/19.
 */
public class RoleRoleMouldDTO {
    public String roleDesc;//权限分类数字

    public String roleDescName;//权限分类中文

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleDescName() {
        return roleDescName;
    }

    public void setRoleDescName(String roleDescName) {
        this.roleDescName = roleDescName;
    }
}
