package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyServicesDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务模块 业务逻辑层接口
 */
public interface PropertyServicesService {

    /**
     * 初始化物业服务信息类型
     * @return
     */
    PropertyServicesDTO queryPropertyServicesType();

    /**
     * 初始化物业服务信息列表
     * @param propertyServicesDTO
     * @param webPage
     * @return
     */
    List<PropertyServicesDTO>  queryPropertyServicesMessage(PropertyServicesDTO propertyServicesDTO,WebPage webPage);

    /**
     * 根据ID删除服务信息
     * @param servicesId
     * @return
     */
    String removePropertyServicesById(String servicesId,UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 新增服务信息
     * @param userPropertystaffEntit
     * @param propertyServicesDTO
     */
    void addPropertyServices(UserPropertyStaffEntity userPropertystaffEntit,PropertyServicesDTO propertyServicesDTO);

    /**
     * 根据服务信息ID查询详情
     * @param servicesId
     * @return
     */
    PropertyServicesDTO queryPropertyServicesById(String servicesId);
}
