package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.UserSettingService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tom on 2016/1/20 11:42.
 * Describe:用户设置Service接口实现类
 */
@Service
public class UserSettingServiceImpl implements UserSettingService {

    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据用户id获取用户配置
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:46:04
     */
    @Override
    public ApiResult getUserSettingByUserId(String userId) {

        UserSettingEntity userSettingEntity = userSettingRepository.get(userId);
        if(userSettingEntity == null){
            return new ErrorApiResult("tip_00000554");
        }

        return new SuccessApiResult(userSettingEntity);
    }

    @Override
    public UserSettingEntity getUserSettingEntityByUserId(String userId) {
        UserSettingEntity userSettingEntity = userSettingRepository.get(userId);
        if(userSettingEntity == null){
            return null;
        }
        return userSettingEntity;
    }
}
