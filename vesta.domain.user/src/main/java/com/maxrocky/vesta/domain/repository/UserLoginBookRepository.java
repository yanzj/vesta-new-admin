package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserLoginBookEntity;

/**
 * Created by Tom on 2016/1/13 19:02.
 * Describe:登录信息Repository接口
 */
public interface UserLoginBookRepository {

    /**
     * Describe:根据登录Id获取登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:05:51
     */
    UserLoginBookEntity get(String loginId);

    /**
     * Describe:根据用户Id获取有效登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:16:16
     */
    UserLoginBookEntity getLoginByUserId(String userId);

    /**
     * Describe:修改登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:36:49
     */
    void update(UserLoginBookEntity userLoginBookEntity);

    /**
     * Describe:创建登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:46:12
     */
    void create(UserLoginBookEntity userLoginBookEntity);

    /**
     * Describe:根据用户ID和openId获取登录信息
     * CreateBy:chen
     * CreateOn:2016-04-06 15:06:32
     * */
    UserLoginBookEntity getLoginBookByuniodidAndUserid(String openId, String userId);

    void updateOpenIdInLoginBook(String userId, String user_unionid, String user_openid);
}
