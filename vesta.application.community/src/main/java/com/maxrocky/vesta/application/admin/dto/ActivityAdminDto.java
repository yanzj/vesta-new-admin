package com.maxrocky.vesta.application.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/2/3.
 */
public class ActivityAdminDto {


    //进行中
    public static final int status_doing = 1;

    //已结束
    public static final int status_end = 2;

    private String cu_projectId;
    private String cu_projectName;
    private String activityId;//活动Id
    private String title;//活动标题
    private String activityDesc;//活动内容
    private String activityDate;//活动时间
    private String address;//地址
    private String hotline;//热线
    private String makedate;//创建时间
    private String modifydate;//修改时间
    private String operator;//操作人
    private String status;//状态
    private String countApply;//报名人数
    private Integer applyMaxNum;//每户报名人数上限
    private String homePage;//首页图片
    private String activity;//活动图片
    private String beginTime;//开始时间
    private String endTime;//结束时间
    private String scope;   //活动范围
    private String types;//类型
    private String state;//是否删除     0-已删，1-未删
    private String pushState;//是否发布     0-未发布，1-已发布
    private Integer headCount;//总人数
    private MultipartFile homePageimgpath;//图片地址(首页推荐图)
    private MultipartFile activityimgpath;//图片地址(新增)

    private String city;        //城市
    private String citys;        //城市集合
    private String cityIds;      //城市Id集合
    private String projects;     //项目集合
    private String projectIds;  //项目Id集合

    private String activityEndDate;   /* 活动截止时间 */
    private String applyStartTime; /* 报名开始时间 */
    private String applyEndTime; /* 报名截止时间 */
    private String applyInfo;//活动信息
    private List<JsonDto> applyInfo2; /* 活动信息 */
    private Integer applyInfoMaxNum;//报名信息最大组数

    /* 新增查询字段(用户权限范围)_2016-09-01_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private Integer activityType;//活动类型(1,普通活动;2,营销活动;3,商业活动;4,总部活动)
    private Integer themeType;//主题类型(1,家兴业茂;2,穆如清风;3,优居计划;4,金彩有你)
    private Integer isBanner;//是否作为宣传位(0,否1,是)
    private Integer isLink;//是否有外链(0,否1,是)
    private String linkSrc;//外链地址
    private Integer isBlacklist;//是否使用黑名单
    private String blacklistId;//黑名单ID
    private Integer isHouseScope;//是否发布房产范围(0,否1,是)
    private String houseScope;//房产范围
    private String projectCode;//项目编码

    private Integer isTimeRange;//是否配置活动报名时间段(0,否1,是)
    private String applyDate;//报名日期
    private List<String> startTimeList;//起始时间段时间列表
    private List<String> endTimeList;//截止时间段时间列表
    private List<String> maxUserList;//交付人数配额列表

    public Integer getIsTimeRange() {
        return isTimeRange;
    }

    public void setIsTimeRange(Integer isTimeRange) {
        this.isTimeRange = isTimeRange;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public List<String> getStartTimeList() {
        return startTimeList;
    }

    public void setStartTimeList(List<String> startTimeList) {
        this.startTimeList = startTimeList;
    }

    public List<String> getEndTimeList() {
        return endTimeList;
    }

    public void setEndTimeList(List<String> endTimeList) {
        this.endTimeList = endTimeList;
    }

    public List<String> getMaxUserList() {
        return maxUserList;
    }

    public void setMaxUserList(List<String> maxUserList) {
        this.maxUserList = maxUserList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getIsHouseScope() {
        return isHouseScope;
    }

    public void setIsHouseScope(Integer isHouseScope) {
        this.isHouseScope = isHouseScope;
    }

    public String getHouseScope() {
        return houseScope;
    }

    public void setHouseScope(String houseScope) {
        this.houseScope = houseScope;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public List<JsonDto> getApplyInfo2() {
        return applyInfo2;
    }

    public void setApplyInfo2(List<JsonDto> applyInfo2) {
        this.applyInfo2 = applyInfo2;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public static int getStatus_doing() {
        return status_doing;
    }

    public static int getStatus_end() {
        return status_end;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public MultipartFile getHomePageimgpath() { return homePageimgpath;  }

    public void setHomePageimgpath(MultipartFile homePageimgpath) {  this.homePageimgpath = homePageimgpath;  }

    public MultipartFile getActivityimgpath() {  return activityimgpath;  }

    public void setActivityimgpath(MultipartFile activityimgpath) {
        this.activityimgpath = activityimgpath;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getMakedate() {
        return makedate;
    }

    public void setMakedate(String makedate) {
        this.makedate = makedate;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountApply() {
        return countApply;
    }

    public void setCountApply(String countApply) {
        this.countApply = countApply;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCu_projectId() {
        return cu_projectId;
    }

    public void setCu_projectId(String cu_projectId) {
        this.cu_projectId = cu_projectId;
    }

    public String getCu_projectName() {
        return cu_projectName;
    }

    public void setCu_projectName(String cu_projectName) {
        this.cu_projectName = cu_projectName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPushState() {
        return pushState;
    }

    public void setPushState(String pushState) {
        this.pushState = pushState;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public Integer getApplyMaxNum() {
        return applyMaxNum;
    }

    public void setApplyMaxNum(Integer applyMaxNum) {
        this.applyMaxNum = applyMaxNum;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Integer isBanner) {
        this.isBanner = isBanner;
    }

    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }

    public Integer getApplyInfoMaxNum() {
        return applyInfoMaxNum;
    }

    public void setApplyInfoMaxNum(Integer applyInfoMaxNum) {
        this.applyInfoMaxNum = applyInfoMaxNum;
    }

    public Integer getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public String getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(String blacklistId) {
        this.blacklistId = blacklistId;
    }

    public Integer getThemeType() {
        return themeType;
    }

    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }
}
