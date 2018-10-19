package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.DTO.PropertyRectifyCRMListDTO;
import com.maxrocky.vesta.application.DTO.WorkApportionDTO;
import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;
import com.maxrocky.vesta.application.DTO.json.HI0001.ProjectReturnJsonDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.ThirdTypeJsonDTO;
import com.maxrocky.vesta.application.adminDTO.*;
import com.maxrocky.vesta.application.inf.PlanInfoService;
import com.maxrocky.vesta.application.inf.RectificationService;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;

import com.maxrocky.vesta.domain.repository.*;

import com.maxrocky.vesta.domain.repository.PlanCaseRepository;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import com.maxrocky.vesta.domain.repository.SupplierCRMRepository;

import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mql on 2016/5/18.
 */
@Service
public class PlanInfoServiceImpl implements PlanInfoService{

    @Autowired
    private PlanCaseRepository planCaseRepository;

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    @Autowired
    private RepairClientService repairClientService;

    @Autowired
    private SupplierCRMRepository supplierCRMRepository;

    @Autowired
    private RectificationService rectificationService;

    @Autowired
    HouseProjectRepository houseProjectRepository;

    @Autowired
    SecondClassificationCRMRepository secondClassificationCRMRepository;

    @Autowired
    FirstClassificationCRMRepository firstClassificationCRMRepository;

    @Autowired
    ThirdClassificationCRMRepository thirdClassificationCRMRepository;



    @Override

