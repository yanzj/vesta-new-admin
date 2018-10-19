package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sunmei on 2016/2/24.
 */
public class UserFeedbackDTO {
    private String id;
    private String name;//姓名
    private String userName;//用户名
    private String type;//用户类型
    private String content;//反馈内容
    private String time;//反馈时间
    private Map<String, String> showUrl;//图片

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Map<String, String> getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(Map<String, String> showUrl) {
        this.showUrl = showUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
