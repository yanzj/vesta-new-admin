package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SubjectEntity;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface SubjectRepository {
    SubjectEntity getById(String questId);
    void create(SubjectEntity q);

}
