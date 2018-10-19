package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by mql on 2016/5/23.
 */

@Entity
@Table(name = "plan_set_init")
public class PlanSetInitEntity {

    private String id;//主键
    private String initName;//名称
    private String planType;//计划类型
    private String projectId;//项目ID
    private String areaId;//区域ID
    private String buildingId;//楼栋ID
    private String unitId;//单元ID
    private String floor;//楼层
    private String position;//方位
    private String isEffective;//是否生效

    @Id
    @Column(name="ID",nullable = false,length = 50,unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="INIT_NAME",length = 300)
    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }

    @Basic
    @Column(name="PLAN_TYPE",length=50)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name="PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name="AREA_ID",length = 50)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name="BUILDING_ID",length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name="UNIT_ID",length = 50)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name="FLOOR",length = 50)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name="POSITION",length = 50)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
     @Column(name="IS_EFFECTIVE",length = 50)
     public String getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }
}
