package com.maxrocky.vesta.application.projectSideStation.DTO;

/**
 * Created by JAIZEFENG on 2016/10/19.
 */
public class ProjectSideStationCountDTO {
    private String projectId;//项目ID
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private int total;//总数


    public ProjectSideStationCountDTO() {
        this.projectId = "";
        this.buildingId = "";
        this.buildingName = "";
        this.total = 0;

    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
