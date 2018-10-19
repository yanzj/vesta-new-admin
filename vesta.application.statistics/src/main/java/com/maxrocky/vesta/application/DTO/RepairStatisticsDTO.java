package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 报修统计 DTO
 */
public class RepairStatisticsDTO {
    private String projectName;//项目小区
    private Integer repairNumber = 0;//报修数量
    private Integer noSolvedNumber = 0;//未解决数量
    private Integer yesSolvedNumber = 0;//解决数量

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getRepairNumber() {
        return repairNumber;
    }

    public void setRepairNumber(Integer repairNumber) {
        this.repairNumber = repairNumber;
    }

    public Integer getNoSolvedNumber() {
        return noSolvedNumber;
    }

    public void setNoSolvedNumber(Integer noSolvedNumber) {
        this.noSolvedNumber = noSolvedNumber;
    }

    public Integer getYesSolvedNumber() {
        return yesSolvedNumber;
    }

    public void setYesSolvedNumber(Integer yesSolvedNumber) {
        this.yesSolvedNumber = yesSolvedNumber;
    }
}
