package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.BlacklistDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.BlacklistService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.management.ObjectName;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 黑名单功能模块Controller
 * Created by WeiYangDong on 2017/11/21.
 */
@Controller
@RequestMapping(value = "/blacklist")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class BlacklistController {

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    StaffUserService staffUserService;

    /**
     * 获取黑名单列表
     */
    @RequestMapping(value = "/getBlacklistList.html")
    public ModelAndView getBlacklistList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                         BlacklistDTO blacklistDTO,
                                         WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/BlacklistList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<BlacklistDTO> list = blacklistService.getBlacklistList(blacklistDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),blacklistDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != blacklistDTO.getCityId() && !"".equals(blacklistDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), blacklistDTO.getCityId(), blacklistDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("blacklistDTO",blacklistDTO);
            //执行查询
            List<BlacklistDTO> blacklistList = blacklistService.getBlacklistList(blacklistDTO, webPage);
            modelAndView.addObject("blacklistList",blacklistList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 获取黑名单列表(JSON格式)
     */
    @ResponseBody
    @RequestMapping(value = "/getBlacklistListJson")
    public Map<String,Object> getBlacklistListJson(){
        Map<String,Object> resultMap = new HashedMap();
        try{
            List<BlacklistDTO> blacklistList = blacklistService.getBlacklistList(null, null);
            resultMap.put("blacklistList",blacklistList);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 物理删除黑名单信息
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBlacklist")
    public Map<String,Object> deleteBlacklist(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              BlacklistDTO blacklistDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            if (null != blacklistDTO.getId() && !"".equals(blacklistDTO.getId())){
                blacklistService.deleteBlacklist(blacklistDTO);
            }
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 去编辑黑名单信息
     */
    @RequestMapping(value = "/toEditBlacklist.html")
    public ModelAndView toEditBlacklist(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                        BlacklistDTO blacklistDTO){
        ModelAndView modelAndView = new ModelAndView("/community/BlacklistEdit");
        try{
            BlacklistDTO blacklistInfo = null;
            if (null != blacklistDTO.getId() && !"".equals(blacklistDTO.getId())){
                //获取黑名单信息
                blacklistInfo = blacklistService.getBlacklistById(blacklistDTO);
                //设置手机号码集合
                if (blacklistInfo.getListType() == 1 && null != blacklistInfo.getPhoneCollection()){
                    List<String> phoneList = Arrays.asList(blacklistInfo.getPhoneCollection().split(","));
                    blacklistInfo.setPhoneList(phoneList);
                }
                if (blacklistInfo.getListType() == 2 && null != blacklistInfo.getHouseCollection()){
                    String scopeTree = blacklistService.getScopeTreeByHouseCollection(blacklistInfo);
                    modelAndView.addObject("scopeJson",scopeTree);
                }
            }
            modelAndView.addObject("blacklistInfo",blacklistInfo);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),blacklistDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != blacklistInfo && null != blacklistInfo.getCityId() && !"".equals(blacklistInfo.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), blacklistInfo.getCityId(), blacklistDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 保存或更新黑名单信息
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateBlacklist", method = RequestMethod.POST, produces="application/json")
    public Map<String,Object> saveOrUpdateBlacklist(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           BlacklistDTO blacklistDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //校验黑名单名称唯一性
            int i = blacklistService.checkBlacklistName(blacklistDTO);
            if (i > 0){
                resultMap.put("error",1);
            }else{
                //设置操作人
                blacklistDTO.setModifyBy(userPropertystaffEntity.getStaffName());
                blacklistService.saveOrUpdateBlacklist(blacklistDTO);
                resultMap.put("error",0);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 通过黑名单房产集合获取房产范围树形数据结构
     */
    @ResponseBody
    @RequestMapping(value = "/getScopeTreeByHouseCollection")
    public Map<String,Object> getScopeTreeByHouseCollection(BlacklistDTO blacklistDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            resultMap.put("error",0);
            String scopeTree = blacklistService.getScopeTreeByHouseCollection(blacklistDTO);
            resultMap.put("scopeTree",scopeTree);
        }catch (Exception e){
            resultMap.put("error",-1);
            e.printStackTrace();
        }
        return resultMap;
    }
}
