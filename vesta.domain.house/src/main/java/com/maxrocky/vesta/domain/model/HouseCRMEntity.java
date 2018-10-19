package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/13.
 */
@Entity
@Table(name = "house_crm")
public class HouseCRMEntity {
    private String houseId;
    private String id;//主键
    private String memberId;//会员编号
    private String userId;//用户id
    private String companyId;//公司Id
    private String companyName;//公司名称
    private String city;//城市
    private String area;//区域
    private String projectId;//项目Id
    private String projectName;//项目名称
    private String instalment;//项目分期:如：一期、二期
    private String buildingId;//楼Id(预售楼栋号)
    private String buildingName;//楼名称(备案楼栋号)
    private String unitId;//单元Id
    private String unitName;//单元名称
    private String floor;//楼层
    private String roomId;//房间Id
    private String roomName;//房间号
    private String roomNum;//房间编号
    private String street;//街道
    private String formatId;//业态Id
    private String formatName;//业态名称
    private String address;//地址
    private String houseType;//户型
    private BigDecimal buildingArea;//建筑面积
    private BigDecimal usableArea;//使用面积
    //private int viewAppHouseId;//物业房产Id
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String declareStandard;//装修标准
    private String state;//状态：0为默认房产;1为非默认
    private String stateCode;//数据删除标识:0可用，1 停用

    @Id
    @Column(name = "HOUSE_ID",nullable = false, length = 32)
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "ID",nullable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID",nullable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "AREA", length = 50)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "COMPANY_ID", length = 50)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "COMPANY_NAME", length = 50)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "PROJECT_ID", length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME", length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "BUILDING_ID", length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "BUILDING_NAME", length = 50)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "UNIT_ID", length = 50)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "UNIT_NAME", length = 50)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "ROOM_ID", length = 50)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "ROOM_NAME", length = 50)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name = "STREET", length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "FORMAT_ID", length = 50)
    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    @Basic
    @Column(name = "FORMAT_NAME", length = 50)
    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @Basic
    @Column(name = "ADDRESS", length = 300)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "HOUSE_TYPE", length = 50)
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name = "BUILDING_AREA", precision = 16, scale = 4)
    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    @Basic
    @Column(name = "USABLE_AREA", precision = 16, scale = 4)
    public BigDecimal getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(BigDecimal usableArea) {
        this.usableArea = usableArea;
    }

    @Basic
    @Column(name = "CREATE_BY",nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON",nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY",nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON",nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "STATE",nullable = true,length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "CITY", length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "INSTALMENT", length = 50)
    public String getInstalment() {
        return instalment;
    }

    public void setInstalment(String instalment) {
        this.instalment = instalment;
    }

    @Basic
    @Column(name = "FLOOR", length = 50)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "MEMBER_ID", length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "DECLARE_STANDARD", length = 100)
    public String getDeclareStandard() {
        return declareStandard;
    }

    public void setDeclareStandard(String declareStandard) {
        this.declareStandard = declareStandard;
    }

    @Basic
    @Column(name = "STATE_CODE", length = 10)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Basic
    @Column(name = "ROM_NUM", length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
