package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.basic.DTO.AssessTempDTO;
import com.maxrocky.vesta.application.basic.inf.BasicService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.List;

/**
 * 考核模板
 * Created by Jason on 2017/6/15.
 */
@Controller
@RequestMapping(value = "assess")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class AssessmentTempController {
    @Autowired
    private BasicService basicService;
    @Autowired
    AuthAgencyService authAgencyService;

    /**
     * @return 考核模板管理页
     */
    @RequestMapping(value = "/assessTempManage.html")
    public String assessTempManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                   Model model, WebPage webPage, @Valid AssessTempDTO assessTempDTO) {
        List<AssessTempDTO> assessTempDTOList = basicService.getAssessTempList(assessTempDTO, webPage);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 sth40020046; //搜索  sth40020047; //下载模板  sth40020048; //导入模板  sth40020049; //导出Excel
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020046":
                        checkAuthFunctionDTO.setSth40020046("Y");
                        break;
                    case "STH40020047":
                        checkAuthFunctionDTO.setSth40020047("Y");
                        break;
                    case "STH40020048":
                        checkAuthFunctionDTO.setSth40020048("Y");
                        break;
                    case "STH40020049":
                        checkAuthFunctionDTO.setSth40020049("Y");
                        break;
                }
            }
        }
        model.addAttribute("assessTempList", assessTempDTOList);
        model.addAttribute("assessTemp", assessTempDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/assessTemp/assessTempManage";
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 考评模板下载
     */
    @RequestMapping(value = "/exportModel")
    @ResponseBody
    public String exportMechanismPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return basicService.exportModel(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * @param domain
     * @param httpServletRequest
     * @return 导入考核模板
     */
    @RequestMapping(value = "/importAssessTempExcel", method = RequestMethod.POST)
    public String importAssessTempExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @RequestParam(value = "domain") String domain, HttpServletRequest httpServletRequest, Model model) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            String flag = basicService.importAssessTempExcel(fis, domain, userInformationEntity);
            String result = "";
            if ("ok".equals(flag)) {
                result = "导入成功！";
            } else {
                result = flag;
            }
            httpServletRequest.getSession().setAttribute("result", result);

            //关闭流
            fis.close();
            model.addAttribute("domain", domain);
            return "redirect:../assess/assessTempManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../assess/assessTempManage.html";
        }
    }

    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportAssessmentTempExcel", method = RequestMethod.GET)
    public void exportAssessmentTempExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response, @Valid AssessTempDTO assessTempDTO) throws Exception {
        String fileName = "考评文档";
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
        String title = "考评管理文档";
        String[] headers = {"考核项目", "考核分数", "排序号", "考核内容", "等级", "扣分原则"};
        ServletOutputStream out = response.getOutputStream();
        basicService.exportAssessmentTempExcel(title, headers, out, assessTempDTO, userInformationEntity);
    }
}
