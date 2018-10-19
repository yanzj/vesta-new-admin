package com.maxrocky.vesta.application.projectLeadersCheck.inf;

import com.maxrocky.vesta.application.projectKeyProcesses.DTO.CheckForUpdateDTO;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.KeyProcessesCheckForUpdateDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.DTO.ProjectLeadersCheckDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Jason on 2017/3/28.
 */
public interface ProjectLeadersCheckService {

    /**
     * 分页获取领导检查列表
     *
     * @param projectLeadersCheckDTO
     * @param webPage
     * @param userInformationEntity
     * @return
     */
    List<ProjectLeadersCheckDTO> getLeaderCheckList(ProjectLeadersCheckDTO projectLeadersCheckDTO, WebPage webPage,  UserInformationEntity userInformationEntity);

    /**
     * 查看领导检查详情
     *
     * @param checkId
     * @return
     */
    ProjectLeadersCheckDTO getLeaderCheckDetailByCheckId(String checkId);

    /**
     * 执行非正常关闭
     *
     * @param projectLeadersCheckDTO
     * @param userInformationEntity
     * @return
     */
    String executeAbnormalOffState(ProjectLeadersCheckDTO projectLeadersCheckDTO, UserInformationEntity userInformationEntity);

    /**
     * 导出EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param projectLeadersCheckDTO
     * @param userInformationEntity
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, ProjectLeadersCheckDTO projectLeadersCheckDTO, UserInformationEntity userInformationEntity);
}
