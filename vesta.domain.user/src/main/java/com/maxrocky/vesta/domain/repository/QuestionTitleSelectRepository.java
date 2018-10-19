package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.QuestionTitleSelectEntity;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface QuestionTitleSelectRepository {
    List<QuestionTitleSelectEntity> getAll(String questionTitleId);
    void create(QuestionTitleSelectEntity q);
}
