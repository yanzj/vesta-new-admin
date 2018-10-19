package com.maxrocky.vesta.application.inspectionPlan.inf;

import com.maxrocky.vesta.application.inspectionPlan.DTO.PlanConditionDTO;
import com.maxrocky.vesta.application.inspectionPlan.DTO.PlanListDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Magic on 2017/6/20.
 */
public interface InspectionPlanService {
    /**
     * Describe:安全app 查询检查计划列表
     * CreateBy:Magic
     * CreateOn:2017-06-20
     */
    List<PlanListDTO> getPlanList(PlanConditionDTO planConditionDTO, WebPage webPage, UserInformationEntity userInformationEntity);
}
