package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseAreaEntity;

import java.util.List;

/**
 * Created by langmafeng on 2016/5/7/12:22.
 */
public interface HouseAreaRepository {
    /**
     * Describe:根据地块id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/12:22.
     */
    HouseAreaEntity getInfoByBlockId(String blockId);

    /**
     * Describe:更新地块信息
     * CreatedBy:langmafeng
     * Describe:2016/5/7/12:22.
     *
     *
     */
    void updateHouseAreaInfo(HouseAreaEntity houseAreaEntity);

    /**
     * Describe:创建地块信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/12:22.
     */
    void create(HouseAreaEntity houseAreaEntity);

    /**
     * 根据项目ID查询区域信息
     * @param projectId
     * @return
     */
    public List<HouseAreaEntity> getInfoByProjectId(String projectId);
}
