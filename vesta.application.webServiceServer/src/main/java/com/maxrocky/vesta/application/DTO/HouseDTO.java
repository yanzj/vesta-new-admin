package com.maxrocky.vesta.application.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/10.
 * 会员房产信息
 */
public class HouseDTO {

    private String id;//房屋id
    private String memberId;//会员编号
    private String userId;//用户id
    private String houseNum;//房间编码
    private String city;//城市
    private String formatName;//业态名称
    private String area;//区域
    private String community;//小区;项目
    private String building;//备号楼号
    private String build;//预付楼号
    private String unit;//单元
    private String room;//房间号
    private BigDecimal buildingArea;//建筑面积
    private BigDecimal carpetArea;//套内面积
    private String houseType;//户型
    private Date modifyDate;//最后修改时间
    private Date buyDate;//购置日期
    private String declareStadanard;//装修标准
    private String stateCode;//数据删除标识:0可用，1 停用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public BigDecimal getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(BigDecimal carpetArea) {
        this.carpetArea = carpetArea;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getDeclareStadanard() {
        return declareStadanard;
    }

    public void setDeclareStadanard(String declareStadanard) {
        this.declareStadanard = declareStadanard;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }
}
