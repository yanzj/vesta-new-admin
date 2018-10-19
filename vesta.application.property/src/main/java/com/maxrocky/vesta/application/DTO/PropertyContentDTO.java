package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/1/23.
 * 报修详情：任务内容
 */
public class PropertyContentDTO {
    private String taskContent;//任务内容

    public PropertyContentDTO() {
        this.taskContent = "";
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}