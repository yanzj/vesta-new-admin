package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

/**
 * Created by Talent on 2016/11/24.
 */
public class ProjectKeyProcessesCountExcelDTO {
    private int serialNumber;//序号
    private String projectName;//项目名称
    private String tenderName;//标段名称
    private String buildingName;//楼栋名称
    private int total;//全部
    private int qualifiedTotal;//合格
    private int unqualifiedTotal;//不合格
    private int abnormalShutdown;//非正常关闭
    private int onePassTotal;//一次通过

    public int getAbnormalShutdown() {
        return abnormalShutdown;
    }

    public void setAbnormalShutdown(int abnormalShutdown) {
        this.abnormalShutdown = abnormalShutdown;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
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

    public int getQualifiedTotal() {
        return qualifiedTotal;
    }

    public void setQualifiedTotal(int qualifiedTotal) {
        this.qualifiedTotal = qualifiedTotal;
    }

    public int getUnqualifiedTotal() {
        return unqualifiedTotal;
    }

    public void setUnqualifiedTotal(int unqualifiedTotal) {
        this.unqualifiedTotal = unqualifiedTotal;
    }

    public int getOnePassTotal() {
        return onePassTotal;
    }

    public void setOnePassTotal(int onePassTotal) {
        this.onePassTotal = onePassTotal;
    }
}
