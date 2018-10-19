package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.OfflineActivityEntity;
import com.maxrocky.vesta.domain.model.OfflineActivitySignEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 线下活动数据持久层
 * Created by WeiYangDong on 2017/8/21.
 */
public interface OfflineActivityRespository {

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
     * 获取线下活动列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<OfflineActivityEntity>
     */
    List<OfflineActivityEntity> getOfflineActivityList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 通过主键ID获取线下活动
     * @param activityId 活动主键ID
     * @return OfflineActivityEntity
     */
    OfflineActivityEntity getOfflineActivityById(String activityId);

    /**
     * 获取活动签到记录列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getOfflineActivitySignList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 通过活动ID删除活动签到记录
     * @param activityId 活动ID
     */
    void delActivitySignByActivity(String activityId);

    /**
     * 通过活动ID获取活动签到记录列表
     * @param activityId 活动
     * @return List<OfflineActivitySignEntity>
     */
    List<OfflineActivitySignEntity> getOfflineActivitySignByActivity(String activityId);

    /**
     * 通过活动ID和联系电话获取活动签到信息
     * @param activityId 活动ID
     * @param mobile 联系电话
     * @return List<OfflineActivitySignEntity>
     */
    List<OfflineActivitySignEntity> getOfflineActivitySignByActivityAndMobile(String activityId,String mobile);
}
