package com.maxrocky.vesta.presentation.admin.controller.house;
import com.maxrocky.vesta.application.DTO.jsonDTO.UpdateInspectionDTO;
import com.maxrocky.vesta.application.inf.UpdateInspectionAdminService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
/**
 * Created by maxrocky on 2017/6/30.
 */
@Controller
@RequestMapping(value = "/inspection")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class UpdateInspectionController {
    @Autowired
    UpdateInspectionAdminService inspectionAdminService;

    @RequestMapping(value="/updateInspection")
    public ApiResult getSupplierList(@Valid UpdateInspectionDTO inspectionDTO){
        return new SuccessApiResult(inspectionAdminService.updateCustomerDeliveryList(inspectionDTO.getProjectNum()));
    }
}
