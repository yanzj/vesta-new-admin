package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.SellerTypeDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;


/**
 * Created by chen on 2016/1/17.
 */
public interface SellerTypeService {
    /**获取商铺类型列表*/
    ApiResult getCategoryList();
    /**后台 获取商铺类型列表*/
    List<SellerTypeDTO> getTypeList();
    /**后台 获取商铺类型 带分页*/
    List<SellerTypeDTO> getTypeList(WebPage webPage);
    /**后台 新增商铺类型*/
    void addSellerType(SellerTypeDTO sellerTypeDTO);
    /**后台 修改商铺类型*/
    void updateSellerType(SellerTypeDTO sellerTypeDTO);
    /**后台 删除商铺类型*/
    void delSellerType(String typeId);
    /**获取分类详情*/
    SellerTypeDTO getSellerTypeDetail(String typeId);
}
