package com.maxrocky.vesta.application.inspectAcceptance.inf;

import com.maxrocky.vesta.application.DTO.QuestionUpdateCheckDTO;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by jiazefeng on 2016/10/17.
 */
public interface InspectAcceptanceService {
    /**
     * 检索检索验收的问题清单
     *
     * @param inspectAcceptanceDTO
     * @param webPage
     * @return
     */
    List<InspectAcceptanceDTO> searchInspectAcceptanceList(InspectAcceptanceDTO inspectAcceptanceDTO, WebPage webPage,String staffId);

    /**
     * 新增工程验收信息
     *
     * @param userPropertyStaffEntity
     * @param projectAcceptanceQuestionDTO
     * @return
     */
    ApiResult addAcceptanceInfo(UserPropertyStaffEntity userPropertyStaffEntity, ProjectAcceptanceQuestionDTO projectAcceptanceQuestionDTO);

    /**
     * 根据项目检索 统计工程检查列表 （APP）
     *
     * @param projectNum
     * @return
     */
    ApiResult searchAcceptanceListByProjectNum(String projectNum) throws Exception;

    /**
     * 根据参数检索检查批次列表（APP）
     *
     * @param buildingId
     * @param projectCategoryId
     * @return
     */
    ApiResult searchAcceptanceBatchListByParameter(String buildingId, String projectCategoryId);

    /**
     * 检索工程检查批次详情
     *
     * @param batchId
     * @return
     */
    ProjectAcceptanceBatchDTO searchAcceptanceBatchInfo(String batchId);

    /**
     * 修改工程检查验收批次详情
     *
     * @param projectAcceptanceQuestionDTO
     * @return
     */
    ApiResult modifyAcceptanceBatchInfo(UserPropertyStaffEntity userPropertyStaffEntity, ProjectAcceptanceQuestionDTO projectAcceptanceQuestionDTO);

    /**
     * 检查工程验收是否有更新
     *
     * @param projectNum
     * @param beginDateNew
     * @param id
     * @return
     */
    QuestionUpdateCheckDTO searchToUpdateByType(String id, String beginDateNew, String projectNum);

    /**
     * 下载工程检查批次列表
     *
     * @param id
     * @param timeStamp
     * @param projectNum
     * @return
     */
    ProjectAcceptanceQuestionDTO getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 检查工程验收是否有更新
     *
     * @return
     */
    List<CheckForUpdateToAcceptanceDTO> checkForUpdatesToAcceptance(CheckForUpdate checkForUpdate,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 根据批次id查询批次详情（管理后台）
     *
     * @param batchId
     * @return
     */
    InspectAcceptanceDTO searchAcceptanceBatchInfoByBatchId(String batchId);

    /**
     * 导出Excel
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @throws Exception
     */
    public void exportExcel(String title, String[] headers, OutputStream out, InspectAcceptanceDTO inspectAcceptanceDTO,UserInformationEntity userInformationEntity) throws Exception;

    /**
     * 统计列表
     *
     * @param inspectAcceptanceDTO
     * @param webPage
     * @return
     */
    ProjectAcceptanceStatisticsDTO searchInspectAcceptanceCountList(InspectAcceptanceDTO inspectAcceptanceDTO, WebPage webPage,String staffId);

    /**
     * 执行非正常关闭
     *
     */
    String executeAbnormalOffState(InspectAcceptanceDTO inspectAcceptanceDTO, UserInformationEntity userInformationEntity);

    /**
     * 查询自己的批次信息
     *
     * @param batchId
     * @param staffId
     * @return
     */
    ApiResult searchAcceptanceBatchInfoByStaffId(String batchId, String staffId);

    /**
     * 按条件查询列表（不带分页）
     *
     * @param inspectAcceptanceDTO
     * @return
     */
    List<InspectAcceptanceDTO> searchInspectAcceptanceAllList(InspectAcceptanceDTO inspectAcceptanceDTO, UserInformationEntity userInformationEntity);

    /**
     * 查询全部列表
     *
     * @return
     */
    List<InspectAcceptanceDTO> searchInspectAcceptanceAllList();

    /**
     * 导出统计EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param inspectAcceptanceDTO
     * @param webPage
     */
    void exportCountExcel(String title, String[] headers, ServletOutputStream out, InspectAcceptanceDTO inspectAcceptanceDTO, WebPage webPage, UserInformationEntity userInformationEntity);

    /**
     * 查询自己的权限（是否有关单权限）
     *
     * @param projectId
     * @param staffId
     * @return
     */
    ApiResult searchAuthorityByStaffId(String projectId, String staffId);
    /**
     * 查询自己的权限（是否有关单权限）
     *
     * @param projectId
     * @param staffId
     * @return
     */
    boolean getAuthorityByStaffId(String projectId, String staffId);
}
