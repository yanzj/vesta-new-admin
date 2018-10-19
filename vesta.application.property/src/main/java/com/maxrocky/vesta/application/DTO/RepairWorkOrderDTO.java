package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2016/5/13.
 * 保修管理
 */
public class RepairWorkOrderDTO {
    private String id;//报修单id
    private String content;//内容
    private String userAddress;//业主地址(员工端：员工部门)
    private String userName;//业主姓名(员工端：员工姓名)
    private String userPhone;//业主电话(员工端：员工电话)
    private String status;//状态
    private String state;//任务状态
    private String createDate;//创建日期
    private String type;//类型:0为报修类;1为客服类;
    private String grade;//评分
    private String gradeContent;//评价内容
    private String project;//项目
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String memo;//备注：报修、投诉、维修、客服、秩序、环境
    private String roomId;//房屋id
    private String repairWay;//报修方式：APP/微信/呼叫中心/网站/项目前台/物业单据
    private Map<String,String> orderState;//工单状态
    private Map<String,String> orderType;//工单类型
    private Map<String,String> orderResource;//工单来源
    private List<PropertyImageDTO> imageList;//图片路径(创建报修单)
    private List<PropertyImageDTO> imagedList;//图片路径(维修完成后，image的过去分词imaged，代表完成后的)
    private List<PropertyServicesDTO> imageServiceList;//微信图片上传：图片在微信服务器上的地址

    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类

    public RepairWorkOrderDTO() {
        this.content="";
        this.content = "";
        this.userAddress = "";
        this.userName = "";
        this.userPhone = "";
        this.status="";
        this.state="";
        this.createDate="";
        this.type="";
        this.project="";
        this.startDate="";
        this.endDate="";
        this.grade="";
        this.gradeContent="";
        this.memo="";
        this.roomId="";
        this.repairWay="";
        this.orderState=new LinkedHashMap<String,String>();
        this.orderType=new LinkedHashMap<String,String>();
        this.orderResource=new LinkedHashMap<String,String>();
        this.imageList=new ArrayList<PropertyImageDTO>();
        this.imagedList=new ArrayList<PropertyImageDTO>();
        this.imageServiceList=new ArrayList<PropertyServicesDTO>();

        this.classifyOne="";
        this.classifyTwo="";
        this.classifyThree="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<PropertyImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<PropertyImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Map<String, String> getOrderState() {
        return orderState;
    }

    public void setOrderState(Map<String, String> orderState) {
        this.orderState = orderState;
    }

    public Map<String, String> getOrderType() {
        return orderType;
    }

    public void setOrderType(Map<String, String> orderType) {
        this.orderType = orderType;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeContent() {
        return gradeContent;
    }

    public void setGradeContent(String gradeContent) {
        this.gradeContent = gradeContent;
    }

    public List<PropertyImageDTO> getImagedList() {
        return imagedList;
    }

    public void setImagedList(List<PropertyImageDTO> imagedList) {
        this.imagedList = imagedList;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<PropertyServicesDTO> getImageServiceList() {
        return imageServiceList;
    }

    public void setImageServiceList(List<PropertyServicesDTO> imageServiceList) {
        this.imageServiceList = imageServiceList;
    }

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    public Map<String, String> getOrderResource() {
        return orderResource;
    }

    public void setOrderResource(Map<String, String> orderResource) {
        this.orderResource = orderResource;
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
}
