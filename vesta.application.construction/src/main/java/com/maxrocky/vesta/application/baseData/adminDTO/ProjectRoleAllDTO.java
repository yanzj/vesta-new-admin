package com.maxrocky.vesta.application.baseData.adminDTO;

import java.util.List;

/**
 * Created by chen on 2016/10/31.
 */
public class ProjectRoleAllDTO {
    private String projectId;                           //项目ID
    private String projectName;                         //项目名
    private String modifyOn;                            //修改时间
    private List<ProjectRoleDTO> ownerDepts;            //甲方权限部门
    private List<ProjectRoleDTO> ownerStaffs;           //甲方权限人员
    private List<ProjectRoleDTO> viewDepts;             //数据查看部门
    private List<ProjectRoleDTO> viewStaffs;            //数据查看人员
    private List<ProjectRoleDTO> closeDepts;            //非正常关单部门
    private List<ProjectRoleDTO> closeStaffs;           //非正常关单人员
    private List<ProjectRoleDTO> surveyorDepts;         //项目监理权限
    private List<ProjectRoleDTO> surveyorStaffs;        //项目监理权限
    private List<ProjectRoleDTO> leaderStaffs;//领导权限人员
    private List<ProjectRoleDTO> leaderDepts;//领导权限部门

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

    public List<ProjectRoleDTO> getOwnerDepts() {
        return ownerDepts;
    }

    public void setOwnerDepts(List<ProjectRoleDTO> ownerDepts) {
        this.ownerDepts = ownerDepts;
    }

    public List<ProjectRoleDTO> getOwnerStaffs() {
        return ownerStaffs;
    }

    public void setOwnerStaffs(List<ProjectRoleDTO> ownerStaffs) {
        this.ownerStaffs = ownerStaffs;
    }

    public List<ProjectRoleDTO> getViewDepts() {
        return viewDepts;
    }

    public void setViewDepts(List<ProjectRoleDTO> viewDepts) {
        this.viewDepts = viewDepts;
    }

    public List<ProjectRoleDTO> getViewStaffs() {
        return viewStaffs;
    }

    public void setViewStaffs(List<ProjectRoleDTO> viewStaffs) {
        this.viewStaffs = viewStaffs;
    }

    public List<ProjectRoleDTO> getCloseDepts() {
        return closeDepts;
    }

    public void setCloseDepts(List<ProjectRoleDTO> closeDepts) {
        this.closeDepts = closeDepts;
    }

    public List<ProjectRoleDTO> getCloseStaffs() {
        return closeStaffs;
    }

    public void setCloseStaffs(List<ProjectRoleDTO> closeStaffs) {
        this.closeStaffs = closeStaffs;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public List<ProjectRoleDTO> getSurveyorDepts() {
        return surveyorDepts;
    }

    public void setSurveyorDepts(List<ProjectRoleDTO> surveyorDepts) {
        this.surveyorDepts = surveyorDepts;
    }

    public List<ProjectRoleDTO> getSurveyorStaffs() {
        return surveyorStaffs;
    }

    public void setSurveyorStaffs(List<ProjectRoleDTO> surveyorStaffs) {
        this.surveyorStaffs = surveyorStaffs;
    }

    public List<ProjectRoleDTO> getLeaderStaffs() {
        return leaderStaffs;
    }

    public void setLeaderStaffs(List<ProjectRoleDTO> leaderStaffs) {
        this.leaderStaffs = leaderStaffs;
    }

    public List<ProjectRoleDTO> getLeaderDepts() {
        return leaderDepts;
    }

    public void setLeaderDepts(List<ProjectRoleDTO> leaderDepts) {
        this.leaderDepts = leaderDepts;
    }
}
