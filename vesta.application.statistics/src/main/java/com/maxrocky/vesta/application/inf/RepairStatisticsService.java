package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.RepairStatisticsDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 报修统计 业务逻辑层接口
 */
public interface RepairStatisticsService {
    /**
     * 初始化 报修统计
     * @param clickTimesSeach
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<RepairStatisticsDTO> repairStatistics(ClickTimesSeachDTO clickTimesSeach,WebPage webPage,UserPropertyStaffEntity userPropertystaff);
}
