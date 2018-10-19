package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ButlerScoreLogEntity;
import com.maxrocky.vesta.domain.model.ButlerStyleEntity;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 管家模块_数据持久层
 * Created by WeiYangDong on 2017/5/5.
 */
public interface ButlerStyleRespository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 删除Entity
     * @param entity
     */
    <E> void delete(E entity);

    /**
     * 获取管家列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<ButlerStyleEntity>
     */
    List<ButlerStyleEntity> getButlerStyleList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 更新管家责任房屋
     * @param butlerId 管家ID
     * @param roomIdList 房产列表
     */
    void updateHouseButlerId(String butlerId,List<String> roomIdList);

    /**
     * 获取管家责任房产
     * @param butlerId 管家ID
     * @return List<HouseInfoEntity>
     */
    List<HouseInfoEntity> getHouseInfoListByButler(String butlerId);

    /**
     * 获取管家评分日志列表
     * @param butlerId 管家ID
     * @param webPage 分页
     * @return List<ButlerScoreLogEntity>
     */
    List<ButlerScoreLogEntity> getButlerScoreLogList(String butlerId, WebPage webPage);

    /**
     * 删除管家房产关系(删除管家后,更新房产管家为空)
     * @param butlerId 管家ID
     */
    void deleteHouseButlerByButler(String butlerId);
}
