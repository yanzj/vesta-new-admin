package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserStaffLoginBookEntity;

/**
 * Created by Tom on 2016/1/22 11:59.
 * Describe:员工登录信息Repository接口
 */
public interface UserStaffLoginBookRepository {

    /**
     * Describe:根据员工Id获取有效登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:16:16
     */
    UserStaffLoginBookEntity getLoginByStaffId(String staffId);

    /**
     * Describe:修改员工登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 12:07:28
     */
    void update(UserStaffLoginBookEntity userStaffLoginBookEntity);

    /**
     * Describe:创建员工登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 12:10:26
     */
    void create(UserStaffLoginBookEntity userStaffLoginBook);

    /**
     * Describe:根据Id获取员工登陆信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 01:51:49
     */
    UserStaffLoginBookEntity get(String loginId);
}
