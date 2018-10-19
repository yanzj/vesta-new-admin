package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.QCKnowledgeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Itzxs on 2018/6/8.
 */
public interface QcKnowledgeRepository {

    List<Object[]> getQcKnowledgeList(String contentType,String knowledgeType,String title,String userName,String createDate, WebPage webPage);

    int getQcKnowledgeCount(String contentType,String knowledgeType,String title,String userName,String createDate);

    List<String> getContentTypeList();

    QCKnowledgeEntity getKnowledgeById(int id);

    <T> void saveOrUpdate(T entity);

    void delKnowledge(String id);
}
