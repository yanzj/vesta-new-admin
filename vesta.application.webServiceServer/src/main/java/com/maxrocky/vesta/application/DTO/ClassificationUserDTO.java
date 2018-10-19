package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/7/17.
 * 分类人员信息
 */
public class ClassificationUserDTO {
    List<ClassificationDTO> classificationList=new ArrayList<>();//分类信息
    List<ClassUserDTO> userList=new ArrayList<>();//人员信息

    public List<ClassificationDTO> getClassificationList() {
        return classificationList;
    }

    public void setClassificationList(List<ClassificationDTO> classificationList) {
        this.classificationList = classificationList;
    }

    public List<ClassUserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<ClassUserDTO> userList) {
        this.userList = userList;
    }
}
