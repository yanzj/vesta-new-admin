package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.json.AppUserInfoDTO;
import com.maxrocky.vesta.application.inf.MessageTokenService;
import com.maxrocky.vesta.application.inf.QualityLogService;
import com.maxrocky.vesta.application.inf.UserStaffLoginBookService;
import com.maxrocky.vesta.application.jsonDTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Tom on 2016/1/22 12:04.
 * Describe:员工登录信息Service接口实现类
 */
@Service
public class UserStaffLoginBookServiceImpl implements UserStaffLoginBookService {

    /* 员工信息 */
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    /* 员工登陆信息 */
    @Autowired
    UserStaffLoginBookRepository userStaffLoginBookRepository;
    /* 系统配置 */
    @Autowired
    SystemConfigRepository systemConfigRepository;
    /* 员工权限 */
    @Autowired
    RoleAnthorityRepository roleAnthorityRepository;

    @Autowired
    RoleRolesetRepository roleRolesetRepository;
    @Autowired
    RoleViewmodelRepository roleViewmodelRepository;
    @Autowired
    RoleRoleRepository roleRoleRepository;

    @Autowired
    AppRoleSetRepository appRoleSetRepository;
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    OrganizeRepository organizeRepository;
    @Autowired
    OrganizeUserRepository organizeUserRepository;
    @Autowired
    UserAgencyRepository userAgencyRepository;
    /* 消息服务 */
    @Autowired
    MessageTokenService messageTokenService;
    @Autowired
    RoleDataRepository roleDataRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    QualityLogRepository qualityLogRepository;

    /**
     * Code:For UI
     * Type:UI Method
     * Describe:专为UI调试登录接口。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:11:32
     */
    @Override
    public UserStaffLoginBookEntity loginForStaffUI(String staffId) throws GeneralException {

        try {
            if (StringUtil.isEmpty(staffId)) {
//                return ErrorResource.getError("tip_00000519");
                return null;
            }

            /* 校验用户 */
            UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(staffId);
            if (userPropertyStaffEntity == null) {
//                return ErrorResource.getError("tip_00000519");
                return null;
            }

            /* 获取有效登录信息 */
            UserStaffLoginBookEntity userStaffLoginBookEntity = userStaffLoginBookRepository.getLoginByStaffId(staffId);
            if (userStaffLoginBookEntity != null) {
//                userLoginBookEntity.setStateExit();
//                userLoginBookRepository.update(userLoginBookEntity);
                return userStaffLoginBookEntity;
            }

            /* 重新创建登录信息 */
            UserStaffLoginBookEntity userStaffLoginBook = new UserStaffLoginBookEntity(userPropertyStaffEntity);
            userStaffLoginBookRepository.create(userStaffLoginBook);
            return userStaffLoginBook;

//            return new SuccessApiResult(userLoginBook);
        } catch (Exception ex) {
            ex.printStackTrace();
//            throw new GeneralException("100","系统处理错误");
            return null;
        }
    }

