package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by sunmei on 2016/2/18.
 */
public class OperationLogDTO {
    private String name;//姓名
    private String userName;//用户名
    private String time;
    private String content;//内容
    private String projectId;//项目

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
