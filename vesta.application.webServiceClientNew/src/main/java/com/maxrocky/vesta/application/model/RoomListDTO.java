package com.maxrocky.vesta.application.model;

/**
 * Created by Magic on 2016/5/30.
 */
public class RoomListDTO {
    private String roomId;
    private String roomName;
    private String roomNum;
    private String projectNum;
    public RoomListDTO(String projectNum,String roomId,String roomName,String roomNum){
        this.roomId=roomId;
        this.roomName=roomName;
        this.roomNum=roomNum;
        this.projectNum=projectNum;
    }
    public RoomListDTO(){
        projectNum="";
        roomId="";
        roomName="";
        roomNum="";
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
