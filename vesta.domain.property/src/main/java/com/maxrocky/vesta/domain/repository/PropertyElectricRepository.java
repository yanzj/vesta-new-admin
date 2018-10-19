package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyElectricEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/2/23.
 */
public interface PropertyElectricRepository {

    /**
     * 分页查询电量列表
     * @param propertyElectricEntity
     * @param webPage
     * @return
     */
    public List<PropertyElectricEntity> listElectric(PropertyElectricEntity propertyElectricEntity,WebPage webPage);

    /**
     * 修改电量信息
     * @param propertyElectricEntity
     * @return
     */
    public boolean updateElectric(PropertyElectricEntity propertyElectricEntity);

    /**
     * 保存电量信息
     * @param propertyElectricEntity
     * @return
     */
    public boolean saveElectric(PropertyElectricEntity propertyElectricEntity);

    /**
     * 查询电量详情
     * @param eleId
     * @return
     */
    public PropertyElectricEntity getElectricByid(String eleId);

    /****
     * 根据房屋id集合查询 电量集合
     * @param s
     * @return
     */
    List<PropertyElectricEntity> getPropertyEletricEnitiesByHouseIds(String houseIds);
}
