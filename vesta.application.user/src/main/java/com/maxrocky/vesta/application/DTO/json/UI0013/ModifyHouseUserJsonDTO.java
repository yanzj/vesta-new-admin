package com.maxrocky.vesta.application.DTO.json.UI0013;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.StringUtil;

/**
 * Created by Tom on 2016/2/21 12:56.
 * Describe:
 */
public class ModifyHouseUserJsonDTO {

    private String id;
    private String role;//角色
    private String name;//真实姓名
    private Boolean isPay;//是否可缴费

    public ModifyHouseUserJsonDTO(){
        this.id = "";
        this.role = "";
        this.name = "";
        this.isPay = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    /**
     * 基础校验
     */
    public ApiResult check(){
        if(StringUtil.isEmpty(id)){
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
        return new SuccessApiResult();
    }

}
