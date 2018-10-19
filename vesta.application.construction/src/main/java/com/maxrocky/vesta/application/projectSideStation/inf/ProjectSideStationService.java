package com.maxrocky.vesta.application.projectSideStation.inf;

import com.maxrocky.vesta.application.projectSideStation.DTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Talent on 2016/11/8.
 */
public interface ProjectSideStationService {

    /**
     * 检查旁站是否有更新
     *
     * @param checkForUpdates
     * @return
     */
    List<CheckForUpdatesFromSideStationDTO> checkForUpdatesFromSideStation(CheckForUpdates checkForUpdates,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 下载更新旁站信息
     *
     * @param id
     * @param beginDateNew
     * @param projectId
     * @return
     */
    ProjectSideStationListDTO getAllSideStationInfo(String id, String beginDateNew, String projectId,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 旁站统计列表
     *
     * @param projectId
     * @return
     */
    ApiResult getSideStationCountByProjectId(String projectId);

    /**
     * 新增旁站信息
     *
     * @param userPropertyStaffEntity
     * @param projectSideStationRequestListDTO
     * @return
     */
    ApiResult addSideStationInfo(UserPropertyStaffEntity userPropertyStaffEntity, ProjectSideStationRequestListDTO projectSideStationRequestListDTO);

    /**
     * 后端检索旁站信息列表
     *
     * @param projectSideStationInfoDTO
     * @param webPage
     * @return
     */
    List<ProjectSideStationInfoDTO> searchBesideStationList(ProjectSideStationInfoDTO projectSideStationInfoDTO, WebPage webPage,String staffId);

    /**
     * 旁站信息（前段返回）
     *
     * @param sideStationId
     * @return
     */
    ProjectSideStationDTO getProjectSideStationInfo(String sideStationId);

    /**
     * 旁站信息（后端返回）
     *
     * @param sideStationId
     * @return
     */
    ProjectSideStationInfoDTO getProjectSideStationInfoById(String sideStationId);

    /**
     * 导出excel
     *
     * @param title
     * @param headers
     * @param out
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out,ProjectSideStationInfoDTO projectSideStationInfoDTO,UserInformationEntity userInformationEntity);

    List<ProjectSideStationInfoDTO> getAllBesideStationList();
}
