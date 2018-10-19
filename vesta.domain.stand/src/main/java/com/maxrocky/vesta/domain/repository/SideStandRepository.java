package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SideStandEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/5/17.
 */
public interface SideStandRepository {
    //新增旁站
    void addSideStand(SideStandEntity sideStandEntity);
    //更新旁站
    void updateSideStand(SideStandEntity sideStandEntity);
    //查询旁站列表
    List<SideStandEntity> getSideStandList();
    //根据项目查询旁站列表
    List<SideStandEntity> getSideStands(String projectId);
    //查询旁站详情
    SideStandEntity getSideStandDetail(String id);
    //根据条件查询旁站列表
    List<SideStandEntity> getStandList(SideStandEntity sideStandEntity,WebPage webPage);

}
