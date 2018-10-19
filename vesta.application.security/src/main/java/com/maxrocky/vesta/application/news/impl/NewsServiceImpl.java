package com.maxrocky.vesta.application.news.impl;

import com.maxrocky.vesta.application.news.DTO.NewsDTO;
import com.maxrocky.vesta.application.news.inf.NewsService;
import com.maxrocky.vesta.domain.news.model.NewsEntity;
import com.maxrocky.vesta.domain.news.repository.NewsRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新闻Service实现类
 * Created by yuanyn on 2017/6/14.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    //获取新闻列表
    @Override
    public List<Map<String, Object>> getNewsList(NewsDTO newsDTO, WebPage webPage) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("newsTitle",newsDTO.getNewsTitle());
        return newsRepository.getNewsList(paramsMap,webPage);
    }

    //获取新闻详情
    @Override
    public NewsEntity getNewsById(String newsId) {
        return newsRepository.getNewsById(newsId);
    }

    //发布或更新新闻
    @Override
    public void saveOrUpdateNews(NewsDTO newsDTO) {
        NewsEntity newsEntity = null;
        if (null != newsDTO.getNewsId() && !"".equals(newsDTO.getNewsId())){
            newsEntity = getNewsById(newsDTO.getNewsId());
            newsEntity.setModifyName(newsDTO.getModifyName());
            newsEntity.setModifyDate(new Date());
            newsEntity.setSlideShow(newsDTO.getSlideShow());
        }else{
            newsEntity = new NewsEntity();
            newsEntity.setNewsId(IdGen.uuid());
            newsEntity.setCreateName(newsDTO.getModifyName());
            newsEntity.setCreateDate(new Date());
            newsEntity.setSlideShow("0");
        }
        newsEntity.setNewsSource(newsDTO.getNewsSource());
        newsEntity.setNewsTitle(newsDTO.getNewsTitle());
        newsEntity.setNewsImgUrl(newsDTO.getNewsImgUrl());
        newsEntity.setNewsContent(newsDTO.getNewsContent());
        newsRepository.saveOrUpdate(newsEntity);

    }

    //删除新闻
    @Override
    public void deleteNews(NewsDTO newsDTO) {
        NewsEntity newsEntity = newsRepository.getNewsById(newsDTO.getNewsId());
        if (null != newsEntity){
            newsRepository.delete(newsEntity);
        }

    }

    //置顶新闻
    @Override
    public boolean topNews(NewsDTO newsDTO) {
        NewsEntity newsEntity = newsRepository.getNewsById(newsDTO.getNewsId());
        if (null != newsEntity) {
            if ("0".equals(newsEntity.getSlideShow())) {
                Map<String, Object> paramsMap = new HashedMap();
                paramsMap.put("newsId", newsDTO.getNewsId());
                if (newsRepository.getNewsList(paramsMap, null).size() >= 4) {
                    return false;
                } else {
                    newsDTO.setSlideShow("1");
                    newsRepository.topNews(newsDTO.getNewsId(),newsDTO.getSlideShow());
                    return true;
                }
            } else {
                newsDTO.setSlideShow("0");
                newsRepository.topNews(newsDTO.getNewsId(),newsDTO.getSlideShow());
                    return true;
            }
        }
        return false;
    }
}
