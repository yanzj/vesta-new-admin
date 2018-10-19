package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.BusinessBulletinEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 商业公告数据持久层
 * Created by WeiYangDong on 2017/9/18.
 */
public interface BusinessBulletinRepository {

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
     * 通过公告ID获取商业公告详情
     * @param id 公告ID
     * @return BusinessBulletinEntity
     */
    BusinessBulletinEntity getBusinessBulletinById(String id);

    /**
     * 获取商业公告列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<BusinessBulletinEntity>
     */
    List<BusinessBulletinEntity> getBusinessBulletinList(Map<String,Object> paramsMap, WebPage webPage);
}
