package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserAuthInfoEntity;

/**
 * Created by Tom on 2016/1/21 0:22.
 * Describe:注册信息Repository接口
 */
public interface UserAuthInfoRepository {

    /**
     * Describe:创建注册信息
     * CreateBy:Tom
     * CreateOn:2016-01-21 12:24:39
     */
    void create(UserAuthInfoEntity userAuthInfoEntity);

}