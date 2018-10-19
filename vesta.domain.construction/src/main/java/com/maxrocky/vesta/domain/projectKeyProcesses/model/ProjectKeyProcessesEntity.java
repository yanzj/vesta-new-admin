package com.maxrocky.vesta.domain.projectKeyProcesses.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 关键工序
 * Created by Talent on 2016/11/21.
 */
@Entity
@Table(name = "project_key_processes")
public class ProjectKeyProcessesEntity {
    private String appId;//appId校验唯一性 防止重复
    private Long id;//ID自增长
    private String processId;//批次ID
    private String processName;//批次名称
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private String floorStar;//开始楼层
    private String floorEnd;//结束楼层
    private String serial;//流水段
    private Date examinationDate;//检查时间
    private String severityRating;//严重等级
    private String partyPrincipalId;//甲方负责人ID
    private String partyPrincipalName;//甲方负责人名称
    private String supervisorId;//第三方监理id
    private String supervisorName;//第三方监理名称
    private String supplierId;//责任单位ID
    private String supplierName;//责任单位名称
    private String assignId;//整改人ID(乙方)
    private String assignName;//整改人名称
    private Date completeOn;//完成期限
    private String qualifiedState;//合格 (不合格、不合格)
    private String firstQualifiedState;//第一次合格
    private String state;//问题状态(合格、整改中)
    private String createBy;//创建人ID
    private String createName;//创建人名称
    private Date createOn;//创建时间
    private String modifyBy;//修改人Id
    private String modifyName;//修改人名称
    private Date modifyOn;//修改时间
    private String handlePeopleId;//处理人ID
    private String abnormalShutdown;//非正常关闭
    private String firstSort;//一级分类
    private String firstSortName;//一级分类名称
    private String secondSort;//二级分类
    private String secondSortName;//二级分类名称
    private String threeSort;//三级分类
    private String threeSortName;//三级分类名称
    private String fourSort;//四级分类
    private String fourSortName;//四级分类名称

    @Basic
    @javax.persistence.Column(name = "APP_ID", unique = true, length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

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
    @Column(name = "PROCESS_ID", nullable = false, length = 50)
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Basic
    @Column(name = "PROCESS_NAME", nullable = false, length = 100)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Basic
    @Column(name = "PROJECT_ID", nullable = false, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "BUILDING_ID", nullable = false, length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "BUILDING_NAME", nullable = true, length = 100)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "FLOOR_STAR", nullable = true, length = 10)
    public String getFloorStar() {
        return floorStar;
    }

    public void setFloorStar(String floorStar) {
        this.floorStar = floorStar;
    }

    @Basic
    @Column(name = "FLOOR_END", nullable = true, length = 10)
    public String getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(String floorEnd) {
        this.floorEnd = floorEnd;
    }

    @Basic
    @Column(name = "SERIAL", nullable = true, length = 100)
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Basic
    @Column(name = "EXAMINATION_DATE", nullable = true)
    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    @Basic
    @Column(name = "SEVERITY_RATING", nullable = true, length = 16)
    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
    }

    @Basic
    @Column(name = "PARTY_PRINCIPAL_ID", nullable = false, length = 50)
    public String getPartyPrincipalId() {
        return partyPrincipalId;
    }

    public void setPartyPrincipalId(String partyPrincipalId) {
        this.partyPrincipalId = partyPrincipalId;
    }

    @Basic
    @Column(name = "PARTY_PRINCIPAL_NAME", nullable = true, length = 100)
    public String getPartyPrincipalName() {
        return partyPrincipalName;
    }

    public void setPartyPrincipalName(String partyPrincipalName) {
        this.partyPrincipalName = partyPrincipalName;
    }

    @Basic
    @Column(name = "SUPERVISOR_ID", nullable = false, length = 50)
    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Basic
    @Column(name = "SUPERVISOR_NAME", nullable = true, length = 100)
    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    @Basic
    @Column(name = "SUPPLIER_ID", nullable = false, length = 50)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "SUPPLIER_NAME", nullable = true, length = 100)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "ASSIGN_ID", nullable = false, length = 50)
    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    @Basic
    @Column(name = "ASSIGN_NAME", nullable = true, length = 100)
    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
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
    @Column(name = "QUALIFIED_STATE", nullable = true, length = 10)
    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }

    @Basic
    @Column(name = "FIRST_QUALIFIED_STATE", nullable = true, length = 10)
    public String getFirstQualifiedState() {
        return firstQualifiedState;
    }

    public void setFirstQualifiedState(String firstQualifiedState) {
        this.firstQualifiedState = firstQualifiedState;
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
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
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
    @Column(name = "ABNORMAL_SHUTDOWN", nullable = true, length = 500)
    public String getAbnormalShutdown() {
        return abnormalShutdown;
    }

    public void setAbnormalShutdown(String abnormalShutdown) {
        this.abnormalShutdown = abnormalShutdown;
    }

    @Basic
    @Column(name = "FIRST_SORT", nullable = true, length = 50)
    public String getFirstSort() {
        return firstSort;
    }

    public void setFirstSort(String firstSort) {
        this.firstSort = firstSort;
    }

    @Basic
    @Column(name = "FIRST_SORT_NAME", nullable = true, length = 50)
    public String getFirstSortName() {
        return firstSortName;
    }

    public void setFirstSortName(String firstSortName) {
        this.firstSortName = firstSortName;
    }

    @Basic
    @Column(name = "SECOND_SORT", nullable = true, length = 50)
    public String getSecondSort() {
        return secondSort;
    }

    public void setSecondSort(String secondSort) {
        this.secondSort = secondSort;
    }

    @Basic
    @Column(name = "SECOND_SORT_NAME", nullable = true, length = 50)
    public String getSecondSortName() {
        return secondSortName;
    }

    public void setSecondSortName(String secondSortName) {
        this.secondSortName = secondSortName;
    }

    @Basic
    @Column(name = "THREE_SORT", nullable = true, length = 50)
    public String getThreeSort() {
        return threeSort;
    }

    public void setThreeSort(String threeSort) {
        this.threeSort = threeSort;
    }

    @Basic
    @Column(name = "THREE_SORT_NAME", nullable = true, length = 50)
    public String getThreeSortName() {
        return threeSortName;
    }

    public void setThreeSortName(String threeSortName) {
        this.threeSortName = threeSortName;
    }

    @Basic
    @Column(name = "FOUR_SORT", nullable = true, length = 50)
    public String getFourSort() {
        return fourSort;
    }

    public void setFourSort(String fourSort) {
        this.fourSort = fourSort;
    }

    @Basic
    @Column(name = "FOUR_SORT_NAME", nullable = true, length = 50)
    public String getFourSortName() {
        return fourSortName;
    }

    public void setFourSortName(String fourSortName) {
        this.fourSortName = fourSortName;
    }
}
