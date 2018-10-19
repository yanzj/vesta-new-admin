package com.maxrocky.vesta.application.clientAccredit.inf;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AccreditManageDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserAndRoleRelationDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserOrRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserProjectRoleAccreditDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by yuanyn on 2018/05/10.
 */
public interface ClientUserAccreditService {

    //获取项目层级（crm组织架构）
    List<AgencyTreeDTO> getClientAgencyListAll();
    List<AgencyTreeDTO> getClientAgencyListAllByIds(List<String> updateProject);

    //检索条件获取人员授权信息
    List<AccreditManageDTO> getAccreditManageListByCondition(AccreditManageDTO accreditManageDTO, WebPage webPage, UserInformationEntity userInformationEntity);

    //获取组织架构关系
    List<AgencyTreeDTO> getClientOAAgencyMessage();

    //获取组织架构关系只显示甲方
    List<AgencyTreeDTO> getOAAgencyMessageClient();

    //删除授权关系
    void deleteClientAccredit(String id);

    //通过Id获取机构下的人
    List<AgencyTreeDTO> getUserByAgencyId(String agencyId, String category);

    //通过Id获取项项目层级下的角色
    List<AgencyTreeDTO> getClientRoleByAgencyId(String agencyId);

    //通过Id获取项项目层级下的甲方角色
    List<AgencyTreeDTO> getOwnerRoleByAgencyId(String agencyId);

    //模糊搜索人员分类
    List<UserOrRoleDTO> getUserByName(String staffName, String category);

    //模糊搜索甲方人员分类
    List<UserOrRoleDTO> getOwnerUserByName(String staffName);


    //模糊搜索角色
    List<UserOrRoleDTO> getRolerByName(String roleName);
    List<UserOrRoleDTO> getRolerByName(String roleName, List<String> updateProject);


    //模糊搜索甲方角色
    List<UserOrRoleDTO> getOwnerRolerByName(String roleName, List<String> updateProject);

    //保存角色和人员关系
    void saveOrUpdateUserRoleRelation(UserAndRoleRelationDTO userAndRoleRelationDTO);
    //保存角色和人员关系
    ApiResult saveOrUpdateUserRoleRelation_2(List<UserProjectRoleAccreditDTO> userList);
    //根据当前登录人id+功能点级别 查询功能点和功能点所在项目
    List<String> getClientAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level);
}
