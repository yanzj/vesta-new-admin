package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyAnnouncementPageDTO;
import com.maxrocky.vesta.application.DTO.PropertyHotLineDisplayDTO;
import com.maxrocky.vesta.application.DTO.PropertyServicesDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务 控制层
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyServicesController {

    /**
     * 物业服务模块 业务逻辑层接口
     */
    @Autowired
    private PropertyServicesService propertyServicesService;
    @Autowired
    private PropertyCompanyService propertyCompanyService;
    @Autowired
    private PropertyHelplineService propertyHelplineService;
    @Autowired
    private HouseCityService houseCityService;
    @Autowired
    private HouseProjectService houseProjectService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    StaffUserService staffUserService;

    /**
     * 初始化物业服务信息列表
     * @param propertyServicesDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/PropertyServices.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid PropertyServicesDTO propertyServicesDTO, Model model,WebPage webPage){
        // 初始化 登陆人所负责范围
        propertyServicesDTO.setQueryScope(userPropertystaff.getQueryScope());
        // 查询物业服务信息
        List<PropertyServicesDTO> servicesDTOList = propertyServicesService.queryPropertyServicesMessage(propertyServicesDTO, webPage);
        // 保存物业服务信息集合
        model.addAttribute("servicesMessage",servicesDTOList);
        // 查询物业服务类别
        PropertyServicesDTO statusMaps = propertyServicesService.queryPropertyServicesType();
        // 保存物业服务信息类别
        model.addAttribute("statusMaps",statusMaps);
        // 保存搜索条件
        model.addAttribute("propertyServices",propertyServicesDTO);
        return "/property/PropertyServices";
    }

    /**
     * 根据ID删除服务信息
     * @param servicesId
     * @return
     */
    @RequestMapping(value = "/removePropertyServicesById")
    public ApiResult removePropertyServices(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity ,@RequestParam String servicesId)
    {
        String propertyServices = propertyServicesService.removePropertyServicesById(servicesId,userPropertystaffEntity);
        return new SuccessApiResult(propertyServices);
    }

    /**
     * 初始化新增 或 修改服务信息页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/NewPropertyServices.html")
    public String newPropertyServices(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertysta,@Valid PropertyServicesDTO propertyServicesDTO,Model model){
        // 不为空 则根据服务ID查询信息 否则初始化新增页面
        if(null != propertyServicesDTO.getServicesId() && !"".equals(propertyServicesDTO.getServicesId())){
            PropertyServicesDTO propertyServices = propertyServicesService.queryPropertyServicesById(propertyServicesDTO.getServicesId());
            model.addAttribute("propertyServices",propertyServices);
        }

        // 查询物业服务类别
        PropertyServicesDTO statusMaps = propertyServicesService.queryPropertyServicesType();
        // 保存物业服务信息类别
        model.addAttribute("statusMaps", statusMaps);
        return "/property/NewPropertyServices";
    }

    /**
     * 新增或修改服务信息
     * @param userPropertystaffEntit
     * @param propertyServicesDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/addPropertyServices.html")
    public String addPropertyServices(@Valid PropertyServicesDTO propertyServicesDTO,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit, Model model,WebPage webPage){
        propertyServicesService.addPropertyServices(userPropertystaffEntit, propertyServicesDTO);
        return "redirect:/property/PropertyServices.html";
    }

    /***********************************************金茂项目：管理端便民信息*********************************************************/

    /**
     * 校验用户是否可以操作该便民信息(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{propertyHelplineId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "propertyHelplineId")String propertyHelplineId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(),menuId);
            //便民信息发布项目范围
            List<Map<String, Object>> announcementProjectList = propertyHelplineService.getProjectScopeByHelplineId(propertyHelplineId);
            //用户权限范围包含公告发布范围
            for (Map<String,Object> announcementProject : announcementProjectList){
                int flag = 0;
                String projectId = announcementProject.get("projectId").toString();
                for (Map<String,Object> roleProject : roleProjectList){
                    if (projectId.equals(roleProject.get("projectId").toString())){
                        flag = 1;
                    }
                }
                if (flag != 1){
                    resultMap.put("error",flag);
                    return resultMap;
                }
            }
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 便民信息列表
     * CreateBy:liudongxin
     * param:user
     * param:model
     * param:webPage
     * @return
     */
    @RequestMapping(value = "/hotLineManagement.html")
    public String hotLineManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                @Valid PropertyAnnouncementPageDTO hotLineDTO,Model model,WebPage webPage){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),hotLineDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != hotLineDTO.getQueryScope() && !"".equals(hotLineDTO.getQueryScope())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), hotLineDTO.getQueryScope(), hotLineDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        hotLineDTO.setRoleScopeList(roleScopeList);
        //获取用户信息
        List<PropertyAnnouncementPageDTO> hotLineList=propertyHelplineService.getHotLineList(user, hotLineDTO, webPage);

        model.addAttribute("hotLines", hotLineList);
        model.addAttribute("isExcel", hotLineList.size());
        //搜索条件
        model.addAttribute("hotLineSearch", hotLineDTO);
        return "/property/PropertyHotLineMange";
    }

    /**
     * 新增城市显示
     * param:user
     * param:hotLineDTO
     * param:model
     * return
     */
    @RequestMapping(value = "/hotLineAdd.html")
    public String hotLineCityShow(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @Valid PropertyAnnouncementPageDTO hotLineDTO,
                                  @Valid String menuId,
                                  Model model){
        //城市下拉框
        Map<String,String> city = houseCityService.getCity();
        hotLineDTO.setCityMap(city);

        Map<String,String> project =new LinkedHashMap<>();
        project.put("-1","---请选择项目---");
        hotLineDTO.setProjectMap(project);
        model.addAttribute("hotLineMap", hotLineDTO);

//        List<Object[]> objects = this.announcementService.listCity();
//        List<TransfersDto> city1 = new ArrayList<TransfersDto>();
//        city1.add(new TransfersDto("0", "全部城市"));
//        for (Object[] object : objects) {
//            String cid = object[0].toString();
//            String name = object[1].toString();
//            city1.add(new TransfersDto(cid, name));
//        }
//        model.addAttribute("city", city1);
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),menuId);
        model.addAttribute("city", cityList);
        return "/property/HotLineAdd";
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
                                        @Valid PropertyAnnouncementPageDTO hotLineDTO){
        List<HouseProjectDto> lineDTO=houseProjectService.getProject(hotLineDTO.getQueryScope());

        return new SuccessApiResult(lineDTO);
    }

    /**
     * 新增保存
     * param:user
     * param:hotLineDTO
     * return
     */
    @RequestMapping(value = "/saveLine")
    public String saveHotLine(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                              @Valid PropertyAnnouncementPageDTO hotLineDTO){
        //保存反馈
        hotLineDTO.setReleasePerson(user.getStaffName());
        String hotLine=propertyHelplineService.saveHotLine(user, hotLineDTO);
        return "redirect:../property/hotLineManagement.html?menuId=005500010000";
    }

    /**
     * 修改显示
     * param:user
     * param:hotLineDTO
     * param:model
     * return
     */
    @RequestMapping(value = "/hotLineDetail.html")
    public String hotLineUpdate(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                @Valid PropertyAnnouncementPageDTO hotLineDTO,
                                @Valid String menuId,Model model,HttpServletRequest request){
        //获取详情信息
        PropertyAnnouncementPageDTO lineDTO=propertyHelplineService.getHotLine(hotLineDTO.getAnnouncementId());
        if (!StringUtils.isEmpty(hotLineDTO.getAnnouncementId())) {
            List<Object[]> PropertyHelplineScopes = this.propertyHelplineService.queryByHotLineId(hotLineDTO.getAnnouncementId());
            //all
            String allCityInScope = "";
            //城市
            String cityInScope = "";
            //城市Id
            String cityIdInScope = "";
            //项目
            String projectInScope = "";
            //项目Id
            String projectIdInScope = "";
            for (Object[] announcementScope : PropertyHelplineScopes) {
                if (null != announcementScope[2])
                    allCityInScope = allCityInScope + announcementScope[2].toString() + ",";
                if (null != announcementScope[1])
                    if (!cityInScope.contains(announcementScope[1].toString())){
                        cityInScope = cityInScope + announcementScope[1].toString() + ",";
                    }
                if (null != announcementScope[3])
                    projectInScope = projectInScope + announcementScope[3].toString() + ",";
                if (null != announcementScope[4]){
                    if (!cityIdInScope.contains(announcementScope[4].toString())){
                        cityIdInScope = cityIdInScope + announcementScope[4].toString() + ",";
                    }
                }

                if (null != announcementScope[5])
                    projectIdInScope = projectIdInScope + announcementScope[5].toString() + ",";
            }
            if (!StringUtils.isEmpty(allCityInScope))
                allCityInScope = StringUtils.substring(allCityInScope, 0, allCityInScope.length() - 1);
            if (!StringUtils.isEmpty(cityInScope))
                cityInScope = StringUtils.substring(cityInScope, 0, allCityInScope.length() - 1);
            if (!StringUtils.isEmpty(projectInScope))
                projectInScope = StringUtils.substring(projectInScope, 0, allCityInScope.length() - 1);


            request.setAttribute("cityInScope", cityInScope);
            request.setAttribute("projectInScope", projectInScope);
            request.setAttribute("cityIdInScope", cityIdInScope);
            request.setAttribute("projectIdInScope", projectIdInScope);
            if (!StringUtils.isEmpty(hotLineDTO.getAnnouncementId())) {
                List<Object[]> PropertyHelplineScopess = this.propertyHelplineService.queryByHotLineId(hotLineDTO.getAnnouncementId());
                for (Object[] announcementScope : PropertyHelplineScopess) {
                    /*if (announcementScope[1].equals("全部城市")){
                        allCityInScope = allCityInScope + announcementScope[1].toString() + ",";
                    }else{
                        allCityInScope = allCityInScope + announcementScope[1].toString() + ",";
                    }*/
                    if (null != announcementScope[5]) {
                        projectIdInScope = projectIdInScope + announcementScope[5].toString() + ",";
                    }
                    allCityInScope = announcementScope[1].toString();
                }

            }
            request.setAttribute("allCityInScope", allCityInScope);
        }
        Map<String, Object> map = propertyHelplineService.queryProjectByHotLineId(hotLineDTO.getAnnouncementId());
        model.addAttribute("projectIds", map.get("projectCodes").toString());
        model.addAttribute("projectNameList",map.get("projectNames").toString());

//        List<Object[]> objects = this.announcementService.listCity();
//        List<TransfersDto> city = new ArrayList<TransfersDto>();
//        city.add(new TransfersDto("0", "全部城市"));
//        for (Object[] object : objects) {
//            String cid = object[0].toString();
//            String name = object[1].toString();
//            city.add(new TransfersDto(cid, name));
//        }
//        model.addAttribute("city", city);
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),menuId);
        model.addAttribute("city", cityList);
        model.addAttribute("hotLine", lineDTO);
        return "/property/HotLineUpdate";
    }

    /**
     * 修改保存
     * param:user
     * param:hotLineDTO
     * return
     */
    @RequestMapping(value = "/updateLine")
    public String updateHotLine(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                @Valid PropertyAnnouncementPageDTO hotLineDTO){
        //保存
        String hotLine=propertyHelplineService.updateHotLine(user, hotLineDTO);
        return "redirect:../property/hotLineManagement.html?menuId=005500010000";
    }

    /**
     * 删除
     * param:user
     * param:id
     * return
     */
    @RequestMapping(value = "/removeLine")
    public ApiResult removeRepair(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @RequestParam String announcementId){
        //删除
        String hotLine=propertyHelplineService.deleteHotLIne(user, announcementId);

        return new SuccessApiResult(hotLine);
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑展示图
    */
    @RequestMapping(value = "/gotoEditDisplay")
    public String gotoEditDisplay(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                              @Valid String menuId,
                              Model model) {
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(), menuId);
        model.addAttribute("city", cityList);
        return "/property/HotLineDisplay";
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 编辑展示图
     */
    @RequestMapping(value = "/editDisplay")
    public String editDisplay(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                              @Valid PropertyHotLineDisplayDTO propertyHotLineDisplayDTO,
                              Model model) {
        try {
            propertyHelplineService.editDisplay(propertyHotLineDisplayDTO);
            return "redirect:../property/hotLineManagement.html?menuId=005500010000";
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取便民信息排序序号最大值，再自加一
    */
    @RequestMapping(value = "/querySortNumberByProjectId/{projectNum}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult querySortNumberByProjectId(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "projectNum") String projectNum,Model model) {
        Integer sortNum = propertyHelplineService.getMaxSortNumber(projectNum);
        return new SuccessApiResult(sortNum);
    }

    /***
     * 导出excel操作   便民信息
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcelbm")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,@Valid PropertyAnnouncementPageDTO hotLineDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            hotLineDTO.setRoleScopeList(roleScopeList);
            return propertyHelplineService.exportExcel(httpServletResponse,hotLineDTO, httpServletRequest);
            //return communityService.exportExcel(httpServletResponse);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
