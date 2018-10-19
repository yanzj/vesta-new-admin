package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.CustomerDeliveryEntity;
import com.maxrocky.vesta.domain.model.HouseOpenEntity;
import com.maxrocky.vesta.domain.model.InternalacceptanceHouseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by liudongxin on 2016/5/23.
 * CRM交验房同步更新
 */
public interface InspectionService {
    String getInspection(InternalacceptanceHouseEntity internalacceptanceHouseEntity,
                         HouseOpenEntity HouseOpenEntity,
                         CustomerDeliveryEntity customerDeliveryEntity);
}
