package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.json.UI0002.RegisterParamJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.exception.GeneralException;

/**
 * Created by sunmei on 2016/3/16.
 */
public interface UserTenantService {
    /**
     * Code:UI0002
     * Type:UI Method
     * Describe:租户注册
     */
    ApiResult register(RegisterParamJsonDTO registerParamJsonDTO) throws GeneralException;
}
