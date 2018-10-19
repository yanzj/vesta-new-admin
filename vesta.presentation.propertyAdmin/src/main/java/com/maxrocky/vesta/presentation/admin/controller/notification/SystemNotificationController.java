package com.maxrocky.vesta.presentation.admin.controller.notification;

import com.maxrocky.vesta.application.DTO.SystemNotificationDTO;
import com.maxrocky.vesta.application.inf.SystemNotificationService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Admin on 2016/5/18.
 */


@Controller
@RequestMapping(value = "/notification")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class SystemNotificationController {

    @Autowired
    SystemNotificationService systemNotificationService;

    /**
     * 系统通知管理
     * @param systemNotification
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/systemMessage.html")
    public String gotoSystemMessage(@Valid SystemNotificationDTO systemNotification,Model model,WebPage webPage){

        List<SystemNotificationDTO> systemNotificationList = systemNotificationService.querySystemNotification(systemNotification,webPage);

        model.addAttribute("systemNotification",systemNotificationList);
        model.addAttribute("systemNotificationDTO",systemNotification);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",systemNotificationList.size());
        return "/notification/SystemNotification";
    }

    /**
     * 获取系统通知详情
     * @param notificationId
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoEditNotification")
    public String editNotification(@RequestParam(required=false) String notificationId, Model model) {

        if(null != notificationId && !"".equals(notificationId) ){

            SystemNotificationDTO systemNotificationM = systemNotificationService.getSystemNotificationDTOById(notificationId);
            model.addAttribute("systemNotification",systemNotificationM);
        }
        return "/notification/NotificationEdit";
    }

    /**
     * 系统通知列表
     * @param systemNotification
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/systemMessagelists.html")
    public String gotoSystemMessageLists(@Valid SystemNotificationDTO systemNotification,Model model,WebPage webPage){
        systemNotification.setNotificationStatus("10001");
        List<SystemNotificationDTO> systemNotificationList = systemNotificationService.querySystemNotification(systemNotification,webPage);
        model.addAttribute("systemNotification",systemNotificationList);
        return "/notification/NotificationList";
    }

    /**
     * 系统通知详情
     * @param notificationId
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoNotificationDetail")
    public String gotoNotificationDetail(@Valid String notificationId,Model model){

        SystemNotificationDTO systemNotificationM = systemNotificationService.getSystemNotificationDTOById(notificationId);
        model.addAttribute("systemNotification", systemNotificationM);

        return "/notification/NotificationDetail";
    }

    /**
     * 新建系统通知信息
     * @param systemNotification
     * @param userProperty
     * @return
     */
    @RequestMapping(value = "/addNotificationDetail")
    public ModelAndView addNotificationDetail(@Valid SystemNotificationDTO systemNotification,@ModelAttribute("propertystaff")UserPropertyStaffEntity userProperty){

        // ID 为空，新增数据
        if( null == systemNotification.getNotificationId() || "".equals(systemNotification.getNotificationId())) {
            systemNotificationService.addSystemNotification(systemNotification, userProperty);
        }else{
            //更改数据
            systemNotificationService.editSystemNotification(systemNotification, userProperty);
        }

        return new ModelAndView("redirect:../notification/systemMessage.html");
    }


    /**
     * 删除系统通知信息
     * @param notificationdetailId
     * @param userProperty
     * @return
     */
    @RequestMapping(value = "/removeNotificationDetail")
    public ModelAndView removeNotificationDetail(@RequestParam String notificationdetailId,@ModelAttribute("propertystaff")UserPropertyStaffEntity userProperty){

        systemNotificationService.removeSystemNotification(notificationdetailId,userProperty);

        return new ModelAndView("redirect:../notification/systemMessage.html");
    }


    /**
     * 修改通知
     * @param notificationId
     * @param notificationStatus
     * @param userProperty
     * @return
     */
    @RequestMapping(value = "/editnotificationstatus")
    public ModelAndView editNotificationStatus(@RequestParam String notificationId ,String notificationStatus,@ModelAttribute("propertystaff")UserPropertyStaffEntity userProperty) {

        SystemNotificationDTO systemNotification = systemNotificationService.getSystemNotificationDTOById(notificationId);
        systemNotification.setNotificationStatus(notificationStatus);
        systemNotificationService.editSystemNotification(systemNotification, userProperty);

        return new ModelAndView("redirect:../notification/systemMessage.html");
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
                              @Valid SystemNotificationDTO systemNotification,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            return systemNotificationService.exportExcel(user,systemNotification,httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

}
