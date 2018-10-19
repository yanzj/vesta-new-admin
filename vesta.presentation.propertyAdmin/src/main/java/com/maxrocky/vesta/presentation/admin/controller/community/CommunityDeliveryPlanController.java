package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.DeliveryPlanCrmDto;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.CommunityDeliveryPlanCRMService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.DeliveryPlanCrmEntity;
import com.maxrocky.vesta.domain.model.DeliveryPlanReservationTimeRangeEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/4/26
 * Time: 11:46
 * Describe:
 */
@Controller
@RequestMapping(value = "/deliveryPlan")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class CommunityDeliveryPlanController {

    @Autowired
    CommunityDeliveryPlanCRMService communityDeliveryPlanCRMService;
    @Autowired
    StaffUserService staffUserService;

    /**
     * 校验用户是否可以操作该交房计划(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{deliveryPlanId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "deliveryPlanId")String deliveryPlanId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(), menuId);
            //交房计划项目范围
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.communityDeliveryPlanCRMService.getById(deliveryPlanId);
            String projectNum = deliveryPlanCrmEntity.getProjectNum();
            //用户权限范围包含发布范围
            int flag = 0;
            for (Map<String,Object> roleProject : roleProjectList){
                if (projectNum.equals(roleProject.get("projectId").toString())){
                    flag = 1;
                }
            }
            if (flag != 1){
                resultMap.put("error",flag);
                return resultMap;
            }
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 交付批次管理列表
     *
     * @param deliveryPlanCrmDto
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/DeliveryPlan.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                           @Valid DeliveryPlanCrmDto deliveryPlanCrmDto,Model model,WebPage webPage) throws Exception {
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),deliveryPlanCrmDto.getMenuId());
        model.addAttribute("city", cityList);
        if (null != deliveryPlanCrmDto.getScopeId() && !"".equals(deliveryPlanCrmDto.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), deliveryPlanCrmDto.getScopeId(), deliveryPlanCrmDto.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        deliveryPlanCrmDto.setRoleScopeList(roleScopeList);
        //#1.1批次状态
        List<TransfersDto> batchStatusDtoList = new ArrayList<TransfersDto>();
        batchStatusDtoList.add(new TransfersDto(2, "--请选择交付批次--"));
        batchStatusDtoList.add(new TransfersDto(1, "已发布"));
        batchStatusDtoList.add(new TransfersDto(0, "未发布"));
        model.addAttribute("batchStatusDtoList", batchStatusDtoList);
        //#1.2项目
//        LinkedHashMap<String, String> projects = communityDeliveryPlanCRMService.listProject();
//        model.addAttribute("projects", projects);
        //#1.3交付计划名称
        //List<String> batchs = communityDeliveryPlanCRMService.listBatch();
        //根据项目编号查询批次详情
        List<String> batchs = this.communityDeliveryPlanCRMService.queryBatchByProjectNum(deliveryPlanCrmDto.getProjectNum());
        model.addAttribute("batchs", batchs);


        //#2.分页查询信息
        List<DeliveryPlanCrmDto> deliveryPlanCrmDtos = this.communityDeliveryPlanCRMService.queryDeliveryPlanCrm(deliveryPlanCrmDto, webPage);

        //#3.属性添加
        model.addAttribute("deliveryPlanCrmDtos", deliveryPlanCrmDtos);
        model.addAttribute("deliveryPlanCrmDto", deliveryPlanCrmDto);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",deliveryPlanCrmDtos.size());

        return "/community/DeliveryPlanCrm";
    }

    /**
     * 删除批次信息
     * @param deliveryPlanCrmDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(@Valid DeliveryPlanCrmDto deliveryPlanCrmDto, Model model) throws Exception {
        DeliveryPlanCrmEntity deliveryPlanCrm = this.communityDeliveryPlanCRMService.getById(deliveryPlanCrmDto.getId());
        deliveryPlanCrm.setStatus(0);
        this.communityDeliveryPlanCRMService.updateStatus(deliveryPlanCrm);
        if (deliveryPlanCrm != null) {
            return "/deliveryPlan/DeliveryPlan.html";
        }
        return "/deliveryPlan/DeliveryPlan.html";
    }

    /**
     * 更新发布状态
     * @param deliveryPlanCrmDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateBatchStatus")
    public String updateBatchStatus(@Valid DeliveryPlanCrmDto deliveryPlanCrmDto,
                                          Model model,
                                          @ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                          RedirectAttributes attr) throws Exception {
        DeliveryPlanCrmEntity deliveryPlanCrm = this.communityDeliveryPlanCRMService.getById(deliveryPlanCrmDto.getId());
        deliveryPlanCrm.setBatchStatus(Integer.parseInt(deliveryPlanCrmDto.getTempStatus()));
        this.communityDeliveryPlanCRMService.updateStatuss(user, deliveryPlanCrm);
        attr.addAttribute("menuId","005400010000");
        return "redirect:../deliveryPlan/DeliveryPlan.html";
    }

    /**
     * 更新批次信息
     */
    @RequestMapping(value = "/updateDeliveryPlan.html",method = RequestMethod.POST)
    public ModelAndView updateDeliveryPlan(DeliveryPlanCrmDto deliveryPlanCrmDto){
        ModelAndView modelAndView = new ModelAndView("redirect:../deliveryPlan/DeliveryPlan.html?menuId=005400010000");
        try{
            this.communityDeliveryPlanCRMService.update(deliveryPlanCrmDto);
        }catch (Exception e){
            e.printStackTrace();
            return modelAndView;
        }
        return modelAndView;
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping(value = "/ueliveryPlanCrmUpdate")
    public String addPage(@Valid DeliveryPlanCrmDto deliveryPlanCrmDto,Model model){
        //获取项目集合
        //封装前台导航栏MAP
        try {
            //查询基础数据
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.communityDeliveryPlanCRMService.getById(deliveryPlanCrmDto.getId());
            model.addAttribute("deliveryPlanCrmEntity",deliveryPlanCrmEntity);
            //查询房屋列表_清晰条件忽略性能
            String houseString = this.communityDeliveryPlanCRMService.getHouseListByPlanId(deliveryPlanCrmEntity.getId());
            model.addAttribute("houseString",houseString);
            //id,projectName
            model.addAttribute("deliveryPlanCrmDto",deliveryPlanCrmDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/community/DeliveryPlanCrmUpdate";
    }

    /**
     * 获取交付预约时间段列表
     */
    @ResponseBody
    @RequestMapping(value = "/getReservationTimeRangeList",method = RequestMethod.POST)
    public Map<String,Object> getReservationTimeRangeList(DeliveryPlanCrmDto deliveryPlanCrmDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            List<DeliveryPlanReservationTimeRangeEntity> reservationTimeRangeList = communityDeliveryPlanCRMService.getReservationTimeRangeList(deliveryPlanCrmDto);
            resultMap.put("reservationTimeRangeList",reservationTimeRangeList);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 保存交付预约时间段配置
     */
    @ResponseBody
    @RequestMapping(value = "/saveReservationTimeRange",method = RequestMethod.POST)
    public Map<String,Object> saveReservationTimeRange(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                                       DeliveryPlanCrmDto deliveryPlanCrmDto){
        Map<String,Object> resultMap = new HashedMap();
        deliveryPlanCrmDto.setModifyBy(user.getStaffName());
        try{
            communityDeliveryPlanCRMService.saveReservationTimeRange(deliveryPlanCrmDto);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping(value = "/deliveryPlanCrmDetail")
    public String detailPage(@Valid DeliveryPlanCrmDto deliveryPlanCrmDto,Model model,WebPage webPage){
        try {
            //查询基础数据
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.communityDeliveryPlanCRMService.getById(deliveryPlanCrmDto.getId());
            model.addAttribute("deliveryPlanCrmEntity",deliveryPlanCrmEntity);
            //查询预约信息
            webPage.setPageSize(20);
            List<Map<String, Object>> planHouseList = communityDeliveryPlanCRMService.getPlanHouseListByPlanId(deliveryPlanCrmDto.getId(), null);
            webPage.setRecordCount(planHouseList.size());
            model.addAttribute("webPage", webPage);

            List<UserAppointDto> userAppointDtoList = this.communityDeliveryPlanCRMService.searchHouseListByPlanId(deliveryPlanCrmDto.getId(), webPage,deliveryPlanCrmEntity);
            model.addAttribute("reservationList",userAppointDtoList);
            //id,projectName
            model.addAttribute("deliveryPlanCrmDto",deliveryPlanCrmDto);
            //记录集合长度，如果没有查询出数据则不可导出
            model.addAttribute("isExcel",userAppointDtoList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/community/DeliveryPlanCrmDetail";
    }

    /**
     * 根据项目编号查询下属批次信息
     * @param projectNum
     * @param model
     */
    @RequestMapping(value = "/queryBatchByProNum/{projectNum}",method = RequestMethod.GET)
    @ResponseBody
    public ApiResult queryBatchByProNum(@PathVariable(value = "projectNum") String projectNum, Model model){

        List<String> list = this.communityDeliveryPlanCRMService.queryBatchByProjectNum(projectNum);

        return new SuccessApiResult(list);
    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              @Valid DeliveryPlanCrmDto deliveryPlanCrmDto,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,
                              @RequestParam String type){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            deliveryPlanCrmDto.setRoleScopeList(roleScopeList);
            return communityDeliveryPlanCRMService.exportExcel(user,deliveryPlanCrmDto,null,httpServletResponse,type,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
