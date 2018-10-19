package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ElectronizationGuideEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/5/23.
 * 电子化指引DAO接口
 */
public interface ElectronizationGuideRepository {

    //条件筛选电子化指引
    List<ElectronizationGuideEntity> getElectronizationGuideList(Map<String ,Object> paramsMap, WebPage webPage);
    //新增or修改
    void saveOrUpdateElectronizationGuide(ElectronizationGuideEntity electronizationGuideEntity);
    //根据id获取电子化指引详情
    ElectronizationGuideEntity getElectronizationGuideById(Long id);

}
