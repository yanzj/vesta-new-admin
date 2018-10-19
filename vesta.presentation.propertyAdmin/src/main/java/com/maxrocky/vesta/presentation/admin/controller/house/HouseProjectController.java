package com.maxrocky.vesta.presentation.admin.controller.house;

import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.DTO.json.HI0001.ProjectReturnJsonDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleDataAdminDTO;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.RoleDataService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by chen on 2016/6/20.
 */
@Controller
@RequestMapping(value = "/project")
public class HouseProjectController {
    @Autowired
    HouseProjectService houseProjectService;
    @Autowired
    AgencyService agencyService;
    @Autowired
    OrganizeService organizeService;
    @Autowired
    RoleDataService roleDataService;

    /**项目管理页*/
    @RequestMapping(value = "/projectList.html")
    public String projectManage(@Valid HouseProjectDto houseProjectDto,Model model,WebPage webPage){
        List<HouseProjectDto> houseProjectDtos = houseProjectService.getProjectAll(houseProjectDto, webPage);
        model.addAttribute("projectList",houseProjectDtos);
        model.addAttribute("projectName",houseProjectDto.getName());
        model.addAttribute("originName",houseProjectDto.getOriginName());
        return "house/projectManage";
    }

    /**项目详情*/
    @RequestMapping(value = "/projectDetail.html")
    public String projectDetail(@RequestParam(value = "projectId")String projectId,Model model){
        HouseProjectDto houseProjectDto = houseProjectService.getProjectById(projectId);   //项目详情
//        List<AgencyAdminDTO> staffs = agencyService.staffListByProjectId(projectId);      //关联的人
//        List<AgencyAdminDTO> agencyAdminDTOs = agencyService.agencyListByProjectId(projectId); //关联的组织机构
//        List<OrganizeDTO> organizeDTOs = organizeService.getOrganizeList();
        List<AgencyAdminDTO> agencyAdminDTO1 = agencyService.getAgencyByProjectId(projectId,"1"); //获取数据查看相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO2 = agencyService.getAgencyByProjectId(projectId,"2");//获取工程接单相关的组织结构
        List<AgencyAdminDTO> agencyAdminDTO5 = agencyService.getAgencyByProjectId(projectId, "3"); //获取派单到人相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO6 = agencyService.getAgencyByProjectId(projectId, "4"); //获取关单权限相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO7 = agencyService.getAgencyByProjectId(projectId, "5"); //获取验房工程师相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO8 = agencyService.getAgencyByProjectId(projectId, "6"); //获取质量经理相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO9 = agencyService.getAgencyByProjectId(projectId, "7"); //获取乙方相关的组织机构
        List<AgencyAdminDTO> dispatchSheetDTO = agencyService.getAgencyByProjectId(projectId, "8"); //获取（投诉-项目前台）报事派单相关的组织机构
        List<AgencyAdminDTO> HQObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "9"); //获取（总部客观）投诉单废弃权限相关的组织机构

        List<AgencyAdminDTO> Repair1ObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "10"); //获取（报修-数据查看）废弃权限相关的组织机构
        List<AgencyAdminDTO> ComplaintObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "11"); //获取（投诉-数据查看）废弃权限相关的组织机构
        List<AgencyAdminDTO> ReceptionObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "12"); //获取（报修-项目前台）废弃权限相关的组织机构
        List<AgencyAdminDTO> ComplaintsButtDTO = agencyService.getComplaintByProjectId(projectId); //获取（投诉-投诉对接人）废弃权限相关的组织机构

        List<AgencyAdminDTO> agencyAdminDTO3 = agencyService.getAgencyByProjectId(projectId,"100000001");//获取物业接单相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO4 = agencyService.getAgencyByProjectId(projectId,"100000000");//获取开发接单相关的组织机构
        model.addAttribute("project",houseProjectDto);
