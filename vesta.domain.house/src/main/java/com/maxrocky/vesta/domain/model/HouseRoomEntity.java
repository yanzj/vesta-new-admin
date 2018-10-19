package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tom on 2016/1/14 20:09.
 * Describe:房屋实体类
 */
@Entity
@Table(name = "house_room")
public class HouseRoomEntity {
    private String id;//主键
    private String floorId;//预售楼层id
    private String floorNum;//预售楼层编号
    private String roomNum;//预售房间编码
    private String name;// 预售房间号
    private Date createOn;//创建时间
    private Date modifyOn;//修改时间
    private String decorationStandard;//装修标准
    /* 以下为新修改 增加备案信息时候所增加字段  */
    private Double useArea;//使用面积（平方米）
    private String businessType;//业态类型
    private Date actualCheckinDate;//实际入住日期
    private String propertyNature;//产权性质
    private String useNature;//出租/（自住/用）
    private String propertyType;//房产类型
    private String houseStatus;//房屋状态
    private String orientation;//朝向
    private String bedroom;//户型-室
    private String parlour;//户型-厅
    private String toilet;//户型-卫
    private String kitchen;//户型-厨
    private String balcony;//户型-阳台
    private Double structureArea;//建筑面积
    private Double insideSpace;//套内面积

    private String recordHouseCode;//备案房间编码
    private String recordFloorCode;//关联备案楼层编码
    private String recordHouseNum;//备案房间号
    private String houseName;//address 预售房间名称
    private String recordHouseName;//address 备案房间名称
    private String stateCode;//状态 0:有效 1:失效



    private String floor;//预售楼层
    private String memberId;//会员编号
    private String unitId;//单元Id
    private String createBy;//创建人
    private String modifyBy;//修改人
    private String buildingNum;//楼号编码
    private String buildingId;//楼Id
    private BigDecimal usableArea;//使用面积
    private String projectNum;//项目编号
    private String projectId;//项目Id
    private String formatId;//业态Id
    private BigDecimal buildingArea;//建筑面积
    private String unit;//单元
    private String unitNum;//单元编码
    private String houseType;//户型

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEMBER_ID",nullable = true,length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "NAME",nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "UNIT_ID",nullable = true, length = 50)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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
    @Column(name = "BUILDING_NUM", length = 50)
    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    @Basic
    @Column(name = "DECORATION_STANDARD", length = 50)
    public String getDecorationStandard() {
        return decorationStandard;
    }

    public void setDecorationStandard(String decorationStandard) {
        this.decorationStandard = decorationStandard;
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
    @Column(name = "FLOOR_NUM", length = 50)
    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
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
    @Column(name = "USABLE_AREA", precision = 16, scale = 4)
    public BigDecimal getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(BigDecimal usableArea) {
        this.usableArea = usableArea;
    }

    @Basic
    @Column(name = "PROJECT_NUM", length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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
    @Column(name = "FORMAT_ID", length = 50)
    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
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
    @Column(name = "UNIT", length = 50)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Basic
    @Column(name = "UNIT_NUM", length = 50)
    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    @Basic
    @Column(name = "ROOM_NUM", length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
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
    @Column(name = "FLOOR_ID", length = 50)
    public String getFloorId() {
        return floorId;
    }
    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "USEAREA", length = 50)
    public Double getUseArea() {
        return useArea;
    }

    public void setUseArea(Double useArea) {
        this.useArea = useArea;
    }
    @Basic
    @Column(name = "BUSINESS_TYPE", length = 50)
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    @Basic
    @Column(name = "ACTUALCHECKINDATE", length = 50)
    public Date getActualCheckinDate() {
        return actualCheckinDate;
    }

    public void setActualCheckinDate(Date actualCheckinDate) {
        this.actualCheckinDate = actualCheckinDate;
    }
    @Basic
    @Column(name = "PROPERTYNATURE", length = 50)
    public String getPropertyNature() {
        return propertyNature;
    }

    public void setPropertyNature(String propertyNature) {
        this.propertyNature = propertyNature;
    }
    @Basic
    @Column(name = "USENATURE", length = 50)
    public String getUseNature() {
        return useNature;
    }

    public void setUseNature(String useNature) {
        this.useNature = useNature;
    }
    @Basic
    @Column(name = "PROPERTY_TYPE", length = 50)
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    @Basic
    @Column(name = "HOUSE_STATUS", length = 50)
    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }
    @Basic
    @Column(name = "ORIENTATION", length = 50)
    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    @Basic
    @Column(name = "BEDROOM", length = 50)
    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }
    @Basic
    @Column(name = "PARLOUR", length = 50)
    public String getParlour() {
        return parlour;
    }

    public void setParlour(String parlour) {
        this.parlour = parlour;
    }
    @Basic
    @Column(name = "TOILET", length = 50)
    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }
    @Basic
    @Column(name = "KITCHEN", length = 50)
    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }
    @Basic
    @Column(name = "BALCONY", length = 50)
    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }
    @Basic
    @Column(name = "STRUCTUREAREA", length = 50)
    public Double getStructureArea() {
        return structureArea;
    }

    public void setStructureArea(Double structureArea) {
        this.structureArea = structureArea;
    }
    @Basic
    @Column(name = "INSIDESPACE", length = 50)
    public Double getInsideSpace() {
        return insideSpace;
    }

    public void setInsideSpace(Double insideSpace) {
        this.insideSpace = insideSpace;
    }
    @Basic
    @Column(name = "RECORD_HOUSECODE", length = 100)
    public String getRecordHouseCode() {
        return recordHouseCode;
    }

    public void setRecordHouseCode(String recordHouseCode) {
        this.recordHouseCode = recordHouseCode;
    }
    @Basic
    @Column(name = "RECORD_FLOORCODE", length = 100)
    public String getRecordFloorCode() {
        return recordFloorCode;
    }

    public void setRecordFloorCode(String recordFloorCode) {
        this.recordFloorCode = recordFloorCode;
    }
    @Basic
    @Column(name = "RECORD_HOUSENUM", length = 50)
    public String getRecordHouseNum() {
        return recordHouseNum;
    }

    public void setRecordHouseNum(String recordHouseNum) {
        this.recordHouseNum = recordHouseNum;
    }
    @Basic
    @Column(name = "HOUSENAME", length = 200)
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    @Basic
    @Column(name = "RECORD_HOUSENAME", length = 200)
    public String getRecordHouseName() {
        return recordHouseName;
    }

    public void setRecordHouseName(String recordHouseName) {
        this.recordHouseName = recordHouseName;
    }
    @Basic
    @Column(name = "STATECODE", length = 50)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
