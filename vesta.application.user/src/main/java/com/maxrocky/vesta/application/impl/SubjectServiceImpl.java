package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.SubjectDTO;
import com.maxrocky.vesta.application.inf.SubjectService;
import com.maxrocky.vesta.application.inf.UserSubjectService;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.domain.model.SubjectEntity;
import com.maxrocky.vesta.domain.repository.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    UserSubjectService userSubjectService;

    @Override
    public List<SubjectDTO> getAll(QuestionnaireEntity q) {
        List<SubjectDTO> dto = new ArrayList<>();
        if(q != null){
            SubjectEntity subjectEntity = subjectRepository.getById(q.getQuestId());
            if(subjectEntity != null){
                SubjectDTO sub = new SubjectDTO();
                BeanUtils.copyProperties(subjectEntity,sub);
                sub.setUserSubjectDTOS(userSubjectService.getAll(subjectEntity.getSubjectId()));
                dto.add(sub);
            }
        }
        return dto;
    }
}
