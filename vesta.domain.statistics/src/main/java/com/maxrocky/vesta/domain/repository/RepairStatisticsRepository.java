package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 报修统计 持久层接口
 */
public interface RepairStatisticsRepository {
    /**
     * 根据项目ID查询 报修
     * @param projectId
     * @return
     */
    List<PropertyRepairEntity> getPropertyRepairList(String projectId,String taskStatus);
}
