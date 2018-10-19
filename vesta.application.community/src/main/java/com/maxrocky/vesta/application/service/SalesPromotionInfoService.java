package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.SalesPromotionInfoDTO;
import com.maxrocky.vesta.domain.model.SalesPromotionInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * 促销信息-功能模块服务层
 * Created by WeiYangDong on 2017/5/11.
 */
public interface SalesPromotionInfoService {

    /**
     * 保存或更新促销信息
     */
    void saveOrUpdateSalesPromotionInfo(SalesPromotionInfoDTO salesPromotionInfoDTO);

    /**
     * 获取促销信息列表
     */
    List<SalesPromotionInfoDTO> getSalesPromotionInfoList(SalesPromotionInfoDTO salesPromotionInfoDTO, WebPage webPage);

    /**
     * 获取促销详情
     */
    SalesPromotionInfoEntity getSalesPromotionInfoById(String salesPromotionInfoId);
    SalesPromotionInfoDTO getSalesPromotionInfoDTOById(String salesPromotionInfoId);

    /**
     * 删除促销信息(逻辑删除)
     */
    void deleteSalesPromotionInfo(String salesPromotionInfoId);
}
