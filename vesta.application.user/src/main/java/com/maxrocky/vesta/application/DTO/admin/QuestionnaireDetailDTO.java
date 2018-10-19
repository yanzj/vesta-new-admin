package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public class QuestionnaireDetailDTO {
    private String questId; //ID

    private String cityId; //区域

    private String projectName; //项目

    private String questName; //标题

    private String ruleNum; //积分

    private Date beginDate; //开始日期

    private Date endDate; //结束日期

    private String questStatus; //状态

    private Date createOn; //创建日期

    private String num; //人数

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getRuleNum() {
        return ruleNum;
    }

    public void setRuleNum(String ruleNum) {
        this.ruleNum = ruleNum;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getQuestStatus() {
        return questStatus;
    }

    public void setQuestStatus(String questStatus) {
        this.questStatus = questStatus;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
