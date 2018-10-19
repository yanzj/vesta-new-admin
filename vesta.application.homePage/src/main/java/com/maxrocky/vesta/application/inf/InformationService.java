package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;

/**
 * Created by Annie on 2016/2/22.
 */

public interface InformationService {
    ApiResult information( UserInfoEntity user);
}
