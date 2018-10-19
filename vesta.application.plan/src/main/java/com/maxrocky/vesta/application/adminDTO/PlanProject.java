package com.maxrocky.vesta.application.adminDTO;

import java.util.List;

/**
 * Created by mql on 2016/5/16.
 */
public class PlanProject {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private List<PlanQuestionDTO> questionList;//问题列表

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

    public List<PlanQuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<PlanQuestionDTO> questionList) {
        this.questionList = questionList;
    }
}
