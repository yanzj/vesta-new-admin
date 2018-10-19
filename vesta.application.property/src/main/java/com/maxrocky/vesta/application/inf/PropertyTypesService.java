package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyTypesDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;


import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
public interface PropertyTypesService {

    /**
     * 初始化公告类型列表
     * @param propertyTypeDTO
     * @param page
     * @return
     */
    List<PropertyTypesDTO> queryPropertyTypeMessage(PropertyTypesDTO propertyTypeDTO,WebPage page);

    /**
     * 根据ID删除公告类型信息
     * @param typeId
     * @return
     */
    String removePropertyTypeById(UserPropertyStaffEntity userPropertystaffEntity,String typeId);

    /**
     * 新增公告类型信息
     * @param propertyTypeDTO
     */
    void addPropertyTypes(UserPropertyStaffEntity userPropertystaffEntit,PropertyTypesDTO propertyTypeDTO);

    /**
     * 根据公告类型ID查询详情
     * @param typeId
     * @return
     */
    PropertyTypesDTO queryPropertyTypesById(String typeId);
}