package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.ViewAppHouseInfoAdminDTO;
import com.maxrocky.vesta.application.inf.ViewAppOwnerInfoService;
import com.maxrocky.vesta.application.DTO.json.HI0006.HouseParamJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * Created by Tom on 2016/1/18 14:47.
 * Describe:基础业主信息Service接口实现类
 */
@Service
public class ViewAppOwnerInfoServiceImpl implements ViewAppOwnerInfoService {

    /* 项目 */
    @Autowired
    HouseProjectRepository houseProjectRepository;
    /* 业态 */
    @Autowired
    HouseFormatRepository houseFormatRepository;
    /* 楼 */
    @Autowired
    HouseBuildingRepository houseBuildingRepository;
    /* 单元 */
    @Autowired
    HouseUnitRepository houseUnitRepository;
    /* 房间 */
    @Autowired
    HouseRoomRepository houseRoomRepository;
    /* 基础房产 */
    @Autowired
    ViewAppHouseInfoRepository viewAppHouseInfoRepository;
    /* 基础合同 */
    @Autowired
    ViewAppChargeInfoRepository viewAppChargeInfoRepository;
    /* 基础业主 */
    @Autowired
    ViewAppOwnerInfoRepository viewAppOwnerInfoRepository;

    /**
     * Code:HI0006
     * Type:UI Method
     * Describe:根据房屋信息查询证件号码
     * CreateBy:Tom
     * CreateOn:2016-01-18 02:54:22
     */
    @Override
    public ApiResult getIdCardByHouse(HouseParamJsonDTO houseParamJsonDTO) {

        /* 基础数据校验 */
        if(houseParamJsonDTO == null){
            return new ErrorApiResult("error_00000003");
        }
        ApiResult checkResult = houseParamJsonDTO.check();
        if(checkResult instanceof ErrorApiResult){
            return checkResult;
        }

        /* 获取校验身份证号 */
        ViewAppHouseInfoAdminDTO viewAppHouseInfoAdminDTO = getBasicInfoByHouse(houseParamJsonDTO);
        if(viewAppHouseInfoAdminDTO == null){
            return new ErrorApiResult("tip_00000478");
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("idCard", viewAppHouseInfoAdminDTO.getCheckIdCard());

        return new SuccessApiResult(modelMap);
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据房屋信息获取房屋、合同、业主信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 05:12:20
     */
    @Override
    public ViewAppHouseInfoAdminDTO getBasicInfoByHouse(HouseParamJsonDTO houseParamJsonDTO) {

        /* 获取项目 */
        HouseProjectEntity houseProjectEntity = houseProjectRepository.get(houseParamJsonDTO.getProjectId());
        if(houseProjectEntity == null){
            return null;
        }

        /* 获取业态 */
        HouseFormatEntity houseFormatEntity = houseFormatRepository.get(houseParamJsonDTO.getFormatId());
        if(houseFormatEntity == null){
            return null;
        }

        /* 获取楼 */
        HouseBuildingEntity houseBuildingEntity = houseBuildingRepository.get(houseParamJsonDTO.getBuildingId());
        if(houseBuildingEntity == null){
            return null;
        }

        /* 获取单元 */
        HouseUnitEntity houseUnitEntity = houseUnitRepository.get(houseParamJsonDTO.getUnitId());
        if(houseUnitEntity == null){
            return null;
        }

        /* 获取房间 */
        HouseRoomEntity houseRoomEntity = houseRoomRepository.get(houseParamJsonDTO.getRoomId());
        if(houseRoomEntity == null){
            return null;
        }

        /* 查询基础房产信息 */
        ViewAppHouseInfoEntity queryViewAppHouseInfo = new ViewAppHouseInfoEntity();
        queryViewAppHouseInfo.setProjectId(houseProjectEntity.getViewAppProjectId());//项目ID
        queryViewAppHouseInfo.setFormatName(houseFormatEntity.getName());//业态名称
        queryViewAppHouseInfo.setBlockName(houseBuildingEntity.getName());//楼号
        queryViewAppHouseInfo.setCellNo(houseUnitEntity.getName());//单元
        queryViewAppHouseInfo.setHouseNo(houseRoomEntity.getName());//房间号
        ViewAppHouseInfoEntity viewAppHouseInfoEntity = viewAppHouseInfoRepository.getByQuery(queryViewAppHouseInfo);
        if(viewAppHouseInfoEntity == null){
            return null;
        }

        /* 根据房产查询合同信息 */
        ViewAppChargeInfoEntity viewAppChargeInfoEntity = viewAppChargeInfoRepository.getByHouseId(viewAppHouseInfoEntity.getHouseId());
        if(viewAppChargeInfoEntity == null){
            return null;
        }

        /* 根据合同查询业主信息 */
        ViewAppOwnerInfoEntity viewAppOwnerInfoEntity = viewAppOwnerInfoRepository.getByOwnerId(viewAppChargeInfoEntity.getOwnerId());
        if(viewAppChargeInfoEntity == null){
            return null;
        }

        ViewAppHouseInfoAdminDTO viewAppHouseInfoAdminDTO = new ViewAppHouseInfoAdminDTO();
        viewAppHouseInfoAdminDTO.setViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
        viewAppHouseInfoAdminDTO.setViewAppOwnerId(viewAppOwnerInfoEntity.getOwnerId());
        viewAppHouseInfoAdminDTO.setCheckIdCard(viewAppOwnerInfoEntity.getVerifyCardNum());
        viewAppHouseInfoAdminDTO.setViewAppIdCard(viewAppOwnerInfoEntity.getCardNum());
        viewAppHouseInfoAdminDTO.setProjectId(houseProjectEntity.getId());
        viewAppHouseInfoAdminDTO.setProjectName(houseProjectEntity.getName());
        viewAppHouseInfoAdminDTO.setViewAppOwnerCardType(viewAppOwnerInfoEntity.getCardType());
        viewAppHouseInfoAdminDTO.setViewAppOwnerMobile(viewAppOwnerInfoEntity.getMobilePhone());
        viewAppHouseInfoAdminDTO.setBuildingName(houseBuildingEntity.getName());
        viewAppHouseInfoAdminDTO.setUnitName(houseUnitEntity.getName());
        viewAppHouseInfoAdminDTO.setRoomName(houseRoomEntity.getName());
        viewAppHouseInfoAdminDTO.setFormatName(houseFormatEntity.getName());
        viewAppHouseInfoAdminDTO.setViewAppOwnerName(viewAppOwnerInfoEntity.getOwnerName());

        return viewAppHouseInfoAdminDTO;
    }

    @Override
    public ViewAppHouseInfoAdminDTO getBasicInfoByTenant(HouseParamJsonDTO houseParamJsonDTO) {

        /* 获取项目 */
        HouseProjectEntity houseProjectEntity = houseProjectRepository.get(houseParamJsonDTO.getProjectId());
        if(houseProjectEntity == null){
            return null;
        }

        /* 获取业态 */
        HouseFormatEntity houseFormatEntity = houseFormatRepository.get(houseParamJsonDTO.getFormatId());
        if(houseFormatEntity == null){
            return null;
        }

        /* 获取楼 */
        HouseBuildingEntity houseBuildingEntity = houseBuildingRepository.get(houseParamJsonDTO.getBuildingId());
        if(houseBuildingEntity == null){
            return null;
        }

        /* 获取单元 */
        HouseUnitEntity houseUnitEntity = houseUnitRepository.get(houseParamJsonDTO.getUnitId());
        if(houseUnitEntity == null){
            return null;
        }

        /* 获取房间 */
        HouseRoomEntity houseRoomEntity = houseRoomRepository.get(houseParamJsonDTO.getRoomId());
        if(houseRoomEntity == null){
            return null;
        }

        /* 查询基础房产信息 */
        ViewAppHouseInfoEntity queryViewAppHouseInfo = new ViewAppHouseInfoEntity();
        queryViewAppHouseInfo.setProjectId(houseProjectEntity.getViewAppProjectId());//项目ID
        queryViewAppHouseInfo.setFormatName(houseFormatEntity.getName());//业态名称
        queryViewAppHouseInfo.setBlockName(houseBuildingEntity.getName());//楼号
        queryViewAppHouseInfo.setCellNo(houseUnitEntity.getName());//单元
        queryViewAppHouseInfo.setHouseNo(houseRoomEntity.getName());//房间号
        ViewAppHouseInfoEntity viewAppHouseInfoEntity = viewAppHouseInfoRepository.getByQuery(queryViewAppHouseInfo);
        if(viewAppHouseInfoEntity == null){
            return null;
        }

        ViewAppHouseInfoAdminDTO viewAppHouseInfoAdminDTO = new ViewAppHouseInfoAdminDTO();
        viewAppHouseInfoAdminDTO.setViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
        viewAppHouseInfoAdminDTO.setProjectId(houseProjectEntity.getId());
        viewAppHouseInfoAdminDTO.setProjectName(houseProjectEntity.getName());
        viewAppHouseInfoAdminDTO.setBuildingName(houseBuildingEntity.getName());
        viewAppHouseInfoAdminDTO.setUnitName(houseUnitEntity.getName());
        viewAppHouseInfoAdminDTO.setRoomName(houseRoomEntity.getName());
        viewAppHouseInfoAdminDTO.setFormatName(houseFormatEntity.getName());

        return viewAppHouseInfoAdminDTO;
    }
}
