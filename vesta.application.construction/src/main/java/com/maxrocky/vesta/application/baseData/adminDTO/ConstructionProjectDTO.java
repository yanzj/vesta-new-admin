package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by yuanyn on 2017/8/28.
 */
public class ConstructionProjectDTO {
    private String securityOfficerDep;//项目关联的部门
    private String securityOfficerStaff;//项目关联的人
    private String projectBelongArea;//人员关联的区域
    private String projectBelongProject;//人员关联的项目

    public String getSecurityOfficerDep() {
        return securityOfficerDep;
    }

    public void setSecurityOfficerDep(String securityOfficerDep) {
        this.securityOfficerDep = securityOfficerDep;
    }

    public String getSecurityOfficerStaff() {
        return securityOfficerStaff;
    }

    public void setSecurityOfficerStaff(String securityOfficerStaff) {
        this.securityOfficerStaff = securityOfficerStaff;
    }

    public String getProjectBelongArea() {
        return projectBelongArea;
    }

    public void setProjectBelongArea(String projectBelongArea) {
        this.projectBelongArea = projectBelongArea;
    }

    public String getProjectBelongProject() {
        return projectBelongProject;
    }

    public void setProjectBelongProject(String projectBelongProject) {
        this.projectBelongProject = projectBelongProject;
    }
}
