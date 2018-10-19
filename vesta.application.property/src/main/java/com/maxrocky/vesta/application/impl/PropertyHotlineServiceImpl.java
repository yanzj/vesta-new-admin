package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyHotlineDTO;
import com.maxrocky.vesta.application.inf.PropertyHotlineService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PropertyHotlineEntity;
import com.maxrocky.vesta.domain.repository.PropertyHotlineRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 物业服务热线Service实现类
 * Created by WeiYangDong on 2016/12/14.
 */
@Service
public class PropertyHotlineServiceImpl implements PropertyHotlineService{
    @Autowired
    private PropertyHotlineRepository propertyHotlineRepository;
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取物业服务热线列表数据
     * @param propertyHotlineDTO 参数
     * @param webPage 分页
     * @return List<PropertyHotlineEntity>
     */
    public List<PropertyHotlineEntity> getPropertyHotlineList(PropertyHotlineDTO propertyHotlineDTO,WebPage webPage){
        //封装参数
        Map<String,Object> params = new HashMap<>();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = propertyHotlineDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        params.put("roleScopes",roleScopes);        //权限范围
        if (null != propertyHotlineDTO.getScopeId() && !"".equals(propertyHotlineDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(propertyHotlineDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
        }
        params.put("projectCode",propertyHotlineDTO.getProjectCode());     //项目编码
        params.put("functionModuleCode",propertyHotlineDTO.getFunctionModuleCode());    //功能模块编码
        params.put("hotline",propertyHotlineDTO.getHotline());      //服务热线电话
        //执行查询
        List<PropertyHotlineEntity> propertyHotlineList = propertyHotlineRepository.getPropertyHotlineList(params, webPage);
        return propertyHotlineList;
    }

    /**
     * 通过主键ID查询物业服务热线信息
     * @param id 服务热线ID
     * @return PropertyHotlineEntity
     */
    public PropertyHotlineEntity getPropertyHotlineById(String id){
        return propertyHotlineRepository.getPropertyHotlineById(id);
    }

    /**
     * 保存或更新物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    public void saveOrUpdatePropertyHotline(PropertyHotlineDTO propertyHotlineDTO){
        String hotlineId = propertyHotlineDTO.getHotlineId();
        PropertyHotlineEntity propertyHotlineEntity = null;
        if (null != hotlineId && !"".equals(hotlineId)){
            //执行更新
            propertyHotlineEntity = getPropertyHotlineById(hotlineId);
            propertyHotlineEntity.setCityId(propertyHotlineDTO.getCityId());
            propertyHotlineEntity.setCityName(propertyHotlineDTO.getCityName());
            propertyHotlineEntity.setProjectCode(propertyHotlineDTO.getProjectCode());
            propertyHotlineEntity.setProjectName(propertyHotlineDTO.getProjectName());
            propertyHotlineEntity.setFunctionModuleCode(propertyHotlineDTO.getFunctionModuleCode());
            propertyHotlineEntity.setFunctionModuleName(propertyHotlineDTO.getFunctionModuleName());
            propertyHotlineEntity.setHotline(propertyHotlineDTO.getHotline());
            propertyHotlineEntity.setModifyOn(new Date());
            propertyHotlineEntity.setModifyBy(propertyHotlineDTO.getModifyBy());
        }else{
            //执行新增
            propertyHotlineEntity = new PropertyHotlineEntity();
            propertyHotlineEntity.setId(IdGen.uuid());
            propertyHotlineEntity.setCityId(propertyHotlineDTO.getCityId());
            propertyHotlineEntity.setCityName(propertyHotlineDTO.getCityName());
            propertyHotlineEntity.setProjectCode(propertyHotlineDTO.getProjectCode());
            propertyHotlineEntity.setProjectName(propertyHotlineDTO.getProjectName());
            propertyHotlineEntity.setFunctionModuleCode(propertyHotlineDTO.getFunctionModuleCode());
            propertyHotlineEntity.setFunctionModuleName(propertyHotlineDTO.getFunctionModuleName());
            propertyHotlineEntity.setHotline(propertyHotlineDTO.getHotline());
            propertyHotlineEntity.setCreateBy(propertyHotlineDTO.getModifyBy());
            propertyHotlineEntity.setCreateOn(new Date());
        }
        propertyHotlineRepository.saveOrUpdate(propertyHotlineEntity);
    }

    /**
     * 删除物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    public void deletePropertyHotline(PropertyHotlineDTO propertyHotlineDTO){
        String hotlineId = propertyHotlineDTO.getHotlineId();
        if (null != hotlineId && !"".equals(hotlineId)){
            PropertyHotlineEntity hotlineEntity = propertyHotlineRepository.getPropertyHotlineById(propertyHotlineDTO.getHotlineId());
            propertyHotlineRepository.delete(hotlineEntity);
        }
    }


    /**
     * 通过项目和功能模块检索服务热线信息
     * @param projectCode   项目名称
     * @param functionModuleCode    功能模块名称
     * @return  ApiResult
     */
    public List<Map<String,Object>> getPropertyHotlineByProjectAndModule(String projectCode,String functionModuleCode){
        //封装参数
        Map<String,Object> params = new HashMap<>();
        params.put("projectCode",projectCode);
        params.put("functionModuleCode",functionModuleCode);
        //执行查询
        List<PropertyHotlineEntity> hotlineEntityList = propertyHotlineRepository.getPropertyHotlineList(params, null);
        //封装结果
        List<Map<String,Object>> resultList = null;
        Map<String,Object> map = null;
        PropertyHotlineEntity propertyHotlineEntity = null;
        if (null != hotlineEntityList && hotlineEntityList.size() > 0){
            resultList = new ArrayList<>();
            for (int i=0,length=hotlineEntityList.size(); i<length; i++){
                propertyHotlineEntity = hotlineEntityList.get(i);
                map = new HashMap<>();
                map.put("hotline",propertyHotlineEntity.getHotline());
                resultList.add(map);
            }
        }
        return resultList;
    }
}
