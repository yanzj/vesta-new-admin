package com.maxrocky.vesta.application.DTO;

/**
 * 产权管理数据导出Excel_DTO
 * Created by WeiYangDong on 2017/6/22.
 */
public class ExportExcelPropertyManageDTO {

    private int num;            //序号
    private String projectCode; //项目编码
    private String projectName; //项目名称
    private String area;        //地块
    private String building;    //楼号
    private String unit;        //单元
    private String floor;       //楼层
    private String roomName;    //房屋号
    private String ownerName;   //业主姓名
    private String handleMode;      //办理方式
    private String handleProgress;  //办理进度
    private String handleStatus;    //办理状态(0,正常状态;1,待提交资料)

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getHandleMode() {
        return handleMode;
    }

    public void setHandleMode(String handleMode) {
        this.handleMode = handleMode;
    }

    public String getHandleProgress() {
        return handleProgress;
    }

    public void setHandleProgress(String handleProgress) {
        this.handleProgress = handleProgress;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }
}
