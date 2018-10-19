package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.CommunityNewsDto;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.admin.dto.UploadImage;
import com.maxrocky.vesta.application.inf.AnnouncementScopeService;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.CommunityNewsService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.CommunityNewsEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AnnouncementScopeRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.jibx.binding.util.MultipleValueMap;
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
import java.util.*;


/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:21
 * Describe:
 */
//@RestController
@Controller
@RequestMapping(value = "/communityNews")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class CommunityNewsController {
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    AnnouncementScopeService announcementScopeService;
    @Autowired
    CommunityNewsService communityNewsService;
    @Autowired
    ImgService imgService;
    @Autowired
    AnnouncementScopeRepository announcementScopeRepository;
    @Autowired
    StaffUserService staffUserService;

    /**
     * 校验用户是否可以操作该品牌资讯(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{communityDetailId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "communityDetailId")String communityDetailId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(),menuId);
            //品牌资讯发布项目范围
            List<Map<String, Object>> announcementProjectList = communityNewsService.getProjectScopeByCommunityDetailId(communityDetailId);
            //用户权限范围包含品牌资讯发布范围
            for (Map<String,Object> announcementProject : announcementProjectList){
                int flag = 0;
                String projectId = announcementProject.get("projectId").toString();
                for (Map<String,Object> roleProject : roleProjectList){
                    if (projectId.equals(roleProject.get("projectId").toString())){
                        flag = 1;
                    }
                }
                if (flag != 1){
                    resultMap.put("error",flag);
                    return resultMap;
                }
            }
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 品牌新闻查询展示
     *
     * @param communityNewsDto
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/NewsList.html")
    public String showNewsList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                               CommunityNewsDto communityNewsDto, Model model, WebPage webPage) {
        //设置类型
        List<TransfersDto> typeMap = new ArrayList<>();
        typeMap.add(new TransfersDto(0, "全部显示"));
        typeMap.add(new TransfersDto(1, "金茂品牌宣传"));
        typeMap.add(new TransfersDto(2, "新闻资讯"));
        model.addAttribute("typeMap", typeMap);

        //设置状态
        List<TransfersDto> statusMap = new ArrayList<>();
        statusMap.add(new TransfersDto(999, "--请选择状态--"));
        statusMap.add(new TransfersDto(0, "未发布"));
        statusMap.add(new TransfersDto(1, "已发布"));
        model.addAttribute("statusMap", statusMap);

        model.addAttribute("communityNewsDto", communityNewsDto);

        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        communityNewsDto.setRoleScopeList(roleScopeList);
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), communityNewsDto.getMenuId());
        model.addAttribute("city", cityList);
        if (null != communityNewsDto.getScopeId() && !"".equals(communityNewsDto.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), communityNewsDto.getScopeId(), communityNewsDto.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        try {
            List<CommunityNewsDto> communityNewsDtos = this.communityNewsService.queryAllByPage(communityNewsDto, webPage);
            model.addAttribute("communityNewsDtos", communityNewsDtos);
            /**=========================================================**/
            //记录集合长度，如果没有查询出数据则不可导出
            model.addAttribute("isExcel",communityNewsDtos.size());
             /**=========================================================**/
        } catch (GeneralException e) {
            e.printStackTrace();
            model.addAttribute("error", "查询出现异常请联系管理员");
            return "/community/NewsDetail";
        }

        return "/community/NewsList";
    }

    /**
     * 新增/修改 页面跳转
     * @param model
     * @param communityNewsDto
     * @return
     */
    @RequestMapping(value = "/transferPage")
    public String transferPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                               CommunityNewsDto communityNewsDto,
                               @Valid String menuId,
                               Model model, HttpServletRequest request, HttpServletResponse response) {
        /**=============================================================**/
        //#1.查询所有CRM项目城市列表
//        List<Object[]> objects = this.announcementService.listCity();
//        List<TransfersDto> city = new ArrayList<TransfersDto>();
//        city.add(new TransfersDto("0", "全部城市"));
//        for (Object[] object : objects) {
//            String cid = object[0].toString();
//            String name = object[1].toString();
//            city.add(new TransfersDto(cid, name));
//        }
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),menuId);
        request.setAttribute("city", cityList);
        /**============================================================**/
        //新增把时间抹去
        model.addAttribute("releaseDateFormat", "");
        /**============================================================**/
        //#2.根据新闻id查询所有通告范围,根据通告范围，返回前台数据,划分为二级数据
        if (!StringUtils.isEmpty(communityNewsDto.getId())) {
            List<Object[]> announcementScopes = this.announcementScopeService.queryByCommunitNewsId(communityNewsDto.getId());
            //all
            String allCityInScope = "";
            //城市
            String cityInScope = "";
            //城市Id
            String cityIdInScope = "";
            //项目
            String projectInScope = "";
            //项目Id
            String projectIdInScope = "";
            for (Object[] announcementScope : announcementScopes) {
                if (null != announcementScope[2])
                    allCityInScope = allCityInScope + announcementScope[2].toString() + ",";
                if (null != announcementScope[1])
                    if (!cityInScope.contains(announcementScope[1].toString())){
                        cityInScope = cityInScope + announcementScope[1].toString() + ",";
                    }
                if (null != announcementScope[3])
                    projectInScope = projectInScope + announcementScope[3].toString() + ",";
                if (null != announcementScope[4]){
                    if (!cityIdInScope.contains(announcementScope[4].toString())){
                        cityIdInScope = cityIdInScope + announcementScope[4].toString() + ",";
                    }
                }

                if (null != announcementScope[5])
                    projectIdInScope = projectIdInScope + announcementScope[5].toString() + ",";
            }
            if (!StringUtils.isEmpty(allCityInScope))
                allCityInScope = StringUtils.substring(allCityInScope, 0, allCityInScope.length() - 1);
            if (!StringUtils.isEmpty(cityInScope))
                /*cityInScope = StringUtils.substring(cityInScope, 0, allCityInScope.length() - 1);*/
                cityInScope = StringUtils.substring(cityInScope, 0, cityInScope.length() - 1);
            if (!StringUtils.isEmpty(projectInScope))
                /*projectInScope = StringUtils.substring(projectInScope, 0, allCityInScope.length() - 1);*/
                projectInScope = StringUtils.substring(projectInScope, 0, projectInScope.length() - 1);
            //model.addAttribute("allCityInScope", allCityInScope);
            request.setAttribute("allCityInScope", allCityInScope);
            //model.addAttribute("cityInScope", cityInScope);
            request.setAttribute("cityInScope", cityInScope);
            //model.addAttribute("projectInScope", projectInScope);
            request.setAttribute("projectInScope", projectInScope);
            request.setAttribute("cityIdInScope", cityIdInScope);
            request.setAttribute("projectIdInScope",projectIdInScope);
        }
        /**=============================================================**/
        if (!StringUtil.isEmpty(communityNewsDto.getId())) {
            CommunityNewsEntity newsDetail = this.communityNewsService.get(CommunityNewsEntity.class, communityNewsDto.getId());
            model.addAttribute("newsDetail", newsDetail);
            /**===============================================================================**/
            //修改操作
            model.addAttribute("isEdit","y");
            //格式化发布时间
            model.addAttribute("releaseDateFormat",
                    newsDetail.getReleaseDate()!=null?
                            DateUtils.format(newsDetail.getReleaseDate(),"yyyy-MM-dd HH:mm"):"");
             /**===============================================================================**/
        }
        return "/community/NewsUpdate";
    }

    /**
     * 新增/修改 功能
     * @param model
     * @param communityNewsDto
     * @return
     */
    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
    public ModelAndView addOrUpdatePage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, CommunityNewsDto communityNewsDto, Model model) {

        if (communityNewsDto.getReleaseStatus() == 1) {
            communityNewsDto.setReleasePerson(userPropertystaffEntity.getStaffName());
        }
        if (StringUtil.isEmpty(communityNewsDto.getId())) {
            communityNewsDto.setCreatePerson(userPropertystaffEntity.getStaffName());
        } else {
            communityNewsDto.setOperator(userPropertystaffEntity.getStaffName());
        }
        this.communityNewsService.saveOrUpdateNews(userPropertystaffEntity,communityNewsDto);

        return new ModelAndView("redirect:../communityNews/NewsList.html?menuId=005300010000");
    }

    /**
     * 删除 功能
     * @param model
     * @param communityNewsDto
     * @return
     */
    @RequestMapping(value = "/deleteNews")
    public ModelAndView deleteNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,CommunityNewsDto communityNewsDto, Model model) {

        this.communityNewsService.deleteNews(userPropertystaffEntity,communityNewsDto);
        /**=================================================================**/
        /**删除关系中间表**/
        announcementScopeRepository.deleteCommunitNewsId(communityNewsDto.getId());
        /**=================================================================**/
        return new ModelAndView("redirect:../communityNews/NewsList.html?menuId=005300010000");
    }

    /**
     * 上传图片
     * @param
     * @return
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

    /**
     * 上传图片
     */
    @RequestMapping(value = "/uploadImage2")
    @ResponseBody
    public void createMerPicture2(@ModelAttribute("uploadImage") @Valid UploadImage uploadImage, @RequestParam(value = "file", required = false) MultipartFile file,
                                 BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
//            LinkedList<MultipartFile> upfileMap = (LinkedList<MultipartFile>) ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("upfile");
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
//                jsonstr ="{\"errno\":0,\"data\":[\""+resultmap.get("url")+"\"]}";
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
            resultmap.put("errno", "文件上传失败诶!");
            resultmap.put("url", "");
            resultmap.put("title", "");
            resultmap.put("original", "");
            String jsonstr = "";
            jsonstr = "{\"state\":\"" + resultmap.get("state") + "\",\"url\":\"" + resultmap.get("url") + "\",\"title\":\"" + resultmap.get("title") + "\",\"original\":\"" + resultmap.get("original") + "\"}";
            System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
            response.setContentType("text/html;charset=UTF-8");
        }
    }

    @RequestMapping(value = "getImage/{path}", method = RequestMethod.GET)
    public void getImage(@PathVariable String path, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("------------getImage----------------------------");
       /* FileInputStream fis = null;
        OutputStream os = null;
        try {
            path = new String(Base64.decodeBase64(path), "UTF-8");
            fis = new FileInputStream(path);
            os = response.getOutputStream();
            int c;
            byte[] b = new byte[4096];
            while ((c = fis.read(b)) != -1) {
                os.write(b, 0, c);
            }
            os.flush();
        } catch (Exception e) {
            logger.error("ueditor get image error : ", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {

                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {

                }
            }
        }*/
    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping(value="/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,CommunityNewsDto communityNewsDto, Model model, WebPage webPage){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            communityNewsDto.setRoleScopeList(roleScopeList);
            return communityNewsService.exportExcel(user,httpServletResponse,communityNewsDto,webPage, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }
}
