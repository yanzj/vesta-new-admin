package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/13 18:28.
 * Describe:登录信息实体
 */

@Entity
@Table(name = "user_loginBook")
public class UserLoginBookEntity {

    /* 登录状态 */
    public final static String STATE_NORMAL = "1";//正常登录
    public final static String STATE_EXIT = "2";//正常退出
    public final static String STATE_OUT = "3";//踢出（在其他设备登录）

    /* 登录类型 */
    public final static String LOGIN_TYPE_UI = "UI";//UI测试登录

    private String loginId;//登录Id
    private String userId;//用户Id
    private String loginType;//登录类型
    private String phoneUUID;//手机唯一标识
    private String unionId;//微信唯一标识
    private String state;//登录状态
    private String businessId;//业务Id
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    public UserLoginBookEntity(){ }

    public UserLoginBookEntity(UserInfoEntity userInfoEntity){
        loginId = IdGen.getLoginBookID();
        userId = userInfoEntity.getUserId();
        loginType = LOGIN_TYPE_UI;
        state = STATE_NORMAL;
        businessId = "";
        createBy = userInfoEntity.getOperator();
        createOn = DateUtils.getDate();
        modifyBy = userInfoEntity.getOperator();
        modifyOn = DateUtils.getDate();
    }

    @Id
    @Column(name = "LOGIN_ID", nullable = false, length = 36, unique = true)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "LOGIN_TYPE", length = 50)
    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Basic
    @Column(name = "PHONE_UUID", length = 100)
    public String getPhoneUUID() {
        return phoneUUID;
    }

    public void setPhoneUUID(String phoneUUID) {
        this.phoneUUID = phoneUUID;
    }

    @Basic
    @Column(name = "STATE", nullable = false, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "BUSINESS_ID", nullable = true, length = 100)
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = false, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = false, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = false)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "UNION_ID",length = 50)
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * Describe:判断是否登陆，登陆状态返回true，否则返回false
     * CreateBy:Tom
     * CreateOn:2016-01-13 08:58:19
     */
    @Transient
    public Boolean isLogin(){
        if(StringUtil.isEqual(state, STATE_NORMAL)){
            return true;
        }
        return false;
    }

    /**
     * 是否被踢掉
     */
    @Transient
    public Boolean isLoginOut(){
        if(StringUtil.isEqual(state, STATE_OUT)){
            return true;
        }
        return false;
    }

    /**
     * Describe:踢出登录
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:29:03
     */
    @Transient
    public void setStateExit(){
        state = STATE_OUT;
        modifyOn = DateUtils.getDate();
    }

    /**
     * 正常退出
     */
    @Transient
    public void setNormalExit(){
        state = STATE_EXIT;
        modifyOn = DateUtils.getDate();
    }

}
