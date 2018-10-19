package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.EngineeringProgressEntity;
import com.maxrocky.vesta.domain.model.EngineeringProjectEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 家书管理-数据持久层
 * Created by WeiYangDong on 2017/5/17.
 */
public interface HomeLetterRespository {

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
     * 获取工程进展列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getEngineeringProgressList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 获取工程进展详情
     * @param engineeringProgressId 主键ID
     * @return EngineeringProgressEntity
     */
    EngineeringProgressEntity getEngineeringProgressById(String engineeringProgressId);

    /**
     * 获取工程项目列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<EngineeringProjectEntity> getEngineeringProjectList(Map<String,Object> paramsMap, WebPage webPage);
}
