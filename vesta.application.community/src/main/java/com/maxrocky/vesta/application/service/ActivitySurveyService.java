package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.ActivitySurveyDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:16
 * @deprecated 线下活动调查Service
 */
public interface ActivitySurveyService {

    ActivitySurveyDTO getInfoById(ActivitySurveyDTO activitySurveyDTO) throws InvocationTargetException, IllegalAccessException;
    ActivitySurveyDTO getListById(ActivitySurveyDTO activitySurveyDTO) throws InvocationTargetException, IllegalAccessException;
    List<ActivitySurveyDTO> getList(ActivitySurveyDTO activitySurveyDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;
    void saveOrUpdateActivitySurveyInfo(ActivitySurveyDTO activitySurveyDTO);
    List<Map<String,Object>> getCityList();
    List<Map<String, Object>> getProjectList(String cityId);
    void exportExcel(String title, String[] headers, ServletOutputStream out, ActivitySurveyDTO activitySurveyDTO) throws Exception;
}
