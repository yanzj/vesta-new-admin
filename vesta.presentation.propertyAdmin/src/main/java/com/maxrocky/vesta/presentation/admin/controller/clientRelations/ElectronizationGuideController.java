package com.maxrocky.vesta.presentation.admin.controller.clientRelations;

import com.maxrocky.vesta.application.adminDTO.ElectronizationGuideDTO;
import com.maxrocky.vesta.application.adminDTO.GuideDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.ElectronizationGuideService;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/5/23.
 * 电子化指引
 */
@Controller
@RequestMapping(value = "/dlectronizationGuide")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ElectronizationGuideController {

    @Autowired
    private ElectronizationGuideService electronizationGuideService;
    @Autowired
    private AuthAgencyService authAgencyService;

    //初始化电子化指引页面
    @RequestMapping(value = "/initElectronizationGuide.html")
    public String initElectronizationGuide(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                           @Valid ElectronizationGuideDTO electronizationGuideDTO, Model model, WebPage webPage){
        //分页设置并回显
        webPage.setPageSize(20);
        List<ElectronizationGuideDTO> electronizationGuideSize = electronizationGuideService.getElectronizationGuideList(electronizationGuideDTO,null);
        List<ElectronizationGuideDTO> electronizationGuideDTOS = electronizationGuideService.getElectronizationGuideList(electronizationGuideDTO,webPage);
        webPage.setRecordCount(electronizationGuideSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
        if(function!=null){
            //校验是否有 搜索: qch40010128  添加电子化指引: qch40010129 修改: qch40010130 删除: qch40010131
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010128":
                        checkAuthFunctionDTO.setQch40010128("Y");
                        break;
                    case "QCH40010129":
                        checkAuthFunctionDTO.setQch40010129("Y");
                        break;
                    case "QCH40010130":
                        checkAuthFunctionDTO.setQch40010130("Y");
                        break;
                    case "QCH40010131":
                        checkAuthFunctionDTO.setQch40010131("Y");
                        break;
                }
            }
        }
        model.addAttribute("electronizationGuideDTO",electronizationGuideDTO);
        model.addAttribute("electronizationGuideDTOS",electronizationGuideDTOS);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/clientRelations/electronizationGuide/electronizationGuideList";
    }

    //新增or修改电子化指引
    @RequestMapping(value = "/addOrUpdateElectronizationGuide")
//    public String addOrUpdateElectronizationGuide(@RequestParam(value = "file", required = false) MultipartFile file,
    public String addOrUpdateElectronizationGuide(@RequestParam(value = "file") MultipartFile file,
                                                  @ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                                  @Valid GuideDTO guideDTO){
        if(null != guideDTO){
            //判断是新增还是修改有id 修改  没有id 新增
            if(!StringUtil.isEmpty(guideDTO.getId1())){
                electronizationGuideService.updateElectronizationGuide(guideDTO,file);
            }else {
                electronizationGuideService.saveElectronizationGuide(guideDTO,file);
            }
        }
        return "redirect:../dlectronizationGuide/initElectronizationGuide.html";
    }

    //删除电子化指引
    @ResponseBody
    @RequestMapping(value = "deleteElectronizationGuide")
    public Map<String,Object> deleteElectronizationGuide(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid GuideDTO guideDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            String res = electronizationGuideService.delElectronizationGuide(guideDTO);
            resultMap.put("error",res);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }
}
