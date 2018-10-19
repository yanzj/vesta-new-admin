package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.RepairModeEntity;
import com.maxrocky.vesta.domain.model.WorkTimeEntity;

import java.util.List;

/**
 * Created by 27978 on 2016/8/8.
 */
public interface RepairModeRepository {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取维修方式列表
    */
    public List<RepairModeEntity> getRepairModeList(String thirdId);

    /**
     * 根据维修方式获取维修工时
     * */
    public WorkTimeEntity getStandardDate(String repairMode);
}
