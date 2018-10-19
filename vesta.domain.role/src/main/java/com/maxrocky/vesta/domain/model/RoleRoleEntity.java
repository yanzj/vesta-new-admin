package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by JillChen on 2016/1/4.
 */

/**
 * 权限表
 */
@Entity
@Table(name = "role_role")
public class RoleRoleEntity {
    private String roleId;          //权限Id
    private Date makeDate;          //创建日期
    private Time makeTime;          //创建时间
    private Date modifyDate;        //修改日期
    private Time modifyTime;        //修改时间
    private String operator;        //操作人
    private String roledesc;        //所属模块，1-物业管理，2-商户管理，3-房屋租赁管理，4-邻里圈管理，5-用户管理管理，6-数据统计
    private String roleDescription;     //描述
    private String roleName;            //权限名称
    private String roleSetId;           //角色Id

    @Id
    @Column(name = "RoleId",length = 50)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "MakeDate",length = 50)
    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    @Basic
    @Column(name = "MakeTime",length = 50)
    public Time getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Time makeTime) {
        this.makeTime = makeTime;
    }

    @Basic
    @Column(name = "ModifyDate",length = 50)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "ModifyTime",length = 50)
    public Time getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Time modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "Operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "roledesc",length = 50)
    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Basic
    @Column(name = "RoleDescription",length = 50)
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Basic
    @Column(name = "RoleName",length = 50)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "RoleSetId",length = 50)
    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRoleEntity that = (RoleRoleEntity) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (makeDate != null ? !makeDate.equals(that.makeDate) : that.makeDate != null) return false;
        if (makeTime != null ? !makeTime.equals(that.makeTime) : that.makeTime != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (roledesc != null ? !roledesc.equals(that.roledesc) : that.roledesc != null) return false;
        if (roleDescription != null ? !roleDescription.equals(that.roleDescription) : that.roleDescription != null)
            return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (roleSetId != null ? !roleSetId.equals(that.roleSetId) : that.roleSetId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (makeDate != null ? makeDate.hashCode() : 0);
        result = 31 * result + (makeTime != null ? makeTime.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (roledesc != null ? roledesc.hashCode() : 0);
        result = 31 * result + (roleDescription != null ? roleDescription.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleSetId != null ? roleSetId.hashCode() : 0);
        return result;
    }
}
