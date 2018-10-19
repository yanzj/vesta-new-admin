package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.knowledge.DTO.KnowledgeDTO;
import com.maxrocky.vesta.application.knowledge.inf.KnowledgeService;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 知识库管理
 * Created by yuanyn on 2017/6/5.
 */
@Controller
@RequestMapping(value = "/knowledge")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class KnowledgeController {
    @Autowired
    KnowledgeService knowledgeService;
    @Autowired
    AuthAgencyService authAgencyService;


    /**
     * 获取知识库模块列表
     */
    @RequestMapping(value = "/initKnowledgeList.html")
    public String knowledgeModuleManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                        @Valid KnowledgeDTO knowledgeDTO, Model model, WebPage webPage) {
        knowledgeDTO.setGraded("1");
        List<KnowledgeDTO> knowledgeDTOs = knowledgeService.getKnowledgeFiles(knowledgeDTO, webPage);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 sth40020035; //模块搜索  sth40020036; //添加模块  sth40020037; //模块删除  sth40020038; //目录管理
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020035":
                        checkAuthFunctionDTO.setSth40020035("Y");
                        break;
                    case "STH40020036":
                        checkAuthFunctionDTO.setSth40020036("Y");
                        break;
                    case "STH40020037":
                        checkAuthFunctionDTO.setSth40020037("Y");
                        break;
                    case "STH40020038":
                        checkAuthFunctionDTO.setSth40020038("Y");
                        break;
                }
            }
        }
        model.addAttribute("knowledgeList", knowledgeDTOs);
        model.addAttribute("knowledge", knowledgeDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/knowledge/knowledgeModuleManage";
    }

    /**
     * 获取知识库目录列表
     */
    @RequestMapping(value = "/cataKnowledgeList.html")
    public String kowledgeCatalogManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                        @Valid KnowledgeDTO knowledgeDTO, Model model, WebPage webPage) {
        List<KnowledgeDTO> knowledgeDTOs = knowledgeService.getKnowledgeFiles(knowledgeDTO, webPage);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 sth40020039; //目录搜索  sth40020040; //添加目录  sth40020041; //目录删除  sth40020042; //文档管理
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020039":
                        checkAuthFunctionDTO.setSth40020039("Y");
                        break;
                    case "STH40020040":
                        checkAuthFunctionDTO.setSth40020040("Y");
                        break;
                    case "STH40020041":
                        checkAuthFunctionDTO.setSth40020041("Y");
                        break;
                    case "STH40020042":
                        checkAuthFunctionDTO.setSth40020042("Y");
                        break;
                }
            }
        }
        model.addAttribute("knowledgeList", knowledgeDTOs);
        model.addAttribute("knowledge", knowledgeDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/knowledge/knowledgeCatalogManage";
    }

    /**
     * 获取知识库文件列表
     */
    @RequestMapping(value = "/fileKnowledgeList.html")
    public String knowledgeFileManage(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                      @Valid KnowledgeDTO knowledgeDTO, Model model, WebPage webPage) {
        List<KnowledgeDTO> knowledgeDTOs = knowledgeService.getKnowledgeFiles(knowledgeDTO, webPage);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 sth40020043; //文档搜索  sth40020044; //上传文档  sth40020045; //文档删除
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020043":
                        checkAuthFunctionDTO.setSth40020043("Y");
                        break;
                    case "STH40020044":
                        checkAuthFunctionDTO.setSth40020044("Y");
                        break;
                    case "STH40020045":
                        checkAuthFunctionDTO.setSth40020045("Y");
                        break;
                }
            }
        }
        model.addAttribute("knowledgeList", knowledgeDTOs);
        model.addAttribute("knowledge", knowledgeDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/knowledge/knowledgeFileManage";
    }

    /**
     * 新增模块
     */
    @RequestMapping(value = "/addKnowledgeModule.html")
    public String addKnowledgeModule(@Valid KnowledgeDTO knowledgeDTO, Model model){

        if (!StringUtil.isEmpty(knowledgeDTO.getFileName())){
            knowledgeDTO.setGraded("1");
            knowledgeDTO.setState("1");
            knowledgeService.addKnowledgeFile(knowledgeDTO);
            model.addAttribute("currentId","");
            model.addAttribute("parentId","");
        }
        return "redirect:../knowledge/initKnowledgeList.html";
    }

    /**
     * 新增目录
     */
    @RequestMapping(value = "/addKnowledgeCatalog.html")
    public String addKnowledgeCatalog(@Valid KnowledgeDTO knowledgeDTO, Model model) {
        if (!StringUtil.isEmpty(knowledgeDTO.getFileName())) {
            KnowledgeDTO knowledgeDTO1 = new KnowledgeDTO();
            knowledgeDTO1.setGraded("2");
            knowledgeDTO1.setState("1");
            knowledgeDTO1.setParentId(knowledgeDTO.getCurrentId());
            knowledgeDTO1.setFileName(knowledgeDTO.getFileName());
            knowledgeService.addKnowledgeFile(knowledgeDTO1);
        }
        model.addAttribute("currentId", knowledgeDTO.getCurrentId());
        return "redirect:../knowledge/cataKnowledgeList.html";
    }

    /**
     * 上传文档
     */
    @RequestMapping(value = "/uploadKnowledge.html")
    public String uploadknowledge(@RequestParam(value = "file", required = false) MultipartFile file,
                                  HttpServletRequest request, Model model,
                                  @Valid KnowledgeDTO knowledgeDTO1) {
        KnowledgeDTO knowledgeDTO = new KnowledgeDTO();
        //文件大小转换为M时 保留小数点后2位数字
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        //文件访问路径
        String path = ImageConfig.PIC_OSS_ADMIN_URL + "safetyfile";
        //文件上传路径
        String knowledgeFileUrl = ImageConfig.PIC_SERVER_ADMIN_URL+"safetyfile";
        String fileName = file.getOriginalFilename();
        //把FileName可能存在的空格改为下划线 非法字符改为空
        String fileNamereplace = fileName.replaceAll("\\s", "_").replaceAll("[`~!@#$^&*()+=|{}':;',\\[\\]<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]", "");
        File targetFile = new File(knowledgeFileUrl, fileNamereplace);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //上传
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String size = "";
        if ((int) (targetFile.length() / 1024 / 1024) > 0) {
            size = decimalFormat.format((double) (targetFile.length() / 1024 / 1024f)) + 1 + "M";
        } else {
            size = (int) (targetFile.length() / 1024) + 1 + "K";
        }
        knowledgeDTO.setGraded("3");
        knowledgeDTO.setState("1");
        knowledgeDTO.setParentId(knowledgeDTO1.getCurrentId());
        knowledgeDTO.setFileName(fileName);
        knowledgeDTO.setPath(path + "/" + fileNamereplace);
        knowledgeDTO.setSize(size);
        knowledgeService.addKnowledgeFile(knowledgeDTO);
        model.addAttribute("currentId", knowledgeDTO1.getCurrentId());
        model.addAttribute("parentId", knowledgeDTO1.getParentId());

        return "redirect:../knowledge/fileKnowledgeList.html";
    }

    /**
     * 删除模块
     */
    @RequestMapping(value = "/deleteKnowledgeModule.html")
    public String delKnowledgeModule(@Valid KnowledgeDTO knowledgeDTO,Model model) {
        try {
            knowledgeService.deleteKnowledge(knowledgeDTO.getCurrentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("currentId","");
        model.addAttribute("parentId","");
        return "redirect:../knowledge/initKnowledgeList.html";
    }

    /**
     * 删除目录
     */
    @RequestMapping(value = "/deleteKnowledgeCatalog.html")
    public String delKnowledgeCatalog(@Valid KnowledgeDTO knowledgeDTO,Model model) {
        try {
            knowledgeService.deleteKnowledge(knowledgeDTO.getCurrentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("currentId", knowledgeDTO.getParentId());
        model.addAttribute("parentId", "");
        return "redirect:../knowledge/cataKnowledgeList.html";
    }

    /**
     * 删除文档
     */
    @RequestMapping(value = "/deleteKnowledgeFile.html")
    public String delKnowledgeFile(@Valid KnowledgeDTO knowledgeDTO,Model model) {
        try {
            knowledgeService.deleteKnowledge(knowledgeDTO.getCurrentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("currentId", knowledgeDTO.getParentId());
        model.addAttribute("parentId", knowledgeDTO.getGrandId());
        return "redirect:../knowledge/fileKnowledgeList.html";
    }
}
