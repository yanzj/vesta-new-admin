package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 物业门禁管理-持久层接口
 * Created by WeiYangDong on 2016/11/1.
 */
public interface PropertyDoorRepository {

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
     * 获取物业访客列表 WeiYangDong_2016-11-01
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<PropertyVisitorEntity>
     */
    List<PropertyVisitorEntity> getPropertyVisitorList(Map<String,Object> params,WebPage webPage);

    /**
     * 获取物业门禁列表 WeiYangDong_2016-11-01
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<PropertyDoorEntity>
     */
    List<PropertyDoorEntity> getPropertyDoorList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过门禁Id获取门禁详情 WeiYangDong_2016-11-10
     * @param id 门禁Id
     * @return  PropertyDoorEntity
     */
    PropertyDoorEntity getPropertyDoorById(String id);

    /**
     * 获取用户门禁关系列表 WeiYangDong_2016-11-11
     * @param params 参数
     * @return  List<PropertyUserDoorMapEntity>
     */
    List<PropertyUserDoorMapEntity> getUserDoorMapList(Map<String,Object> params);

    /**
     * 通过项目编码与地块名称检索地块信息
     * @param projectCode   项目编码
     * @param areaName  地块名称
     * @return  List<HouseAreaEntity>
     */
    List<HouseAreaEntity> getAreaListByProjectAndAreaName(String projectCode,String areaName);

    /**
     * 通过地块编码与备案楼号检索楼栋信息
     * @param blockCode 地块编码
     * @param buildingRecord  备案楼号
     * @return  List<HouseBuildingEntity>
     */
    List<HouseBuildingEntity> getBuildingListByAreaAndBuildingRecord(String blockCode,String buildingRecord);

    /**
     * 通过地块编码与预售楼号检索楼栋信息
     * @param blockCode 地块编码
     * @param buildingSale  预售楼号
     * @return  List<HouseBuildingEntity>
     */
    List<HouseBuildingEntity> getBuildingListByAreaAndBuildingSale(String blockCode,String buildingSale);

    /**
     * 通过楼栋编码与单元名称检索单元信息
     * @param buildingNum  楼栋编码
     * @param unitName  单元名称
     * @return  List<HouseUnitEntity>
     */
    List<HouseUnitEntity> getUnitListByBuildingAndUnitName(String buildingNum,String unitName);

    /**
     * 通过单元编码与楼层名称检索楼层信息
     * @param unitCode  单元编码
     * @param floorName  楼层名称
     * @return  List<HouseFloorEntity>
     */
    List<HouseFloorEntity> getFloorListByUnitAndFloorName(String unitCode,String floorName);

    /**
     * 通过楼层编码与房间号检索房屋信息
     * @param floorCode 楼层编码
     * @param roomName 房间号
     * @return List<HouseInfoEntity>
     */
    List<HouseInfoEntity> getHouseInfoListByFloorAndRoomName(String floorCode,String roomName);

    /**
     * 获取用户门禁关系的用户列表
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getPropertyUserDoorList(Map<String,Object> params,WebPage webPage);

    /**
     * 获取用户列表
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getUserList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过房产单位信息(项目/地块/楼栋/单元)获取用户(业主/同住人/虚拟业主)列表
     * @param params    参数Map
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getUserListByHousePosition(Map<String,Object> params);

    /**
     * 获取用户详情
     * @param params    参数Map
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getUserInfo(Map<String,Object> params);

    /**
     * 通过用户获取门禁权限列表
     * @param userId    用户Id
     * @return  List<PropertyDoorEntity>
     */
    List<PropertyDoorEntity> getPropertyDoorListByUser(String userId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param: PropertyDoorLogEntity
    *  @Description: 保存开门记录
    */
    void saveDoorLog(PropertyDoorLogEntity propertyDoorLogEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param: params 参数
    *  @Description: 获取开门记录列表
    */
    List<PropertyDoorLogEntity> getDoorLogList(Map<String, Object> params, WebPage webPage);

    /**
     * 通过房产信息获取门禁列表
     * @param params  参数
     * @return  List<PropertyDoorEntity>
     */
    List<PropertyDoorEntity> getPropertyDoorListByHouse(Map<String,Object> params);

    /**
     * 通过楼层获取门禁日志统计信息
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getDoorLogStatisticsByFloor(Map<String,Object> params);

    /**
     * 通过时间段(小时)获取门禁日志统计信息
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,String>> getDoorLogStatisticsByTime(Map<String,Object> params);

    /**
     * 通过楼栋获取门禁日志统计信息
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getDoorLogStatisticsByBuilding(Map<String,Object> params);
}
