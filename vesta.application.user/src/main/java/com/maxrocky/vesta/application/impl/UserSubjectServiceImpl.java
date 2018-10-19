package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.QuestionnaireDTO;
import com.maxrocky.vesta.application.DTO.admin.UserSubjectDTO;
import com.maxrocky.vesta.application.inf.UserSubjectService;
import com.maxrocky.vesta.domain.model.UserSubjectEntity;
import com.maxrocky.vesta.domain.repository.UserSubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Service
public class UserSubjectServiceImpl implements UserSubjectService {
    @Autowired
    UserSubjectRepository userSubjectRepository;
    @Override
    public List<UserSubjectDTO> getAll(String subjectId) {
        List<Object> userSubjectEntities = userSubjectRepository.getAll(subjectId);
        List<UserSubjectDTO> dto = new ArrayList<>();
        for (Object l : userSubjectEntities){
            UserSubjectDTO idto = new UserSubjectDTO();
            Object[] obj = (Object[]) l;
            idto.setUserSubjectId((String) obj[0] == null ? "" : (String) obj[0]);
            idto.setUserId((String)obj[1] == null ? "" : (String) obj[1]);
            idto.setMessage((String) obj[2] == null ? "" : (String) obj[2]);
            dto.add(idto);
        }
        return dto;
    }
}
