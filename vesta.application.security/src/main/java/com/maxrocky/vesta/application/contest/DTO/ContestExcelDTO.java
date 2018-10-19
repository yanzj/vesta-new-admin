package com.maxrocky.vesta.application.contest.DTO;

/**
 * 导出ExcelDTO
 * Created by yuanyn on 2017/7/14.
 */
public class ContestExcelDTO {
    private String projectName;//项目公司
    private String creatName;//创建人
    private String creatDate;//创建时间
    private byte[] image;//隐患图片
    private String describe;//隐患描述

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
