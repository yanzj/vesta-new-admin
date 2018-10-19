package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tom on 2016/1/14 20:09.
 * Describe:业主房屋实体类
 */
@Entity
@Table(name = "house_houseInfo")
public class HouseInfoEntity {
    private String id;//主键
    private String memberId;//会员编号
    private String projectId;//项目Id
    private String projectName;//项目名称
    private String projectNum;//项目编号
    private String projectCompany;//项目公司
    private String createBy;//创建人
    private Date createOn;//创建时间(项目开工时间)
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间(项目修改时间)
    private Date completeOn;//竣工时间
    private String companyId;//公司Id
    private String companyName;//公司名称
    private String city;//城市
    private String instalment;//项目分期:如：一期、二期
    private String area;//地块
    private String buildingId;//楼Id
    private String buildingNum;//楼号编码
    private String recordbuildingNum;//备案楼号编码
    private String buildingSale;//预售楼号
    private String buildingRecord;//备案楼号
    private String floor;//楼层
    private String floorNum;//楼层编号
    private String recordFloorNum;//备案楼层编号
    private String unitId;//单元ID
    private String unit;//单元
    private String unitNum;//单元编码
    private String roomId;//房间Id
    private String roomNum;//预售房间编码
    private String recordRoomNum;//备案房间编码
    private String roomName;//预售房间号
    private String recordRoomName;//备案房间号
    private String houseType;//户型
    private BigDecimal buildingArea;//建筑面积
    private BigDecimal usableArea;//使用面积
    private String decorationStandard;//装修标准
    private String formatName;//业态名称
    private String address;//预售地址
    private String recordAddress;//备案地址
    private String state;//状态：0为默认房产;1为非默认
    private int viewAppHouseId=0;//物业房产Id
    private String street;//街道
    private String formatId;//业态Id

    private String cityId;//城市Id;
    private String cityNum;//城市编码
    private String floorId;//楼层主键
    private String areaId;//地块id
    private String areaNum;//地块编码

    /*
    * 追加管家ID
    * Wyd_20170509
    */
    private String butlerId;//管家ID

    public HouseInfoEntity(){}

    public HouseInfoEntity(ViewAppHouseInfoEntity viewAppHouseInfoEntity){
        this.id = IdGen.uuid();
        this.companyId = viewAppHouseInfoEntity.getCompanyId() + "";
        this.companyName = viewAppHouseInfoEntity.getCompanyName();
        this.projectId = viewAppHouseInfoEntity.getProjectId() + "";
        this.projectName = viewAppHouseInfoEntity.getProjectName();
        this.roomName = viewAppHouseInfoEntity.getHouseNo();
        this.formatId = viewAppHouseInfoEntity.getFormatId() + "";
        this.formatName = viewAppHouseInfoEntity.getFormatName();
        this.address = viewAppHouseInfoEntity.getAddress();
        this.houseType = viewAppHouseInfoEntity.getHouseType();
        this.buildingArea = viewAppHouseInfoEntity.getBillingArea();
        this.viewAppHouseId = 0;
        this.createOn = DateUtils.getDate();
        this.modifyOn = DateUtils.getDate();
    }

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "CREATE_ON",nullable = true)
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
    @Column(name = "CITY_ID", length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "CITY_NUM", length = 50)
    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
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
    @Column(name = "PROJECT_NUM", length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "PROJECT_COMPANY", length = 50)
    public String getProjectCompany() {
        return projectCompany;
    }

    public void setProjectCompany(String projectCompany) {
        this.projectCompany = projectCompany;
    }

    @Basic
    @Column(name = "COMPLETE_ON", length = 50)
    public Date getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(Date completeOn) {
        this.completeOn = completeOn;
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
    @Column(name = "BUILDING_NUM", length = 50)
    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    @Basic
    @Column(name = "BUILDING_SALE", length = 50)
    public String getBuildingSale() {
        return buildingSale;
    }

    public void setBuildingSale(String buildingSale) {
        this.buildingSale = buildingSale;
    }

    @Basic
    @Column(name = "BUILDING_RECORD", length = 50)
    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
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
    @Column(name = "UNIT_ID", length = 50)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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
    @Column(name = "DECORATION_STANDARD", length = 50)
    public String getDecorationStandard() {
        return decorationStandard;
    }

    public void setDecorationStandard(String decorationStandard) {
        this.decorationStandard = decorationStandard;
    }

    @Basic
    @Column(name = "VIEW_APP_HOUSE_ID",length = 50)
    public int getViewAppHouseId() {
        return viewAppHouseId;
    }

    public void setViewAppHouseId(int viewAppHouseId) {
        this.viewAppHouseId = viewAppHouseId;
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
    @Column(name = "AREA_ID", length = 50)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "AREA_NUM", length = 50)
    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }

    @Basic
    @Column(name = "butler_id", length = 32)
    public String getButlerId() {
        return butlerId;
    }

    public void setButlerId(String butlerId) {
        this.butlerId = butlerId;
    }
    @Basic
    @Column(name = "RECORD_BUILDING_NUM", length = 50)
    public String getRecordbuildingNum() {
        return recordbuildingNum;
    }

    public void setRecordbuildingNum(String recordbuildingNum) {
        this.recordbuildingNum = recordbuildingNum;
    }
    @Basic
    @Column(name = "RECORD_FLOOR_NUM", length = 50)
    public String getRecordFloorNum() {
        return recordFloorNum;
    }

    public void setRecordFloorNum(String recordFloorNum) {
        this.recordFloorNum = recordFloorNum;
    }
    @Basic
    @Column(name = "RECORD_ROOM_NAME", length = 50)
    public String getRecordRoomName() {
        return recordRoomName;
    }

    public void setRecordRoomName(String recordRoomName) {
        this.recordRoomName = recordRoomName;
    }
    @Basic
    @Column(name = "RECORD_ROOM_NUM", length = 50)
    public String getRecordRoomNum() {
        return recordRoomNum;
    }

    public void setRecordRoomNum(String recordRoomNum) {
        this.recordRoomNum = recordRoomNum;
    }
    @Basic
    @Column(name = "RECORD_ADDRESS", length = 300)
    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }
}
