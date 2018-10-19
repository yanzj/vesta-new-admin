package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.StartPageDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;


/**
 * Created by sunmei on 2016/2/29.
 */
public interface StartPageService {
    String createStartPage(UserPropertyStaffEntity userPropertystaffEntit,StartPageDTO startPageDTO);
}
