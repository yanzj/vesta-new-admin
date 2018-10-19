package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.json.AppealDTO;
import com.maxrocky.vesta.application.DTO.json.LG0001.LoginReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.QualicationDTO;
import com.maxrocky.vesta.application.DTO.json.UI0002.RegisterParamJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0003.ResetPasswordJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0004.UserReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0005.ModifyUserInfoParamJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0006.HouseUserJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0007.MyHouseUserJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0009.CreateHouseUserJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0010.FeedbackParamJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0011.ModifyPassword;
import com.maxrocky.vesta.application.DTO.json.UI0012.HouseUserReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0013.ModifyHouseUserJsonDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.utility.*;
import com.maxrocky.vesta.utility.CookieTools;
import com.maxrocky.vesta.weixin.*;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Map;


/**
 * Created by Tom on 2016/1/14 9:45.
 * Describe:用户信息Service接口实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /* 用户信息 */
    @Autowired
    UserInfoRepository userInfoRepository;
    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;
    /* 登录信息 */
    @Autowired
    UserLoginBookRepository userLoginBookRepository;
    /* 注册信息 */
    @Autowired
    UserAuthInfoRepository userAuthInfoRepository;
    /* 意见反馈 */
    @Autowired
    UserFeedbackRepository userFeedbackRepository;
    /* 用户图片 */
    @Autowired
    UserImageRepository userImageRepository;
    @Autowired
    HouseCityRepository houseCityRepository;
    @Autowired
    HouseProjectRepository houseProjectRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;
    /*微信token*/
    @Autowired
    UserTokenRepository userTokenRepository;

    /* 手机验证码服务 */
    @Autowired
    SMSAuthService smsAuthService;
    /* 基础业主服务 */
    @Autowired
    ViewAppOwnerInfoService viewAppOwnerInfoService;
    /* 房屋信息服务 */
    @Autowired
    HouseInfoService houseInfoService;
    /* 房屋业主关系服务 */
    @Autowired
    HouseUserBookService houseUserBookService;
    /* 登录日志服务 */
    @Autowired
    LoginLogService loginLogService;
    /* 设备日志服务 */
    @Autowired
    EquipStatisticsService equipStatisticsService;
    /* 物业项目服务 */
    @Autowired
    HouseProjectService houseProjectService;
    /* 物业公司服务 */
    @Autowired
    HouseCompanyService houseCompanyService;
    @Autowired
    AppVersionRepository appVersionRepository;
    @Autowired
    SystemConfigRepository systemConfigRepository;
    @Autowired
    UserCRMRepository userCRMRepository;
    @Autowired
    private MembershipRegisterService membershipRegisterService;
    @Autowired
    private AccountCRMRepository accountCRMRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;
    /*会员更新*/
    @Autowired
    private MemberinfoService memberinfoService;
    @Autowired
    private InvoicesTotalRepository invoicesTotalRepository;
    @Autowired
    private UserTotalRepository userTotalRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;
    @Autowired
    UserSettingService userSettingService;

    /* 默认配置服务 */
    @Autowired
    private DefaultConfigService defaultConfigService;
    @Autowired
    SystemLogRepository systemLogRepository;

    @Autowired
    CommunityCenterRespository communityCenterRespository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    SMSAlertsService smsAlertsService;

    /**
     * Code:UI0002
     * Type:UI Method
     * Describe:业主注册
     * CreateBy:Tom
     * CreateOn:2016-01-18 05:02:12
     */
    @Override
    public ApiResult register(RegisterParamJsonDTO registerParamJsonDTO) throws GeneralException {

        try {
            /* 基础数据判断 */
            if (registerParamJsonDTO == null) {
                return new ErrorApiResult("error_00000003");
            }
            ApiResult checkResult = registerParamJsonDTO.check();
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
            ViewAppHouseInfoAdminDTO viewAppHouseInfoAdminDTO = viewAppOwnerInfoService.getBasicInfoByHouse(registerParamJsonDTO.toHouseParamJsonDTO());
            if (viewAppHouseInfoAdminDTO == null) {
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

            /* 验证业主基础信息 需要增加注册信息*/
            if (!viewAppHouseInfoAdminDTO.checkOwnerName(registerParamJsonDTO.getName())) {

                userAuthInfoEntity.setState(UserAuthInfoEntity.STATE_AUTO_FAIL);
                userAuthInfoEntity.setContent("业主姓名不符");
                userAuthInfoRepository.create(userAuthInfoEntity);

                return new ErrorApiResult("tip_UI000004");
            }
            if (!viewAppHouseInfoAdminDTO.checkIDCard(registerParamJsonDTO.getIdNumber())) {

                userAuthInfoEntity.setState(UserAuthInfoEntity.STATE_AUTO_FAIL);
                userAuthInfoEntity.setContent("证件号码不符");
                userAuthInfoRepository.create(userAuthInfoEntity);

                return new ErrorApiResult("tip_UI000004");
            }
//            if(!viewAppHouseInfoAdminDTO.getViewAppOwnerMobile().contains(registerParamJsonDTO.getMobile())){
//
//                userAuthInfoEntity.setState(UserAuthInfoEntity.STATE_AUDIT_WAIT);
//                userAuthInfoEntity.setContent("手机号码不符");
//                userAuthInfoRepository.create(userAuthInfoEntity);
//
//                return new ErrorApiResult("tip_UI000004");
//            }

            /* 增加用户 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(registerParamJsonDTO.getMobile());
            if (userInfoEntity == null) {
                userInfoEntity = registerParamJsonDTO.toUserInfoEntity();
                userInfoEntity.setIdType(viewAppHouseInfoAdminDTO.getViewAppOwnerCardType());
                userInfoEntity.setViewAppOwnerId(viewAppHouseInfoAdminDTO.getViewAppOwnerId());
                userInfoRepository.create(userInfoEntity);
            } else {
                if (StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_OWNER)) {
                    return new ErrorApiResult("tip_00000063");
                }
                userInfoEntity = registerParamJsonDTO.toUserInfoEntity(userInfoEntity);
                userInfoEntity.setIdType(viewAppHouseInfoAdminDTO.getViewAppOwnerCardType());
                userInfoEntity.setViewAppOwnerId(viewAppHouseInfoAdminDTO.getViewAppOwnerId());
                userInfoRepository.update(userInfoEntity);
            }

            /* 创建业主房产关系 */
            houseUserBookService.createOwnerHouseUserBook(userInfoEntity.getUserId(), viewAppHouseInfoAdminDTO.getViewAppOwnerId());

            /* 如果用户没有配置信息，则增加 */
            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
            if (userSettingEntity == null) {
                userSettingEntity = new UserSettingEntity();
                userSettingEntity.setUserId(userInfoEntity.getUserId());
                userSettingEntity.setProjectId(viewAppHouseInfoAdminDTO.getProjectId());
                userSettingEntity.setProjectName(viewAppHouseInfoAdminDTO.getProjectName());
                userSettingEntity.setCreateBy(userInfoEntity.getRealName());
                userSettingEntity.setCreateOn(DateUtils.getDate());
                userSettingEntity.setModifyBy(userInfoEntity.getRealName());
                userSettingEntity.setModifyOn(DateUtils.getDate());
                userSettingRepository.create(userSettingEntity);
            } else {
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
            userAuthInfoRepository.create(userAuthInfoEntity);


            /* 查询用户有效登录信息 */
            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
            if (userLoginBookEntity != null) {
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
                if (houseProjectDto != null) {
                    HouseCompanyAdminDTO houseCompanyAdminDTO = houseCompanyService.getHouseCompanyAdminDTOById(houseProjectDto.getCompanyId());
                    if (houseCompanyAdminDTO != null) {
                        /* 登录日志 */
                        loginLogService.AddLoginLogManage(houseCompanyAdminDTO.getAreaId(), userSettingEntity.getProjectId(), userSettingEntity.getUserId(), "192.168.0.1", userInfoEntity.getUserName() + "登录成功了！", registerParamJsonDTO.getForm());
                        /* 设备日志 */
                        equipStatisticsService.addEquipManage(userSettingEntity.getProjectId(), houseProjectDto.getCompanyId(), houseCompanyAdminDTO.getAreaId(), registerParamJsonDTO.getForm(), userInfoEntity.getUserId());
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("---------->>Create login log exception.");
            }

            return new SuccessApiResult(userLoginInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0003
     * Type:UI Method
     * Describe:重置密码
     * CreateBy:Tom
     * CreateOn:2016-01-19 03:38:11
     */
    @Override
    public ApiResult resetPassword(ResetPasswordJsonDTO resetPasswordJsonDTO) throws GeneralException {
        try {
            if (resetPasswordJsonDTO == null) {
                return new ErrorApiResult("error_00000003");
            }
            ApiResult checkResult = resetPasswordJsonDTO.check();
            if (checkResult instanceof ErrorApiResult) {
                return checkResult;
            }

            /* 手机短信验证码 */
            Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(resetPasswordJsonDTO.getPhone()
                    , resetPasswordJsonDTO.getAuthCode()
                    , SMSAuthEntity.TYPE_PASSWORD_R);
            if (!checkAuthCode) {
                return new ErrorApiResult("tip_00000467");
            }

            /* 获取用户信息 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(resetPasswordJsonDTO.getPhone());
            if (userInfoEntity == null) {
                return new ErrorApiResult("tip_00000553");
            }
            if (StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_VISITOR)) {
                return new ErrorApiResult("tip_00000553");
            }
            userInfoEntity.resetPassword(DigestUtils.md5Hex(resetPasswordJsonDTO.getPassword()));
            userInfoRepository.update(userInfoEntity);

            if (null != userInfoEntity.getId() && !"".equals(userInfoEntity.getId())) {
                UserCRMEntity userCRM = userCRMRepository.getByMemberId(userInfoEntity.getId());
                if (userCRM != null) {
                    userCRM.setPassword(userInfoEntity.getPassword());
                    userCRM.setModifyBy(userInfoEntity.getModifyBy());
                    userCRM.setModifyOn(userInfoEntity.getModifyOn());
                    userCRMRepository.update(userCRM);
                }
                AccountCRMEntity accountCRM = accountCRMRepository.get(null, userInfoEntity.getId());
                if (accountCRM != null) {
                    accountCRM.setPassword(userInfoEntity.getPassword());
                    accountCRM.setModifyBy(userInfoEntity.getModifyBy());
                    accountCRM.setModifyOn(userInfoEntity.getModifyOn());
                    accountCRMRepository.update(accountCRM);
                }
                if (userCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, null, null);
                }
                if (userCRM != null && accountCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, accountCRM, null);
                }
            } else {
                UserCRMEntity userCRM = userCRMRepository.get(userInfoEntity.getUserId());
                if (userCRM != null) {
                    userCRM.setMobile(userInfoEntity.getMobile());
                    userCRM.setRealName(userInfoEntity.getRealName());
                    userCRM.setBirthday(userInfoEntity.getBirthday());
                    userCRM.setSex(String.valueOf(userInfoEntity.getSex()));
                    userCRM.setLogo(userInfoEntity.getLogo());
                }
                AccountCRMEntity accountCRM = accountCRMRepository.get(userInfoEntity.getUserId());
                if (accountCRM != null) {
                    accountCRM.setPassword(userInfoEntity.getPassword());
                    accountCRM.setModifyBy(userInfoEntity.getModifyBy());
                    accountCRM.setModifyOn(userInfoEntity.getModifyOn());
                    accountCRMRepository.update(accountCRM);
                }
                if (userCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, null, null);
                }
                if (userCRM != null && accountCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, accountCRM, null);
                }
            }

            return new SuccessApiResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("抱歉，发生了异常");
        }
    }

    /**
     * Code:UI0004
     * Type:UI Method
     * Describe:我的首页获取用户信息。
     * CreateBy:Tom
     * CreateOn:2016-01-14 10:13:44
     */
    @Override
    public ApiResult getUserInfoForHome(UserInfoEntity userInfoEntity) throws GeneralException {

        try {

            /* 获取用户配置信息 */
            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
            if (userInfoEntity == null) {
                return new ErrorApiResult("tip_00000554");
            }

            UserReturnJsonDTO userInfo = mapper.map(userInfoEntity, UserReturnJsonDTO.class);
            userInfo.setProjectId(userSettingEntity.getProjectId());
            userInfo.setProjectName(userSettingEntity.getProjectName());
            /* 获取地址信息 */
            List<HouseUserAdminDTO> houseUserAdminDTOList = houseUserBookService.getHouseUserAdminListByUserIdAndProjectId(userInfoEntity.getUserId()
                    , userSettingEntity.getProjectId());
            if (houseUserAdminDTOList.size() > 0) {
                userInfo.setAddress(houseUserAdminDTOList.get(0).getAddress());
            }

            return new SuccessApiResult(userInfo);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0005
     * Type:UI Method
     * Describe:修改用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:09:48
     */
    @Override
    public ApiResult modifyUserInfo(ModifyUserInfoParamJsonDTO userInfoJsonDTO) throws GeneralException {
        try {
            if (userInfoJsonDTO == null) {
                return new ErrorApiResult("error_00000003");
            }
            ApiResult checkResult = userInfoJsonDTO.check();
            if (checkResult instanceof ErrorApiResult) {
                return checkResult;
            }

            userInfoRepository.update(userInfoJsonDTO.toUserInfoEntity());
            return new SuccessApiResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0006
     * Type:UI Method
     * Describe:查询房产下成员信息
     * CreateBy:Tom
     * CreateOn:2016-01-20 05:36:19
     */
    @Override
    public ApiResult getUserInfoList(String userId, String houseId) throws GeneralException {

        try {
            if (StringUtil.isEmpty(houseId)) {
                return new ErrorApiResult("error_00000003");
            }

            HouseInfoAdminDTO houseInfoAdminDTO = houseInfoService.getHouseInfoEntityByHouseId(houseId);
            if (houseInfoAdminDTO == null) {
                return new ErrorApiResult("tip_00000550");
            }

            /* 查询房产下所有用户 */
            ApiResult userResult = houseUserBookService.getHouseUserBookListByHouseId(houseId);
            if (userResult instanceof ErrorApiResult) {
                return userResult;
            }
            List<HouseUserBookEntity> houseUserBookEntityList = (List<HouseUserBookEntity>) userResult.get("data");

            List<HouseUserJsonDTO> familyList = new ArrayList<HouseUserJsonDTO>();
            List<HouseUserJsonDTO> tenantList = new ArrayList<HouseUserJsonDTO>();
            familyList.add(new HouseUserJsonDTO("我"));

            for (HouseUserBookEntity houseUserBookEntity : houseUserBookEntityList) {

                UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserBookEntity.getUserId());
                if (userInfoEntity == null) {
                    continue;
                }
                if (StringUtil.isEqual(houseUserBookEntity.getUserId(), userId)) {
                    continue;
                }

                if (StringUtil.isEqual(houseUserBookEntity.getUserType(), HouseUserBookEntity.USER_TYPE_TENANT)) {
                    tenantList.add(new HouseUserJsonDTO(userInfoEntity.getRealName()));
                } else {
                    familyList.add(new HouseUserJsonDTO(userInfoEntity.getRealName()));
                }

            }

            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("familyList", familyList);
            modelMap.addAttribute("tenantList", tenantList);
            modelMap.addAttribute("address", houseInfoAdminDTO.getAddress());
            return new SuccessApiResult(modelMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0007
     * Type:UI Method
     * Describe:我的授权列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 06:20:39
     */
    @Override
    public ApiResult getHouseUserList(String userId) throws GeneralException {
        try {
            List<MyHouseUserJsonDTO> myHouseUserJsonDTOList = new ArrayList<MyHouseUserJsonDTO>();

            /* 查询用户基础配置 */
            UserSettingEntity userSettingEntity = userSettingRepository.get(userId);
            if (userSettingEntity == null) {
                return new ErrorApiResult("error_00000003");
            }

            /* 获取用户项目下所有房产成员信息 */
            List<HouseUserAdminDTO> houseUserAdminDTOList = houseUserBookService.getHouseUserAdminListByUserIdAndProjectId(userId, userSettingEntity.getProjectId());
            for (HouseUserAdminDTO houseUserAdminDTO : houseUserAdminDTOList) {
                if (StringUtil.isEqual(houseUserAdminDTO.getUserType(), HouseUserBookEntity.USER_TYPE_OWNER)) {
                    continue;
                }

                /* 查询用户信息 */
                UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserAdminDTO.getUserId());
                if (userInfoEntity == null) {
                    continue;
                }
                myHouseUserJsonDTOList.add(new MyHouseUserJsonDTO(houseUserAdminDTO, userInfoEntity));
            }

            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("list", myHouseUserJsonDTOList);
            return new SuccessApiResult(modelMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0008
     * Type:UI Method
     * Describe:删除授权
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:38:32
     */
    @Override
    public ApiResult deleteHouseUserByHouseId(String userId, String houseUserId) throws GeneralException {
        try {
            if (StringUtil.isEmpty(houseUserId)) {
                return new ErrorApiResult("error_00000003");
            }

            /* 设置房产成员关系无效 */
            ApiResult apiResult = houseUserBookService.deleteHouseUserBook(userId, houseUserId);
            if (apiResult instanceof ErrorApiResult) {
                return apiResult;
            }
            HouseUserBookEntity houseUserBookEntity = (HouseUserBookEntity) apiResult.get("data");

            /* 获取用户当前最高状态 */
            String userType = houseUserBookService.getUserType(houseUserBookEntity.getUserId(), houseUserBookEntity.getHouseId());

            /* 设置解除用户类型 */
            UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserBookEntity.getUserId());
            if (userInfoEntity != null) {
                userInfoEntity.resetUserType(userType);
                userInfoRepository.update(userInfoEntity);
            }

            return new SuccessApiResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0009
     * Type:UI Method
     * Describe:新增授权
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:48:53
     */
    @Override
    public ApiResult createHouseUser(CreateHouseUserJsonDTO createHouseUserJsonDTO) throws GeneralException {
        try {

            ApiResult checkResult = createHouseUserJsonDTO.check();
            if (checkResult instanceof ErrorApiResult) {
                return checkResult;
            }

            /* 判断业主 */
            Boolean checkOwner = houseUserBookService.checkOwner(createHouseUserJsonDTO.getUserInfoEntity().getUserId()
                    , createHouseUserJsonDTO.getHouseId());
            if (!checkOwner) {
                return new ErrorApiResult("tip_00000555");
            }

            /* 获取用户，不存在则创建新用户 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(createHouseUserJsonDTO.getMobile());
            if (userInfoEntity == null) {
                userInfoEntity = createHouseUserJsonDTO.toUserInfoEntity();
                userInfoRepository.create(userInfoEntity);

                /* 获取房产项目 */
                HouseInfoAdminDTO houseInfoAdminDTO = houseInfoService.getHouseInfoEntityByHouseId(createHouseUserJsonDTO.getHouseId());
                if (houseInfoAdminDTO == null) {
                    return new ErrorApiResult("tip_00000141");
                }

                /* 增加用户默认配置 */
                UserSettingEntity userSettingEntity = new UserSettingEntity();
                userSettingEntity.setUserId(userInfoEntity.getUserId());
                userSettingEntity.setProjectId(houseInfoAdminDTO.getProjectId());
                userSettingEntity.setProjectName(houseInfoAdminDTO.getProjectName());
                userSettingEntity.setCreateBy(userInfoEntity.getRealName());
                userSettingEntity.setCreateOn(DateUtils.getDate());
                userSettingEntity.setModifyBy(userInfoEntity.getRealName());
                userSettingEntity.setModifyOn(DateUtils.getDate());
                userSettingRepository.create(userSettingEntity);

            } else {
                /* 判断用户在房产下是否存在 */
                ApiResult checkUserResult = houseUserBookService.getHouseUserBookByUserIdAndHouseId(userInfoEntity.getUserId()
                        , createHouseUserJsonDTO.getHouseId());
                if (checkUserResult instanceof ErrorApiResult) {
                    return checkUserResult;
                }
                HouseUserBookEntity houseUserBookEntity = (HouseUserBookEntity) checkUserResult.get("data");
                if (houseUserBookEntity != null) {
                    return new ErrorApiResult("tip_00000061");
                }

                /* 如果存在用户，并且不是业主、家属，则进行数据升级 */
                if (!StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_FAMILY)
                        && !StringUtil.isEqual(userInfoEntity.getUserType(), UserInfoEntity.USER_TYPE_OWNER)) {
                    userInfoEntity.setBestUserType(createHouseUserJsonDTO.getRole());
                    userInfoRepository.update(userInfoEntity);
                }

            }

            /* 增加房屋业主关系 */
            HouseUserBookEntity houseUserBookEntity = new HouseUserBookEntity();
            houseUserBookEntity.setId(IdGen.uuid());
            houseUserBookEntity.setUserId(userInfoEntity.getUserId());
            houseUserBookEntity.setHouseId(createHouseUserJsonDTO.getHouseId());
            houseUserBookEntity.setState(HouseUserBookEntity.STATE_NORMAL);
            houseUserBookEntity.setUserType(createHouseUserJsonDTO.getRole());
            if (createHouseUserJsonDTO.getIsPay()) {
                houseUserBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_YES);
            } else {
                houseUserBookEntity.setIsPay(HouseUserBookEntity.IS_PAY_NO);
            }
            houseUserBookEntity.setCreateBy(userInfoEntity.getRealName());
            houseUserBookEntity.setCreateOn(DateUtils.getDate());
            houseUserBookEntity.setModifyBy(userInfoEntity.getRealName());
            houseUserBookEntity.setModifyOn(DateUtils.getDate());
            houseUserBookService.createHouseUserBook(houseUserBookEntity);

            return new SuccessApiResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0010
     * Type:UI Method
     * Describe:新增意见反馈
     * CreateBy:Tom
     * CreateOn:2016-01-21 06:11:42
     */
    @Override
    public ApiResult createFeedback(FeedbackParamJsonDTO feedbackParamJsonDTO, UserInfoEntity userInfoEntity) throws GeneralException {
        String visit = feedbackParamJsonDTO.getVisit();
        try {
            ApiResult checkResult = feedbackParamJsonDTO.check();
            if (checkResult instanceof ErrorApiResult) {
                return checkResult;
            }
            /* 获取默认项目 */
            //UserSettingEntity userSettingEntity = userSettingRepository.get(feedbackParamJsonDTO.getUserInfoEntity().getUserId());
            String projectId = "";
            String cityId = "";
            /* 新增意见反馈 */
            UserFeedbackEntity userFeedbackEntity = new UserFeedbackEntity();
            if (feedbackParamJsonDTO != null) {

                userFeedbackEntity.setId(IdGen.uuid());
                userFeedbackEntity.setUserId(userInfoEntity.getUserId());
                userFeedbackEntity.setContent(feedbackParamJsonDTO.getContent());
                userFeedbackEntity.setMobile(userInfoEntity.getMobile());
                userFeedbackEntity.setCreateBy(userInfoEntity.getUserId());
                userFeedbackEntity.setCreateOn(new Date());
                userFeedbackEntity.setModifyBy(userInfoEntity.getUserId());
                userFeedbackEntity.setModifyOn(new Date());
                userFeedbackEntity.setState("1");       //未处理
//                if(!StringUtil.isEmpty(feedbackParamJsonDTO.getProjectId())) {
//                    HouseInfoEntity houseInfo=houseInfoRepository.get(feedbackParamJsonDTO.getProjectId());
//                    if(houseInfo!=null) {
//                        userFeedbackEntity.setProjectId(houseInfo.getProjectId());
//                        userFeedbackEntity.setProjectName(houseInfo.getProjectName());
//                        userFeedbackEntity.setAddress(houseInfo.getAddress());
//                        projectId=houseInfo.getProjectId();
//                    }
//                }

                /* 补充意见反馈项目查询 */
                String roomId = feedbackParamJsonDTO.getProjectId();
                if (null != roomId && !"".equals(roomId)) {
                    //意见反馈选择了房产,查询该房产的项目
                    List<Map<String, Object>> projectList = houseProjectRepository.getProjectByRoom(roomId);
                    if (projectList.size() > 0) {
                        Map<String, Object> projectMap = projectList.get(0);
                        userFeedbackEntity.setProjectId(projectMap.get("projectId").toString());
                        userFeedbackEntity.setProjectName(projectMap.get("projectName").toString());
                    } else {
                        //未查询到房产对应项目信息,查询默认项目
                        UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
                        if (null != userSettingEntity) {
                            userFeedbackEntity.setProjectId(userSettingEntity.getPinyinCode());
                            userFeedbackEntity.setProjectName(userSettingEntity.getProjectName());
                        }
                    }
                } else {
                    //意见反馈未返回房产,查询默认项目
                    UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
                    if (null != userSettingEntity) {
                        userFeedbackEntity.setProjectId(userSettingEntity.getPinyinCode());
                        userFeedbackEntity.setProjectName(userSettingEntity.getProjectName());
                    }
                }
                /* **************** */
                userFeedbackEntity.setFeedBackType(feedbackParamJsonDTO.getType());         //来源:1金茂会员 2金茂质检 3微信
                userFeedbackEntity.setMemo("2");        //标示:意见反馈
                if (visit.equals("opinion")) {
                    userFeedbackEntity.setFbClassification("2");//意见反馈来源分类 2:意见反馈
                } else {
                    userFeedbackEntity.setFbClassification("1");//意见反馈来源分类 1:便民信息纠错
                }
                userFeedbackEntity.setUserType(userInfoEntity.getUserType());
                userFeedbackRepository.create(userFeedbackEntity);
            }
            //调用单据统计
            String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            //反馈统计已存在项目
            InvoicesTotalEntity invoices = invoicesTotalRepository.getTotalInfo(dateNow, projectId);
            //如果存在，更新反馈数量
            if (invoices != null) {
                if (!StringUtil.isEmpty(projectId)) {
                    invoices.setFeedbackTotal(invoices.getFeedbackTotal() + 1);
                    //invoices.setCity(cityId);
                } else {
                    invoices.setFeedbackNum(invoices.getFeedbackNum() + 1);
                }
                invoicesTotalRepository.update(invoices);
            } else {//没有则创建
                InvoicesTotalEntity invoicesTotal = new InvoicesTotalEntity();
                invoicesTotal.setId(IdGen.uuid());
                invoicesTotal.setCreateDate(new Date());
                if (!StringUtil.isEmpty(projectId)) {
                    //invoicesTotal.setCity(cityId);
                    //invoicesTotal.setProject(projectId);
                    invoicesTotal.setFeedbackTotal(1);
                    invoicesTotal.setRepairNum(0);
                    invoicesTotal.setFeedbackNum(0);
                    invoicesTotal.setPaymentNum(0);
                    invoicesTotal.setVisitorNum(0);
                } else {
                    invoicesTotal.setFeedbackNum(1);
                    invoicesTotal.setFeedbackTotal(0);
                    invoicesTotal.setRepairNum(0);
                    invoicesTotal.setPaymentNum(0);
                    invoicesTotal.setVisitorNum(0);
                }
                invoicesTotalRepository.create(invoicesTotal);
            }
            //调用点击人统计
            ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "10", userInfoEntity.getUserId());
            if (clickUserEntity == null) {
                ClickUsersEntity clickUser = new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userInfoEntity.getUserId());
                clickUser.setForeignId("10");
                clickUserRepository.create(clickUser);
            } else {
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }

            /* 新增图片 */
            /*List<UserImageEntity> userImageEntityList = feedbackParamJsonDTO.toUserImageList(userFeedbackEntity.getId());
            if(userImageEntityList != null){
                userImageRepository.create(userImageEntityList);
            }*/

            if (visit.equals("opinion")) {
                //增加日志
                UserDocumentsEntity userDocumentsLog = new UserDocumentsEntity();
                userDocumentsLog.setLogId(IdGen.uuid());
                userDocumentsLog.setLogTime(new Date());//注册日期
                userDocumentsLog.setUserName(userInfoEntity.getNickName());//用户昵称
                userDocumentsLog.setUserType(userInfoEntity.getUserType());//用户类型
                userDocumentsLog.setUserMobile(userInfoEntity.getMobile());//手机号
                userDocumentsLog.setSourceFrom(userInfoEntity.getSourceType());//来源
                userDocumentsLog.setThisType("意见反馈");//单据类型
                userDocumentsLog.setDocNum(userFeedbackEntity.getId());//单据编号
                userDocumentsLog.setLogContent(userFeedbackEntity.getContent());//单据内容
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
                HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseByMemberId(userInfoEntity.getId());
                if (null != userSettingEntity) {
                    userDocumentsLog.setProjectId(userSettingEntity.getProjectName());
                } else {
                    userDocumentsLog.setProjectId("");//项目
                }
                if (null != houseInfoEntity) {
                    userDocumentsLog.setRealEstate(houseInfoEntity.getAddress());
                }
                systemLogRepository.addUserDocumentsLog(userDocumentsLog);
            }
            if (visit.equals("error")) {
                UserDocumentsEntity userDocumentsLog = new UserDocumentsEntity();
                userDocumentsLog.setLogId(IdGen.uuid());
                userDocumentsLog.setLogTime(new Date());//注册日期
                userDocumentsLog.setUserName(userInfoEntity.getNickName());//用户昵称
                userDocumentsLog.setUserType(userInfoEntity.getUserType());//用户类型
                userDocumentsLog.setUserMobile(userInfoEntity.getMobile());//手机号
                userDocumentsLog.setSourceFrom(userInfoEntity.getSourceType());//来源
                userDocumentsLog.setThisType("便民信息-我要纠错");//单据类型
                userDocumentsLog.setDocNum(userFeedbackEntity.getId());//单据编号
                userDocumentsLog.setLogContent(userFeedbackEntity.getContent());//单据内容
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
                HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseByMemberId(userInfoEntity.getId());
                if (null != userSettingEntity) {
                    userDocumentsLog.setProjectId(userSettingEntity.getProjectName());
                } else {
                    userDocumentsLog.setProjectId("");//项目
                }
                if (null != houseInfoEntity) {
                    userDocumentsLog.setRealEstate(houseInfoEntity.getAddress());
                }

                systemLogRepository.addUserDocumentsLog(userDocumentsLog);
            }
            return new SuccessApiResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0011
     * Type:UI Method
     * Describe:修改密码
     * CreateBy:Tom
     * CreateOn:2016-01-25 07:23:00
     */
    @Override
    public ApiResult modifyPassword(ModifyPassword modifyPassword) throws GeneralException {
        try {
            ApiResult checkResult = modifyPassword.check();
            if (checkResult instanceof ErrorApiResult) {
                return checkResult;
            }

            userInfoRepository.update(modifyPassword.toUserInfoEntity());
            return new SuccessApiResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * Code:UI0012
     * Type:UI Method
     * Describe:返回指定授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 11:36:23
     */
    @Override
    public ApiResult getHouseUserByHouseUserId(String houseUserId) {
        if (StringUtil.isEmpty(houseUserId)) {
            return new ErrorApiResult("tip_00000060");
        }
        HouseUserReturnJsonDTO houseUserInfo = new HouseUserReturnJsonDTO();
        HouseUserBookAdminDTO houseUserBookAdminDTO = houseUserBookService.getHouseUserBookAdminDTOByHouseUserId(houseUserId);
        if (houseUserBookAdminDTO == null) {
            return new ErrorApiResult("tip_UI000001");
        }
        if (!houseUserBookAdminDTO.isNormal()) {
            return new ErrorApiResult("tip_UI000002");
        }
        UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserBookAdminDTO.getUserId());
        if (userInfoEntity == null) {
            return new ErrorApiResult("tip_UI000001");
        }

        houseUserInfo.setId(houseUserBookAdminDTO.getId());
        houseUserInfo.setAddress(houseUserBookAdminDTO.getAddress());
        houseUserInfo.setMobile(userInfoEntity.getMobile());
        houseUserInfo.setRealName(userInfoEntity.getRealName());
        houseUserInfo.setRole(houseUserBookAdminDTO.getUserType());
        houseUserInfo.setIsPay(houseUserBookAdminDTO.isPayment());

        return new SuccessApiResult(houseUserInfo);
    }

    /**
     * Code:UI0013
     * Type:UI Method
     * Describe:修改授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 01:58:49
     */
    @Override
    public ApiResult modifyHouseUser(ModifyHouseUserJsonDTO houseUserInfo) {

        ApiResult checkResult = houseUserInfo.check();
        if (checkResult instanceof ErrorApiResult) {
            return checkResult;
        }

        HouseUserBookAdminDTO houseUserBookAdminDTO = houseUserBookService.getHouseUserBookAdminDTOByHouseUserId(houseUserInfo.getId());
        if (houseUserBookAdminDTO == null) {
            return new ErrorApiResult("tip_UI000001");
        }
        if (!houseUserBookAdminDTO.isNormal()) {
            return new ErrorApiResult("tip_UI000002");
        }

        /* 修改授权信息 */
        houseUserBookAdminDTO.setUserType(houseUserInfo.getRole());
        houseUserBookAdminDTO.setIsPay(HouseUserBookEntity.IS_PAY_NO);
        if (houseUserInfo.getIsPay()) {
            houseUserBookAdminDTO.setIsPay(HouseUserBookEntity.IS_PAY_YES);
        }
        ApiResult houseUserResult = houseUserBookService.updateHouseUserBook(houseUserBookAdminDTO);
        if (houseUserResult instanceof ErrorApiResult) {
            return houseUserResult;
        }

        /* 修改用户信息 */
        UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserBookAdminDTO.getUserId());
        if (userInfoEntity == null) {
            return new ErrorApiResult("tip_UI000001");
        }

        /* 获取用户当前最高状态 */
        String userType = houseUserBookService.getUserType(userInfoEntity.getUserId(), houseUserInfo.getId());
        userType = userInfoEntity.getBestType(userType, houseUserInfo.getRole());
        userInfoEntity.setRealName(houseUserInfo.getName());
        userInfoEntity.setBestUserType(userType);
        userInfoRepository.update(userInfoEntity);

        return new SuccessApiResult();
    }

    /**
     * Code:
     * Type:UI Method
     * Describe:微信入会与App入会同一个逻辑，为什么不用一个接口？
     * CreateBy:
     * CreateOn:
     * ModifyBy:tom
     * ModifyOn:2016-7-16 03:48:50
     * ModifyDescribe:微信入会，userCRM不修改身份证、memberID、真实姓名。
     */
    @Override
    public ApiResult wechatAddUser(QualicationDTO qualicationDTO, UserInfoEntity userInfo, UserTokenEntity userToken) {

        qualicationDTO.setNickName(qualicationDTO.getMobileNumber());//将手机号作为昵称
        if (userToken == null) {
            return new ErrorApiResult(20201, "信息不完整，请重新刷新后操作！");
        }

        //1、判断基础参数
        if (qualicationDTO == null) {
            return new ErrorApiResult(20201, "注册信息不正确");
        }
        if (userInfo == null) {
            return new ErrorApiResult(20201, "请先关注微信号");
        }
        if (StringUtil.isEmpty(userInfo.getUserId())) {
            return new ErrorApiResult(20201, "请先关注微信号");
        }
        //验证短信验证码
        Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(qualicationDTO.getMobileNumber()
                , qualicationDTO.getPhoneCode()
                , SMSAuthEntity.TYPE_REGISTER);
        if (!checkAuthCode) {
            return new ErrorApiResult("tip_00000467");
        }
        if (StringUtil.isEmpty(qualicationDTO.getMobileNumber())) {
            return new ErrorApiResult(20201, "手机号为空");
        }
        if (StringUtil.isEmpty(qualicationDTO.getPassWord())) {
            return new ErrorApiResult(20201, "密码为空");
        }
//            if (StringUtil.isEmpty(qualicationDTO.getNickName()))
//            {
//                return new ErrorApiResult(20201, "昵称为空");
//            }
//            if(qualicationDTO.getNickName().length()>14){
//                return new ErrorApiResult(20201,"昵称不能超过14个字符");
//            }
        if (qualicationDTO.getMobileNumber().length() != 11) {
            return new ErrorApiResult(20201, "请您输入有效的11位手机号码…");
        }

        /**
         * @author chenning
         *验证用户昵称的的唯一性
         */
        UserInfoEntity ckUser = userInfoRepository.getUserByNickName(qualicationDTO.getNickName());
        if (ckUser != null) {
            if (!StringUtil.isEqual(ckUser.getUserId(), userToken.getUserId())) {
                return new ErrorApiResult(20201, "该昵称已经被注册");
            }
        }

        /**
         * （验证的是该手机该渠道是否已经注册过）---start
         */
        //当验证到系统有此手机注册的用户(只有注册过的用户才有手机号)
        UserInfoEntity checkUser = userInfoRepository.getByMobile(qualicationDTO.getMobileNumber());
        if (checkUser != null) {
            return new ErrorApiResult(20201, "此手机已注册");
        } else {  // 该用户没有在系统中有任何渠道执行3个动作：1、调整默认游客用户为注册用户；2、为用户创建请求的渠道；3、清除原游客绑定关系（openid或者pcno）
//            1、调整默认游客用户为注册用户；
            //添加基本数据
            userInfo.setPassword(qualicationDTO.getPassWord());//密码
            userInfo.setNickName(qualicationDTO.getNickName());//昵称
            userInfo.setMobile(qualicationDTO.getMobileNumber());//手机
            userInfo.setUserName(userInfo.getMobile());//账号
            userInfo.setUserState(1);//用户状态
            userInfo.setUserType("2");//用户类型
            userInfo.setLogo(AppConfig.UserDefaultLogo);//用户默认头像
            userInfo.setCreateOn(new Date());//创建时间
            userInfo.setModifyBy(userInfo.getMobile());//修改人
            userInfo.setIdCard(qualicationDTO.getIdCard());//身份证号
            userInfo.setRegisterDate(new Date());
            userInfo.setJump("1");
            userInfo.setSourceType(UserInfoEntity.USER_TYPE_SOURCE_WEIXIN);
            userInfo.setSex(1);
            userInfo.setOperatingSystem(qualicationDTO.getOs());//操作系统
//                if(qualicationDTO.getIdCard()!=null&&!"".equals(qualicationDTO.getIdCard())){  //如果用户填了身份证号 则需要进行业主认证
//                    userInfo.setJump("2");
//                    if(userCRMRepository.checkOwner(qualicationDTO.getMobileNumber(),qualicationDTO.getIdCard())){  //业主认证成功 则变更为业主类型
//                        UserCRMEntity userCRMEntity = userCRMRepository.getOwner(qualicationDTO.getMobileNumber(),qualicationDTO.getIdCard());
//                        userInfo.setUserType("3");
//                        userInfo.setId(userCRMEntity.getMemberId());     //将CRM中业主ID绑定到当前用户下
//                        userInfo.setJump("4");
//                    }else {
//                        userInfo.setJump("3");
//                    }
//                }

            if(userToken.getOpenId() != null && !userToken.getOpenId().equals("")){
                /**
                 * 获取接口访问凭证
                 */
                String accessToken = WeixinUtil.getAccessToken(AppConfig.AppID,AppConfig.AppSecret).getToken();
                /**
                 * 获取用户信息
                 */
                WeixinUserInfo user = WeixinUtil.getUserInfo(accessToken, userToken.getOpenId());
                userInfo.setWC_nickName(user.getNickname());
            }

            userInfoRepository.update(userInfo);

            //调用用户统计
            String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            UserToTalEntity userToTal = userTotalRepository.getTotalInfo(dateNow);
            if (userToTal != null) {
                if ("2".equals(userInfo.getUserType())) {
                    userToTal.setCommonUser(userToTal.getCommonUser() + 1);//普通用户
                } else if ("3".equals(userInfo.getUserType())) {
                    userToTal.setOwnerUser(userToTal.getOwnerUser() + 1);//业主用户
                }
                if ("android".equals(qualicationDTO.getOs())) {
                    userToTal.setAndroid(userToTal.getAndroid() + 1);//安卓用户
                }
                if ("ios".equals(qualicationDTO.getOs())) {
                    userToTal.setIos(userToTal.getIos() + 1);//ios用户
                }
                userToTal.setWeChat(userToTal.getWeChat() + 1);//微信用户
                userTotalRepository.update(userToTal);
            } else {
                UserToTalEntity userToTalEntity = new UserToTalEntity();
                userToTalEntity.setId(IdGen.uuid());
                userToTalEntity.setCreateDate(new Date());
                if ("2".equals(userInfo.getUserType())) {
                    userToTalEntity.setCommonUser(1);//普通用户
                } else {
                    userToTalEntity.setCommonUser(0);//普通用户
                }
                if ("3".equals(userInfo.getUserType())) {
                    userToTalEntity.setOwnerUser(1);//业主用户
                } else {
                    userToTalEntity.setOwnerUser(0);//业主用户
                }
                if ("android".equals(qualicationDTO.getOs())) {
                    userToTalEntity.setAndroid(1);//安卓用户
                } else {
                    userToTalEntity.setAndroid(0);//安卓用户
                }
                if ("ios".equals(qualicationDTO.getOs())) {
                    userToTalEntity.setIos(1);//ios用户
                } else {
                    userToTalEntity.setIos(0);//ios用户
                }
                userToTalEntity.setWeChat(1);//微信用户
                userToTalEntity.setAPP(0);//APP用户
                userTotalRepository.create(userToTalEntity);
            }

// 2、为用户创建请求的渠道；
            //第一次使用第三方授权登录时 需在系统中为其生成对应用户
            UserLoginBookEntity oldbook = userLoginBookRepository.getLoginBookByuniodidAndUserid(userToken.getOpenId(), userInfo.getUserId());
            //注册编码为来源对应的编码
//            userloginbook.setLoginId(usertoken.getOpenId());   //登记为请求对应的设备编号（实现该设备与账户的默认绑定关系）

            //处理没有注册过微信账号的情况
            if (oldbook == null) {
                UserLoginBookEntity userloginbook = new UserLoginBookEntity();
                userloginbook.setUnionId(userToken.getOpenId());
                //手机注册用户  手机号与其注册的用户对应系统同一用户
                userloginbook.setUserId(userInfo.getUserId());
                userloginbook.setCreateBy("WC");
                userloginbook.setModifyBy("WC");
                userloginbook.setModifyOn(new Date());
                userloginbook.setState(UserLoginBookEntity.STATE_NORMAL);
                userloginbook.setCreateOn(new Date());
                userloginbook.setLoginType("CORE");   //这里创建的关系是核心关系 （只有注册用户才有此关系）
                userloginbook.setLoginId(userToken.getOpenId());   //登记为请求对应的设备编号（实现该设备与账户的默认绑定关系）
                userLoginBookRepository.create(userloginbook);
            } else {
                oldbook.setModifyOn(new Date());
                oldbook.setModifyBy("WC");
                userLoginBookRepository.update(oldbook);
            }
        }

        /**
         * （验证的是该手机该渠道是否已经注册过）---end
         */
        UserCRMEntity userCRM = userCRMRepository.getByMobile(userInfo.getMobile());
        AccountCRMEntity accountCRMEntity = accountCRMRepository.getByMobile(userInfo.getMobile());
        if (userCRM == null || accountCRMEntity == null) {
            //创建用户信息
            UserCRMEntity userCRMEntity = new UserCRMEntity();
            userCRMEntity.setUserId(userInfo.getUserId());
            userCRMEntity.setPassword(qualicationDTO.getPassWord());//密码
            userCRMEntity.setNickName(qualicationDTO.getNickName());//昵称
            userCRMEntity.setMobile(qualicationDTO.getMobileNumber());//手机
            userCRMEntity.setUserName(qualicationDTO.getMobileNumber());//账号
            userCRMEntity.setUserState(userInfo.getUserState());//用户状态
            userCRMEntity.setUserType(userInfo.getUserType());//用户类型
            userCRMEntity.setLogo(userInfo.getLogo());//用户默认头像
            userCRMEntity.setCreateOn(userInfo.getCreateOn());//创建时间
            userCRMEntity.setRegisterDate(new Date());//注册时间
            userCRMEntity.setModifyBy(userInfo.getMobile());//修改人
            userCRMEntity.setIdCard(userInfo.getIdCard());//身份证号
            //userCRMEntity.setMemberId(userInfo.getId());
            userCRMRepository.create(userCRMEntity);
            //创建账号信息
            AccountCRMEntity accountCRM = new AccountCRMEntity();
            accountCRM.setAccountId(userInfo.getUserId());
            accountCRM.setMobile(qualicationDTO.getMobileNumber());
            accountCRM.setMemberId(userInfo.getId());
            accountCRM.setNickName(qualicationDTO.getNickName());
            accountCRM.setPassword(qualicationDTO.getPassWord());
            accountCRM.setCreateBy(userInfo.getUserId());
            accountCRM.setCreateOn(new Date());
            accountCRMRepository.create(accountCRM);
            //调用webService接口,向CRM传送数据
            membershipRegisterService.userInfoRegister(accountCRM, userCRMEntity, null, null);
        } else {
            //更新用户信息
            userCRM.setPassword(qualicationDTO.getPassWord());//密码
            userCRM.setNickName(qualicationDTO.getNickName());//昵称
            userCRM.setMobile(qualicationDTO.getMobileNumber());//手机
            userCRM.setUserName(qualicationDTO.getMobileNumber());//账号
            userCRM.setUserState(userInfo.getUserState());//用户状态
            userCRM.setLogo(userInfo.getLogo());//用户默认头像
            userCRM.setModifyOn(new Date());//修改时间
            userCRM.setModifyBy(userInfo.getUserId());//修改人

            /********** modify by tom on 2016-7-16 03:48:50 begin **********/
//            userCRM.setIdCard(userInfo.getIdCard());//身份证号
//            userCRM.setUserType(userInfo.getUserType());//用户类型
            /********** modify by tom on 2016-7-16 03:48:50 end **********/
            //更新账号信息
            //accountCRMEntity.setAccountId(userInfo.getUserId());
            accountCRMEntity.setMobile(qualicationDTO.getMobileNumber());
            accountCRMEntity.setRegisterId(userCRM.getMemberId());
            accountCRMEntity.setNickName(qualicationDTO.getNickName());
            accountCRMEntity.setPassword(qualicationDTO.getPassWord());
            accountCRMEntity.setModifyBy(userInfo.getUserId());
            accountCRMEntity.setModifyOn(new Date());
            accountCRMRepository.update(accountCRMEntity);
            userCRMRepository.update(userCRM);
            //调用webService接口,向CRM传送数据
            membershipRegisterService.userInfoRegister(accountCRMEntity, userCRM, null, null);
        }

        //增加用户日志记录
        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setLogId(IdGen.uuid());
        systemLogEntity.setLogTime(new Date());//注册日期
        systemLogEntity.setUserName(userInfo.getNickName());//用户昵称
        systemLogEntity.setUserType(userInfo.getUserType());//用户类型
        systemLogEntity.setUserMobile(userInfo.getMobile());//手机号
        systemLogEntity.setIdCard(userInfo.getIdCard());//身份证
        systemLogEntity.setSourceFrom(userInfo.getSourceType());//来源
        systemLogEntity.setSysVersion(userInfo.getOperatingSystem());//系统版本
        if (userInfo != null) {
            HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseByMemberId(userInfo.getId());
            if (houseInfoEntity != null) {
                systemLogEntity.setProjectId(houseInfoEntity.getProjectName());//项目
            }
        } else {
            systemLogEntity.setProjectId("");//项目
        }
        systemLogRepository.addSysLog(systemLogEntity);
        //返回成功
        return new SuccessApiResult();
    }

    @Override
    public UserTokenEntity GetLoginByWeChatCode(String code) {
        System.out.println("-----通过code换取网页授权access_token-----");
//        2 第二步：通过code换取网页授权access_token
        ClientAccessToken clientAccessToken = WeixinUtil.authorizationUser(code);
        if (clientAccessToken == null) {
            return null;
        }
//        3 第三步：刷新access_token（如果需要）---在内部方法中刷新，此处不需要

//        4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        System.out.println("-----拉取用户信息(需scope为 snsapi_userinfo)-----");
        WeChatUser wechatuser = WeixinUtil.getWeChatUser(clientAccessToken);
        if (wechatuser == null) {
            return null;
        }
//        5 附：检验授权凭证（access_token）是否有效
        /**
         * 第六步  与系统用户匹配--------start
         */

        System.out.println("-----判断该openid是否已经创建过用户-----");
        //6-1 判断该openid是否已经创建过用户

        //2016-04-13---屏蔽unionid方式-------Start
//        UserInfoEntity reuser = userInfoRepository.GetUserByUnionCode("WC", wechatuser.getUser_unionid());  //调整为使用 unionid识别
        //2016-04-13---屏蔽unionid方式-------end
        //2016-04-13---使用openid方式-------Start
        UserInfoEntity reuser = userInfoRepository.GetUserByUnionCode("WC", wechatuser.getUser_openid());  //调整为使用 unionid识别
        //2016-04-13---使用openid方式-------end
        //第一次使用第三方授权登录时 需在系统中为其生成对应用户
        if (reuser == null) {
            UserLoginBookEntity userloginbook = new UserLoginBookEntity();
            userloginbook.setCreateOn(new Date());
            userloginbook.setCreateBy("auto");
            userloginbook.setState(UserLoginBookEntity.STATE_NORMAL);
            userloginbook.setModifyBy("auto");
            userloginbook.setModifyOn(new Date());
            userloginbook.setLoginId(wechatuser.getUser_openid());   //只用来支付使用
            //2016-04-13屏蔽unionid方式---start
//            userloginbook.setUnionId(wechatuser.getUser_unionid());
            //2016-04-13屏蔽unionid方式---end
            //2016-04-13使用openid方式---start
            userloginbook.setUnionId(wechatuser.getUser_openid());
            //2016-04-13使用openid方式---end
            userloginbook.setUserId(Long.toString(System.currentTimeMillis()));
            userloginbook.setLoginType("WC");
            userloginbook.setBusinessId("");
            userLoginBookRepository.create(userloginbook);
            reuser = userInfoRepository.AddNewWechatUser(userloginbook.getUserId());
//            reuser.setNickName(wechatuser.getUser_nickname());
            reuser.setSex(Integer.parseInt(wechatuser.getUser_sex()));  //1--男   0--女
            reuser.setWC_nickName(wechatuser.getUser_nickname());//微信昵称
            //微信昵称过滤 Wyd
            reuser.setWC_nickName(filterEmoji(reuser.getWC_nickName()));
            userInfoRepository.update(reuser);  //实际是执行了更新操作
        } else {
            reuser.setWC_nickName(wechatuser.getUser_nickname());//微信昵称
            //微信昵称过滤 Wyd
            reuser.setWC_nickName(filterEmoji(reuser.getWC_nickName()));
            userInfoRepository.update(reuser);  //实际是执行了更新操作
            //2016-04-13---屏蔽unionid方式-------start
            //更新 关系中openid 为当前微信的openid    20151115 chenrongjun
//            userLoginBookRepository.updateOpenIdInLoginBook(reuser.getUserId(),wechatuser.getUser_unionid(),wechatuser.getUser_openid());
            //2016-04-13---屏蔽unionid方式-------end
            //2016-04-13---使用openid方式-------start
            userLoginBookRepository.updateOpenIdInLoginBook(reuser.getUserId(), wechatuser.getUser_openid(), wechatuser.getUser_openid());
            //2016-04-13---使用openid方式-------end
        }

//        //6-2为本用户创建一个usertoken
//        UserToken userToken = userTokenRepository.getUserTokenByUserId(reuser.getUserId());
//        //当前如果没有可用token 则新建一个
//        if(userToken == null){

        System.out.println("-----新建一个token -----");
        UserTokenEntity userToken = new UserTokenEntity();
        userToken.setTokenId(CookieTools.getNewCookie());
        userToken.setUserId(reuser.getUserId());

//        userToken.setOpenId(wechatuser.getUser_openid());
        //2016-04-13屏蔽unionid方式---start
//        userToken.setOpenId(wechatuser.getUser_unionid());
        //2016-04-13屏蔽unionid方式---end
        //2016-04-13使用openid方式---start
        userToken.setOpenId(wechatuser.getUser_openid());
        //2016-04-13使用openid方式---end
        userToken.setIsActive("1");  //1  有效  2  无效
        userToken.setCreateBy("WC");
        userToken.setCreateDate(SqlDateUtils.getDate());
        userToken.setCreateTime(SqlDateUtils.getTime());

        userTokenRepository.Add(userToken);

        reuser.setWC_nickName(wechatuser.getUser_nickname());//微信昵称
        userInfoRepository.update(reuser);  //实际是执行了更新操作

        return userToken;
    }

    @Override
    public UserTokenEntity GetUserTokenByID(String vestaToken) {
        String tokenCheckFlag = systemConfigRepository.get("tokenCheckFlag").getConfigValue();
        if ("Y".equals(tokenCheckFlag)) {
            return userTokenRepository.GET(vestaToken);
        }
        //這是一個假的 usertoken
        UserTokenEntity userToken = new UserTokenEntity();

        userToken = userTokenRepository.GET("1");
        return userToken;
    }

    @Override
    public UserInfoEntity GetUserInfoByTokenValue(String vestatoken) {
        String tokenCheckFlag = systemConfigRepository.get("tokenCheckFlag").getConfigValue();
        if ("Y".equals(tokenCheckFlag)) {
            UserInfoEntity user = userInfoRepository.GetUserByToken(vestatoken);
            if (user != null) {
                //更新token过期时间
                userTokenRepository.Update(vestatoken);

                return user;
            }
            return null;
        }
        //這是一個假的 user
        UserInfoEntity testUser = new UserInfoEntity();

        testUser = userInfoRepository.get(systemConfigRepository.get("testUserId").getConfigValue());
        testUser.setPassword("");
        return testUser;
    }

    /**
     * Code:
     * Type:UI Method
     * Describe:微信认证与App认证 逻辑一样 为什么要用两个接口？？？？
     * CreateBy:
     * CreateOn:
     * ModifyBy:tom
     * ModifyOn:2016-7-16 02:41:47
     * ModifyDescribe:修复认证业主成功后，将userCRM身份证保存到userInfo中。
     */
    @Override
    public ApiResult authentication(QualicationDTO qualicationDTO, UserInfoEntity userInfoEntity) {
        //验证短信验证码
//        Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(qualicationDTO.getMobileNumber()
//                , qualicationDTO.getPhoneCode()
//                , SMSAuthEntity.TYPE_REGISTER);
//        if(!checkAuthCode) {
//            return new ErrorApiResult("tip_00000467");
//        }
        userInfoEntity.setJump("4");
        if (!userCRMRepository.checkOwner(qualicationDTO.getIdCard())) {
            //北京公司的项目
            String cityId = "68ff84c7-1380-e411-80c5-005056a61361";
            List<Object[]> projectList = announcementRepository.listProject(cityId);
            List<ProjectSelDTO> projectSelDTOList = new ArrayList<ProjectSelDTO>();
            for (Object[] obj : projectList) {
                ProjectSelDTO projectSelDTO = new ProjectSelDTO();
                projectSelDTO.setProjectId((String) obj[0]);
                projectSelDTO.setProjectName((String) obj[1]);
                projectSelDTOList.add(projectSelDTO);
            }
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("projectList", projectSelDTOList);
            userInfoEntity.setJump("3");
            modelMap.addAttribute("301", "认证失败！");
            return new SuccessApiResult(modelMap);
        }
        if (!userInfoRepository.checkIdCard(qualicationDTO.getIdCard())) {
            return new ErrorApiResult(301, "身份证号已认证过！");
        }

        UserCRMEntity userCRMEntity = userCRMRepository.getOwner(qualicationDTO.getIdCard());
        userInfoEntity.setId(userCRMEntity.getMemberId());           //将CRM的业主ID绑定到当前用户下
        userInfoEntity.setRealName(userCRMEntity.getRealName());
        /********** modify on 2016-7-16 02:41:47 by tom begin **********/
        userInfoEntity.setIdCard(qualicationDTO.getIdCard());       //身份证信息
        /********** modify on 2016-7-16 02:41:47 by tom end **********/
        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);  //认证成功 将用户类型变更为业主
        userInfoRepository.update(userInfoEntity);
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
        //调用用户统计 Wyd_2016-12-29
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
            if ("3".equals(userInfoEntity.getUserType())) {
                userToTalEntity.setOwnerUser(1);//业主用户
            } else {
                userToTalEntity.setOwnerUser(0);//业主用户
            }
            userTotalRepository.create(userToTalEntity);
        }
        return new SuccessApiResult();
    }

    @Override
    public ApiResult appeal(UserInfoEntity user, AppealDTO appealDTO) {
        //验证短信验证码
       /* Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(appealDTO.getMobile()
                , appealDTO.getPhoneCode()
                , SMSAuthEntity.TYPE_APPEAL);
        if(!checkAuthCode) {
            return new ErrorApiResult("tip_00000467");
        }*/
        if (!VerifyUtils.isMobile(appealDTO.getMobile())) {
            return new ErrorApiResult("tip_00000283");
        }
        UserFeedbackEntity userFeedbackEntity = new UserFeedbackEntity();
        userFeedbackEntity.setId(IdGen.uuid());
        userFeedbackEntity.setUserId(user.getUserId());
        userFeedbackEntity.setState("1");   //未处理
        userFeedbackEntity.setContent("姓名:" + appealDTO.getRealName() + "，证件号:" + appealDTO.getIdCard());
        userFeedbackEntity.setMobile(user.getMobile());
        userFeedbackEntity.setCreateBy(user.getUserId());
        userFeedbackEntity.setCreateOn(new Date());
        userFeedbackEntity.setModifyBy(user.getUserId());
        userFeedbackEntity.setModifyOn(new Date());
        userFeedbackEntity.setFeedBackType("3");    //1金茂会员 2金茂质检 3微信
        userFeedbackEntity.setMemo("1");    //类别：1为身份申诉、2为意见反馈
        userFeedbackEntity.setAddress(appealDTO.getAddress());
        userFeedbackEntity.setUserType(user.getUserType());

        if (appealDTO.getProjectId() != null && !appealDTO.getProjectId().equals("")) {
            HouseProjectEntity houseProjectEntity = houseProjectRepository.getProjectByCode(appealDTO.getProjectId());
            userFeedbackEntity.setProjectId(appealDTO.getProjectId());

            userFeedbackEntity.setProjectName(houseProjectEntity.getName());
        } else {
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
            if (null != userSettingEntity) {
                userFeedbackEntity.setProjectId(userSettingEntity.getPinyinCode());
                userFeedbackEntity.setProjectName(userSettingEntity.getProjectName());
            }
        }

        //发送短信消息提醒
        List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = smsAlertsService.getAllByModel("", "身份申诉管理");

        //同一个人，如果是不同项目，相同模块，选择一个，相同的name、phone、model
        for (int i=0; i<smsPeopleAlertsEntityList.size()-1; i++) {
            SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsPeopleAlertsEntityList.get(i);
            for (int j=smsPeopleAlertsEntityList.size()-1; j>i; j--) {
                SMSPeopleAlertsEntity smsPeopleAlertsEntity1 = smsPeopleAlertsEntityList.get(j);
                if ((smsPeopleAlertsEntity.getName()).equals(smsPeopleAlertsEntity1.getName()) && (smsPeopleAlertsEntity.getPhone()).equals(smsPeopleAlertsEntity1.getPhone()) && (smsPeopleAlertsEntity.getModel()).equals(smsPeopleAlertsEntity1.getModel())) {
                    smsPeopleAlertsEntityList.remove(smsPeopleAlertsEntity1);
                }
            }
        }
        for (SMSPeopleAlertsEntity smsPeopleAlertsEntity : smsPeopleAlertsEntityList) {
            smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+" ,"+userFeedbackEntity.getContent()+" ,手机号:"+userFeedbackEntity.getMobile()+"。");
            smsAuthService.sendSMSAlerts(smsPeopleAlertsEntity);
        }

        userFeedbackRepository.create(userFeedbackEntity);
        return new SuccessApiResult();
    }

    /**
     * Code:
     * Type:UI Method
     * Describe:会员App注册用户
     * CreateBy:
     * CreateOn:
     * ModifyBy:tom
     * ModifyOn:2016-7-15 13:55:40
     * ModifyDescribe:修复创建用户时，获取系统用户默认头像，赋值给用户。
     * ModifyBy:tom
     * ModifyOn:2016-7-16 02:35:58
     * ModifyDescribe:修改，创建用户时，userCRM中存在注册手机号，不进行修改userCRM的身份证号、用户类型
     */
    @Override
    public ApiResult appAddUser(QualicationDTO qualicationDTO) {
        qualicationDTO.setNickName(qualicationDTO.getMobileNumber());//将手机号作为昵称
        //1、判断基础参数
        if (qualicationDTO == null) {
            return new ErrorApiResult(20201, "注册信息不正确");
        }
        //验证码
        Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(qualicationDTO.getMobileNumber()
                , qualicationDTO.getPhoneCode()
                , SMSAuthEntity.TYPE_REGISTER);
        if (!checkAuthCode) {
            return new ErrorApiResult("tip_00000467");
        }
        if (StringUtil.isEmpty(qualicationDTO.getMobileNumber())) {
            return new ErrorApiResult(20201, "手机号为空");
        }
        if (StringUtil.isEmpty(qualicationDTO.getPassWord())) {
            return new ErrorApiResult(20201, "密码为空");
        }
//        if (StringUtil.isEmpty(qualicationDTO.getNickName()))
//        {
//            return new ErrorApiResult(20201, "昵称为空");
//        }
//        if(qualicationDTO.getNickName().length()>14){
//            return new ErrorApiResult(20201,"昵称不能超过14个字符");
//        }
        if (qualicationDTO.getMobileNumber().length() != 11) {
            return new ErrorApiResult(20201, "请您输入有效的11位手机号码…");
        }
        /**
         *验证用户昵称的的唯一性
         */
//        UserInfoEntity ckUser = userInfoRepository.getUserByNickName(qualicationDTO.getNickName());
//        if(ckUser !=null){
//            if(!"".equals(ckUser.getUserId())|| ckUser.getUserId()!= null){
//                return new ErrorApiResult(20201,"该昵称已经被注册");
//            }
//        }
        /**
         * （验证的是该手机该渠道是否已经注册过）
         */
        //当验证到系统有此手机注册的用户(只有注册过的用户才有手机号)
        UserInfoEntity checkUser = userInfoRepository.getByMobile(qualicationDTO.getMobileNumber());
        if (checkUser != null) {
            return new ErrorApiResult(20201, "此手机已注册");
        }

        //添加基本数据
        UserInfoEntity userInfo = new UserInfoEntity();
        String newUserId = Long.toString(System.currentTimeMillis());
        userInfo.setUserId(newUserId);
        userInfo.setPassword(qualicationDTO.getPassWord());//密码
        userInfo.setNickName(qualicationDTO.getNickName());//昵称
        userInfo.setMobile(qualicationDTO.getMobileNumber());//手机
        userInfo.setUserName(qualicationDTO.getMobileNumber());//账号
        userInfo.setUserState(1);//用户状态
        userInfo.setUserType("2");//用户类型
        /********** modify by tom 2016-7-15 13:58:12 begin **********/
        /* old code begin */
        //userInfo.setLogo(AppConfig.UserDefaultLogo);//用户默认头像
        /* old code end */
        DefaultConfigEntity defaultConfigEntity = defaultConfigService.queryDefaultConfig(DefaultConfigEntity.CONFIG_TYPE_DEFAULT_USER_LOGO);
        if (defaultConfigEntity != null) {
            userInfo.setLogo(defaultConfigEntity.getConfigValue());//用户默认头像
        } else {
            userInfo.setLogo(AppConfig.UserDefaultLogo);//用户默认头像
        }
        /********** modify by tom 2016-7-15 13:58:12 end **********/
        userInfo.setCreateOn(new Date());//创建时间
        userInfo.setModifyBy(qualicationDTO.getMobileNumber());//修改人
        userInfo.setIdCard(qualicationDTO.getIdCard());//身份证号
        userInfo.setRegisterDate(new Date());
        userInfo.setSourceType(UserInfoEntity.USER_TYPE_SOURCE_APP);//APP注册
        userInfo.setSex(1);
        userInfo.setJump("1");
        userInfo.setOperatingSystem(qualicationDTO.getOs());//操作系统
        //注册用户
        userInfoRepository.create(userInfo);

        //调用用户统计
        String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
        UserToTalEntity userToTal = userTotalRepository.getTotalInfo(dateNow);
        if (userToTal != null) {
            if ("2".equals(userInfo.getUserType())) {
                userToTal.setCommonUser(userToTal.getCommonUser() + 1);//普通用户
            } else if ("3".equals(userInfo.getUserType())) {
                userToTal.setOwnerUser(userToTal.getOwnerUser() + 1);//业主用户
            }
            if ("android".equals(qualicationDTO.getOs())) {
                userToTal.setAndroid(userToTal.getAndroid() + 1);//安卓用户
            }
            if ("ios".equals(qualicationDTO.getOs())) {
                userToTal.setIos(userToTal.getIos() + 1);//ios用户
            }
            userToTal.setAPP(userToTal.getAPP() + 1);//APP用户
            userTotalRepository.update(userToTal);
        } else {
            UserToTalEntity userToTalEntity = new UserToTalEntity();
            userToTalEntity.setId(IdGen.uuid());
            userToTalEntity.setCreateDate(new Date());
            if ("2".equals(userInfo.getUserType())) {
                userToTalEntity.setCommonUser(1);//普通用户
            } else {
                userToTalEntity.setCommonUser(0);//普通用户
            }
            if ("3".equals(userInfo.getUserType())) {
                userToTalEntity.setOwnerUser(1);//业主用户
            } else {
                userToTalEntity.setOwnerUser(0);//业主用户
            }
            if ("android".equals(qualicationDTO.getOs())) {
                userToTalEntity.setAndroid(1);//安卓用户
            } else {
                userToTalEntity.setAndroid(0);//安卓用户
            }
            if ("ios".equals(qualicationDTO.getOs())) {
                userToTalEntity.setIos(1);//ios用户
            } else {
                userToTalEntity.setIos(0);//ios用户
            }
            userToTalEntity.setAPP(1);//APP用户
            userToTalEntity.setWeChat(0);//微信用户
            userTotalRepository.create(userToTalEntity);
        }

        //添加token数据表
        UserTokenEntity userToken = new UserTokenEntity();
        userToken.setTokenId(CookieTools.getNewCookie());
        userToken.setUserId(userInfo.getUserId());
        userToken.setIsActive("1");  //1  有效  2  无效
        userToken.setCreateBy("WC");
        userToken.setCreateDate(SqlDateUtils.getDate());
        userToken.setCreateTime(SqlDateUtils.getTime());
        userTokenRepository.Add(userToken);

        //如果有身份证号，直接进行业主认证
        if (qualicationDTO.getIdCard() != null && !"".equals(qualicationDTO.getIdCard())) {
            userInfo.setJump("2");
            if (userCRMRepository.checkOwner(qualicationDTO.getIdCard())) {  //业主认证成功 则变更为业主类型
                UserCRMEntity userCRMEntity = userCRMRepository.getOwner(qualicationDTO.getIdCard());
                userInfo.setUserType(UserInfoEntity.USER_TYPE_OWNER);//认证成功 将用户类型变更为业主
                userInfo.setId(userCRMEntity.getMemberId());     //将CRM中业主ID绑定到当前用户下
                userInfo.setJump("4");
            } else {
                userInfo.setJump("3");
            }
            userInfoRepository.update(userInfo);
        }

        UserCRMEntity userCRM = userCRMRepository.getByMobile(userInfo.getMobile());
        AccountCRMEntity accountCRMEntity = accountCRMRepository.getByMobile(userInfo.getMobile());
        if (userCRM == null || accountCRMEntity == null) {
            //创建用户信息
            UserCRMEntity userCRMEntity = new UserCRMEntity();
            userCRMEntity.setUserId(userInfo.getUserId());
            userCRMEntity.setPassword(qualicationDTO.getPassWord());//密码
            userCRMEntity.setNickName(qualicationDTO.getNickName());//昵称
            userCRMEntity.setMobile(qualicationDTO.getMobileNumber());//手机
            userCRMEntity.setUserName(qualicationDTO.getMobileNumber());//账号
            userCRMEntity.setUserState(userInfo.getUserState());//用户状态
            userCRMEntity.setUserType(userInfo.getUserType());//用户类型
            userCRMEntity.setLogo(userInfo.getLogo());//用户默认头像
            userCRMEntity.setCreateOn(userInfo.getCreateOn());//创建时间
            userCRMEntity.setRegisterDate(new Date());//注册时间
            userCRMEntity.setModifyBy(userInfo.getMobile());//修改人
            userCRMEntity.setIdCard(userInfo.getIdCard());//身份证号
            //userCRMEntity.setMemberId(userInfo.getId());
            userCRMRepository.create(userCRMEntity);
            //创建账号信息
            AccountCRMEntity accountCRM = new AccountCRMEntity();
            accountCRM.setAccountId(userInfo.getUserId());
            accountCRM.setMobile(qualicationDTO.getMobileNumber());
            accountCRM.setMemberId(userInfo.getId());
            accountCRM.setNickName(qualicationDTO.getNickName());
            accountCRM.setPassword(qualicationDTO.getPassWord());
            accountCRM.setCreateBy(userInfo.getUserId());
            accountCRM.setCreateOn(new Date());
            accountCRMRepository.create(accountCRM);
            //调用webService接口,向CRM传送数据
            membershipRegisterService.userInfoRegister(accountCRM, userCRMEntity, null, null);
        } else {
            //更新用户信息
            userCRM.setPassword(qualicationDTO.getPassWord());//密码
            userCRM.setNickName(qualicationDTO.getNickName());//昵称
            userCRM.setMobile(qualicationDTO.getMobileNumber());//手机
            userCRM.setUserName(qualicationDTO.getMobileNumber());//账号
            userCRM.setUserState(userInfo.getUserState());//用户状态
            userCRM.setLogo(userInfo.getLogo());//用户默认头像
            userCRM.setModifyOn(new Date());//修改时间
            userCRM.setModifyBy(userInfo.getUserId());//修改人
            /********** modify by tom 2016-7-16 02:35:58 begin **********/
//            userCRM.setIdCard(userInfo.getIdCard());//身份证号
//            userCRM.setUserType(userInfo.getUserType());//用户类型
            /********** modify by tom 2016-7-16 02:35:58 end **********/
            //更新账号信息
            //accountCRMEntity.setAccountId(userInfo.getUserId());
            accountCRMEntity.setMobile(qualicationDTO.getMobileNumber());
            accountCRMEntity.setRegisterId(userCRM.getMemberId());
            accountCRMEntity.setNickName(qualicationDTO.getNickName());
            accountCRMEntity.setPassword(qualicationDTO.getPassWord());
            accountCRMEntity.setModifyBy(userInfo.getUserId());
            accountCRMEntity.setModifyOn(new Date());
            accountCRMRepository.update(accountCRMEntity);
            userCRMRepository.update(userCRM);
            //调用webService接口,向CRM传送数据
            membershipRegisterService.userInfoRegister(accountCRMEntity, userCRM, null, null);
        }

        LoginReturnJsonDTO userLoginInfo = mapper.map(userInfo, LoginReturnJsonDTO.class);
        userLoginInfo.setToken(userInfo.getUserId());
        //增加用户日志记录
        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setLogId(IdGen.uuid());
        systemLogEntity.setLogTime(new Date());//注册日期
        systemLogEntity.setUserName(userInfo.getNickName());//用户昵称
        systemLogEntity.setUserType(userInfo.getUserType());//用户类型
        systemLogEntity.setUserMobile(userInfo.getMobile());//手机号
        systemLogEntity.setIdCard(userInfo.getIdCard());//身份证
        systemLogEntity.setSourceFrom(userInfo.getSourceType());//来源
        systemLogEntity.setSysVersion(userInfo.getOperatingSystem());//系统版本
        if (userInfo != null) {
            HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseByMemberId(userInfo.getId());
            if (houseInfoEntity != null) {
                systemLogEntity.setProjectId(houseInfoEntity.getProjectName());//项目
            }
        } else {
            systemLogEntity.setProjectId("");//项目
        }
        systemLogRepository.addSysLog(systemLogEntity);
        //返回成功
        return new SuccessApiResult(userLoginInfo);
    }

    @Override
    public ApiResult appLandlord(QualicationDTO qualicationDTO, UserInfoEntity userInfoEntity) {
        //验证短信验证码
//        Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(qualicationDTO.getMobileNumber()
//                , qualicationDTO.getPhoneCode()
//                , SMSAuthEntity.TYPE_REGISTER);
//        if(!checkAuthCode) {
//            return new ErrorApiResult("tip_00000467");
//        }
        userInfoEntity.setJump("4");
        if (!userCRMRepository.checkOwner(qualicationDTO.getIdCard())) {
            userInfoEntity.setJump("3");
            return new ErrorApiResult(301, "认证失败！");
        }
        if (!userInfoRepository.checkIdCard(qualicationDTO.getIdCard())) {
            return new ErrorApiResult(301, "身份证号已认证过！");
        }
        UserCRMEntity userCRMEntity = userCRMRepository.getOwner(qualicationDTO.getIdCard());
        userInfoEntity.setId(userCRMEntity.getMemberId());           //将CRM的业主ID绑定到当前用户下
        userInfoEntity.setRealName(userCRMEntity.getRealName());
        userInfoEntity.setIdCard(qualicationDTO.getIdCard());       //身份证信息
        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);  //认证成功 将用户类型变更为业主
        userInfoRepository.update(userInfoEntity);
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
        if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
            systemLogEntity.setProjectId(houseInfoEntityList.get(0).getProjectName());//项目
        } else {
            systemLogEntity.setProjectId("");//项目
        }
        systemLogRepository.addSysLog(systemLogEntity);
        //调用用户统计 Wyd_2016-12-30
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
            if ("3".equals(userInfoEntity.getUserType())) {
                userToTalEntity.setOwnerUser(1);//业主用户
            } else {
                userToTalEntity.setOwnerUser(0);//业主用户
            }
            userTotalRepository.create(userToTalEntity);
        }
        return new SuccessApiResult();
    }

    @Override
    public ApiResult appAppeal(AppealDTO appealDTO) {
        //验证短信验证码
        Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(appealDTO.getMobile()
                , appealDTO.getPhoneCode()
                , SMSAuthEntity.TYPE_APPEAL);
        if (!checkAuthCode) {
            return new ErrorApiResult("tip_00000467");
        }
        UserAuthInfoEntity userAuthInfoEntity = new UserAuthInfoEntity();
        userAuthInfoEntity.setId(IdGen.uuid());
        userAuthInfoEntity.setCreateBy(appealDTO.getRealName());
        userAuthInfoEntity.setCreateOn(new Date());
        userAuthInfoEntity.setIdNumber(appealDTO.getIdCard());
        userAuthInfoEntity.setMobile(appealDTO.getMobile());
        userAuthInfoEntity.setModifyBy(appealDTO.getRealName());
        userAuthInfoEntity.setModifyOn(new Date());
        userAuthInfoEntity.setName(appealDTO.getRealName());
        userAuthInfoEntity.setState(UserAuthInfoEntity.STATE_AUDIT_WAIT);
        userAuthInfoEntity.setContent(appealDTO.getAddress());
        userAuthInfoRepository.create(userAuthInfoEntity);
        return new SuccessApiResult();
    }

    @Override
    public UserInfoEntity getUserInfoByMobile(String mobile) {
        // 根据手机号取得userInfoEntity
        UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(mobile);
        return userInfoEntity;

    }

    @Override
    public ApiResult addRealName(String userId, String realname) {
        UserInfoEntity userInfoEntity = userInfoRepository.getByUserId(userId);
        userInfoEntity.setRealName(realname);
        userInfoRepository.update(userInfoEntity);
        return new SuccessApiResult();

    }

    @Override
    public ApiResult getUserInfoById(String userId) {

        UserInfoEntity userInfoEntity = userInfoRepository.getByUserId(userId);
        LoginReturnJsonDTO loginReturnJsonDTO = new LoginReturnJsonDTO();
        if (userInfoEntity != null) {
            loginReturnJsonDTO.setToken(userInfoEntity.getUserId());
            loginReturnJsonDTO.setUserName(userInfoEntity.getUserName());
            loginReturnJsonDTO.setPassword(userInfoEntity.getPassword());
            loginReturnJsonDTO.setMobile(userInfoEntity.getMobile());
            loginReturnJsonDTO.setNickName(userInfoEntity.getNickName());
            loginReturnJsonDTO.setRealName(userInfoEntity.getRealName());
            loginReturnJsonDTO.setSex(userInfoEntity.getSex());
            if (userInfoEntity.getBirthday() != null) {
                loginReturnJsonDTO.setBirthday(DateUtils.format(userInfoEntity.getBirthday(), DateUtils.FORMAT_SHORT));
            }
            loginReturnJsonDTO.setIdCard(userInfoEntity.getIdCard());
            loginReturnJsonDTO.setIdType(userInfoEntity.getIdType());
            loginReturnJsonDTO.setLogo(userInfoEntity.getLogo());
            loginReturnJsonDTO.setUserState(userInfoEntity.getUserState());
            loginReturnJsonDTO.setUserType(userInfoEntity.getUserType());
            if (userInfoEntity.getRegisterDate() != null) {
                loginReturnJsonDTO.setBeginTime(DateUtils.format(userInfoEntity.getCreateOn(), DateUtils.FORMAT_SHORT));
            }

            //增加用户日志记录
            // UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class,userId);
            UserVisitLogEntity userVisitLogEntity = new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
            userVisitLogEntity.setUserName(userInfoEntity.getNickName());//用户昵称
            userVisitLogEntity.setUserType(userInfoEntity.getUserType());//用户类型
            userVisitLogEntity.setUserMobile(userInfoEntity.getMobile());//手机号
            userVisitLogEntity.setSourceFrom(userInfoEntity.getSourceType());//来源
            userVisitLogEntity.setThisSection("服务");
            userVisitLogEntity.setThisFunction("意见反馈");
            userVisitLogEntity.setLogContent("");
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
            if (null != userSettingEntity) {
                userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
            } else {
                userVisitLogEntity.setProjectId("");//项目
            }
            systemLogRepository.addUserVisitLog(userVisitLogEntity);
        }
        return new SuccessApiResult(loginReturnJsonDTO);

    }

    /**
     * Code:
     * Type:UI Method
     * Describe:
     * CreateBy:
     * CreateOn:
     * ModifyBy:tom
     * ModifyOn:2016-7-16 02:44:51
     * ModifyDescribe:郭佳say 不能修改userCRM中身份证、memberID、真实姓名，去掉对userCRM真是姓名的修改。
     */
    @Override
    public ApiResult changeUserInfo(ModelMap modelMap) {

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String userId = (String) modelMap.get("userId");//用户ID
            String realname = (null != modelMap.get("realName")) ? (String) modelMap.get("realName") : "";//用户真实姓名
            String sex = (String) modelMap.get("sex");//用户性别
            String birthday = (String) modelMap.get("birthday");//用户生日
            String logo = (null != modelMap.get("logo")) ? (String) modelMap.get("logo") : "";//用户头像
            String mobile = (null != modelMap.get("mobile")) ? (String) modelMap.get("mobile") : "";//用户手机
            String nickName = (null != modelMap.get("nickName")) ? (String) modelMap.get("nickName") : "";   //用户昵称
            UserInfoEntity userInfoEntity = userInfoRepository.getByUserId(userId);

            //判断用户是否更改手机号码
            if (!mobile.equals(userInfoEntity.getMobile())) {
                //判断手机号码的唯一性
                UserInfoEntity checkUser = userInfoRepository.getByMobile(mobile);
                if (checkUser != null) {
                    //判断该渠道是否已经注册过
                    if (userInfoRepository.existBookofuserIDInRegisterType(mobile)) {
                        return new ErrorApiResult(20201, "此手机已注册，请确认填写的手机号码是否正确");
                    }
                }
            }
            /**
             *验证用户昵称的的唯一性
             */
            UserInfoEntity ckUser = userInfoRepository.ckUserByNickName(userId,nickName);
            if (ckUser != null) {
                if (!StringUtil.isEqual(ckUser.getUserId(), nickName)) {
                    return new ErrorApiResult(20201, "该昵称已经被注册");
                }
            }

            userInfoEntity.setMobile(mobile);

            userInfoEntity.setRealName(realname);
            if (null != birthday && !"".equals(birthday)) {
                userInfoEntity.setBirthday(format.parse(birthday));
            } else {
                userInfoEntity.setBirthday(null);
            }
            if (null != sex) {
                userInfoEntity.setSex(Integer.valueOf(sex));
            } else {
                userInfoEntity.setSex(null);
            }
            userInfoEntity.setNickName(nickName);
            userInfoEntity.setLogo(logo);
            userInfoEntity.setModifyBy(userInfoEntity.getNickName());
            userInfoEntity.setModifyOn(new Date());

            userInfoRepository.update(userInfoEntity);

            //调用点击人统计
            String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "15", userInfoEntity.getUserId());
            if (clickUserEntity == null) {
                ClickUsersEntity clickUser = new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userInfoEntity.getUserId());
                clickUser.setForeignId("15");
                clickUserRepository.create(clickUser);
            } else {
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }

            if (null != userInfoEntity.getId() && !"".equals(userInfoEntity.getId())) {
                UserCRMEntity userCRM = userCRMRepository.getByMemberId(userInfoEntity.getId());
                if (userCRM != null) {
                    userCRM.setMobile(userInfoEntity.getMobile());//联系方式手机号码

                    /********** modify on 2016-7-16 02:44:51 by tom begin **********/
                    userCRM.setRealName(userInfoEntity.getRealName());//真实姓名
                    /********** modify on 2016-7-16 02:44:51 by tom end **********/

                    userCRM.setBirthday(userInfoEntity.getBirthday());//生日
                    userCRM.setSex(String.valueOf(userInfoEntity.getSex()));//性别
                    userCRM.setLogo(userInfoEntity.getLogo());//头像
                    userCRM.setModifyBy(userInfoEntity.getNickName());//修改人
                    userCRM.setModifyOn(userInfoEntity.getModifyOn());//修改时间

                    userCRMRepository.update(userCRM);
                }
                AccountCRMEntity accountCRM = accountCRMRepository.get(null, userInfoEntity.getId());
                if (accountCRM != null) {
                    accountCRM.setMobile(userInfoEntity.getMobile());//联系方式手机号码
                    accountCRM.setModifyBy(userInfoEntity.getNickName());//修改人
                    accountCRM.setModifyOn(userInfoEntity.getModifyOn());//修改时间
                    accountCRMRepository.update(accountCRM);
                }
                if (userCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, null, null);
                }
                if (userCRM != null && accountCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, accountCRM, null);
                }
            } else {
                UserCRMEntity userCRM = userCRMRepository.get(userInfoEntity.getUserId());
                if (userCRM != null) {
                    userCRM.setMobile(userInfoEntity.getMobile());//联系方式手机号码

                    /********** modify on 2016-7-16 02:44:51 by tom begin **********/
                    userCRM.setRealName(userInfoEntity.getRealName());//真实姓名
                    /********** modify on 2016-7-16 02:44:51 by tom end **********/

                    userCRM.setBirthday(userInfoEntity.getBirthday());//生日
                    userCRM.setSex(String.valueOf(userInfoEntity.getSex()));//性别
                    userCRM.setLogo(userInfoEntity.getLogo());//头像
                    userCRM.setModifyBy(userInfoEntity.getNickName());//修改人
                    userCRM.setModifyOn(userInfoEntity.getModifyOn());//修改时间
                }
                AccountCRMEntity accountCRM = accountCRMRepository.get(userInfoEntity.getUserId());
                if (accountCRM != null) {
                    accountCRM.setMobile(userInfoEntity.getMobile());//联系方式手机号码
                    accountCRM.setModifyBy(userInfoEntity.getNickName());//修改人
                    accountCRM.setModifyOn(userInfoEntity.getModifyOn());//修改时间
                    accountCRMRepository.update(accountCRM);
                }
                if (userCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, null, null);
                }
                if (userCRM != null && accountCRM != null) {
                    memberinfoService.userInfoUpdate(userCRM, null, null, null, null, accountCRM, null);
                }
            }
            /**
             * 2016-06-22,重新封装返回数据
             */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);
            userLoginInfo.setToken(userInfoEntity.getUserId());

            //增加用户日志记录
//UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class,userId);
            UserVisitLogEntity userVisitLogEntity = new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
            userVisitLogEntity.setUserName(userInfoEntity.getNickName());//用户昵称
            userVisitLogEntity.setUserType(userInfoEntity.getUserType());//用户类型
            userVisitLogEntity.setUserMobile(userInfoEntity.getMobile());//手机号
            userVisitLogEntity.setSourceFrom(userInfoEntity.getSourceType());//来源
            userVisitLogEntity.setThisSection("我的");
            userVisitLogEntity.setThisFunction("账号管理");
            userVisitLogEntity.setLogContent("");
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
            if (null != userSettingEntity) {
                userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
            } else {
                userVisitLogEntity.setProjectId("");//项目
            }
            systemLogRepository.addUserVisitLog(userVisitLogEntity);
            return new SuccessApiResult(userLoginInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult(500, "抱歉，发生了异常");
        }
    }


    @Override
    public UserTokenEntity getUserTokenByUserID(String userId) {

        UserTokenEntity userTokenEntity = userTokenRepository.getByUserId(userId);

        return userTokenEntity;

    }

    /**
     * 通过memberId获取userCRM详情 WeiYangDong 2016-11-22
     */
    public UserCRMEntity getByMemberId(String memberId){
        return userCRMRepository.getByMemberId(memberId);
    }

    /* ------------------------微信昵称特殊字符过滤 Wyd-------------------------- */
    /**
     * 检测是否有emoji字符
     * @param source 需要判断的字符串
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!notisEmojiCharacter(codePoint)) {
                //判断确认有表情字符
                return true;
            }
        }
        return false;
    }

    /**
     * 非emoji表情字符判断
     * @param codePoint
     * @return  boolean
     */
    private static boolean notisEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source  需要过滤的字符串
     * @return String
     */
    public static String filterEmoji(String source) {
        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        StringBuilder buf = null;//该buf保存非emoji的字符
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (notisEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return "";//如果没有找到非emoji的字符，则返回无内容的字符串
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }
    /* ------------------------微信昵称特殊字符过滤 Wyd-------------------------- */

}
