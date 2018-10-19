package com.maxrocky.vesta.application.DTO;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liudongxin on 2016/1/26.
 * 员工端：工单进展(任务日期、任务内容)/管理端：反馈
 */
public class WorkOrderContentDTO {
    private String id;//报修id
    private String taskDate;//任务日期
    private String taskContent;//任务内容

    public WorkOrderContentDTO() {
        this.id="";
        this.taskDate = "";
        this.taskContent = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}
