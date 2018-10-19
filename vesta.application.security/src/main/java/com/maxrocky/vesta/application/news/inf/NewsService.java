package com.maxrocky.vesta.application.news.inf;

import com.maxrocky.vesta.application.news.DTO.NewsDTO;
import com.maxrocky.vesta.domain.news.model.NewsEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 新闻管理Service
 * Created by yuanyn on 2017/6/13.
 */
public interface NewsService {
    //获取新闻列表
    List<Map<String,Object>> getNewsList(NewsDTO newsDTO, WebPage webPage);

    //获取新闻详情
    NewsEntity getNewsById(String NewsId);

    //发布或更新新闻
    void saveOrUpdateNews(NewsDTO newsDTO);

    //删除新闻
    void deleteNews(NewsDTO newsDTO);

    //置顶新闻
    boolean topNews(NewsDTO newsDTO);
}
