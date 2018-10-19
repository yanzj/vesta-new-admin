package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * 极光推送 阶段标题和内容
 * Created by Magic on 2017/7/13.
 */
@Entity
@Table(name = "message_push_targe")
public class MessagePushTargeEntity {
    private String id;//id
    private String messageType;//消息模块消息类型  1.交付app    2.工程APP   3.安全APP
    private String type;// 1.1报修单员工  1.2报修单经理  1.3.投诉单处理人
    private String target;//内容
    private String title;//标题
    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="MESSAGE_TYPE",length = 10)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    @Basic
    @Column(name="TYPE",length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name="TARGET",length = 1000)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    @Basic
    @Column(name="TITLE",length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
