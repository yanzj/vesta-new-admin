package com.maxrocky.vesta.application.knowledge.impl;

import com.maxrocky.vesta.application.knowledge.DTO.KnowledgeDTO;
import com.maxrocky.vesta.application.knowledge.inf.KnowledgeService;
import com.maxrocky.vesta.domain.knowledge.model.KnowledgeEntity;
import com.maxrocky.vesta.domain.knowledge.repository.KnowledgeRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2017/6/5.
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Override
    public List<KnowledgeDTO> getKnowledgeFiles(KnowledgeDTO knowledgeDTO, WebPage webPage) {
        Map<String ,Object> paramsMap = new HashedMap();
        paramsMap.put("graded",knowledgeDTO.getGraded());
        paramsMap.put("fileName",knowledgeDTO.getFileName());
        paramsMap.put("parentId",knowledgeDTO.getCurrentId());
        List<KnowledgeDTO> knowledgeDTOList = new ArrayList<KnowledgeDTO>();
        List<KnowledgeEntity> knowledgeEntities = knowledgeRepository.getKnowledgeFiles(paramsMap,webPage);
        KnowledgeEntity knowledgeEntity1 = null;
        if(!StringUtil.isEmpty(knowledgeDTO.getCurrentId())){
            knowledgeEntity1= knowledgeRepository.getKnowledgeFileById(knowledgeDTO.getCurrentId());
        }
        if(knowledgeEntities!=null){
            KnowledgeDTO knowledgeDTO1;
            for(KnowledgeEntity knowledgeEntity:knowledgeEntities){
                knowledgeDTO1 = new KnowledgeDTO();
                knowledgeDTO1.setId(knowledgeEntity.getId());
                knowledgeDTO1.setModifyDate(knowledgeEntity.getModifyDate());
                knowledgeDTO1.setCurrentId(knowledgeEntity.getCurrentId());
                knowledgeDTO1.setCreateDate(DateUtils.format(knowledgeEntity.getCreateDate(), DateUtils.FORMAT_LONG));
                knowledgeDTO1.setParentId(knowledgeEntity.getParentId());
                knowledgeDTO1.setState(knowledgeEntity.getState());
                knowledgeDTO1.setFileName(knowledgeEntity.getFileName());
                knowledgeDTO1.setGraded(knowledgeEntity.getGraded());
                knowledgeDTO1.setPath(knowledgeEntity.getPath());
                knowledgeDTO1.setSize(knowledgeEntity.getSize());
                if(null != knowledgeEntity1){
                    knowledgeDTO1.setParentName(knowledgeEntity1.getFileName());
                }

                knowledgeDTOList.add(knowledgeDTO1);
            }
        }
        return knowledgeDTOList;
    }

    @Override
    public void addKnowledgeFile(KnowledgeDTO knowledgeDTO) {
        KnowledgeEntity knowledgeEntity = new KnowledgeEntity();
        knowledgeEntity.setModifyDate(new Date());
        knowledgeEntity.setCurrentId(IdGen.uuid());
        knowledgeEntity.setCreateDate(new Date());
        knowledgeEntity.setParentId(knowledgeDTO.getParentId());
        knowledgeEntity.setState("1");
        knowledgeEntity.setFileName(knowledgeDTO.getFileName());
        knowledgeEntity.setGraded(knowledgeDTO.getGraded());
        knowledgeEntity.setPath(knowledgeDTO.getPath());
        knowledgeEntity.setSize(knowledgeDTO.getSize());

        knowledgeRepository.addKnowledgeFile(knowledgeEntity);
    }

    @Override
    public void deleteKnowledge(String currentId) {
        KnowledgeEntity knowledgeEntity = knowledgeRepository.getKnowledgeFileById(currentId);
        if (null != knowledgeEntity) {
            knowledgeRepository.deleteKnowledgeById(currentId);
            if("1".equals(knowledgeEntity.getGraded())){
                List<String> currentIdList = knowledgeRepository.getCurrentIdKnowledgByparentId(currentId);
                knowledgeRepository.deleteRelevanceKnowledgById(currentIdList);
            }
        }
    }
}
