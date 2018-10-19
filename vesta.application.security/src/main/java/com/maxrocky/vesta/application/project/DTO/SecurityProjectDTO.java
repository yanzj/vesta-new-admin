package com.maxrocky.vesta.application.project.DTO;

import java.util.List;

/**
 * Created by Jason on 2017/6/7.
 */
public class SecurityProjectDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String groupId;//集团ID
    private String groupName;//集团名称
    private String areaId;    //区域ID
    private String areaName;//区域名称
    private String projectOfficerDep;//项目全体人员关联的部门
    private String projectOfficerStaff;//项目全体人员关联的人
    private String partyASecurityOfficerDep;//甲方安全员关联的部门
    private String partyASecurityOfficerStaff;//甲方安全员关联的人
    private String partyAEngineerDep;//甲方工程师关联的部门
    private String partyAEngineerStaff;//甲方工程师关联的人
    private String contractorDep;//总包关联的部门
    private String contractorStaff;//总包关联的人
    private String supervisorDep;//监理关联的部门
    private String supervisortaff;//监理关联的人

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProjectOfficerDep() {
        return projectOfficerDep;
    }

    public void setProjectOfficerDep(String projectOfficerDep) {
        this.projectOfficerDep = projectOfficerDep;
    }

    public String getProjectOfficerStaff() {
        return projectOfficerStaff;
    }

    public void setProjectOfficerStaff(String projectOfficerStaff) {
        this.projectOfficerStaff = projectOfficerStaff;
    }

    public String getPartyASecurityOfficerDep() {
        return partyASecurityOfficerDep;
    }

    public void setPartyASecurityOfficerDep(String partyASecurityOfficerDep) {
        this.partyASecurityOfficerDep = partyASecurityOfficerDep;
    }

    public String getPartyASecurityOfficerStaff() {
        return partyASecurityOfficerStaff;
    }

    public void setPartyASecurityOfficerStaff(String partyASecurityOfficerStaff) {
        this.partyASecurityOfficerStaff = partyASecurityOfficerStaff;
    }

    public String getPartyAEngineerDep() {
        return partyAEngineerDep;
    }

    public void setPartyAEngineerDep(String partyAEngineerDep) {
        this.partyAEngineerDep = partyAEngineerDep;
    }

    public String getPartyAEngineerStaff() {
        return partyAEngineerStaff;
    }

    public void setPartyAEngineerStaff(String partyAEngineerStaff) {
        this.partyAEngineerStaff = partyAEngineerStaff;
    }

    public String getContractorDep() {
        return contractorDep;
    }

    public void setContractorDep(String contractorDep) {
        this.contractorDep = contractorDep;
    }

    public String getContractorStaff() {
        return contractorStaff;
    }

    public void setContractorStaff(String contractorStaff) {
        this.contractorStaff = contractorStaff;
    }

    public String getSupervisorDep() {
        return supervisorDep;
    }

    public void setSupervisorDep(String supervisorDep) {
        this.supervisorDep = supervisorDep;
    }

    public String getSupervisortaff() {
        return supervisortaff;
    }

    public void setSupervisortaff(String supervisortaff) {
        this.supervisortaff = supervisortaff;
    }
}
