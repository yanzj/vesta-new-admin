package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.json.HI0005.RoomReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseFloorService;
import com.maxrocky.vesta.application.inf.HouseRoomService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseFloorEntity;
import com.maxrocky.vesta.domain.model.HouseRoomEntity;
import com.maxrocky.vesta.domain.repository.HouseFloorRepository;
import com.maxrocky.vesta.domain.repository.HouseRoomRepository;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

/**
 * Created by Tom on 2016/1/18 12:05.
 * Describe:单元Service接口实现类
 */
@Service
public class HouseFloorServiceImpl implements HouseFloorService {

    /* 房号 */
    @Autowired
    HouseFloorRepository houseFloorRepository;

    @Override
    public Map<String, String> getRoomsByUnitId(String unitId) {
        List<HouseFloorEntity> houseFloorEntityList = houseFloorRepository.getFloorListByUtilId(unitId);
        Map<String,String> rooms = new LinkedHashMap<>();
        rooms.put("0", "请选择");
        if (houseFloorEntityList.size()>0){
            for (HouseFloorEntity houseFloorEntity:houseFloorEntityList){
                rooms.put(houseFloorEntity.getId(), houseFloorEntity.getFloorName());
            }
        }

        return rooms;
    }

    @Override
    public Map<String, String> getRoomsByUnitNum(String unitNum) {
        List<HouseFloorEntity> houseFloorEntityList = houseFloorRepository.getFloorListByUnitNum(unitNum);
        Map<String,String> rooms = new LinkedHashMap<>();
        rooms.put("0", "请选择");
        if (houseFloorEntityList.size()>0){
            for (HouseFloorEntity houseFloorEntity:houseFloorEntityList){
                rooms.put(houseFloorEntity.getFloorCode(), houseFloorEntity.getFloorName());
            }
        }

        return rooms;
    }

    @Override
    public Map<String,String> getFloorByUnits(List<String> units){
        Map<String,String> floors = new LinkedHashMap<>();
        floors.put("0","请选择");
        if(units != null && units.size() > 0 ){
            //map key为list下标，value为单元下的楼层数
            Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < units.size(); i++) {
                //获取单元下的楼层
                List<HouseFloorEntity> floorList = houseFloorRepository.getFloorListByUnitNum(units.get(i));
                map.put(i,floorList.size());
            }
            if(map != null){
                Collection<Integer> c = map.values();
                Object[] obj = c.toArray();
                //排序
                Arrays.sort(obj);
                //获取最后一个
                int index =  (int)obj[obj.length-1];
                List<HouseFloorEntity> floorList = houseFloorRepository.getFloorListByUnitNum(units.get(index));
                if (floorList.size()>0){
                    for (HouseFloorEntity houseFloorEntity:floorList){
                        floors.put(houseFloorEntity.getFloorCode(), houseFloorEntity.getFloorName());
                    }
                }
            }
        }
        return floors;
    }
}
