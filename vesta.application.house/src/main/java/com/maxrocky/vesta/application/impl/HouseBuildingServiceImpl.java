package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseBuildingDTO;
import com.maxrocky.vesta.application.DTO.json.HI0003.BuildingReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseBuildingService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.HouseAreaEntity;
import com.maxrocky.vesta.domain.model.HouseBuildingEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.repository.HouseBuildingRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

/**
 * Created by Tom on 2016/1/18 11:17.
 * Describe:楼Service接口实现类
 */
@Service
public class HouseBuildingServiceImpl implements HouseBuildingService {

    /* 楼 */
    @Autowired
    HouseBuildingRepository houseBuildingRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;

    /**
     * Code:HI0003
     * Type:UI Method
     * Describe:根据项目Id获取楼列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:27:37
     */
    @Override
    public ApiResult getBuildingListByProjectId(String projectId, String formatId) {

        if (StringUtil.isEmpty(projectId)) {
            return new ErrorApiResult("tip_00000539");
        }

        List<BuildingReturnJsonDTO> buildingJsonList = new ArrayList<BuildingReturnJsonDTO>();
        List<HouseBuildingEntity> houseBuildingEntityList = houseBuildingRepository.getListByProjectId(projectId, formatId);
        for (HouseBuildingEntity houseBuildingEntity : houseBuildingEntityList) {
            buildingJsonList.add(mapper.map(houseBuildingEntity, BuildingReturnJsonDTO.class));
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("buildingList", buildingJsonList);

        return new SuccessApiResult(modelMap);
    }

    public Map getBuildListByProjectNum(String projectNum) {
        List<HouseBuildingEntity> list = houseBuildingRepository.getBuildListByProjectNum(projectNum);
        Map<String, String> buildings = new LinkedHashMap<>();
        buildings.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseBuildingEntity houseBuildingEntity : list) {
                /*****update***公共区域区分**************预售楼号*****备案楼号***取其中一个************20161021*******Marco****start*****/
                String buildingNum = StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord()) ? houseBuildingEntity.getBuildingSale() : houseBuildingEntity.getBuildingRecord();
                if (StringUtil.isEmpty(buildingNum)) {
                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
                } else {
                    buildings.put(houseBuildingEntity.getBuildingNum(), buildingNum);
                }
                /*****update***公共区域区分**************预售楼号*****备案楼号***取其中一个************20161021*******Marco****end*****/
              /*  if(StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())){
                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
                }else{
                    buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingRecord());
                }*/
            }
        }
        return buildings;
    }

    @Override
    public Map getProjectListByCityNum(String cityNum) {
        List<HouseProjectEntity> list = houseBuildingRepository.getProjectListByCityNum(cityNum);
        Map<String, String> projects = new LinkedHashMap<>();
        projects.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseProjectEntity houseProjectEntity : list) {
                if (!StringUtil.isEmpty(houseProjectEntity.getInstalment())) {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName() + "" + houseProjectEntity.getInstalment());
                } else {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName());
                }
            }
        }
        return projects;
    }

    /**
     * 通过项目编码获取地块列表 WeiYangDong_2016-11-04
     *
     * @param projectNum 项目编码
     * @return Map
     */
    public Map getAreaListByProjectNum(String projectNum) {
        List<HouseAreaEntity> list = houseBuildingRepository.getAreaListByProjectNum(projectNum);
        Map<String, String> areas = new LinkedHashMap<>();
        areas.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseAreaEntity houseAreaEntity : list) {
                String areaid = houseAreaEntity.getBlockCode().substring(houseAreaEntity.getBlockCode().lastIndexOf("-") + 1, houseAreaEntity.getBlockCode().length());
                if ("#G".equals(areaid)) {
                    areas.put(houseAreaEntity.getBlockCode(), "公共区域");
                } else {
                    areas.put(houseAreaEntity.getBlockCode(), houseAreaEntity.getName());
                }
            }
        }
        return areas;
    }

    /**
     * 通过地块编码获取楼栋列表 WeiYangDong_2016-11-04
     *
     * @param blockNum 地块编码
     * @return Map
     */
    public Map getBuildListByBlockNum(String blockNum) {
        List<HouseBuildingEntity> list = houseBuildingRepository.getBuildListByBlockNum(blockNum);
        Map<String, String> buildings = new LinkedHashMap<>();
        buildings.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseBuildingEntity houseBuildingEntity : list) {
                if(!StringUtil.isEmpty(houseBuildingEntity.getBuildingNum())){
                    String buildingNum = houseBuildingEntity.getBuildingNum().substring(houseBuildingEntity.getBuildingNum().lastIndexOf("-") + 1, houseBuildingEntity.getBuildingNum().length());
                    if ("#G".equals(buildingNum)) {
                        buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
                    } else {
                        if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())){
                            buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingRecord());
                        }else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())){
                            buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingSale());
                        }else if (!StringUtil.isEmpty(houseBuildingEntity.getName())){
                            buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getName());
                        }
                    }
                }
