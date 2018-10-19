package com.maxrocky.vesta.application.dailyPatrolInspection.inf;

import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Magic on 2016/10/17.
 */
public interface DailyPatrolInspectionService {
    /**
     * 查询日常巡检列表
     */
    List<DailyPatrolInspectionDTO> getInspection(GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO, WebPage webPage, String creaBy);


    /**
     * APP新增日常巡检单
     * Magic 2016/10/24
     */

    ApiResult saveDailyPatrolInspectionForApp(PostDailyPatrolInspectionListDTO DailyList, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * APP按项目查询
     * Magic 2016/10/24
     */
    PostDailyPatrolInspectionListDTO getDailyPatrolInspectionForApp(String id, String timeStamp, String projectId, String creaid);

    /**
     * APP查询所有项目是否有数据更新
     * creaby  当前登录人员工id
     */
    List<CheckDailyPatrolInspectionDTO> checkDailyPatrolInspectionForApp(CheckDailyPatrolInspectionListDTO checkDailyPatrolInspectionList, String creaby);

    /**
     * APP按项目查询统计列表
     * Magic 2016/10/24
     */

    ApiResult searchInspection(String projectId);

    /**
     * APP待办事项查询
     * Magic 2016/10/24
     */
    PostDailyPatrolInspectionListDTO getInspectionForAppTodo(String id, String timeStamp, String creaid);

    /**
     * 判断戴白事项是否有数据更新
     */
    String checkInspectionForAppTodo(String id, String timeStamp, String creaid);

    /**
     * 日常巡检处理问题（乙方）
     *
     * @param fromPartyBProblemSolvingListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    ApiResult fromPartyBProblemSolving(FromPartyBProblemSolvingListDTO fromPartyBProblemSolvingListDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 日常巡检处理问题（第三方监理）
     *
     * @param fromPartyBProblemSolvingListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    ApiResult thirdPartySupervisionRectification(FromPartyBProblemSolvingListDTO fromPartyBProblemSolvingListDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 日常巡检处理问题（甲方）
     *
     * @param fromPartyBProblemSolvingListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    ApiResult partyADealWith(FromPartyBProblemSolvingListDTO fromPartyBProblemSolvingListDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 日常巡检统计列表（楼栋）
     *
     * @param patrolInspectionCountDTO
     * @param webPage
     * @return
     */
    ProjectDailyPatrolInspectionDTO searchdailyPatrolInspectionCountList(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy);

    /**
     * 日常巡检统计列表(项目)
     *
     * @param patrolInspectionCountDTO
     * @param webPage
     * @return
     */
    ProjectDailyPatrolInspectionDTO searchProjectInspectionCountList(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy);

    /**
     * 日常巡检统计列表（区域）
     *
     * @param patrolInspectionCountDTO
     * @param webPage
     * @return
     */
    ProjectDailyPatrolInspectionDTO searchPoreatInspectionCountList(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy);


    /**
     * 查询详情
     *
     * @return PostDailyPatrolInspectionDTO
     */
    GetDailyPatrolInspectionAdminDTO getInspectionByinspectionId(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin, UserInformationEntity userInformationEntity);

    /**
     * 导出统计EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param patrolInspectionCountDTO
     * @param webPage
     */
    void exportCountExcel(String title, String[] headers, ServletOutputStream out, ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy);

    /**
     * 第三方监理 + 甲方负责人  验收
     *
     * @param getDailyPatrolInspectionAdmin
     */
    String checkBeforeAcceptance(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin,  UserInformationEntity userInformationEntity);

    /**
     * 关闭
     */
    String shutDownAdmin(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin,UserInformationEntity userInformationEntity);

    /**
     * 导出EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param getDailyPatrolInspectionDTO
     * @param webPage
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO, WebPage webPage, String creaBy);

    /**
     * 草稿
     */

    GetDailyPatrolInspectionDraftDTO getDraftInspection(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin);


    /**
     * 草稿提交
     *
     * @param getDailyPatrolInspectionDraftDTO
     */
    String updateInspectionDraft(GetDailyPatrolInspectionDraftDTO getDailyPatrolInspectionDraftDTO, UserInformationEntity userInformationEntity);

    /**
     * 导出Word
     *
     * @param request
     * @param response
     * @param dailyInspectionDTO
     */
    void exportWord(HttpServletRequest request, HttpServletResponse response, List<GetDailyPatrolInspectionDraftDTO> dailyInspectionDTO);

    /**
     * 批量查询批次信息
     *
     * @param dailyPatrolInspectionAdminDTO
     * @return
     */
    List<GetDailyPatrolInspectionDraftDTO> getDraftInspectionList(GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO);
}
