package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.json.LG0001.LoginReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.application.inf.MemberAppealService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by zhanghj on 2016/1/26.
 */
@Service
public class UserOwnerServiceImpl implements UserOwnerService {

    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Autowired
    private HouseUserBookRepository houseUserBookRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private HouseProjectRepository houseProjectRepository;//初始化项目列表
    @Autowired
    private HouseFormatRepository houseFormatRepository;//初始化业态列表
    @Autowired
    private HouseBuildingRepository houseBuildingRepository;//初始化楼号列表
    @Autowired
    private HouseUnitRepository houseUnitRepository;//初始化单元列表
    @Autowired
    private HouseRoomRepository houseRoomRepository;//初始化屋子列表
    @Autowired
    private HouseCompanyRepository houseCompanyRepository;//公司
    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Autowired
    private UserSettingRepository userSettingRepository;
    /* 房屋信息 */
    @Autowired
    ViewAppHouseInfoRepository viewAppHouseInfoRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    DefaultConfigRepository defaultConfigRepository;
    @Autowired
    UserCRMRepository userCRMRepository;
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    UserTotalRepository userTotalRepository;
    @Autowired
    HouseInfoService houseInfoService;
    @Autowired
    MemberAppealService memberAppealService;

    @Autowired
    UserIntegralRuleRepository userIntegralRuleRepository;

    @Autowired
    IntegralRuleRepository integralRuleRepository;

    @Autowired
    UserIntegralRuleRecordRepository userIntegralRuleRecordRepository;

    @Autowired
    HouseUserCRMRepository houseUserCRMRepository;

    /**
     * 获取业主列表
     * @param userOwnerDTO
     * @return
     */

    public List<UserOwnerDTO> listOwnerDto_0(UserOwnerDTO userOwnerDTO,WebPage webPage,String userType) {
        //初始化业主列表
        List<UserOwnerDTO> userOwnerDTOs = new ArrayList<>();
        //根据房屋条件筛选
        HouseInfoEntity houseInfoEntity = new HouseInfoEntity();
        houseInfoEntity.setProjectId(userOwnerDTO.getProjectIdDto());      //项目
        houseInfoEntity.setBuildingId(userOwnerDTO.getBuildingId());        //楼号
        //houseInfoEntity.setUnitId(userOwnerDTO.getUnitId());        //单元
        houseInfoEntity.setRoomId(userOwnerDTO.getRoomId());        //房间
        //根据项目Id查询房子列表
        List<HouseInfoEntity> houseInfoEntities =houseInfoRepository.listHouseInfo(houseInfoEntity, webPage);

        if (houseInfoEntities.size()>0) {
            for (HouseInfoEntity house:houseInfoEntities) {
                //根据房子信息查找房屋住户关系
                List<HouseUserBookEntity> houseUserBookEntitys = houseUserBookRepository.getListByHouseId(house.getId());
                    if (houseUserBookEntitys.size()>0){
                        for (HouseUserBookEntity houseUserBookEntity:houseUserBookEntitys){
                            //获取业主的关系
                            if (houseUserBookEntity.getUserType().equals(userType)){
                                //查找用户
                                UserInfoEntity userInfoEntity = new UserInfoEntity();
                                userInfoEntity.setUserId(houseUserBookEntity.getUserId());//用户Id
                                userInfoEntity.setUserName(userOwnerDTO.getUserNameDto());//用户名
                                userInfoEntity.setRealName(userOwnerDTO.getRealNameDto());//真实姓名
                                userInfoEntity.setMobile(userOwnerDTO.getMobileDto());//联系方式
                                userInfoEntity.setCreateBy(userOwnerDTO.getBeginTime());//注册开始时间
                                userInfoEntity.setModifyBy(userOwnerDTO.getEndTime());//注册结束时间
                               List<UserInfoEntity> users = userInfoRepository.getByUserInfoEntity(userInfoEntity);
                                if (users.size()>0) {
                                    for (UserInfoEntity user:users){
                                        if (user != null) {
                                            UserOwnerDTO ownerDTO = new UserOwnerDTO();
                                            ownerDTO.setUserIdDto(userInfoEntity.getUserId());//用户Id
                                            ownerDTO.setUserNameDto(user.getUserName());//用户名
                                            ownerDTO.setMobileDto(user.getMobile());//手机
                                            ownerDTO.setRealNameDto(user.getRealName());//真实姓名
                                            ownerDTO.setRegisterDate(DateUtils.format(user.getRegisterDate()));//注册时间
                                            ownerDTO.setHouseInfoid(house.getId());//房屋信息Id
                                            ownerDTO.setProjectIdDto(house.getProjectId());//项目id
                                            ownerDTO.setProjectName(house.getProjectName());//项目名称
                                            ownerDTO.setBuildingId(house.getBuildingId());//楼Id
                                            //ownerDTO.setBuildingName(house.getBuildingName());//楼名称
                                            //ownerDTO.setUserIdDto(house.getUnitId());//单元Id
                                            //ownerDTO.setUnitName(house.getUnitName());//单元号
                                            ownerDTO.setRoomId(house.getRoomId());//房间id
                                            ownerDTO.setRoomName(house.getRoomName());//房间号
                                            ownerDTO.setHouseInfoid(house.getId());//房屋信息Id
                                            ownerDTO.setHouseUserid(houseUserBookEntity.getId());//房屋用户关系Id
                                            ownerDTO.setUserType(houseUserBookEntity.getUserType());//用户类型
                                            ownerDTO.setIspay(houseUserBookEntity.getIsPay());//是否有缴费授权
                                            if (userType.equals(UserInfoEntity.USER_TYPE_FAMILY)||userType.equals(UserInfoEntity.USER_TYPE_TENANT)){
                                                List<HouseUserBookEntity> owners = houseUserBookRepository.listUser(houseUserBookEntity.getHouseId(),UserInfoEntity.USER_TYPE_OWNER);
                                                String ownerName=" ";
                                                String ownerMobile=" ";
                                                if (owners.size()>0){
                                                    for (HouseUserBookEntity owner:owners){
                                                        UserInfoEntity userInfoEntity1 = userInfoRepository.get(owner.getUserId());
                                                        if (userInfoEntity1!=null){
                                                            ownerName=ownerName+userInfoEntity1.getRealName()+" ";
                                                            ownerMobile=ownerMobile+userInfoEntity1.getMobile()+" ";
                                                        }
                                                    }
                                                }
                                                if (ownerName.equals("")){
                                                    break;
                                                }
                                                else {
                                                    ownerDTO.setOwnerName(ownerName);
                                                    ownerDTO.setOwnerMobile(ownerMobile);
                                                }

                                            }
                                            userOwnerDTOs.add(ownerDTO);
                                        }
                                    }
                                }
                            }
                        }
                    }

            }
        }

        return userOwnerDTOs;
    }

    @Override
    public List<UserOwnerDTO> listOwnerDto(UserOwnerDTO userOwnerDTO, WebPage webPage, String userType) {

        List<UserOwnerDTO> userOwnerDTOs = new ArrayList<>();

        HouseInfoEntity houseInfoEntity = new HouseInfoEntity();
//        houseInfoEntity.setCompanyId(userOwnerDTO.getCompanyId());//公司Id
        houseInfoEntity.setProjectId(userOwnerDTO.getProjectIdDto());      //项目
        houseInfoEntity.setBuildingId(userOwnerDTO.getBuildingId());        //楼号
        //houseInfoEntity.setUnitId(userOwnerDTO.getUnitId());        //单元
        houseInfoEntity.setRoomId(userOwnerDTO.getRoomId());        //房间

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserName(userOwnerDTO.getUserNameDto());//用户名
        userInfoEntity.setRealName(userOwnerDTO.getRealNameDto());//真实姓名
        userInfoEntity.setMobile(userOwnerDTO.getMobileDto());//联系方式
        userInfoEntity.setCreateBy(userOwnerDTO.getBeginTime());//注册开始时间
        userInfoEntity.setModifyBy(userOwnerDTO.getEndTime());//注册结束时间

        HouseUserBookEntity houseUserBookEntity = new HouseUserBookEntity();
        houseUserBookEntity.setUserType(userType);

        List<Object> list = houseUserBookRepository.listUsers(houseInfoEntity, houseUserBookEntity, userInfoEntity, webPage);
        if (list.size()>0){
            for(int i=0;i<list.size();i++){
                Object[] objects = (Object[])list.get(i);
                HouseInfoEntity house = (HouseInfoEntity)objects[0];
                HouseUserBookEntity book = (HouseUserBookEntity)objects[1];
                UserInfoEntity user = (UserInfoEntity)objects[2];

                UserOwnerDTO ownerDTO = new UserOwnerDTO();
                ownerDTO.setUserIdDto(userInfoEntity.getUserId());//用户Id
                ownerDTO.setUserNameDto(user.getUserName());//用户名
                ownerDTO.setMobileDto(user.getMobile());//手机
                ownerDTO.setRealNameDto(user.getRealName());//真实姓名
                ownerDTO.setRegisterDate(DateUtils.format(user.getRegisterDate()));//注册时间
                ownerDTO.setHouseInfoid(house.getId());//房屋信息Id
                ownerDTO.setProjectIdDto(house.getProjectId());//项目id
                ownerDTO.setProjectName(house.getProjectName());//项目名称
                ownerDTO.setBuildingId(house.getBuildingId());//楼Id
                //ownerDTO.setBuildingName(house.getBuildingName());//楼名称
                //ownerDTO.setUserIdDto(house.getUnitId());//单元Id
                //ownerDTO.setUnitName(house.getUnitName());//单元号
                ownerDTO.setRoomId(house.getRoomId());//房间id
                ownerDTO.setRoomName(house.getRoomName());//房间号
                ownerDTO.setHouseInfoid(house.getId());//房屋信息Id
                ownerDTO.setHouseUserid(book.getId());//房屋用户关系Id
                ownerDTO.setUserType(book.getUserType());//用户类型
                ownerDTO.setIspay(book.getIsPay());//是否有缴费授权
                if (userType.equals(UserInfoEntity.USER_TYPE_FAMILY+HouseUserBookEntity.USER_TYPE_TENANT)){//查找出对应的业主信息
                    List<HouseUserBookEntity> owners = houseUserBookRepository.listUser(house.getId(),UserInfoEntity.USER_TYPE_OWNER);
                    String ownerName=" ";
                    String ownerMobile=" ";
                    if (owners.size()>0){
                        for (HouseUserBookEntity owner:owners){
                            UserInfoEntity userInfoEntity1 = userInfoRepository.get(owner.getUserId());
                            if (userInfoEntity1!=null){
                                ownerName=ownerName+userInfoEntity1.getRealName()+" ";
                                ownerMobile=ownerMobile+userInfoEntity1.getMobile()+" ";
                            }
                        }
                    }
                    if (!ownerName.equals(" ")){
                        ownerDTO.setOwnerName(ownerName);
                        ownerDTO.setOwnerMobile(ownerMobile);
                    }

                }
                userOwnerDTOs.add(ownerDTO);
            }
        }

        return userOwnerDTOs;
    }

