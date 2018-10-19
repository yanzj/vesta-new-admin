package com.maxrocky.vesta.application.DTO;

/**
 * @author WeiYangDong
 * @date 2018/3/20 14:31
 * @deprecated 房产数据导出DTO
 */
public class ExportExcelHouseDataDTO {

    private int num;//序号
    private String projectCode;//项目编码
    private String projectName;//项目
    private String areaName;//地块
    private String buildingName;//楼栋
    private String unitName;//单元
    private String floorName;//楼层
    private String roomName;//房间
    private String ownerName;//业主姓名
    private String handleMode;//办理方式
    private String handleProgress;//办理进度
    private String handleStatus;//状态
    private String isPushMessage;//是否发送短信
    private String phone;//联系方式
    private String messageContent;//推送短信内容

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
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

    public String getIsPushMessage() {
        return isPushMessage;
    }

    public void setIsPushMessage(String isPushMessage) {
        this.isPushMessage = isPushMessage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
