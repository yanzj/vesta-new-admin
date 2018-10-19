package com.maxrocky.vesta.domain.knowledge.repository;

import com.maxrocky.vesta.domain.knowledge.model.KnowledgeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2017/6/5.
 */
public interface KnowledgeRepository {
    //通过Id获取该知识库文件的详情
    KnowledgeEntity getKnowledgeFileById(String currentId);
    //获取知识库列表
    List<KnowledgeEntity> getKnowledgeFiles(Map<String,Object> map, WebPage webPage);
    //增加目录或文档
    void addKnowledgeFile(KnowledgeEntity knowledgeEntity);
    //根据ID删除目录或文档
    void deleteKnowledgeById(String currentId);
    //根据模块获取其下属所有目录列表
    List<String> getCurrentIdKnowledgByparentId(String currentId);
    //根据ID删除模块时同步删除其下属目录的所有文档
    void deleteRelevanceKnowledgById(List<String> currentIdList);

}
