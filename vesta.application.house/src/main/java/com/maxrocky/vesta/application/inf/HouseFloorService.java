package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

import java.util.List;
import java.util.Map;

/**
 *楼层Service接口
 */
public interface HouseFloorService {


    /**
     * 根据单元id获取房号列表
     * @param unitId
     * @return
     */
    Map<String,String> getRoomsByUnitId(String unitId);

    Map<String, String> getRoomsByUnitNum(String unitNum);

    /**
     * 参数是楼栋下的所有单元，获取这些单元里最多的楼层
     * @param units
     * @return
     */
    Map<String,String> getFloorByUnits(List<String> units);

}
