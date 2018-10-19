package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员权限管理-会员角色菜单操作级别表
 * Created by Administrator on 2016/8/4.
 */
@Entity
@Table(name = "member_role_menu")
public class MemberRoleMenuEntity implements Serializable{

    private String id;
    private String roleSetId;   //角色Id
    private String menuId;      //菜单Id
    private String menuName;    //菜单名称
    private String operationLevel;  //操作级别(0:仅查看,1:所有操作)
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
    @Column(name = "menu_id",nullable = true, length = 100)
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_name",nullable = true, length = 100)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "operation_level",nullable = true, length = 2)
    public String getOperationLevel() {
        return operationLevel;
    }

    public void setOperationLevel(String operationLevel) {
        this.operationLevel = operationLevel;
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
