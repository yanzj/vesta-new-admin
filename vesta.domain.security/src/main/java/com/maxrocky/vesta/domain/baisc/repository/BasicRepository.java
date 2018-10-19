package com.maxrocky.vesta.domain.baisc.repository;

import com.maxrocky.vesta.domain.baisc.model.SecurityAssessmentTempEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/15.
 */
public interface BasicRepository {
    /**
     * 根据名称查询考核模板信息
     *
     * @param name     名称
     * @param domain   所属模板
     * @param level    级别
     * @param parentId 父级ID
     * @return
     */
    SecurityAssessmentTempEntity getSecurityAssessByName(String level, String parentId, String name, String domain);

    /**
     * 根据名称查询考核模板信息
     *
     * @param content  内容
     * @param domain   所属模板
     * @param level    级别
     * @param parentId 父级ID
     * @return
     */
    SecurityAssessmentTempEntity getSecurityAssessByContent(String level, String parentId, String content, String domain);

    /**
     * 更新数据
     *
     * @param object
     */
    void updateEntity(Object object);

    /**
     * 添加数据
     *
     * @param object
     */
    void addEntity(Object object);

    /**
     * 分页获取模板信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getAssessTempList(Map map, WebPage webPage);
    /**
     * 获取模板信息
     *
     * @param map
     * @return
     */
    List<Object[]> getAssessTempList(Map map);

    /**
     * 获取总条数
     *
     * @param map
     * @return
     */
    int getCount(Map map);
}
