package com.maxrocky.vesta.application.DTO.jsonDTO;

import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.AppFeedBackDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.DeliveryInformationDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.HouseReceptionDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.InternalDTO;
import com.maxrocky.vesta.application.JsonDTO.ReceiveStandDTO;
import com.maxrocky.vesta.application.JsonDTO.RecodeDTO;
import com.maxrocky.vesta.application.adminDTO.CaseRepairDTO;
import com.maxrocky.vesta.application.adminDTO.PlanQuestionDTO;
import com.maxrocky.vesta.application.adminDTO.PlanSetDTO;
import com.maxrocky.vesta.application.jsonDTO.ExitReceiveDTO;
import com.maxrocky.vesta.application.jsonDTO.MaterialReceiveDTO;


import java.util.List;

/**
 * Created by Magic on 2016/5/15.
 */
public class RepairJosnPostDTO {
    private List<RectificationListDTO> repairList;     //整改单列表
    private List<ReceiveStandDTO> standList;           //旁站列表
    private List<RecodeDTO> standRecodeList;           //旁站记录列表
    private List<AppFeedBackDTO> feedbackList;         //意见反馈列表
    private List<MaterialReceiveDTO> materialPlanList; //材料验收列表
    private List<ExitReceiveDTO> materialExitList;     //材料验收退场列表
    private List<HouseReceptionDTO> HouseReceptionList;// 工地开放评价信息
    private List<InternalDTO> Internallist;             //内部语言
    private List<DeliveryInformationDTO> DeliveryList;//交房信息表
    private List<PlanSetDTO> planSetList;//批次列表
    private List<PlanQuestionDTO> questionList;//问题列表
    private List<CaseRepairDTO> questionRepairList;//问题整改记录



    public List<RectificationListDTO> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<RectificationListDTO> repairList) {
        this.repairList = repairList;
    }

    public List<ReceiveStandDTO> getStandList() {
        return standList;
    }

    public void setStandList(List<ReceiveStandDTO> standList) {
        this.standList = standList;
    }

    public List<AppFeedBackDTO> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<AppFeedBackDTO> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public List<MaterialReceiveDTO> getMaterialPlanList() {
        return materialPlanList;
    }

    public void setMaterialPlanList(List<MaterialReceiveDTO> materialPlanList) {
        this.materialPlanList = materialPlanList;
    }

    public List<ExitReceiveDTO> getMaterialExitList() {
        return materialExitList;
    }

    public void setMaterialExitList(List<ExitReceiveDTO> materialExitList) {
        this.materialExitList = materialExitList;
    }
    public List<HouseReceptionDTO> getHouseReceptionList() {
        return HouseReceptionList;
    }

    public void setHouseReceptionList(List<HouseReceptionDTO> houseReceptionList) {
        HouseReceptionList = houseReceptionList;
    }

    public List<DeliveryInformationDTO> getDeliveryList() {
        return DeliveryList;
    }

    public void setDeliveryList(List<DeliveryInformationDTO> deliveryList) {
        DeliveryList = deliveryList;

    }

    public List<PlanSetDTO> getPlanSetList() {
        return planSetList;
    }

    public void setPlanSetList(List<PlanSetDTO> planSetList) {
        this.planSetList = planSetList;
    }

    public List<PlanQuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<PlanQuestionDTO> questionList) {
        this.questionList = questionList;
    }

    public List<CaseRepairDTO> getQuestionRepairList() {
        return questionRepairList;
    }

    public void setQuestionRepairList(List<CaseRepairDTO> questionRepairList) {
        this.questionRepairList = questionRepairList;
    }

    public List<RecodeDTO> getStandRecodeList() {
        return standRecodeList;
    }

    public void setStandRecodeList(List<RecodeDTO> standRecodeList) {
        this.standRecodeList = standRecodeList;
    }


    public List<InternalDTO> getInternallist() {
        return Internallist;
    }

    public void setInternallist(List<InternalDTO> internallist) {
        Internallist = internallist;
    }
}
