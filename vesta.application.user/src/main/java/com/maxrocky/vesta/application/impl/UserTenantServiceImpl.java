package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.ViewAppHouseInfoAdminDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.DTO.admin.HouseCompanyAdminDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.DTO.json.LG0001.LoginReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0002.RegisterParamJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.UserAuthInfoRepository;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.domain.repository.UserLoginBookRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunmei on 2016/3/16.
 */
@Service
public class UserTenantServiceImpl implements UserTenantService {
    /* 手机验证码服务 */
    @Autowired
    SMSAuthService smsAuthService;
    /* 基础业主服务 */
    @Autowired
    ViewAppOwnerInfoService viewAppOwnerInfoService;
    /* 用户信息 */
    @Autowired
    UserInfoRepository userInfoRepository;
    /* 房屋业主关系服务 */
    @Autowired
    HouseUserBookService houseUserBookService;
    /* 物业项目服务 */
    @Autowired
    HouseProjectService houseProjectService;
    /* 注册信息 */
    @Autowired
    UserAuthInfoRepository userAuthInfoRepository;
    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;
    /* 登录信息 */
    @Autowired
    UserLoginBookRepository userLoginBookRepository;
    /* 物业公司服务 */
    @Autowired
    HouseCompanyService houseCompanyService;
    /* 登录日志服务 */
    @Autowired
    LoginLogService loginLogService;
    /* 设备日志服务 */
    @Autowired
    EquipStatisticsService equipStatisticsService;
    /* mapper */
    @Autowired
    MapperFacade mapper;
    @Override
    public ApiResult register(RegisterParamJsonDTO registerParamJsonDTO) throws GeneralException {
        try {
            /* 基础数据判断 */
            if (registerParamJsonDTO == null) {
                return new ErrorApiResult("error_00000003");
            }
            ApiResult checkResult = registerParamJsonDTO.checkTenant();
            if (checkResult instanceof ErrorApiResult) {
                return checkResult;
            }

            /* 业主数据校验 */
            /* 手机短信验证码 */
            Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(registerParamJsonDTO.getMobile()
                    , registerParamJsonDTO.getAuthCode()
                    , SMSAuthEntity.TYPE_REGISTER);
            if (!checkAuthCode) {
                return new ErrorApiResult("tip_00000467");
            }
              /* 房屋数据 */
            ViewAppHouseInfoAdminDTO viewAppHouseInfoAdminDTO = viewAppOwnerInfoService.getBasicInfoByTenant(registerParamJsonDTO.toHouseParamJsonDTO());
            if(viewAppHouseInfoAdminDTO == null){
                return new ErrorApiResult("tip_UI000004");
            }

            /* 增加注册业主信息 */
            UserAuthInfoEntity userAuthInfoEntity = new UserAuthInfoEntity();
            userAuthInfoEntity.setId(IdGen.uuid());
            userAuthInfoEntity.setName(registerParamJsonDTO.getName());
            userAuthInfoEntity.setMobile(registerParamJsonDTO.getMobile());
            userAuthInfoEntity.setIdNumber(registerParamJsonDTO.getIdNumber());
            userAuthInfoEntity.setFormatName(viewAppHouseInfoAdminDTO.getFormatName());
            userAuthInfoEntity.setProjectName(viewAppHouseInfoAdminDTO.getProjectName());
            userAuthInfoEntity.setBuildingName(viewAppHouseInfoAdminDTO.getBuildingName());
            userAuthInfoEntity.setUnitName(viewAppHouseInfoAdminDTO.getUnitName());
            userAuthInfoEntity.setRoomName(viewAppHouseInfoAdminDTO.getRoomName());
            userAuthInfoEntity.setCreateBy(registerParamJsonDTO.getMobile());
            userAuthInfoEntity.setCreateOn(DateUtils.getDate());
            userAuthInfoEntity.setModifyBy(registerParamJsonDTO.getMobile());
            userAuthInfoEntity.setModifyOn(DateUtils.getDate());
            userAuthInfoEntity.setForm(registerParamJsonDTO.getForm());
            userAuthInfoEntity.setPhoneUUID(registerParamJsonDTO.getPhoneUUID());
            userAuthInfoEntity.setState(UserAuthInfoEntity.STATE_AUDIT_TENANT);
           /* 增加用户 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(registerParamJsonDTO.getMobile());
            if(userInfoEntity == null){
                userInfoEntity = registerParamJsonDTO.toUserInfoEntityTenant();
                userInfoRepository.create(userInfoEntity);
            }else{
                if(StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_TENANT)){
                    return new ErrorApiResult("tip_00000063");
                }
                userInfoEntity = registerParamJsonDTO.toUserInfoEntityTenant(userInfoEntity);
                userInfoRepository.update(userInfoEntity);
            }

            /* 创建业主房产关系 */
            houseUserBookService.createOwnerHouseUserBookByHouseId(userInfoEntity.getUserId(),viewAppHouseInfoAdminDTO.getViewAppHouseId());

            /* 如果用户没有配置信息，则增加 */
            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
            if(userSettingEntity == null){
                userSettingEntity = new UserSettingEntity();
                userSettingEntity.setUserId(userInfoEntity.getUserId());
                userSettingEntity.setProjectId(viewAppHouseInfoAdminDTO.getProjectId());
                userSettingEntity.setProjectName(viewAppHouseInfoAdminDTO.getProjectName());
                userSettingEntity.setCreateBy(userInfoEntity.getRealName());
                userSettingEntity.setCreateOn(DateUtils.getDate());
                userSettingEntity.setModifyBy(userInfoEntity.getRealName());
                userSettingEntity.setModifyOn(DateUtils.getDate());
                userSettingRepository.create(userSettingEntity);
            }else {
                userSettingEntity.setProjectId(viewAppHouseInfoAdminDTO.getProjectId());
                userSettingEntity.setProjectName(viewAppHouseInfoAdminDTO.getProjectName());
                userSettingEntity.setCreateBy(userInfoEntity.getRealName());
                userSettingEntity.setCreateOn(DateUtils.getDate());
                userSettingEntity.setModifyBy(userInfoEntity.getRealName());
                userSettingEntity.setModifyOn(DateUtils.getDate());
                userSettingRepository.update(userSettingEntity);
            }

            userAuthInfoEntity.setState(UserAuthInfoEntity.STATE_AUTO_SUCCEED);
            userAuthInfoEntity.setContent("自动注册成功");
            userAuthInfoEntity.setIdNumber("暂无");
            userAuthInfoRepository.create(userAuthInfoEntity);


            /* 查询用户有效登录信息 */
            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
            if(userLoginBookEntity != null){
            /* 将已经登录的信息设置其他地方登录 */
                userLoginBookEntity.setStateExit();
                userLoginBookRepository.update(userLoginBookEntity);
            }

            /* 创建登录信息 */
            UserLoginBookEntity userLoginBook = new UserLoginBookEntity(userInfoEntity);
            userLoginBook.setLoginType(registerParamJsonDTO.getForm());
            userLoginBook.setPhoneUUID(registerParamJsonDTO.getPhoneUUID());
            userLoginBookRepository.create(userLoginBook);

            /* 获取返回信息 */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);
            /* 获取用户设置 */
            userLoginInfo.setProjectId(userSettingEntity.getProjectId());
            userLoginInfo.setProjectName(userSettingEntity.getProjectName());

            userLoginInfo.setToken(userLoginBook.getLoginId());

            try {
                /* 获取项目信息 */
                HouseProjectDto houseProjectDto = houseProjectService.getProjectById(userSettingEntity.getProjectId());
                if(houseProjectDto != null){
                    HouseCompanyAdminDTO houseCompanyAdminDTO = houseCompanyService.getHouseCompanyAdminDTOById(houseProjectDto.getCompanyId());
                    if(houseCompanyAdminDTO != null){
                        /* 登录日志 */
                        loginLogService.AddLoginLogManage(houseCompanyAdminDTO.getAreaId(),userSettingEntity.getProjectId(),userSettingEntity.getUserId(),"192.168.0.1",userInfoEntity.getUserName() + "登录成功了！",registerParamJsonDTO.getForm());
                        /* 设备日志 */
                        equipStatisticsService.addEquipManage(userSettingEntity.getProjectId(),houseProjectDto.getCompanyId(),houseCompanyAdminDTO.getAreaId(),registerParamJsonDTO.getForm(),userInfoEntity.getUserId());
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
                System.out.println("---------->>Create login log exception.");
            }

            return new SuccessApiResult(userLoginInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
}
