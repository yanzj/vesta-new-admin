package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by JillChen on 2016/1/4.
 */
public class RoleRolebuttonmapEntityPK implements Serializable {
    private String roleId;
    private String menuId;
    private String buttonId;

    @Column(name = "RoleId")
    @Id
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "MenuId")
    @Id
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Column(name = "ButtonId")
    @Id
    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRolebuttonmapEntityPK that = (RoleRolebuttonmapEntityPK) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;
        if (buttonId != null ? !buttonId.equals(that.buttonId) : that.buttonId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        result = 31 * result + (buttonId != null ? buttonId.hashCode() : 0);
        return result;
    }
}
