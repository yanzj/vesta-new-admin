package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.PropertyTypeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;


import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
public interface PropertyTypesRepository {
    /**
     * 初始化公告类型信息
     * @param page
     * @return
     */
    List<PropertyTypeEntity> queryPropertyTypeMessage(PropertyTypeEntity propertyTypeEntity, WebPage page);


    /**
     * 根据服务信息ID删除信息
     * @param typeId
     * @return
     */
    boolean removePropertyTypeById(String typeId);

    /**
     * 新增服务信息
     * @param
     */
    void addPropertyType(PropertyTypeEntity propertyTypeEntity);

    /**
     *
     * @param typeId
     * @return
     */
     PropertyTypeEntity getPropertyTypeById(String typeId);

    /**
     * 更新广告类型
     * @param propertyTypeEntity
     */
    void modifyPropertyType(PropertyTypeEntity propertyTypeEntity);
}
