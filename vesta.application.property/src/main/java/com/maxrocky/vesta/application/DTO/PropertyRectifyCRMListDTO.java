package com.maxrocky.vesta.application.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luxinxin on 2016/6/28.
 */
public class PropertyRectifyCRMListDTO {

    private String caseId;          //问题ID 主键
    private String setId;           //批次ID
    private String caseTitle;       //问题标题
    private String casePlace;       //问题部位
    private String caseType;        //问题类型
    private String oneType;         //一级分类
    private String twoType;         //二级分类
    private String threeType;       //三级分类
    private String caseDesc;        //问题描述
    private String caseState;       //问题状态,------草稿、待接单、处理中、已完成、已废弃
    private String roomId;          //房间ID,或者公共区域ID
    private String projectId;       //项目名称
    private String planType;          //计划（模块）类型
    private Date createDate;        //创建时间
    private String createBy;        //创建人
    private String createCode;      //创建人账号
    private Date limitTime;           //整改时限
    private String comments;         //批注留言
    private String contractor;      //供应商
    private String responsibleUnit; //责任单位------------
    private String responsibleUnit2;//责任单位2-----------
    private String responsibleUnit3;//责任单位3--------------
    private String dispatchUnit;//派遣对象----------------
    private String modifyBy;    // 修改人
    private Date modifyDate;//修改时间
    private String repairManager;//整改负责人
    private String repairPhone;//整改负责人电话
    private BigDecimal xCoordinates;//X坐标
    private BigDecimal yCoordinates;//Y坐标
    private String projectName; // 工程名称
    private String firstTypeName; // 一级分类

    private String secondTyoeName; // 二级分类
    private String thirdTypeName; // 三级分类
    private String buildingId;// 楼栋ID
    private String unitId;// 单元Id
    private String floorId; // 楼层ID

    private Date realityDate;//整改完成期限
    private String address;

    private List<RectifyImageDTO> questionImageList;//问题图片
    private List<RectifyImageDTO> rectifyImageList;//整改图片
    private String houseTypeUrl;//户型图片

    private String isOverdue;//是否超期
    private String houseTypeId;
    private String dealPeople;//处理人


    private String createByName;//创建人
    private String createPhone;//创建人
    private String senUserName;//派单人
    private Date sendDate;//派单时间
    private String dealPeopleName;//处理人
    private String upcloseName;//填报人
    private Date updateDate;//填报时间（关单）
    private Date dutyTaskDate;//接单时间

    //强制关闭事项
    private String forceClose;//强制关闭 原因
    private String forceCloseName;  //强制关闭 人员名字
    private Date forceCloseDate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
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

    public Date getRealityDate() {
        return realityDate;
    }

    public void setRealityDate(Date realityDate) {
        this.realityDate = realityDate;
    }

    public List<RectifyImageDTO> getQuestionImageList() {
        return questionImageList;
    }

    public void setQuestionImageList(List<RectifyImageDTO> questionImageList) {
        this.questionImageList = questionImageList;
    }

    public List<RectifyImageDTO> getRectifyImageList() {
        return rectifyImageList;
    }

    public void setRectifyImageList(List<RectifyImageDTO> rectifyImageList) {
        this.rectifyImageList = rectifyImageList;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    public String getHouseTypeUrl() {
        return houseTypeUrl;
    }

    public void setHouseTypeUrl(String houseTypeUrl) {
        this.houseTypeUrl = houseTypeUrl;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getSenUserName() {
        return senUserName;
    }

    public void setSenUserName(String senUserName) {
        this.senUserName = senUserName;
    }

    public String getDealPeopleName() {
        return dealPeopleName;
    }

    public void setDealPeopleName(String dealPeopleName) {
        this.dealPeopleName = dealPeopleName;
    }

    public String getUpcloseName() {
        return upcloseName;
    }

    public void setUpcloseName(String upcloseName) {
        this.upcloseName = upcloseName;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(Date dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    public String getForceClose() {
        return forceClose;
    }

    public void setForceClose(String forceClose) {
        this.forceClose = forceClose;
    }

    public String getForceCloseName() {
        return forceCloseName;
    }

    public void setForceCloseName(String forceCloseName) {
        this.forceCloseName = forceCloseName;
    }

    public Date getForceCloseDate() {
        return forceCloseDate;
    }

    public void setForceCloseDate(Date forceCloseDate) {
        this.forceCloseDate = forceCloseDate;
    }

    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }
}
