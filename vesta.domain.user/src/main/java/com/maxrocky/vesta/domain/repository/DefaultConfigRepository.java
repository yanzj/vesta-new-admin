package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 默认配置_持久层
 * 2016/6/20_Wyd
 */
public interface DefaultConfigRepository {

    /**
     * 检索默认配置
     */
    DefaultConfigEntity queryDefaultConfig(String configType);

    /**
     * 保存或更新
     * @param defaultConfigEntity
     */
//    void saveOrUpdate(DefaultConfigEntity defaultConfigEntity);
    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 通过主键ID获取默认配置信息
     * @param id   _主键ID
     * @return DefaultConfigEntity
     */
    DefaultConfigEntity getDefaultConfig(String id);

    /**
     * 获取用户类型可视项目配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getUserShowProjectConfigList(Map<String,Object> paramsMap,WebPage webPage);

    /**
     * 通过项目编码获取用户类型可视项目配置
     * @param projectNum 项目编码
     * @return List<UserTypeProjectConfigEntity>
     */
    List<UserTypeProjectConfigEntity> getUserTypeProjectConfigByProjectNum(String projectNum);

    /**
     * 通过项目编码获取客户端可视项目配置
     * @param roleScopes 项目编码集合
     * @return List<ClientProjectConfigEntity>
     */
    List<ClientProjectConfigEntity> getClientShowProjectConfigByProjectNums(String roleScopes);

    /**
     * 通过项目编码获取项目详情
     * @param projectNum 项目编码
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getProjectInfoByNum(String projectNum);

    /**
     * 通过用户类型获取城市列表
     * @param userType 用户类型
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getCityListByUserType(String userType);

    /**
     * 通过城市和用户类型获取项目列表
     * @param cityId 城市ID
     * @param userType 用户类型
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getProjectListByCityAndUserType(String cityId,String userType);

    /**
     * 获取App项目可视功能模块配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getAppShowFunctionConfigList(Map<String,Object> paramsMap,WebPage webPage);

    /**
     * 通过项目编码获取App项目可视功能模块配置
     * @param projectNum 项目编码
     * @return List<UserTypeProjectConfigEntity>
     */
    List<AppFunctionConfigEntity> getAppShowFunctionConfigByProjectNum(String projectNum);

    /**
     * 获取客户端可视项目配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getClientShowProjectConfigList(Map<String,Object> paramsMap,WebPage webPage);

    /**
     * 通过项目编码获取客户端可视项目配置
     * @param projectNum 项目编码
     * @return List<ClientProjectConfigEntity>
     */
    List<ClientProjectConfigEntity> getClientShowProjectConfigByProjectNum(String projectNum);

    /**
     * 获取客户端基础配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<ClientConfigEntity> getClientConfigList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 通过客户端ID集合获取客户端基础配置列表
     * @param ids 客户端ID集合
     * @return List<Map<String,Object>>
     */
    List<ClientConfigEntity> getClientConfigList(Set<Integer> ids);

    /**
     * 通过主键ID获取客户端基础配置信息
     * @param id 主键
     * @return ClientConfigEntity
     */
    ClientConfigEntity getClientConfigById(String id);


    ClientConfigEntity getClientConfigByAppId(String appId);

}
