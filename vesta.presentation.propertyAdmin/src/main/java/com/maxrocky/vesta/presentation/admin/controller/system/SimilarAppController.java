package com.maxrocky.vesta.presentation.admin.controller.system;

import com.maxrocky.vesta.application.DTO.SimilarAppContentDTO;
import com.maxrocky.vesta.application.DTO.SimilarAppPictureDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.SimilarAppService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @Description 类APP管理Controller
 * @data 2018/5/28
 */
@Controller
@RequestMapping(value = "/sApp")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class SimilarAppController {

    @Autowired
    SimilarAppService similarAppService;
    @Autowired
    DefaultConfigService defaultConfigService;
    @Autowired
    StaffUserService staffUserService;

    /* ========== 类APP图片展示 ========== */

    @RequestMapping(value = "/getPictureList.html")
    public ModelAndView getPictureList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       SimilarAppPictureDTO similarAppPictureDTO,
                                       WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/system/similarApp/PictureList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(roleScopeList);
            modelAndView.addObject("clientConfigList",clientConfigList);
            List<Integer> clientIdList = new ArrayList<>();
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                clientIdList.add(clientConfigEntity.getId());
            }
            similarAppPictureDTO.setClientIdList(clientIdList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<SimilarAppPictureDTO> list = similarAppService.getPictureList(similarAppPictureDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            modelAndView.addObject("pictureDTO",similarAppPictureDTO);
            //执行查询
            List<SimilarAppPictureDTO> pictureList = similarAppService.getPictureList(similarAppPictureDTO, webPage);
            modelAndView.addObject("pictureList",pictureList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/toEditPicture.html")
    public ModelAndView toEditPicture(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                      SimilarAppPictureDTO similarAppPictureDTO){
        ModelAndView modelAndView = new ModelAndView("/system/similarApp/PictureEdit");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(roleScopeList);
            modelAndView.addObject("clientConfigList",clientConfigList);
            if (null != similarAppPictureDTO.getId() && !"".equals(similarAppPictureDTO.getId())){
                SimilarAppPictureDTO pictureDTO = similarAppService.getPictureById(similarAppPictureDTO.getId());
                modelAndView.addObject("pictureDTO",pictureDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveOrUpdatePicture.html")
    public ModelAndView saveOrUpdatePicture(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            SimilarAppPictureDTO similarAppPictureDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../sApp/getPictureList.html?menuId=006000040000");
        try{
            similarAppPictureDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            similarAppService.saveOrUpdatePicture(similarAppPictureDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/deletePicture")
    public Map<String,Object> deletePicture(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            SimilarAppPictureDTO similarAppPictureDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            if (null != similarAppPictureDTO.getId() && !"".equals(similarAppPictureDTO.getId())){
                similarAppService.deletePicture(similarAppPictureDTO.getId());
            }
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /* ========== 类APP内容管理 ========== */

    @RequestMapping(value = "/getContentList.html")
    public ModelAndView getContentList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       SimilarAppContentDTO similarAppContentDTO,
                                       WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/system/similarApp/ContentList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(roleScopeList);
            modelAndView.addObject("clientConfigList",clientConfigList);
            List<Integer> clientIdList = new ArrayList<>();
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                clientIdList.add(clientConfigEntity.getId());
            }
            similarAppContentDTO.setClientIdList(clientIdList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<SimilarAppContentDTO> list = similarAppService.getContentList(similarAppContentDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);

            //检索条件回显
            modelAndView.addObject("contentDTO",similarAppContentDTO);
            //执行查询
            List<SimilarAppContentDTO> contentList = similarAppService.getContentList(similarAppContentDTO, webPage);
            modelAndView.addObject("contentList",contentList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/toEditContent.html")
    public ModelAndView toEditContent(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                      SimilarAppContentDTO similarAppContentDTO){
        ModelAndView modelAndView = new ModelAndView("/system/similarApp/ContentEdit");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(roleScopeList);
            modelAndView.addObject("clientConfigList",clientConfigList);
            if (null != similarAppContentDTO.getId() && !"".equals(similarAppContentDTO.getId())){
                SimilarAppContentDTO contentDTO = similarAppService.getContentById(similarAppContentDTO.getId());
                modelAndView.addObject("contentDTO",contentDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveOrUpdateContent.html")
    public ModelAndView saveOrUpdateContent(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            SimilarAppContentDTO similarAppContentDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../sApp/getContentList.html?menuId=006000040000");
        try{
            similarAppContentDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            similarAppService.saveOrUpdateContent(similarAppContentDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteContent")
    public Map<String,Object> deleteContent(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            SimilarAppContentDTO similarAppContentDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            if (null != similarAppContentDTO.getId() && !"".equals(similarAppContentDTO.getId())){
                similarAppService.deleteContent(similarAppContentDTO.getId());
            }
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

}
