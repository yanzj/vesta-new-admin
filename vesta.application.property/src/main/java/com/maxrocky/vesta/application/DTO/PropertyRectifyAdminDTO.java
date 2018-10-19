package com.maxrocky.vesta.application.DTO;

import com.maxrocky.vesta.application.DTO.admin.HouseTypeLabelDTO;
import com.maxrocky.vesta.domain.model.HouseTypeLabelEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/7/7.
 */
public class PropertyRectifyAdminDTO {
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
    private String buildingNum;//楼栋编码
    private String floorNum;//楼层编码
    private List<String> image;//已添加图片的ID
    private List<String> reviewImage;//整改图片的ID

    private String houseTypeId;
    private String houseTyprUrl;

    private MultipartFile[] imgFile;//问题图片
    private MultipartFile[] reviewImgFile;//整改图片

    private Map<String,String> firstLevels;
    private Map<String,String> secondLevels;
    private Map<String,String> thirdLevels;
    private Map<String,String> problemtypes;
    private Map<String,String> projects;
    private Map<String,String> buildings;
    private Map<String,String> units;
    private Map<String,String> floors;
    private Map<String,String> rooms;
    private Map<String,String> supplierMap;

    private List<String> ids;

    private String innerPerson;//内部负责人
    private Map<String,String> innerPersonList;//内部负责人列表
    private String supplierResponsible;//供应商负责人
    private Map<String,String> supplierResponsibleList;//供应商负责人列表

    private String forceClose;//强制关闭原因

    public String getInnerPerson() {
        return innerPerson;
    }

    public void setInnerPerson(String innerPerson) {
        this.innerPerson = innerPerson;
    }

    public Map<String, String> getInnerPersonList() {
        return innerPersonList;
    }

    public void setInnerPersonList(Map<String, String> innerPersonList) {
        this.innerPersonList = innerPersonList;
    }

    public String getSupplierResponsible() {
        return supplierResponsible;
    }

    public void setSupplierResponsible(String supplierResponsible) {
        this.supplierResponsible = supplierResponsible;
    }


    public Map<String, String> getSupplierResponsibleList() {
        return supplierResponsibleList;
    }

    public void setSupplierResponsibleList(Map<String, String> supplierResponsibleList) {
        this.supplierResponsibleList = supplierResponsibleList;
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

    public Map<String, String> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public MultipartFile[] getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile[] imgFile) {
        this.imgFile = imgFile;
    }

    public Map<String, String> getProblemtypes() {
        return problemtypes;
    }

    public void setProblemtypes(Map<String, String> problemtypes) {
        this.problemtypes = problemtypes;
    }

    public Map<String, String> getSupplierMap() {
        return supplierMap;
    }

    public void setSupplierMap(Map<String, String> supplierMap) {
        this.supplierMap = supplierMap;
    }

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public String getHouseTyprUrl() {
        return houseTyprUrl;
    }

    public void setHouseTyprUrl(String houseTyprUrl) {
        this.houseTyprUrl = houseTyprUrl;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(List<String> reviewImage) {
        this.reviewImage = reviewImage;
    }

    public MultipartFile[] getReviewImgFile() {
        return reviewImgFile;
    }

    public void setReviewImgFile(MultipartFile[] reviewImgFile) {
        this.reviewImgFile = reviewImgFile;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getForceClose() {
        return forceClose;
    }

    public void setForceClose(String forceClose) {
        this.forceClose = forceClose;
    }
}
