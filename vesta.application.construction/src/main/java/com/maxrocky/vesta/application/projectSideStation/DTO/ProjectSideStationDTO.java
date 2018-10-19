package com.maxrocky.vesta.application.projectSideStation.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Talent on 2016/11/8.
 */
public class ProjectSideStationDTO {
    private String id;//APP自用id
    private String selfId;//自增ID
    private String sideStationId;//旁站ID
    private String processName;//工序名称
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private int floorStart;//开始楼层
    private int floorEnd;//结束楼层
    private String location;//具体位置
    private String firstCategory;//一级分类
    private String firstCategoryName;//一级分类名称
    private String secondCategory;//二级分类
    private String secondCategoryName;//二级分类名称
    private String sideStationPoint;//旁站要点
    private String sideStationDate;//旁站日期
    private String sideStationStaffId;//旁站人员ID
    private String sideStationStaffName;//旁站人员名称
    private String createDate;//创建时间
    private List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOs;//旁站描述详情

    public ProjectSideStationDTO() {
        this.sideStationId = "";
        this.id = "";//APP自用id
        this.selfId = "";
        this.processName = "";//工序名称
        this.projectId = "";//项目ID
        this.projectName = "";//项目名称
        this.buildingId = "";//楼栋ID
        this.buildingName = "";//楼栋名称
        this.floorStart = 0;//开始楼层
        this.floorEnd = 0;//结束楼层
        this.location = "";//具体位置
        this.firstCategory = "";//一级分类
        this.firstCategoryName = "";//一级分类名称
        this.secondCategory = "";//二级分类
        this.secondCategoryName = "";//二级分类名称
        this.sideStationPoint = "";//旁站要点
        this.sideStationDate = "";//旁站日期
        this.createDate = "";
        this.sideStationStaffId = "";//旁站人员ID
        this.sideStationStaffName = "";//旁站人员名称
        this.projectSideStationDetailsDTOs = new ArrayList<ProjectSideStationDetailsDTO>();//旁站描述详情
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }

    public String getSideStationId() {
        return sideStationId;
    }

    public void setSideStationId(String sideStationId) {
        this.sideStationId = sideStationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getFloorStart() {
        return floorStart;
    }

    public void setFloorStart(int floorStart) {
        this.floorStart = floorStart;
    }

    public int getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(int floorEnd) {
        this.floorEnd = floorEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getSideStationPoint() {
        return sideStationPoint;
    }

    public void setSideStationPoint(String sideStationPoint) {
        this.sideStationPoint = sideStationPoint;
    }

    public String getSideStationDate() {
        return sideStationDate;
    }

    public void setSideStationDate(String sideStationDate) {
        this.sideStationDate = sideStationDate;
    }

    public String getSideStationStaffId() {
        return sideStationStaffId;
    }

    public void setSideStationStaffId(String sideStationStaffId) {
        this.sideStationStaffId = sideStationStaffId;
    }

    public String getSideStationStaffName() {
        return sideStationStaffName;
    }

    public void setSideStationStaffName(String sideStationStaffName) {
        this.sideStationStaffName = sideStationStaffName;
    }

    public List<ProjectSideStationDetailsDTO> getProjectSideStationDetailsDTOs() {
        return projectSideStationDetailsDTOs;
    }

    public void setProjectSideStationDetailsDTOs(List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOs) {
        this.projectSideStationDetailsDTOs = projectSideStationDetailsDTOs;
    }
}
