package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.admin.StaffDTO;
import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.application.dto.adminDTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.batch.StaffReceiveDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/25.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})

public class PropertyStaffController {

    @Autowired
    private UserPropertystaffService userPropertystaffService;

    @Autowired
    private RoleRolesetService roleRolesetService;

    @Autowired
    private UserAnthorityService userAnthorityService;

    @Autowired
    MessageInsertService messageInsertService;

    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    HouseSectionService houseSectionService;

    @Autowired
    AppRolesetService appRolesetService;

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    RoleButtonMapService roleButtonMapService;
    @Autowired
    OrganizeService organizeService;
    @Autowired
    RoleDataService roleDataService;
    @Autowired
    AgencyService agencyService;


    /**
     * 初始化员工界面
     *
     * @param userPropertystaffEntity
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/userStaffManage.html", method = RequestMethod.GET)
    public String listStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid AgencyAdminDTO agencyAdminDTO, HttpServletRequest request, WebPage webPage, Model model) {
//        //项目列表
//        Map projectSelDTOs = houseProjectService.getProjects();
//        //角色列表
//        List<AppRolesetDTO> appRolesetDTOs = appRolesetService.listAppRoleSet();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cook = cookies[i];
            if (cook.getName().equalsIgnoreCase("agencyId") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                agencyAdminDTO.setAgencyId(cook.getValue());
            }
            if (cook.getName().equalsIgnoreCase("agencyType") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点的类型
                agencyAdminDTO.setAgencyType(cook.getValue());
            }
        }
        List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
        String flag = "";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAdmStaff()) || !StringUtil.isEmpty(agencyAdminDTO.getAdmUser())) {
            //员工信息
            agencyDTOs = agencyService.staffListFor(agencyAdminDTO, webPage);
            flag = "1";
        } else if (agencyAdminDTO.getAgencyType() != null) {
            if (!agencyAdminDTO.getAgencyType().equals("2") && !"3".equals(agencyAdminDTO.getAgencyType())) {
                //员工信息
                agencyDTOs = agencyService.staffListFor(agencyAdminDTO, webPage);
                flag = "1";
            } else {
                //部门信息
                agencyDTOs = agencyService.AgencyParment(agencyAdminDTO.getAgencyId(),agencyAdminDTO.getAgencyName(), webPage);
                flag = "2";
            }
        }

//        List<UserPropertystaffDTO> userPropertyStaffEntities = userPropertystaffService.listStaffDTO(userPropertystaffDTO, webPage);
        model.addAttribute("agencyStaff", agencyDTOs);
        model.addAttribute("flag", flag);
        model.addAttribute("adminDTO", agencyAdminDTO);
        return "/user/StaffManage";
    }

    /**
     * 删除员工
     *
     * @param userPropertystaffDTO
     * @return
     */
    @RequestMapping(value = "/deleteStaffManage")
    public ModelAndView deleteStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid UserPropertystaffDTO userPropertystaffDTO, WebPage webPage) {

        if (userPropertystaffService.deleteStaff(userPropertystaffDTO.getStaffIdDto(), userPropertystaffEntity))//删除员工
        {
            userAnthorityService.deleteUserAnthority(userPropertystaffDTO.getStaffIdDto());//删除该员工的关系
        }
        return new ModelAndView("redirect:/user/userStaffManage.html?pageIndex=" + webPage.getPageIndex());
    }

    /**
     * 跳转到角色分配页
     *
     * @param model
     * @param userPropertystaffDTO
     * @return
     */
    @RequestMapping(value = "/gotoDisRoleset")
    public String gotoDisRoleset(Model model, @Valid UserPropertystaffDTO userPropertystaffDTO) {
        // 查询角色表 //角色类型为3 --员工可用角色
        List<RoleRolesetDTO> roleRolesetDTOs = roleRolesetService.listRoleset("");
        model.addAttribute("roleRolesetDTOs", roleRolesetDTOs);
        List<AppRolesetDTO> appRolesetDTOs = appRolesetService.listAppRoleSet();
        model.addAttribute("appRolesetDTOs", appRolesetDTOs);
        model.addAttribute("userPropertystaffDTO", userPropertystaffDTO);
        return "/user/StaffDisRoleset";
    }

    /**
     * 分配角色
     *
     * @param model
     * @param userPropertystaffDTO
     * @return
     */
    @RequestMapping(value = "/disRoleset")
    public ModelAndView disRoleset(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model, @Valid UserPropertystaffDTO userPropertystaffDTO) {
        UserAnthorityDTO userAnthorityDTO = new UserAnthorityDTO();
        userAnthorityDTO.setStaffId(userPropertystaffDTO.getStaffIdDto());
        userAnthorityDTO.setSetId(userPropertystaffDTO.getRoleSetIdDto());
        userAnthorityDTO.setAppRoleSetId(userPropertystaffDTO.getAppRoleSetIdDto());
        userAnthorityService.updateUserAnthority(userAnthorityDTO, userPropertystaffEntity);
        return new ModelAndView("redirect:/user/userStaffManage.html");
    }

    /**
     * 根据名称搜索员工
     *
     * @return
     */
    @RequestMapping(value = "/searchStaff")
    public ApiResult gotoAddStaff(@RequestParam(value = "staffName") String staffName) {
        List<StaffNameDTO> staffNameDTOList = userPropertystaffService.searchStaffByName(staffName);
        return new SuccessApiResult(staffNameDTOList);
    }

    /**
     * 添加员工
     *
     * @param model
     * @param userPropertystaffDTO
     * @return
     */
    @RequestMapping(value = "/addStaff")
    public String addStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model, @Valid UserPropertystaffDTO userPropertystaffDTO, @Param String wandastaffId) {

        userPropertystaffDTO.setCreateByDto(userPropertystaffEntity.getStaffName());//添加创建人
        userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());//添加修改人
        userPropertystaffDTO.setCompanyIdDto(userPropertystaffEntity.getCompanyId());//公司Id
        userPropertystaffDTO.setModifyByUserNameDto(userPropertystaffEntity.getUserName());
        userPropertystaffDTO.setProjectIdDto(userPropertystaffEntity.getQueryScope());
        UserPropertystaffDTO result = userPropertystaffService.addStaff(userPropertystaffDTO);
        model.addAttribute("result", result);
        //默认项目
        HouseProjectDto houseProjectDto = houseProjectService.getProjectById(userPropertystaffEntity.getQueryScope());
        model.addAttribute("houseProjectDto", houseProjectDto);
        //获取部门下拉框
        List<HouseSectionAdminDTO> houseSectionAdminDTOs = houseSectionService.listSectionByProjecj(userPropertystaffEntity.getQueryScope());
        model.addAttribute("houseSectionAdminDTOs", houseSectionAdminDTOs);
        return "/user/StaffAdd";
    }

    /**
     * 跳转到新增 修改员工页面 质检
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUpdateStaff.html")
    public String gotoUpdateStaff(Model model, @RequestParam(value = "staffIdDto", required = false) String staffIdDto, HttpServletRequest request) {
        //项目列表
//            Map projectSelDTOs = houseProjectService.getProjects();
        //角色列表
//            List<AppRolesetDTO> appRolesetDTOs = appRolesetService.listAppRoleSet();
        StaffDTO staffDTO = new StaffDTO();
        Cookie[] cookies = request.getCookies();
        List<HouseProjectDto> houseProjectDtos = new ArrayList<HouseProjectDto>();
        List<AppRolesetDTO> appRolesetDTOs = new ArrayList<AppRolesetDTO>();
        List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
        String str = "";
        if (!StringUtil.isEmpty(staffIdDto)) {
            //员工信息
            staffDTO = userPropertystaffService.getStaffDetail(staffIdDto);
            houseProjectDtos = houseProjectService.getProjectsByStaffId(staffIdDto);  //人关联的项目
            appRolesetDTOs = appRolesetService.getAppRoleSetsByStaffId(staffIdDto);    //人关联的角色
        } else {
            AgencyDTO agencyDTO = new AgencyDTO();
            for (int i = 0; i < cookies.length; i++) {
                Cookie cook = cookies[i];
                if (cook.getName().equalsIgnoreCase("agencyId") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                    agencyDTO = agencyService.getAgencyDetail(cook.getValue());
                    str = cook.getValue();
                }
            }
            agencyDTOs.add(agencyDTO);
            staffDTO.setStaffAgency(agencyDTOs);
        }
//            model.addAttribute("houseProjectDto",projectSelDTOs);
//            model.addAttribute("appRoleSetList",appRolesetDTOs);
        model.addAttribute("userStaffDTO", staffDTO);
        model.addAttribute("projectDTO", houseProjectDtos);
        model.addAttribute("roleDTO", appRolesetDTOs);
        model.addAttribute("cookieId", str);
        return "/user/StaffUpdate";
    }

    /**
     * 员工详情页
     */
    @RequestMapping(value = "/staffDetail.html")
    public String staffDetail(@RequestParam(value = "staffId") String staffId, @RequestParam(value = "agencyId") String agencyId, Model model) {
        StaffDTO staffDTO = userPropertystaffService.getStaffDetail(staffId);
        List<OrganizeDTO> organizeDTOs = organizeService.getOrgans(staffId);  //人关联的组
        List<HouseProjectDto> houseProjectDtos = houseProjectService.getProjectsByStaffId(staffId);  //人关联的项目
        List<AppRolesetDTO> appRolesetDTOs = appRolesetService.getAppRoleSetsByStaffId(staffId);    //人关联的角色
        model.addAttribute("userStaffDTO", staffDTO);
        model.addAttribute("roleSetList", appRolesetDTOs);
        model.addAttribute("organizeList", organizeDTOs);
        model.addAttribute("projectList", houseProjectDtos);
        model.addAttribute("agencyId", agencyId);
        return "/user/staffDetail";
    }


    /**
     * 修改员工
     *
     * @param userPropertystaffDTO
     * @return
     */
    @RequestMapping(value = "/updateStaff")
    public ModelAndView updateStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid UserPropertystaffDTO userPropertystaffDTO, Model model) {
        userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());
        userPropertystaffDTO.setModifyByUserNameDto(userPropertystaffEntity.getUserName());
        UserPropertystaffDTO result = userPropertystaffService.updateStaff(userPropertystaffDTO);
        model.addAttribute("result", result);
        //默认项目
        HouseProjectDto houseProjectDto = houseProjectService.getProjectById(userPropertystaffEntity.getQueryScope());
        model.addAttribute("houseProjectDto", houseProjectDto);
        //获取部门下拉框
        List<HouseSectionAdminDTO> houseSectionAdminDTOs = houseSectionService.listSectionByProjecj(userPropertystaffEntity.getQueryScope());
        model.addAttribute("houseSectionAdminDTOs", houseSectionAdminDTOs);

        model.addAttribute("userPropertystaffDTO", userPropertystaffDTO);
        return new ModelAndView("redirect:/user/userStaffManage.html");
    }

    //-----当新建员工为引入时---------------------------------
    /**
     * Ajax获取万达内部员工个人信息
     * @param userNameDto
     * @return
     */
    /*@RequestMapping(value = "/getWandaStaff")
    public ApiResult getWandaStaff(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Param String userNameDto){
       UserWandaStaffDTO userWandaStaffDTO = userPropertystaffService.getWandaStaffByUserName(userNameDto);
        return new SuccessApiResult(userWandaStaffDTO);
    }*/


    /**
     * 新增、编辑 员工
     */
    @RequestMapping(value = "/addUserStaff")
    public ApiResult addStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertyStaffEntity, @Valid StaffReceiveDTO staffDTO) {
        String flag;
        if (!StringUtil.isEmpty(staffDTO.getStaffIdR())) {   //如果员工ID不为空 则为编辑
            flag = userPropertystaffService.alterStaff(staffDTO, userPropertyStaffEntity);
        } else {        //否则为新增
            flag = userPropertystaffService.saveStaff(staffDTO, userPropertyStaffEntity);
        }
        return new SuccessApiResult(flag);
    }

    /**
     * 后台修改密码
     */
    @RequestMapping(value = "/altPassword")
    public ApiResult altPassword(@Valid StaffDTO staffDTO) {
        userPropertystaffService.altPassword(staffDTO);
        return new SuccessApiResult("ok");
    }

    /**
     * 解除员工组织绑定
     */
    @RequestMapping(value = "/delStaffAgency.html")
    public String delStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @RequestParam("staffIdDto") String staffIdDto, @RequestParam("agencyId") String agencyId) {
        userPropertystaffService.delStaffAgency(staffIdDto,agencyId);
        return "redirect:../user/userStaffManage.html";
    }

    /**
     * 角色管理
     */
    @RequestMapping(value = "/roleManage.html")
    public String roleManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid RoleSetDTO roleSetDTO, Model model, WebPage webPage) {
        List<RoleSetDTO> roleSetDTOs = appRolesetService.getAppRoleSets(roleSetDTO.getAppRoleSetName(), webPage);
        model.addAttribute("appRoleSetList", roleSetDTOs);
        model.addAttribute("setDto", roleSetDTO);
        return "user/roleSetManage";
    }

    /**
     * 角色列表
     */
    @RequestMapping(value = "/appRoleSets", method = RequestMethod.GET)
    public ApiResult appRoleSets() {
        List<RoleSetDTO> roleSetDTOs = appRolesetService.allAppRoleSets();
        return new SuccessApiResult(roleSetDTOs);
    }

    /**
     * 跳转新增、编辑角色页面
     */
    @RequestMapping(value = "/gotoRoleSet.html")
    public String gotoAddRoleSet(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, Model model, @RequestParam(value = "roleSetId", required = false) String roleSetId) {
        RoleSetDTO roleSetDTO = new RoleSetDTO();
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
        if (!StringUtil.isEmpty(roleSetId)) {
            roleSetDTO = appRolesetService.getAppRoleSetDetail(roleSetId);
            agencyAdminDTOs = agencyService.agencyListByRoleId(roleSetId);  //该角色相关的机构
            staffNameDTOList = appRolesetService.getSetsStaff(roleSetId); //该角色相关的人
        }
        List<AppRoleDTO> appRoleDTOList = appRoleService.appRoleMenus();
        List<AppRoleDTO> appRoleDTOs = appRoleService.appRoleMenuList(roleSetId);  //该角色已存在的菜单关系
        model.addAttribute("roleMenuList", appRoleDTOList);
//        model.addAttribute("appRoleSetId",roleSetId);
        model.addAttribute("roleList", appRoleDTOs);
        model.addAttribute("roleSetDTO", roleSetDTO);
        model.addAttribute("agencyList", agencyAdminDTOs);
        model.addAttribute("staffList", staffNameDTOList);
        return "user/addRoleSet";
    }

    /**
     * 新增、编辑 角色
     */
    @RequestMapping(value = "/addRole.html")
    public String addRole(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid AppRoleDTO appRoleDTO) {
        if (appRoleDTO.getAppRoleSetId() == null || "".equals(appRoleDTO.getAppRoleSetId())) {               //如果角色ID为空则为新增
            appRoleDTO.setAppRoleSetId(IdGen.uuid());
            appRolesetService.addAppRoleSet(appRoleDTO);
            roleButtonMapService.addRoleButtonMap(appRoleDTO);   //保存角色与菜单的关系
        } else {            //否则为修改
            appRolesetService.updateAppRoleSet(appRoleDTO);
            roleButtonMapService.addRoleButtonMap(appRoleDTO);
        }
        return "redirect:../user/roleManage.html";
    }

    /**
     * 跳转角色详情页面
     */
    @RequestMapping(value = "/roleSetDetail.html")
    public String roleSetDetail(@RequestParam(value = "appRoleSetId") String appRoleSetId, Model model) {
        RoleSetDTO roleSetDTO = appRolesetService.getAppRoleSetDetail(appRoleSetId);
        List<AppRoleDTO> appRoleDTOList = appRoleService.appRoleMenus();
        List<AppRoleDTO> appRoleDTOs = appRoleService.appRoleMenuList(appRoleSetId);  //该角色已存在的菜单关系
        List<AgencyAdminDTO> agencyAdminDTOs = agencyService.agencyListByRoleId(appRoleSetId); //获取与该角色关联的组织架构列表
        List<AgencyAdminDTO> staffs = agencyService.staffListByRoleSetId(appRoleSetId); //获取与该角色关联的人员列表
        List<OrganizeDTO> organizeDTOs = organizeService.getOrganizeInRole(appRoleSetId);    //获取与该角色相关的组列表
        model.addAttribute("roleMenuList", appRoleDTOList);
        model.addAttribute("roleList", appRoleDTOs);
        model.addAttribute("roleSetDTO", roleSetDTO);
        model.addAttribute("agencyList", agencyAdminDTOs);
        model.addAttribute("staffList", staffs);
        model.addAttribute("organizeList", organizeDTOs);
        return "user/appRoleSetDetail";
    }

    /**
     * 删除角色
     * 需先清理人员角色关联关系表有关该角色的信息
     */
    @RequestMapping(value = "/delRole.html")
    public String delRole(@RequestParam("appRoleSetId") String appRoleSetId) {
        appRolesetService.delAppRoleSet(appRoleSetId);
        return "redirect:../user/roleManage.html";
    }

    /**
     *跳转 为角色添加不同阶段的菜单 页面
     * */
