package com.maxrocky.vesta.application.DTO.jsonDTO;

import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;
import com.maxrocky.vesta.application.DTO.admin.RectifyPlanDTO;


import com.maxrocky.vesta.application.model.PreInspectionList;
import com.maxrocky.vesta.application.adminDTO.*;

import com.maxrocky.vesta.application.DTO.json.HI0012.ThirdTypeJsonDTO;

import com.maxrocky.vesta.application.JsonDTO.StandAllDTO;
import com.maxrocky.vesta.application.jsonDTO.MaterialAllDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/5/4.
 * 质检APP统一接口数据封装类
 */
public class JinmaoAppDTO {
    private ConcealedAcceptanceDTO hiddens;                      //隐蔽验收模块
    private Object sites;                                        //工地开放模块

    private ModelReviewsDTO templet;                           //样板点评模块
    private FieldTestDTO trial;                                //关键工序模块
    private Object oneHouse;                                   //一户一档模块
    private Object material;                                   //材料验收模块
    private Object within;                                     //内部预验模块  后面模块谁负责的谁补上
    private Object inspection;                                 //日常巡检模块
    private List<StandAllDTO> sideStation;                     //旁站模块
    private List<MaterialAllDTO> materialPlans;                //材料验收模块
    private List<PlanQuestionDTO> questionList;                    //问题列表
    private List<RectificationListDTO> changeList;                   //保修单列表
    private List<RectificationListDTO> changeListzyl;                   //整改单列表
    private List<RectifyPlanDTO> soughtPlanList;          // 正式交房
    private List<RectifyPlanDTO> sitePlanList;              //供地开放
    private List<RectifyPlanDTO> internalPlanList;          //内部预验模块
    private List<Object> qmapList;                             //问题图列表
    private List<Object> changeNoteList;                       //整改记录列表
    private List<ThirdTypeJsonDTO> ThirdTypeList;              //三级分类list
    private List<PreInspectionList> PreInspectionList;          //楼栋清单
    private List<SupplierDataDTO> supplierList;                     //供应商列表
    public JinmaoAppDTO(){
        this.sideStation=new ArrayList<>();
        this.materialPlans=new ArrayList<>();
        this.questionList=new ArrayList<>();
        this.changeList=new ArrayList<>();
        this.changeListzyl=new ArrayList<>();
        this.soughtPlanList=new ArrayList<>();
        this.sitePlanList=new ArrayList<>();
        this.internalPlanList=new ArrayList<>();
        this.ThirdTypeList=new ArrayList<>();
        this.PreInspectionList=new ArrayList<>();
    }
    public ConcealedAcceptanceDTO getHiddens() {
        return hiddens;
    }

    public void setHiddens(ConcealedAcceptanceDTO hiddens) {
        this.hiddens = hiddens;
    }

    public FieldTestDTO getTrial() {
        return trial;
    }

    public void setTrial(FieldTestDTO trial) {
        this.trial = trial;
    }


    public List<PlanQuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<PlanQuestionDTO> questionList) {
        this.questionList = questionList;
    }

    public List<StandAllDTO> getSideStation() {
        return sideStation;
    }

    public void setSideStation(List<StandAllDTO> sideStation) {
        this.sideStation = sideStation;

    }


    public ModelReviewsDTO getTemplet() {
        return templet;
    }

    public void setTemplet(ModelReviewsDTO templet) {
        this.templet = templet;
    }


    public List<ThirdTypeJsonDTO> getThirdTypeList() {
        return ThirdTypeList;
    }

    public void setThirdTypeList(List<ThirdTypeJsonDTO> thirdTypeList) {
        ThirdTypeList = thirdTypeList;
    }
    public List<MaterialAllDTO> getMaterialPlans() {
        return materialPlans;
    }

    public void setMaterialPlans(List<MaterialAllDTO> materialPlans) {
        this.materialPlans = materialPlans;


    }

    public List<RectificationListDTO> getChangeList() {
        return changeList;
    }

    public void setChangeList(List<RectificationListDTO> changeList) {
        this.changeList = changeList;
    }

    public List<com.maxrocky.vesta.application.model.PreInspectionList> getPreInspectionList() {
        return PreInspectionList;
    }

    public void setPreInspectionList(List<com.maxrocky.vesta.application.model.PreInspectionList> preInspectionList) {
        PreInspectionList = preInspectionList;
    }

    public List<RectifyPlanDTO> getSoughtPlanList() {
        return soughtPlanList;
    }

    public void setSoughtPlanList(List<RectifyPlanDTO> soughtPlanList) {
        this.soughtPlanList = soughtPlanList;
    }

    public List<RectifyPlanDTO> getInternalPlanList() {
        return internalPlanList;
    }

    public void setInternalPlanList(List<RectifyPlanDTO> internalPlanList) {
        this.internalPlanList = internalPlanList;
    }

    public List<RectifyPlanDTO> getSitePlanList() {
        return sitePlanList;
    }

    public void setSitePlanList(List<RectifyPlanDTO> sitePlanList) {
        this.sitePlanList = sitePlanList;
    }

    public List<RectificationListDTO> getChangeListzyl() {
        return changeListzyl;
    }

    public void setChangeListzyl(List<RectificationListDTO> changeListzyl) {
        this.changeListzyl = changeListzyl;
    }

    public List<SupplierDataDTO> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<SupplierDataDTO> supplierList) {
        this.supplierList = supplierList;
    }
}
