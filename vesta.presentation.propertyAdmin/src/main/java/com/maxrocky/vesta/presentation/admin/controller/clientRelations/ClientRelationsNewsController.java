package com.maxrocky.vesta.presentation.admin.controller.clientRelations;

import com.maxrocky.vesta.application.adminDTO.QCNewsDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.ClientRelationsNewsService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 客关新闻Controller
 * Created by yuanyn on 2018/6/20 0011.
 */
@Controller
@RequestMapping(value = "/qcNews")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ClientRelationsNewsController {


    @Autowired
    private ClientRelationsNewsService clientRelationsNewsService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private AuthAgencyService authAgencyService;


    //获取新闻列表
    @RequestMapping(value = "/getNewsList.html")
    public ModelAndView getNewsList(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                         QCNewsDTO newsDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/clientRelations/clientRelationsNews/clientRelationsNewsList");
        try{
            //判断校验是否有授权功能点
            List<String> projectIds = authAgencyService.getNewsFunctionByStaffId("007800010000",userInformationEntity.getStaffId());
            //分页设置并回显
            webPage.setPageSize(20);
            List<QCNewsDTO> list = clientRelationsNewsService.getNewsList(newsDTO, null,projectIds);
            //执行检索
            List<QCNewsDTO> newsDTOs = clientRelationsNewsService.getNewsList(newsDTO, webPage,projectIds);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("news",newsDTO);
            modelAndView.addObject("newsDTOs",newsDTOs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    //跳转编辑
    @RequestMapping(value = "/toEditNews")
    public ModelAndView toEditNews (@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                         QCNewsDTO newsDTO){
        ModelAndView modelAndView = new ModelAndView("/clientRelations/clientRelationsNews/clientRelationsAddNews");
        try {
            //获取要编辑新闻的详情
            if (!StringUtil.isEmpty(newsDTO.getNewsId())){
                newsDTO = clientRelationsNewsService.getNewsById(newsDTO.getNewsId());
            }
            //判断校验是否有授权功能点
            Map<String, String> projectList = authAgencyService.getNewsProjectNameAndIdByStaffId("007800010000",userInformationEntity.getStaffId());
            //回显编辑内容
            modelAndView.addObject("projects",projectList);
            modelAndView.addObject("newsDTO",newsDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


    //发布新闻
    @RequestMapping(value = "/saveOrUpdateNews",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateNews(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                              @RequestParam(value = "newsImgFile",required = false) MultipartFile newsImgFile,
                                              QCNewsDTO newsDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../qcNews/getNewsList.html");
        try{
            //设置信息标识图
            if (null != newsImgFile){
                String newsImgUrl = imgUpload(newsImgFile);
                if (newsImgUrl.lastIndexOf("/") != newsImgUrl.length()-1){
                    newsDTO.setNewsImgUrl(newsImgUrl);
                }
            }
            clientRelationsNewsService.saveOrUpdateNews(userInformationEntity,newsDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    //跳转详情
    @RequestMapping(value = "/toNewsDetails")
    public ModelAndView toNewsDetails(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                           QCNewsDTO newsDTO){
        ModelAndView modelAndView = new ModelAndView("/clientRelations/clientRelationsNews/clientRelationsNewsDetails");
        try {
            //新闻详情
            if (!StringUtil.isEmpty(newsDTO.getNewsId())){
                newsDTO = clientRelationsNewsService.getNewsById(newsDTO.getNewsId());
            }
            modelAndView.addObject("newsDTO",newsDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    //删除新闻
    @ResponseBody
    @RequestMapping(value = "/delNews")
    public Map<String,Object> delNews(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                           QCNewsDTO newsDTO) {

        Map<String,Object> resultMap = new HashedMap();
        try{
            clientRelationsNewsService.delNews(userInformationEntity,newsDTO);
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
