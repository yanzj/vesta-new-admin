package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import java.util.Map;

/**
 * Created by Talent on 2016/11/11.
 */
public class ProjectDailyPatrolInspectionCountDTO {
    private String groupName;//集团名称
    private String operationId;//区域id
    private String operationName;//区域名称
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private String categoryName;//检查项
    private int total;//全部
    private int qualifiedToatl;//合格
    private int unqualifiedToatl;//不合格
    private int closses;//非正常关闭
    private String tenderId;//标段ID
    private String tenderName;//标段名称
    private Map<String, String> areas; //区域名称
    private Map<String, String> projects; // 项目名称
    private Map<String, String> tenders;//标段名称
    private Map<String, String> buildings; // 楼栋
    private String tabIndex;//tab标识 1：区域；2：项目；3楼栋；


    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
    }

    public Map<String, String> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<String, String> buildings) {
        this.buildings = buildings;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQualifiedToatl() {
        return qualifiedToatl;
    }

    public void setQualifiedToatl(int qualifiedToatl) {
        this.qualifiedToatl = qualifiedToatl;
    }

    public int getUnqualifiedToatl() {
        return unqualifiedToatl;
    }

    public void setUnqualifiedToatl(int unqualifiedToatl) {
        this.unqualifiedToatl = unqualifiedToatl;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public Map<String, String> getTenders() {
        return tenders;
    }

    public void setTenders(Map<String, String> tenders) {
        this.tenders = tenders;
    }

    public int getClosses() {
        return closses;
    }

    public void setClosses(int closses) {
        this.closses = closses;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Map<String, String> getAreas() {
        return areas;
    }

    public void setAreas(Map<String, String> areas) {
        this.areas = areas;
    }

    public String getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(String tabIndex) {
        this.tabIndex = tabIndex;
    }
}
