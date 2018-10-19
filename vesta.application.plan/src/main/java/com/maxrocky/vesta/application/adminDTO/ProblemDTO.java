package com.maxrocky.vesta.application.adminDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by lpc on 2016/6/6.
 */
public class ProblemDTO {

    private String caseId;          //问题ID 主键
    private String setId;           //批次ID
    private String caseTitle;       //问题标题
    private String casePlace;       //问题部位
    private String caseType;        //问题类型
    private String oneType;         //一级分类
    private String twoType;         //二级分类
    private String threeType;       //三级分类
    private String caseDesc;        //问题描述
    private String caseState;       //问题状态,------待定：1待接单 2待整改 3已整改 4已通过 5一次通过 6二次通过 7三次通过 0作废
    private String roomId;          //房间ID,或者公共区域ID
    private String projectId;       //项目名称
    private String planType;          //计划（模块）类型
    private Date createDate;        //创建时间
    private String createBy;        //创建人
    private int limitTime;           //整改时限
    private String comments;         //批注留言
    private String contractor;      //承建商（整改单位）
    private String responsibleUnit; //责任单位
    private String responsibleUnit2;//责任单位2
    private String responsibleUnit3;//责任单位3
    private String dispatchUnit;//派遣对象
    private String modifyBy;    // 修改人
    private Date modifyDate;//修改时间
    private BigDecimal xCoordinates;//X坐标
    private BigDecimal yCoordinates;//Y坐标
    private String projectName; // 工程名称
    private String firstTypeName; // 一级分类



    private String secondTyoeName; // 二级分类
    private String thirdTypeName; // 三级分类

    private String buildingId;// 楼栋ID
    private String unitId;// 单元Id
    private String floorId; // 楼层ID

    private Map<String,String> firstLevels; // 一级分类列表

    private Map<String,String> secondLevels; // 二级分类列表

    private Map<String,String> thirdLevels; // 二级分类列表

    private Map<String,String>  projects; // 项目名称

    private Map<String,String>  buildings; // 楼栋

    private Map<String,String>  units; // 单元

    private Map<String,String>  floors; // 楼层

    private Map<String,String>  rooms; // 房间号

    public Map<String, String> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
    }

    public Map<String, String> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<String, String> buildings) {
        this.buildings = buildings;
    }

    public Map<String, String> getUnits() {
        return units;
    }

    public void setUnits(Map<String, String> units) {
        this.units = units;
    }

    public Map<String, String> getFloors() {
        return floors;
    }

    public void setFloors(Map<String, String> floors) {
        this.floors = floors;
    }



    public Map<String, String> getThirdLevels() {
        return thirdLevels;
    }

    public void setThirdLevels(Map<String, String> thirdLevels) {
        this.thirdLevels = thirdLevels;
    }

    public Map<String, String> getSecondLevels() {
        return secondLevels;
    }

    public void setSecondLevels(Map<String, String> secondLevels) {
        this.secondLevels = secondLevels;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public String getResponsibleUnit2() {
        return responsibleUnit2;
    }

    public void setResponsibleUnit2(String responsibleUnit2) {
        this.responsibleUnit2 = responsibleUnit2;
    }

    public String getResponsibleUnit3() {
        return responsibleUnit3;
    }

    public void setResponsibleUnit3(String responsibleUnit3) {
        this.responsibleUnit3 = responsibleUnit3;
    }

    public String getDispatchUnit() {
        return dispatchUnit;
    }

    public void setDispatchUnit(String dispatchUnit) {
        this.dispatchUnit = dispatchUnit;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public BigDecimal getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(BigDecimal xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public BigDecimal getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(BigDecimal yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public Map<String, String> getFirstLevels() {
        return firstLevels;
    }

    public void setFirstLevels(Map<String, String> firstLevels) {
        this.firstLevels = firstLevels;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCasePlace() {
        return casePlace;
    }

    public void setCasePlace(String casePlace) {
        this.casePlace = casePlace;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    public String getTwoType() {
        return twoType;
    }

    public void setTwoType(String twoType) {
        this.twoType = twoType;
    }

    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

}
