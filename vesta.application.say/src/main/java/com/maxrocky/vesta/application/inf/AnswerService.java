package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.AnswerAdminDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

/**
 * Created by chen on 2016/2/20.
 */

public interface AnswerService {
    void AddAnswer(UserPropertyStaffEntity userPropertyStaffEntity,AnswerAdminDTO answerAdminDTO);
}
