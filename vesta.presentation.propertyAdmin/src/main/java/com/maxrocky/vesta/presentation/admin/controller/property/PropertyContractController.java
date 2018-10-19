package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyContractDTO;
import com.maxrocky.vesta.application.DTO.PropertyManageDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/8 16:50
 * @deprecated 合同查询模块Controller
 */
@Controller
@RequestMapping(value = "/propertyContract")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyContractController {

    @Autowired
    StaffUserService staffUserService;
    @Autowired
    HouseBuildingService houseBuildingService;
    @Autowired
    HouseUnitService houseUnitService;
    @Autowired
    PropertyContractService propertyContractService;

    /**
     * 合同导入模板下载
     */
    @ResponseBody
    @RequestMapping(value="/downLoadExcelTemplate")
    public String downLoadExcelTemplate(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            propertyContractService.downLoadExcelTemplate(httpServletRequest, httpServletResponse);
            return "模板下载完成！";
        }catch (Exception e){
            e.printStackTrace();
            return "模板下载错误！";
        }
    }

    /**
     * 合同导入模板及导入数据下载
     */
    @ResponseBody
    @RequestMapping(value="/downLoadExcelTemplateAndData")
    public String downLoadExcelTemplateAndData(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,PropertyContractDTO propertyContractDTO){
        try {
            propertyContractService.downLoadExcelTemplateAndData(httpServletResponse,httpServletRequest,propertyContractDTO);
            return "0";
        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     * 获取合同管理列表
     */
    @RequestMapping(value = "/getPropertyContractList.html")
    public String getPropertyContractList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                        PropertyContractDTO propertyContractDTO,
                                        WebPage webPage,
                                        Model model
    ){
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyContractDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyContractDTO.getScopeId() && !"".equals(propertyContractDTO.getScopeId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(),propertyContractDTO.getScopeId(),propertyContractDTO.getMenuId());
                model.addAttribute("projectList",projectList);
                if (null != propertyContractDTO.getProjectCode() && !"".equals(propertyContractDTO.getProjectCode())){
                    //项目不为空,回显地块列表
                    Map areaMap =houseBuildingService.getAreaListByProjectNum(propertyContractDTO.getProjectCode());
                    model.addAttribute("areaMap",areaMap);
                    if (null != propertyContractDTO.getBlockCode() && !"".equals(propertyContractDTO.getBlockCode())){
                        //地块不为空,回显楼栋列表
                        Map buildMap =houseBuildingService.getBuildListByBlockNum(propertyContractDTO.getBlockCode());
                        model.addAttribute("buildMap",buildMap);
                        if (null != propertyContractDTO.getBuildingNum() && !"".equals(propertyContractDTO.getBuildingNum())){
                            //楼栋不为空,回显单元列表
                            Map unitMap = houseUnitService.getUnitMapByBuildingId(propertyContractDTO.getBuildingNum());
                            model.addAttribute("unitMap",unitMap);
                        }
                    }
                }
            }
            model.addAttribute("propertyContractDTO",propertyContractDTO);
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            propertyContractDTO.setRoleScopeList(roleScopeList);
            //执行查询
            List<PropertyContractDTO> propertyList = propertyContractService.getPropertyContractList(propertyContractDTO, webPage);
            model.addAttribute("propertyList",propertyList);
            model.addAttribute("isExcel",webPage.getRecordCount());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/property/PropertyContractList";
    }

    /**
     * 批量导入 WeiYangDong_2018-03-08
     */
    @ResponseBody
    @RequestMapping(value = "/uploadPropertyExcel")
    public Map<String,Object> uploadPropertyExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                                  HttpServletRequest httpServletRequest) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("excelFile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            Map<String, Object> flagMap = propertyContractService.importEmployeeByPoi(user, fis);
            if (flagMap.containsKey("successNum")){
                resultMap.put("error", "0");
                resultMap.put("flag",flagMap);
            }else{
                resultMap.put("error", "-1");
            }
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }
}
