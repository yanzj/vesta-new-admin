package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.SellerDTO;
import com.maxrocky.vesta.application.AdminDTO.SellerReceiveDTO;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by chen on 2016/1/17.
 */
public interface ShopSellerService {
    /**根据前台条件获取商户列表*/
    ApiResult getShopSellerList(UserInfoEntity userInfoEntity,String projectId,String categoryId,String level,String range,String range2,Page page);
    /**获取商户详情*/
    ApiResult getShopSellerDetail(String sellerId);

    /**根据后台条件查询商户列表*/
    List<SellerDTO> getShopSellers(SellerDTO sellerDTO,WebPage webPage);
    /**修改商户*/
    boolean UpdateSeller(SellerReceiveDTO sellerReceiveDTO);
    /**删除商户*/
    void DelShopSeller(String sellerId);
    /**新增商户*/
    void AddShopSeller(SellerReceiveDTO sellerReceiveDTO);
    /**后台获取商户详情*/
    SellerReceiveDTO getSellerDetail(String sellerId);
    /**后台移动排序*/
    void MoveShopSeller(String sellerId,String status);
}
