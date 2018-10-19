package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/12/8.
 */
public interface AuthAgencyService {
    //组织机构列表
    List<AgencyTreeDTO> getAgencyFullList();

    //组织机构列表
    List<AgencyTreeDTO> getESAgencyFullList();

    //组织机构列表
    List<AgencyTreeDTO> getQCAgencyFullList();

    //组织机构列表 根据当前登录人权限
    List<AgencyTreeDTO> getAuthAgencyFullList(UserInformationEntity userPropertystaffEntity);

    //组织机构列表增加选中    安全
    List<AgencyTreeDTO> getAgencyFullListByID(String apply);

    //组织机构列表增加选中 工程
    List<AgencyTreeDTO> getAgencyFullListByIDES(String apply);

    //组织机构列表增加选中 客观
    List<AgencyTreeDTO> getAgencyFullListByIDQC(String apply);

    //查询组织机构下一级别信息  安全
    List<AuthAdminAgencyDTO> getAgencyById(AgencyAdminDTO agencyAdminDTO);

    //查询组织机构下一级别信息  工程
    List<AuthAdminAgencyDTO> getESAgencyById(AgencyAdminDTO agencyAdminDTO);

    //查询组织机构下一级别信息  客观
    List<AuthAdminAgencyDTO> getQCAgencyById(AgencyAdminDTO agencyAdminDTO);

    //查询组织机构第一级别信息
    List<AuthAdminAgencyDTO> getAgency();

    //后台管理系统角色页面
    List<AuthRoleseListDTO> getAuthRoleseList(AuthRoleseListDTO authRoleseListDTO, WebPage webPage);

    //新增角色信息
    int saveAuthRolese(UserInformationEntity userPropertystaffEntity, AuthRoleseListDTO authRoleseListDTO);

    //查询角色信息
    AuthRoleseListDTO getAuthRoleseById(AuthRoleseListDTO authRoleseListDTO);

    //修改角色信息
    int upAuthRolese(UserInformationEntity userPropertystaffEntity, AuthRoleseListDTO authRoleseListDTO);

    //修改角色信息
    int delAuthRolese(UserInformationEntity userPropertystaffEntity, AuthRoleseListDTO authRoleseListDTO);

    //角色功能点授权
    AuthFunctionPointListDTO getAuthFunctionPoint(AuthFunctionPointRoleListDTO authFunctionPointRoleListDTO);

    //角色功能点授权保存
    int addRoleButtonMap(AuthFunctionPointRoleListDTO authFunctionPointRoleList,UserInformationEntity userPropertystaff);

    //按类型查询角色
    public Map<String, String> getRoleList(String category);


    //查询组织机构角色人员关联关系
    AuthAdminAgencyProjectDTO getAuthAdminAgencyProjectList(AgencyAdminDTO agencyAdminDTO,WebPage webPage,UserInformationEntity userPropertystaffEntity);

    //查询组织机构角色人员关联关系
    AuthAdminAgencyProjectDTO getESAuthAdminAgencyProjectList(AgencyAdminDTO agencyAdminDTO,WebPage webPage,UserInformationEntity userPropertystaffEntity);

    //查询组织机构角色人员关联关系 客观
    AuthAdminAgencyProjectDTO getQCAuthAdminAgencyProjectList(AgencyAdminDTO agencyAdminDTO,WebPage webPage,UserInformationEntity userPropertystaffEntity);


    //查询组织机构总机构关联关系
    AuthAdminAgencyProjectDTO getAuthAdminAgencyRole(AgencyAdminDTO agencyAdminDTO);

    //按类型查询角色   安全
    public Map<String, String> getAuthAgencyRole(AgencyAdminDTO agencyAdminDTO);

    //按类型查询角色 工程
    public Map<String, String> getESAuthAgencyRole(AgencyAdminDTO agencyAdminDTO);

    //按类型查询角色 客观
    public Map<String, String> getQCAuthAgencyRole(AgencyAdminDTO agencyAdminDTO);

    //保存角色和人员关系
    int  saveOrUpdateUserRoleRelation(UserAndRoleRelationDTO userAndRoleRelationDTO);


    //根据当前登录人id 和需要查询的级别 查询一级菜单id
    List<String> getNewVimList(String staffId,String level,String category);

    //根据当前登录人id+功能点级别 查询所属功能点
    List<String> getAuthFunctionByStaffId(String staffId,String level,String category);

    //根据当前登录人id+功能点级别 查询所属功能点  工程
    List<String> getProjectAuthFunctionByStaffId(String staffId,String level,String category);

    //根据当前登录人id+功能点级别 查询所属功能点  客观
    List<String> getQCProjectAuthFunctionByStaffId(String staffId,String level,String category);

    //根据当前登录人id+功能点级别 查询功能点和功能点所在项目
    List<String> getAuthFunctionAndProjectIdByStaffId(String function, String staffId,String level);

    //根据当前登录人获取其在客关下有新闻权限的区域或集团
    List<String> getNewsFunctionByStaffId(String function, String staffId);

    //根据当前登录人获取其在客关下有新闻权限的区域或集团名称和id
    Map<String, String> getNewsProjectNameAndIdByStaffId(String function, String staffId);

    //获取区域信息
    Map getCityListByAreaId(String areaId,String category);

    //根据区域Id获取城市信息  安全
    Map getAreaListByInit();

    //根据区域Id获取城市信息 工程
    Map getESAreaListByInit();

    //根据区域Id获取城市信息 客观
    Map getQCAreaListByInit();

    //保存项目信息
    void saveProjectByProjectName(String projectName,String cityId,String category);

    //根据用户ID获取用户权限下的所有项目
    List<String> getAgencyIdList(String userId);


    //项目分类人员信息
    List<AuthClassifyStaffDTO> getAuthClassifyStaff(AuthClassifyStaffDTO authClassifyStaffDTO, WebPage webPage);
}