    public PlanCaseInfoDTO getQuestionList(List<String> userProArr,List<String> planType,Date beginTime) {
        List<Object[]> qList = planCaseRepository.getQuestionList(userProArr,planType,beginTime);
        String finalTime = "";
        if(qList !=null && !qList.isEmpty()){
            finalTime = DateUtils.format((Date)qList.get(0)[38]);
            List<PlanQuestionDTO> planQuestionList = new ArrayList<PlanQuestionDTO>();
            for(Object[] obj : qList){
                PlanQuestionDTO pq = new PlanQuestionDTO();
                pq.setCaseId(obj[0] == null ? "" : obj[0].toString());
                pq.setCaseTitle(obj[1] == null ? "" : obj[1].toString());
                pq.setCasePlace(obj[2] == null ? "" : obj[2].toString());
                pq.setOneType(obj[3] == null ? "" : obj[3].toString());
                pq.setTwoType(obj[4] == null ? "" : obj[4].toString());
                pq.setCaseDesc(obj[5] == null ? "" : obj[5].toString());
                pq.setCaseState(obj[6] == null ? "" : obj[6].toString());
                pq.setRoomId(obj[7] == null ? "" : obj[7].toString());
                pq.setAddress(obj[8] == null ? "" : obj[8].toString());
                pq.setRoomName(obj[9] == null ? "" : obj[9].toString());
                pq.setUnitId(obj[10] == null ? "" : obj[10].toString());
                pq.setUnitName(obj[11] == null ? "" : obj[11].toString());
                pq.setFloor(obj[12] == null ? "" : obj[12].toString());
                pq.setBuildingId(obj[13] == null ? "" : obj[13].toString());
                pq.setBuildingName(obj[14] == null ? "" : obj[14].toString());
                pq.setProjectId(obj[15] == null ? "" : obj[15].toString());
                pq.setProjectName(obj[16] == null ? "" : obj[16].toString());
                pq.setAreaId(obj[17] == null ? "" : obj[17].toString());
                pq.setAreaId(obj[18] == null ? "" : obj[18].toString());
                pq.setPlanType(obj[19] == null ? "" : obj[19].toString());
                pq.setPlanName(obj[20] == null ? "" : obj[20].toString());
                pq.setThreeType(obj[21] == null ? "" : obj[21].toString());
                pq.setLimitTime(Integer.parseInt(obj[22].toString()));
                pq.setComments(obj[23] == null ? "" : obj[23].toString());
                pq.setContractor(obj[24] == null ? "" : obj[24].toString());
                pq.setResponsibleUnit(obj[25] == null ? "" : obj[25].toString());
                pq.setSetId(obj[26] == null ? "" : obj[26].toString());
                pq.setCreateBy(obj[27] == null ? "" : obj[27].toString());
                pq.setCreateDate(DateUtils.format((Date) obj[28]));
                pq.setStage(obj[29] == null ? "" : obj[29].toString());
                pq.setResponsibleUnit2(obj[30] == null ? "" : obj[30].toString());
                pq.setResponsibleUnit2(obj[31] == null ? "" : obj[31].toString());
                pq.setDispatchUnit(obj[32] == null ? "" : obj[32].toString());
                pq.setRoomNum(obj[33] == null ? "" : obj[33].toString());
                pq.setCreatePhone(obj[34] == null ? "" : obj[34].toString());
                pq.setCreateName(obj[35] == null ? "" : obj[35].toString());
                pq.setCreateAddress(obj[8] == null ? "" : obj[8].toString());
                pq.setxCoordinates(obj[36] == null ? new BigDecimal(0) : new BigDecimal(obj[36].toString()));
                pq.setyCoordinates(obj[37] == null ? new BigDecimal(0) : new BigDecimal(obj[37].toString()));
                pq.setModifyDate(DateUtils.format((Date)obj[37]));

                //问题图片
                List<QuestionImageDTO> caseImageList = new ArrayList<QuestionImageDTO>();
                List<PropertyImageEntity> planCaseList = propertyImageRepository.getImageByType(pq.getCaseId(),"6");//问题图片List
                if(planCaseList != null && !planCaseList.isEmpty()){
                    for(PropertyImageEntity p :planCaseList){
                        QuestionImageDTO image = new QuestionImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        caseImageList.add(image);
                    }
                }
                pq.setCaseImageList(caseImageList);

                List<CaseRepairDTO> checkRepairList = new ArrayList<CaseRepairDTO>();
                List<PlanCaseRepairEntity> repairEntityList = planCaseRepository.getCaseRepairList(pq.getCaseId());
                if(repairEntityList!=null && !repairEntityList.isEmpty()){
                    for(PlanCaseRepairEntity planCaseRepairEntity : repairEntityList){
                        CaseRepairDTO checkRepairDTO = new CaseRepairDTO();
                        checkRepairDTO.setId(planCaseRepairEntity.getId());
                        checkRepairDTO.setTimes(planCaseRepairEntity.getTimes());
                        checkRepairDTO.setCaseId(planCaseRepairEntity.getCaseId());
                        checkRepairDTO.setRepairBy(planCaseRepairEntity.getRepairBy());
                        checkRepairDTO.setRepairPhone(planCaseRepairEntity.getRepairPhone());
                        checkRepairDTO.setRepairDate(planCaseRepairEntity.getRepairDate());
                        checkRepairDTO.setRepairDesc(planCaseRepairEntity.getRepairDesc());
                        checkRepairDTO.setReviewBy(planCaseRepairEntity.getReviewBy());
                        checkRepairDTO.setReviewPhone(planCaseRepairEntity.getReviewPhone());
                        checkRepairDTO.setReviewDate(planCaseRepairEntity.getReviewDate());
                        checkRepairDTO.setReviewDesc(planCaseRepairEntity.getReviewDesc());
                        checkRepairDTO.setReviewOpinion(planCaseRepairEntity.getReviewOpinion());
                        checkRepairDTO.setSupervisor(planCaseRepairEntity.getSupervisor());
                        checkRepairDTO.setSupervisorPhone(planCaseRepairEntity.getSupervisorPhone());
                        checkRepairDTO.setSupervisorDate(planCaseRepairEntity.getSupervisorDate());
                        checkRepairDTO.setSupervisorOpinion(planCaseRepairEntity.getSupervisorOpinion());
                        checkRepairDTO.setSupervisorDesc(planCaseRepairEntity.getSupervisorDesc());

                        //整改记录，复查图片
                        List<QuestionImageDTO> repairImageList = new ArrayList<QuestionImageDTO>();
                        List<PropertyImageEntity> planCaseImageEntityList = propertyImageRepository.getImageByType(checkRepairDTO.getId(), "6");//整改记录复查图片List
                        if(planCaseImageEntityList != null && !planCaseImageEntityList.isEmpty()){
                            for(PropertyImageEntity p :planCaseImageEntityList){
                                QuestionImageDTO image = new QuestionImageDTO();
                                image.setCaseId(p.getImgFkId());
                                image.setImageId(p.getImageId());
                                image.setImageUrl(p.getImagePath());
                                repairImageList.add(image);
                            }
                        }
                        checkRepairDTO.setReviewImgList(repairImageList);

                        checkRepairList.add(checkRepairDTO);
                    }
                }
                pq.setRepairList(checkRepairList);
                planQuestionList.add(pq);
            }
            PlanCaseInfoDTO planCaseInfoDTO = new PlanCaseInfoDTO();
            planCaseInfoDTO.setFinalTime(finalTime);
            planCaseInfoDTO.setQuestionList(planQuestionList);
            return planCaseInfoDTO;
        }else{
            return null;
        }
    }
    /***
     *
     * 整改单
     * */
    @Override
    public List<RectificationListDTO> getOrderList(Object[] userProArr,String dispatchUnit,String id) {

        List<Object[]> qList = planCaseRepository.getOrderList(userProArr, dispatchUnit);//
        if(qList !=null && !qList.isEmpty()){
            List<RectificationListDTO> planQuestionList = new ArrayList<RectificationListDTO>();
            for(Object[] obj : qList){
                String dealpeopleid="";
                String start="";
                RectificationListDTO rl = new RectificationListDTO();
                rl.setType("1");
                rl.setRepairId(obj[0] == null ? "" : obj[0].toString());////报修单号
                rl.setRoomId(obj[1] == null ? "" : obj[1].toString());//房间id
                rl.setRoomNum(obj[2] == null ? "" : obj[2].toString());//房间编码
                rl.setAddress(obj[3] == null ? "" : obj[3].toString());//房屋地址
                rl.setMemberId(obj[4] == null ? "" : obj[4].toString());//报修人会员编号
                rl.setUserPhone(obj[5] == null ? "" : obj[5].toString());//报修人电话
                rl.setUserAddress(obj[3] == null ? "" : obj[3].toString());//报修人地址
                rl.setUsername(obj[6] == null ? "" : obj[6].toString());//报修人姓名
                rl.setCreateDate(obj[7] == null ? "" : obj[7].toString());//报修时间 登记时间
                rl.setClassifyOne(obj[8] == null ? "" : obj[8].toString());//一级分类
                rl.setClassifyTwo(obj[9] == null ? "" : obj[9].toString());//二级分类
                rl.setClassifyThree(obj[10] == null ? "" : obj[10].toString());//三级分类 问题类型
                rl.setRoomLocation(obj[11] == null ? "" : obj[11].toString());//房屋位置name
                rl.setState(obj[12] == null ? "" : obj[12].toString());//单据状态 已完成未完成
                start=(obj[12] == null ? "" : obj[12].toString());
                rl.setContent(obj[13] == null ? "" : obj[13].toString());//基本内容 描述
                rl.setDutyCompanyOne(obj[14] == null ? "" : obj[14].toString());//责任单位1
                rl.setProjectname(obj[16] == null ? "" : obj[16].toString());//所属项目
                rl.setProjectid(obj[15] == null ? "" : obj[15].toString());//项目id
                rl.setBuildnum(obj[17] == null ? "" : obj[17].toString());//楼栋编码
                rl.setPlantype(obj[18] == null ? "" : obj[18].toString());//活动编号

                //问题图片
                List<ProjectReturnJsonDTO> caseImageList = new ArrayList<ProjectReturnJsonDTO>();
                List<PropertyImageEntity> planCaseList = propertyImageRepository.getImageByType(rl.getRepairId(),"5");//问题图片List
                if(planCaseList != null && !planCaseList.isEmpty()){
                    for(PropertyImageEntity p :planCaseList){
                        ProjectReturnJsonDTO image = new ProjectReturnJsonDTO();
                        image.setId(p.getImgFkId());
                        image.setName(p.getImageId());
                        caseImageList.add(image);
                    }
                }
                rl.setImageList(caseImageList);

                List<CaseRepairDTO> checkRepairList = new ArrayList<CaseRepairDTO>();
                List<PlanCaseRepairEntity> repairEntityList = planCaseRepository.getCaseRepairList(rl.getRepairId());
                if(repairEntityList!=null && !repairEntityList.isEmpty()){
                    PlanCaseRepairEntity planCaseRepairEntity = repairEntityList.get(0);
                    rl.setDealPeoplename(planCaseRepairEntity.getRepairName() == null ? "" : planCaseRepairEntity.getRepairName());//处理人姓名
                    rl.setDealPeople(planCaseRepairEntity.getRepairBy() == null ? "" : planCaseRepairEntity.getRepairBy());//处理人
                    dealpeopleid=planCaseRepairEntity.getRepairBy();
                    rl.setDealPhone(planCaseRepairEntity.getRepairPhone() == null ? "" : planCaseRepairEntity.getRepairPhone());//处理人电话
                    rl.setDealResult(planCaseRepairEntity.getRepairDesc() == null ? "" : planCaseRepairEntity.getRepairDesc());//处理结果
                    rl.setDealCompleteDate(planCaseRepairEntity.getRepairDate().toString() == null ? "" : planCaseRepairEntity.getRepairDate().toString());//处理人完工时间
                    rl.setTaskDate(planCaseRepairEntity.getRepairDate().toString() == null ? "" : planCaseRepairEntity.getRepairDate().toString()) ;
                    rl.setMode("");
                    //整改记录，复查图片
                    List<ProjectReturnJsonDTO> repairImageList = new ArrayList<ProjectReturnJsonDTO>();
                    List<PropertyImageEntity> planCaseImageEntityList = propertyImageRepository.getImageByType(planCaseRepairEntity.getId(), "6");//整改记录复查图片List
                    if(planCaseList != null && !planCaseList.isEmpty()){
                        for(PropertyImageEntity p :planCaseList){
                            ProjectReturnJsonDTO image = new ProjectReturnJsonDTO();
                            image.setId(p.getImgFkId());
                            image.setName(p.getImageId());
                            repairImageList.add(image);
                        }
                    }
                    rl.setImgList(repairImageList);
                }
//                if(start.equals("accepted")){
//                    planQuestionList.add(rl);
//                }else if(id.equals(dealpeopleid)){
//                    planQuestionList.add(rl);
//                }else {
//                    continue;
//                }
                planQuestionList.add(rl);
            }
            return planQuestionList;
        }else{
            return null;
        }
    }

    @Override
    public Object[] getUserProjectArray(String userId) {
        List<UserProjectEntity> userProjectList = planCaseRepository.getUserProject(userId);
        if(userProjectList!=null && !userProjectList.isEmpty()){
            Object[] userProArr = new Object[userProjectList.size()];
            for(int i = 0;i<userProjectList.size();i++){
                userProArr[i]=userProjectList.get(i).getProjectId();
            }
            return userProArr;
        }else{
            return null;
        }
    }

