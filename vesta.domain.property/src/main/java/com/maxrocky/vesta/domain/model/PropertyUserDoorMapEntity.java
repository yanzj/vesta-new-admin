package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业用户门禁关系表
 * Created by WeiYangDong on 2016/11/10.
 */
@Entity
@Table(name = "property_user_door")
public class PropertyUserDoorMapEntity implements Serializable{

    private String id;      //主键Id
    private String userId;  //用户Id
    private String doorId;  //门禁Id

    private String createBy;    //创建人
    private Date createOn;      //创建时间

    @Id
    @Column(name = "id",nullable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id",nullable = true,length = 100)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "door_id",nullable = true,length = 100)
    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
