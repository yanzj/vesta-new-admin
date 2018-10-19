package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ShopSellerEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by chen on 2016/1/14.
 */
public interface ShopSellerRepository {
    /**
     * 新增商户
     * */
    void AddShopSeller(ShopSellerEntity shopSellerEntity);

    /**
     * 修改商户
     * */
    boolean UpdateShopSeller(ShopSellerEntity shopSellerEntity);

    /**删除分类下级联删除商户*/
    void DelSellers(String typeId);

    /**
     * 根据条件查询商户列表
     * */
    List<Object[]> getShopSellerList(String projectId,String categoryId,String level,String range,String range2,Page page);

    /**
     * 查询商户详情
     * */
    ShopSellerEntity getShopSellerDetail(String sellerId);

    /**
     * 后台根据条件查询商户列表
     * */
    List<ShopSellerEntity> getShopSellers(ShopSellerEntity shopSellerEntity,WebPage webPage);

    /**
     * 查询大于该排序号的信息
     * */
    ShopSellerEntity getGreaterShopSeller(Integer sellerSort);

    /**
     * 查询小于该排序号的信息
     * */
    ShopSellerEntity getLessShopSeller(Integer sellerSort);

    /**查询排序号最大的信息*/
    ShopSellerEntity getMaxShopseller();

    /**置顶排序号*/
    void topShopSeller();

}
