package com.maxrocky.vesta.application.admin.dto;

import java.util.Date;

/**
 * 线下活动DTO
 * Created by WeiYangDong on 2017/8/21.
 */
public class OfflineActivityDTO {

    private String menuId;//菜单ID

    private String activityId;//活动主键ID
    private String activityTitle;//活动标题
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String activityPlace;//活动地点
    private Integer partakeNumber;//参与人数
    private Date activityStartTime;//活动开始时间
    private String activityStartTimeStr;//活动开始时间
    private Date activityEndTime;//活动结束时间
    private String activityEndTimeStr;//活动结束时间
    private Integer activityState;//活动状态

    private Integer isLuckDraw;//是否抽奖(0,否;1,是)
    private Integer onePrizeNumber;//一等奖人数
    private Integer twoPrizeNumber;//二等奖人数
    private Integer threePrizeNumber;//三等奖人数

    private Integer signNumber;//签到人数

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String createOnStr;//创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    private String signId;//主键ID
    private String ownerName;//业主姓名
    private String mobile;//联系电话
    private String address;//房产地址
    private String prizeInfo;//中奖信息

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public Integer getIsLuckDraw() {
        return isLuckDraw;
    }

    public void setIsLuckDraw(Integer isLuckDraw) {
        this.isLuckDraw = isLuckDraw;
    }

    public Integer getOnePrizeNumber() {
        return onePrizeNumber;
    }

    public void setOnePrizeNumber(Integer onePrizeNumber) {
        this.onePrizeNumber = onePrizeNumber;
    }

    public Integer getTwoPrizeNumber() {
        return twoPrizeNumber;
    }

    public void setTwoPrizeNumber(Integer twoPrizeNumber) {
        this.twoPrizeNumber = twoPrizeNumber;
    }

    public Integer getThreePrizeNumber() {
        return threePrizeNumber;
    }

    public void setThreePrizeNumber(Integer threePrizeNumber) {
        this.threePrizeNumber = threePrizeNumber;
    }

    public Integer getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(Integer signNumber) {
        this.signNumber = signNumber;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getActivityStartTimeStr() {
        return activityStartTimeStr;
    }

    public void setActivityStartTimeStr(String activityStartTimeStr) {
        this.activityStartTimeStr = activityStartTimeStr;
    }

    public String getActivityEndTimeStr() {
        return activityEndTimeStr;
    }

    public void setActivityEndTimeStr(String activityEndTimeStr) {
        this.activityEndTimeStr = activityEndTimeStr;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrizeInfo() {
        return prizeInfo;
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = prizeInfo;
    }

    public String getCreateOnStr() {
        return createOnStr;
    }

    public void setCreateOnStr(String createOnStr) {
        this.createOnStr = createOnStr;
    }

    public Integer getPartakeNumber() {
        return partakeNumber;
    }

    public void setPartakeNumber(Integer partakeNumber) {
        this.partakeNumber = partakeNumber;
    }
}
