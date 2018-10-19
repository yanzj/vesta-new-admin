package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.SubjectDTO;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface SubjectService {
    List<SubjectDTO> getAll(QuestionnaireEntity q);
}
