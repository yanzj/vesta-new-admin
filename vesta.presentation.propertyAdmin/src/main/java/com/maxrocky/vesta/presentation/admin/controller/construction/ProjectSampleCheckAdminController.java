package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.ProjectMaterial.DTO.GetProjectMaterialDTO;
import com.maxrocky.vesta.application.ProjectMaterial.DTO.MaterialAdminDTO;
import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.GetDailyPatrolInspectionDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inspectAcceptance.inf.InspectAcceptanceService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.projectSampleCheck.DTO.*;
import com.maxrocky.vesta.application.projectSampleCheck.inf.ProjectSampleCheckService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
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
 * Created by Magic on 2017/1/11.
 * 样板点评修改
 */
@Controller
@RequestMapping(value = "/SampleCheckAdmin")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectSampleCheckAdminController {
    @Autowired
    GetAllClassifyService getAllClassifyService;

    @Autowired
    ProjectProjectService projectProjectService;

    @Autowired
    ProjectSampleCheckService projectSampleCheckService;
    @Autowired
    InspectAcceptanceService inspectAcceptanceService;

    @Autowired
    private SecurityProjectService securityProjectService;

    @Autowired
    AuthAgencyService authAgencyService;

    @RequestMapping(value = "/projectSampleCheck.html")
    public String projectSampleCheck(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                     @Valid RequestSampleAdminDTO requestSampleAdminDTO) {
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaffEntity.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            model.addAttribute("projects", projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userPropertystaffEntity.getStaffId());
//            model.addAttribute("projects", projectMap);
//        }
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(requestSampleAdminDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",requestSampleAdminDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(requestSampleAdminDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",requestSampleAdminDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(requestSampleAdminDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",requestSampleAdminDTO.getCityId());
            model.addAttribute("projects", projectList);
        }

        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("3");
        model.addAttribute("firstLevels", firstLevel);
        //获取二级分类
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getClassifyOne())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(requestSampleAdminDTO.getClassifyOne(), "3");
            model.addAttribute("secondLevels", secondLevel);
        }
        List<ProjectSampleCheckAdminListDTO> list = projectSampleCheckService.getSampleCheckAdmin(requestSampleAdminDTO, webPage, userInformationEntity.getStaffId());

        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020012 搜素 ESH40020013 导出Excel ESH40020014 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020012":
                        checkAuthFunctionDTO.setEsh40020012("Y");
                        break;
                    case "ESH40020013":
                        checkAuthFunctionDTO.setEsh40020013("Y");
                        break;
                    case "ESH40020014":
                        checkAuthFunctionDTO.setEsh40020014("Y");
                        break;

                }
            }
        }

        model.addAttribute("problems", list);
        model.addAttribute("problem", requestSampleAdminDTO);
        model.addAttribute("typeMaps", requestSampleAdminDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/sampleCheck/ProjectSampleCheckList";
    }

    /**
     * 根据一级分类id查询二级分类
     *
     * @param getDailyPatrolInspectionDTO
     * @return
     */
    @RequestMapping(value = "/getSecondLevel")
    public ApiResult getSecondLevel(@Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        Map map = getAllClassifyService.getClassifyTwo(getDailyPatrolInspectionDTO.getClassifyOne(), "3");
        return new SuccessApiResult(map);
    }

    /**
     * 查询详情预修改
     *
     * @param requestSampleAdminDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/getSampleCheckById")
    public String getMaterialBymaterialId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                          @Valid RequestSampleAdminDTO requestSampleAdminDTO) {
        MaterialAdminDTO materialAdminDTO = null;
        model.addAttribute("material", materialAdminDTO);
        return "/construction/projectMaterial/projectMaterialDetail";

    }

    /**
     * 导出查询EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                            @Valid RequestSampleAdminDTO requestSampleAdminDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "样板点评清单";
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
        String title = "样板点评清单";
        String[] headers = {"序号", "项目名称", "标题", "内容", "检查项", "严重等级", "责任单位名称", "整改负责人名字", "甲方负责人", "第三方监理", "创建时间", "创建人", "状态"};
        ServletOutputStream out = response.getOutputStream();
        projectSampleCheckService.exportExcel(title, headers, out, requestSampleAdminDTO, webPage, userInformationEntity.getStaffId());
    }

    /**
     * 样板点评统计列表
     */
    @RequestMapping(value = "/projectSampleCheckCount.html")
    public String sampleCheckCount(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                   @Valid RequestSampleAdminDTO requestSampleAdminDTO) {
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        if (f) {
            //获取项目数据
            Map<String, String> projectMap = projectProjectService.getProjectProjects();
            model.addAttribute("projects", projectMap);
        } else {
            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userInformationEntity.getStaffId());
            model.addAttribute("projects", projectMap);
        }
        ProjectSampleCheckCountListDTO list = projectSampleCheckService.getSampleCheckCount(requestSampleAdminDTO, webPage, userInformationEntity.getStaffId());
        model.addAttribute("problems", list);
        return "/construction/sampleCheck/ProjectSampleCheckCount";
    }

    /**
     * 查看样板点评详细信息
     *
     * @param userInformationEntity
     * @param model
     * @param sampleCheckId
     * @return
     */
    @RequestMapping(value = "/searchSampleCheckDetail")
    public String searchSampleCheckDetail(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @RequestParam String sampleCheckId, @RequestParam String projectId) {
        ProjectSampleCheckAdminBackDTO projectSampleCheckDTO = projectSampleCheckService.searchSampleCheckDetailBySampleCheckId(sampleCheckId);
//        boolean authority = inspectAcceptanceService.getAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020001 搜素 ESH40020002 导出Excel ESH40020003 导出Word ESH40020004 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020101":
                        checkAuthFunctionDTO.setEsh40020101("Y");
                        break;
                }
            }
        }
        model.addAttribute("sampleCheck", projectSampleCheckDTO);
