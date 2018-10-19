package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserSubjectEntity;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public interface UserSubjectRepository {
    List<Object> getAll(String subjectId);
}
