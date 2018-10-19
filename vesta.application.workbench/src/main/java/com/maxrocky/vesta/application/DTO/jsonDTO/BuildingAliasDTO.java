package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhaowen on 2016/8/30.14:06
 * Describe:
 */
public class BuildingAliasDTO {

    private String id;
    private String groupId;//总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;
    private String  areaId;// 地块编码
    private String projectName;//项目名称
    private String buildingId;// 楼栋ID
    private List<String> userProject;
    List <Map<String,String>> buildings;// 楼栋
    private String buildingAlias;
    private String buildingNum;

   /* private Date startDate;//开始日期
    private Date endDate;//结束时间*/

    private String startDate;//开始日期
    private String endDate;//结束时间

    private Map<String,String> projects; // 项目名称


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<Map<String, String>> getBuildings() {
        return buildings;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public List<String> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<String> userProject) {
        this.userProject = userProject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
    }


    public String getBuildingAlias() {
        return buildingAlias;
    }

    public void setBuildingAlias(String buildingAlias) {
        this.buildingAlias = buildingAlias;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public void setBuildings(List<Map<String, String>> buildings) {
        this.buildings = buildings;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