//        model.addAttribute("authority", authority);
        model.addAttribute("checkAuthFunction", checkAuthFunctionDTO);
        return "/construction/sampleCheck/ProjectSampleCheckDetail";
    }

    /**
     * 执行非正常关闭
     */
    @RequestMapping(value = "/executeAbnormalOffState")
    public String executeAbnormalOffState(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectSampleCheckAdminBackDTO backDTO) {
        String result = projectSampleCheckService.executeAbnormalOffState(backDTO, userInformationEntity);
        return "redirect:/SampleCheckAdmin/projectSampleCheck.html";
    }

    /**
     * 查看样板点评详细信息
     *
     * @param userInformationEntity
     * @param model
     * @param sampleCheckId
     * @return
     */
    @RequestMapping(value = "/exportPPT")
    public void exportPPT(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @RequestParam String sampleCheckId) throws Exception {
        ProjectSampleCheckAdminBackDTO projectSampleCheckDTO = projectSampleCheckService.searchSampleCheckDetailBySampleCheckId(sampleCheckId);
        String fileName = "样板点评详情";
        response.reset();
        response.setContentType("application/x-ppt");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".ppt");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".ppt");
        }
        ServletOutputStream out = response.getOutputStream();
        projectSampleCheckService.exportPPT(request,out, projectSampleCheckDTO);
    }
    /**
     * 导出统计EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportCountExcels", method = RequestMethod.GET)
    public void exportCountExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request, HttpServletResponse response, WebPage webPage,
                                  @Valid RequestSampleAdminDTO requestSampleAdminDTO) throws Exception {
        String fileName = "样板点评统计";
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
        String title = "样板点评统计EXCEL文档";
        String[] headers = {"序号", "项目名称", "检查项", "合格数", "不合格数"};
        ServletOutputStream out = response.getOutputStream();
        projectSampleCheckService.exportCountExcel(title, headers, out,requestSampleAdminDTO,userInformationEntity,webPage);
    }
}
