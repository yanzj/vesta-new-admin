package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserSettingEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/17 13:47.
 * Describe:用户设置Repository接口
 */
public interface UserSettingRepository {

    /**
     * Describe:根据用户Id获取用户设置
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:48:38
     */
    UserSettingEntity get(String userId);

    /**
     * Describe:创建用户设置
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:22:26
     */
    void create(UserSettingEntity userSettingEntity);

    /**
     * Describe:修改用户设置
     * CreateBy:Tom
     * CreateOn:2016-02-20 04:06:06
     */
    void update(UserSettingEntity userSettingEntity);

    /**
     * 根据项目ID 查询用户ID
     * @param project
     * @return
     */
    List<UserSettingEntity> userSettingList(String project);



}
