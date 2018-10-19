package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhanghj on 2016/1/15.
 */

@Entity
@Table(name ="message_token")
public class MessageTokenEntity {
    public static  class MOBILETYPE{
        public static final int Mobile_Type_ios = 1;
    }
    private String messageTokenId;

    private String messageTokenNum;//token码

    private int mobileType;//手机类型

    private Date tokenCreateTime;//创建时间

    private String userId;//用户Id


    @Id
    @Column(name="MESSAGE_TOKENID",nullable = false,insertable = true,updatable = false,length = 100)
    public String getMessageTokenId() {
        return messageTokenId;
    }

    public void setMessageTokenId(String messageTokenId) {
        this.messageTokenId = messageTokenId;
    }
    @Basic
    @Column(name="MESSAGE_TOKENNUM",length = 100)
    public String getMessageTokenNum() {
        return messageTokenNum;
    }

    public void setMessageTokenNum(String messageTokenNum) {
        this.messageTokenNum = messageTokenNum;
    }
    @Basic
    @Column(name="MESSAGE_MOBILETYPE",length = 10)
    public int getMobileType() {
        return mobileType;
    }

    public void setMobileType(int mobileType) {
        this.mobileType = mobileType;
    }
    @Basic
    @Column(name="MESSAGE_CREATETIME",length = 100)
    public Date getTokenCreateTime() {
        return tokenCreateTime;
    }

    public void setTokenCreateTime(Date tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }
    @Basic
    @Column(name="USER_ID",length = 100)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