//        model.addAttribute("organizeInList",organizeDTOs);
//        model.addAttribute("organizeOutList",organizeDTOList);
//        model.addAttribute("agencyList", agencyAdminDTOs);
        model.addAttribute("views",agencyAdminDTO1);
        model.addAttribute("plumbs",agencyAdminDTO2);
        model.addAttribute("properties",agencyAdminDTO3);
        model.addAttribute("makes",agencyAdminDTO4);
        model.addAttribute("dispatches",agencyAdminDTO5);
        model.addAttribute("closeBills",agencyAdminDTO6);
        model.addAttribute("checkInspect",agencyAdminDTO7);
        model.addAttribute("qualityManager",agencyAdminDTO8);
        model.addAttribute("secondParty",agencyAdminDTO9);
        model.addAttribute("dispatchSheet",dispatchSheetDTO);
        model.addAttribute("HQObjective",HQObjectiveDTO);
        model.addAttribute("Repair1Objective",Repair1ObjectiveDTO);
        model.addAttribute("ComplaintObjective",ComplaintObjectiveDTO);
        model.addAttribute("ReceptionObjective",ReceptionObjectiveDTO);
        model.addAttribute("ComplaintsButt",ComplaintsButtDTO);
//        model.addAttribute("staffList",staffs);
//        model.addAttribute("agencyList",agencyAdminDTOs);
//        model.addAttribute("organizeList",organizeDTOs);
        return "house/ProjectDetail";
    }

    /**项目编辑页面*/
    @RequestMapping(value = "/updateProject.html")
    public String updateProject(@RequestParam(value = "projectId",required = false)String projectId,Model model){
        HouseProjectDto houseProjectDto = houseProjectService.getProjectById(projectId);
//        List<OrganizeDTO> organizeDTOs = organizeService.getOrganizeInList(projectId);       //项目内组列表
//        List<OrganizeDTO> organizeDTOList = organizeService.getOrganizeOutList(projectId);   //项目外组列表
//        List<AgencyAdminDTO> agencyAdminDTOs = agencyService.getAgencyByProjectId(projectId); //获取与该项目关联的组织架构列表
        List<AgencyAdminDTO> agencyAdminDTO1 = agencyService.getAgencyByProjectId(projectId,"1"); //获取数据查看相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO2 = agencyService.getAgencyByProjectId(projectId,"2");//获取工程接单相关的组织结构
        List<AgencyAdminDTO> agencyAdminDTO5 = agencyService.getAgencyByProjectId(projectId, "3"); //获取派单到人相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO6 = agencyService.getAgencyByProjectId(projectId, "4"); //获取关单权限相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO7 = agencyService.getAgencyByProjectId(projectId, "5"); //获取验房工程师相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO8 = agencyService.getAgencyByProjectId(projectId, "6"); //获取质量经理相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO9 = agencyService.getAgencyByProjectId(projectId, "7"); //获取乙方相关的组织机构
        List<AgencyAdminDTO> dispatchSheetDTO = agencyService.getAgencyByProjectId(projectId, "8"); //获取（投诉-项目前台）报事派单相关的组织机构
        List<AgencyAdminDTO> HQObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "9"); //获取（总部客观）投诉单废弃权限相关的组织机构

        List<AgencyAdminDTO> Repair1ObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "10"); //获取（报修-数据查看）废弃权限相关的组织机构
        List<AgencyAdminDTO> ComplaintObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "11"); //获取（投诉-数据查看）废弃权限相关的组织机构
        List<AgencyAdminDTO> ReceptionObjectiveDTO = agencyService.getAgencyByProjectId(projectId, "12"); //获取（报修-项目前台）废弃权限相关的组织机构
        List<AgencyAdminDTO> ComplaintsButtDTO = agencyService.getComplaintByProjectId(projectId); //获取（投诉-投诉对接人）废弃权限相关的组织机构

        List<AgencyAdminDTO> agencyAdminDTO3 = agencyService.getAgencyByProjectId(projectId,"100000001");//获取物业接单相关的组织机构
        List<AgencyAdminDTO> agencyAdminDTO4 = agencyService.getAgencyByProjectId(projectId,"100000000");//获取开发接单相关的组织机构
        model.addAttribute("project",houseProjectDto);
