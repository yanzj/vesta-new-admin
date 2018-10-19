package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseCRMEntity;
import com.maxrocky.vesta.domain.model.HouseRoomEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/18 11:57.
 * Describe:房号Repository接口
 */
public interface HouseRoomRepository {

    /**
     * Describe:根据单元Id获取房号列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:58:52
     */
    List<HouseRoomEntity> getListByUnitId(String unitId);

    /**
     * Describe:根据房间Id获取房间信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:17:53
     */
    HouseRoomEntity get(String roomId);

    /**
     * Describe:根据单元Id、房间号获取房间
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:24:37
     */
    HouseRoomEntity getByRoomNameAndUnitId(String roomName, String unitId);

    /**
     * 获取房间下拉框
     * @param unitId
     * @return
     */
    List<HouseRoomEntity> mapRoom(String unitId);

    /**
     * Describe:创建房间信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    void create(HouseRoomEntity houseRoomEntity);

    /**
     * Describe:根据会员编号获取房间信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    HouseRoomEntity getByMemberId(String id);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改房产信息
     * ModifyBy:
     */
    void updateHouseInfo(HouseRoomEntity houseRoomEntity);

    /**
     * Describe:根据房屋id去查询房屋信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 16:28
     */
    HouseRoomEntity getHouseByRoomId(String roomId);


    /**
     * 根据楼层ID取得房间
     * @param floorId
     * @return
     */
    List<HouseRoomEntity> getListByFloorId(String floorId);

    List<HouseRoomEntity> getListByFloorNum(String floorNum);

    /**
     * 根据房间编码查询
     * @param roomNum
     * @return
     */
    HouseRoomEntity getByRoomNum(String roomNum);

    List<Object[]> getUserCrmForRoomid(String id);

    /**
     * 根据项目编码和模块获取计划信息
     * */
    List<Object[]> getDeliveryPlanList(String projectNum,String type);

    /**
     * 根据计划编码获取房间信息
     * */
    List<Object[]> getRoomByPlanList(String planNum);

    /**
     * 根据项目编码获取房间信息
     * @param projectNum
     * @return
     */
    List<Object[]> getRoomByProjectList(String projectNum);

    /**
     * 根据楼层编码获取房间信息
     * @param floorNum
     * @return
     */
    List<Object[]> getRoomByFloor(String floorNum);
    /**
     * 根据房间编码获取业主信息
     * @param roomNum
     * @return
     */
    List<Object[]> getUserCrmByRoomNum(String roomNum);
    /**
     * 根据房间编码获取房间地址信息
     * @param roomNum
     * @return
     */
    List<Object[]> getAddressByRoomNum(String roomNum);

    /**
     * 根据项目ID获取项目num
     * @param projectId
     * @return
     */
    String getProjectNumById(String projectId);
}
