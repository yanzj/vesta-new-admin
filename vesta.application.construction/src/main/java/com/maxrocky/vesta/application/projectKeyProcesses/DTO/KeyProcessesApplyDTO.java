package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.List;

/**
 * 关键工序提交申请的信息
 * Created by Talent on 2016/11/22.
 */
public class KeyProcessesApplyDTO {
    private String appId;//appId校验唯一性 防止重复
    private String processName;//批次名称
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private String floorStar;//开始楼层
    private String floorEnd;//结束楼层
    private String serial;//流水段
    private String examinationDate;//检查时间
    private String severityRating;//严重等级
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
    private String fourSortName;//（四级分类名称）检查项
    private List<KeyProcessesTargetApplyDTO> keyProcessesTargetApplyDTOList;//工序指标信息
    private List<KeyProcessesCopyDTO> keyProcessesCopyDTOList;//工序抄送人

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public List<KeyProcessesTargetApplyDTO> getKeyProcessesTargetApplyDTOList() {
        return keyProcessesTargetApplyDTOList;
    }

    public void setKeyProcessesTargetApplyDTOList(List<KeyProcessesTargetApplyDTO> keyProcessesTargetApplyDTOList) {
        this.keyProcessesTargetApplyDTOList = keyProcessesTargetApplyDTOList;
    }

    public List<KeyProcessesCopyDTO> getKeyProcessesCopyDTOList() {
        return keyProcessesCopyDTOList;
    }

    public void setKeyProcessesCopyDTOList(List<KeyProcessesCopyDTO> keyProcessesCopyDTOList) {
        this.keyProcessesCopyDTOList = keyProcessesCopyDTOList;
    }

    public String getFirstSort() {
        return firstSort;
    }

    public void setFirstSort(String firstSort) {
        this.firstSort = firstSort;
    }

    public String getSecondSort() {
        return secondSort;
    }

    public void setSecondSort(String secondSort) {
        this.secondSort = secondSort;
    }

    public String getThreeSort() {
        return threeSort;
    }

    public void setThreeSort(String threeSort) {
        this.threeSort = threeSort;
    }

    public String getFourSort() {
        return fourSort;
    }

    public void setFourSort(String fourSort) {
        this.fourSort = fourSort;
    }

    public String getFirstSortName() {
        return firstSortName;
    }

    public void setFirstSortName(String firstSortName) {
        this.firstSortName = firstSortName;
    }

    public String getSecondSortName() {
        return secondSortName;
    }

    public void setSecondSortName(String secondSortName) {
        this.secondSortName = secondSortName;
    }

    public String getThreeSortName() {
        return threeSortName;
    }

    public void setThreeSortName(String threeSortName) {
        this.threeSortName = threeSortName;
    }

    public String getFourSortName() {
        return fourSortName;
    }

    public void setFourSortName(String fourSortName) {
        this.fourSortName = fourSortName;
    }
}
