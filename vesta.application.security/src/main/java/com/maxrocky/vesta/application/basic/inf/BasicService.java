package com.maxrocky.vesta.application.basic.inf;

import com.maxrocky.vesta.application.basic.DTO.AssessTempDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Jason on 2017/6/15.
 */
public interface BasicService {
    /**
     * 考核模板导入
     *
     * @param fis
     * @param domain
     * @param userInformationEntity
     * @return
     */
    String importAssessTempExcel(InputStream fis, String domain, UserInformationEntity userInformationEntity);

    /**
     * 查询考核模板列表信息
     *
     * @param assessTempDTO
     * @param webPage
     * @return
     */
    List<AssessTempDTO> getAssessTempList(AssessTempDTO assessTempDTO, WebPage webPage);

    /**
     * 下载考评模板
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    String exportModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * 导出考核管理Excel
     *
     * @param title
     * @param headers
     * @param out
     * @param assessTempDTO
     * @param userInformationEntity
     */
    void exportAssessmentTempExcel(String title, String[] headers, ServletOutputStream out, AssessTempDTO assessTempDTO, UserInformationEntity userInformationEntity);
}
