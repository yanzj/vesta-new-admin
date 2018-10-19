package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/5/3.
 * 房间预约计划
 */
public class HousePlanDTO {
    private String roomId;//房间id
    private String planId;//计划id
    private String roomNum;//房间编号

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
