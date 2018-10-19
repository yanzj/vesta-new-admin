package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectCityEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectOperationEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectStaffRelationEntity;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/19.
 */
public interface ProjectProjectRepository {
    //新增工程项目
    void addProjectProject(ProjectProjectEntity projectProjectEntity);
    //根据区域ID和当前人ID获取工程项目列表
    List<ProjectProjectEntity> getProjectProjectsByCityId(String cityId,String staffId);
    //获取当前人的项目列表
    List<ProjectProjectEntity> getProjectProjectsByStaffId(String staffId);
    List<ProjectProjectEntity> getProjectProjects();
    //删除工程项目
    void delProjectProject(String projectId);
    //更新工程项目
    void updateProjectProject(ProjectProjectEntity projectProjectEntity);

    /**
     * @param projectId
     * @return
     * 获取项目详情
     */
    ProjectProjectEntity getProjectProjectDetail(String projectId);

    /**
     * @param projectId
     * @return
     * 获取项目详情
     */
    AuthAgencyESEntity getAuthAgencyES(String projectId,String type);

    /**
     * @param timeStamp
     * @param autoNum
     * @return
     * 根据时间戳和自增长ID获取数据
     */
    List<ProjectProjectEntity> getProjectProjectList(String timeStamp, int autoNum);

    /**
     * @param projectProjectEntity
     * @param webPage
     * @return
     * 获取所有项目列表
     */
    List<ProjectProjectEntity> getProjectAll(ProjectProjectEntity projectProjectEntity, WebPage webPage);

    /**
     * @param projectId
     * @return
     * 根据项目ID获取责任单位列表
     */
    List<Object[]> getDutyList(String projectId);

    /**
     * @param dutyId
     * @return
     * 根据责任单位ID获取所有整改人
     */
    List<Object[]> getDutyPeople(String dutyId);

    /**
     * @param projectId
     * @return
     * 根据项目ID获取所有监理人员
     */
    List<Object[]> getSurveyorUserList(String projectId);

    /**
     * @param projectId
     * @return
     * 根据项目ID获取所有甲方人员
     */
    List<Object[]> getOwnerUserList(String projectId);

    /**
     * 根据项目名模糊查询项目
     */
    List<ProjectProjectEntity> getProjectByName(String name);

    /**
     * 获取所有经营单位
     */
    List<ProjectOperationEntity> getProjectOperation();

    /**
     * 获取经营单位下的项目
     */
    List<ProjectProjectEntity> getProjectList(String parentId);

    /**
     * 获取项目与人员关系
     */
    List<ProjectStaffRelationEntity> getProjectRole();

    /**
     * 删除关系表
     */
    void updateProjectStaff();

    /**
     * 新建立人员项目关系
     */
    void addProjectStaff(ProjectStaffRelationEntity projectStaffRelationEntity);

    /**
     * 经营单位下的项目
     */
    List<ProjectProjectEntity> getProjectListByArea(List<String> areaIds);

    /**
     * 经营单位详情
     */
    List<ProjectOperationEntity> getAreaById(List<String> areaId);

    /**
     * 项目详情
     */
    List<ProjectProjectEntity> getProjectById(List<String> projectId);

    /**
     * 人员详情
     */
    List<UserPropertyStaffEntity> getUserByStaffId(List<String> userId);

    /**
     * 部门详情
     */
    List<AgencyEntity> getAgencyBydepartmentId(List<String> departmentId);
}
