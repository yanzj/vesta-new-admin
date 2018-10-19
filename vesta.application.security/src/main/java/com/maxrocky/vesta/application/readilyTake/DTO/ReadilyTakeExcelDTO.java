package com.maxrocky.vesta.application.readilyTake.DTO;

/**
 * 导出ExcelDTO
 * Created by yuanyn on 2017/7/14.
 */
public class ReadilyTakeExcelDTO {
    private String projectName;//项目公司
    private String creatName;//创建人
    private String creatDate;//创建时间
    private String examinationParts;//检查部位
    private String severityLevel;//严重等级
    private String state;//状态

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
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
}
