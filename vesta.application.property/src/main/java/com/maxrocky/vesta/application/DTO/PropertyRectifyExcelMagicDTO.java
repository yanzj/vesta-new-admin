package com.maxrocky.vesta.application.DTO;

/**
 * Created by maxrocky on 2017/5/8.
 */
public class PropertyRectifyExcelMagicDTO {
    private int serialNumber;            //序号
    private String projectName;//项目名称
    private String roomAddress;//房间位置
    private String userName;//客户姓名
    private String state;//办理状态
    private String count;//问题统计
    private String userTime;//签字时间

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
