package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MallProductEntity;
import com.maxrocky.vesta.domain.model.MallProductTypeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 积分商城 DAO
 * Created by WeiYangDong on 2017/7/10.
 */
public interface IntegralMallRepository {

    <T> void saveOrUpdate(T entity);
    <E> void delete(E entity);

    /**
     * 获取商品类目列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<MallProductTypeEntity>
     */
    List<MallProductTypeEntity> getProductTypeList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 获取商品类目信息
     * @param productTypeId 商品类目ID
     * @return MallProductTypeEntity
     */
    MallProductTypeEntity getProductTypeById(String productTypeId);

    /**
     * 获取商品列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<MallProductEntity>
     */
    List<Object> getProductList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 获取商品信息
     * @param productId 商品类目ID
     * @return MallProductEntity
     */
    MallProductEntity getProductById(String productId);
}
