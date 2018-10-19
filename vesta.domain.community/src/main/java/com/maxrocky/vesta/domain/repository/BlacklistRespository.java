package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.BlacklistEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 黑名单功能模块数据持久层
 * Created by WeiYangDong on 2017/11/21.
 */
public interface BlacklistRespository {

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
     * 获取黑名单列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<BlacklistEntity>
     */
    List<BlacklistEntity> getBlacklistList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 通过主键ID获取黑名单信息
     * @param id 主键ID
     * @return BlacklistEntity
     */
    BlacklistEntity getBlacklistById(String id);

    /**
     * 检验黑名单名称唯一性
     * @param id 黑名单ID
     * @param name 黑名单名称
     * @return List<BlacklistEntity>
     */
    List<BlacklistEntity> checkBlacklistName(String id,String name);
}
