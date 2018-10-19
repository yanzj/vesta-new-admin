package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.UserInfoDTO;
import com.maxrocky.vesta.application.inf.BasicUserInfoService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * Created by liudongxin on 2016/4/11.
 */
@WebService(name="userInfoName",serviceName = "userInfoServiceName")
public class TestUserInfoService {
    @Autowired
    private BasicUserInfoService basicUserInfoService;

    @WebMethod
    public String userInfo(@WebParam(name="userInfo") UserInfoDTO userInfo){
       return  basicUserInfoService.userInfo(userInfo);
       /*System.out.println("service success!");
       return "service  success!";*/
    }

    /*public static void main(String[]args){
        TestUserInfoService service=new TestUserInfoService();
        List<UserDTO> userList=new ArrayList<>();
        UserInfoDTO userInfo=new UserInfoDTO();
        userInfo.setMemberId("20160415001");
        UserDTO user=new UserDTO();
        user.setId("201604150101");
        user.setUserName("liudongxin");
        user.setRealName("刘东鑫");
        user.setEnglishName("liudongxin");
        userList.add(user);
        userInfo.setUserList(userList);
        service.userInfo(userInfo);
    }*/
}
