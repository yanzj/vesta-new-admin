package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 11:46.
 * Describe:单元Service接口
 */
public interface HouseUnitService {

    /**
     * Code:HI0004
     * Type:UI Method
     * Describe:根据楼Id获取单元列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:51:04
     */
    ApiResult getUnitListByBuildingId(String buildingId);

    /**
     * 根据楼栋ID获取单元列表
     * @param buildingId
     * @return
     */
    public Map getUnitMapByBuildingId(String buildingId);

    /**
     * 根据楼栋编码获取单元列表
     * @param buildingNum
     * @return
     */
    public List<String> getUnitByBuildingNum(String buildingNum);

}
