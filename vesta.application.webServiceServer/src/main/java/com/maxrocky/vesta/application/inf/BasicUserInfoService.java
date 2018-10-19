package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.UserInfoDTO;

/**
 * Created by liudongxin on 2016/4/10.
 * Description:
 * webService:接收金茂项目CRM传递的业主信息
 * ModifyBy:
 */

public interface BasicUserInfoService {

    /**
     * CreateBy:liudongxin
     * Description:接收用户信息
     * param userInfo：用户信息参数
     * ModifyBy:
     */
    String userInfo(UserInfoDTO userInfo);
}
