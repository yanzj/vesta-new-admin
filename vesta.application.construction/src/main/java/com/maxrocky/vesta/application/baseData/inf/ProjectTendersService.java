package com.maxrocky.vesta.application.baseData.inf;

import com.maxrocky.vesta.application.baseData.adminDTO.AuthSupplierDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.ProjectTendersDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.TreeCategoryDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
public interface ProjectTendersService {
    /**
     * @param projectTendersDTO
     * 新增标段
     */
    void addProjectTenders(ProjectTendersDTO projectTendersDTO,UserInformationEntity userPropertyStaffEntity);

    /**
     * @param projectTendersDTO
     * 更新标段
     */
    void updateProjectTenders(ProjectTendersDTO projectTendersDTO);

    /**
     * @param tenderId
     * 删除标段
     */
    void delProjectTenders(String tenderId);

    /**
     * @param tenderId
     * @return
     * 获取标段详情
     */
    ProjectTendersDTO getTendersDetail(String tenderId);


    /**
     * @param projectId
     * @param webPage
     * @return 根据项目获取标段列表
     */
    List<ProjectTendersDTO> getTenderList(String projectId,WebPage webPage);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return
     * 同步标段与三级分类数据
     */
    ApiResult getTendCategory(String projectId,String timeStamp,String autoId);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return
     * 同步标段与楼栋数据
     */
    ApiResult getTendBuild(String projectId,String timeStamp,String autoId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 新建标段下，获取对应总包
     */
    List<AgencyEntity> getAgencyList(String projectId);
    /**.
     *  @Date:
     *  @Description: 新建标段下，获取对应总包
     */
    List<AuthSupplierDTO> getAuthSupplierList(String projectId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description:
     */
    List<TreeCategoryDTO> getCategoryByParentId(String parentId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description:
     */
    List<TreeCategoryDTO> getCategoryByModelId(String modelId);
}
