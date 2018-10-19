package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.DefaultConfigDTO;
import com.maxrocky.vesta.application.DTO.admin.UserTypeProjectConfigDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.DefaultConfigRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import ma.glasnost.orika.converter.builtin.NumericConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import java.util.*;

/**
 * 默认配置_Service实现类
 * 2016/6/21_Wyd
 */
@Service
public class DefaultConfigServiceImpl implements DefaultConfigService {

    @Autowired
    private DefaultConfigRepository defaultConfigRepository;
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 检索默认配置信息
     * @param configType    _配置类型
     * @return DefaultConfigEntity
     */
    public DefaultConfigEntity queryDefaultConfig(String configType){
        DefaultConfigEntity defaultConfigEntity = defaultConfigRepository.queryDefaultConfig(configType);
        return defaultConfigEntity;
    }

    /**
     * 保存或更新默认配置
     * @param defaultConfigDTO      _默认配置
     */
    public void saveOrUpdateDefaultConfig(DefaultConfigDTO defaultConfigDTO){
        if (null != defaultConfigDTO.getId() && !defaultConfigDTO.getId().equals("")){
            //执行更新
            DefaultConfigEntity defaultConfigEntity = defaultConfigRepository.getDefaultConfig(defaultConfigDTO.getId());
            defaultConfigEntity.setModifyBy(defaultConfigDTO.getModifyBy());
            defaultConfigEntity.setModifyOn(new Date());
            defaultConfigEntity.setConfigValue(defaultConfigDTO.getConfigValue());
            defaultConfigRepository.saveOrUpdate(defaultConfigEntity);
        }else{
            //执行保存
            DefaultConfigEntity defaultConfigEntity = new DefaultConfigEntity();
            defaultConfigEntity.setId(IdGen.uuid());
            defaultConfigEntity.setConfigType(defaultConfigDTO.getConfigType());
            defaultConfigEntity.setConfigValue(defaultConfigDTO.getConfigValue());
            defaultConfigEntity.setCreateOn(new Date());
            defaultConfigEntity.setCreateBy(defaultConfigDTO.getModifyBy());
            defaultConfigEntity.setModifyOn(new Date());
            defaultConfigEntity.setModifyBy(defaultConfigDTO.getModifyBy());
            defaultConfigRepository.saveOrUpdate(defaultConfigEntity);
        }
    }

