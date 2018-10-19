package com.maxrocky.vesta.application.inf;

import java.util.Map;

/**
 * Created by mql on 2016/6/2.
 */
public interface SecondClassificationService {

    /**
     * 根据一级分类获取二级分类
     * @param firstid
     * @return
     */
    public Map<String, String> getSecondClassification(String firstid);
}
