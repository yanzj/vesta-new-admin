package com.maxrocky.vesta.application;

import com.maxrocky.vesta.application.admin.dto.BillInfoAdminListResDto;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by liuwei on 2016/3/5.
 */
public interface BillInfoService {
    List<BillInfoAdminListResDto> getBillInfoList(String projectId, WebPage webPage);

    ApiResult updateBillInfoStatus(UserPropertyStaffEntity userPropertystaffEntity, String billInfoId) throws Exception;


    String exportExcel(UserPropertyStaffEntity userPropertystaffEntity, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;
}
