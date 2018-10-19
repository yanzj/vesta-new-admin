package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.ProjectSyncForApp.DTO.UserSyncProjectDTO;
import com.maxrocky.vesta.application.ProjectSyncForApp.inf.ProjectSyncForAppService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserAndRoleRelationDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.projectAccredit.inf.ProjectUserAccreditService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2018/4/24.
 * app项目同步
 */
@Controller
@RequestMapping(value = "/projectSync")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectSyncForAppController {

    @Autowired
    private AuthAgencyService authAgencyService;
    @Autowired
    private ProjectSyncForAppService projectSyncForAppService;
    @Autowired
    private ProjectUserAccreditService projectUserAccreditService;

    //根据权限初始化APP授权项目角色列表
    @RequestMapping(value = "/initProjectSyncForApp",method = RequestMethod.GET)
    public String initProjectSyncForApp(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                        @RequestParam(value = "agencyIdPro" ,required = false ) String agencyIdPro , Model model , WebPage webPage){

        List<String> updateProject = projectUserAccreditService.getProjectAuthFunctionAndProjectIdByStaffId("ESH40020102",userInformationEntity.getStaffId(),"4");
        List<UserSyncProjectDTO> userSyncProjectDTOS = new ArrayList<>();
        webPage.setPageSize(20);
        if(null != agencyIdPro && !StringUtil.isEmpty(agencyIdPro) && null != updateProject && updateProject.size()>0){
            List<UserSyncProjectDTO> userSyncProjectSize = projectSyncForAppService.getAccreditManageListByAgencyId(agencyIdPro,null,userInformationEntity, updateProject);
            userSyncProjectDTOS = projectSyncForAppService.getAccreditManageListByAgencyId(agencyIdPro,webPage,userInformationEntity, updateProject);
            webPage.setRecordCount(userSyncProjectSize.size());
        }else {
            webPage.setRecordCount(0);
            userSyncProjectDTOS = projectSyncForAppService.getAccreditManageByStaffId(userInformationEntity);
            if(null != userSyncProjectDTOS && userSyncProjectDTOS.size()>0){
                webPage.setRecordCount(1);
            }
        }
        model.addAttribute("userSyncProjectDTOS",userSyncProjectDTOS);
        model.addAttribute("agencyIdPro","");
        if(null != agencyIdPro && !StringUtil.isEmpty(agencyIdPro)){
            model.addAttribute("agencyIdPro",agencyIdPro);
        }
        return "/construction/projectUserAccreditManage/ProjectSyncForApp";
    }


    //获取工程的所有项目层级
    @RequestMapping(value = "/getProjectAllAgency",method = RequestMethod.GET)
    public void fullAgencyES(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getESAgencyFullList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    //根据人员id查询角色
    @RequestMapping(value = "/getRoleByUserId",method = RequestMethod.GET)
    public ApiResult getRoleByUserId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                     @RequestParam(value = "peoId" ,required = true )String peoId ){
        Map map = projectSyncForAppService.getRoleByUserId(peoId);
        return new SuccessApiResult(map);
    }

    //查询app端是否设置项目
    @RequestMapping(value = "/getPorjectByInit",method = RequestMethod.GET)
    public void getPorjectByInit(HttpServletResponse response,
                                  @RequestParam(value = "userId" ,required = true )String userId) throws IOException {
        String roleId = projectSyncForAppService.getAppRoleByUserId(userId);
        if(null != roleId && !StringUtil.isEmpty(roleId)){
            this.getPorjectByCheck(response, roleId, userId);
        }
    }

    //根据角色Id获取项目
    @RequestMapping(value = "/getPorjectByCheck",method = RequestMethod.GET)
    public void getPorjectByCheck(HttpServletResponse response, String roleId, String userId) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        if(null != userId){
            List<AgencyTreeDTO> agencyTreeDTOList = projectSyncForAppService.getPorjectByCheck(roleId, userId);
            response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
        }
    }

    //保存三者关系
    @RequestMapping(value = "saveUserRoleProject")
    public String saveUserRoleProject(@Valid UserAndRoleRelationDTO userAndRoleRelationDTO){
        projectSyncForAppService.saveUserRoleProject(userAndRoleRelationDTO);
        return "redirect:../projectSync/initProjectSyncForApp";
    }

}
