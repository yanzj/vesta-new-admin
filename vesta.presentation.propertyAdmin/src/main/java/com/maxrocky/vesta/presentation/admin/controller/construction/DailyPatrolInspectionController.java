package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.DTO.admin.HouseRoomTypeDTO;
import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.baseData.inf.ProjectCityService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.*;
import com.maxrocky.vesta.application.dailyPatrolInspection.inf.DailyPatrolInspectionService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.SupplierService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日常巡检
 * Created by Magic on 2016/10/17.
 */
@Controller
@RequestMapping(value = "/DailyPatrolInspection")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class DailyPatrolInspectionController {
    @Autowired
    GetAllClassifyService getAllClassifyService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    OrganizeService organizeService;
    @Autowired
    DailyPatrolInspectionService dailyPatrolInspectionService;
    @Autowired
    ProjectProjectService projectProjectService;
    @Autowired
    ProjectBuildingService projectBuildingService;
    @Autowired
    ProjectCityService projectCityService;

    @Autowired
    private SecurityProjectService securityProjectService;

    @Autowired
    AuthAgencyService authAgencyService;

    @RequestMapping(value = "/dailyPatrolInspection.html")
    public String dailyPatrolInspection(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                        @Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //增加权限校验
//        Map<String, String> projectMap = securityProjectService.getESProjectByUser(userInformationEntity);
//        model.addAttribute("projects", projectMap);
//        新权限之前逻辑
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            model.addAttribute("projects", projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userInformationEntity.getStaffId());
//            model.addAttribute("projects", projectMap);
//        }
//        Map<String, String> projectMap = projectProjectService.getProjectProjects();
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",getDailyPatrolInspectionDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",getDailyPatrolInspectionDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",getDailyPatrolInspectionDTO.getCityId());
            model.addAttribute("projects", projectList);
        }


        // 取得楼栋信息
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getProjectId())) {
            Map<String, String> builds = projectBuildingService.getBuildListByProjectId(getDailyPatrolInspectionDTO.getProjectId());
            model.addAttribute("builds", builds);
        }
        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("1");
        model.addAttribute("firstLevels", firstLevel);
        //获取二级分类
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getClassifyOne())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(getDailyPatrolInspectionDTO.getClassifyOne(), "1");
            model.addAttribute("secondLevels", secondLevel);
        }
        //获取三级分类
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getClassifyTwo())) {
            Map<String, String> thirdLevel = getAllClassifyService.getClassifyThree(getDailyPatrolInspectionDTO.getClassifyTwo(), "1");
            model.addAttribute("thirdLevel", thirdLevel);
        }

        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020001 搜素 ESH40020002 导出Excel ESH40020003 导出Word ESH40020004 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020001":
                        checkAuthFunctionDTO.setEsh40020001("Y");
                        break;
                    case "ESH40020002":
                        checkAuthFunctionDTO.setEsh40020002("Y");
                        break;
                    case "ESH40020003":
                        checkAuthFunctionDTO.setEsh40020003("Y");
                        break;
                    case "ESH40020004":
                        checkAuthFunctionDTO.setEsh40020004("Y");
                        break;
                }
            }
        }

        List<DailyPatrolInspectionDTO> list = dailyPatrolInspectionService.getInspection(getDailyPatrolInspectionDTO, webPage, userInformationEntity.getStaffId());
        model.addAttribute("problems", list);
        model.addAttribute("problem", getDailyPatrolInspectionDTO);
        model.addAttribute("typeMaps", getDailyPatrolInspectionDTO);
        model.addAttribute("function",checkAuthFunctionDTO);

        return "/construction/dailyPatrolInspection/dailyPatrolInspectionlist";
    }
    /**
     * 根据用户和项目层级
     *
     * @param getDailyPatrolInspectionDTO
     * @return
     */
    @RequestMapping(value = "/getESAuthAgency")
    public ApiResult getESAuthAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                     @Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        if("100000002".equals(getDailyPatrolInspectionDTO.getType())){
            Map map = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,getDailyPatrolInspectionDTO.getType(),getDailyPatrolInspectionDTO.getCityId());
            return new SuccessApiResult(map);
        }
        if("100000003".equals(getDailyPatrolInspectionDTO.getType())){
            Map map = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,getDailyPatrolInspectionDTO.getType(),getDailyPatrolInspectionDTO.getRegionId());
            return new SuccessApiResult(map);
        }else {
            Map map = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,getDailyPatrolInspectionDTO.getType(),getDailyPatrolInspectionDTO.getGroupId());
            return new SuccessApiResult(map);
        }
    }


    /**
     * 根据一级分类id查询二级分类
     *
     * @param getDailyPatrolInspectionDTO
     * @return
     */
    @RequestMapping(value = "/getSecondLevel")
    public ApiResult getSecondLevel(@Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        Map map = getAllClassifyService.getClassifyTwo(getDailyPatrolInspectionDTO.getClassifyOne(), "1");
        return new SuccessApiResult(map);
    }


    /**
     * 根据二级分类id查询三级分类
     *
     * @param getDailyPatrolInspectionDTO
     * @return thirdLevel
     */
    @RequestMapping(value = "/getThirdLevel")
    public ApiResult getThirdLevel(@Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        Map map = getAllClassifyService.getClassifyThree(getDailyPatrolInspectionDTO.getClassifyTwo(), "1");
        return new SuccessApiResult(map);
    }

    /**
     * 日常巡检（楼栋）统计列表
     *
     * @param userInformationEntity
     * @param patrolInspectionCountDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/dailyPatrolInspectionCount.html", method = RequestMethod.GET)
    public String dailyPatrolInspectionCount(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, Model model, WebPage webPage) {
        //获取项目数据
//        Map<String, String> projectMap = projectProjectService.getProjectProjects();
//        patrolInspectionCountDTO.setProjects(projectMap);

        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        if (f) {
            //获取项目数据
            Map<String, String> projectMap = projectProjectService.getProjectProjects();
            patrolInspectionCountDTO.setProjects(projectMap);
        } else {
            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userInformationEntity.getStaffId());
            patrolInspectionCountDTO.setProjects(projectMap);
        }
        // 取得楼栋信息
        if (!StringUtil.isEmpty(patrolInspectionCountDTO.getProjectId())) {
            Map<String, String> builds = projectBuildingService.getBuildListByProjectId(patrolInspectionCountDTO.getProjectId());
            model.addAttribute("builds", builds);
        }
        ProjectDailyPatrolInspectionDTO projectDailyPatrolInspectionDTO = dailyPatrolInspectionService.searchdailyPatrolInspectionCountList(patrolInspectionCountDTO, webPage, userInformationEntity.getStaffId());

        model.addAttribute("projectDailyPatrolInspectionList", projectDailyPatrolInspectionDTO.getList());
        model.addAttribute("DailyPatrolInspection", patrolInspectionCountDTO);
        model.addAttribute("typeMaps", patrolInspectionCountDTO);
        model.addAttribute("projectDailyPatrolInspection", projectDailyPatrolInspectionDTO);
        model.addAttribute("tabIndex", "3");
        return "/construction/dailyPatrolInspection/DailyPatrolInspectionCount";
    }

    /**
     * 日常巡检（区域）统计列表
     *
     * @param userInformationEntity
     * @param patrolInspectionCountDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/dailyPatrolInspectionAreaCount.html", method = RequestMethod.GET)
    public String dailyPatrolInspectionAreaCount(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, Model model, WebPage webPage) {
        //经营单位下拉框数据
        Map map = projectCityService.getProjectOperationList();
        model.addAttribute("operationList", map);

        ProjectDailyPatrolInspectionDTO projectDailyPatrolInspectionDTO = dailyPatrolInspectionService.searchPoreatInspectionCountList(patrolInspectionCountDTO, webPage, userInformationEntity.getStaffId());

        model.addAttribute("projectDailyPatrolInspectionList", projectDailyPatrolInspectionDTO.getList());
        model.addAttribute("DailyPatrolInspection", patrolInspectionCountDTO);
        model.addAttribute("projectDailyPatrolInspection", projectDailyPatrolInspectionDTO);
        model.addAttribute("tabIndex", "1");
        return "/construction/dailyPatrolInspection/DailyPatrolInspectionCount";
    }

    /**
     * 日常巡检（项目）统计列表
     *
     * @param userInformationEntity
     * @param patrolInspectionCountDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/dailyPatrolInspectionProejctCount.html", method = RequestMethod.GET)
    public String dailyPatrolInspectionProejctCount(@ModelAttribute("authPropertystaff")  UserInformationEntity userInformationEntity, @Valid ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, Model model, WebPage webPage) {
        Map map = projectCityService.getProjectOperationList();  //经营单位下拉框数据
        model.addAttribute("operationList", map);

        if (!StringUtil.isEmpty(patrolInspectionCountDTO.getOperationId())) {
            Map<String, String> projectmap = projectCityService.getProejctListByOptId(patrolInspectionCountDTO.getOperationId());
            model.addAttribute("projectMap", projectmap);
        }

        ProjectDailyPatrolInspectionDTO projectDailyPatrolInspectionDTO = dailyPatrolInspectionService.searchProjectInspectionCountList(patrolInspectionCountDTO, webPage, userInformationEntity.getStaffId());

        model.addAttribute("projectDailyPatrolInspectionList", projectDailyPatrolInspectionDTO.getList());
        model.addAttribute("DailyPatrolInspection", patrolInspectionCountDTO);
        model.addAttribute("typeMaps", patrolInspectionCountDTO);
        model.addAttribute("projectDailyPatrolInspection", projectDailyPatrolInspectionDTO);
        model.addAttribute("tabIndex", "2");
        return "/construction/dailyPatrolInspection/DailyPatrolInspectionCount";
    }

    /**
     * 根据区域获取项目
     *
     * @param optId 区域ID
     * @return
     */
    @RequestMapping(value = "/getProjects")
    public ApiResult getProjects(@RequestParam(value = "optId") String optId) {
        Map map = projectCityService.getProejctListByOptId(optId);
        return new SuccessApiResult(map);
    }

    /**
     * 根据项目id获取楼栋数据
     *
     * @param getDailyPatrolInspectionDTO
     * @return thirdLevel
     */
    @RequestMapping(value = "/getBuilds")
    public ApiResult getBuilds(@Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        Map map = projectBuildingService.getBuildListByProjectId(getDailyPatrolInspectionDTO.getProjectId());
        return new SuccessApiResult(map);
    }


    /**
     * 查询详情预修改
     *
     * @param dailyPatrolInspectionAdminDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/getInspectionByinspectionId")
    public String getInspectionByinspectionId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO) {

        GetDailyPatrolInspectionAdminDTO inspectionAdminDTO = dailyPatrolInspectionService.getInspectionByinspectionId(dailyPatrolInspectionAdminDTO, userInformationEntity);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020001 搜素 ESH40020002 导出Excel ESH40020003 导出Word ESH40020004 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020097":
                        checkAuthFunctionDTO.setEsh40020097("Y");
                        break;
                }
            }
        }
        model.addAttribute("inspection", inspectionAdminDTO);
        model.addAttribute("checkAuthFunction", checkAuthFunctionDTO);
        return "/construction/dailyPatrolInspection/DailyPatrolInspectionDetail";

    }

    /**
     * 导出统计EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportCountExcels", method = RequestMethod.GET)
    public void exportCountExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response, @Valid ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "日常巡检统计";
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
        String title = "日常巡检统计EXCEL文档";
        String[] headers = null;
        if (patrolInspectionCountDTO.getTabIndex().equals("1")) {
            headers = new String[]{"序号", "集团名称", "区域名称", "全部", "已完成", "整改中", "非正常关闭"};
        }else if(patrolInspectionCountDTO.getTabIndex().equals("2")){
            headers = new String[]{"序号", "区域名称", "项目名称", "全部", "已完成", "整改中", "非正常关闭"};
        }else if(patrolInspectionCountDTO.getTabIndex().equals("3")){
            headers = new String[]{"序号", "项目名称", "楼栋名称", "全部", "已完成", "整改中", "非正常关闭"};
        }
        ServletOutputStream out = response.getOutputStream();
        dailyPatrolInspectionService.exportCountExcel(title, headers, out, patrolInspectionCountDTO, webPage, userInformationEntity.getStaffId());
    }

    /**
     * 监理+甲方 验收
     *
     * @param dailyPatrolInspectionAdminDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/checkBeforeAcceptance.html")
    public ModelAndView checkBeforeAcceptance(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO) {
        String ss = dailyPatrolInspectionService.checkBeforeAcceptance(dailyPatrolInspectionAdminDTO, userInformationEntity);
        return new ModelAndView("redirect:..//DailyPatrolInspection/dailyPatrolInspection.html");
    }

    /**
     * 非正常关闭
     *
     * @param dailyPatrolInspectionAdminDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/shutDown")
    public ModelAndView shutDown(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO) {
        String ss = dailyPatrolInspectionService.shutDownAdmin(dailyPatrolInspectionAdminDTO, userInformationEntity);
        return new ModelAndView("redirect:..//DailyPatrolInspection/dailyPatrolInspection.html");

    }


    /**
     * 导出查询EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                            @Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "日常巡检问题清单";
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
        String title = "日常巡检问题清单";
        String[] headers = {"序号", "项目名称", "位置",  "严重等级", "一级分类", "二级分类", "三级分类", "创建人","责任单位", "甲方负责人", "第三方监理", "乙方负责人", "登记时间", "描述", "位置", "状态" };
        ServletOutputStream out = response.getOutputStream();
        dailyPatrolInspectionService.exportExcel(title, headers, out, getDailyPatrolInspectionDTO, webPage, userInformationEntity.getStaffId());
    }

    /**
     * 草稿状态详情
     */
    @RequestMapping(value = "/getInspectionDraftById")
    public String getInspectionDraftById(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                         @Valid GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO) {
        GetDailyPatrolInspectionDraftDTO getDraftInspection = dailyPatrolInspectionService.getDraftInspection(dailyPatrolInspectionAdminDTO);
        model.addAttribute("inspection", getDraftInspection);
        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("1");
        model.addAttribute("firstLevels", firstLevel);
        //获取二级分类
        if (!StringUtil.isEmpty(getDraftInspection.getClassifyOne())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(getDraftInspection.getClassifyOne(), "1");
            model.addAttribute("secondLevels", secondLevel);
        }
        //获取三级分类
        if (!StringUtil.isEmpty(getDraftInspection.getClassifyTwo())) {
            Map<String, String> thirdLevel = getAllClassifyService.getClassifyThree(getDraftInspection.getClassifyTwo(), "1");
            model.addAttribute("thirdLevel", thirdLevel);
        }
        // 根据项目id 获取责任单位 + 第三方监理 两个下拉框
        if (!StringUtil.isEmpty(dailyPatrolInspectionAdminDTO.getProjectId())) {
            Map<String, String> supplierList = getAllClassifyService.getDutyList(dailyPatrolInspectionAdminDTO.getProjectId());
            model.addAttribute("supplierList", supplierList);

            Map<String, String> supervisorList = getAllClassifyService.getSurveyorUserList(dailyPatrolInspectionAdminDTO.getProjectId());
            model.addAttribute("supervisorList", supervisorList);
        }
        //根据责任单位获取整改人列表
        if (!StringUtil.isEmpty(getDraftInspection.getSupplierId())) {
            Map<String, String> assignList = getAllClassifyService.getDutyPeople(getDraftInspection.getSupplierId());
            model.addAttribute("assignList", assignList);
        }
        return "/construction/dailyPatrolInspection/DailyPatrolInspectionDraft";
    }

    /**
     * 根据责任单位获取整改人下拉
     *
     * @param getDailyPatrolInspectionDTO
     * @return thirdLevel
     */
    @RequestMapping(value = "/getDutyPeople")
    public ApiResult getDutyPeople(@Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        Map map = getAllClassifyService.getDutyPeople(getDailyPatrolInspectionDTO.getSupplierId());
        return new SuccessApiResult(map);
    }

    /**
     * 草稿提交
     *
     * @param getDailyPatrolInspectionDraftDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/updateInspectionDraft")
    public ModelAndView updateInspectionDraft(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                              @Valid GetDailyPatrolInspectionDraftDTO getDailyPatrolInspectionDraftDTO) {
        String ss = dailyPatrolInspectionService.updateInspectionDraft(getDailyPatrolInspectionDraftDTO, userInformationEntity);
        return new ModelAndView("redirect:..//DailyPatrolInspection/dailyPatrolInspection.html");
    }

    /**
     * 导出Word文档
     *
     * @param userInformationEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportWord")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO) throws Exception {
        List<GetDailyPatrolInspectionDraftDTO> dailyInspectionDTOList = null;
        if (!StringUtil.isEmpty(dailyPatrolInspectionAdminDTO.getInspectionId())) {
            dailyInspectionDTOList = dailyPatrolInspectionService.getDraftInspectionList(dailyPatrolInspectionAdminDTO);
        }
        dailyPatrolInspectionService.exportWord(request, response, dailyInspectionDTOList);
    }
}
