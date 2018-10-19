package com.maxrocky.vesta.domain.projectSampleCheck.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评
 */
@Entity
@Table(name = "project_sample_check")
public class ProjectSampleCheckEntity {
    private Long id;//自增长ID
    private String appId;//app端传入 校验唯一性 防止重复
    private String sampleCheckId;//样板点评ID
    private String title;//样板点评标题
    private String description;//描述
    private String createBy;//创建人ID
    private String createName;//创建人姓名
    private Date createOn;//创建时间
    private Date modifyDate;//修改时间
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋id
    private String buildingName;//楼栋名称
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String classifyOneName;//一级分类名称
    private String classifyTwoName;//二级分类名称
    private String classifyThreeName;//三级分类名称
    private String state;//状态
    private String severityLevel;//严重等级
    private String floorNum1;//楼层区间-始
    private String floorNum2;//楼层区间-终
    private String checkPosition;//检查部位
    private Date checkDate;//检查时间

    private String supplierId;          //责任单位ID
    private String supplier;            //责任单位名称
    private String assignId;            //乙方负责人ID
    private String assignName;          //乙方负责人名字
    private String supervisorId;          //第三方监理id
    private String supervisorName;      //第三方监理名字
    private String firstParty;          //甲方负责人ID
    private String firstPartyName;      //甲方负责人名字
    private String dealPeople;          //处理人ID

    private Date rectificationPeriod;//整改时限
    private String shutDownBy;//关闭人姓名
    private String shutDown;//关闭理由
    private Date shutDownOn;//关闭时间
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
    @javax.persistence.Column(name = "APP_ID", unique = true ,length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Basic
    @Column(name = "SAMPLE_CHECK_ID", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
    }
    @Basic
    @Column(name = "TITLE", length = 2000, nullable = true, insertable = true, updatable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Basic
    @Column(name = "DESCRIPTION", length = 2000, nullable = true, insertable = true, updatable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic
    @Column(name = "CREATE_BY", length = 100, nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATE_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "CREATE_ON", length = 200, nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "PROJECT_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "PROJECT_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
    @Column(name = "BUILDING_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    @Basic
    @Column(name = "CLASSIFY_ONE", length = 100, nullable = true, insertable = true, updatable = true)
    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }
    @Basic
    @Column(name = "CLASSIFY_TWO", length = 100, nullable = true, insertable = true, updatable = true)
    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }
    @Basic
    @Column(name = "CLASSIFY_THREE", length = 100, nullable = true, insertable = true, updatable = true)
    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }
    @Basic
    @Column(name = "CLASSIFY_ONE_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }
    @Basic
    @Column(name = "CLASSIFY_TWO_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }
    @Basic
    @Column(name = "CLASSIFY_THREE_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getClassifyThreeName() {
        return classifyThreeName;
    }

    public void setClassifyThreeName(String classifyThreeName) {
        this.classifyThreeName = classifyThreeName;
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
    @Column(name = "SEVERITYLEVEL", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    @Basic
    @Column(name = "FLOORNUM1", length = 100, nullable = true, insertable = true, updatable = true)
    public String getFloorNum1() {
        return floorNum1;
    }

    public void setFloorNum1(String floorNum1) {
        this.floorNum1 = floorNum1;
    }
    @Basic
    @Column(name = "FLOORNUM2", length = 100, nullable = true, insertable = true, updatable = true)
    public String getFloorNum2() {
        return floorNum2;
    }

    public void setFloorNum2(String floorNum2) {
        this.floorNum2 = floorNum2;
    }
    @Basic
    @Column(name = "CHECKPOSITION", length = 200, nullable = true, insertable = true, updatable = true)
    public String getCheckPosition() {
        return checkPosition;
    }

    public void setCheckPosition(String checkPosition) {
        this.checkPosition = checkPosition;
    }
    @Basic
    @Column(name = "CHECKDATE", length = 200, nullable = true, insertable = true, updatable = true)
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    @Basic
    @Column(name = "SUPPLIER_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
    @Column(name = "ASSIGN_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }
    @Basic
    @Column(name = "ASSIGN_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
    @Basic
    @Column(name = "SUPERVISOR_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }
    @Basic
    @Column(name = "SUPERVISOR_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
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
    @Column(name = "DEALPEOPLE", length = 200, nullable = true, insertable = true, updatable = true)
    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }
    @Basic
    @Column(name = "RECTIFICATIONPERIOD", length = 100, nullable = true, insertable = true, updatable = true)
    public Date getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(Date rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }
    @Basic
    @Column(name = "SHUTDOWN_BY", length = 100, nullable = true, insertable = true, updatable = true)
    public String getShutDownBy() {
        return shutDownBy;
    }

    public void setShutDownBy(String shutDownBy) {
        this.shutDownBy = shutDownBy;
    }
    @Basic
    @Column(name = "SHUTDOWN", length = 2000, nullable = true, insertable = true, updatable = true)
    public String getShutDown() {
        return shutDown;
    }

    public void setShutDown(String shutDown) {
        this.shutDown = shutDown;
    }
    @Basic
    @Column(name = "SHUTDOWN_ON", length = 100, nullable = true, insertable = true, updatable = true)
    public Date getShutDownOn() {
        return shutDownOn;
    }

    public void setShutDownOn(Date shutDownOn) {
        this.shutDownOn = shutDownOn;
    }
    @Basic
    @Column(name = "MODIFYDATE", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