    @Override
    public void saveSet(List<PlanSetDTO> planSetDTOList) {
        if(planSetDTOList!=null && !planSetDTOList.isEmpty()){
            for(PlanSetDTO planSetDTO : planSetDTOList){
                PlanCaseSetEntity planCaseSetEntity = new PlanCaseSetEntity();
                planCaseSetEntity.setSetId(planSetDTO.getSetId());
                planCaseSetEntity.setPlanType(planSetDTO.getPlanType());          //计划ID
                planCaseSetEntity.setSetName(planSetDTO.getSetName());         //批次名称
                planCaseSetEntity.setProjectId(planSetDTO.getProjectId());       //项目ID
                planCaseSetEntity.setAreaId(planSetDTO.getAreaId());          //区域ID
                planCaseSetEntity.setBuildingId(planSetDTO.getBuildingId());      //楼栋ID
                planCaseSetEntity.setUnitId(planSetDTO.getUnitId());          //单元ID
                planCaseSetEntity.setFloor(planSetDTO.getFloor());           //楼层
                planCaseSetEntity.setPosition(planSetDTO.getPosition());          //方位
                planCaseSetEntity.setCreateBy(planSetDTO.getCreateBy());        //创建人
                planCaseSetEntity.setScope(planSetDTO.getScope());           //批次验收范围
                planCaseSetEntity.setCreateDate(planSetDTO.getCreateDate());        //创建时间
                planCaseSetEntity.setApproachDate(planSetDTO.getApproachDate());      //进场时间
                planCaseSetEntity.setApproachNum(planSetDTO.getApproachNum());     //进场批量
                planCaseSetEntity.setInspectBy(planSetDTO.getInspectBy());       //验收人员
                planCaseSetEntity.setInspectDate(planSetDTO.getInspectDate());     //验收时间
                planCaseSetEntity.setStatus(planSetDTO.getStatus());         //批次状态 0关闭 1打开 2删除
                planCaseSetEntity.setSetType(planSetDTO.getSetType());         //类型：批次、活动
                planCaseRepository.saveSet(planCaseSetEntity);
            }
        }

    }

    @Override
    public ConcealedAcceptanceDTO getConcealedAcceptance(Object[] userProArr) {

        ConcealedAcceptanceDTO concealedAcceptanceDTO = new ConcealedAcceptanceDTO();
        List<Object[]> setsList = planCaseRepository.getSetListByPlanType("2", userProArr);
        if(setsList!=null && !setsList.isEmpty()){
            List<PlanSetDTO> planSetDTOList = new ArrayList<PlanSetDTO>();
            for(Object[] obj : setsList){
                PlanSetDTO planSetDTO = new PlanSetDTO();
                planSetDTO.setSetId(obj[0] == null ? "" : obj[0].toString());
                planSetDTO.setSetName(obj[1] == null ? "" : obj[1].toString());
                planSetDTO.setStatus(obj[2] == null ? "" : obj[2].toString());
                planSetDTO.setProjectId(obj[3] == null ? "" : obj[3].toString());
                planSetDTO.setProjectName(obj[4] == null ? "" : obj[4].toString());
                planSetDTO.setAreaId(obj[5] == null ? "" : obj[5].toString());
                planSetDTO.setAreaName(obj[6] == null ? "" : obj[6].toString());
                planSetDTO.setBuildingId(obj[7] == null ? "" : obj[7].toString());
                planSetDTO.setBuildingName(obj[8] == null ? "" : obj[8].toString());
                planSetDTO.setUnitId(obj[9] == null ? "" : obj[9].toString());
                planSetDTO.setUnitName(obj[10] == null ? "" : obj[10].toString());
                planSetDTO.setFloor(obj[11] == null ? "" : obj[11].toString());
                planSetDTO.setPosition(obj[12] == null ? "" : obj[12].toString());
                planSetDTOList.add(planSetDTO);
            }

            /*List<Object[]> initList = planCaseRepository.getSetInitList(userProArr,"2");
            List<SetInitDTO> setInitDTOList = new ArrayList<SetInitDTO>();
            if(initList!=null && !initList.isEmpty()){
                for(Object[] obj : initList){
                    SetInitDTO setInit = new SetInitDTO();
                    setInit.setId(obj[0] == null ? "" : obj[0].toString());//主键
                    setInit.setInitName(obj[1] == null ? "" : obj[1].toString());//名称
                    setInit.setPlanType(obj[2] == null ? "" : obj[2].toString());//计划类型
                    setInit.setProjectId(obj[3] == null ? "" : obj[3].toString());//项目ID
                    setInit.setProjectName(obj[4] == null ? "" : obj[4].toString());//项目名称
                    setInit.setAreaId(obj[5] == null ? "" : obj[5].toString());//区域ID
                    setInit.setAreaName(obj[6] == null ? "" : obj[6].toString());//区域名称
                    setInit.setBuildingId(obj[7] == null ? "" : obj[7].toString());//楼栋ID
                    setInit.setBuildingName(obj[8] == null ? "" : obj[8].toString());//楼栋名称
                    setInit.setUnitId(obj[9] == null ? "" : obj[9].toString());//单元ID
                    setInit.setUnitName(obj[10] == null ? "" : obj[10].toString());
                    setInit.setFloor(obj[11] == null ? "" : obj[11].toString());//楼层
                    setInit.setPosition(obj[12] == null ? "" : obj[12].toString());//方位
                    setInitDTOList.add(setInit);
                }
            }
            concealedAcceptanceDTO.setSetInitDTOList(setInitDTOList);*/
            concealedAcceptanceDTO.setPlanSetList(planSetDTOList);


        }

        return concealedAcceptanceDTO;
    }





    @Override
    public FieldTestDTO  getFieldTestDTO(Object[] userProArr) {

        FieldTestDTO fieldTestDTO= new FieldTestDTO();
        List<Object[]> setsList = planCaseRepository.getSetListByPlanType("5", userProArr);
        if(setsList!=null && !setsList.isEmpty()){
            List<PlanSetDTO> planSetDTOList = new ArrayList<PlanSetDTO>();
            for(Object[] obj : setsList){
                PlanSetDTO planSetDTO = new PlanSetDTO();
                planSetDTO.setSetId(obj[0] == null ? "" : obj[0].toString());
                planSetDTO.setSetName(obj[1] == null ? "" : obj[1].toString());
                planSetDTO.setProjectId(obj[2] == null ? "" : obj[2].toString());
                planSetDTO.setProjectName(obj[3] == null ? "" : obj[3].toString());
                planSetDTO.setAreaId(obj[4] == null ? "" : obj[4].toString());
                planSetDTO.setAreaName(obj[5] == null ? "" : obj[5].toString());
                planSetDTO.setBuildingId(obj[6] == null ? "" : obj[6].toString());
                planSetDTO.setBuildingName(obj[7] == null ? "" : obj[7].toString());
                planSetDTO.setUnitId(obj[8] == null ? "" : obj[8].toString());
                planSetDTO.setUnitName(obj[9] == null ? "" : obj[9].toString());
                planSetDTO.setFloor(obj[10] == null ? "" : obj[10].toString());
                planSetDTO.setPosition(obj[11] == null ? "" : obj[11].toString());
                planSetDTOList.add(planSetDTO);
            }

            /*List<Object[]> initList = planCaseRepository.getSetInitList(userProArr,"5");
            List<SetInitDTO> setInitDTOList = new ArrayList<SetInitDTO>();
            if(initList!=null && !initList.isEmpty()){
                for(Object[] obj : initList){
                    SetInitDTO setInit = new SetInitDTO();
                    setInit.setId(obj[0] == null ? "" : obj[0].toString());//主键
                    setInit.setInitName(obj[1] == null ? "" : obj[1].toString());//名称
                    setInit.setPlanType(obj[2] == null ? "" : obj[2].toString());//计划类型
                    setInit.setProjectId(obj[3] == null ? "" : obj[3].toString());//项目ID
                    setInit.setProjectName(obj[4] == null ? "" : obj[4].toString());//项目名称
                    setInit.setAreaId(obj[5] == null ? "" : obj[5].toString());//区域ID
                    setInit.setAreaName(obj[6] == null ? "" : obj[6].toString());//区域名称
                    setInit.setBuildingId(obj[7] == null ? "" : obj[7].toString());//楼栋ID
                    setInit.setBuildingName(obj[8] == null ? "" : obj[8].toString());//楼栋名称
                    setInit.setUnitId(obj[9] == null ? "" : obj[9].toString());//单元ID
                    setInit.setUnitName(obj[10] == null ? "" : obj[10].toString());
                    setInit.setFloor(obj[11] == null ? "" : obj[11].toString());//楼层
                    setInit.setPosition(obj[12] == null ? "" : obj[12].toString());//方位
                    setInitDTOList.add(setInit);
                }
            }
            fieldTestDTO.setSetInitDTOList(setInitDTOList);*/
            fieldTestDTO.setPlanSetList(planSetDTOList);


        }

        return fieldTestDTO;
    }

