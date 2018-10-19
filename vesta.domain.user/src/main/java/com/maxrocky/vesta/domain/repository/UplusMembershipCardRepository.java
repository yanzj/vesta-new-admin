package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UplusMembershipCardEntity;

/**
 * U+会员卡数据持久层
 * Created by WeiYangDong on 2017/11/1.
 */
public interface UplusMembershipCardRepository {

    /**
     * 保存或更新Entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 返回ID最大值
     */
    int getMaxId();

    /**
     * 通过用户ID获取U+会员卡信息
     * @param userId 用户ID
     * @return UplusMembershipCardEntity
     */
    UplusMembershipCardEntity getUplusMembershipCardByUserId(String userId);
}
