package com.maxrocky.vesta.presentation.admin.controller.VersionManagement;

import com.maxrocky.vesta.application.DTO.QualityAppVersionManageDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AppVersionManageService;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 质检APP版本管理
 * Created by Talent on 2016/11/3.
 */
@Controller
@RequestMapping(value = "/qualityAPPVersionManagement")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class QualityAPPVersionManagementController {
    @Autowired
    AppVersionManageService appVersionManageService;
    @Autowired
    AuthAgencyService authAgencyService;

    /**
     * 检索版本列表
     *
     * @param userInformationEntity
     * @param qualityAppVersionManageDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/appVersionManage.html", method = RequestMethod.GET)
    public String searchAppVersionManageList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid QualityAppVersionManageDTO qualityAppVersionManageDTO, Model model, WebPage webPage) {
        List<QualityAppVersionManageDTO> qualityAppVersionManageDTOs = appVersionManageService.getVersionListByType(qualityAppVersionManageDTO, webPage);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点  qch40010078   发布版本  qch40010080  导出Excel  qch40010081  修改  qch40010083   删除
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
        if(function!=null){
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010078":
                        checkAuthFunctionDTO.setQch40010078("Y");
                    case "QCH40010080":
                        checkAuthFunctionDTO.setQch40010080("Y");
                    case "QCH40010081":
                        checkAuthFunctionDTO.setQch40010081("Y");
                    case "QCH40010083":
                        checkAuthFunctionDTO.setQch40010083("Y");
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("appVersionDTOs", qualityAppVersionManageDTOs);
        model.addAttribute("appVersionDTO", qualityAppVersionManageDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel", qualityAppVersionManageDTOs.size());
        return "/appVersionManage/AppVersionManageList";
    }

    /**
     * 跳转到添加版本信息页面
     *
     * @param userInformationEntity
     * @param appVersionDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddVersion")
    public String gotoAddVersion(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid QualityAppVersionManageDTO appVersionDTO, Model model) {
        appVersionDTO.setCreateOn(DateUtils.format(DateUtils.getDate()));
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点  qch40010079; //发布版本-保存
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
        if(function!=null){
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010079":
                        checkAuthFunctionDTO.setQch40010079("Y");
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("appVersionDTO", appVersionDTO);
        return "/appVersionManage/AppVersionAdd";
    }

    /**
     * 添加版本信息
     *
     * @param
     * @param appVersionDTO
     * @return
     */
    @RequestMapping(value = "/versionAdd")
    public ModelAndView versionAdd(@Valid QualityAppVersionManageDTO appVersionDTO) {
        appVersionManageService.saveAppVersion(appVersionDTO);
        return new ModelAndView("redirect:../qualityAPPVersionManagement/appVersionManage.html");
    }

    /**
     * 跳转到版本修改页面
     *
     * @param userInformationEntity
     * @param appVersionDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUpdateVersion")
    public String gotoUpdateVersion(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid QualityAppVersionManageDTO appVersionDTO, Model model) {
        appVersionDTO = appVersionManageService.getVersionById(appVersionDTO.getAppVersionId());
        appVersionDTO.setModifyOn(DateUtils.format(DateUtils.getDate()));
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点  qch40010082; //修改 --保存
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
        if(function!=null){
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010082":
                        checkAuthFunctionDTO.setQch40010082("Y");
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("appVersionDTO", appVersionDTO);
        return "/appVersionManage/AppVersionUpdate";
    }

    /**
     * 版本修改
     *
     * @param
     * @param appVersionDTO
     * @return
     */
    @RequestMapping(value = "/versionUpdate")
    public ModelAndView versionUpdate(@Valid QualityAppVersionManageDTO appVersionDTO) {
        appVersionManageService.updateAppVersion(appVersionDTO);
        return new ModelAndView("redirect:../qualityAPPVersionManagement/appVersionManage.html");
    }

    /**
     * 删除版本信息
     *
     * @param userInformationEntity
     * @param appVersionId
     * @param model
     * @return
     */
    @RequestMapping(value = "/versionDel")
    public ModelAndView versionDel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid String appVersionId, Model model) {

        appVersionManageService.delAppVersion(appVersionId, userInformationEntity);
        return new ModelAndView("redirect:../qualityAPPVersionManagement/appVersionManage.html");
    }

    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcels")
    public void exportExcels(HttpServletRequest request, HttpServletResponse response, QualityAppVersionManageDTO qualityAppVersionManageDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "APP版本信息";
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
        String title = "APP版本信息EXCEL文档";
        String[] headers = {"编号","APP类型","系统类型", "版本名称", "版本号", "文件大小", "发布人", "发布时间", "下载地址"};

        ServletOutputStream out = response.getOutputStream();
        appVersionManageService.exportExcel(title, headers, out, qualityAppVersionManageDTO, webPage);
    }
}
