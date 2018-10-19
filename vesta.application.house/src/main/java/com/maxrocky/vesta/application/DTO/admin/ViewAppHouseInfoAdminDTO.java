package com.maxrocky.vesta.application.DTO.admin;

import com.maxrocky.vesta.utility.StringUtil;

/**
 * Created by Tom on 2016/1/22 14:57.
 * Describe:物业房产业主数据实体类
 */
public class ViewAppHouseInfoAdminDTO {

    private int viewAppHouseId;//物业房产Id
    private int viewAppOwnerId;//业主Id
    private String viewAppOwnerMobile;//业主手机
    private String viewAppOwnerCardType;//证件类型
    private String viewAppOwnerName;//业主名称
    private String viewAppIdCard;//证件号码
    private String checkIdCard;//验证证件号码
    private String projectId;//项目id
    private String projectName;//项目名称
    private String buildingName;//楼名称
    private String unitName;//单元名称
    private String roomName;//房间号
    private String formatName;//业态

    public ViewAppHouseInfoAdminDTO(){
        viewAppOwnerId = 0;
        viewAppIdCard = "";
        checkIdCard = "";
        viewAppHouseId = 0;
        projectId = "";
        projectName = "";
        viewAppOwnerMobile = "";
        viewAppOwnerCardType = "";
        buildingName = "";
        unitName = "";
        roomName = "";
        viewAppOwnerName = "";
        formatName = "";
    }

    public int getViewAppOwnerId() {
        return viewAppOwnerId;
    }

    public void setViewAppOwnerId(int viewAppOwnerId) {
        this.viewAppOwnerId = viewAppOwnerId;
    }

    public String getViewAppIdCard() {
        return viewAppIdCard;
    }

    public void setViewAppIdCard(String viewAppIdCard) {
        this.viewAppIdCard = viewAppIdCard;
    }

    public String getCheckIdCard() {
        return checkIdCard;
    }

    public void setCheckIdCard(String checkIdCard) {
        this.checkIdCard = checkIdCard;
    }

    public int getViewAppHouseId() {
        return viewAppHouseId;
    }

    public void setViewAppHouseId(int viewAppHouseId) {
        this.viewAppHouseId = viewAppHouseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getViewAppOwnerMobile() {
        return viewAppOwnerMobile;
    }

    public void setViewAppOwnerMobile(String viewAppOwnerMobile) {
        this.viewAppOwnerMobile = viewAppOwnerMobile;
    }

    public String getViewAppOwnerCardType() {
        return viewAppOwnerCardType;
    }

    public void setViewAppOwnerCardType(String viewAppOwnerCardType) {
        this.viewAppOwnerCardType = viewAppOwnerCardType;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getViewAppOwnerName() {
        return viewAppOwnerName;
    }

    public void setViewAppOwnerName(String viewAppOwnerName) {
        this.viewAppOwnerName = viewAppOwnerName;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    /**
     * 验证号码是否匹配
     */
    public Boolean checkOwnerName(String ownerName){
        String[] nameSplit = viewAppOwnerName.split("[ /-]");
        for (String item : nameSplit){
            if(StringUtil.isEqual(item, ownerName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 验证号码是否匹配
     */
    public Boolean checkIDCard(String idCard){

        String[] cardSplit = null;
        if(viewAppOwnerCardType.equals("5444") || viewAppOwnerCardType.equals("5369")){
            cardSplit = viewAppIdCard.split("[ /]");
        }else {
            cardSplit = viewAppIdCard.split("[ /-]");
        }

        for (String item : cardSplit){
            if(StringUtil.isEqual(item, idCard)){
                return true;
            }
        }
        return false;
    }

}
