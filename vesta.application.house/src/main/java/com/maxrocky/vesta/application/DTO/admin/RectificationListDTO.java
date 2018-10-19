package com.maxrocky.vesta.application.DTO.admin;

import com.maxrocky.vesta.application.DTO.json.HI0012.ProjectReturnImageJsonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/4/29.
 */
public class RectificationListDTO {
    private String id;
    private String repairId;////报修单号
    private String roomId;//房间id
    private String roomNum;//房间编码
    private String address;//房屋地址
    private String memberId;//报修人会员编号
    private String userPhone;//报修人电话
    private String userAddress;//报修人地址
    private String username;//报修人姓名
    private String createDate;//报修时间 登记时间
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类 问题类型
    private String mode;//维修方式：换、维修 还是这个是问题类型
    private String repairDate;//维修工时
    private String roomLocation;//房屋位置name
    private String state;//单据状态 已完成未完成
    private String taskDate;//接单时间
    private String content;//基本内容 描述
    private String dealMode;//处理方式:内部/责任单位/第三方
    private String dutyCompanyOne;//责任单位1
    private String dutyCompanyTwo;//责任单位2
    private String dutyCompanyThree;//责任单位3
    private String dealPeoplename;//处理人姓名
    private String repairCompany;//维修单位
    private String repairManager;//维修负责人
    private String dealPeople;//处理人
    private String dealPhone;//处理人电话
    private String dealResult;//处理结果
    private String dealCompleteDate;//处理人完工时间
    private String projectname;//所属项目
    private String projectNum;//所属项目
    private String projectid;//项目id
    private List<ProjectReturnImageJsonDTO> imgendList;//整改完成后图片
    private List imgList;//报修完成后图片list
    private List imageList;//报修完成前list
    private String type;// 0保修单 ， 1 整改单
    private String buildnum;
    private String plantype;
    private String supplier;//供应商
    private String limitDate;//整改时限
    private String repairComment;//整改完成后描述
    private String department;//派单部门
    private String uptime;//修改时间  最大时间为时间戳
    private String problemType;//问题类型
    private String serialNumber;//问题编号

    private String repairManagerId;//内部负责人id
    private String supplierId;//责任单位负责人（供应商）
    private String reminderTime;//t提醒时间

    private String xCoordinates;
    private String yCoordinates;

//    private String appId;//appId
    public RectificationListDTO(){
        //构造初始值
//        this.appId="";
        this.xCoordinates="";
        this.yCoordinates="";
        this.reminderTime="";//提醒时间
        this. repairManagerId="";//派单人姓名
        this. supplierId="";//派单时间
        this.serialNumber="";
        this.projectNum="";
        this.repairManager="";
        this.dealMode="";
        this.id="";
        this.repairCompany="";
        this.imgendList=new ArrayList<>();
        this.imgList=new ArrayList<>();
        this.imageList=new ArrayList<>();
        this.repairId="";////报修单号
        this.roomId="";//房间id
        this.roomNum="";//房间编码
        this.address="";//房屋地址
        this.memberId="";//报修人会员编号
        this.userPhone="";//报修人电话
        this.userAddress="";//报修人地址
        this.username="";//报修人姓名
        this.createDate="";//报修时间 登记时间
        this.classifyOne="";//一级分类
        this.classifyTwo="";//二级分类
        this.classifyThree="";//三级分类 问题类型
        this.mode="";//维修方式：换、维修 还是这个是问题类型
        this.roomLocation="";//房屋位置name
        this.state="";//单据状态 已完成未完成
        this.taskDate="";//接单时间
        this.content="";//基本内容 描述
        this.dutyCompanyOne="";//责任单位1
        this.dutyCompanyTwo="";//责任单位2
        this.dutyCompanyThree="";//责任单位3
        this.dealPeoplename="";//处理人姓名
        this.dealPeople="";//处理人
        this.dealPhone="";//处理人电话
        this.dealResult="";//处理结果
        this.dealCompleteDate="";//处理人完工时间
        this.projectname="";//所属项目
        this.projectid="";//项目id
        this.type="";// 0保修单 ， 1 整改单
        this.buildnum="";
        this.plantype="";
        this.department="";//派单部门
        this.uptime="";
        this.repairDate="";//维修工时
        this.department="";
        this.limitDate="";
        this.supplier="";
        this.repairComment="";
        this.problemType="";
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDutyCompanyOne() {
        return dutyCompanyOne;
    }

    public void setDutyCompanyOne(String dutyCompanyOne) {
        this.dutyCompanyOne = dutyCompanyOne;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getDealPhone() {
        return dealPhone;
    }

    public void setDealPhone(String dealPhone) {
        this.dealPhone = dealPhone;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }



    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(String dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public List getImgList() {
        return imgList;
    }

    public void setImgList(List imgList) {
        this.imgList = imgList;
    }

    public List getImageList() {
        return imageList;
    }

    public void setImageList(List imageList) {
        this.imageList = imageList;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDealPeoplename() {
        return dealPeoplename;
    }

    public void setDealPeoplename(String dealPeoplename) {
        this.dealPeoplename = dealPeoplename;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuildnum() {
        return buildnum;
    }

    public void setBuildnum(String buildnum) {
        this.buildnum = buildnum;
    }

    public String getPlantype() {
        return plantype;
    }

    public void setPlantype(String plantype) {
        this.plantype = plantype;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getRepairComment() {
        return repairComment;
    }

    public void setRepairComment(String repairComment) {
        this.repairComment = repairComment;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getDutyCompanyTwo() {
        return dutyCompanyTwo;
    }

    public void setDutyCompanyTwo(String dutyCompanyTwo) {
        this.dutyCompanyTwo = dutyCompanyTwo;
    }

    public String getDutyCompanyThree() {
        return dutyCompanyThree;
    }

    public void setDutyCompanyThree(String dutyCompanyThree) {
        this.dutyCompanyThree = dutyCompanyThree;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public List<ProjectReturnImageJsonDTO> getImgendList() {
        return imgendList;
    }

    public void setImgendList(List<ProjectReturnImageJsonDTO> imgendList) {
        this.imgendList = imgendList;
    }

    public String getRepairCompany() {
        return repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
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


    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

//    public String getAppId() {
//        return appId;
//    }
//
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
}
