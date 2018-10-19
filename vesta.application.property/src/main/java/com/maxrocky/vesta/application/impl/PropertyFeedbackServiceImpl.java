package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyRepairDTO;
import com.maxrocky.vesta.application.inf.PropertyFeedbackService;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修评价方法实现
 */
@Service
public class PropertyFeedbackServiceImpl implements PropertyFeedbackService {
    @Autowired
    private PropertyFeedbackRepository propertyFeedbackRepository;
    @Autowired
    private PropertyRepairRepository propertyRepairRepository;
    @Autowired
    private PropertyRepairTaskRepository propertyRepairTaskRepository;
    @Autowired
    private PraiseRepository praiseRepository;//表扬数据操作接口
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    @Autowired
    private PropertyRepairCRMRepository propertyRepairCRMRepository;
    @Autowired
    private RepairClientService repairClientService;

    /**
     * 添加评价(业主端)
     * @param user
     * @param propertyRepairDTO
     */
    @Override
    public ApiResult createPropertyFeedback(UserInfoEntity user, PropertyRepairDTO propertyRepairDTO) throws GeneralException {
        if (propertyRepairDTO == null) {
            return ErrorResource.getError("tip_00000040");//报修信息为空
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(propertyRepairDTO.getId())){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if(StringUtil.isEmpty(propertyRepairDTO.getGrade())){
            return ErrorResource.getError("tip_pe00000003");//报修评分为空
        }
        /*if(propertyRepairDTO.getGrade().equals("1") || propertyRepairDTO.getGrade().equals("2")) {
            if (StringUtil.isEmpty(propertyRepairDTO.getEvaluateContent())) {
                return ErrorResource.getError("tip_pe00000005");//报修评价为空
            }
        }*/
        try {
            PropertyFeedbackEntity propertyFeedbackInfo = propertyFeedbackRepository.getFeedback(propertyRepairDTO.getId());
            if (propertyFeedbackInfo != null) {
                //return ErrorResource.getError("tip_pe00000008");//已评价过，不可重复提交
                propertyFeedbackInfo.setGrade(propertyRepairDTO.getGrade());
                propertyFeedbackRepository.updateFeedback(propertyFeedbackInfo);
            }else {
                //添加评价信息
                PropertyFeedbackEntity propertyFeedbackEntity = new PropertyFeedbackEntity();
                propertyFeedbackEntity.setFeedbackId(IdGen.uuid());
                //propertyFeedbackEntity.setContent(propertyRepairDTO.getEvaluateContent());
                propertyFeedbackEntity.setCreateBy(user.getNickName());
                propertyFeedbackEntity.setCreateDate(DateUtils.getDate());
                propertyFeedbackEntity.setRepairId(propertyRepairDTO.getId());
                propertyFeedbackEntity.setState("0");//默认有效
                propertyFeedbackEntity.setGrade(propertyRepairDTO.getGrade());
                propertyFeedbackRepository.createFeedback(propertyFeedbackEntity);
            }
            //修改报修主表
            PropertyRepairEntity propertyRepairEntity=  propertyRepairRepository.getRepairDetail(propertyRepairDTO.getId());
            if (propertyRepairEntity==null){
                return ErrorResource.getError("tip_00000040");//报修信息为空
            }
            propertyRepairEntity.setModifyBy(user.getNickName());
            propertyRepairEntity.setModifyDate(DateUtils.getDate());
            propertyRepairEntity.setTypes("1");
            propertyRepairEntity.setState("已评价");

            //添加任务信息
            PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
            propertyRepairTaskEntity.setTaskId(IdGen.uuid());
            propertyRepairTaskEntity.setRepairId(propertyRepairDTO.getId());
            propertyRepairTaskEntity.setTaskNode("已评价");
            propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());
            propertyRepairTaskEntity.setReadStatus("0");

            //获取报修类型
            List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairDTO.getId());
            if (repairTaskEntities.size() > 0) {
                if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                    propertyRepairEntity.setTaskStatus("14");//14为结束
                    propertyRepairTaskEntity.setTaskStatus("14");//14为维修已评价，流程结束
                    propertyRepairTaskEntity.setTaskName("已评价");
                    propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");
                } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                    propertyRepairEntity.setTaskStatus("17");//17为结束
                    propertyRepairTaskEntity.setTaskStatus("17");//17为投诉已评价，流程结束
                    propertyRepairTaskEntity.setTaskName("投诉解决");
                    propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");
                }
            }
            propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            propertyRepairRepository.updateRepair(propertyRepairEntity);
            //获取报修临时信息
            PropertyRepairCRMEntity propertyRepairCRM=propertyRepairCRMRepository.getById(propertyRepairDTO.getId());
            if(propertyRepairCRM!=null){
                if (propertyRepairDTO.getGrade().equals("5")) {
                    propertyRepairCRM.setVisitEvaluate("highlySatisfied");
                } else if (propertyRepairDTO.getGrade().equals("4")) {
                    propertyRepairCRM.setVisitEvaluate("satisfied");
                } else if (propertyRepairDTO.getGrade().equals("3")) {
                    propertyRepairCRM.setVisitEvaluate("commonSatisfied");
                } else if (propertyRepairDTO.getGrade().equals("2")) {
                    propertyRepairCRM.setVisitEvaluate("dissatisfied");
                } else if (propertyRepairDTO.getGrade().equals("1")) {
                    propertyRepairCRM.setVisitEvaluate("highlyDissatisfied");
                }
                propertyRepairCRM.setVisitDate(new Date());
                propertyRepairCRM.setState("returnVisit");
                propertyRepairCRMRepository.update(propertyRepairCRM);
                //调用webService接口,向CRM传送数据
                repairClientService.getPropertyRepair(propertyRepairCRM, null);
            }else {
                PropertyRepairCRMEntity propertyRepairCRMEntity = new PropertyRepairCRMEntity();
                propertyRepairCRMEntity.setRepairId(propertyRepairEntity.getRepairId());
                HouseInfoEntity house = houseInfoRepository.get(propertyRepairEntity.getRoomId());
                if (house != null) {
                    propertyRepairCRMEntity.setRoomId(house.getRoomId());
                    propertyRepairCRMEntity.setRoomNum(house.getRoomNum());
                    propertyRepairCRMEntity.setMemberId(house.getMemberId());
                }
                propertyRepairCRMEntity.setUserName(propertyRepairEntity.getUserName());
                propertyRepairCRMEntity.setUserPhone(propertyRepairEntity.getUserPhone());
                propertyRepairCRMEntity.setUserAddress(propertyRepairEntity.getUserAddress());
                propertyRepairCRMEntity.setCreateDate(propertyRepairEntity.getCreateDate());
                propertyRepairCRMEntity.setState("returnVisit");
                propertyRepairCRMEntity.setRepairWay("wechat");
                propertyRepairCRMEntity.setContent(propertyRepairEntity.getContent());
                if (propertyRepairDTO.getGrade().equals("5")) {
                    propertyRepairCRMEntity.setVisitEvaluate("highlySatisfied");
                } else if (propertyRepairDTO.getGrade().equals("4")) {
                    propertyRepairCRMEntity.setVisitEvaluate("satisfied");
                } else if (propertyRepairDTO.getGrade().equals("3")) {
                    propertyRepairCRMEntity.setVisitEvaluate("commonSatisfied");
                } else if (propertyRepairDTO.getGrade().equals("2")) {
                    propertyRepairCRMEntity.setVisitEvaluate("dissatisfied");
                } else if (propertyRepairDTO.getGrade().equals("1")) {
                    propertyRepairCRMEntity.setVisitEvaluate("highlyDissatisfied");
                }
                propertyRepairCRMEntity.setVisitDate(new Date());
                propertyRepairCRMRepository.create(propertyRepairCRMEntity);
                //调用webService接口,向CRM传送数据
                repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 添加评价(员工端：随手报)
     * @param user
     * @param propertyRepairDTO
     */
    @Override
    public ApiResult reportsEvaluate(UserPropertyStaffEntity user, PropertyRepairDTO propertyRepairDTO) throws GeneralException {
        if (propertyRepairDTO == null) {
            return ErrorResource.getError("tip_00000040");//报修信息为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(propertyRepairDTO.getId())){
            return ErrorResource.getError("tip_pe00000004");//投诉单号为空
        }
        if(StringUtil.isEmpty(propertyRepairDTO.getGrade())){
            return ErrorResource.getError("tip_pe00000003");//报修评分为空
        }
        if(propertyRepairDTO.getGrade().equals("1") || propertyRepairDTO.getGrade().equals("2")) {
            if (StringUtil.isEmpty(propertyRepairDTO.getEvaluateContent())) {
                return ErrorResource.getError("tip_pe00000005");//报修评价为空
            }
        }
        try {
            PropertyFeedbackEntity propertyFeedbackInfo = propertyFeedbackRepository.getFeedback(propertyRepairDTO.getId());
            if (propertyFeedbackInfo != null) {
                return ErrorResource.getError("tip_pe00000008");//已评价过，不可重复提交
            }
            //添加评价信息
            PropertyFeedbackEntity propertyFeedbackEntity = new PropertyFeedbackEntity();
            propertyFeedbackEntity.setFeedbackId(IdGen.uuid());
            propertyFeedbackEntity.setContent(propertyRepairDTO.getEvaluateContent());
            propertyFeedbackEntity.setCreateBy(user.getStaffId());
            propertyFeedbackEntity.setCreateDate(DateUtils.getDate());
            propertyFeedbackEntity.setRepairId(propertyRepairDTO.getId());
            propertyFeedbackEntity.setState("0");//默认有效
            propertyFeedbackEntity.setGrade(propertyRepairDTO.getGrade());
            propertyFeedbackRepository.createFeedback(propertyFeedbackEntity);
            //修改报修主表
            PropertyRepairEntity propertyRepairEntity=  propertyRepairRepository.getRepairDetail(propertyRepairDTO.getId());
            if (propertyRepairEntity==null){
                return ErrorResource.getError("tip_00000040");//报修信息为空
            }
            propertyRepairEntity.setModifyBy(user.getStaffName());
            propertyRepairEntity.setModifyDate(DateUtils.getDate());
            propertyRepairEntity.setTypes("1");
            propertyRepairEntity.setState("已完成");

            //添加任务信息
            PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
            propertyRepairTaskEntity.setTaskId(IdGen.uuid());
            propertyRepairTaskEntity.setRepairId(propertyRepairDTO.getId());
            propertyRepairTaskEntity.setTaskNode("已评价");
            propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());
            //获取报修类型
            List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
            if(repairTaskEntities.size()>0) {
                if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                    propertyRepairEntity.setTaskStatus("14");//14为结束
                    propertyRepairTaskEntity.setTaskStatus("14");//14为维修已评价，流程结束
                    propertyRepairTaskEntity.setTaskName("评价/回访");
                    propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");
                }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                    propertyRepairEntity.setTaskStatus("17");//17为结束
                    propertyRepairTaskEntity.setTaskStatus("17");//17为投诉已评价，流程结束
                    propertyRepairTaskEntity.setTaskName("投诉解决");
                    propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");
                }
            }
            propertyRepairRepository.updateRepair(propertyRepairEntity);
            propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            //当业主好评的星级为4时，把报修信息添加到表扬表
            if(propertyRepairDTO.getGrade().equals("4") || propertyRepairDTO.getGrade().equals("5")){
                PraiseEntity praiseEntity=new PraiseEntity();
                praiseEntity.setId(IdGen.uuid());
                if(propertyRepairDTO.getEvaluateContent()!=null || "".equals(propertyRepairDTO.getEvaluateContent())) {
                    praiseEntity.setContent(propertyRepairDTO.getEvaluateContent());
                }
                praiseEntity.setCrtTime(DateUtils.getDate());
                praiseEntity.setUserId(user.getStaffId());
                praiseEntity.setTargetId(propertyRepairDTO.getUserId());
                praiseEntity.setCodenum(propertyRepairDTO.getId());
                praiseEntity.setLevel(propertyRepairDTO.getGrade());
                praiseEntity.setReadStatus("0");
                praiseEntity.setAddress(propertyRepairEntity.getUserAddress());
                praiseEntity.setUserName(propertyRepairEntity.getUserName());
                praiseEntity.setMobile(propertyRepairEntity.getUserPhone());
                praiseEntity.setStatus("1");
                praiseEntity.setReply("2");
                praiseEntity.setProjectId(propertyRepairEntity.getRegionId());
                praiseEntity.setProjectName(propertyRepairEntity.getRegionName());
                //添加表扬信息
                praiseRepository.AddPraise(praiseEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
}