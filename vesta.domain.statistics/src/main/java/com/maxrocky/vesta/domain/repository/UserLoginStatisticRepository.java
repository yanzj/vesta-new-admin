package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserLoginBookEntity;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 用户登录统计 持久层接口
 */
public interface UserLoginStatisticRepository {

    /**
     * 根据用户ID查询 登录信息
     * @param userId
     * @return
     */
    List<UserLoginBookEntity> getLoginByUserId(String userId);
}
