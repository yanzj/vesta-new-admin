package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.ProjectMaterial.DTO.GetProjectMaterialDTO;
import com.maxrocky.vesta.application.ProjectMaterial.DTO.MaterialAdminDTO;
import com.maxrocky.vesta.application.ProjectMaterial.DTO.ProjectMaterialAdminDTO;
import com.maxrocky.vesta.application.ProjectMaterial.inf.ProjectMaterialService;
import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.GetDailyPatrolInspectionAdminDTO;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.GetDailyPatrolInspectionDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 材料验收
 * Created by Magic on 2016/12/12.
 */
@Controller
@RequestMapping(value = "/ProjectMaterial")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectMaterialAdminController {
    @Autowired
    ProjectBuildingService projectBuildingService;

    @Autowired
    ProjectProjectService projectProjectService;

    @Autowired
    GetAllClassifyService getAllClassifyService;

    @Autowired
    ProjectMaterialService projectMaterialService;

    @Autowired
    private SecurityProjectService securityProjectService;
    @Autowired
    AuthAgencyService authAgencyService;

    @RequestMapping(value = "/projectMaterial.html")
    public String dailyPatrolInspection(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                        @Valid GetProjectMaterialDTO getProjectMaterialDTO) {
//        Map<String, String> projectMap = projectProjectService.getProjectProjects();
//        model.addAttribute("projects", projectMap);
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaffEntity.getStaffId());
//        if (f) {
//            //获取项目数据
//            Map<String, String> projectMap = projectProjectService.getProjectProjects();
//            model.addAttribute("projects", projectMap);
//        } else {
//            Map<String, String> projectMap = getAllClassifyService.getProjectProjectsByStaffId(userPropertystaffEntity.getStaffId());
//            model.addAttribute("projects", projectMap);
//        }

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        Map<String, String> groupList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(getProjectMaterialDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000001",getProjectMaterialDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(getProjectMaterialDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000003",getProjectMaterialDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }

        if(!StringUtil.isEmpty(getProjectMaterialDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getESAgencyByTypeAndUser(userInformationEntity,"100000002",getProjectMaterialDTO.getCityId());
            model.addAttribute("projects", projectList);
        }
        // 取得楼栋信息
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getProjectId())) {
            Map<String, String> builds = projectBuildingService.getBuildListByProjectId(getProjectMaterialDTO.getProjectId());
            model.addAttribute("builds", builds);
        }
        //获取一级分类
        Map<String, String> firstLevel = getAllClassifyService.getClassifyOne("4");
        model.addAttribute("firstLevels", firstLevel);
        //获取二级分类
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getClassifyOne())) {
            Map<String, String> secondLevel = getAllClassifyService.getClassifyTwo(getProjectMaterialDTO.getClassifyOne(), "4");
            model.addAttribute("secondLevels", secondLevel);
        }
        List<ProjectMaterialAdminDTO> list=projectMaterialService.getMaterialAdmin(getProjectMaterialDTO,webPage,userInformationEntity.getStaffId());

        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020022 搜素 ESH40020023 导出Excel ESH40020024 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020022":
                        checkAuthFunctionDTO.setEsh40020022("Y");
                        break;
                    case "ESH40020023":
                        checkAuthFunctionDTO.setEsh40020023("Y");
                        break;
                    case "ESH40020024":
                        checkAuthFunctionDTO.setEsh40020024("Y");
                        break;

                }
            }
        }
        model.addAttribute("problems", list);
        model.addAttribute("problem", getProjectMaterialDTO);
        model.addAttribute("typeMaps", getProjectMaterialDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/projectMaterial/projectMaterialList";
    }

    /**
     * 根据一级分类id查询二级分类
     *
     * @param getDailyPatrolInspectionDTO
     * @return
     */
    @RequestMapping(value = "/getSecondLevel")
    public ApiResult getSecondLevel(@Valid GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO) {
        Map map = getAllClassifyService.getClassifyTwo(getDailyPatrolInspectionDTO.getClassifyOne(), "4");
        return new SuccessApiResult(map);
    }

    /** 查询详情预修改
     * @param getProjectMaterialDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/getMaterialBymaterialId")
    public String getMaterialBymaterialId(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                                          @Valid GetProjectMaterialDTO getProjectMaterialDTO) {
        MaterialAdminDTO materialAdminDTO=projectMaterialService.getMaterialAdminByID(getProjectMaterialDTO, userInformationEntity);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有       ESH40020001 搜素 ESH40020002 导出Excel ESH40020003 导出Word ESH40020004 详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020099":
                        checkAuthFunctionDTO.setEsh40020099("Y");
                        break;
                }
            }
        }
        model.addAttribute("checkAuthFunction", checkAuthFunctionDTO);
        model.addAttribute("material", materialAdminDTO);
        return "/construction/projectMaterial/projectMaterialDetail";

    }

    /** 非正常关闭
     * @param materialAdminDTO
     * @return postDailyPatrolInspection
     */
    @RequestMapping(value = "/shutDown")
    public ModelAndView shutDown(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                                 @Valid MaterialAdminDTO materialAdminDTO) {
        String ss=projectMaterialService.shutDownAdmin(materialAdminDTO, userInformationEntity);
        return new ModelAndView("redirect:..//ProjectMaterial/projectMaterial.html");

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
                            @Valid GetProjectMaterialDTO getProjectMaterialDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "材料验收清单";
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
        String title = "材料验收清单";
        String[] headers = {"序号", "项目名称", "材料类型", "批次名称","进场时间","进场批量","准备使用部位","供应商","材料负责人","甲方负责人","第三方监理","创建人","状态"};
        ServletOutputStream out = response.getOutputStream();
        projectMaterialService.exportExcel(title, headers, out, getProjectMaterialDTO, webPage, userInformationEntity.getStaffId());
    }

}
