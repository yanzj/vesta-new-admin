package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.DTO.AuthAgencyDTO;
import com.maxrocky.vesta.application.DTO.admin.StaffDTO;
import com.maxrocky.vesta.application.adminDTO.SupplierDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.*;
import com.maxrocky.vesta.application.baseData.inf.*;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AuthSupplierAgencyRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AuthSupplierPeopleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.SupplierService;
import com.maxrocky.vesta.application.inf.UserPropertystaffService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import net.sf.json.JSONArray;
import org.apache.log4j.helpers.AbsoluteTimeDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Magic on 2016/10/18.
 */
@Controller
@RequestMapping(value = "/BaseData")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectBaseDataController {
    @Autowired
    ProjectProjectService projectProjectService;
    @Autowired
    ProjectBuildingService projectBuildingService;
    @Autowired
    ProjectCityService projectCityService;
    @Autowired
    ProjectTendersService projectTendersService;
    @Autowired
    StaffEmployService staffEmployService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    AgencyService agencyService;
    @Autowired
    UserPropertystaffService userPropertystaffService;
    @Autowired
    ProjectCategoryService projectCategoryService;

    @Autowired
    private SecurityProjectService securityProjectService;

    @Autowired
    AuthAgencyService authAgencyService;
    /**
     * 工程项目管理页面
     *
     * @param projectProjectDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/projectManage.html")
    public String ProjectProjectManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectProjectDTO projectProjectDTO, Model model) {
        List<ProjectProjectDTO> projectProjectDTOs = projectProjectService.getProjectProjectsByCityId(projectProjectDTO.getCityId(), userInformationEntity.getStaffId());
//        model.addAttribute("projectList", projectProjectDTOs);
//        model.addAttribute("allCity", selectCity);
        //增加安全线校验
        Map<String, String> projectList = securityProjectService.getESAllProjectByUser(userInformationEntity);
        model.addAttribute("projectList", projectList);

//        model.addAttribute("cityId", projectProjectDTO.getCityId());
        return "/construction/baseData/projectProjectManage";
    }

    /**
     * @param projectProjectDTO
     * @return 新增工程项目
     */
    @RequestMapping(value = "/addProjectProject.html")
    public String addProjectProject(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectProjectDTO projectProjectDTO) {
        projectProjectService.addProjectProject(projectProjectDTO, userInformationEntity);
        return "redirect:../BaseData/projectRoleManage.html";
    }

    /**
     * 工程项目权限管理页
     *
     * @param projectProjectDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/projectRoleManage.html")
    public String projectRoleManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectProjectDTO projectProjectDTO, Model model, WebPage webPage) {
        List<ProjectProjectDTO> projectProjectDTOs = projectProjectService.getProjectAll(projectProjectDTO, webPage);
        Map map = projectCityService.getProjectOperationList();  //经营单位下拉框数据
//        Map selectCity = projectCityService.getCityListByOptId(projectProjectDTO.getOptId());  //区域城市下拉框数据?
        model.addAttribute("projectList", projectProjectDTOs);
        model.addAttribute("project", projectProjectDTO);
        model.addAttribute("operationList", map);
//        model.addAttribute("allCity", selectCity);
        return "/construction/baseData/projectRoleManage";
    }


    /**
     * 工程项目权限详情页
     *
     * @param projectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/projectRoleDetail.html")
    public String projectRoleDetail(@RequestParam(value = "projectId") String projectId, Model model) {
        ProjectRoleAllDTO projectRoleAllDTO = projectProjectService.getProjectDetail(projectId);
        model.addAttribute("projectProject", projectRoleAllDTO);
        return "/construction/baseData/projectRoleDetail";
    }

    /**
     * 工程项目权限编辑页
     *
     * @param projectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/editProjectRole.html")
    public String editProjectRole(@RequestParam(value = "projectId") String projectId, @RequestParam(value = "flag") String flag, Model model) {
        ProjectRoleAllDTO projectRoleAllDTO = projectProjectService.getProjectDetail(projectId);
        model.addAttribute("projectProject", projectRoleAllDTO);
        model.addAttribute("flag", flag);     //flag 1隐藏甲方权限 2只显示甲方权限
        return "/construction/baseData/editProjectRole";
    }

    /**
     * 保存工程项目权限
     *
     * @return
     */
    @RequestMapping(value = "/saveProjectRole.html")
    public String saveProjectRole(@Valid ProjectProjectReceiveDTO projectProjectReceiveDTO) {
        projectProjectService.updateProjectProject(projectProjectReceiveDTO);
        if ("1".equals(projectProjectReceiveDTO.getFlag())) {
            return "redirect:../BaseData/projectRoleManage.html";
        } else {
            return "redirect:../BaseData/projectManage.html";
        }
    }

    /**
     * 删除工程项目
     *
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/delProjectProject.html")
    public String delProjectProject(@RequestParam(value = "projectId") String projectId) {
        projectProjectService.delProjectProject(projectId);
        return "redirect:../BaseData/projectRoleManage.html";
    }

    /**
     * 工程楼栋列表页面
     *
     * @param projectId
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/projectBuilding.html")
    public String projectBuildings(@RequestParam(value = "projectId") String projectId, Model model, WebPage webPage) {
        List<ProjectBuildingDTO> projectBuildingDTOs = projectBuildingService.getProjectBuildings(projectId, webPage); //工程楼栋列表
        model.addAttribute("buildingList", projectBuildingDTOs);
        model.addAttribute("projectId", projectId);

        /* ss 导出excel，size */
        model.addAttribute("size", projectBuildingDTOs.size());
        return "/construction/baseData/projectBuildingManage";
    }

    /**
     * 工程楼栋楼层管理页面
     *
     * @param buildId
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/projectFloorManage.html")
    public String addOrUpdateBuild(@RequestParam(value = "buildId") String buildId, @RequestParam(value = "projectId") String projectId, String result, Model model, WebPage webPage) {
        ProjectBuildingDTO projectBuildingDTO = projectBuildingService.getProjectBuildingDetail(buildId);
        List<ProjectFloorDTO> projectFloorDTOs = projectBuildingService.getProjectFloors(buildId, webPage);
        model.addAttribute("projectId", projectId);
        model.addAttribute("buildId", buildId);
        model.addAttribute("building", projectBuildingDTO);
        model.addAttribute("floorList", projectFloorDTOs);
        if (StringUtil.isEmpty(result)) {
            model.addAttribute("result", "");
        } else if ("1".equals(result.split(",")[0])) {
            model.addAttribute("result", "该楼层已存在！");
        } else if ("2".equals(result.split(",")[0])) {
            model.addAttribute("result", "该数据不存在！");
        } else if ("3".equals(result.split(",")[0])) {
            model.addAttribute("result", "保存成功！");
        }

        return "/construction/baseData/projectFloorManage";
    }

    /**
     * 删除工程楼栋
     *
     * @param buildId
     * @return
     */
    @RequestMapping(value = "/deleteBuild.html")
    public String delProjectBuilding(@RequestParam(value = "buildId") String buildId, @RequestParam(value = "projectId") String projectId) {
        projectBuildingService.delProjectBuilding(buildId);
        return "redirect:../BaseData/projectBuilding.html?projectId" + projectId;
    }

    /**
     * 删除工程楼层
     *
     * @param floorId
     * @return
     */
    @RequestMapping(value = "/deleteFloor.html")
    public String deleteProjectFloor(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @RequestParam(value = "floorId") String floorId, @RequestParam(value = "buildId") String buildId) {
        projectBuildingService.delProjectFloor(floorId, userInformationEntity);
        return "redirect:../BaseData/projectFloorManage.html?buildId" + buildId;
    }

    /**
     * 工程标段管理页
     *
     * @param projectId
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/tendersManage.html")
    public String tenderManage(@RequestParam(value = "projectId") String projectId, Model model, WebPage webPage) {
        List<ProjectTendersDTO> projectTendersDTOs = projectTendersService.getTenderList(projectId, webPage);

        model.addAttribute("projectId", projectId);
        model.addAttribute("tendersList", projectTendersDTOs);
        return "/construction/baseData/projectTendersManage";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 删除标段
     */
    @RequestMapping(value = "/deleteTender.html")
    public String deleteTender(@RequestParam(value = "tenderId") String tenderId, @RequestParam(value = "projectId") String projectId, Model model) {
        projectTendersService.delProjectTenders(tenderId);
        return "redirect:../BaseData/tendersManage.html?projectId" + projectId;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 进入新建标段页面
     */
    @RequestMapping(value = "/gotoAddTenders.html")
    public String gotoAddTenders(@RequestParam(value = "projectId") String projectId, Model model) {
        model.addAttribute("projectId", projectId);

        //获取该项目下所有楼栋
        List<ProjectBuildingDTO> list = projectBuildingService.getProjectBuildings(projectId, null);
        model.addAttribute("buildList", list);

        //获取对应总包列表，agencyList，责任单位总部下的所有子元素
//        List<AgencyEntity> agencyList = projectTendersService.getAgencyList(projectId);
        List<AuthSupplierDTO> agencyList=projectTendersService.getAuthSupplierList(projectId);

        model.addAttribute("agencyList", agencyList);
        return "/construction/baseData/projectTendersUpdate";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 进入修改标段页面
     */
    @RequestMapping(value = "/gotoUpdateTender")
    public String gotoUpdateTender(@RequestParam(value = "tenderId") String tenderId, Model model) {
        //根据标段id获取该标段详情
        ProjectTendersDTO projectTendersDTO = projectTendersService.getTendersDetail(tenderId);
        model.addAttribute("projectTender", projectTendersDTO);
        model.addAttribute("projectId", projectTendersDTO.getProjectId());
        //获取对应总包下的楼栋
        List<ProjectBuildingDTO> projectBuildingDTOList = projectBuildingService.getBuildersBySupplierId(projectTendersDTO.getSupplierId(),projectTendersDTO.getProjectId());
        model.addAttribute("supplierBuild", projectBuildingDTOList);
        //获取该项目下能选的所有楼栋
        List<ProjectBuildingDTO> list = projectBuildingService.getBuildsByProjectId(projectTendersDTO.getProjectId(), tenderId);
        model.addAttribute("buildList", list);
        //获取对应总包列表，agencyList，责任单位总部下的所有子元素
//        List<AgencyEntity> agencyList = projectTendersService.getAgencyList(projectTendersDTO.getProjectId());
        List<AuthSupplierDTO> agencyList=projectTendersService.getAuthSupplierList(projectTendersDTO.getProjectId());

        model.addAttribute("agencyList", agencyList);
        return "/construction/baseData/projectTendersUpdate";
    }

    /**
     * 根据总包ID查询对应得楼栋
     *
     * @param supplierId
     * @return
     */
    @RequestMapping(value = "/getBuildersBySupplierId")
    public ApiResult getBuildersBySupplierId(@RequestParam(value = "supplierId") String supplierId,@RequestParam(value = "projectId") String projectId) {
        List<ProjectBuildingDTO> projectBuildingDTOList = projectBuildingService.getBuildersBySupplierId(supplierId,projectId);
        return new SuccessApiResult(projectBuildingDTOList);
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增或修改标段
     */
    @RequestMapping(value = "/addTenders")
    public String addTenders(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectTendersDTO projectTendersDTO, Model model) {
        if (!StringUtils.isNullOrEmpty(projectTendersDTO.getTenderId())) {
            //修改
            projectTendersService.updateProjectTenders(projectTendersDTO);
        } else {
            //新增
            projectTendersService.addProjectTenders(projectTendersDTO, userInformationEntity);
        }
        return "redirect:../BaseData/tendersManage.html?projectId" + projectTendersDTO.getProjectId();
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param: tenderId 标段id， projectId 项目id
     * @Description: 标段检查项设置
     */
    @RequestMapping(value = "/tenderCategorySetting")
    public String tenderCategorySetting(@RequestParam(value = "tenderId") String tenderId, @RequestParam(value = "projectId") String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("tenderId", tenderId);
        return "/construction/baseData/tenderCategorySetting";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 检查项设置（某条标段）
     */
    @RequestMapping(value = "/tenderCategory")
    public String tenderCategory(@RequestParam(value = "tenderId") String tenderId, @RequestParam(value = "domain") String domain, @RequestParam(value = "projectId") String projectId, Model model) {
        String title;
        switch (domain) {
            case "1":
                title = "日常巡检";
                break;
            case "2":
                title = "检查验收";
                break;
            case "3":
                title = "样板点评";
                break;
            case "4":
                title = "材料验收";
                break;
            case "5":
                title = "旁站";
                break;
            case "6":
                title = "关键工序";
                break;
            default:
                title = "设置检查项";
                break;
        }
        model.addAttribute("projectId", projectId);
        model.addAttribute("tenderId", tenderId);
        model.addAttribute("domain", domain);
        model.addAttribute("titleName", title);
        return "/construction/baseData/tenderCategory";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 初始化标段、检查项的数据
     */
    @RequestMapping(value = "/initTenderCategory", method = RequestMethod.POST)
    public void initTenderCategory(HttpServletResponse response, @Valid TreeReceiveDTO treeReceiveDTO) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<CategoryTreeDTO> categoryTreeDTOs = projectCategoryService.getRootCategoryByTenderId(treeReceiveDTO.getTenderId(), treeReceiveDTO.getDomain(), treeReceiveDTO.getId());
        response.getWriter().print(JSONArray.fromObject(categoryTreeDTOs).toString());
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据模块和标段获取所有检查项
     */
    @RequestMapping(value = "/allTenderCategory", method = RequestMethod.GET)
    public void allTenderCategory(HttpServletResponse response, @RequestParam(value = "domain") String domain, @RequestParam(value = "tenderId") String tenderId) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<CategoryTreeDTO> categoryTreeDTOs = projectCategoryService.getCategoryAllByTenderId(domain, tenderId);
        response.getWriter().print(JSONArray.fromObject(categoryTreeDTOs).toString());
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 保存标段和检查项关系
     */
    @RequestMapping(value = "/addTenderCategory")
    public ApiResult addTenderCategory(@Valid TenderCategoryReceiveDTO tenderCategoryReceiveDTO) {
        projectCategoryService.addTenderCategory(tenderCategoryReceiveDTO);
        return new SuccessApiResult("ok");
    }


    /**
     * @param projectEmployDTO
     * @param model
     * @param webPage
     * @return 责任单位管理页
     */
    @RequestMapping(value = "/employManage.html")
    public String employManage(@Valid ProjectEmployDTO projectEmployDTO, Model model, WebPage webPage) {
//        List<ProjectEmployDTO> projectEmployDTOs = staffEmployService.getEmploys(projectEmployDTO, webPage);
        webPage.setPageSize(20);
        List<ProjectEmployDTO> getwebPage = staffEmployService.getAuthEmploys(projectEmployDTO, null);
        webPage.setRecordCount(getwebPage.size());

        List<ProjectEmployDTO> projectEmployDTOs = staffEmployService.getAuthEmploys(projectEmployDTO, webPage);
        model.addAttribute("employs", projectEmployDTOs);
        model.addAttribute("projectEmploy", projectEmployDTO);
        model.addAttribute("webPage", webPage);
        return "/construction/baseData/projectEmployManage";
    }

    /**
     * @param projectEmployDTO1
     * @param model
     * @return 跳转新增修改责任单位页面
     */
    @RequestMapping(value = "/goToUpdateEmploy.html")
    public String goToUpdateEmploy(@Valid ProjectEmployDTO projectEmployDTO1, Model model) {
        ProjectEmployDTO projectEmployDTO = new ProjectEmployDTO();
        if (!StringUtil.isEmpty(projectEmployDTO1.getsId())) {
//            projectEmployDTO = staffEmployService.getEmployDetail(projectEmployDTO1.getsId());
            projectEmployDTO = staffEmployService.getAuthEmployDetail(projectEmployDTO1.getsId(),projectEmployDTO1.getProjectId());

        }
//        List<SupplierDTO> supplierDTOs = supplierService.getSupplierList();      //供应商列表
        //查询所有的不关联当前项目的责任单位
        List<String> supplierNameList=staffEmployService.getAllSuoolierNameList(projectEmployDTO1.getProjectId());
        List<ProjectBuildingDTO> projectBuildingDTOs = projectBuildingService.getBuildingList(projectEmployDTO1.getProjectId()); //当前项目下楼栋列表
        List<String> buildIds = projectBuildingService.getDutysBuildId(projectEmployDTO1.getsId());//当前责任单位下关联的楼栋ID列表
        model.addAttribute("buildList", projectBuildingDTOs);
        model.addAttribute("buildIds", buildIds);
        model.addAttribute("employ", projectEmployDTO);
        model.addAttribute("projectId", projectEmployDTO1.getProjectId());
        model.addAttribute("nameList", supplierNameList);
//        model.addAttribute("supplierList", supplierDTOs);
        return "/construction/baseData/updateEmploy";
    }

    /**
     * @param projectEmployDTO
     * @return 新增或修改责任单位
     */
    @RequestMapping(value = "/updateEmploy.html")
    public String addEmploy(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,@Valid ProjectEmployDTO projectEmployDTO) {
        if (StringUtil.isEmpty(projectEmployDTO.getsId())) {         //责任单位ID为空时新增
//            staffEmployService.addEmploy(projectEmployDTO);
            staffEmployService.addAuthEmploy(projectEmployDTO,userInformationEntity);
        } else {    //ID不为空时则修改
//            staffEmployService.updateEmploy(projectEmployDTO);
            staffEmployService.updateAuthEmploy(projectEmployDTO,userInformationEntity);
        }
        return "redirect:../BaseData/employManage.html";
    }

    /**
     * @param sId
     * @param model
     * @param webPage
     * @return 工程阶段责任单位下人员列表管理页
     */
    @RequestMapping(value = "/projectStaff.html")
    public String projectStaffManage(@RequestParam(value = "sId") String sId, @RequestParam(value = "projectId") String projectId, Model model, WebPage webPage) {
        AgencyAdminDTO agencyAdminDTO = new AgencyAdminDTO();
        agencyAdminDTO.setAgencyId(sId);
        List<AgencyDTO> agencyDTOs = agencyService.staffListFor(agencyAdminDTO, webPage);
        model.addAttribute("staffList", agencyDTOs);
        model.addAttribute("sId", sId);
        model.addAttribute("projectId", projectId);
        return "/construction/baseData/projectStaffList";
    }

    /**
     * @param model
     * @param webPage
     * @return 工程阶段责任单位下人员列表管理页 新权限
     */
    @RequestMapping(value = "/authProjectStaff.html")
    public String authProjectStaff(@Valid AuthSupplierRoleUserDTO authSupplierRoleUserDTO, Model model, WebPage webPage) {

        webPage.setPageSize(20);
        AuthSupplierRoleUserDTO supplierRoleUser=agencyService.getAuthAgencySupplierUser(authSupplierRoleUserDTO, null);
        webPage.setRecordCount(supplierRoleUser.getUserList().size());
        AuthSupplierRoleUserDTO getSupplierRoleUser=agencyService.getAuthAgencySupplierUser(authSupplierRoleUserDTO, webPage);
        model.addAttribute("staffList", getSupplierRoleUser);
        model.addAttribute("sId", authSupplierRoleUserDTO.getSupplierId());
        model.addAttribute("projectId", authSupplierRoleUserDTO.getAgencyId());
        model.addAttribute("agencyType", authSupplierRoleUserDTO.getAgencyType());
        model.addAttribute("webPage", webPage);
        return "/construction/baseData/projectStaffList";
    }
    /**
     * @return 跳转新增修改人员页
     */
    @RequestMapping(value = "/goToAltStaff.html")
    public String goToAltStaff(@RequestParam(value = "staffId", required = false) String staffId, @RequestParam(value = "sId") String sId, @RequestParam(value = "projectId") String projectId, Model model) {
        StaffDTO staffDTO = new StaffDTO();
        if (!StringUtil.isEmpty(staffId)) {       //如果人员ID不为空则为修改
            staffDTO = userPropertystaffService.getStaffDetail(staffId);
        }
        AgencyDTO agencyDTO = agencyService.getAgencyDetail(sId);
        model.addAttribute("userStaffDTO", staffDTO);
        model.addAttribute("agency", agencyDTO);
        model.addAttribute("projectId", projectId);
        return "/construction/baseData/altProjectStaff";
    }

    /**
     * @return 跳转新增修改人员页
     */
    @RequestMapping(value = "/authGoToAltStaff.html")
    public String authGoToAltStaff(@Valid AuthSupplierPeopleDTO authSupplierPeopleDTO,  Model model) {
        AuthSupplierPeopleDTO staffDTO = new AuthSupplierPeopleDTO();

        AuthSupplierPeopleDTO supplierName=agencyService.getsupplierName(authSupplierPeopleDTO);
        List<AuthSupplierAgencyRoleDTO> roleList=agencyService.getAllAuthSupplierAgencyRole(authSupplierPeopleDTO);

        if (!StringUtil.isEmpty(authSupplierPeopleDTO.getUserId())) {       //如果人员ID不为空则为修改
            staffDTO = agencyService.getAuthStaffDetail(authSupplierPeopleDTO);
            if(roleList!=null && roleList.size()>0){
                List<AuthSupplierAgencyRoleDTO> getRoleList=roleList;
                if(staffDTO.getRoleList()!=null && staffDTO.getRoleList().size()>0){
                    for(int i=0;i<roleList.size();i++){
                        for(int j=0;j<staffDTO.getRoleList().size();j++){
                            if(roleList.get(i).getRoleId().equals(staffDTO.getRoleList().get(j).getRoleId())){
                                getRoleList.remove(roleList.get(i));
                            }
                        }
                    }
                    roleList=getRoleList;
                    roleList.addAll(staffDTO.getRoleList());
                }
            }
        }
        model.addAttribute("userStaffDTO", staffDTO);
        model.addAttribute("agency", authSupplierPeopleDTO);
        model.addAttribute("supplier", supplierName);
        model.addAttribute("roleList", roleList);
        String checkRoleList="0";
        if(roleList!=null && roleList.size()>0){
            checkRoleList="1";
        }
        model.addAttribute("checkRoleList", checkRoleList);
        return "/construction/baseData/altProjectStaff";
    }

    /**
     * @return 跳转新增修改人员页
     */
    @RequestMapping(value = "/authPprojectStaffDetail.html")
    public String authProjectStaffDetail(@Valid AuthSupplierPeopleDTO authSupplierPeopleDTO,  Model model) {
        AuthSupplierPeopleDTO staffDTO = new AuthSupplierPeopleDTO();

        AuthSupplierPeopleDTO supplierName=agencyService.getsupplierName(authSupplierPeopleDTO);
        List<AuthSupplierAgencyRoleDTO> roleList=new ArrayList<AuthSupplierAgencyRoleDTO>();
        if (!StringUtil.isEmpty(authSupplierPeopleDTO.getUserId())) {       //如果人员ID不为空则为修改
            staffDTO = agencyService.getAuthStaffDetail(authSupplierPeopleDTO);
            if(staffDTO.getRoleList()!=null && staffDTO.getRoleList().size()>0) {
                roleList.addAll(staffDTO.getRoleList());
            }
        }
        model.addAttribute("userStaffDTO", staffDTO);
        model.addAttribute("agency", authSupplierPeopleDTO);
        model.addAttribute("supplier", supplierName);
        model.addAttribute("roleList", roleList);
        return "/construction/baseData/projectStaffDetail";
    }

    /**
     * @return 工程阶段责任单位下人员详情页
     */
    @RequestMapping(value = "/projectStaffDetail.html")
    public String projectStaffDetail(@RequestParam(value = "staffId") String staffId, Model model) {
        StaffDTO staffDTO = userPropertystaffService.getStaffDetail(staffId);
        model.addAttribute("userStaffDTO", staffDTO);
        return "/construction/baseData/projectStaffDetail";
    }

    /**
     * @param employId
     * @param model
     * @return 责任单位对应关系页面
     */
    @RequestMapping(value = "/defaultPeople.html")
    public String defaultPeople(@RequestParam(value = "employId") String employId, @RequestParam(value = "projectId") String projectId, @RequestParam(value = "domain", required = false) String domain, Model model) {
        if (StringUtil.isEmpty(domain)) {
            domain = "1";
        }
        List<SupplierCategoryDTO> supplierCategoryDTOs = projectCategoryService.getSupplierCategory(employId, domain);
        List<ProjectEmployDTO> projectEmployDTOs = projectCategoryService.getEmploys();     //责任单位列表
        List<ProjectEmployDTO> projectEmployDTOList = projectCategoryService.getEmploys();   //监理列表

        AgencyDTO agencyDTO = agencyService.getAgencyDetail(employId);
        model.addAttribute("categoryList", supplierCategoryDTOs);
        model.addAttribute("employList", projectEmployDTOs);
        model.addAttribute("surveyor", projectEmployDTOList);
        model.addAttribute("projectId", projectId);
        model.addAttribute("employ", agencyDTO);
        model.addAttribute("domainValue", domain);
        return "/construction/baseData/defaultPeople";
    }

    /**
     * @param supplierCategoryDTO
     * @return 新增责任单位与检查项默认人
     */
    @RequestMapping(value = "/addDefaultPeople.html")
    public String addDefaultPeople(@Valid SupplierCategoryDTO supplierCategoryDTO) {
        projectCategoryService.updateSupplierCategory(supplierCategoryDTO);
        return "redirect:../BaseData/defaultPeople.html?employId" + supplierCategoryDTO.getSupplierId();
    }

    /**
     * @param employId
     * @param projectId
     * @param model
     * @return 检查项设置页面
     */
    @RequestMapping(value = "/categorySetting.html")
    public String categorySetting(@RequestParam(value = "employId") String employId, @RequestParam(value = "projectId") String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("employId", employId);
        return "/construction/baseData/categorySetting";
    }

    /**
     * @param employId
     * @param projectId
     * @param model
     * @return 责任单位与检查项
     */
    @RequestMapping(value = "/employCategory.html")
    public String employCategory(@RequestParam(value = "employId") String employId, @RequestParam(value = "domain") String domain, @RequestParam(value = "projectId") String projectId, Model model) {
        String title;
        switch (domain) {
            case "1":
                title = "日常巡检";
                break;
            case "2":
                title = "检查验收";
                break;
            case "3":
                title = "样板点评";
                break;
            case "4":
                title = "材料验收";
                break;
            case "5":
                title = "旁站";
                break;
            case "6":
                title = "关键工序";
                break;
            default:
                title = "设置检查项";
                break;
        }
        model.addAttribute("projectId", projectId);
        model.addAttribute("employId", employId);
        model.addAttribute("domain", domain);
        model.addAttribute("titleName", title);
        return "/construction/baseData/employCategory";
    }

    /**
     * @param response
     * @param domain
     * @throws IOException 根据模块获取所有检查项
     */
    @RequestMapping(value = "/allCategory", method = RequestMethod.GET)
    public void allCategory(HttpServletResponse response, @RequestParam(value = "domain") String domain, @RequestParam(value = "dutyId") String dutyId) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<CategoryTreeDTO> categoryTreeDTOs = projectCategoryService.getCategoryAllByDutyId(domain, dutyId);
        response.getWriter().print(JSONArray.fromObject(categoryTreeDTOs).toString());
    }

    /**
     * @param response
     * @param treeReceiveDTO
     * @throws IOException ztree初始化责任单位与检查项的数据
     */
    @RequestMapping(value = "/initCategory", method = RequestMethod.POST)
    public void initCategory(HttpServletResponse response, @Valid TreeReceiveDTO treeReceiveDTO) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<CategoryTreeDTO> categoryTreeDTOs = projectCategoryService.getRootCategorys(treeReceiveDTO.getEmployId(), treeReceiveDTO.getDomain(), treeReceiveDTO.getId());
        response.getWriter().print(JSONArray.fromObject(categoryTreeDTOs).toString());
    }

    /**
     * @param response
     * @param treeReceiveDTO
     * @throws IOException 根据模块初始化检查项信息
     */
    @RequestMapping(value = "/categoryTree", method = RequestMethod.POST)
    public void categoryTree(HttpServletResponse response, @Valid TreeReceiveDTO treeReceiveDTO) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<CategoryTreeDTO> categoryTreeDTOs = projectCategoryService.getCategoryTree(treeReceiveDTO.getDomain(), treeReceiveDTO.getId());
        response.getWriter().print(JSONArray.fromObject(categoryTreeDTOs).toString());
    }

    /**
     * @param categoryReceiveDTO
     * @return 保存责任单位与检查项关系
     */
    @RequestMapping(value = "/addEmployCategory")
    public ApiResult addEmployCategory(@Valid CategoryReceiveDTO categoryReceiveDTO) {
        projectCategoryService.addSupplierCategory(categoryReceiveDTO);// 保存责任单位与检查项关系
        return new SuccessApiResult("ok");
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增或修改楼栋
     */
    @RequestMapping(value = "/addBuilding", method = RequestMethod.POST)
    public ApiResult addBuilding(@Valid ProjectBuildingDTO projectBuildingDTO, @RequestParam(value = "projectId") String projectId) {
        if (StringUtil.isEmpty(projectBuildingDTO.getBuildId())) {     //如果楼栋ID为空则为新增
            return projectBuildingService.insertBuilding(projectBuildingDTO.getBuildName(), projectId);
        } else {
            return projectBuildingService.updateProjectBuilding(projectBuildingDTO, projectId);
        }
    }


    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼层
     */
    @RequestMapping(value = "/addFloor")
    public String addFloor(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectFloorDTO projectFloorDTO) {
        String result = projectBuildingService.insertFloor(projectFloorDTO, userInformationEntity);
        return "redirect:../BaseData/projectFloorManage.html";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量添加楼层
     */
    @RequestMapping(value = "/executeAddFloor")
    public String executeAddFloor(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @RequestParam(value = "buildId") String buildId, @Valid ProjectFloorDTO projectFloorDTO, Model model) {
//        projectBuildingService.executeFloor(projectFloorDTO, userPropertystaffEntity);
        projectBuildingService.updateBatchFloorFromJason(projectFloorDTO, userInformationEntity);
        return "redirect:../BaseData/projectFloorManage.html";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 修改楼层
     */
    @RequestMapping(value = "/updateFloor")
    public String updateFloor(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, @Valid ProjectFloorDTO projectFloorDTO) {
        String result = projectBuildingService.updateFloor(projectFloorDTO, userInformationEntity);
        return "redirect:../BaseData/projectFloorManage.html?";
//        return "redirect:../BaseData/projectFloorManage.html?buildId=" + projectFloorDTO.getBuildId() + "&projectId=" + projectFloorDTO.getProjectId() + "&result=" + result;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 获取该楼层对应的户型图，点击修改时，回显
     */
    @RequestMapping(value = "/getHouseImgByFloorId")
    @ResponseBody
    public ApiResult getHouseImgByFloorId(@RequestParam(value = "floorId") String floorId) {
        ProjectHouseImageEntity projectHouseImageEntity = projectBuildingService.getHouseImgByFloorId(floorId);
        String imageUrl = null;
        if (projectHouseImageEntity != null) {
            imageUrl = projectHouseImageEntity.getImgUrl();
            return new SuccessApiResult(imageUrl);
        }
        return new SuccessApiResult(imageUrl);
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量修改楼层
     */
    @RequestMapping(value = "/executeUpdateFloor")
    public String executeUpdateFloor(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectFloorDTO projectFloorDTO, Model model) {
//        projectBuildingService.executeUpdateFloor(projectFloorDTO, userPropertystaffEntity);
        projectBuildingService.updateBatchFloorFromJason(projectFloorDTO, userInformationEntity);
        return "redirect:../BaseData/projectFloorManage.html";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 批量修改，获取最新修改的图片
     */
    @RequestMapping(value = "/getHouseImg")
    @ResponseBody
    public ApiResult getHouseImg() {
        ProjectHouseImageEntity projectHouseImageEntity = projectBuildingService.getHouseImg();
        if (projectHouseImageEntity != null) {
            return new SuccessApiResult(projectHouseImageEntity.getImgUrl());
        }
        return null;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导出excel
     */
    @RequestMapping("/projectBuildingExcel")
    @ResponseBody
    public String activityProjectApplyExcel(@RequestParam(value = "projectId") String projectId, WebPage webPage, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        try {
            return projectBuildingService.exportProjectBuildingExcel(projectId, webPage, httpServletResponse, httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 楼栋下载模板
     */
    @RequestMapping(value = "/downloadModel")
    public String downloadModel(HttpServletRequest request, HttpServletResponse response) {
        try {
            return projectBuildingService.downloadModel(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
//        String fileName = "楼栋列表模板";
//        response.reset();
//        response.setContentType("application/x-xls");
//        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//        String agent = request.getHeader("USER-AGENT").toLowerCase();
////        if (agent.contains("firefox")) {
//            response.setCharacterEncoding("utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xlsx");
////        } else {
////            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xlsx");
////        }
//        String title = "楼栋列表模板";
//        String[] headers = {"楼栋名称", "开始楼层（只能为数字）", "结束楼层（只能为数字）"};
//
//        ServletOutputStream out = response.getOutputStream();
//        projectBuildingService.downloadModel(title, headers, out);
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 检查项下载模板
     */
    @RequestMapping(value = "/exportModel")
    public String exportModel(@RequestParam(value = "domain") String domain, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return projectCategoryService.exportModel(domain, httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * 楼栋批量导入
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导入excel
     */
    @RequestMapping(value = "/importExcel")
    public String importExcel(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, @RequestParam(value = "projectId") String projectId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            boolean flag = projectBuildingService.importExcelByPoi(userInformationEntity, fis, projectId);
            String result = "";
            if (!flag) {
                result = "导入失败！";
            } else {
                result = "导入成功！";
            }
            httpServletRequest.getSession().setAttribute("result", result);

            //关闭流
            fis.close();
            return "redirect:../BaseData/projectBuilding.html?projectId" + projectId;
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "系统错误";
        }
    }

    /**
     * 楼栋批量导入
     *
     * @Author: talent
     * @Date:
     * @Description: 导入excel
     */
    @RequestMapping(value = "/importBuildsExcel", method = RequestMethod.POST)
    public String importBuildsExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            String projectId = request.getParameter("projectId");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            boolean flag = projectBuildingService.importBuildsExcel(userInformationEntity, fis, projectId);
            String result = "";
            if (!flag) {
                result = "导入失败！";
            } else {
                result = "导入成功！";
            }
            httpServletRequest.getSession().setAttribute("result", result);

            //关闭流
            fis.close();
            return "redirect:../BaseData/projectBuilding.html?projectId" + projectId;
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "系统错误";
        }
    }

    /**
     * @param domain
     * @param httpServletRequest
     * @return 检查项导入
     */
    @RequestMapping(value = "/importCategoryExcel", method = RequestMethod.POST)
    public String importCategoryExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @RequestParam(value = "domain") String domain, HttpServletRequest httpServletRequest, Model model) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            String flag = projectCategoryService.importCategoryExcel(fis, domain, userInformationEntity);
            String result = "";
            if ("ok".equals(flag)) {
                result = "导入成功！";
            } else {
                result = flag;
            }
            httpServletRequest.getSession().setAttribute("result", result);

            //关闭流
            fis.close();
            model.addAttribute("domain", domain);
            return "redirect:../BaseData/categoryManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../BaseData/categoryManage.html";
        }
    }

    /**
     * 根据项目ID获取楼栋列表
     *
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/getBuildingListByProject")
    public ApiResult getBuildingListByProject(@RequestParam(value = "projectId") String projectId) {
        Map map = projectBuildingService.getBuildListByProjectId(projectId);
        return new SuccessApiResult(map);
    }

    /**
     * @return 检查项管理页
     */
    @RequestMapping(value = "/categoryManage.html")
    public String categoryManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,@Valid Model model) {
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020056 下載模板 ESH40020057 导入 ESH40020058 导出
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020056":
                        checkAuthFunctionDTO.setEsh40020056("Y");
                        break;
                    case "ESH40020057":
                        checkAuthFunctionDTO.setEsh40020057("Y");
                        break;
                    case "ESH40020058":
                        checkAuthFunctionDTO.setEsh40020058("Y");
                        break;

                }
            }
        }
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/baseData/categoryManage";
    }

    /**
     * @return 根据检查项获取指标列表
     */
    @RequestMapping(value = "/getTarget", method = RequestMethod.GET)
    public ApiResult getTarget(@RequestParam(value = "categoryId") String categoryId, @RequestParam(value = "domain") String domain) {
        if ("5".equals(domain)) {
            return projectCategoryService.getCategory(categoryId);
        } else {
            return projectCategoryService.getTargetList(categoryId);
        }
    }

    /**
     * @param categoryId
     * @param categoryName
     * @return 修改检查项名称
     */
    @RequestMapping(value = "/altCategory", method = RequestMethod.GET)
    public ApiResult altCategory(@RequestParam(value = "categoryId") String categoryId, @RequestParam(value = "categoryName") String categoryName) {
        return projectCategoryService.altCategory(categoryId, categoryName);
    }

    /**
     * @param categoryId
     * @return 删除检查项
     */
    @RequestMapping(value = "/delCategory", method = RequestMethod.GET)
    public ApiResult delCategory(@RequestParam(value = "categoryId") String categoryId) {
        return projectCategoryService.delCategory(categoryId);
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 导出检查项
     */
    @RequestMapping(value = "/exportCategory")
    public String exportCategory(@RequestParam(value = "domain") String domain, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return projectCategoryService.exportCategory(domain, httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * @param model
     * @return 经营单位管理页面
     */
    @RequestMapping(value = "/operationManage.html")
    public String operationManage(Model model) {
        List<OperationDTO> operationDTOs = projectCityService.getOperationList();
        model.addAttribute("OperationList", operationDTOs);
        return "/construction/baseData/projectOperationManage";
    }

    /**
     * @param optName
     * @return 新增经营单位
     */
    @RequestMapping(value = "/addOperation.html")
    public String addOperation(@RequestParam(value = "optName") String optName) {
        projectCityService.addProjectOperation(optName);
        return "redirect:../BaseData/operationManage.html";
    }

    /**
     * @param operationDTO
     * @return 修改经营单位
     */
    @RequestMapping(value = "/updateOperation", method = RequestMethod.POST)
    public ApiResult updateOperation(@Valid OperationDTO operationDTO) {
        projectCityService.updateProjectOperation(operationDTO);
        String optName = operationDTO.getOptName();
        return new SuccessApiResult(optName);
    }

    /**
     * @param optId
     * @return 删除经营单位
     */
    @RequestMapping(value = "/delOperation.html")
    public String delOperation(@RequestParam(value = "optId") String optId) {
        projectCityService.delProjectOperation(optId);
        return "redirect:../BaseData/operationManage.html";
    }

    /**
     * @param model
     * @return 工程区域城市管理页面
     */
    @RequestMapping(value = "/projectCityManage.html")
    public String projectCityManage(@RequestParam(value = "optId") String optId, Model model) {
        List<OperationDTO> operationDTOs = projectCityService.getAllCityList();
        Map map = projectCityService.getProjectOperationList();
        model.addAttribute("cityList", operationDTOs);
        model.addAttribute("operationList", map);
        model.addAttribute("areaId", optId);
        return "/construction/baseData/projectCityManage";
    }

    /**
     * @param operationDTO
     * @return 修改工程区域城市信息
     */
    @RequestMapping(value = "/altProjectCity.html")
    public String altProjectCity(@Valid OperationDTO operationDTO) {
        if (StringUtil.isEmpty(operationDTO.getCityId())) {
            projectCityService.addProjectCity(operationDTO);
        } else {
            projectCityService.updateProjectCity(operationDTO);
        }
        return "redirect:../BaseData/projectCityManage.html?optId="+operationDTO.getOptId();
    }

    /**
     * @param cityName
     * @return 校验城市名称
     */
    @RequestMapping(value = "/chekProjectCity")
    public ApiResult chekProjectCityProjectCity(@RequestParam(value = "cityName") String cityName) {
        if (projectCityService.checkProjectCity(cityName)) {
            return new SuccessApiResult("1");//城市名不存在
        } else {
            return new SuccessApiResult("0");//城市名存在
        }
    }

    /**
     * @param optId
     * @return 根据经营单位获取城市列表
     */
    @RequestMapping(value = "/getThisCity")
    public ApiResult getThisCity(@RequestParam(value = "optId") String optId) {
        Map map = projectCityService.getCityListByOptId(optId);
        return new SuccessApiResult(map);
    }

    /**
     * @param cityId
     * @return 删除工程区域城市
     */
    @RequestMapping(value = "/delProjectCity.html")
    public String delProjectCity(String cityId) {
        projectCityService.delProjectCity(cityId);
        return "redirect:../BaseData/projectCityManage.html";
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 机构与人员的下载模板
     */
    @RequestMapping(value = "/exportMechanismPeopleModel")
    @ResponseBody
    public String exportMechanismPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return projectCategoryService.exportMechanismPeopleModel(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 机构与人员的下载模板
     */
    @RequestMapping(value = "/authExportMechanismPeopleModel")
    @ResponseBody
    public String authExportMechanismPeopleModel(@Valid AuthSupplierRoleUserDTO authSupplierRoleUserDTO,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return projectCategoryService.authExportMechanismPeopleModel(authSupplierRoleUserDTO,httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * @param httpServletRequest
     * @return 机构与人员导入
     */
    @RequestMapping(value = "/importMechanismPeopleExcel", method = RequestMethod.POST)
    public String importMechanismPeopleExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest httpServletRequest, @RequestParam(value = "projectId") String projectId, RedirectAttributes attr) {
        try {
//            System.out.print("开始时间===================="+new Date());
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            String fileName = file.getOriginalFilename();
            //POI:得到解析Excel的实体集合
//            String result = projectCategoryService.importMechanismPeopleExcel(fis, projectId, userInformationEntity);
            //auth 新修改
            String result = projectCategoryService.importAuthMechanismPeopleExcel(fis, projectId, userInformationEntity);


//            List<String> attributes = Arrays.asList(new String[]{"机构名称", "机构性质", "机构备注", "用户名", "姓名", "联系方式", "人员备注"});
//            int attributesNotNullNumber = 7; // 表示Attributes前AttributesNotNullNumber项为必填项
//            String result = projectCategoryService.importMechanismPeopleExcel(fis, projectId, userPropertyStaffEntity, fileName, attributes, attributesNotNullNumber);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
//            System.out.print("结束时间============="+new Date());
            attr.addAttribute("projectId", projectId);
            return "redirect:/BaseData/employManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            attr.addAttribute("projectId", projectId);
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:/BaseData/employManage.html";
        }
    }

    /**
     * 模糊查询项目列表
     */
    @RequestMapping(value = "/getProjectByName")
    public ApiResult getProjectByName(@RequestParam(value = "projectName") String projectName){
        List<ProjectDTO> list = projectProjectService.getProjectByName(projectName);
        return new SuccessApiResult(list);
    }

    /**
     * 组织树
     */
    @RequestMapping(value = "/initProject")
    public void initProject(HttpServletResponse response,@Valid ProjectTreeDTO projectTreeDTO) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<ProjectTreeDTO> projectTreeDTOS;
        if(projectTreeDTO.getId()!=null&&!StringUtil.isEmpty(projectTreeDTO.getId())){
            projectTreeDTOS = projectProjectService.getProjectList(projectTreeDTO.getId());
        }else{
            projectTreeDTOS = projectProjectService.getAreaList();
        }
        response.getWriter().print(JSONArray.fromObject(projectTreeDTOS).toString());
    }
    /**
     * 组织树获取项目
     */
    @RequestMapping(value = "/getNextProject")
    public ApiResult getNextProject(@RequestParam(value = "id")String pId){
        List<ProjectTreeDTO> projectTreeDTOS;
        if(pId.equals("1")) {
            projectTreeDTOS = projectProjectService.getProjectList(pId);
        }else{
            projectTreeDTOS = projectProjectService.getNextProjectList(pId);
        }
        return new SuccessApiResult(projectTreeDTOS);
    }

    /**
     * 人员项目设置
     */
    @RequestMapping(value = "/projectRoleEdit.html")
    public String projectRoleEdit(Model model) {
        ProjectStaffRelationDTO projectStaffRelationDTO = projectProjectService.getProjectRole();
        model.addAttribute("projectStaffRelationDTO", projectStaffRelationDTO);
        return "/construction/baseData/projectSystemAdmin";
    }

    /**
     * 人员项目
     */
    @RequestMapping(value = "/saveAreaRole.html")
    public String updateProjectRole(@Valid ConstructionProjectDTO constructionProjectDTO){
        projectProjectService.updateProjectRole(constructionProjectDTO);
        return "redirect:../BaseData/projectRoleManage.html";
    }


    /**
     * 添加角色信息
     */
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public Map<String,Object> checkName(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaffEntity,
                                          Model model, @Valid AuthCheckNameDTO authCheckNameDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int check=projectProjectService.checkName(authCheckNameDTO);
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }


    /**
     * 新增、编辑 员工
     */
    @RequestMapping(value = "/addUserStaff")
    public ApiResult addStaff(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaffEntity,
                              @Valid AuthSupplierPeopleDTO authSupplierPeopleDTO) {
        String flag;
        if (!StringUtil.isEmpty(authSupplierPeopleDTO.getUserId1())) {   //如果员工ID不为空 则为编辑
            flag = userPropertystaffService.alterAuthStaff(authSupplierPeopleDTO, userPropertystaffEntity);
        } else {        //否则为新增
            flag = userPropertystaffService.saveAuthStaff(authSupplierPeopleDTO, userPropertystaffEntity);
        }
        return new SuccessApiResult(flag);
    }
}

