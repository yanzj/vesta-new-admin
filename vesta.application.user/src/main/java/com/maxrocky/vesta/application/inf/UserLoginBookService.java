package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.exception.GeneralException;

/**
 * Created by Tom on 2016/1/13 19:08.
 * Describe:登录信息Service接口
 */
public interface UserLoginBookService {

    /**
     * Important Information:此接口已经作废
     *                       请使用 ApiResult getUserInfoByVestaToken(String vestaToken)
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取用户登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:30:50
     */
    UserInfoEntity getUserInfoByToken(String token);

    /**
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取用户登录信息。
     * CreateBy:Tom
     * CreateOn:2016-03-01 10:28:47
     */
    ApiResult getUserInfoByVestaToken(String vestaToken);

    /**
     * Code:For UI
     * Type:UI Method
     * Describe:专为UI调试登录接口。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:10:51
     */
    UserInfoEntity loginForUI(String userId) throws GeneralException;

    /**
     * Code:LG0001
     * Type:UI Method
     * Describe:业主、家属、租户App登录
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:15:35
     */
    ApiResult login(String username, String password, String form, String phoneUUID) throws GeneralException;

    /**
     * Code:LG0002
     * Type:UI Method
     * Describe:游客登录
     * CreateBy:Tom
     * CreateOn:2016-01-17 03:58:32
     */
    ApiResult loginForVisitors(String phone, String authCode, String form, String phoneUUID) throws GeneralException;

    /**
     * Code:LO0001
     * Type:UI Method
     * Describe:登出
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:49:51
     */
    ApiResult exit(UserInfoEntity userInfoEntity);

    /**
     * 用户名密码登陆
     * @param username
     * @param password
     * @return
     * @throws GeneralException
     */
    ApiResult loginForUP(String username, String password, UserInfoEntity user, String vestaToken);

    /**
     * 微信上用户名密码登陆
     * @param username
     * @param password
     * @return
     * @throws GeneralException
     */
    ApiResult weChatloginForUP(String username, String password, UserInfoEntity user, String vestaToken);

    /**
     * 用户名密码登陆
     * @param moblie
     * @param phoneCode
     * @return
     * @throws GeneralException
     */
    ApiResult loginForMoblie(String moblie, String phoneCode);
}
