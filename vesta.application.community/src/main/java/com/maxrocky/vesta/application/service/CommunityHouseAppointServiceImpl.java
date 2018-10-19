package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.admin.dto.CommunityAppointManageDto;
import com.maxrocky.vesta.application.admin.dto.HousePayBatchQueryDto;
import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.CommunityHouseAppointRespository;
import com.maxrocky.vesta.domain.repository.CommunityHouseBatchRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/4/14 0:00.
 * Describe:
 */
@Service
public class CommunityHouseAppointServiceImpl implements CommunityHouseAppointService {

    /* 交付预约 */
    @Autowired
    CommunityHouseAppointRespository communityHouseAppointRespository;

    @Autowired
    CommunityHouseBatchRespository communityHouseBatchRespository;

    /* 项目（社区）服务 */
    @Autowired
    HouseProjectService houseProjectService;

    /**
     * Code:Admin
     * Type:Admin Method
     * Describe:交付批次管理列表
     * CreateBy:Tom
     * CreateOn:2016-04-14 12:02:35
     */
    @Override
    public List<CommunityAppointManageDto> getCommunityAppointList(HousePayBatchQueryDto housePayBatchQueryDto, WebPage webPage) {
        List<CommunityAppointManageDto> communityAppointManageDtoList = new ArrayList<CommunityAppointManageDto>();
        /* 获取交付预约数据 */

        List<CommunityHouseAppointEntity> communityHouseAppointEntityList = communityHouseAppointRespository.getList(housePayBatchQueryDto.toCommunityHouseAppointEntity(), webPage);
        for (CommunityHouseAppointEntity communityHouseAppointEntity : communityHouseAppointEntityList) {
            if (null == communityHouseAppointEntity.getBatchManageId()) {
                continue;
            }
            CommunityBatchManageEntity communityBatchManageEntity = communityHouseBatchRespository.get(CommunityBatchManageEntity.class, communityHouseAppointEntity.getBatchManageId());

            CommunityAppointManageDto communityAppointManageDto = new CommunityAppointManageDto();
            communityAppointManageDto.setDeliveryBatch(communityBatchManageEntity.getDeliveryBatch());

            if (communityHouseAppointEntity.getCommunityId() != null) {
                /* 根据项目ID查询项目 */
                HouseProjectDto houseProjectDto = houseProjectService.getProjectById(communityHouseAppointEntity.getCommunityId());
                if (houseProjectDto != null) {
                    communityAppointManageDto.setCommunityName(houseProjectDto.getName());
                }
            }

            communityAppointManageDto.setPayStaDate(DateUtils.format(communityHouseAppointEntity.getPayStaTime(), DateUtils.FORMAT_SHORT));
            communityAppointManageDto.setPayEndDate(DateUtils.format(communityHouseAppointEntity.getPayEndTime(), DateUtils.FORMAT_SHORT));
            communityAppointManageDto.setMaxUser(communityBatchManageEntity.getMaxUser());

            communityAppointManageDtoList.add(communityAppointManageDto);
        }

        return communityAppointManageDtoList;
    }

    /**
     * 用户预约列表
     *
     * @param userAppointDto
     * @param webPage
     * @return
     */
    public List<UserAppointDto> listAppoints(UserAppointDto userAppointDto, WebPage webPage) {
        CommunityHouseAppointEntity communityHouseAppointEntity = new CommunityHouseAppointEntity();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        HouseInfoEntity houseInfoEntity = new HouseInfoEntity();

        //项目
        if (userAppointDto.getAppointProjectId() != null && !"0".equals(userAppointDto.getAppointProjectId())) {
            communityHouseAppointEntity.setCommunityId(userAppointDto.getAppointProjectId());
        }
        //楼栋
        if (userAppointDto.getAppointBuildId() != null && !"0".equals(userAppointDto.getAppointBuildId())) {
            houseInfoEntity.setBuildingId(userAppointDto.getAppointBuildId());
        }
        //单元
        if (userAppointDto.getAppointUnitId() != null && !"0".equals(userAppointDto.getAppointUnitId())) {
            //houseInfoEntity.setUnitId(userAppointDto.getAppointUnitId());
        }
        //批次
        if (userAppointDto.getPlanName() != null && !"".equals(userAppointDto.getPlanName())) {
            communityHouseAppointEntity.setDeliveryBatch(userAppointDto.getPlanName());
        }
        //上下午
        if (!StringUtil.isEmpty(userAppointDto.getAmOrPm()) && !"999".equals(userAppointDto.getAmOrPm())) {
            communityHouseAppointEntity.setAmOrPm(Integer.parseInt(userAppointDto.getAmOrPm()));
        }
        //移动电话
        if (userAppointDto.getUserMobile() != null && !"".equals(userAppointDto.getUserMobile())) {
            userInfoEntity.setMobile(userAppointDto.getUserMobile());
        }
        List<Object> appoints = communityHouseAppointRespository.getUserAppoints(communityHouseAppointEntity, userInfoEntity, houseInfoEntity, webPage);
        List<UserAppointDto> userAppointList = new ArrayList<>();
        if (appoints.size() > 0) {
            for (Object o : appoints) {
                Object[] appoint = (Object[]) o;
                CommunityHouseAppointEntity houseAppoint = (CommunityHouseAppointEntity) appoint[0];
                UserInfoEntity user = (UserInfoEntity) appoint[1];
                HouseInfoEntity house = (HouseInfoEntity) appoint[2];
                UserAppointDto userAppoint = new UserAppointDto();
                userAppoint.setAppointId(houseAppoint.getId());
                userAppoint.setAppointUserId(user.getUserId());
                userAppoint.setAppointHouseId(house.getId());
                userAppoint.setUserName(user.getRealName());
                userAppoint.setUserMobile(user.getMobile());
                userAppoint.setProjectName(house.getProjectName());
                //userAppoint.setBuildName(house.getBuildingName());
                //userAppoint.setUnitName(house.getUnitName());
                userAppoint.setRoomName(house.getRoomName());
                userAppoint.setIdCard(user.getIdCard());
                userAppoint.setAppointUserTime(DateUtils.format(houseAppoint.getAppointEndTime()));
                userAppoint.setAmOrPm(houseAppoint.getAmOrPm().toString());
                userAppoint.setPlanName(houseAppoint.getDeliveryBatch());
                userAppointList.add(userAppoint);
            }
        }

        return userAppointList;
    }

    @Override
    public String removeHousePayById(String housepayID, UserPropertyStaffEntity userPropertystaffEntity) {
        // 判断是否执行删除成功
        String resultMessage = "";
        // 根据公告信息ID查询 公告信息详情
        CommunityHouseAppointEntity communityHouseAppointEntity = communityHouseAppointRespository.propertyHousePay(housepayID);
        if (null != communityHouseAppointEntity) {
            // 执行删除物业公告信息
            boolean delPropertyAnnouncement = communityHouseAppointRespository.delPropertyHousePay(communityHouseAppointEntity);
            if (delPropertyAnnouncement) {
                resultMessage = "0";//此信息删除成功!
            } else {
                resultMessage = "1";//此信息删除失败!
            }
//            // 后台核心日志
//            OperationLogDTO operation = new OperationLogDTO();
//            operation.setUserName(userPropertystaffEntity.getStaffName());  // 用户名
//            operation.setProjectId(userPropertystaffEntity.getProjectId());// 用户项目ID
//            operation.setContent("删除交付信息!");      // 操作动作
//            // 添加后台核心日志
//            operationLogService.addOperationLog(operation);

        } else {
            resultMessage = "2";//此信息不存在!
            return resultMessage;
        }
        return resultMessage;
    }
}
