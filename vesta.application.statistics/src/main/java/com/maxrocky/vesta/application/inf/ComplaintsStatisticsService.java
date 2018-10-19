package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.ComplaintsStatisticsDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 投诉统计 业务逻辑层接口
 */
public interface ComplaintsStatisticsService {

    /**
     * 投诉统计
     * @param clickTimesSeach
     * @param webPage
     * @param userPropertySta
     * @return
     */
    List<ComplaintsStatisticsDTO> complaintsStatistics(ClickTimesSeachDTO clickTimesSeach,WebPage webPage,UserPropertyStaffEntity userPropertySta);
}
