package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inspectionPlan.DTO.InspectionPlanDTO;
import com.maxrocky.vesta.application.inspectionPlan.DTO.PlanConditionDTO;
import com.maxrocky.vesta.application.inspectionPlan.DTO.PlanListDTO;
import com.maxrocky.vesta.application.inspectionPlan.inf.InspectionPlanService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 检查计划
 * Created by Magic on 2017/6/20.
 */
@Controller
@RequestMapping(value = "/inspectionPlan")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class InspectionPlanController {
    @Autowired
    InspectionPlanService inspectionPlanService;

    @Autowired
    private SecurityProjectService securityProjectService;
    @Autowired
    private AuthAgencyService authAgencyService;
    /**
     * Describe:安全app 按条件下查询检查计划信息
     * CreateBy:Magic
     * CreateOn:2017-06-20
     */
    @RequestMapping(value = "/queryInspectionPlan.html")
    public String dailyPatrolInspection(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                        @Valid PlanConditionDTO planConditionDTO) {

        Map<String, String> groupList = securityProjectService.getAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("goups", groupList);
        if(!StringUtil.isEmpty(planConditionDTO.getGroupId())){
            Map<String, String> areaList = securityProjectService.getAgencyByTypeAndUser(userInformationEntity,"100000001",planConditionDTO.getGroupId());
//            Map<String, String> areaList = securityProjectService.getAreaById(planConditionDTO.getGroupId());
            model.addAttribute("areas", areaList);
        }
        if(!StringUtil.isEmpty(planConditionDTO.getAreaId())){
            Map<String, String> projectList = securityProjectService.getAgencyByTypeAndUser(userInformationEntity,"100000002",planConditionDTO.getAreaId());
//            Map<String, String> projectList = securityProjectService.getProjectById(planConditionDTO.getAreaId());
            model.addAttribute("projects", projectList);
        }

        List<PlanListDTO> list = inspectionPlanService.getPlanList(planConditionDTO, webPage, userInformationEntity);
        model.addAttribute("problems", list);
        model.addAttribute("problem", planConditionDTO);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 sth40020058; //搜索  sth40020059;//详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020058":
                        checkAuthFunctionDTO.setSth40020058("Y");
                        break;
                    case "STH40020059":
                        checkAuthFunctionDTO.setSth40020059("Y");
                        break;
                }
            }
        }
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/inspectionPlan/planList";
    }


    /**
     * 根据集团公司id查询区域公司id
     *
     * @param planConditionDTO
     * @return areaList
     */
    @RequestMapping(value = "/getAreaList")
    public ApiResult getAreaList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid PlanConditionDTO planConditionDTO) {
        Map map  = securityProjectService.getAgencyByTypeAndUser(userInformationEntity,"100000001",planConditionDTO.getGroupId());
//        Map map = securityProjectService.getAreaById(planConditionDTO.getGroupId());
        return new SuccessApiResult(map);
    }

    /**
     * 根据区域公司id查询项目公司id
     *
     * @param planConditionDTO
     * @return projectList
     */
    @RequestMapping(value = "/getProjectList")
    public ApiResult getProjectList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid PlanConditionDTO planConditionDTO) {
        Map map = securityProjectService.getAgencyByTypeAndUser(userInformationEntity,"100000002",planConditionDTO.getAreaId());
//        Map map = securityProjectService.getProjectById(planConditionDTO.getAreaId());
        return new SuccessApiResult(map);
    }

    /**
     * 检查计划详情
     */
    @RequestMapping(value = "/inspectionPlanDetails.html")
    public String inspectionPlanDetails(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                        @RequestParam String planId){
        InspectionPlanDTO inspectionPlanDTO =securityProjectService.inspectionPlanDetails(planId);
        model.addAttribute("plan",inspectionPlanDTO);
        return "/security/inspectionPlan/planDetail";
    }
}
