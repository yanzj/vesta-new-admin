package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/1/17.
 * 物业项目公司DTO
 */
public class PropertyCompanyDTO {
    private String companyId;        // ID
    private String companyName;      // 公司名称
    private String propertyProject;  // 项目
    private String propertyArea;     // 区域
    private String projectAdmin;     // 项目管理员
    private String roleName;         // 角色
    private String name;             // 姓名
    private String phnoe;            // 手机号

    private String   propertyMessageTime; // 项目公司添加时间
    private String responsiblePerson;// 总负责人
    private String responsiblePhone; // 总负责人电话
    private String projectAdminPhone;// 项目管理员电话

    private String propertyType;     // 状态(禁用或启用)
    private String roleId;          //权限Id

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnoe() {
        return phnoe;
    }

    public void setPhnoe(String phnoe) {
        this.phnoe = phnoe;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPropertyProject() {
        return propertyProject;
    }

    public void setPropertyProject(String propertyProject) {
        this.propertyProject = propertyProject;
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }

    public String getProjectAdmin() {
        return projectAdmin;
    }

    public void setProjectAdmin(String projectAdmin) {
        this.projectAdmin = projectAdmin;
    }

    public String getProjectAdminPhone() {
        return projectAdminPhone;
    }

    public void setProjectAdminPhone(String projectAdminPhone) {
        this.projectAdminPhone = projectAdminPhone;
    }

    public String getPropertyMessageTime() {
        return propertyMessageTime;
    }

    public void setPropertyMessageTime(String propertyMessageTime) {
        this.propertyMessageTime = propertyMessageTime;
    }
}

