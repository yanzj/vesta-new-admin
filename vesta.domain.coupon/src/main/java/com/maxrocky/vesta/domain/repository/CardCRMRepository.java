package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CardCRMEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/13.
 */
public interface CardCRMRepository {
    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    CardCRMEntity get(String id, String memberId);

    /**
     * Describe:创建会员卡信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    void create(CardCRMEntity CardCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改会员卡信息
     * ModifyBy:
     */
    void update(CardCRMEntity CardCRMEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 获取会员的会员卡的信息
     * ModifyBy:
     */
    List<CardCRMEntity> getCardInfo();
}
