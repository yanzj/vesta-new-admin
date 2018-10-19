package com.maxrocky.vesta.application.DTO.admin;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
public class QuestionTitleSelectDTO {

    private String questionTitleSelectId; //ID


    private String questionTitleId; //题目ID


    private String selectName; //选项名称


    private String pictureUrl; //图片地址


    private Integer number; //选项数量


    private Date createOn; //创建日期

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getQuestionTitleSelectId() {
        return questionTitleSelectId;
    }

    public void setQuestionTitleSelectId(String questionTitleSelectId) {
        this.questionTitleSelectId = questionTitleSelectId;
    }

    public String getQuestionTitleId() {
        return questionTitleId;
    }

    public void setQuestionTitleId(String questionTitleId) {
        this.questionTitleId = questionTitleId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
