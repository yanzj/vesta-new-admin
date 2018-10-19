package com.maxrocky.vesta.application.impl;

import com.ctc.wstx.util.DataUtil;
import com.maxrocky.vesta.application.DTO.admin.HouseCompanyAdminDTO;
import com.maxrocky.vesta.application.DTO.json.LG0001.LoginReturnJsonDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.domain.model.SMSAuthEntity;
import com.maxrocky.vesta.domain.model.SystemConfigEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserLoginBookEntity;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.SystemConfigRepository;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.domain.repository.UserLoginBookRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.utility.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Tom on 2016/1/13 19:09.
 * Describe:登录信息Service接口实现类
 */
@Service
public class UserLoginBookServiceImpl implements UserLoginBookService {

    /* 登录信息 */
    @Autowired
    UserLoginBookRepository userLoginBookRepository;
    /* 用户信息 */
    @Autowired
    UserInfoRepository userInfoRepository;
    /* 系统配置 */
    @Autowired
    SystemConfigRepository systemConfigRepository;
    /* 用户配置 */
    @Autowired
    UserSettingRepository userSettingRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;

    /* 手机验证码服务 */
    @Autowired
    SMSAuthService smsAuthService;
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
    /* 消息服务 */
    @Autowired
    MessageTokenService messageTokenService;

    /**
     * Important Information:此接口已经作废
     *                       请使用 public ApiResult getUserInfoByVestaToken(String vestaToken)
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取用户登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:31:52
     */
    @Override
    public UserInfoEntity getUserInfoByToken(String token) {

        /* 获取系统配置参数 */
        SystemConfigEntity systemConfigEntity = systemConfigRepository.get("tokenCheckFlag");
        if(systemConfigEntity == null){
            return null;
        }

        String userId = "";
        if(StringUtil.isEqual(systemConfigEntity.getConfigValue(), "N")){
            /* 本地开发，直接获取用户Id */
            SystemConfigEntity systemConfig = systemConfigRepository.get("testUserID");
            userId = systemConfig.getConfigValue();
        }else{
            /* 正常获取用户Id */

            if(StringUtil.isEmpty(token)){
                return null;
            }
            /* 获取登录信息 */
            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.get(token);
            if(userLoginBookEntity == null){
                return null;
            }
            if(!userLoginBookEntity.isLogin()){
                return null;
            }
            userId = userLoginBookEntity.getUserId();
        }

        /* 获取用户信息 */
        return userInfoRepository.get(userId);
    }

    /**
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取用户登录信息。
     * CreateBy:Tom
     * CreateOn:2016-03-01 10:29:04
     */
    @Override
    public ApiResult getUserInfoByVestaToken(String vestaToken) {
        /* 获取系统配置参数 */
        SystemConfigEntity systemConfigEntity = systemConfigRepository.get("tokenCheckFlag");
        if(systemConfigEntity == null){
            return new ErrorApiResult(10000 ,"系统设置失败，请联系客服。");
        }

        String userId = "";
        if(StringUtil.isEqual(systemConfigEntity.getConfigValue(), "N")){
            /* 本地开发，直接获取用户Id */
            SystemConfigEntity systemConfig = systemConfigRepository.get("testUserID");
            userId = systemConfig.getConfigValue();
        }else{
            /* 正常获取用户Id */
            if(StringUtil.isEmpty(vestaToken)){
                return new ErrorApiResult(10002 ,"当前未登录。");
            }
            /* 获取登录信息 */
            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.get(vestaToken);
            if(userLoginBookEntity == null){
                return new ErrorApiResult(10002 ,"当前未登录。");
            }
            if(!userLoginBookEntity.isLogin()){
                if(userLoginBookEntity.isLoginOut()){
                    return new ErrorApiResult(10003 ,"您的账号已在其他地方登录。");
                }
                return new ErrorApiResult(10002 ,"当前未登录。");
            }
            userId = userLoginBookEntity.getUserId();
        }

        /* 获取用户信息 */
        UserInfoEntity userInfoEntity = userInfoRepository.get(userId);
        if(userInfoEntity == null){
            return new ErrorApiResult(10004, "用户信息错误，请联系客服。");
        }
        return new SuccessApiResult(userInfoEntity);
    }

