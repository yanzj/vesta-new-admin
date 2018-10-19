package com.maxrocky.vesta.application.readilyTake.DTO;

/**
 * 随手拍查询列表
 * Created by Magic on 2017/6/9.
 */
public class ReadilyTakeListDTO {
    private String patId;//随手拍id
    private String projectName;//项目公司
    private String projectId;//项目公司Id
    private String creatName;//创建人
    private String creatDate;//创建时间
    private String state;//状态
    private String examinationParts;//检查部位
    private String severityLevel;//严重等级

    public ReadilyTakeListDTO(){
        this.creatDate="";//创建时间
        this.patId="";//随手拍id
        this.projectName="";//项目公司
        this.projectId="";//项目公司Id
        this.creatName="";//创建人
        this.state="";//状态
        this.examinationParts="";//检查部位
        this.severityLevel="";//严重等级
    }
    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }



    public String getExaminationParts() {
        return examinationParts;
    }

    public void setExaminationParts(String examinationParts) {
        this.examinationParts = examinationParts;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }
}
