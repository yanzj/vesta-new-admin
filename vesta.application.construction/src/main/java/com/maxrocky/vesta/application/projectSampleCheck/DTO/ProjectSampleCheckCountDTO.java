package com.maxrocky.vesta.application.projectSampleCheck.DTO;

/**
 * Created by Magic on 2017/1/16.
 */
public class ProjectSampleCheckCountDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String classifyThree;//三级分类
    private int qualified;//合格数
    private int unqualified;//不合格数
    public ProjectSampleCheckCountDTO(){
        this.projectId="";
        this.projectName="";
        this.classifyThree="";
        this.qualified=0;
        this.unqualified=0;
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

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }


    public int getQualified() {
        return qualified;
    }

    public void setQualified(int qualified) {
        this.qualified = qualified;
    }

    public int getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(int unqualified) {
        this.unqualified = unqualified;
    }
}