    @Override
    public List<ProjectSelDTO> getProjectSel(String companyId) {
      List<ProjectSelDTO> projectSelDTOs = new ArrayList<>();
      //项目下拉列表
      List<HouseProjectEntity> houseProjectEntities = houseProjectRepository.getListOfNormal();
      ProjectSelDTO projects = new ProjectSelDTO();
      projects.setProjectId("0");
      projects.setProjectName("--请选择项目--");
      projectSelDTOs.add(projects);
        if (houseProjectEntities.size()>0){
            for(HouseProjectEntity project:houseProjectEntities){
                if(companyId.equals(project.getId())){
                    ProjectSelDTO projectSelDTO = new ProjectSelDTO();
                    projectSelDTO.setProjectId(project.getId());
                    projectSelDTO.setProjectName(project.getName());
                    projectSelDTOs.add(projectSelDTO);
                }
            }
        }
        return projectSelDTOs;
    }

    @Override
    public List<FormatSelDTO> getFormatSel(String projectId) {
        List<FormatSelDTO> formatSelDTOs = new ArrayList<>();
        List<HouseFormatEntity> houseFormatEntities = houseFormatRepository.getList(projectId);
        FormatSelDTO formatSelDTO = new FormatSelDTO();
        formatSelDTO.setFormatId("0");
        formatSelDTO.setFormatName("----------请选择业态----------");
        formatSelDTOs.add(formatSelDTO);
        if (houseFormatEntities!=null){
            for (HouseFormatEntity houseFormatEntity:houseFormatEntities){
                FormatSelDTO formatSelDTO1 = new FormatSelDTO();
                formatSelDTO1.setFormatId(houseFormatEntity.getId());
                formatSelDTO1.setFormatName(houseFormatEntity.getName());
                formatSelDTOs.add(formatSelDTO1);
            }
        }
        return formatSelDTOs;
    }

    /**
     * 获取楼栋下拉框
     * @param projectId
     * @return
     */
    @Override
    public List<BuildingSelDTO> getBuildSel(String projectId) {
        List<BuildingSelDTO> buildingSelDTOs = new ArrayList<>();
        List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getListByProjectId(projectId, "");
        BuildingSelDTO buildings = new BuildingSelDTO();
        buildings.setBuildingId("0");
        buildings.setBuildingName("--------请选择楼层--------");
        buildingSelDTOs.add(buildings);
        if (houseBuildingEntities.size()>0){
            for (HouseBuildingEntity building:houseBuildingEntities){
                BuildingSelDTO buildingSelDTO = new BuildingSelDTO();
                buildingSelDTO.setBuildingId(building.getId());
                buildingSelDTO.setBuildingName(building.getName());
                buildingSelDTOs.add(buildingSelDTO);
            }
        }
        return buildingSelDTOs;
    }

    @Override
    public List<BuildingSelDTO> getformatBuildSel(String projectId,String formatId) {
        List<BuildingSelDTO> buildingSelDTOs = new ArrayList<>();
        List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getListByProjectId(projectId, formatId);
        BuildingSelDTO buildings = new BuildingSelDTO();
        buildings.setBuildingId("0");
        buildings.setBuildingName("----------请选择楼层----------");
        buildingSelDTOs.add(buildings);
        if (houseBuildingEntities.size()>0){
            for (HouseBuildingEntity building:houseBuildingEntities){
                BuildingSelDTO buildingSelDTO = new BuildingSelDTO();
                buildingSelDTO.setBuildingId(building.getId());
                buildingSelDTO.setBuildingName(building.getName());
                buildingSelDTOs.add(buildingSelDTO);
            }
        }
        return buildingSelDTOs;
    }

    /**
     * 获取单元下拉框
     * @param buildingId
     * @return
     */
    @Override
    public List<UnitSelDTO> getUnitSel(String buildingId) {
        List<UnitSelDTO> unitSelDTOs = new ArrayList<>();
        List<HouseUnitEntity> unitEntities = houseUnitRepository.getListByBuildingId(buildingId);
        UnitSelDTO units = new UnitSelDTO();
        units.setUnitId("0");
        units.setUnitName("----------请选择单元----------");
        unitSelDTOs.add(units);
        if (unitEntities.size()>0){
            for(HouseUnitEntity unit:unitEntities){
                UnitSelDTO unitSelDTO = new UnitSelDTO();
                unitSelDTO.setUnitId(unit.getId());
                unitSelDTO.setUnitName(unit.getName());
                unitSelDTOs.add(unitSelDTO);
            }
        }
        return unitSelDTOs;
    }

    /**
     * 获取房间下拉框
     * @param unitId
     * @return
     */
    @Override
    public List<RoomDTO> getRoomSel(String unitId) {
        List<RoomDTO> roomDTOs = new ArrayList<>();
        List<HouseRoomEntity> houseRoomEntities = houseRoomRepository.getListByUnitId(unitId);
        RoomDTO rooms = new RoomDTO();
        rooms.setRoomId("0");
        rooms.setRoomName("----------请选择房间----------");
        roomDTOs.add(rooms);
        if (houseRoomEntities.size()>0){
            for(HouseRoomEntity room:houseRoomEntities){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setRoomId(room.getId());
                roomDTO.setRoomName(room.getName());
                roomDTOs.add(roomDTO);
            }
        }

        return roomDTOs;
    }

