package com.maxrocky.vesta.domain.dailyPatrolInspection.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程抄送人
 * Created by Magic on 2016/10/17.
 */
@Entity
@Table(name = "project_copy")
public class ProjectCopyEntity {
    private String id;//
    private String sender;//发送人
    private String senderName;//发送人姓名
    private String business;//业务ID
    private String damain;//所属模块
    private Date createOn;//创建时间
    @Id
    @Column(name = "ID", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "SENDER", length = 32, nullable = true, insertable = true, updatable = true)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    @Basic
    @Column(name = "BUSINESS", length = 32, nullable = true, insertable = true, updatable = true)
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
    @Basic
    @Column(name = "DAMAIN", length = 32, nullable = true, insertable = true, updatable = true)
    public String getDamain() {
        return damain;
    }

    public void setDamain(String damain) {
        this.damain = damain;
    }
    @Basic
    @Column(name = "CREATE_ON", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "SENDER_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
