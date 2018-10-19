package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.Date;

/**
 * 检查验收统计
 * Created by jiazefeng on 2016/10/17.
 */
public class InspectAcceptanceCountExcelDTO {
    private int number;//序号
    private String projectName;//项目名称
    private String tenderName;//标段名称
    private String buildingName;//楼栋名称
    private int total;//全部
    private int qualifiedToatl;//合格
    private int unqualifiedToatl;//不合格
    private int abnormalShutdown;//非正常关闭
    private int onePassToatl;//一次通过

    public int getAbnormalShutdown() {
        return abnormalShutdown;
    }

    public void setAbnormalShutdown(int abnormalShutdown) {
        this.abnormalShutdown = abnormalShutdown;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

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

    public int getOnePassToatl() {
        return onePassToatl;
    }

    public void setOnePassToatl(int onePassToatl) {
        this.onePassToatl = onePassToatl;
    }
}
