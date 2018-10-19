package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseBuildingJSONDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseRoomTypeDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.BuildingMappingTimeDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.BuildingMappingUpDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/4.
 */
public interface HouseRoomTypeService {

    /**
     * 获取户型
     * @param houseRoomTypeDTO
     * @return
     */
    public List<HouseRoomTypeDTO> getHouseTypeList(HouseRoomTypeDTO houseRoomTypeDTO);

    /**
     * 获取楼层房间数
     * @param houseRoomTypeDTO
     * @return
     */
    public List<HouseRoomTypeDTO> getFloorRooms(HouseRoomTypeDTO houseRoomTypeDTO);

    /**
     * 查询单元楼层
     * @param houseRoomTypeDTO
     * @return
     */
    public List<HouseRoomTypeDTO> getFloorListByUnitId(HouseRoomTypeDTO houseRoomTypeDTO);

    /**
     * 根据项目ID获取区域信息
     * @param projectId
     * @return
     */
    public Map getAreaListByProjectId(String projectId);

    /**
     *保存房间户型
     * @param houseRoomTypeDTO
     */
    public void saveRoomTypeLabel(HouseRoomTypeDTO houseRoomTypeDTO);


//    /**
//     * 查询单元楼层
//     * @param unitId
//     * @return
//     */
//    public Map<String,String> getFloorsByUnitId(String unitId);

    /**
     * 根据ID和时间获取
     * @param modifyDate
     * @param id
     * @return
     */
    BuildingMappingUpDTO getListByModifyTimeAndId(String modifyDate,String id,String projectNum);

    /**
     * 根据房间编码获取房间户型图片地址
     * @param roomNum
     * @return
     */
    HouseTypeDTO getHouseRoomTypeUrlByRoomNum(String roomNum);

}
