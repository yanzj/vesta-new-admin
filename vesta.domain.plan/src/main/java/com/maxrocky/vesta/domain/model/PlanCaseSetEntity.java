package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/6.
 * 计划问题批次信息 实体
 */

@Entity
@Table(name = "plan_case_set")
public class PlanCaseSetEntity {
    private String setId;           //主键 批次ID
    private String planType;          //计划类型
    private String setName;         //批次名称
    private String projectId;       //项目ID
    private String areaId;          //区域ID
    private String buildingId;      //楼栋ID
    private String unitId;          //单元ID
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

    @Basic
    @Column(name = "APPROACH_DATE")
    public Date getApproachDate() {
        return approachDate;
    }

    public void setApproachDate(Date approachDate) {
        this.approachDate = approachDate;
    }

    @Basic
    @Column(name = "APPROACH_NUM",length = 32)
    public String getApproachNum() {
        return approachNum;
    }

    public void setApproachNum(String approachNum) {
        this.approachNum = approachNum;
    }

    @Basic
    @Column(name = "BUILDING_ID",length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "POSITION",length = 50)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "UNIT_ID",length = 50)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "FLOOR",length = 50)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "AREA_ID",length = 50)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "INSPECT_BY",length = 32)
    public String getInspectBy() {
        return inspectBy;
    }

    public void setInspectBy(String inspectBy) {
        this.inspectBy = inspectBy;
    }

    @Basic
    @Column(name = "INSPECT_DATE")
    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "SCOPE",length = 80)
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Id
    @Column(name = "SET_ID",length = 50,unique = true,nullable = false)
    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "SET_NAME",length = 32)
    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    @Basic
    @Column(name = "PLAN_TYPE",length = 80)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name = "STATUS",length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name="SET_TYPE",length = 50)
    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }
}
