package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * 积分商城 Service
 * Created by WeiYangDong on 2017/7/10.
 */
public interface IntegralMallService {

    /**
     * 获取商品类目列表
     * @return List<MallDTO>
     */
    List<MallDTO> getProductTypeList(MallDTO mallDTO, WebPage webPage);


    /**
     * 获取商品类目列表
     * @return List<MallDTO>
     */
    List<MallDTO> getProductTypeLists(MallDTO mallDTO, WebPage webPage);

    /**
     * 获取商品类目信息
     * @return MallDTO
     */
    MallDTO getProductTypeInfo(MallDTO mallDTO);

    /**
     * 校验商品类目名称
     * @return int
     */
    int checkProductTypeName(MallDTO mallDTO);

    /**
     * 保存或更新商品类目信息
     */
    Integer saveOrUpdateProductType(MallDTO mallDTO);

    /**
     * 获取商品列表
     * @return List<MallDTO>
     */
    List<MallDTO> getProductList(MallDTO mallDTO, WebPage webPage);

    /**
     * 获取商品信息
     * @return MallDTO
     */
    MallDTO getProductInfo(MallDTO mallDTO);

    /**
     * 保存或更新商品类目信息
     */
    void saveOrUpdateProduct(MallDTO mallDTO);

    /**
     * 变更商品状态
     * @return int
     */
    int updateProductState(MallDTO mallDTO);

    /**
     * 删除商品
     * @return int
     */
    int deleteProduct(MallDTO mallDTO);

}
