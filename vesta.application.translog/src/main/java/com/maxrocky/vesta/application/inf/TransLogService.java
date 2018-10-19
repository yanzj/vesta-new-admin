package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.TransLogEntity;
import org.springframework.ui.ModelMap;

/**
 * Created by JillChen on 2016/1/26.
 */
public interface TransLogService {


    void saveOut(String vestaToken, Object object, TransLogEntity transLogEntity);

    TransLogEntity saveIn(TransLogEntity transLogEntity, Object modelIn);
}
