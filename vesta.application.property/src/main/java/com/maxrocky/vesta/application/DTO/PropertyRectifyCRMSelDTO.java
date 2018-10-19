package com.maxrocky.vesta.application.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created by luxinxin on 2016/6/28.
 */
public class PropertyRectifyCRMSelDTO {
    private String id;


    private String groupId;//总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectNum;//项目
    private String type;//100000000 集团  100000001 区域  100000003 城市 100000002 项目
    private String projectName;//项目名称
    private String proType;//问题菜单类型：11内部预验 12工地开放 13正式交房
    private String oneType;//一级分类
    private String twoType;//二级分类
    private String threeType;//三级分类
    private String firstTypeName; // 一级分类名称
    private String secondTyoeName; // 二级分类名称
    private String thirdTypeName; // 三级分类名称
    private String caseState;//状态 ：草稿、待接单、处理中、已完成、已废弃、有效问题
    private String area;//地块
    private String buildingId;// 楼栋ID
    private String unitId;// 单元Id
    private String floorId;// 楼层ID
    private String roomId;//房间ID,或者公共区域ID
    private String supplier;//供应商

    private List<String> userProject;

   /* private Date startDate;//开始日期
    private Date endDate;//结束时间*/

    private String startDate;//开始日期
    private String endDate;//结束时间

    private String problemDesc;//问题描述
    private String projectId;
    private String userName;

    private String bewrite;//问题描述(非账号)
    private String createByName;//创建人姓名(非账号)
    private String dealPeopleName;//处理人姓名（非账号）
    private String senUserName;//派单人姓名（非账号）
    private String upcloseName;//填报人姓名（非账号）
    private String rectifyId; //整改单id

    private String successOrFailure;//上传crm是否成功

    private String planNum;//活动、计划、批次编码

    private String signaType;//是否为验房 1 为验房打印

    private String checkImage;//校验图片是否导出  1.导出

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    private Map<String,String> firstLevels; // 一级分类列表

    private Map<String,String> secondLevels; // 二级分类列表

    private Map<String,String> thirdLevels; // 二级分类列表

    private Map<String,String>  projects; // 项目名称

    private Map<String,String>  areas; // 地块

    private Map<String,String>  buildings; // 楼栋

    private Map<String,String>  units; // 单元

    private Map<String,String>  floors; // 楼层

    private Map<String,String>  rooms; // 房间号

    private Map<String,String> suppliers;//供应商

    private Map<String,String> planList;//供应商

   /* public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }*/

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
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

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Map<String, String> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public Map<String, String> getFirstLevels() {
        return firstLevels;
    }

    public void setFirstLevels(Map<String, String> firstLevels) {
        this.firstLevels = firstLevels;
    }

    public Map<String, String> getSecondLevels() {
        return secondLevels;
    }

    public void setSecondLevels(Map<String, String> secondLevels) {
        this.secondLevels = secondLevels;
    }

    public Map<String, String> getThirdLevels() {
        return thirdLevels;
    }

    public void setThirdLevels(Map<String, String> thirdLevels) {
        this.thirdLevels = thirdLevels;
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

    public List<String> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<String> userProject) {
        this.userProject = userProject;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Map<String, String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Map<String, String> suppliers) {
        this.suppliers = suppliers;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getDealPeopleName() {
        return dealPeopleName;
    }

    public void setDealPeopleName(String dealPeopleName) {
        this.dealPeopleName = dealPeopleName;
    }

    public String getSenUserName() {
        return senUserName;
    }

    public void setSenUserName(String senUserName) {
        this.senUserName = senUserName;
    }


    public String getBewrite() {
        return bewrite;
    }

    public void setBewrite(String bewrite) {
        this.bewrite = bewrite;
    }

    public String getUpcloseName() {
        return upcloseName;
    }

    public void setUpcloseName(String upcloseName) {
        this.upcloseName = upcloseName;
    }

    public String getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(String rectifyId) {
        this.rectifyId = rectifyId;
    }

    public String getSuccessOrFailure() {
        return successOrFailure;
    }

    public void setSuccessOrFailure(String successOrFailure) {
        this.successOrFailure = successOrFailure;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public Map<String, String> getPlanList() {
        return planList;
    }

    public void setPlanList(Map<String, String> planList) {
        this.planList = planList;
    }

    public Map<String, String> getAreas() {
        return areas;
    }

    public void setAreas(Map<String, String> areas) {
        this.areas = areas;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSignaType() {
        return signaType;
    }

    public void setSignaType(String signaType) {
        this.signaType = signaType;
    }

    public String getCheckImage() {
        return checkImage;
    }

    public void setCheckImage(String checkImage) {
        this.checkImage = checkImage;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
