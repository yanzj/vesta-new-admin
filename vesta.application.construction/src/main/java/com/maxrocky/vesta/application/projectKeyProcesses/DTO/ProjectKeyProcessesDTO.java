package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/24.
 */
public class ProjectKeyProcessesDTO {
    private String processId;//工序ID
    private String processName;//工序名称
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private String floorStar;//开始楼层
    private String floorEnd;//结束楼层
    private String serial;//流水段
    private String examinationDate;//检查时间
    private String severityRating;//严重等级
    private String partyPrincipalId;//甲方负责人ID
    private String partyPrincipalName;//甲方负责人名称
    private String supervisorId;//第三方监理id
    private String supervisorName;//第三方监理名称
    private String supplierId;//责任单位ID
    private String supplierName;//责任单位名称
    private String assignId;//整改人ID(乙方)
    private String assignName;//整改人名称
    private String completeOn;//完成期限
    private String qualifiedState;//合格 (不合格、不合格)
    private String firstSort;//一级分类
    private String firstSortName;//一级分类名称
    private String secondSort;//二级分类
    private String secondSortName;//二级分类名称
    private String threeSort;//三级分类
    private String threeSortName;//三级分类名称
    private String fourSort;//四级分类
    private String fourSortName;//四级分类名称
    private String state;//问题状态
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String overdue;//超期
    private String abnormalShutdown;//非正常关闭
    private String createBy;//创建人
    private String createDate;//创建时间
    private String modifyName;//修改人
    private String modifyDate;//修改时间
    private List<ProjectKeyProcessesTargetDTO> targetDTOList;//工序指标信息
    private List<ProjectKeyProcessesTargetDTO> targetDTOByPartyBAnnalList;//乙方整改信息
    private List<ProjectKeyProcessesTargetDTO> targetDTOBySupervisionAnnalList;//监理整改信息
    private List<ProjectKeyProcessesTargetDTO> targetDTOByPartyAAnnalList;//甲方整改信息

    private String tenderId;//标段ID
    private List<KeyProcessesCopyDTO> copyDTOList;//工序抄送人

    private Map<String, String> firstLevels; // 一级分类列表

    private Map<String, String> secondLevels; // 二级分类列表

    private Map<String, String> thirdLevels; // 三级分类列表

    private Map<String, String> projects; // 项目名称
    private Map<String, String> tenders; // 标段名称
    private Map<String, String> buildings; // 楼栋

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorStar() {
        return floorStar;
    }

    public void setFloorStar(String floorStar) {
        this.floorStar = floorStar;
    }

    public String getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(String floorEnd) {
        this.floorEnd = floorEnd;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(String examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
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

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(String completeOn) {
        this.completeOn = completeOn;
    }

    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }

    public String getFirstSort() {
        return firstSort;
    }

    public void setFirstSort(String firstSort) {
        this.firstSort = firstSort;
    }

    public String getFirstSortName() {
        return firstSortName;
    }

    public void setFirstSortName(String firstSortName) {
        this.firstSortName = firstSortName;
    }

    public String getSecondSort() {
        return secondSort;
    }

    public void setSecondSort(String secondSort) {
        this.secondSort = secondSort;
    }

    public String getSecondSortName() {
        return secondSortName;
    }

    public void setSecondSortName(String secondSortName) {
        this.secondSortName = secondSortName;
    }

    public String getThreeSort() {
        return threeSort;
    }

    public void setThreeSort(String threeSort) {
        this.threeSort = threeSort;
    }

    public String getThreeSortName() {
        return threeSortName;
    }

    public void setThreeSortName(String threeSortName) {
        this.threeSortName = threeSortName;
    }

    public String getFourSort() {
        return fourSort;
    }

    public void setFourSort(String fourSort) {
        this.fourSort = fourSort;
    }

    public String getFourSortName() {
        return fourSortName;
    }

    public void setFourSortName(String fourSortName) {
        this.fourSortName = fourSortName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<KeyProcessesCopyDTO> getCopyDTOList() {
        return copyDTOList;
    }

    public void setCopyDTOList(List<KeyProcessesCopyDTO> copyDTOList) {
        this.copyDTOList = copyDTOList;
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

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public String getAbnormalShutdown() {
        return abnormalShutdown;
    }

    public void setAbnormalShutdown(String abnormalShutdown) {
        this.abnormalShutdown = abnormalShutdown;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOList() {
        return targetDTOList;
    }

    public void setTargetDTOList(List<ProjectKeyProcessesTargetDTO> targetDTOList) {
        this.targetDTOList = targetDTOList;
    }

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOByPartyBAnnalList() {
        return targetDTOByPartyBAnnalList;
    }

    public void setTargetDTOByPartyBAnnalList(List<ProjectKeyProcessesTargetDTO> targetDTOByPartyBAnnalList) {
        this.targetDTOByPartyBAnnalList = targetDTOByPartyBAnnalList;
    }

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOBySupervisionAnnalList() {
        return targetDTOBySupervisionAnnalList;
    }

    public void setTargetDTOBySupervisionAnnalList(List<ProjectKeyProcessesTargetDTO> targetDTOBySupervisionAnnalList) {
        this.targetDTOBySupervisionAnnalList = targetDTOBySupervisionAnnalList;
    }

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOByPartyAAnnalList() {
        return targetDTOByPartyAAnnalList;
    }

    public void setTargetDTOByPartyAAnnalList(List<ProjectKeyProcessesTargetDTO> targetDTOByPartyAAnnalList) {
        this.targetDTOByPartyAAnnalList = targetDTOByPartyAAnnalList;
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
