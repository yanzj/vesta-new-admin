package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/10/26.
 * 工程项目回传数据封装类
 */
public class ProjectProjectReceiveDTO {
    private String projectId;          //项目ID
    private String projectName;        //项目名称
    private String ownerDept;          //甲方权限关联部门ID
    private String ownerStaff;         //甲方权限关联人员ID
    private String viewDept;           //数据查看关联部门ID
    private String viewStaff;          //数据查看关联人员ID
    private String closeDept;          //关单权限关联部门ID
    private String closeStaff;         //关单权限关联人员ID
    private String surveyorDept;       //监理权限关联部门ID
    private String surveyorStaff;      //监理权限关联人员ID
    private String flag;               //特殊标记 1跳转项目权限 2跳转项目管理

    private String leaderStaff;//领导权限关联人员ID(Jason新加的)
    private String leaderDept;//领导权限管理部门ID(Jason新加的)

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

    public String getOwnerDept() {
        return ownerDept;
    }

    public void setOwnerDept(String ownerDept) {
        this.ownerDept = ownerDept;
    }

    public String getOwnerStaff() {
        return ownerStaff;
    }

    public void setOwnerStaff(String ownerStaff) {
        this.ownerStaff = ownerStaff;
    }

    public String getViewDept() {
        return viewDept;
    }

    public void setViewDept(String viewDept) {
        this.viewDept = viewDept;
    }

    public String getViewStaff() {
        return viewStaff;
    }

    public void setViewStaff(String viewStaff) {
        this.viewStaff = viewStaff;
    }

    public String getCloseDept() {
        return closeDept;
    }

    public void setCloseDept(String closeDept) {
        this.closeDept = closeDept;
    }

    public String getCloseStaff() {
        return closeStaff;
    }

    public void setCloseStaff(String closeStaff) {
        this.closeStaff = closeStaff;
    }

    public String getSurveyorDept() {
        return surveyorDept;
    }

    public void setSurveyorDept(String surveyorDept) {
        this.surveyorDept = surveyorDept;
    }

    public String getSurveyorStaff() {
        return surveyorStaff;
    }

    public void setSurveyorStaff(String surveyorStaff) {
        this.surveyorStaff = surveyorStaff;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLeaderStaff() {
        return leaderStaff;
    }

    public void setLeaderStaff(String leaderStaff) {
        this.leaderStaff = leaderStaff;
    }

    public String getLeaderDept() {
        return leaderDept;
    }

    public void setLeaderDept(String leaderDept) {
        this.leaderDept = leaderDept;
    }
}
