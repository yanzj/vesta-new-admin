package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.SellerEvaluateDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerSeachDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;

import java.util.List;

/**
 * Created by chen on 2016/1/17.
 */
public interface SellerEvaluateService {
    /**新增评价*/
    ApiResult AddEvaluate(UserInfoEntity userInfoEntity,SellerSeachDTO sellerSeachDTO);
    /**修改评价*/
    void UpdateEvaluate(String evaluateId);
    /**查询好评排行榜*/
    List<SellerEvaluateDTO> getReputationList();
}
