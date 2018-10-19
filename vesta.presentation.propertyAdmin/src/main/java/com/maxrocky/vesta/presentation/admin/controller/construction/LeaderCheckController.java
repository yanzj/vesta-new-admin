package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inspectAcceptance.inf.InspectAcceptanceService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.projectKeyProcesses.inf.ProjectKeyProcessService;
import com.maxrocky.vesta.application.projectLeadersCheck.DTO.ProjectLeadersCheckDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.inf.ProjectLeadersCheckService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 领导检查
 * Created by Jason on 2017/03/28.
 */
@Controller
@RequestMapping(value = "/projectLeaderCheck")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class LeaderCheckController {
    @Autowired
    ProjectLeadersCheckService projectLeadersCheckService;
    @Autowired
    ProjectKeyProcessService projectKeyProcessService;
    @Autowired
    ProjectProjectService projectProjectService;
    @Autowired
    GetAllClassifyService getAllClassifyService;
    @Autowired
    InspectAcceptanceService inspectAcceptanceService;

    @Autowired
    private SecurityProjectService securityProjectService;
    @Autowired
    AuthAgencyService authAgencyService;
    /**
     * 关键工序列表信息
     *
     * @param userInformationEntity
     * @param projectLeadersCheckDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/leaderCheck.html", method = RequestMethod.GET)
    public String searchKeyProcessesList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                         @Valid ProjectLeadersCheckDTO projectLeadersCheckDTO, Model model, WebPage webPage) {
        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaff.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            projectLeadersCheckDTO.setProjects(projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userPropertystaff.getStaffId());
//            projectLeadersCheckDTO.setProjects(projectMap);
//        }

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(projectLeadersCheckDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",projectLeadersCheckDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(projectLeadersCheckDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",projectLeadersCheckDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }

        if(!StringUtil.isEmpty(projectLeadersCheckDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",projectLeadersCheckDTO.getCityId());
            model.addAttribute("projects", projectList);
        }

        List<ProjectLeadersCheckDTO> projectLeadersCheckDTOList = projectLeadersCheckService.getLeaderCheckList(projectLeadersCheckDTO, webPage, userInformationEntity);


        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020059 搜素 ESH40020061 导出Excel ESH40020060 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020059":
                        checkAuthFunctionDTO.setEsh40020059("Y");
                        break;
                    case "ESH40020061":
                        checkAuthFunctionDTO.setEsh40020061("Y");
                        break;
                    case "ESH40020060":
                        checkAuthFunctionDTO.setEsh40020060("Y");
                        break;

                }
            }
        }
        model.addAttribute("leaderCheckList", projectLeadersCheckDTOList);
        model.addAttribute("leaderCheck", projectLeadersCheckDTO);
        model.addAttribute("typeMaps", projectLeadersCheckDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/leaderCheck/LeaderCheckList";
    }

    /**
     * 查看关键工序详细信息
     *
     * @param userInformationEntity
     * @param model
     * @param checkId
     * @return
     */
    @RequestMapping(value = "/getLeaderCheckDetail")
    public String getLeaderCheckDetail(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, Model model, @RequestParam String checkId, @RequestParam String projectId) {
        ProjectLeadersCheckDTO projectLeadersCheckDTO = projectLeadersCheckService.getLeaderCheckDetailByCheckId(checkId);
        boolean authority = inspectAcceptanceService.getAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
        model.addAttribute("leaderCheck", projectLeadersCheckDTO);
        model.addAttribute("authority", authority);
        return "/construction/leaderCheck/ProjectLeaderCheckDetail";
    }

    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcels", method = RequestMethod.GET)
    public void exportExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response, @Valid ProjectLeadersCheckDTO projectLeadersCheckDTO) throws Exception {
        String fileName = "领导检查";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "领导检查EXCEL文档";
        String[] headers = {"序号","项目名称", "检查人","开始时间", "整改人", "状态"};

        ServletOutputStream out = response.getOutputStream();
        projectLeadersCheckService.exportExcel(title, headers, out, projectLeadersCheckDTO, userInformationEntity);
    }
    /**
     * 查询自己权限（是否具有关单权限）
     */
    @RequestMapping(value = "/searchAuthorityByStaffId", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult searchAuthorityByStaffId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @RequestParam String projectId) {
        return projectKeyProcessService.searchAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
    }

    /**
     * 执行非正常关闭
     */
    @RequestMapping(value = "/executeAbnormalOffState")
    public String executeAbnormalOffState(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectLeadersCheckDTO projectLeadersCheckDTO) {
        String result = projectLeadersCheckService.executeAbnormalOffState(projectLeadersCheckDTO, userInformationEntity);
        return "redirect:/projectLeaderCheck/leaderCheck.html";
    }

}
