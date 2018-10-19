package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.ClassificationUserDTO;
import com.maxrocky.vesta.application.inf.BasicClassificationUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 同步项目分类人员信息
 * Created by Magic on 2017/7/17.
 */
@WebService(name="ClassificationUserName",serviceName = "ClassificationUserServiceName")
public class TestClassificationUserService {
    @Autowired
    BasicClassificationUserService classificationUserService;

    @WebMethod
    public String setClassUser(@WebParam(name="setClassUser") ClassificationUserDTO classificationUserDTO){
        return classificationUserService.setClassUser(classificationUserDTO);
    }
}
