package com.maxrocky.vesta.application.inf;

import java.util.Map;

/**
 * Created by 27978 on 2016/8/8.
 */
public interface RepairModeService {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取维修方式列表
    */
    public Map<String,String> getRepairModeList(String thirdId);

    /**
     * 根据维修方式获取维修工时
     * */
    public Map<String,String> getStandardDate(String repairMode);

}
