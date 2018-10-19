package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.WorkOrderDetailDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.exception.GeneralException;

import java.util.List;

/**
 * Created by liudongxin on 2016/1/14.
 * 物业报修图片接口方法
 */
public interface PropertyImageService {
    /**
     * 查看报修图片
     * @param id
     * @return
     */
    List<PropertyImageEntity> getImageUrl(String id);

    /**
     * 添加报修图片
     * @param
     */
    ApiResult createImage(WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException;
}