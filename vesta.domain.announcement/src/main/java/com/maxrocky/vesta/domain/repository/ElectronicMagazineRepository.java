package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ElectronicMagazineEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 电子杂志数据持久层
 * Created by WeiYangDong on 2017/9/25.
 */
public interface ElectronicMagazineRepository {

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
     * 通过杂志ID获取电子杂志详情
     * @param id 杂志ID
     * @return ElectronicMagazineEntity
     */
    ElectronicMagazineEntity getElectronicMagazineById(String id);

    /**
     * 获取电子杂志列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<ElectronicMagazineEntity>
     */
    List<ElectronicMagazineEntity> getElectronicMagazineList(Map<String,Object> paramsMap, WebPage webPage);
}
