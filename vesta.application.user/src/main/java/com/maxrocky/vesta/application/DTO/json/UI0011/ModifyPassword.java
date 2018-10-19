package com.maxrocky.vesta.application.DTO.json.UI0011;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.EncryptUtils;
import com.maxrocky.vesta.utility.StringUtil;

/**
 * Created by Tom on 2016/1/25 19:14.
 * Describe:UI0011输入参数实体类
 */
public class ModifyPassword {

    private String oldPassword;//旧密码
    private String newPassword;//新密码
    private UserInfoEntity userInfoEntity;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
        if(StringUtil.isEmpty(oldPassword)){
            return new ErrorApiResult("tip_00000012");
        }
        if(StringUtil.isEmpty(newPassword)){
            return new ErrorApiResult("tip_00000011");
        }
        if(newPassword.length() > 20){
            return new ErrorApiResult("tip_00000552");
        }
        if(!StringUtil.isEqual(EncryptUtils.encryptPassword(oldPassword), userInfoEntity.getPassword())){
            return new ErrorApiResult("tip_00000504");
        }
        return new SuccessApiResult();
    }

    /**
     * to UserInfoEntity
     */
    public UserInfoEntity toUserInfoEntity(){
            userInfoEntity.setPassword(EncryptUtils.encryptPassword(newPassword));
        return userInfoEntity;
    }

}
