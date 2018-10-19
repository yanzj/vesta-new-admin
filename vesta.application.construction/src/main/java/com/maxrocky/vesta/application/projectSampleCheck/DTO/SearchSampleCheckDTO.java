package com.maxrocky.vesta.application.projectSampleCheck.DTO;

/**
 * Created by Magic on 2017/1/10.
 */
public class SearchSampleCheckDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String classifyOne;//一级分类
    private String classifyOneName;//一级分类名称
    private int sampleCheckAll;//全部
    private int sampleCheckIng;//不合格（整改中）
    private int sampleCheckEnd;//合格（已完成）
    public SearchSampleCheckDTO(){
        this.projectId="";
        this.projectName="";
        this.classifyOne="";
        this.classifyOneName="";
        this.sampleCheckAll=0;
        this.sampleCheckEnd=0;
        this.sampleCheckIng=0;
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

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }

    public int getSampleCheckAll() {
        return sampleCheckAll;
    }

    public void setSampleCheckAll(int sampleCheckAll) {
        this.sampleCheckAll = sampleCheckAll;
    }

    public int getSampleCheckIng() {
        return sampleCheckIng;
    }

    public void setSampleCheckIng(int sampleCheckIng) {
        this.sampleCheckIng = sampleCheckIng;
    }

    public int getSampleCheckEnd() {
        return sampleCheckEnd;
    }

    public void setSampleCheckEnd(int sampleCheckEnd) {
        this.sampleCheckEnd = sampleCheckEnd;
    }
}
