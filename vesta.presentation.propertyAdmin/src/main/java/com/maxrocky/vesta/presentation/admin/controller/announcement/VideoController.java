package com.maxrocky.vesta.presentation.admin.controller.announcement;

import com.maxrocky.vesta.application.DTO.VideoHQDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.VideoService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 视频管理Controller
 * Created by WeiYangDong on 2017/9/26.
 */
@Controller
@RequestMapping(value = "/video")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class VideoController {

    @Autowired
    VideoService videoService;

    /**
     * Describe:新增或编辑总部视频
     * CreateBy:WeiYangDong_2017-09-27
     */
    @RequestMapping(value = "/toEditVideoHQInfo.html")
    public ModelAndView toEditVideoHQInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                           VideoHQDTO videoHQDTO){
        ModelAndView modelAndView = new ModelAndView("/announcement/VideoHQEdit");
        try {
            //获取商业公告详情
            VideoHQDTO videoHQInfo = null;
            if (null != videoHQDTO.getId() && !"".equals(videoHQDTO.getId())){
                videoHQInfo = videoService.getVideoHQById(videoHQDTO.getId());
            }
            modelAndView.addObject("videoHQInfo",videoHQInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取总部视频列表
     * CreateBy:WeiYangDong_2017-09-27
     */
    @RequestMapping(value = "/getVideoHQList.html")
    public ModelAndView getVideoHQList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                VideoHQDTO videoHQDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/announcement/VideoHQList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<VideoHQDTO> list = videoService.getVideoHQList(videoHQDTO,null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            modelAndView.addObject("videoHQDTO",videoHQDTO);
            //获取总部视频列表
            List<VideoHQDTO> videoHQList = videoService.getVideoHQList(videoHQDTO, webPage);
            modelAndView.addObject("videoHQList",videoHQList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新总部视频信息
     * CreateBy:WeiYangDong_2017-09-27
     */
    @RequestMapping(value = "/saveOrUpdateVideoHQInfo.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateVideoHQInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                 VideoHQDTO videoHQDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../video/getVideoHQList.html?menuId=006900030000");
        try{
            //设置操作人
            videoHQDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            videoService.saveOrUpdateVideoHQInfo(videoHQDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除总部视频数据(物理删除)
     * CreateBy:WeiYangDong_2017-09-27
     */
    @ResponseBody
    @RequestMapping(value = "/deleteVideoHQInfo")
    public Map<String,Object> deleteVideoHQInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                VideoHQDTO videoHQDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            videoService.deleteVideoHQInfo(videoHQDTO.getId());
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }
}
