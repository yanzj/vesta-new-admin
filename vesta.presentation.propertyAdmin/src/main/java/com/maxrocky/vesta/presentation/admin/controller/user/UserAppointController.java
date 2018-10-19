package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.CommunityDeliveryPlanCRMService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/28.
 * 业主预约房屋交付
 */
@Controller
@RequestMapping(value = "/userAppoint")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class UserAppointController {
    @Autowired
    private HouseInfoService houseInfoService;
    @Autowired
    StaffUserService staffUserService;
    /* 交付批次服务 */
    @Autowired
    private CommunityDeliveryPlanCRMService deliveryPlanCRMService;

    /**
     * 用于预约列表
     * @param userAppointDto
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/userAppointManagement.html")
    public String userAppoint(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                              @Valid UserAppointDto userAppointDto,Model model,WebPage webPage){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),userAppointDto.getMenuId());
        model.addAttribute("city", cityList);
        if (null != userAppointDto.getScopeId() && !"".equals(userAppointDto.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), userAppointDto.getScopeId(), userAppointDto.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        userAppointDto.setRoleScopeList(roleScopeList);
        /*Map<String,String> projects = houseInfoService.listProject();
        model.addAttribute("projectName", projects);
        Map<String,String> builds = houseInfoService.listBuild();
        model.addAttribute("buildName", builds);
        Map<String,String> units = houseInfoService.listUnit();
        model.addAttribute("unitName", units);
        Map<String,String> batchs=new LinkedHashMap<>();
        batchs.put("0", "--请选择交付批次--");
        batchs.put("1", "第一批");
        batchs.put("2", "第二批");
        model.addAttribute("batchName", batchs);*/
        Map<String,String> times=new LinkedHashMap<>();
        times.put("0", "--请选择时间段--");
        times.put("1", "上午");
        times.put("2", "下午");
        model.addAttribute("timeName", times);
        List<UserAppointDto> userAppointDtos = deliveryPlanCRMService.reservationList(userAppointDto,webPage);
        model.addAttribute("userAppointDtos",userAppointDtos);
        //搜索条件
        model.addAttribute("userAppointDto",userAppointDto);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",userAppointDtos.size());

        return "/community/UserAppoint";
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
                              @Valid UserAppointDto userAppointDto,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,
                              @RequestParam String type){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            userAppointDto.setRoleScopeList(roleScopeList);
            return deliveryPlanCRMService.exportExcel(user,null,userAppointDto,httpServletResponse,type,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