    /**
     * Code:For UI
     * Type:UI Method
     * Describe:专为UI调试登录接口。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:11:32
     */
    @Override
    public UserInfoEntity loginForUI(String userId) throws GeneralException {

        try {
            if(StringUtil.isEmpty(userId)){
//                return ErrorResource.getError("tip_00000519");
                return null;
            }

            /* 校验用户 */
            UserInfoEntity userInfoEntity = userInfoRepository.get(userId);
            if(userInfoEntity == null){
//                return ErrorResource.getError("tip_00000519");
                return null;
            }

//            /* 获取有效登录信息 */
//            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userId);
//            if(userLoginBookEntity != null){
////                userLoginBookEntity.setStateExit();
////                userLoginBookRepository.update(userLoginBookEntity);
//                return userLoginBookEntity;
//            }
//
//            /* 重新创建登录信息 */
//            UserLoginBookEntity userLoginBook = new UserLoginBookEntity(userInfoEntity);
//            userLoginBookRepository.create(userLoginBook);
//            return userLoginBook;
            return userInfoEntity;
//            return new SuccessApiResult(userLoginBook);
        }catch (Exception ex){
            ex.printStackTrace();
//            throw new GeneralException("100","系统处理错误");
            return null;
        }
    }

    /**
     * Code:LG0001
     * Type:UI Method
     * Describe:业主、家属、租户App登录
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:16:19
     */
    @Override
    public ApiResult login(String username, String password, String form, String phoneUUID) throws GeneralException {

        try {
            if(StringUtil.isEmpty(username)){
                return new ErrorApiResult("tip_00000010");
            }
            if(StringUtil.isEmpty(password)){
                return new ErrorApiResult("tip_00000010");
            }

            /* 查询用户信息 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByUserNameAndPassword(username, EncryptUtils.encryptPassword(password));
            if(userInfoEntity == null){
                return new ErrorApiResult("tip_00000009");
            }

            /* 查询用户有效登录信息 */
            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
            if(userLoginBookEntity != null){
            /* 将已经登录的信息设置其他地方登录 */
                userLoginBookEntity.setStateExit();
                userLoginBookRepository.update(userLoginBookEntity);
            }

            /* 创建登录信息 */
            UserLoginBookEntity userLoginBook = new UserLoginBookEntity(userInfoEntity);
            userLoginBook.setLoginType(form);
            userLoginBook.setPhoneUUID(phoneUUID);
            userLoginBookRepository.create(userLoginBook);

            /* 获取返回信息 */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);
            /* 获取用户设置 */
            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
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
                        loginLogService.AddLoginLogManage(houseCompanyAdminDTO.getAreaId(),userSettingEntity.getProjectId(),userSettingEntity.getUserId(),"192.168.0.1",userInfoEntity.getUserName() + "登录成功了！",form);
                        /* 设备日志 */
                        equipStatisticsService.addEquipManage(userSettingEntity.getProjectId(),houseProjectDto.getCompanyId(),houseCompanyAdminDTO.getAreaId(),form,userInfoEntity.getUserId());
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

    /**
     * Code:LG0002
     * Type:UI Method
     * Describe:游客登陆
     * CreateBy:Tom
     * CreateOn:2016-01-17 03:59:07
     */
    @Override
    public ApiResult loginForVisitors(String phone, String authCode, String form, String phoneUUID) throws GeneralException {

        try {
            if(StringUtil.isEmpty(phone)){
                return new ErrorApiResult("tip_00000252");
            }
            if(StringUtil.isEmpty(authCode)){
                return new ErrorApiResult("tip_00000466");
            }

            /* 验证码 */
            Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(phone, authCode, SMSAuthEntity.TYPE_LOGIN);
            if(!checkAuthCode) {
                return new ErrorApiResult("tip_00000467");
            }

            /* 查询用户信息 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(phone);
            if(userInfoEntity == null){
                /* 系统不存在用户则创建 */
                userInfoEntity = new UserInfoEntity();
                userInfoEntity.createVisitor(phone);
                userInfoRepository.create(userInfoEntity);
            }
            if(!userInfoEntity.isVisitor()){
                return new ErrorApiResult("tip_00000063");
            }

            /* 查询用户有效登录信息 */
            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
            if(userLoginBookEntity != null){
                /* 将已经登录的信息设置其他地方登录 */
                userLoginBookEntity.setStateExit();
                userLoginBookRepository.update(userLoginBookEntity);
            }

            /* 创建登录信息 */
            UserLoginBookEntity userLoginBook = new UserLoginBookEntity(userInfoEntity);
            userLoginBook.setLoginType(form);
            userLoginBook.setPhoneUUID(phoneUUID);
            userLoginBookRepository.create(userLoginBook);

            /* 设置用户配置 */
            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
            if(userSettingEntity == null){
                userSettingEntity = new UserSettingEntity();
                userSettingEntity.setUserId(userInfoEntity.getUserId());
                userSettingEntity.setProjectId(AppConfig.DEFAULT_PROJECT_ID);
                userSettingEntity.setProjectName(AppConfig.DEFAULT_PROJECT_NAME);
                userSettingEntity.setCreateBy("ADMIN");
                userSettingEntity.setCreateOn(DateUtils.getDate());
                userSettingEntity.setModifyBy("ADMIN");
                userSettingEntity.setModifyOn(DateUtils.getDate());
                userSettingRepository.create(userSettingEntity);
            }else{
                userSettingEntity.setProjectId(AppConfig.DEFAULT_PROJECT_ID);
                userSettingEntity.setProjectName(AppConfig.DEFAULT_PROJECT_NAME);
                userSettingEntity.setModifyBy("ADMIN");
                userSettingEntity.setModifyOn(DateUtils.getDate());
                userSettingRepository.update(userSettingEntity);
            }

            /* 获取返回信息 */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);
            userLoginInfo.setToken(userLoginBook.getLoginId());

            return new SuccessApiResult(userLoginInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * Code:LO0001
     * Type:UI Method
     * Describe:登出
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:50:12
     */
    @Override
    public ApiResult exit(UserInfoEntity userInfoEntity) {

         /* 查询用户有效登录信息 */
        UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
        if(userLoginBookEntity != null){
            /* 将已经登录的信息设置其他地方登录 */
            userLoginBookEntity.setNormalExit();
            userLoginBookRepository.update(userLoginBookEntity);

            messageTokenService.delToken(userLoginBookEntity.getUserId());
        }

        return new SuccessApiResult();
    }

    /**
     * 根据用户名密码登陆
     * @param username
     * @param password
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult loginForUP(String username, String password, UserInfoEntity user, String vestaToken) throws GeneralException {

        try {
            if(StringUtil.isEmpty(username)){
                return new ErrorApiResult("tip_00000010");
            }
            if(StringUtil.isEmpty(password)){
                return new ErrorApiResult("tip_00000010");
            }

            /* 查询用户信息 */
            //app用户
            UserInfoEntity userInfoEntity = userInfoRepository.getByUserNameAndPassword(username, password);
            if(userInfoEntity == null){
                return new ErrorApiResult("tip_00000009");
            }

            /* 查询用户有效登录信息 */
//            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
//            if(userLoginBookEntity != null){
//            /* 将已经登录的信息设置其他地方登录 */
//                userLoginBookEntity.setStateExit();
//                userLoginBookRepository.update(userLoginBookEntity);
//            }

            /* 创建登录信息 */
//            UserLoginBookEntity userLoginBook = new UserLoginBookEntity(userInfoEntity);
//            userLoginBook.setLoginType(form);
//            userLoginBook.setPhoneUUID(phoneUUID);
//            userLoginBookRepository.create(userLoginBook);

            /* 获取返回信息 */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);
//            /* 获取用户设置 */
//            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
//            userLoginInfo.setProjectId(userSettingEntity.getProjectId());
//            userLoginInfo.setProjectName(userSettingEntity.getProjectName());

            userLoginInfo.setToken(userInfoEntity.getUserId());

//            try {
//                /* 获取项目信息 */
//                HouseProjectDto houseProjectDto = houseProjectService.getProjectById(userSettingEntity.getProjectId());
//                if(houseProjectDto != null){
//                    HouseCompanyAdminDTO houseCompanyAdminDTO = houseCompanyService.getHouseCompanyAdminDTOById(houseProjectDto.getCompanyId());
//                    if(houseCompanyAdminDTO != null){
//                        /* 登录日志 */
//                        loginLogService.AddLoginLogManage(houseCompanyAdminDTO.getAreaId(),userSettingEntity.getProjectId(),userSettingEntity.getUserId(),"192.168.0.1",userInfoEntity.getUserName() + "登录成功了！",form);
//                        /* 设备日志 */
//                        equipStatisticsService.addEquipManage(userSettingEntity.getProjectId(),houseProjectDto.getCompanyId(),houseCompanyAdminDTO.getAreaId(),form,userInfoEntity.getUserId());
//                    }
//                }
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//                System.out.println("---------->>Create login log exception.");
//            }

            return new SuccessApiResult(userLoginInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new GeneralException("抱歉，出现异常");
        }
    }

