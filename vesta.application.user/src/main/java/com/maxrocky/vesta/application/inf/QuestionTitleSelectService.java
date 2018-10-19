package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.QuestionTitleSelectDTO;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface QuestionTitleSelectService {
    List<QuestionTitleSelectDTO> getAll(String titleId);
}
