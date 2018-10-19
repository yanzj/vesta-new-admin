package com.maxrocky.vesta.presentation.admin.controller.plan;

import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by chen on 2016/5/24.
 * 材料验收
 */
@Controller
@RequestMapping(value = "/material")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class MaterialController {
}
