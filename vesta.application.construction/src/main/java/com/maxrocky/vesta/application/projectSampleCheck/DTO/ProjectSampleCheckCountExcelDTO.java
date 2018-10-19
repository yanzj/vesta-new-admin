package com.maxrocky.vesta.application.projectSampleCheck.DTO;

/**
 * Created by Magic on 2017/2/8.
 */
public class ProjectSampleCheckCountExcelDTO {
    private int serialNumber;//序号
    private String projectName;//项目名称
    private String classifyThree;//三级分类
    private int qualified;//合格数
    private int unqualified;//不合格数

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

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public int getQualified() {
        return qualified;
    }

    public void setQualified(int qualified) {
        this.qualified = qualified;
    }

    public int getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(int unqualified) {
        this.unqualified = unqualified;
    }
}
