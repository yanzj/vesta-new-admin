package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.VisitorPassEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 访客模块Repository
 * Created by WeiYangDong on 2017/12/18.
 */
public interface VisitorRepository {

    /**
     * 保存或更新Entity
     * @param entity 实体类
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 删除Entity
     * @param entity 实体类
     */
    <E> void delete(E entity);

    /**
     * 获取访客通行列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<VisitorPassEntity>
     */
    List<VisitorPassEntity> getVisitorPassList(Map<String, Object> paramsMap, WebPage webPage);

    /**
     * 通过主键ID获取访客通行信息
     * @param id 主键ID
     * @return VisitorPassEntity
     */
    VisitorPassEntity getVisitorPassById(String id);

    /**
     * 获取访客通行日志列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Object[]>
     */
    List<Object[]> getVisitorPassLogList(Map<String,Object> paramsMap, WebPage webPage);
}
