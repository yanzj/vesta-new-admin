package com.maxrocky.vesta.application.DTO.json.UI0005;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.EncryptUtils;
import com.maxrocky.vesta.utility.StringUtil;

/**
 * Created by Tom on 2016/1/20 10:04.
 * Describe:UI0005输入参数实体类
 */
public class ModifyUserInfoParamJsonDTO {

    private String logo;//头像
    private String nickName;//昵称
    private UserInfoEntity userInfoEntity;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    /**
     * 基本校验
     */
    public ApiResult check(){
        if(userInfoEntity == null){
            return new ErrorApiResult("tip_00000078");
        }
        return new SuccessApiResult();
    }

    /**
     * to UserInfoEntity
     */
    public UserInfoEntity toUserInfoEntity(){
        if(!StringUtil.isEmpty(logo)){
            userInfoEntity.setLogo(logo);
        }
        if(!StringUtil.isEmpty(nickName)){
            userInfoEntity.setNickName(nickName);
        }
        return userInfoEntity;
    }

}
