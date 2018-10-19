package com.maxrocky.vesta.presentation.admin.controller.house;

import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.admin.dto.ActivityAdminDto;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.HouseTypeLabelService;
import com.maxrocky.vesta.application.inf.HouseTypeService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.RoomLocationEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/3.
 */
@Controller
@RequestMapping(value = "/housetype")
@SessionAttributes(types={UserInformationEntity.class,String.class},value = {"authPropertystaff","menulist","secanViewlist"})
public class HouseTypeController {

    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private HouseTypeLabelService houseTypeLabelService;

    @Autowired
    private HouseProjectService houseProjectService;

    @Autowired
    private AuthAgencyService authAgencyService;

    @RequestMapping(value = "/houseTypeManage.html")
    public String houseTypeManage(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO,WebPage webPage,Model model){

        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<HouseTypeDTO> houseTypeList = houseTypeService.getHouseTypeList(houseTypeDTO, webPage);
        Map projects =houseProjectService.getProjectsNum();

        model.addAttribute("houseTypeList",houseTypeList);
        model.addAttribute("houseTypeDTO", houseTypeDTO);
        model.addAttribute("projects",projects);

        //房间位置列表数据
        List<RoomLocationEntity> roomLocations = houseTypeLabelService.getRoomLocations();
        model.addAttribute("roomLocations",roomLocations);
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有  qch40010056; //搜素   qch40010057; //新增   qch40010059; //原位标注   qch40010061 修改   qch40010063   删除  qch40010060; //原位标注-保存
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010056":
                        checkAuthFunctionDTO.setQch40010056("Y");
                        break;
                    case "QCH40010057":
                        checkAuthFunctionDTO.setQch40010057("Y");
                        break;
                    case "QCH40010059":
                        checkAuthFunctionDTO.setQch40010059("Y");
                        break;
                    case "QCH40010060":
                        checkAuthFunctionDTO.setQch40010060("Y");
                        break;
                    case "QCH40010061":
                        checkAuthFunctionDTO.setQch40010061("Y");
                        break;
                    case "QCH40010063":
                        checkAuthFunctionDTO.setQch40010063("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        return "/house/houseTypeManage";
    }

    /**
     * 跳转到添加页面
     * @param userInformationEntity
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/houseTypeAdd")
    public String houseTypeAdd(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO,WebPage webPage,Model model){
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有  qch40010058; //保存
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010058":
                        checkAuthFunctionDTO.setQch40010058("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        return "/house/houseTypeAdd";
    }

    /**
     * 新增户型
     * @param userInformationEntity
     * @return
     */
    @RequestMapping(value = "/houseTypeSave",method = RequestMethod.POST)
    public ModelAndView houseTypeSave(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO){

        houseTypeService.saveHouseType(userInformationEntity,houseTypeDTO);
        return new ModelAndView("redirect:../housetype/houseTypeManage.html");
    }


    /**
     * 删除户型
     * @param userInformationEntity
     * @param houseTypeDTO
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/houseTypeDelete")
    public ModelAndView houseTypeDelete(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO,WebPage webPage,Model model){

        houseTypeService.deleteHouseType(houseTypeDTO);
        return new ModelAndView("redirect:../housetype/houseTypeManage.html");
    }

    /**
     * 修改户型
     * @param userInformationEntity
     * @param houseTypeDTO
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/houseTypeUpdate")
    public String houseTypeUpdate(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO,WebPage webPage,Model model){
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有  qch40010062; //修改-保存
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010062":
                        checkAuthFunctionDTO.setQch40010062("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("houseType", houseTypeService.getHouseTypeById(houseTypeDTO));
        return "/house/houseTypeUpdate";
    }

    /**
     * 修改户型
     * @param userInformationEntity
     * @return
     */
    @RequestMapping(value = "/saveUpdate",method = RequestMethod.POST)
    public ModelAndView saveUpdate(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO){

        houseTypeService.updateHouseType(houseTypeDTO);
        return new ModelAndView("redirect:../housetype/houseTypeManage.html");
    }

    /**
     * 通过户型Id获取户型标注列表
     * @param houseTypeDTO
     * @return
     */
    @RequestMapping(value = "/getHouseTypeLabels", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getHouseTypeLabels(@Valid HouseTypeDTO houseTypeDTO){
        try {
            return houseTypeLabelService.getHouseTypeLabels(houseTypeDTO);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorApiResult("error_00000035");
        }
    }
    /**
     * 通过户型type获取户型标注信息
     * @param houseTypeDTO
     * @return
     */
    @RequestMapping(value = "/getHouseLocationList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getHouseLocationList(@Valid HouseTypeDTO houseTypeDTO){
        try {
            return houseTypeLabelService.getHouseLocaltion(houseTypeDTO.getType());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorApiResult("error_00000035");
        }
    }
    /**
     * 通过户型Id获取户型标注列表
     * @param houseTypeDTO
     * @return
     */
    @RequestMapping(value = "/getHouseTypeLabelByTypeId", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getHouseTypeLabelByTypeId(@Valid HouseTypeDTO houseTypeDTO){
        try {
            return new SuccessApiResult(houseTypeLabelService.getHouseTypeLabelByTypeId(houseTypeDTO));
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorApiResult("error_00000035");
        }
    }

    /**
     * 保存户型标注列表信息
     */
    @RequestMapping(value = "/saveHouseTypeLabels",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult saveOrUpdateHouseTypeLabels(@Valid HouseTypeDTO houseTypeDTO){
        try {
            houseTypeLabelService.saveHouseTypeLabels(houseTypeDTO);
            return new SuccessApiResult();
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorApiResult("error_00000035");
        }
    }

    /**
     * 房间位置列表
     */
    @ResponseBody
    @RequestMapping(value = "/getRoomLocations")
    public Map<String,Object> getRoomLocations(){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            List<RoomLocationEntity> roomLocations = houseTypeLabelService.getRoomLocations();

            resultMap.put("data",roomLocations);
            resultMap.put("code", 0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code", 1);
        }

        return resultMap;
    }

    /**
     * 户型详情
     * @param userInformationEntity
     * @param houseTypeDTO
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/houseTypeDetail")
    public String houseTypeDetail(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO,WebPage webPage,Model model){

        model.addAttribute("houseType", houseTypeService.getHouseTypeById(houseTypeDTO));
        return "/house/houseTypeDetail";
    }

    /**
     * 户型详情
     * @param userInformationEntity
     * @param houseTypeDTO
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/houseTypeDetailForRoom")
    public String houseTypeDetailForRoom(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseTypeDTO houseTypeDTO,WebPage webPage,Model model){
        if(houseTypeDTO.getUnitNum() != null){
            houseTypeDTO.setUnitNum(houseTypeDTO.getUnitNum().replace("@$@", "#"));
        }
        if(houseTypeDTO.getBuildingNum() != null){
            houseTypeDTO.setBuildingNum(houseTypeDTO.getBuildingNum().replace("@$@", "#"));
        }
        model.addAttribute("houseType", houseTypeService.getHouseTypeById(houseTypeDTO));
        model.addAttribute("typeSearch", houseTypeDTO);
        return "/house/houseTypeDetailForRoom";
    }

}
