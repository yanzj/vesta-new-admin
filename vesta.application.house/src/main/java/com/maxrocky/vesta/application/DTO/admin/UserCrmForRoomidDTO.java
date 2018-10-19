package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Magic on 2016/8/8.
 */
/**
 * 为打印功能查询出业主信息（根据roomid）
 * */
public class UserCrmForRoomidDTO {
    private String userName;
    private String userMobile;
    private String building;
    private String room;
    private String rommName;
    public UserCrmForRoomidDTO(){
        this.userMobile="";
        this.userName="";
        this.building="";
        this.room="";
        this.rommName="";
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRommName() {
        return rommName;
    }

    public void setRommName(String rommName) {
        this.rommName = rommName;
    }
}
