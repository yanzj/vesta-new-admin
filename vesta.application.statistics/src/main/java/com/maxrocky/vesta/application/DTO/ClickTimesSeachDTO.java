package com.maxrocky.vesta.application.DTO;

/**
 * Created by sunmei on 2016/2/15.
 */
public class ClickTimesSeachDTO {
    private String companyName;      // 公司名称
    private String propertyProject;  // 项目
    private String propertyArea;     // 区域
    private String queryScope;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

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

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }
}
