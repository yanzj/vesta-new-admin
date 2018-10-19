package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CopyDetailsListDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 检查验收
 * Created by jiazefeng on 2016/10/17.
 */
public class InspectAcceptanceExcelDTO {
    private int serialNumber;//序号
    //    private String overdue;//超期
    private String batchName;//批次名称
    private String projectName;//项目名称
    private String buildingName;//楼栋名称
    private String categoryName;//检查项
    private String severityRating;//严重等级
    private String supervisorName;//第三方监理名称
    private String supplierName;//责任单位名称
    private String assignnName;//整改人名称
    private Date completeOn;//登记时间
    private String state;//问题状态

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAssignnName() {
        return assignnName;
    }

    public void setAssignnName(String assignnName) {
        this.assignnName = assignnName;
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
}