    /**
     * 添加业主信息
     * @param userOwnerDTO
     * @return
     */
    @Override
    public boolean addOwner(UserOwnerDTO userOwnerDTO) {
        HouseInfoEntity houseInfoEntity = new HouseInfoEntity();
        String houseInfoId = IdGen.uuid();
        houseInfoEntity.setId(houseInfoId);
        houseInfoEntity.setCompanyId(userOwnerDTO.getCompanyId());
        if (userOwnerDTO.getCompanyId()!=null){
            HouseCompanyEntity company= houseCompanyRepository.getCompanyById(userOwnerDTO.getCompanyId());
            if (company!=null){
                houseInfoEntity.setCompanyName(company.getName());
            }
            else {
                return false;
            }
        }
        houseInfoEntity.setProjectId(userOwnerDTO.getProjectIdDto());
        if (userOwnerDTO.getProjectIdDto()!=null){
            HouseProjectEntity project = houseProjectRepository.get(userOwnerDTO.getProjectIdDto());
            if (project!=null){
                houseInfoEntity.setProjectName(project.getName());
            }
            else {
                return false;
            }
        }
        houseInfoEntity.setBuildingId(userOwnerDTO.getBuildingId());
        if (userOwnerDTO.getBuildingId()!=null){
            HouseBuildingEntity building = houseBuildingRepository.get(userOwnerDTO.getBuildingId());
            if (building!=null){
                //houseInfoEntity.setBuildingName(building.getName());
            }
            else {
                return false;
            }
        }
        //houseInfoEntity.setUnitId(userOwnerDTO.getUnitId());
        if (userOwnerDTO.getUnitId()!=null){
            HouseUnitEntity unit = houseUnitRepository.get(userOwnerDTO.getUnitId());
            if (unit!=null){
                //houseInfoEntity.setUnitName(unit.getName());
            }
        }
        houseInfoEntity.setRoomId(userOwnerDTO.getRoomId());
        if (userOwnerDTO.getRoomId()!=null){
            HouseRoomEntity room = houseRoomRepository.get(userOwnerDTO.getRoomId());
            if (room!=null){
                houseInfoEntity.setRoomName(room.getName());
            }
        }
        houseInfoEntity.setRoomName(userOwnerDTO.getRoomName());
        String address = "";
        if (userOwnerDTO.getProjectName()!=null&&!"".equals(userOwnerDTO.getProjectName())){
            address = address+userOwnerDTO.getProjectName();
        }
        if (userOwnerDTO.getBuildingName()!=null&&!"".equals(userOwnerDTO.getBuildingName())){
            address = address+userOwnerDTO.getBuildingName();
        }
        if (userOwnerDTO.getBuildingName()!=null&&!"".equals(userOwnerDTO.getBuildingName())){
            address = address+userOwnerDTO.getBuildingName();
        }
        if (userOwnerDTO.getRoomName()!=null&&!"".equals(userOwnerDTO.getRoomName())){
            address = address+userOwnerDTO.getRoomName();
        }
        houseInfoEntity.setAddress(address);
        houseInfoEntity.setCreateBy(userOwnerDTO.getCreateBy());
        houseInfoEntity.setCreateOn(DateUtils.getDate());
        houseInfoEntity.setModifyBy(userOwnerDTO.getCreateBy());
        houseInfoEntity.setModifyOn(DateUtils.getDate());
        WebPage webPage = new WebPage();
        List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.listHouseInfo(houseInfoEntity, webPage);
        if (houseInfoEntities.size()>0){
            return false;//房产信息已存在
        }
        houseInfoRepository.create(houseInfoEntity);//添加房产信息

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        String userId = IdGen.getNewUserID();
        userInfoEntity.setUserId(userId);
        UserInfoEntity user = userInfoRepository.getByMobile(userOwnerDTO.getMobileDto());
        if (user!=null){
            return false;//手机号重复
        }
        userInfoEntity.setUserName(userOwnerDTO.getMobileDto());//用户名就是手机号
        userInfoEntity.setPassword(userOwnerDTO.getPassword());
        userInfoEntity.setRealName(userOwnerDTO.getRealNameDto());
        userInfoEntity.setUserState(UserInfoEntity.USER_STATE_NORMAL);//用户状态，一般
        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);//业主
        userInfoEntity.setRegisterDate(DateUtils.getDate());
        userInfoEntity.setCreateOn(DateUtils.getDate());
        userInfoEntity.setCreateBy(userOwnerDTO.getCreateBy());
        userInfoEntity.setModifyOn(DateUtils.getDate());
        userInfoEntity.setModifyBy(userOwnerDTO.getCreateBy());
        userInfoEntity.setMobile(userOwnerDTO.getMobileDto());
        userInfoEntity.setNickName("");
        userInfoEntity.setSex(1);
        userInfoRepository.create(userInfoEntity);

