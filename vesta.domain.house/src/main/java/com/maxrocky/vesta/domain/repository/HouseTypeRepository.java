package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseTypeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/2.
 */
public interface HouseTypeRepository {

    /**
     *查询户型信息List
     * @param map
     * @param webPage
     * @return
     */
    public List<Object[]> getHouseTypeList(Map map,WebPage webPage);

    /**
     * 保存户型信息
     * @param houseTypeEntity
     */
    public void saveHouseType(HouseTypeEntity houseTypeEntity);

    /**
     * 修改
     * @param houseTypeEntity
     */
    public void updateHouseType(HouseTypeEntity houseTypeEntity);

    /**
     * 删除
     * @param houseTypeEntity
     */
    public void deleteHouseType(HouseTypeEntity houseTypeEntity);

    public HouseTypeEntity getHouseTypeById(String id);

    public List<HouseTypeEntity> getAllHouseType();
    List<HouseTypeEntity> getAllHouseType(String houseType,WebPage webPage);
    /**
     * 根据修改时间查询
     * @param operateDate
     * @param id
     * @return
     */
    List<HouseTypeEntity> getByOperateDate(Date operateDate,String id);

    /**
     * 根据修改时间查询
     * @param operateDate
     * @param id
     * @return
     */
    List<HouseTypeEntity> getByOperateDate(String operateDate,String id,String projectNum);

    List<String> getProjectNamesByHouseType(String houseTypeId);

}
