package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.QuestionTitleDTO;
import com.maxrocky.vesta.application.inf.QuestionTitleSelectService;
import com.maxrocky.vesta.application.inf.QuestionTitleService;
import com.maxrocky.vesta.domain.model.QuestionTitleEntity;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.domain.repository.QuestionTitleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Service
public class QuestionTitleServiceImpl implements QuestionTitleService {

    @Autowired
    QuestionTitleRepository questionTitleRepository;

    @Autowired
    QuestionTitleSelectService questionTitleSelectService;

    @Override
    public List<QuestionTitleDTO> getAll(QuestionnaireEntity q) {
        List<QuestionTitleEntity> l = new ArrayList<>();
        List<QuestionTitleDTO> qdto = new ArrayList<>();
        if(q != null){
            l = questionTitleRepository.getAll(q.getQuestId());
        }
        for (QuestionTitleEntity i : l){
            QuestionTitleDTO questionTitleDto = new QuestionTitleDTO();
            BeanUtils.copyProperties(i,questionTitleDto);
            questionTitleDto.setQuestionTitleSelect(questionTitleSelectService.getAll(questionTitleDto.getQuestionTitleId()));
            qdto.add(questionTitleDto);
        }
        return qdto;
    }
}
