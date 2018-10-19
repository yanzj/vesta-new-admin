package com.maxrocky.vesta.application.baseData.inf;

import com.maxrocky.vesta.application.baseData.adminDTO.ProjectBuildingDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.ProjectFloorDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/10/18.
 */
public interface ProjectBuildingService {
    /**
     * 根据项目ID获取楼栋列表
     *
     * @param projectId
     * @return
     */
    List<ProjectBuildingDTO> getProjectBuildings(String projectId, WebPage webPage);

    /**
     * 根据项目ID获取楼栋列表
     *
     * @param projectId
     * @return
     */
    List<ProjectBuildingDTO> getBuildingList(String projectId);

    List<ProjectBuildingDTO> getBuildsByProjectId(String projectId, String tendId);

    /**
     * 获取工程楼栋详情
     *
     * @param buildId
     * @return
     */
    ProjectBuildingDTO getProjectBuildingDetail(String buildId);

    /**
     * 根据楼栋ID删除工程楼栋
     *
     * @param buildId
     */
    void delProjectBuilding(String buildId);

    /**
     * 更新工程楼栋信息
     *
     * @param projectBuildingDTO
     */
    ApiResult updateProjectBuilding(ProjectBuildingDTO projectBuildingDTO, String projectId);

    /**
     * 获取工程楼层列表
     *
     * @param buildId
     * @return
     */
    List<ProjectFloorDTO> getProjectFloors(String buildId, WebPage webPage);

    /**
     * 获取工程楼层列表
     *
     * @param buildingId
     * @return
     */
    Map<String,String> getProjectFloors(String buildingId);

    /**
     * 删除楼层
     *
     * @param floorID
     */
    void delProjectFloor(String floorID, UserInformationEntity userPropertyStaffEntity);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼栋，添加信息为楼栋名称
     */
    ApiResult insertBuilding(String buildingName, String projectId);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼层
     */
    String insertFloor(ProjectFloorDTO projectFloorDTO, UserInformationEntity userInformationEntity);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量添加楼层
     */
    void executeFloor(ProjectFloorDTO projectFloorDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 修改楼层
     */
    String updateFloor(ProjectFloorDTO projectFloorDTO, UserInformationEntity userInformationEntity);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param: floorId
     * @Description: 获取该楼层对应的户型图
     */
    ProjectHouseImageEntity getHouseImgByFloorId(String floorId);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量修改楼层
     */
    void executeUpdateFloor(ProjectFloorDTO projectFloorDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 获取最新的户型图
     */
    ProjectHouseImageEntity getHouseImg();

    /**
     * @param projectId
     * @param timeStamp
     * @param autoNum
     * @return 同步工程楼栋数据
     */
    ApiResult getProjectBuildForTime(String projectId, String timeStamp, String autoNum);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoNum
     * @return 同步工程楼层信息数据
     */
    ApiResult getProjectFloorForTime(String projectId, String timeStamp, String autoNum);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoNum
     * @return 同步工程楼层图数据
     */
    ApiResult getFloorImageForTime(String projectId, String timeStamp, String autoNum);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导出excel
     */
    String exportProjectBuildingExcel(String projectId, WebPage webPage, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 下载模板
     */
    String downloadModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导入excel
     */
    boolean importExcelByPoi(UserInformationEntity user, InputStream fis, String projectId);

    Map getBuildListByProjectId(String projectId);

    /**
     * 获取projectIds中楼栋最多的
     * @param projectIds
     * @return
     */
    Map getBuildListByProjectIds(List<String> projectIds);

    /**
     * @param dutyId
     * @return 获取责任单位下所有楼栋ID
     */
    List<String> getDutysBuildId(String dutyId);

    /**
     * 楼栋下载模板
     *
     * @param title
     * @param headers
     * @param out
     * @return
     */
    void downloadModel(String title, String[] headers, ServletOutputStream out);

    /**
     * 楼栋批量导入数据
     *
     * @param userPropertyStaffEntity
     * @param fis
     * @param projectId
     * @return
     */
    boolean importBuildsExcel(UserInformationEntity userPropertyStaffEntity, InputStream fis, String projectId);

    /**
     * 批量修改楼层
     *
     * @param projectFloorDTO
     * @param userInformationEntity
     */
    void updateBatchFloorFromJason(ProjectFloorDTO projectFloorDTO, UserInformationEntity userInformationEntity);

    /**
     * 根据总包ID查询楼栋信息
     *
     * @param supplierId
     * @return
     */
    List<ProjectBuildingDTO> getBuildersBySupplierId(String supplierId,String projectId);
}
