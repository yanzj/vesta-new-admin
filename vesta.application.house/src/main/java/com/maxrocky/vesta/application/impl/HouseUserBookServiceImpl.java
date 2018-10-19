package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseUserBookAdminDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseUserAdminDTO;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.application.inf.HouseUserBookService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tom on 2016/1/19 10:08.
 * Describe:房屋业主关系Service接口实现类
 */
@Service
public class HouseUserBookServiceImpl implements HouseUserBookService {

    /* 房屋业主关系 */
    @Autowired
    HouseUserBookRepository houseUserBookRepository;
    /* 物业房产信息 */
    @Autowired
    ViewAppHouseInfoRepository viewAppHouseInfoRepository;
    /* 业主房产 */
    @Autowired
    HouseInfoRepository houseInfoRepository;
    /* 公司 */
    @Autowired
    HouseCompanyRepository houseCompanyRepository;
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
    /* 用户 */
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserCRMRepository userCRMRepository;
    @Autowired
    SystemLogRepository systemLogRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;
    @Autowired
    HouseInfoService houseInfoService;

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:创建房屋业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:10:26
     */
    @Override
    public ApiResult createHouseUserBook(HouseUserBookEntity houseUserBookEntity) {
        houseUserBookRepository.create(houseUserBookEntity);
        return new SuccessApiResult();
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据房产Id查询成员信息
     * CreateBy:Tom
     * CreateOn:2016-01-20 05:40:31
     */
    @Override
    public ApiResult getHouseUserBookListByHouseId(String houseId) {
        return new SuccessApiResult(houseUserBookRepository.getListByHouseId(houseId));
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据用户Id、房产Id获取用户关系列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 06:36:15
     */
    @Override
    public List<HouseUserAdminDTO> getHouseUserAdminListByUserIdAndProjectId(String userId, String projectId) {

        List<HouseUserAdminDTO> houseUserAdminDTOList = new ArrayList<HouseUserAdminDTO>();
        List<Object[]> objectList = houseUserBookRepository.getObjectListByUserIdAndProjectId(userId, projectId);
        for (Object[] houseUserAdmin : objectList){
            HouseUserAdminDTO houseUserAdminDTO = new HouseUserAdminDTO();
            houseUserAdminDTO.setId((String)houseUserAdmin[0]);
            houseUserAdminDTO.setUserId((String)houseUserAdmin[1]);
            houseUserAdminDTO.setUserType((String)houseUserAdmin[2]);
            houseUserAdminDTO.setAddress((String)houseUserAdmin[3]);
            houseUserAdminDTOList.add(houseUserAdminDTO);
        }

        return houseUserAdminDTOList;
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:删除房产、业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:41:51
     */
    @Override
    public ApiResult deleteHouseUserBook(String userId, String houseUserId) {

        /* 获取房产关系 */
        HouseUserBookEntity houseUserBookEntity = houseUserBookRepository.get(houseUserId);
        if(houseUserBookEntity == null){
            return new ErrorApiResult("tip_00000431");
        }

        /* 验证业主权限 */
        Boolean checkOwner = houseUserBookRepository.checkOwner(userId, houseUserBookEntity.getHouseId());
        if(!checkOwner){
            return new ErrorApiResult("tip_00000555");
        }

        houseUserBookEntity.remove();
        houseUserBookRepository.update(houseUserBookEntity);
        return new SuccessApiResult(houseUserBookEntity);
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:获取用户当前最高类型
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:24:42
     */
    @Override
    public String getUserType(String userId, String houseId) {
        return houseUserBookRepository.getUserType(userId, houseId);
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:判断是否业主
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:31:07
     */
    @Override
    public Boolean checkOwner(String userId, String houseId) {
        return houseUserBookRepository.checkOwner(userId, houseId);
    }

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据用户Id、房产Id获取房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:52:07
     */
    @Override
    public ApiResult getHouseUserBookByUserIdAndHouseId(String userId, String houseId) {
        return new SuccessApiResult(houseUserBookRepository.getByUserIdAndHouseId(userId, houseId));
    }

    /**
     * Code:For UI0002
     * Type:Service Method
     * Describe:根据用户Id、物业业主Id创建房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-22 03:41:02
     */
    @Override
    public void createOwnerHouseUserBook(String userId, int viewAppOwnerId) {

        /* 根据物业业主ID查询名下所有物业房产 */
        List<ViewAppHouseInfoEntity> viewAppHouseInfoEntityList = viewAppHouseInfoRepository.getListByOwnerId(viewAppOwnerId);
        for (ViewAppHouseInfoEntity viewAppHouseInfoEntity : viewAppHouseInfoEntityList){
            /* 查询物业房产是否在系统中存在 */
            HouseInfoEntity houseInfoEntity = houseInfoRepository.getByViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
            if(houseInfoEntity == null){
                /* 不存在则新增房产 */
                houseInfoEntity = new HouseInfoEntity();
                houseInfoEntity.setId(IdGen.uuid());
                /* 获取公司 */
                HouseCompanyEntity houseCompanyEntity = houseCompanyRepository.getByCompanyName(viewAppHouseInfoEntity.getCompanyName());
                if(houseCompanyEntity == null){
                    continue;
                }
                houseInfoEntity.setCompanyId(houseCompanyEntity.getId());
                houseInfoEntity.setCompanyName(houseCompanyEntity.getName());
                /* 获取项目 */
                HouseProjectEntity houseProjectEntity = houseProjectRepository.getByCompanyIdAndProjectName(houseCompanyEntity.getId(), viewAppHouseInfoEntity.getProjectName());
                if(houseProjectEntity == null){
                    continue;
                }
                HouseFormatEntity houseFormatEntity = houseFormatRepository.getByProjectIdAndName(houseProjectEntity.getId(),viewAppHouseInfoEntity.getFormatName());
                if(houseFormatEntity == null){
                    continue;
                }
                houseInfoEntity.setProjectId(houseProjectEntity.getId());
                houseInfoEntity.setProjectName(houseProjectEntity.getName());
                /* 获取楼 */
                HouseBuildingEntity houseBuildingEntity = houseBuildingRepository.getByBuildingNameAmdProjectId(viewAppHouseInfoEntity.getBlockName(), houseProjectEntity.getId(), houseFormatEntity.getId());
                if(houseBuildingEntity == null){
                    continue;
                }
                houseInfoEntity.setBuildingId(houseBuildingEntity.getId());
                //houseInfoEntity.setBuildingName(houseBuildingEntity.getName());
                /* 获取单元 */
                HouseUnitEntity houseUnitEntity = houseUnitRepository.getByUnitNameAndBuildingId(viewAppHouseInfoEntity.getCellNo(), houseBuildingEntity.getId());
                if(houseUnitEntity == null){
                    continue;
                }
                //houseInfoEntity.setUnitId(houseUnitEntity.getId());
                //houseInfoEntity.setUnitName(houseUnitEntity.getName());
                /* 获取房间 */
                HouseRoomEntity houseRoomEntity = houseRoomRepository.getByRoomNameAndUnitId(viewAppHouseInfoEntity.getHouseNo(), houseUnitEntity.getId());
                if(houseRoomEntity == null){
                    continue;
                }
                houseInfoEntity.setRoomId(houseRoomEntity.getId());
                houseInfoEntity.setRoomName(houseRoomEntity.getName());

                houseInfoEntity.setFormatId(houseFormatEntity.getId());
                houseInfoEntity.setFormatName(houseFormatEntity.getName());
                houseInfoEntity.setAddress(viewAppHouseInfoEntity.getAddress());
                houseInfoEntity.setHouseType(viewAppHouseInfoEntity.getHouseType());
                houseInfoEntity.setBuildingArea(viewAppHouseInfoEntity.getBillingArea());
                houseInfoEntity.setViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
                houseInfoEntity.setCreateBy(userId);
                houseInfoEntity.setCreateOn(DateUtils.getDate());
                houseInfoEntity.setModifyBy(userId);
                houseInfoEntity.setModifyOn(DateUtils.getDate());
                houseInfoRepository.create(houseInfoEntity);
            }

            /* 增加房产业主关系 */
            HouseUserBookEntity houseUserBookEntity = new HouseUserBookEntity();
            houseUserBookEntity.setId(IdGen.uuid());
            houseUserBookEntity.setUserId(userId);
            houseUserBookEntity.setHouseId(houseInfoEntity.getId());
            houseUserBookEntity.setState(HouseUserBookEntity.STATE_NORMAL);
            houseUserBookEntity.setUserType(HouseUserBookEntity.USER_TYPE_OWNER);
            houseUserBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_YES);
            houseUserBookEntity.setCreateBy(userId);
            houseUserBookEntity.setCreateOn(DateUtils.getDate());
            houseUserBookEntity.setModifyBy(userId);
            houseUserBookEntity.setModifyOn(DateUtils.getDate());
            houseUserBookRepository.create(houseUserBookEntity);
        }
    }

    /**
     * Code:For UI0002
     * Type:Service Method
     * Describe:根据houseId创建房产业主关系
     * CreateBy:sunmei
     * CreateOn:2016-01-22 03:41:02
     */
    @Override
    public void createOwnerHouseUserBookByHouseId(String userId,int houseId) {


        ViewAppHouseInfoEntity viewAppHouseInfoEntity = viewAppHouseInfoRepository.getHomeInfoByHouseId(houseId+"");
            /* 查询物业房产是否在系统中存在 */
            HouseInfoEntity houseInfoEntity = houseInfoRepository.getByViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
            if(houseInfoEntity == null){
                /* 不存在则新增房产 */
                houseInfoEntity = new HouseInfoEntity();
                houseInfoEntity.setId(IdGen.uuid());
                /* 获取公司 */
                HouseCompanyEntity houseCompanyEntity = houseCompanyRepository.getByCompanyName(viewAppHouseInfoEntity.getCompanyName());

                houseInfoEntity.setCompanyId(houseCompanyEntity.getId());
                houseInfoEntity.setCompanyName(houseCompanyEntity.getName());
                /* 获取项目 */
                HouseProjectEntity houseProjectEntity = houseProjectRepository.getByCompanyIdAndProjectName(houseCompanyEntity.getId(), viewAppHouseInfoEntity.getProjectName());

                houseInfoEntity.setProjectId(houseProjectEntity.getId());
                houseInfoEntity.setProjectName(houseProjectEntity.getName());
                /* 获取楼 */
                HouseBuildingEntity houseBuildingEntity = houseBuildingRepository.getByBuildingNameAmdProjectId(viewAppHouseInfoEntity.getBlockName(), houseProjectEntity.getId());

                houseInfoEntity.setBuildingId(houseBuildingEntity.getId());
                //houseInfoEntity.setBuildingName(houseBuildingEntity.getName());
                /* 获取单元 */
                HouseUnitEntity houseUnitEntity = houseUnitRepository.getByUnitNameAndBuildingId(viewAppHouseInfoEntity.getCellNo(), houseBuildingEntity.getId());

                //houseInfoEntity.setUnitId(houseUnitEntity.getId());
                //houseInfoEntity.setUnitName(houseUnitEntity.getName());
                /* 获取房间 */
                HouseRoomEntity houseRoomEntity = houseRoomRepository.getByRoomNameAndUnitId(viewAppHouseInfoEntity.getHouseNo(), houseUnitEntity.getId());

                houseInfoEntity.setRoomId(houseRoomEntity.getId());
                houseInfoEntity.setRoomName(houseRoomEntity.getName());
                HouseFormatEntity houseFormatEntity = houseFormatRepository.getByName(viewAppHouseInfoEntity.getFormatName());

                houseInfoEntity.setFormatId(houseFormatEntity.getId());
                houseInfoEntity.setFormatName(houseFormatEntity.getName());
                houseInfoEntity.setAddress(viewAppHouseInfoEntity.getAddress());
                houseInfoEntity.setHouseType(viewAppHouseInfoEntity.getHouseType());
                houseInfoEntity.setBuildingArea(viewAppHouseInfoEntity.getBillingArea());
                houseInfoEntity.setViewAppHouseId(viewAppHouseInfoEntity.getHouseId());
                houseInfoEntity.setCreateBy(userId);
                houseInfoEntity.setCreateOn(DateUtils.getDate());
                houseInfoEntity.setModifyBy(userId);
                houseInfoEntity.setModifyOn(DateUtils.getDate());
                houseInfoRepository.create(houseInfoEntity);
            }

            /* 增加房产业主关系 */
            HouseUserBookEntity houseUserBookEntity = new HouseUserBookEntity();
            houseUserBookEntity.setId(IdGen.uuid());
            houseUserBookEntity.setUserId(userId);
            houseUserBookEntity.setHouseId(houseInfoEntity.getId());
            houseUserBookEntity.setState(HouseUserBookEntity.STATE_NORMAL);
            houseUserBookEntity.setUserType(HouseUserBookEntity.USER_TYPE_TENANT);
            houseUserBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_YES);
            houseUserBookEntity.setCreateBy(userId);
            houseUserBookEntity.setCreateOn(DateUtils.getDate());
            houseUserBookEntity.setModifyBy(userId);
            houseUserBookEntity.setModifyOn(DateUtils.getDate());
            houseUserBookRepository.create(houseUserBookEntity);

    }

    /**
     * Code:For UI0012
     * Type:UI Method
     * Describe:返回指定授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 11:53:04
     */
    @Override
    public HouseUserBookAdminDTO getHouseUserBookAdminDTOByHouseUserId(String houseUserId) {

        HouseUserBookEntity houseUserBookEntity = houseUserBookRepository.get(houseUserId);
        if(houseUserBookEntity == null){
            return null;
        }
        HouseInfoEntity houseInfoEntity = houseInfoRepository.get(houseUserBookEntity.getHouseId());
        HouseUserBookAdminDTO houseUserBookAdminDTO = mapper.map(houseUserBookEntity, HouseUserBookAdminDTO.class);
        if(houseInfoEntity != null){
            houseUserBookAdminDTO.setAddress(houseInfoEntity.getAddress());
        }

        return houseUserBookAdminDTO;
    }

    /**
     * Code:For UI0013
     * Type:UI Method
     * Describe:修改授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 02:09:59
     */
    @Override
    public ApiResult updateHouseUserBook(HouseUserBookAdminDTO houseUserBookAdminDTO) {

        HouseUserBookEntity houseUserBookEntity = houseUserBookRepository.get(houseUserBookAdminDTO.getId());
        if(houseUserBookEntity == null){
            return new ErrorApiResult("tip_UI000001");
        }

        houseUserBookEntity.setIsPay(houseUserBookAdminDTO.getIsPay());
        houseUserBookEntity.setUserType(houseUserBookAdminDTO.getUserType());
        houseUserBookRepository.update(houseUserBookEntity);

        return new SuccessApiResult();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取同住人
     * param id:房屋id
     * ModifyBy:
     */
    @Override
    public ApiResult getHousemate(UserInfoEntity userInfo, String id) {
        if (userInfo == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try{
            List<HouseUserAdminDTO> houseUserDTOs=new ArrayList<HouseUserAdminDTO>();
            if(!"4".equals(userInfo.getUserType())){//非同住人身份可获取该房产同住人
                //即是业主，也是同住人身份，判断该房产业主是否与添加同住人信息相同
                HouseUserBookEntity userBooks = houseUserBookRepository.getByUserIdAndHouseId(userInfo.getUserId(), id);
                if (userBooks == null) {
                    //获取该房产下所有同住人
                    List<HouseUserBookEntity> userBook = houseUserBookRepository.getListByHouseId(id);
                    if (userBook.size() > 0) {
                        for (HouseUserBookEntity users : userBook) {
                            UserInfoEntity user = userInfoRepository.get(users.getUserId());
                            HouseUserAdminDTO houseUser = new HouseUserAdminDTO();
                            if (user != null) {
                                houseUser.setUserId(user.getUserId());//用户id
                                houseUser.setName(user.getRealName());//真实姓名
                                houseUser.setPhone(user.getMobile());//电话
                            }
                            houseUserDTOs.add(houseUser);
                        }
                    }
                }
            }
            return new SuccessApiResult(houseUserDTOs);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 添加同住人
     * param id:房屋id
     * param name:真实姓名
     * param phone:电话
     * ModifyBy:
     */
    @Override
    public ApiResult createHousemates(UserInfoEntity userInfo,String id,String name, String phone,String idCardNumber) {
        if (userInfo == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(name)){
            return ErrorResource.getError("tip_00000508");//真实姓名不能为空
        }
        if(StringUtil.isEmpty(phone)){
            return ErrorResource.getError("tip_00000397");//手机号不能为空
        }
        if(phone.length()<11){
            return ErrorResource.getError("tip_00000402");//手机号码应为11位
        }
        if(phone.length()>12){
            return ErrorResource.getError("tip_00000402");//手机号码应为11位
        }
        /*
        if(StringUtil.isEmpty(idCardNumber)){
            return ErrorResource.getError("tip_00000388");//身份证为空
        }
        if(!StringUtil.isEmpty(idCardNumber)) {
            if (idCardNumber.length() < 15) {
                return ErrorResource.getError("tip_00000390");//身份证应为15位
            }
            if (idCardNumber.length() > 18) {
                return ErrorResource.getError("tip_00000390");//身份证应为18位
            }
        }
        */
        try{
            HouseInfoEntity houseInfo=houseInfoRepository.get(id);
            if(houseInfo==null){
                return ErrorResource.getError("tip_00000141");//获取房屋信息失败
            }
            /*if(houseInfo.getMemberId().equals(userInfo.getId())){
                return ErrorResource.getError("tip_00000143");//不能添加自己为同住人
            }*/
            if("4".equals(userInfo.getUserType())){
                return ErrorResource.getError("tip_00000144");//您是同住人身份，暂无权限
            }
            //查询该房产下正常状态：用户是否已存在
            HouseUserBookEntity userBooks = houseUserBookRepository.getByUserIdAndHouseId(userInfo.getUserId(), id);
            if (userBooks != null) {
                return ErrorResource.getError("tip_00000145");//该房屋您是同住人，暂无权限
            }
            UserInfoEntity userInfoEntity=userInfoRepository.getUserInfo(name, phone);
            if(userInfoEntity==null){
                return ErrorResource.getError("tip_00000474");//用户不存在
            }else {
                if(!StringUtil.isEmpty(userInfoEntity.getId())) {
                    if (houseInfo.getMemberId().equals(userInfoEntity.getId())) {
                        return ErrorResource.getError("tip_00000143");//不能添加自己为同住人
                    }
                }
                /*
                if(!StringUtil.isEmpty(userInfoEntity.getIdCard()) && !StringUtil.isEmpty(idCardNumber)){
                    if(!userInfoEntity.getIdCard().equals(idCardNumber)){
                        return ErrorResource.getError("tip_00000388");//身份证不匹配
                    }
                }
                */
                //查询该房产下正常状态：用户是否已存在
                HouseUserBookEntity userBook = houseUserBookRepository.getByUserIdAndHouseId(userInfoEntity.getUserId(), id);
                if (userBook != null) {
                    return ErrorResource.getError("tip_00000061");//此房屋已经存在该用户
                }
                UserCRMEntity userCrm=new UserCRMEntity();
                if(!StringUtil.isEmpty(userInfoEntity.getIdCard())) {
                    userCrm = userCRMRepository.getByIdCard(userInfoEntity.getIdCard());//输入身份证时判断
                }
                //查询该房产下解除状态：用户是否已存在
                HouseUserBookEntity remove = houseUserBookRepository.getUserByUserIdAndHouseId(userInfoEntity.getUserId(), id);
                if (remove != null) {
                    if(userInfoEntity.getUserType().equals("2")){
                        userInfoEntity.setUserType("4");
                    }
                    if(userCrm!=null) {
                        if (!StringUtil.isEmpty(userCrm.getMemberId())) {
                            userInfoEntity.setId(userCrm.getMemberId());
                        }
                    }
                    userInfoEntity.setRealName(name);
                    userInfoRepository.update(userInfoEntity);
                    remove.setState(remove.STATE_NORMAL);//修改为正常状态
                    remove.setModifyBy(userInfo.getUserId());
                    remove.setModifyOn(DateUtils.getDate());
                    remove.setUserType(userInfoEntity.getUserType());
                    houseUserBookRepository.update(remove);
                }else {
                    if(userInfoEntity.getUserType().equals("2")){
                        userInfoEntity.setUserType("4");
                    }
                    if(userCrm!=null) {
                        if (!StringUtil.isEmpty(userCrm.getMemberId())) {
                            userInfoEntity.setId(userCrm.getMemberId());
                        }
                    }
                    userInfoEntity.setRealName(name);
                    userInfoRepository.update(userInfoEntity);
                    HouseUserBookEntity userBookEntity = new HouseUserBookEntity();
                    userBookEntity.setId(IdGen.uuid());
                    userBookEntity.setUserId(userInfoEntity.getUserId());
                    userBookEntity.setHouseId(id);
                    userBookEntity.setState(HouseUserBookEntity.STATE_NORMAL);
                    userBookEntity.setUserType(userInfoEntity.getUserType());
                    userBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_YES);
                    userBookEntity.setCreateBy(userInfo.getUserId());
                    userBookEntity.setCreateOn(DateUtils.getDate());
                    userBookEntity.setIdCardNumber(idCardNumber);
                    if(userCrm!=null) {
                        if (!StringUtil.isEmpty(userCrm.getMemberId())) {
                            userBookEntity.setMemberId(userCrm.getMemberId());
                        }
                    }
                    if(!StringUtil.isEmpty(userInfoEntity.getId())){
                        userBookEntity.setMemberId(userInfoEntity.getId());
                    }
                    houseUserBookRepository.create(userBookEntity);
                }

                //新增用户日志
                SystemLogEntity systemLogEntity = new SystemLogEntity();
                systemLogEntity.setLogId(IdGen.uuid());
                systemLogEntity.setUserName(userInfoEntity.getNickName());
                systemLogEntity.setUserType(userInfoEntity.getUserType());
                systemLogEntity.setUserMobile(userInfoEntity.getMobile());
                systemLogEntity.setIdCard(userInfoEntity.getIdCard());
                systemLogEntity.setSysVersion(userInfoEntity.getOperatingSystem());
                systemLogEntity.setSourceFrom(userInfoEntity.getSourceType());
                systemLogEntity.setLogTime(new Date());
                systemLogEntity.setProjectId(houseInfo.getProjectName());
                systemLogRepository.addSysLog(systemLogEntity);
                //同住人分配门禁
                houseInfoService.assignDoorByHouse(houseInfo.getProjectNum(), houseInfo.getArea(), houseInfo.getBuildingNum(), houseInfo.getUnitNum(), userInfoEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 删除同住人
     * param id:房屋id
     * param userId
     * ModifyBy:
     */
    @Override
    public ApiResult getDelHousemate(UserInfoEntity userInfo, String id, String userId) {
        if (userInfo == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        if(StringUtil.isEmpty(userId)){
            return ErrorResource.getError("tip_me0001");//用户Id不能为空
        }
        try {
            //查询该房产下正常状态：用户是否已存在
            HouseUserBookEntity userBook = houseUserBookRepository.getByUserIdAndHouseId(userId, id);
            if (userBook != null) {
                userBook.setState(userBook.STATE_REMOVE);//解除关系
                userBook.setModifyBy(userInfo.getUserId());
                userBook.setModifyOn(DateUtils.getDate());
                houseUserBookRepository.update(userBook);
                //更新用户类型为普通用户
                UserInfoEntity userInfoEntity = userInfoRepository.get(userId);
                userInfoEntity.setIdCard(null);
                userInfoEntity.setUserType("2");
                userInfoRepository.update(userInfoEntity);
                //取消门禁权限
                HouseInfoEntity houseInfo=houseInfoRepository.get(userBook.getHouseId());
                houseInfoService.cancelDoorByHouse(houseInfo.getProjectNum(), houseInfo.getArea(), houseInfo.getBuildingNum(),houseInfo.getUnitNum(),userInfoEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }
}
