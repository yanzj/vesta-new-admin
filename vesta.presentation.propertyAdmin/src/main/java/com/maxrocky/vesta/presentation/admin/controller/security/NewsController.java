package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.admin.dto.UploadImage;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.news.DTO.NewsDTO;
import com.maxrocky.vesta.application.news.inf.NewsService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.news.model.NewsEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 新闻管理Controller
 * Created by yuanyn on 2017/6/13.
 */
@Controller
@RequestMapping(value = "/news")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    StaffUserService staffUserService;
    @Autowired
    ImgService imgService;
    @Autowired
    private AuthAgencyService authAgencyService;

    /**
     * 获取新闻列表
     * Created by yuanyn on 2017/6/14.
     */
    @RequestMapping(value = "/getNewsList.html")
    public ModelAndView getNewsList(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                    NewsDTO newsDTO,
                                    WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/security/news/NewsInitManage");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = newsService.getNewsList(newsDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("NewsDTO",newsDTO);
            //执行检索
            List<Map<String, Object>> newsList = newsService.getNewsList(newsDTO, webPage);
            modelAndView.addObject("newsList",newsList);
            CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
            //判断校验是否有授权功能点
            List<String> function = authAgencyService.getAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","3");
            if(function!=null){
                //校验是否有  sth40020053//搜索 sth40020054 //新增  sth40020055 //置顶（取消置顶） sth40020056//编辑（修改） sth40020057 //删除
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "STH40020053":
                            checkAuthFunctionDTO.setSth40020053("Y");
                            break;
                        case "STH40020054":
                            checkAuthFunctionDTO.setSth40020054("Y");
                            break;
                        case "STH40020055":
                            checkAuthFunctionDTO.setSth40020055("Y");
                            break;
                        case "STH40020056":
                            checkAuthFunctionDTO.setSth40020056("Y");
                            break;
                        case "STH40020057":
                            checkAuthFunctionDTO.setSth40020057("Y");
                            break;
                    }
                }
            }
            modelAndView.addObject("function",checkAuthFunctionDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 编辑新闻
     * Created by yuanyn on 2017/6/14.
     */
    @RequestMapping(value = "/toEditNews.html")
    public ModelAndView toEditNews(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                                  NewsDTO newsDTO){
        ModelAndView modelAndView = new ModelAndView("/security/news/NewsAddManage");
        try {
            //获取要编辑新闻的详情
            NewsEntity newsEntity = null;
            if (null != newsDTO.getNewsId() && !"".equals(newsDTO.getNewsId())){
                newsEntity = newsService.getNewsById(newsDTO.getNewsId());
            }
            //回显编辑内容
            modelAndView.addObject("newsDTO",newsEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 发布新闻
     * Created by yuanyn on 2017/6/14.
     */
    @RequestMapping(value = "/toSaveOrUpdateNews.html",method = RequestMethod.POST)
    public ModelAndView toSaveOrUpdateNews(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                           @RequestParam(value = "newsImgFile",required = false) MultipartFile newsImgFile,
                                                        NewsDTO newsDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../news/getNewsList.html");
        try{
            //设置操作人
            newsDTO.setModifyName(userInformationEntity.getStaffName());
            //设置信息标识图
            if (null != newsImgFile){
                String newsImgUrl = imgUpload(newsImgFile);
                if (newsImgUrl.lastIndexOf("/") != newsImgUrl.length()-1){
                    newsDTO.setNewsImgUrl(newsImgUrl);
                }
            }
            newsService.saveOrUpdateNews(newsDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 置顶新闻
     * Created by yuanyn on 2017/6/14.
     */
    @RequestMapping(value = "/toTopNews")
    public Map<String,Object> toTopNews(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                        NewsDTO newsDTO){
        Map<String,Object> resultMap = new HashedMap();
            boolean flag = newsService.topNews(newsDTO);
            if(flag){
                resultMap.put("error",0);
            }else {
                resultMap.put("error",-1);
            }
            return resultMap;
    }

    /**
     * 删除新闻
     * Created by yuanyn on 2017-06-14
     */
    @ResponseBody
    @RequestMapping(value = "/toDeleteNews")
    public Map<String,Object> toDeleteNews(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                           NewsDTO newsDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            newsService.deleteNews(newsDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 上传图片
     *Created by yuanyn on 2017/6/14.
     */
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public void createMerPicture(@ModelAttribute("uploadImage") @Valid UploadImage uploadImage, @RequestParam(value = "file", required = false) MultipartFile file,
                                 BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
            LinkedList<MultipartFile> upfileMap = (LinkedList<MultipartFile>) ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("upfile");
            for (MultipartFile tempfile : upfileMap) {
                //图片上传
                String fileName = imgService.uploadAdminImage(tempfile, ImgType.ACTIVITY);
                //图片地址特殊处理
//                String urlTitle = "http://211.94.93.223/images/";

                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;

//                String fileNameNote = fileName.replace("/opt/image.server/images/images_source/houserental/", "");
//                fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");
                String fileNameNote = fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL,"");
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                resultmap.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                resultmap.put("url",fileName);
                resultmap.put("size", tempfile.getSize());
                resultmap.put("title", fileNameNote);
                resultmap.put("type", fileName.substring(fileName.lastIndexOf(".")));
                resultmap.put("original", fileNameNote);
                String jsonstr = "";
                jsonstr ="{\"state\":\""+resultmap.get("state")+"\",\"url\":\""+resultmap.get("url")+"\",\"title\":\""+resultmap.get("title")+"\",\"original\":\""+resultmap.get("original")+"\"}";
                System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
                response.setContentType("text/html;charset=UTF-8");
                ServletOutputStream os ;
                try {
                    os = response.getOutputStream();
                    os.write(jsonstr.getBytes("UTF-8"));
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            resultmap.put("state", "文件上传失败!");
            resultmap.put("url", "");
            resultmap.put("title", "");
            resultmap.put("original", "");
            String jsonstr = "";
            jsonstr = "{\"state\":\"" + resultmap.get("state") + "\",\"url\":\"" + resultmap.get("url") + "\",\"title\":\"" + resultmap.get("title") + "\",\"original\":\"" + resultmap.get("original") + "\"}";
            System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
            response.setContentType("text/html;charset=UTF-8");
        }
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
