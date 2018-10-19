package com.maxrocky.vesta.application.projectSideStation.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/8.
 */
public class ProjectSideStationInfoDTO {

    private String processName;//工序名称
    private String location;//具体位置
    private Date sideStationDate;//旁站日期
    private String sideStationTimeSpace;//旁站日期
    private String sideStationId;//旁站ID
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String firstCategory;//一级分类
    private String secondCategory;//二级分类
    private String sideStationStartDate;//旁站开始日期
    private String sideStationEndDate;//旁站结束日期
    private String sideStationStaffName;//旁站人员名称
    private String state;//状态
    private List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOs;//旁站描述详情
    private Map<String, String> firstLevels; // 一级分类列表

    private Map<String, String> secondLevels; // 二级分类列表

    private Map<String, String> thirdLevels; // 二级分类列表

    private Map<String, String> projects; // 项目名称

    public String getSideStationTimeSpace() {
        return sideStationTimeSpace;
    }

    public void setSideStationTimeSpace(String sideStationTimeSpace) {
        this.sideStationTimeSpace = sideStationTimeSpace;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getSideStationDate() {
        return sideStationDate;
    }

    public void setSideStationDate(Date sideStationDate) {
        this.sideStationDate = sideStationDate;
    }

    public String getSideStationId() {
        return sideStationId;
    }

    public void setSideStationId(String sideStationId) {
        this.sideStationId = sideStationId;
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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }

    public String getSideStationStartDate() {
        return sideStationStartDate;
    }

    public void setSideStationStartDate(String sideStationStartDate) {
        this.sideStationStartDate = sideStationStartDate;
    }

    public String getSideStationEndDate() {
        return sideStationEndDate;
    }

    public void setSideStationEndDate(String sideStationEndDate) {
        this.sideStationEndDate = sideStationEndDate;
    }

    public String getSideStationStaffName() {
        return sideStationStaffName;
    }

    public void setSideStationStaffName(String sideStationStaffName) {
        this.sideStationStaffName = sideStationStaffName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ProjectSideStationDetailsDTO> getProjectSideStationDetailsDTOs() {
        return projectSideStationDetailsDTOs;
    }

    public void setProjectSideStationDetailsDTOs(List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOs) {
        this.projectSideStationDetailsDTOs = projectSideStationDetailsDTOs;
    }

    public Map<String, String> getFirstLevels() {
        return firstLevels;
    }

    public void setFirstLevels(Map<String, String> firstLevels) {
        this.firstLevels = firstLevels;
    }

    public Map<String, String> getSecondLevels() {
        return secondLevels;
    }

    public void setSecondLevels(Map<String, String> secondLevels) {
        this.secondLevels = secondLevels;
    }

    public Map<String, String> getThirdLevels() {
        return thirdLevels;
    }

    public void setThirdLevels(Map<String, String> thirdLevels) {
        this.thirdLevels = thirdLevels;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
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
}
