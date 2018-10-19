package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

/**
 * 日常巡检统计
 * Created by jiazefeng on 2016/10/17.
 */
public class ProjectDailyPatrolCountExcelDTO {
    private int number;//序号
    private String projectName;//项目名称
//    private String tenderName;//标段名称
    private String buildingName;//楼栋名称
    private int total;//全部
    private int qualifiedToatl;//已完成
    private int unqualifiedToatl;//整改中
    private int closses;//非正常关闭

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQualifiedToatl() {
        return qualifiedToatl;
    }

    public void setQualifiedToatl(int qualifiedToatl) {
        this.qualifiedToatl = qualifiedToatl;
    }

    public int getUnqualifiedToatl() {
        return unqualifiedToatl;
    }

    public void setUnqualifiedToatl(int unqualifiedToatl) {
        this.unqualifiedToatl = unqualifiedToatl;
    }

    public int getClosses() {
        return closses;
    }

    public void setClosses(int closses) {
        this.closses = closses;
    }
}
