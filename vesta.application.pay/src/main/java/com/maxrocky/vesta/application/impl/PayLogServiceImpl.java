package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.Json.JsonUtil;
import com.maxrocky.vesta.application.DTO.json.PayJsonDTO;
import com.maxrocky.vesta.application.inf.PayLogService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PayLogEntity;
import com.maxrocky.vesta.domain.repository.PayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tom on 2016/2/14 16:03.
 * Describe:支付日志Service接口实现类
 */
@Service
public class PayLogServiceImpl implements PayLogService {

    /* 支付日志 */
    @Autowired
    PayLogRepository payLogRepository;

    /**
     * Code:For Pay
     * Type:Log Method
     * Describe:创建支付日志
     * CreateBy:Tom
     * CreateOn:2016-02-14 04:04:24
     */
    @Override
    public ApiResult createPayLog(PayJsonDTO payJsonDTO) {
        if(payJsonDTO == null){
            return new ErrorApiResult("error_00000002");
        }
        payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                                               , JsonUtil.toJson(payJsonDTO)
                                               , PayLogEntity.TYPE_REQUEST));
        return new SuccessApiResult();
    }

    /**
     * Code:For Pay
     * Type:Log Method
     * Describe:创建支付日志
     * CreateBy:Tom
     * CreateOn:2016-02-20 11:28:19
     */
    @Override
    public ApiResult createPayLog(String paymentId, String content, String type) {
        payLogRepository.create(new PayLogEntity(paymentId, content, type));
        return new SuccessApiResult();
    }
}
