package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.TransLogEntity;

/**
 * Created by JillChen on 2016/1/26.
 */
public interface TransLogRepository {
    void save(TransLogEntity s);

    void update(TransLogEntity transLogEntity);
}
