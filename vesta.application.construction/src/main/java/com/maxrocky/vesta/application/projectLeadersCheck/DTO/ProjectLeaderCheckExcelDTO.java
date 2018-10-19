package com.maxrocky.vesta.application.projectLeadersCheck.DTO;

/**
 * Created by Jason on 2017/3/28.
 */
public class ProjectLeaderCheckExcelDTO {
    private int serialNumber;//序号
    private String projectName;//项目名称
    private String createName;//检查人名称
    private String createDate;//创建时间
    private String assignName;//整改人名称
    private String state;//问题状态

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
