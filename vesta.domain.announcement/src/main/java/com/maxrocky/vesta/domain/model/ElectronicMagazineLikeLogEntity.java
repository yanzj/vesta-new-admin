package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 电子杂志点赞日志表
 * Created by WeiYangDong on 2017/10/9.
 */
@Entity
@Table(name = "electronic_magazine_like_log")
public class ElectronicMagazineLikeLogEntity {

    private String id;//主键ID
    private String resourceId;//资源ID(电子杂志ID)
    private String userId;//用户ID
    private Date createOn;//创建时间

    @Id
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "resource_id",length = 32)
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "user_id",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
