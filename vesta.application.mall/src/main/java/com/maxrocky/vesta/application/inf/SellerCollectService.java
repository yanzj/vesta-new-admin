package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.SellerSeachDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.Page;


/**
 * Created by chen on 2016/1/27.
 */
public interface SellerCollectService {
    /**收藏店铺*/
    ApiResult AddSellerCollect(SellerSeachDTO sellerSeachDTO,UserInfoEntity userInfoEntity);
    /**取消店铺收藏*/
    ApiResult CancelSellerCollect(SellerSeachDTO sellerSeachDTO,UserInfoEntity userInfoEntity);
    /**获取用户收藏店铺列表*/
    ApiResult getUserSellerCollects(String userId,Page page);
}
