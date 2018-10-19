package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by Tom on 2016/1/18 10:52.
 * Describe:业态Service接口
 */
public interface HouseFormatService {

    /**
     * Code:HI0002
     * Type:UI Method
     * Describe:获取业态列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 10:58:23
     */
    ApiResult getFormatList(String projectId);

}
