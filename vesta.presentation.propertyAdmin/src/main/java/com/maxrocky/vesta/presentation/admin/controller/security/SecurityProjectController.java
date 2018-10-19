package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.project.DTO.SecurityAllRoleDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityAreaDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityGroupDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityProjectDTO;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 安全项目
 * Created by Jason on 2017/6/5.
 */
@Controller
@RequestMapping(value = "/securityProject")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class SecurityProjectController {
    @Autowired
    private SecurityProjectService securityProjectService;

    /**
     * 安全集团列表信息
     *
     * @param userPropertystaff
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/securityGroup.html", method = RequestMethod.GET)
    public String securityGroupList(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertystaff,
                                    Model model, WebPage webPage, @Valid SecurityGroupDTO securityGroupDTO) {
        List<SecurityGroupDTO> securityGroupDTOList = securityProjectService.getSecurityGroupList(securityGroupDTO, webPage, userPropertystaff);
        model.addAttribute("securityGroupDTOList", securityGroupDTOList);
        model.addAttribute("securityGroupDTO", securityGroupDTO);
        return "/security/project/securityGroupManage";
    }

    /**
     * @param groupName
     * @return 校验集团名称
     */
    @RequestMapping(value = "/chekGroupName")
    public ApiResult chekGroupName(@RequestParam(value = "groupName") String groupName) {
        if (securityProjectService.checkGroupName(groupName)) {
            return new SuccessApiResult("1");//集团名称不存在
        } else {
            return new SuccessApiResult("0");//集团名称存在
        }
    }

    /**
     * 新增安全集团信息
     *
     * @param userPropertystaff
     * @param model
     * @param securityGroupDTO
     * @return
     */
    @RequestMapping(value = "/addSecurityGroup.html", method = RequestMethod.GET)
    public String addSecurityGroup(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertystaff,
                                   Model model, @Valid SecurityGroupDTO securityGroupDTO) {
        securityProjectService.addSecurityGroup(securityGroupDTO, userPropertystaff);
        return "redirect:../securityProject/securityGroup.html";
    }

    /**
     * 跳转到安全集团设置页面
     *
     * @param groupId
     * @param model
     * @return
     */
    @RequestMapping(value = "/editGroupRole.html")
    public String editGroupRole(@RequestParam(value = "groupId") String groupId, Model model) {
        SecurityAllRoleDTO securityAllRoleDTO = securityProjectService.getSecurityGroupRoleListById(groupId);
        model.addAttribute("securityAllRoleDTO", securityAllRoleDTO);
        return "/security/project/editGroupRole";
    }

    /**
     * 保存集团权限
     *
     * @return
     */
    @RequestMapping(value = "/saveGroupRole.html")
    public String saveGroupRole(@Valid SecurityGroupDTO securityGroupDTO) {
        securityProjectService.updateGroupRole(securityGroupDTO);
        return "redirect:../securityProject/securityGroup.html";
    }

    /**
     * 安全区域列表信息
     *
     * @param userPropertystaff
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/securityArea.html", method = RequestMethod.GET)
    public String securityAreaList(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertystaff,
                                   Model model, WebPage webPage, @Valid SecurityAreaDTO securityAreaDTO) {
        List<SecurityAreaDTO> securityAreaDTOList = securityProjectService.getSecurityAreaList(securityAreaDTO, webPage, userPropertystaff);
        model.addAttribute("securityAreaDTOList", securityAreaDTOList);
        model.addAttribute("securityAreaDTO", securityAreaDTO);
        return "/security/project/securityAreaManage";
    }

    /**
     * @param areaName
     * @return 校验区域名称
     */
    @RequestMapping(value = "/chekAreaName")
    public ApiResult chekAreaName(@RequestParam(value = "areaName") String areaName) {
        if (securityProjectService.checkAreaName(areaName)) {
            return new SuccessApiResult("1");//区域名称不存在
        } else {
            return new SuccessApiResult("0");//区域名称存在
        }
    }

    /**
     * 新增安全区域信息
     *
     * @param userPropertystaff
     * @param model
     * @param securityAreaDTO
     * @return
     */
    @RequestMapping(value = "/addSecurityArea.html", method = RequestMethod.GET)
    public String addSecurityArea(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertystaff,
                                  Model model, @Valid SecurityAreaDTO securityAreaDTO) {
        securityProjectService.addSecurityArea(securityAreaDTO, userPropertystaff);
        model.addAttribute("groupId", securityAreaDTO.getGroupId());
        return "redirect:../securityProject/securityArea.html";
    }

    /**
     * 跳转到安全区域设置页面
     *
     * @param areaId
     * @param model
     * @return
     */
    @RequestMapping(value = "/editAreaRole.html")
    public String editAreaRole(@RequestParam(value = "groupId") String groupId, @RequestParam(value = "areaId") String areaId, Model model) {
        SecurityAllRoleDTO securityAllRoleDTO = securityProjectService.getSecurityAreaRoleListById(areaId);
        model.addAttribute("securityAllRoleDTO", securityAllRoleDTO);
        model.addAttribute("groupId", groupId);
        return "/security/project/editAreaRole";
    }

    /**
     * 保存区域权限
     *
     * @return
     */
    @RequestMapping(value = "/saveAreaRole.html")
    public String saveAreaRole(@Valid SecurityAreaDTO securityAreaDTO) {
        securityProjectService.updateAreaRole(securityAreaDTO);
        return "redirect:../securityProject/securityArea.html";
    }

    /**
     * 安全项目列表信息
     *
     * @param userPropertystaff
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/securityProject.html", method = RequestMethod.GET)
    public String securityProjectList(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertystaff,
                                      Model model, WebPage webPage, @Valid SecurityProjectDTO securityProjectDTO) {
        List<SecurityProjectDTO> securityProjectDTOList = securityProjectService.getSecurityProjectList(securityProjectDTO, webPage, userPropertystaff);
        model.addAttribute("securityProjectDTOList", securityProjectDTOList);
        model.addAttribute("securityProjectDTO", securityProjectDTO);
        return "/security/project/securityProjectManage";
    }

    /**
     * @param projectName
     * @return 校验项目名称
     */
    @RequestMapping(value = "/chekProjectName")
    public ApiResult chekProjectName(@RequestParam(value = "projectName") String projectName) {
        if (securityProjectService.checkProjectName(projectName)) {
            return new SuccessApiResult("1");//项目名称不存在
        } else {
            return new SuccessApiResult("0");//项目名称存在
        }
    }

    /**
     * 新增安全项目信息
     *
     * @param userPropertystaff
     * @param model
     * @param securityProjectDTO
     * @return
     */
    @RequestMapping(value = "/addSecurityProject.html", method = RequestMethod.GET)
    public String addSecurityProject(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertystaff,
                                     Model model, @Valid SecurityProjectDTO securityProjectDTO) {
        securityProjectService.addSecurityProject(securityProjectDTO, userPropertystaff);
        model.addAttribute("areaId", securityProjectDTO.getAreaId());
        return "redirect:../securityProject/securityProject.html";
    }

    /**
     * 跳转到安全项目设置页面
     *
     * @param areaId
     * @param model
     * @return
     */
    @RequestMapping(value = "/editProjectRole.html")
    public String editProjectRole(@RequestParam(value = "areaId") String areaId, @RequestParam(value = "projectId") String projectId, Model model) {
        SecurityAllRoleDTO securityAllRoleDTO = securityProjectService.getSecurityProjectRoleListById(projectId);
        model.addAttribute("securityAllRoleDTO", securityAllRoleDTO);
        model.addAttribute("areaId", areaId);
        return "/security/project/editProjectRole";
    }

    /**
     * 保存项目权限
     *
     * @return
     */
    @RequestMapping(value = "/saveProjectRole.html")
    public String saveProjectRole(@Valid SecurityProjectDTO securityProjectDTO) {
        securityProjectService.updateProjectRole(securityProjectDTO);
        return "redirect:../securityProject/securityProject.html";
    }

    /**
     * 删除安全项目
     *
     * @return
     */
    @RequestMapping(value = "/deleteSecurityProject.html")
    public String deleteSecurityProject(@Valid SecurityProjectDTO securityProjectDTO) {
        securityProjectService.deleteSecurityProject(securityProjectDTO);
        return "redirect:../securityProject/securityProject.html";
    }

    /**
     * 删除安全区域
     *
     * @return
     */
    @RequestMapping(value = "/deleteSecurityArea.html")
    public String deleteSecurityArea(@Valid SecurityAreaDTO securityAreaDTO) {
        securityProjectService.deleteSecurityArea(securityAreaDTO);
        return "redirect:../securityProject/securityArea.html";
    }

    /**
     * 删除安全集团
     *
     * @return
     */
    @RequestMapping(value = "/deleteSecurityGroup.html")
    public String deleteSecurityGroup(@Valid SecurityGroupDTO securityGroupDTO) {
        securityProjectService.deleteSecurityGroup(securityGroupDTO);
        return "redirect:../securityProject/securityGroup.html";
    }
}
