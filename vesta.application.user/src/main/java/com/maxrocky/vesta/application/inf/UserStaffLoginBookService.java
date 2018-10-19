package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.model.UserStaffLoginBookEntity;
import com.maxrocky.vesta.exception.GeneralException;

/**
 * Created by Tom on 2016/1/22 12:04.
 * Describe:员工登录信息Service接口
 */
public interface UserStaffLoginBookService {

    /**
     * Code:For UI
     * Type:UI Method
     * Describe:专为UI调试登录接口。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:10:51
     */
    UserStaffLoginBookEntity loginForStaffUI(String staffId) throws GeneralException;

    /**
     * Code:LG0003
     * Type:UI Method
     * Describe:员工App登陆
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:15:35
     */
    ApiResult loginForStaff(String username, String password, String ip) throws GeneralException;

    /**
     * Important Information:此接口已经作废
     * 请使用 ApiResult getStaffInfoByVestaToken(String vestaToken)
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取员工登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:30:50
     */
    UserPropertyStaffEntity getStaffInfoByToken(String token);

    /**
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取员工登录信息。
     * CreateBy:Tom
     * CreateOn:2016-03-01 11:16:54
     */
    ApiResult getStaffInfoByVestaToken(String vestaToken);

    /**
     * Code:LO0002
     * Type:UI Method
     * Describe:员工登出
     * CreateBy:Tom
     * CreateOn:2016-02-20 05:14:40
     */
    ApiResult exit(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 交付app登录
     *
     * @param username
     * @param password
     * @param ipAddress
     * @return
     */
    ApiResult loginForStaffToRepairApp(String username, String password, String ipAddress);

    /**
     * 工程APP 登录
     *
     * @param username
     * @param password
     * @param ipAddress
     * @return
     */
    ApiResult loginForStaffToEngineeringApp(String username, String password, String ipAddress);
}
