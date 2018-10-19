package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/10/18.
 * 工程项目数据封装类
 */
public class ProjectProjectDTO {
    private String projectId;    // 项目Id
    private String projectName;  // 项目名称
    private String cityId;       //城市ID
    private String optId;        //经营单位ID
    private String optName;      //经营单位名称
    private String cityName;     //城市名称
    private String modifyOn;     //修改时间

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
