package com.maxrocky.vesta.application.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DeliveryPlanCrmDto {

    private String id;//计划id
    private String projectNum;//项目编号_关联的项目信息new_deliveryplan的项目编码——BJ-YZJMY-X88-10-01-1001
    private String planNum;//计划编号_FXBJ001JFJH201603310001
    private String planName;//计划名称
    private String planStart;//计划开始时间 2016/4/5_集中交付开始时间
    private String planEnd;//计划结束时间
    private String dealStart;//办理开始时间
    private String dealEnd;//办理结束时间
    private String appoint;//合同约定时间
    private String description;//计划描述
    private String planType;//计划类型
    private String state;//计划执行状态
    private String focusDate;//集中处理时间
    private String focusAddress;//集中处理地点
    private String openAddress;//客户开放日地点
    private String sporadicAddress;//零星交房地址
    private String shortName;//简称
    private String modifyTime;//修改时间
    private String modifyBy;//修改人
    private Integer batchStatus;         //批次管理状态，在用户端是否显示  0，未发布 1，已发布，2，查询所有
    private String  batchImg;           //批次背景图
    private String  maxUserAm;     //上午时间段最大人数
    private String  maxUserPm;     //下午时间段最大人数
    private String  note;            //描述补充
    private String tempStatus;  //temp狀態
    private String url; //图片
    private String reservationDate;//预约日期
    private List<String> startDateList;//起始时间段时间列表
    private List<String> endDateList;//截止时间段时间列表
    private List<String> maxUserList;//交付人数配额列表
    private String reservationType;//预约形式
    private String projectName;//项目名称
    private MultipartFile homePageimgpath;//图片地址(首页推荐图)
    private MultipartFile activityimgpath;//图片地址(新增)

    private String planName_alias;//计划名称_别名
    private String planDate_alias;//计划起止时间_别名
    private String description_alias;//计划描述_别名
    private String focusAddress_alias;//集中交付地点_别名
    /* 新增校验字段(判断用户是否可以预约)_2016-09-12_Wyd */
    private Integer isDelivery;     //0,不可预约 1,可预约
    /* ------------------------------------- */

    /* 新增查询字段(用户权限范围)_2016-08-31_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private String menuId;  //菜单ID
    private String scopeId; //区域ID

    /* 新增查询字段_2018-05-25 */
    private String groupId;//总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目
    /* ------------------------------------- */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStart() {
        return planStart;
    }

    public void setPlanStart(String planStart) {
        this.planStart = planStart;
    }

    public String getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(String planEnd) {
        this.planEnd = planEnd;
    }

    public String getDealStart() {
        return dealStart;
    }

    public void setDealStart(String dealStart) {
        this.dealStart = dealStart;
    }

    public String getDealEnd() {
        return dealEnd;
    }

    public void setDealEnd(String dealEnd) {
        this.dealEnd = dealEnd;
    }

    public String getAppoint() {
        return appoint;
    }

    public void setAppoint(String appoint) {
        this.appoint = appoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFocusDate() {
        return focusDate;
    }

    public void setFocusDate(String focusDate) {
        this.focusDate = focusDate;
    }

    public String getFocusAddress() {
        return focusAddress;
    }

    public void setFocusAddress(String focusAddress) {
        this.focusAddress = focusAddress;
    }

    public String getOpenAddress() {
        return openAddress;
    }

    public void setOpenAddress(String openAddress) {
        this.openAddress = openAddress;
    }

    public String getSporadicAddress() {
        return sporadicAddress;
    }

    public void setSporadicAddress(String sporadicAddress) {
        this.sporadicAddress = sporadicAddress;
    }

    public Integer getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getBatchImg() {
        return batchImg;
    }

    public void setBatchImg(String batchImg) {
        this.batchImg = batchImg;
    }

    public String getMaxUserAm() {
        return maxUserAm;
    }

    public void setMaxUserAm(String maxUserAm) {
        this.maxUserAm = maxUserAm;
    }

    public String getMaxUserPm() {
        return maxUserPm;
    }

    public void setMaxUserPm(String maxUserPm) {
        this.maxUserPm = maxUserPm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTempStatus() {
        return tempStatus;
    }

    public void setTempStatus(String tempStatus) {
        this.tempStatus = tempStatus;
    }

    public MultipartFile getHomePageimgpath() {
        return homePageimgpath;
    }

    public void setHomePageimgpath(MultipartFile homePageimgpath) {
        this.homePageimgpath = homePageimgpath;
    }

    public MultipartFile getActivityimgpath() {
        return activityimgpath;
    }

    public void setActivityimgpath(MultipartFile activityimgpath) {
        this.activityimgpath = activityimgpath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public Integer getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Integer isDelivery) {
        this.isDelivery = isDelivery;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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

    public List<String> getStartDateList() {
        return startDateList;
    }

    public void setStartDateList(List<String> startDateList) {
        this.startDateList = startDateList;
    }

    public List<String> getEndDateList() {
        return endDateList;
    }

    public void setEndDateList(List<String> endDateList) {
        this.endDateList = endDateList;
    }

    public List<String> getMaxUserList() {
        return maxUserList;
    }

    public void setMaxUserList(List<String> maxUserList) {
        this.maxUserList = maxUserList;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getPlanName_alias() {
        return planName_alias;
    }

    public void setPlanName_alias(String planName_alias) {
        this.planName_alias = planName_alias;
    }

    public String getPlanDate_alias() {
        return planDate_alias;
    }

    public void setPlanDate_alias(String planDate_alias) {
        this.planDate_alias = planDate_alias;
    }

    public String getDescription_alias() {
        return description_alias;
    }

    public void setDescription_alias(String description_alias) {
        this.description_alias = description_alias;
    }

    public String getFocusAddress_alias() {
        return focusAddress_alias;
    }

    public void setFocusAddress_alias(String focusAddress_alias) {
        this.focusAddress_alias = focusAddress_alias;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}

