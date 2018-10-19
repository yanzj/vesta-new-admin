package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.RoleViewmodelService;
import com.maxrocky.vesta.application.inspectAcceptance.inf.InspectAcceptanceService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.ProjectKeyProcessesCountListDTO;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.ProjectKeyProcessesDTO;
import com.maxrocky.vesta.application.projectKeyProcesses.inf.ProjectKeyProcessService;
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
 * 关键工序
 * Created by Talent on 2016/11/24.
 */
@Controller
@RequestMapping(value = "/projectKeyProcesses")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class KeyProcessesController {
    @Autowired
    ProjectKeyProcessService projectKeyProcessService;
    @Autowired
    ProjectProjectService projectProjectService;
    @Autowired
    ProjectBuildingService projectBuildingService;
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
     * @param projectKeyProcessesDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/keyProcesses.html", method = RequestMethod.GET)
    public String searchKeyProcessesList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                         @Valid ProjectKeyProcessesDTO projectKeyProcessesDTO, Model model, WebPage webPage) {
//        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaff.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            projectKeyProcessesDTO.setProjects(projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userPropertystaff.getStaffId());
//            projectKeyProcessesDTO.setProjects(projectMap);
//        }
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(projectKeyProcessesDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",projectKeyProcessesDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(projectKeyProcessesDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",projectKeyProcessesDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(projectKeyProcessesDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",projectKeyProcessesDTO.getCityId());
            model.addAttribute("projects", projectList);
        }
        // 取得楼栋信息
        if (!StringUtil.isEmpty(projectKeyProcessesDTO.getProjectId())) {
            Map<String, String> builds = projectBuildingService.getBuildListByProjectId(projectKeyProcessesDTO.getProjectId());
            projectKeyProcessesDTO.setBuildings(builds);
        }

        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("2");
        projectKeyProcessesDTO.setFirstLevels(firstLevel);

        //获取二级分类
        if (!StringUtil.isEmpty(projectKeyProcessesDTO.getFirstSort())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(projectKeyProcessesDTO.getFirstSort(), "2");
            projectKeyProcessesDTO.setSecondLevels(secondLevel);

        }

        //获取三级分类
        if (!StringUtil.isEmpty(projectKeyProcessesDTO.getSecondSort())) {
            Map<String, String> thirdLevel = getAllClassifyService.getClassifyThree(projectKeyProcessesDTO.getSecondSort(), "2");
            projectKeyProcessesDTO.setThirdLevels(thirdLevel);

        }

        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020017 搜素 ESH40020018 导出Excel ESH40020019 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020017":
                        checkAuthFunctionDTO.setEsh40020017("Y");
                        break;
                    case "ESH40020018":
                        checkAuthFunctionDTO.setEsh40020018("Y");
                        break;
                    case "ESH40020019":
                        checkAuthFunctionDTO.setEsh40020019("Y");
                        break;

                }
            }
        }
        List<ProjectKeyProcessesDTO> projectKeyProcessesDTOList = projectKeyProcessService.searchKeyProcessesList(projectKeyProcessesDTO, webPage, userInformationEntity);
        model.addAttribute("keyProcessesList", projectKeyProcessesDTOList);
        model.addAttribute("keyProcesses", projectKeyProcessesDTO);
        model.addAttribute("typeMaps", projectKeyProcessesDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/keyProcesses/KeyProcessesList";
    }

    /**
     * 查看关键工序详细信息
     *
     * @param userInformationEntity
     * @param model
     * @param processesId
     * @return
     */
    @RequestMapping(value = "/searchKeyProcessesDetail")
    public String searchKeyProcessesDetail(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @RequestParam String processesId, @RequestParam String projectId) {
        ProjectKeyProcessesDTO projectKeyProcessesDTO = projectKeyProcessService.searchKeyProcessesDetailByBatchId(processesId);
//        boolean authority = inspectAcceptanceService.getAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020001 搜素 ESH40020002 导出Excel ESH40020003 导出Word ESH40020004 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020100":
                        checkAuthFunctionDTO.setEsh40020100("Y");
                        break;
                }
            }
        }
        model.addAttribute("keyProcesses", projectKeyProcessesDTO);
        model.addAttribute("checkAuthFunction", checkAuthFunctionDTO);
