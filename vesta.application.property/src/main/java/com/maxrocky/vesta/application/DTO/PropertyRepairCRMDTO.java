package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/8/3.
 */
public class PropertyRepairCRMDTO {

    private String caseId;//问题id
    private Date createDate;//登记时间
    private String caseState;//问题状态
    private String userName;//记录人
    private String userNum;//记录人编号
    private String userPhone;//记录人电话
    private String address;//房间地址
    private String casePlace;//问题部位
    private String caseDesc;//问题描述
    private List<RectifyImageDTO> questionImageList;//问题图片
    private String classifyOne;//一级分类id
    private String classifyTwo;//二级分类id
    private String classifyThree;//三级分类id
    private String oneType;//一级分类
    private String twoType;//二级分类
    private String threeType;//三级分类
    private String repairMode;//维修方式
    private String repairWay;//维修方式id
    private String repairGroup;//维修组
    private String standardDate;//标准工时
    private String dealMode;//处理方式
    private String repairManager;//维修负责人
    private Date taskDate;//接单时间
    private Date dealCompleteDate;//完成时间
    private String thirdRepair;//维修第三方
    private String companyOne;//责任单位1
    private String companyTwo;//责任单位2
    private String companyThree;//责任单位3
    private String dealWay;//处理过程
    private List<RectifyImageDTO> repairImageList;//报修图片
    private String projectId;//项目编号
    private String projectName;//项目名称
    private String buildingId;
    private String floorId;
    private String unitId;
    private Map<String,String> firstLevels;//一级分类列表
    private Map<String,String> secondLevels;//二级分类列表
    private Map<String,String> thirdLevels;//三级分类列表
    private Map<String,String> alwaysLevels;//常用分类列表
    private Map<String,String> repairModes;//维修方式列表

    private List<String> reviewImage;//整改图片的ID
    private MultipartFile[] reviewImgFile;//整改图片

    private Map<String,String> companys;//责任单位1列表

    private String dMode;//处理方式，用于在页面判断出现哪些信息
    private String handleType;//是否有权限接单
    public Map<String, String> getCompanys() {
        return companys;
    }

    public void setCompanys(Map<String, String> companys) {
        this.companys = companys;
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

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    public Map<String, String> getRepairModes() {
        return repairModes;
    }

    public void setRepairModes(Map<String, String> repairModes) {
        this.repairModes = repairModes;
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

    public Map<String, String> getAlwaysLevels() {
        return alwaysLevels;
    }

    public void setAlwaysLevels(Map<String, String> alwaysLevels) {
        this.alwaysLevels = alwaysLevels;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCasePlace() {
        return casePlace;
    }

    public void setCasePlace(String casePlace) {
        this.casePlace = casePlace;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public List<RectifyImageDTO> getQuestionImageList() {
        return questionImageList;
    }

    public void setQuestionImageList(List<RectifyImageDTO> questionImageList) {
        this.questionImageList = questionImageList;
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

    public String getRepairMode() {
        return repairMode;
    }

    public void setRepairMode(String repairMode) {
        this.repairMode = repairMode;
    }

    public String getRepairGroup() {
        return repairGroup;
    }

    public void setRepairGroup(String repairGroup) {
        this.repairGroup = repairGroup;
    }

    public String getStandardDate() {
        return standardDate;
    }

    public void setStandardDate(String standardDate) {
        this.standardDate = standardDate;
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

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(Date dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public String getThirdRepair() {
        return thirdRepair;
    }

    public void setThirdRepair(String thirdRepair) {
        this.thirdRepair = thirdRepair;
    }

    public String getCompanyOne() {
        return companyOne;
    }

    public void setCompanyOne(String companyOne) {
        this.companyOne = companyOne;
    }

    public String getCompanyTwo() {
        return companyTwo;
    }

    public void setCompanyTwo(String companyTwo) {
        this.companyTwo = companyTwo;
    }

    public String getCompanyThree() {
        return companyThree;
    }

    public void setCompanyThree(String companyThree) {
        this.companyThree = companyThree;
    }

    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    public List<RectifyImageDTO> getRepairImageList() {
        return repairImageList;
    }

    public void setRepairImageList(List<RectifyImageDTO> repairImageList) {
        this.repairImageList = repairImageList;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }
}
