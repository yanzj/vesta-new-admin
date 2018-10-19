package com.maxrocky.vesta.application.DTO.admin;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
public class QuestionTitleDTO {

    private String questionTitleId; //ID

    private String questId; //调查问卷ID

    private String questionTitleName; //标题

    private Integer questionOpt; // 1 单选 2多选

    private Date createOn;//创建日期

    private List<QuestionTitleSelectDTO> questionTitleSelect;

    public List<QuestionTitleSelectDTO> getQuestionTitleSelect() {
        return questionTitleSelect;
    }

    public void setQuestionTitleSelect(List<QuestionTitleSelectDTO> questionTitleSelect) {
        this.questionTitleSelect = questionTitleSelect;
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
