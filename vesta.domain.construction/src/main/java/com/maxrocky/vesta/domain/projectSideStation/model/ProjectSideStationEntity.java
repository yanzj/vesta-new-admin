package com.maxrocky.vesta.domain.projectSideStation.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程旁站信息
 * Created by Talent on 2016/11/8.
 */
@Entity
@Table(name = "project_side_station")
public class ProjectSideStationEntity {
    private Long id;//自增长ID
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
    private Date sideStationDate;//旁站日期
    private String sideStationStaffId;//旁站人员ID
    private String sideStationStaffName;//旁站人员名称
    private String createById;//创建人ID
    private String createByName;//创建人名称
    private Date createOn;//创建时间
    private String appId;//appId
    private String state;//状态 1：结束
    private Date endDate;//结束时间

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SIDE_STATION_ID", nullable = true, length = 32)
    public String getSideStationId() {
        return sideStationId;
    }

    public void setSideStationId(String sideStationId) {
        this.sideStationId = sideStationId;
    }

    @Basic
    @Column(name = "PROCESS_NAME", nullable = true, length = 50)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Basic
    @Column(name = "PROJECT_ID", nullable = true, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "BUILDING_ID", nullable = true, length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "BUILDING_NAME", nullable = true, length = 50)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "FLOOR_START", nullable = true, length = 11)
    public int getFloorStart() {
        return floorStart;
    }

    public void setFloorStart(int floorStart) {
        this.floorStart = floorStart;
    }

    @Basic
    @Column(name = "FLOOR_END", nullable = true, length = 11)
    public int getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(int floorEnd) {
        this.floorEnd = floorEnd;
    }

    @Basic
    @Column(name = "LOCATION", nullable = true, length = 50)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "FIRST_CATEGORY", nullable = true, length = 50)
    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }
    @Basic
    @Column(name = "FIRST_CATEGORY_NAME", nullable = true, length = 50)
    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    @Basic
    @Column(name = "SECOND_CATEGORY", nullable = true, length = 50)
    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }
    @Basic
    @Column(name = "SECOND_CATEGORY_NAME", nullable = true, length = 50)
    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    @Basic
    @Column(name = "SIDE_STATION_POINT", nullable = true, length = 1000)
    public String getSideStationPoint() {
        return sideStationPoint;
    }

    public void setSideStationPoint(String sideStationPoint) {
        this.sideStationPoint = sideStationPoint;
    }

    @Basic
    @Column(name = "SIDE_STATION_DATE", nullable = true)
    public Date getSideStationDate() {
        return sideStationDate;
    }

    public void setSideStationDate(Date sideStationDate) {
        this.sideStationDate = sideStationDate;
    }

    @Basic
    @Column(name = "SIDE_STATION_STAFF_ID", nullable = true, length = 32)
    public String getSideStationStaffId() {
        return sideStationStaffId;
    }

    public void setSideStationStaffId(String sideStationStaffId) {
        this.sideStationStaffId = sideStationStaffId;
    }

    @Basic
    @Column(name = "SIDE_STATION_STAFF_NAME", nullable = true, length = 50)
    public String getSideStationStaffName() {
        return sideStationStaffName;
    }

    public void setSideStationStaffName(String sideStationStaffName) {
        this.sideStationStaffName = sideStationStaffName;
    }

    @Basic
    @Column(name = "CREATE_BY_ID", nullable = true, length = 32)
    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    @Basic
    @Column(name = "CREATE_BY_NAME", nullable = true, length = 50)
    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @javax.persistence.Column(name = "APP_ID", unique = true, length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "STATE", nullable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "END_DATE", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
