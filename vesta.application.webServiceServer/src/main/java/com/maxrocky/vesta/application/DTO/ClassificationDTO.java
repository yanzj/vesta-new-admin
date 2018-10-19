package com.maxrocky.vesta.application.DTO;

/**
 * Created by Magic on 2017/7/17.
 * 分类信息
 */
public class ClassificationDTO {

    private String classificationId;    //分类id
    private String classificationNum;   //分类id
    private String classificationName;  //描述


    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationNum() {
        return classificationNum;
    }

    public void setClassificationNum(String classificationNum) {
        this.classificationNum = classificationNum;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

}
