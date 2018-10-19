package com.maxrocky.vesta.application.adminDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by langmafeng on 2016/5/9/17:48.
 */
public class InspectionDTO {
    private String id;//主键
    private String projectName ;//项目名
    private Date createTime;//创建时间
    private String title;//日常巡检标题
    private String titleId;//日常巡检标题ID
    private String areaType;//问题位置
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类 问题类型
    private String description;//工程师或业主问题描述
    private String company;//承建商(整改单位);
    private String duty;//责任单位
    private String dealPeople;//整改人
    private String dealMpbile;//整改人电话
    private String dealResult;//处理结果
    private String dealCompleteDate;//整改完工时间
    //还没写附件
    private String content;//整改人基本内容 描述
    private String suggest;//批注留言
    private String reuser;//复查人
    private String reMobile;//复查人电话
    private String redeScription;//复查人描述
    private String status;//问题状态  是否删除     0-带接单，1-待整改,2-已整改,3-已通过
    private Date RegistrationTime;//登记时间
    private String RegistrationPeople;//登记人员
    private String floorArea;//楼层位置
    private String floorList;//楼层列表
    private String majorOne;//楼层列表
    private String majorTwo;//楼层列表
    private String majorThree;//楼层列表
    private String majorFour;//楼层列表
    private String warn;//紧急程度

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getDealMpbile() {
        return dealMpbile;
    }

    public void setDealMpbile(String dealMpbile) {
        this.dealMpbile = dealMpbile;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(String dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getReuser() {
        return reuser;
    }

    public void setReuser(String reuser) {
        this.reuser = reuser;
    }

    public String getReMobile() {
        return reMobile;
    }

    public void setReMobile(String reMobile) {
        this.reMobile = reMobile;
    }

    public String getRedeScription() {
        return redeScription;
    }

    public void setRedeScription(String redeScription) {
        this.redeScription = redeScription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistrationTime() {
        return RegistrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        RegistrationTime = registrationTime;
    }

    public String getRegistrationPeople() {
        return RegistrationPeople;
    }

    public void setRegistrationPeople(String registrationPeople) {
        RegistrationPeople = registrationPeople;
    }

    public String getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea;
    }

    public String getFloorList() {
        return floorList;
    }

    public void setFloorList(String floorList) {
        this.floorList = floorList;
    }

    public String getMajorOne() {
        return majorOne;
    }

    public void setMajorOne(String majorOne) {
        this.majorOne = majorOne;
    }

    public String getMajorTwo() {
        return majorTwo;
    }

    public void setMajorTwo(String majorTwo) {
        this.majorTwo = majorTwo;
    }

    public String getMajorThree() {
        return majorThree;
    }

    public void setMajorThree(String majorThree) {
        this.majorThree = majorThree;
    }

    public String getMajorFour() {
        return majorFour;
    }

    public void setMajorFour(String majorFour) {
        this.majorFour = majorFour;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }
}