    /**
     * 微信上用户名密码登陆
     * @param username
     * @param password
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult weChatloginForUP(String username, String password, UserInfoEntity user, String vestaToken) {

        try {
            if(StringUtil.isEmpty(username)){
                return new ErrorApiResult("tip_00000010");
            }
            if(StringUtil.isEmpty(password)){
                return new ErrorApiResult("tip_00000010");
            }

            /* 查询用户信息 */
            //app用户
            UserInfoEntity userInfoEntity = userInfoRepository.getByUserNameAndPassword(username, password);
            if(userInfoEntity == null){
                return new ErrorApiResult("tip_00000009");
            }

            //判断该app用户是否绑定过微信
            UserLoginBookEntity loginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
            if (loginBookEntity != null) {
                //说明该app用户被绑定过
                //return new ErrorApiResult(20201, "该用户已被绑定！");
            }else {
                //说明该app用户未被绑定过
                //微信用户清除，并绑定微信用户和app用户
                userInfoEntity.setModifyOn(new Date());
                userInfoEntity.setModifyBy(user.getModifyBy());
                userInfoEntity.setWC_nickName(user.getWC_nickName());
                userInfoRepository.update(userInfoEntity);
                userInfoRepository.delWCUser(user);
                //将对应vestaToken的userId变为app用户Id
                UserTokenEntity userTokenEntity = userInfoRepository.getUserTokenByVestaToken(vestaToken);
                if (userTokenEntity != null) {
                    userTokenEntity.setUserId(userInfoEntity.getUserId());
                    userTokenEntity.setCreateDate(SqlDateUtils.getDate());
                    userTokenEntity.setCreateTime(SqlDateUtils.getTime());
                    userInfoRepository.updateTokenEntity(userTokenEntity);
                }
                //得到该微信用户对应的userLoginBook，将对应微信用户清除，并绑定微信和app用户
                UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(user.getUserId());
                if (userLoginBookEntity != null) {
                    userLoginBookEntity.setUserId(userInfoEntity.getUserId());
                    userLoginBookEntity.setModifyBy(userInfoEntity.getModifyBy());
                    userLoginBookEntity.setModifyOn(new Date());
                    userLoginBookRepository.update(userLoginBookEntity);
                }
            }

