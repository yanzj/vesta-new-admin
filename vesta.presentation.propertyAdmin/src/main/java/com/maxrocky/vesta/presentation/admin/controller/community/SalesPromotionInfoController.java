package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.SalesPromotionInfoDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.SalesPromotionInfoService;
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
 * 促销信息-功能模块Controller
 * Created by WeiYangDong on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/salesPromotionInfo")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class SalesPromotionInfoController {

    @Autowired
    StaffUserService staffUserService;
    @Autowired
    SalesPromotionInfoService salesPromotionInfoService;

    /**
     * Describe:获取促销信息列表
     * CreateBy:WeiYangDong_2017-05-11
     */
    @RequestMapping(value = "/getSalesPromotionInfoList.html")
    public ModelAndView getSalesPromotionInfoList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                  SalesPromotionInfoDTO salesPromotionInfoDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/SalesPromotionInfoList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            salesPromotionInfoDTO.setRoleScopeList(roleScopeList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<SalesPromotionInfoDTO> list = salesPromotionInfoService.getSalesPromotionInfoList(salesPromotionInfoDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),salesPromotionInfoDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != salesPromotionInfoDTO.getCityId() && !"".equals(salesPromotionInfoDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), salesPromotionInfoDTO.getCityId(), salesPromotionInfoDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("salesPromotionInfoDTO",salesPromotionInfoDTO);
            //获取促销信息列表
            List<SalesPromotionInfoDTO> salesPromotionInfoList = salesPromotionInfoService.getSalesPromotionInfoList(salesPromotionInfoDTO, webPage);
            modelAndView.addObject("salesPromotionInfoList",salesPromotionInfoList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:编辑促销信息
     * CreateBy:WeiYangDong_2017-05-11
     */
    @RequestMapping(value = "/toEditSalesPromotionInfo.html")
    public ModelAndView toEditSalesPromotionInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                 SalesPromotionInfoDTO salesPromotionInfoDTO){
        ModelAndView modelAndView = new ModelAndView("/community/SalesPromotionInfoEdit");
        try {
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),salesPromotionInfoDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            //获取促销信息
            SalesPromotionInfoDTO salesPromotionInfo = null;
            if (null != salesPromotionInfoDTO.getId() && !"".equals(salesPromotionInfoDTO.getId())){
                salesPromotionInfo = salesPromotionInfoService.getSalesPromotionInfoDTOById(salesPromotionInfoDTO.getId());
            }
            modelAndView.addObject("salesPromotionInfo",salesPromotionInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新促销信息
     * CreateBy:WeiYangDong_2017-05-11
     */
    @RequestMapping(value = "/saveOrUpdateSalesPromotionInfo.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateSalesPromotionInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                       @RequestParam(value = "infoSignImgFile",required = false) MultipartFile infoSignImgFile,
                                                      SalesPromotionInfoDTO salesPromotionInfoDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../salesPromotionInfo/getSalesPromotionInfoList.html?menuId=006000010000");
        try{
            //设置操作人
            salesPromotionInfoDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            //设置信息标识图
            if (null != infoSignImgFile){
                String infoSignImgUrl = imgUpload(infoSignImgFile);
                if (infoSignImgUrl.lastIndexOf("/") != infoSignImgUrl.length()-1){
                    salesPromotionInfoDTO.setInfoSignImgUrl(infoSignImgUrl);
                }
            }
            salesPromotionInfoService.saveOrUpdateSalesPromotionInfo(salesPromotionInfoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除促销信息(逻辑删除)
     * CreateBy:WeiYangDong_2017-05-11
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSalesPromotionInfo")
    public Map<String,Object> deleteSalesPromotionInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                      SalesPromotionInfoDTO salesPromotionInfoDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            salesPromotionInfoService.deleteSalesPromotionInfo(salesPromotionInfoDTO.getId());
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
