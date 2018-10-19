package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by sunmei on 2016/2/18.
 */
public class LoginLogSearchDTO {
    private String propertyProject;//项目id
    private String propertyArea;//区域id
    private String startDate;//发布开始时间
    private String endDate;//发布结束时间
    private String userName;//用户名
    private String queryScope;

    public String getPropertyProject() {
        return propertyProject;
    }

    public void setPropertyProject(String propertyProject) {
        this.propertyProject = propertyProject;
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }
}
