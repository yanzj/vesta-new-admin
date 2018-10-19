package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ElectricWarnEntity;

/**
 * Created by zhanghj on 2016/2/23.
 */
public interface ElectricWarnRepository {

    /**
     * 添加预警信息
     * @param electricWarnEntity
     * @return
     */
    public boolean saveEleWarn(ElectricWarnEntity electricWarnEntity);

    /**
     * 根据预警id查询预警信息
     * @param warnId
     * @return
     */
    public ElectricWarnEntity getEleWarnById(String warnId);

    /**
     * 根据项目id查询预警信息
     * @param projectId
     * @return
     */
    public ElectricWarnEntity getEleWarnByProId(String projectId);

    /**
     * 跟新预警信息
     * @param electricWarnEntity
     * @return
     */
    public boolean updateEleWarn(ElectricWarnEntity electricWarnEntity);

    ElectricWarnEntity getEleWarnByProIdForInterface(String projectId);
}
