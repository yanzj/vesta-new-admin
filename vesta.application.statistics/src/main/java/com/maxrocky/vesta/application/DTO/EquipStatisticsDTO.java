package com.maxrocky.vesta.application.DTO;

/**
 * Created by sunmei on 2016/2/17.
 */

public class EquipStatisticsDTO {
    private String project;//项目小区
    private String projectId;//项目id
    private String type;//设备类型
    private int counts;//设备个数

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
