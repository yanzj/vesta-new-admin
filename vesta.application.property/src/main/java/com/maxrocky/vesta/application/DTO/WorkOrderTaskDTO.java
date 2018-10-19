package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/1/26.
 * 员工端：工单进展(任务名称、任务日期、任务时间)
 */
public class WorkOrderTaskDTO {
    //private String taskName;//任务名称
    private String taskDateOne;//任务日期
    private String taskTimeOne;//任务时间
    private String taskDateTwo;//任务日期
    private String taskTimeTwo;//任务时间
    private String taskDateThree;//任务日期
    private String taskTimeThree;//任务时间
    private String taskDateFour;//任务日期
    private String taskTimeFour;//任务时间
    private String taskDateFive;//任务日期
    private String taskTimeFive;//任务时间

    public WorkOrderTaskDTO() {
        this.taskDateOne = "";
        this.taskTimeOne = "";
        this.taskDateTwo = "";
        this.taskTimeTwo = "";
        this.taskDateThree = "";
        this.taskTimeThree = "";
        this.taskDateFour = "";
        this.taskTimeFour = "";
        this.taskDateFive = "";
        this.taskTimeFive = "";
    }

    public String getTaskDateOne() {
        return taskDateOne;
    }

    public void setTaskDateOne(String taskDateOne) {
        this.taskDateOne = taskDateOne;
    }

    public String getTaskTimeOne() {
        return taskTimeOne;
    }

    public void setTaskTimeOne(String taskTimeOne) {
        this.taskTimeOne = taskTimeOne;
    }

    public String getTaskDateTwo() {
        return taskDateTwo;
    }

    public void setTaskDateTwo(String taskDateTwo) {
        this.taskDateTwo = taskDateTwo;
    }

    public String getTaskTimeTwo() {
        return taskTimeTwo;
    }

    public void setTaskTimeTwo(String taskTimeTwo) {
        this.taskTimeTwo = taskTimeTwo;
    }

    public String getTaskDateThree() {
        return taskDateThree;
    }

    public void setTaskDateThree(String taskDateThree) {
        this.taskDateThree = taskDateThree;
    }

    public String getTaskTimeThree() {
        return taskTimeThree;
    }

    public void setTaskTimeThree(String taskTimeThree) {
        this.taskTimeThree = taskTimeThree;
    }

    public String getTaskDateFour() {
        return taskDateFour;
    }

    public void setTaskDateFour(String taskDateFour) {
        this.taskDateFour = taskDateFour;
    }

    public String getTaskTimeFour() {
        return taskTimeFour;
    }

    public void setTaskTimeFour(String taskTimeFour) {
        this.taskTimeFour = taskTimeFour;
    }

    public String getTaskDateFive() {
        return taskDateFive;
    }

    public void setTaskDateFive(String taskDateFive) {
        this.taskDateFive = taskDateFive;
    }

    public String getTaskTimeFive() {
        return taskTimeFive;
    }

    public void setTaskTimeFive(String taskTimeFive) {
        this.taskTimeFive = taskTimeFive;
    }
}
