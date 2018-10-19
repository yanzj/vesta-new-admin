package com.maxrocky.vesta.application.DTO;

import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2016/10/28.
 */
public class PropertyRectifyExcelListDTO {
    private String rectifyId;//整改单id
    private Date limitTime;  //整改时限
    private String problemDescription;//内容
    private String address;//地址
    private String firstTypeName; // 一级分类
    private String secondTyoeName; // 二级分类
    private String thirdTypeName; // 三级分类
    private String supplierName;//供应商
    private String dealPeopleName;//接单人
    private Date createDate;//创建时间
    private String rectifyState;//状态
    private String createBy;//创建人
    private String city;        //城市
    private String cityNum;     //城市Num
    private String area;        //地块
    private String areaNum;     //地块Num
    private String project;     //项目
    private String projectNum;  //项目Num
    private String build;       //楼栋
    private String buildNum;    //楼栋Num
    private String unit;        //单元
    private String unitNum;     //单元Num
    private String floor;       //楼层
    private String floorNum;    //楼层Num
    private String room;        //房间
    private String roomNum;     //房间Num
    private String localtion;//部位
    private Date realityDate;//整改完成期限

    private String dealResult;              //整改信息
    private String repairManager;           //整改责任人
    private Date dutyTaskDate;              //接单时间
    private String updateUserName;          //填报人
    private Date updateUserDate;            //填报时间
    private String createPhone;             //创建人联系电话
    private String repairPhone;             //整改人联系电话
    private String problemType;             //简要描述
    private String planNum;                 //计划编码

    private List<String> imageList;//

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(String rectifyId) {
        this.rectifyId = rectifyId;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstTypeName() {
        return firstTypeName;
    }

    public void setFirstTypeName(String firstTypeName) {
        this.firstTypeName = firstTypeName;
    }

    public String getSecondTyoeName() {
        return secondTyoeName;
    }

    public void setSecondTyoeName(String secondTyoeName) {
        this.secondTyoeName = secondTyoeName;
    }

    public String getThirdTypeName() {
        return thirdTypeName;
    }

    public void setThirdTypeName(String thirdTypeName) {
        this.thirdTypeName = thirdTypeName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDealPeopleName() {
        return dealPeopleName;
    }

    public void setDealPeopleName(String dealPeopleName) {
        this.dealPeopleName = dealPeopleName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRectifyState() {
        return rectifyState;
    }

    public void setRectifyState(String rectifyState) {
        this.rectifyState = rectifyState;
    }

    public Date getRealityDate() {
        return realityDate;
    }

    public void setRealityDate(Date realityDate) {
        this.realityDate = realityDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    public Date getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(Date dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateUserDate() {
        return updateUserDate;
    }

    public void setUpdateUserDate(Date updateUserDate) {
        this.updateUserDate = updateUserDate;
    }

    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