    /**
     * Code:LG0003
     * Type:UI Method
     * Describe:员工App登录
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:16:19
     */
    @Override
    public ApiResult loginForStaff(String username, String password, String ip) throws GeneralException {

        try {
            if (StringUtil.isEmpty(username)) {
                return new ErrorApiResult("tip_00000010");
            }
            if (StringUtil.isEmpty(password)) {
                return new ErrorApiResult("tip_00000010");
            }

            UserPropertyStaffEntity userPropertyStaffEntity = null;

//            if(username.startsWith(AppConfig.STAFF_START_IDENTIFY)){
                /* 查询用户信息 */
            userPropertyStaffEntity = userPropertyStaffRepository.getByUserNameAndPassword(username, password);
//            }else{
//                /* SSO验证用户 */
//                StringBuffer params = new StringBuffer();
//                params.append("SystemCode=");
//                params.append(AppConfig.SSO_SYSTEM_CODE);
//                params.append("&UserID=");
//                params.append(username);
//                params.append("&UserPassword=");
//                params.append(password);
//                params.append("&ResultMIMEType=text/xml");
//                String retString = HttpURLConnectionTools.postXML(AppConfig.SSO_CLIENT_LOGIN_PATH, params.toString());
////                System.out.println("retString---->>" + retString);
//                if(retString.contains("<ResultCode>0</ResultCode>")){
//                    userPropertyStaffEntity = userPropertyStaffRepository.get(username);
//                }
//            }
            if (userPropertyStaffEntity == null) {
                /**
                 * 添加日志
                 */
                QualityLogEntity qualityLogEntity = new QualityLogEntity();
                qualityLogEntity.setLogId(IdGen.uuid());//id
                qualityLogEntity.setUserName(username);//用户名
                qualityLogEntity.setSourceFrom("APP");//来源
                qualityLogEntity.setIpAddress(ip);//ip地址
                String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录失败";
                qualityLogEntity.setSysContent(content);//登录内容
                qualityLogEntity.setSysTime(new Date());//访问时间
                qualityLogRepository.addQualityLogInfo(qualityLogEntity);

                return new ErrorApiResult("tip_00000009");
            }

            /* 查询用户有效登录信息 */
//            UserStaffLoginBookEntity userStaffLoginBookEntity = userStaffLoginBookRepository.getLoginByStaffId(userPropertyStaffEntity.getStaffId());
//            if(userStaffLoginBookEntity != null){
//            /* 将已经登录的信息设置其他地方登录 */
//                userStaffLoginBookEntity.setStateExit();
//                userStaffLoginBookRepository.update(userStaffLoginBookEntity);
//            }

            /* 创建登录信息 */
//            UserStaffLoginBookEntity userStaffLoginBook = new UserStaffLoginBookEntity(userPropertyStaffEntity);
//            userStaffLoginBook.setLoginType(form);
//            userStaffLoginBook.setPhoneUUID(phoneUUID);
//            userStaffLoginBookRepository.create(userStaffLoginBook);

            /* 获取权限 */
//            RoleRoleanthorityEntity roleRoleanthorityEntity = roleAnthorityRepository.getAnthorityByStaffId(userPropertyStaffEntity.getStaffId());
//            if(roleRoleanthorityEntity == null){
//                return new ErrorApiResult("tip_00000009");
//            }

            /**
             * 第一步 根据员工ID获取对应角色列表
             * 第二步 根据角色找到相应的阶段列表
             * 第三步 根据阶段获取对应的菜单列表
             * */
            AppUserInfoDTO appUserInfoDTO = new AppUserInfoDTO();
            appUserInfoDTO.setUserId(userPropertyStaffEntity.getStaffId());              //员工ID
            appUserInfoDTO.setRealName(userPropertyStaffEntity.getStaffName());          //员工姓名
//            appUserInfoDTO.setCompany(userPropertyStaffEntity.getCompanyId());           //员工所属公司 待修改
//            appUserInfoDTO.setDepartment(userPropertyStaffEntity.getDepartmentId());     //员工所属部门 待修改
            appUserInfoDTO.setMobile(userPropertyStaffEntity.getMobile());               //联系电话
            appUserInfoDTO.setLogo(userPropertyStaffEntity.getLogo());                   //头像
            List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeByStaffId(userPropertyStaffEntity.getStaffId()); //根据员工ID获取组列表
            Set<OProjectDTO> projectDTOList = new HashSet<OProjectDTO>();
            List<HouseProjectEntity> houseProjectEntities = organizeUserRepository.getOProjectByStaff(userPropertyStaffEntity.getStaffId()); //根据员工ID从常用组获取对应项目列表
            List<HouseProjectEntity> houseProjectEntities1 = roleDataRepository.getDataByStaffId(userPropertyStaffEntity.getStaffId()); //根据员工ID获取对应项目列表
            List<HouseProjectEntity> houseProjectEntities2 = agencyRepository.getProjectsByAgency(userPropertyStaffEntity.getStaffId()); //根据员工ID从组织架构获取对应项目列表
            Set<HouseProjectEntity> houseProjectEntityList = new HashSet<HouseProjectEntity>();
            if (houseProjectEntities != null) {
                houseProjectEntityList.addAll(houseProjectEntities);
            }
            if (houseProjectEntities1 != null) {
                houseProjectEntityList.addAll(houseProjectEntities1);
            }
            if (houseProjectEntities2 != null) {
                houseProjectEntityList.addAll(houseProjectEntities2);
            }
            Map<String, String> map = new HashMap<String, String>();
            if (houseProjectEntityList != null) {
                for (HouseProjectEntity houseProjectEntity : houseProjectEntityList) {
                    map.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName());
                }
                for (String key : map.keySet()) {
                    OProjectDTO oProjectDTO = new OProjectDTO();
                    oProjectDTO.setProjectId(key);
                    oProjectDTO.setProjectName(map.get(key));
                    if (roleDataRepository.havaDispatch(userPropertyStaffEntity.getStaffId(), key, "3")) {
                        oProjectDTO.setDispatch("1");
                    } else {
                        oProjectDTO.setDispatch("0");
                    }
                    if (roleDataRepository.havaDispatch(userPropertyStaffEntity.getStaffId(), key, "4")) {
                        oProjectDTO.setCloseInvoices("1");
                    } else {
                        oProjectDTO.setCloseInvoices("0");
                    }
                    projectDTOList.add(oProjectDTO);
                }
            }
            List<ProjectProjectEntity> projectProjectEntities = new ArrayList<ProjectProjectEntity>();
            List<ProjectProjectEntity> projectProjectEntities1 = staffEmployRepository.getProjectListByStaffId(userPropertyStaffEntity.getStaffId());//获取工程阶段项目列表
            if (projectProjectEntities1 != null) {
                projectProjectEntities.addAll(projectProjectEntities1);
            }
            List<ProjectProjectEntity> projectProjectEntities2 = staffEmployRepository.getProjectListForAgency(userPropertyStaffEntity.getStaffId());
            if (projectProjectEntities2 != null) {
                projectProjectEntities.addAll(projectProjectEntities2);
            }
            List<PurviewNameDTO> purviewNameDTOs;
            List<FProjectDTO> fProjectDTOs = new ArrayList<FProjectDTO>();
            if (projectProjectEntities != null) {
                map.clear();
                for (ProjectProjectEntity projectProjectEntity : projectProjectEntities) {
                    map.put(projectProjectEntity.getId(), projectProjectEntity.getName());
                }
                for (String key : map.keySet()) {
                    purviewNameDTOs = new ArrayList<PurviewNameDTO>();
                    FProjectDTO fProjectDTO = new FProjectDTO();
                    PurviewNameDTO purviewNameDTO;
                    fProjectDTO.setProjectId(key);
                    fProjectDTO.setProjectName(map.get(key));
                    if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), key, "1")) {
                        purviewNameDTO = new PurviewNameDTO();
                        purviewNameDTO.setPurviewName("甲方");
                        purviewNameDTOs.add(purviewNameDTO);
                    }
                    if ("4".equals(staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), key))) {
                        purviewNameDTO = new PurviewNameDTO();
                        purviewNameDTO.setPurviewName("责任人");
                        purviewNameDTOs.add(purviewNameDTO);
                    } else if ("5".equals(staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), key))) {
                        purviewNameDTO = new PurviewNameDTO();
                        purviewNameDTO.setPurviewName("监理");
                        purviewNameDTOs.add(purviewNameDTO);
                    }
                    fProjectDTO.setPurviewList(purviewNameDTOs);
                    fProjectDTOs.add(fProjectDTO);
                }
            }
            appUserInfoDTO.setfProjectList(fProjectDTOs);
            appUserInfoDTO.setProjectList(projectDTOList);

            List<RoleNameDTO> roleNameDTOs = new ArrayList<RoleNameDTO>();
            Set<AppRolesetEntity> appRolesetEntities = new HashSet<AppRolesetEntity>();
            List<AppRolesetEntity> appRolesetEntities1 = appRoleSetRepository.getRoleNames(userPropertyStaffEntity.getStaffId());//根据员工ID查找角色表 获取角色列表信息
            List<AppRolesetEntity> appRolesetEntityList = organizeUserRepository.getRoleSetByStaff(userPropertyStaffEntity.getStaffId());//根据员工ID从常用组获取角色列表
            List<AppRolesetEntity> appRolesetEntityList1 = userAgencyRepository.getRoleSetFromAgency(userPropertyStaffEntity.getStaffId());//根据员工ID从组织机构获取角色列表
            List<AppRolesetEntity> appRolesetEntityList2 = roleDataRepository.getRoleSetFromData(userPropertyStaffEntity.getStaffId());//根据员工ID从配置表获取角色列表
            appRolesetEntities.addAll(appRolesetEntities1);
            appRolesetEntities.addAll(appRolesetEntityList);
            appRolesetEntities.addAll(appRolesetEntityList1);
            appRolesetEntities.addAll(appRolesetEntityList2);
            if (appRolesetEntities != null) {
                RoleNameDTO roleNameDTO;
                for (AppRolesetEntity appRolesetEntity : appRolesetEntities) {
                    List<StageDTO> stageDTOs = new ArrayList<StageDTO>();
                    roleNameDTO = new RoleNameDTO();
                    roleNameDTO.setRoleName(appRolesetEntity.getAppSetName());
                    List<AppRoleEntity> appRoleEntities = appRoleRepository.getRoleList(appRolesetEntity.getAppSetId());//根据角色ID查找权限表  获取阶段列表信息
                    if (appRoleEntities != null) {
                        StageDTO stageDTO;
                        for (AppRoleEntity appRoleEntity : appRoleEntities) {
                            stageDTO = new StageDTO();
                            stageDTO.setStageName(appRoleEntity.getAppRoleName());           //阶段名
                            List<RoleViewmodelEntity> roleViewmodelEntities = roleViewmodelRepository.getMenuList(appRoleEntity.getAppRoleId(), appRolesetEntity.getAppSetId());   //根据权限ID 查找菜单表  获取菜单列表
                            if (roleViewmodelEntities != null) {
                                List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
                                MenuDTO menuDTO;
                                for (RoleViewmodelEntity roleViewmodelEntity : roleViewmodelEntities) {
                                    menuDTO = new MenuDTO();
                                    menuDTO.setMenuName(roleViewmodelEntity.getMenuName());   //菜单名
                                    menuDTOs.add(menuDTO);
                                }
                                stageDTO.setMenuList(menuDTOs);
                            }
                            stageDTOs.add(stageDTO);
                        }
                    }
                    roleNameDTO.setStageList(stageDTOs);
                    roleNameDTOs.add(roleNameDTO);//角色名
                }
            }
            appUserInfoDTO.setRoleNameList(roleNameDTOs);
            /**
             * 添加日志
             */
            QualityLogEntity qualityLogEntity = new QualityLogEntity();
            qualityLogEntity.setLogId(IdGen.uuid());//id
            qualityLogEntity.setUserName(userPropertyStaffEntity.getUserName());//用户名
            qualityLogEntity.setStaffName(userPropertyStaffEntity.getStaffName());//姓名
            qualityLogEntity.setUserMobile(userPropertyStaffEntity.getMobile());//手机号
            qualityLogEntity.setSourceFrom("APP");//来源
            qualityLogEntity.setIpAddress(ip);//ip地址
            String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录成功";
            qualityLogEntity.setSysContent(content);//登录内容
            qualityLogEntity.setSysTime(new Date());//访问时间
            qualityLogRepository.addQualityLogInfo(qualityLogEntity);

            return new SuccessApiResult(appUserInfoDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }


    /**
     * Important Information:此接口已经作废
     * 请使用 ApiResult getStaffInfoByVestaToken(String vestaToken)
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取员工登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:30:50
     */
    @Override
    public UserPropertyStaffEntity getStaffInfoByToken(String token) {

        /* 获取系统配置参数 */
        SystemConfigEntity systemConfigEntity = systemConfigRepository.get("tokenCheckFlag");
        if (systemConfigEntity == null) {
            return null;
        }

        String staffId = "";
        if (StringUtil.isEqual(systemConfigEntity.getConfigValue(), "N")) {
            /* 本地开发，直接获取用户Id */
            SystemConfigEntity systemConfig = systemConfigRepository.get("testStaffID");
            staffId = systemConfig.getConfigValue();
        } else {
            /* 正常获取用户Id */

            if (StringUtil.isEmpty(token)) {
                return null;
            }
            /* 获取登录信息 */
            UserStaffLoginBookEntity userStaffLoginBookEntity = userStaffLoginBookRepository.get(token);
            if (userStaffLoginBookEntity == null) {
                return null;
            }
            if (!userStaffLoginBookEntity.isLogin()) {
                return null;
            }
            staffId = userStaffLoginBookEntity.getStaffId();
        }

        /* 获取用户信息 */
        return userPropertyStaffRepository.get(staffId);
    }

    /**
     * Code:Every
     * Type:UI Method
     * Describe:根据Token获取员工登录信息。
     * CreateBy:Tom
     * CreateOn:2016-03-01 11:16:54
     */
    @Override
    public ApiResult getStaffInfoByVestaToken(String vestaToken) {
        /* 获取系统配置参数 */
        SystemConfigEntity systemConfigEntity = systemConfigRepository.get("tokenCheckFlag");
        if (systemConfigEntity == null) {
            return new ErrorApiResult(10000, "系统设置失败，请联系客服。");
        }

        String staffId = "";
        if (StringUtil.isEqual(systemConfigEntity.getConfigValue(), "N")) {
            /* 本地开发，直接获取用户Id */
            SystemConfigEntity systemConfig = systemConfigRepository.get("testStaffID");
            staffId = systemConfig.getConfigValue();
            vestaToken = staffId;
        } else {
            /* 正常获取用户Id */

            if (StringUtil.isEmpty(vestaToken)) {
                return new ErrorApiResult(10002, "当前未登录。");
            }
            /* 获取登录信息 */
//            UserStaffLoginBookEntity userStaffLoginBookEntity = userStaffLoginBookRepository.get(vestaToken);
//            if(userStaffLoginBookEntity == null){
//                return new ErrorApiResult(10002 ,"当前未登录。");
//            }
//            if(!userStaffLoginBookEntity.isLogin()){
//                if(userStaffLoginBookEntity.isLoginOut()){
//                    return new ErrorApiResult(10003 ,"您的账号已在其他地方登录。");
//                }
//                return new ErrorApiResult(10002 ,"当前未登录。");
//            }
//            staffId = userStaffLoginBookEntity.getStaffId();
        }
//
//        /* 获取用户信息 */
//        UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(staffId);
//        if(userPropertyStaffEntity == null){
//            return new ErrorApiResult(10004, "用户信息错误，请联系客服。");
//        }
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(vestaToken);
        if (userPropertyStaffEntity == null) {
            return new ErrorApiResult(10002, "当前未登录。");
        }
//        return new SuccessApiResult(userPropertyStaffEntity);
        return new SuccessApiResult(userPropertyStaffEntity);
    }

    /**
     * Code:LO0002
     * Type:UI Method
     * Describe:员工登出
     * CreateBy:Tom
     * CreateOn:2016-02-20 05:15:16
     */
    @Override
    public ApiResult exit(UserPropertyStaffEntity userPropertyStaffEntity) {

        /* 查询员工有效登录信息 */
        UserStaffLoginBookEntity userStaffLoginBookEntity = userStaffLoginBookRepository.getLoginByStaffId(userPropertyStaffEntity.getStaffId());
        if (userStaffLoginBookEntity != null) {
            /* 将已经登录的信息设置其他地方登录 */
            userStaffLoginBookEntity.setNormalExit();
            userStaffLoginBookRepository.update(userStaffLoginBookEntity);

            messageTokenService.delToken(userPropertyStaffEntity.getStaffId());
        }

        return new SuccessApiResult();
    }

    /**
     * 交付app登录
     * @param username
     * @param password
     * @param ipAddress
     * @return
     */
    @Override
    public ApiResult loginForStaffToRepairApp(String username, String password, String ipAddress) {
       try {
           if (StringUtil.isEmpty(username)) {
               return new ErrorApiResult("tip_00000010");
           }
           if (StringUtil.isEmpty(password)) {
               return new ErrorApiResult("tip_00000010");
           }

           UserPropertyStaffEntity userPropertyStaffEntity = null;
                /* 查询用户信息 */
           userPropertyStaffEntity = userPropertyStaffRepository.getByUserNameAndPassword(username, password);
           if (userPropertyStaffEntity == null) {
               /**
                * 添加日志
                */
               QualityLogEntity qualityLogEntity = new QualityLogEntity();
               qualityLogEntity.setLogId(IdGen.uuid());//id
               qualityLogEntity.setUserName(username);//用户名
               qualityLogEntity.setSourceFrom("APP");//来源
               qualityLogEntity.setIpAddress(ipAddress);//ip地址
               String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录失败";
               qualityLogEntity.setSysContent(content);//登录内容
               qualityLogEntity.setSysTime(new Date());//访问时间
               qualityLogRepository.addQualityLogInfo(qualityLogEntity);

               return new ErrorApiResult("tip_00000009");
           }else {
               /**
                * 第一步 根据员工ID获取对应角色列表
                * 第二步 根据角色找到相应的阶段列表
                * 第三步 根据阶段获取对应的菜单列表
                * */
               AppUserInfoDTO appUserInfoDTO = new AppUserInfoDTO();
               appUserInfoDTO.setUserId(userPropertyStaffEntity.getStaffId());              //员工ID
               appUserInfoDTO.setRealName(userPropertyStaffEntity.getStaffName());          //员工姓名
               appUserInfoDTO.setMobile(userPropertyStaffEntity.getMobile());               //联系电话
               appUserInfoDTO.setLogo(userPropertyStaffEntity.getLogo());                   //头像

               List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeByStaffId(userPropertyStaffEntity.getStaffId()); //根据员工ID获取组列表
               List<HouseProjectEntity> houseProjectEntities = organizeUserRepository.getOProjectByStaff(userPropertyStaffEntity.getStaffId()); //根据员工ID从常用组获取对应项目列表
               List<HouseProjectEntity> houseProjectEntities1 = roleDataRepository.getDataByStaffId(userPropertyStaffEntity.getStaffId()); //根据员工ID获取对应项目列表
               List<HouseProjectEntity> houseProjectEntities2 = agencyRepository.getProjectsByAgency(userPropertyStaffEntity.getStaffId()); //根据员工ID从组织架构获取对应项目列表
               Set<OProjectDTO> projectDTOList = new HashSet<OProjectDTO>();
               Set<HouseProjectEntity> houseProjectEntityList = new HashSet<HouseProjectEntity>();
               if (houseProjectEntities != null) {
                   houseProjectEntityList.addAll(houseProjectEntities);
               }
               if (houseProjectEntities1 != null) {
                   houseProjectEntityList.addAll(houseProjectEntities1);
               }
               if (houseProjectEntities2 != null) {
                   houseProjectEntityList.addAll(houseProjectEntities2);
               }
               Map<String, String> map = new HashMap<String, String>();
               if (houseProjectEntityList != null) {
                   for (HouseProjectEntity houseProjectEntity : houseProjectEntityList) {
                       map.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName());
                   }
                   for (String key : map.keySet()) {
                       OProjectDTO oProjectDTO = new OProjectDTO();
                       oProjectDTO.setProjectId(key);
                       oProjectDTO.setProjectName(map.get(key));
                       if (roleDataRepository.havaDispatch(userPropertyStaffEntity.getStaffId(), key, "3")) {
                           oProjectDTO.setDispatch("1");
                       } else {
                           oProjectDTO.setDispatch("0");
                       }
                       if (roleDataRepository.havaDispatch(userPropertyStaffEntity.getStaffId(), key, "4")) {
                           oProjectDTO.setCloseInvoices("1");
                       } else {
                           oProjectDTO.setCloseInvoices("0");
                       }
                       projectDTOList.add(oProjectDTO);
                   }
               }
               appUserInfoDTO.setProjectList(projectDTOList);

               List<RoleNameDTO> roleNameDTOs = new ArrayList<RoleNameDTO>();
               Set<AppRolesetEntity> appRolesetEntities = new HashSet<AppRolesetEntity>();
               List<AppRolesetEntity> appRolesetEntities1 = appRoleSetRepository.getRoleNames(userPropertyStaffEntity.getStaffId());//根据员工ID查找角色表 获取角色列表信息
               List<AppRolesetEntity> appRolesetEntityList = organizeUserRepository.getRoleSetByStaff(userPropertyStaffEntity.getStaffId());//根据员工ID从常用组获取角色列表
               List<AppRolesetEntity> appRolesetEntityList1 = userAgencyRepository.getRoleSetFromAgency(userPropertyStaffEntity.getStaffId());//根据员工ID从组织机构获取角色列表
               List<AppRolesetEntity> appRolesetEntityList2 = roleDataRepository.getRoleSetFromData(userPropertyStaffEntity.getStaffId());//根据员工ID从配置表获取角色列表
               appRolesetEntities.addAll(appRolesetEntities1);
               appRolesetEntities.addAll(appRolesetEntityList);
               appRolesetEntities.addAll(appRolesetEntityList1);
               appRolesetEntities.addAll(appRolesetEntityList2);
               if (appRolesetEntities != null) {
                   RoleNameDTO roleNameDTO;
                   for (AppRolesetEntity appRolesetEntity : appRolesetEntities) {
                       List<StageDTO> stageDTOs = new ArrayList<StageDTO>();
                       roleNameDTO = new RoleNameDTO();
                       roleNameDTO.setRoleName(appRolesetEntity.getAppSetName());
                       List<AppRoleEntity> appRoleEntities = appRoleRepository.getRepairAppRoleList(appRolesetEntity.getAppSetId());//根据角色ID查找权限表  获取阶段列表信息
                       if (appRoleEntities != null) {
                           StageDTO stageDTO;
                           for (AppRoleEntity appRoleEntity : appRoleEntities) {
                               stageDTO = new StageDTO();
                               stageDTO.setStageName(appRoleEntity.getAppRoleName());           //阶段名
                               List<RoleViewmodelEntity> roleViewmodelEntities = roleViewmodelRepository.getMenuList(appRoleEntity.getAppRoleId(), appRolesetEntity.getAppSetId());   //根据权限ID 查找菜单表  获取菜单列表
                               if (roleViewmodelEntities != null) {
                                   List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
                                   MenuDTO menuDTO;
                                   for (RoleViewmodelEntity roleViewmodelEntity : roleViewmodelEntities) {
                                       menuDTO = new MenuDTO();
                                       menuDTO.setMenuName(roleViewmodelEntity.getMenuName());   //菜单名
                                       menuDTOs.add(menuDTO);
                                   }
                                   stageDTO.setMenuList(menuDTOs);
                               }
                               stageDTOs.add(stageDTO);
                           }
                       }
                       roleNameDTO.setStageList(stageDTOs);
                       roleNameDTOs.add(roleNameDTO);//角色名
                   }
               }
               appUserInfoDTO.setRoleNameList(roleNameDTOs);
               /**
                * 添加日志
                */
               QualityLogEntity qualityLogEntity = new QualityLogEntity();
               qualityLogEntity.setLogId(IdGen.uuid());//id
               qualityLogEntity.setUserName(userPropertyStaffEntity.getUserName());//用户名
               qualityLogEntity.setStaffName(userPropertyStaffEntity.getStaffName());//姓名
               qualityLogEntity.setUserMobile(userPropertyStaffEntity.getMobile());//手机号
               qualityLogEntity.setSourceFrom("APP");//来源
               qualityLogEntity.setIpAddress(ipAddress);//ip地址
               String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录成功";
               qualityLogEntity.setSysContent(content);//登录内容
               qualityLogEntity.setSysTime(new Date());//访问时间
               qualityLogRepository.addQualityLogInfo(qualityLogEntity);

               return new SuccessApiResult(appUserInfoDTO);
           }
       }catch (Exception ex) {
           ex.printStackTrace();
           throw new GeneralException("100", "系统处理错误");
       }
    }

    /**
     * 工程APP 登录
     * @param username
     * @param password
     * @param ip
     * @return
     */
    @Override
    public ApiResult loginForStaffToEngineeringApp(String username, String password, String ip) {

        try {
            if (StringUtil.isEmpty(username)) {
                return new ErrorApiResult("tip_00000010");
            }
            if (StringUtil.isEmpty(password)) {
                return new ErrorApiResult("tip_00000010");
            }

            UserPropertyStaffEntity userPropertyStaffEntity = null;
            userPropertyStaffEntity = userPropertyStaffRepository.getByUserNameAndPassword(username, password);
            if (userPropertyStaffEntity == null) {
                /**
                 * 添加日志
                 */
                QualityLogEntity qualityLogEntity = new QualityLogEntity();
                qualityLogEntity.setLogId(IdGen.uuid());//id
                qualityLogEntity.setUserName(username);//用户名
                qualityLogEntity.setSourceFrom("APP");//来源
                qualityLogEntity.setIpAddress(ip);//ip地址
                String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录失败";
                qualityLogEntity.setSysContent(content);//登录内容
                qualityLogEntity.setSysTime(new Date());//访问时间
                qualityLogRepository.addQualityLogInfo(qualityLogEntity);

                return new ErrorApiResult("tip_00000009");
            }else {
                /**
                 * 第一步 根据员工ID获取对应角色列表
                 * 第二步 根据角色找到相应的阶段列表
                 * 第三步 根据阶段获取对应的菜单列表
                 * */
                AppUserInfoDTO appUserInfoDTO = new AppUserInfoDTO();
                appUserInfoDTO.setUserId(userPropertyStaffEntity.getStaffId());              //员工ID
                appUserInfoDTO.setRealName(userPropertyStaffEntity.getStaffName());          //员工姓名
                appUserInfoDTO.setMobile(userPropertyStaffEntity.getMobile());               //联系电话
                appUserInfoDTO.setLogo(userPropertyStaffEntity.getLogo());                   //头像
                Map<String, String> map = new HashMap<String, String>();
                List<ProjectProjectEntity> projectProjectEntities = new ArrayList<ProjectProjectEntity>();
                List<ProjectProjectEntity> projectProjectEntities1 = staffEmployRepository.getProjectListByStaffId(userPropertyStaffEntity.getStaffId());//获取工程阶段项目列表
                if (projectProjectEntities1 != null) {
                    projectProjectEntities.addAll(projectProjectEntities1);
                }
                List<ProjectProjectEntity> projectProjectEntities2 = staffEmployRepository.getProjectListForAgency(userPropertyStaffEntity.getStaffId());
                if (projectProjectEntities2 != null) {
                    projectProjectEntities.addAll(projectProjectEntities2);
                }
                List<PurviewNameDTO> purviewNameDTOs;
                List<FProjectDTO> fProjectDTOs = new ArrayList<FProjectDTO>();
                if (projectProjectEntities != null) {
                    map.clear();
                    for (ProjectProjectEntity projectProjectEntity : projectProjectEntities) {
                        map.put(projectProjectEntity.getId(), projectProjectEntity.getName());
                    }
                    for (String key : map.keySet()) {
                        purviewNameDTOs = new ArrayList<PurviewNameDTO>();
                        FProjectDTO fProjectDTO = new FProjectDTO();
                        PurviewNameDTO purviewNameDTO;
                        fProjectDTO.setProjectId(key);
                        fProjectDTO.setProjectName(map.get(key));
                        if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), key, "1")) {
                            purviewNameDTO = new PurviewNameDTO();
                            purviewNameDTO.setPurviewName("甲方");
                            purviewNameDTOs.add(purviewNameDTO);
                        }
                        if ("4".equals(staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), key))) {
                            purviewNameDTO = new PurviewNameDTO();
                            purviewNameDTO.setPurviewName("责任人");
                            purviewNameDTOs.add(purviewNameDTO);
                        } else if ("5".equals(staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), key))) {
                            purviewNameDTO = new PurviewNameDTO();
                            purviewNameDTO.setPurviewName("监理");
                            purviewNameDTOs.add(purviewNameDTO);
                        }
                        fProjectDTO.setPurviewList(purviewNameDTOs);
                        fProjectDTOs.add(fProjectDTO);
                    }
                }
                appUserInfoDTO.setfProjectList(fProjectDTOs);
                List<RoleNameDTO> roleNameDTOs = new ArrayList<RoleNameDTO>();
                Set<AppRolesetEntity> appRolesetEntities = new HashSet<AppRolesetEntity>();
                List<AppRolesetEntity> appRolesetEntities1 = appRoleSetRepository.getRoleNames(userPropertyStaffEntity.getStaffId());//根据员工ID查找角色表 获取角色列表信息
                List<AppRolesetEntity> appRolesetEntityList = organizeUserRepository.getRoleSetByStaff(userPropertyStaffEntity.getStaffId());//根据员工ID从常用组获取角色列表
                List<AppRolesetEntity> appRolesetEntityList1 = userAgencyRepository.getRoleSetFromAgency(userPropertyStaffEntity.getStaffId());//根据员工ID从组织机构获取角色列表
                List<AppRolesetEntity> appRolesetEntityList2 = roleDataRepository.getRoleSetFromData(userPropertyStaffEntity.getStaffId());//根据员工ID从配置表获取角色列表
                appRolesetEntities.addAll(appRolesetEntities1);
                appRolesetEntities.addAll(appRolesetEntityList);
                appRolesetEntities.addAll(appRolesetEntityList1);
                appRolesetEntities.addAll(appRolesetEntityList2);
                if (appRolesetEntities != null) {
                    RoleNameDTO roleNameDTO;
                    for (AppRolesetEntity appRolesetEntity : appRolesetEntities) {
                        List<StageDTO> stageDTOs = new ArrayList<StageDTO>();
                        roleNameDTO = new RoleNameDTO();
                        roleNameDTO.setRoleName(appRolesetEntity.getAppSetName());
                        List<AppRoleEntity> appRoleEntities = appRoleRepository.getEngineeringAppRoleList(appRolesetEntity.getAppSetId());//根据角色ID查找权限表  获取阶段列表信息
                        if (appRoleEntities != null) {
                            StageDTO stageDTO;
                            for (AppRoleEntity appRoleEntity : appRoleEntities) {
                                stageDTO = new StageDTO();
                                stageDTO.setStageName(appRoleEntity.getAppRoleName());           //阶段名
                                List<RoleViewmodelEntity> roleViewmodelEntities = roleViewmodelRepository.getMenuList(appRoleEntity.getAppRoleId(), appRolesetEntity.getAppSetId());   //根据权限ID 查找菜单表  获取菜单列表
                                if (roleViewmodelEntities != null) {
                                    List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
                                    MenuDTO menuDTO;
                                    for (RoleViewmodelEntity roleViewmodelEntity : roleViewmodelEntities) {
                                        menuDTO = new MenuDTO();
                                        menuDTO.setMenuName(roleViewmodelEntity.getMenuName());   //菜单名
                                        menuDTOs.add(menuDTO);
                                    }
                                    stageDTO.setMenuList(menuDTOs);
                                }
                                stageDTOs.add(stageDTO);
                            }
                        }
                        roleNameDTO.setStageList(stageDTOs);
                        roleNameDTOs.add(roleNameDTO);//角色名
                    }
                }
                appUserInfoDTO.setRoleNameList(roleNameDTOs);
                /**
                 * 添加日志
                 */
                QualityLogEntity qualityLogEntity = new QualityLogEntity();
                qualityLogEntity.setLogId(IdGen.uuid());//id
                qualityLogEntity.setUserName(userPropertyStaffEntity.getUserName());//用户名
                qualityLogEntity.setStaffName(userPropertyStaffEntity.getStaffName());//姓名
                qualityLogEntity.setUserMobile(userPropertyStaffEntity.getMobile());//手机号
                qualityLogEntity.setSourceFrom("APP");//来源
                qualityLogEntity.setIpAddress(ip);//ip地址
                String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录成功";
                qualityLogEntity.setSysContent(content);//登录内容
                qualityLogEntity.setSysTime(new Date());//访问时间
                qualityLogRepository.addQualityLogInfo(qualityLogEntity);

                return new SuccessApiResult(appUserInfoDTO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

}
