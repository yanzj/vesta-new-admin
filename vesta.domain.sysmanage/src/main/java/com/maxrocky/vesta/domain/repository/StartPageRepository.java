package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.StartPageEntity;

/**
 * Created by sunmei on 2016/2/29.
 */
public interface StartPageRepository {
    /**
     * 创建启动页
     */
    boolean createStartPage(StartPageEntity startPageEntity);
}

