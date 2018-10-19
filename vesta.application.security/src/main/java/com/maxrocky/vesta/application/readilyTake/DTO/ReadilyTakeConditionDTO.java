package com.maxrocky.vesta.application.readilyTake.DTO;

/**
 * Created by Magic on 2017/6/8.
 * 随手拍 条件dto
 */
public class ReadilyTakeConditionDTO {
    private String startDate;//开始日期
    private String endDate;//结束时间
    private String severityLevel;//严重等级
    private String examinationParts;//检查部位
    private String projectId;//项目公司ID
    private String patId;//随手拍id
    private String state;//状态
    private String createName;//创建人

    public ReadilyTakeConditionDTO(){
        this.state="";
        this.startDate="";//开始日期
        this.endDate="";//结束时间
        this.severityLevel="";//严重等级
        this.examinationParts="";//检查部位
        this.projectId="";//项目公司ID
        this.patId="";
        this.createName="";

    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getExaminationParts() {
        return examinationParts;
    }

    public void setExaminationParts(String examinationParts) {
        this.examinationParts = examinationParts;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
