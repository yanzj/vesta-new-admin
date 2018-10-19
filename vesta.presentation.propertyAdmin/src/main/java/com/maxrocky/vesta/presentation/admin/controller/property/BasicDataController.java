package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/5/23.
 * 基础数据更新(房产、报修、供应商信息)
 * 地块、项目、楼栋、单元、楼、房间
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserInformationEntity.class,String.class},value = {"authPropertystaff","menulist","secanViewlist"})
public class BasicDataController {
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private HouseProjectService houseProjectService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private HouseLocationService houseLocationService;
    @Autowired
    private BasicSupplierService basicSupplierService;
    @Autowired
    private AuthAgencyService authAgencyService;
    /**
     * 初始化数据
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/basicData.html")
    public String dataManage(@ModelAttribute("authPropertystaff")UserInformationEntity user,
                             @Valid PropertyCompanyMapDTO dataDTO,Model model){
        Map projectMap= houseProjectService.getProjectInfo();
        dataDTO.setPropertyProjectMap(projectMap);
        model.addAttribute("data", dataDTO);

        //搜索条件
        model.addAttribute("dataSearch", dataDTO);
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(user.getStaffId(),"4","1");
        if(function!=null){
            //数据更新管理 更新-项目房产 QCH40010066  更新-三级分类  QCH40010067  更新-房间位置  QCH40010068  更新-供应商  QCH40010069
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010066":
                        checkAuthFunctionDTO.setQch40010066("Y");
                        break;
                    case "QCH40010067":
                        checkAuthFunctionDTO.setQch40010067("Y");
                        break;
                    case "QCH40010068":
                        checkAuthFunctionDTO.setQch40010068("Y");
                        break;
                    case "QCH40010069":
                        checkAuthFunctionDTO.setQch40010069("Y");
                        break;
                }
            }
        }
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/property/propertyBasicData";
    }

    /**
     * 数据查询更新
     * param user
     * param dataDTO
     * param model
     * return
     */
    @RequestMapping(value = "/querySubmit")
    public ApiResult saveFreeBack(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                  @Valid PropertyCompanyMapDTO dataDTO,Model model){
        String result="";
        //调用查询房产接口
        //如果传入项目，则只更新当前项目数据;不传项目，则更新所有
        if(dataDTO!=null && !StringUtil.isEmpty(dataDTO.getModifyDate())) {
            if(!dataDTO.getProject().equals("0")) {
                HouseProjectDto projectDto=houseProjectService.getProjectById(dataDTO.getProject());
                String[] project = new String[5];
                project[0] = projectDto.getPinyinCode();
                //日期转换
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateUtils.parse(dataDTO.getModifyDate(), "yyyy-MM-dd HH:mm:ss"));
                calendar.getTime();
                if(project.length>0) {
                    result = basicHouseService.queryBasicInfo(project, calendar);
                }
            }else{//更新所有
                //日期转换
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateUtils.parse(dataDTO.getModifyDate(), "yyyy-MM-dd HH:mm:ss"));
                calendar.getTime();
                result = basicHouseService.queryBasicInfo(null, calendar);
            }
        }

        //调用查询三级分类接口
        if(dataDTO!=null && !StringUtil.isEmpty(dataDTO.getThirdLevel())) {
            result = classificationService.Classification();
        }

        //调用查询房屋位置接口
        if(dataDTO!=null && !StringUtil.isEmpty(dataDTO.getRoomId())) {
            result = houseLocationService.houseLocation();
        }

        //调用查询供应商接口
        //如果传入项目，则只更新当前项目数据;不传项目，则更新所有
        if(dataDTO!=null && !StringUtil.isEmpty(dataDTO.getProjectNum())) {
            if(!dataDTO.getProjectNum().equals("0")) {
                HouseProjectDto projectDto=houseProjectService.getProjectById(dataDTO.getProjectNum());
                String[] project = new String[5];
                project[0] = projectDto.getPinyinCode();
                if(project.length>0) {
                    result = basicSupplierService.queryBasicInfo(project);
                }
            }else{//更新所有
                result = basicSupplierService.queryBasicInfo(null);
            }
        }
        return new SuccessApiResult(result);
    }
}
