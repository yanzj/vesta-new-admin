package com.maxrocky.vesta.application.project.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/6/6.
 */
public class SecurityAllRoleDTO {
    private String id;
    private String name;
    private String modifyDate;//最后修改时间
    private List<SecurityRoleDTO> groupDep;//集团HSE部门关联的部门
    private List<SecurityRoleDTO> groupStaff;//集团HSE部门关联的人
    private List<SecurityRoleDTO> securityOfficerDep;//区域安全员关联的部门
    private List<SecurityRoleDTO> securityOfficerStaff;//区域安全员关联的人
    private List<SecurityRoleDTO> projectOfficerDep;//项目全体人员关联的部门
    private List<SecurityRoleDTO> projectOfficerStaff;//项目全体人员关联的人
    private List<SecurityRoleDTO> partyASecurityOfficerDep;//甲方安全员关联的部门
    private List<SecurityRoleDTO> partyASecurityOfficerStaff;//甲方安全员关联的人
    private List<SecurityRoleDTO> partyAEngineerDep;//甲方工程师关联的部门
    private List<SecurityRoleDTO> partyAEngineerStaff;//甲方工程师关联的人
    private List<SecurityRoleDTO> contractorDep;//总包关联的部门
    private List<SecurityRoleDTO> contractorStaff;//总包关联的人
    private List<SecurityRoleDTO> supervisorDep;//监理关联的部门
    private List<SecurityRoleDTO> supervisortaff;//监理关联的人

    public SecurityAllRoleDTO() {
        this.id = "";
        this.name = "";
        this.modifyDate = "";
        this.groupDep = new ArrayList<SecurityRoleDTO>();
        this.groupStaff = new ArrayList<SecurityRoleDTO>();
        this.securityOfficerDep = new ArrayList<SecurityRoleDTO>();
        this.securityOfficerStaff = new ArrayList<SecurityRoleDTO>();
        this.projectOfficerDep = new ArrayList<SecurityRoleDTO>();
        this.projectOfficerStaff = new ArrayList<SecurityRoleDTO>();
        this.partyASecurityOfficerDep = new ArrayList<SecurityRoleDTO>();
        this.partyASecurityOfficerStaff = new ArrayList<SecurityRoleDTO>();
        this.partyAEngineerDep = new ArrayList<SecurityRoleDTO>();
        this.partyAEngineerStaff = new ArrayList<SecurityRoleDTO>();
        this.contractorDep = new ArrayList<SecurityRoleDTO>();
        this.contractorStaff = new ArrayList<SecurityRoleDTO>();
        this.supervisorDep = new ArrayList<SecurityRoleDTO>();
        this.supervisortaff = new ArrayList<SecurityRoleDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<SecurityRoleDTO> getGroupDep() {
        return groupDep;
    }

    public void setGroupDep(List<SecurityRoleDTO> groupDep) {
        this.groupDep = groupDep;
    }

    public List<SecurityRoleDTO> getGroupStaff() {
        return groupStaff;
    }

    public void setGroupStaff(List<SecurityRoleDTO> groupStaff) {
        this.groupStaff = groupStaff;
    }

    public List<SecurityRoleDTO> getSecurityOfficerDep() {
        return securityOfficerDep;
    }

    public void setSecurityOfficerDep(List<SecurityRoleDTO> securityOfficerDep) {
        this.securityOfficerDep = securityOfficerDep;
    }

    public List<SecurityRoleDTO> getSecurityOfficerStaff() {
        return securityOfficerStaff;
    }

    public void setSecurityOfficerStaff(List<SecurityRoleDTO> securityOfficerStaff) {
        this.securityOfficerStaff = securityOfficerStaff;
    }

    public List<SecurityRoleDTO> getProjectOfficerDep() {
        return projectOfficerDep;
    }

    public void setProjectOfficerDep(List<SecurityRoleDTO> projectOfficerDep) {
        this.projectOfficerDep = projectOfficerDep;
    }

    public List<SecurityRoleDTO> getProjectOfficerStaff() {
        return projectOfficerStaff;
    }

    public void setProjectOfficerStaff(List<SecurityRoleDTO> projectOfficerStaff) {
        this.projectOfficerStaff = projectOfficerStaff;
    }

    public List<SecurityRoleDTO> getPartyASecurityOfficerDep() {
        return partyASecurityOfficerDep;
    }

    public void setPartyASecurityOfficerDep(List<SecurityRoleDTO> partyASecurityOfficerDep) {
        this.partyASecurityOfficerDep = partyASecurityOfficerDep;
    }

    public List<SecurityRoleDTO> getPartyASecurityOfficerStaff() {
        return partyASecurityOfficerStaff;
    }

    public void setPartyASecurityOfficerStaff(List<SecurityRoleDTO> partyASecurityOfficerStaff) {
        this.partyASecurityOfficerStaff = partyASecurityOfficerStaff;
    }

    public List<SecurityRoleDTO> getPartyAEngineerDep() {
        return partyAEngineerDep;
    }

    public void setPartyAEngineerDep(List<SecurityRoleDTO> partyAEngineerDep) {
        this.partyAEngineerDep = partyAEngineerDep;
    }

    public List<SecurityRoleDTO> getPartyAEngineerStaff() {
        return partyAEngineerStaff;
    }

    public void setPartyAEngineerStaff(List<SecurityRoleDTO> partyAEngineerStaff) {
        this.partyAEngineerStaff = partyAEngineerStaff;
    }

    public List<SecurityRoleDTO> getContractorDep() {
        return contractorDep;
    }

    public void setContractorDep(List<SecurityRoleDTO> contractorDep) {
        this.contractorDep = contractorDep;
    }

    public List<SecurityRoleDTO> getContractorStaff() {
        return contractorStaff;
    }

    public void setContractorStaff(List<SecurityRoleDTO> contractorStaff) {
        this.contractorStaff = contractorStaff;
    }

    public List<SecurityRoleDTO> getSupervisorDep() {
        return supervisorDep;
    }

    public void setSupervisorDep(List<SecurityRoleDTO> supervisorDep) {
        this.supervisorDep = supervisorDep;
    }

    public List<SecurityRoleDTO> getSupervisortaff() {
        return supervisortaff;
    }

    public void setSupervisortaff(List<SecurityRoleDTO> supervisortaff) {
        this.supervisortaff = supervisortaff;
    }
}
