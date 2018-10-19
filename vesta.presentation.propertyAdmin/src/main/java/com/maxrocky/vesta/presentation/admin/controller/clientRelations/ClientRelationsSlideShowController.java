package com.maxrocky.vesta.presentation.admin.controller.clientRelations;

import com.maxrocky.vesta.application.admin.dto.UploadImage;
import com.maxrocky.vesta.application.adminDTO.SlideShowDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.ClientRelationsSlideShowService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 客关轮播图Controller
 * Created by yuanyn on  2018/6/11 0011.
 */
@RestController
@RequestMapping(value = "/qcSlideShow")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ClientRelationsSlideShowController {

    @Autowired
    private ClientRelationsSlideShowService clientRelationsSlideShowService;
    @Autowired
    private AuthAgencyService authAgencyService;
    @Autowired
    private ImgService imgService;

    /**
     * 获取轮播图列表
     * Created by yuanyn on 2018/6/11.
     */
    @RequestMapping(value = "/getSlideShowList.html")
    public ModelAndView getSlideShowList(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                         SlideShowDTO slideShowDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/clientRelations/clientRelationsSlideShow/clientRelationsSlideShowList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<SlideShowDTO> list = clientRelationsSlideShowService.getSlideShowList(slideShowDTO, null);
            //执行检索
            List<SlideShowDTO> slideShowDTOs = clientRelationsSlideShowService.getSlideShowList(slideShowDTO, webPage);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("slideShowDTO",slideShowDTO);
            modelAndView.addObject("slideShowDTOs",slideShowDTOs);
            CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
            //判断校验是否有授权功能点
            List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
            if(function!=null){
                //校验是否有  qch40010135//搜索 qch40010136 //创建轮播图  qch40010137 //详情 qch40010138//编辑（修改） qch40010139 //上架、下架 qch40010140 //删除
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "QCH40010135":
                            checkAuthFunctionDTO.setQch40010135("Y");
                            break;
                        case "QCH40010136":
                            checkAuthFunctionDTO.setQch40010136("Y");
                            break;
                        case "QCH40010137":
                            checkAuthFunctionDTO.setQch40010137("Y");
                            break;
                        case "QCH40010138":
                            checkAuthFunctionDTO.setQch40010138("Y");
                            break;
                        case "QCH40010139":
                            checkAuthFunctionDTO.setQch40010139("Y");
                            break;
                        case "QCH40010140":
                            checkAuthFunctionDTO.setQch40010140("Y");
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

    //跳转编辑
    @RequestMapping(value = "/toEditSlideShow")
    public ModelAndView toEditSlideShow (@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                         SlideShowDTO slideShowDTO){
        ModelAndView modelAndView = new ModelAndView("/clientRelations/clientRelationsSlideShow/clientRelationsAddSlideShow");
        try {
            String flag = "N";
            if(clientRelationsSlideShowService.getIsSlideShow()){
                flag = "Y";
            }
            //获取要编辑轮播图的详情
            if (!StringUtil.isEmpty(slideShowDTO.getSlideShowId())){
                slideShowDTO = clientRelationsSlideShowService.getSlideShowById(slideShowDTO.getSlideShowId());
                if("N".equals(flag) && !"0".equals(slideShowDTO.getIsSlideShow())){
                    flag = "Y";
                }
            }
            //回显编辑内容
            slideShowDTO.setIsShow(flag);
            modelAndView.addObject("slideShowDTO",slideShowDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    //跳转详情
    @RequestMapping(value = "/toSlideShowDetails")
    public ModelAndView toSlideShowDetails(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                           SlideShowDTO slideShowDTO){
        ModelAndView modelAndView = new ModelAndView("/clientRelations/clientRelationsSlideShow/clientRelationsSlideShowDetails");
        try {
            //轮播图的详情
            if (!StringUtil.isEmpty(slideShowDTO.getSlideShowId())){
                slideShowDTO = clientRelationsSlideShowService.getSlideShowById(slideShowDTO.getSlideShowId());
            }
            modelAndView.addObject("slideShowDTO",slideShowDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 发布轮播图
     * Created by yuanyn on 2018/6/11.
     */
    @RequestMapping(value = "/saveOrUpdateSlideShow",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateSlideShow(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                           @RequestParam(value = "slideShowImgFile",required = false) MultipartFile slideShowImgFile,
                                           SlideShowDTO slideShowDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../qcSlideShow/getSlideShowList.html");
        try{
            //设置信息标识图
            if (null != slideShowImgFile){
                String slideShowImgUrl = imgUpload(slideShowImgFile);
                if (slideShowImgUrl.lastIndexOf("/") != slideShowImgUrl.length()-1){
                    slideShowDTO.setSlideShowImgUrl(slideShowImgUrl);
                }
            }
            clientRelationsSlideShowService.saveOrUpdateSlideShow(userInformationEntity,slideShowDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    //删除轮播图
    @ResponseBody
    @RequestMapping(value = "/delSlideShow")
    public Map<String,Object> delSlideShow(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                           SlideShowDTO slideShowDTO) {

        Map<String,Object> resultMap = new HashedMap();
        try{
            clientRelationsSlideShowService.delSlideShow(userInformationEntity,slideShowDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 上架、下架轮播图
     * Created by yuanyn on 2018/6/11.
     */
    @RequestMapping(value = "/toTopSlideShow")
    public Map<String,Object> toTopSlideShow(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                             SlideShowDTO slideShowDTO){
        Map<String,Object> resultMap = new HashedMap();
        boolean flag = clientRelationsSlideShowService.toTopSlideShow(userInformationEntity,slideShowDTO);
        if(flag){
            resultMap.put("error",0);
        }else {
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
