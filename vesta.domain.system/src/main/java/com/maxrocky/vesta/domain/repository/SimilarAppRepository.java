package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SimilarAppContentEntity;
import com.maxrocky.vesta.domain.model.SimilarAppPictureEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @Description 类APP管理DAO
 * @data 2018/5/28
 */
public interface SimilarAppRepository {
    <T> void saveOrUpdate(T entity);
    <E> void delete(E entity);
    List<SimilarAppPictureEntity> getPictureList(Map<String,Object> paramsMap, WebPage webPage);
    SimilarAppPictureEntity getPictureById(String id);
    List<SimilarAppContentEntity> getContentList(Map<String,Object> paramsMap, WebPage webPage);
    SimilarAppContentEntity getContentById(String id);
}
