package com.maxrocky.vesta.presentation.admin.controller.sysmanage;

import com.maxrocky.vesta.application.DTO.LoginLogDTO;
import com.maxrocky.vesta.application.DTO.LoginLogSearchDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.inf.LoginLogService;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
@Controller
@RequestMapping(value = "/log")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class LoginLogController {
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    @Autowired
    private LoginLogService loginLogService;
    /*    *//**
     * 初始化登录日志列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/LoginLog.html")

    public String gotoClickNums(@ModelAttribute("propertystaff") UserPropertyStaffEntity userProperty,@Valid LoginLogSearchDTO loginLogSearchDTO ,Model model,WebPage page){
        // 初始化 登陆人所负责范围
        loginLogSearchDTO.setQueryScope(userProperty.getQueryScope());
        // 查询登录日志
        List<LoginLogDTO> loginLogList= loginLogService.LoginLogManage(loginLogSearchDTO, page);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userProperty.getProjectId());
        model.addAttribute("loginLogList",loginLogList);
        model.addAttribute("loginLogMap",propertyCompanyMap);
        model.addAttribute("loginLogSearchDTO",loginLogSearchDTO);
        return "/sysmanage/loginLog";
    }
}
