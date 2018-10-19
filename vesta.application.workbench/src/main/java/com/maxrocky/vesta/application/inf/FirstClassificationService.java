package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.FirstClassificationEntity;

import java.util.Map;

/**
 * Created by mql on 2016/6/2.
 */
public interface FirstClassificationService {
    /**
     * 获取一级分类
     * @return
     */
    public Map<String, String> getFirstClassification();


    FirstClassificationEntity getFirstClassificationById(String id);
}
