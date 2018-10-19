package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 * 用户问答结果
 */
@Entity
@Table(name = "usersubject")
public class UserSubjectEntity {

    @Id
    @Column(name = "usersubjectid", length = 32)
    private String userSubjectId; //ID

    @Basic
    @Column(name = "questId",length = 250)
    private String questId; //问卷ID

    @Basic
    @Column(name = "subjectId",length = 250)
    private String subjectId; //问答ID

    @Basic
    @Column(name = "userId",length = 250)
    private String userId; //用户ID

    @Basic
    @Column(name = "createOn",length = 250)
    private Date createOn;//创建日期

    @Basic
    @Column(name = "message",length = 250)
    private String message; //内容

    public String getUserSubjectId() {
        return userSubjectId;
    }

    public void setUserSubjectId(String userSubjectId) {
        this.userSubjectId = userSubjectId;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
