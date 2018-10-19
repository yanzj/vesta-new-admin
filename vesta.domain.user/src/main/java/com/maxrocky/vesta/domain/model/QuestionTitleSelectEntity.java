package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
@Entity
@Table(name = "questiontitleselect")
public class QuestionTitleSelectEntity {
    @Id
    @Column(name = "questiontitleselectid", length = 32)
    private String questionTitleSelectId; //ID

    @Basic
    @Column(name = "questionTitleId",length = 250)
    private String questionTitleId; //题目ID

    @Basic
    @Column(name = "selectName",length = 250)
    private String selectName; //选项名称

    @Basic
    @Column(name = "pictureUrl",length = 250)
    private String pictureUrl; //图片地址

    @Basic
    @Column(name = "number",length = 250)
    private Integer number; //选项数量

    @Basic
    @Column(name = "createon",length = 250)
    private Date createOn; //创建日期

    @Basic
    @Column(name = "countNum",length = 250)
    private Integer countNum; //

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

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
