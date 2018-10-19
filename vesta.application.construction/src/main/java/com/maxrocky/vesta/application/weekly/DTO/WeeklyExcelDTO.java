package com.maxrocky.vesta.application.weekly.DTO;

/**
 * Created by Itzxs on 2018/4/13.
 */
public class WeeklyExcelDTO {
    private String projectName;
    private String ProjectInspection;
    private String ProjectExamine;
    private String ProjectMaterial;
    private String ProjectSampleCheck;
    private String ProjectKeyProcesses;
    private String ProjectSideStation;
    private String ProjectLeadersCheck;

    public WeeklyExcelDTO(){}

    public WeeklyExcelDTO(String projectName, String projectInspection, String projectExamine, String projectMaterial, String projectSampleCheck, String projectKeyProcesses, String projectSideStation, String projectLeadersCheck) {
        this.projectName = projectName;
        ProjectInspection = projectInspection;
        ProjectExamine = projectExamine;
        ProjectMaterial = projectMaterial;
        ProjectSampleCheck = projectSampleCheck;
        ProjectKeyProcesses = projectKeyProcesses;
        ProjectSideStation = projectSideStation;
        ProjectLeadersCheck = projectLeadersCheck;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectInspection() {
        return ProjectInspection;
    }

    public void setProjectInspection(String projectInspection) {
        ProjectInspection = projectInspection;
    }

    public String getProjectExamine() {
        return ProjectExamine;
    }

    public void setProjectExamine(String projectExamine) {
        ProjectExamine = projectExamine;
    }

    public String getProjectMaterial() {
        return ProjectMaterial;
    }

    public void setProjectMaterial(String projectMaterial) {
        ProjectMaterial = projectMaterial;
    }

    public String getProjectSampleCheck() {
        return ProjectSampleCheck;
    }

    public void setProjectSampleCheck(String projectSampleCheck) {
        ProjectSampleCheck = projectSampleCheck;
    }

    public String getProjectKeyProcesses() {
        return ProjectKeyProcesses;
    }

    public void setProjectKeyProcesses(String projectKeyProcesses) {
        ProjectKeyProcesses = projectKeyProcesses;
    }

    public String getProjectSideStation() {
        return ProjectSideStation;
    }

    public void setProjectSideStation(String projectSideStation) {
        ProjectSideStation = projectSideStation;
    }

    public String getProjectLeadersCheck() {
        return ProjectLeadersCheck;
    }

    public void setProjectLeadersCheck(String projectLeadersCheck) {
        ProjectLeadersCheck = projectLeadersCheck;
    }
}
