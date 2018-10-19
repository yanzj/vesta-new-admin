package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SellerEvaluateEntity;

import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */
public interface SellerEvaluateRepository {
    /**新增评价*/
    void AddSellerEvaluate(SellerEvaluateEntity sellerEvaluateEntity);
    /**查询好评列表*/
    List<SellerEvaluateEntity> getGoodEvaluates(String sellerId);
    /**查询差评列表*/
    List<SellerEvaluateEntity> getBadEvaluates(String sellerId);
    /**修改评价状态*/
    void UpdateEvaluate(SellerEvaluateEntity sellerEvaluateEntity);
    /**根据ID查询评价详情*/
    SellerEvaluateEntity getEvaluateDetail(String id);
    /**查询好评排行榜*/
    List<Object[]> getReputationList();
    /**根据用户ID商户ID查询评价情况*/
    List<SellerEvaluateEntity> getUserEvaluate(String userId,String sellerId);
}
