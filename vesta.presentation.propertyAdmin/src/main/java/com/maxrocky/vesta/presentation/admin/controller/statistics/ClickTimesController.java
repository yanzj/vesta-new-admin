package com.maxrocky.vesta.presentation.admin.controller.statistics;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.inf.ClickTimesService;
import com.maxrocky.vesta.application.inf.HouseCityService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
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
 * Created by sunmei on 2016/2/15.
 */
@Controller
@RequestMapping(value = "/click")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class ClickTimesController {
    @Autowired
    private PropertyCompanyService propertyCompanyService;
    @Autowired
    private ClickTimesService clickTimesService;
    @Autowired
    private HouseCityService houseCityService;
    @Autowired
    private HouseProjectService houseProjectService;
/*    *//**
     * 初始化各项目点击量列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/ClickTimes.html")

    public String gotoClickNums(@ModelAttribute("propertystaff") UserPropertyStaffEntity userProperty,@Valid ClickTimesSeachDTO clickTimesSeachDTO ,Model model,WebPage page){
        // 初始化 登陆人所负责范围
        clickTimesSeachDTO.setQueryScope(userProperty.getQueryScope());
        // 查询各模块点击量信息
        List<ClickTimesDTO> ClickTimesList= clickTimesService.ClickManage(clickTimesSeachDTO, page);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userProperty.getProjectId());
        model.addAttribute("ClickTimesList",ClickTimesList);
        model.addAttribute("ClickTimesMap",propertyCompanyMap);
        model.addAttribute("clickTimesSeachDTO",clickTimesSeachDTO);
        return "/statistics/clickTimes";
    }

    /*    *//**
     * 首页点击量列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/ClickTimesPageManage.html")

    public String gotoPageManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid ClickTimesSeachDTO clickTimesSeachDTO ,Model model,WebPage page){
        // 查询各模块点击量信息
        List<ClickTimesDTO> ClickTimesList= clickTimesService.ClickPageManage(clickTimesSeachDTO, page);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaff.getProjectId());
        model.addAttribute("ClickTimesList",ClickTimesList);
        model.addAttribute("ClickTimesMap",propertyCompanyMap);
        model.addAttribute("clickTimesSeachDTO",clickTimesSeachDTO);
        return "/statistics/clickTimesPage";
    }

       /*    *//**
     * 初始化首页点击量列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/ClickTimesPage.html")

    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertysta,@RequestParam String projectId ,Model model,WebPage page){
        ClickTimesSeachDTO clickTimesSeachDTO =new ClickTimesSeachDTO();
        clickTimesSeachDTO.setPropertyProject(projectId);
        List<ClickTimesDTO> ClickTimesList= clickTimesService.ClickPageManage(clickTimesSeachDTO, page);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertysta.getProjectId());
        model.addAttribute("ClickTimesList",ClickTimesList);
        model.addAttribute("ClickTimesMap",propertyCompanyMap);
        return "/statistics/clickTimesPage";
    }


    /*************************************************金茂项目：管理端用户分析管理*************************************/

    /**
     * 用户分析
     * param user
     * param projectId
     * param model
     * param page
     * return
     */
    @RequestMapping(value = "/userReport.html")
    public String userReportManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                   @Valid UserLoginStatisticDTO userTotalDTO ,Model model,WebPage page){
        List<UserLoginStatisticDTO> userList=clickTimesService.getUserTotal(userTotalDTO,page);
        model.addAttribute("users", userList);
        //搜索条件
        model.addAttribute("userSearch", userTotalDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",userList.size());
        return "/statistics/userReport";
    }

    /**
     * 客户单据统计
     * param user
     * param projectId
     * param model
     * param page
     * return
     */
    @RequestMapping(value = "/invoicesTotal.html")
    public String invoicesManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                   @Valid UserLoginStatisticDTO userTotalDTO ,Model model,WebPage page){
        //城市下拉框
        Map<String,String> city = houseCityService.getCity();
        userTotalDTO.setCityMap(city);
        //项目
        List<HouseProjectDto> lineDTO=houseProjectService.getProject(userTotalDTO.getCity());

        List<UserLoginStatisticDTO> invoicesList=clickTimesService.getInvoicesTotal(userTotalDTO, page);
        model.addAttribute("invoices", invoicesList);
        //搜索条件
        model.addAttribute("project",lineDTO);
        model.addAttribute("invoiceSearch", userTotalDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",invoicesList.size());
        return "/statistics/invoicesTotal";
    }

    /**
     * 新增项目显示
     * param:user
     * param:hotLineDTO
     * param:model
     * return
     */
    @RequestMapping(value = "/projectShow")
    public ApiResult hotLineProjectShow(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                        @Valid UserLoginStatisticDTO userTotalDTO){
        List<HouseProjectDto> projectDTO=houseProjectService.getProject(userTotalDTO.getCity());
        return new SuccessApiResult(projectDTO);
    }

    /**
     * 菜单分析
     * param user
     * param projectId
     * param model
     * param page
     * return
     */
    @RequestMapping(value = "/menuReport.html")
    public String menuManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                   @Valid UserLoginStatisticDTO userTotalDTO ,Model model,WebPage page){
        List<UserLoginStatisticDTO> menuList=clickTimesService.getMenuTotal(userTotalDTO, page);
        model.addAttribute("menus", menuList);
        //搜索条件
        model.addAttribute("menuSearch", userTotalDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",menuList.size());
        return "/statistics/menuReport";
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
                              @Valid UserLoginStatisticDTO userTotalDTO,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,
                              @RequestParam String type){
        try {
            return clickTimesService.exportExcel(user,userTotalDTO,httpServletResponse,type,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }
}
