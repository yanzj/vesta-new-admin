package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;

/**
 * Created by Tom on 2016/1/15 10:18.
 * Describe:手机短信验证Service接口
 */
public interface SMSAuthService {

    /**
     * Code:AC0001
     * Type:UI Method
     * Describe:发送手机验证码
     * CreateBy:Tom
     * CreateOn:2016-01-15 11:02:06
     */
    ApiResult sendSMSAuthCode(String phone, String type);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据手机号、类型获取验证码
     * CreateBy:Tom
     * CreateOn:2016-01-17 04:12:13
     */
    Boolean getSMSAuthByPhoneAndType(String phone, String authCode, String type);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 发送短信
    */
    ApiResult sendSMSAlerts(SMSPeopleAlertsEntity smsPeopleAlertsEntity);

    /**
     * 短信消息提醒 Wyd_2016-12-07
     * @param phone 手机号
     * @param content  内容
     */
    void sendSms(String phone,String content);

}
