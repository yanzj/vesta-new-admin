package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.UserCrmForRoomidDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 12:05.
 * Describe:单元Service接口
 */
public interface HouseRoomService {

    /**
     * Code:HI0005
     * Type:UI Method
     * Describe:根据单元id获取房号列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 12:15:39
     */
    ApiResult getRoomListByUnitId(String unitId);

    /**
     * 根据楼层id获取房号列表
     *
     * @param FloorId
     * @return
     */
    Map<String, String> getRoomsByFloorId(String FloorId);

    Map<String, String> getRoomsByFloorNum(String FloorNum);

    UserCrmForRoomidDTO getuserCrm(String id);


    /**
     * 根据项目编码和模块获取计划信息
     */
    Map<String, String> getPlanListByProjectNumAndType(String projectNum, String type);

    /**
     * 根据计划编码获取房间信息
     */
    Map<String, String> getRoomsByPlanNum(String planNum);

    /**
     * 根据项目编码获取房间信息
     *
     * @param projectNum
     * @return
     */
    Map<String, String> getRoomsByProjectNum(String projectNum);

    /**
     * 根据房间编码 获取业主房屋地址等信息
     */
    UserCrmForRoomidDTO getSignPrint(String roomNum);

    /**
     * 根据项目ID获取项目num
     * @param projectId
     * @return
     */
    String getProjectNumById(String projectId);
}