//                if ("#G".equals(buildingNum)) {
//                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
//                } else {
//                    if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())) {
//                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingSale());
//                    } else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
//                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingRecord());
//                    }
//                }
            }
        }
        return buildings;
    }

    @Override
    public Map getBuildListByProjectId(String projectId) {
        List<HouseBuildingEntity> list = houseBuildingRepository.getBuildListByProjectId(projectId);
        Map<String, String> buildings = new LinkedHashMap<>();
        buildings.put("0", "请选择");
       /* if (list.size()>0){
            for (HouseBuildingEntity houseBuildingEntity:list){
                if(!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())){
                    buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingRecord());
                }else{
                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
                }
            }
        }*/
        if (list.size() > 0) {
            for (HouseBuildingEntity houseBuildingEntity : list) {
                String buildingNum = houseBuildingEntity.getBuildingNum().substring(houseBuildingEntity.getBuildingNum().lastIndexOf("-") + 1, houseBuildingEntity.getBuildingNum().length());
                if ("#G".equals(buildingNum)) {
                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
                } else {
                    if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingRecord());
                    }else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())) {
                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingSale());
                    }
                }

//                if ("#G".equals(buildingNum)) {
//                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
//                } else {
//                    if (!StringUtil.isEmpty(houseBuildingEntity.getName())) {
//                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getName());
//                    } else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())) {
//                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingSale());
//                    } else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
//                        buildings.put(houseBuildingEntity.getBuildingNum(), houseBuildingEntity.getBuildingRecord());
//                    }
//                }


