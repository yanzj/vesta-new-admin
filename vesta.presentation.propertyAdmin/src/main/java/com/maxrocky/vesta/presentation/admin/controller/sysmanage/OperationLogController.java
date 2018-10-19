package com.maxrocky.vesta.presentation.admin.controller.sysmanage;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.DTO.OperationLogSearchDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
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
@RequestMapping(value = "/operation")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;
    @RequestMapping(value = "/OperationLog.html")
    public String gotoClickNums(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid OperationLogSearchDTO operationLogSearchDTO ,Model model,WebPage page){
        // 查询后台核心日志信息
        // 初始化 登陆人所负责范围
        operationLogSearchDTO.setQueryScope(userPropertystaff.getQueryScope());
        List<OperationLogDTO> operationLogList= operationLogService.OperationLogManage(operationLogSearchDTO,page);
        model.addAttribute("operationLogList",operationLogList);
        model.addAttribute("operationLogSearchDTO",operationLogSearchDTO);
        return "/sysmanage/OperationLog";
    }
}
