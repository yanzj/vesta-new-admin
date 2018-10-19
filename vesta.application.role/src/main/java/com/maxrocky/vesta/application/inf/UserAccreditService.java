package com.maxrocky.vesta.application.inf;

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
 * Created by hp on 2017/12/21.
 */
public interface UserAccreditService {

    //获取项目层级（crm组织架构）
    List<AgencyTreeDTO> getAgencyListAll();
    List<AgencyTreeDTO> getAgencyListAll(List<String> updateProject);

    //检索条件获取人员授权信息
    List<AccreditManageDTO> getAccreditManageListByCondition(AccreditManageDTO accreditManageDTO, WebPage webPage, UserInformationEntity userInformationEntity);

    //获取组织架构关系
    List<AgencyTreeDTO> getOAAgencyMessage(String category);

    //删除授权关系
    void deleteAccredit(String id);

    //通过Id获取机构下的人
    List<AgencyTreeDTO> getUserByAgencyId(String agencyId,String category);

    //通过Id获取项项目层级下的角色
    List<AgencyTreeDTO> getRoleByAgencyId(String agencyId);

    //模糊搜索人员分类
    List<UserOrRoleDTO> getUserByName(String staffName,String category);


    //模糊搜索角色
    List<UserOrRoleDTO> getRolerByName(String roleName);
    List<UserOrRoleDTO> getRolerByName(String roleName,List<String> updateProject);

    //保存角色和人员关系
    void saveOrUpdateUserRoleRelation(UserAndRoleRelationDTO userAndRoleRelationDTO);
    //保存角色和人员关系
    ApiResult saveOrUpdateUserRoleRelation_2(List<UserProjectRoleAccreditDTO> userList);
}
