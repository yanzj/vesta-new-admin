package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.ClassificationUserDTO;

/**
 * Created by Magic on 2017/7/17.
 * Description:
 * webService:接收CRM传递的项目分类人员信息
 * ModifyBy:
 */
public interface BasicClassificationUserService {

    /**
     * CreateBy:Magic
     * Description:接收分类人员信息
     * ModifyBy:
     */
    String setClassUser(ClassificationUserDTO classificationUserDTO);
}
