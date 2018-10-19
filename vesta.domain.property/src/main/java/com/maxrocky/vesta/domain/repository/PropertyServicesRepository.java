package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务 持久层接口
 */
public interface PropertyServicesRepository {

    /**
     * 初始化物业服务信息
     * @param propertyServicesEntity
     * @param webPage
     * @return
     */
    List<PropertyServicesEntity>  queryPropertyServicesMessage(PropertyServicesEntity propertyServicesEntity, WebPage webPage);

    /**
     * 根据ID查询物业服务信息
     * @param servicesId
     * @return
     */
    List<PropertyServicesEntity>  queryPropertyServicesById(String servicesId);

    /**
     * 根据服务信息ID删除信息
     * @param servicesId
     * @return
     */
    boolean removePropertyServicesById(String servicesId);

    /**
     * 新增服务信息
     * @param propertyServicesEntity
     */
    void addPropertyServices(PropertyServicesEntity propertyServicesEntity);

    /**
     * 根据服务信息ID查询信息
     * @param servicesId
     * @return
     */
    PropertyServicesEntity getPropertyServicesById(String servicesId);

    /**
     * 更新服务信息
     * @param propertyServicesEntity
     */
    void modifyPropertyServices(PropertyServicesEntity propertyServicesEntity);
}
