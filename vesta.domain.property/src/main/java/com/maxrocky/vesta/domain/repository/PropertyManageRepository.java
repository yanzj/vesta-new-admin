package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyOwnerEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 产权管理数据持久层
 * Created by WeiYangDong on 2017/6/22.
 */
public interface PropertyManageRepository {

    <T> void saveOrUpdate(T entity);

    <E> void delete(E entity);

    /**
     * 获取产权管理列表
     * @param params 参数
     * @param webPage 分页
     * @return List<PropertyEntity>
     */
    List<PropertyOwnerEntity> getPropertyManageList(Map<String,Object> params, WebPage webPage);

    /**
     * 通过房产编码获取产权信息
     * @param roomNum 房屋编码
     * @return List<PropertyOwnerEntity>
     */
    List<PropertyOwnerEntity> getPropertyManageByRoomNum(String roomNum);

    /**
     * 根据项目及地块编码获取房产七级数据列表
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getHouseDataByProject(Map<String,Object> params);

    /**
     * 获取产权推送短信数据列表
     */
    List<PropertyOwnerEntity> getPropertyManageListByMes();
}
