package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.baseData.adminDTO.ProjectProjectDTO;
import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.InspectAcceptanceDTO;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ProjectAcceptanceStatisticsDTO;
import com.maxrocky.vesta.application.inspectAcceptance.inf.InspectAcceptanceService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
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
 * 检查验收
 * <p>
 * Created by jiazefeng on 2016/10/17.
 */
@Controller
@RequestMapping(value = "/inspectAcceptance")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class InspectAcceptanceController {
    @Autowired
    private GetAllClassifyService getAllClassifyService;
    @Autowired
    private InspectAcceptanceService inspectAcceptanceService;
    @Autowired
    ProjectProjectService projectProjectService;
    @Autowired
    ProjectBuildingService projectBuildingService;

    @Autowired
    private SecurityProjectService securityProjectService;

    @Autowired
    AuthAgencyService authAgencyService;

    /**
     * 根据一级分类id查询二级分类
     *
     * @param classifyOne
     * @return
     */
    @RequestMapping(value = "/getSecondLevel")
    public ApiResult getSecondLevel(@RequestParam(value = "classifyOne") String classifyOne) {
        Map map = getAllClassifyService.getClassifyTwo(classifyOne, "2");
        return new SuccessApiResult(map);
    }


    /**
     * 根据二级分类id查询三级分类
     *
     * @param classifyTwo
     * @return thirdLevel
     */
    @RequestMapping(value = "/getThirdLevel")
    public ApiResult getThirdLevel(@RequestParam(value = "classifyTwo") String classifyTwo) {
        Map map = getAllClassifyService.getClassifyThree(classifyTwo, "2");
        return new SuccessApiResult(map);
    }

    /**
     * 检查验收列表
     *
     * @param userInformationEntity
     * @param inspectAcceptanceDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/inspectAcceptance.html", method = RequestMethod.GET)
    public String searchInspectAcceptanceList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                              @Valid InspectAcceptanceDTO inspectAcceptanceDTO, Model model, WebPage webPage) {
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();

        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            inspectAcceptanceDTO.setProjects(projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userInformationEntity.getStaffId());
//            inspectAcceptanceDTO.setProjects(projectMap);
//        }
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(inspectAcceptanceDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",inspectAcceptanceDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(inspectAcceptanceDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",inspectAcceptanceDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(inspectAcceptanceDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",inspectAcceptanceDTO.getCityId());
            model.addAttribute("projects", projectList);
        }

        // 取得楼栋信息
        if (!StringUtil.isEmpty(inspectAcceptanceDTO.getProjectId())) {
            Map<String, String> builds = projectBuildingService.getBuildListByProjectId(inspectAcceptanceDTO.getProjectId());
            inspectAcceptanceDTO.setBuildings(builds);
        }

        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("2");
        inspectAcceptanceDTO.setFirstLevels(firstLevel);

        //获取二级分类
        if (!StringUtil.isEmpty(inspectAcceptanceDTO.getFirstClassification())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(inspectAcceptanceDTO.getFirstClassification(), "2");
            inspectAcceptanceDTO.setSecondLevels(secondLevel);

        }

        //获取三级分类
        if (!StringUtil.isEmpty(inspectAcceptanceDTO.getSecondaryClassification())) {
            Map<String, String> thirdLevel = getAllClassifyService.getClassifyThree(inspectAcceptanceDTO.getSecondaryClassification(), "2");
            inspectAcceptanceDTO.setThirdLevels(thirdLevel);

        }

        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020007 搜素 ESH40020008 导出Excel ESH40020009 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020007":
                        checkAuthFunctionDTO.setEsh40020007("Y");
                        break;
                    case "ESH40020008":
                        checkAuthFunctionDTO.setEsh40020008("Y");
                        break;
                    case "ESH40020009":
                        checkAuthFunctionDTO.setEsh40020009("Y");
                        break;

                }
            }
        }

        List<InspectAcceptanceDTO> inspectAcceptanceDTOList = inspectAcceptanceService.searchInspectAcceptanceList(inspectAcceptanceDTO, webPage, userInformationEntity.getStaffId());
        model.addAttribute("inspectAcceptanceList", inspectAcceptanceDTOList);
        model.addAttribute("inspectAcceptance", inspectAcceptanceDTO);
        model.addAttribute("typeMaps", inspectAcceptanceDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/inspectAcceptance/InspectAcceptance";
    }

    /**
     * 查看详细
     */
    @RequestMapping(value = "/searchAcceptanceDetail")
    public String searchAcceptanceDetail(@ModelAttribute("authPropertystaff")  UserInformationEntity userInformationEntity,
                                         Model model, @RequestParam String batchId, @RequestParam String projectId, WebPage webPage) {
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020001 搜素 ESH40020002 导出Excel ESH40020003 导出Word ESH40020004 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020098":
                        checkAuthFunctionDTO.setEsh40020098("Y");
                        break;
                }
            }
        }
        InspectAcceptanceDTO inspectAcceptanceDTO = inspectAcceptanceService.searchAcceptanceBatchInfoByBatchId(batchId);
