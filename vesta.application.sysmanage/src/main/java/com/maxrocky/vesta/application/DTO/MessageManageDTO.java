package com.maxrocky.vesta.application.DTO;

import java.util.List;

/**
 * Created by sunmei on 2016/3/3.
 */
public class MessageManageDTO {
    private String projectId;
    private String typeId;
    private List<String> department;
    private String messageManageId;//消息表id
    private String content;
    private String title;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMessageManageId() {
        return messageManageId;
    }

    public void setMessageManageId(String messageManageId) {
        this.messageManageId = messageManageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(List<String> department) {
        this.department = department;
    }

}
