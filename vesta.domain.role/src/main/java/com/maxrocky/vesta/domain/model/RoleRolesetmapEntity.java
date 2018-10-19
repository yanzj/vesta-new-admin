package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by JillChen on 2016/1/4.
 */

/**
 * 角色权限关系表
 */
@Entity
@Table(name = "role_rolesetmap")
public class RoleRolesetmapEntity {
    private String rolesetid;//关系Id
    private String setId;//角色Id
    private String rolRoleId;//权限Id

    @Id
    @Column(name = "Rolesetid",length = 50)
    public String getRolesetid() {
        return rolesetid;
    }

    public void setRolesetid(String rolesetid) {
        this.rolesetid = rolesetid;
    }

    @Basic
    @Column(name = "SetId",length = 50)
    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "rol_RoleId",length = 50)
    public String getRolRoleId() {
        return rolRoleId;
    }

    public void setRolRoleId(String rolRoleId) {
        this.rolRoleId = rolRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRolesetmapEntity that = (RoleRolesetmapEntity) o;

        if (rolesetid != null ? !rolesetid.equals(that.rolesetid) : that.rolesetid != null) return false;
        if (setId != null ? !setId.equals(that.setId) : that.setId != null) return false;
        if (rolRoleId != null ? !rolRoleId.equals(that.rolRoleId) : that.rolRoleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rolesetid != null ? rolesetid.hashCode() : 0;
        result = 31 * result + (setId != null ? setId.hashCode() : 0);
        result = 31 * result + (rolRoleId != null ? rolRoleId.hashCode() : 0);
        return result;
    }
}
