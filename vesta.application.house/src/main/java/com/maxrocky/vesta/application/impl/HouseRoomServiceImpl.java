package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.UserCrmForRoomidDTO;
import com.maxrocky.vesta.application.DTO.json.HI0005.RoomReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseRoomService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.DeliveryPlanCrmEntity;
import com.maxrocky.vesta.domain.model.HouseRoomEntity;
import com.maxrocky.vesta.domain.model.HouseUnitEntity;
import com.maxrocky.vesta.domain.repository.HouseRoomRepository;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 12:05.
 * Describe:单元Service接口实现类
 */
@Service
public class HouseRoomServiceImpl implements HouseRoomService {

    /* 房号 */
    @Autowired
    HouseRoomRepository houseRoomRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;

    /**
     * Code:HI0005
     * Type:UI Method
     * Describe:根据单元id获取房号列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 12:16:16
     */
    @Override
    public ApiResult getRoomListByUnitId(String unitId) {
        if(StringUtil.isEmpty(unitId)){
            return new ErrorApiResult("tip_00000541");
        }

        List<RoomReturnJsonDTO> roomJsonList = new ArrayList<RoomReturnJsonDTO>();
        List<HouseRoomEntity> houseRoomEntityList = houseRoomRepository.getListByUnitId(unitId);
        for (HouseRoomEntity houseRoomEntity : houseRoomEntityList){
            roomJsonList.add(mapper.map(houseRoomEntity, RoomReturnJsonDTO.class));
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("roomList", roomJsonList);

        return new SuccessApiResult(modelMap);
    }

    @Override
    public Map<String, String> getRoomsByFloorId(String floorId) {

        List<HouseRoomEntity> houseRoomEntityList = houseRoomRepository.getListByFloorId(floorId);
        Map<String,String> rooms = new LinkedHashMap<>();
        rooms.put("0", "请选择");
        if (houseRoomEntityList.size()>0){
            for (HouseRoomEntity houseRoomEntity:houseRoomEntityList){
                rooms.put(houseRoomEntity.getRoomNum(), houseRoomEntity.getName());
            }
        }

        return rooms;
    }

    @Override
    public Map<String, String> getRoomsByFloorNum(String floorNum) {

        List<HouseRoomEntity> houseRoomEntityList = houseRoomRepository.getListByFloorNum(floorNum);
        Map<String,String> rooms = new LinkedHashMap<>();
        rooms.put("0", "请选择");
        if (houseRoomEntityList.size()>0){
            for (HouseRoomEntity houseRoomEntity:houseRoomEntityList){
                //由于#导致url分页功能不能使用 对#号进行转义
                String roomNum = houseRoomEntity.getRoomNum();
                roomNum = roomNum.replaceAll("#","%23");
                rooms.put(roomNum, houseRoomEntity.getName());
            }
        }

        return rooms;
    }

    @Override
    public UserCrmForRoomidDTO getuserCrm(String id) {
        List<Object[]> user=houseRoomRepository.getUserCrmForRoomid(id);
        UserCrmForRoomidDTO UserCrmName=new UserCrmForRoomidDTO();
        if(user!=null){
            for(Object[] obj : user){
//                UserCrmName.setUserName(obj[1] == null ? "" : obj[1].toString());
//                UserCrmName.setUserMobile(obj[2] == null ? "" : obj[2].toString());
                UserCrmName.setBuilding(obj[2] == null ? "" : obj[2].toString());
//                if(obj[0]!=null &&!StringUtil.isEmpty(obj[0].toString())){
//                    UserCrmName.setBuilding(obj[0].toString());
//                }else if(obj[2]!=null &&!StringUtil.isEmpty(obj[2].toString())){
//                    UserCrmName.setBuilding(obj[2].toString());
//                }
                UserCrmName.setRoom(obj[1] == null ? "" : obj[1].toString());
                UserCrmName.setRommName(UserCrmName.getBuilding()+"号楼"+UserCrmName.getRoom()+"号房间");
            }
        }
        return UserCrmName;
    }

    @Override
    public Map<String, String> getPlanListByProjectNumAndType(String projectNum, String type) {
        List<Object[]> list=houseRoomRepository.getDeliveryPlanList(projectNum,type);
        Map<String,String> plans = new LinkedHashMap<>();
        plans.put("", "请选择");
        if(list!=null){
            for(Object[] obj : list){
                if(obj[2]!=null){
                    plans.put(obj[0].toString(),obj[2].toString());
                }else{
                    plans.put(obj[0].toString(),obj[1].toString());
                }
            }
        }
        return plans;
    }

    @Override
    public Map<String, String> getRoomsByPlanNum(String planNum) {
        List<Object[]> list=houseRoomRepository.getRoomByPlanList(planNum);
        Map<String,String> plans = new LinkedHashMap<>();
        plans.put("", "请选择");
        if(list!=null){
            for(Object[] obj : list){
               plans.put(obj[0].toString(),obj[1].toString());
            }
        }
        return plans;
    }

    @Override
    public Map<String, String> getRoomsByProjectNum(String projectNum) {
        List<Object[]> list=houseRoomRepository.getRoomByProjectList(projectNum);
//        List<Object[]> list=houseRoomRepository.getRoomByFloor(floorNum);
        Map<String,String> projects = new LinkedHashMap<>();
        projects.put("", "请选择");
        if(list!=null){
            for(Object[] obj : list){
                projects.put(obj[0].toString(),obj[1].toString());
            }
        }
        return projects;
    }

    @Override
    public UserCrmForRoomidDTO getSignPrint(String roomNum) {
        List<Object[]> user=houseRoomRepository.getUserCrmByRoomNum(roomNum);
        UserCrmForRoomidDTO UserCrmName=new UserCrmForRoomidDTO();
        if(user!=null && user.size()>0){
            String userName="";
            for(Object[] obj : user){
                if(obj[0] !=null && !"".equals(obj[0].toString())){
                    userName=userName+obj[0].toString()+",";
                }
            }
            UserCrmName.setUserName(userName.substring(0,userName.length()-1));
        }
        List<Object[]> address=houseRoomRepository.getAddressByRoomNum(roomNum);
        if(address!=null && address.size()>0){
            for(Object[] obj : address){
                String roomName="";
                if(obj[0] !=null && !"".equals(obj[0].toString())){
                    roomName=obj[0].toString();
                }
                if(obj[2] !=null && !"".equals(obj[2].toString())){
                    roomName=roomName+obj[2].toString()+"楼栋";
                }else if(obj[1] !=null && !"".equals(obj[1].toString())){
                    roomName=roomName+obj[1].toString()+"楼栋";
                }
                if(obj[3] !=null && !"".equals(obj[3].toString())){
                    roomName=roomName+obj[3].toString();
                }
                if(obj[5] !=null && !"".equals(obj[5].toString())){
                    roomName=roomName+obj[5].toString();
                }
                UserCrmName.setRommName(roomName);
            }

        }
        return UserCrmName;
    }

    @Override
    public String getProjectNumById(String projectId){
        if(projectId != null && !"".equals(projectId)){
            return houseRoomRepository.getProjectNumById(projectId);
        }
        return null;
    }
}
