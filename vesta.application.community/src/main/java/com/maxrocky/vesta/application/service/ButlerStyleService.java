package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.ButlerStyleDto;
import com.maxrocky.vesta.domain.model.ButlerScoreLogEntity;
import com.maxrocky.vesta.domain.model.ButlerStyleEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 管家模块_Service
 * Created by WeiYangDong on 2017/5/5.
 */
public interface ButlerStyleService {

    /**
     * 获取管家列表
     * @param butlerStyleDto 参数
     * @param webPage 分页
     * @return List<ButlerStyleEntity>
     */
    List<ButlerStyleEntity> getButlerStyleList(ButlerStyleDto butlerStyleDto, WebPage webPage);

    /**
     * 获取管家详情
     * @param butlerId 管家ID
     * @return ButlerStyleEntity
     */
    ButlerStyleEntity getButlerStyleInfoById(String butlerId);

    /**
     * 保存或更新管家详情
     */
    void saveOrUpdateButlerStyle(ButlerStyleDto butlerStyleDto);

    /**
     * 重置管家评分
     */
    void resetButlerScope(ButlerStyleDto butlerStyleDto);

    /**
     * 校验管家编号(butlerNum)唯一性
     */
    boolean checkButlerNum(String butlerNum);

    /**
     * 保存或更新管家责任范围
     */
    void saveOrUpdateButlerScope(ButlerStyleDto butlerStyleDto);

    /**
     * 通过管家及责任项目检索责任范围信息
     */
    List<Map<String,Object>> getScopeInfoByButler(ButlerStyleDto butlerStyleDto);
    String getScopeTreeByButler(ButlerStyleDto butlerStyleDto);

    /**
     * 获取管家评分日志列表
     */
    List<ButlerScoreLogEntity> getButlerScoreLogList(ButlerStyleDto butlerStyleDto, WebPage webPage);

    /**
     * 删除管家数据
     */
    void deleteButlerStyle(ButlerStyleDto butlerStyleDto);
}
