package com.maxrocky.vesta.application.ProjectSyncForApp.inf;

import com.maxrocky.vesta.application.ProjectSyncForApp.DTO.UserSyncProjectDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserAndRoleRelationDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * app项目同步Service
 * Created by yuanyn on 2018/04/24.
 */
public interface ProjectSyncForAppService {

    //根据项目Id获取人员授权角色与项目
    List<UserSyncProjectDTO> getAccreditManageListByAgencyId(String agencyIdPro, WebPage webPage, UserInformationEntity userInformationEntity, List<String> updateProject);

    //根据当前登录人id获取人员授权角色与项目
    List<UserSyncProjectDTO> getAccreditManageByStaffId(UserInformationEntity userInformationEntity);

    //根据用户id查询角色
    Map getRoleByUserId(String userId);

    //根据用户id查询app端角色
    String getAppRoleByUserId(String userId);

    //根据角色id获取人员app授权项目
    List<AgencyTreeDTO> getPorjectByCheck(String roleId, String userId);

    //保存三者关系
    void saveUserRoleProject(UserAndRoleRelationDTO userAndRoleRelationDTO);

}
