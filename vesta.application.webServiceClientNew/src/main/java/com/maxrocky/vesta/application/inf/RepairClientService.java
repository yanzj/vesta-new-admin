package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.PropertyRectifyCRMEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairCRMEntity;

/**
 * Created by liudongxin on 2016/5/6.
 */
public interface RepairClientService {
    String getPropertyRepair(PropertyRepairCRMEntity propertyRepair,
                             PropertyRectifyCRMEntity propertyRectify);
}
