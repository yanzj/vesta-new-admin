package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserToTalEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by liudongxin on 2016/6/4.
 * 用户统计数据操作
 */
public interface UserTotalRepository {

    /**
     * 通过日期获取统计信息
     * param:创建日期
     * return
     */
    UserToTalEntity getTotalInfo(String date);

    /**
     * 获取统计列表
     * param:userToTalEntity
     * param:webPage
     * return
     */
    List<UserToTalEntity> getTotalList(UserToTalEntity userToTalEntity,WebPage webPage);

    /**
     * 创建统计信息
     * param:userToTalEntity
     */
    void create(UserToTalEntity userToTalEntity);

    /**
     * 修改统计信息
     * param:userToTalEntity
     */
    void update(UserToTalEntity userToTalEntity);
}
