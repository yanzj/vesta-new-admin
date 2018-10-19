package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.QuestionTitleDTO;
import com.maxrocky.vesta.application.DTO.admin.QuestionTitleSelectDTO;
import com.maxrocky.vesta.application.inf.QuestionTitleSelectService;
import com.maxrocky.vesta.domain.model.QuestionTitleEntity;
import com.maxrocky.vesta.domain.model.QuestionTitleSelectEntity;
import com.maxrocky.vesta.domain.repository.QuestionTitleSelectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Service
public class QuestionTitleSelectServiceImpl implements QuestionTitleSelectService {
    @Autowired
    QuestionTitleSelectRepository questionTitleSelectRepository;


    @Override
    public List<QuestionTitleSelectDTO> getAll(String titleId) {
        List<QuestionTitleSelectEntity> questionTitleSelectEntity = questionTitleSelectRepository.getAll(titleId);
        List<QuestionTitleSelectDTO> dto = new ArrayList<>();
        for (QuestionTitleSelectEntity i : questionTitleSelectEntity){
            QuestionTitleSelectDTO selectDTO = new QuestionTitleSelectDTO();
            BeanUtils.copyProperties(i,selectDTO);
            dto.add(selectDTO);
        }
        return dto;
    }
}
