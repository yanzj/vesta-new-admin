package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Magic on 2017/12/11.
 * 角色 --项目级别关系表 多对多
 */
@Entity
@Table(name = "auth_role_project")
public class AuthRoleProjectEntity {
    private String authId;
    private String authRoleId;//角色ID
    private String authAgencyId;//组织结构id
    @Id
    @Column(name = "AUTH_ID",unique = true,nullable = false,length = 50)
    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }
    @Basic
    @Column(name = "AUTH_ROLE_ID",length = 30)
    public String getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(String authRoleId) {
        this.authRoleId = authRoleId;
    }
    @Basic
    @Column(name = "AUTH_AGENCY_ID",length = 30)
    public String getAuthAgencyId() {
        return authAgencyId;
    }

    public void setAuthAgencyId(String authAgencyId) {
        this.authAgencyId = authAgencyId;
    }
}
