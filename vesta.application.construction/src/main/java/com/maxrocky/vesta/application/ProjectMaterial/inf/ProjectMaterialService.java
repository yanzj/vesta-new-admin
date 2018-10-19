package com.maxrocky.vesta.application.ProjectMaterial.inf;

import com.maxrocky.vesta.application.ProjectMaterial.DTO.*;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CheckDailyPatrolInspectionDTO;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CheckDailyPatrolInspectionListDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Magic on 2016/11/29.
 */
public interface ProjectMaterialService {
    /**
     * 材料验收新增实体 批量
     * */
    ApiResult saveMaterialNew(ProjectMaterialListDTO projectMaterialListDTO,UserPropertyStaffEntity userProperty);

    /**
     * 材料验收新增实体 批量
     * */
    ApiResult saveMaterial(ProjectMaterialListDTO projectMaterialListDTO,UserPropertyStaffEntity userProperty);

    /**
     * 根据项目Id 时间 自增长id 增量查询材料验收  getMaterialByApp
     * */
    ProjectMaterialListDTO getMaterialList(String id,String time,String projectId,UserPropertyStaffEntity userProperty);

    /**
     * 材料验收增加退场纪录 支持批量
     * */
    ApiResult saveMaterialOut(ProjectMaterialOutListDTO materialOutListDTO,UserPropertyStaffEntity userProperty);

    /**
     * APP查询所有项目是否有数据更新
     * creaby  当前登录人员工id
     */
    List<CheckDailyPatrolInspectionDTO> checkDailyPatrolInspectionForApp(CheckDailyPatrolInspectionListDTO checkDailyPatrolInspectionList, String creaby);
    /**
     * 更具项目Id查询统计所有材料验收记录
     * */
    ApiResult searchMaterial(String projectId);


    /**
     * 后台查询材料验收列表
     * */
    List<ProjectMaterialAdminDTO> getMaterialAdmin(GetProjectMaterialDTO getProjectMaterialDTO, WebPage webPage, String creaBy);

    /**
     * 导出EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param getProjectMaterialDTO
     * @param webPage
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, GetProjectMaterialDTO getProjectMaterialDTO, WebPage webPage,String creaBy);

    /**
     * 材料验收查询详情
     * */
    MaterialAdminDTO getMaterialAdminByID(GetProjectMaterialDTO getProjectMaterialDTO,UserInformationEntity userInformationEntity);

    /**
     * 非正常关闭
     * */
    String shutDownAdmin(MaterialAdminDTO materialAdminDTO,UserInformationEntity userInformationEntity);
}
