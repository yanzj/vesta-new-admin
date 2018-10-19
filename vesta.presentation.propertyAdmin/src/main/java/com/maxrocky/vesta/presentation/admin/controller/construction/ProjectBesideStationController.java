package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.SupplierService;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.InspectAcceptanceDTO;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ProjectAcceptanceStatisticsDTO;
import com.maxrocky.vesta.application.inspectAcceptance.inf.InspectAcceptanceService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.projectSideStation.DTO.ProjectSideStationDTO;
import com.maxrocky.vesta.application.projectSideStation.DTO.ProjectSideStationInfoDTO;
import com.maxrocky.vesta.application.projectSideStation.inf.ProjectSideStationService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 旁站
 * <p>
 * Created by jiazefeng on 2016/11/09.
 */
@Controller
@RequestMapping(value = "/besideStation")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectBesideStationController {
    @Autowired
    private ProjectSideStationService projectSideStationService;
    @Autowired
    ProjectProjectService projectProjectService;
    @Autowired
    private GetAllClassifyService getAllClassifyService;

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
        Map map = getAllClassifyService.getClassifyTwo(classifyOne, "5");
        return new SuccessApiResult(map);
    }

    /**
     * 旁站列表
     *
     * @param userInformationEntity
     * @param projectSideStationInfoDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/besideStation.html", method = RequestMethod.GET)
    public String searchBesideStationList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                          @Valid ProjectSideStationInfoDTO projectSideStationInfoDTO, Model model, WebPage webPage) {
        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaff.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            projectSideStationInfoDTO.setProjects(projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userPropertystaff.getStaffId());
//            projectSideStationInfoDTO.setProjects(projectMap);
//        }

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(projectSideStationInfoDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",projectSideStationInfoDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(projectSideStationInfoDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",projectSideStationInfoDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(projectSideStationInfoDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",projectSideStationInfoDTO.getCityId());
            model.addAttribute("projects", projectList);
        }

        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("5");
        projectSideStationInfoDTO.setFirstLevels(firstLevel);

        //获取二级分类
        if (!StringUtil.isEmpty(projectSideStationInfoDTO.getFirstCategory())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(projectSideStationInfoDTO.getFirstCategory(), "5");
            projectSideStationInfoDTO.setSecondLevels(secondLevel);
        }

        List<ProjectSideStationInfoDTO> projectSideStationDTOList = projectSideStationService.searchBesideStationList(projectSideStationInfoDTO, webPage,userInformationEntity.getStaffId());
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020025 搜素 ESH40020026 导出Excel ESH40020027 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020025":
                        checkAuthFunctionDTO.setEsh40020025("Y");
                        break;
                    case "ESH40020026":
                        checkAuthFunctionDTO.setEsh40020026("Y");
                        break;
                    case "ESH40020027":
                        checkAuthFunctionDTO.setEsh40020027("Y");
                        break;

                }
            }
        }

        model.addAttribute("sideStationList", projectSideStationDTOList);
        model.addAttribute("sideStation", projectSideStationInfoDTO);
        model.addAttribute("typeMaps", projectSideStationInfoDTO);
        //导出excel用  记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",projectSideStationDTOList.size());
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/besideStation/ProjectBesideStationList";
    }

    /**
     * 查看详细
     */
    @RequestMapping(value = "/searchSideStationDetail")
    public String getProblemManagementDetail(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                             Model model, @RequestParam String sideStationId) {

        ProjectSideStationInfoDTO projectSideStationDTO = projectSideStationService.getProjectSideStationInfoById(sideStationId);
        model.addAttribute("projectSideStation", projectSideStationDTO);
        return "/construction/besideStation/ProjectBesideStationDetail";
    }


    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcels", method = RequestMethod.GET)
    public void exportExcels(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,HttpServletRequest request, HttpServletResponse response,
                             @Valid ProjectSideStationInfoDTO projectSideStationInfoDTO) throws Exception {
        String fileName = "旁站";
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
        String title = "旁站EXCEL文档";
        String[] headers = {"编号", "项目名称", "工序名称", "旁站位置", "旁站人员", "旁站日期"};

        ServletOutputStream out = response.getOutputStream();
        projectSideStationService.exportExcel(title, headers, out,projectSideStationInfoDTO,userInformationEntity);
        //Object obj = null;
        // return new SuccessApiResult(obj);
    }

}
