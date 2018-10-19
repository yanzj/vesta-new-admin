package com.maxrocky.vesta.application.DTO.json.UI0003;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.EncryptUtils;
import com.maxrocky.vesta.utility.StringUtil;
import com.maxrocky.vesta.utility.VerifyUtils;

/**
 * Created by Tom on 2016/1/19 15:34.
 * Describe:UI0003重置密码输入参数实体类
 */
public class ResetPasswordJsonDTO {

    private String phone;//手机号
    private String authCode;//验证码
    private String password;//新密码

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    /**
     * Describe:基础数据校验
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:52:44
     */
    public ApiResult check(){
        if(StringUtil.isEmpty(phone)){
            return new ErrorApiResult("tip_00000396");
        }
        if(!VerifyUtils.isMobile(phone)){
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
}
