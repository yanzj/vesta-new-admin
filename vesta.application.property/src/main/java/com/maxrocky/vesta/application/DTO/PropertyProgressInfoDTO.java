package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 业主端:物业报修详情界面：报修进展封装
 */
public class PropertyProgressInfoDTO {
    private String status;//状态
    private String taskStatus;//任务状态
    private String imageUrl;//任务人头像
    private String src;//任务人头像
    private String userId;//维修人员id
    private List<PropertyProgressDTO> progressList;

    public PropertyProgressInfoDTO() {
        this.status="";
        this.taskStatus = "";
        this.imageUrl="";
        this.src="";
        this.userId="";
        this.progressList = new ArrayList<PropertyProgressDTO>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<PropertyProgressDTO> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<PropertyProgressDTO> progressList) {
        this.progressList = progressList;
    }
}