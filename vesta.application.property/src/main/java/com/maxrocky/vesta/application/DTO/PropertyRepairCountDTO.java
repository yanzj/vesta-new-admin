package com.maxrocky.vesta.application.DTO;

/**
 * Created by chen on 2016/10/13.
 * 问题清单数据统计封装类
 */
public class PropertyRepairCountDTO {
    private String projectCode;          //项目编码
    private String buildingName;         //楼栋位置名称  totalAll 总计
    private String buildingCode;         //楼栋编码
    private Integer acceptedNum;         //待确认数
    private Integer processingNum;       //整改中数
    private Integer completedNum;        //已完成数
    private Integer allNum;              //全部数


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getAcceptedNum() {
        return acceptedNum;
    }

    public void setAcceptedNum(Integer acceptedNum) {
        this.acceptedNum = acceptedNum;
    }

    public Integer getProcessingNum() {
        return processingNum;
    }

    public void setProcessingNum(Integer processingNum) {
        this.processingNum = processingNum;
    }

    public Integer getCompletedNum() {
        return completedNum;
    }

    public void setCompletedNum(Integer completedNum) {
        this.completedNum = completedNum;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }
}
