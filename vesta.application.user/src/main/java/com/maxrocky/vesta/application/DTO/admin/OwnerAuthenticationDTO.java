package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业主认证申诉功能模块DTO
 * Created by WeiYangDong on 2017/8/4.
 */
public class OwnerAuthenticationDTO {

    private String menuId;//菜单ID
    private List<Map<String,Object>> roleScopeList;

    private String id;//主键ID
    private String appId;//客户端ID
    private String clientName;//客户端名称
    private String userId;//用户ID
    private String mobile;//手机号
    private String idType;//证件类型
    private String idCard;//证件号
    private String idPhotoFrontUrl;//证件照片(正面)
    private String idPhotoBackUrl;//证件照片(背面)
    private String ownerName;//业主姓名
    private String houseNum;//房产编码(对应houseInfo -> roomNum)
    private String houseAddress;//房产地址
    private String contractCoverUrl;//合同封面

    //0,普通申诉;1,特殊申诉,身份证被使用;
    private Integer appealType;//申诉类型
    //100,认证或申诉未处理;101,认证或申诉通过;102,认证或申诉失败;
    private Integer handleState;//处理状态

    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectNum;//项目编码
    private String projectName;//项目名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdPhotoFrontUrl() {
        return idPhotoFrontUrl;
    }

    public void setIdPhotoFrontUrl(String idPhotoFrontUrl) {
        this.idPhotoFrontUrl = idPhotoFrontUrl;
    }

    public String getIdPhotoBackUrl() {
        return idPhotoBackUrl;
    }

    public void setIdPhotoBackUrl(String idPhotoBackUrl) {
        this.idPhotoBackUrl = idPhotoBackUrl;
    }

    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getContractCoverUrl() {
        return contractCoverUrl;
    }

    public void setContractCoverUrl(String contractCoverUrl) {
        this.contractCoverUrl = contractCoverUrl;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public Integer getAppealType() {
        return appealType;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
    }
}
