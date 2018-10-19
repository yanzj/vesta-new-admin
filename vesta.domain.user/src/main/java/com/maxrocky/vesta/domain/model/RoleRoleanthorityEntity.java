package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by JillChen on 2016/1/4.
 */
@Entity
@Table(name = "role_roleanthority")
public class RoleRoleanthorityEntity {
    private String userId;//主键 不知道谁弄的叫UserID混淆视听
    private String staffId;//人员ID
    private String setId;//后台角色ID
    private String appSetId;//app角色id
    private String projectId;//项目ID

    @Id
    @Column(name = "UserId", length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "StaffId", length = 50)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "SetId", length = 50)
    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "AppSetId", length = 50)
    public String getAppSetId() {
        return appSetId;
    }

    public void setAppSetId(String appSetId) {
        this.appSetId = appSetId;
    }
    @Basic
    @Column(name = "ProjectId", length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRoleanthorityEntity that = (RoleRoleanthorityEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (setId != null ? !setId.equals(that.setId) : that.setId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (setId != null ? setId.hashCode() : 0);
        return result;
    }
}