    /**
     * 获取用户类型可视项目配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getUserShowProjectConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("cityId",userTypeProjectConfigDTO.getCityId());
        paramsMap.put("projectNum",userTypeProjectConfigDTO.getProjectNum());
        //执行查询
        List<Map<String, Object>> mapList = defaultConfigRepository.getUserShowProjectConfigList(paramsMap, webPage);
        return mapList;
    }

    /**
     * 保存或更新用户可视项目配置
     */
    public void saveOrUpdateUserShowProjectConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        if (null != userTypeProjectConfigDTO.getProjectNum() && !"".equals(userTypeProjectConfigDTO.getProjectNum())){
            String projectNum = userTypeProjectConfigDTO.getProjectNum();
            List<UserTypeProjectConfigEntity> userTypeProjectConfigEntities = defaultConfigRepository.getUserTypeProjectConfigByProjectNum(projectNum);
            Map<String,Object> map = null;
            UserTypeProjectConfigEntity userTypeProjectConfigEntity = null;
            if (null == userTypeProjectConfigEntities || userTypeProjectConfigEntities.size() == 0 ){
                //保存
                List<Map<String, Object>> projectInfoList = defaultConfigRepository.getProjectInfoByNum(projectNum);
                if (null != projectInfoList && projectInfoList.size() > 0){
                    Map<String,Object> projectInfo = projectInfoList.get(0);
                    userTypeProjectConfigEntity = new UserTypeProjectConfigEntity();
                    userTypeProjectConfigEntity.setId(IdGen.uuid());            //主键ID
                    userTypeProjectConfigEntity.setCityId(projectInfo.get("cityId").toString());    //城市ID
                    userTypeProjectConfigEntity.setCityNum(projectInfo.get("cityNum").toString());         //城市编码
                    userTypeProjectConfigEntity.setCityName(projectInfo.get("cityName").toString());        //城市名称
                    userTypeProjectConfigEntity.setProjectNum(projectInfo.get("projectNum").toString());      //项目编码
                    userTypeProjectConfigEntity.setProjectName(projectInfo.get("projectName").toString());     //项目名称
                    userTypeProjectConfigEntity.setUserTypes(userTypeProjectConfigDTO.getUserTypes());      //用户类型
                    userTypeProjectConfigEntity.setCreateOn(new Date());
                    userTypeProjectConfigEntity.setCreateBy(userTypeProjectConfigDTO.getModifyBy());
                }
            }else if (userTypeProjectConfigEntities.size() == 1){
                //更新
                userTypeProjectConfigEntity = userTypeProjectConfigEntities.get(0);
                userTypeProjectConfigEntity.setUserTypes(userTypeProjectConfigDTO.getUserTypes());
                userTypeProjectConfigEntity.setModifyOn(new Date());
                userTypeProjectConfigEntity.setModifyBy(userTypeProjectConfigDTO.getModifyBy());
            }
            defaultConfigRepository.saveOrUpdate(userTypeProjectConfigEntity);
        }
    }

    /**
     * 获取App项目可视功能模块配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getAppShowFunctionConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("cityId",userTypeProjectConfigDTO.getCityId());
        paramsMap.put("projectNum",userTypeProjectConfigDTO.getProjectNum());
        //执行查询
        List<Map<String, Object>> mapList = defaultConfigRepository.getAppShowFunctionConfigList(paramsMap, webPage);
        return mapList;
    }

    /**
     * 保存或更新App项目可视功能模块配置
     */
    public void saveOrUpdateAppShowFunctionConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        if (null != userTypeProjectConfigDTO.getProjectNum() && !"".equals(userTypeProjectConfigDTO.getProjectNum())){
            String projectNum = userTypeProjectConfigDTO.getProjectNum();
            List<AppFunctionConfigEntity> appFunctionConfigEntities = defaultConfigRepository.getAppShowFunctionConfigByProjectNum(projectNum);
            Map<String,Object> map = null;
            AppFunctionConfigEntity appFunctionConfigEntity = null;
            if (null == appFunctionConfigEntities || appFunctionConfigEntities.size() == 0 ){
                //保存
                List<Map<String, Object>> projectInfoList = defaultConfigRepository.getProjectInfoByNum(projectNum);
                if (null != projectInfoList && projectInfoList.size() > 0){
                    Map<String,Object> projectInfo = projectInfoList.get(0);
                    appFunctionConfigEntity = new AppFunctionConfigEntity();
                    appFunctionConfigEntity.setId(IdGen.uuid());            //主键ID
                    appFunctionConfigEntity.setCityId(projectInfo.get("cityId").toString());    //城市ID
                    appFunctionConfigEntity.setCityNum(projectInfo.get("cityNum").toString());         //城市编码
                    appFunctionConfigEntity.setCityName(projectInfo.get("cityName").toString());        //城市名称
                    appFunctionConfigEntity.setProjectNum(projectInfo.get("projectNum").toString());      //项目编码
                    appFunctionConfigEntity.setProjectName(projectInfo.get("projectName").toString());     //项目名称
                    appFunctionConfigEntity.setFunctionModules(userTypeProjectConfigDTO.getFunctionModules());      //功能模块
                    appFunctionConfigEntity.setCreateOn(new Date());
                    appFunctionConfigEntity.setCreateBy(userTypeProjectConfigDTO.getModifyBy());
                }
            }else if (appFunctionConfigEntities.size() == 1){
                //更新
                appFunctionConfigEntity = appFunctionConfigEntities.get(0);
                appFunctionConfigEntity.setFunctionModules(userTypeProjectConfigDTO.getFunctionModules());
                appFunctionConfigEntity.setModifyOn(new Date());
                appFunctionConfigEntity.setModifyBy(userTypeProjectConfigDTO.getModifyBy());
            }
            defaultConfigRepository.saveOrUpdate(appFunctionConfigEntity);
        }
    }

    /**
     * 获取默认功能模块集合
     * @return  Map<String,Object>
     */
    public Map<String,Object> getFunctionModuleMap(){
        AppFunctionConfigEntity.setFunctionModuleMap();
        return AppFunctionConfigEntity.getFunctionModuleMap();
    }

    /**
     * 获取客户端可视项目配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getClientShowProjectConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("cityId",userTypeProjectConfigDTO.getCityId());
        paramsMap.put("projectNum",userTypeProjectConfigDTO.getProjectNum());
        //执行查询
        List<Map<String, Object>> mapList = defaultConfigRepository.getClientShowProjectConfigList(paramsMap, webPage);
        return mapList;
    }

    /**
     * 保存或更新客户端可视项目配置
     */
    public void saveOrUpdateClientShowProjectConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        if (null != userTypeProjectConfigDTO.getProjectNum() && !"".equals(userTypeProjectConfigDTO.getProjectNum())){
            String projectNum = userTypeProjectConfigDTO.getProjectNum();
            List<ClientProjectConfigEntity> clientProjectConfigEntities = defaultConfigRepository.getClientShowProjectConfigByProjectNum(projectNum);
            Map<String,Object> map = null;
            ClientProjectConfigEntity clientProjectConfigEntity = null;
            if (null == clientProjectConfigEntities || clientProjectConfigEntities.size() == 0 ){
                //保存
                List<Map<String, Object>> projectInfoList = defaultConfigRepository.getProjectInfoByNum(projectNum);
                if (null != projectInfoList && projectInfoList.size() > 0){
                    Map<String,Object> projectInfo = projectInfoList.get(0);
                    clientProjectConfigEntity = new ClientProjectConfigEntity();
                    clientProjectConfigEntity.setId(IdGen.uuid());            //主键ID
                    clientProjectConfigEntity.setCityId(projectInfo.get("cityId").toString());    //城市ID
                    clientProjectConfigEntity.setCityNum(projectInfo.get("cityNum").toString());         //城市编码
                    clientProjectConfigEntity.setCityName(projectInfo.get("cityName").toString());        //城市名称
                    clientProjectConfigEntity.setProjectNum(projectInfo.get("projectNum").toString());      //项目编码
                    clientProjectConfigEntity.setProjectName(projectInfo.get("projectName").toString());     //项目名称
                    clientProjectConfigEntity.setClientIds(userTypeProjectConfigDTO.getClientIds());      //客户端ID
                    clientProjectConfigEntity.setCreateOn(new Date());
                    clientProjectConfigEntity.setCreateBy(userTypeProjectConfigDTO.getModifyBy());
                }
            }else if (clientProjectConfigEntities.size() == 1){
                //更新
                clientProjectConfigEntity = clientProjectConfigEntities.get(0);
                clientProjectConfigEntity.setClientIds(userTypeProjectConfigDTO.getClientIds());
                clientProjectConfigEntity.setModifyOn(new Date());
                clientProjectConfigEntity.setModifyBy(userTypeProjectConfigDTO.getModifyBy());
            }
            defaultConfigRepository.saveOrUpdate(clientProjectConfigEntity);
        }
    }

    /**
     * 获取客户端基础配置列表
     * @param userTypeProjectConfigDTO 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<ClientConfigEntity> getClientConfigList(UserTypeProjectConfigDTO userTypeProjectConfigDTO,WebPage webPage){
        //执行查询
        List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(null, webPage);
        return clientConfigList;
    }

    /**
     * 保存或更新客户端基础配置
     */
    public void saveOrUpdateClientConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        ClientConfigEntity clientConfigEntity = null;
        if (null != userTypeProjectConfigDTO.getId() && !"".equals(userTypeProjectConfigDTO.getId())){
            //更新
            clientConfigEntity = defaultConfigRepository.getClientConfigById(userTypeProjectConfigDTO.getId());
        }else{
            //保存
            clientConfigEntity = new ClientConfigEntity();
        }
        clientConfigEntity.setClientName(userTypeProjectConfigDTO.getClientName());     //客户端名称
        clientConfigEntity.setWeChatAppId(userTypeProjectConfigDTO.getWeChatAppId());   //客户端编码(微信AppId)
        clientConfigEntity.setWeChatAppSecret(userTypeProjectConfigDTO.getWeChatAppSecret());   //客户端编码(微信AppSecret)
        defaultConfigRepository.saveOrUpdate(clientConfigEntity);
        }

    /**
     * 通过主键ID获取客户端基础配置信息
     * @param id 主键
     * @return ClientConfigEntity
     */
    public ClientConfigEntity getClientConfigById(String id){
        return defaultConfigRepository.getClientConfigById(id);
    }

    /**
     * 通过用户类型获取城市项目列表
     * @param userType 用户类型
     * @return ApiResult
     */
    public ApiResult getCityProjectByUserType(String userType){
        List<Map<String, Object>> cityList = defaultConfigRepository.getCityListByUserType(userType);
        for (int i = 0,length = cityList.size(); i < length; i++){
            Map<String, Object> cityInfo = cityList.get(i);
            String cityId = cityInfo.get("cityId").toString();
            List<Map<String, Object>> projectList = defaultConfigRepository.getProjectListByCityAndUserType(cityId, userType);
            cityInfo.put("projectList",projectList);
        }
        return new SuccessApiResult(cityList);
    }

    /**
     * 通过数据权限范围获取客户端基础配置列表
     * @param roleScopeList 数据权限范围
     * @return List<Map<String,Object>>
     */
    public List<ClientConfigEntity> getClientConfigList(List<Map<String, Object>> roleScopeList){
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }
        Set<Integer> ids = new HashSet<>();
        //通过项目编码集合获取客户端可视项目配置
        List<ClientProjectConfigEntity> projectConfigEntityList = defaultConfigRepository.getClientShowProjectConfigByProjectNums(roleScopes);
        for (ClientProjectConfigEntity clientProjectConfigEntity : projectConfigEntityList){
            if (null != clientProjectConfigEntity.getClientIds() && !"".equals(clientProjectConfigEntity.getClientIds())){
                String clientIds = clientProjectConfigEntity.getClientIds();
                String[] split = clientIds.split(",");
                for (int i=0,length=split.length;i<length;i++){
                    if (null != split[i] && !"".equals(split[i])){
                        ids.add(Integer.valueOf(split[i]));
                    }
                }
            }
        }
        //执行查询
        List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(ids);
        return clientConfigList;
    }
}
