package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ClientRelationsNewsEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 客关新闻DAO层接口
 * Created by yuanyn on 2018/6/20 0011.
 */
public interface ClientRelationsNewsRepository {

    //根据新闻列表
    List<ClientRelationsNewsEntity> getNewsList(Map<String, Object> paramsMap, WebPage webPage, List<String> projectIds);

    //根据id获取新闻详情
    ClientRelationsNewsEntity getNewsById(String newsId);

    //保存或者修改Entity
    void saveNewsEntity(ClientRelationsNewsEntity clientRelationsNewsEntity);


}
