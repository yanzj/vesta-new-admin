package com.maxrocky.vesta.presentation.admin.controller.sysmanage;

import com.maxrocky.vesta.application.DTO.MessageManageDTO;
import com.maxrocky.vesta.application.DTO.MessageTypeDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.inf.MessageManageService;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunmei on 2016/3/3.
 */
@Controller
@RequestMapping(value = "/message")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class MessageManageController {
    @Autowired
    private MessageManageService messageManageService;
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    @RequestMapping(value = "/messageManage.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid MessageManageDTO messageManageDTO,Model model,WebPage webPage){
        // 初始化 登陆人所负责范围
        messageManageDTO.setProjectId(userPropertystaff.getQueryScope());
        List<MessageManageDTO> messageManageList =messageManageService.MessageList(messageManageDTO,webPage);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaff.getProjectId());
        model.addAttribute("messageManage", messageManageList);
        model.addAttribute("projects", propertyCompanyMap);
        return "/sysmanage/messageManage";
    }

    /**
     * 初始化新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/CreateMessage.html")
    public String gotocreatActivities(@ModelAttribute("propertystaff") UserPropertyStaffEntity userProperty,@RequestParam String messageManageId,Model model){
        List<String> messageDepartment=messageManageService.getMessageDepartment(messageManageId);
        Map<String,String> checkedMap  =   new HashMap<>();
        if(messageDepartment!=null){
            messageDepartment.forEach(s -> checkedMap.put(s,s));
        }
        MessageManageDTO messageManageDTO=messageManageService.getMessages(messageManageId);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userProperty.getProjectId());
        List<MessageTypeDTO> MessageTypeDTOList = messageManageService.getMessageType();
        model.addAttribute("projects", propertyCompanyMap);
        model.addAttribute("MessageTypeDTOList", MessageTypeDTOList);
        model.addAttribute("checkedMap", checkedMap);
         model.addAttribute("messageManageDTO", messageManageDTO);
        return "/sysmanage/AddmessageManage";
    }

    /**
     * 新增
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/addMessage.html",method = RequestMethod.POST)
    public String creatActivities(@Valid MessageManageDTO messageManageDTO,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,Model model,WebPage webPage){
        messageManageService.addMessage(userPropertystaffEntit, messageManageDTO);
        return "redirect:/message/messageManage.html";
    }
}
