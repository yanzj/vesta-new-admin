package com.maxrocky.vesta.application.baseData.inf;

import com.maxrocky.vesta.application.baseData.adminDTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AuthCheckNameDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/10/20.
 */
public interface ProjectProjectService {

    /**
     * 新增工程项目
     * @param projectProjectDTO
     */
    void addProjectProject(ProjectProjectDTO projectProjectDTO, UserInformationEntity userPropertyStaffEntity);

    /**
     * 删除工程项目
     * @param projectId
     */
    void delProjectProject(String projectId);

    /**
     * 根据区域ID获取工程项目列表
     * @param cityId
     * @return
     */
    List<ProjectProjectDTO> getProjectProjectsByCityId(String cityId, String staffId);
    /**
     * 获取工程项目列表
     * @return
     */
    Map getProjectProjects();
    /**
     * @param timeStamp
     * @param autoNum
     * @return
     * 根据时间戳和自增长ID同步项目数据
     */
    ApiResult getProjectProjectList(String timeStamp, String autoNum);

    /**
     * @param projectProjectDTO
     * @param webPage
     * @return
     * 获取项目列表
     */
    List<ProjectProjectDTO> getProjectAll(ProjectProjectDTO projectProjectDTO, WebPage webPage);

    /**
     * @param projectProjectReceiveDTO
     * 更新工程项目信息及权限
     */
    void updateProjectProject(ProjectProjectReceiveDTO projectProjectReceiveDTO);

    /**
     * @param projectId
     * @return
     * 获取工程阶段项目权限详情
     */
    ProjectRoleAllDTO getProjectDetail(String projectId);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoNum
     * @return
     * 同步基础人员数据
     */
    ApiResult getProjectPeopleForTime(String projectId, String timeStamp, String autoNum);

    /**
     * 新增  人员项目权限功能
     * 通过模糊查询项目
     */
    List<ProjectDTO> getProjectByName(String projectName);

    /**
     * 总部
     */
    List<ProjectTreeDTO> getAreaList();

    /**
     * 获取区域
     */
    List<ProjectTreeDTO> getProjectList(String parentId);

    /**
     *获取项目
     */
    List<ProjectTreeDTO> getNextProjectList(String parentId);

    /**
     * 获取项目与人员关系
     */
    ProjectStaffRelationDTO getProjectRole();

    /**
     * 保存项目与人员关系
     */
    void updateProjectRole(ConstructionProjectDTO constructionProjectDTO);
    /**
     * 校验组织机构是否重复
     */
    int checkName(AuthCheckNameDTO authCheckNameDTO);
}
