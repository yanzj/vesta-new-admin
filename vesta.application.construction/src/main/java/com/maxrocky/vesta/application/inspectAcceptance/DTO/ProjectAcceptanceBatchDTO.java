package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CopyDetailsListDTO;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JIAZEFENG on 2016/10/20.
 */
public class ProjectAcceptanceBatchDTO {
    private String selfId;//自增ID
    private String id;
    private String batchId;//批次ID
    private String projectNum;//项目num
    private String porjectName;//项目名称
    private String batchName;//批次名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋name
    private int floorStar;//开始楼层
    private int floorEnd;//结束楼层
    private String serial;//流水段
    private String severityRating;//严重等级
    private String supplierId;//责任单位ID
    private String supplierName;//责任单位名称
    private String assignId;//整改人ID
    private String assignName;//整改人名称
    private String completeOn;//完成期限
    private String isQualified;//检查批次是否合格 1：合格（点击合格的时候）0：不合格（点击不合格）
    private String state;//状态 1：合格； 0：整改中
    private String startDate;//创建时间
    private String checkTime;//检查时间
    private String modifyTime;
    private String handlePeopleId;//处理人ID
    //    private String abnormalShutdown;//非正常关闭
    private String firstSort;//一级分类
    private String firstSortName;//一级分类名称
    private String secondSort;//二级分类
    private String secondSortName;//二级分类名称
    private String threeSort;//三级分类
    private String threeSortName;//三级分类名称
    private String fourSort;//四级分类
    private String categoryName;//(四级分类名称)检查项
    private List<CopyDetailsListDTO> copyDetailsEntities;//抄送人
    private List<ProjectExamineTargetDetailsDTO> projectExamineTargetDetailsDTOs;//对应指标信息

    public ProjectAcceptanceBatchDTO() {
        this.selfId = "";//自增ID
        this.id = "";
        this.batchId = "";//批次ID
        this.projectNum = "";//项目num
        this.porjectName = "";//项目名称
        this.batchName = "";//批次名称
        this.buildingId = "";//楼栋ID
        this.buildingName = "";//楼栋name
        this.floorStar = 0;//开始楼层
        this.floorEnd = 0;//结束楼层
        this.serial = "";//流水段
        this.severityRating = "";//严重等级
        this.supplierId = "";//责任单位ID
        this.supplierName = "";//责任单位名称
        this.assignId = "";//整改人ID
        this.assignName = "";//整改人名称
        this.completeOn = "";//完成期限
        this.isQualified = "";//检查批次是否合格 1：合格（点击合格的时候）0：不合格（点击不合格）
        this.state = "";//状态 1：合格； 0：整改中
        this.startDate = "";//创建时间
        this.checkTime = "";//检查时间
        this.modifyTime = "";
        this.handlePeopleId = "";//处理人
        this.firstSort = "";
        this.firstSortName = "";
        this.secondSort = "";
        this.secondSortName = "";
        this.threeSort = "";
        this.threeSortName = "";
        this.fourSort = "";
        this.categoryName = "";//检查项
//        this.abnormalShutdown="";//非正常关闭
        this.copyDetailsEntities = new ArrayList<CopyDetailsListDTO>();//抄送人
        this.projectExamineTargetDetailsDTOs = new ArrayList<ProjectExamineTargetDetailsDTO>();//对应指标信息
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

    public String getHandlePeopleId() {
        return handlePeopleId;
    }

    public void setHandlePeopleId(String handlePeopleId) {
        this.handlePeopleId = handlePeopleId;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }

    public List<CopyDetailsListDTO> getCopyDetailsEntities() {
        return copyDetailsEntities;
    }

    public void setCopyDetailsEntities(List<CopyDetailsListDTO> copyDetailsEntities) {
        this.copyDetailsEntities = copyDetailsEntities;
    }

    public void setCompleteOn(String completeOn) {
        this.completeOn = completeOn;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
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

    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getCompleteOn() {
        return completeOn;
    }

    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }

    public List<ProjectExamineTargetDetailsDTO> getProjectExamineTargetDetailsDTOs() {
        return projectExamineTargetDetailsDTOs;
    }

    public void setProjectExamineTargetDetailsDTOs(List<ProjectExamineTargetDetailsDTO> projectExamineTargetDetailsDTOs) {
        this.projectExamineTargetDetailsDTOs = projectExamineTargetDetailsDTOs;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getPorjectName() {
        return porjectName;
    }

    public void setPorjectName(String porjectName) {
        this.porjectName = porjectName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

}
