package com.maxrocky.vesta.application.projectSideStation.DTO;

import java.util.Date;

/**
 * Created by Talent on 2016/11/9.
 */
public class ProjectSideStationExcelDTO {
    private int serialNumber;//编号
    private String projectName;//项目名称
    private String processName;//工序名称
    private String location;//具体位置
    private String sideStationStaffName;//旁站人员名称
    private Date sideStationDate;//旁站日期

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

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getSideStationDate() {
        return sideStationDate;
    }

    public void setSideStationDate(Date sideStationDate) {
        this.sideStationDate = sideStationDate;
    }

    public String getSideStationStaffName() {
        return sideStationStaffName;
    }

    public void setSideStationStaffName(String sideStationStaffName) {
        this.sideStationStaffName = sideStationStaffName;
    }
}
