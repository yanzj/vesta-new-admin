package com.maxrocky.vesta.application.ProjectMaterial.inf;

import com.maxrocky.vesta.application.ProjectMaterial.DTO.AppCountNumberDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

/**
 * Created by Magic on 2016/11/30.
 */
public interface AppCountNBService {
    /**
     *app待办事项统计列表
     * 只统计整改中
     * */
    ApiResult getAppCountNB(AppCountNumberDTO appCountNumberDTO,UserPropertyStaffEntity userProperty);
}
