package com.maxrocky.vesta.application.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/13.
 * 整改单
 */
public class PropertyRectifyDTO {
    private String rectifyId;//整改单号
    private String department;//部门
    private String roomId;//房间id
    private String roomNum;//房间编码
    private String planNum;//房间计划编码
    private String acceptanceDate;//内部预验房日期
    private String problemType;//问题类型
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String registerDate;//登记日期
    private String rectifyState;//整改状态
    private String roomLocation;//房屋位置
    private String supplier;//供应商
    private String rectifyCompleteDate;//整改完成时间
    private String realityDate;//实际完成时间
    private String problemDescription;//问题描述
    private String dealResult;//处理结果
    private String createDate;//创建时间
    private String modifyDate;//修改时间
    private String createBy;//创建人编码
    private String createPhone;//创建人联系电话
    private String planType;//活动类型
    private String repairManager;//整改负责人名称
    private String repairPhone;//整改人联系电话
    private String repairDescription;//整改描述
    private String dutyTaskDate;//接单时间
    private String limitDate;//整改时限
    private String xCoordinates;//X坐标
    private String yCoordinates;//Y坐标
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private String createName;//创建人名称
    private String address;//地址
    private String dutyCompany;//责任单位
    private List<RectifyImageDTO> imageList;//问题图片
    private List<RectifyImageDTO> reviewImgList;//整改记录图片
    private String updateFlag;//更新标志
    private String locationName;//位置名称
    private String unitNum;//单元编码
    private String serialNumber;//问题编号
    private String repairManagerId;//内部负责人id
    private String supplierID;//责任单位负责人（供应商）
    private String reminderTime;//t提醒时间
    public PropertyRectifyDTO() {
        this.reminderTime="";
        this.supplierID="";
        this.repairManagerId="";
        this.serialNumber="";
        this.rectifyId = "";
        this.department = "";
        this.roomId = "";
        this.roomNum = "";
        this.planNum = "";
        this.acceptanceDate = "";
        this.problemType = "";
        this.classifyOne = "";
        this.classifyTwo = "";
        this.classifyThree = "";
        this.registerDate = "";
        this.rectifyState = "";
        this.roomLocation = "";
        this.supplier = "";
        this.rectifyCompleteDate = "";
        this.realityDate = "";
        this.problemDescription = "";
        this.dealResult = "";
        this.createDate = "";
        this.modifyDate = "";
        this.createBy = "";
        this.createPhone = "";
        this.planType = "";
        this.repairManager = "";
        this.repairPhone = "";
        this.repairDescription = "";
        this.dutyTaskDate = "";
        this.limitDate = "";
        this.xCoordinates = "";
        this.yCoordinates = "";
        this.projectNum = "";
        this.projectName = "";
        this.createName = "";
        this.address = "";
        this.dutyCompany = "";
        this.imageList =  new ArrayList<RectifyImageDTO>();
        this.reviewImgList = new ArrayList<RectifyImageDTO>();
        this.updateFlag = "";
        this.locationName="";
        this.unitNum="";
    }

    public String getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(String rectifyId) {
        this.rectifyId = rectifyId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRectifyState() {
        return rectifyState;
    }

    public void setRectifyState(String rectifyState) {
        this.rectifyState = rectifyState;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRectifyCompleteDate() {
        return rectifyCompleteDate;
    }

    public void setRectifyCompleteDate(String rectifyCompleteDate) {
        this.rectifyCompleteDate = rectifyCompleteDate;
    }

    public String getRealityDate() {
        return realityDate;
    }

    public void setRealityDate(String realityDate) {
        this.realityDate = realityDate;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
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

    public String getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(String dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(String xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public String getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(String yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public List<RectifyImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<RectifyImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<RectifyImageDTO> getReviewImgList() {
        return reviewImgList;
    }

    public void setReviewImgList(List<RectifyImageDTO> reviewImgList) {
        this.reviewImgList = reviewImgList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDutyCompany() {
        return dutyCompany;
    }

    public void setDutyCompany(String dutyCompany) {
        this.dutyCompany = dutyCompany;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRepairManagerId() {
        return repairManagerId;
    }

    public void setRepairManagerId(String repairManagerId) {
        this.repairManagerId = repairManagerId;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
}
