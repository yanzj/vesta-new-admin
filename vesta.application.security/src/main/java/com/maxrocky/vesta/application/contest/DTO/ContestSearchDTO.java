package com.maxrocky.vesta.application.contest.DTO;

/**
 * Created by yuanyn on 2017/9/4.
 */
public class ContestSearchDTO {

    private String projectName;//项目公司
    private String projectId; //项目公司Id
    private String startDate;//开始日期
    private String endDate;//结束时间
    private String createName;//创建人
    private String describe; //隐患描述

    public ContestSearchDTO(){
        this.projectName="";//项目公司
        this.projectId="";//项目公司Id
        this.startDate="";//开始日期
        this.endDate="";//结束时间
        this.createName="";//创建人
        this.describe="";//隐患描述
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
