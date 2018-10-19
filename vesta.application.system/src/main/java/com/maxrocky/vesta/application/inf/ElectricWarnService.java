package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ElectricWarnDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

/**
 * Created by zhanghj on 2016/2/23.
 */
public interface ElectricWarnService {

    /**
     * 添加预警信息
     * @param electricWarn
     * @return
     */
    public boolean saveEleWarn(ElectricWarnDTO electricWarn);

    /**
     * 根据预警id查询预警信息
     * @param warnId
     * @return
     */
    public ElectricWarnDTO getEleWarnById(String warnId);

    /**
     * 根据项目id查询预警信息
     * @param projectId
     * @return
     */
    public ElectricWarnDTO getEleWarnByProId(String projectId);

    /**
     * 跟新预警信息
     * @param electricWarn
     * @return
     */
    public boolean updateEleWarn(ElectricWarnDTO electricWarn,UserPropertyStaffEntity userPropertyStaffEntity);
}
