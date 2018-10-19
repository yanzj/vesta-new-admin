package com.maxrocky.vesta.application.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/29
 * Time: 21:51
 * 社区详细信息
 */
public class CommunityOverviewDto implements Serializable {

    private String id; // 社区ID
    private String name; // 楼盘名称
    private String panoramaImg; // 全景图
    private String favorable; // 优惠信息
    private List<String> titles;//标题，金茂楼盘详情，去编辑
    private String priceAverage; // 均价
    private String phone; // 电话
    private List<String> typeList = new ArrayList<String>();// 标注【科技住宅，d公园地产】
    private Integer status;//樓盤是否在售
    private String city;//所属城市
    private String createDate;      //创建时间
    private String createPerson;     //创建人
    private String operatorDate;    //操作时间
    private String operator;        //操作人
    private Integer orderNum;   //排序字段
    private String releasePerson;   //发布人
    private String releaseDate;   //发布日期
    private Integer releaseStatus;          //发布状态  0，未发布，1，已经发布
    private String url;     //h5_url 金茂制作
    private MultipartFile homePageimgpath;//图片地址(首页推荐图)
    private String homePageimgUrl;//图片地址(首页推荐图)
    private List<MultipartFile> homePageimgpaths;//图片地址，金茂楼盘详情，去编辑
    private MultipartFile activityimgpath;//图片地址(新增)
    private Integer imgStatus;//图片状态
    private String orderDate;
    private List<String> describes;//金茂楼盘详情去编辑，描述
    private String cityScope;//区域城市

    private String projectName;//项目名称
    private String types;//标签
    private String staDate;
    private String endDate;

    private String projectIds;  //项目Id集合
    private String projectList; //项目集合
    private String cityIds;     //城市Id集合
    private String cityList;    //城市集合

    /* 新增查询字段(用户权限范围)_2016-08-26_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private String menuId;      //菜单Id
    private String scopeId;     //区域Id
    private String projectCode; //项目编码

    /* 新增楼盘字段_2017-05-22_Wyd */
    private String address;//项目地址
    private String introduction;//项目介绍
    private String periphery;//周边配套描述
    private List<String> floorPlanUrlList;//户型图列表
    private List<String> masterPlanUrlList;//总平面图列表
    private List<String> interiorDesignList;//室内设计图列表
    private List<String> gardenDesignList;//园林设计图列表
    private List<String> supportingFacilitiesList;//配套设施图片列表

    private String overviewName;//楼盘项目名称
    private String reservationPer;//预约人
    private String reservationTel;//联系方式
    /* --------------------------- */

    public String getCityScope() {
        return cityScope;
    }

    public void setCityScope(String cityScope) {
        this.cityScope = cityScope;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getDescribes() {
        return describes;
    }

    public void setDescribes(List<String> describes) {
        this.describes = describes;
    }

    public List<MultipartFile> getHomePageimgpaths() {
        return homePageimgpaths;
    }

    public void setHomePageimgpaths(List<MultipartFile> homePageimgpaths) {
        this.homePageimgpaths = homePageimgpaths;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
//        this.name = name.trim();
        this.name = name;
    }

    public String getPanoramaImg() {
        return panoramaImg;
    }

    public void setPanoramaImg(String panoramaImg) {
        this.panoramaImg = panoramaImg;
    }

    public String getFavorable() {
        return favorable;
    }

    public void setFavorable(String favorable) {
//        this.favorable = favorable.trim();
        this.favorable = favorable;
    }

    public String getPriceAverage() {
        return priceAverage;
    }

    public void setPriceAverage(String priceAverage) {
        this.priceAverage = priceAverage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(String operatorDate) {
        this.operatorDate = operatorDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getTypes() {
        return types;
    }
    public void setTypes(String types) {
        this.types = types;
    }

    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getCityList() {
        return cityList;
    }

    public void setCityList(String cityList) {
        this.cityList = cityList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPeriphery() {
        return periphery;
    }

    public void setPeriphery(String periphery) {
        this.periphery = periphery;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }

    public List<String> getFloorPlanUrlList() {
        return floorPlanUrlList;
    }

    public void setFloorPlanUrlList(List<String> floorPlanUrlList) {
        this.floorPlanUrlList = floorPlanUrlList;
    }

    public List<String> getMasterPlanUrlList() {
        return masterPlanUrlList;
    }

    public void setMasterPlanUrlList(List<String> masterPlanUrlList) {
        this.masterPlanUrlList = masterPlanUrlList;
    }

    public List<String> getInteriorDesignList() {
        return interiorDesignList;
    }

    public void setInteriorDesignList(List<String> interiorDesignList) {
        this.interiorDesignList = interiorDesignList;
    }

    public List<String> getGardenDesignList() {
        return gardenDesignList;
    }

    public void setGardenDesignList(List<String> gardenDesignList) {
        this.gardenDesignList = gardenDesignList;
    }

    public List<String> getSupportingFacilitiesList() {
        return supportingFacilitiesList;
    }

    public void setSupportingFacilitiesList(List<String> supportingFacilitiesList) {
        this.supportingFacilitiesList = supportingFacilitiesList;
    }

    public String getHomePageimgUrl() {
        return homePageimgUrl;
    }

    public void setHomePageimgUrl(String homePageimgUrl) {
        this.homePageimgUrl = homePageimgUrl;
    }

    public String getOverviewName() {
        return overviewName;
    }

    public void setOverviewName(String overviewName) {
        this.overviewName = overviewName;
    }

    public String getReservationPer() {
        return reservationPer;
    }

    public void setReservationPer(String reservationPer) {
        this.reservationPer = reservationPer;
    }

    public String getReservationTel() {
        return reservationTel;
    }

    public void setReservationTel(String reservationTel) {
        this.reservationTel = reservationTel;
    }
}
