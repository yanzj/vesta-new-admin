package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserSettingEntity;

/**
 * Created by Tom on 2016/1/20 11:41.
 * Describe:用户设置Service接口
 */
public interface UserSettingService {

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据用户id获取用户设置
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:41:49
     */
    ApiResult getUserSettingByUserId(String userId);

    UserSettingEntity getUserSettingEntityByUserId(String userId);
}
