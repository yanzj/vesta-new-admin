package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
public interface QuestionnaireRepository {

    List<Object> getAll(Map<String, Object> paramsMap, WebPage webPage);

    QuestionnaireEntity getDetail(String id);

    void create(QuestionnaireEntity q);
    void update(QuestionnaireEntity q);

    void updateTpKs(String date);

    void updateTpJs(String date);

}
