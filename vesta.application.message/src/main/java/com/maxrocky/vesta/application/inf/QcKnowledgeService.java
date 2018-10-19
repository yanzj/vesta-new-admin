package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.adminDTO.QcKnowledgeDTO;
import com.maxrocky.vesta.domain.model.QCKnowledgeEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/6/8.
 */
public interface QcKnowledgeService {

    List<QCKnowledgeEntity> getKnowledgeList(QcKnowledgeDTO qcKnowledgeDTO, WebPage webPage);

    Map<String,String> getContentType();

    QCKnowledgeEntity getKnowledgeById(int id);

    void editKnowledge(QcKnowledgeDTO qcKnowledgeDTO, MultipartFile file, UserInformationEntity userInformationEntity);

    void delKnowledge(QcKnowledgeDTO qcKnowledgeDTO);

    QcKnowledgeDTO getKnowledgeDTOById(int id);
}
