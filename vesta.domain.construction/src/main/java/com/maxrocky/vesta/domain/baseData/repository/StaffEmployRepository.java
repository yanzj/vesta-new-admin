package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectStaffEmployEntity;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/25.
 */
public interface StaffEmployRepository {
    /**
     * @param projectStaffEmployEntity
     * 新增关系
     */
    void addProjectStaffEmploy(ProjectStaffEmployEntity projectStaffEmployEntity);

    /**
     * @param staffId
     * @return
     * 根据员工ID通过责任单位间接获取项目列表
     */
    List<ProjectProjectEntity> getProjectListForAgency(String staffId);

    /**
     * @param staffId
     * @return
     * 根据员工ID获取项目列表
     */
    List<ProjectProjectEntity> getProjectListByStaffId(String staffId);

    /**
     * @param projectStaffEmployEntity
     * @return
     * 获取责任单位列表
     */
    List<AgencyEntity> getEmploys(ProjectStaffEmployEntity projectStaffEmployEntity,WebPage webPage);

    /**
     * @param agencyId
     * @return
     * 获取责任单位详情
     */
    AgencyEntity getEmployDetail(String agencyId);

    /**
     * @param projectId
     * @param permission
     * @return
     * 根据项目及权限获取对应责任单位列表
     */
    List<AgencyEntity> getEmploysForProjectPermission(String projectId,String permission);

    /**
     * @param projectId
     * @param dataType
     * @param permission
     * @return
     * 根据条件获取现有的关系数据
     */
    List<ProjectStaffEmployEntity> getStaffEmploys(String projectId,String dataType,String permission);

    /**
     * @param projectId
     * @param permission
     * @return
     * 根据项目及权限获取对应人员列表
     */
    List<UserPropertyStaffEntity> getStaffsForProjectPermission(String projectId,String permission);

    /**
     * @param projectStaffEmployEntity
     * 删除关系
     */
    void deleteProjectRole(ProjectStaffEmployEntity projectStaffEmployEntity);

    /**
     * @param staffId
     * 根据用户ID批量删除关系
     */
    void delProjectRole(String staffId);

    /**
     * @param projectStaffEmployEntity
     * 有则更新 无则新增
     */
    void dumpAddProjectRole(ProjectStaffEmployEntity projectStaffEmployEntity);

    /**
     * @param staffId
     * @param projectId
     * @param permission
     * @return
     * 根据项目ID判断当前人是否有相应权限
     */
    boolean checkOwner(String staffId,String projectId,String permission);

    /**
     * @param staffId
     * @param projectId
     * @return
     * 获取当前人工程阶段权限名
     */
    String getPurviewName(String staffId,String projectId);

    /**
     * @param dutyId
     * @return
     * 获取责任单位对应的项目
     */
    String getProjectIdByDuty(String dutyId);

    /**
     * @param projectId
     * @param timeStamp
     * @param num
     * @return
     * 同步甲方人员数据
     */
    List<Object[]> getOwnerProjectRole(String projectId,String timeStamp,int num);

    /**
     * 根据人员ids 或者部门ids 批量删除关系
     * type 1:部门 2:人员
     */
    void deleProjectRole(String ids,String type);

    /**
     * 删除所有的关系
     */
    void deleteProjectRole();


    /**
     * 根据项目id和责任单位名称 检索是否存在数据
     */
    boolean checkAgencyName(String projectId,String agencyName);

    /**
     * 根据组织机构id查询数据集
     */
    AuthOuterAgencyEntity getAuthOuterAgency(String agencyId);


    /**
     * 根据组织机构名称查询数据
     */
    AuthOuterAgencyEntity getAuthOuterAgencyByName(String agencyName);


    //新增机构
    void addAgency(AuthOuterAgencyEntity authOuterAgencyEntity);


    //新增责任单位  项目  人员关联关系
    void addbaseProjectPeople(BaseProjectPeopleEntity baseProjectPeopleEntity);

    //删除 修改责任单位+项目+人员关联关系
    /**
     * projectId 项目id
     * userId  人员id
     * authOuterAgencyId  责任单位id
     * supplierName 责任单位名称
     * supplierType  性质
     * abbreviationName 简称
     */
    void deleteBaseProjectPeople(String projectId,String authOuterAgencyId,String userId,String supplierName,String supplierType,String abbreviationName,String status);

    //查询当前项目下责任单位
    List<BaseProjectPeopleEntity> getBaseProjectBByAgencyId(String projectId,String supplierName,WebPage webPage);

    //查询当前项目下责任单位
    List<Object []> getAuthOuterAgencyProject(String projectId,String supplierName,WebPage webPage);

    //查询当前项目和责任单位关联信息
    BaseProjectPeopleEntity getBaseProjectByProjectId(String projectId,String supplierId);

    //查询当前项目下关联的责任单位id
    List<String> getIdListByProjectId(String projectId);

    //查询当前项目下还未关联的idlist
    List<String> getNameListByProjectId(List<String> agencyIdList);


    //按角色性质  项目id 查询角色名称
    List<String> getRoleIdlistById(String roleType,String agencyId);

    //按角色性质  项目id 查询角色id+名称
    List<Object[]> getRoleIdNamelistById(String roleType,String agencyId);

    //查询当前项目下责任单位
    List<Object[]> getAgencyPeopleUser(String projectId,String supplierId,WebPage webPage);

    //查询当前项目下责任单位
    List<UserInformationEntity> getAgencyPeopleUserAuth(String projectId,String supplierId,WebPage webPage);

    //根据当前角色性质 、项目id、人员id 查询角色名称
    List<String> getRoleNamesByEs(String roleType,String agencyId,String userId);

    //查询人员信息
    UserInformationEntity get(String staffId);

    //根据当前角色性质 、项目id、人员id 查询角色 id+名称
    List<Object[]> getRoleIDNamesByEs(String roleType,String agencyId,String userId);


    //删除三者关系表
    void delAgencyRoleUser(String agencyId,String userId,String roleId,String status);

    //保存角色 项目 人员关系
    void saveOrUpdateProjectRoleStaff(RoleStaffProjectMapESEntity roleStaffProjectMapESEntity);


    //保存 责任单位和人员关系
    void saveOrUpdateuserAgencyEntity(UserAgencyEntity userAgencyEntity);

    //保存 人员信息
    void saveUserInformationEntity(UserInformationEntity userInformationEntity);


    void saveUserMapEntity(UserMapEntity userMapEntity);

    //校验是否存在关系 责任单位+人
    boolean getCheckUserAgencyEntity(String supplierId,String userId,String status);

    UserAgencyEntity getUserAgencyEntity(String supplierId,String userId);


    //校验是否存在关系 责任单位 + 项目 + 人
    boolean getCheckBaseProjectPeopleEntity(String supplierId,String userId,String projectId);

    UserMapEntity getUserMapEntity(String staffId,String type);

    //根据项目id查询项目名称
    AuthAgencyESEntity getAuthAgencyESEntityByid(String id);

    // 根据项目id + 责任单位性质+ 角色名字  筛选jueseid
    String getRoleIdBylikeName(String roleName,String roleType,String projectId);


    /**
     * projectId 项目id
     * userId  人员id
     * authOuterAgencyId  责任单位id
     * supplierName 责任单位名称
     * supplierType  性质
     * abbreviationName 简称
     */
    void upBaseProjectPeopleName(String userId,String userName,String abbreviationName,String supplierType,String authStatus);

}
