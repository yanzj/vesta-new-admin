package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
@Entity
@Table(name = "userselect")
public class UserSelectEntity {

    @Id
    @Column(name = "userselectid", length = 32)
    private String userSelectId; //ID

    @Basic
    @Column(name = "questId",length = 250)
    private String questId; //问卷ID

    @Basic
    @Column(name = "questionTitleId",length = 250)
    private String questionTitleId; //题目ID

    @Basic
    @Column(name = "questionTitleSelectId",length = 250)
    private String questionTitleSelectId; //选项ID

    @Basic
    @Column(name = "userId",length = 250)
    private String userId; //用户ID

    @Basic
    @Column(name = "createOn",length = 250)
    private Date createOn;//创建日期

    public String getUserSelectId() {
        return userSelectId;
    }

    public void setUserSelectId(String userSelectId) {
        this.userSelectId = userSelectId;
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

    public String getQuestionTitleSelectId() {
        return questionTitleSelectId;
    }

    public void setQuestionTitleSelectId(String questionTitleSelectId) {
        this.questionTitleSelectId = questionTitleSelectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
