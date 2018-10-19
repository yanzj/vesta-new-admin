package com.maxrocky.vesta.application.DTO.json.UI0009;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.*;

/**
 * Created by Tom on 2016/1/20 22:17.
 * Describe:UI0009输入参数实体类
 */
public class CreateHouseUserJsonDTO {

    private String houseId;//房产Id
    private String role;//角色
    private String name;//真实姓名
    private String mobile;//联系方式
    private String password;//登录密码
    private Boolean isPay;//是否可缴费
    private UserInfoEntity userInfoEntity;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    /**
     * 基础校验
     */
    public ApiResult check(){
        if(userInfoEntity == null){
            return new ErrorApiResult("tip_00000078");
        }
        if(StringUtil.isEmpty(houseId)){
            return new ErrorApiResult("tip_00000321");
        }
        if(StringUtil.isEmpty(role)){
            return new ErrorApiResult("tip_00000147");
        }
        if(!StringUtil.isEqual(role, UserInfoEntity.USER_TYPE_FAMILY)
                && !StringUtil.isEqual(role, UserInfoEntity.USER_TYPE_TENANT)){
            return new ErrorApiResult("tip_00000147");
        }
        if(StringUtil.isEmpty(name)){
            return new ErrorApiResult("tip_00000508");
        }
        if(name.length() > 60){
            return new ErrorApiResult("tip_00000551");
        }
        if(StringUtil.isEmpty(mobile)){
            return new ErrorApiResult("tip_00000396");
        }
        if(!VerifyUtils.isMobile(mobile)){
            return new ErrorApiResult("tip_00000400");
        }
        if(StringUtil.isEmpty(password)){
            return new ErrorApiResult("tip_00000196");
        }
        if(password.length() > 20){
            return new ErrorApiResult("tip_00000552");
        }

        return new SuccessApiResult();
    }

    /**
     * 生成新用户
     */
    public UserInfoEntity toUserInfoEntity(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(IdGen.getNewUserID());
        userInfoEntity.setCreateBy(name);
        userInfoEntity.setCreateOn(DateUtils.getDate());
        userInfoEntity.setUserName(mobile);
        userInfoEntity.setPassword(EncryptUtils.encryptPassword(password));
        userInfoEntity.setMobile(mobile);
        userInfoEntity.setNickName(name);
        userInfoEntity.setRealName(name);
        userInfoEntity.setSex(1);
        userInfoEntity.setLogo(AppConfig.UserDefaultLogo);
        userInfoEntity.setUserState(UserInfoEntity.USER_STATE_NORMAL);
        userInfoEntity.setUserType(role);
        userInfoEntity.setModifyBy(name);
        userInfoEntity.setModifyOn(DateUtils.getDate());
        return userInfoEntity;
    }

    /**
     * 游客、租户升级
     */
    public UserInfoEntity toUserInfoEntity(UserInfoEntity userInfoEntity){

        if(StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_VISITOR)){
            userInfoEntity.setUserName(mobile);
            userInfoEntity.setPassword(EncryptUtils.encryptPassword(password));
            userInfoEntity.setNickName(name);
            userInfoEntity.setRealName(name);
            userInfoEntity.setUserType(role);
        }else if(StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_TENANT)){
            userInfoEntity.setUserType(role);
        }

        userInfoEntity.setModifyBy(name);
        userInfoEntity.setModifyOn(DateUtils.getDate());
        return userInfoEntity;
    }

}