        HouseUserBookEntity userBookEntity = new HouseUserBookEntity();
        userBookEntity.setId(IdGen.uuid());
        userBookEntity.setUserId(userId);
        userBookEntity.setHouseId(houseInfoId);
        userBookEntity.setUserType(HouseUserBookEntity.USER_TYPE_OWNER);//业主
        userBookEntity.setState(HouseUserBookEntity.STATE_NORMAL);//正常
        userBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_YES);//可缴费
        userBookEntity.setCreateBy(userOwnerDTO.getCreateBy());
        userBookEntity.setCreateOn(DateUtils.getDate());
        userBookEntity.setModifyBy(userOwnerDTO.getCreateBy());
        userBookEntity.setModifyOn(DateUtils.getDate());
        houseUserBookRepository.create(userBookEntity);
        return true;
    }

    /**
     * 家属租户关系解除
     * @param houseUserId
     * @return
     */
    @Override
    public String  delFamTen(String houseUserId) {
        HouseUserBookEntity houseUserBookEntity = houseUserBookRepository.get(houseUserId);
        if (houseUserBookEntity!=null){
            houseUserBookEntity.setState(HouseUserBookEntity.STATE_REMOVE);
             houseUserBookRepository.update(houseUserBookEntity);
//            String type = houseUserBookRepository.getUserType(houseUserBookEntity.getUserId(),houseUserBookEntity.getHouseId());
//            UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserBookEntity.getUserId());
//            userInfoEntity.setUserType(type);
//            userInfoRepository.update(userInfoEntity);
            return houseUserBookEntity.getUserId();
        }
        else {
            return "";
        }
    }

    /**
     * 添加家属租户信息
     * @param userTenantDTO
     * @return
     */
    @Override
    public UserTenantDTO addFamTen(UserTenantDTO userTenantDTO,UserPropertyStaffEntity userPropertyStaffEntity) {
        String totalHouseId = "";
        String totalUserId = "";
        UserTenantDTO userFam = new UserTenantDTO();
        HouseInfoEntity houseInfoEntity = new HouseInfoEntity();
        houseInfoEntity.setProjectId(userPropertyStaffEntity.getProjectId());
        houseInfoEntity.setFormatId(userTenantDTO.getDtoFormatId());
        houseInfoEntity.setBuildingId(userTenantDTO.getDtoBuildingId());
        //houseInfoEntity.setUnitId(userTenantDTO.getDtoUnitId());
        houseInfoEntity.setRoomId(userTenantDTO.getDtoRoomId());

        HouseProjectEntity houseProjectEntity = houseProjectRepository.get(userPropertyStaffEntity.getQueryScope());
        if (houseProjectEntity==null){
//            userFam"项目id有误。";userFam
            userFam.setTnResult("项目id有误。");
        }
        HouseInfoEntity houseInfoEntity1 = houseInfoRepository.getHouseInfo(houseInfoEntity);
        if (houseInfoEntity1==null){

            HouseFormatEntity houseFormatEntity = houseFormatRepository.get(userTenantDTO.getDtoFormatId());
            if (houseFormatEntity==null){
                userFam.setTnResult("业态id有误。");
            }
            HouseBuildingEntity houseBuildingEntity = houseBuildingRepository.get(userTenantDTO.getDtoBuildingId());
            if (houseBuildingEntity==null){
                userFam.setTnResult("楼号id有误。");
            }
            HouseUnitEntity houseUnitEntity = houseUnitRepository.get(userTenantDTO.getDtoUnitId());
            if (houseUnitEntity==null){
                userFam.setTnResult("单元id有误。");
            }
            HouseRoomEntity houseRoomEntity = houseRoomRepository.get(userTenantDTO.getDtoRoomId());
            if (houseRoomEntity==null){
                userFam.setTnResult("房间id有误。");
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
                userFam.setTnResult("该地址在物管系统未登记！");
                return userFam;
            }

            HouseInfoEntity newHouseInfo = new HouseInfoEntity();
            String houseId  = IdGen.uuid();
            totalHouseId = houseId;
            newHouseInfo.setId(houseId);
            newHouseInfo.setCompanyId(userPropertyStaffEntity.getCompanyId());
//            newHouseInfo.setCompanyName();
            newHouseInfo.setProjectId(houseProjectEntity.getId());
            newHouseInfo.setProjectName(houseProjectEntity.getName());
            newHouseInfo.setBuildingId(houseBuildingEntity.getId());
            //newHouseInfo.setBuildingName(houseBuildingEntity.getName());
            //newHouseInfo.setUnitId(houseUnitEntity.getId());
            //newHouseInfo.setUnitName(houseUnitEntity.getName());
            newHouseInfo.setRoomId(houseRoomEntity.getId());
            newHouseInfo.setRoomName(houseRoomEntity.getName());
            newHouseInfo.setFormatId(houseFormatEntity.getId());
            newHouseInfo.setFormatName(houseFormatEntity.getName());
            newHouseInfo.setAddress(viewAppHouseInfoEntity.getAddress());
            newHouseInfo.setCreateBy(userPropertyStaffEntity.getStaffName());
            newHouseInfo.setHouseType(viewAppHouseInfoEntity.getHouseType());
            newHouseInfo.setCreateOn(DateUtils.getDate());
            newHouseInfo.setModifyBy(userPropertyStaffEntity.getStaffName());
            newHouseInfo.setModifyOn(DateUtils.getDate());
            newHouseInfo.setViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
            newHouseInfo.setCompanyName(viewAppHouseInfoEntity.getCompanyName());
            newHouseInfo.setCompanyId(viewAppHouseInfoEntity.getCompanyId() + "");
            newHouseInfo.setBuildingArea(viewAppHouseInfoEntity.getBillingArea());
            houseInfoRepository.create(newHouseInfo);
        }else {
            totalHouseId = houseInfoEntity1.getId();
        }

            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(userTenantDTO.getDtoMobile());
            if (userInfoEntity!=null){
                if(userInfoEntity.getUserState().equals(UserInfoEntity.USER_STATE_NORMAL)
                        && !userInfoEntity.isVisitor()){
                    if(userInfoEntity.getRealName().equals(userTenantDTO.getDtoRealName())) {
                        totalUserId = userInfoEntity.getUserId();
                    }
                    else {
                        userFam.setTnResult("该手机已经以"+userInfoEntity.getRealName()+"的名字注册过。");
                        return userFam;
                    }
                }
                else {
                    totalUserId=userInfoEntity.getUserId();
                    userInfoEntity.setUserName(userTenantDTO.getDtoMobile());
                    userInfoEntity.setPassword(EncryptUtils.encryptPassword(userTenantDTO.getDtoMobile().substring(userTenantDTO.getDtoMobile().length()-6,userTenantDTO.getDtoMobile().length())));
                    userInfoEntity.setMobile(userTenantDTO.getDtoMobile());
                    userInfoEntity.setNickName(userTenantDTO.getDtoRealName());
                    userInfoEntity.setSex(1);
                    userInfoEntity.setRealName(userTenantDTO.getDtoRealName());
                    userInfoEntity.setLogo(systemConfigRepository.get("UserDefaultLogo").getConfigValue());
                    userInfoEntity.setUserState(UserInfoEntity.USER_STATE_NORMAL);
                    userInfoEntity.setUserType(userTenantDTO.getTenantOrFamily());
                    userInfoEntity.setRegisterDate(DateUtils.getDate());
                    userInfoEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
                    userInfoEntity.setCreateOn(userPropertyStaffEntity.getCreateOn());
                    userInfoEntity.setModifyOn(DateUtils.getDate());
                    userInfoEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
                    userInfoRepository.update(userInfoEntity);
                }
            }
            else {
                UserInfoEntity newUserInfo = new UserInfoEntity();
                String userId = IdGen.getNewUserID();
                newUserInfo.setUserId(userId);
                totalUserId=userId;
                newUserInfo.setUserName(userTenantDTO.getDtoMobile());
                newUserInfo.setPassword(EncryptUtils.encryptPassword(userTenantDTO.getDtoMobile().substring(userTenantDTO.getDtoMobile().length()-6,userTenantDTO.getDtoMobile().length())));
                newUserInfo.setMobile(userTenantDTO.getDtoMobile());
                newUserInfo.setNickName(userTenantDTO.getDtoRealName());
                newUserInfo.setSex(1);
                newUserInfo.setRealName(userTenantDTO.getDtoRealName());
                newUserInfo.setLogo(systemConfigRepository.get("UserDefaultLogo").getConfigValue());
                newUserInfo.setUserState(UserInfoEntity.USER_STATE_NORMAL);
                newUserInfo.setUserType(userTenantDTO.getTenantOrFamily());
                newUserInfo.setRegisterDate(DateUtils.getDate());
                newUserInfo.setCreateBy(userPropertyStaffEntity.getStaffName());
                newUserInfo.setCreateOn(userPropertyStaffEntity.getCreateOn());
                newUserInfo.setModifyOn(DateUtils.getDate());
                newUserInfo.setModifyBy(userPropertyStaffEntity.getStaffName());
                userInfoRepository.create(newUserInfo);
            }


        HouseUserBookEntity houseUserBookEntity = houseUserBookRepository.getByUserIdAndHouseId(totalUserId,totalHouseId);
        if (houseUserBookEntity != null) {
            if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_OWNER)) {
                userFam.setTnResult("此人已经是房产业主。");
                return userFam;
            }
            if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_FAMILY)) {
                userFam.setTnResult("此人已经是房产家属。");
                return userFam;
            }
            if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_TENANT)) {
                userFam.setTnResult("此人已经是房产租户。");
                return userFam;
            }
        }else {
            HouseUserBookEntity houseUserBookEntity1 = new HouseUserBookEntity();
            houseUserBookEntity1.setId(IdGen.uuid());
            houseUserBookEntity1.setUserId(totalUserId);
            houseUserBookEntity1.setModifyOn(DateUtils.getDate());
            houseUserBookEntity1.setUserType(userTenantDTO.getTenantOrFamily());//之后可能会改成userTenantDTO.getUserType
            houseUserBookEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());
            houseUserBookEntity1.setHouseId(totalHouseId);
            houseUserBookEntity1.setState(HouseUserBookEntity.STATE_NORMAL);
            houseUserBookEntity1.setIsPay(userTenantDTO.getDtoPccredit());
            houseUserBookEntity1.setCreateOn(DateUtils.getDate());
            houseUserBookEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());
            houseUserBookRepository.create(houseUserBookEntity1);
        }
        UserSettingEntity userSettingEntity = userSettingRepository.get(totalUserId);
        if (userSettingEntity==null){
            UserSettingEntity userSetting = new UserSettingEntity();
            userSetting.setUserId(totalUserId);
            userSetting.setProjectId(houseProjectEntity.getId());
            userSetting.setProjectName(houseProjectEntity.getName());
            userSetting.setCreateBy(userPropertyStaffEntity.getStaffName());
            userSetting.setCreateOn(DateUtils.getDate());
            userSetting.setModifyBy(userPropertyStaffEntity.getStaffName());
            userSetting.setModifyOn(DateUtils.getDate());
            userSettingRepository.create(userSetting);
        }else {
            userSettingEntity.setProjectId(houseProjectEntity.getId());
            userSettingEntity.setProjectName(houseProjectEntity.getName());
            userSettingEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
            userSettingEntity.setCreateOn(DateUtils.getDate());
            userSettingEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
            userSettingEntity.setModifyOn(DateUtils.getDate());
            userSettingRepository.update(userSettingEntity);
        }


        userFam.setTnResult("添加成功。");
        userFam.setTnHouseId(totalHouseId);
        userFam.setTnUserId(totalUserId);
        return userFam;
    }

    @Override
    public boolean updateType(UserTenantDTO userTenantDTO) {
        if(userTenantDTO.getTnUserId()==null||userTenantDTO.getTnUserId().equals("")){
            return false;
        }
        String type = houseUserBookRepository.maxUserType(userTenantDTO.getTnUserId());
        UserInfoEntity totalUser = userInfoRepository.get(userTenantDTO.getTnUserId());
        totalUser.setUserType(type);
        userInfoRepository.update(totalUser);
        return true;
    }

    /**
     * 更改家属租户缴费授权
     * @param houseuserId
     * @return
     */
    @Override
    public String updatePayType(String houseuserId) {
        HouseUserBookEntity houseUserBookEntity = houseUserBookRepository.get(houseuserId);
        if (houseUserBookEntity==null){
            return "信息已经不存在。";
        }
        String isPay = houseUserBookEntity.getIsPay();
        if (isPay.equals(HouseUserBookEntity.IS_PAY_YES)){
            houseUserBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_NO);
            houseUserBookRepository.update(houseUserBookEntity);
        }
        if (isPay.equals(HouseUserBookEntity.IS_PAY_NO)){
            houseUserBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_YES);
            houseUserBookRepository.update(houseUserBookEntity);
        }
        return houseUserBookEntity.getIsPay();
    }

    /**
     * 获取用户列表
     * param:userDTO
     * param:webPage
     * return
     */
    @Override
    public List<LoginReturnJsonDTO> getUserList(UserPropertyStaffEntity user, LoginReturnJsonDTO userDTO, WebPage webPage) {
        //检索条件
        UserInfoEntity userSearch=new UserInfoEntity();
        //项目(id为替用字段)
//        userSearch.setId(userDTO.getProjectName());
        //追加区域项目检索条件-Wyd20170401
        //如果检索项目不为Null,直接set user的id
        //如果检索项目为Null,城市不为Null,set user的id为该城市下所有项目Code,逗号间隔
        if (null != userDTO.getProjectId() && !"0".equals(userDTO.getProjectId()) && !"".equals(userDTO.getProjectId())){
            userSearch.setId("'" + userDTO.getProjectId()+"',");
        }else if (null != userDTO.getScopeId() && !"0".equals(userDTO.getScopeId()) && !"".equals(userDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(userDTO.getScopeId());
            String projectIds = "";
            for (Object[] project : projectList) {
                projectIds += "'" + project[0].toString() + "',";
            }
            userSearch.setId(projectIds);
        }
        userSearch.setUserName(userDTO.getUserName()); //姓名
        userSearch.setNickName(userDTO.getNickName());//昵称
        userSearch.setMobile(userDTO.getMobile());//手机号
        userSearch.setIdCard(userDTO.getIdCard());//身份证号
        userSearch.setCreateBy(userDTO.getBeginTime());//开始时间(替用字段)
        userSearch.setModifyBy(userDTO.getEndTime());//结束时间(替用字段)
        userSearch.setUserType(userDTO.getUserType());//用户类型

        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = userDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }

        //获取用户信息
        List<Object[]> userList;
        //页面内容：封装到dto里
        List<LoginReturnJsonDTO> userDTOs=new ArrayList<LoginReturnJsonDTO>();
        if(userSearch.getUserType().equals("2")) {//普通用户
            userList = userInfoRepository.getCommonUsers(userSearch, webPage);
            if(userList.size()>0) {
                for (Object[] users : userList) {
                    LoginReturnJsonDTO usersDTO = new LoginReturnJsonDTO();
                    usersDTO.setUserName((String) users[0]);//姓名
                    usersDTO.setNickName((String) users[1]);//昵称
                    usersDTO.setMobile((String) users[2]);//手机号
                    usersDTO.setIdCard((String) users[3]);//身份证号
                    usersDTO.setBeginTime(DateUtils.format((Date) users[4], "yyyy-MM-dd"));//注册时间
                    usersDTO.setWc_nickName((String) users[5]);//微信昵称
                    userDTOs.add(usersDTO);
                }
            }
        }else if(userSearch.getUserType().equals("3")){//业主
            userList = userInfoRepository.getOwnerUsers(userSearch, webPage,roleScopes);
            if(userList.size()>0) {
                for (Object[] users : userList) {
                    LoginReturnJsonDTO usersDTO = new LoginReturnJsonDTO();
                    usersDTO.setRealName((String) users[0]);//姓名
                    usersDTO.setNickName((String) users[1]);//昵称
                    usersDTO.setMobile((String) users[2]);//手机号
                    usersDTO.setIdCard((String) users[3]);//身份证号
                    usersDTO.setBeginTime(DateUtils.format((Date) users[4], "yyyy-MM-dd"));//注册时间
                    usersDTO.setProjectName((String) users[5]);//项目名称
                    usersDTO.setAddress((String) users[6]);//地址
                    usersDTO.setWc_nickName((String) users[7]);//微信昵称
                    userDTOs.add(usersDTO);
                }
            }
        }else if(userSearch.getUserType().equals("4")){//同住人
            userList = userInfoRepository.getHousemateUsers(userSearch, webPage,roleScopes);
            if(userList.size()>0) {
                for (Object[] users : userList) {
                    LoginReturnJsonDTO usersDTO = new LoginReturnJsonDTO();
                    usersDTO.setUserName((String) users[0]);//姓名
                    usersDTO.setMobile((String) users[1]);//手机号
                    usersDTO.setProjectName((String) users[2]);//项目名称
                    usersDTO.setAddress((String) users[3]);//地址
                    usersDTO.setIdCard((String) users[4]);//身份证号
                    usersDTO.setBeginTime(DateUtils.format((Date) users[5], "yyyy-MM-dd"));//授权时间
                    usersDTO.setWc_nickName((String) users[7]);//微信昵称
                    usersDTO.setRealName((String) users[8]);//真实姓名
                    UserInfoEntity userInfoEntity=userInfoRepository.get((String) users[6]);

                    if(userInfoEntity!=null) {
                        usersDTO.setNickName(userInfoEntity.getNickName());//业主昵称
                        usersDTO.setYz_wc_nickName(userInfoEntity.getWC_nickName());//业主微信昵称
                    }
                    userDTOs.add(usersDTO);
                }
            }
        }
        return userDTOs;
    }

    /**
     * 优化会员管理导出Excel
     */
    public void exportExcel2(HttpServletResponse response,HttpServletRequest request,String type,UserPropertyStaffEntity user,LoginReturnJsonDTO userLoginDTO) throws Exception {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(4000);
        ServletOutputStream out = response.getOutputStream();
        if("1".equals(type)) {
            //普通用户管理
            String fileName = "普通用户管理列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "普通用户列表";
            String[] headers = {"序号", "昵称", "手机号", "身份证", "注册时间"};

            userLoginDTO.setUserType("2");
            List<LoginReturnJsonDTO> userDTOs = getUserList(user, userLoginDTO, webPage);
            List<ExportExcelCommonUserDTO> exportExcelCommonUserDTOs = new ArrayList<>();
            if (null != userDTOs){
                int num = 1;
                ExportExcelCommonUserDTO exportExcelCommonUserDTO = null;
                LoginReturnJsonDTO loginReturnJsonDTO = null;
                for (int i = 0,length = userDTOs.size();i<length;i++){
                    loginReturnJsonDTO = userDTOs.get(i);
                    exportExcelCommonUserDTO = new ExportExcelCommonUserDTO();
                    //序号
                    exportExcelCommonUserDTO.setNum(num++);
                    //昵称
                    exportExcelCommonUserDTO.setNickName(loginReturnJsonDTO.getNickName() == null ? "" : loginReturnJsonDTO.getNickName());
                    //手机
                    exportExcelCommonUserDTO.setMobile(loginReturnJsonDTO.getMobile() == null ? "" : loginReturnJsonDTO.getMobile());
                    //证件号码
                    exportExcelCommonUserDTO.setIdCard(loginReturnJsonDTO.getIdCard() == null ? "" : loginReturnJsonDTO.getIdCard());
                    //注册开始时间
                    exportExcelCommonUserDTO.setBeginTime(loginReturnJsonDTO.getBeginTime() == null ? "" : loginReturnJsonDTO.getBeginTime());
                    exportExcelCommonUserDTOs.add(exportExcelCommonUserDTO);
                }
                ExportExcel<ExportExcelCommonUserDTO> ex = new ExportExcel<ExportExcelCommonUserDTO>();
                ex.exportExcel(title, headers, exportExcelCommonUserDTOs, out, "yyyy-MM-dd");
                System.out.println("excel导出成功！");
            }
        }else if("2".equals(type)){
            //业主管理
            String fileName = "业主管理列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "业主列表";
            String[] headers = {"序号", "姓名", "微信昵称","手机号", "项目", "地址", "身份证","注册时间"};

            userLoginDTO.setUserType("3");
            List<LoginReturnJsonDTO> userDTOs = getUserList(user, userLoginDTO, webPage);
            List<ExportExcelOwnerUserDTO> exportExcelOwnerUserDTOs = new ArrayList<>();
            if (null != userDTOs){
                int num = 1;
                ExportExcelOwnerUserDTO exportExcelOwnerUserDTO = null;
                LoginReturnJsonDTO loginReturnJsonDTO = null;
                for (int i = 0,length = userDTOs.size();i<length;i++){
                    loginReturnJsonDTO = userDTOs.get(i);
                    exportExcelOwnerUserDTO = new ExportExcelOwnerUserDTO();
                    //序号
                    exportExcelOwnerUserDTO.setNum(num++);
                    //真实姓名
                    exportExcelOwnerUserDTO.setRealName(loginReturnJsonDTO.getRealName() == null ? "" : loginReturnJsonDTO.getRealName());
                    //微信昵称
                    exportExcelOwnerUserDTO.setNickName(loginReturnJsonDTO.getWc_nickName() == null ? "" : loginReturnJsonDTO.getWc_nickName());
                    //手机
                    exportExcelOwnerUserDTO.setMobile(loginReturnJsonDTO.getMobile() == null ? "" : loginReturnJsonDTO.getMobile());
                    //项目名称
                    exportExcelOwnerUserDTO.setProjectName(loginReturnJsonDTO.getProjectName() == null ? "" : loginReturnJsonDTO.getProjectName());
                    //地址
                    exportExcelOwnerUserDTO.setAddress(loginReturnJsonDTO.getAddress() == null ? "" : loginReturnJsonDTO.getAddress());
                    //证件号码
                    exportExcelOwnerUserDTO.setIdCard(loginReturnJsonDTO.getIdCard() == null ? "" : loginReturnJsonDTO.getIdCard());
                    //注册开始时间
                    exportExcelOwnerUserDTO.setBeginTime(loginReturnJsonDTO.getBeginTime() == null ? "" : loginReturnJsonDTO.getBeginTime());
                    exportExcelOwnerUserDTOs.add(exportExcelOwnerUserDTO);
                }
                ExportExcel<ExportExcelOwnerUserDTO> ex = new ExportExcel<ExportExcelOwnerUserDTO>();
                ex.exportExcel(title, headers, exportExcelOwnerUserDTOs, out, "yyyy-MM-dd");
                System.out.println("excel导出成功！");
            }
        }else if("3".equals(type)){
            //同住人管理
            String fileName = "同住人管理列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "同住人列表";
            String[] headers = {"序号", "姓名", "手机号", "项目", "地址", "身份证","业主昵称","授权时间"};

            userLoginDTO.setUserType("4");
            List<LoginReturnJsonDTO> userDTOs = getUserList(user, userLoginDTO, webPage);
            List<ExportExcelResidentDTO> exportExcelResidentDTOs = new ArrayList<>();
            if (null != userDTOs){
                int num = 1;
                ExportExcelResidentDTO exportExcelResidentDTO = null;
                LoginReturnJsonDTO loginReturnJsonDTO = null;
                for (int i = 0,length = userDTOs.size();i<length;i++){
                    loginReturnJsonDTO = userDTOs.get(i);
                    exportExcelResidentDTO = new ExportExcelResidentDTO();
                    //序号
                    exportExcelResidentDTO.setNum(num++);
                    //用户名
                    exportExcelResidentDTO.setUserName(loginReturnJsonDTO.getUserName() == null ? "" : loginReturnJsonDTO.getUserName());
                    //手机
                    exportExcelResidentDTO.setMobile(loginReturnJsonDTO.getMobile() == null ? "" : loginReturnJsonDTO.getMobile());
                    //项目名称
                    exportExcelResidentDTO.setProjectName(loginReturnJsonDTO.getProjectName() == null ? "" : loginReturnJsonDTO.getProjectName());
                    //地址
                    exportExcelResidentDTO.setAddress(loginReturnJsonDTO.getAddress() == null ? "" : loginReturnJsonDTO.getAddress());
                    //证件号码
                    exportExcelResidentDTO.setIdCard(loginReturnJsonDTO.getIdCard() == null ? "" : loginReturnJsonDTO.getIdCard());
                    //昵称
                    exportExcelResidentDTO.setNickName(loginReturnJsonDTO.getNickName() == null ? "" : loginReturnJsonDTO.getNickName());
                    //注册开始时间
                    exportExcelResidentDTO.setBeginTime(loginReturnJsonDTO.getBeginTime() == null ? "" : loginReturnJsonDTO.getBeginTime());
                    exportExcelResidentDTOs.add(exportExcelResidentDTO);
                }
                ExportExcel<ExportExcelResidentDTO> ex = new ExportExcel<ExportExcelResidentDTO>();
                ex.exportExcel(title, headers, exportExcelResidentDTOs, out, "yyyy-MM-dd");
                System.out.println("excel导出成功！");
            }
        }
    }

    /**
     * 导出excel
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user,LoginReturnJsonDTO userLoginDTO, HttpServletResponse httpServletResponse,
                              String type,HttpServletRequest httpServletRequest) throws Exception {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        if("1".equals(type)) {
            userLoginDTO.setUserType("2");
            List<LoginReturnJsonDTO> usersDTO = getUserList(user, userLoginDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("普通用户列表");

            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();

            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);

            String[] titles = {"序号", "昵称", "手机号", "身份证", "注册时间"};
            XSSFRow headRow = sheet.createRow(0);


            if (usersDTO.size() > 0) {

                usersDTO.forEach(userDTO -> {

                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (usersDTO.size() > 0) {
                        for (int i = 0; i < usersDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            LoginReturnJsonDTO commonUser = usersDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(commonUser.getNickName());//昵称

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(commonUser.getMobile());//手机号

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(commonUser.getIdCard());//身份证

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(commonUser.getBeginTime());//注册时间
                        }
                    }
                });
            }
            try {
                //String fileName = new String(("普通用户管理").getBytes(), "ISO8859_1");
                String fileName = new String(("普通用户管理").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("普通用户管理", "UTF8");
                }
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if("2".equals(type)){
            userLoginDTO.setUserType("3");
            List<LoginReturnJsonDTO> usersDTO = getUserList(user,userLoginDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("业主用户列表");
            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);
            String[] titles = {"序号", "姓名", "昵称","手机号", "项目", "地址", "身份证","注册时间"};
            XSSFRow headRow = sheet.createRow(0);

            if (usersDTO.size() > 0) {

                usersDTO.forEach(userDTO -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (usersDTO.size() > 0) {
                        for (int i = 0; i < usersDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            LoginReturnJsonDTO userDto = usersDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getRealName());//姓名

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getNickName());//昵称

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getMobile());//手机号

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getProjectName());//项目

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAddress());//地址

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getIdCard());//身份证

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getBeginTime());//注册时间
                        }
                    }
                });
            }
            try {
                //String fileName = new String(("业主管理").getBytes(), "ISO8859-1");
                String fileName = new String(("业主管理").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("业主管理", "UTF8");
                }
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if("3".equals(type)){
            userLoginDTO.setUserType("4");
            List<LoginReturnJsonDTO> usersDTO = getUserList(user,userLoginDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("同住人列表");
            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);
            String[] titles = {"序号", "姓名", "手机号", "项目", "地址", "身份证","业主昵称","授权时间"};
            XSSFRow headRow = sheet.createRow(0);

            if (usersDTO.size() > 0) {

                usersDTO.forEach(userDTO -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (usersDTO.size() > 0) {
                        for (int i = 0; i < usersDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            LoginReturnJsonDTO userDto = usersDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserName());//姓名

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getMobile());//手机号

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getProjectName());//项目

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAddress());//地址

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getIdCard());//身份证

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getNickName());//业主昵称

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getBeginTime());//授权时间
                        }
                    }
                });
            }
            try {
                //String fileName = new String(("同住人管理").getBytes(), "ISO8859-1");
                String fileName = new String(("同住人管理").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("同住人管理", "UTF8");
                }
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取业主认证信息列表
     */
    @Override
    public List<OwnerAuthenticationDTO> getOwnerAuthenticationList(OwnerAuthenticationDTO ownerAuthenticationDTO,WebPage webPage){
        //结果集
        List<OwnerAuthenticationDTO> ownerADTOs = new ArrayList<>();
        //执行查询
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("mobile",ownerAuthenticationDTO.getMobile());
        paramsMap.put("idCard",ownerAuthenticationDTO.getIdCard());
        paramsMap.put("handleState",ownerAuthenticationDTO.getHandleState());
        paramsMap.put("appId",ownerAuthenticationDTO.getAppId());
        List<OwnerAuthenticationEntity> ownerAuthenticationEntities = userInfoRepository.getOwnerAuthenticationList(paramsMap, webPage);
        //封装结果集
        List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(null, null);
        for (OwnerAuthenticationEntity ownerAuthenticationEntity : ownerAuthenticationEntities){
            OwnerAuthenticationDTO ownerADTO = new OwnerAuthenticationDTO();
            BeanUtils.copyProperties(ownerAuthenticationEntity,ownerADTO);
            //设置客户端名称
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                if (ownerADTO.getAppId().equals(clientConfigEntity.getWeChatAppId())){
                    ownerADTO.setClientName(clientConfigEntity.getClientName());
                }
            }
            ownerADTOs.add(ownerADTO);
        }
        return ownerADTOs;
    }

    /**
     * 获取业主认证信息
     */
    @Override
    public OwnerAuthenticationDTO getOwnerAuthenticationInfo(OwnerAuthenticationDTO ownerAuthenticationDTO){
        OwnerAuthenticationDTO ownerADTO = new OwnerAuthenticationDTO();
        OwnerAuthenticationEntity ownerAuthenticationEntity = userInfoRepository.getOwnerAuthenticationById(ownerAuthenticationDTO.getId());
        if (null != ownerAuthenticationEntity){
            List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(null,null);
            BeanUtils.copyProperties(ownerAuthenticationEntity,ownerADTO);
            //设置客户端名称
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                if (ownerADTO.getAppId().equals(clientConfigEntity.getWeChatAppId())){
                    ownerADTO.setClientName(clientConfigEntity.getClientName());
                }
            }
        }
        return ownerADTO;
    }

    /**
     * 更新业主认证状态
     */
    @Override
    public int updateOwnerAuthenticationState(OwnerAuthenticationDTO ownerAuthenticationDTO){
        int flag = 0;
        OwnerAuthenticationEntity ownerAuthenticationEntity = userInfoRepository.getOwnerAuthenticationById(ownerAuthenticationDTO.getId());
        if (null != ownerAuthenticationEntity){
            //操作人
            ownerAuthenticationEntity.setModifyBy(ownerAuthenticationDTO.getModifyBy());
            //前端点击认证通过,执行认证业主逻辑
            if (ownerAuthenticationDTO.getHandleState() == 101){
                //校验userCrm中用户身份证是否存在
                if (!userCRMRepository.checkOwner(ownerAuthenticationEntity.getIdCard())) {
                    flag = 1011;//业主证件号码未找到,认证失败
                    ownerAuthenticationEntity.setHandleState(102);//认证不通过
                    defaultConfigRepository.saveOrUpdate(ownerAuthenticationEntity);
                    return flag;
                }
                //校验身份证是否被认证
                if (!userInfoRepository.checkIdCard(ownerAuthenticationEntity.getIdCard())) {
                    flag = 1012;//业主证件号码已被认证,默认通过此条认证记录
                    ownerAuthenticationEntity.setHandleState(101);//认证默认通过
                    defaultConfigRepository.saveOrUpdate(ownerAuthenticationEntity);
                    return flag;
                }
                //-↓- 认证开始 -↓-
                UserInfoEntity userInfoEntity = userInfoRepository.get(ownerAuthenticationEntity.getUserId());
                UserCRMEntity userCRMEntity = userCRMRepository.getOwner(ownerAuthenticationEntity.getIdCard());
                userInfoEntity.setId(userCRMEntity.getMemberId());//Crm业主memberId绑定
                userInfoEntity.setRealName(userCRMEntity.getRealName());//业主真实姓名
                userInfoEntity.setIdCard(ownerAuthenticationEntity.getIdCard());//业主证件号码
                userInfoEntity.setIdType(ownerAuthenticationEntity.getIdType());//业主证件类型
                userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);//用户类型变更为业主
                userInfoEntity.setJump("4");//认证成功
                userInfoRepository.update(userInfoEntity);
                //-↑- 认证完成 -↑-
                //检索用户房产列表
                List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getHouseByUserMemberId(userInfoEntity.getId());
                //门禁分配
                HouseInfoEntity houseInfoEntity = null;
                if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
                    for (int i = 0,length = houseInfoEntityList.size(); i<length; i++){
                        houseInfoEntity = houseInfoEntityList.get(i);
                        houseInfoService.assignDoorByHouse(houseInfoEntity.getProjectNum(),houseInfoEntity.getArea(),houseInfoEntity.getBuildingNum(),houseInfoEntity.getUnitNum(),userInfoEntity);
                    }
                }
                //增加用户日志记录
                SystemLogEntity systemLogEntity = new SystemLogEntity();
                systemLogEntity.setLogId(IdGen.uuid());
                systemLogEntity.setLogTime(new Date());//注册日期
                systemLogEntity.setUserName(userInfoEntity.getNickName());//用户昵称
                systemLogEntity.setUserType(userInfoEntity.getUserType());//用户类型
                systemLogEntity.setUserMobile(userInfoEntity.getMobile());//手机号
                systemLogEntity.setIdCard(userInfoEntity.getIdCard());//身份证
                systemLogEntity.setSourceFrom(userInfoEntity.getSourceType());//来源
                systemLogEntity.setSysVersion(userInfoEntity.getOperatingSystem());//系统版本
                if (houseInfoEntityList != null && houseInfoEntityList.size() > 0) {
                    systemLogEntity.setProjectId(houseInfoEntityList.get(0).getProjectName());//项目
                } else {
                    systemLogEntity.setProjectId("");//项目
                }
                systemLogRepository.addSysLog(systemLogEntity);
                //调用用户统计
                String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                UserToTalEntity userToTal = userTotalRepository.getTotalInfo(dateNow);
                if (userToTal != null) {
                    if ("3".equals(userInfoEntity.getUserType())) {
                        userToTal.setOwnerUser(userToTal.getOwnerUser() + 1);//业主用户
                    }
                    userTotalRepository.update(userToTal);
                } else {
                    UserToTalEntity userToTalEntity = new UserToTalEntity();
                    userToTalEntity.setId(IdGen.uuid());
                    userToTalEntity.setCreateDate(new Date());
                    userToTalEntity.setCommonUser(0);//普通用户
                    if ("3".equals(userInfoEntity.getUserType())) {
                        userToTalEntity.setOwnerUser(1);//业主用户
                    } else {
                        userToTalEntity.setOwnerUser(0);//业主用户
                    }
                    userToTalEntity.setAndroid(0);      //安卓用户
                    userToTalEntity.setIos(0);          //IOS用户
                    userToTalEntity.setWeChat(0);       //微信用户
                    userToTalEntity.setAPP(0);          //APP用户
                    userTotalRepository.create(userToTalEntity);
                }
                //认证通过
                ownerAuthenticationEntity.setHandleState(101);
            }else{
                UserInfoEntity userInfoEntity = userInfoRepository.getByUserId(ownerAuthenticationEntity.getUserId());
                if ("3".equals(userInfoEntity.getUserType())){
                    //已经是业主,无法将认证状态设为失败
                    flag = 1021;
                    return flag;
                }else{
                    //前端点击认证失败,设置状态为102
                    ownerAuthenticationEntity.setHandleState(ownerAuthenticationDTO.getHandleState());//状态
                }
            }
            defaultConfigRepository.saveOrUpdate(ownerAuthenticationEntity);
        }
        return flag;
    }

    /**
     * 获取业主申诉信息列表
     */
    @Override
    public List<OwnerAuthenticationDTO> getOwnerAppealList(OwnerAuthenticationDTO ownerAuthenticationDTO,WebPage webPage){
        //结果集
        List<OwnerAuthenticationDTO> ownerADTOs = new ArrayList<>();
        //执行查询
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("cityId",ownerAuthenticationDTO.getCityId());
        paramsMap.put("projectNum",ownerAuthenticationDTO.getProjectNum());
        paramsMap.put("appId",ownerAuthenticationDTO.getAppId());
        paramsMap.put("ownerName",ownerAuthenticationDTO.getOwnerName());
        paramsMap.put("mobile",ownerAuthenticationDTO.getMobile());
        paramsMap.put("idCard",ownerAuthenticationDTO.getIdCard());
        paramsMap.put("handleState",ownerAuthenticationDTO.getHandleState());
        paramsMap.put("appealType",ownerAuthenticationDTO.getAppealType());
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = ownerAuthenticationDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        paramsMap.put("roleScopes",roleScopes);
        List<OwnerAppealEntity> ownerAppealEntities = userInfoRepository.getOwnerAppealList(paramsMap, webPage);
        //封装结果集
        List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(null, null);
        for (OwnerAppealEntity ownerAppealEntity : ownerAppealEntities){
            OwnerAuthenticationDTO ownerADTO = new OwnerAuthenticationDTO();
            BeanUtils.copyProperties(ownerAppealEntity,ownerADTO);
            //设置客户端名称
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                if (ownerADTO.getAppId().equals(clientConfigEntity.getWeChatAppId())){
                    ownerADTO.setClientName(clientConfigEntity.getClientName());
                }
            }
            ownerADTOs.add(ownerADTO);
        }
        return ownerADTOs;
    }

    /**
     * 获取业主申诉信息
     */
    @Override
    public OwnerAuthenticationDTO getOwnerAppealInfo(OwnerAuthenticationDTO ownerAuthenticationDTO){
        OwnerAuthenticationDTO ownerADTO = new OwnerAuthenticationDTO();
        OwnerAppealEntity ownerAppealEntity = userInfoRepository.getOwnerAppealById(ownerAuthenticationDTO.getId());
        if (null != ownerAppealEntity){
            List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(null,null);
            BeanUtils.copyProperties(ownerAppealEntity,ownerADTO);
            //设置客户端名称
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                if (ownerADTO.getAppId().equals(clientConfigEntity.getWeChatAppId())){
                    ownerADTO.setClientName(clientConfigEntity.getClientName());
                }
            }
        }
        return ownerADTO;
    }

    /**
     * 更新业主申诉状态,执行申诉
     * 该方法加锁,防止重复处理
     */
    @Override
    public synchronized int updateOwnerAppealState(OwnerAuthenticationDTO ownerAuthenticationDTO){
        int flag = 0;
        OwnerAppealEntity ownerAppealEntity = userInfoRepository.getOwnerAppealById(ownerAuthenticationDTO.getId());
        if (null != ownerAppealEntity){
            //操作人
            ownerAppealEntity.setModifyBy(ownerAuthenticationDTO.getModifyBy());
            if (ownerAuthenticationDTO.getHandleState() == 101){
                //前端点击申诉处理,状态101
                if (ownerAppealEntity.getHandleState() == 101){
                    //该条申诉已被处理成功
                    flag = 1011;
                }else{
                    //该条申诉状态为未处理100或申诉失败102,执行申诉
                    //会员方执行申诉
                    UserInfoEntity userInfoEntity = userInfoRepository.get(ownerAppealEntity.getUserId());
                    UserCRMEntity userCRMEntity = userCRMRepository.getByIdCard(ownerAppealEntity.getIdCard());
                    if (userCRMEntity != null) {
                        userInfoEntity.setIdCard(userCRMEntity.getIdCard());
                        userInfoEntity.setId(userCRMEntity.getMemberId());
                        userInfoEntity.setRealName(userCRMEntity.getRealName());
                        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);//用户类型变更为业主
                        userInfoEntity.setJump("4");
                        userInfoRepository.update(userInfoEntity);
                        //检索用户房产列表
                        List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getHouseByUserMemberId(userCRMEntity.getMemberId());
                        //门禁分配
                        HouseInfoEntity houseInfoEntity = null;
                        if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
                            for (int i = 0,length = houseInfoEntityList.size(); i<length; i++){
                                houseInfoEntity = houseInfoEntityList.get(i);
                                houseInfoService.assignDoorByHouse(houseInfoEntity.getProjectNum(),houseInfoEntity.getArea(),houseInfoEntity.getBuildingNum(),houseInfoEntity.getUnitNum(),userInfoEntity);
                            }
                        }
                        //对除当前业主外的该身份业主进行解绑
                        List<UserInfoEntity> userInfoEntityList = userInfoRepository.getUserInfoListByIdCard(ownerAppealEntity.getIdCard());
                        if (userInfoEntityList.size() > 1){
                            for (UserInfoEntity userInfo : userInfoEntityList){
                                if (!userInfo.getUserId().equals(userInfoEntity.getUserId())){
                                    //非当前用户,执行解绑
                                    userInfo.setIdCard(null);
                                    userInfo.setId(null);
                                    userInfo.setRealName(null);
                                    userInfo.setUserType(UserInfoEntity.USER_TYPE_NORMAL);//用户类型变更为普通会员
                                    userInfo.setJump("1");//跳转状态变更为未填写证件信息
                                    userInfoRepository.update(userInfo);
                                }
                            }
                        }
                        //增加用户日志记录
                        SystemLogEntity systemLogEntity = new SystemLogEntity();
                        systemLogEntity.setLogId(IdGen.uuid());
                        systemLogEntity.setLogTime(new Date());//注册日期
                        systemLogEntity.setUserName(userInfoEntity.getNickName());//用户昵称
                        systemLogEntity.setUserType(userInfoEntity.getUserType());//用户类型
                        systemLogEntity.setUserMobile(userInfoEntity.getMobile());//手机号
                        systemLogEntity.setIdCard(userInfoEntity.getIdCard());//身份证
                        systemLogEntity.setSourceFrom(userInfoEntity.getSourceType());//来源
                        systemLogEntity.setSysVersion(userInfoEntity.getOperatingSystem());//系统版本
                        if (houseInfoEntityList != null && houseInfoEntityList.size() > 0) {
                            systemLogEntity.setProjectId(houseInfoEntityList.get(0).getProjectName());//项目
                        } else {
                            systemLogEntity.setProjectId("");//项目
                        }
                        systemLogRepository.addSysLog(systemLogEntity);
                        //调用用户统计
                        String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                        UserToTalEntity userToTal = userTotalRepository.getTotalInfo(dateNow);
                        if (userToTal != null) {
                            if ("3".equals(userInfoEntity.getUserType())) {
                                userToTal.setOwnerUser(userToTal.getOwnerUser() + 1);//业主用户
                            }
                            userTotalRepository.update(userToTal);
                        } else {
                            UserToTalEntity userToTalEntity = new UserToTalEntity();
                            userToTalEntity.setId(IdGen.uuid());
                            userToTalEntity.setCreateDate(new Date());
                            userToTalEntity.setCommonUser(0);//普通用户
                            if ("3".equals(userInfoEntity.getUserType())) {
                                userToTalEntity.setOwnerUser(1);//业主用户
                            } else {
                                userToTalEntity.setOwnerUser(0);//业主用户
                            }
                            userToTalEntity.setAndroid(0);      //安卓用户
                            userToTalEntity.setIos(0);          //IOS用户
                            userToTalEntity.setWeChat(0);       //微信用户
                            userToTalEntity.setAPP(0);          //APP用户
                            userTotalRepository.create(userToTalEntity);
                        }
                        ownerAppealEntity.setHandleState(101);
                        defaultConfigRepository.saveOrUpdate(ownerAppealEntity);
                        //flag=0;
                    }else{
                        //会员系统中未找到业主信息,需请求CRM
                        String responseStatus = memberAppealService.requestCrmMemberAppeal(ownerAppealEntity);
                        if (responseStatus.equals("1")){
                            //请求成功
                            flag = 1012;
                            //增加积分
                            userRule(ownerAppealEntity);

                        }else{
                            //请求失败
                            flag = 1013;
                        }
                    }
                }
            }else{
                //前端点击申诉失败,状态102
                if (ownerAppealEntity.getHandleState() == 101){
                    //该条申诉已被处理成功
                    flag = 1021;
                }else{
                    //该条申诉状态为未处理100或申诉失败102,直接设置状态失败102
                    ownerAppealEntity.setHandleState(102);
                    defaultConfigRepository.saveOrUpdate(ownerAppealEntity);
                    //flag=0;
                }
            }
        }
        return flag;
    }

    private void userRule(OwnerAppealEntity ownerAppealEntity){

        HouseInfoEntity house = houseInfoRepository.getHouseByRoomId(ownerAppealEntity.getHouseNum());


        ClientConfigEntity clientConfigEntity = defaultConfigRepository.getClientConfigByAppId(ownerAppealEntity.getAppId());
        int appid = clientConfigEntity.getId();
        String modelType = "4";
        IntegralRuleEntity integralRuleEntity = integralRuleRepository.getIntegralRuleModelByTypeClien(appid,modelType);
        UserIntegralRuleRecordEntity u = new UserIntegralRuleRecordEntity(
                IdGen.uuid(),
                ownerAppealEntity.getAppId(),
                ownerAppealEntity.getProjectNum(),
                house == null ? "" : house.getAddress(),
                ownerAppealEntity.getUserId(),
                modelType,
                integralRuleEntity == null ? "0" : integralRuleEntity.getIntegralNumber(),
                new Date());
        userIntegralRuleRecordRepository.AddIntegralRuleEntity(u);
        UserIntegralRuleEntity user = userIntegralRuleRepository.get(ownerAppealEntity.getUserId(),ownerAppealEntity.getAppId());
        if(user == null){
            user = new UserIntegralRuleEntity();
            user.setIntegralNumber(integralRuleEntity == null ? "0" : integralRuleEntity.getIntegralNumber());
            user.setUserId(ownerAppealEntity.getUserId());
            user.setWeChatAppId(ownerAppealEntity.getAppId());
            user.setId(IdGen.uuid());
            userIntegralRuleRepository.AddUserIntegral(user);
        }else{
            String number =String.valueOf (Integer.parseInt(user.getIntegralNumber()) + Integer.parseInt(integralRuleEntity == null ? "0" : integralRuleEntity.getIntegralNumber()));
            user.setIntegralNumber(number);
            userIntegralRuleRepository.UpdateUserIntegral(user);
        }
    }

    /**
     * 获取CRM业主数据列表
     */
    @Override
    public List<Map<String,Object>> getCRMOwnerUserList(LoginReturnJsonDTO loginReturnJsonDTO,WebPage webPage){
        Map<String,Object> params = new HashedMap();
        params.put("projectCode",loginReturnJsonDTO.getProjectId());//项目
        params.put("realName",loginReturnJsonDTO.getUserName());//姓名
        params.put("idCard",loginReturnJsonDTO.getIdCard());//身份证号
        params.put("mobile",loginReturnJsonDTO.getMobile());//手机号
        params.put("roomName",loginReturnJsonDTO.getAddress());//房号
        if (null != loginReturnJsonDTO.getScopeId() && !"".equals(loginReturnJsonDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(loginReturnJsonDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            params.put("cityProjects",cityProjects);//城市下所有项目,以城市为单位检索
        }
        return userCRMRepository.getCRMOwnerUserList(params,webPage);
    }

    /**
     * 用户申诉模块导出Excel
     */
    @Override
    public void exportOwnerAppealListExcel(HttpServletResponse response,HttpServletRequest request,UserPropertyStaffEntity user,OwnerAuthenticationDTO ownerAuthenticationDTO) throws Exception{
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(4000);
        ServletOutputStream out = response.getOutputStream();
        String fileName = "用户申诉列表";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "用户申诉列表";
        String[] headers = {"序号", "手机号码", "业主姓名", "证件类型", "证件编码","房间编码","房产地址","申诉时间","项目","来源","申诉类型","处理状态"};
        //执行查询
        List<OwnerAuthenticationDTO> ownerAppealList = getOwnerAppealList(ownerAuthenticationDTO, webPage);
        List<ExportExcelUserAppealDTO> exportExcelUserAppealDTOs = new ArrayList<>();
        if (null != ownerAppealList){
            int num = 1;
            ExportExcelUserAppealDTO exportExcelUserAppealDTO = null;
            OwnerAuthenticationDTO userAppealDTO = null;
            for (int i = 0,length = ownerAppealList.size();i<length;i++){
                userAppealDTO = ownerAppealList.get(i);
                exportExcelUserAppealDTO = new ExportExcelUserAppealDTO();
                //序号
                exportExcelUserAppealDTO.setNum(num++);
                //手机号码
                exportExcelUserAppealDTO.setMobile(userAppealDTO.getMobile() == null ? "" : userAppealDTO.getMobile());
                //业主姓名
                exportExcelUserAppealDTO.setOwnerName(userAppealDTO.getOwnerName() == null ? "" : userAppealDTO.getOwnerName());
                //证件类型
                String idType = "";
                switch (userAppealDTO.getIdType()){
                    default:
                    case "100000000":
                        idType = "身份证";
                        break;
                    case "100000001":
                        idType = "军官证";
                        break;
                    case "100000002":
                        idType = "警官证";
                        break;
                    case "100000003":
                        idType = "护照";
                        break;
                    case "100000004":
                        idType = "营业执照";
                        break;
                }
                exportExcelUserAppealDTO.setIdType(idType);
                //证件编码
                exportExcelUserAppealDTO.setIdCard(userAppealDTO.getIdCard() == null ? "" : userAppealDTO.getIdCard());
                //房间编码
                exportExcelUserAppealDTO.setHouseNum(userAppealDTO.getHouseNum() == null ? "" : userAppealDTO.getHouseNum());
                //房间地址
                exportExcelUserAppealDTO.setHouseAddress(userAppealDTO.getHouseAddress() == null ? "" : userAppealDTO.getHouseAddress());
                //申诉时间
                exportExcelUserAppealDTO.setCreateOn(userAppealDTO.getCreateOn() == null ? "" : DateUtils.format(userAppealDTO.getCreateOn(),DateUtils.FORMAT_LONG));
                //项目
                exportExcelUserAppealDTO.setProjectName(userAppealDTO.getProjectName() == null ? "" : userAppealDTO.getProjectName());
                //来源
                exportExcelUserAppealDTO.setClientName(userAppealDTO.getClientName() == null ? "" : userAppealDTO.getClientName());
                //申诉类型
                String appealType = "";
                if (userAppealDTO.getAppealType() == 0){appealType = "普通申诉";}else{appealType = "特殊申诉";}
                exportExcelUserAppealDTO.setAppealType(appealType);
                //处理状态
                String handleState = "";
                if (userAppealDTO.getHandleState() == 100){handleState = "申诉未处理";}else if (userAppealDTO.getHandleState() == 101){handleState = "申诉通过";}else{handleState = "申诉失败";}
                exportExcelUserAppealDTO.setHandleState(handleState);
                exportExcelUserAppealDTOs.add(exportExcelUserAppealDTO);
            }
            ExportExcel<ExportExcelUserAppealDTO> ex = new ExportExcel<>();
            ex.exportExcel(title, headers, exportExcelUserAppealDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    /**
     * 手动同步HouseUserCrm房产数据
     */
    public void houseUserCrmSyn(){
        List<String> memberIds = userCRMRepository.getHouseUserCrmWithHouseNull();
        for (String memberId : memberIds){
            houseUserCRMRepository.updateHouseUserCrm(memberId);
        }
    }
}
