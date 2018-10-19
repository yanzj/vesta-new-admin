package com.maxrocky.vesta.application.projectAuthority.inf;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2018/3/23.
 */
public interface ProjectUserAuthorityService {

    //根据机构ID查询该机构下所有人员
    List<UserStaffDTO> getStaffListByAgencyId(String agencyId);
    //条件检索OA同步人员
    List<UserStaffDTO> conditionQueryProjectUser(UserStaffDTO userStaffDTO,WebPage webPage);
    //启用或停用内部人员
    void startInsideProjectStaff(StaffBatchDTO staffBatchDTO);
    //外部组织机构列表
    List<AgencyTreeDTO> getProjectOuterAgencyList();
    //根据用户id 查询所在的机构
    List<AgencyTreeDTO> getProjectOuterAgencyListById(String staffIdW);
    //条件检索外部合作单位人员
    List<OuterUserDTO> getProjectOuterUserList(OuterUserDTO outerUserDTO , WebPage webPage);
    //删除人员
    void toDeleteProjectUser(String staffId);
    //批量删除人员
    void batchDeleteProjectUser(StaffBatchDTO staffBatchDTO);
    //条件检索已启用人员
    List<EnabledUserDTO> getProjectEnabledUserList(EnabledUserDTO enabledUserDTO, WebPage webPage);
    //获取组织机构
    Map getProjectAgencys();
    //获取人员信息
    UserManageDTO getProjectUserManage(String staffId);
    //修改人员信息
    String updateProjectStaff(UserManageDTO userManageDTO,UserInformationEntity userInformationEntity);
    //新增人员信息
    String saveProjectStaff(UserManageDTO userManageDTO,UserInformationEntity userInformationEntity);
    //新增外部合作单位组织机构
    ApiResult saveProjectOuterAgency(OuterAgencyDTO outerAgencyDTO, UserInformationEntity userPropertyStaffEntity);
    // 删除外部合作单位组织架构
    ApiResult delProjectOuterAgency(String agencyId,UserInformationEntity userPropertyStaffEntity);
    //导出Excel
    void projectEnabledUserExcel(String title, String[] headers, ServletOutputStream out, EnabledUserDTO enabledUserDTO);
    //外部人员导出Excel
    void projectOuterUserExcel(String title, String[] headers, ServletOutputStream out, OuterUserDTO outerUserDTO);
    //新权限外部人员导入新增
    String importProjectOuterPeopleExcel(InputStream fis, UserInformationEntity userPropertyStaffEntity);


}
