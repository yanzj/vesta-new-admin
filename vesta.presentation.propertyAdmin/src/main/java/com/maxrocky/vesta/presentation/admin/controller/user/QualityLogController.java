package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.QualityAccessLogDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by jia on 2016/11/17.
 */

@Controller
@RequestMapping(value = "/qualityLog")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class QualityLogController {
    @Autowired
    QualityLogService qualityLogService;
    @Autowired
    HouseProjectService houseProjectService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    StaffUserService staffUserService;

    /**
     * 用户访问日志管理页
     */
    @RequestMapping(value = "/qualityAccessLog.html")
    public String qualityAccessLog(@Valid QualityAccessLogDTO qualityAccessLogDTO, Model model, WebPage webPage, HttpServletRequest request) {
        List<QualityAccessLogDTO> qualityAccessLogDTOList = qualityLogService.getQualityAccessLogList(qualityAccessLogDTO, webPage);
        model.addAttribute("logList", qualityAccessLogDTOList);
        model.addAttribute("sysLog", qualityAccessLogDTO);
        return "log/QualityAccessLog";
    }

    /**
     * 导出用户访问EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportAccessExcels", method = RequestMethod.GET)
    public void exportExcels(HttpServletRequest request, HttpServletResponse response,@Valid QualityAccessLogDTO qualityAccessLogDTO) throws Exception {
        String fileName = "访问日志";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "访问日志EXCEL文档";
        String[] headers = {"编号", "访问时间", "用户名", "用户昵称", "手机号", "访问来源", "访问内容", "IP地址"};

        ServletOutputStream out = response.getOutputStream();
        qualityLogService.exportAccessExcel(title, headers, out,qualityAccessLogDTO);
    }
}
