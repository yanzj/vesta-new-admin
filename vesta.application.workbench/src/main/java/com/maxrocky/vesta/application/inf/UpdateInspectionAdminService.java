package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by maxrocky on 2017/6/30.
 */
public interface UpdateInspectionAdminService {
    /**
     * 按项目编码重新查询正式交房交房单数据 推送给crm
     * */
    ApiResult updateCustomerDeliveryList(String projectNum);
}
