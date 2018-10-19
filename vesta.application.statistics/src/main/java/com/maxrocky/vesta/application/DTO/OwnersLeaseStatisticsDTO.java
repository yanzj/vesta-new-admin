package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 业主、出租统计DTO
 */
public class OwnersLeaseStatisticsDTO {
    private String projectName;//项目小区
    private Integer roomNumber = 0;//房间数量
    private Integer registrationNumber = 0;//注册数量
    private Integer relativesNumbert = 0;//亲属数量
    private Integer tenantsNumbert = 0;//租客数量

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getRelativesNumbert() {
        return relativesNumbert;
    }

    public void setRelativesNumbert(Integer relativesNumbert) {
        this.relativesNumbert = relativesNumbert;
    }

    public Integer getTenantsNumbert() {
        return tenantsNumbert;
    }

    public void setTenantsNumbert(Integer tenantsNumbert) {
        this.tenantsNumbert = tenantsNumbert;
    }
}
