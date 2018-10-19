package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseRoomTypeDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.BuildingMappingTimeDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.BuildingMappingUpDTO;
import com.maxrocky.vesta.application.inf.HouseRoomTypeService;
import com.maxrocky.vesta.domain.model.HouseAreaEntity;
import com.maxrocky.vesta.domain.model.HouseRoomTypeEntity;
import com.maxrocky.vesta.domain.repository.HouseAreaRepository;
import com.maxrocky.vesta.domain.repository.HouseInfoRepository;
import com.maxrocky.vesta.domain.repository.HouseRoomTypeRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mql on 2016/6/4.
 */
@Service
public class HouseRoomTypeServiceImpl implements HouseRoomTypeService {

    @Autowired
    HouseRoomTypeRepository houseRoomTypeRepository;

    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Autowired
    HouseAreaRepository houseAreaRepository;


    @Override
    public List<HouseRoomTypeDTO> getHouseTypeList(HouseRoomTypeDTO houseRoomTypeDTO) {
        List<Object[]> list = houseRoomTypeRepository.getRoomTypeList(houseRoomTypeDTO.getProjectId(), houseRoomTypeDTO.getAreaId(), houseRoomTypeDTO.getBuildingId(), houseRoomTypeDTO.getUnitId());
        List<HouseRoomTypeDTO> roomList = new ArrayList<HouseRoomTypeDTO>();
        if(list !=null && !list.isEmpty()){
            for(Object[] obj : list){
                HouseRoomTypeDTO hrt = new HouseRoomTypeDTO();
                hrt.setRoomId(obj[0]==null?"":obj[0].toString());
                hrt.setRoomNum(obj[1] == null ? "" : obj[1].toString());
                hrt.setRoomName(obj[2] == null ? "" : obj[2].toString());
                hrt.setFloor(obj[3] == null ? null : obj[3].toString());
                hrt.setHouseTypeName(obj[4] == null ? "" : obj[4].toString());
                hrt.setFloorName(obj[5] == null ? "" : obj[5].toString());
                hrt.setUnitId(obj[6] == null ? "" : obj[6].toString());
                hrt.setHouseType(obj[7] == null ? "" : obj[7].toString());
                roomList.add(hrt);
            }
        }
        return roomList;
    }

    @Override
    public List<HouseRoomTypeDTO> getFloorRooms(HouseRoomTypeDTO houseRoomTypeDTO) {
        List<String> list = houseRoomTypeRepository.getFloorRooms(houseRoomTypeDTO.getProjectId(), houseRoomTypeDTO.getAreaId(), houseRoomTypeDTO.getBuildingId(), houseRoomTypeDTO.getUnitId());
        List<HouseRoomTypeDTO> roomsList = new ArrayList<HouseRoomTypeDTO>();
        if(list !=null && !list.isEmpty()){
            for(String obj : list){
                HouseRoomTypeDTO hrt = new HouseRoomTypeDTO();
                hrt.setRoomName(obj);
                roomsList.add(hrt);
            }
        }
        return roomsList;
    }

    @Override
    public List<HouseRoomTypeDTO> getFloorListByUnitId(HouseRoomTypeDTO houseRoomTypeDTO) {
        List<String> list = houseInfoRepository.getFloorListByUnitId(houseRoomTypeDTO.getProjectId(), houseRoomTypeDTO.getAreaId(), houseRoomTypeDTO.getBuildingId(), houseRoomTypeDTO.getUnitId());
        List<HouseRoomTypeDTO> roomsList = new ArrayList<HouseRoomTypeDTO>();
        if(list !=null && !list.isEmpty()){
            for(String obj : list){
                HouseRoomTypeDTO hrt = new HouseRoomTypeDTO();
                hrt.setRoomName(obj);
                roomsList.add(hrt);
            }
        }
        return roomsList;
    }

    @Override
    public Map getAreaListByProjectId(String projectId) {
        List<HouseAreaEntity> list = houseAreaRepository.getInfoByProjectId(projectId);
        Map<String,String> areas = new LinkedHashMap<>();
        areas.put("0","请选择");
        if (list.size()>0){
            for (HouseAreaEntity houseAreaEntity:list){
                areas.put(houseAreaEntity.getId(), houseAreaEntity.getName());
            }
        }
        return areas;
    }

    @Override
    public void saveRoomTypeLabel(HouseRoomTypeDTO houseRoomTypeDTO) {

        if(houseRoomTypeDTO.getRoomId()!=null && !"".equals(houseRoomTypeDTO.getRoomId())){
            houseRoomTypeRepository.deleteByRoomId(houseRoomTypeDTO.getRoomNum());
            houseRoomTypeRepository.saveByRoomId(houseRoomTypeDTO.getRoomId(),houseRoomTypeDTO.getRoomNum(), houseRoomTypeDTO.getHouseType());
        }else if(houseRoomTypeDTO.getHouseTypeName() != null && !"".equals(houseRoomTypeDTO.getHouseTypeName())){
            houseRoomTypeRepository.deleteByUnitId(houseRoomTypeDTO.getUnitId(),houseRoomTypeDTO.getHouseTypeName());
            houseRoomTypeRepository.saveByUnitId(houseRoomTypeDTO.getUnitId(),houseRoomTypeDTO.getHouseTypeName(),houseRoomTypeDTO.getHouseType());
        }

    }

    @Override
    public BuildingMappingUpDTO getListByModifyTimeAndId(String modifyDate, String id,String projectNum) {
        List<HouseRoomTypeEntity> list = houseRoomTypeRepository.getByModifyDateAndId(modifyDate,id,projectNum);
        BuildingMappingUpDTO buildingMappingUpDTO = new BuildingMappingUpDTO();
        List<BuildingMappingTimeDTO> retList = new ArrayList<BuildingMappingTimeDTO>();
        if(list != null && !list.isEmpty()){
            buildingMappingUpDTO.setTimeStamp(DateUtils.format(list.get(list.size() - 1).getModifyTime()));
            buildingMappingUpDTO.setId(list.get(list.size()-1).getId()+"");
            for(HouseRoomTypeEntity obj : list){
                BuildingMappingTimeDTO buildingMappingTimeDTO = new BuildingMappingTimeDTO();
                buildingMappingTimeDTO.setCurrentId(obj.getId()+"");
                buildingMappingTimeDTO.setParentId(obj.getRoomId());
                buildingMappingTimeDTO.setParentNum(obj.getRoomNum());
                buildingMappingTimeDTO.setName(obj.getHouseType()+"");
                buildingMappingTimeDTO.setGraded("6");//户型
                retList.add(buildingMappingTimeDTO);
            }
        }
        buildingMappingUpDTO.setList(retList);
        return buildingMappingUpDTO;
    }

    @Override
    public HouseTypeDTO getHouseRoomTypeUrlByRoomNum(String roomNum) {
        Object[] obj = houseRoomTypeRepository.getRoomHouseType(roomNum);
        HouseTypeDTO houseTypeDTO = new HouseTypeDTO();
        if(obj != null){
            houseTypeDTO.setName(obj[0]==null?"":obj[0].toString());
            houseTypeDTO.setImgUrl(obj[1] == null ? "" : obj[1].toString());
            houseTypeDTO.setId(obj[2]==null?"":obj[2].toString());
            return houseTypeDTO;
        }else{
            return null;
        }
    }


}
