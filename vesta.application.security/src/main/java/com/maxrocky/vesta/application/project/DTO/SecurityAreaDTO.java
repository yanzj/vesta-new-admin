package com.maxrocky.vesta.application.project.DTO;

/**
 * Created by Jason on 2017/6/7.
 */
public class SecurityAreaDTO {
    private String groupId;//集团ID
    private String groupName;//集团名称
    private String areaId;//区域ID
    private String areaName;//区域名称
    private String securityOfficerDep;//项目安全员（hse部门）关联的部门
    private String securityOfficerStaff;//项目安全员（hse部门）关联的人

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
}
