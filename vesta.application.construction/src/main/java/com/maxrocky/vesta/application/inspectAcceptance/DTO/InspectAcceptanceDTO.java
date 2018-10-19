package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CopyDetailsListDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 检查验收
 * Created by jiazefeng on 2016/10/17.
 */
public class InspectAcceptanceDTO {
    private String id;//主键ID
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目ID
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
    private String isQualified;//合格
    private String isQualifiedForTarget;//指标是否合格
    private String isFirstQualified;//第一次合格 1:第一次合格；2：第二次合格
    private String domain;//模块
    private String modifyByName;//修改人名称
    private String firstClassification;//一级分类
    private String secondaryClassification;//二级分类
    private String threeClassification;//三级分类
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String overdue;//超期
    private String severityRating;//严重等级
    private String targetId;//检查指标ID
    private String description;//指标描述
    private String categoryName;//检查项
    private Date checkTime;//检查时间
    private Date creatDate;//创建时间
    private String creatBy;//创建人
    private String modifyDate;//修改时间
    private String abnormalShutdown;//非正常关闭
    private List<CopyDetailsListDTO> copyDetailsEntities;//抄送人
    private List<ProjectTargetDTO> projectTargetDTOList;//对应指标信息
    private List<ProjectTargetDTO> projectTargetChangeDTOList;//整改记录
    private List<ProjectTargetDTO> projectTargetAcceptanceDTOList;//验收记录
    private String tenderId;//标段ID

    private Map<String, String> firstLevels; // 一级分类列表
    private Map<String, String> secondLevels; // 二级分类列表
    private Map<String, String> thirdLevels; // 三级分类列表
    private Map<String, String> projects; // 项目名称
    private Map<String, String> tenders; // 标段名称
    private Map<String, String> buildings; // 楼栋
    private Map<String, String> suppliers;//供应商

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAbnormalShutdown() {
        return abnormalShutdown;
    }

    public void setAbnormalShutdown(String abnormalShutdown) {
        this.abnormalShutdown = abnormalShutdown;
    }

    public List<ProjectTargetDTO> getProjectTargetChangeDTOList() {
        return projectTargetChangeDTOList;
    }

    public void setProjectTargetChangeDTOList(List<ProjectTargetDTO> projectTargetChangeDTOList) {
        this.projectTargetChangeDTOList = projectTargetChangeDTOList;
    }

    public List<ProjectTargetDTO> getProjectTargetAcceptanceDTOList() {
        return projectTargetAcceptanceDTOList;
    }

    public void setProjectTargetAcceptanceDTOList(List<ProjectTargetDTO> projectTargetAcceptanceDTOList) {
        this.projectTargetAcceptanceDTOList = projectTargetAcceptanceDTOList;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getPartyPrincipalId() {
        return partyPrincipalId;
    }

    public void setPartyPrincipalId(String partyPrincipalId) {
        this.partyPrincipalId = partyPrincipalId;
    }

    public String getPartyPrincipalName() {
        return partyPrincipalName;
    }

    public void setPartyPrincipalName(String partyPrincipalName) {
        this.partyPrincipalName = partyPrincipalName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getAssignnName() {
        return assignnName;
    }

    public void setAssignnName(String assignnName) {
        this.assignnName = assignnName;
    }

    public int getFloorStar() {
        return floorStar;
    }

    public void setFloorStar(int floorStar) {
        this.floorStar = floorStar;
    }

    public int getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(int floorEnd) {
        this.floorEnd = floorEnd;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(Date completeOn) {
        this.completeOn = completeOn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }

    public String getIsFirstQualified() {
        return isFirstQualified;
    }

    public void setIsFirstQualified(String isFirstQualified) {
        this.isFirstQualified = isFirstQualified;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public String getModifyByName() {
        return modifyByName;
    }

    public void setModifyByName(String modifyByName) {
        this.modifyByName = modifyByName;
    }


    public String getFirstClassification() {
        return firstClassification;
    }

    public void setFirstClassification(String firstClassification) {
        this.firstClassification = firstClassification;
    }

    public String getSecondaryClassification() {
        return secondaryClassification;
    }

    public void setSecondaryClassification(String secondaryClassification) {
        this.secondaryClassification = secondaryClassification;
    }

    public String getThreeClassification() {
        return threeClassification;
    }

    public void setThreeClassification(String threeClassification) {
        this.threeClassification = threeClassification;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public Map<String, String> getFirstLevels() {
        return firstLevels;
    }

    public void setFirstLevels(Map<String, String> firstLevels) {
        this.firstLevels = firstLevels;
    }

    public Map<String, String> getSecondLevels() {
        return secondLevels;
    }

    public void setSecondLevels(Map<String, String> secondLevels) {
        this.secondLevels = secondLevels;
    }

    public Map<String, String> getThirdLevels() {
        return thirdLevels;
    }

    public void setThirdLevels(Map<String, String> thirdLevels) {
        this.thirdLevels = thirdLevels;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
    }

    public Map<String, String> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<String, String> buildings) {
        this.buildings = buildings;
    }

    public Map<String, String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Map<String, String> suppliers) {
        this.suppliers = suppliers;
    }

    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getIsQualifiedForTarget() {
        return isQualifiedForTarget;
    }

    public void setIsQualifiedForTarget(String isQualifiedForTarget) {
        this.isQualifiedForTarget = isQualifiedForTarget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public List<CopyDetailsListDTO> getCopyDetailsEntities() {
        return copyDetailsEntities;
    }

    public void setCopyDetailsEntities(List<CopyDetailsListDTO> copyDetailsEntities) {
        this.copyDetailsEntities = copyDetailsEntities;
    }

    public List<ProjectTargetDTO> getProjectTargetDTOList() {
        return projectTargetDTOList;
    }

    public void setProjectTargetDTOList(List<ProjectTargetDTO> projectTargetDTOList) {
        this.projectTargetDTOList = projectTargetDTOList;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public Map<String, String> getTenders() {
        return tenders;
    }

    public void setTenders(Map<String, String> tenders) {
        this.tenders = tenders;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
