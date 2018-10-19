package com.maxrocky.vesta.application.contest.inf;

import com.maxrocky.vesta.application.contest.DTO.ContestListDTO;
import com.maxrocky.vesta.application.contest.DTO.ContestSearchDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 安全比武
 * Created by yuanyn on 2017/9/1.
 */
public interface ContestService {

    /**
     * 条件检索问题列表
     */
    List<ContestListDTO> getContestList(ContestSearchDTO contestSearchDTO, WebPage webPage);

    /**
     * Describe:安全大比武 导出EXCEL
     */
    void contestExcel(String title, String[] headers, ServletOutputStream out, ContestSearchDTO contestSearchDTO, UserInformationEntity userInformationEntity);

}