    @Override
    public ModelReviewsDTO  getModelReviewsDTO(Object[] userProArr) {

        ModelReviewsDTO modelReviewsDTO= new ModelReviewsDTO();
        List<Object[]> setsList = planCaseRepository.getSetListByPlanType("3", userProArr);
        if(setsList!=null && !setsList.isEmpty()){
            List<PlanSetDTO> planSetDTOList = new ArrayList<PlanSetDTO>();
            for(Object[] obj : setsList){
                PlanSetDTO planSetDTO = new PlanSetDTO();
                planSetDTO.setSetId(obj[0] == null ? "" : obj[0].toString());
                planSetDTO.setSetName(obj[1] == null ? "" : obj[1].toString());
                planSetDTO.setProjectId(obj[2] == null ? "" : obj[2].toString());
                planSetDTO.setProjectName(obj[3] == null ? "" : obj[3].toString());
                planSetDTO.setAreaId(obj[4] == null ? "" : obj[4].toString());
                planSetDTO.setAreaName(obj[5] == null ? "" : obj[5].toString());
                planSetDTO.setBuildingId(obj[6] == null ? "" : obj[6].toString());
                planSetDTO.setBuildingName(obj[7] == null ? "" : obj[7].toString());
                planSetDTO.setUnitId(obj[8] == null ? "" : obj[8].toString());
                planSetDTO.setUnitName(obj[9] == null ? "" : obj[9].toString());
                planSetDTO.setFloor(obj[10] == null ? "" : obj[10].toString());
                planSetDTO.setPosition(obj[11] == null ? "" : obj[11].toString());
                planSetDTOList.add(planSetDTO);
            }

            /*List<Object[]> initList = planCaseRepository.getSetInitList(userProArr,"3");
            List<SetInitDTO> setInitDTOList = new ArrayList<SetInitDTO>();
            if(initList!=null && !initList.isEmpty()){
                for(Object[] obj : initList){
                    SetInitDTO setInit = new SetInitDTO();
                    setInit.setId(obj[0] == null ? "" : obj[0].toString());//主键
                    setInit.setInitName(obj[1] == null ? "" : obj[1].toString());//名称
                    setInit.setPlanType(obj[2] == null ? "" : obj[2].toString());//计划类型
                    setInit.setProjectId(obj[3] == null ? "" : obj[3].toString());//项目ID
                    setInit.setProjectName(obj[4] == null ? "" : obj[4].toString());//项目名称
                    setInit.setAreaId(obj[5] == null ? "" : obj[5].toString());//区域ID
                    setInit.setAreaName(obj[6] == null ? "" : obj[6].toString());//区域名称
                    setInit.setBuildingId(obj[7] == null ? "" : obj[7].toString());//楼栋ID
                    setInit.setBuildingName(obj[8] == null ? "" : obj[8].toString());//楼栋名称
                    setInit.setUnitId(obj[9] == null ? "" : obj[9].toString());//单元ID
                    setInit.setUnitName(obj[10] == null ? "" : obj[10].toString());
                    setInit.setFloor(obj[11] == null ? "" : obj[11].toString());//楼层
                    setInit.setPosition(obj[12] == null ? "" : obj[12].toString());//方位
                    setInitDTOList.add(setInit);
                }
            }
            modelReviewsDTO.setSetInitDTOList(setInitDTOList);*/
            modelReviewsDTO.setPlanSetList(planSetDTOList);


        }

        return modelReviewsDTO;
    }

    @Override
    public void saveQuestion(List<PlanQuestionDTO> planQuestionList) {
        if(planQuestionList != null && !planQuestionList.isEmpty()){
            for(PlanQuestionDTO questionDTO : planQuestionList){
                PlanCaseInfoEntity planCaseInfoEntity = planCaseRepository.getCaseById(questionDTO.getCaseId());
                if(planCaseInfoEntity==null){
                    PropertyRectifyCRMEntity propertyRectify = null;//整改单
                    planCaseInfoEntity = new PlanCaseInfoEntity();
                    planCaseInfoEntity.setCaseId(questionDTO.getCaseId());          //问题ID 主键
                    planCaseInfoEntity.setSetId(questionDTO.getSetId());           //批次ID
                    planCaseInfoEntity.setCaseTitle(questionDTO.getCaseTitle());       //问题标题
                    planCaseInfoEntity.setCasePlace(questionDTO.getCasePlace());       //问题部位
                    planCaseInfoEntity.setCaseType(questionDTO.getCaseType());        //问题类型
                    planCaseInfoEntity.setOneType(questionDTO.getOneType());         //一级分类
                    planCaseInfoEntity.setTwoType(questionDTO.getTwoType());         //二级分类
                    planCaseInfoEntity.setThreeType(questionDTO.getThreeType());       //三级分类
                    planCaseInfoEntity.setCaseDesc(questionDTO.getCaseDesc());        //问题描述
//                    planCaseInfoEntity.setCaseState(questionDTO.getCaseState());       //问题状态,------待定：1待接单 2待整改 3已整改 4已通过 5一次通过 6二次通过 7三次通过 0作废
                    planCaseInfoEntity.setCaseState("accepted");
                    planCaseInfoEntity.setRoomId(questionDTO.getRoomId());          //房间ID,或者公共区域ID
                    planCaseInfoEntity.setProjectId(questionDTO.getProjectId());       //项目ID
                    planCaseInfoEntity.setPlanType(questionDTO.getPlanType());          //计划（模块）类型
                    planCaseInfoEntity.setCreateDate(DateUtils.parse(questionDTO.getCreateDate()));        //创建时间
                    planCaseInfoEntity.setCreateBy(questionDTO.getCreateBy());        //创建人
                    planCaseInfoEntity.setLimitTime(questionDTO.getLimitTime());           //整改时限
                    planCaseInfoEntity.setComments(questionDTO.getComments());         //批注留言
                    planCaseInfoEntity.setContractor(questionDTO.getContractor());      //承建商（整改单位）
                    planCaseInfoEntity.setResponsibleUnit(questionDTO.getResponsibleUnit()); //责任单位
                    planCaseInfoEntity.setResponsibleUnit2(questionDTO.getResponsibleUnit2());
                    planCaseInfoEntity.setResponsibleUnit3(questionDTO.getResponsibleUnit3());
                    planCaseInfoEntity.setDispatchUnit(questionDTO.getDispatchUnit());//派单单位
                    planCaseInfoEntity.setxCoordinates(questionDTO.getxCoordinates());
                    planCaseInfoEntity.setyCoordinates(questionDTO.getyCoordinates());
//
//
//                    propertyRectify.setRectifyId(questionDTO.getCaseId());
//                    propertyRectify.setRectifyState(questionDTO.getCasePlace());
//                    propertyRectify.setProblemType(questionDTO.getCaseType());
//                    propertyRectify.setClassifyOne(questionDTO.getOneType());
//                    propertyRectify.setClassifyTwo(questionDTO.getTwoType());
//                    propertyRectify.setClassifyThree(questionDTO.getThreeType());
//                    propertyRectify.setProblemDescription(questionDTO.getCaseDesc());
//                    propertyRectify.setRoomId(questionDTO.getRoomId());
//                    propertyRectify.setPlanNum(questionDTO.getPlanType());
//                    propertyRectify.setCreateDate(questionDTO.getCreateDate());
//
//                    repairClientService.getPropertyRepair(null,propertyRectify);
                    planCaseRepository.saveQuestion(planCaseInfoEntity);
                }else{
                    planCaseInfoEntity.setCaseState(questionDTO.getCaseState());
                    planCaseInfoEntity.setModifyBy(questionDTO.getModifyBy());
                    planCaseInfoEntity.setModifyDate(DateUtils.parse(questionDTO.getModifyDate()));
                    planCaseInfoEntity.setOneType(questionDTO.getOneType());         //一级分类
                    planCaseInfoEntity.setTwoType(questionDTO.getTwoType());         //二级分类
                    planCaseInfoEntity.setThreeType(questionDTO.getThreeType());       //三级分类
                    planCaseRepository.updateCase(planCaseInfoEntity);
                }

//
//                planCaseRepository.saveQuestion(planCaseInfoEntity);
                //问题图片
                List<QuestionImageDTO> qImageList = questionDTO.getCaseImageList();
                if(qImageList!=null && ! qImageList.isEmpty()){
                    for(QuestionImageDTO questionImageDTO : qImageList){
                        PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                        propertyImageEntity.setImageId(questionImageDTO.getImageId());//图片id
                        propertyImageEntity.setUploadDate(new Date());//上传日期
                        propertyImageEntity.setImgFkId(planCaseInfoEntity.getCaseId());//图片外键id
                        propertyImageEntity.setImagePath(questionImageDTO.getImageUrl());//图片路径
                        propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                        propertyImageEntity.setState("0");//状态:0为有效；1为无效
                        propertyImageRepository.saveImage(propertyImageEntity);

                    }
                }
            }
        }
    }

