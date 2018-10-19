package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
public class QuestionnaireDTO {
    private String questionId;
    private String questName;
    private String projectName;
    private String questStatus;
    private String num;
    private Date createOn;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getQuestStatus() {
        return questStatus;
    }

    public void setQuestStatus(String questStatus) {
        this.questStatus = questStatus;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
