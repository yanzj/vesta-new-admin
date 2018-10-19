package com.maxrocky.vesta.application.projectSampleCheck.DTO;

/**
 * Created by Talent on 2016/11/23.
 */
public class SampleCheckForUpdateDTO {
    private String id;
    private String timeStamp;
    private String updateFlag;
    private String projectId;

    public SampleCheckForUpdateDTO() {
        this.id = "";
        this.timeStamp = "";
        this.updateFlag = "";
        this.projectId = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
