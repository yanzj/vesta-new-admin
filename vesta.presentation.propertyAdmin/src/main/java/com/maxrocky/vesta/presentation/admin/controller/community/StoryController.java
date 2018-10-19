package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.StoryDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.StoryService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
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
 * @author WeiYangDong
 * @date 2018/3/22 16:51
 * @deprecated 故事荟功能模块Controller
 */
@Controller
@RequestMapping(value = "/story")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class StoryController {

    @Autowired
    StaffUserService staffUserService;
    @Autowired
    StoryService storyService;

    /**
     * Describe:获取故事荟列表
     * CreateBy:WeiYangDong_2018-03-23
     */
    @RequestMapping(value = "/getStoryInfoList.html")
    public ModelAndView getStoryInfoList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                         StoryDTO storyDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/StoryInfoList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            storyDTO.setRoleScopeList(roleScopeList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<StoryDTO> list = storyService.getStoryInfoList(storyDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),storyDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != storyDTO.getCityId() && !"".equals(storyDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), storyDTO.getCityId(), storyDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("storyDTO",storyDTO);
            //获取故事荟列表
            List<StoryDTO> storyInfoList = storyService.getStoryInfoList(storyDTO, webPage);
            modelAndView.addObject("storyInfoList",storyInfoList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:编辑故事荟
     * CreateBy:WeiYangDong_2018-03-23
     */
    @RequestMapping(value = "/toEditStoryInfo.html")
    public ModelAndView toEditStoryInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        StoryDTO storyDTO){
        ModelAndView modelAndView = new ModelAndView("/community/StoryInfoEdit");
        try {
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),storyDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            //获取故事荟
            StoryDTO storyInfo = null;
            if (null != storyDTO.getId() && !"".equals(storyDTO.getId())){
                storyInfo = storyService.getStoryInfoDTOById(storyDTO.getId());
            }
            modelAndView.addObject("storyInfo",storyInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新故事荟
     * CreateBy:WeiYangDong_2018-03-23
     */
    @RequestMapping(value = "/saveOrUpdateStoryInfo.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateStoryInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                              @RequestParam(value = "infoSignImgFile",required = false) MultipartFile infoSignImgFile,
                                              StoryDTO storyDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../story/getStoryInfoList.html?menuId=006000030000");
        try{
            //设置操作人
            storyDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            //设置信息标识图
            if (null != infoSignImgFile){
                String infoSignImgUrl = imgUpload(infoSignImgFile);
                if (infoSignImgUrl.lastIndexOf("/") != infoSignImgUrl.length()-1){
                    storyDTO.setInfoSignImgUrl(infoSignImgUrl);
                }
            }
            storyService.saveOrUpdateStoryInfo(storyDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除故事荟(逻辑删除)
     * CreateBy:WeiYangDong_2018-03-23
     */
    @ResponseBody
    @RequestMapping(value = "/deleteStoryInfo")
    public Map<String,Object> deleteStoryInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                              StoryDTO storyDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            storyService.deleteStoryInfo(storyDTO.getId());
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
