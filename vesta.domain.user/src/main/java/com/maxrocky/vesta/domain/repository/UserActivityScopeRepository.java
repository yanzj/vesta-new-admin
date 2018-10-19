package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserActivityScopeEntity;

import java.util.List;

/**
 * Created by luxinxin on 2016/7/21.
 */
public interface UserActivityScopeRepository {

    UserActivityScopeEntity get(String userId, String activityId);

    void create(UserActivityScopeEntity userActivityScopeEntity);

    void update(UserActivityScopeEntity userActivityScopeEntity);

    List<UserActivityScopeEntity> get();

}
