package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mql on 2016/6/4.
 */
@Entity
@Table(name="house_room_type")
public class HouseRoomTypeEntity {
    private long id;
    private String roomId;
    private String roomNum;
    private long houseType;//户型ID
    private Date modifyTime;//修改时间

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",length = 50,nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name="ROOM_ID",length = 50)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name="ROOM_NUM",length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @Basic
    @Column(name="HOUSE_TYPE",length = 50)
    public long getHouseType() {
        return houseType;
    }

    public void setHouseType(long houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name="MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
