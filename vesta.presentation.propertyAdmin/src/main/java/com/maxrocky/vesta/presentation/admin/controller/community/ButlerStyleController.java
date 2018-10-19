package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.ButlerStyleDto;
import com.maxrocky.vesta.application.impl.StaffUserServiceImpl;
import com.maxrocky.vesta.application.service.ButlerStyleService;
import com.maxrocky.vesta.application.service.ButlerStyleServiceImpl;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.ButlerScoreLogEntity;
import com.maxrocky.vesta.domain.model.ButlerStyleEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管家风采-功能模块Controller
 * Created by WeiYangDong on 2017/5/3.
 */
@Controller
@RequestMapping(value = "/butlerStyle")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class ButlerStyleController {

    @Autowired
    StaffUserServiceImpl staffUserService;
    @Autowired
    ButlerStyleService butlerStyleService;

    /**
     * Describe:获取管家列表
     * CreateBy:WeiYangDong_2017-05-03
     */
    @RequestMapping(value = "/getButlerStyleList.html")
    public ModelAndView getButlerStyleList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           ButlerStyleDto butlerStyleDto,WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/ButlerStyleList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            butlerStyleDto.setRoleScopeList(roleScopeList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<ButlerStyleEntity> list = butlerStyleService.getButlerStyleList(butlerStyleDto, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),butlerStyleDto.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != butlerStyleDto.getCityId() && !"".equals(butlerStyleDto.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), butlerStyleDto.getCityId(), butlerStyleDto.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("butlerStyleDto",butlerStyleDto);
            //获取管家列表
            List<ButlerStyleEntity> butlerStyleList = butlerStyleService.getButlerStyleList(butlerStyleDto, webPage);
            modelAndView.addObject("butlerStyleList",butlerStyleList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取管家评分日志列表
     * CreateBy:WeiYangDong_2017-07-25
     */
    @RequestMapping(value = "/getButlerScoreLogList.html")
    public ModelAndView getButlerScoreLogList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           ButlerStyleDto butlerStyleDto,
                                           WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/ButlerScoreInfo");
        try{
            //获取管家详情
            ButlerStyleEntity butlerStyleEntity = null;
            if (null != butlerStyleDto.getButlerId() && !"".equals(butlerStyleDto.getButlerId())){
                butlerStyleEntity = butlerStyleService.getButlerStyleInfoById(butlerStyleDto.getButlerId());
            }
            modelAndView.addObject("butlerStyle",butlerStyleEntity);
            //分页设置并回显
            webPage.setPageSize(20);
            List<ButlerScoreLogEntity> list = butlerStyleService.getButlerScoreLogList(butlerStyleDto, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //获取管家评分日志列表
            List<ButlerScoreLogEntity> butlerScoreLogList = butlerStyleService.getButlerScoreLogList(butlerStyleDto, webPage);
            modelAndView.addObject("butlerScoreLogList",butlerScoreLogList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:编辑管家详情
     * CreateBy:WeiYangDong_2017-05-03
     */
    @RequestMapping(value = "/toEditButlerStyle.html")
    public ModelAndView toEditButlerStyle(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                          ButlerStyleDto butlerStyleDto){
        ModelAndView modelAndView = new ModelAndView("/community/ButlerStyleEdit");
        try {
            //获取管家详情
            ButlerStyleEntity butlerStyleEntity = null;
            if (null != butlerStyleDto.getButlerId() && !"".equals(butlerStyleDto.getButlerId())){
                butlerStyleEntity = butlerStyleService.getButlerStyleInfoById(butlerStyleDto.getButlerId());
            }
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),butlerStyleDto.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != butlerStyleEntity && null != butlerStyleEntity.getServiceCityId() && !"".equals(butlerStyleEntity.getServiceCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), butlerStyleEntity.getServiceCityId(), butlerStyleDto.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("butlerStyle",butlerStyleEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:重置管家评分
     * CreateBy:WeiYangDong_2017-05-10
     */
    @ResponseBody
    @RequestMapping(value = "/resetButlerScope")
    public Map<String,Object> resetButlerScope(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                      ButlerStyleDto butlerStyleDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            butlerStyleDto.setModifyBy(userPropertystaffEntity.getStaffName());
            butlerStyleService.resetButlerScope(butlerStyleDto);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:校验管家名称(butlerNum)唯一性
     * CreateBy:WeiYangDong_2017-05-10
     */
    @ResponseBody
    @RequestMapping(value = "/checkButlerNum")
    public Map<String,Object> checkButlerNum(ButlerStyleDto butlerStyleDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            resultMap.put("check",butlerStyleService.checkButlerNum(butlerStyleDto.getButlerNum()));
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:分配管家责任范围
     * CreateBy:WeiYangDong_2017-05-08
     */
    @RequestMapping(value = "/toAssignButlerScope.html")
    public ModelAndView toAssignButlerScope(ButlerStyleDto butlerStyleDto){
        ModelAndView modelAndView = new ModelAndView("/community/ButlerStyleAssignScope");
        try{
            //获取管家详情
            ButlerStyleEntity butlerStyleEntity = null;
            if (null != butlerStyleDto.getButlerId() && !"".equals(butlerStyleDto.getButlerId())){
                butlerStyleEntity = butlerStyleService.getButlerStyleInfoById(butlerStyleDto.getButlerId());
            }
            modelAndView.addObject("butlerStyle",butlerStyleEntity);
            //获取数据列表
            butlerStyleDto.setProjectNum(butlerStyleEntity.getServiceProjectCode());
            String scopeJson = butlerStyleService.getScopeTreeByButler(butlerStyleDto);
            modelAndView.addObject("scopeJson",scopeJson);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新管家责任范围
     * CreateBy:WeiYangDong_2017-05-08
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateButlerScope")
    public Map<String,Object> saveOrUpdateButlerScope(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                      ButlerStyleDto butlerStyleDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            JSONArray jsonArray = JSONArray.fromObject((butlerStyleDto.getResultJson()));
            //存储房产ID
            List<String> roomIdList = new ArrayList<>();
            //存储楼栋名称
            List<String> buildingNameList = new ArrayList<>();
            //解析所有楼栋,用于保存管家服务楼栋
            //解析所有房产,用于更新管家服务房产
            JSONObject jsonObject = null;
            for (int i=0,length=jsonArray.size();i<length;i++){
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.get("name").toString().contains("楼栋")){
                    buildingNameList.add(jsonObject.get("name").toString());
                }
                if (jsonObject.get("id").toString().length() >= 4){
                    roomIdList.add(jsonObject.get("value").toString());
                }
            }
            butlerStyleDto.setRoomIdList(roomIdList);
            butlerStyleDto.setBuildingNameList(buildingNameList);
            butlerStyleService.saveOrUpdateButlerScope(butlerStyleDto);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:保存或更新管家详情
     * CreateBy:WeiYangDong_2017-05-03
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateButlerStyle")
    public Map<String,Object> saveOrUpdateButlerStyle(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                      @RequestParam(value = "wechatQRCodeFile",required = false) MultipartFile wechatQRCodeFile,
                                                      @RequestParam(value = "butlerHeadImgFile",required = false) MultipartFile butlerHeadImgFile,
                                                      ButlerStyleDto butlerStyleDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            butlerStyleDto.setModifyBy(userPropertystaffEntity.getStaffName());
            //设置微信二维码Url
            if (null != wechatQRCodeFile){
                butlerStyleDto.setWechatQRCodeUrl(imgUpload(wechatQRCodeFile));
            }
            //设置管家头像Url
            if (null != butlerHeadImgFile){
                butlerStyleDto.setButlerHeadImgUrl(imgUpload(butlerHeadImgFile));
            }
            butlerStyleService.saveOrUpdateButlerStyle(butlerStyleDto);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:删除管家数据
     * CreateBy:WeiYangDong_2017-11-03
     */
    @ResponseBody
    @RequestMapping(value = "/deleteButlerStyle")
    public Map<String,Object> deleteButlerStyle(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                ButlerStyleDto butlerStyleDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //执行删除
            butlerStyleService.deleteButlerStyle(butlerStyleDto);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }
/*
    @RequestMapping(value = "/saveOrUpdateButlerStyle.html")
    public ModelAndView saveOrUpdateButlerStyle(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                @RequestParam(value = "wechatQRCodeFile",required = false) MultipartFile wechatQRCodeFile,
                                                @RequestParam(value = "butlerHeadImgFile",required = false) MultipartFile butlerHeadImgFile,
                                                ButlerStyleDto butlerStyleDto){
        ModelAndView modelAndView = new ModelAndView("redirect:/butlerStyle/getButlerStyleList.html?menuId=000200060000");
        try {
            //设置操作人
            butlerStyleDto.setModifyBy(userPropertystaffEntity.getStaffName());
            //设置微信二维码Url
            butlerStyleDto.setWechatQRCodeUrl(imgUpload(wechatQRCodeFile));
            //设置管家头像Url
            butlerStyleDto.setButlerHeadImgUrl(imgUpload(butlerHeadImgFile));

            modelAndView.addObject("butlerStyle",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }
*/

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
