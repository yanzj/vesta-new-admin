package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/6/18.
 */
public interface AgencyService {
    //获取机构根目录
    List<AgencyTreeDTO> getAgencyRootList();
    //获取机构下级目录
    List<AgencyTreeDTO> getAgencyList(String parentId);
    //获取下级目录（名称带前缀）
    List<AgencyTreeDTO> getAgencyNext(String parentId,String agencyType);
    //获取下级部门
    List<AgencyTreeDTO> getAgencyFor1(String agencyId);
    //获取机构列表
    List<AgencyTreeDTO> getAllAgencyList();
    List<AgencyTreeDTO> getAgencyFullList();
    //OA同步机构列表
    List<AgencyTreeDTO> getOAAgencyList();
    //外部组织机构列表
    List<AgencyTreeDTO> getOuterAgencyList();
    //根据用户id 查询所在的机构
    List<AgencyTreeDTO> getOuterAgencyListById(String staffIdW);
    //根据机构下级列表 加分页
    List<AgencyDTO> AgencyParment(String parentId,String agencyName,WebPage webPage);
    //新增机构
    ApiResult addAgency(AgencyReceiveDTO agencyReceiveDTO);
    //根据项目id获取相关的机构
    List<AgencyAdminDTO> getAgencyByProjectId(String projectId,String permission);
    //根据项目id获取投诉对接人信息
    List<AgencyAdminDTO> getComplaintByProjectId(String projectId);
    //根据项目ID获取相关机构（不包含人）
    List<AgencyAdminDTO> agencyListByProjectId(String projectId);
    //根据项目ID获取相关人员
    List<AgencyAdminDTO> staffListByProjectId(String projectId);
    //根据角色ID获取相关的机构
    List<AgencyAdminDTO> getAgencyByRoleId(String roleSetId);
    //根据角色ID获取相关的机构（不包含人）
    List<AgencyAdminDTO> agencyListByRoleId(String roleSetId);
    //根据角色ID获取相关人员
    List<AgencyAdminDTO> staffListByRoleSetId(String roleSetId);
    //修改机构
    ApiResult updateAgency(AgencyReceiveDTO agencyReceiveDTO);
    //删除机构
    void delAgency(AgencyTreeDTO agencyTreeDTO);
    //根据机构ID获取人员列表
    ApiResult getStaffListByAgency(String agencyId);
    //根据机构ID和页数获取人员列表
    List<AgencyDTO> staffListFor(AgencyAdminDTO agencyAdminDTO,WebPage webPage);
    //根据条件获取机构外人员列表
    List<StaffDTO> getAgencyOutStaff(AgencyAdminDTO agencyAdminDTO);
    //新增机构与人员关系
    void saveAgencyStaff(AgencyStaffDTO agencyStaffDTO);
    //根据机构ID查询机构详情
    AgencyDTO getAgencyDetail(String agencyId);
    //根据机构ID获取关联角色列表
    List<AppRolesetDTO> getAppRoleSetByAgencyId(String agencyId);
    //根据机构ID获取关联项目列表
    List<OrganizeProjectDTO> getProjectByAgencyId(String agencyId);
    //新增人员
    String importPeopleExcel(InputStream is,UserPropertyStaffEntity userPropertyStaffEntity);
    //机构与人员的下载版
    String exportPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    //外部人员导入模板下载
    String exportOuterPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    //客户关系外部人员导入模板下载
    String qcExportOuterPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    //根据机构ID查询该机构下所有人员
    List<UserStaffDTO> getStaffListByAgencyId(String agencyId);
    //条件检索OA同步人员
    List<UserStaffDTO> conditionQueryUser(UserStaffDTO userStaffDTO,WebPage webPage);
    //启用或停用外部人员
    void startStaff(StaffBatchDTO staffBatchDTO);
    //启用或停用内部人员
    void startInsideStaff(StaffBatchDTO staffBatchDTO);
    //获取组织机构
    Map getAgencys();
    //获取人员信息
    UserManageDTO getUserManage(String staffId);
    //删除人员
    void toDeleteUser(String staffId);
    //批量删除人员
    void batchDelete(StaffBatchDTO staffBatchDTO);
    //修改人员信息
    String updateStaff(UserManageDTO userManageDTO,UserInformationEntity userPropertyStaffEntity);
    //新增人员信息
    String saveStaff(UserManageDTO userManageDTO,UserInformationEntity userPropertyStaffEntity);
    //新增外部合作单位组织机构
    ApiResult saveOuterAgency(OuterAgencyDTO outerAgencyDTO,UserInformationEntity userPropertyStaffEntity);
    // 删除外部合作单位组织架构
    ApiResult delOuterAgency(String agencyId,UserInformationEntity userPropertyStaffEntity);
    //条件检索已启用人员
    List<EnabledUserDTO> getEnabledUserList(EnabledUserDTO enabledUserDTO, WebPage webPage);
    //条件检索外部合作单位人员
    List<OuterUserDTO> getOuterUserList(OuterUserDTO outerUserDTO , WebPage webPage);
    //导出Excel
    void enabledUserExcel(String title, String[] headers, ServletOutputStream out, EnabledUserDTO enabledUserDTO);
    //外部人员导出Excel
    void outerUserExcel(String title, String[] headers, ServletOutputStream out, OuterUserDTO outerUserDTO);
    //新权限外部人员导入新增
    String importOuterPeopleExcel(InputStream fis,UserInformationEntity userPropertyStaffEntity);
    //根据组织机构Id查询组织机构
    AgencyStateDTO getDelAgencyState(String agencyId);
    //同步OA人员数据
    String getOAUserData();
    //同步OA组织机构数据
    String getOAAgencyData();
    //迁移历史数据
    void getUserAndAgencyByHistoryTable();
    void getUserByHistoryTable();
    void getUserAndAgencyAndRoleByHistoryTable();


    //根据机构ID和页数获取人员列表
    AuthSupplierRoleUserDTO getAuthAgencySupplierUser(AuthSupplierRoleUserDTO authAgencyDTO,WebPage webPage);


    // 查询项目 责任单位 人员
    AuthSupplierPeopleDTO getAuthStaffDetail(AuthSupplierPeopleDTO authSupplierPeopleDTO);

    AuthSupplierPeopleDTO getsupplierName(AuthSupplierPeopleDTO authSupplierPeopleDTO);

    List<AuthSupplierAgencyRoleDTO> getAllAuthSupplierAgencyRole(AuthSupplierPeopleDTO authSupplierPeopleDTO);

}
