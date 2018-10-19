package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.domain.model.UserInformationEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Itzxs on 2018/7/6.
 */
@Controller
@RequestMapping(value="/mensure")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class MensureController {

    public String mensureList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity){
        return null;
    }
}
