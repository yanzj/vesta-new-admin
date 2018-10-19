package com.maxrocky.vesta.application.adminDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/5/17.
 * 问题DTO
 */
public class PlanQuestionDTO {

    private String planType;//模块类型
    private String stage;//工程阶段，1工程建设 2交房阶段 3物业运营阶段
    private String planName;//模块名称

    private String setId;           //批次ID
    private String setName;         //批次名称
    private String setStatus;       //批次状态 0关闭 1打开 2删除

    private String caseId;          //问题ID 主键
    private String caseTitle;       //问题标题
    private String casePlace;       //问题部位
    private String caseType;        //问题类型
    private String createBy;        //问题提出人
    private String createDate;        //问题提出时间
    private String oneType;         //一级分类
    private String twoType;         //二级分类
    private String threeType;       //三级分类
    private String caseDesc;        //问题描述
    private String caseState;       //问题状态,1待接单 2待整改 3已整改 4已通过 5一次通过 6二次通过 7三次通过 0作废
    private String roomId;          //房间ID
    private String roomNum;         //房间编号
    private String address;         //地址
    private String roomName;        //房间名称
    private String unitId;          //单元ID
    private String unitName;        //单元名称
    private String floor;           //楼层
    private String buildingId;      //楼栋ID
    private String buildingName;    //楼栋名称
    private String projectId;       //项目ID
    private String projectName;     //项目名称
    private String areaId;          //区域ID
    private String areaName;        //区域名称
    private int limitTime;       //整改时限
    private String comments;        //批注留言
    private String contractor;      //承建商（整改单位）
    private String responsibleUnit; //责任单位
    private String responsibleUnit2;//责任单位2
    private String responsibleUnit3;//责任单位3
    private String dispatchUnit;//派遣对象
    private String createName;//创建人名称
    private String createPhone;//创建人电话
    private String createAddress;//创建人地址
    private String modifyBy;//修改人
    private String modifyDate;//修改日期
    private BigDecimal xCoordinates;//X坐标
    private BigDecimal yCoordinates;//Y坐标

    private List<CaseRepairDTO> repairList;//复查记录
    private List<QuestionImageDTO> caseImageList;//图片列表

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetStatus() {
        return setStatus;
    }

    public void setSetStatus(String setStatus) {
        this.setStatus = setStatus;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public List<CaseRepairDTO> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<CaseRepairDTO> repairList) {
        this.repairList = repairList;
    }

    public List<QuestionImageDTO> getCaseImageList() {
        return caseImageList;
    }

    public void setCaseImageList(List<QuestionImageDTO> caseImageList) {
        this.caseImageList = caseImageList;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    public String getCreateAddress() {
        return createAddress;
    }

    public void setCreateAddress(String createAddress) {
        this.createAddress = createAddress;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
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
}