    public void saveRepair(List<CaseRepairDTO> caseRepairDTOList){

        if(caseRepairDTOList !=null && !caseRepairDTOList.isEmpty()){
            for(CaseRepairDTO caseRepairDTO : caseRepairDTOList){
                PlanCaseRepairEntity planCaseRepairEntity = planCaseRepository.getRepairById(caseRepairDTO.getCaseId());
                if(planCaseRepairEntity == null){
                    planCaseRepairEntity = new PlanCaseRepairEntity();
                    planCaseRepairEntity.setId(caseRepairDTO.getId());//主键
                    planCaseRepairEntity.setTimes(caseRepairDTO.getTimes());//复查次数
                    planCaseRepairEntity.setCaseId(caseRepairDTO.getCaseId());//问题ID
                    planCaseRepairEntity.setRepairBy(caseRepairDTO.getRepairBy());//整改人
                    planCaseRepairEntity.setRepairName(caseRepairDTO.getRepairName());
                    planCaseRepairEntity.setRepairPhone(caseRepairDTO.getRepairPhone());//整改人联系电话
                    planCaseRepairEntity.setRepairDate(caseRepairDTO.getRepairDate());//整改时间
                    planCaseRepairEntity.setRepairDesc(caseRepairDTO.getRepairDesc());//整改描述
                    planCaseRepairEntity.setReviewBy(caseRepairDTO.getReviewBy());//复查人
                    planCaseRepairEntity.setReviewPhone(caseRepairDTO.getReviewPhone());//复查人联系电话
                    planCaseRepairEntity.setReviewDate(caseRepairDTO.getReviewDate());//复查时间
                    planCaseRepairEntity.setReviewOpinion(caseRepairDTO.getReviewOpinion());//复查意见，是否通过
                    planCaseRepairEntity.setReviewDesc(caseRepairDTO.getReviewDesc());//复查描述
                    planCaseRepairEntity.setSupervisor(caseRepairDTO.getSupervisor());//第三方监理
                    planCaseRepairEntity.setSupervisorPhone(caseRepairDTO.getSupervisorPhone());//第三方监理电话
                    planCaseRepairEntity.setSupervisorDate(caseRepairDTO.getSupervisorDate());//第三方监理复查时间
                    planCaseRepairEntity.setSupervisorOpinion(caseRepairDTO.getSupervisorOpinion());//第三方复查意见，是否通过
                    planCaseRepairEntity.setSupervisorDesc(caseRepairDTO.getSupervisorDesc());//第三方监理复查描述
                    planCaseRepository.saveRepir(planCaseRepairEntity);
                }else {
                    planCaseRepairEntity.setTimes(caseRepairDTO.getTimes());//复查次数
                    planCaseRepairEntity.setCaseId(caseRepairDTO.getCaseId());//问题ID
                    planCaseRepairEntity.setRepairBy(caseRepairDTO.getRepairBy());//整改人
                    planCaseRepairEntity.setRepairName(caseRepairDTO.getRepairName());
                    planCaseRepairEntity.setRepairPhone(caseRepairDTO.getRepairPhone());//整改人联系电话
                    planCaseRepairEntity.setRepairDate(caseRepairDTO.getRepairDate());//整改时间
                    planCaseRepairEntity.setRepairDesc(caseRepairDTO.getRepairDesc());//整改描述
                    planCaseRepairEntity.setReviewBy(caseRepairDTO.getReviewBy());//复查人
                    planCaseRepairEntity.setReviewPhone(caseRepairDTO.getReviewPhone());//复查人联系电话
                    planCaseRepairEntity.setReviewDate(caseRepairDTO.getReviewDate());//复查时间
                    planCaseRepairEntity.setReviewOpinion(caseRepairDTO.getReviewOpinion());//复查意见，是否通过
                    planCaseRepairEntity.setReviewDesc(caseRepairDTO.getReviewDesc());//复查描述
                    planCaseRepairEntity.setSupervisor(caseRepairDTO.getSupervisor());//第三方监理
                    planCaseRepairEntity.setSupervisorPhone(caseRepairDTO.getSupervisorPhone());//第三方监理电话
                    planCaseRepairEntity.setSupervisorDate(caseRepairDTO.getSupervisorDate());//第三方监理复查时间
                    planCaseRepairEntity.setSupervisorOpinion(caseRepairDTO.getSupervisorOpinion());//第三方复查意见，是否通过
                    planCaseRepairEntity.setSupervisorDesc(caseRepairDTO.getSupervisorDesc());//第三方监理复查描述
                    planCaseRepository.updateRepir(planCaseRepairEntity);
                }

                //问题图片
                List<QuestionImageDTO> qImageList = caseRepairDTO.getReviewImgList();
                if(qImageList!=null && ! qImageList.isEmpty()){
                    for(QuestionImageDTO questionImageDTO : qImageList){
                        PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                        propertyImageEntity.setImageId(IdGen.getDateId());//图片id
                        propertyImageEntity.setUploadDate(new Date());//上传日期
                        propertyImageEntity.setImgFkId(planCaseRepairEntity.getId());//图片外键id
                        propertyImageEntity.setImagePath(questionImageDTO.getImageUrl());//图片路径
                        propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                        propertyImageEntity.setState("0");//状态:0为有效；1为无效
                        propertyImageRepository.saveImage(propertyImageEntity);
                    }
                }

                //上传crm，新增或者修改都调用同一个接口
                PropertyRectifyCRMEntity propertyRectify = null;//整改单
                PropertyRepairCRMEntity propertyRepair = null;//保修单
                Object[] questionObj = planCaseRepository.getQuestionById(planCaseRepairEntity.getCaseId());
                if(questionObj !=null){
                    if("2".equals(questionObj[0].toString())){//交房阶段，整改单
                        propertyRectify = new PropertyRectifyCRMEntity();
                        propertyRectify.setRectifyId(IdGen.getDateId());//整改单号
                        propertyRectify.setDepartment(null);//部门
                        propertyRectify.setRoomId(questionObj[1] == null ? "" : questionObj[1].toString());//房间id
                        propertyRectify.setRoomNum(questionObj[2] == null ? "" : questionObj[2].toString());//房间编码
                        propertyRectify.setPlanNum(questionObj[3] == null ? "" : questionObj[3].toString());//房间计划编码
                        //propertyRectify.setAcceptanceDate();//内部预验房日期
                        propertyRectify.setProblemType(questionObj[4] == null ? "" : questionObj[4].toString());//问题类型
                        propertyRectify.setClassifyOne(questionObj[5] == null ? "" : questionObj[5].toString());//一级分类
                        propertyRectify.setClassifyTwo(questionObj[6] == null ? "" : questionObj[6].toString());//二级分类
                        propertyRectify.setClassifyThree(questionObj[7] == null ? "" : questionObj[7].toString());//三级分类
                        //propertyRectify.setRegisterDate();//登记日期
                        propertyRectify.setRectifyState(questionObj[8] == null ? "" : questionObj[8].toString());//整改状态
                        propertyRectify.setRoomLocation(questionObj[9] == null ? "" : questionObj[9].toString());//房屋位置
                        //propertyRectify.setSupplier();//供应商
                        //propertyRectify.setRectifyCompleteDate();//整改完成时间
                        //propertyRectify.setRealityDate();//实际完成时间
                        propertyRectify.setProblemDescription(questionObj[10]==null?"":questionObj[10].toString());//问题描述
                        //propertyRectify.setDealResult();//处理结果
                        //propertyRectify.setCreateDate();//创建时间
                        //propertyRectify.setModifyDate();//修改时间
                    }

                    /*else if("3".equals(questionObj[0].toString())){//整改单
                        propertyRepair = new PropertyRepairCRMEntity();
                        propertyRepair.setRepairId(IdGen.getDateId());////报修单号
                        propertyRepair.setDepartment(null);//部门
                        propertyRepair.setRoomId(questionObj[1] == null ? "" : questionObj[1].toString());//房间id
                        propertyRepair.setRoomNum(questionObj[2] == null ? "" : questionObj[2].toString());//房间编码
                        propertyRepair.setMemberId();//报修人会员编号
                        propertyRepair.setUserName();//报修人姓名
                        propertyRepair.setUserPhone();//报修人电话
                        propertyRepair.setUserAddress();//报修人地址
                        propertyRepair.setCreateDate();//报修时间
                        propertyRepair.setClassifyOne();//一级分类
                        propertyRepair.setClassifyTwo();//二级分类
                        propertyRepair.setClassifyThree();//三级分类
                        propertyRepair.setMode();//维修方式：换、维修
                        propertyRepair.setRepairDate();//维修工时(分类绑定)：限期整改，工时是否超时
                        propertyRepair.setRoomLocation();//房屋位置id
                        propertyRepair.setState();//单据状态
                        propertyRepair.setDuty();//责任判定:主责(true)、非主责(false)
                        propertyRepair.setProblemLevel();//问题级别：紧急/非紧急
                        propertyRepair.setRepairWay();//报修方式：会员APP/微信/呼叫中心
                        propertyRepair.setContent();//基本内容
                        propertyRepair.setTaskDate();//接单时间
                        propertyRepair.setDealWay();//处理方案
                        propertyRepair.setDealMode();//处理方式:内部/责任单位/第三方
                        propertyRepair.setDutyCompanyOne();//责任单位1
                        propertyRepair.setDutyCompanyTwo();//责任单位2
                        propertyRepair.setDutyCompanyThree();//责任单位3
                        propertyRepair.setRepairCompany();//维修单位
                        propertyRepair.setDutyTaskDate();//责任单位接单时间
                        propertyRepair.setDutyReturnDate();//责任单位返单时间
                        propertyRepair.setRepairManager();//维修负责人
                        propertyRepair.setDealPeople();//处理人
                        propertyRepair.setDealPhone();//处理人电话
                        propertyRepair.setDealPeopleTwo();//处理人2
                        propertyRepair.setDealPhoneTwo();//处理人电话2
                        propertyRepair.setFirstVisitDate();//首选上门日期
                        propertyRepair.setBackupVisitDate();//备选上门日期
                        propertyRepair.setDealState();//处理状态(处理/暂不处理)
                        propertyRepair.setDealResult();//处理结果
                        propertyRepair.setDealCompleteDate();//受理人完工时间
                        propertyRepair.setVisitOpinion();//回访意见
                        propertyRepair.setVisitEvaluate();//回访评价:非常满意/满意/一般/不满意/非常不满意
                        propertyRepair.setVisitDate();//回访日期
                        propertyRepair.setVisitType();//电话/短信/其他
                        propertyRepair.setModifyDate();//修改时间

                    }*/

                    repairClientService.getPropertyRepair(propertyRepair,propertyRectify);
                }

            }
        }
    }

