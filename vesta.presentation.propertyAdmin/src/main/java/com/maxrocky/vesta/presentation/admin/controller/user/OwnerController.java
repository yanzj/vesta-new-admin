package com.maxrocky.vesta.presentation.admin.controller.user;


import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.json.LG0001.LoginReturnJsonDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/1/26.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class OwnerController {

    @Autowired
    private UserOwnerService userOwnerService;

    @Autowired
    StaffUserService staffUserService;

    @Autowired
    DefaultConfigService defaultConfigService;

    /**
     * Ajax改变业态列表
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/listFormat")
    public ApiResult listFormat(@Param String projectId){
        List<FormatSelDTO> formatSelDTOs = userOwnerService.getFormatSel(projectId);
        return new SuccessApiResult(formatSelDTOs);
    }

    /**
     * Ajax改变楼栋列表
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/listBuild")
    public ApiResult listBuild(@Param String projectId){
        List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getBuildSel(projectId);
        return new SuccessApiResult(buildingSelDTOs);
    }

    /**
     * Ajax改变楼栋列表
     * @param projectId,formatId
     * @return
     */
    @RequestMapping(value = "/listBuildF")
    public ApiResult listBuild(@Param String projectId,@Param String formatId){
        List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getformatBuildSel(projectId, formatId);
        return new SuccessApiResult(buildingSelDTOs);
    }

    /**
     *Ajax改变单元下拉框
     * @param buildingId
     * @return
     */
    @RequestMapping(value = "/listUnit")
    public ApiResult listUnit(@Param String buildingId){

        List<UnitSelDTO> unitSelDTOs = userOwnerService.getUnitSel(buildingId);

        return new SuccessApiResult(unitSelDTOs);
    }

    /**
     * Ajax改变房间下拉框
     * @param unitId
     * @return
     */
    @RequestMapping(value = "/listRoom")
    public ApiResult listRoom(@Param String unitId){


        List<RoomDTO> roomDTOs = userOwnerService.getRoomSel(unitId);

        return new SuccessApiResult(roomDTOs);
    }

    /**
     * 初始化业主列表
     * @param userPropertystaffEntity
     * @param userOwnerDTO
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/ownerManage.html",method = RequestMethod.GET)
    public String listOwner(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid UserOwnerDTO userOwnerDTO,WebPage webPage,Model model){

        //初始化项目下拉框
        List<ProjectSelDTO> projectSelDTOs = userOwnerService.getProjectSel(userPropertystaffEntity.getProjectId());
        model.addAttribute("projectSelDTOs", projectSelDTOs);
        if (projectSelDTOs.size()>0) {
            //初始化楼栋列表
            List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getBuildSel(projectSelDTOs.get(0).getProjectId());
            model.addAttribute("buildingSelDTOs",buildingSelDTOs);
            if (buildingSelDTOs.size()>0){
                //初始化单元列表
                List<UnitSelDTO> unitSelDTOs = userOwnerService.getUnitSel(buildingSelDTOs.get(0).getBuildingId());
                model.addAttribute("unitSelDTOs",unitSelDTOs);
                if (unitSelDTOs.size()>0){
                    List<RoomDTO> roomDTOs = userOwnerService.getRoomSel(unitSelDTOs.get(0).getUnitId());
                    model.addAttribute("roomDTOs",roomDTOs);
                }
            }
        }
        //获取业主列表
        userOwnerDTO.setProjectIdDto(userPropertystaffEntity.getQueryScope());//按照管理员所属项目权限
        List<UserOwnerDTO> userOwnerDTOs = userOwnerService.listOwnerDto(userOwnerDTO, webPage, HouseUserBookEntity.USER_TYPE_OWNER);
        model.addAttribute("userOwnerDTOs",userOwnerDTOs);      //列表
        model.addAttribute("userOwnerDTO",userOwnerDTO);        //搜索栏条件
        return "/user/OwnerManage";
    }

    /**
     * 跳转到新增用户界面
     * @param userPropertystaffEntity
     * @param userOwnerDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddOwner")
    public String gotoAddOwner(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid UserOwnerDTO userOwnerDTO,Model model){

        //初始化项目下拉框
        List<ProjectSelDTO> projectSelDTOs = userOwnerService.getProjectSel(userPropertystaffEntity.getCompanyId());
        model.addAttribute("projectSelDTOs", projectSelDTOs);
        if (projectSelDTOs.size()>0) {
            //初始化楼栋列表
            List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getBuildSel(projectSelDTOs.get(0).getProjectId());
            model.addAttribute("buildingSelDTOs",buildingSelDTOs);
            if (buildingSelDTOs.size()>0){
                //初始化单元列表
                List<UnitSelDTO> unitSelDTOs = userOwnerService.getUnitSel(buildingSelDTOs.get(0).getBuildingId());
                model.addAttribute("unitSelDTOs",unitSelDTOs);
                if (unitSelDTOs.size()>0){
                    List<RoomDTO> roomDTOs = userOwnerService.getRoomSel(unitSelDTOs.get(0).getUnitId());
                    model.addAttribute("roomDTOs",roomDTOs);
                }
            }
        }

        return "/user/OwnerAdd";
    }

    /**
     * 新增业主
     * @param userPropertystaffEntity
     * @param userOwnerDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/addOwner")
    public ModelAndView addOwner(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid UserOwnerDTO userOwnerDTO,Model model){

        userOwnerDTO.setCompanyId(userPropertystaffEntity.getCompanyId());
        userOwnerDTO.setCreateBy(userPropertystaffEntity.getCreateBy());

        userOwnerService.addOwner(userOwnerDTO);

        return new ModelAndView("redirect:/user/ownerManage.html");
    }

    /**
     * 新增业主
     * @param userPropertystaffEntity
     * @param userOwnerDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteOwner")
    public ModelAndView deleteOwner(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid UserOwnerDTO userOwnerDTO,Model model){

        userOwnerDTO.setCompanyId(userPropertystaffEntity.getCompanyId());
        userOwnerDTO.setCreateBy(userPropertystaffEntity.getCreateBy());

        userOwnerService.addOwner(userOwnerDTO);

        return new ModelAndView("redirect:/user/ownerManage.html");
    }

    /****************************************************金茂项目：管理端会员管理****************************************/

    /**
     * 普通用户
     * CreateBy:liudongxin
     * param:user
     * param:model
     * param:webPage
     * @return
     */
    @RequestMapping(value = "/commonUserManagement.html")
    public String commonUserManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @Valid LoginReturnJsonDTO userDTO,Model model,WebPage webPage){
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        userDTO.setRoleScopeList(roleScopeList);
        userDTO.setUserType("2");//用户类型：普通会员
        //获取用户信息
        List<LoginReturnJsonDTO> userList=userOwnerService.getUserList(user, userDTO, webPage);
        model.addAttribute("commonUsers", userList);
        //搜索条件
        model.addAttribute("commonUserSearch", userDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",userList.size());
        return "/user/commonUserManage";
    }

    /**
     * 业主用户
     * CreateBy:liudongxin
     * param:user
     * param:model
     * param:webPage
     * @return
     */
    @RequestMapping(value = "/ownerUserManagement.html")
    public String ownerUserManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                   @Valid LoginReturnJsonDTO userDTO,Model model,WebPage webPage){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),userDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != userDTO.getScopeId() && !"".equals(userDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), userDTO.getScopeId(), userDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        userDTO.setRoleScopeList(roleScopeList);
        userDTO.setUserType("3");//用户类型：业主
        //获取用户信息
        List<LoginReturnJsonDTO> userList=userOwnerService.getUserList(user, userDTO, webPage);
        model.addAttribute("commonUsers", userList);
        //搜索条件
        model.addAttribute("commonUserSearch",userDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",userList.size());
        return "/user/ownerUserManage";
    }

    /**
     * 同住人用户
     * CreateBy:liudongxin
     * param:user
     * param:model
     * param:webPage
     * @return
     */
    @RequestMapping(value = "/housemateUserManagement.html")
    public String housemateManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @Valid LoginReturnJsonDTO userDTO,Model model,WebPage webPage){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),userDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != userDTO.getScopeId() && !"".equals(userDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), userDTO.getScopeId(), userDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        userDTO.setRoleScopeList(roleScopeList);
        userDTO.setUserType("4");//用户类型：同住人
        //获取用户信息
        List<LoginReturnJsonDTO> userList=userOwnerService.getUserList(user, userDTO, webPage);
        model.addAttribute("commonUsers", userList);
        //搜索条件
        model.addAttribute("commonUserSearch",userDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",userList.size());
        return "/user/housemateUserManage";
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
                              @Valid LoginReturnJsonDTO userDTO,
                              HttpServletResponse response,HttpServletRequest request,
                              @RequestParam String type){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            userDTO.setRoleScopeList(roleScopeList);
//            return userOwnerService.exportExcel(user,userDTO,httpServletResponse,type,httpServletRequest);
            userOwnerService.exportExcel2(response,request,type,user,userDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * 获取业主认证信息列表
     */
    @RequestMapping(value = "/getOwnerAuthenticationList.html")
    public ModelAndView getOwnerAuthenticationList(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                                   OwnerAuthenticationDTO ownerAuthenticationDTO,
                                                   WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/user/OwnerAuthenticationList");
        try{
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
            modelAndView.addObject("clientConfigList",clientConfigList);
            //检索参数回显
            modelAndView.addObject("ownerAuthenticationDTO",ownerAuthenticationDTO);
            //分页设置并回显
            webPage.setPageSize(20);
            List<OwnerAuthenticationDTO> list = userOwnerService.getOwnerAuthenticationList(ownerAuthenticationDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //执行查询
            List<OwnerAuthenticationDTO> ownerAuthenticationList = userOwnerService.getOwnerAuthenticationList(ownerAuthenticationDTO, webPage);
            modelAndView.addObject("ownerAuthenticationList",ownerAuthenticationList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 获取业主认证信息
     */
    @RequestMapping(value = "/getOwnerAuthenticationInfo.html")
    public ModelAndView getOwnerAuthenticationInfo(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                                   OwnerAuthenticationDTO ownerAuthenticationDTO){
        ModelAndView modelAndView = new ModelAndView("/user/OwnerAuthenticationInfo");
        try{
            OwnerAuthenticationDTO ownerAuthenticationInfo = userOwnerService.getOwnerAuthenticationInfo(ownerAuthenticationDTO);
            modelAndView.addObject("ownerAuthenticationInfo",ownerAuthenticationInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 更新业主认证状态
     */
    @ResponseBody
    @RequestMapping(value = "/updateOwnerAuthenticationState")
    public Map<String,Object> updateOwnerAuthenticationState(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                                 OwnerAuthenticationDTO ownerAuthenticationDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //操作人
            ownerAuthenticationDTO.setModifyBy(user.getStaffName());
            int flag = userOwnerService.updateOwnerAuthenticationState(ownerAuthenticationDTO);
            //falg:(1011—业主证件号码未找到,认证失败;1012-业主证件号码已被认证,默认通过;)
            resultMap.put("error",0);
            resultMap.put("flag",flag);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:CRM业主数据
     * CreateBy:WeiYangDong_20171017
     */
    @RequestMapping(value = "/crmOwnerUserManagement.html")
    public String crmOwnerUserManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @Valid LoginReturnJsonDTO userDTO,Model model,WebPage webPage){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),userDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != userDTO.getScopeId() && !"".equals(userDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), userDTO.getScopeId(), userDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        model.addAttribute("commonUserSearch",userDTO);
        //分页设置并回显
        webPage.setPageSize(20);
        List<Map<String, Object>> list = userOwnerService.getCRMOwnerUserList(userDTO, null);
        webPage.setRecordCount(list.size());
        model.addAttribute("webPage", webPage);
        //获取CRM业主数据
        List<Map<String, Object>> crmOwnerUserList = userOwnerService.getCRMOwnerUserList(userDTO, webPage);
        model.addAttribute("crmOwnerUserList", crmOwnerUserList);
        return "/user/CRMOwnerUserManage";
    }

    /**
     * 手动同步HouseUserCrm房产数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/houseUserCrmSyn")
    public Map<String,Object> houseUserCrmSyn(){
        Map<String,Object> resultMap = new HashedMap();
        try{
            userOwnerService.houseUserCrmSyn();
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }
}
