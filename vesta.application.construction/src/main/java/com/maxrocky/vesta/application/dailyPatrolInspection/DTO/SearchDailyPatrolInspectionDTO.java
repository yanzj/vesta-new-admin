package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

/**
 * Created by Magic on 2016/10/26.
 */
public class SearchDailyPatrolInspectionDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private int inspectionAll;//全部
    private int inspectionIng;//进行中
    private int inspectionEnd;//完成
    public SearchDailyPatrolInspectionDTO(){
        this.projectId="";
        this.projectName="";
        this.buildingId="";
        this.buildingName="";
        this.inspectionAll=0;
        this.inspectionIng=0;
        this.inspectionEnd=0;
    }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getInspectionAll() {
        return inspectionAll;
    }

    public void setInspectionAll(int inspectionAll) {
        this.inspectionAll = inspectionAll;
    }

    public int getInspectionIng() {
        return inspectionIng;
    }

    public void setInspectionIng(int inspectionIng) {
        this.inspectionIng = inspectionIng;
    }

    public int getInspectionEnd() {
        return inspectionEnd;
    }

    public void setInspectionEnd(int inspectionEnd) {
        this.inspectionEnd = inspectionEnd;
    }
}
