package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseInfoAdminDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseUserAdminDTO;
import com.maxrocky.vesta.application.DTO.json.HI0007.HouseInfoReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.HI0008.MyHouseReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.HI0008.MyProjectReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Tom on 2016/1/19 9:35.
 * Describe:房屋信息Service接口实现类
 */
@Service
public class HouseInfoServiceImpl implements HouseInfoService {

    /* 房产信息 */
    @Autowired
    HouseInfoRepository houseInfoRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;
    @Autowired
    HouseProjectRepository houseProjectRepository;
    @Autowired
    HouseBuildingRepository houseBuildingRepository;
    @Autowired
    HouseUnitRepository houseUnitRepository;
    @Autowired
    HouseRoomRepository houseRoomRepository;
    @Autowired
    HouseUserBookRepository houseUserBookRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;
    @Autowired
    private SystemLogRepository systemLogRepository;
    /*@Autowired
    UserSettingService userSettingService;*/
    @Autowired
    PropertyDoorRepository propertyDoorRepository;
    @Autowired
    UserSettingRepository userSettingRepository;
    /**
     * Code:For Service
     * Type:Service Method
     * Describe:添加房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:00:27
     */
    @Override
    public ApiResult createHouseInfoEntity(HouseInfoEntity houseInfoEntity) {
        houseInfoRepository.create(houseInfoEntity);
        return new SuccessApiResult();
    }

