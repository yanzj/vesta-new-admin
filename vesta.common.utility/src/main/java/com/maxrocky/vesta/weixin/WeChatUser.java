package com.maxrocky.vesta.weixin;

/**
 * Created by JillChen on 2015/9/20.
 */
public class WeChatUser {

    private String user_openid;
    private String user_nickname;
    private String user_sex;
    private String user_province;
    private String user_city;
    private String user_country;
    private String user_headimgurl;
    private String user_unionid;

    public String getUser_unionid() {
        return user_unionid;
    }

    public void setUser_unionid(String user_unionid) {
        this.user_unionid = user_unionid;
    }

    public String getUser_openid() {
        return user_openid;
    }

    public void setUser_openid(String user_openid) {
        this.user_openid = user_openid;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_province() {
        return user_province;
    }

    public void setUser_province(String user_province) {
        this.user_province = user_province;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_headimgurl() {
        return user_headimgurl;
    }

    public void setUser_headimgurl(String user_headimgurl) {
        this.user_headimgurl = user_headimgurl;
    }
}
