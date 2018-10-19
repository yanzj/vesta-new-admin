package com.maxrocky.vesta.application.adminDTO;

import java.util.Date;

/**
 * Created by mql on 2016/5/20.
 * 批次
 */
public class PlanSetDTO {

    private String setId;           //主键 批次ID
    private String planType;          //模块类型
    private String setName;         //批次名称
    private String projectId;       //项目ID
    private String projectName;     //项目名称
    private String areaId;          //区域ID
    private String areaName;        //区域名称
    private String buildingId;      //楼栋ID
    private String buildingName;    //楼栋名称
    private String unitId;          //单元ID
    private String unitName;        //单元名称
    private String floor;           //楼层
    private String position;          //方位
    private String createBy;        //创建人
    private String scope;           //批次验收范围
    private Date createDate;        //创建时间
    private Date approachDate;      //进场时间
    private String approachNum;     //进场批量
    private String inspectBy;       //验收人员
    private Date inspectDate;     //验收时间
    private String status;         //批次状态 0关闭 1打开 2删除
    private String setType;         //类型：批次、活动

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getApproachDate() {
        return approachDate;
    }

    public void setApproachDate(Date approachDate) {
        this.approachDate = approachDate;
    }

    public String getApproachNum() {
        return approachNum;
    }

    public void setApproachNum(String approachNum) {
        this.approachNum = approachNum;
    }

    public String getInspectBy() {
        return inspectBy;
    }

    public void setInspectBy(String inspectBy) {
        this.inspectBy = inspectBy;
    }

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