//        model.addAttribute("authority", authority);
        return "/construction/keyProcesses/KeyProcessesDetail";
    }

    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcels", method = RequestMethod.GET)
    public void exportExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request, HttpServletResponse response, @Valid ProjectKeyProcessesDTO projectKeyProcessesDTO) throws Exception {
        String fileName = "关键工序";
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
        String title = "检查验收EXCEL文档";
        String[] headers = {"序号","批次名称", "项目名称", "楼栋名称", "检查项", "严重等级", "甲方负责人", "第三方监理", "责任单位", "整改人", "登记时间", "状态"};

        ServletOutputStream out = response.getOutputStream();
        projectKeyProcessService.exportExcel(title, headers, out, projectKeyProcessesDTO,userInformationEntity);
    }


    /**
     * 关键工序统计列表
     *
     * @param userInformationEntity
     * @param keyProcessesDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/keyProcessesCount.html", method = RequestMethod.GET)
    public String inspectAcceptanceCount(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, @Valid ProjectKeyProcessesDTO keyProcessesDTO, Model model, WebPage webPage) {
        //获取当前人的菜单权限
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        if (f) {
            //获取项目数据
            Map<String, String> projectMap = projectProjectService.getProjectProjects();
            keyProcessesDTO.setProjects(projectMap);
        } else {
            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userInformationEntity.getStaffId());
            keyProcessesDTO.setProjects(projectMap);
        }

        // 取得标段信息
        if (!StringUtil.isEmpty(keyProcessesDTO.getProjectId())) {
            Map<String, String> tenders = getAllClassifyService.getTendersByProjectId(keyProcessesDTO.getProjectId());
            keyProcessesDTO.setTenders(tenders);
        }
        // 取得楼栋信息
        if (!StringUtil.isEmpty(keyProcessesDTO.getTenderId())) {
            Map<String, String> builds = getAllClassifyService.getBuildingByTenderId(keyProcessesDTO.getTenderId());
            keyProcessesDTO.setBuildings(builds);
        }
        // 取得楼栋信息
//        if (!StringUtil.isEmpty(keyProcessesDTO.getProjectId())) {
//            Map<String, String> builds = projectBuildingService.getBuildListByProjectId(keyProcessesDTO.getProjectId());
//            keyProcessesDTO.setBuildings(builds);
//        }
        ProjectKeyProcessesCountListDTO projectKeyProcessesCountListDTO = projectKeyProcessService.searchKeyProcessesCountList(keyProcessesDTO, webPage,userInformationEntity);
        model.addAttribute("keyProcessesCount", projectKeyProcessesCountListDTO.getList());
        model.addAttribute("keyProcesses", keyProcessesDTO);
        model.addAttribute("typeMaps", keyProcessesDTO);
        model.addAttribute("keyProcessesCountList", projectKeyProcessesCountListDTO);
        return "/construction/keyProcesses/KeyProcessesCount";
    }

    /**
     * 导出统计EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportCountExcels", method = RequestMethod.GET)
    public void exportCountExcels(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,HttpServletRequest request, HttpServletResponse response, @Valid ProjectKeyProcessesDTO keyProcessesDTO) throws Exception {
        String fileName = "关键工序统计";
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
        String title = "关键工序统计EXCEL文档";
        String[] headers = {"序号", "项目名称", "标段名称", "楼栋名称", "全部", "合格数", "不合格数","非正常关闭", "一次通过数"};
        ServletOutputStream out = response.getOutputStream();
        projectKeyProcessService.exportCountExcel(title, headers, out, keyProcessesDTO,userInformationEntity);
    }

    /**
     * 查询自己权限（是否具有关单权限）
     */
    @RequestMapping(value = "/searchAuthorityByStaffId", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult searchAuthorityByStaffId(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, @RequestParam String projectId) {
        return projectKeyProcessService.searchAuthorityByStaffId(projectId, userInformationEntity.getStaffId());
    }

    /**
     * 执行非正常关闭
     */
    @RequestMapping(value = "/executeAbnormalOffState")
    public String executeAbnormalOffState(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,@Valid ProjectKeyProcessesDTO keyProcessesDTO) {
        String result = projectKeyProcessService.executeAbnormalOffState(keyProcessesDTO,userInformationEntity);
        return "redirect:/projectKeyProcesses/keyProcesses.html";
    }

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
     * 根据项目获取标段
     *
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/getTendersByProjectId")
    public ApiResult getTendersByProjectId(@RequestParam(value = "projectId") String projectId) {
        Map map = getAllClassifyService.getTendersByProjectId(projectId);
        return new SuccessApiResult(map);
    }

    /**
     * 根据标段获取楼栋
     *
     * @param tenderId
     * @return
     */
    @RequestMapping(value = "/getBuildingByTenderId")
    public ApiResult getBuildingByTenderId(@RequestParam(value = "tenderId") String tenderId) {
        Map map = getAllClassifyService.getBuildingByTenderId(tenderId);
        return new SuccessApiResult(map);
    }
}
