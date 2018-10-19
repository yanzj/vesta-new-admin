package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ActivitySurveyInfoEntity;
import com.maxrocky.vesta.domain.model.ActivitySurveyListEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:24
 * @deprecated 线下活动调查DAO
 */
public interface ActivitySurveyRepository {

    <T> void saveOrUpdate(T entity);
    ActivitySurveyInfoEntity getInfoById(String id);
    ActivitySurveyListEntity getListById(String id);
    List<ActivitySurveyListEntity> getList(Map<String,Object> paramsMap, WebPage webPage);
    List<Map<String,Object>> getCityList();
    List<Map<String,Object>> getProjectList(String cityId);
}
