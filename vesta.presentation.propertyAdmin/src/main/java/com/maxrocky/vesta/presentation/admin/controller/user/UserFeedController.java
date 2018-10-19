package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.OwnerAuthenticationDTO;
import com.maxrocky.vesta.application.DTO.admin.UserFeedbackDTO;
import com.maxrocky.vesta.application.DTO.admin.UserFeedbackSearchDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.UserFeedbackService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.UserFeedbackEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunmei on 2016/2/24.
 */
@Controller
@RequestMapping(value = "/userFeed")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class UserFeedController {
    @Autowired
    private UserFeedbackService userFeedbackService;
    @Autowired
    StaffUserService staffUserService;
    @Autowired
    DefaultConfigService defaultConfigService;
    @Autowired
    UserOwnerService userOwnerService;

    @RequestMapping(value = "/userFeedManage.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid UserFeedbackSearchDTO userFeedbackSearchDTO, Model model, WebPage webPage) {
        // 初始化 登陆人所负责范围
        userFeedbackSearchDTO.setQueryScope(userPropertystaff.getQueryScope());
        List<UserFeedbackDTO> userFeedManageList = userFeedbackService.FeedbackList(userFeedbackSearchDTO, webPage);
        model.addAttribute("userFeedManage", userFeedManageList);
        //搜索条件
        model.addAttribute("userFeedbackSearch", userFeedbackSearchDTO);

        return "/user/userFeedManage";
    }


    @RequestMapping(value = "/showImg.html")
    public String ShowImg(@RequestParam String businessId, Model model) {
        UserFeedbackDTO userFeedManage = userFeedbackService.getUserImageByBusinessId(businessId);
        model.addAttribute("userFeedManage", userFeedManage);
        return "/user/userFeedImgManage";
    }

    /*******************************************金茂项目：管理端意见反馈管理**********************************/

    /**
     * 校验用户是否可以操作该反馈(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{feedbackId}/{menuId}")
    public Map<String, Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                         @PathVariable(value = "feedbackId") String feedbackId,
                                         @PathVariable(value = "menuId") String menuId) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(), menuId);
            //反馈项目
            OwnerAuthenticationDTO ownerAuthenticationDTO = new OwnerAuthenticationDTO();
            ownerAuthenticationDTO.setId(feedbackId);
            OwnerAuthenticationDTO ownerAppealInfo = userOwnerService.getOwnerAppealInfo(ownerAuthenticationDTO);
//            UserFeedbackEntity userFeedbackEntity = userFeedbackService.getFeedbackById(feedbackId);
            int flag = 0;
            String projectId = ownerAppealInfo.getProjectNum();
            if (null == projectId){
                resultMap.put("error", 1);
            }else{
                for (Map<String, Object> roleProject : roleProjectList) {
                    if (projectId.equals(roleProject.get("projectId").toString())) {
                        flag = 1;
                    }
                }
                if (flag != 1) {
                    resultMap.put("error", flag);
                    return resultMap;
                }
                resultMap.put("error", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", -1);
        }
        return resultMap;
    }

    /**
     * 获取意见反馈列表
     * param user
     * param feedbackDTO
     * param model
     * param webPage
     * return
     */
    @RequestMapping(value = "/feedbackManage.html")
    public String feedbackManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                 @Valid UserFeedbackSearchDTO feedbackDTO, Model model, WebPage webPage) {
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),feedbackDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != feedbackDTO.getScopeId() && !"".equals(feedbackDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), feedbackDTO.getScopeId(), feedbackDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        feedbackDTO.setRoleScopeList(roleScopeList);
        /* 意见反馈_memo:2 */
        //反馈类型(1金茂会员,2金茂质检,3微信)(1:APP,2:微信)
        if ("1".equals(feedbackDTO.getMemo())) {
            feedbackDTO.setFeedbackType("1");
        } else if ("2".equals(feedbackDTO.getMemo())) {
            feedbackDTO.setFeedbackType("3");
        }
        feedbackDTO.setMemo("2");
        //获取用户信息
        List<UserFeedbackSearchDTO> feedbackList = userFeedbackService.getFeedbackList(user, feedbackDTO, webPage);
        model.addAttribute("feedBacks", feedbackList);

        UserFeedbackSearchDTO feedbackDTOs = new UserFeedbackSearchDTO();
        //用户类型下拉框
        Map<String, String> typeMap = new LinkedHashMap<>();
        typeMap.put("0", "--请选择用户类型--");
        typeMap.put("1", "普通用户");
        typeMap.put("2", "同住人");
        typeMap.put("3", "业主");
        typeMap.put("6", "虚拟用户");
        feedbackDTOs.setUserTypeMap(typeMap);
        //反馈状态下拉框
        Map<String, String> stateMap = new LinkedHashMap<>();
        stateMap.put("0", "--请选择反馈状态--");
        stateMap.put("1", "未处理");
        stateMap.put("2", "已处理");
        feedbackDTOs.setStateMap(stateMap);
        //来源下拉框
        Map<String, String> feedBackTypeMap = new LinkedHashMap<>();
        feedBackTypeMap.put("0", "--请选择来源--");
        feedBackTypeMap.put("1", "APP");
        feedBackTypeMap.put("2", "微信");
        feedbackDTOs.setResourceMap(feedBackTypeMap);
        //意见反馈来源分类
        Map<String, String> fbMap = new LinkedHashMap<>();
        fbMap.put("0", "--意见反馈来源分类--");
        fbMap.put("1", "便民信息纠错");
        fbMap.put("2", "意见反馈");
        feedbackDTOs.setFbMap(fbMap);
        model.addAttribute("feedbackMaps", feedbackDTOs);

        //搜索条件
        //正确回显反馈来源
        if ("1".equals(feedbackDTO.getFeedbackType())) {
            feedbackDTO.setMemo("1");
        } else if ("3".equals(feedbackDTO.getFeedbackType())) {
            feedbackDTO.setMemo("2");
        } else {
            feedbackDTO.setMemo("0");
        }
        model.addAttribute("feedBackSearch", feedbackDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel", feedbackList.size());
        return "/user/userFeedManage";
    }

    /**
     * 详情
     * param feedbackDTO
     * param model
     * return
     */
    @RequestMapping(value = "/feedbackDetail.html")
    public String feedbackDetail(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                 @Valid UserFeedbackSearchDTO feedbackDTO, Model model) {
        UserFeedbackSearchDTO feedbackDTOs = userFeedbackService.feedInfo(feedbackDTO);
        model.addAttribute("feedBacks", feedbackDTOs);
        return "/user/feedbackDetail";
    }

    /**
     * 申诉详情
     * param feedbackDTO
     * param model
     * return
     */
    @RequestMapping(value = "/appealDetail.html")
    public String appealDetail(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                               @Valid UserFeedbackSearchDTO feedbackDTO, Model model) {
        UserFeedbackSearchDTO feedbackDTOs = userFeedbackService.feedInfo(feedbackDTO);
        model.addAttribute("feedBacks", feedbackDTOs);
        return "/user/appealDetail";
    }

    /**
     * 修改
     * param feedbackDTO
     * param model
     * return
     */
    @RequestMapping(value = "/feedbackUpdate.html")
    public String feedbackUpdate(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                 @Valid UserFeedbackSearchDTO feedbackDTO) {
        //获取报修信息
        String feedback = userFeedbackService.feedUpdate(user, feedbackDTO);
        return "redirect:../userFeed/feedbackManage.html?menuId=005700040000";
    }

    /**
     * 检查该用户是否已经变成业主
     * param feedbackDTO
     * param model
     * return
     */
    @RequestMapping(value = "/searchFeedback/{feedbackId}", produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public ApiResult searchFeedback(@PathVariable(value = "feedbackId") String feedbackId) {
        return userFeedbackService.searchFeedBack(feedbackId);
    }

    /**
     * 修改
     * param feedbackDTO
     * param model
     * return
     */
    @RequestMapping(value = "/appealUpdate.html")
    public String appealUpdate(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                               @Valid UserFeedbackSearchDTO feedbackDTO) {
        String feedback = userFeedbackService.feedUpdate(user, feedbackDTO);
        return "redirect:../userFeed/appealManage.html?menuId=005200010000";
    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                              @Valid UserFeedbackSearchDTO feedbackDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, @RequestParam String type) {
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            feedbackDTO.setRoleScopeList(roleScopeList);
            return userFeedbackService.exportExcel(user, feedbackDTO, httpServletResponse, type, httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * 获取意见反馈列表
     * param user
     * param feedbackDTO
     * param model
     * param webPage
     * return
     */
    @RequestMapping(value = "/appealManage.html")
    public String appealManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                               @Valid UserFeedbackSearchDTO feedbackDTO, Model model, WebPage webPage) {
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),feedbackDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != feedbackDTO.getScopeId() && !"".equals(feedbackDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), feedbackDTO.getScopeId(), feedbackDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        feedbackDTO.setRoleScopeList(roleScopeList);
        /* 意见反馈_memo:2 */
        //反馈类型(1金茂会员,2金茂质检,3微信)(1:APP,2:微信)
        if ("1".equals(feedbackDTO.getMemo())) {
            feedbackDTO.setFeedbackType("1");
        } else if ("2".equals(feedbackDTO.getMemo())) {
            feedbackDTO.setFeedbackType("3");
        }
        feedbackDTO.setMemo("1");
        //获取用户信息
        List<UserFeedbackSearchDTO> feedbackList = userFeedbackService.getFeedbackList(user, feedbackDTO, webPage);
        model.addAttribute("feedBacks", feedbackList);

        UserFeedbackSearchDTO feedbackDTOs = new UserFeedbackSearchDTO();
        //用户类型下拉框
        Map<String, String> typeMap = new LinkedHashMap<>();
        typeMap.put("0", "--请选择用户类型--");
        typeMap.put("1", "普通用户");
        typeMap.put("2", "同住人");
        typeMap.put("3", "业主");
        feedbackDTOs.setUserTypeMap(typeMap);
        //反馈状态下拉框
        Map<String, String> stateMap = new LinkedHashMap<>();
        stateMap.put("0", "--请选择反馈状态--");
        stateMap.put("1", "未处理");
        stateMap.put("2", "已处理");
        feedbackDTOs.setStateMap(stateMap);
        //来源下拉框
        Map<String, String> feedBackTypeMap = new LinkedHashMap<>();
        feedBackTypeMap.put("0", "--请选择来源--");
        feedBackTypeMap.put("1", "APP");
        feedBackTypeMap.put("2", "微信");
        feedbackDTOs.setResourceMap(feedBackTypeMap);
        model.addAttribute("feedbackMaps", feedbackDTOs);
        //正确回显反馈来源
        if ("1".equals(feedbackDTO.getFeedbackType())) {
            feedbackDTO.setMemo("1");
        } else if ("3".equals(feedbackDTO.getFeedbackType())) {
            feedbackDTO.setMemo("2");
        } else {
            feedbackDTO.setMemo("0");
        }
        model.addAttribute("feedBackSearch", feedbackDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel", feedbackList.size());
        return "/user/userAppealManage";
    }

    /**
     * 获取业主申诉信息列表
     */
    @RequestMapping(value = "/getOwnerAppealList.html")
    public ModelAndView getOwnerAppealList(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                           OwnerAuthenticationDTO ownerAuthenticationDTO,
                                           WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/user/OwnerAppealList");
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),ownerAuthenticationDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != ownerAuthenticationDTO.getCityId() && !"".equals(ownerAuthenticationDTO.getCityId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), ownerAuthenticationDTO.getCityId(), ownerAuthenticationDTO.getMenuId());
                modelAndView.addObject("projectList", projectList);
            }
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            ownerAuthenticationDTO.setRoleScopeList(roleScopeList);
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
            modelAndView.addObject("clientConfigList",clientConfigList);
            //检索参数回显
            modelAndView.addObject("ownerAuthenticationDTO",ownerAuthenticationDTO);
            //分页设置并回显
            webPage.setPageSize(20);
            List<OwnerAuthenticationDTO> list = userOwnerService.getOwnerAppealList(ownerAuthenticationDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //执行查询
            List<OwnerAuthenticationDTO> ownerAppealList = userOwnerService.getOwnerAppealList(ownerAuthenticationDTO, webPage);
            modelAndView.addObject("ownerAppealList",ownerAppealList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 获取业主申诉信息
     */
    @RequestMapping(value = "/getOwnerAppealInfo.html")
    public ModelAndView getOwnerAppealInfo(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                           OwnerAuthenticationDTO ownerAuthenticationDTO){
        ModelAndView modelAndView = new ModelAndView("/user/OwnerAppealInfo");
        try{
            OwnerAuthenticationDTO ownerAppealInfo = userOwnerService.getOwnerAppealInfo(ownerAuthenticationDTO);
            modelAndView.addObject("ownerAppealInfo",ownerAppealInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 更新业主申诉状态
     */
    @ResponseBody
    @RequestMapping(value = "/updateOwnerAppealState")
    public Map<String,Object> updateOwnerAppealState(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                                     OwnerAuthenticationDTO ownerAuthenticationDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            TimeUnit.SECONDS.sleep(5);
            //操作人
            ownerAuthenticationDTO.setModifyBy(user.getStaffName());
            int flag = userOwnerService.updateOwnerAppealState(ownerAuthenticationDTO);
            //0:操作成功！
            //1011:该业主申诉已被处理成功，无需重复点击！
            //1012:请求CRM
            //1021:该业主申诉已被处理成功，无法执行失败操作！
            resultMap.put("flag",flag);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /***
     * 用户申诉模块导出Excel
     */
    @ResponseBody
    @RequestMapping("/exportUserAppealListExcel")
    public String exportOwnerAppealListExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              @Valid OwnerAuthenticationDTO ownerAuthenticationDTO,
                              HttpServletResponse response,HttpServletRequest request){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            ownerAuthenticationDTO.setRoleScopeList(roleScopeList);
            userOwnerService.exportOwnerAppealListExcel(response,request,user,ownerAuthenticationDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

}
