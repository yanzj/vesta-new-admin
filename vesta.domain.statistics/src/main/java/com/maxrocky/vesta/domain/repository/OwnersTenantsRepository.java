package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserInfoEntity;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 业主、租户 持久层接口
 */
public interface OwnersTenantsRepository {

    /**
     * 用户ID查询 用户信息表
     * @param userId
     * @return
     */
    List<UserInfoEntity> userInfoList(String userId);

    /**
     * 用户ID查询
     * @param projectId
     * @return
     */

    List<Object[]> getOwnersTenants(String projectId);
}
