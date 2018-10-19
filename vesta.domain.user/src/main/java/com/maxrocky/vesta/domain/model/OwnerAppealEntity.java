package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 业主申诉实体类
 * Created by WeiYangDong on 2017/8/9.
 */
@Entity
@Table(name = "owner_appeal")
public class OwnerAppealEntity {

    public static final int STATE_UNTREAT = 100;//申诉未处理
    public static final int STATE_PASS = 101;//申诉通过
    public static final int STATE_FAIL = 102;//申诉失败

    private String id;//主键ID
    private String appId;//客户端ID
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private String userId;//用户ID
    private String mobile;//手机号
    private String idType;//证件类型(OwnerAuthenticationEntity,参照常量)
    private String idCard;//证件号
    private String ownerName;//业主姓名
    private String houseNum;//房产编码(对应houseInfo -> roomNum)
    private String houseAddress;//房产地址
    private String contractCoverUrl;//合同封面
    private String idPhotoFrontUrl;//证件照片(正面)
    private String idPhotoBackUrl;//证件照片(背面)
    //0,普通申诉;1,特殊申诉,身份证被使用;
    private Integer appealType;//申诉类型

    //100,申诉未处理;101,申诉通过;102,申诉失败;
    private Integer handleState;//处理状态

    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    @Id
    @Column(name = "id",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "app_id",length = 50)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "city_id",length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_name",length = 50)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "project_num",length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "project_name",length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "user_id",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "mobile",nullable = false, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "id_type", length = 10)
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "id_card", length = 30)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "owner_name",length = 20)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "house_num",length = 100)
    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    @Basic
    @Column(name = "house_address",length = 200)
    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    @Basic
    @Column(name = "contract_cover_url",length = 100)
    public String getContractCoverUrl() {
        return contractCoverUrl;
    }

    public void setContractCoverUrl(String contractCoverUrl) {
        this.contractCoverUrl = contractCoverUrl;
    }

    @Basic
    @Column(name = "id_photo_front_url",length = 100)
    public String getIdPhotoFrontUrl() {
        return idPhotoFrontUrl;
    }

    public void setIdPhotoFrontUrl(String idPhotoFrontUrl) {
        this.idPhotoFrontUrl = idPhotoFrontUrl;
    }

    @Basic
    @Column(name = "id_photo_back_url",length = 100)
    public String getIdPhotoBackUrl() {
        return idPhotoBackUrl;
    }

    public void setIdPhotoBackUrl(String idPhotoBackUrl) {
        this.idPhotoBackUrl = idPhotoBackUrl;
    }

    @Basic
    @Column(name = "appeal_type",length = 2)
    public Integer getAppealType() {
        return appealType;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
    }

    @Basic
    @Column(name = "handle_state",length = 3)
    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }


    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
