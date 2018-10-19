package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.HousingOrdersDTO;

/**
 * Created by Magic on 2017/2/8.
 */
public interface BasicHousingOrdersService {
    /**
     * CreateBy:Magic
     * Description:接收交房单信息
     * param housingOrders：交房单参数
     * ModifyBy:
     */
    String sethousing(HousingOrdersDTO housingOrders);
}
