package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.DailyPatrolInspectionDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.readilyTake.DTO.*;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.readilyTake.inf.ReadilyTakeService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 随手拍 管理平台Controller
 * Created by Magic on 2017/6/8
 */
@Controller
@RequestMapping(value = "/readilyTake")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ReadilyTakeController {
    @Autowired
    private SecurityProjectService securityProjectService;

    @Autowired
    private ReadilyTakeService readilyTakeService;

    @Autowired
    private AuthAgencyService authAgencyService;
    /**
     * Describe:安全app 按条件下载随手拍数据
     * CreateBy:Magic
     * CreateOn:2017-06-06
     */
    @RequestMapping(value = "/queryReadilyTake.html")
    public String dailyPatrolInspection(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                        @Valid ReadilyTakeConditionDTO readilyTakeConditionDTO) {
        //增加安全线校验
        Map<String, String> projectList = securityProjectService.getProjectByUser(userInformationEntity);
        model.addAttribute("projects", projectList);
        List<String> angencyIdList=new ArrayList<>();
        for (String key : projectList.keySet()) {
            angencyIdList.add(key);
        }
        List<ReadilyTakeListDTO> list = readilyTakeService.getReadilyTakeList(readilyTakeConditionDTO, webPage, userInformationEntity,angencyIdList);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有  sth40020050 //搜索  sth40020051 //导出Excel   sth40020052 //详情
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020050":
                        checkAuthFunctionDTO.setSth40020050("Y");
                        break;
                    case "STH40020051":
                        checkAuthFunctionDTO.setSth40020051("Y");
                        break;
                    case "STH40020052":
                        checkAuthFunctionDTO.setSth40020052("Y");
                        break;
                }
            }
        }
        model.addAttribute("problems", list);
        model.addAttribute("problem", readilyTakeConditionDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",list.size());
        model.addAttribute("function",checkAuthFunctionDTO);

        return "/security/readilyTake/readilyTakeList";
    }

    /**
     * Describe:安全app  导出EXCEL
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    @RequestMapping(value = "/readilyTakeExportExcel", method = RequestMethod.GET)
    public void readilyTakeExportExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response,
                                       @Valid ReadilyTakeConditionDTO readilyTakeConditionDTO) throws Exception {
        Map<String, String> projectList = securityProjectService.getProjectByUser(userInformationEntity);
        List<String> angencyIdList=new ArrayList<>();
        for (String key : projectList.keySet()) {
            angencyIdList.add(key);
        }
        String fileName = "随手拍文档";
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
        String title = "随手拍管理文档";
        String[] headers = {"项目公司", "创建人", "创建时间", "检查部位", "严重等级", "整改状态"};
        ServletOutputStream out = response.getOutputStream();
        readilyTakeService.readilyTakeExcel(title, headers, out, readilyTakeConditionDTO,userInformationEntity,angencyIdList);

    }

    /**
     * Describe:安全app  整改详情页
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    @RequestMapping(value = "/readilyTakeDetails.html")
    public String getReadilyTakeDetails(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                        @Valid ReadilyTakeConditionDTO readilyTakeConditionDTO) {
        //获取随手拍详情
        ReadilyTakeDetailDTO readilyTakeDetailDTO = readilyTakeService.getReadilyTake(readilyTakeConditionDTO.getPatId());
        //查看权限
        ReadilyTakeRoleDTO readilyTakeRoleDTO = readilyTakeService.getReadilyTakeRole(userInformationEntity.getStaffId(),readilyTakeConditionDTO.getProjectId());
        model.addAttribute("readilyTake",readilyTakeDetailDTO);//随手拍详情
        model.addAttribute("role",readilyTakeRoleDTO);//判断该角色有没有  操作权限
        return "/security/readilyTake/readilyTakeDetail";
    }

    /**
     * Describe:安全app  跳转到整改单修改页面
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    @RequestMapping(value = "/rectifyReadilyTake.html")
    public String rectifyReadilyTake(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                                     @RequestParam String patId){
        ReadilyTakeDetailDTO readilyTakeDetailDTO = readilyTakeService.getReadilyTake(patId);
        model.addAttribute("readilyTake",readilyTakeDetailDTO);
        return "/security/readilyTake/readilyTakeEdit";
    }

    /**
     * Describe:安全app  整改单状态  修改
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    @RequestMapping(value = "/updateReadilyTake")
    public ModelAndView updateReadilyTake(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, Model model,
                                          @RequestParam String patId, @RequestParam String state,String supplementaryDescription){

        readilyTakeService.updateReadilyTakeRole(patId,state,supplementaryDescription);
        return new ModelAndView("redirect:../readilyTake/queryReadilyTake.html");
    }

    /**
     * Describe:安全app  整改单状态  修改
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    @RequestMapping(value = "/updateRectifyReadilyTake")
    public ModelAndView updateRectifyReadilyTake(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, Model model,
                                                 @RequestParam(value = "reviewImgFile",required = false) MultipartFile[] reviewImgFile,
                                                 ReadilyTakeRectifyDTO readilyTakeRectifyDTO){

        readilyTakeService.updateReadilyTakeRole(readilyTakeRectifyDTO.getPatId(),readilyTakeRectifyDTO.getState(),"");
        readilyTakeService.updateReadilyTake(readilyTakeRectifyDTO,reviewImgFile);
        return new ModelAndView("redirect:../readilyTake/queryReadilyTake.html");
    }


}
