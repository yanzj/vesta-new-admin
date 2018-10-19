package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修详情界面：报修进展
 */
public class PropertyProgressDTO {
    private String taskName;//任务名称
    private String taskDate;//任务日期
    private String taskTime;//任务时间
    private String imageUrl;//用户头像
    private String src;//用户头像
    private String taskContent;//任务内容
    private List<PropertyContentDTO> taskContentList;//任务内容

    public PropertyProgressDTO() {
        this.taskName = "";
        this.taskDate = "";
        this.taskTime = "";
        this.imageUrl="";
        this.src="";
        this.taskContentList=new ArrayList<PropertyContentDTO>();
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<PropertyContentDTO> getTaskContentList() {
        return taskContentList;
    }

    public void setTaskContentList(List<PropertyContentDTO> taskContentList) {
        this.taskContentList = taskContentList;
    }
}