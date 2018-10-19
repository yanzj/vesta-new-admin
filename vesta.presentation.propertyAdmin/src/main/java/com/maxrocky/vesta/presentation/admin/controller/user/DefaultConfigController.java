package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.DefaultConfigDTO;
import com.maxrocky.vesta.application.DTO.admin.UserTypeProjectConfigDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.DefaultConfigEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认配置_Controller
 * 2016/6/21_Wyd
 */
@Controller
@RequestMapping(value = "/defaultConfig")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class DefaultConfigController {

    @Autowired
    private DefaultConfigService defaultConfigService;

    @Autowired
    private StaffUserService staffUserService;

    /**
     * 查询用户默认配置头像
     * @param model
     * @return String
     */
    @RequestMapping(value = "/queryDefaultHeadImg")
    public String queryDefaultHeadImg(Model model){
        //用户默认头像_配置类型为1
        String configType = "1";
        DefaultConfigEntity defaultConfigEntity = defaultConfigService.queryDefaultConfig(configType);
        model.addAttribute("defaultConfig",defaultConfigEntity);
        return "/user/DefaultHeadImg";
    }

    /**
     * 保存或更新用户默认配置头像
     * @param userPropertystaffEntity
     * @param defaultConfigDTO
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveOrUpdateDefaultHeadImg")
    public ModelAndView saveOrUpdateDefaultHeadImg(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,DefaultConfigDTO defaultConfigDTO){
        try{
            //设置操作人
            defaultConfigDTO.setModifyBy(userPropertystaffEntity.getStaffId());
            //设置默认配置类型
            defaultConfigDTO.setConfigType("1");
            //处理图片上传
            if (defaultConfigDTO.getMultipartFile().isEmpty()){
                return new ModelAndView("redirect:/defaultConfig/queryDefaultHeadImg");
            }else{
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;

                String imgUrl = imgService.uploadAdminImage(defaultConfigDTO.getMultipartFile(), ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                defaultConfigDTO.setConfigValue(imgUrl);
            }
            defaultConfigService.saveOrUpdateDefaultConfig(defaultConfigDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/defaultConfig/queryDefaultHeadImg");
    }

    /**
     * 查询功能介绍页
     * @param model
     * @return String
     */
    @RequestMapping(value = "/queryFunctionDescImg")
    public String queryFunctionDescImg(Model model){
        //功能介绍页_配置类型为2
        String configType = "2";
        DefaultConfigEntity defaultConfigEntity = defaultConfigService.queryDefaultConfig(configType);
        model.addAttribute("defaultConfig",defaultConfigEntity);
        return "/system/appversion/FunctionDescImg";
    }

    /**
     * 保存或更新功能介绍页
     * @param userPropertystaffEntity
     * @param defaultConfigDTO
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveOrUpdateFunctionDescImg")
    public ModelAndView saveOrUpdateFunctionDescImg(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,DefaultConfigDTO defaultConfigDTO){
        try{
            //设置操作人
            defaultConfigDTO.setModifyBy(userPropertystaffEntity.getStaffId());
            //设置默认配置类型
            defaultConfigDTO.setConfigType("2");
            //处理图片上传
            if (defaultConfigDTO.getMultipartFile().isEmpty()){
                return new ModelAndView("redirect:/defaultConfig/queryFunctionDescImg");
            }else{
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理

                String urlTitle =ImageConfig.PIC_OSS_ADMIN_URL;

//                String urlTitle ="http://211.94.93.223/images/";

                String imgUrl = imgService.uploadAdminImage(defaultConfigDTO.getMultipartFile(), ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                defaultConfigDTO.setConfigValue(imgUrl);
            }
            defaultConfigService.saveOrUpdateDefaultConfig(defaultConfigDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/defaultConfig/queryFunctionDescImg");
    }

    /**
     * 获取用户可视项目配置列表
     * WeiYangDong_2017-01-23
     */
    @RequestMapping(value = "/userShowProjectConfigList.html")
    public ModelAndView getUserShowProjectConfigList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                     UserTypeProjectConfigDTO userTypeProjectConfigDTO,
                                                     WebPage webPage,Model model){
        ModelAndView modelAndView = new ModelAndView("/user/UserShowProjectConfigList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = defaultConfigService.getUserShowProjectConfigList(userTypeProjectConfigDTO, null);
            webPage.setRecordCount(list.size());
            model.addAttribute("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),userTypeProjectConfigDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != userTypeProjectConfigDTO.getCityId() && !"".equals(userTypeProjectConfigDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), userTypeProjectConfigDTO.getCityId(), userTypeProjectConfigDTO.getMenuId());
                model.addAttribute("projectList",projectList);
            }
            model.addAttribute("userTypeProjectConfigDTO", userTypeProjectConfigDTO);
            //获取用户可视项目配置列表
            List<Map<String, Object>> projectConfigList = defaultConfigService.getUserShowProjectConfigList(userTypeProjectConfigDTO, webPage);
            model.addAttribute("projectConfigList", projectConfigList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 编辑用户可视项目配置
     * WeiYangDong_2017-01-23
     */
    @RequestMapping(value = "/toEditUserShowProjectConfig.html")
    public ModelAndView toEditUserShowProjectConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO,Model model){
        ModelAndView modelAndView = new ModelAndView("/user/UserShowProjectConfigEdit");
        try{
            List<Map<String, Object>> projectConfigList = defaultConfigService.getUserShowProjectConfigList(userTypeProjectConfigDTO, null);
            if (null != projectConfigList && projectConfigList.size() > 0){
                Map<String, Object> projectConfig = projectConfigList.get(0);
                model.addAttribute("projectConfig",projectConfig);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 保存或更新用户可视项目配置
     * WeiYangDong_2017-01-23
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateUserShowProjectConfig")
    public Map<String,Object> saveOrUpdateUserShowProjectConfig(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                          UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //设置操作人
            userTypeProjectConfigDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            defaultConfigService.saveOrUpdateUserShowProjectConfig(userTypeProjectConfigDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 获取App项目可视功能模块配置列表
     * WeiYangDong_2017-04-12
     */
    @RequestMapping(value = "/appShowFunctionConfigList.html")
    public ModelAndView getAppShowFunctionConfigList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                     UserTypeProjectConfigDTO userTypeProjectConfigDTO,
                                                     WebPage webPage,Model model){
        ModelAndView modelAndView = new ModelAndView("/user/AppShowFunctionConfigList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = defaultConfigService.getAppShowFunctionConfigList(userTypeProjectConfigDTO, null);
            webPage.setRecordCount(list.size());
            model.addAttribute("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),userTypeProjectConfigDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != userTypeProjectConfigDTO.getCityId() && !"".equals(userTypeProjectConfigDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), userTypeProjectConfigDTO.getCityId(), userTypeProjectConfigDTO.getMenuId());
                model.addAttribute("projectList",projectList);
            }
            model.addAttribute("userTypeProjectConfigDTO", userTypeProjectConfigDTO);
            //获取用户可视项目配置列表
            List<Map<String, Object>> functionConfigList = defaultConfigService.getAppShowFunctionConfigList(userTypeProjectConfigDTO, webPage);
            //功能模块编码处理
            Map<String, Object> functionModuleMap = defaultConfigService.getFunctionModuleMap();
            for (int i = 0,length = functionConfigList.size(); i < length; i++){
                Map<String, Object> functionConfigMap = functionConfigList.get(i);
                String functionModulesStr = "";
                if (null != functionConfigMap.get("functionModules")){
                    String functionModules = functionConfigMap.get("functionModules").toString();
                    List<String> functionModuleList = Arrays.asList(functionModules.split(","));
                    for (String functionModuleStr : functionModuleList){
                        functionModulesStr += functionModuleMap.get(functionModuleStr)+",";
                    }
                    functionConfigMap.put("functionModuleStr",functionModulesStr);
                }
            }
            model.addAttribute("functionConfigList", functionConfigList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 编辑App项目可视功能模块配置
     * WeiYangDong_2017-04-12
     */
    @RequestMapping(value = "/toEditAppShowFunctionConfig.html")
    public ModelAndView toEditAppShowFunctionConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO,Model model){
        ModelAndView modelAndView = new ModelAndView("/user/AppShowFunctionConfigEdit");
        try{
            Map<String, Object> functionModuleMap = defaultConfigService.getFunctionModuleMap();
            model.addAttribute("functionModuleMap",functionModuleMap);
            List<Map<String, Object>> functionConfigList = defaultConfigService.getAppShowFunctionConfigList(userTypeProjectConfigDTO, null);
            if (null != functionConfigList && functionConfigList.size() > 0){
                Map<String, Object> functionConfig = functionConfigList.get(0);
                model.addAttribute("functionConfig",functionConfig);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 保存或更新App项目可视功能模块配置
     * WeiYangDong_2017-04-12
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateAppShowFunctionConfig")
    public Map<String,Object> saveOrUpdateAppShowFunctionConfig(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                                UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //设置操作人
            userTypeProjectConfigDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            defaultConfigService.saveOrUpdateAppShowFunctionConfig(userTypeProjectConfigDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 获取客户端可视项目配置列表
     * WeiYangDong_2017-04-13
     */
    @RequestMapping(value = "/clientShowProjectConfigList.html")
    public ModelAndView getClientShowProjectConfigList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                     UserTypeProjectConfigDTO userTypeProjectConfigDTO,
                                                     WebPage webPage,Model model){
        ModelAndView modelAndView = new ModelAndView("/user/ClientShowProjectConfigList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = defaultConfigService.getClientShowProjectConfigList(userTypeProjectConfigDTO, null);
            webPage.setRecordCount(list.size());
            model.addAttribute("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),userTypeProjectConfigDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != userTypeProjectConfigDTO.getCityId() && !"".equals(userTypeProjectConfigDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), userTypeProjectConfigDTO.getCityId(), userTypeProjectConfigDTO.getMenuId());
                model.addAttribute("projectList",projectList);
            }
            model.addAttribute("userTypeProjectConfigDTO", userTypeProjectConfigDTO);
            //获取客户端可视项目配置列表
            List<Map<String, Object>> projectConfigList = defaultConfigService.getClientShowProjectConfigList(userTypeProjectConfigDTO, webPage);
            //客户端ID处理
            Map<String,Object> clientConfigMap = new HashedMap();
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(userTypeProjectConfigDTO, null);
            for (int i = 0,length = clientConfigList.size(); i<length; i++){
                ClientConfigEntity clientConfigEntity = clientConfigList.get(i);
                clientConfigMap.put(String.valueOf(clientConfigEntity.getId()),clientConfigEntity.getClientName());
            }
            for (int i = 0,length = projectConfigList.size(); i < length; i++){
                Map<String, Object> projectConfigMap = projectConfigList.get(i);
                String clientIdsStr = "";
                if (null != projectConfigMap.get("clientIds") && !"".equals(projectConfigMap.get("clientIds"))){
                    String clientIds = projectConfigMap.get("clientIds").toString();
                    List<String> clientIdList = Arrays.asList(clientIds.split(","));
                    for (String clientId : clientIdList){
                        clientIdsStr += clientConfigMap.get(clientId)+",";
                    }
                    projectConfigMap.put("clientIdsStr",clientIdsStr);
                }
            }
            model.addAttribute("projectConfigList", projectConfigList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 编辑客户端可视项目配置
     * WeiYangDong_2017-04-13
     */
    @RequestMapping(value = "/toEditClientShowProjectConfig.html")
    public ModelAndView toEditClientShowProjectConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO,Model model){
        ModelAndView modelAndView = new ModelAndView("/user/ClientShowProjectConfigEdit");
        try{
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
            model.addAttribute("clientConfigList",clientConfigList);
            List<Map<String, Object>> projectConfigList = defaultConfigService.getClientShowProjectConfigList(userTypeProjectConfigDTO, null);
            if (null != projectConfigList && projectConfigList.size() > 0){
                Map<String, Object> projectConfig = projectConfigList.get(0);
                model.addAttribute("projectConfig",projectConfig);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 保存或更新客户端可视项目配置
     * WeiYangDong_2017-04-13
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateClientShowProjectConfig")
    public Map<String,Object> saveOrUpdateClientShowProjectConfig(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                                UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //设置操作人
            userTypeProjectConfigDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            defaultConfigService.saveOrUpdateClientShowProjectConfig(userTypeProjectConfigDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 获取客户端基础配置列表
     * WeiYangDong_2017-04-14
     */
    @RequestMapping(value = "/clientConfigList.html")
    public ModelAndView getClientConfigList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                       UserTypeProjectConfigDTO userTypeProjectConfigDTO,
                                                       WebPage webPage,Model model){
        ModelAndView modelAndView = new ModelAndView("/system/appversion/ClientConfigList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<ClientConfigEntity> list = defaultConfigService.getClientConfigList(userTypeProjectConfigDTO, null);
            webPage.setRecordCount(list.size());
            model.addAttribute("webPage", webPage);
            //检索条件回显
//            model.addAttribute("userTypeProjectConfigDTO", userTypeProjectConfigDTO);
            //获取用户可视项目配置列表
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(userTypeProjectConfigDTO, webPage);
            model.addAttribute("clientConfigList", clientConfigList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 编辑客户端基础配置
     * WeiYangDong_2017-04-14
     */
    @RequestMapping(value = "/toEditClientConfig")
    public Map<String,Object> toEditClientConfig(UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            if (null != userTypeProjectConfigDTO.getId() && !"".equals(userTypeProjectConfigDTO.getId())){
                ClientConfigEntity clientConfig = defaultConfigService.getClientConfigById(userTypeProjectConfigDTO.getId());
                resultMap.put("clientConfig",clientConfig);
                resultMap.put("error","0");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 保存或更新客户端基础配置
     * WeiYangDong_2017-04-14
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateClientConfig")
    public Map<String,Object> saveOrUpdateClientConfig(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                                  UserTypeProjectConfigDTO userTypeProjectConfigDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            defaultConfigService.saveOrUpdateClientConfig(userTypeProjectConfigDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

}
