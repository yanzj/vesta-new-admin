package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by ZhangBailiang on 2016/1/21.
 * 物业公司权限表
 */

//@Entity
//@Table(name = "property_company_rolesetmap")
public class PropertyCompanyRoleMapEntity {

    private String rolesetid;//关系Id
    private String setId;//公司Id
    private String rolRoleId;//权限Id

    @Id
    @Column(name = "Rolesetid")
    public String getRolesetid() {
        return rolesetid;
    }

    public void setRolesetid(String rolesetid) {
        this.rolesetid = rolesetid;
    }

    @Basic
    @Column(name = "SetId")
    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "rol_RoleId")
    public String getRolRoleId() {
        return rolRoleId;
    }

    public void setRolRoleId(String rolRoleId) {
        this.rolRoleId = rolRoleId;
    }

}
