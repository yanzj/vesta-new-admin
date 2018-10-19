package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

/**
 * Created by Magic on 2016/10/25.
 */
public class CheckDailyPatrolInspectionDTO {
    private String id;
    private String timeStamp;
    private String updateFlag;
    private String projectId;
    public CheckDailyPatrolInspectionDTO(){
        this.id="";
        this.timeStamp="";
        this.updateFlag="";
        this.projectId="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
