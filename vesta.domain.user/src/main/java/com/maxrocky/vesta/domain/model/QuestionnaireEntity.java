package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 * 调查问卷
 */
@Entity
@Table(name = "questionnaire")
public class QuestionnaireEntity {
    @Id
    @Column(name = "questid", length = 32)
    private String questId; //ID

    @Basic
    @Column(name = "cityId",length = 250)
    private String cityId; //区域

    @Basic
    @Column(name = "projectName",length = 250)
    private String projectName; //项目

    @Basic
    @Column(name = "questName",length = 250)
    private String questName; //标题

    @Basic
    @Column(name = "ruleNum",length = 250)
    private String ruleNum; //积分

    @Basic
    @Column(name = "beginDate",length = 250)
    private Date beginDate; //开始日期

    @Basic
    @Column(name = "endDate",length = 250)
    private Date endDate; //结束日期

    @Basic
    @Column(name = "questStatus",length = 250)
    private String questStatus; //状态 1 未开始 2开始3 结束

    @Basic
    @Column(name = "createon",length = 250)
    private Date createOn; //创建日期

    @Basic
    @Column(name = "num",length = 250)
    private String num; //人数

    @Basic
    @Column(name = "deleteStatus",length = 2)
    private Integer deleteStatus; //状态 0,未删除;1,已删除

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

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
