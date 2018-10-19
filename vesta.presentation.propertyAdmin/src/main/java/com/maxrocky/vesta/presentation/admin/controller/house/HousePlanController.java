package com.maxrocky.vesta.presentation.admin.controller.house;

import com.maxrocky.vesta.application.admin.dto.DeliveryPlanCrmDto;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.service.CommunityDeliveryPlanCRMService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.DeliveryPlanCrmEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/houseplan")
@SessionAttributes(types={UserInformationEntity.class,String.class},value = {"authPropertystaff","menulist","secanViewlist"})
public class HousePlanController {

    @Autowired
    CommunityDeliveryPlanCRMService communityDeliveryPlanCRMService;
    @Autowired
    StaffUserService staffUserService;
    @Autowired
    SecurityProjectService securityProjectService;
    @Autowired
    OrganizeService organizeService;
    @Autowired
    AuthAgencyService authAgencyService;

    /**
     * 交付批次管理列表
     *
     * @param deliveryPlanCrmDto
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/housePlanManage.html")
    public String gotoPage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                           @Valid DeliveryPlanCrmDto deliveryPlanCrmDto,Model model,WebPage webPage) throws Exception {
        //获取数据范围权限
//        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userInformationEntity.getStaffId());
//        deliveryPlanCrmDto.setRoleScopeList(roleScopeList);

        //#1.1批次状态
        List<TransfersDto> batchStatusDtoList = new ArrayList<TransfersDto>();
        batchStatusDtoList.add(new TransfersDto(2, "--请选择交付批次--"));
        batchStatusDtoList.add(new TransfersDto(1, "已发布"));
        batchStatusDtoList.add(new TransfersDto(0, "未发布"));
        model.addAttribute("batchStatusDtoList", batchStatusDtoList);
        //#1.2项目
        //集团
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(deliveryPlanCrmDto.getGroupId())) {
            //区域
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", deliveryPlanCrmDto.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(deliveryPlanCrmDto.getRegionId())) {
            //城市
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", deliveryPlanCrmDto.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(deliveryPlanCrmDto.getCityId())){
            //项目
            Map<String, String> projectIdList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", deliveryPlanCrmDto.getCityId());
            Map<String, Object> projectList = organizeService.getProjectNumListProjectId(projectIdList);
            model.addAttribute("projects", projectList);
        }
//        LinkedHashMap<String, String> projects = communityDeliveryPlanCRMService.listProject();
//        model.addAttribute("projects", projects);
        //#1.3交付计划名称
        //List<String> batchs = communityDeliveryPlanCRMService.listBatch();
        //根据项目编号查询批次详情
        List<String> batchs = this.communityDeliveryPlanCRMService.queryBatchByProjectNum(deliveryPlanCrmDto.getProjectNum());
        model.addAttribute("batchs", batchs);

        //#2.分页查询信息
        List<DeliveryPlanCrmDto> deliveryPlanCrmDtos = new ArrayList<>();
        if(!StringUtil.isEmpty(deliveryPlanCrmDto.getProjectNum())){
            deliveryPlanCrmDtos =   this.communityDeliveryPlanCRMService.queryDeliveryPlanCrm_1(deliveryPlanCrmDto, webPage);
        }

        //#3.属性添加
        model.addAttribute("deliveryPlanCrmDtos", deliveryPlanCrmDtos);
        model.addAttribute("deliveryPlanCrmDto", deliveryPlanCrmDto);
        //#4.获取功能点
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有交房验房计划别名   qch40010070; //设置简称
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010070":
                        checkAuthFunctionDTO.setQch40010070("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);

        return "/house/housePlanManage";
    }


    /**
     * 跳转到修改页面
     */
    @RequestMapping(value = "/toUpdate")
    public String toUpdate(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                           @Valid DeliveryPlanCrmDto deliveryPlanCrmDto,Model model){
        //获取项目集合
        //封装前台导航栏MAP
        try {
            //查询基础数据
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.communityDeliveryPlanCRMService.getById(deliveryPlanCrmDto.getId());
            model.addAttribute("deliveryPlanCrmEntity",deliveryPlanCrmEntity);
            //查询房屋列表_清晰条件忽略性能
//            String houseString = this.communityDeliveryPlanCRMService.getHouseListByPlanId(deliveryPlanCrmEntity.getId());
//            model.addAttribute("houseString",houseString);
            //id,projectName
            //#4.获取功能点
            CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
            List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
            if (function != null) {
                //校验是否有交房验房计划别名    qch40010071; //保存
                for (int i = 0; i < function.size(); i++) {
                    switch (function.get(i).toString()) {
                        case "QCH40010071":
                            checkAuthFunctionDTO.setQch40010071("Y");
                            break;
                    }
                }
            }
            model.addAttribute("function", checkAuthFunctionDTO);
            model.addAttribute("deliveryPlanCrmDto",deliveryPlanCrmDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/house/housePlanModify";
    }

    /**
     * 更新批次信息
     * @param deliveryPlanCrmDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView update(@Valid DeliveryPlanCrmDto deliveryPlanCrmDto, Model model) throws Exception {
        this.communityDeliveryPlanCRMService.updateShortName(deliveryPlanCrmDto);
        return new ModelAndView("redirect:../houseplan/housePlanManage.html");
    }


}
