package com.maxrocky.vesta.domain.inspectionPlan.repository;

import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by maxrocky on 2017/6/20.
 */
public interface InspectionPlanRepository {
    /**
     * 查询检查计划数据List
     */
    List<Object[]> getInspectionPlanList(Map map, WebPage webPage);

    /**
     * 根据集团id查询其下所有区域公司id
     */
    List<String> getAreaIdListByGroup(String id);

    /**
     * 根据集团id查询其下所有项目公司id
     */
    List<String> getProjectIdListByGroup(String id);

    /**
     * 根据区域id查询其下所有项目公司id
     */
    List<String> getProjectIdListByArea(String id);
}
