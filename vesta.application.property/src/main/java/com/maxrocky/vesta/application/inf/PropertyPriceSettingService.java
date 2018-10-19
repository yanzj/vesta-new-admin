package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyPriceSettingDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/16.
 * 物业管理 维修价格设置 业务逻辑层接口
 */
public interface PropertyPriceSettingService {

    /**
     * 初始化维修价格
     * 根据当前登录人所负责项目ID
     * @param userPropertystaffEntity
     * @param propertyPriceSetting
     * @return
     */
    List<PropertyPriceSettingDTO> queryPriceSettingByProjectId(UserPropertyStaffEntity userPropertystaffEntity,PropertyPriceSettingDTO propertyPriceSetting,WebPage webPage);

    /**
     * 新增价格 或修改价格页面
     * @param propertyPriceSetting
     * @param userPropertystaff
     * @return
     */
    PropertyPriceSettingDTO initializePriceSetting(PropertyPriceSettingDTO propertyPriceSetting,UserPropertyStaffEntity userPropertystaff);

    /**
     * 新增或修改维修价格信息
     * @param propertyPriceSetting
     * @param userProperty
     * @return
     */
    String modifyPriceSetting(PropertyPriceSettingDTO propertyPriceSetting,UserPropertyStaffEntity userProperty);

    /**
     * 根据维修价格ID删除信息
     * @param priceSettingId
     * @param userPropertystaffs
     * @return
     */
    String removePropertyConsultById(String priceSettingId,UserPropertyStaffEntity userPropertystaffs);
}
