package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyHotlineDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.PropertyHotlineEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 物业服务热线Service
 * Created by WeiYangDong on 2016/12/14.
 */
public interface PropertyHotlineService {

    /**
     * 获取物业服务热线列表数据
     * @param propertyHotlineDTO 参数
     * @param webPage 分页
     * @return List<PropertyHotlineEntity>
     */
    List<PropertyHotlineEntity> getPropertyHotlineList(PropertyHotlineDTO propertyHotlineDTO,WebPage webPage);

    /**
     * 通过主键ID查询物业服务热线信息
     * @param id 服务热线ID
     * @return PropertyHotlineEntity
     */
    PropertyHotlineEntity getPropertyHotlineById(String id);

    /**
     * 保存或更新物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    void saveOrUpdatePropertyHotline(PropertyHotlineDTO propertyHotlineDTO);

    /**
     * 删除物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    void deletePropertyHotline(PropertyHotlineDTO propertyHotlineDTO);




    /**
     * 通过项目和功能模块检索服务热线信息
     * @param projectCode   项目名称
     * @param functionModuleCode    功能模块名称
     * @return  ApiResult
     */
    List<Map<String,Object>> getPropertyHotlineByProjectAndModule(String projectCode,String functionModuleCode);

}
