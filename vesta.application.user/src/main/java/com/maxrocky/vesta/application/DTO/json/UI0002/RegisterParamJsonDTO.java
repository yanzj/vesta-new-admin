package com.maxrocky.vesta.application.DTO.json.UI0002;

import com.maxrocky.vesta.application.DTO.json.HI0006.HouseParamJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.*;

/**
 * Created by Tom on 2016/1/18 16:48.
 * Describe:UI0002输入参数实体类
 */
public class RegisterParamJsonDTO {

    private String projectId;//项目id
    private String formatId;//业态Id
    private String buildingId;//楼Id
    private String unitId;//单元Id
    private String roomId;//房间号Id
    private String name;//真实姓名
    private String idNumber;//证件号码
    private String mobile;//手机号码
    private String authCode;//验证码
    private String password;//密码
    private String form;//来源
    private String phoneUUID;//手机唯一标识

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getPhoneUUID() {
        return phoneUUID;
    }

    public void setPhoneUUID(String phoneUUID) {
        this.phoneUUID = phoneUUID;
    }

    /**
     * Describe:基础数据校验
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:52:44
     */
    public ApiResult check(){
        if(StringUtil.isEmpty(projectId)){
            return new ErrorApiResult("tip_00000539");
        }
        if(StringUtil.isEmpty(formatId)){
            return new ErrorApiResult("tip_00000542");
        }
        if(StringUtil.isEmpty(buildingId)){
            return new ErrorApiResult("tip_00000540");
        }
        if(StringUtil.isEmpty(unitId)){
            return new ErrorApiResult("tip_00000541");
        }
        if(StringUtil.isEmpty(roomId)){
            return new ErrorApiResult("tip_00000543");
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
        if(StringUtil.isEmpty(idNumber)){
            return new ErrorApiResult("tip_00000285");
        }
        if(StringUtil.isEmpty(authCode)){
            return new ErrorApiResult("tip_00000466");
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
     * Describe:租户基础数据校验
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:52:44
     */
    public ApiResult checkTenant(){
        if(StringUtil.isEmpty(projectId)){
            return new ErrorApiResult("tip_00000539");
        }
        if(StringUtil.isEmpty(formatId)){
            return new ErrorApiResult("tip_00000542");
        }
        if(StringUtil.isEmpty(buildingId)){
            return new ErrorApiResult("tip_00000540");
        }
        if(StringUtil.isEmpty(unitId)){
            return new ErrorApiResult("tip_00000541");
        }
        if(StringUtil.isEmpty(roomId)){
            return new ErrorApiResult("tip_00000543");
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
        if(StringUtil.isEmpty(authCode)){
            return new ErrorApiResult("tip_00000466");
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
     * Describe:to HouseParamJsonDTO
     * CreateBy:Tom
     * CreateOn:2016-01-18 05:21:28
     */
    public HouseParamJsonDTO toHouseParamJsonDTO(){
        HouseParamJsonDTO houseParamJsonDTO = new HouseParamJsonDTO();
        houseParamJsonDTO.setProjectId(projectId);
        houseParamJsonDTO.setBuildingId(buildingId);
        houseParamJsonDTO.setFormatId(formatId);
        houseParamJsonDTO.setUnitId(unitId);
        houseParamJsonDTO.setRoomId(roomId);
        return houseParamJsonDTO;
    }

    /**
     * Describe:to UserInfoEntity
     * CreateBy:Tom
     * CreateOn:2016-01-18 05:47:26
     */
    public UserInfoEntity toUserInfoEntity(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(IdGen.getNewUserID());
        userInfoEntity.setCreateBy(name);
        userInfoEntity.setCreateOn(DateUtils.getDate());
        return toUserInfoEntity(userInfoEntity);
    }
    /**
     * Describe:to UserInfoEntity
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:34:20
     */
    public UserInfoEntity toUserInfoEntity(UserInfoEntity userInfoEntity){
        userInfoEntity.setUserName(mobile);
        userInfoEntity.setPassword(EncryptUtils.encryptPassword(password));
        userInfoEntity.setMobile(mobile);
        userInfoEntity.setNickName(name);
        userInfoEntity.setRealName(name);
        userInfoEntity.setSex(1);
        userInfoEntity.setIdCard(idNumber);
        userInfoEntity.setLogo(AppConfig.UserDefaultLogo);
        userInfoEntity.setUserState(UserInfoEntity.USER_STATE_NORMAL);
        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);
        userInfoEntity.setModifyBy(name);
        userInfoEntity.setModifyOn(DateUtils.getDate());
        userInfoEntity.setRegisterDate(DateUtils.getDate());
        return userInfoEntity;
    }

    /**
     * Describe:to UserInfoEntity
     * CreateBy:sunmei
     */
    public UserInfoEntity toUserInfoEntityTenant(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(IdGen.getNewUserID());
        userInfoEntity.setCreateBy(name);
        userInfoEntity.setCreateOn(DateUtils.getDate());
        return toUserInfoEntityTenant(userInfoEntity);
    }

    /**
     * Describe:to UserInfoEntity
     * CreateBy:sunemi
     */
    public UserInfoEntity toUserInfoEntityTenant(UserInfoEntity userInfoEntity){
        userInfoEntity.setUserName(mobile);
        userInfoEntity.setPassword(EncryptUtils.encryptPassword(password));
        userInfoEntity.setMobile(mobile);
        userInfoEntity.setNickName(name);
        userInfoEntity.setRealName(name);
        userInfoEntity.setSex(1);
        //userInfoEntity.setIdCard(idNumber);
        userInfoEntity.setLogo(AppConfig.UserDefaultLogo);
        userInfoEntity.setUserState(UserInfoEntity.USER_STATE_NORMAL);
        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_TENANT);
        userInfoEntity.setModifyBy(name);
        userInfoEntity.setModifyOn(DateUtils.getDate());
        userInfoEntity.setRegisterDate(DateUtils.getDate());
        return userInfoEntity;
    }
}
