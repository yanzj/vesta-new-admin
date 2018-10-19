package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.DefaultConfigDTO;
import com.maxrocky.vesta.application.DTO.admin.UserTypeProjectConfigDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.DefaultConfigEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 默认配置_Service
 * 2016/6/21_Wyd
 */
public interface DefaultConfigService {

    /**
     * 检索默认配置信息
     * @param configType    _配置类型
     * @return DefaultConfigEntity
     */
    DefaultConfigEntity queryDefaultConfig(String configType);

    /**
     * 保存或更新默认配置
     * @param defaultConfigDTO      _默认配置
     */
    void saveOrUpdateDefaultConfig(DefaultConfigDTO defaultConfigDTO);

    /**
     * 获取用户类型可视项目配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getUserShowProjectConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage);

    /**
     * 保存或更新用户可视项目配置
     */
    void saveOrUpdateUserShowProjectConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO);

    /**
     * 获取App项目可视功能模块配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getAppShowFunctionConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage);

    /**
     * 保存或更新App项目可视功能模块配置
     */
    void saveOrUpdateAppShowFunctionConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO);

    /**
     * 获取默认功能模块集合
     * @return  Map<String,Object>
     */
    Map<String,Object> getFunctionModuleMap();

    /**
     * 获取客户端可视项目配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getClientShowProjectConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage);

    /**
     * 保存或更新客户端可视项目配置
     */
    void saveOrUpdateClientShowProjectConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO);

    /**
     * 获取客户端基础配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<ClientConfigEntity> getClientConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO, WebPage webPage);

    /**
     * 保存或更新客户端基础配置
     */
    void saveOrUpdateClientConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO);

    /**
     * 通过主键ID获取客户端基础配置信息
     * @param id 主键
     * @return ClientConfigEntity
     */
    ClientConfigEntity getClientConfigById(String id);

    /**
     * 通过用户类型获取城市项目列表
     * @param userType 用户类型
     * @return ApiResult
     */
    ApiResult getCityProjectByUserType(String userType);

    /**
     * 通过数据权限范围获取客户端基础配置列表
     * @param roleScopeList 数据权限范围
     * @return List<Map<String,Object>>
     */
    List<ClientConfigEntity> getClientConfigList(List<Map<String, Object>> roleScopeList);

}
