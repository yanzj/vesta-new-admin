package com.maxrocky.vesta.domain.inspectAcceptance.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程检查验收
 * <p>
 * Created by JIAZEFENG on 2016/10/17.
 */
@Entity
@Table(name = "project_examine")
public class ProjectExamineEntity {
    private Long id;//ID自增长
    private String projectNum;//项目NUM
    private String projectName;//项目名称
    private String batchId;//批次ID
    private String batchName;//批次名称
    private String partyPrincipalId;//甲方负责人ID
    private String partyPrincipalName;//甲方负责人名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private String supervisorId;//第三方监理id
    private String supervisorName;//第三方监理名称
    private String supplierId;//责任单位ID
    private String supplierName;//责任单位名称
    private String assignId;//整改人ID
    private String assignnName;//整改人名称
    private int floorStar;//开始楼层
    private int floorEnd;//结束楼层
    private String serial;//流水段
    private Date completeOn;//完成期限
    private String state;//问题状态
    private String isQualified;//合格 0：不合格；1：不合格
    private String isFirstQualified;//第一次合格
    private String domain;//模块
    private String createBy;//创建人ID
    private String createName;//创建人名称
    private Date createOn;//创建时间
    private String modifyBy;//修改人Id
    private String modifyName;//修改人名称
    private Date modifyOn;//修改时间
    private String firstClassification;//一级分类
    private String firstClassificationName;//一级分类
    private String secondaryClassification;//二级分类
    private String secondaryClassificationName;//二级分类名称
    private String threeClassification;//三级分类
    private String threeClassificationName;//三级分类名称
    private String fourClassification;//四级分类
    private String categoryName;//（四级分类名称）检查项
    private String severityRating;//严重等级
    //    private String isOnePass;//一次性通过 1：一次通过（走新增接口的时候，如果是合格就是一次通过） 0：不是一次通过
    private Date checkTime;//检查时间
    private String copyId;//抄送人ID
    private String copyName;//抄送人名称
    private String handlePeopleId;//处理人ID
    private String handlePeopleName;//处理人姓名
    private String abnormalShutdown;//非正常关闭
    private String appId;//appId校验唯一性 防止重复

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
    @Column(name = "PROJECT_NUM", nullable = true, length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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
    @Column(name = "BATCH_ID", nullable = false, length = 50)
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "BATCH_NAME", nullable = true, length = 50)
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    @Basic
    @Column(name = "PARTY_PRINCIPAL_ID", nullable = true, length = 32)
    public String getPartyPrincipalId() {
        return partyPrincipalId;
    }

    public void setPartyPrincipalId(String partyPrincipalId) {
        this.partyPrincipalId = partyPrincipalId;
    }

    @Basic
    @Column(name = "PARTY_PRINCIPAL_NAME", nullable = true, length = 50)
    public String getPartyPrincipalName() {
        return partyPrincipalName;
    }

