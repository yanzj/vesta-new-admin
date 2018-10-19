package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/24.
 */
public class ProjectKeyProcessesExcelDTO {
    private int serialNumber;//序号
//    private String overdue;//超期
    private String processName;//工序名称
    private String projectName;//项目名称
    private String buildingName;//楼栋名称
    private String fourSortName;//四级分类名称
    private String severityRating;//严重等级
    private String partyPrincipalName;//甲方负责人名称
    private String supervisorName;//第三方监理名称
    private String supplierName;//责任单位名称
    private String assignName;//整改人名称
    private String completeOn;//完成期限
    private String state;//问题状态

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public String getFourSortName() {
        return fourSortName;
    }

    public void setFourSortName(String fourSortName) {
        this.fourSortName = fourSortName;
    }

    public String getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(String severityRating) {
        this.severityRating = severityRating;
    }

    public String getPartyPrincipalName() {
        return partyPrincipalName;
    }

    public void setPartyPrincipalName(String partyPrincipalName) {
        this.partyPrincipalName = partyPrincipalName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
