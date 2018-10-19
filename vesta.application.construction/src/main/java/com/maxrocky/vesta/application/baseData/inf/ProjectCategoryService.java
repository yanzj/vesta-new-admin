package com.maxrocky.vesta.application.baseData.inf;

import com.maxrocky.vesta.application.baseData.adminDTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AuthSupplierRoleUserDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chen on 2016/11/1.
 */
public interface ProjectCategoryService {

    /**
     * @param autoId
     * @param timeStamp
     * @return
     * 与前台同步工程阶段三级分类数据
     */
    ApiResult getCategoryForTime(String autoId,String timeStamp);

    /**
     * @param projectId
     * @param autoId
     * @param timeStamp
     * @return
     * 与前台同步工程阶段检查项与责任单位关系数据
     */
    ApiResult getCategoryDutyForTime(String projectId,String autoId,String timeStamp);

    /**
     * @param projectId
     * @param autoId
     * @param timeStamp
     * @return
     * 与前台同步工程阶段责任单位与楼栋关系数据
     */
    ApiResult getBuildDutyForTime(String projectId,String autoId,String timeStamp);

    /**
     * @param autoId
     * @param timeStamp
     * @return
     * 同步工程阶段检查指标数据
     */
    ApiResult getProjectTargetForTime(String autoId,String timeStamp);

    /**
     * @param dutyId
     * @return
     * 根据责任单位ID获取对应的检查项信息列表
     */
    List<SupplierCategoryDTO> getSupplierCategory(String dutyId,String domain);

    /**
     * @param supplierCategoryDTO
     * 修改责任单位检查项默认数据
     */
    void updateSupplierCategory(SupplierCategoryDTO supplierCategoryDTO);

    /**
     * @param categoryReceiveDTO
     * 保存责任单位与检查项关系
     */
    void addSupplierCategory(CategoryReceiveDTO categoryReceiveDTO);

    /**
     * @return
     * 获取责任单位列表
     */
    List<ProjectEmployDTO> getEmploys();

    /**
     * @return
     * 获取监理列表
     */
    List<ProjectEmployDTO> getSurveyors();

    /**
     * @return
     * 根据责任单位ID获取一级检查项
     */
    List<CategoryTreeDTO> getRootCategorys(String dutyId,String domain,String parentId);

    /**
     * @param categoryId
     * @return
     * 根据当前ID获取下级检查项列表
     */
    List<CategoryTreeDTO> getCategoryByPId(String categoryId);

    /**
     * @param domain
     * @return
     * 根据责任单位和模块获取所有检查项
     */
    List<CategoryTreeDTO> getCategoryAllByDutyId(String domain,String dutyId);

    /**
     * @param domain
     * @param categoryId
     * @return
     * 根据模块获取检查项信息
     */
    List<CategoryTreeDTO> getCategoryTree(String domain,String categoryId);

    /**
     * @param categoryId
     * @return
     * 根据检查项获取指标列表
     */
    ApiResult getTargetList(String categoryId);

    /**
     * @param categoryId
     * @return
     * 获取检查项详情
     */
    ApiResult getCategory(String categoryId);

    /**
     * @param domain
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     * 检查项下载模板
     */
    String exportModel(String domain,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * @param fis
     * @param domain
     * @return
     * 导入检查项
     */
    String importCategoryExcel(InputStream fis, String domain,UserInformationEntity user);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 保存标段和检查项关系
     */
    void addTenderCategory(TenderCategoryReceiveDTO tenderCategoryReceiveDTO);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 根据标段id获取一级检查项
     */
    List<CategoryTreeDTO> getRootCategoryByTenderId(String tenderId, String domain, String parentId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 根据标段和模块获取所有检查项
     */
    List<CategoryTreeDTO> getCategoryAllByTenderId(String domain, String tenderId);

    /**
     * @param categoryId
     * @param categoryName
     * @return
     * 修改检查项名字
     */
    ApiResult altCategory(String categoryId,String categoryName);

    /**
     * @param categoryId
     * @return
     * 删除检查项
     */
    ApiResult delCategory(String categoryId);

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param domain
     * @return
     * @throws IOException
     * 导出检查项
     */
    String exportCategory(String domain,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * 机构与人员的下载版
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    String exportMechanismPeopleModel( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);


    /**
     * 机构与人员的下载版
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    String authExportMechanismPeopleModel(AuthSupplierRoleUserDTO authSupplierRoleUserDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * 机构人员的导入
     * @param fis
     * @param projectId
     * @param userInformationEntity
     * @return
     */
    String importMechanismPeopleExcel(InputStream fis, String projectId, UserInformationEntity userInformationEntity);

    /**
     * 机构人员的导入
     * @param fis
     * @param projectId
     * @param userInformationEntity
     * @return
     */
    String importAuthMechanismPeopleExcel(InputStream fis, String projectId, UserInformationEntity userInformationEntity);

    String importMechanismPeopleExcel(InputStream fis, String projectId, UserPropertyStaffEntity userPropertyStaffEntity, String fileName, List<String> attributes, int attributesNotNullNumber);
}
