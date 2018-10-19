package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.DeliverPlanDTO;
import com.maxrocky.vesta.application.DTO.DeliverPlanInfoDTO;
import com.maxrocky.vesta.application.DTO.HousePlanDTO;
import com.maxrocky.vesta.application.inf.BasicDeliveryPlanService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.DeliveryPlanCRMRepository;
import com.maxrocky.vesta.domain.repository.HousePlanCRMRepository;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.domain.repository.TemporaryTableUpdateRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import javax.xml.ws.Endpoint;

/**
 * Created by liudongxin on 2016/4/10.
 * Description:
 * webService:接收金茂项目CRM传递的质检交付计划信息
 * ModifyBy:
 */
@Service("basicDeliveryPlanService")
public class BasicDeliveryPlanServiceImpl implements BasicDeliveryPlanService {
    @Autowired
    private DeliveryPlanCRMRepository deliveryPlanCRMRepository;
    @Autowired
    private HousePlanCRMRepository housePlanCRMRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private TemporaryTableUpdateRepository temporaryTableUpdateRepository;

    /**
     * CreateBy:liudongxin
     * Description:接收交付计划信息
     * param houseUser：交付计划信息参数
     * ModifyBy:
     */
    @Override
    public String deliverPlanInfo(DeliverPlanInfoDTO deliverPlan) {
        try{
            //日期转换
            Calendar cal = Calendar.getInstance();
            if(deliverPlan!=null && deliverPlan.getDeliverPlanList().size()>0){
                //记录计划批次的id(关闭的)
                List<String> planIdList=new ArrayList<>();
                for(DeliverPlanDTO plan:deliverPlan.getDeliverPlanList()){
                    DeliveryPlanCrmEntity deliveryPlan=deliveryPlanCRMRepository.getById(plan.getId());
                    //如果有数据，则更新;如果无，则创建
                    if(deliveryPlan!=null){
                        if(!StringUtil.isEmpty(plan.getProjectNum())) {
                            deliveryPlan.setProjectNum(plan.getProjectNum());
                        }
                        if(!StringUtil.isEmpty(plan.getPlanNum())){
                            deliveryPlan.setPlanNum(plan.getPlanNum());
                        }
                        if(!StringUtil.isEmpty(plan.getPlanName())){
                            deliveryPlan.setPlanName(plan.getPlanName());
                        }
                        if(plan.getPlanStart()!=null){
                            cal.setTime(plan.getPlanStart());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlan.setPlanStart(cal.getTime());
                        }
                        if(plan.getPlanEnd()!=null){
                            cal.setTime(plan.getPlanEnd());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlan.setPlanEnd(cal.getTime());
                        }
                        if(plan.getDeliveryStandard()!=null){
                            deliveryPlan.setDeliveryStandard(plan.getDeliveryStandard());
                        }
                        if(plan.getDealStart()!=null){
                            cal.setTime(plan.getDealStart());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlan.setDealStart(cal.getTime());
                        }
                        if(plan.getDealEnd()!=null){
                            cal.setTime(plan.getDealEnd());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlan.setDealEnd(cal.getTime());
                        }
                        if(plan.getAppoint()!=null){
                            cal.setTime(plan.getAppoint());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlan.setAppoint(cal.getTime());
                        }
                        if(!StringUtil.isEmpty(plan.getDescription())){
                            deliveryPlan.setDescription(plan.getDescription());
                        }
                        if(!StringUtil.isEmpty(plan.getPlanType())){
                            deliveryPlan.setPlanType(plan.getPlanType());
                        }
                        if(!StringUtil.isEmpty(plan.getState())){
                            deliveryPlan.setState(plan.getState());
                        }
                        if(!StringUtil.isEmpty(plan.getFocusDate())){
                            deliveryPlan.setFocusDate(plan.getFocusDate());
                        }
                        if(!StringUtil.isEmpty(plan.getFocusAddress())){
                            deliveryPlan.setFocusAddress(plan.getFocusAddress());
                        }
                        if(!StringUtil.isEmpty(plan.getOpenAddress())){
                            deliveryPlan.setOpenAddress(plan.getOpenAddress());
                        }
                        if(!StringUtil.isEmpty(plan.getSporadicAddress())){
                            deliveryPlan.setSporadicAddress(plan.getSporadicAddress());
                        }
                        deliveryPlan.setModifyDate(new Date());
                        deliveryPlanCRMRepository.update(deliveryPlan);

                    }else{
                        DeliveryPlanCrmEntity deliveryPlanCrmEntity=new DeliveryPlanCrmEntity();
                        deliveryPlanCrmEntity.setId(plan.getId());
                        deliveryPlanCrmEntity.setProjectNum(plan.getProjectNum());
                        deliveryPlanCrmEntity.setPlanNum(plan.getPlanNum());
                        deliveryPlanCrmEntity.setPlanName(plan.getPlanName());
                        deliveryPlanCrmEntity.setShortName(plan.getPlanName());
                        if(plan.getPlanStart()!=null){
                            cal.setTime(plan.getPlanStart());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlanCrmEntity.setPlanStart(cal.getTime());
                        }
                        if(plan.getDeliveryStandard()!=null){
                            deliveryPlanCrmEntity.setDeliveryStandard(plan.getDeliveryStandard());
                        }
                        if(plan.getPlanEnd()!=null){
                            cal.setTime(plan.getPlanEnd());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlanCrmEntity.setPlanEnd(cal.getTime());
                        }
                        if(plan.getDealStart()!=null){
                            cal.setTime(plan.getDealStart());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlanCrmEntity.setDealStart(cal.getTime());
                        }
                        if(plan.getDealEnd()!=null){
                            cal.setTime(plan.getDealEnd());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlanCrmEntity.setDealEnd(cal.getTime());
                        }
                        if(plan.getAppoint()!=null){
                            cal.setTime(plan.getAppoint());
                            //cal.add(Calendar.HOUR_OF_DAY, 16);
                            deliveryPlanCrmEntity.setAppoint(cal.getTime());
                        }
                        //deliveryPlanCrmEntity.setPlanStart(cal.getTime());
                        //deliveryPlanCrmEntity.setPlanEnd(plan.getPlanEnd());
                        //deliveryPlanCrmEntity.setDealStart(plan.getDealStart());
                        //deliveryPlanCrmEntity.setDealEnd(plan.getDealEnd());
                        //deliveryPlanCrmEntity.setAppoint(plan.getAppoint());
                        deliveryPlanCrmEntity.setDescription(plan.getDescription());
                        deliveryPlanCrmEntity.setPlanType(plan.getPlanType());
                        deliveryPlanCrmEntity.setState(plan.getState());
                        deliveryPlanCrmEntity.setFocusDate(plan.getFocusDate());
                        deliveryPlanCrmEntity.setFocusAddress(plan.getFocusAddress());
                        deliveryPlanCrmEntity.setOpenAddress(plan.getOpenAddress());
                        deliveryPlanCrmEntity.setSporadicAddress(plan.getSporadicAddress());
                        deliveryPlanCrmEntity.setModifyDate(new Date());
                        deliveryPlanCrmEntity.setBatchStatus(0);
                        deliveryPlanCRMRepository.create(deliveryPlanCrmEntity);
                    }


                    //更新计划信息临时表 计划数据
                    ActiveTemporaryTimeEntity housePlan=temporaryTableUpdateRepository.queryActive(plan.getId());
                    if(housePlan!=null){
                        String up="0";
                        if(!StringUtil.isEmpty(plan.getPlanNum()) && !housePlan.getCurrentNum().equals(plan.getPlanNum())){
                            housePlan.setCurrentNum(plan.getPlanNum());
                            up="1";
                        }
                        if(!StringUtil.isEmpty(plan.getPlanName()) && !housePlan.getName().equals(plan.getPlanName())){
                            housePlan.setName(plan.getPlanName());
                            up="1";
                        }
                        if(!StringUtil.isEmpty(plan.getProjectNum()) && !housePlan.getParentNum().equals(plan.getProjectNum())){
                            housePlan.setParentNum(plan.getProjectNum());
                            up="1";
                        }
                        if(!StringUtil.isEmpty(plan.getPlanType()) && !housePlan.getType().equals(plan.getPlanType())){
                            housePlan.setType(plan.getPlanType());
                            up="1";
                        }
                        if(!StringUtil.isEmpty(plan.getState()) && !housePlan.getStart().equals(plan.getState())){
                            housePlan.setStart(plan.getState());
                            up="1";
                        }
                        housePlan.setPath(plan.getPlanStart());
                        housePlan.setPlanEnd(plan.getPlanEnd());
                        housePlan.setGraded("1");
                        housePlan.setTimeStamp(new Date());
                        if("1".equals(up)){
                            temporaryTableUpdateRepository.updateActive(housePlan);
                        }
                        if(!StringUtil.isEmpty(plan.getState()) && "forceClosed".equals(plan.getState())){
                            //如果该计划已经关闭 批量关闭其计划下房间
                            planIdList.add(plan.getId());
//                            temporaryTableUpdateRepository.updateActiveByState(plan.getId());
                        }
                    }else{
                        ActiveTemporaryTimeEntity housePlanEntity=new ActiveTemporaryTimeEntity();
                        housePlanEntity.setCurrentId(plan.getId());
                        housePlanEntity.setCurrentNum(plan.getPlanNum());
                        housePlanEntity.setParentNum(plan.getProjectNum());
                        housePlanEntity.setName(plan.getPlanName());
                        housePlanEntity.setStart(plan.getState());
                        housePlanEntity.setGraded("1");
                        housePlanEntity.setTimeStamp(new Date());
                        housePlanEntity.setType(plan.getPlanType());
                        housePlanEntity.setPath(plan.getPlanStart());
                        housePlanEntity.setPlanEnd(plan.getPlanEnd());
                        temporaryTableUpdateRepository.createActive(housePlanEntity);
                        if(!StringUtil.isEmpty(plan.getState()) && "forceClosed".equals(plan.getState())){
                            //如果该计划已经关闭 批量关闭其计划下房间
                            planIdList.add(plan.getId());
                        }
                    }
                    //调用日志
                    InterfacePlanLogEntity interfacePlanLog=new InterfacePlanLogEntity();
                    interfacePlanLog.setId(IdGen.uuid());
                    interfacePlanLog.setInterfaceName("调用交付计划接口:新增+更新 交付计划数据！");
                    interfacePlanLog.setCode("200");
                    interfacePlanLog.setEntityName("delivery_plan_crm");
                    interfacePlanLog.setErrorDate(new Date());
                    interfacePlanLog.setEntityId(plan.getId());
                    interfaceLogRepository.createPlanLog(interfacePlanLog);
                }
                if(deliverPlan!=null && deliverPlan.getHousePlanList().size()>0) {
                    for (HousePlanDTO housePlan : deliverPlan.getHousePlanList()) {
                        String log="0";//判断是否写入日志信息
                        HousePlanCRMEntity housePlanCRM = housePlanCRMRepository.getByIdAndPlanId(housePlan.getRoomId(),housePlan.getPlanId());
                        if (housePlanCRM != null) {
                            String up="0";
                            if(!StringUtil.isEmpty(housePlan.getRoomNum()) && !housePlanCRM.getRoomNum().equals(housePlan.getRoomNum())) {
                                housePlanCRM.setRoomNum(housePlan.getRoomNum());
                                up="1";
                            }
                            if("1".equals(up)){
                                log="1";
                                housePlanCRM.setModifyOn(new Date());
                                housePlanCRMRepository.update(housePlanCRM);
                            }

                        } else {
                            HousePlanCRMEntity housePlanCRMEntity = new HousePlanCRMEntity();
                            housePlanCRMEntity.setRoomId(housePlan.getRoomId());
                            housePlanCRMEntity.setPlanId(housePlan.getPlanId());
                            housePlanCRMEntity.setRoomNum(housePlan.getRoomNum());
                            housePlanCRMEntity.setCreateOn(new Date());
                            housePlanCRMEntity.setState("0");
                            log="1";
                            housePlanCRMRepository.create(housePlanCRMEntity);
                        }
                        //更新计划信息临时表 同步房间数据
                        ActiveTemporaryTimeEntity housePlanInfo=temporaryTableUpdateRepository.queryActiveBUild(housePlan.getRoomId(),housePlan.getPlanId());
                        if(housePlanInfo!=null && housePlan.getPlanId().equals(housePlanInfo.getParentId())){
                            String up="0";
                            if(!StringUtil.isEmpty(housePlan.getRoomNum()) && !housePlanInfo.getCurrentNum().equals(housePlan.getRoomNum())){
                                housePlanInfo.setCurrentNum(housePlan.getRoomNum());
                                housePlanInfo.setPath(null);
                                housePlanInfo.setPlanEnd(null);
                                up="1";
                            }
                            if("1".equals(up)){
                                log="1";
                                housePlanInfo.setTimeStamp(new Date());
                                housePlanInfo.setPath(null);
                                housePlanInfo.setPlanEnd(null);
                                temporaryTableUpdateRepository.updateActive(housePlanInfo);
                            }
                        }else{
                            ActiveTemporaryTimeEntity housePlanEntity=new ActiveTemporaryTimeEntity();
                            housePlanEntity.setCurrentId(housePlan.getRoomId());
                            housePlanEntity.setCurrentNum(housePlan.getRoomNum());
                            housePlanEntity.setParentId(housePlan.getPlanId());
                            housePlanEntity.setStart("1");
                            housePlanEntity.setGraded("2");
                            housePlanEntity.setPath(null);
                            housePlanEntity.setPlanEnd(null);
                            housePlanEntity.setTimeStamp(new Date());
                            log="1";
                            temporaryTableUpdateRepository.createActive(housePlanEntity);
                        }
                        if("1".equals(log)){
                            //调用日志
                            InterfacePlanLogEntity interfacePlanLog=new InterfacePlanLogEntity();
                            interfacePlanLog.setId(IdGen.uuid());
                            interfacePlanLog.setInterfaceName("调用交付计划接口:新增+更新 交付计划下房间数据！");
                            interfacePlanLog.setCode("200");
                            interfacePlanLog.setEntityName("delivery_plan_crm");
                            interfacePlanLog.setErrorDate(new Date());
                            interfacePlanLog.setEntityId("ID: "+housePlan.getRoomId()+";编码: "+ housePlan.getRoomNum() +";计划: "+housePlan.getPlanId());
                            interfaceLogRepository.createPlanLog(interfacePlanLog);
                        }
                    }
                }
                //计划被关闭的 计划批次idList 根据idList改变其下所有房间的状态
                if(planIdList!=null && planIdList.size()>0){
                    //1 每个计划修改其下所属的房间状态
                    for(String id:planIdList){
                        //1.1 app层级表
                        temporaryTableUpdateRepository.updateActiveStateById(id);
                        //1.2 crm信息同步表
                        housePlanCRMRepository.updateHousePlanStateById(id);
                        //调用日志
                        InterfacePlanLogEntity interfacePlanLog=new InterfacePlanLogEntity();
                        interfacePlanLog.setId(IdGen.uuid());
                        interfacePlanLog.setInterfaceName("调用交付计划接口:关闭计划下房间状态！");
                        interfacePlanLog.setCode("200");
                        interfacePlanLog.setEntityName("delivery_plan_crm");
                        interfacePlanLog.setErrorDate(new Date());
                        interfacePlanLog.setEntityId("计划ID: "+ id);
                        interfaceLogRepository.createPlanLog(interfacePlanLog);
                    }

//                    //2.直接以idList批量关闭所属的房间状态
//                    //2.1 app层级表
//                    temporaryTableUpdateRepository.updateActiveStateByIdList(planIdList);
//                    //2.2 crm信息同步表
//                    housePlanCRMRepository.updateHousePlanStateByIdList(planIdList);
//                    //调用日志
//                    InterfacePlanLogEntity interfacePlanLog=new InterfacePlanLogEntity();
//                    interfacePlanLog.setId(IdGen.uuid());
//                    interfacePlanLog.setInterfaceName("调用交付计划接口:关闭计划下房间状态！");
//                    interfacePlanLog.setCode("200");
//                    interfacePlanLog.setEntityName("delivery_plan_crm");
//                    interfacePlanLog.setErrorDate(new Date());
//                    interfacePlanLog.setEntityId("计划ID: "+ planIdList);
//                    interfaceLogRepository.createPlanLog(interfacePlanLog);
                }
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            //System.out.println("调用：交付计划接口失败！");
            //调用日志
            InterfacePlanLogEntity interfacePlanLog=new InterfacePlanLogEntity();
            interfacePlanLog.setId(IdGen.uuid());
            interfacePlanLog.setInterfaceName("接收交付计划信息接口:更新/创建数据失败！");
            interfacePlanLog.setCode("500");
            interfacePlanLog.setEntityName("delivery_plan_crm/house_plan_crm");
            interfacePlanLog.setErrorDate(new Date());
            interfaceLogRepository.createPlanLog(interfacePlanLog);
            return "500";
        }
    }

    /**
     * CreateBy:liudongxin
     * Description:发布service类
     * Endpoint(服务绑定的地址,发布的实现类)
     * ModifyBy:
     */
    /*public static void main(String[] args){
        Endpoint.publish("http://localhost:8090/ws/deliveryPlanService", new BasicDeliveryPlanServiceImpl());
        System.out.println("service success!");
    }*/
}