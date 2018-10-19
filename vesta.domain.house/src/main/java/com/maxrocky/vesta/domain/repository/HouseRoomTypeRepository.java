package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseRoomTypeEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/4.
 */
public interface HouseRoomTypeRepository {

    /**
     * 获取指定单元的房间户型信息
     * @param projectId 项目ID
     * @param areaId 区域ID
     * @param buildingId 楼栋ID
     * @param unitId 单元ID
     * @return
     */
    public List<Object[]> getRoomTypeList(String projectId,String areaId,String buildingId,String unitId);

    /**
     * 获取楼层的房间数
     * @param projectId
     * @param areaId
     * @param buildingId
     * @param unitId
     * @return
     */
    public List<String> getFloorRooms(String projectId,String areaId,String buildingId,String unitId);

    /**
     * 根据楼层房间号删除
     * @param floorRoomName
     */
    public void deleteByUnitId(String unitId,String floorRoomName);

    /**
     * 根据楼层房间号保存
     * @param floorRoomName
     * @param houseType
     */
    public void saveByUnitId(String unitId,String floorRoomName,String houseType);


    /**
     * 根据房间删除
     * @param roomNum
     */
    public void deleteByRoomId(String roomNum);

    /**
     * 根据房间保存
     * @param roomId
     * @param houseType
     * @param roomNum
     */
    public void saveByRoomId(String roomId,String roomNum,String houseType);

    /**
     * 按照时间和ID查询
     * @param modifyDate
     * @param id
     * @return
     */
    List<HouseRoomTypeEntity> getByModifyDateAndId(String modifyDate,String id,String projectNum);

    /**
     * 获取房间户型信息
     * @param roomNum
     * @return
     */
    Object[] getRoomHouseType(String roomNum);
}
