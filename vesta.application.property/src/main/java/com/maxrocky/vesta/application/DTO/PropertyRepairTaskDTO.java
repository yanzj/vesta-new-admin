package com.maxrocky.vesta.application.DTO;

/**
 * Created by lpc on 2016/6/4.
 */
public class PropertyRepairTaskDTO {

    private String taskId; // 任务单ID
    private String taskDate; // 创建时间
    private String taskContent; // 内容
    private String readStatus; // 已读状态   1-已读  0-未读
    private String taskName; // 报修名称
    private String repairId; // 报修单ID
    private String repairContent;//报修内容

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }
}