    public void setPartyPrincipalName(String partyPrincipalName) {
        this.partyPrincipalName = partyPrincipalName;
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
    @Column(name = "SUPERVISOR_ID", nullable = true, length = 32)
    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Basic
    @Column(name = "SUPERVISOR_NAME", nullable = true, length = 32)
    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    @Basic
    @Column(name = "SUPPLIER_ID", nullable = true, length = 32)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "SUPPLIER_NAME", nullable = true, length = 50)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "ASSIGN_ID", nullable = true, length = 32)
    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    @Basic
    @Column(name = "ASSIGN_NAME", nullable = true, length = 50)
    public String getAssignnName() {
        return assignnName;
    }

    public void setAssignnName(String assignnName) {
        this.assignnName = assignnName;
    }

    @Basic
    @Column(name = "FLOOR_STAR")
    public int getFloorStar() {
        return floorStar;
    }

    public void setFloorStar(int floorStar) {
        this.floorStar = floorStar;
    }

    @Basic
    @Column(name = "FLOOR_END")
    public int getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(int floorEnd) {
        this.floorEnd = floorEnd;
    }

    @Basic
    @Column(name = "SERIAL", nullable = true, length = 50)
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Basic
    @Column(name = "COMPLETE_ON", nullable = true)
    public Date getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(Date completeOn) {
        this.completeOn = completeOn;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "IS_QUALIFIED", nullable = true, length = 50)
    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }

    @Basic
    @Column(name = "IS_FIRST_QUALIFIED", nullable = true, length = 50)
    public String getIsFirstQualified() {
        return isFirstQualified;
    }

    public void setIsFirstQualified(String isFirstQualified) {
        this.isFirstQualified = isFirstQualified;
    }

    @Basic
    @Column(name = "DOMAIN", nullable = true, length = 10)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "MODIFY_BY", nullable = true, length = 32)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "FIRST_CLASSIFICATION", nullable = true, length = 50)
    public String getFirstClassification() {
        return firstClassification;
    }

    public void setFirstClassification(String firstClassification) {
        this.firstClassification = firstClassification;
    }

    @Basic
    @Column(name = "FIRST_CLASSIFICATION_NAME", nullable = true, length = 50)
    public String getFirstClassificationName() {
        return firstClassificationName;
    }

    public void setFirstClassificationName(String firstClassificationName) {
        this.firstClassificationName = firstClassificationName;
    }

    @Basic
    @Column(name = "SECONDARY_CLASSIFICATION", nullable = true, length = 50)
    public String getSecondaryClassification() {
        return secondaryClassification;
    }

    public void setSecondaryClassification(String secondaryClassification) {
        this.secondaryClassification = secondaryClassification;
    }

    @Basic
    @Column(name = "SECONDARY_CLASSIFICATION_NAME", nullable = true, length = 50)
    public String getSecondaryClassificationName() {
        return secondaryClassificationName;
    }

    public void setSecondaryClassificationName(String secondaryClassificationName) {
        this.secondaryClassificationName = secondaryClassificationName;
    }

    @Basic
    @Column(name = "THREE_CLASSIFICATION", nullable = true, length = 50)
    public String getThreeClassification() {
        return threeClassification;
    }

    public void setThreeClassification(String threeClassification) {
        this.threeClassification = threeClassification;
    }

    @Basic
    @Column(name = "THREE_CLASSIFICATION_NAME", nullable = true, length = 50)
    public String getThreeClassificationName() {
        return threeClassificationName;
    }

    public void setThreeClassificationName(String threeClassificationName) {
        this.threeClassificationName = threeClassificationName;
    }

    @Basic
    @Column(name = "FOUR_CLASSIFICATION", nullable = true, length = 50)
    public String getFourClassification() {
        return fourClassification;
    }

    public void setFourClassification(String fourClassification) {
        this.fourClassification = fourClassification;
    }

    @Basic
    @Column(name = "CATEGORY_NAME", nullable = true, length = 50)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "SEVERITY_RATING", nullable = true, length = 32)
    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
    }


    @Basic
    @Column(name = "CHECK_TIME", nullable = true)
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }


    @Basic
    @Column(name = "COPY_ID", nullable = true, length = 50)
    public String getCopyId() {
        return copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    @Basic
    @Column(name = "COPY_NAME", nullable = true, length = 50)
    public String getCopyName() {
        return copyName;
    }

    public void setCopyName(String copyName) {
        this.copyName = copyName;
    }

    @Basic
    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Basic
    @Column(name = "MODIFY_NAME", nullable = true, length = 50)
    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    @Basic
    @Column(name = "HANDLE_PEOPLE_ID", nullable = true, length = 50)
    public String getHandlePeopleId() {
        return handlePeopleId;
    }

    public void setHandlePeopleId(String handlePeopleId) {
        this.handlePeopleId = handlePeopleId;
    }

    @Basic
    @Column(name = "HANDLE_PEOPLE_NAME", nullable = true, length = 50)
    public String getHandlePeopleName() {
        return handlePeopleName;
    }

    public void setHandlePeopleName(String handlePeopleName) {
        this.handlePeopleName = handlePeopleName;
    }

    @Basic
    @Column(name = "ABNORMAL_SHUTDOWN", nullable = true, length = 500)
    public String getAbnormalShutdown() {
        return abnormalShutdown;
    }

    public void setAbnormalShutdown(String abnormalShutdown) {
        this.abnormalShutdown = abnormalShutdown;
    }

    @Basic
    @javax.persistence.Column(name = "APP_ID", unique = true, length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
