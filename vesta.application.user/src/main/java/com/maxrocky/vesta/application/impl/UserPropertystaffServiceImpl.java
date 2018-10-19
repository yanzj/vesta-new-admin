package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.DTO.admin.StaffDTO;
import com.maxrocky.vesta.application.DTO.admin.UserAnthorityDTO;
import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.application.DTO.json.AppUserInfoDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.AppFeedBackDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AuthSupplierPeopleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AppRoleSetListDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.StaffReceiveDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.SMSAuthService;
import com.maxrocky.vesta.application.inf.UserAnthorityService;
import com.maxrocky.vesta.application.inf.UserPropertystaffService;
import com.maxrocky.vesta.application.jsonDTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectPeopleRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectProjectRepository;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by JillChen on 2016/1/13.
 */
@Service
public class UserPropertystaffServiceImpl implements UserPropertystaffService {

    @Autowired
    UserPropertyStaffRepository userPropertystaffReposiroty;

    @Autowired
    private HouseCompanyRepository houseCompanyRepository;//公司

    @Autowired
    private HouseSectionRepository houseSectionRepository;//部门

    @Autowired
    private RoleRolesetRepository roleRolesetRepository;//角色
    @Autowired
    private RoleAnthorityRepository roleAnthorityRepository;

    @Autowired
    private UserAnthorityService userAnthorityService;//员工角色处理
    @Autowired
    private SMSAuthService smsAuthService;
    @Autowired
    private HouseProjectRepository houseProjectRepository;//项目
    @Autowired
    private  SystemConfigRepository systemConfigRepository;//配置头像
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    RoleViewmodelRepository roleViewmodelRepository;
    @Autowired
    AppRoleSetRepository appRoleSetRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private UserFeedbackRepository userFeedbackRepository;
    @Autowired
    OrganizeRepository organizeRepository;
    @Autowired
    OrganizeUserRepository organizeUserRepository;
    @Autowired
    UserAgencyRepository userAgencyRepository;
    @Autowired
    RoleDataRepository roleDataRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    ProjectPeopleRepository projectPeopleRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;


