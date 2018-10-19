package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.AnswerAdminDTO;
import com.maxrocky.vesta.application.inf.AnswerService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AnswerRepository;
import com.maxrocky.vesta.domain.repository.PraiseRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chen on 2016/2/20.
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    PraiseRepository praiseRepository;

    @Override
    public void AddAnswer(UserPropertyStaffEntity userPropertyStaffEntity,AnswerAdminDTO answerAdminDTO) {
        if(answerAdminDTO.getAnswerType().equals(AnswerEntity.answer_praise)){
            PraiseEntity praiseEntity = praiseRepository.getPraiseDetail(answerAdminDTO.getConsultId());
            if(praiseEntity.getReply().equals(PraiseEntity.REPLY_YES)){
                AnswerEntity answerEntity = answerRepository.getAnswer(answerAdminDTO.getConsultId(),AnswerEntity.answer_praise);
                answerEntity.setContent(answerAdminDTO.getContent());
                answerRepository.chengeAnswerEntity(answerEntity);
                return;
            }
            praiseEntity.setReply(PraiseEntity.REPLY_YES);
            praiseRepository.altPraise(praiseEntity);
        }
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(IdGen.uuid());
        answerEntity.setUserId(userPropertyStaffEntity.getStaffId());
        answerEntity.setAnswerType(AnswerEntity.answer_praise);
        answerEntity.setConsultId(answerAdminDTO.getConsultId());
        answerEntity.setCrtTime(new Date());
        answerEntity.setContent(answerAdminDTO.getContent());
        answerRepository.AddAnswer(answerEntity);
    }
}
