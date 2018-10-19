package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/13 19:23.
 * Describe:用户信息实体类
 */
@Entity
@Table(name = "user_userInfo")
public class UserInfoEntity {

    /* 用户类型 */
    public final static String USER_TYPE_VISITOR = "1";//游客
    public final static String USER_TYPE_NORMAL = "2";//会员
    public final static String USER_TYPE_OWNER = "3";//业主
    public final static String USER_TYPE_FAMILY = "4";//同住人
    public final static String USER_TYPE_TENANT = "5";//废弃


    public final static String USER_TYPE_SOURCE_APP = "1";//APP 注册
    public final static String USER_TYPE_SOURCE_WEIXIN = "2";//微信注册

    /* 用户状态 */
    public final static int USER_STATE_NORMAL = 1;//正常

    private String userId;//主键Id
    private String userName;//用户名
    private String password;//登录密码
    private String mobile;//手机
    private String nickName;//昵称
    private String realName;//真实姓名
    private Integer sex;//性别：1男;2女
    private Date birthday;//生日
    private String idCard;//证件号码
    private String idType;//证件类型
    private String logo;//头像
    private String marriage;//婚姻状况
    private Integer userState;//用户状态
    private String userType;//用户类型
    private Date registerDate;//注册时间
    private Integer viewAppOwnerId;//物业业主Id
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String id;//金茂项目：业主id
    private String jump;//金茂跳转页面专用字段 1未填写身份证 2填写了身份证 3填了身份证认证失败 4认证成功

    private String sourceType;//数据来源  1 -APP  2 -微信
    private String operatingSystem;//操作系统：android/ios
    private String WC_nickName;//微信用户昵称

    @Id
    @Column(name = "USER_ID",nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NAME",nullable = true, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD",nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "MOBILE",nullable = false, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "NICK_NAME",nullable = true, length = 20)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "REAL_NAME", length = 60)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "SEX",nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "ID_CARD", length = 30)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "ID_TYPE", length = 10)
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "LOGO", length = 300)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "MARRIAGE", length = 10)
    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    @Basic
    @Column(name = "USER_STATE")
    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    @Basic
    @Column(name = "USER_TYPE", length = 10)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "REGISTER_DATE")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "VIEW_APP_OWNER_ID")
    public Integer getViewAppOwnerId() {
        return viewAppOwnerId;
    }

    public void setViewAppOwnerId(Integer viewAppOwnerId) {
        this.viewAppOwnerId = viewAppOwnerId;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "ID", nullable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JUMP_PAGE",length = 2)
    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    @Basic
    @Column(name = "SOURCE_TYPE",length = 2)
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Basic
    @Column(name = "OPERATING_SYSTEM",length = 10)
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Basic
    @Column(name = "WC_NICK_NAME",nullable = true, length = 20)
    public String getWC_nickName() {
        return WC_nickName;
    }

    public void setWC_nickName(String WC_nickName) {
        this.WC_nickName = WC_nickName;
    }

    /**
     * 获取操作人
     * @return
     */
    @Transient
    public String getOperator(){

        String operator = "";

        if(!StringUtil.isEmpty(this.realName)){
            operator = realName;
        }else if(!StringUtil.isEmpty(this.nickName)){
            operator = nickName;
        }else {
            operator = userName;
        }

        return operator;
    }

    /**
     * Describe:创建游客账号
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:13:33
     */
    @Transient
    public void createVisitor(String mobile){
        this.userId = IdGen.getNewUserID();
        this.userName = mobile;
        this.password = mobile;
        this.mobile = mobile;
        this.nickName = "游客";
        this.sex = 0;
        this.logo = AppConfig.UserDefaultLogo;
        this.userState = USER_STATE_NORMAL;
        this.userType = USER_TYPE_VISITOR;
        this.createBy = mobile;
        this.createOn = DateUtils.getDate();
        this.modifyBy = mobile;
        this.modifyOn = DateUtils.getDate();
    }

    /**
     * 设置密码
     */
    @Transient
    public void resetPassword(String password){
        this.password = password;
        this.modifyBy = getOperator();
        this.modifyOn = DateUtils.getDate();
    }

    /**
     * 设置用户类型
     */
    @Transient
    public void resetUserType(String userType){
        this.userType = userType;
        this.modifyOn = DateUtils.getDate();
    }

    /**
     * 设置最大权限
     */
    @Transient
    public void setBestUserType(String userType){

        if(StringUtil.isEqual(this.userType, USER_TYPE_OWNER)){
            return;
        }
        resetUserType(userType);
    }


    /**
     * 获取用户类型
     */
    @Transient
    public static String getUserType(UserInfoEntity entity){

        return entity.getUserType();
    }

    /**
     * 返回两个角色中最大角色
     */
    @Transient
    public String getBestType(String oldType,String newType){
        if(StringUtil.isEqual(oldType, USER_TYPE_OWNER)
                || StringUtil.isEqual(newType, USER_TYPE_OWNER)){
            return USER_TYPE_OWNER;
        }
        if(StringUtil.isEqual(oldType, USER_TYPE_FAMILY)
                || StringUtil.isEqual(newType, USER_TYPE_FAMILY)){
            return USER_TYPE_FAMILY;
        }
        return USER_TYPE_VISITOR;
    }

    /**
     * 返回游客
     */
    @Transient
    public Boolean isVisitor(){
        if(StringUtil.isEqual(userType, USER_TYPE_VISITOR)){
            return true;
        }
        return false;
    }

}