    @Override
    public ApiResult saveOrder(String userId, WorkApportionDTO workApportionDTO) {
        PlanCaseInfoEntity planCaseInfoEntity = planCaseRepository.getCaseById(workApportionDTO.getId());
        if(planCaseInfoEntity == null ){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        planCaseInfoEntity.setModifyBy(userId);
        planCaseInfoEntity.setModifyDate(new Date());
        planCaseInfoEntity.setCaseState("处理中");
        planCaseInfoEntity.setOneType(workApportionDTO.getClassifyOne());
        planCaseInfoEntity.setTwoType(workApportionDTO.getClassifyTwo());
        planCaseInfoEntity.setThreeType(workApportionDTO.getClassifyThree());
        //增加整改信息
//        PlanCaseRepairEntity PlanCaseRepair=new PlanCaseRepairEntity();
//        PlanCaseRepair.setCaseId(planCaseInfoEntity.getCaseId());
        planCaseRepository.updateCase(planCaseInfoEntity);
        return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
    }

    @Override
    public List<ProblemDTO> queryProblemList(ProblemDTO problem) {

        PlanCaseInfoEntity planCaseInfoEntity = new PlanCaseInfoEntity();

        planCaseInfoEntity.setOneType(problem.getOneType());
        planCaseInfoEntity.setTwoType(problem.getTwoType());
        planCaseInfoEntity.setThreeType(problem.getThreeType());
        planCaseInfoEntity.setCaseState(problem.getCaseState());
        planCaseInfoEntity.setRoomId(problem.getRoomId());



        List<PlanCaseInfoEntity> planCaseInfoEntityList = planCaseRepository.queryProblemList(planCaseInfoEntity);

        List<ProblemDTO> problemList = new ArrayList<>();

        for (PlanCaseInfoEntity planCaseInfoEntity1 : planCaseInfoEntityList){
            ProblemDTO problemDTO = new ProblemDTO();

            problemDTO.setCaseId(planCaseInfoEntity1.getCaseId());

            if( null != planCaseInfoEntity1.getProjectId() && !"".equals(planCaseInfoEntity1.getProjectId())){
                HouseProjectEntity houseProjectEntity = houseProjectRepository.get(planCaseInfoEntity1.getProjectId());
                if(houseProjectEntity!=null){
                    problemDTO.setProjectName(houseProjectEntity.getName());
                }
            }else{
                problemDTO.setProjectName(null);
            }

            //取一级分类名称
            if( null != planCaseInfoEntity1.getOneType() && !"".equals(planCaseInfoEntity1.getOneType())){
                FirstClassificationEntity firstClassification =  firstClassificationCRMRepository.get(planCaseInfoEntity1.getOneType());
                problemDTO.setFirstTypeName(firstClassification.getLabel());
            }else{
                problemDTO.setFirstTypeName(null);
            }

            //  取二级分类名称
            if( null != planCaseInfoEntity1.getTwoType() && !"".equals(planCaseInfoEntity1.getTwoType())){
                SecondClassificationEntity secondClassification =  secondClassificationCRMRepository.get(planCaseInfoEntity1.getTwoType());
                problemDTO.setSecondTyoeName(secondClassification.getLabel());
            }else{
                problemDTO.setSecondTyoeName(null);
            }

            // 取三级分类名称
            if( null != planCaseInfoEntity1.getThreeType() && !"".equals(planCaseInfoEntity1.getThreeType())){
                ThirdClassificationEntity thirdClassification =  thirdClassificationCRMRepository.get(planCaseInfoEntity1.getThreeType());
                problemDTO.setThirdTypeName(thirdClassification.getLabel());
            }else{
                problemDTO.setThirdTypeName(null);
            }

            problemDTO.setCaseState(planCaseInfoEntity1.getCaseState());
            problemDTO.setCaseDesc(planCaseInfoEntity1.getCaseDesc());
            problemDTO.setCreateDate(planCaseInfoEntity1.getCreateDate());
            problemDTO.setCasePlace(planCaseInfoEntity1.getCasePlace());

            problemList.add(problemDTO);
        }

        return problemList;

    }


    @Override
    public List<SupplierDTO> getSupplierList() {
        List<SupplierCRMEntity> list = supplierCRMRepository.getSupplierList();
        List<SupplierDTO> reList = new ArrayList<SupplierDTO>();
        if(list != null && !list.isEmpty()){
            for(SupplierCRMEntity sp : list){
                SupplierDTO supplierDTO = new SupplierDTO();
                supplierDTO.setSupplierId(sp.getSupplierId());
                supplierDTO.setSupplierName(sp.getName());
                supplierDTO.setThreeType(sp.getThreeType());
                supplierDTO.setProjectId(sp.getSupplierId());
                reList.add(supplierDTO);
            }
        }
        return reList;
    }


    @Override
    public void saveProblem(ProblemDTO problem , List<String> list) {
        PlanCaseInfoEntity planCaseInfoEntity = new PlanCaseInfoEntity();

        planCaseInfoEntity.setCaseId(IdGen.getDateId());  //Id
        planCaseInfoEntity.setCaseTitle(problem.getCaseTitle()); //问题标题
        planCaseInfoEntity.setSetId(problem.getSetId());  //批次Id
        planCaseInfoEntity.setProjectId(problem.getProjectId()); // 项目id
        planCaseInfoEntity.setRoomId(problem.getRoomId()); //房间Id
        planCaseInfoEntity.setCaseState(problem.getCaseState()); // 问题状态
        planCaseInfoEntity.setOneType(problem.getOneType()); //一级分类
        planCaseInfoEntity.setTwoType(problem.getTwoType()); //二级分类
        planCaseInfoEntity.setThreeType(problem.getThreeType()); //三级分类
        planCaseInfoEntity.setCasePlace(problem.getCasePlace()); //问题位置
        planCaseInfoEntity.setCaseDesc(problem.getCaseDesc()); //问题描述
        planCaseInfoEntity.setLimitTime(problem.getLimitTime()); //整改时限
        planCaseInfoEntity.setContractor(problem.getContractor()); //派单单位
        planCaseInfoEntity.setResponsibleUnit(problem.getResponsibleUnit()); //责任单位1
        planCaseInfoEntity.setResponsibleUnit2(problem.getResponsibleUnit2()); //责任单位2
        planCaseInfoEntity.setResponsibleUnit3(problem.getResponsibleUnit3()); //责任单位3
        planCaseInfoEntity.setCreateDate(new Date()); //创建时间
        planCaseInfoEntity.setCreateBy(problem.getCreateBy()); //创建人

        planCaseRepository.saveProblem(planCaseInfoEntity);

        for(String imapath:list) {
            PlanCaseImageEntity planCaseImageEntity = new PlanCaseImageEntity();

            planCaseImageEntity.setImageId(IdGen.getDateId()); //图片ID
            planCaseImageEntity.setCheckId(planCaseInfoEntity.getCaseId()); // 问题ID
            planCaseImageEntity.setTypes("1"); // 类型:1问题检查项 2整改单
            planCaseImageEntity.setImageUrl(imapath); // 图片URL
            planCaseImageEntity.setCreateDate(new Date()); //创建时间
            planCaseImageEntity.setCreateBy(planCaseInfoEntity.getCreateBy()); //创建人

            planCaseRepository.savePlanImg(planCaseImageEntity);
        }

    }

    @Override
    public void editProblem(ProblemDTO problem) {
        PlanCaseInfoEntity planCaseInfoEntity = planCaseRepository.getCaseById(problem.getCaseId());

        if(null != planCaseInfoEntity){
            planCaseInfoEntity.setModifyBy(problem.getModifyBy());
            planCaseInfoEntity.setModifyDate(new Date());
            planCaseInfoEntity.setComments(problem.getComments());
            planCaseInfoEntity.setCaseState("0"); // 0 是作废

            planCaseRepository.updateCase(planCaseInfoEntity);
        }
    }

    @Override
    public ProblemDTO getProblemById(String caseId) {
        PlanCaseInfoEntity planCaseInfoEntity = planCaseRepository.getCaseById(caseId);
        //PropertyRectifyCRMEntity planCaseInfoEntity = planCaseRepository.getCaseByIds(caseId);

        ProblemDTO problem = new ProblemDTO();

        problem.setCaseId(planCaseInfoEntity.getCaseId());
        problem.setCaseTitle(planCaseInfoEntity.getCaseTitle());
        if( null != planCaseInfoEntity.getProjectId() && !"".equals(planCaseInfoEntity.getProjectId())){
            HouseProjectEntity houseProjectEntity = houseProjectRepository.get(planCaseInfoEntity.getProjectId());
            problem.setProjectName(houseProjectEntity.getName());
        }else{
            problem.setProjectName(null);
        }

        //取一级分类名称
        if( null != planCaseInfoEntity.getOneType() && !"".equals(planCaseInfoEntity.getOneType())){
            FirstClassificationEntity firstClassification =  firstClassificationCRMRepository.get(planCaseInfoEntity.getOneType());
            problem.setFirstTypeName(firstClassification.getLabel());
        }else{
            problem.setFirstTypeName(null);
        }

        //  取二级分类名称
        if( null != planCaseInfoEntity.getTwoType() && !"".equals(planCaseInfoEntity.getTwoType())){
            SecondClassificationEntity secondClassification =  secondClassificationCRMRepository.get(planCaseInfoEntity.getTwoType());
            problem.setSecondTyoeName(secondClassification.getLabel());
        }else{
            problem.setSecondTyoeName(null);
        }

        // 取三级分类名称
        if( null != planCaseInfoEntity.getThreeType() && !"".equals(planCaseInfoEntity.getThreeType())){
            ThirdClassificationEntity thirdClassification =  thirdClassificationCRMRepository.get(planCaseInfoEntity.getThreeType());
            problem.setThirdTypeName(thirdClassification.getLabel());
        }else{
            problem.setThirdTypeName(null);
        }
        problem.setSetId(planCaseInfoEntity.getSetId());
        problem.setCaseState(planCaseInfoEntity.getCaseState());
        problem.setCaseDesc(planCaseInfoEntity.getCaseDesc());
        problem.setCreateDate(planCaseInfoEntity.getCreateDate());
        problem.setCasePlace(planCaseInfoEntity.getCasePlace());
        problem.setLimitTime(planCaseInfoEntity.getLimitTime());


        return problem;
    }

    @Override
    public PropertyRectifyCRMListDTO getPropertyRectifyCRMById(String caseId) {
        PropertyRectifyCRMListDTO propertyRectifyCRMListDTO=new PropertyRectifyCRMListDTO();
        Object[] obj = planCaseRepository.getCaseByIds(caseId);
        //封装到dto
        if(obj!=null||obj.length!=0){
            propertyRectifyCRMListDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());         //问题ID 主键
            propertyRectifyCRMListDTO.setSetId(obj[1] == null ? "" : obj[1].toString());          //批次ID
            propertyRectifyCRMListDTO.setCaseTitle(obj[2] == null ? "" : obj[2].toString());      //问题标题
            propertyRectifyCRMListDTO.setCasePlace(obj[3] == null ? "" : obj[3].toString());      //问题部位
            propertyRectifyCRMListDTO.setCaseType(obj[4] == null ? "" : obj[4].toString());       //问题类型
            propertyRectifyCRMListDTO.setOneType(obj[5] == null ? "" : obj[5].toString());        //一级分类
            propertyRectifyCRMListDTO.setTwoType(obj[6] == null ? "" : obj[6].toString());        //二级分类
            propertyRectifyCRMListDTO.setThreeType(obj[7] == null ? "" : obj[7].toString());      //三级分类
            propertyRectifyCRMListDTO.setCaseDesc(obj[8] == null ? "" : obj[8].toString());       //问题描述
            propertyRectifyCRMListDTO.setCaseState(obj[9] == null ? "" : obj[9].toString());      //问题状态,------草稿、待接单、处理中、已完成、已废弃
            propertyRectifyCRMListDTO.setRoomId(obj[10] == null ? "" : obj[10].toString());         //房间ID,或者公共区域ID
            propertyRectifyCRMListDTO.setProjectId(obj[11] == null ? "" : obj[11].toString());      //项目名称
            propertyRectifyCRMListDTO.setPlanType(obj[12] == null ? "" : obj[12].toString());         //计划（模块）类型
            propertyRectifyCRMListDTO.setCreateDate(obj[13] == null ? null : (Date) obj[13]);       //创建时间
            propertyRectifyCRMListDTO.setCreateBy(obj[14] == null ? "" : obj[14].toString());       //创建人
            propertyRectifyCRMListDTO.setLimitTime(obj[15] == null ? null : (Date) obj[15]);          //整改时限
            propertyRectifyCRMListDTO.setComments(obj[16] == null ? "" : obj[16].toString());        //批注留言
            propertyRectifyCRMListDTO.setContractor(obj[17] == null ? "" : obj[17].toString());     //承建商（整改单位）
            propertyRectifyCRMListDTO.setModifyBy(obj[18] == null ? "" : obj[18].toString());   // 修改人
            propertyRectifyCRMListDTO.setModifyDate(obj[19] == null ? null : (Date) obj[19]);//修改时间
            propertyRectifyCRMListDTO.setxCoordinates(obj[20] == null ? null : new BigDecimal(obj[20].toString()));//X坐标
            propertyRectifyCRMListDTO.setyCoordinates(obj[21] == null ? null : new BigDecimal(obj[21].toString()));//Y坐标
            propertyRectifyCRMListDTO.setProjectName(obj[22] == null ? "" : obj[22].toString());// 工程名称
            propertyRectifyCRMListDTO.setFirstTypeName(obj[23] == null ? "" : obj[23].toString());// 一级分类
            propertyRectifyCRMListDTO.setSecondTyoeName(obj[24] == null ? "" : obj[24].toString());// 二级分类
            propertyRectifyCRMListDTO.setThirdTypeName(obj[25] == null ? "" : obj[25].toString());// 三级分类
            propertyRectifyCRMListDTO.setBuildingId(obj[26] == null ? "" : obj[26].toString());// 楼栋ID
            propertyRectifyCRMListDTO.setUnitId(obj[27] == null ? "" : obj[27].toString());// 单元Id
            propertyRectifyCRMListDTO.setFloorId(obj[28] == null ? "" : obj[28].toString());// 楼层ID
            propertyRectifyCRMListDTO.setRealityDate(obj[29] == null ? null : (Date) obj[29]);//整改完成期限
            propertyRectifyCRMListDTO.setAddress(obj[30] == null ? "" : obj[30].toString());
        }
        return propertyRectifyCRMListDTO;
    }



