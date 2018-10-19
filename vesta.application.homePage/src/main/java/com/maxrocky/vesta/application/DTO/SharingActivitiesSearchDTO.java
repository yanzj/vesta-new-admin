package com.maxrocky.vesta.application.DTO;

import java.util.Map;

/**
 * Created by sunmei on 2016/2/19.
 */
public class SharingActivitiesSearchDTO {
    private String title;//标题
    private String propertyProject;//项目
    private String publishStartDate;//发布开始时间
    private String publishEndDate;//发布结束时间
    private String queryScope;//查询负责范围(模块条件)

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPropertyProject() {
        return propertyProject;
    }

    public void setPropertyProject(String propertyProject) {
        this.propertyProject = propertyProject;
    }

    public String getPublishStartDate() {
        return publishStartDate;
    }

    public void setPublishStartDate(String publishStartDate) {
        this.publishStartDate = publishStartDate;
    }

    public String getPublishEndDate() {
        return publishEndDate;
    }

    public void setPublishEndDate(String publishEndDate) {
        this.publishEndDate = publishEndDate;
    }

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }
}
