package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.contest.DTO.ContestListDTO;
import com.maxrocky.vesta.application.contest.DTO.ContestSearchDTO;
import com.maxrocky.vesta.application.contest.inf.ContestService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 安全大比武
 * Created by yuanyn on 2017/9/1.
 */
@Controller
@RequestMapping(value = "/contest")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ContestController {
    @Autowired
    ContestService contestService;

    @Autowired
    SecurityProjectService securityProjectService;

    /**
     * 检索条件查询隐患详情
     */
    @RequestMapping(value = "/queryContest.html")
    public String queryContest(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                               @Valid ContestSearchDTO contestSearchDTO) {
        Map<String, String> projectList = securityProjectService.getProject();
        model.addAttribute("projects", projectList);
        List<ContestListDTO> list = contestService.getContestList(contestSearchDTO, webPage);
        model.addAttribute("contests", list);
        model.addAttribute("contest", contestSearchDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel", list.size());

        return "/security/contest/contestList";
    }

    /**
     * Describe:安全app  导出EXCEL
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    @RequestMapping(value = "/contestExportExcel", method = RequestMethod.GET)
    public void readilyTakeExportExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response,
                                       @Valid ContestSearchDTO contestSearchDTO) throws Exception {
        String fileName = "安全大比武文档";
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
        String title = "安全大比武管理文档";
        String[] headers = {"项目公司", "创建人","创建时间","隐患图片","隐患描述"};
        ServletOutputStream out = response.getOutputStream();
        contestService.contestExcel(title, headers, out, contestSearchDTO,userInformationEntity);
    }
}