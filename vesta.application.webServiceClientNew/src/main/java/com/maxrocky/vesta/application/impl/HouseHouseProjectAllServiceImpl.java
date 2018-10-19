package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.HouseHouseProjectAllService;
import com.maxrocky.vesta.application.model.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/2.
 */
@Service
public class HouseHouseProjectAllServiceImpl implements HouseHouseProjectAllService {
//    static  List<PreInspectionList> PreInspectionStatic=new ArrayList<PreInspectionList>();
    @Autowired
    RectificationRepository rectificationRepository;
    @Override
    public List<PreInspectionList> getProjectListAllRoom() {
        {
            List<PreInspectionList> PreInspectionList = new ArrayList<PreInspectionList>();
            List<HouseProjectEntity> housList = rectificationRepository.gethousListAll();//获取所有项目信息
            if (housList != null) {
                for (HouseProjectEntity HouseProject : housList) {
                    PreInspectionList PreInspection = new PreInspectionList();
                    PreInspection.setProjectid(HouseProject.getId() == null ? "" : HouseProject.getId());
                    PreInspection.setProjectName(HouseProject.getName() == null ? "" : HouseProject.getName());
                    PreInspection.setCompanyId(HouseProject.getCompanyId() == null ? "" : HouseProject.getCompanyId());
                    PreInspection.setInstalment(HouseProject.getInstalment() == null ? "" : HouseProject.getInstalment());
                    PreInspection.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                    //根据项目id获取楼栋信息取消

                    List<HouseBuildingEntity> HouseBuildingList = rectificationRepository.gethouseBuilding(HouseProject.getId());
                    List<BuildingListDTO> BuildingList = new ArrayList<BuildingListDTO>();
                    if (HouseBuildingList != null) {
                        for (HouseBuildingEntity HouseBuilding : HouseBuildingList) {
                            BuildingListDTO BuildingListDTO = new BuildingListDTO();
                            BuildingListDTO.setBuildingId(HouseBuilding.getId() == null ? "" : HouseBuilding.getId());
                            BuildingListDTO.setBuildingName(HouseBuilding.getName() == null ? "" : HouseBuilding.getBuildingRecord());
                            BuildingListDTO.setBuildingNum(HouseBuilding.getBuildingNum() == null ? "" : HouseBuilding.getBuildingNum());
                            BuildingListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                            //根据buildingID获取单元信息
                            List<HouseUnitEntity> houseUnit = rectificationRepository.gethouseUnit(HouseBuilding.getId() == null ? "" : HouseBuilding.getId());
                            List<UnitListDTO> UnitList = new ArrayList<UnitListDTO>();
                            if (houseUnit != null) {
                                for (HouseUnitEntity HouseUnit : houseUnit) {
                                    UnitListDTO UnitListDTO = new UnitListDTO();
                                    UnitListDTO.setUnitId(HouseUnit.getId() == null ? "" : HouseUnit.getId());
                                    UnitListDTO.setUnitName(HouseUnit.getName() == null ? "" : HouseUnit.getName());
                                    UnitListDTO.setUnitNum(HouseUnit.getUnitCode() == null ? "" : HouseUnit.getUnitCode());
                                    UnitListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                                    List<HouseFloorEntity> HouseFloor = rectificationRepository.getfloorList(HouseUnit.getId() == null ? "" : HouseUnit.getId());
                                    List<FloorListDTO> FloorList = new ArrayList<FloorListDTO>();
                                    if (HouseFloor != null) {
                                        for (HouseFloorEntity HouseFloorEntity : HouseFloor) {
                                            FloorListDTO FloorListDTO = new FloorListDTO();
                                            FloorListDTO.setFloorId(HouseFloorEntity.getId() == null ? "" : HouseFloorEntity.getId());
                                            FloorListDTO.setFloorName(HouseFloorEntity.getFloorName() == null ? "" : HouseFloorEntity.getFloorName());
                                            FloorListDTO.setFloorNum(HouseFloorEntity.getFloorCode() == null ? "" : HouseFloorEntity.getFloorCode());
                                            FloorListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                                            List<HouseRoomEntity> houseRoom = rectificationRepository.gethouseroom(HouseFloorEntity.getFloorCode() == null ? "" : HouseFloorEntity.getFloorCode());
                                            List<RoomListDTO> RoomList = new ArrayList<RoomListDTO>();
                                            if (houseRoom != null) {
                                                for (HouseRoomEntity HouseRoomEntity : houseRoom) {
                                                    RoomListDTO RoomListDTO = new RoomListDTO();
                                                    RoomListDTO.setRoomId(HouseRoomEntity.getId() == null ? "" : HouseRoomEntity.getId());
                                                    RoomListDTO.setRoomName(HouseRoomEntity.getName() == null ? "" : HouseRoomEntity.getName());
                                                    RoomListDTO.setRoomNum(HouseRoomEntity.getRoomNum() == null ? "" : HouseRoomEntity.getRoomNum());
                                                    RoomListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                                                    RoomList.add(RoomListDTO);
                                                }
                                            }
                                            FloorListDTO.setRoomList(RoomList);
                                            FloorList.add(FloorListDTO);
                                        }
                                    }
                                    UnitListDTO.setFloorList(FloorList);
                                    UnitList.add(UnitListDTO);
                                }

                            }
                            BuildingListDTO.setUnitList(UnitList);
                            BuildingList.add(BuildingListDTO);
                        }
                    }

                    PreInspection.setBuilding(BuildingList);
                    PreInspectionList.add(PreInspection);

                }
            }
            PreInspectionJSON.PreInspectionStatic.clear();
            PreInspectionJSON.PreInspectionStatic.addAll(PreInspectionList);
            return PreInspectionList;
        }
    }
}
