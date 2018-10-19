package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyRepairDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修评价方法接口
 */
public interface PropertyFeedbackService {
    /**
     * 添加评价(业主端)
     * @param user
     * @param propertyRepairDTO
     */
    ApiResult createPropertyFeedback(UserInfoEntity user,PropertyRepairDTO propertyRepairDTO) throws GeneralException;

    /**
     * 添加评价(员工端：随手报)
     * @param user
     * @param propertyRepairDTO
     */
    ApiResult reportsEvaluate(UserPropertyStaffEntity user,PropertyRepairDTO propertyRepairDTO) throws GeneralException;
}
