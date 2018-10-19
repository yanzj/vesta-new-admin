package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/15 9:57.
 * Describe:短信验证码实体类
 */
@Entity
@Table(name = "sms_auth")
public class SMSAuthEntity {

    /* 验证类型 */
    public final static String TYPE_LOGIN = "LOGIN";//登录验证
    public final static String TYPE_REGISTER = "REGISTER";//注册验证
    public final static String TYPE_PASSWORD_R = "PASSWORD_R";//忘记密码
    public final static String TYPE_APPEAL = "APPEAL";//申诉
    public final static String TYPE_REPAIR = "REPAIR";//报修
    public final static String TYPE_ACTIVITY = "ACTIVITY";//活动报名
    public final static String TYPE_ORDER = "ORDER";//商品订单
    private String id;//主键
    private String phone;//手机号
    private String code;//验证码
    private String authType;//类型
    private Date sendTime;//发送时间

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PHONE",nullable = false, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "CODE",nullable = false, length = 30)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "AUTH_TYPE",nullable = false, length = 10)
    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    @Basic
    @Column(name = "SEND_TIME",nullable = false)
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * Describe:创建基础数据
     * CreateBy:Tom
     * CreateOn:2016-01-15 11:28:12
     */
    @Transient
    public void create(){
        this.id = IdGen.getSMSAuthID();
        this.sendTime = DateUtils.getDate();
    }

    /**
     * Describe:根据类型生成短信内容
     * CreateBy:Tom
     * CreateOn:2016-02-19 01:50:26
     */
    @Transient
    public String getSMSContent(){
        StringBuffer smsContent = new StringBuffer();
//        smsContent.append("欢迎注册成为金茂会员：");
        smsContent.append("您的短信验证码是");
        smsContent.append(code);
        smsContent.append("。");
        smsContent.append("如非本人操作，请忽略此短信。本条短信免费。");
        return smsContent.toString();
    }

}
