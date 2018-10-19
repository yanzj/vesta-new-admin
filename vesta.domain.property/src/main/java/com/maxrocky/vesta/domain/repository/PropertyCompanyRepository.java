package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/17.
 * 物业项目公司 持久层接口
 */
public interface PropertyCompanyRepository {

    /**
     * 初始化物业管理公司列表
     * @return
     */
    List<PropertyCompanyEntity> queryPropertyCompanyMessage(PropertyCompanyEntity propertyCompanyEntity,WebPage webPage);

    /**
     * 区域列表
     */
    List<HouseAreaEntity> queryHouseAreaEntity(String id);

    /**
     * 公司列表
     * @return
     */
    List<HouseCompanyEntity> queryHouseCompanyEntity(String id);

    /**
     * 项目列表
     * @return
     */
    List<HouseProjectEntity> queryHouseProjectEntity(String id);

    /**
     * 公告类型
     * @param type
     * @param typeId
     * @return
     */
    List<PropertyTypeEntity> queryPropertyTypeEntity(String type,String typeId);

    /**
     * 判断w物业公司权限和关系是否存在
     * @param setId
     * @return
     */
    PropertyCompanyRoleMapEntity getRolesetMap(String setId,String roleId);

    /**
     * 根据公司ID查询 公司权限表
     * @param companyId
     * @return
     */
    List<PropertyCompanyRoleMapEntity> queryPropertyCompanyRoleMap(String companyId);

    /**
     * 根据公司ID 删除公司权限表
     * @param propertyCompanyRoleMaps
     */
    void delPropertyCompanyRoleMap(PropertyCompanyRoleMapEntity propertyCompanyRoleMaps);

    /**
     * 添加物业公司信息
     * @param propertyCompany
     */
    void addPropertyCompanyEntity(PropertyCompanyEntity propertyCompany);

    /**
     * 添加物业公司权限信息
     */
    void savePropertyCompanyRoleMapEntity(PropertyCompanyRoleMapEntity propertyCompanyRoleMap);

    /**
     * 更新物业公司信息
     * @param propertyCompany
     */
    void updatePropertyCompanyEntity(PropertyCompanyEntity propertyCompany);

    /**
     * 根据物业公司ID查询信息详情
     * @param companyId
     * @return
     */
    PropertyCompanyEntity queryPropertyCompanyById(String companyId);

    /**
     * 根据物业公司ID 删除物业公司信息
     * @param propertyCompanyEntity
     * @return
     */
    void delPropertyCompanyEntity(PropertyCompanyEntity propertyCompanyEntity);

    /**
     * 联动 根据区域ID查询公司信息
     * @return
     */
    List<HouseCompanyEntity> getHouseCompany(String companyName);

    /**
     * 联动 根据公司ID查询项目信息
     * @param pojectName
     * @return
     */
    List<HouseProjectEntity> getHouseProject(String pojectName);


}
