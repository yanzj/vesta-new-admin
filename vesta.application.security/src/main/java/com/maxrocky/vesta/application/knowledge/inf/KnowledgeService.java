package com.maxrocky.vesta.application.knowledge.inf;

import com.maxrocky.vesta.application.knowledge.DTO.KnowledgeDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by yuanyn on 2017/6/5.
 */
public interface KnowledgeService {

    /**
     *获取知识库列表
     */
    List<KnowledgeDTO> getKnowledgeFiles(KnowledgeDTO knowledgeDTO, WebPage webPage);

    /**
     * 增加知识库文件
     */
    void addKnowledgeFile(KnowledgeDTO knowledgeDTO);

    /**
     * 删除知识库文件
     */
    void deleteKnowledge(String currentId);
}
