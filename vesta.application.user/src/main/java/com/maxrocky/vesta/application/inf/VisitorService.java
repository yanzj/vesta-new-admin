package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.VisitorPassDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.IOException;
import java.util.List;

/**
 * 访客模块Service
 * Created by WeiYangDong on 2017/12/18.
 */
public interface VisitorService {

    /**
     * 获取访客通行列表
     */
    List<VisitorPassDTO> getVisitorPassList(VisitorPassDTO visitorPassDTO, WebPage webPage);

    /**
     * 获取访客通行日志列表
     */
    List<VisitorPassDTO> getVisitorPassLogList(VisitorPassDTO visitorPassDTO, WebPage webPage);

}
