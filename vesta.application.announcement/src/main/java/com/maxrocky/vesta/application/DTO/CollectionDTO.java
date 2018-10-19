package com.maxrocky.vesta.application.DTO;

/**
 * Created by Admin on 2016/6/3.
 */
public class CollectionDTO {

    private String id;  //收藏Id
    private String messageId;// 收藏内容Id
    private String messageType;// 收藏类型  1--公告  2--活动
    private String userId; // 用户Id


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