//        model.addAttribute("organizeInList",organizeDTOs);
//        model.addAttribute("organizeOutList",organizeDTOList);
//        model.addAttribute("agencyList", agencyAdminDTOs);
        model.addAttribute("views",agencyAdminDTO1);
        model.addAttribute("plumbs",agencyAdminDTO2);
        model.addAttribute("properties",agencyAdminDTO3);
        model.addAttribute("makes",agencyAdminDTO4);
        model.addAttribute("dispatches",agencyAdminDTO5);
        model.addAttribute("closeBills",agencyAdminDTO6);
        model.addAttribute("checkInspect",agencyAdminDTO7);
        model.addAttribute("qualityManager",agencyAdminDTO8);
        model.addAttribute("secondParty",agencyAdminDTO9);
        model.addAttribute("dispatchSheet",dispatchSheetDTO);
        model.addAttribute("HQObjective",HQObjectiveDTO);
        model.addAttribute("Repair1Objective",Repair1ObjectiveDTO);
        model.addAttribute("ComplaintObjective",ComplaintObjectiveDTO);
        model.addAttribute("ReceptionObjective",ReceptionObjectiveDTO);
        model.addAttribute("ComplaintsButt",ComplaintsButtDTO);
//        return "house/updateProject";
        return "house/updateProjectClassification";//分类权限临时修改！
    }

    /**更新项目信息*/
    @RequestMapping(value = "/altProject.html")
    public String altProject(@Valid HouseProjectDto houseProjectDto){
        if(houseProjectDto.getId()!=null && !StringUtil.isEmpty(houseProjectDto.getId())){   //如果项目ID不为空 则视为修改
            houseProjectService.updateProject(houseProjectDto);
        }else{ //如果项目ID为空 则视为新增
            houseProjectService.addProject(houseProjectDto);
        }
        return "redirect:../project/projectList.html";
    }

    /**新增组 项目关系*/
    @RequestMapping(value = "/addProjectOrganize",method = RequestMethod.GET)
    public ApiResult addProjectOrganize(@Valid RoleDataAdminDTO roleDataAdminDTO){
        roleDataAdminDTO.setAuthorityType("2");
        roleDataAdminDTO.setDataType("1");
        roleDataService.addRoleData(roleDataAdminDTO);
        return new SuccessApiResult();
    }

    /**删除组 项目关系*/
    @RequestMapping(value = "/delProjectOrganize",method = RequestMethod.GET)
    public ApiResult delProjectOrganize(@Valid RoleDataAdminDTO roleDataAdminDTO){
        roleDataAdminDTO.setAuthorityType("2");
        roleDataAdminDTO.setDataType("1");
        roleDataService.updateRoleData(roleDataAdminDTO);
        return new SuccessApiResult();
    }

    /**初始化机构信息*/
    @RequestMapping(value = "/allAgency",method = RequestMethod.POST)
    public void allAgency(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = agencyService.getAllAgencyList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
//        return agencyService.getAllAgencyList();
    }

    /**项目列表*/
    @RequestMapping(value = "/allProjectList",method = RequestMethod.GET)
    public ApiResult allProjectList(){
        List<ProjectReturnJsonDTO> projectReturnJsonDTOs = houseProjectService.getDTOList();
        return new SuccessApiResult(projectReturnJsonDTOs);
    }

    /**新增组织机构 项目关系*/
    @RequestMapping(value = "/addAgencyProject",method = RequestMethod.GET)
    public ApiResult addAgencyProject(@Valid RoleDataAdminDTO roleDataAdminDTO){
        roleDataAdminDTO.setDataType("1");
        return roleDataService.addRoleData(roleDataAdminDTO);
    }

    /**删除组织机构 项目关系*/
    @RequestMapping(value = "/delAgencyProject",method = RequestMethod.GET)
    public ApiResult delAgencyProject(@Valid RoleDataAdminDTO roleDataAdminDTO){
        roleDataAdminDTO.setDataType("1");
        roleDataService.delRoleOrganize(roleDataAdminDTO);
        return new SuccessApiResult();
    }

}
