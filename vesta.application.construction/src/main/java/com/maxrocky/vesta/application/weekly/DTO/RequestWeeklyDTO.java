package com.maxrocky.vesta.application.weekly.DTO;

/**
 * Created by Itzxs on 2018/4/9.
 */
public class RequestWeeklyDTO {
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目编码    项目id

    public RequestWeeklyDTO(String startDate, String endDate, String groupId, String regionId, String cityId, String projectId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupId = groupId;
        this.regionId = regionId;
        this.cityId = cityId;
        this.projectId = projectId;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
