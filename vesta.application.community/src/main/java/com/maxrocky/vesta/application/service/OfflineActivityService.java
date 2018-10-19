package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.OfflineActivityDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 线下活动Service
 * Created by WeiYangDong on 2017/8/21.
 */
public interface OfflineActivityService {

    /**
     * 获取线下活动列表
     */
    List<OfflineActivityDTO> getOfflineActivityList(OfflineActivityDTO offlineActivityDTO, WebPage webPage);

    /**
     * 通过主键ID获取线下活动
     */
    OfflineActivityDTO getOfflineActivityById(String activityId);

    /**
     * 保存或更新线下活动
     */
    void saveOrUpdateOfflineActivity(OfflineActivityDTO offlineActivityDTO);

    /**
     * 删除线下活动
     */
    void deleteOfflineActivity(String activityId);

    /**
     * 获取活动签到记录列表
     */
    List<Map<String, Object>> getOfflineActivitySignList(OfflineActivityDTO offlineActivityDTO, WebPage webPage);

    /**
     * 通过活动ID获取活动签到记录列表
     */
    List<String> getOfflineActivitySignByActivity(String activityId);

    /**
     * 保存活动抽奖信息
     */
    void saveActivityPrizeInfo(OfflineActivityDTO offlineActivityDTO);

    /**
     * 线下活动参与记录导出Excel
     */
    void exportActivitySignListExcel(HttpServletResponse response, HttpServletRequest request, OfflineActivityDTO offlineActivityDTO) throws Exception;
}
