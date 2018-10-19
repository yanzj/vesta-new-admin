package com.maxrocky.vesta.presentation.admin.controller.sysmanage;

import com.maxrocky.vesta.application.DTO.HomePageCarouselDTO;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.HomePageCarouselService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.HomePageCarouselEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页轮播图_Controller
 * 2016/6/24_Wyd
 */
@Controller
@RequestMapping(value = "/homePageCarousel")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class HomePageCarouselController {

    @Autowired
    private HomePageCarouselService homePageCarouselService;

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 首页轮播图列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/gotoCarouselList")
    public ModelAndView gotoCarouselList(HomePageCarouselDTO homePageCarouselDTO,Model model,WebPage webPage){
        //1.数据回显
        //查询所有CRM项目城市列表
        List<Object[]> objects = this.announcementService.listCity();
        List<TransfersDto> city = new ArrayList<TransfersDto>();
        city.add(new TransfersDto("0", "全部城市"));
        for (Object[] object : objects) {
            String cid = object[0].toString();
            String name = object[1].toString();
            city.add(new TransfersDto(cid, name));
        }
        model.addAttribute("city", city);
        //2.获取项目轮播图数据列表
        String cityId = homePageCarouselDTO.getCityId();
        String projectName = homePageCarouselDTO.getProjectName();
        if (null != cityId && "0".equals(cityId)){
            //全部城市
            List<Object[]> list = homePageCarouselService.listAllProject();
            Object[] all = {"0","全部城市"};
            ((ArrayList) list).add(0,all);
            model.addAttribute("projectList",list);
        }else if (null != projectName && "0".equals(projectName)){
            //全部项目
            List<Object[]> list = homePageCarouselService.listProject(cityId);
            model.addAttribute("projectList",list);
        }else if (null != projectName && !"0".equals(projectName) && !"".equals(projectName)){
            //项目
            List<Object[]> list = homePageCarouselService.getProjectByCode(projectName);
            model.addAttribute("projectList",list);
        }

        return new ModelAndView("/sysmanage/HomePageCarouselList");
    }

    /**
     * 去到轮播图编辑页
     * @return ModelAndView
     */
    @RequestMapping(value = "/gotoCarouselEdit")
    public ModelAndView gotoCarouselEdit(HomePageCarouselDTO homePageCarouselDTO,Model model){
        //#1.查询所有CRM项目城市列表
        List<Object[]> objects = this.announcementService.listCity();
        List<TransfersDto> city = new ArrayList<TransfersDto>();
        city.add(new TransfersDto("0", "全部城市"));
        for (Object[] object : objects) {
            String cid = object[0].toString();
            String name = object[1].toString();
            city.add(new TransfersDto(cid, name));
        }
        model.addAttribute("city", city);

        String projectCode = homePageCarouselDTO.getProjectCode();
        List<HomePageCarouselEntity> homePageCarouselEntities = homePageCarouselService.queryCarouselByProjectCode(projectCode);
        if (homePageCarouselEntities.size() == 0){
            for (int i=0; i<5; i++){
                homePageCarouselEntities.add(null);
            }
        }
        model.addAttribute("projectName",homePageCarouselDTO.getProjectName());
        model.addAttribute("projectCode",projectCode);
        model.addAttribute("carouselList",homePageCarouselEntities);

        return new ModelAndView("/sysmanage/HomePageCarouselUpdate");
    }

    /**
     * 保存或更新轮播图信息
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveOrUpdateCarousel")
    public ModelAndView saveOrUpdateCarousel(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                             HomePageCarouselDTO homePageCarouselDTO){
        //设置操作人
        homePageCarouselDTO.setModifyBy(userPropertystaffEntity.getStaffId());
        //处理_多图片上传Url
        MultipartFile[] multipartFiles = homePageCarouselDTO.getMultipartFiles();
        ArrayList<String> imgUrls = new ArrayList<String>();
        ImgService imgService = new ImgServiceImpl();
        for (int i = 0; i < multipartFiles.length; i++){
            if (multipartFiles[i].isEmpty()){
                imgUrls.add("");
            }else{
                //图片地址特殊处理
//                String urlTitle ="http://211.94.93.223/images/";
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                String imgUrl = imgService.uploadAdminImage(multipartFiles[i], ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                imgUrls.add(imgUrl);
            }
        }
        homePageCarouselDTO.setImgUrls(imgUrls);
        homePageCarouselService.saveOrUpdateCarousel(homePageCarouselDTO);
        return new ModelAndView("redirect:/homePageCarousel/gotoCarouselList");
    }
}