    /**
     * Code:HI0007
     * Type:UI Method
     * Describe:获取用户下房产
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:37:29
     */
    @Override
    public ApiResult getHouseListByUserId(String userId,String projectId) {
        if(StringUtil.isEmpty(projectId)){
            return new ErrorApiResult("error_00000003");
        }

        List<HouseInfoReturnJsonDTO> houseInfoReturnJsonDTOList = new ArrayList<HouseInfoReturnJsonDTO>();
        List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getListByUserIdAndProjectId(userId, projectId);
        for (HouseInfoEntity houseInfoEntity : houseInfoEntityList){
            houseInfoReturnJsonDTOList.add(mapper.map(houseInfoEntity, HouseInfoReturnJsonDTO.class));
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("houseList", houseInfoReturnJsonDTOList);

        return new SuccessApiResult(modelMap);
    }

    /**
     * Code:HI0008
     * Type:UI Method
     * Describe:获取用户房产
     * CreateBy:Tom
     * CreateOn:2016-01-20 01:57:17
     */
    @Override
    public ApiResult getHouseListByUserId(String userId) {

        List<MyProjectReturnJsonDTO> myProjectReturnJsonDTOList = new ArrayList<MyProjectReturnJsonDTO>();
        List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getListByUserIdAndProjectId(userId, "");
        for (HouseInfoEntity houseInfoEntity : houseInfoEntityList){
            MyProjectReturnJsonDTO projectReturnJsonDTO = null;
            /* 循环项目列表是否存在，存在则删除 */
            for (int i = 0; i < myProjectReturnJsonDTOList.size(); i++) {
                if(StringUtil.isEqual(myProjectReturnJsonDTOList.get(i).getProjectId(), houseInfoEntity.getProjectId())){
                    projectReturnJsonDTO = myProjectReturnJsonDTOList.get(i);
                    myProjectReturnJsonDTOList.remove(i);
                }
            }

            if(projectReturnJsonDTO == null){
                projectReturnJsonDTO = new MyProjectReturnJsonDTO();
                projectReturnJsonDTO.setProjectId(houseInfoEntity.getProjectId());
                projectReturnJsonDTO.setProjectName(houseInfoEntity.getProjectName());
            }
            projectReturnJsonDTO.addCount();

            /* 添加项 */
            MyHouseReturnJsonDTO houseReturnJsonDTO = new MyHouseReturnJsonDTO();
            houseReturnJsonDTO.setHouseId(houseInfoEntity.getId());
            //houseReturnJsonDTO.setAddress(houseInfoEntity.getBuildingName() + houseInfoEntity.getUnitName() + houseInfoEntity.getRoomName());
            projectReturnJsonDTO.getHouseList().add(houseReturnJsonDTO);

            myProjectReturnJsonDTOList.add(projectReturnJsonDTO);
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("projectList", myProjectReturnJsonDTOList);

        return new SuccessApiResult(modelMap);
    }

    /**
     * Code:For UI0006、UI0009
     * Type:Service Method
     * Describe:根据房产Id获取房产
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:18:06
     */
    @Override
    public HouseInfoAdminDTO getHouseInfoEntityByHouseId(String houseId) {
        HouseInfoEntity houseInfoEntity = houseInfoRepository.get(houseId);
        if(houseInfoEntity == null){
            return null;
        }
        return mapper.map(houseInfoEntity, HouseInfoAdminDTO.class);
    }

    /**
     * Code:For UI0002
     * Type:Service Method
     * Describe:根据物业房产Id获取房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-21 02:19:01
     */
    @Override
    public HouseInfoAdminDTO getByViewAppHouseId(int houseId) {
        HouseInfoEntity houseInfoEntity = houseInfoRepository.getByViewAppHouseId(houseId);
        if(houseInfoEntity == null){
            return null;
        }
        return mapper.map(houseInfoEntity, HouseInfoAdminDTO.class);
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取用户其他的房产
     * ModifyBy:
     */
    @Override
    public ApiResult getOtherHouse(UserInfoEntity user) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try {
            List<HouseUserAdminDTO> houseDTO=new ArrayList<HouseUserAdminDTO>();
            List<Object[]> houseList=new ArrayList<>();
            if(user.getUserType().equals("4")){//同住人
                houseList = houseInfoRepository.getHousemateOtherHouse(user.getUserId());
                if (houseList.size() > 0) {
                    for (Object[] house : houseList) {
                        HouseUserAdminDTO houseUserAdminDTO = new HouseUserAdminDTO();
                        houseUserAdminDTO.setId((String)house[0]);//房屋id
                        houseUserAdminDTO.setAddress((String)house[1]);//房屋地址
                        houseUserAdminDTO.setState((String)house[2]);//是否默认房产
                        houseDTO.add(houseUserAdminDTO);
                    }
                }
            }else if(user.getUserType().equals("3") || user.getUserType().equals("6")){//业主
                houseList = houseInfoRepository.getOwnerOtherHouse(user.getId());
                if (houseList.size() > 0) {
                    for (Object[] house : houseList) {
                        HouseUserAdminDTO houseUserAdminDTO = new HouseUserAdminDTO();
                        houseUserAdminDTO.setId((String)house[0]);//房屋id
                        houseUserAdminDTO.setAddress((String)house[1]);//房屋地址
                        houseUserAdminDTO.setState((String)house[2]);//是否默认房产
                        houseDTO.add(houseUserAdminDTO);
                    }
                }
            }
            //业主并不是同住人情况
            if(houseList.size()==0){
                List<HouseInfoEntity> list=houseInfoRepository.getOtherHouse(user.getId());
                if(list.size()>0){
                    for(HouseInfoEntity house:list){
                        HouseUserAdminDTO houseUserAdminDTO = new HouseUserAdminDTO();
                        houseUserAdminDTO.setId(house.getId());//房屋id
                        houseUserAdminDTO.setAddress(house.getAddress());//房屋地址
                        houseUserAdminDTO.setState(house.getState());//是否默认房产
                        houseDTO.add(houseUserAdminDTO);
                    }
                }
            }
            //调用点击人统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"12",user.getUserId());
            if(clickUserEntity==null){
                ClickUsersEntity clickUser=new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(user.getUserId());
                clickUser.setForeignId("12");
                clickUserRepository.create(clickUser);
            }else{
                clickUserEntity.setClicks(clickUserEntity.getClicks()+1);
                clickUserRepository.update(clickUserEntity);
            }
            return new SuccessApiResult(houseDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过房屋id获取房屋详情信息
     * param houseId：房屋id
     * ModifyBy:
     */
    @Override
    public ApiResult getHouseInfo(UserInfoEntity user, String houseId) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(houseId)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try{
            //获取房屋信息
            Object[] houseInfo=houseInfoRepository.getById(houseId);
            HouseInfoAdminDTO houseInfoDTO=new HouseInfoAdminDTO();
            if(houseInfo!=null){
                houseInfoDTO.setAddress((String) houseInfo[0]);//地址
                houseInfoDTO.setHouseType((String) houseInfo[1]);//户型
                houseInfoDTO.setBuildingArea((BigDecimal) houseInfo[2]);//建筑面积
                houseInfoDTO.setUsableArea((BigDecimal) houseInfo[3]);//室内面积
            }
            return new SuccessApiResult(houseInfoDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 设置默认房产
     * param houseId：房屋id
     * ModifyBy:
     */
    @Override
    public ApiResult getDefaultHouse(UserInfoEntity user, String houseId) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(houseId)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try{
            if("4".equals(user.getUserType())){
                return ErrorResource.getError("tip_00000144");//您是同住人身份，暂无权限
            }
            //查询该房产下正常状态：用户是否已存在
            HouseUserBookEntity userBook = houseUserBookRepository.getByUserIdAndHouseId(user.getUserId(), houseId);
            if (userBook != null) {
                return ErrorResource.getError("tip_00000145");//该房屋您是同住人，暂无权限
            }
            HouseInfoEntity houseInfo=houseInfoRepository.getDefaultHouse(user.getId());
            if(houseInfo!=null){
                //获取房屋信息
                houseInfo.setState("1");//修改为非默认房产
                houseInfo.setModifyBy(user.getUserId());
                houseInfo.setModifyOn(new Date());
                houseInfoRepository.updateHouseInfo(houseInfo);
            }
            HouseInfoEntity house=houseInfoRepository.get(houseId);
            if(house!=null) {
                //获取房屋信息
                house.setState("0");//修改为默认房产
                house.setModifyBy(user.getUserId());
                house.setModifyOn(new Date());
                houseInfoRepository.updateHouseInfo(house);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"));
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取用户默认的房产
     * ModifyBy:
     */
    @Override
    public ApiResult getDefaultHoused(UserInfoEntity user) throws GeneralException {
        try {
            List<HouseUserAdminDTO> houseDTO=new ArrayList<HouseUserAdminDTO>();
            List<Object[]> houseList=new ArrayList<>();
            if(user.getUserType().equals("3") || user.getUserType().equals("6")) {//业主
                houseList = houseInfoRepository.getOwnerDefaultHouse(user.getUserId(), user.getId());
                if (houseList.size() > 0) {
                    for (Object[] house : houseList) {
                        HouseUserAdminDTO houseUserAdminDTO = new HouseUserAdminDTO();
                        houseUserAdminDTO.setId((String) house[0]);//房屋id
                        houseUserAdminDTO.setAddress((String) house[1]);//房屋地址
                        houseUserAdminDTO.setState((String) house[2]);//是否默认房产
                        houseDTO.add(houseUserAdminDTO);
                    }
                }
            }else if(user.getUserType().equals("4")){//同住人
                houseList=houseInfoRepository.getHousemateDefaultHouse(user.getUserId());
                if(houseList.size()>0){
                    for (Object[] house : houseList) {
                        HouseUserAdminDTO houseUserAdminDTO = new HouseUserAdminDTO();
                        houseUserAdminDTO.setId((String) house[0]);//房屋id
                        houseUserAdminDTO.setAddress((String) house[1]);//房屋地址
                        houseUserAdminDTO.setState((String) house[2]);//是否默认房产
                        houseDTO.add(houseUserAdminDTO);
                    }
                }
            }
            //增加用户日志记录
            UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
            userVisitLogEntity.setUserName(user.getNickName());//用户昵称
            userVisitLogEntity.setUserType(user.getUserType());//用户类型
            userVisitLogEntity.setUserMobile(user.getMobile());//手机号
            userVisitLogEntity.setSourceFrom(user.getSourceType());//来源
            userVisitLogEntity.setThisSection("我的");
            userVisitLogEntity.setThisFunction("我的房产");
            userVisitLogEntity.setLogContent("");
           // UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
            UserSettingEntity userSettingEntity = userSettingRepository.get(user.getUserId());
            if (null != userSettingEntity) {
                userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
            } else{
                userVisitLogEntity.setProjectId("");//项目
            }
            systemLogRepository.addUserVisitLog(userVisitLogEntity);
            return new SuccessApiResult(houseDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取用户所有的房产
     * ModifyBy:
     */
    @Override
    public ApiResult getAllHouse(UserInfoEntity user) throws GeneralException {
        try {
            List<HouseUserAdminDTO> houseDTO = new ArrayList<>();
            List<Object[]> houseList=new ArrayList<>();
            if(user.getUserType().equals("3") || user.getUserType().equals("6")) {//业主
                houseList = houseInfoRepository.getAllHouse(user.getId());
                if (houseList.size() > 0) {
                    for (Object[] houseInfo : houseList) {
                        HouseUserAdminDTO house = new HouseUserAdminDTO();
                        house.setId((String) houseInfo[0]);//房屋id
                        house.setAddress((String) houseInfo[1]);//房屋地址
                        house.setState((String) houseInfo[2]);//是否默认房产
                        houseDTO.add(house);
                    }
                }
            }else if(user.getUserType().equals("4")){//同住人
                houseList=houseInfoRepository.getHousemateHouse(user.getUserId());
                if (houseList.size() > 0) {
                    for (Object[] houseInfo : houseList) {
                        HouseUserAdminDTO house = new HouseUserAdminDTO();
                        house.setId((String) houseInfo[0]);//房屋id
                        house.setAddress((String) houseInfo[1]);//房屋地址
                        house.setState((String) houseInfo[2]);//是否默认房产
                        houseDTO.add(house);
                    }
                }
            }
            return new SuccessApiResult(houseDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public LinkedHashMap listProject() {
        List<HouseProjectEntity> projectEntities = houseProjectRepository.getListOfNormal();
        LinkedHashMap project = new LinkedHashMap();
        project.put("0","--请选择项目--");
        int i=1;
        if (projectEntities.size()>0){
            for(HouseProjectEntity houseProjectEntity:projectEntities){
                project.put(i++,houseProjectEntity.getName());
            }
        }
        return project;
    }

    @Override
    public LinkedHashMap listUnit() {
        List<HouseUnitEntity> unitEntities = houseUnitRepository.mapUnit("999");
        LinkedHashMap unit = new LinkedHashMap();
        unit.put("0","--请选择单元--");
        int i=1;
        if (unitEntities.size()>0){
            for (HouseUnitEntity unitEntity:unitEntities){
                unit.put(i++, unitEntity.getName());
            }
        }
        return unit;
    }

    @Override
    public LinkedHashMap listBuild() {
        List<HouseBuildingEntity> buildEntities = houseBuildingRepository.mapBuild("999");
        LinkedHashMap build = new LinkedHashMap();
        build.put("0", "--请选择楼栋--");
        int i=1;
        if (buildEntities.size()>0){
            for (HouseBuildingEntity buildEntity:buildEntities){
                build.put(buildEntity.getId(),buildEntity.getName());
            }
        }
        return build;
    }

    @Override
    public LinkedHashMap listRoom() {
        List<HouseRoomEntity> buildEntities = houseRoomRepository.mapRoom("999");
        LinkedHashMap room = new LinkedHashMap();
        room.put("0","--请选择房间--");
        int i=1;
        if (room.size()>0){
            for (HouseRoomEntity roomEntity:buildEntities){
                room.put(i++,roomEntity.getName());
            }
        }
        return room;
    }

    @Override
    public Map<String, String> getBuildNum() {
        List<HouseInfoEntity> houseList = houseInfoRepository.getHouseInfoByBuildingNum(null);
        Map<String, String> buildNum = new LinkedHashMap<>();
        buildNum.put("0000","请选择楼栋");
        if(houseList != null && !houseList.isEmpty()){
            for(HouseInfoEntity house:houseList){
                buildNum.put(house.getBuildingNum(),house.getBuildingNum());
            }
        }

        return buildNum;
    }

    @Override
    public Map<String, String> getBuildFloor(String BuildingNum) {
        Map<String, String> buildFloor = new LinkedHashMap<>();
        if(BuildingNum==null || "".equals(BuildingNum)){
            return buildFloor;
        }
        List<HouseInfoEntity> houseList = houseInfoRepository.getHouseInfoByBuildingNum(BuildingNum);

        if(houseList != null && !houseList.isEmpty()){
            for(HouseInfoEntity house:houseList){
                buildFloor.put(house.getFloor(),house.getFloor());
            }
        }

        return buildFloor;
    }

    /**
     * 通过房屋id检索房屋信息_Wyd
     * @param roomId    房屋Id
     * @return  HouseInfoEntity
     */
    public HouseInfoEntity getHouseInfoByRoomId(String roomId){
        return houseInfoRepository.getHouseInfoByRoomId(roomId);
    }

    /**
     * 通过房产信息分配门禁_Wyd
     * @param projectCode   项目编码
     * @param area           地块
     * @param buildingNum   楼栋编码
     * @param unitCode      单元编码
     */
    public void assignDoorByHouse(String projectCode,String area,String buildingNum,String unitCode,UserInfoEntity userInfoEntity){
        if (null != projectCode && !"".equals(projectCode) && null != area && !"".equals(area) && null != buildingNum && !"".equals(buildingNum) && null != unitCode && !"".equals(unitCode)){
            //封装查询条件
            Map<String,Object> params = new HashMap<>();
            params.put("projectCode",projectCode);
            params.put("area",area);
            params.put("buildingNum",buildingNum);
            params.put("unitCode",unitCode);
            //执行查询,单元门
            List<PropertyDoorEntity> propertyDoorList = propertyDoorRepository.getPropertyDoorListByHouse(params);
            //分配单元门门禁
            if (!propertyDoorList.isEmpty() && propertyDoorList.size() > 0){
                PropertyUserDoorMapEntity userDoorMapEntity = null;
                for (int i = 0,length = propertyDoorList.size(); i<length; i++){
                    userDoorMapEntity = new PropertyUserDoorMapEntity();
                    userDoorMapEntity.setId(IdGen.uuid());
                    userDoorMapEntity.setUserId(userInfoEntity.getUserId());
                    userDoorMapEntity.setDoorId(propertyDoorList.get(i).getId());
                    userDoorMapEntity.setCreateBy(userInfoEntity.getNickName());
                    userDoorMapEntity.setCreateOn(new Date());
                    propertyDoorRepository.saveOrUpdate(userDoorMapEntity);
                }
            }
            //执行查询,大门
            params.clear();
            params.put("projectCode", projectCode);
            params.put("deviceType","2");
            List<PropertyDoorEntity> propertyDoorList_1 = propertyDoorRepository.getPropertyDoorListByHouse(params);
            //分配大门门禁
            if (!propertyDoorList_1.isEmpty() && propertyDoorList_1.size() > 0){
                PropertyUserDoorMapEntity userDoorMapEntity = null;
                for (int i = 0,length = propertyDoorList_1.size(); i<length; i++){
                    userDoorMapEntity = new PropertyUserDoorMapEntity();
                    userDoorMapEntity.setId(IdGen.uuid());
                    userDoorMapEntity.setUserId(userInfoEntity.getUserId());
                    userDoorMapEntity.setDoorId(propertyDoorList_1.get(i).getId());
                    userDoorMapEntity.setCreateBy(userInfoEntity.getNickName());
                    userDoorMapEntity.setCreateOn(new Date());
                    propertyDoorRepository.saveOrUpdate(userDoorMapEntity);
                }
            }
        }
    }

    /**
     * 通过房产信息取消门禁权限_Wyd
     * @param projectCode   项目编码
     * @param area           地块
     * @param buildingNum   楼栋编码
     * @param unitCode      单元编码
     */
    public void cancelDoorByHouse(String projectCode,String area,String buildingNum,String unitCode,UserInfoEntity userInfoEntity){
        if (null != projectCode && !"".equals(projectCode) && null != area && !"".equals(area) && null != buildingNum && !"".equals(buildingNum) && null != unitCode && !"".equals(unitCode)){
            //检索用户的门禁权限列表
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("userId", userInfoEntity.getUserId());
            List<PropertyUserDoorMapEntity> userDoorMapList = propertyDoorRepository.getUserDoorMapList(userMap);
            if (!userDoorMapList.isEmpty() && userDoorMapList.size() > 0){
                //封装查询条件
                Map<String,Object> params = new HashMap<>();
                params.put("projectCode",projectCode);
                params.put("area",area);
                params.put("buildingNum",buildingNum);
                params.put("unitCode",unitCode);
                //执行查询,单元门
                List<PropertyDoorEntity> propertyDoorList = propertyDoorRepository.getPropertyDoorListByHouse(params);
                //取消单元门门禁
                if (!propertyDoorList.isEmpty() && propertyDoorList.size() > 0){
                    PropertyDoorEntity propertyDoorEntity = null;
                    for (int i = 0,length = propertyDoorList.size(); i<length; i++){
                        propertyDoorEntity = propertyDoorList.get(i);
                        for (int j = 0,length1 = userDoorMapList.size(); j < length1; j++){
                            if (propertyDoorEntity.getId().equals(userDoorMapList.get(j).getDoorId())){
                                propertyDoorRepository.delete(userDoorMapList.get(j));
                            }
                        }
                    }
                }
                //执行查询,大门
                params.clear();
                params.put("projectCode", projectCode);
                params.put("deviceType","2");
                List<PropertyDoorEntity> propertyDoorList_1 = propertyDoorRepository.getPropertyDoorListByHouse(params);
                //取消大门门禁
                if (!propertyDoorList_1.isEmpty() && propertyDoorList_1.size() > 0){
                    PropertyDoorEntity propertyDoorEntity = null;
                    for (int i = 0,length = propertyDoorList_1.size(); i<length; i++){
                        propertyDoorEntity = propertyDoorList_1.get(i);
                        for (int j = 0,length1 = userDoorMapList.size(); j < length1; j++){
                            if (propertyDoorEntity.getId().equals(userDoorMapList.get(j).getDoorId())){
                                propertyDoorRepository.delete(userDoorMapList.get(j));
                            }
                        }
                    }
                }
            }
        }
    }

}