//        boolean authority = inspectAcceptanceService.getAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
        model.addAttribute("acceptance", inspectAcceptanceDTO);
//        model.addAttribute("authority", authority);
        model.addAttribute("checkAuthFunction", checkAuthFunctionDTO);
        return "/construction/inspectAcceptance/InspectAcceptanceDetail";
    }

    /**
     * 查询自己的权限（是否有关单权限）
     */
    @RequestMapping(value = "/searchAuthorityByStaffId", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult searchAuthorityByStaffId(@ModelAttribute("authPropertystaff")  UserInformationEntity userInformationEntity, @RequestParam String projectId) {
        return inspectAcceptanceService.searchAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
    }

    /**
     * 执行非正常关闭
     */
    @RequestMapping(value = "/executeAbnormalOffState")
    public String executeAbnormalOffState(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid InspectAcceptanceDTO inspectAcceptanceDTO) {
        String result = inspectAcceptanceService.executeAbnormalOffState(inspectAcceptanceDTO, userInformationEntity);
        return "redirect:/inspectAcceptance/inspectAcceptance.html";
    }

    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcels", method = RequestMethod.GET)
    public void exportExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response, @Valid InspectAcceptanceDTO inspectAcceptanceDTO) throws Exception {
        String fileName = "检查验收";
//        String fileName = "acceptance-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
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
//        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");

        String title = "检查验收EXCEL文档";
        String[] headers = {"序号", "批次名称", "项目名称", "楼栋名称", "检查项", "严重等级", "第三方监理", "责任单位", "整改人", "登记时间", "状态"};

        ServletOutputStream out = response.getOutputStream();
        inspectAcceptanceService.exportExcel(title, headers, out, inspectAcceptanceDTO, userInformationEntity);
        //Object obj = null;
        // return new SuccessApiResult(obj);
    }

    /**
     * 检查验收统计列表
     *
     * @param userInformationEntity
     * @param inspectAcceptanceDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/inspectAcceptanceCount.html", method = RequestMethod.GET)
    public String inspectAcceptanceCount(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid InspectAcceptanceDTO inspectAcceptanceDTO, Model model, WebPage webPage) {
        //获取当前人的菜单权限
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        if (f) {
            //获取项目数据
            Map<String, String> projectMap = projectProjectService.getProjectProjects();
            inspectAcceptanceDTO.setProjects(projectMap);
        } else {
            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userInformationEntity.getStaffId());
            inspectAcceptanceDTO.setProjects(projectMap);
        }

        // 取得标段信息
        if (!StringUtil.isEmpty(inspectAcceptanceDTO.getProjectId())) {
            Map<String, String> tenders = getAllClassifyService.getTendersByProjectId(inspectAcceptanceDTO.getProjectId());
            inspectAcceptanceDTO.setTenders(tenders);
        }
        // 取得楼栋信息
        if (!StringUtil.isEmpty(inspectAcceptanceDTO.getTenderId())) {
            Map<String, String> builds = getAllClassifyService.getBuildingByTenderId(inspectAcceptanceDTO.getTenderId());
            inspectAcceptanceDTO.setBuildings(builds);
        }

        ProjectAcceptanceStatisticsDTO projectAcceptanceStatisticsDTO = inspectAcceptanceService.searchInspectAcceptanceCountList(inspectAcceptanceDTO, webPage, userInformationEntity.getStaffId());
        model.addAttribute("inspectAcceptanceCountList", projectAcceptanceStatisticsDTO.getList());
        model.addAttribute("inspectAcceptance", inspectAcceptanceDTO);
        model.addAttribute("typeMaps", inspectAcceptanceDTO);
        model.addAttribute("projectAcceptanceStatistics", projectAcceptanceStatisticsDTO);
        return "/construction/inspectAcceptance/InspectAcceptanceCount";
    }

    /**
     * 导出统计EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportCountExcels", method = RequestMethod.GET)
    public void exportCountExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response, @Valid InspectAcceptanceDTO inspectAcceptanceDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "检查验收统计";
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
        String title = "检查验收统计EXCEL文档";
        String[] headers = {"序号", "项目名称", "标段名称", "楼栋名称", "全部", "合格数", "不合格数", "非正常关闭数", "一次通过数"};
        ServletOutputStream out = response.getOutputStream();
        inspectAcceptanceService.exportCountExcel(title, headers, out, inspectAcceptanceDTO, webPage, userInformationEntity);
    }
}
