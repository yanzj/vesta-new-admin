package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SMSAuthEntity;

/**
 * Created by Tom on 2016/1/15 10:02.
 * Describe:手机短信验证Repository接口
 */
public interface SMSAuthRepository {

    /**
     * Describe:创建手机验证码
     * CreateBy:Tom
     * CreateOn:2016-01-15 11:33:32
     */
    void create(SMSAuthEntity smsAuthEntity);

    /**
     * Describe:根据手机号、验证码、类型判断验证码是否有效
     * CreateBy:Tom
     * CreateOn:2016-01-17 04:19:25
     */
    Boolean getSMSAuthIsValid(String phone, String authCode, String type);
}
