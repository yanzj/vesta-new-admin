package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by maxrocky on 2018/4/3.
 */
public class AuthSupplierAgencyRoleDTO {
    private String roleId;
    private String roleName;
    private String checked;

    public AuthSupplierAgencyRoleDTO(){
        this.roleId="";//
        this.roleName="";//
        this.checked="";//
    }
    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
