package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by sunmei on 2016/2/18.
 */
public class OperationLogSearchDTO {
    private String userName;//用户名
    private String content;//内容
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String queryScope;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }
}
