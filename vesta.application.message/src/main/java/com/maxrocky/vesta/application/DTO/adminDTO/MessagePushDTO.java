package com.maxrocky.vesta.application.DTO.adminDTO;

/**
 * Created by zhanghj on 2016/1/17.
 */
public class MessagePushDTO {

    String mPushID;

    String mPushTitle;//推送消息标题

    String mPushContent;//推送消息内容

    String mPushSound;//推送消息声音

    String mPushUrl;//消息推送跳转页面

    String mUserType;//消息接收人类型，1-业主，2-员工

    String mType;//消息类型

    String IosPushP12Path;//ios证书路径

    String IosPushP12Password;//ios证书密码

    String IosPushP12Type;//ios推送环境

    String apiKey;//安卓推送码

    String secretKey;//安卓推送私钥

    String pushToken;//推送设备码

    String pushupid;

    public String getPushupid() {
        return pushupid;
    }

    public void setPushupid(String pushupid) {
        pushupid = pushupid;
    }
//
//    int IosCount;
//    public int getIosCount() {
//        return IosCount;
//    }
//
//    public void setIosCount(int IosCount) {
//        IosCount = IosCount;
//    }
    public String getIosPushP12Path() {
        return IosPushP12Path;
    }

    public void setIosPushP12Path(String iosPushP12Path) {
        IosPushP12Path = iosPushP12Path;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getIosPushP12Type() {
        return IosPushP12Type;
    }

    public void setIosPushP12Type(String iosPushP12Type) {
        IosPushP12Type = iosPushP12Type;
    }

    public String getIosPushP12Password() {
        return IosPushP12Password;
    }

    public void setIosPushP12Password(String iosPushP12Password) {
        IosPushP12Password = iosPushP12Password;
    }

    public String getmPushID() {
        return mPushID;
    }

    public void setmPushID(String mPushID) {
        this.mPushID = mPushID;
    }

    public String getmPushTitle() {
        return mPushTitle;
    }

    public void setmPushTitle(String mPushTitle) {
        this.mPushTitle = mPushTitle;
    }

    public String getmPushContent() {
        return mPushContent;
    }

    public void setmPushContent(String mPushContent) {
        this.mPushContent = mPushContent;
    }

    public String getmPushSound() {
        return mPushSound;
    }

    public void setmPushSound(String mPushSound) {
        this.mPushSound = mPushSound;
    }

    public String getmPushUrl() {
        return mPushUrl;
    }

    public void setmPushUrl(String mPushUrl) {
        this.mPushUrl = mPushUrl;
    }

    public String getmUserType() {
        return mUserType;
    }

    public void setmUserType(String mUserType) {
        this.mUserType = mUserType;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
