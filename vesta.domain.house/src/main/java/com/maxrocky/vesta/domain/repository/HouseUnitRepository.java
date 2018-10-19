package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseUnitEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/18 11:41.
 * Describe:单元Repository接口
 */
public interface HouseUnitRepository {

    /**
     * Describe:根据楼Id获取单元列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:44:08
     */
    List<HouseUnitEntity> getListByBuildingId(String buildingId);

    /**
     * Describe:根据单元Id获取单元
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:16:53
     */
    HouseUnitEntity get(String unitId);

    /**
     * Describe:根据楼Id、单元号获取单元
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:17:52
     */
    HouseUnitEntity getByUnitNameAndBuildingId(String unitName, String buildingId);

    /**
     * 获取单元下拉框
     * @param buildId
     * @return
     */
    List<HouseUnitEntity> mapUnit(String buildId);

    /**
     * Describe:根据楼Id、单元号获取单元
     * CreateBy:langamfeng
     * CreateOn:2016-05-07 23:38
     */
    HouseUnitEntity getInfoByUnitId(String unitId);

    /**
     * Describe:更新单元信息
     * CreatedBy:langmafeng
     * Describe:2016-05-07 23:50
     *
     *
     */
    void updateUnitInfo(HouseUnitEntity houseUnitEntity);

    /**
     * Describe:创建单元信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-07  23:50
     */
    void create(HouseUnitEntity houseUnitEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除
     * ModifyBy:
     */
    void delete();

    /**
     * Describe:根据楼编码获取单元列表
     */
    List<HouseUnitEntity> getListByBuildingNum(String buildingNum);

    /**
     * 根据楼栋编码获取单元编码列表
     * @param buildingNum
     * @return
     */
    List<String> getUnitCodeByBuildingNum(String buildingNum);

}
