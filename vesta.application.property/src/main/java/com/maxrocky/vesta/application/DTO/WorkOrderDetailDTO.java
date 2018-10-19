package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/1/25.
 * 业主端:添加报修单/员工端:工单详情、添加工单/管理端：工单列表
 */
public class WorkOrderDetailDTO {
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
    private String completeStartDate;//开始时间
    private String completeEndDate;//结束时间
    private String memo;//备注：报修、投诉、维修、客服、秩序、环境
    private String roomId;//房屋id
    private String repairWay;//报修方式：APP/微信/呼叫中心/网站/项目前台/物业单
    public String firstType;
    public String secondType;
    public String buildingNum;
    public String buildingFloor;
    private Map<String,String> firstTypeMap;//一级分类
    private Map<String,String> secondTypeMap;//二级分类
    private Map<String,String> buildingNumMap;//楼栋
    private Map<String,String> buildingFloorMap;//楼层
    private Map<String,String> orderState;//工单状态
    private Map<String,String> orderType;//工单类型
    private Map<String,String> orderResource;//工单来源
    private List<PropertyImageDTO> imageList;//图片路径(创建报修单)
    private List<PropertyImageDTO> imagedList;//图片路径(维修完成后，image的过去分词imaged，代表完成后的)
    private List<PropertyServicesDTO> imageServiceList;//微信图片上传：图片在微信服务器上的地址

    /* 新增查询字段(用户权限范围)_2016-10-19_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private String menuId;      //菜单ID
    private String scopeId;     //区域ID
    private String projectCode; //项目Code

    public WorkOrderDetailDTO() {
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

    public Map<String, String> getFirstTypeMap() {
        return firstTypeMap;
    }

    public void setFirstTypeMap(Map<String, String> firstTypeMap) {
        this.firstTypeMap = firstTypeMap;
    }

    public Map<String, String> getSecondTypeMap() {
        return secondTypeMap;
    }

    public void setSecondTypeMap(Map<String, String> secondTypeMap) {
        this.secondTypeMap = secondTypeMap;
    }

    public Map<String, String> getBuildingNumMap() {
        return buildingNumMap;
    }

    public void setBuildingNumMap(Map<String, String> buildingNumMap) {
        this.buildingNumMap = buildingNumMap;
    }

    public Map<String, String> getBuildingFloorMap() {
        return buildingFloorMap;
    }

    public void setBuildingFloorMap(Map<String, String> buildingFloorMap) {
        this.buildingFloorMap = buildingFloorMap;
    }
    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(String buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCompleteStartDate() {
        return completeStartDate;
    }

    public void setCompleteStartDate(String completeStartDate) {
        this.completeStartDate = completeStartDate;
    }

    public String getCompleteEndDate() {
        return completeEndDate;
    }

    public void setCompleteEndDate(String completeEndDate) {
        this.completeEndDate = completeEndDate;
    }
}