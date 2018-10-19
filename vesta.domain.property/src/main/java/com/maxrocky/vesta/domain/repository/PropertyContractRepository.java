package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyContractEntity;
import com.maxrocky.vesta.domain.model.PropertyOwnerEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/8 16:56
 * @deprecated 合同查询模块Dao
 */
public interface PropertyContractRepository {

    <T> void saveOrUpdate(T entity);

    <E> void delete(E entity);

    /**
     * 获取合同管理列表
     * @param params 参数
     * @param webPage 分页
     * @return List<PropertyContractEntity>
     */
    List<PropertyContractEntity> getPropertyContractList(Map<String,Object> params, WebPage webPage);

    /**
     * 通过房产编码获取合同信息
     * @param roomNum 房屋编码
     * @return List<PropertyContractEntity>
     */
    List<PropertyContractEntity> getPropertyContractByRoomNum(String roomNum,String propertyType);
}
