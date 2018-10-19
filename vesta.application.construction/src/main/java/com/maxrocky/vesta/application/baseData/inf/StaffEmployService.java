package com.maxrocky.vesta.application.baseData.inf;

import com.maxrocky.vesta.application.baseData.adminDTO.ProjectEmployDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/26.
 */
public interface StaffEmployService {
    /**
     * @param projectEmployDTO
     * @param webPage
     * @return
     * 根据项目ID获取责任单位列表
     */
    List<ProjectEmployDTO> getEmploys(ProjectEmployDTO projectEmployDTO,WebPage webPage);


    /**
     * @param projectEmployDTO
     * @param webPage
     * @return
     * 根据项目ID获取责任单位列表   新权限 工程
     */
    List<ProjectEmployDTO> getAuthEmploys(ProjectEmployDTO projectEmployDTO,WebPage webPage);

    /**
     * @param employId
     * @return
     * 获取责任单位详情
     */
    ProjectEmployDTO getEmployDetail(String employId);


    /**
     * @param employId
     * @return
     * 获取责任单位详情新權限
     */
    ProjectEmployDTO getAuthEmployDetail(String employId,String projectId);

    /**
     * @param projectEmployDTO
     * 新增责任单位
     */
    void addEmploy(ProjectEmployDTO projectEmployDTO);


    /**
     * @param projectEmployDTO
     * 新增责任单位 新权限组织机构
     */
    void addAuthEmploy(ProjectEmployDTO projectEmployDTO,UserInformationEntity userInformationEntity);


    /**
     * @param projectEmployDTO
     * 更新责任单位
     */
    void updateEmploy(ProjectEmployDTO projectEmployDTO);

    /**
     * @param projectEmployDTO
     * 更新责任单位
     */
    void updateAuthEmploy(ProjectEmployDTO projectEmployDTO,UserInformationEntity userInformationEntity);

    /**
     * @param projectId
     * @param timeStamp
     * @param num
     * @return
     * 质检APP同步甲方人员数据
     */
    ApiResult getProjectOwnerPeopleForTime(String projectId,String timeStamp,String num);

    /**
     * @param projectId
     * 根据projectId 筛选还未关联的责任单位
     */
    List<String> getAllSuoolierNameList(String projectId);

}
