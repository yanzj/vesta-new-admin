package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/4/22.
 * 房间位置名称实体
 */

@Entity
@Table(name = "room_location")
public class RoomLocationEntity {
    private String id;
    private String name;
    private Date createOn;
    private Date modifyDate;//修改时间
    private String state;//状态：0为创建;1为修改

    @Id
    @Column(name = "ID",length = 50,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "STATE", nullable = true,length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
