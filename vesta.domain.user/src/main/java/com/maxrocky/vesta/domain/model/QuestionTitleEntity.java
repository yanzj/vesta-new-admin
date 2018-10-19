package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
@Entity
@Table(name = "questiontitle")
public class QuestionTitleEntity {
    @Id
    @Column(name = "questiontitleid", length = 32)
    private String questionTitleId; //ID

    @Basic
    @Column(name = "questId",length = 250)
    private String questId; //调查问卷ID

    @Basic
    @Column(name = "qyestionTitleName",length = 250)
    private String questionTitleName; //标题

    @Basic
    @Column(name = "qyestionOpt",length = 250)
    private Integer questionOpt; // 1 单选 2多选

    @Basic
    @Column(name = "createon",length = 250)
    private Date createOn;//创建日期

    @Basic
    @Column(name = "countNum",length = 250)
    private Integer countNum; //

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getQuestionTitleId() {
        return questionTitleId;
    }

    public void setQuestionTitleId(String questionTitleId) {
        this.questionTitleId = questionTitleId;
    }

    public String getQuestionTitleName() {
        return questionTitleName;
    }

    public void setQuestionTitleName(String questionTitleName) {
        this.questionTitleName = questionTitleName;
    }

    public Integer getQuestionOpt() {
        return questionOpt;
    }

    public void setQuestionOpt(Integer questionOpt) {
        this.questionOpt = questionOpt;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
