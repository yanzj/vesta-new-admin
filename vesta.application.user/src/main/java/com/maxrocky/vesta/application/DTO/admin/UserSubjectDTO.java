package com.maxrocky.vesta.application.DTO.admin;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 * 用户问答结果
 */
public class UserSubjectDTO {

    private String userSubjectId; //ID

    private String questId; //问卷ID

    private String subjectId; //问答ID

    private String userId; //用户ID

    private Date createOn;//创建日期

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
