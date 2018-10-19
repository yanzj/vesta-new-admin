package com.maxrocky.vesta.application.complain.inf;

import com.maxrocky.vesta.application.complain.DTO.ComplainDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Jason on 2017/7/26.
 */
public interface ComplainService {
    /**
     * 分页获取投诉单列表信息
     *
     * @param complainDTO
     * @param webPage
     * @param userInformationEntity
     * @return
     */
    List<ComplainDTO> getComplainList(ComplainDTO complainDTO, WebPage webPage, UserInformationEntity userInformationEntity);

    /**
     * 投诉单详情
     *
     * @param userInformationEntity
     * @param complainId
     * @return
     */
    ComplainDTO getComplainInfoById(UserInformationEntity userInformationEntity, String complainId);

    /**
     * 导出Excel
     *
     * @param title
     * @param headers
     * @param out
     * @param complainDTO
     * @param userInformationEntity
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, ComplainDTO complainDTO, UserInformationEntity userInformationEntity);
}
