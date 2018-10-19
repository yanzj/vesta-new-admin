package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyHotlineEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 物业服务热线数据持久层
 * Created by WeiYangDong on 2016/12/14.
 */
public interface PropertyHotlineRepository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 删除Entity
     * @param entity
     */
    <E> void delete(E entity);

    /**
     * 获取物业服务热线列表数据
     * @param params 参数
     * @param webPage 分页
     * @return List<PropertyHotlineEntity>
     */
    List<PropertyHotlineEntity> getPropertyHotlineList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过主键ID查询物业服务热线信息
     * @param id 服务热线ID
     * @return PropertyHotlineEntity
     */
    PropertyHotlineEntity getPropertyHotlineById(String id);
}
