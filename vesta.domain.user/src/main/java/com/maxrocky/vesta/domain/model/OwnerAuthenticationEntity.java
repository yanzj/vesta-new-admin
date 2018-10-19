package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 业主认证实体类
 * Created by WeiYangDong on 2017/8/1.
 */
@Entity
@Table(name = "owner_authentication")
public class OwnerAuthenticationEntity {

    public static final String ID_TYPE_SF = "100000000";//身份证
    public static final String ID_TYPE_JUNG = "100000001";//军官证
    public static final String ID_TYPE_JINGG = "100000002";//警官证
    public static final String ID_TYPE_HZ = "100000003";//护照
    public static final String ID_TYPE_YYZZ = "100000004";//营业执照

    public static final int STATE_UNTREAT = 100;//认证未处理
    public static final int STATE_PASS = 101;//认证通过
    public static final int STATE_FAIL = 102;//认证失败

    private String id;//主键ID
    private String appId;//客户端ID
    private String userId;//用户ID
    private String mobile;//手机号
    private String idType;//证件类型
    private String idCard;//证件号
    private String idPhotoFrontUrl;//证件照片(正面)
    private String idPhotoBackUrl;//证件照片(背面)

    //100,认证未处理;101,认证通过;102,认证失败;
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
