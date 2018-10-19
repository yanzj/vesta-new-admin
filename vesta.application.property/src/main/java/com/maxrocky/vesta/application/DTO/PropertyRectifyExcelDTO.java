package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by Magic on 2016/10/31.
 */
public class PropertyRectifyExcelDTO {
    /**
     * 		String[] titles = {
     * 		"序号","整改单ID", "是否超期","登记人","登记时间","整改期限","部位","问题描述","整改信息","位置",
     * 		"一级分类", "二级分类", "三级分类","供应商", "整改责任人","接单人","接单时间","填报人","完成时间","登记人电话","整改人电话","状态",
     * 		"城市", "地块", "项目", "楼栋", "单元", "楼层", "房间","简要描述","计划编码" }*/
    private int serialNumber;            //序号
    private String rectifyId;               //整改单id
    private String overdue;                 //是否超期
    private String createBy;                //登记人
    private Date createDate;                //登记时间
    private Date limitTime;                 //整改时限
    private String localtion;               //部位
    private String problemDescription;      //问题描述
    private String dealResult;              //整改信息
    private String address;                 //位置
    private String firstTypeName;           // 一级分类
    private String secondTyoeName;          // 二级分类
    private String thirdTypeName;           // 三级分类
    private String supplierName;            //供应商
    private String repairManager;           //整改责任人
    private String dealPeopleName;          //接单人
    private Date dutyTaskDate;              //接单时间
    private String updateUserName;          //填报人
    private Date updateUserDate;            //填报时间
    private String createPhone;             //创建人联系电话
    private String repairPhone;             //整改人联系电话
    private String rectifyState;            //状态
    private String city;        //城市
    private String area;        //地块
    private String project;     //项目
    private String build;       //楼栋
    private String unit;        //单元
    private String floor;       //楼层
    private String room;        //房间
    private String problemType;             //简要描述
    private String planNum;                 //计划编码
    private byte[] image1;//隐患图片
    private byte[] image2;//隐患图片
    private byte[] image3;//隐患图片


    public String getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(String rectifyId) {
        this.rectifyId = rectifyId;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
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

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }
}
