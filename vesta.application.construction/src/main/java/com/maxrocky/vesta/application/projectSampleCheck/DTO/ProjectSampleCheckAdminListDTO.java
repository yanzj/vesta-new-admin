package com.maxrocky.vesta.application.projectSampleCheck.DTO;

/**
 * Created by Magic on 2017/1/12.
 */
public class ProjectSampleCheckAdminListDTO {
    private String sampleCheckId;//样板点评ID
    private String projectName;//项目名称
    private String projectId;//项目ID
    private String title;//样板点评标题
    private String classifyThree;//三级分类
    private String severityLevel;//严重等级
    private String supplier;            //责任单位名称
    private String assignName;          //乙方负责人名字
    private String firstPartyName;      //甲方负责人名字
    private String supervisorName;      //第三方监理名字
    private String createOn;//创建时间
    private String state;//状态
    public ProjectSampleCheckAdminListDTO(){
        this.sampleCheckId="";
        this.projectName="";
        this.classifyThree="";
        this.severityLevel="";
        this.supplier="";
        this.assignName="";
        this.firstPartyName="";
        this.supervisorName="";
        this.createOn="";
        this.state="";
        this.title="";
        this.projectId="";
    }

    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
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

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
