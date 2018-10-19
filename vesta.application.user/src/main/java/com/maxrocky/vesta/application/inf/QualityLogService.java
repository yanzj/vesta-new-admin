package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.QualityAccessLogDTO;
import com.maxrocky.vesta.domain.model.QualityLogEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Talent on 2016/11/16.
 */
public interface QualityLogService {
    /**
     * 新增日志信息
     *
     * @param qualityLogEntity
     * @return
     */
    void addQualityLogInfo(QualityLogEntity qualityLogEntity);

    /**
     * 日志列表
     *
     * @param qualityAccessLogDTO
     * @param webPage
     * @return
     */
    List<QualityAccessLogDTO> getQualityAccessLogList(QualityAccessLogDTO qualityAccessLogDTO, WebPage webPage);

    /**
     * 导出用户访问日志excel
     *
     * @param title
     * @param headers
     * @param out
     */
    void exportAccessExcel(String title, String[] headers, ServletOutputStream out,QualityAccessLogDTO qualityAccessLogDTO);
}
