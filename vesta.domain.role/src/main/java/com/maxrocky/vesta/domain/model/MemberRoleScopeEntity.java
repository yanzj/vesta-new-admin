package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员权限管理-会员角色操作数据范围表
 * Created by Administrator on 2016/8/4.
 */
@Entity
@Table(name = "member_role_scope")
public class MemberRoleScopeEntity implements Serializable{

    private String id;          //主键Id
    private String roleSetId;   //角色Id
    private String scopeType;   //权限范围类型(0:全部城市级别,1:城市级别,2:项目级别)
    private String scopeId;     //权限范围Id(0/城市Id/项目Code)
    private String scopeName;   //权限范围名称(全部城市/北京/亚奥金茂悦)
    private String nowState;    //当前状态(0:不启用,1:启用)

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",nullable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_set_id",nullable = true,length = 100)
    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    @Basic
    @Column(name = "scope_type",nullable = true, length = 10)
    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    @Basic
    @Column(name = "scope_id",nullable = true, length = 100)
    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    @Basic
    @Column(name = "scope_name",nullable = true, length = 100)
    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    @Basic
    @Column(name = "now_state",nullable = true, length = 2)
    public String getNowState() {
        return nowState;
    }

    public void setNowState(String nowState) {
        this.nowState = nowState;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }


}