    @Override
    public UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff) {
        UserPropertyStaffEntity propertystaff1 = userPropertystaffReposiroty.CheckStaffByIdAPwd(propertystaff);
        if(propertystaff1==null){
            return null;
        }
        return propertystaff1;
    }

    /**
     * Code:For admin login
     * Type:Server Method
     * Describe:返回指定员工ID的员工信息
     * CreateBy:Tom
     * CreateOn:2016-02-17 11:23:51
     */
    @Override
    public UserPropertyStaffEntity get(String staffId) {
        return userPropertystaffReposiroty.get(staffId);
    }

    /**
     * Code:UI0001
     * Type:UI Method
     * Describe:This is describe.
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:31:22
     */
    @Override
    public UserPropertyStaffEntity getByUserName(String userName) {
        return userPropertystaffReposiroty.getByUserName(userName);
    }

    /**
     * 获取员工列表
     * @return
     */
    @Override
    public List<UserPropertystaffDTO> listStaffDTO(UserPropertystaffDTO staffDto,WebPage webPage) {
        UserPropertyStaffEntity staff = new UserPropertyStaffEntity();
        if (staffDto.getProjectIdDto()!=null&&!staffDto.getProjectIdDto().equals("0")){//搜索项目
            staff.setProjectId(staffDto.getProjectIdDto());
        }
        if (staffDto.getAppRoleSetIdDto()!=null&&!staffDto.getAppRoleSetIdDto().equals("0")){//搜索角色
            staff.setRoleSetId(staffDto.getAppRoleSetIdDto());
        }
        if (staffDto.getUserNameDto()!=null&&!"".equals(staffDto.getUserNameDto())){//搜索用户名
            staff.setUserName(staffDto.getUserNameDto());
        }
        if (staffDto.getStaffNameDto()!=null&&!"".equals(staffDto.getStaffNameDto())){//搜索名称
            staff.setStaffName(staffDto.getStaffNameDto());
        }
//        if (staffDto.getBeginTimeDto()!=null&&!"".equals(staffDto.getBeginTimeDto())){//搜索创建开始时间，赋值给创建人
//            staff.setCreateBy(staffDto.getBeginTimeDto());
//        }
//        if (staffDto.getEndTimeDto()!=null&&!"".equals(staffDto.getEndTimeDto())){//搜索创建结束时间，赋值给修改人
//            staff.setModifyBy(staffDto.getEndTimeDto());
//        }
        List<RoleRoleanthorityEntity> roleRoleanthorityEntities = roleAnthorityRepository.getRoleanthorityList(staffDto.getAppRoleSetIdDto());
        if(roleRoleanthorityEntities!=null){
            String ids="";
            for(RoleRoleanthorityEntity roleRoleanthorityEntity:roleRoleanthorityEntities){
                ids+=","+roleRoleanthorityEntity.getStaffId();
            }
            if(!ids.equals("")){
                staff.setStaffId(ids.substring(1));
            }
        }
        List<UserPropertyStaffEntity> userPropertyStaffEntities = userPropertystaffReposiroty.listPropertyStaff(staff, webPage);

        List<UserPropertystaffDTO> userPropertystaffDTOs = new ArrayList<UserPropertystaffDTO>();
        if (userPropertyStaffEntities.size()>0){
            for (UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                UserPropertystaffDTO userPropertystaffDTO = new UserPropertystaffDTO();
                userPropertystaffDTO.setStaffIdDto(userPropertyStaffEntity.getStaffId());
                userPropertystaffDTO.setUserNameDto(userPropertyStaffEntity.getUserName());
                userPropertystaffDTO.setPasswordDto(userPropertyStaffEntity.getPassword());
                userPropertystaffDTO.setStaffNameDto(userPropertyStaffEntity.getStaffName());
                userPropertystaffDTO.setStaffStateDto(userPropertyStaffEntity.getStaffState());
                userPropertystaffDTO.setTypeDto(userPropertyStaffEntity.getType());
                userPropertystaffDTO.setMobileDto(userPropertyStaffEntity.getMobile());
                userPropertystaffDTO.setCompanyIdDto(userPropertyStaffEntity.getCompanyId());
                userPropertystaffDTO.setProjectIdDto(userPropertyStaffEntity.getProjectId());//项目Id
                userPropertystaffDTO.setSectionIdDto(userPropertyStaffEntity.getDepartmentId());
                userPropertystaffDTO.setCreateByDto(userPropertyStaffEntity.getCreateBy());
                userPropertystaffDTO.setCreateOnDto(DateUtils.format(userPropertyStaffEntity.getCreateOn(), "yyyy-MM-dd"));
                userPropertystaffDTO.setModifyByDto(userPropertyStaffEntity.getModifyBy());
                userPropertystaffDTO.setModifyOnDto(DateUtils.format(userPropertyStaffEntity.getModifyOn()));

                if (userPropertyStaffEntity.getCompanyId()!=null) {//赋值公司名称
                    HouseCompanyEntity companyEntity = houseCompanyRepository.getCompanyById(userPropertyStaffEntity.getCompanyId());
                    if (companyEntity!=null) {
                        userPropertystaffDTO.setCompanyNameDto(companyEntity.getName());
                    }

                }
                if (userPropertyStaffEntity.getProjectId()!=null){//赋值项目名称
                    HouseProjectEntity houseProjectEntity = houseProjectRepository.get(userPropertyStaffEntity.getProjectId());
                    if (houseProjectEntity!=null){
                        userPropertystaffDTO.setProjectNameDto(houseProjectEntity.getName());
                    }
                }
                if (userPropertyStaffEntity.getDepartmentId()!=null) {//赋值部门名称
                    HouseSectionEntity houseSectionEntity = houseSectionRepository.getSectionById(userPropertyStaffEntity.getDepartmentId());
                    if (houseSectionEntity!=null) {
                        userPropertystaffDTO.setSectionNameDto(houseSectionEntity.getSectionName());
                    }

                }
                if (userPropertyStaffEntity.getStaffId()!=null) {//获取角色名称
                    UserAnthorityDTO userAnthorityDTO =userAnthorityService.getUserAnthority(userPropertyStaffEntity.getStaffId());
                    if (userAnthorityDTO!=null) {
                        userPropertystaffDTO.setRoleSetNameDto(userAnthorityDTO.getRoledesc());
                        userPropertystaffDTO.setRoleSetIdDto(userAnthorityDTO.getSetId());
                        userPropertystaffDTO.setAppRoleSetIdDto(userAnthorityDTO.getAppRoleSetId());
                        userPropertystaffDTO.setAppRoleSetNameDto(userAnthorityDTO.getAppRoleSetName());
                    }

                }
                userPropertystaffDTOs.add(userPropertystaffDTO);
            }

        }

        return userPropertystaffDTOs;
    }

    /**
     * 删除员工
     * @param staffId
     * @return
     */
    @Override
    public boolean deleteStaff(String staffId,UserPropertyStaffEntity propertystaff) {
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(staffId);
        userPropertyStaffEntity.setStaffState(UserPropertyStaffEntity.State_Off);
        roleAnthorityRepository.delRoleanthority(staffId);           //清除该员工与角色的关系
        boolean result = userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);

        //删除员工日志
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setProjectId(propertystaff.getQueryScope());
        operationLogDTO.setUserName(propertystaff.getUserName());
        operationLogDTO.setContent("删除员工（"+userPropertyStaffEntity.getUserName()+")"+userPropertyStaffEntity.getStaffName());
        operationLogService.addOperationLog(operationLogDTO);
        return result;
    }

    @Override
    public void delStaffAgency(String staffId,String agencyId) {
        userAgencyRepository.deleteUserAgency(staffId,agencyId);
    }

    /**
     * 解除员工组织绑定关系
     * */



    /**
     * 添加新员工
     * @param userPropertystaffDTO
     * @return
     */
    @Override
    public UserPropertystaffDTO addStaff(UserPropertystaffDTO userPropertystaffDTO) {

        UserPropertystaffDTO result = new UserPropertystaffDTO();
        if (userPropertystaffDTO.getUserNameDto()==null||userPropertystaffDTO.getProjectIdDto()==null){
            result.setStaffResult("用户名不能为空。");
            return result;
        }
        String userName = "";
        if (userPropertystaffDTO.getTypeDto().equals("OFF")){
            userName = AppConfig.STAFF_START_IDENTIFY + userPropertystaffDTO.getUserNameDto();
        }else {
            userName = userPropertystaffDTO.getUserNameDto();
        }

        UserPropertyStaffEntity userPropertyStaff  = userPropertystaffReposiroty.testStaffByUserName(userName);
        if (userPropertyStaff!=null&&userPropertyStaff.getUserName()!=null){
            if (userPropertyStaff.getStaffState().equals(UserPropertyStaffEntity.State_On)) {
                result.setStaffResult("用户名已经注册。");
                return result;
            }
            if (userPropertyStaff.getStaffState().equals(UserPropertyStaffEntity.State_Off)) {
                userPropertyStaff.setStaffId(IdGen.getNewUserID());//id
                if (userPropertystaffDTO.getTypeDto().equals("OFF")) {
                    userPropertyStaff.setStaffId(IdGen.getNewUserID());//id
                }
                userPropertyStaff.setUserName(userPropertystaffDTO.getUserNameDto());//用户名username  1
                if (userPropertystaffDTO.getTypeDto().equals("OFF")){
                    userPropertyStaff.setUserName(AppConfig.STAFF_START_IDENTIFY+userPropertystaffDTO.getUserNameDto());
                }

                //密码默认为123456，然后加密
                String pwd = PasswordEncode.digestPassword("123456");
                userPropertyStaff.setPassword(pwd);//密码password,默认123456

                userPropertyStaff.setStaffName(userPropertystaffDTO.getStaffNameDto());//姓名Name    1
                userPropertyStaff.setStaffState(UserPropertyStaffEntity.State_On);//账号有效
                userPropertyStaff.setType(userPropertystaffDTO.getTypeDto());//编外，自建     1
                userPropertyStaff.setMobile(userPropertystaffDTO.getMobileDto());//手机    1
                userPropertyStaff.setCompanyId(userPropertystaffDTO.getCompanyIdDto());//公司Id     1
                userPropertyStaff.setProjectId(userPropertystaffDTO.getProjectIdDto());//项目ID     1
                userPropertyStaff.setQueryScope(userPropertystaffDTO.getProjectIdDto());//范围Id
                userPropertyStaff.setDepartmentId(userPropertystaffDTO.getSectionIdDto());//部门Id    1
                userPropertyStaff.setCreateBy(userPropertystaffDTO.getCreateByDto());//创建人
                userPropertyStaff.setCreateOn(DateUtils.getDate());//创建时间
                userPropertyStaff.setModifyBy(userPropertystaffDTO.getModifyByDto());//修改人
                userPropertyStaff.setModifyOn(DateUtils.getDate());//修改时间
                userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                userPropertystaffReposiroty.addStaff(userPropertyStaff);
                result.setStaffResult("注册成功。");
                return result;
            }

        }
        UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
        userPropertyStaffEntity.setStaffId(IdGen.getNewUserID());//id
        if (userPropertystaffDTO.getTypeDto().equals("OFF")) {
            userPropertyStaffEntity.setStaffId(IdGen.getNewUserID());//id
        }
        userPropertyStaffEntity.setUserName(userPropertystaffDTO.getUserNameDto());//用户名username  1
        if (userPropertystaffDTO.getTypeDto().equals("OFF")){
            userPropertyStaffEntity.setUserName(AppConfig.STAFF_START_IDENTIFY+userPropertystaffDTO.getUserNameDto());
        }

        //密码默认为123456，然后加密
        String pwd = PasswordEncode.digestPassword("123456");
        userPropertyStaffEntity.setPassword(pwd);//密码password,默认123456

        userPropertyStaffEntity.setStaffName(userPropertystaffDTO.getStaffNameDto());//姓名Name    1
        userPropertyStaffEntity.setStaffState(UserPropertyStaffEntity.State_On);//账号有效
        userPropertyStaffEntity.setType(userPropertystaffDTO.getTypeDto());//编外，自建     1
        userPropertyStaffEntity.setMobile(userPropertystaffDTO.getMobileDto());//手机    1
        userPropertyStaffEntity.setCompanyId(userPropertystaffDTO.getCompanyIdDto());//公司Id     1
        userPropertyStaffEntity.setProjectId(userPropertystaffDTO.getProjectIdDto());//项目ID     1
        userPropertyStaffEntity.setQueryScope(userPropertystaffDTO.getProjectIdDto());//范围Id
        userPropertyStaffEntity.setDepartmentId(userPropertystaffDTO.getSectionIdDto());//部门Id    1
        userPropertyStaffEntity.setCreateBy(userPropertystaffDTO.getCreateByDto());//创建人
        userPropertyStaffEntity.setCreateOn(DateUtils.getDate());//创建时间
        userPropertyStaffEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());//修改人
        userPropertyStaffEntity.setModifyOn(DateUtils.getDate());//修改时间
        userPropertyStaffEntity.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
        userPropertystaffReposiroty.addStaff(userPropertyStaffEntity);
        //添加员工日志
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setProjectId(userPropertystaffDTO.getProjectIdDto());
        operationLogDTO.setUserName(userPropertystaffDTO.getModifyByUserNameDto());
        operationLogDTO.setContent("新增员工（" + userPropertyStaffEntity.getUserName() + ")" + userPropertyStaffEntity.getStaffName());
        operationLogService.addOperationLog(operationLogDTO);

        result.setStaffResult("注册成功。");
        return result;
    }

    //根据Id查询元员工详情
    @Override
    public UserPropertystaffDTO getStaffById(String id) {
        UserPropertystaffDTO userPropertystaffDTO = new UserPropertystaffDTO();
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(id);
        if (userPropertyStaffEntity!=null){
            userPropertystaffDTO.setStaffIdDto(userPropertyStaffEntity.getStaffId());//staffId
            userPropertystaffDTO.setUserNameDto(userPropertyStaffEntity.getUserName());//userName
            userPropertystaffDTO.setPasswordDto(userPropertyStaffEntity.getPassword());//密码
            userPropertystaffDTO.setStaffNameDto(userPropertyStaffEntity.getStaffName());//姓名
            userPropertystaffDTO.setStaffStateDto(userPropertyStaffEntity.getStaffState());//状态
            userPropertystaffDTO.setTypeDto(userPropertyStaffEntity.getType());//类型
            userPropertystaffDTO.setMobileDto(userPropertyStaffEntity.getMobile());//手机号
            userPropertystaffDTO.setProjectIdDto(userPropertyStaffEntity.getProjectId());//项目Id
            userPropertystaffDTO.setSectionIdDto(userPropertyStaffEntity.getDepartmentId());//部门id
        }
        return userPropertystaffDTO;
    }

    @Override
    public ApiResult getRecentStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        AppUserInfoDTO appUserInfoDTO = new AppUserInfoDTO();
        appUserInfoDTO.setUserId(userPropertyStaffEntity.getStaffId());              //员工ID
        appUserInfoDTO.setRealName(userPropertyStaffEntity.getStaffName());          //员工姓名
