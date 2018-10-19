package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.SMSAuthService;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;
import com.maxrocky.vesta.sms.SDKHttpClient;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.SMSAuthEntity;
import com.maxrocky.vesta.domain.config.SMSConfig;
import com.maxrocky.vesta.domain.repository.SMSAuthRepository;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.maxrocky.vesta.utility.VerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 * Created by Tom on 2016/1/15 10:18.
 * Describe:手机短信验证Service接口实现类
 */
@Service
public class SMSAuthServiceImpl implements SMSAuthService {

    /* 手机短信验证 */
    @Autowired
    SMSAuthRepository smsAuthRepository;

    /**
     * Code:UI0001
     * Type:UI Method
     * Describe:发送手机验证码
     * CreateBy:Tom
     * CreateOn:2016-01-15 11:02:41
     */
    @Override
    public ApiResult sendSMSAuthCode(String phone, String type) {

        if(StringUtil.isEmpty(phone)){
            return new ErrorApiResult("tip_00000303");
        }
        if(!VerifyUtils.isMobile(phone)){
            return new ErrorApiResult("tip_00000283");
        }
        if(StringUtil.isEmpty(type)){
            return new ErrorApiResult("tip_00000056");
        }

        SMSAuthEntity smsAuthEntity = new SMSAuthEntity();
        smsAuthEntity.create();
        smsAuthEntity.setPhone(phone);
        smsAuthEntity.setAuthType(type);
        smsAuthEntity.setCode("123456");
        String param = "";
        if(StringUtil.isEqual(AppConfig.SMS_CHECK_FLAG, "Y")){
            String random = IdGen.getSixRandom() + "";
            smsAuthEntity.setCode(random);
            if(StringUtil.isEqual(AppConfig.SMS_CHECK_DOMAIN, "PRODUCE")){
                String message=(new StringBuilder("【金茂】")).append(smsAuthEntity.getSMSContent()).toString();
                try {
                    message = URLEncoder.encode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String code = "888";//验证码
                long seqId = System.currentTimeMillis();
                param = (new StringBuilder("cdkey=")).append(AppConfig.sn).append("&password=").append(AppConfig.password).append("&phone=").append(phone).append("&message=").append(message).append("&addserial=").append(code).append("&seqid=").append(seqId).toString();
                String url = (new StringBuilder(String.valueOf(AppConfig.baseUrl))).append("sendsms.action").toString();
                String retString = SDKHttpClient.sendSMS(url,param);
                System.out.println(retString);
            }else{
                String message=(new StringBuilder("【金茂荟】")).append(smsAuthEntity.getSMSContent()).toString();
                try {
                    message = URLEncoder.encode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String code = "888";//验证码
                long seqId = System.currentTimeMillis();
                param = (new StringBuilder("cdkey=")).append(AppConfig.sn).append("&password=").append(AppConfig.password).append("&phone=").append(phone).append("&message=").append(message).append("&addserial=").append(code).append("&seqid=").append(seqId).toString();
                String url = (new StringBuilder(String.valueOf(AppConfig.baseUrl))).append("sendsms.action").toString();
                String retString = SDKHttpClient.sendSMS(url, param);
                System.out.println(retString);
            }

        }
        smsAuthRepository.create(smsAuthEntity);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("deadTime", SMSConfig.DEAD_TIME);

        return new SuccessApiResult(modelMap);
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据手机号、类型获取验证码
     * CreateBy:Tom
     * CreateOn:2016-01-17 04:12:13
     */
    @Override
    public Boolean getSMSAuthByPhoneAndType(String phone,String authCode, String type) {
        return smsAuthRepository.getSMSAuthIsValid(phone, authCode, type);
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 发送短信
    */
    @Override
    public ApiResult sendSMSAlerts(SMSPeopleAlertsEntity smsPeopleAlertsEntity) {
        String phone = smsPeopleAlertsEntity.getPhone();
        if(StringUtil.isEmpty(phone)){
            return new ErrorApiResult("tip_00000303");
        }
        if(!VerifyUtils.isMobile(phone)){
            return new ErrorApiResult("tip_00000283");
        }

        SMSAuthEntity smsAuthEntity = new SMSAuthEntity();
        smsAuthEntity.create();
        smsAuthEntity.setPhone(phone);
        //smsAuthEntity.setAuthType(type);
        if ("身份申诉管理".equals(smsPeopleAlertsEntity.getModel())) {
            smsAuthEntity.setAuthType(SMSAuthEntity.TYPE_APPEAL);//
        }else if ("房屋报修管理".equals(smsPeopleAlertsEntity.getModel())) {
            smsAuthEntity.setAuthType(SMSAuthEntity.TYPE_REPAIR);
        }else if ("活动报名管理".equals(smsPeopleAlertsEntity.getModel())) {
            smsAuthEntity.setAuthType(SMSAuthEntity.TYPE_ACTIVITY);
        }else if ("商品订单管理".equals(smsPeopleAlertsEntity.getModel())) {
            smsAuthEntity.setAuthType(SMSAuthEntity.TYPE_ORDER);
        }
        smsAuthEntity.setCode("123456");
        String param = "";
        if(StringUtil.isEqual(AppConfig.SMS_CHECK_FLAG, "Y")){
            //String random = IdGen.getSixRandom() + "";
            //smsAuthEntity.setCode(random);
            if(StringUtil.isEqual(AppConfig.SMS_CHECK_DOMAIN, "PRODUCE")){
                String message=(new StringBuilder("【金茂】")).append(smsPeopleAlertsEntity.getContent()).toString();
                try {
                    message = URLEncoder.encode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String code = "888";//验证码
                long seqId = System.currentTimeMillis();
                param = (new StringBuilder("cdkey=")).append(AppConfig.sn).append("&password=").append(AppConfig.password).append("&phone=").append(phone).append("&message=").append(message).append("&addserial=").append(code).append("&seqid=").append(seqId).toString();
                String url = (new StringBuilder(String.valueOf(AppConfig.baseUrl))).append("sendsms.action").toString();
                String retString = SDKHttpClient.sendSMS(url,param);
                System.out.println(retString);
            }else{
                String message=(new StringBuilder("【中国金茂】")).append(smsPeopleAlertsEntity.getContent()).toString();
                try {
                    message = URLEncoder.encode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String code = "888";//验证码
                long seqId = System.currentTimeMillis();
                param = (new StringBuilder("cdkey=")).append(AppConfig.sn).append("&password=").append(AppConfig.password).append("&phone=").append(phone).append("&message=").append(message).append("&addserial=").append(code).append("&seqid=").append(seqId).toString();
                String url = (new StringBuilder(String.valueOf(AppConfig.baseUrl))).append("sendsms.action").toString();
                String retString = SDKHttpClient.sendSMS(url, param);
                System.out.println(retString);
            }

        }
        smsAuthRepository.create(smsAuthEntity);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("deadTime", SMSConfig.DEAD_TIME);

        return new SuccessApiResult(modelMap);
    }

    /**
     * 短信消息提醒 Wyd_2016-12-07
     * @param phone 手机号
     * @param content  内容
     */
    public void sendSms(String phone,String content){
        String message=(new StringBuilder("【金茂】")).append(content).toString();
        try {
            message = URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String code = "888";    //验证码
        long seqId = System.currentTimeMillis();
        String param = (new StringBuilder("cdkey=")).append(AppConfig.sn).append("&password=").append(AppConfig.password).append("&phone=").append(phone).append("&message=").append(message).append("&addserial=").append(code).append("&seqid=").append(seqId).toString();
        String url = (new StringBuilder(String.valueOf(AppConfig.baseUrl))).append("sendsms.action").toString();
        String retString = SDKHttpClient.sendSMS(url,param);
        System.out.println(retString);
    }
}
