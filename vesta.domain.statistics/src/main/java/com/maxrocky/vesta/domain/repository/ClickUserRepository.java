package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ClickUsersEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/6/5.
 * 点击人统计数据操作
 */
public interface ClickUserRepository {
    /**
     * 通过日期获取统计信息
     * param:创建日期
     * param:菜单:模块id
     * return
     */
    ClickUsersEntity getTotalInfo(String date,String id,String userId);

    /**
     * 获取模块点击人数量
     * param:创建日期
     * param:菜单:模块id
     * return
     */
    Integer getTotalNum(ClickUsersEntity clickUser,String id);

    /**
     * 获取模块点击数量
     * param:创建日期
     * return
     */
    Long getClickTotal(ClickUsersEntity clickUser,String id);

    /**
     * 获取点击人统计信息
     * param clickUser
     * return
     */
    ClickUsersEntity getTotalList(ClickUsersEntity clickUser,String id);

    /**
     * 创建统计信息
     * param:clickUserEntity
     */
    void create(ClickUsersEntity clickUserEntity);

    /**
     * 修改统计信息
     * param:clickUserEntity
     */
    void update(ClickUsersEntity clickUserEntity);
}
