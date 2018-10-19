package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.application.inf.RoleRoleService;
import com.maxrocky.vesta.application.inf.UserAnthorityService;
import com.maxrocky.vesta.application.DTO.admin.UserAnthorityDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.PasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administratord on 2016/1/15.
 * 物业项目公司 业务逻辑层实现类
 */
@Service
public class PropertyCompanyServiceImpl implements PropertyCompanyService {

    /**
     * 物业项目公司 持久层接口
     */
    @Autowired
    PropertyCompanyRepository propertyCompanyRepository;
    /**
     * 系统管理 权限业务逻辑层接口
     */
    @Resource
    private RoleRoleService roleRoleService;

    @Resource
    UserAnthorityService userAnthorityService;

    @Autowired
    private HouseSectionRepository houseSectionRepository;

    @Autowired
    UserPropertyStaffRepository userPropertystaffReposiroty;

    @Autowired
    RoleAnthorityRepository roleAnthorityRepository;
    @Autowired
    SystemConfigRepository systemConfigRepository;

    /**
     *  后台核心日志 业务逻辑层接口
     */
    @Autowired
    private OperationLogService operationLogService;


    /**
     * 初始化物业管理公司列表
     * @param propertyCompanySearchDTO
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyCompanyDTO> queryPropertyCompanyMessage(PropertyCompanyDTO propertyCompanySearchDTO, WebPage webPage) {
        List<PropertyCompanyDTO> propertyCompanyDTOList = new ArrayList<>();//物业管理公司DTO集合
        PropertyCompanyEntity propertyCompanyEntity = new PropertyCompanyEntity();//物业管理公司实体表
        /*搜索条件*/
        propertyCompanyEntity.setPropertyArea(propertyCompanySearchDTO.getPropertyArea());//区域
        propertyCompanyEntity.setCompanyName(propertyCompanySearchDTO.getCompanyName());//公司
        propertyCompanyEntity.setPropertyProject(propertyCompanySearchDTO.getPropertyProject());//项目
        propertyCompanyEntity.setProjectAdmin(propertyCompanySearchDTO.getProjectAdmin());// 管理员
        // 查询物业管理公司数据列表
        List<PropertyCompanyEntity> propertyCompanyEntityList = propertyCompanyRepository.queryPropertyCompanyMessage(propertyCompanyEntity,webPage);
        for (PropertyCompanyEntity companyMessage : propertyCompanyEntityList) {
            PropertyCompanyDTO propertyCompanyDTO = new PropertyCompanyDTO();//物业管理公司DTO
            propertyCompanyDTO.setCompanyId(companyMessage.getCompanyId());//  ID
            // 根据公司ID查询公司名称
            List<HouseCompanyEntity> houseCompanyEntities =  propertyCompanyRepository.queryHouseCompanyEntity(companyMessage.getCompanyName());
            if(houseCompanyEntities.size() >0 ){
                propertyCompanyDTO.setCompanyName(houseCompanyEntities.get(0).getName());//  公司名称
            }else {
                propertyCompanyDTO.setCompanyName("");//  公司名称
            }
            // 根据项目ID 查询项目名称
            List<HouseProjectEntity> houseProjectEntities = propertyCompanyRepository.queryHouseProjectEntity(companyMessage.getPropertyProject());
            if(houseProjectEntities.size() > 0){
                propertyCompanyDTO.setPropertyProject(houseProjectEntities.get(0).getName());//  项目
            }else {
                propertyCompanyDTO.setPropertyProject("");//  项目
            }
            //根据区域ID查询区域名称
            List<HouseAreaEntity> houseAreaEntities = propertyCompanyRepository.queryHouseAreaEntity(companyMessage.getPropertyArea());
            if(houseAreaEntities.size() > 0){
                propertyCompanyDTO.setPropertyArea(houseAreaEntities.get(0).getName());//  区域
            }else {
                propertyCompanyDTO.setPropertyArea("");//  区域
            }
            propertyCompanyDTO.setPropertyMessageTime(DateUtils.format(companyMessage.getPropertyMessageTime()));// 项目公司添加时间
            propertyCompanyDTO.setProjectAdmin(companyMessage.getProjectAdmin());//  项目管理员
            propertyCompanyDTO.setPhnoe(companyMessage.getPhnoe());//联系方式
            // 是否启用
            if(companyMessage.getPropertyType().equals(PropertyCompanyEntity.COMPANY__TYPE_ENABLE)){
                propertyCompanyDTO.setPropertyType("启用");
            }else {
                propertyCompanyDTO.setPropertyType("禁用");
            }
            propertyCompanyDTOList.add(propertyCompanyDTO);
        }
        return propertyCompanyDTOList;
    }

    /**
     * 查询下拉框
     * 区域 Map
     * 公司 Map
     * 项目 Map
     * @return
     */
    @Override
    public PropertyCompanyMapDTO queryPropertyCompanyMap(String projectId) {
        // 区域 公司 项目 MAPDTO
        PropertyCompanyMapDTO propertyCompanyMap = new PropertyCompanyMapDTO();

        // 查询区域数据
        List<HouseAreaEntity> houseAreaEntities = propertyCompanyRepository.queryHouseAreaEntity("");
        // 存放区域map
        Map<String, String> propertyArea = new LinkedHashMap<>();
        propertyArea.put("0","--请选择区域--");
        for(HouseAreaEntity houseAreaEntity : houseAreaEntities){
            propertyArea.put(houseAreaEntity.getId(),houseAreaEntity.getName());
        }
        propertyCompanyMap.setPropertyAreaMap(propertyArea);

        // 查询公司数据
        List<HouseCompanyEntity> houseCompanyEntities = propertyCompanyRepository.queryHouseCompanyEntity("");
        // 存放公司map
        Map<String, String> companyName = new LinkedHashMap<>();
        companyName.put("0","--请选择公司--");
        for(HouseCompanyEntity houseCompanyEntity : houseCompanyEntities){
            companyName.put(houseCompanyEntity.getId(),houseCompanyEntity.getName());
        }
        propertyCompanyMap.setCompanyNameMap(companyName);

        // 查询项目数据
        List<HouseProjectEntity> houseProjectEntities = propertyCompanyRepository.queryHouseProjectEntity(projectId);
        // 存放项目map
        Map<String, String> propertyProject = new LinkedHashMap<>();
        propertyProject.put("0","--请选择项目--");
        for(HouseProjectEntity houseProjectEntity : houseProjectEntities){
            propertyProject.put(houseProjectEntity.getId(),houseProjectEntity.getName());
        }
        propertyCompanyMap.setPropertyProjectMap(propertyProject);

        //获得部门列表
        List<HouseSectionEntity> houseSections = houseSectionRepository.listSectionByProject(projectId);
        Map<String,String> propertySection = new LinkedHashMap<>();
        propertySection.put("0","--请选择部门--");
        if (houseSections.size()>0){
            for (HouseSectionEntity houseSectionEntity:houseSections){
                propertySection.put(houseSectionEntity.getSectionId(),houseSectionEntity.getSectionName());
            }
        }
        propertyCompanyMap.setHouseSectionMap(propertySection);

        // 公告类型
        List<PropertyTypeEntity> propertyType = propertyCompanyRepository.queryPropertyTypeEntity("","");
        Map<String, String> noticeType = new LinkedHashMap<>();
        noticeType.put("0", "--请选择公告类型--");
        for(PropertyTypeEntity propertyTypeEntity : propertyType){
            noticeType.put(propertyTypeEntity.getTypeId(),propertyTypeEntity.getType());
        }
        propertyCompanyMap.setNoticeType(noticeType);

        return propertyCompanyMap;
    }


    /**
     * 公司权限管理
     * @param roleRole
     * @return
     */
    @Override
    public List<RoleRoleDTO> listRoleSetMap(RoleRoleDTO roleRole) {
        //获取所有权限列表
        List<RoleRoleDTO> roleRoleDTOs = roleRoleService.listRoleRole();
        if(null != roleRole.getRoleSetId() && !"".equals(roleRole.getRoleSetId())){
            if (roleRoleDTOs.size()>0){
                for(RoleRoleDTO roleRoles :roleRoleDTOs){
                    //查询当前物业公司权限
                    PropertyCompanyRoleMapEntity propertyCompanyRole = propertyCompanyRepository.getRolesetMap(roleRole.getRoleSetId(),roleRoles.getRoleId());
                    if(propertyCompanyRole!=null){
                        roleRoles.setCheckOut("1");
                        roleRoles.setRoleSetMapId(propertyCompanyRole.getRolesetid());
                        roleRoles.setRoleSetId(propertyCompanyRole.getSetId());
                    }
                    else {
                        roleRoles.setCheckOut("0");
                    }
                }
            }
        }
        return roleRoleDTOs;
    }

    /**
     * 添加或修改物业公司及权限
     * @param propertyServicesDTO
     * @param userPropertystaffEntit
     */
    @Override
    public void addORupdatePropertyCompany(PropertyCompanyDTO propertyServicesDTO, UserPropertyStaffEntity userPropertystaffEntit) {
        PropertyCompanyEntity propertyCompany = new PropertyCompanyEntity();
        if(!"".equals(propertyServicesDTO.getCompanyId())){
            // 根据物业公司ID查询信息详情
            propertyCompany = propertyCompanyRepository.queryPropertyCompanyById(propertyServicesDTO.getCompanyId());
            propertyCompany.setPropertyType(propertyCompany.getPropertyType()); // 状态(启用)
        }else {
            propertyCompany.setCompanyId(propertyServicesDTO.getCompanyId()); // 公司管理表ID
            propertyCompany.setPropertyType(PropertyCompanyEntity.COMPANY__TYPE_ENABLE); // 状态(启用)
        }
        propertyCompany.setCompanyName(propertyServicesDTO.getCompanyName());// 公司表(ID)
        propertyCompany.setPropertyArea(propertyServicesDTO.getPropertyArea()); // 区域表(ID)
        propertyCompany.setPropertyProject(propertyServicesDTO.getPropertyProject());// 项目表(ID)
        propertyCompany.setPropertyMessageTime(new Date());// 项目公司添加时间
        propertyCompany.setProjectAdmin(propertyServicesDTO.getProjectAdmin());// 项目管理员
        propertyCompany.setRoleName(propertyServicesDTO.getRoleId());// 角色
        propertyCompany.setName(propertyServicesDTO.getName()); // 姓名
        propertyCompany.setPhnoe(propertyServicesDTO.getPhnoe()); // 手机号
        propertyCompany.setOptname(userPropertystaffEntit.getStaffName()); //  操作人
        propertyCompany.setOptdate(new Date()); //  操作时间

        if(!"".equals(propertyServicesDTO.getCompanyId())){
            // 执行 更新物业公司信息
            propertyCompanyRepository.updatePropertyCompanyEntity(propertyCompany);
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffEntit.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffEntit.getProjectId());// 用户项目ID
            operation.setContent("修改公司信息!(" + propertyServicesDTO.getName() + ")用户分配角色为【项目公司管理员】");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);
        }else {
            PropertyCompanyEntity propertyCompanys = new PropertyCompanyEntity();
            propertyCompanys.setCompanyId(IdGen.uuid());//自增主键
            propertyCompany.setCompanyId(propertyCompanys.getCompanyId());
            // 执行添加物业公司表
            propertyCompanyRepository.addPropertyCompanyEntity(propertyCompany);

            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffEntit.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffEntit.getProjectId());// 用户项目ID
            operation.setContent("新增项目公司信息!(" + propertyServicesDTO.getName() + ")用户分配角色为【项目公司管理员】");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);
        }

        // 员工信息
        UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();

        userPropertyStaffEntity.setStaffId(propertyServicesDTO.getProjectAdmin());//id
        userPropertyStaffEntity.setUserName(propertyServicesDTO.getProjectAdmin());//用户名username  1
        //密码默认为123456，然后加密
        userPropertyStaffEntity.setStaffName(propertyServicesDTO.getName());//姓名Name    1
        userPropertyStaffEntity.setStaffState(UserPropertyStaffEntity.State_On);//账号有效
        userPropertyStaffEntity.setType(UserPropertyStaffEntity.TYPE_IN_STAFF);//编外，自建     1
        userPropertyStaffEntity.setMobile(propertyServicesDTO.getPhnoe());//手机    1
        userPropertyStaffEntity.setCompanyId(propertyCompany.getCompanyId());//公司Id     1
        userPropertyStaffEntity.setProjectId(propertyServicesDTO.getPropertyProject());//项目ID     1
        userPropertyStaffEntity.setCreateBy(userPropertystaffEntit.getCreateBy());//创建人
        userPropertyStaffEntity.setQueryScope(propertyServicesDTO.getPropertyProject());//项目ID
        userPropertyStaffEntity.setCreateOn(DateUtils.getDate());//创建时间
        userPropertyStaffEntity.setModifyBy(userPropertystaffEntit.getModifyBy());//修改人
        userPropertyStaffEntity.setModifyOn(DateUtils.getDate());//修改时间
        SystemConfigEntity systemConfigEntity = systemConfigRepository.get("UserDefaultLogo");
        if (systemConfigEntity!=null) {
            userPropertyStaffEntity.setLogo(systemConfigEntity.getConfigValue());//员工默认头像
        }
        UserPropertyStaffEntity userPropertyStaff = userPropertystaffReposiroty.get(propertyServicesDTO.getProjectAdmin());
        // 为空则添加员工信息
        if(null == userPropertyStaff){
            String pwd = PasswordEncode.digestPassword("123456");
            userPropertyStaffEntity.setPassword(pwd);//密码password,默认123456
            // 执行添加 员工信息
            userPropertystaffReposiroty.addUserPropertyStaff(userPropertyStaffEntity);
        }else {
            // 不为空则更新当前员工项目ID
            userPropertyStaff.setUserName(propertyServicesDTO.getProjectAdmin());//用户名
            userPropertyStaff.setProjectId(propertyServicesDTO.getPropertyProject());
            userPropertyStaff.setQueryScope(propertyServicesDTO.getPropertyProject());
            userPropertyStaff.setModifyBy(userPropertystaffEntit.getModifyBy());//修改人
            userPropertyStaff.setModifyOn(DateUtils.getDate());//修改时间
            userPropertyStaff.setType(UserPropertyStaffEntity.TYPE_IN_STAFF);
            userPropertyStaff.setStaffState(UserPropertyStaffEntity.State_On);
            //  更新员工信息
            userPropertystaffReposiroty.updateUserPropertyStaff(userPropertyStaff);
        }

        // 判断是否选择权限
        if(null != propertyServicesDTO.getRoleId()){
            UserAnthorityDTO userAnthorityDTO = new UserAnthorityDTO();
            userAnthorityDTO.setStaffId(propertyServicesDTO.getProjectAdmin());
            userAnthorityDTO.setSetId(propertyServicesDTO.getRoleId());
            userAnthorityDTO.setAppRoleSetId("0");
            boolean s = userAnthorityService.updateUserAnthority(userAnthorityDTO,userPropertystaffEntit);
        }

    }

    /**
     * 根据物业公司ID查询详细信息
     * @param companyId
     * @return
     */
    @Override
    public PropertyCompanyDTO queryPropertyCompanyById(String companyId) {
        PropertyCompanyDTO propertyCompany = new PropertyCompanyDTO();
        PropertyCompanyEntity propertyCompanyEntity = propertyCompanyRepository.queryPropertyCompanyById(companyId);
        propertyCompany.setCompanyId(propertyCompanyEntity.getCompanyId());// ID
        propertyCompany.setCompanyName(propertyCompanyEntity.getCompanyName()); // 公司表(ID)
        propertyCompany.setPropertyProject(propertyCompanyEntity.getPropertyProject());// 项目表(ID)
        propertyCompany.setPropertyArea(propertyCompanyEntity.getPropertyArea());// 区域表(ID)
        propertyCompany.setProjectAdmin(propertyCompanyEntity.getProjectAdmin());// 管理员
        // 根据ID查询角色
        RoleRoleanthorityEntity roleRoleanthorityEntity = roleAnthorityRepository.getAnthorityByStaffId(propertyCompanyEntity.getProjectAdmin());
        propertyCompany.setRoleId(roleRoleanthorityEntity.getSetId());//权限
        propertyCompany.setName(propertyCompanyEntity.getName());//姓名
        propertyCompany.setPhnoe(propertyCompanyEntity.getPhnoe());// 电话
        return propertyCompany;
    }

    /**
     * 判断是删除信息或更新状态
     * 根据信息ID
     * 启用(type 0)
     * 禁用(type 1)
     * 删除(type 2)
     * @param userPropertystaffEntity
     * @param companyId
     * @param propertyType
     * @return
     */
    @Override
    public String removeORupdatePropertyServices(UserPropertyStaffEntity userPropertystaffEntity, String companyId, String propertyType) {
        String resultMessage = "";
        PropertyCompanyEntity propertyCompanyEntity = new PropertyCompanyEntity();
        // 根据物业公司ID查询信息详情
        propertyCompanyEntity = propertyCompanyRepository.queryPropertyCompanyById(companyId);
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(propertyCompanyEntity.getProjectAdmin());
        // propertyType 为0 则根据物业公司ID 更新当前信息为禁用
        if("0".equals(propertyType)){
            if(null != propertyCompanyEntity){
                // 不为空则更新状态为禁用
                propertyCompanyEntity.setPropertyType(PropertyCompanyEntity.COMPANY_TYPE_DISABLE);
                // 执行 更新物业公司信息
                propertyCompanyRepository.updatePropertyCompanyEntity(propertyCompanyEntity);

                userPropertyStaffEntity.setStaffState(UserPropertyStaffEntity.State_Off);
                boolean result = userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);
            }else{
                resultMessage = "0";//此信息不存在!
            }
        }
        // propertyType 为1 则根据物业公司ID 更新当前信息为启用
        if("1".equals(propertyType)){
            if(null != propertyCompanyEntity){
                // 不为空则更新状态为启用
                propertyCompanyEntity.setPropertyType(PropertyCompanyEntity.COMPANY__TYPE_ENABLE);
                // 执行 更新物业公司信息
                propertyCompanyRepository.updatePropertyCompanyEntity(propertyCompanyEntity);

                userPropertyStaffEntity.setStaffState(UserPropertyStaffEntity.State_On);
                boolean result = userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);
            }else{
                resultMessage = "0";//此信息不存在!
            }
        }
        // propertyType 为2 则根据物业公司ID 删除当前物业公司信息 并且删除物业公司权限
        if("2".equals(propertyType)){
            // 删除当前物业公司信息
            if(null != propertyCompanyEntity){
                propertyCompanyRepository.delPropertyCompanyEntity(propertyCompanyEntity);
                // 后台核心日志
                OperationLogDTO operation = new OperationLogDTO();
                operation.setUserName(userPropertystaffEntity.getStaffName());  // 用户名
                operation.setProjectId(userPropertystaffEntity.getProjectId());// 用户项目ID
                operation.setContent("删除项目公司信息!("+propertyCompanyEntity.getName()+")权限禁用!");      // 操作动作
                // 添加后台核心日志
                operationLogService.addOperationLog(operation);

            }else {
                resultMessage = "0";//此信息不存在!
            }

            // 删除物业公司权限表
            if(null != userPropertyStaffEntity){
                userPropertyStaffEntity.setStaffState(UserPropertyStaffEntity.State_Off);
                boolean result = userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);
            }
        }
        return resultMessage;
    }

    /**
     * 查询当前管理员是否已分配负责项目
     * @param projectAdmin
     * @return
     */
    @Override
    public String whetherAreAdmin(String projectAdmin) {
        // 返回值 判断是否存在改用户
        String resultMsg = "";
        // 根据管理员名查询 当前管理员是否存在
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(projectAdmin);
        if(null != userPropertyStaffEntity){
            if(UserPropertyStaffEntity.State_On.equals(userPropertyStaffEntity.getStaffState())){
                resultMsg="1";// 此用户已存在 状态为有效账号
            }else {
                resultMsg="2";// 此用户存在  状态为无效账号
            }
        }else {
            resultMsg="3";// 此用户不存在
        }
        return resultMsg;
    }

    /**
     * 联动 根据区域ID查询公司信息
     * @param companyName
     * @return
     */
    @Override
    public List<HouseCompanyDTO> getHouseCompany(String companyName) {
        List<HouseCompanyDTO> houseCompany = new ArrayList<>();
        List<HouseCompanyEntity> houseCompanyList = propertyCompanyRepository.getHouseCompany(companyName);
        for(HouseCompanyEntity house : houseCompanyList){
            HouseCompanyDTO houseCompanys = new HouseCompanyDTO();
            houseCompanys.setId(house.getId());// 公司ID
            houseCompanys.setName(house.getName());//公司名称
            houseCompany.add(houseCompanys);
        }
        return houseCompany;
    }

    /**
     * 联动 根据公司ID查询项目信息
     * @param pojectName
     * @return
     */
    @Override
    public List<HouseProjectDTO> getHouseProject(String pojectName) {
        List<HouseProjectDTO> houseProject = new ArrayList<>();
        List<HouseProjectEntity> houseProjectList = propertyCompanyRepository.getHouseProject(pojectName);
        for(HouseProjectEntity house : houseProjectList){
            HouseProjectDTO houseProjecs = new HouseProjectDTO();
            houseProjecs.setId(house.getId());// 项目ID
            houseProjecs.setName(house.getName());// 项目名称
            houseProject.add(houseProjecs);
        }
        return houseProject;
    }
}
