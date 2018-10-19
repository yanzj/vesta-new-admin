package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SellerCollectEntity;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by chen on 2016/1/27.
 */
public interface SellerCollectRepository {
    /**新增商户收藏*/
    void AddSellerCollect(SellerCollectEntity sellerCollectEntity);
    /**根据用户ID和店铺ID查询数据*/
    SellerCollectEntity getSellerCollect(String sellerId,String userId);
    /**取消店铺收藏*/
    void DelSellerCollect(SellerCollectEntity sellerCollectEntity);
    /**获取用户收藏列表*/
    List<SellerCollectEntity> getUserCollects(String userId,Page page);
    /**判断当前用户是否收藏过该商户*/
    Boolean SellerCollects(String userId,String SellerId);
    /**获取用户收藏的店铺列表*/
    List<Object[]> getCollectSeller(String sellerIds);
    /**获取店铺被收藏数*/
    Integer getCollectNum(String sellerId);
}