//    @RequestMapping(value = "/roleMenu.html")
//    public String roleMenu(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,Model model,@RequestParam(value = "appRoleSetId")String appRoleSetId){
//        List<AppRoleDTO> appRoleDTOList = appRoleService.appRoleMenus();
//        List<AppRoleDTO> appRoleDTOs = appRoleService.appRoleMenuList(appRoleSetId);  //该角色已存在的菜单关系
//        model.addAttribute("roleMenuList",appRoleDTOList);
//        model.addAttribute("appRoleSetId",appRoleSetId);
//        model.addAttribute("roleList", appRoleDTOs);
//        return "user/allotAppMenu";
//    }

    /**
     * 保存角色菜单
     * */
//    @RequestMapping(value = "/saveRoleMenu.html")
//    public String saveRoleMenu(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid AppRoleDTO appRoleDTO){
//        roleButtonMapService.addRoleButtonMap(appRoleDTO);
//        return "redirect:../user/roleManage.html";
//    }

    /**
     * 跳转 为角色添加人员 页面
     */
    @RequestMapping(value = "/rolePeople.html")
    public String gotoRolePeople(@RequestParam(value = "appRoleSetId") String appRoleSetId, @Valid StaffNameDTO staffNameDTO, Model model) {
        staffNameDTO.setRoleSetId(appRoleSetId);
        List<StaffNameDTO> staffNameDTOs = appRolesetService.getSetOutStaff(staffNameDTO);  //获取该角色外的用户列表
//        List<StaffNameDTO> staffNameDTOList = appRolesetService.getSetsStaff(appRoleSetId);
        List<AgencyAdminDTO> agencyAdminDTOs = agencyService.getAgencyByRoleId(appRoleSetId); //获取与该角色关联的组织架构列表
        RoleSetDTO roleSetDTO = appRolesetService.getAppRoleSetDetail(appRoleSetId);  //角色详情
        List<OrganizeDTO> organizeDTOs = organizeService.getOrganizeInRole(appRoleSetId);    //获取与该角色相关的组列表
        List<OrganizeDTO> organizeDTOList = organizeService.getOrganizeOutRole(appRoleSetId);//获取该角色外的组列表
        model.addAttribute("staffList", agencyAdminDTOs);
        model.addAttribute("staffOutList", staffNameDTOs);
        model.addAttribute("organizeInList", organizeDTOs);
        model.addAttribute("organizeOutList", organizeDTOList);
        model.addAttribute("roleSet", roleSetDTO);
        model.addAttribute("ckFlag", staffNameDTO.getCkFlag());
        return "role/roleStaff";
    }

    /**
     * 保存 人员角色关系
     */
    @RequestMapping(value = "/saveRolePeople", method = RequestMethod.GET)
    public ApiResult saveRolePeople(@Valid StaffNameDTO staffNameDTO) {
        appRolesetService.saveStaffRoleSet(staffNameDTO);
        return new SuccessApiResult();
    }