//        appUserInfoDTO.setCompany(userPropertyStaffEntity.getCompanyId());           //员工所属公司 待修改
//        appUserInfoDTO.setDepartment(userPropertyStaffEntity.getDepartmentId());     //员工所属部门 待修改
        appUserInfoDTO.setMobile(userPropertyStaffEntity.getMobile());               //联系电话
        appUserInfoDTO.setLogo(userPropertyStaffEntity.getLogo());                   //头像
        List<HouseProjectEntity> houseProjectEntities = organizeUserRepository.getOProjectByStaff(userPropertyStaffEntity.getStaffId()); //根据员工ID从常用组获取对应项目列表
        List<HouseProjectEntity> houseProjectEntities1 = roleDataRepository.getDataByStaffId(userPropertyStaffEntity.getStaffId()); //根据员工ID获取对应项目列表
        List<HouseProjectEntity> houseProjectEntities2 = agencyRepository.getProjectsByAgency(userPropertyStaffEntity.getStaffId()); //根据员工ID从组织架构获取对应项目列表
        Set<HouseProjectEntity> houseProjectEntityList = new HashSet<HouseProjectEntity>();
        if(houseProjectEntities!=null){
            houseProjectEntityList.addAll(houseProjectEntities);
        }
        if(houseProjectEntities1!=null){
            houseProjectEntityList.addAll(houseProjectEntities1);
        }
        if(houseProjectEntities2!=null){
            houseProjectEntityList.addAll(houseProjectEntities2);
        }
        Set<OProjectDTO> projectDTOList = new HashSet<OProjectDTO>();
        Map<String,String> map = new HashMap<>();
        if(houseProjectEntityList!=null){
            for(HouseProjectEntity houseProjectEntity:houseProjectEntityList){
                map.put(houseProjectEntity.getPinyinCode(),houseProjectEntity.getName());
            }
            for(String key:map.keySet()){
                OProjectDTO oProjectDTO = new OProjectDTO();
                oProjectDTO.setProjectId(key);
                oProjectDTO.setProjectName(map.get(key));
                if(roleDataRepository.havaDispatch(userPropertyStaffEntity.getStaffId(),key,"3")){
                    oProjectDTO.setDispatch("1");
                }else{
                    oProjectDTO.setDispatch("0");
                }
                if(roleDataRepository.havaDispatch(userPropertyStaffEntity.getStaffId(),key,"4")){
                    oProjectDTO.setCloseInvoices("1");
                }else{
                    oProjectDTO.setCloseInvoices("0");
                }
                projectDTOList.add(oProjectDTO);
            }
        }

        List<ProjectProjectEntity> projectProjectEntities = new ArrayList<ProjectProjectEntity>();
        List<ProjectProjectEntity> projectProjectEntities1 = staffEmployRepository.getProjectListByStaffId(userPropertyStaffEntity.getStaffId());//获取工程阶段项目列表
        if(projectProjectEntities1!=null){
            projectProjectEntities.addAll(projectProjectEntities1);
        }
        List<ProjectProjectEntity> projectProjectEntities2 = staffEmployRepository.getProjectListForAgency(userPropertyStaffEntity.getStaffId());
        if(projectProjectEntities2!=null){
            projectProjectEntities.addAll(projectProjectEntities2);
        }
        List<PurviewNameDTO> purviewNameDTOs;
        List<FProjectDTO> fProjectDTOs = new ArrayList<FProjectDTO>();
        if(projectProjectEntities!=null){
            map.clear();
            for(ProjectProjectEntity projectProjectEntity:projectProjectEntities){
                map.put(projectProjectEntity.getId(), projectProjectEntity.getName());
            }
            for(String key:map.keySet()){
                purviewNameDTOs = new ArrayList<PurviewNameDTO>();
                FProjectDTO fProjectDTO = new FProjectDTO();
                PurviewNameDTO purviewNameDTO;
                fProjectDTO.setProjectId(key);
                fProjectDTO.setProjectName(map.get(key));
                if(staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(),key,"1")){
                    purviewNameDTO = new PurviewNameDTO();
                    purviewNameDTO.setPurviewName("甲方");
                    purviewNameDTOs.add(purviewNameDTO);
                }
                if("4".equals(staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), key))){
                    purviewNameDTO = new PurviewNameDTO();
                    purviewNameDTO.setPurviewName("责任人");
                    purviewNameDTOs.add(purviewNameDTO);
                }else if("5".equals(staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), key))){
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
        if(appRolesetEntities!=null){
            RoleNameDTO roleNameDTO;
            for(AppRolesetEntity appRolesetEntity:appRolesetEntities){
                List<StageDTO> stageDTOs = new ArrayList<StageDTO>();
                roleNameDTO = new RoleNameDTO();
                roleNameDTO.setRoleName(appRolesetEntity.getAppSetName());
                List<AppRoleEntity> appRoleEntities = appRoleRepository.getRoleList(appRolesetEntity.getAppSetId());//根据角色ID查找权限表  获取阶段列表信息
                if(appRoleEntities!=null){
                    StageDTO stageDTO;
                    for(AppRoleEntity appRoleEntity:appRoleEntities){
                        stageDTO = new StageDTO();
                        stageDTO.setStageName(appRoleEntity.getAppRoleName());           //阶段名
                        List<RoleViewmodelEntity> roleViewmodelEntities = roleViewmodelRepository.getMenuList(appRoleEntity.getAppRoleId(),appRolesetEntity.getAppSetId());   //根据权限ID 查找菜单表  获取菜单列表
                        if(roleViewmodelEntities!=null){
                            List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
                            MenuDTO menuDTO;
                            for(RoleViewmodelEntity roleViewmodelEntity:roleViewmodelEntities){
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
        return new SuccessApiResult(appUserInfoDTO);
    }

    //修改员工信息
    @Override
    public UserPropertystaffDTO updateStaff(UserPropertystaffDTO userPropertystaffDTO) {
        UserPropertystaffDTO result = new UserPropertystaffDTO();
        if (userPropertystaffDTO.getUserNameDto()==null||userPropertystaffDTO.getProjectIdDto()==null){
            result.setStaffResult("用户名不能为空。");
            return result;
        }
        String userName = "";
        if (userPropertystaffDTO.getTypeDto().equals("OFF")){
            userName = AppConfig.STAFF_START_IDENTIFY + userPropertystaffDTO.getUserNameDto();
        }else {
            userName = userPropertystaffDTO.getUserNameDto();
        }
        UserPropertyStaffEntity userPropertyStaff  = userPropertystaffReposiroty.testStaffByUserName(userName);

        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(userPropertystaffDTO.getStaffIdDto());
        if (userPropertyStaff!=null){
            if (!userPropertyStaff.getStaffId().equals(userPropertyStaffEntity.getStaffId())) {
                result.setStaffResult("该用户名已经注册。");
                return result;
            }
        }
//        userPropertyStaffEntity.setProjectId(userPropertystaffDTO.getProjectId());
        userPropertyStaffEntity.setDepartmentId(userPropertystaffDTO.getSectionIdDto());//部门id
        userPropertyStaffEntity.setUserName(userPropertystaffDTO.getUserNameDto());//用户名
        userPropertyStaffEntity.setStaffName(userPropertystaffDTO.getStaffNameDto());//员工姓名
        userPropertyStaffEntity.setMobile(userPropertystaffDTO.getMobileDto());//手机号
        userPropertyStaffEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());
        userPropertyStaffEntity.setModifyOn(DateUtils.getDate());
//        userPropertyStaffEntity.setPassword(userPropertystaffDTO.getPassword());
        userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);

        //修改员工日志
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setProjectId(userPropertystaffDTO.getProjectIdDto());
        operationLogDTO.setUserName(userPropertystaffDTO.getModifyByUserNameDto());
        operationLogDTO.setContent("修改员工（" + userPropertyStaffEntity.getUserName() + ")" + userPropertyStaffEntity.getStaffName());
        operationLogService.addOperationLog(operationLogDTO);
        result.setStaffResult("修改成功。");
        return result;
    }

    @Override
    public ApiResult updateAppStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);
        return new SuccessApiResult();
    }

    @Override
    public String saveStaff(StaffReceiveDTO staffDTO,UserPropertyStaffEntity userPropertyStaffEntity) {
        UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(staffDTO.getUserNameR());
        if(userPropertyStaffEntity1!=null){
            return "该用户名已被注册！";
        }
        UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
//        RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
//
//        if(!staffDTO.getCnRoleSetId().equals("0")){
//            roleRoleanthorityEntity.setUserId(IdGen.getNewUserID());
//            roleRoleanthorityEntity.setAppSetId(staffDTO.getCnRoleSetId());
//            roleRoleanthorityEntity.setStaffId(userPropertyStaffEntity.getStaffId());//绑定员工角色关联关系
//            roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
//        }

        String pwd = PasswordEncode.digestPassword("123456");
        userPropertyStaff.setStaffId(IdGen.uuid());
        userPropertyStaff.setUserName(staffDTO.getUserNameR());
        userPropertyStaff.setPassword(pwd);
        userPropertyStaff.setStaffName(staffDTO.getStaffNameR());//姓名Name
        userPropertyStaff.setStaffState(staffDTO.getStatus());//账号有效
        userPropertyStaff.setType("OFF");//编外，自建
        userPropertyStaff.setMobile(staffDTO.getUserMobile());//手机
//        userPropertyStaff.setProjectId(staffDTO.getCnProjectId());//项目ID
        userPropertyStaff.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
        userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
        userPropertyStaff.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
        userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
        userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
//        userPropertyStaff.setBirthday(SqlDateUtils.getDate(staffDTO.getCnBirthDay()));
//        userPropertyStaff.setBirthdayType(staffDTO.getCnBirthType());
        userPropertyStaff.setEmail(staffDTO.getStaffEmail());
        userPropertyStaff.setJinmaoIs(staffDTO.getJinmaoStaff());
        userPropertyStaff.setMemo(staffDTO.getStaffMemo());
        userPropertyStaff.setOrderNum(staffDTO.getOrderNumber());
        if(!StringUtil.isEmpty(staffDTO.getAgencyId())&&staffDTO.getStatus().equals("1")){
            String[] ids = staffDTO.getAgencyId().split(",");
            UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
            BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
            baseProjectPeopleEntity.setStatus("1");
            baseProjectPeopleEntity.setPeopleName(staffDTO.getStaffNameR());
            baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
            for(String id:ids){
                if("4".equals(agencyRepository.getAgencyDetail(id).getAgencyType())){  //当类型为责任单位或监理时需维护基础数据表
                    baseProjectPeopleEntity.setSupplierId(id);
                    baseProjectPeopleEntity.setSupplierName(agencyRepository.getAgencyDetail(id).getAgencyName());
                    baseProjectPeopleEntity.setSupplierType("1");
                    baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(id));
                    baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                    baseProjectPeopleEntity.setModifyTime(new Date());
                    projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                }
                if("5".equals(agencyRepository.getAgencyDetail(id).getAgencyType())){
                    baseProjectPeopleEntity.setSupplierId(id);
                    baseProjectPeopleEntity.setSupplierName(agencyRepository.getAgencyDetail(id).getAgencyName());
                    baseProjectPeopleEntity.setSupplierType("2");
                    baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(id));
                    baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                    baseProjectPeopleEntity.setModifyTime(new Date());
                    projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                }
                userAgencymapEntity.setMapId(IdGen.uuid());
                userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                userAgencymapEntity.setAgencyId(staffDTO.getAgencyId());
                userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
            }
        }
        if(staffDTO.getAppRoleSet()!=null){               //保存用户与角色关系
            String[] ids = staffDTO.getAppRoleSet().split(",");
//            List<AppRoleSetListDTO> appRoleSetListDTOs = staffDTO.getAppRoleSetList();
            for(int i=0;i<ids.length;i++){
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.getNewUserID());
                roleRoleanthorityEntity.setAppSetId(ids[i]);
                roleRoleanthorityEntity.setStaffId(userPropertyStaff.getStaffId());
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }
//        if(staffDTO.getProjectList()!=null){          //保存用户与项目关系
//            List<ProjectListDTO> projectListDTOs = staffDTO.getProjectList();
//            for(ProjectListDTO str:projectListDTOs){         //保存用户与项目的关联关系
//                RoleDataEntity roleDataEntity1 = new RoleDataEntity();
//                roleDataEntity1.setId(IdGen.uuid());
//                roleDataEntity1.setStatus("1");
//                roleDataEntity1.setAuthorityType("3");
//                roleDataEntity1.setDataType("1");
//                roleDataEntity1.setAuthorityId(userPropertyStaffEntity.getStaffId());
//                roleDataEntity1.setDataId(str.getProjectId());
//                roleDataRepository.addRoleData(roleDataEntity1);
//            }
//        }
        userPropertystaffReposiroty.addStaff(userPropertyStaff);
        return "ok";
    }

    @Override
    public String saveAuthStaff(AuthSupplierPeopleDTO authSupplierPeopleDTO, UserInformationEntity userPropertystaffEntity) {
        UserInformationEntity userInformationEntity = userPropertystaffReposiroty.getStaffBySysName(authSupplierPeopleDTO.getPhone());
        if(userInformationEntity!=null){
            userInformationEntity.setStaffName(authSupplierPeopleDTO.getStaffName1());
            String pwd = PasswordEncode.digestPassword("123456");
            userInformationEntity.setPassword(pwd);//密码
            userInformationEntity.setModifyBy(userPropertystaffEntity.getStaffId());//修改人
            userInformationEntity.setModifyOn(new Date());//修改时间
            userInformationEntity.setMemo(authSupplierPeopleDTO.getRemarks());//备注
            userInformationEntity.setStaffState("1");//人员状态
            staffEmployRepository.saveUserInformationEntity(userInformationEntity);

            UserMapEntity userMapEntity=staffEmployRepository.getUserMapEntity(userInformationEntity.getStaffId(),"es");
            if(userMapEntity!=null){
                userMapEntity.setState("1");
                userMapEntity.setModifyOn(new Date());
                staffEmployRepository.saveUserMapEntity(userMapEntity);
            }else {
                UserMapEntity userMap=new UserMapEntity();
                userMap.setStaffId(userInformationEntity.getStaffId());
                userMap.setState("1");//启用状态   0  未启用   1 已启用
                userMap.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                userMap.setModifyOn(new Date());//修改时间
                userMap.setProjectModule("es");//所属模块  st  安全   qc 交付  es 工程
                staffEmployRepository.saveUserMapEntity(userMap);
            }
            // 判断 其余两个app是否存在关联关系  不存在添加未启用关联关系
            UserMapEntity userMapEntitySt=staffEmployRepository.getUserMapEntity(userInformationEntity.getStaffId(),"st");
            if(userMapEntitySt==null){
                UserMapEntity userMapSt=new UserMapEntity();
                userMapSt.setStaffId(userInformationEntity.getStaffId());
                userMapSt.setState("0");//启用状态   0  未启用   1 已启用
                userMapSt.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                userMapSt.setModifyOn(new Date());//修改时间
                userMapSt.setProjectModule("st");//所属模块  st  安全   qc 交付  es 工程
                staffEmployRepository.saveUserMapEntity(userMapSt);
            }
            UserMapEntity userMapEntityQc=staffEmployRepository.getUserMapEntity(userInformationEntity.getStaffId(),"qc");
            if(userMapEntityQc==null){
                UserMapEntity userMapQc=new UserMapEntity();
                userMapQc.setStaffId(userInformationEntity.getStaffId());
                userMapQc.setState("0");//启用状态   0  未启用   1 已启用
                userMapQc.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                userMapQc.setModifyOn(new Date());//修改时间
                userMapQc.setProjectModule("qc");//所属模块  st  安全   qc 交付  es 工程
                staffEmployRepository.saveUserMapEntity(userMapQc);
            }
            //检验当前责任单位和人员关系是否存在
            if(staffEmployRepository.getCheckUserAgencyEntity(authSupplierPeopleDTO.getSupplierId(),userInformationEntity.getStaffId(),"1")){
                //校验是否绑定过
                //是否已经绑定
                UserAgencyEntity userAgencyEntity=staffEmployRepository.getUserAgencyEntity(authSupplierPeopleDTO.getSupplierId(),userInformationEntity.getStaffId());
                if(userAgencyEntity!=null){

                }else{
                    userAgencyEntity=new UserAgencyEntity();
                    userAgencyEntity.setMapId(IdGen.uuid()); //主键
                }
                userAgencyEntity.setStaffId(userInformationEntity.getStaffId());//用户ID
                userAgencyEntity.setAgencyId(authSupplierPeopleDTO.getSupplierId()); //责任单位机构ID
                userAgencyEntity.setModifyTime(new Date()); //修改时间
                userAgencyEntity.setStatus("1");
                userAgencyEntity.setSourceFrom("external");
                staffEmployRepository.saveOrUpdateuserAgencyEntity(userAgencyEntity);
            }
            if(!"0".equals(authSupplierPeopleDTO.getStatus())){

                //检验是否存在  项目+责任单位+人 关联关系
                if(staffEmployRepository.getCheckBaseProjectPeopleEntity(authSupplierPeopleDTO.getSupplierId(),userInformationEntity.getStaffId(),authSupplierPeopleDTO.getAgencyId())){
                    // 不存在 则保存 三者关系表 项目  责任单位  人员
                    BaseProjectPeopleEntity getBaseProjectPeopleEntity=staffEmployRepository.getBaseProjectByProjectId(authSupplierPeopleDTO.getAgencyId(),authSupplierPeopleDTO.getSupplierId());
                    if(getBaseProjectPeopleEntity!=null){
                        BaseProjectPeopleEntity baseProjectPeople=new BaseProjectPeopleEntity();
                        baseProjectPeople.setPeopleId(userInformationEntity.getStaffId());//人员ID
                        baseProjectPeople.setPeopleName(userInformationEntity.getStaffName());//人员名称
                        baseProjectPeople.setSupplierId(authSupplierPeopleDTO.getSupplierId());  //责任单位或监理ID
                        baseProjectPeople.setSupplierName(getBaseProjectPeopleEntity.getSupplierName());   //责任单位名称
                        baseProjectPeople.setSupplierType(getBaseProjectPeopleEntity.getSupplierType());  //类型 1总包  2分包   3.监理
                        baseProjectPeople.setProjectId(authSupplierPeopleDTO.getAgencyId());  //项目ID
                        baseProjectPeople.setProjectName(getBaseProjectPeopleEntity.getProjectName()); //项目名
                        baseProjectPeople.setModifyTime(new Date());  //修改时间
                        baseProjectPeople.setStatus("1"); //状态 0删除 1正常
                        baseProjectPeople.setAbbreviationName(getBaseProjectPeopleEntity.getAbbreviationName());//责任单位简称
                        staffEmployRepository.addbaseProjectPeople(baseProjectPeople);
                    }
                }else{
                    staffEmployRepository.upBaseProjectPeopleName(userInformationEntity.getStaffId(),userInformationEntity.getStaffName(),"","","");
                }
                //先删除当前项目下 当前人员绑定的 三者关系白哦
                staffEmployRepository.delAgencyRoleUser(authSupplierPeopleDTO.getAgencyId(),userInformationEntity.getStaffId(),"","0");
                //根据角色 保存关联关系
                if(!StringUtil.isEmpty(authSupplierPeopleDTO.getRoleAgencyId())){
                    String str[] = authSupplierPeopleDTO.getRoleAgencyId().split(",");
                    List<String> roleIdList=Arrays.asList(str);
                    for(String roleId:roleIdList){
                        if(!StringUtil.isEmpty(roleId)){
                            RoleStaffProjectMapESEntity roleStaffProjectMapES=new RoleStaffProjectMapESEntity();
                            roleStaffProjectMapES.setAgencyId(authSupplierPeopleDTO.getAgencyId());
                            roleStaffProjectMapES.setRoleId(roleId);
                            roleStaffProjectMapES.setStaffId(userInformationEntity.getStaffId());
                            roleStaffProjectMapES.setState("1");
                            roleStaffProjectMapES.setModifyOn(new Date());
                            staffEmployRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapES);
                        }
                    }
                }
            }else{
                //删除 原有的责任单位和人员关联关系
                /*
                //马强 定需求  不删除责任单位和人员关系
                UserAgencyEntity userAgency = new UserAgencyEntity();
                userAgency.setAgencyId(authSupplierPeopleDTO.getSupplierId());
                userAgency.setStaffId(authSupplierPeopleDTO.getUserId());
                userAgency.setModifyTime(new Date());
                userAgencyRepository.delAuthUserAgency(userAgency);         //删除原有关联数据
                */

                //删除 人员-责任单位-项目关联关系表
                staffEmployRepository.deleteBaseProjectPeople(authSupplierPeopleDTO.getAgencyId(),authSupplierPeopleDTO.getSupplierId(),authSupplierPeopleDTO.getUserId1(),"","","","0");
                // 删除 人员-角色-项目三者授权表
                staffEmployRepository.delAgencyRoleUser("",authSupplierPeopleDTO.getUserId1(),"","0");

            }
            return "ok1";
        }else {
            UserInformationEntity userInformation=new UserInformationEntity();
            userInformation.setStaffId(IdGen.uuid());//员工ID
            String pwd = PasswordEncode.digestPassword("123456");
            userInformation.setPassword(pwd);//密码
            userInformation.setSysName(authSupplierPeopleDTO.getPhone());//系统账号   系统登录账号
            userInformation.setStaffName(authSupplierPeopleDTO.getStaffName1());//名称
            userInformation.setStaffState("1");//状态  0 停用  1 启用
            userInformation.setMobile(authSupplierPeopleDTO.getPhone());
            userInformation.setCreateBy(userPropertystaffEntity.getStaffId());//创建人
            userInformation.setCreateOn(new Date());//创建时间
            userInformation.setModifyBy(userPropertystaffEntity.getStaffId());//修改人
            userInformation.setModifyOn(new Date());//修改时间
            userInformation.setEmail(authSupplierPeopleDTO.getMailbox());//邮件
            userInformation.setMemo(authSupplierPeopleDTO.getRemarks());//备注
            userInformation.setJinmaoIs("0");
//            userInformation.setStaffState(authSupplierPeopleDTO.getStatus());//人员为关闭状态
            staffEmployRepository.saveUserInformationEntity(userInformation);

            UserMapEntity userMapEntity=new UserMapEntity();
            userMapEntity.setStaffId(userInformation.getStaffId());
            userMapEntity.setState("1");//启用状态   0  未启用   1 已启用
            userMapEntity.setSourceFrom("external");  //来源 OA:同步 external:本地创建
            userMapEntity.setModifyOn(new Date());//修改时间
            userMapEntity.setProjectModule("es");//所属模块  st  安全   qc 交付  es 工程
            staffEmployRepository.saveUserMapEntity(userMapEntity);

            //安全
            UserMapEntity userMapEntitySt=new UserMapEntity();
            userMapEntitySt.setStaffId(userInformation.getStaffId());
            userMapEntitySt.setState("0");
            userMapEntitySt.setSourceFrom("external");  //来源 OA:同步 external:本地创建
            userMapEntitySt.setModifyOn(new Date());//修改时间
            userMapEntitySt.setProjectModule("st");//所属模块  st  安全   qc 交付  es 工程
            staffEmployRepository.saveUserMapEntity(userMapEntitySt);

            //客观
            UserMapEntity userMapEntityQc=new UserMapEntity();
            userMapEntityQc.setStaffId(userInformation.getStaffId());
            userMapEntityQc.setState("1");
            userMapEntityQc.setSourceFrom("external");  //来源 OA:同步 external:本地创建
            userMapEntityQc.setModifyOn(new Date());//修改时间
            userMapEntityQc.setProjectModule("qc");//所属模块  st  安全   qc 交付  es 工程
            staffEmployRepository.saveUserMapEntity(userMapEntityQc);

            //保存 责任单位和人员关联关系
            UserAgencyEntity userAgencyEntity=new UserAgencyEntity();
            userAgencyEntity.setMapId(IdGen.uuid()); //主键
            userAgencyEntity.setStaffId(userInformation.getStaffId());//用户ID
            userAgencyEntity.setAgencyId(authSupplierPeopleDTO.getSupplierId()); //责任单位机构ID
            userAgencyEntity.setModifyTime(new Date()); //修改时间
            userAgencyEntity.setStatus("1");
            userAgencyEntity.setSourceFrom("external");
            staffEmployRepository.saveOrUpdateuserAgencyEntity(userAgencyEntity);

            if(!"0".equals(authSupplierPeopleDTO.getStatus())){ //非停用状态
                //先查询责任单位和项目id 的关联
                BaseProjectPeopleEntity getBaseProjectPeopleEntity=staffEmployRepository.getBaseProjectByProjectId(authSupplierPeopleDTO.getAgencyId(),authSupplierPeopleDTO.getSupplierId());
                if(getBaseProjectPeopleEntity!=null){
                    // 保存 三者关系表 项目  责任单位  人员
                    BaseProjectPeopleEntity baseProjectPeople=new BaseProjectPeopleEntity();
                    baseProjectPeople.setPeopleId(userInformation.getStaffId());//人员ID
                    baseProjectPeople.setPeopleName(userInformation.getStaffName());//人员名称
                    baseProjectPeople.setSupplierId(authSupplierPeopleDTO.getSupplierId());  //责任单位或监理ID
                    baseProjectPeople.setSupplierName(getBaseProjectPeopleEntity.getSupplierName());   //责任单位名称
                    baseProjectPeople.setSupplierType(getBaseProjectPeopleEntity.getSupplierType());  //类型 1总包  2分包   3.监理
                    baseProjectPeople.setProjectId(authSupplierPeopleDTO.getAgencyId());  //项目ID
                    baseProjectPeople.setProjectName(getBaseProjectPeopleEntity.getProjectName()); //项目名
                    baseProjectPeople.setModifyTime(new Date());  //修改时间
                    baseProjectPeople.setStatus("1"); //状态 0删除 1正常
                    baseProjectPeople.setAbbreviationName(getBaseProjectPeopleEntity.getAbbreviationName());//责任单位简称
                    staffEmployRepository.addbaseProjectPeople(baseProjectPeople);

                }
                //根据角色 保存关联关系
                if(!StringUtil.isEmpty(authSupplierPeopleDTO.getRoleAgencyId())){
                    String str[] = authSupplierPeopleDTO.getRoleAgencyId().split(",");
                    List<String> roleIdList=Arrays.asList(str);
                    for(String roleId:roleIdList){
                        if(!StringUtil.isEmpty(roleId)){
                            RoleStaffProjectMapESEntity roleStaffProjectMapES=new RoleStaffProjectMapESEntity();
                            roleStaffProjectMapES.setAgencyId(authSupplierPeopleDTO.getAgencyId());
                            roleStaffProjectMapES.setRoleId(roleId);
                            roleStaffProjectMapES.setStaffId(userInformation.getStaffId());
                            roleStaffProjectMapES.setState("1");
                            roleStaffProjectMapES.setModifyOn(new Date());
                            staffEmployRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapES);
                        }
                    }
                }
            }
        }
        return "ok";
    }
    @Override
    public String alterStaff(StaffReceiveDTO staffDTO,UserPropertyStaffEntity userPropertyStaffEntity) {
        UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByNameID(staffDTO.getUserNameR(), staffDTO.getStaffIdR());
        if(userPropertyStaffEntity1!=null){
            return "该用户名已被注册！";
        }
        UserPropertyStaffEntity userPropertyStaff = userPropertystaffReposiroty.get(staffDTO.getStaffIdR());
//        if(!staffDTO.getCnPassword().equals(userPropertyStaffEntity.getPassword())){  //如果当前密码和用户密码不相同 说明修改了密码 则需要加密
//            String pwd = PasswordEncode.digestPassword(staffDTO.getCnPassword());
//            userPropertyStaff.setPassword(pwd);
//        }
        if("1".equals(staffDTO.getStatus())){   //当此人为正常情况下时
            List<UserAgencymapEntity> userAgencymapEntities = userAgencyRepository.getUserAgencymap(userPropertyStaff.getStaffId());//查询数据库已存在的数据
            List<String> compair1 = new ArrayList<String>();
            List<String> compair2 = new ArrayList<String>();
            List<String> compair3 = new ArrayList<String>();
            Iterator<String> it1;
            Iterator<String> it2;
            if(userAgencymapEntities!=null){
                for(UserAgencymapEntity userAgencymapEntity2:userAgencymapEntities){
                    compair1.add(userAgencymapEntity2.getAgencyId());
                }
            }
            if(!StringUtil.isEmpty(staffDTO.getAgencyId())){
                String[] ids = staffDTO.getAgencyId().split(",");
                for(int i = 0; i <ids.length;i++){
                    compair2.add(ids[i]);      //将页面传来的数据存放于compair2
                }
            }
            compair3.addAll(compair1);
            compair1.removeAll(compair2);//比较后 为待删除的数据
            it1 = compair1.iterator();
            UserAgencymapEntity userAgencymapEntity1 = new UserAgencymapEntity();
            while (it1.hasNext()){
                AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(it1.next());
                if("4".equals(agencyEntity.getAgencyType())||"5".equals(agencyEntity.getAgencyType())){  //当类型为责任单位或监理时需维护基础数据表
                    projectPeopleRepository.delProjectPeople(agencyEntity.getAgencyId());   //删除基础人员数据
                }
                userAgencymapEntity1.setAgencyId(agencyEntity.getAgencyId());
                userAgencymapEntity1.setStaffId(userPropertyStaff.getStaffId());
                userAgencymapEntity1.setModifyTime(new Date());
                userAgencyRepository.delUserAgency(userAgencymapEntity1);         //删除原有关联数据
            }
            compair2.removeAll(compair3);//比较后 待新增的数据
            it2 = compair2.iterator();
            UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();

            BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
            baseProjectPeopleEntity.setStatus("1");
            baseProjectPeopleEntity.setPeopleName(staffDTO.getStaffNameR());
            baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
            projectPeopleRepository.updateForStaffName(staffDTO.getStaffNameR(),userPropertyStaff.getStaffId());
            while (it2.hasNext()){
                AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(it2.next());
                if("4".equals(agencyEntity.getAgencyType())){  //当类型为责任单位或监理时需维护基础数据表
                    baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                    baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                    baseProjectPeopleEntity.setSupplierType("1");
                    baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                    baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                    baseProjectPeopleEntity.setModifyTime(new Date());
                    projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                }
                if("5".equals(agencyEntity.getAgencyType())){
                    baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                    baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                    baseProjectPeopleEntity.setSupplierType("2");
                    baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                    baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                    baseProjectPeopleEntity.setModifyTime(new Date());
                    projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                }
                userAgencymapEntity.setMapId(IdGen.uuid());
                userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                userAgencymapEntity.setAgencyId(agencyEntity.getAgencyId());
                userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
            }
        }else { //否则删除此人  并解除此人与组织架构关系、群组关系、项目关系
            userAgencyRepository.deleteUserAgency(staffDTO.getStaffIdR());         //删除人与组织架构关系
            RoleDataEntity roleDataEntity = new RoleDataEntity();
            roleDataEntity.setAuthorityType("3");
            roleDataEntity.setAuthorityId(staffDTO.getStaffIdR());
            roleDataEntity.setModifyTime(new Date());
            roleDataRepository.delOrganizeRoleData(roleDataEntity);                 //删除人与项目关系
            organizeUserRepository.delUserOrganize(staffDTO.getStaffIdR());         //删除人与群组关系
            projectPeopleRepository.delProjectPeople(staffDTO.getStaffIdR());       //删除基础表数据
            staffEmployRepository.delProjectRole(staffDTO.getStaffIdR());          // 删除人与工程项目关系
        }

        userPropertyStaff.setUserName(staffDTO.getUserNameR());
        userPropertyStaff.setStaffName(staffDTO.getStaffNameR());//姓名Name
        userPropertyStaff.setMobile(staffDTO.getUserMobile());//手机
        userPropertyStaff.setJinmaoIs(staffDTO.getJinmaoStaff());
        userPropertyStaff.setOrderNum(staffDTO.getOrderNumber());
        userPropertyStaff.setMemo(staffDTO.getStaffMemo());
        userPropertyStaff.setStaffState(staffDTO.getStatus());
//        userPropertyStaff.setOpenMobile(staffDTO.getCnOpenMobile());
        userPropertyStaff.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
        userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
//        userPropertyStaff.setBirthday(SqlDateUtils.getDate(staffDTO.getCnBirthDay()));
        userPropertyStaff.setEmail(staffDTO.getStaffEmail());

        if(!StringUtil.isEmpty(staffDTO.getAppRoleSet())){               //保存用户与角色关系
            List<AppRoleSetListDTO> appRoleSetListDTOs = staffDTO.getAppRoleSetList();
            roleAnthorityRepository.delRoleanthority(userPropertyStaffEntity.getStaffId());   //更新前 先清除该用户下的角色关联关系
            String[] ids = staffDTO.getAppRoleSet().split(",");
            for(String str:ids){
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.getNewUserID());
                roleRoleanthorityEntity.setAppSetId(str);
                roleRoleanthorityEntity.setStaffId(userPropertyStaff.getStaffId());
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }
        userPropertystaffReposiroty.updateStaff(userPropertyStaff);
        return "ok";
    }

    @Override
    public String alterAuthStaff(AuthSupplierPeopleDTO authSupplierPeopleDTO, UserInformationEntity userPropertystaffEntity) {
        UserInformationEntity userInformation = userPropertystaffReposiroty.getUser(authSupplierPeopleDTO.getUserId1());
        if("0".equals(authSupplierPeopleDTO.getStatus())){ //停用状态
//            userInformation.setStaffState("0");//人员为关闭状态
//            staffEmployRepository.saveUserInformationEntity(userInformation);
            //删除 原有的责任单位和人员关联关系\

            /**
             *  //马强定需求  不删除 责任单位关联关系
            UserAgencyEntity userAgency = new UserAgencyEntity();
            userAgency.setAgencyId(authSupplierPeopleDTO.getSupplierId());
            userAgency.setStaffId(authSupplierPeopleDTO.getUserId());
            userAgency.setModifyTime(new Date());
            userAgencyRepository.delAuthUserAgency(userAgency);         //删除原有关联数据
             */
            //人员启动表删除关系
            /*
            //马强定需求  不删除 人员启动表
            userAgencyRepository.delUserMapEntity(userInformation.getStaffId());
            */
            //删除 人员-责任单位-项目关联关系表
            staffEmployRepository.deleteBaseProjectPeople(authSupplierPeopleDTO.getAgencyId(),authSupplierPeopleDTO.getSupplierId(),authSupplierPeopleDTO.getUserId1(),"","","","0");
            // 删除 人员-角色-项目三者授权表
            staffEmployRepository.delAgencyRoleUser("",authSupplierPeopleDTO.getUserId1(),"","0");
        }else{
            UserInformationEntity userInformation1 = userPropertystaffReposiroty.getAuthByNameID(authSupplierPeopleDTO.getPhone(), authSupplierPeopleDTO.getUserId1());
            if(userInformation1!=null){
                return "该手机号已被注册！";
            }
            userInformation.setStaffName(authSupplierPeopleDTO.getStaffName1());
            userInformation.setMobile(authSupplierPeopleDTO.getPhone());
            userInformation.setEmail(authSupplierPeopleDTO.getMailbox());
            userInformation.setMemo(authSupplierPeopleDTO.getRemarks());
            staffEmployRepository.saveUserInformationEntity(userInformation);
            //修改关联关系名称
            staffEmployRepository.upBaseProjectPeopleName(userInformation.getStaffId(),userInformation.getStaffName(),"","","");
            //先删除当前项目下 当前人员绑定的 三者关系白哦
            staffEmployRepository.delAgencyRoleUser(authSupplierPeopleDTO.getAgencyId(),authSupplierPeopleDTO.getUserId1(),"","0");
            //根据角色 保存关联关系
            if(!StringUtil.isEmpty(authSupplierPeopleDTO.getRoleAgencyId())){
                String str[] = authSupplierPeopleDTO.getRoleAgencyId().split(",");
                List<String> roleIdList=Arrays.asList(str);
                for(String roleId:roleIdList){
                    if(!StringUtil.isEmpty(roleId)){
                        RoleStaffProjectMapESEntity roleStaffProjectMapES=new RoleStaffProjectMapESEntity();
                        roleStaffProjectMapES.setAgencyId(authSupplierPeopleDTO.getAgencyId());
                        roleStaffProjectMapES.setRoleId(roleId);
                        roleStaffProjectMapES.setStaffId(authSupplierPeopleDTO.getUserId1());
                        roleStaffProjectMapES.setState("1");
                        roleStaffProjectMapES.setModifyOn(new Date());
                        staffEmployRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapES);
                    }
                }
            }
        }
        return "ok";
    }

    @Override
    public void altPassword(StaffDTO staffDTO) {
        UserPropertyStaffEntity userPropertyStaff = userPropertystaffReposiroty.get(staffDTO.getCnStaffId());
        String pwd = PasswordEncode.digestPassword(staffDTO.getCnPassword());
        userPropertyStaff.setPassword(pwd);
        userPropertystaffReposiroty.updateStaff(userPropertyStaff);
    }

    @Override
    public boolean removeStaff(String staffId) {

        return true;
    }

    @Override
    public StaffDTO getStaffDetail(String staffId) {
        StaffDTO staffDTO = new StaffDTO();
        if(staffId!=null){
            UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(staffId);
            staffDTO.setCnBirthDay(String.valueOf(userPropertyStaffEntity.getBirthday()));
            staffDTO.setCnBirthType(userPropertyStaffEntity.getBirthdayType());
            staffDTO.setCnOfficePhone(userPropertyStaffEntity.getOfficePhone());
            staffDTO.setCnOpenMobile(userPropertyStaffEntity.getOpenMobile());
            staffDTO.setCnStaffMobile(userPropertyStaffEntity.getMobile());
            List<RoleRoleanthorityEntity> roleanthoritys = roleAnthorityRepository.getRoleanthoritys(staffId);
            if(roleanthoritys!=null&&roleanthoritys.size()>0){
                staffDTO.setCnRoleSetId(roleanthoritys.get(0).getAppSetId());
            }
            List<AgencyEntity> agencyEntities = userAgencyRepository.getAgencysByStaffId(staffId);
            List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
            if(agencyEntities!=null){
                AgencyDTO agencyDTO;
                for(AgencyEntity agencyEntity:agencyEntities){
                    agencyDTO = new AgencyDTO();
                    agencyDTO.setAgencyId(agencyEntity.getAgencyId());
                    AgencyEntity agencyEntity1 = agencyRepository.getAgency(agencyEntity.getParentId());
                    agencyDTO.setAgencyName(agencyEntity.getAgencyName());
                    if(agencyEntity1!=null){
                        agencyDTO.setAgencyName(agencyEntity.getAgencyName()+"-"+agencyEntity1.getAgencyName());
                    }
                    agencyDTOs.add(agencyDTO);
                }
            }
            staffDTO.setStaffAgency(agencyDTOs);
            staffDTO.setCnProjectId(userPropertyStaffEntity.getProjectId());
            staffDTO.setCnPassword(userPropertyStaffEntity.getPassword());
            staffDTO.setCnStaffName(userPropertyStaffEntity.getStaffName());
            staffDTO.setCnStaffId(userPropertyStaffEntity.getStaffId());
            staffDTO.setCnStaffSex(userPropertyStaffEntity.getSex());
            staffDTO.setCnUserName(userPropertyStaffEntity.getUserName());
            staffDTO.setCnPassword(userPropertyStaffEntity.getPassword());
            staffDTO.setCnStaffEmail(userPropertyStaffEntity.getEmail());
            staffDTO.setCnModifyTime(DateUtils.format(userPropertyStaffEntity.getModifyOn()));
            staffDTO.setJinmaoIs(userPropertyStaffEntity.getJinmaoIs());
            staffDTO.setOrderNum(userPropertyStaffEntity.getOrderNum());
            staffDTO.setMemo(userPropertyStaffEntity.getMemo());
            staffDTO.setStatus(userPropertyStaffEntity.getStaffState());
        }else {
            staffDTO.setCnPassword("111111");
        }
        return staffDTO;
    }

    @Override
    public ApiResult foundPassword(String userName, String mobile, String phoneCode) {
        //验证短信验证码
        Boolean checkAuthCode = smsAuthService.getSMSAuthByPhoneAndType(mobile
                , phoneCode
                , SMSAuthEntity.TYPE_PASSWORD_R);
        if(!checkAuthCode) {
            return new ErrorApiResult("tip_00000467");
        }
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.getByUserNameAndMobile(userName, phoneCode);
        if(userPropertyStaffEntity!=null){
            return new SuccessApiResult();
        }
        return new ErrorApiResult("用户名或手机号错误");
    }

    @Override
    public void saveFeedBack(List<AppFeedBackDTO> feedbacks,UserPropertyStaffEntity userPropertyStaffEntity) {
        UserFeedbackEntity userFeedbackEntity;
        for(AppFeedBackDTO feedbackParamJsonDTO:feedbacks){
            userFeedbackEntity = new UserFeedbackEntity();
            userFeedbackEntity.setFeedBackType("2");
            userFeedbackEntity.setId(IdGen.uuid());
            userFeedbackEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
            userFeedbackEntity.setCreateOn(new Date());
            userFeedbackEntity.setContent(feedbackParamJsonDTO.getContent());
            userFeedbackEntity.setMobile(feedbackParamJsonDTO.getMobile());
            userFeedbackEntity.setState("1");
            userFeedbackEntity.setUserId(userPropertyStaffEntity.getStaffId());
            userFeedbackEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
            userFeedbackEntity.setModifyOn(new Date());
            userFeedbackRepository.create(userFeedbackEntity);
        }
    }

    @Override
    public List<StaffNameDTO> searchStaffByName(String staffName) {
        List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
        StaffNameDTO staffNameDTO;
        List<UserPropertyStaffEntity> userPropertyStaffEntities = userPropertystaffReposiroty.searchStaffByName(staffName);
        if(userPropertyStaffEntities!=null){
            for(UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                staffNameDTO = new StaffNameDTO();
                staffNameDTO.setStaffId(userPropertyStaffEntity.getStaffId());
                staffNameDTO.setStaffName(userPropertyStaffEntity.getStaffName()+" ("+userPropertyStaffEntity.getUserName()+")");
                staffNameDTO.setUserName(userPropertyStaffEntity.getUserName());
                staffNameDTOList.add(staffNameDTO);
            }
        }
        return staffNameDTOList;
    }

    @Override
    public UserInformationEntity CheckUserInforByIdAPwd(UserInformationEntity userInfor,String checkLogin) {
        UserInformationEntity userStaff = userPropertystaffReposiroty.CheckUserStaffByIdAPwd(userInfor);
        if(userStaff!=null && !StringUtil.isEmpty(checkLogin)){
            if(userPropertystaffReposiroty.getuserMapById(userStaff.getStaffId(),checkLogin)){
                return userStaff;
            }
        }
        return null;

    }

    @Override
    public UserInformationEntity CheckAuthUserInforByIdAPwd(UserInformationEntity userInfor) {
        UserInformationEntity userStaff = userPropertystaffReposiroty.CheckUserStaffByIdAPwd(userInfor);
        if(userStaff!=null ){
            return userStaff;
        }
        return null;
    }

    @Override
    public boolean getCheckUserMapById(String staffId, String checkLogin) {
        if(userPropertystaffReposiroty.getuserMapById(staffId,checkLogin)){
            return true;
        }
        return false;
    }


    /**
     * 获取万达内部员工详情
     * @param userName
     * @return
     */
    /*@Override
    public UserWandaStaffDTO getWandaStaffByUserName(String userName) {
        UserWandaStaffDTO userWandaStaffDTO = new UserWandaStaffDTO();
        EhrStaffEntity ehrStaffEntity = userWandaStaffRepository.getEhrWandaByUserName(userName);
        if (ehrStaffEntity==null){
            userWandaStaffDTO.setStaffName("NoMatch");//无匹配数据
            return userWandaStaffDTO;
        }
        userWandaStaffDTO.setWandastaffId(ehrStaffEntity.getEmployeeId());
        userWandaStaffDTO.setStaffName(ehrStaffEntity.getEmployeeName());
        userWandaStaffDTO.setMobile(ehrStaffEntity.getMobile());
        return userWandaStaffDTO;
    }*/
}
