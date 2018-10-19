package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/12/13.
 */
public class CategoryTableDTO {
    private String categoryOne;  //一级分类
    private String categoryTwo;  //二级分类
    private String categoryThree;//三级分类
    private String categoryFour; //四级分类
    private String targetName;   //指标名称
    private String targetDesc;   //指标详解
    private String havePhoto;    //是否拍照

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public String getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(String categoryThree) {
        this.categoryThree = categoryThree;
    }

    public String getCategoryFour() {
        return categoryFour;
    }

    public void setCategoryFour(String categoryFour) {
        this.categoryFour = categoryFour;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetDesc() {
        return targetDesc;
    }

    public void setTargetDesc(String targetDesc) {
        this.targetDesc = targetDesc;
    }

    public String getHavePhoto() {
        return havePhoto;
    }

    public void setHavePhoto(String havePhoto) {
        this.havePhoto = havePhoto;
    }
}
