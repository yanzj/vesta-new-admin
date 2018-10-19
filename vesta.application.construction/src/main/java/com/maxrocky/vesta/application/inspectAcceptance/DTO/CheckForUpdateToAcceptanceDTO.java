package com.maxrocky.vesta.application.inspectAcceptance.DTO;

/**
 * Created by jiazefeng on 2016/10/26.
 */
public class CheckForUpdateToAcceptanceDTO {
    private String id;
    private String timeStamp;
    private String updateFlag;
    private String projectNum;
    public CheckForUpdateToAcceptanceDTO(){
        this.id="";
        this.timeStamp="";
        this.updateFlag="";
        this.projectNum="";
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

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
