package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SalesPromotionInfoEntity;
import com.maxrocky.vesta.domain.model.SalesPromotionInfoScopeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 促销信息-功能模块数据持久层
 * Created by WeiYangDong on 2017/5/11.
 */
public interface SalesPromotionInfoRepository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 获取促销信息列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<SalesPromotionInfoEntity>
     */
    List<SalesPromotionInfoEntity> getSalesPromotionInfoList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 删除促销信息发布范围
     * @param salesPromotionInfoId 促销ID
     */
    void delSalesPromotionInfoScopeById(String salesPromotionInfoId);

    /**
     * 通过促销ID
     * @param salesPromotionInfoId 促销ID
     * @return List<SalesPromotionInfoScopeEntity>
     */
    List<SalesPromotionInfoScopeEntity> getSalesPromotionInfoScopeById(String salesPromotionInfoId);
}
