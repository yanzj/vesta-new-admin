package com.maxrocky.vesta.domain.dailyPatrolInspection.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 日常巡检实体主表
 * Created by Magic on 2016/10/17.
 */
@Entity
@Table(name = "project_inspection")
public class DailyPatrolInspectionEntity {
    private Long id;//自增长id
    private String inspectionId;  //日常巡检单id
    private String supervisor;//第三方监理id
    private String supervisorName;//第三方监理名字
    private String pointId;//详细位置ID  相当于第三级分类
    private String projectId;//工程项目_ID
    private String projectNum;//工程项目编码
    private String buildingId;//楼栋id
    private String buildingNum;//楼栋编码
    private String floorId;//楼层id
    private String floorNum;//楼层编码
    private String supplierId;//责任单位ID
    private String supplier;//责任单位
    private String assignId;//整改人ID
    private String assignName;//整改人名字
    private String dealPeople;              //处理人ID
    private String firstParty;              //甲方负责人ID
    private String firstPartyName;          //甲方负责人名字
    private String title;//巡检标题
    private String description;//描述
    private BigDecimal xCoordinate;//x坐标
    private BigDecimal yCoordinate;//y坐标
    private Date rectificationTime;//整改时间
    private String state;//状态
    private String identity;//创建人标识
    private String createBy;//创建人
    private String createName;//创建人名字
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private String modifyName;//修改人姓名
    private Date modifyOn;//修改时间

    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String severityLevel;//严重等级
    private Date rectificationPeriod;//整改时限
    private String appId;//appId校验唯一性 防止重复

    private String shutDownBy;//关闭人姓名
    private String shutDown;//关闭理由
    private Date shutDownOn;//关闭时间

    private String classifyOneName;//一级分类名称
    private String classifyTwoName;//二级分类名称
    private String classifyThreeName;//三级分类名称

    @Id
    @Column(name = "ID", length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "INSPECTION_ID", length = 50, nullable = false, insertable = true, updatable = false)
    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }
    @Basic
    @Column(name = "SUPERVISOR", length = 32, nullable = true, insertable = true, updatable = true)
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    @Basic
    @Column(name = "POINT_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
    @Basic
    @Column(name = "PROJECT_ID", length = 50, nullable = true, insertable = true, updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "SUPPLIER_ID", length = 50, nullable = true, insertable = true, updatable = true)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    @Basic
    @Column(name = "ASSIGN_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }
    @Basic
    @Column(name = "TITLE", length = 50, nullable = true, insertable = true, updatable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Basic
    @Column(name = "DESCRIPTION", length = 500, nullable = true, insertable = true, updatable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "RECTIFICATION_TIME", nullable = true, insertable = true, updatable = true)
    public Date getRectificationTime() {
        return rectificationTime;
    }

    public void setRectificationTime(Date rectificationTime) {
        this.rectificationTime = rectificationTime;
    }
    @Basic
    @Column(name = "STATE", length = 20, nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "IDENTITY", length = 10, nullable = true, insertable = true, updatable = true)
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
    @Basic
    @Column(name = "CREATE_BY", length = 50, nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATE_ON", nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "MODIFY_BY", length = 50, nullable = true, insertable = true, updatable = true)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "MODIFY_ON", nullable = true, insertable = true, updatable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "X_COORDINATE", length = 50, nullable = true, insertable = true, updatable = true)
    public BigDecimal getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(BigDecimal xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    @Basic
    @Column(name = "Y_COORDINATE", length = 50, nullable = true, insertable = true, updatable = true)
    public BigDecimal getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(BigDecimal yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    @Basic
    @Column(name = "SUPERVISOR_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }
    @Basic
    @Column(name = "ASSIGN_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
    @Basic
    @Column(name = "CREATE_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "MODIFY_NAME", length = 100, nullable = true, insertable = true,  updatable = true)
    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_ONE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    @Basic
    @javax.persistence.Column(name = "CLASSIFY_TWO", nullable = true, insertable = true, updatable = true,length = 50)
    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    @Basic
    @javax.persistence.Column(name = "CLASSIFY_THREE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }
    @Basic
    @Column(name = "PROJECT_NUM", length = 100, nullable = true, insertable = true, updatable = true)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
    @Basic
    @Column(name = "BUILDING_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
    @Basic
    @Column(name = "BUILDING_NUM", length = 100, nullable = true, insertable = true, updatable = true)
    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }
    @Basic
    @Column(name = "FLOOR_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
    @Basic
    @Column(name = "FLOOR_NUM", length = 100, nullable = true, insertable = true, updatable = true)
    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }
    @Basic
    @Column(name = "SUPPLIER", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    @Column(name = "SEVERITY_LEVEL", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    @Basic
    @Column(name = "RECTIFICATION_PERIOD", nullable = true, insertable = true, updatable = true)
    public Date getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(Date rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }
    @Basic
    @Column(name = "DEAL_PEOPLE", length = 200, nullable = true, insertable = true, updatable = true)
    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }
    @Basic
    @Column(name = "FIRST_PARTY", length = 200, nullable = true, insertable = true, updatable = true)
    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }
    @Basic
    @Column(name = "FIRST_PARTY_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    @Basic
    @javax.persistence.Column(name = "APP_ID", unique = true ,length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Basic
    @Column(name = "SHUT_DOWN_BY", length = 100, nullable = true, insertable = true, updatable = true)
    public String getShutDownBy() {
        return shutDownBy;
    }

    public void setShutDownBy(String shutDownBy) {
        this.shutDownBy = shutDownBy;
    }
    @Basic
    @Column(name = "SHUT_DOWN", length = 500, nullable = true, insertable = true, updatable = true)
    public String getShutDown() {
        return shutDown;
    }

    public void setShutDown(String shutDown) {
        this.shutDown = shutDown;
    }
    @Basic
    @Column(name = "SHUT_DOWN_ON", length = 100, nullable = true, insertable = true, updatable = true)
    public Date getShutDownOn() {
        return shutDownOn;
    }

    public void setShutDownOn(Date shutDownOn) {
        this.shutDownOn = shutDownOn;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_ONE_NAME", nullable = true, insertable = true, updatable = true,length = 200)
    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_TWO_NAME", nullable = true, insertable = true, updatable = true,length = 200)
    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_THREE_NAME", nullable = true, insertable = true, updatable = true,length = 200)
    public String getClassifyThreeName() {
        return classifyThreeName;
    }

    public void setClassifyThreeName(String classifyThreeName) {
        this.classifyThreeName = classifyThreeName;
    }
}
