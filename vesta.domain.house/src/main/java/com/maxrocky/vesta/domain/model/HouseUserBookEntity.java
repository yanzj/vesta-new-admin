package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.DateUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/14 20:07.
 * Describe:业主房屋关系实体类
 */
@Entity
@Table(name = "house_userBook")
public class HouseUserBookEntity {

    /* 状态 */
    public final static String STATE_NORMAL = "NORMAL";//正常
    public final static String STATE_REMOVE = "REMOVE";//解除

    /* 用户类型 */
    public final static String USER_TYPE_OWNER = "3";//业主
    public final static String USER_TYPE_FAMILY = "4";//同住人
    public final static String USER_TYPE_TENANT = "5";//租户(废弃)

    /* 是否可缴费 */
    public final static String IS_PAY_YES = "YES";//可缴费
    public final static String IS_PAY_NO = "NO";//不可缴费

    private String id;//主键
    private String userId;//用户Id
    private String memberId;//会员编号
    private String houseId;//房屋Id
    private String userType;//用户类型
    private String state;//状态
    private String isPay;//是否可缴费
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String idCardNumber;//身份证号
    private String city;//城市

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEMBER_ID",nullable = true, length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "USER_ID",nullable = true, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "HOUSE_ID",nullable = false, length = 50)
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "USER_TYPE",nullable = true, length = 10)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "STATE",nullable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "IS_PAY",nullable = true, length = 10)
    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    @Basic
    @Column(name = "CREATE_BY",nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON",nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY",nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON",nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "IDCARD_NUMBER",nullable = true,length = 18)
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @Basic
    @Column(name = "CITY",nullable = true,length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 解除房产关系
     */
    @Transient
    public void remove(){
        state = STATE_REMOVE;
        modifyOn = DateUtils.getDate();
    }

}
