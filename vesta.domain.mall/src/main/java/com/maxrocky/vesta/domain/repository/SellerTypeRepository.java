package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SellerTypeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */
public interface SellerTypeRepository {
    /**新增商铺类型*/
    void AddSellerType(SellerTypeEntity sellerTypeEntity);
    /**修改商铺类型*/
    void UpdateSellerType(SellerTypeEntity sellerTypeEntity);
    /**查询商铺类型*/
    List<SellerTypeEntity> getSellerTypeList();
    /**后台查询商铺类型  带分页*/
    List<SellerTypeEntity> getSellerTypeList(WebPage webPage);
    /**分类详情*/
    SellerTypeEntity getSellerTypeDetail(String id);
}