//
//    /**
//     * 删除 人员角色关系
//     * */
//    @RequestMapping(value = "/delRolePeople",method = RequestMethod.GET)
//    public ApiResult delRolePeople(@Valid StaffNameDTO staffNameDTO){
//        appRolesetService.delStaffRoleSet(staffNameDTO);
//        return new SuccessApiResult();
//    }

    /**
     * 保存角色 组织关系
     */
    @RequestMapping(value = "saveRoleAgency", method = RequestMethod.GET)
    public ApiResult saveRoleAgency(@Valid RoleDataAdminDTO roleDataAdminDTO) {
        roleDataAdminDTO.setDataType("2");
        return roleDataService.addRoleData(roleDataAdminDTO);
    }

    /**
     * 删除角色 组织关系
     */
    @RequestMapping(value = "delRoleAgency", method = RequestMethod.GET)
    public ApiResult delRoleAgency(@Valid RoleDataAdminDTO roleDataAdminDTO) {
        StaffNameDTO staffNameDTO = new StaffNameDTO();
        staffNameDTO.setRoleSetId(roleDataAdminDTO.getDataId());
        if (!roleDataAdminDTO.getAuthorityType().equals("0")) {
            roleDataAdminDTO.setDataType("2");
            roleDataService.delRoleOrganize(roleDataAdminDTO);
        } else {
//            staffNameDTO.setRoleSetId(roleDataAdminDTO.getDataId());
            staffNameDTO.setStaffId(roleDataAdminDTO.getAuthorityId());
            appRolesetService.delStaffRoleSet(staffNameDTO);
        }
        List<StaffNameDTO> staffNameDTOs = appRolesetService.getSetOutStaff(staffNameDTO);
        return new SuccessApiResult(staffNameDTOs);
    }

    /**
     * 保存角色 常用组关系
     */
    @RequestMapping(value = "/addRoleOrganize", method = RequestMethod.GET)
    public ApiResult addOrganizeRole(@Valid RoleDataAdminDTO roleDataAdminDTO) {
        roleDataAdminDTO.setDataType("2");
        roleDataAdminDTO.setAuthorityType("2");
        roleDataService.addRoleData(roleDataAdminDTO);
        return new SuccessApiResult();
    }

    /**
     * 删除角色 常用组关系
     */
    @RequestMapping(value = "delRoleOrganize", method = RequestMethod.GET)
    public ApiResult delOrganizeRole(@Valid RoleDataAdminDTO roleDataAdminDTO) {
        roleDataAdminDTO.setDataType("2");
        roleDataAdminDTO.setAuthorityType("2");
        roleDataService.delRoleOrganize(roleDataAdminDTO);
        return new SuccessApiResult();
    }

}
