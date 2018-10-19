package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/7/9.
 * 实测实量实体类
 */
@Entity
@Table(name = "measure")
public class MeasureEntity {

    private String id;
    private String regionId;//区域ID
    private String regionName;//区域名称
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectId;//项目ID
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private int projectToTalScore;//项目总得分
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private int buildingToTalScore;//楼栋总得分
    private String unitId;//单元ID
    private String unitName;//单元名称
    private String floorId;//楼层ID
    private String floorName;//楼层名称
    private float mensurePercentOfPass;//标段实测合格率
    private String createBy;//添加人员
    private String createByName;//添加人员名称
    private Date modifyDate;//变更时间
    private Date createDate;//创建时间
    private String state;//状态  0 可用  1 删除

    public MeasureEntity() {
    }

    @Id
    @Column(name = "ID", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "REGION_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "REGION_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Basic
    @Column(name = "CITY_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "CITY_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "PROJECT_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NUM", length = 50,nullable = true, insertable = true, updatable = true)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "PROJECT_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "PROJECT_TOTAL_SCORE", nullable = true, insertable = true, updatable = true)
    public int getProjectToTalScore() {
        return projectToTalScore;
    }

    public void setProjectToTalScore(int projectToTalScore) {
        this.projectToTalScore = projectToTalScore;
    }

    @Basic
    @Column(name = "BUILDING_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "BUILDING_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "BUILDING_TOTAL_SCORE",nullable = true, insertable = true, updatable = true)
    public int getBuildingToTalScore() {
        return buildingToTalScore;
    }

    public void setBuildingToTalScore(int buildingToTalScore) {
        this.buildingToTalScore = buildingToTalScore;
    }

    @Basic
    @Column(name = "UNIT_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "UNIT_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "FLOOR_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "FLOOR_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @Basic
    @Column(name = "MENSURE_PERCENT_OF_PASS",nullable = true, insertable = true, updatable = true)
    public float getMensurePercentOfPass() {
        return mensurePercentOfPass;
    }

    public void setMensurePercentOfPass(float mensurePercentOfPass) {
        this.mensurePercentOfPass = mensurePercentOfPass;
    }

    @Basic
    @Column(name = "CREATE_BY", length = 50,nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_BY_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    @Basic
    @Column(name = "MODIFY_DATE", length = 50,nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "CREATE_DATE", length = 50,nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "STATE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
