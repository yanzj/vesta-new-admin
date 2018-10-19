package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MenuTotalEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by liudongxin on 2016/6/4.
 * 菜单统计数据操作
 */
public interface MenuTotalRepository {
    /**
     * 通过日期获取统计信息
     * param:创建日期
     * return
     */
    MenuTotalEntity getTotalInfo(String date);

    /**
     * 获取统计列表
     * param:menuToTalEntity
     * param:webPage
     * return
     */
    List<MenuTotalEntity> getTotalList();

    /**
     * 创建统计信息
     * param:menuToTalEntity
     */
    void create(MenuTotalEntity menuToTalEntity);

    /**
     * 修改统计信息
     * param:menuToTalEntity
     */
    void update(MenuTotalEntity menuToTalEntity);
}