//                /*****update***公共区域区分**************预售楼号*****备案楼号***取其中一个************20161021*******Marco****start*****/
//                String buildingNum = StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord()) ? houseBuildingEntity.getBuildingSale() : houseBuildingEntity.getBuildingRecord();
//                if (StringUtil.isEmpty(buildingNum)) {
//                    buildings.put(houseBuildingEntity.getBuildingNum(), "公共区域");
//                } else {
//                    buildings.put(houseBuildingEntity.getBuildingNum(), buildingNum);
//                }
//                /*****update***公共区域区分**************预售楼号*****备案楼号***取其中一个************20161021*******Marco****end*****/
            }
        }
        return buildings;
    }

    /**
     * Code:D
     * Type:
     * Describe:获得楼栋信息->修改别名
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/30
     */
    @Override
    public List<Map<String, String>> getBuildInfoListByProjectId(String projectId, String buildingId, String alias, WebPage webPage) {
        if (StringUtil.isEmpty(projectId)) {
            return null;
        }
        List<HouseBuildingEntity> list = houseBuildingRepository.getBuildAliasListByProjectId(projectId, buildingId, alias, webPage,"0");
        List<Map<String, String>> buildingList = new ArrayList<>();
        if (list.size() > 0) {
            for (HouseBuildingEntity houseBuildingEntity : list) {
                Map<String, String> buildings = new LinkedHashMap<>();
                buildings.put("buildingRecord", houseBuildingEntity.getBuildingRecord());
                buildings.put("buildingNum", houseBuildingEntity.getBuildingNum());

                String buildingNum = houseBuildingEntity.getBuildingNum().substring(houseBuildingEntity.getBuildingNum().lastIndexOf("-") + 1, houseBuildingEntity.getBuildingNum().length());
                if ("#G".equals(buildingNum)) {
                    buildings.put("name", "公共区域");
                } else {
                    if (!StringUtil.isEmpty(houseBuildingEntity.getName())) {
                        buildings.put("name", houseBuildingEntity.getName());
                    } else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())) {
                        buildings.put("name", houseBuildingEntity.getBuildingSale());
                    } else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
                        buildings.put("name", houseBuildingEntity.getBuildingRecord());
                    }
                }
                if ("#G".equals(buildingNum)) {
                    buildings.put("buildingRecord", "公共区域");
                } else {
                    if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())) {
                        buildings.put("buildingRecord", houseBuildingEntity.getBuildingSale());
                    } else if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
                        buildings.put("buildingRecord", houseBuildingEntity.getBuildingRecord());
                    }
                }
                buildings.put("area", houseBuildingEntity.getStreet());
                buildingList.add(buildings);
            }
        }

        return buildingList;
    }

    @Override
    public List<Map<String, String>> getBuildInfoListByProjectIdNew(String projectId, String buildingId, String alias, WebPage webPage,String areaId) {
        if (StringUtil.isEmpty(projectId)) {
            return null;
        }
        List<HouseBuildingEntity> list = houseBuildingRepository.getBuildAliasListByProjectId(projectId, buildingId, alias, webPage,areaId);
        List<Map<String, String>> buildingList = new ArrayList<>();
        if (list.size() > 0) {
            for (HouseBuildingEntity houseBuildingEntity : list) {
                Map<String, String> buildings = new LinkedHashMap<>();
                buildings.put("buildingRecord", houseBuildingEntity.getBuildingRecord());
                buildings.put("buildingNum", houseBuildingEntity.getBuildingNum());

                String buildingNum = houseBuildingEntity.getBuildingNum().substring(houseBuildingEntity.getBuildingNum().lastIndexOf("-") + 1, houseBuildingEntity.getBuildingNum().length());
                if ("#G".equals(buildingNum)) {
                    buildings.put("name", "公共区域");
                } else {
                    if (!StringUtil.isEmpty(houseBuildingEntity.getName())) {
                        buildings.put("name", houseBuildingEntity.getName());
                    }
                }
                if ("#G".equals(buildingNum)) {
                    buildings.put("buildingRecord", "公共区域");
                } else {
                    if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
                        buildings.put("buildingRecord", houseBuildingEntity.getBuildingRecord());
                    }
                }
                if ("#G".equals(buildingNum)) {
                    buildings.put("buildingSale", "公共区域");
                } else {
                    if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())) {
                        buildings.put("buildingSale", houseBuildingEntity.getBuildingSale());
                    }
                }
                buildings.put("area", houseBuildingEntity.getStreet());
                buildingList.add(buildings);
            }
        }

        return buildingList;
    }

    @Override
    public List<HouseBuildingDTO> getHouseBuildingList(String projectId, String buildingId, String alias, WebPage webPage) {
        if (StringUtil.isEmpty(projectId)) {
            return null;
        }
        List<HouseBuildingEntity> list = houseBuildingRepository.getBuildAliasListByProjectId(projectId, buildingId, alias, webPage,"0");
        List<HouseBuildingDTO> houseBuildingDTOList = new ArrayList<HouseBuildingDTO>();
        if (list != null && list.size() > 0) {
            for (HouseBuildingEntity houseBuildingEntity : list) {
                HouseBuildingDTO houseBuildingDTO = new HouseBuildingDTO();
                houseBuildingDTO.setName(houseBuildingEntity.getName());
                houseBuildingDTO.setBuildingNum(houseBuildingEntity.getBuildingNum());
                houseBuildingDTO.setBuildingRecord(houseBuildingEntity.getBuildingRecord());
                houseBuildingDTO.setId(houseBuildingEntity.getId());
                houseBuildingDTO.setStreet(houseBuildingEntity.getStreet());

                houseBuildingDTOList.add(houseBuildingDTO);
            }
        }
        return houseBuildingDTOList;
    }

    @Override
    public void updateBuildingAlias(String buildingNum, String buildingAlias) {
        HouseBuildingEntity houseBuildingEntity = houseBuildingRepository.getInfoByBuildingNum(buildingNum);
        if (houseBuildingEntity != null) {
            houseBuildingEntity.setName(buildingAlias);
            houseBuildingRepository.updateBuildingInfo(houseBuildingEntity);
        }
        BuildingMappingTimeEntity buildingMappingTimeEntity = houseBuildingRepository.getBuildingMappingInfoByBuildingNum(buildingNum);
        if (buildingMappingTimeEntity != null) {
            buildingMappingTimeEntity.setBuildingAlias(buildingAlias);
            buildingMappingTimeEntity.setTimeStamp(new Date());
            houseBuildingRepository.updateBuildingMappingInfo(buildingMappingTimeEntity);
        }
//        houseBuildingRepository.updateBuildingAlias(buildingNum, buildingAlias);
    }


}
