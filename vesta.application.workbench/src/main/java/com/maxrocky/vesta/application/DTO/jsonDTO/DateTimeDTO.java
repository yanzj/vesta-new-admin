package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by Magic on 2016/6/13.
 */
public class DateTimeDTO {
    private String repairTime;      //时间戳  保修单
    private String roomTime;        //时间戳  房间  基础数据楼栋更
    private String planTime;        //时间戳  活动

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(String roomTime) {
        this.roomTime = roomTime;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}