    @Override
    public Map<String, String> getProblemImgs(String caseId) {
        List<PlanCaseImageEntity> list = planCaseRepository.getPlanCaseImages(caseId);
        Map<String,String> imgs = new LinkedHashMap<>();
        if (list.size()>0){
            for (PlanCaseImageEntity planCaseImageEntity:list){
                imgs.put(planCaseImageEntity.getImageId(), planCaseImageEntity.getImageUrl());
            }
        }
        return imgs;
    }

    @Override
    public List<ProblemDTO> queryProblemLists(ProblemDTO problem) {
        //PlanCaseInfoEntity planCaseInfoEntity = new PlanCaseInfoEntity();

        PropertyRectifyCRMEntity propertyRectifyCRMEntity =new PropertyRectifyCRMEntity();

       /* planCaseInfoEntity.setOneType(problem.getOneType());
        planCaseInfoEntity.setTwoType(problem.getTwoType());
        planCaseInfoEntity.setThreeType(problem.getThreeType());
        planCaseInfoEntity.setCaseState(problem.getCaseState());
        planCaseInfoEntity.setRoomId(problem.getRoomId());*/

        //List<PlanCaseInfoEntity> planCaseInfoEntityList = planCaseRepository.queryProblemList(planCaseInfoEntity);

        List<PropertyRectifyCRMEntity> propertyRectifyCRMEntityList = planCaseRepository.queryProblemLists(propertyRectifyCRMEntity);




        List<ProblemDTO> problemList = new ArrayList<>();

      /*  for (PlanCaseInfoEntity planCaseInfoEntity1 : planCaseInfoEntityList){
            ProblemDTO problemDTO = new ProblemDTO();

            problemDTO.setCaseId(planCaseInfoEntity1.getCaseId());

            if( null != planCaseInfoEntity1.getProjectId() && !"".equals(planCaseInfoEntity1.getProjectId())){
                HouseProjectEntity houseProjectEntity = houseProjectRepository.get(planCaseInfoEntity1.getProjectId());
                if(houseProjectEntity!=null){
                    problemDTO.setProjectName(houseProjectEntity.getName());
                }
            }else{
                problemDTO.setProjectName(null);
            }

            //取一级分类名称
            if( null != planCaseInfoEntity1.getOneType() && !"".equals(planCaseInfoEntity1.getOneType())){
                FirstClassificationEntity firstClassification =  firstClassificationCRMRepository.get(planCaseInfoEntity1.getOneType());
                problemDTO.setFirstTypeName(firstClassification.getLabel());
            }else{
                problemDTO.setFirstTypeName(null);
            }

            //  取二级分类名称
            if( null != planCaseInfoEntity1.getTwoType() && !"".equals(planCaseInfoEntity1.getTwoType())){
                SecondClassificationEntity secondClassification =  secondClassificationCRMRepository.get(planCaseInfoEntity1.getTwoType());
                problemDTO.setSecondTyoeName(secondClassification.getLabel());
            }else{
                problemDTO.setSecondTyoeName(null);
            }

            // 取三级分类名称
            if( null != planCaseInfoEntity1.getThreeType() && !"".equals(planCaseInfoEntity1.getThreeType())){
                ThirdClassificationEntity thirdClassification =  thirdClassificationCRMRepository.get(planCaseInfoEntity1.getThreeType());
                problemDTO.setThirdTypeName(thirdClassification.getLabel());
            }else{
                problemDTO.setThirdTypeName(null);
            }

            problemDTO.setCaseState(planCaseInfoEntity1.getCaseState());
            problemDTO.setCaseDesc(planCaseInfoEntity1.getCaseDesc());
            problemDTO.setCreateDate(planCaseInfoEntity1.getCreateDate());
            problemDTO.setCasePlace(planCaseInfoEntity1.getCasePlace());

            problemList.add(problemDTO);
        }*/

        return problemList;

    }

}
