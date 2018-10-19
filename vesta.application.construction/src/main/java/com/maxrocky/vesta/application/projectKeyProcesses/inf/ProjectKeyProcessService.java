package com.maxrocky.vesta.application.projectKeyProcesses.inf;

import com.maxrocky.vesta.application.projectKeyProcesses.DTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Talent on 2016/11/22.
 */
public interface ProjectKeyProcessService {
    /**
     * 通过工序ID查询工序信息
     *
     * @param processId
     * @return
     */
    KeyProcessesBackDTO getKeyProcessesBackInfoByProcessId(String processId);

    /**
     * 新增关键工序信息
     *
     * @param keyProcessesApplyListDTO
     * @return
     */
    ApiResult addKeyProcesses(KeyProcessesApplyListDTO keyProcessesApplyListDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 检查关键工序是否有更新
     *
     * @param checkForUpdateDTO
     * @return
     */
    List<KeyProcessesCheckForUpdateDTO> keyProcessesCheckForUpdate(CheckForUpdateDTO checkForUpdateDTO,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 下载更新关键工序
     *
     * @param id
     * @param time
     * @param projectId
     * @return
     */
    KeyProcessesQuestionDTO getAllKeyProcessesQuestion(String id, String time, String projectId,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 关键工序统计
     *
     * @param projectId
     * @return
     */
    ApiResult addUpKeyProcessesByProjectId(String projectId);

    /**
     * 关键工序整改审核记录
     *
     * @param keyProcessesApplyAnnalListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    ApiResult keyProcessesAnnal(KeyProcessesApplyAnnalListDTO keyProcessesApplyAnnalListDTO, UserPropertyStaffEntity userPropertyStaffEntity);

//////////////////////////////////////////////////////////////////////////////////
    //后端接口

    /**
     * 关键工序列表
     *
     * @param projectKeyProcessesDTO
     * @param webPage
     * @return
     */
    List<ProjectKeyProcessesDTO> searchKeyProcessesList(ProjectKeyProcessesDTO projectKeyProcessesDTO, WebPage webPage, UserInformationEntity userInformationEntity);

    /**
     * 查看关键工序详细信息
     *
     * @param processesId
     * @return
     */
    ProjectKeyProcessesDTO searchKeyProcessesDetailByBatchId(String processesId);

    /**
     * 导出EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param projectKeyProcessesDTO
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, ProjectKeyProcessesDTO projectKeyProcessesDTO,UserInformationEntity userInformationEntity);

    /**
     * 关键工序统计
     *
     * @param keyProcessesDTO
     * @param webPage
     * @return
     */
    ProjectKeyProcessesCountListDTO searchKeyProcessesCountList(ProjectKeyProcessesDTO keyProcessesDTO, WebPage webPage,UserInformationEntity userInformationEntity);

    /**
     * 关键工序统计excel
     *
     * @param title
     * @param headers
     * @param out
     * @param keyProcessesDTO
     */
    void exportCountExcel(String title, String[] headers, ServletOutputStream out, ProjectKeyProcessesDTO keyProcessesDTO,UserInformationEntity userInformationEntity);

    /**
     * 查询自己的批次
     *
     * @param processId
     * @param staffId
     * @return
     */
    ApiResult searchKeyProcessByStaffId(String processId, String staffId);

    /**
     * 执行非正常关闭
     *
     * @return
     */
    String executeAbnormalOffState(ProjectKeyProcessesDTO keyProcessesDTO,UserInformationEntity userInformationEntity);

    /**
     * 获取自己的权限（是否有关单权限）
     *
     * @param projectId
     * @param staffId
     * @return
     */
    ApiResult searchAuthorityByStaffId(String projectId, String staffId);
}
