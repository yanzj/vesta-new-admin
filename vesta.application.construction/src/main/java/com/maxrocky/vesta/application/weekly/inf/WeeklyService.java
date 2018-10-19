package com.maxrocky.vesta.application.weekly.inf;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.weekly.DTO.WeeklyDTO;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/4/8.
 */
public interface WeeklyService {

    /**
     * 检查验收统计
     * @param map
     * @return
     */
    WeeklyDTO getInspectAcceptanceData(Map map);

    /**
     * 日常巡检统计
     * @param map
     * @return
     */
    WeeklyDTO getDailyPatrolInspectionData(Map map);

    /**
     * 材料验收统计
     * @param map
     * @return
     */
    WeeklyDTO getProjectMaterialData(Map map);

    /**
     * 样板点评统计
     * @param map
     * @return
     */
    WeeklyDTO getProjectSampleCheckData(Map map);

    /**
     * 关键工序统计
     * @param map
     * @return
     */
    WeeklyDTO getProjectKeyProcessesData(Map map);

    /**
     * 旁站统计
     * @param map
     * @return
     */
    WeeklyDTO getProjectSideStationData(Map map);

    /**
     * 领导检查统计
     * @param map
     * @return
     */
    WeeklyDTO getProjectLeadersCheckData(Map map);

    /**
     * 根据用户信息查询对应权限  工程
     * @param userInformationEntity
     * @return
     */
    List<AgencyTreeDTO> getAgencyList(UserInformationEntity userInformationEntity);

    /**
     * 根据条件获取角色关联的项目  工程
     * @param agencyId
     * @return
     */
    List<AuthAgencyESEntity> getAgencyListByAgencyId(String agencyId,String agencyType);

    /**
     * 根据用户信息查询对应Id
     * @param userInformationEntity
     * @return
     */
    List<String> getAgencyIdList(UserInformationEntity userInformationEntity);

    /**
     * 根据用户信息查询用户对应的次级权限
     * @param userInformationEntity
     * @return
     */
    List<AuthAgencyESEntity> getSecondaryAgencyByUser(UserInformationEntity userInformationEntity);

    /**
     * 根据用户信息查询用户对应的次级权限Id
     * @param userInformationEntity
     * @return
     */
    List<String> getSecondaryAgencyIdByUser(UserInformationEntity userInformationEntity);

    /**
     * 根据父级Id和type获取子级项目
     * @param AgencyId
     * @param AgencyType
     * @return
     */
    List<AuthAgencyESEntity> getSecondaryAgencyIdByAgencyIdAndType(String AgencyId,String AgencyType);

    /**
     * 根据项目id找到项目
     * @param AgencyId
     * @return
     */
    AuthAgencyESEntity getESAuthAgencyByID(String AgencyId);

    /**
     * 获取用户的最高级权限
     * @param userInformationEntity
     * @return
     */
    AuthAgencyESEntity getTopESAuthAgency(UserInformationEntity userInformationEntity);

    /**
     * 根据项目（一个或多个）查询检查次数，合格率，超过两周未整改等
     * @param map
     * @return
     */
    WeeklyDTO getWeeklyDTOData(Map map);

    /**
     * 查询是否是个人项目权限
     * @param userInformationEntity
     * @return
     */
    public boolean isProjectAuthority(UserInformationEntity userInformationEntity);

    /**
     * 虚拟区域下的所有项目
     * @return
     */
    List<AuthAgencyESEntity> getVirtualAreaProject();
}
