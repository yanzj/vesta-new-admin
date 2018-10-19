package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyPriceSettingEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/16.
 *  物业管理 维修价格设置 持久层接口
 */
public interface PropertyPriceSettingRepository {

    /**
     * 根据当前登录人所负责项目ID查询列表
     * @param propertyPrice
     * @return
     */
    List<PropertyPriceSettingEntity> queryPriceSettingByProjectId(PropertyPriceSettingEntity propertyPrice,WebPage webPage);

    /**
     * 根据项目ID查询是否有项目名称及内容
     * @param project
     * @return
     */
    PropertyPriceSettingEntity queryPriceSetting(String  project);

    /**
     * 更新维修价格信息
     * @return
     */
    boolean updatePropertyPriceSetting(PropertyPriceSettingEntity priceSettingEntity);

    /**
     * 添加维修价格信息
     * @param priceSettingEntity
     * @return
     */
    boolean savePropertyPriceSetting(PropertyPriceSettingEntity priceSettingEntity);

    /**
     * 根据维修价格ID查询信息
     * @param priceSettingId
     * @return
     */
    PropertyPriceSettingEntity queryPriceSettingById(String priceSettingId);

    /**
     * 根据维修价格ID 删除信息
     * @param priceSettingEntity
     * @return
     */
    boolean delPropertyPriceSetting(PropertyPriceSettingEntity priceSettingEntity);
}
