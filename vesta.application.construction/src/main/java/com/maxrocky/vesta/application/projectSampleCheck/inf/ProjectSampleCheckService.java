package com.maxrocky.vesta.application.projectSampleCheck.inf;

import com.maxrocky.vesta.application.projectSampleCheck.DTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 */
public interface ProjectSampleCheckService {
    /**
     * 样板点评save 批量
     */
    ApiResult saveProjectSampleCheck(ProjectSampleCheckListDTO projectSampleCheckListDTO, UserPropertyStaffEntity user);

    /**
     * 检查样板点评是否有更新
     *
     * @param checkForUpdateDTO
     * @param userPropertyStaffEntity
     * @return
     */
    List<SampleCheckForUpdateDTO> sampleCheckForUpdate(CheckForUpdateDTO checkForUpdateDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 下载更新样板点评的数据
     *
     * @param id
     * @param time
     * @param projectId
     * @param userPropertyStaffEntity
     * @return
     */
    ProjectSampleCheckListDTO getAllKeyProcessesQuestion(String id, String time, String projectId, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 按项目统计样板点评数据
     **/
    ApiResult searchSampleCheck(String projectId);

    /**
     * 样板点评指标整改
     */
    ApiResult updageProjectSampleCheck(ProjectUpSamplCheckListDTO projectUpSamplCheckListDTO, UserPropertyStaffEntity user);

    /**
     * 后台查询样板点评列表
     */
    List<ProjectSampleCheckAdminListDTO> getSampleCheckAdmin(RequestSampleAdminDTO requestSampleAdminDTO, WebPage webPage, String creaBy);

    /**
     * 导出EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param requestSampleAdminDTO
     * @param webPage
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, RequestSampleAdminDTO requestSampleAdminDTO, WebPage webPage, String creaBy);

    /**
     * 统计列表
     */
    ProjectSampleCheckCountListDTO getSampleCheckCount(RequestSampleAdminDTO requestSampleAdminDTO, WebPage webPage, String creaBy);

    /**
     * 检索样板点评详情
     *
     * @param sampleCheckId
     * @return
     */
    ProjectSampleCheckAdminBackDTO searchSampleCheckDetailBySampleCheckId(String sampleCheckId);

    /**
     * 执行非正常关闭
     *
     * @param backDTO
     * @param userInformationEntity
     * @return
     */
    String executeAbnormalOffState(ProjectSampleCheckAdminBackDTO backDTO, UserInformationEntity userInformationEntity);

    /**
     * 导出PPT
     *
     * @param out
     * @param projectSampleCheckDTO
     */
    void exportPPT(HttpServletRequest httpServletRequest,ServletOutputStream out, ProjectSampleCheckAdminBackDTO projectSampleCheckDTO) throws Exception;

     /*
     * 样板点评统计导出excel
     * */
    void exportCountExcel(String title, String[] headers, ServletOutputStream out, RequestSampleAdminDTO requestSampleAdminDTO, UserInformationEntity userInformationEntity, WebPage webPage);
}
