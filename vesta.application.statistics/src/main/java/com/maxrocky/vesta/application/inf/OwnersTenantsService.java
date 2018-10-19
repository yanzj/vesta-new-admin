package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.OwnersLeaseStatisticsDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 *  业主、租户统计 业务逻辑层接口
 */
public interface OwnersTenantsService {

    /**
     * 初始化业主、租户统计
     * @param user
     * @param clickTimes
     * @return
     */
    List<OwnersLeaseStatisticsDTO> ownersLease(UserPropertyStaffEntity user,ClickTimesSeachDTO clickTimes);
}
