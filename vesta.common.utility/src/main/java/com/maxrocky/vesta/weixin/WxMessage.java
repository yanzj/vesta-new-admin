package com.maxrocky.vesta.weixin;

/**
 * Created by zhanghj on 2016/4/18.
 * Modify by WeiYangDong on 2017/12/11.
 */
public class WxMessage {

    private String appID;//微信AppId

    private String appSecret;//微信AppSecret

    public static final class TemplateCode{
        public static final String USER_IDEN="1";       //用户认证模板
    }

    private String openId;      //目标人openId

    private String template_code;//模板Code

    private String template_id; //模板Id

    private String turnUrl;//消息跳转网页

    private String first;       //首部消息

    private String remark;      //底部备注
    /**
     * 以下是不同模板可能不同的参数
     * 如果段落换行要加\n
     */
    private String name;        //姓名

    private String tel;     //手机

    private String address;     //地址
    /**
     * 物业报修模板消息参数
     */
    private String keyword1;//报修位置
    private String keyword2;//报修主题
    private String keyword3;//维保人员
    private String keyword4;//完成日期

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTurnUrl() {
        return turnUrl;
    }

    public void setTurnUrl(String turnUrl) {
        this.turnUrl = turnUrl;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