            /* 查询用户有效登录信息 */
//            UserLoginBookEntity userLoginBookEntity = userLoginBookRepository.getLoginByUserId(userInfoEntity.getUserId());
//            if(userLoginBookEntity != null){
//            /* 将已经登录的信息设置其他地方登录 */
//                userLoginBookEntity.setStateExit();
//                userLoginBookRepository.update(userLoginBookEntity);
//            }

            /* 创建登录信息 */
//            UserLoginBookEntity userLoginBook = new UserLoginBookEntity(userInfoEntity);
//            userLoginBook.setLoginType(form);
//            userLoginBook.setPhoneUUID(phoneUUID);
//            userLoginBookRepository.create(userLoginBook);

            /* 获取返回信息 */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);
//            /* 获取用户设置 */
//            UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
//            userLoginInfo.setProjectId(userSettingEntity.getProjectId());
//            userLoginInfo.setProjectName(userSettingEntity.getProjectName());

            userLoginInfo.setToken(userInfoEntity.getUserId());

//            try {
//                /* 获取项目信息 */
//                HouseProjectDto houseProjectDto = houseProjectService.getProjectById(userSettingEntity.getProjectId());
//                if(houseProjectDto != null){
//                    HouseCompanyAdminDTO houseCompanyAdminDTO = houseCompanyService.getHouseCompanyAdminDTOById(houseProjectDto.getCompanyId());
//                    if(houseCompanyAdminDTO != null){
//                        /* 登录日志 */
//                        loginLogService.AddLoginLogManage(houseCompanyAdminDTO.getAreaId(),userSettingEntity.getProjectId(),userSettingEntity.getUserId(),"192.168.0.1",userInfoEntity.getUserName() + "登录成功了！",form);
//                        /* 设备日志 */
//                        equipStatisticsService.addEquipManage(userSettingEntity.getProjectId(),houseProjectDto.getCompanyId(),houseCompanyAdminDTO.getAreaId(),form,userInfoEntity.getUserId());
//                    }
//                }
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//                System.out.println("---------->>Create login log exception.");
//            }

            return new SuccessApiResult(userLoginInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new GeneralException("抱歉，出现异常");
        }
    }

    /**
     * 根据手机号和验证码登陆
     * @param moblie
     * @param phoneCode
     * @return
     * @throws GeneralException
     */
    public ApiResult loginForMoblie(String moblie, String phoneCode) throws GeneralException {

        try {
            if(StringUtil.isEmpty(moblie)){
                return new ErrorApiResult("tip_00000010");
            }
//            if(StringUtil.isEmpty(phoneCode)){
//                return new ErrorApiResult("tip_00000010");
//            }

            //验证短信验证码
            Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(moblie
                    , phoneCode
                    , SMSAuthEntity.TYPE_REGISTER);
            if(!checkAuthCode) {
                return new ErrorApiResult("tip_00000467");
            }

            /* 查询用户信息 */
            UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(moblie);
            if(userInfoEntity == null){
                return new ErrorApiResult("tip_00000009");
            }

            /* 获取返回信息 */
            LoginReturnJsonDTO userLoginInfo = mapper.map(userInfoEntity, LoginReturnJsonDTO.class);

            userLoginInfo.setToken(userInfoEntity.getUserId());

            return new SuccessApiResult(userLoginInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new GeneralException("抱歉，出现异常");
        }
    }

}
