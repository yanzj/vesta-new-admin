package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/2/17.
 */
public class ComplaintsStatisticsDTO {
    private String projectName;//项目小区
    private Integer complaintsNumber = 0;//投诉数量
    private Integer feedbackNumber = 0;//反馈数量

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getComplaintsNumber() {
        return complaintsNumber;
    }

    public void setComplaintsNumber(Integer complaintsNumber) {
        this.complaintsNumber = complaintsNumber;
    }

    public Integer getFeedbackNumber() {
        return feedbackNumber;
    }

    public void setFeedbackNumber(Integer feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }
}
