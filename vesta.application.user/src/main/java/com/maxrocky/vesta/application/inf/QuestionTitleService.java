package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.QuestionTitleDTO;
import com.maxrocky.vesta.domain.model.QuestionTitleEntity;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface QuestionTitleService {
    List<QuestionTitleDTO> getAll(QuestionnaireEntity q);
}
