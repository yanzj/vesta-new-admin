package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.nursery.DTO.NurseryDTO;
import com.maxrocky.vesta.application.nursery.inf.NurseryService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 苗木管理Controller
 * Created by yuanyn on 2017/6/13.
 */
@Controller
@RequestMapping(value = "/nursery")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class NurseryController {

    @Autowired
    private NurseryService nurserycService;


    /**
     * 获取苗木详情
     */
    @RequestMapping(value = "getNurseryList.html")
    public String getNurseryList(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertyStaffEntity,Model model,WebPage webPage){
        //分页设置并回显
        webPage.setPageSize(20);
        List<NurseryDTO> list = nurserycService.getNurseryList(null);
        webPage.setRecordCount(list.size());
        List<NurseryDTO> nurseryDTO = nurserycService.getNurseryList(webPage);
        model.addAttribute("nurserys",nurseryDTO);
        model.addAttribute("webPage", webPage);
        return "/security/nursery/nurseryList";
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 苗木模板下载
     */
    @RequestMapping(value = "/exportNurseryModel")
    @ResponseBody
    public String exportNurseryModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return nurserycService.exportNurseryModel(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * 苗木模板导入
     */
    @RequestMapping(value = "importNurseryExcel")
    public String importNurseryExcel(@ModelAttribute("authPropertystaff") UserPropertyStaffEntity userPropertyStaffEntity, HttpServletRequest httpServletRequest){
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            String fileName = file.getOriginalFilename();
            //POI:得到解析Excel的实体集合
            String result = nurserycService.importNurseryExcel(fis);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
            return "redirect:../nursery/getNurseryList.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../nursery/getNurseryList.html";
        }
    }

    /**
     * 批量上传图片
     */
    @RequestMapping(value = "uploadNurseryImage")
    public String uploadNurseryImage(@ModelAttribute("authPropertystaff")UserPropertyStaffEntity userPropertystaff, Model model,
                                     @RequestParam(value = "reviewImgFile",required = false) MultipartFile[] reviewImgFile){
        nurserycService.uploadNurseryImage(reviewImgFile);
        return "redirect:../nursery/getNurseryList.html";
    }
}
