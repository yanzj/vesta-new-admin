package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.QuestionTitleEntity;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface QuestionTitleRepository {

    List<QuestionTitleEntity> getAll(String questId);

    void create(QuestionTitleEntity q);
}
