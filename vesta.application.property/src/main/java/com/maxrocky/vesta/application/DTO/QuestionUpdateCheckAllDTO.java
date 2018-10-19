package com.maxrocky.vesta.application.DTO;

/**
 * Created by Magic on 2016/10/19.
 */
public class QuestionUpdateCheckAllDTO {
    private String id;
    private String time;
    private String updateFlag;
    private String projectNum;
    public QuestionUpdateCheckAllDTO(){
        this.id="";
        this.time="";
        this.updateFlag="";
        this.projectNum="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
