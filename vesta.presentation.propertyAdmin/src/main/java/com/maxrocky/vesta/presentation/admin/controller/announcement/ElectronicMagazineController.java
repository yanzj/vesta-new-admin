package com.maxrocky.vesta.presentation.admin.controller.announcement;

import com.maxrocky.vesta.application.DTO.ElectronicMagazineDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.ElectronicMagazineService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.DefaultConfigRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电子杂志Controller
 * Created by WeiYangDong on 2017/9/22.
 */
@Controller
@RequestMapping(value = "/electronicMagazine")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class ElectronicMagazineController {

    @Autowired
    ElectronicMagazineService electronicMagazineService;
    @Autowired
    StaffUserService staffUserService;
    @Autowired
    DefaultConfigService defaultConfigService;

    /**
     * Describe:新增或编辑电子杂志
     * CreateBy:WeiYangDong_2017-09-25
     */
    @RequestMapping(value = "/toEditElectronicMagazineInfo.html")
    public ModelAndView toEditElectronicMagazineInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                   ElectronicMagazineDTO electronicMagazineDTO){
        ModelAndView modelAndView = new ModelAndView("/announcement/ElectronicMagazineEdit");
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(roleScopeList);
            modelAndView.addObject("clientConfigList",clientConfigList);
            //获取工程进展详情
            ElectronicMagazineDTO electronicMagazineInfo = null;
            if (null != electronicMagazineDTO.getId() && !"".equals(electronicMagazineDTO.getId())){
                electronicMagazineInfo = electronicMagazineService.getElectronicMagazineById(electronicMagazineDTO.getId());
            }
            modelAndView.addObject("electronicMagazineInfo",electronicMagazineInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取电子杂志列表
     * CreateBy:WeiYangDong_2017-09-25
     */
    @RequestMapping(value = "/getElectronicMagazineList.html")
    public ModelAndView getElectronicMagazineList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                ElectronicMagazineDTO electronicMagazineDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/announcement/ElectronicMagazineList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<ElectronicMagazineDTO> list = electronicMagazineService.getElectronicMagazineList(electronicMagazineDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            modelAndView.addObject("electronicMagazineDTO",electronicMagazineDTO);
            //获取电子杂志列表
            List<ElectronicMagazineDTO> electronicMagazineList = electronicMagazineService.getElectronicMagazineList(electronicMagazineDTO, webPage);
            modelAndView.addObject("electronicMagazineList",electronicMagazineList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新电子杂志
     * CreateBy:WeiYangDong_2017-09-25
     */
    @RequestMapping(value = "/saveOrUpdateElectronicMagazineInfo.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateElectronicMagazineInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                         ElectronicMagazineDTO electronicMagazineDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../electronicMagazine/getElectronicMagazineList.html?menuId=006900020000");
        try{
            //设置操作人
            electronicMagazineDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            electronicMagazineService.saveOrUpdateElectronicMagazineInfo(electronicMagazineDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除电子杂志(物理删除)
     * CreateBy:WeiYangDong_2017-09-25
     */
    @ResponseBody
    @RequestMapping(value = "/deleteElectronicMagazineInfo")
    public Map<String,Object> deleteElectronicMagazineInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                         ElectronicMagazineDTO electronicMagazineDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            electronicMagazineService.deleteElectronicMagazineInfo(electronicMagazineDTO.getId());
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:图片上传
     * CreateBy:WeiYangDong_2017-09-28
     */
    @ResponseBody
    @RequestMapping(value = "/imgUpload")
    public Map<String,Object> imgUpload(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                        MultipartFile multipartFile) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String imgUrl = electronicMagazineService.imgUpload(multipartFile);
            resultMap.put("imgUrl",imgUrl);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

}
