package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.PropertyRectifyCRMEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairCRMEntity;

/**
 * Created by liudongxin on 2016/5/6.
 * 报修/整改信息同步接口
 */
public interface RepairClientService {
    String getPropertyRepair(PropertyRepairCRMEntity propertyRepair,
                             PropertyRectifyCRMEntity propertyRectify);
}
