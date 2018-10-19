package com.maxrocky.vesta.application.DTO.admin;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 * 问答题目
 */
public class SubjectDTO {
    private String subjectId; //ID

    private String questId; //问卷ID

    private String subjectName; //标题

    private List<UserSubjectDTO> userSubjectDTOS;

    public List<UserSubjectDTO> getUserSubjectDTOS() {
        return userSubjectDTOS;
    }

    public void setUserSubjectDTOS(List<UserSubjectDTO> userSubjectDTOS) {
        this.userSubjectDTOS = userSubjectDTOS;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
