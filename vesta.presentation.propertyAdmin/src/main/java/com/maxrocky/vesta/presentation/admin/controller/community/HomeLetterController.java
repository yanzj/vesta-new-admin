package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.HomeLetterDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.HomeLetterService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 家书管理Controller
 * Created by WeiYangDong on 2017/5/16.
 */
@Controller
@RequestMapping(value = "/homeLetter")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class HomeLetterController {

    @Autowired
    HomeLetterService homeLetterService;
    @Autowired
    StaffUserService staffUserService;

    /**
     * Describe:获取工程进展列表
     * CreateBy:WeiYangDong_2017-05-17
     */
    @RequestMapping(value = "/getEngineeringProgressList.html")
    public ModelAndView getEngineeringProgressList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                          HomeLetterDTO homeLetterDTO,
                                          WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/EngineeringProgressList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = homeLetterService.getEngineeringProgressList(homeLetterDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),homeLetterDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != homeLetterDTO.getCityId() && !"".equals(homeLetterDTO.getCityId())){
                //城市不为空,回显项目列表
                List<EngineeringProjectEntity> engineeringProjectList = homeLetterService.getEngineeringProjectList(homeLetterDTO);
                modelAndView.addObject("engineeringProjectList",engineeringProjectList);
            }
            modelAndView.addObject("homeLetterDTO",homeLetterDTO);
            //执行检索
            List<Map<String, Object>> engineeringProgressList = homeLetterService.getEngineeringProgressList(homeLetterDTO, webPage);
            modelAndView.addObject("engineeringProgressList",engineeringProgressList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:编辑工程进展
     * CreateBy:WeiYangDong_2017-05-17
     */
    @RequestMapping(value = "/toEditEngineeringProgress.html")
    public ModelAndView toEditEngineeringProgress(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                  HomeLetterDTO homeLetterDTO){
        ModelAndView modelAndView = new ModelAndView("/community/EngineeringProgressEdit");
        try {
            //获取工程进展详情
            EngineeringProgressEntity engineeringProgressEntity = null;
            if (null != homeLetterDTO.getEngineeringProgressId() && !"".equals(homeLetterDTO.getEngineeringProgressId())){
                engineeringProgressEntity = homeLetterService.getEngineeringProgressById(homeLetterDTO.getEngineeringProgressId());
            }
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),homeLetterDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != engineeringProgressEntity && null != engineeringProgressEntity.getCityId() && !"".equals(engineeringProgressEntity.getCityId())){
                homeLetterDTO.setCityId(engineeringProgressEntity.getCityId());
                //城市不为空,回显项目列表
                List<EngineeringProjectEntity> engineeringProjectList = homeLetterService.getEngineeringProjectList(homeLetterDTO);
                modelAndView.addObject("engineeringProjectList",engineeringProjectList);
            }
            modelAndView.addObject("engineeringProgress",engineeringProgressEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新工程进展
     * CreateBy:WeiYangDong_2017-05-17
     */
    @RequestMapping(value = "/saveOrUpdateEngineeringProgress.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateEngineeringProgress(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                        @RequestParam(value = "engineeringProgressSignImgFile",required = false) MultipartFile engineeringProgressSignImgFile,
                                                        HomeLetterDTO homeLetterDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../homeLetter/getEngineeringProgressList.html?menuId=006100020000");
        try{
            //设置操作人
            homeLetterDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            //设置信息标识图
            if (null != engineeringProgressSignImgFile){
                String engineeringProgressSignImgUrl = imgUpload(engineeringProgressSignImgFile);
                if (engineeringProgressSignImgUrl.lastIndexOf("/") != engineeringProgressSignImgUrl.length()-1){
                    homeLetterDTO.setEngineeringProgressSignImgUrl(engineeringProgressSignImgUrl);
                }
            }
            homeLetterService.saveOrUpdateEngineeringProgress(homeLetterDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除工程进展
     * CreateBy:WeiYangDong_2017-08-06
     */
    @ResponseBody
    @RequestMapping(value = "/toDeleteEngineeringProgress")
    public Map<String,Object> toDeleteEngineeringProgress(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                          HomeLetterDTO homeLetterDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            homeLetterService.deleteEngineeringProgress(homeLetterDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:编辑工程项目
     * CreateBy:WeiYangDong_2017-06-08
     */
    @RequestMapping(value = "/toEditEngineeringProject.html")
    public ModelAndView toEditEngineeringProject(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                 HomeLetterDTO homeLetterDTO){
        ModelAndView modelAndView = new ModelAndView("/community/EngineeringProjectEdit");
        try{
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),homeLetterDTO.getMenuId());
            modelAndView.addObject("city", cityList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新工程项目
     * CreateBy:WeiYangDong_2017-06-08
     */
    @RequestMapping(value = "/saveOrUpdateEngineeringProject.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateEngineeringProject(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                               HomeLetterDTO homeLetterDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../homeLetter/getEngineeringProgressList.html?menuId=006100020000");
        try{
            //设置操作人
            homeLetterDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            homeLetterService.saveOrUpdateEngineeringProject(homeLetterDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取工程项目列表
     * CreateBy:WeiYangDong_2017-06-08
     */
    @ResponseBody
    @RequestMapping(value = "/getEngineeringProjectList")
    public Map<String,Object> getEngineeringProjectList(HomeLetterDTO homeLetterDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            List<EngineeringProjectEntity> engineeringProjectList = homeLetterService.getEngineeringProjectList(homeLetterDTO);
            resultMap.put("engineeringProjectList",engineeringProjectList);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    public String imgUpload(MultipartFile multipartFile){
        String imgUrl = "";
        try{
            //处理图片上传
            if (null != multipartFile){
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                imgUrl = imgService.uploadAdminImage(multipartFile, ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgUrl;
    }
}
