package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by zhanghj on 2016/4/18.
 */
public class RoleMenuDTO {
    private String roleMenuId;      //菜单id

    private String roleMenuFlag;        //是否能点，Y，N

    private String roleMenuDesc;        //菜单描述

    private String roleMenuName;        //菜单名称

    private String roleMenuState;       //菜单状态

    private String roleMenuLevel;       //菜单级别

    private String roleMenuOrder;       //菜单排序

    private String roleMenuOperator;        //菜单操作人

    private String roleMenuParId;           //菜单父层id

    private String roleMenuRun;         //跳转网页

    private String owner;               //owner

    public String getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public String getRoleMenuFlag() {
        return roleMenuFlag;
    }

    public void setRoleMenuFlag(String roleMenuFlag) {
        this.roleMenuFlag = roleMenuFlag;
    }

    public String getRoleMenuDesc() {
        return roleMenuDesc;
    }

    public void setRoleMenuDesc(String roleMenuDesc) {
        this.roleMenuDesc = roleMenuDesc;
    }

    public String getRoleMenuName() {
        return roleMenuName;
    }

    public void setRoleMenuName(String roleMenuName) {
        this.roleMenuName = roleMenuName;
    }

    public String getRoleMenuState() {
        return roleMenuState;
    }

    public void setRoleMenuState(String roleMenuState) {
        this.roleMenuState = roleMenuState;
    }

    public String getRoleMenuLevel() {
        return roleMenuLevel;
    }

    public void setRoleMenuLevel(String roleMenuLevel) {
        this.roleMenuLevel = roleMenuLevel;
    }

    public String getRoleMenuOrder() {
        return roleMenuOrder;
    }

    public void setRoleMenuOrder(String roleMenuOrder) {
        this.roleMenuOrder = roleMenuOrder;
    }

    public String getRoleMenuOperator() {
        return roleMenuOperator;
    }

    public void setRoleMenuOperator(String roleMenuOperator) {
        this.roleMenuOperator = roleMenuOperator;
    }

    public String getRoleMenuParId() {
        return roleMenuParId;
    }

    public void setRoleMenuParId(String roleMenuParId) {
        this.roleMenuParId = roleMenuParId;
    }

    public String getRoleMenuRun() {
        return roleMenuRun;
    }

    public void setRoleMenuRun(String roleMenuRun) {
        this.roleMenuRun = roleMenuRun;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
