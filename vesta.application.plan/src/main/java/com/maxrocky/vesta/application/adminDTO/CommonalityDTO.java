package com.maxrocky.vesta.application.adminDTO;

import java.util.List;

/**
 * Created by langmafeng on 2016/5/10/ 16:47.
 */
public class CommonalityDTO {
    private String id;//主键
    private List projectList;//项目列表
    private String projectName;//项目名
    private String projectId;//项目ID
    private List buildingList;//楼栋列表
    private String buildingName;//楼栋名
    private String buildingId;//楼栋ID
    private List roomList;//房间列表
    private String roomName;//房间名
    private String roomId;//房间ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getProjectList() {
        return projectList;
    }

    public void setProjectList(List projectList) {
        this.projectList = projectList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List buildingList) {
        this.buildingList = buildingList;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public List getRoomList() {
        return roomList;
    }

    public void setRoomList(List roomList) {
        this.roomList = roomList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
