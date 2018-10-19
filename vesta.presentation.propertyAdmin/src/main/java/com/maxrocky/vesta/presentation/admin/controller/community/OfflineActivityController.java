package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.OfflineActivityDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.OfflineActivityService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 线下活动管理Controller
 * Created by WeiYangDong on 2017/8/17.
 */
@Controller
@RequestMapping(value = "/offlineActivity")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class OfflineActivityController {

    @Autowired
    OfflineActivityService offlineActivityService;
    @Autowired
    StaffUserService staffUserService;

    /**
     * Describe:获取线下活动列表
     * CreateBy:WeiYangDong_2017-08-21
     */
    @RequestMapping(value = "/getOfflineActivityList.html")
    public ModelAndView getOfflineActivityList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                               OfflineActivityDTO offlineActivityDTO,
                                               WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/OfflineActivityList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<OfflineActivityDTO> list = offlineActivityService.getOfflineActivityList(offlineActivityDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),offlineActivityDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            modelAndView.addObject("offlineActivityDTO",offlineActivityDTO);
            //执行查询
            List<OfflineActivityDTO> offlineActivityList = offlineActivityService.getOfflineActivityList(offlineActivityDTO, webPage);
            modelAndView.addObject("offlineActivityList",offlineActivityList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:编辑线下活动
     * CreateBy:WeiYangDong_2017-08-21
     */
    @RequestMapping(value = "/toEditOfflineActivity.html")
    public ModelAndView toEditOfflineActivity(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                              OfflineActivityDTO offlineActivityDTO){
        ModelAndView modelAndView = new ModelAndView("/community/OfflineActivityEdit");
        try {
            //获取线下活动详情
            OfflineActivityDTO offlineActivity = null;
            if (null != offlineActivityDTO.getActivityId() && !"".equals(offlineActivityDTO.getActivityId())){
                offlineActivity = offlineActivityService.getOfflineActivityById(offlineActivityDTO.getActivityId());
            }
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),offlineActivityDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            modelAndView.addObject("offlineActivity",offlineActivity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新线下活动
     * CreateBy:WeiYangDong_2017-08-21
     */
    @RequestMapping(value = "/saveOrUpdateOfflineActivity.html")
    public ModelAndView saveOrUpdateOfflineActivity(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                    OfflineActivityDTO offlineActivityDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../offlineActivity/getOfflineActivityList.html?menuId=005400050000");
        try{
            //设置操作人
            offlineActivityDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            offlineActivityService.saveOrUpdateOfflineActivity(offlineActivityDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除线下活动
     * CreateBy:WeiYangDong_2017-08-21
     */
    @ResponseBody
    @RequestMapping(value = "/toDeleteOfflineActivity")
    public Map<String,Object> toDeleteOfflineActivity(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                      OfflineActivityDTO offlineActivityDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            offlineActivityService.deleteOfflineActivity(offlineActivityDTO.getActivityId());
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:去抽奖
     * CreateBy:WeiYangDong_2017-08-23
     */
    @RequestMapping(value = "/toGoLuckDraw.html")
    public ModelAndView toGoLuckDraw(OfflineActivityDTO offlineActivityDTO){
        ModelAndView modelAndView = new ModelAndView("/community/LuckDraw");
        try{
            //获取线下活动详情
            OfflineActivityDTO offlineActivity = null;
            if (null != offlineActivityDTO.getActivityId() && !"".equals(offlineActivityDTO.getActivityId())){
                offlineActivity = offlineActivityService.getOfflineActivityById(offlineActivityDTO.getActivityId());
            }
            modelAndView.addObject("offlineActivity",offlineActivity);
            //获取活动签到记录
            List<String> activitySignList = offlineActivityService.getOfflineActivitySignByActivity(offlineActivityDTO.getActivityId());
            modelAndView.addObject("activitySignList",activitySignList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取线下活动签到记录列表
     * CreateBy:WeiYangDong_2017-08-21
     */
    @RequestMapping(value = "/getOfflineActivitySignList.html")
    public ModelAndView getOfflineActivitySignList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                               OfflineActivityDTO offlineActivityDTO,
                                               WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/OfflineActivitySignList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = offlineActivityService.getOfflineActivitySignList(offlineActivityDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            modelAndView.addObject("offlineActivityDTO",offlineActivityDTO);
            //执行查询
            List<Map<String, Object>> activitySignList = offlineActivityService.getOfflineActivitySignList(offlineActivityDTO, webPage);
            modelAndView.addObject("activitySignList",activitySignList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:活动抽奖
     * CreateBy:WeiYangDong_2017-08-24
     */
    @ResponseBody
    @RequestMapping(value = "/activityLuckDraw")
    public Map<String,Object> activityLuckDraw(OfflineActivityDTO offlineActivityDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            offlineActivityService.saveActivityPrizeInfo(offlineActivityDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /***
     * 线下活动参与记录导出Excel
     */
    @ResponseBody
    @RequestMapping("/exportActivitySignListExcel")
    public String exportActivitySignListExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                             @Valid OfflineActivityDTO offlineActivityDTO,
                                             HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限
//            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
//            offlineActivityDTO.setRoleScopeList(roleScopeList);
            offlineActivityService.exportActivitySignListExcel(response,request,offlineActivityDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

}
