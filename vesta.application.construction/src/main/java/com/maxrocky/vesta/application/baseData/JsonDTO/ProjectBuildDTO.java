package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/10/25.
 */
public class ProjectBuildDTO {
    private String currentId="";
    private String name="";
    private String parentId="";
    private String grade="";        //1楼栋 2楼层
    private String state="";

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
