package com.maxrocky.vesta.application.model;

import java.util.List;

/**
 * Created by Magic on 2016/5/30.
 */
public class FloorListDTO {
    private String floorId;
    private String floorName;
    private String floorNum;
    private List roomList;
    private String projectNum;
    public FloorListDTO(String projectNum,String floorId,String floorName,String floorNum,List roomList){
        this.floorId=floorId;
        this.floorName=floorName;
        this.floorNum=floorNum;
        this.roomList=roomList;
        this.projectNum=projectNum;
    }
    public FloorListDTO(){
        floorId="";
        floorName="";
        floorNum="";
        projectNum="";
        roomList=null;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public List getRoomList() {
        return roomList;
    }

    public void setRoomList(List roomList) {
        this.roomList = roomList;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
