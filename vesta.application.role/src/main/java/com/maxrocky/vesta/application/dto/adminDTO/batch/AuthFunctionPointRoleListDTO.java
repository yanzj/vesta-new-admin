package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.List;
import java.util.Map;

/**
 * Created by magic on 2017/12/18.
 * 角色功能点关联关系
 */
public class AuthFunctionPointRoleListDTO {
    private String authRoleId;//角色id
    private String authFunctionId;//功能点id
    private String control;//控制方式
    private String state;//状态 0 正常 1删除
    private String category;//类别 1.客关 2.工程 3.安全
    private String classification;//分类 0.管理平台   1.前段app
    private List<AuthFuncPointIdDTO> list;
    private String controlList;//控制方式组合
    //查询条件
    private Map<String,String> roles; // 角色
    public String getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(String authRoleId) {
        this.authRoleId = authRoleId;
    }

    public String getAuthFunctionId() {
        return authFunctionId;
    }

    public void setAuthFunctionId(String authFunctionId) {
        this.authFunctionId = authFunctionId;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public List<AuthFuncPointIdDTO> getList() {
        return list;
    }

    public void setList(List<AuthFuncPointIdDTO> list) {
        this.list = list;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    public String getControlList() {
        return controlList;
    }

    public void setControlList(String controlList) {
        this.controlList = controlList;
    }
}
