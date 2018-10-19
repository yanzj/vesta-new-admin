package com.maxrocky.vesta.domain.news.repository;

import com.maxrocky.vesta.domain.news.model.NewsEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 新闻管理 数据持久层
 * Created by yuanyn on 2017/6/13.
 */
public interface NewsRepository {
    /**
     * 获取新闻列表
     * @param paramsMap
     * @param webPage
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getNewsList(Map<String,Object> paramsMap, WebPage webPage);
    /**
     * 获取新闻详情
     * @param newsId
     * @return NewsEntity
     */
    NewsEntity getNewsById(String newsId);

    /**
     * 添加或修改Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);
    /**
     * 删除Entity
     * @param entity
     */
    <E> void delete(E entity);

    /**
     * 置顶或取消置顶新闻
     *@param newsId
     */
    void topNews(String newsId, String slideShow);

}
