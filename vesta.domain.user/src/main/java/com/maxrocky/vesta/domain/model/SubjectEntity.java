package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 * 问答题目
 */
@Entity
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @Column(name = "subjectid", length = 32)
    private String subjectId; //ID

    @Basic
    @Column(name = "questId",length = 250)
    private String questId; //问卷ID

    @Basic
    @Column(name = "subjectName",length = 250)
    private String subjectName; //标题

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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
