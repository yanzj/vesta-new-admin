package com.maxrocky.vesta.application.DTO.appDTO;

import java.util.Date;

/**
 * Created by zhanghj on 2016/3/3.
 */
public class MessageTokenDTO {


    private String messageTokenId;

    private String messageTokenNum;//token码

    private int mobileType;//手机类型

    private Date tokenCreateTime;//创建时间

    private String userId;//用户Id
    private Date lastTime;//上一波推送时间

    public String getMessageTokenId() {
        return messageTokenId;
    }

    public void setMessageTokenId(String messageTokenId) {
        this.messageTokenId = messageTokenId;
    }

    public String getMessageTokenNum() {
        return messageTokenNum;
    }

    public void setMessageTokenNum(String messageTokenNum) {
        this.messageTokenNum = messageTokenNum;
    }

    public int getMobileType() {
        return mobileType;
    }

    public void setMobileType(int mobileType) {
        this.mobileType = mobileType;
    }

    public Date getTokenCreateTime() {
        return tokenCreateTime;
    }

    public void setTokenCreateTime(Date tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
