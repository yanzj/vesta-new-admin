package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.HouseOpenDTO;
import com.maxrocky.vesta.application.DTO.HouseTransferDTO;
import com.maxrocky.vesta.application.DTO.HousingOrdersDTO;
import com.maxrocky.vesta.application.DTO.InternalacceptanceDTO;
import com.maxrocky.vesta.application.inf.BasicHousingOrdersService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.HouseTransferRepository;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Magic on 2017/2/8.
 */
@Service("basicHousingOrdersService")
public class BasicHousingOrdersServiceImpl implements BasicHousingOrdersService {
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;

    @Autowired
    private HouseTransferRepository houseTransferRepository;

    @Override
    public String sethousing(HousingOrdersDTO housingOrders) {
        try {
            //日期转换
            Calendar cal = Calendar.getInstance();
            //内部预验交房单数据
            if(housingOrders!=null && housingOrders.getInternalacceptance().size()>0){
                for(InternalacceptanceDTO acceptance : housingOrders.getInternalacceptance()){
                    InternalacceptanceHouseEntity acceptanceEntity=houseTransferRepository.getInternalacceptanceHouseById(acceptance.getHouseCode());
                    //如果有数据，则更新;如果无，则创建
                    if(acceptanceEntity!=null){
                        //修改时间为服务器时间
                        acceptanceEntity.setUpdateTime(DateUtils.getDate());
                        //内部预验状态
                        if(!StringUtil.isEmpty(acceptance.getAcceptanceStatus())){
                            acceptanceEntity.setAcceptanceStatus(acceptance.getAcceptanceStatus());
                        }
                        //计划
                        if(!StringUtil.isEmpty(acceptance.getInterdeliveryPla())){
                            acceptanceEntity.setInterdeliveryPla(acceptance.getInterdeliveryPla());
                        }
                        //客户姓名
                        if(!StringUtil.isEmpty(acceptance.getCustomerName())){
                            acceptanceEntity.setCustomerName(acceptance.getCustomerName());
                        }
                        //陪验人 = 最后修改人
                        if(!StringUtil.isEmpty(acceptance.getUpdateName())){
                            acceptanceEntity.setUpdateName(acceptance.getUpdateName());
                        }
                        houseTransferRepository.updateInternalacceptanceHouseEntity(acceptanceEntity);
                    }else{
                        InternalacceptanceHouseEntity acceptanceHouseEntity=new InternalacceptanceHouseEntity();
                        //计划
                        if(!StringUtil.isEmpty(acceptance.getInterdeliveryPla())){
                            acceptanceHouseEntity.setInterdeliveryPla(acceptance.getInterdeliveryPla());
                        }
                        //房间
                        if(!StringUtil.isEmpty(acceptance.getHouseCode())){
                            acceptanceHouseEntity.setHouseCode(acceptance.getHouseCode());
                        }
                        //内部预验状态
                        if(!StringUtil.isEmpty(acceptance.getAcceptanceStatus())){
                            acceptanceHouseEntity.setAcceptanceStatus(acceptance.getAcceptanceStatus());
                        }
                        //创建人
                        if(!StringUtil.isEmpty(acceptance.getCreaName())){
                            acceptanceHouseEntity.setCreaName(acceptance.getCreaName());
                        }
                        //创建时间
                        if(acceptance.getCreaTime()!=null){
                            cal.setTime(acceptance.getCreaTime());
                            //cal.add(Calendar.HOUR_OF_DAY);
                            acceptanceHouseEntity.setCreaTime(cal.getTime());
                        }
                        //陪验人 = 最后修改人
                        if(!StringUtil.isEmpty(acceptance.getUpdateName())){
                            acceptanceHouseEntity.setUpdateName(acceptance.getUpdateName());
                        }else{
                            acceptanceHouseEntity.setUpdateName(acceptance.getCreaName());
                        }
                        //修改时间为服务器时间
                        acceptanceHouseEntity.setUpdateTime(DateUtils.getDate());
                        //客户姓名
                        if(!StringUtil.isEmpty(acceptance.getCustomerName())){
                            acceptanceHouseEntity.setCustomerName(acceptance.getCustomerName());
                        }
                        houseTransferRepository.saveInternalacceptanceHouseEntity(acceptanceHouseEntity);
                    }
                }
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("调用交房单数据接口:更新/创建数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("内部预验！house_acceptance");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            //工地开放
            if(housingOrders!=null && housingOrders.getHouseOpen().size()>0){
                //HouseOpenEntity
               for(HouseOpenDTO houseOpenDTO:housingOrders.getHouseOpen()){
                   HouseOpenEntity houseOpen = houseTransferRepository.getHouseOpenById(houseOpenDTO.getHouseCode());
                   //如果有数据，则更新;如果无，则创建
                   if(houseOpen!=null){
                       //计划
                       if(!StringUtil.isEmpty(houseOpenDTO.getDeliveryPlan())){
                           houseOpen.setDeliveryPlan(houseOpenDTO.getDeliveryPlan());
                       }
                       //业主开放日状态
                       if(!StringUtil.isEmpty(houseOpenDTO.getCompletedStatus())){
                           houseOpen.setCompletedStatus(houseOpenDTO.getCompletedStatus());
                       }
                       //业主开放日描述
                       if(!StringUtil.isEmpty(houseOpenDTO.getDescribes())){
                           houseOpen.setDescribes(houseOpenDTO.getDescribes());
                       }
                       //工程质量
                       if(!StringUtil.isEmpty(houseOpenDTO.getQuality())){
                           houseOpen.setQuality(houseOpenDTO.getQuality());
                       }
                       //活动组织
                       if(!StringUtil.isEmpty(houseOpenDTO.getSchedulesatisfied())){
                           houseOpen.setSchedulesatisfied(houseOpenDTO.getSchedulesatisfied());
                       }
                       //修改人
                       if(!StringUtil.isEmpty(houseOpenDTO.getUpdateName())){
                           houseOpen.setUpdateName(houseOpenDTO.getUpdateName());
                       }
                       //签字时间
                       if(houseOpenDTO.getUpdateTime()!=null){
                           cal.setTime(houseOpenDTO.getUpdateTime());
                           //cal.add(Calendar.HOUR_OF_DAY);
                           houseOpen.setInspectionTime(cal.getTime());
                       }
                       //修改时间
                       houseOpen.setUpdateTime(DateUtils.getDate());
                       //客户姓名
                       if(!StringUtil.isEmpty(houseOpenDTO.getCustomerName())){
                           houseOpen.setCustomerName(houseOpenDTO.getCustomerName());
                       }
                       houseTransferRepository.updateHouseOpenEntity(houseOpen);
                   }else{
                       HouseOpenEntity houseOpenEntity=new HouseOpenEntity();
                       //房间
                       if(!StringUtil.isEmpty(houseOpenDTO.getHouseCode())){
                           houseOpenEntity.setHouseCode(houseOpenDTO.getHouseCode());
                       }
                       //创建人
                       if(!StringUtil.isEmpty(houseOpenDTO.getCreaName())){
                           houseOpenEntity.setCreaName(houseOpenDTO.getCreaName());
                       }
                       //创建时间
                       if(houseOpenDTO.getCreaTime()!=null){
                           cal.setTime(houseOpenDTO.getCreaTime());
                           //cal.add(Calendar.HOUR_OF_DAY);
                           houseOpenEntity.setCreaTime(cal.getTime());
                       }
                       //计划
                       if(!StringUtil.isEmpty(houseOpenDTO.getDeliveryPlan())){
                           houseOpenEntity.setDeliveryPlan(houseOpenDTO.getDeliveryPlan());
                       }
                       //业主开放日状态
                       if(!StringUtil.isEmpty(houseOpenDTO.getCompletedStatus())){
                           houseOpenEntity.setCompletedStatus(houseOpenDTO.getCompletedStatus());
                       }
                       //业主开放日描述
                       if(!StringUtil.isEmpty(houseOpenDTO.getDescribes())){
                           houseOpenEntity.setDescribes(houseOpenDTO.getDescribes());
                       }
                       //工程质量
                       if(!StringUtil.isEmpty(houseOpenDTO.getQuality())){
                           houseOpenEntity.setQuality(houseOpenDTO.getQuality());
                       }
                       //活动组织
                       if(!StringUtil.isEmpty(houseOpenDTO.getSchedulesatisfied())){
                           houseOpenEntity.setSchedulesatisfied(houseOpenDTO.getSchedulesatisfied());
                       }
                       //修改人
                       if(!StringUtil.isEmpty(houseOpenDTO.getUpdateName())){
                           houseOpenEntity.setUpdateName(houseOpenDTO.getUpdateName());
                       }else{
                           houseOpenEntity.setUpdateName(houseOpenDTO.getCreaName());
                       }
                       //签字时间
                       if(houseOpenDTO.getUpdateTime()!=null){
                           cal.setTime(houseOpenDTO.getUpdateTime());
                           //cal.add(Calendar.HOUR_OF_DAY);
                           houseOpenEntity.setInspectionTime(cal.getTime());
                       }
                       //修改时间
                       houseOpenEntity.setUpdateTime(DateUtils.getDate());
                       //客户姓名
                       if(!StringUtil.isEmpty(houseOpenDTO.getCustomerName())){
                           houseOpenEntity.setCustomerName(houseOpenDTO.getCustomerName());
                       }
                       houseTransferRepository.saveHouseOpenEntity(houseOpenEntity);
                   }
               }
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("调用交房单数据接口:更新/创建数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("工地开放！house_open");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            //正式交房
            if(housingOrders!=null && housingOrders.getHouseTransfer().size()>0){
                for(HouseTransferDTO houseTransferDTO:housingOrders.getHouseTransfer()){
                    HouseTransferEntity entity = houseTransferRepository.getHouseTransferById(houseTransferDTO.getHouseCode());
                    //如果有数据，则更新;如果无，则创建 HouseTransferEntity
                    if(entity!=null){
                        // 水表号	WATER_METER
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeter())){
                            entity.setWaterMeter(houseTransferDTO.getWaterMeter());
                        }
                        // 水表底数	WATE_RBASE
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeteReadings())){
                            entity.setWaterBase(houseTransferDTO.getWaterMeteReadings());
                        }
                        // 电表号	WATT_HOUR_METER electricityMeter
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricityMeter())){
                            entity.setWatthourMeter(houseTransferDTO.getElectricityMeter());
                        }
                        // 电表底数	METER_BASE  electricMeterReadings
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricMeterReadings())){
                            entity.setMeterBase(houseTransferDTO.getElectricMeterReadings());
                        }
                        // 中水表号 MEDIUM_WATER_METER   mediumWaterMeter
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterMeter())){
                            entity.setMediumWaterMeter(houseTransferDTO.getMediumWaterMeter());
                        }
                        // 中水表底数	MEDIUM_WATER_BASE  mediumWaterBase
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterBase())){
                            entity.setMediumWaterBase(houseTransferDTO.getMediumWaterBase());
                        }
                        // 燃气表	GAS_METER  gasMeter
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeter())){
                            entity.setGasMeter(houseTransferDTO.getGasMeter());
                        }
                        // 燃气表底数	GAS_METER_BASE   gasMeterReading
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeterReading())){
                            entity.setGasMeterBase(houseTransferDTO.getGasMeterReading());
                        }
                        // 时间戳	TIME_STAMP	新增和修改当前字段取当前时间
                        entity.setTimeStamp(DateUtils.getDate());
                        // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                        if(!StringUtil.isEmpty(houseTransferDTO.getLandlordConfirm())){
                            entity.setFormalLaunch("1");
                        }
                        // 正式交房时间
                        if(houseTransferDTO.getConfirmDate()!=null){
                            cal.setTime(houseTransferDTO.getConfirmDate());
                            //cal.add(Calendar.HOUR_OF_DAY);
                            entity.setFormalLaunchDate(cal.getTime());
                        }
                        houseTransferRepository.updateHouseTransfer(entity);
                    }else{
                        HouseTransferEntity entityNew=new HouseTransferEntity();
                        //是不是少一个房间编码的存入
                        if(!StringUtil.isEmpty(houseTransferDTO.getHouseCode())){
                            entityNew.setRoomNum(houseTransferDTO.getHouseCode());
                        }
                        // 水表号	WATER_METER
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeter())){
                            entityNew.setWaterMeter(houseTransferDTO.getWaterMeter());
                        }
                        // 水表底数	WATE_RBASE
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeteReadings())){
                            entityNew.setWaterBase(houseTransferDTO.getWaterMeteReadings());
                        }
                        // 电表号	WATT_HOUR_METER electricityMeter
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricityMeter())){
                            entityNew.setWatthourMeter(houseTransferDTO.getElectricityMeter());
                        }
                        // 电表底数	METER_BASE  electricMeterReadings
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricMeterReadings())){
                            entityNew.setMeterBase(houseTransferDTO.getElectricMeterReadings());
                        }
                        // 中水表号 MEDIUM_WATER_METER   mediumWaterMeter
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterMeter())){
                            entityNew.setMediumWaterMeter(houseTransferDTO.getMediumWaterMeter());
                        }
                        // 中水表底数	MEDIUM_WATER_BASE  mediumWaterBase
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterBase())){
                            entityNew.setMediumWaterBase(houseTransferDTO.getMediumWaterBase());
                        }
                        // 燃气表	GAS_METER  gasMeter
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeter())){
                            entityNew.setGasMeter(houseTransferDTO.getGasMeter());
                        }
                        // 燃气表底数	GAS_METER_BASE   gasMeterReading
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeterReading())){
                            entityNew.setGasMeterBase(houseTransferDTO.getGasMeterReading());
                        }
                        // 时间戳	TIME_STAMP	新增和修改当前字段取当前时间
                        entityNew.setTimeStamp(DateUtils.getDate());
                        // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                        if(!StringUtil.isEmpty(houseTransferDTO.getLandlordConfirm())){
                            entityNew.setFormalLaunch("1");
                        }
                        // 正式交房时间
                        if(houseTransferDTO.getConfirmDate()!=null){
                            cal.setTime(houseTransferDTO.getConfirmDate());
                            //cal.add(Calendar.HOUR_OF_DAY);
                            entityNew.setFormalLaunchDate(cal.getTime());
                        }else{
                            entityNew.setFormalLaunchDate(DateUtils.getDate());
                        }
                        houseTransferRepository.saveHouseTransfer(entityNew);
                    }
                    //如果有数据，则更新;如果无，则创建 CustomerDeliveryEntity
                    CustomerDeliveryEntity CustomerEntity=houseTransferRepository.getCustomerById(houseTransferDTO.getHouseCode());
                    if(CustomerEntity!=null){
                        //计划
                        if(!StringUtil.isEmpty(houseTransferDTO.getInterdeliveryPla())){
                            CustomerEntity.setInterdeliveryPla(houseTransferDTO.getInterdeliveryPla());
                        }
                        //交房邀请发送日期
                        if(houseTransferDTO.getNoticedate()!=null){
                            cal.setTime(houseTransferDTO.getNoticedate());
                            //cal.add(Calendar.HOUR_OF_DAY);
                            CustomerEntity.setNoticedate(cal.getTime());
                        }
                        //合同约定交房日期
                        if(houseTransferDTO.getDeliveryDate()!=null){
                            cal.setTime(houseTransferDTO.getDeliveryDate());
                            CustomerEntity.setDeliveryDate(cal.getTime());
                        }
                        //预约时段
                        if(!StringUtil.isEmpty(houseTransferDTO.getAppointmentTime())){
                            CustomerEntity.setAppointmentTime(houseTransferDTO.getAppointmentTime());
                        }
                        //开始办理时间
                        if(houseTransferDTO.getBeginDate()!=null){
                            cal.setTime(houseTransferDTO.getBeginDate());
                            CustomerEntity.setBeginDate(cal.getTime());
                        }
                        //办理结束日期
                        if(houseTransferDTO.getEndDate()!=null){
                            cal.setTime(houseTransferDTO.getEndDate());
                            CustomerEntity.setEndDate(cal.getTime());
                        }
                        //办理进度
                        if(!StringUtil.isEmpty(houseTransferDTO.getProgress())){
                            CustomerEntity.setProgress(houseTransferDTO.getProgress());
                        }
                        //是否二次验房
                        if(!StringUtil.isEmpty(houseTransferDTO.getIsSecondary())){
                            CustomerEntity.setIsSecondary(houseTransferDTO.getIsSecondary());
                        }
                        //委托人
                        if(!StringUtil.isEmpty(houseTransferDTO.getTrustor())){
                            CustomerEntity.setTrustor(houseTransferDTO.getTrustor());
                        }
                        //合同总价
                        if(!StringUtil.isEmpty(houseTransferDTO.getSalesCount())){
                            CustomerEntity.setSalesCount(houseTransferDTO.getSalesCount());
                        }
                        //实测面积
                        if(!StringUtil.isEmpty(houseTransferDTO.getMeasureDarea())){
                            CustomerEntity.setMeasureDarea(houseTransferDTO.getMeasureDarea());
                        }
                        //差价总额
                        if(!StringUtil.isEmpty(houseTransferDTO.getMargincompensate())){
                            CustomerEntity.setMargincompensate(houseTransferDTO.getMargincompensate());
                        }
                        //实际总价
                        if(!StringUtil.isEmpty(houseTransferDTO.getRealMoney())){
                            CustomerEntity.setRealMoney(houseTransferDTO.getRealMoney());
                        }
                        //登记费用
                        if(!StringUtil.isEmpty(houseTransferDTO.getRegistrationfee())){
                            CustomerEntity.setRegistrationfee(houseTransferDTO.getRegistrationfee());
                        }
                        //契税收取
                        if(!StringUtil.isEmpty(houseTransferDTO.getDeedtax())){
                            CustomerEntity.setDeedtax(houseTransferDTO.getDeedtax());
                        }
                        //公共维修基金缴纳
                        if(!StringUtil.isEmpty(houseTransferDTO.getPublicrepairfund())){
                            CustomerEntity.setPublicrepairfund(houseTransferDTO.getPublicrepairfund());
                        }
                        //前期物业签约
                        if(!StringUtil.isEmpty(houseTransferDTO.getPropertycontract())){
                            CustomerEntity.setPropertycontract(houseTransferDTO.getPropertycontract());
                        }
                        //物业费缴纳
                        if(!StringUtil.isEmpty(houseTransferDTO.getPropertymanagementFee())){
                            CustomerEntity.setPropertymanagementFee(houseTransferDTO.getPropertymanagementFee());
                        }
                        //业主临时公约签署
                        if(!StringUtil.isEmpty(houseTransferDTO.getInterimconvention())){
                            CustomerEntity.setInterimconvention(houseTransferDTO.getInterimconvention());
                        }
                        //水表号码
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeter())){
                            CustomerEntity.setWaterMeter(houseTransferDTO.getWaterMeter());
                        }
                        //水表读数
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeteReadings())){
                            CustomerEntity.setWaterMeteReadings(houseTransferDTO.getWaterMeteReadings());
                        }
                        //电表号码
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricityMeter())){
                            CustomerEntity.setElectricityMeter(houseTransferDTO.getElectricityMeter());
                        }
                        //电表读数
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricMeterReadings())){
                            CustomerEntity.setElectricMeterReadings(houseTransferDTO.getElectricMeterReadings());
                        }
                        //气表号码
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeter())){
                            CustomerEntity.setGasMeter(houseTransferDTO.getGasMeter());
                        }
                        //气表读数
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeterReading())){
                            CustomerEntity.setGasMeterReading(houseTransferDTO.getGasMeterReading());
                        }
                        //产权办理方式
                        if(!StringUtil.isEmpty(houseTransferDTO.getOwnershipRegistration())){
                            CustomerEntity.setOwnershipRegistration(houseTransferDTO.getOwnershipRegistration());
                        }
                        //产权办理委托书收集
                        if(!StringUtil.isEmpty(houseTransferDTO.getProxy())){
                            CustomerEntity.setProxy(houseTransferDTO.getProxy());
                        }
                        //收据回收
                        if(!StringUtil.isEmpty(houseTransferDTO.getReceiptRecover())){
                            CustomerEntity.setReceiptRecover(houseTransferDTO.getReceiptRecover());
                        }
                        //购房合同发票开具
                        if(!StringUtil.isEmpty(houseTransferDTO.getInvoice())){
                            CustomerEntity.setInvoice(houseTransferDTO.getInvoice());
                        }
                        //业主签字
                        if(!StringUtil.isEmpty(houseTransferDTO.getLandlordConfirm())){
                            CustomerEntity.setLandlordConfirm(houseTransferDTO.getLandlordConfirm());
                        }
                        //业主签字日期
                        if(houseTransferDTO.getConfirmDate()!=null){
                            cal.setTime(houseTransferDTO.getConfirmDate());
                            CustomerEntity.setConfirmDate(cal.getTime());
                        }
                        //交房人员
                        if(!StringUtil.isEmpty(houseTransferDTO.getDeliverer())){
                            CustomerEntity.setDeliverer(houseTransferDTO.getDeliverer());
                        }
                        //交房人员签字日期
                        if(houseTransferDTO.getDelivererConfirmDate()!=null){
                            cal.setTime(houseTransferDTO.getDelivererConfirmDate());
                            CustomerEntity.setDelivererConfirmDate(cal.getTime());
                        }
                        // 中水表 MEDIUM_WATER_METER
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterMeter())){
                            CustomerEntity.setMediumWaterMeter(houseTransferDTO.getMediumWaterMeter());
                        }
                        // 中水表底数	MEDIUM_WATER_BASE
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterBase())){
                            CustomerEntity.setMediumWaterBase(houseTransferDTO.getMediumWaterBase());
                        }
                        //	new_customername	业主姓名	可选
                        if(!StringUtil.isEmpty(houseTransferDTO.getCustomerName())){
                            CustomerEntity.setCustomerName(houseTransferDTO.getCustomerName());
                        }
                        houseTransferRepository.saveCustomerDelivery(CustomerEntity);
                    }else{
                        CustomerDeliveryEntity CustomerEntityNew=new CustomerDeliveryEntity();
                        CustomerEntityNew.setId(IdGen.uuid());
                        //计划
                        if(!StringUtil.isEmpty(houseTransferDTO.getInterdeliveryPla())){
                            CustomerEntityNew.setInterdeliveryPla(houseTransferDTO.getInterdeliveryPla());
                        }
                        //房间
                        if(!StringUtil.isEmpty(houseTransferDTO.getHouseCode())){
                            CustomerEntityNew.setHouseCode(houseTransferDTO.getHouseCode());
                        }
                        //交房邀请发送日期
                        if(houseTransferDTO.getNoticedate()!=null){
                            cal.setTime(houseTransferDTO.getNoticedate());
                            //cal.add(Calendar.HOUR_OF_DAY);
                            CustomerEntityNew.setNoticedate(cal.getTime());
                        }
                        //合同约定交房日期
                        if(houseTransferDTO.getDeliveryDate()!=null){
                            cal.setTime(houseTransferDTO.getDeliveryDate());
                            CustomerEntityNew.setDeliveryDate(cal.getTime());
                        }
                        //预约时段
                        if(!StringUtil.isEmpty(houseTransferDTO.getAppointmentTime())){
                            CustomerEntityNew.setAppointmentTime(houseTransferDTO.getAppointmentTime());
                        }
                        //开始办理时间
                        if(houseTransferDTO.getBeginDate()!=null){
                            cal.setTime(houseTransferDTO.getBeginDate());
                            CustomerEntityNew.setBeginDate(cal.getTime());
                        }
                        //办理结束日期
                        if(houseTransferDTO.getEndDate()!=null){
                            cal.setTime(houseTransferDTO.getEndDate());
                            CustomerEntityNew.setEndDate(cal.getTime());
                        }
                        //办理进度
                        if(!StringUtil.isEmpty(houseTransferDTO.getProgress())){
                            CustomerEntityNew.setProgress(houseTransferDTO.getProgress());
                        }
                        //是否二次验房
                        if(!StringUtil.isEmpty(houseTransferDTO.getIsSecondary())){
                            CustomerEntityNew.setIsSecondary(houseTransferDTO.getIsSecondary());
                        }
                        //委托人
                        if(!StringUtil.isEmpty(houseTransferDTO.getTrustor())){
                            CustomerEntityNew.setTrustor(houseTransferDTO.getTrustor());
                        }
                        //合同总价
                        if(!StringUtil.isEmpty(houseTransferDTO.getSalesCount())){
                            CustomerEntityNew.setSalesCount(houseTransferDTO.getSalesCount());
                        }
                        //实测面积
                        if(!StringUtil.isEmpty(houseTransferDTO.getMeasureDarea())){
                            CustomerEntityNew.setMeasureDarea(houseTransferDTO.getMeasureDarea());
                        }
                        //差价总额
                        if(!StringUtil.isEmpty(houseTransferDTO.getMargincompensate())){
                            CustomerEntityNew.setMargincompensate(houseTransferDTO.getMargincompensate());
                        }
                        //实际总价
                        if(!StringUtil.isEmpty(houseTransferDTO.getRealMoney())){
                            CustomerEntityNew.setRealMoney(houseTransferDTO.getRealMoney());
                        }
                        //登记费用
                        if(!StringUtil.isEmpty(houseTransferDTO.getRegistrationfee())){
                            CustomerEntityNew.setRegistrationfee(houseTransferDTO.getRegistrationfee());
                        }
                        //契税收取
                        if(!StringUtil.isEmpty(houseTransferDTO.getDeedtax())){
                            CustomerEntityNew.setDeedtax(houseTransferDTO.getDeedtax());
                        }
                        //公共维修基金缴纳
                        if(!StringUtil.isEmpty(houseTransferDTO.getPublicrepairfund())){
                            CustomerEntityNew.setPublicrepairfund(houseTransferDTO.getPublicrepairfund());
                        }
                        //前期物业签约
                        if(!StringUtil.isEmpty(houseTransferDTO.getPropertycontract())){
                            CustomerEntityNew.setPropertycontract(houseTransferDTO.getPropertycontract());
                        }
                        //物业费缴纳
                        if(!StringUtil.isEmpty(houseTransferDTO.getPropertymanagementFee())){
                            CustomerEntityNew.setPropertymanagementFee(houseTransferDTO.getPropertymanagementFee());
                        }
                        //业主临时公约签署
                        if(!StringUtil.isEmpty(houseTransferDTO.getInterimconvention())){
                            CustomerEntityNew.setInterimconvention(houseTransferDTO.getInterimconvention());
                        }
                        //水表号码
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeter())){
                            CustomerEntityNew.setWaterMeter(houseTransferDTO.getWaterMeter());
                        }
                        //水表读数
                        if(!StringUtil.isEmpty(houseTransferDTO.getWaterMeteReadings())){
                            CustomerEntityNew.setWaterMeteReadings(houseTransferDTO.getWaterMeteReadings());
                        }
                        //电表号码
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricityMeter())){
                            CustomerEntityNew.setElectricityMeter(houseTransferDTO.getElectricityMeter());
                        }
                        //电表读数
                        if(!StringUtil.isEmpty(houseTransferDTO.getElectricMeterReadings())){
                            CustomerEntityNew.setElectricMeterReadings(houseTransferDTO.getElectricMeterReadings());
                        }
                        //气表号码
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeter())){
                            CustomerEntityNew.setGasMeter(houseTransferDTO.getGasMeter());
                        }
                        //气表读数
                        if(!StringUtil.isEmpty(houseTransferDTO.getGasMeterReading())){
                            CustomerEntityNew.setGasMeterReading(houseTransferDTO.getGasMeterReading());
                        }
                        //产权办理方式
                        if(!StringUtil.isEmpty(houseTransferDTO.getOwnershipRegistration())){
                            CustomerEntityNew.setOwnershipRegistration(houseTransferDTO.getOwnershipRegistration());
                        }
                        //产权办理委托书收集
                        if(!StringUtil.isEmpty(houseTransferDTO.getProxy())){
                            CustomerEntityNew.setProxy(houseTransferDTO.getProxy());
                        }
                        //收据回收
                        if(!StringUtil.isEmpty(houseTransferDTO.getReceiptRecover())){
                            CustomerEntityNew.setReceiptRecover(houseTransferDTO.getReceiptRecover());
                        }
                        //购房合同发票开具
                        if(!StringUtil.isEmpty(houseTransferDTO.getInvoice())){
                            CustomerEntityNew.setInvoice(houseTransferDTO.getInvoice());
                        }
                        //业主签字
                        if(!StringUtil.isEmpty(houseTransferDTO.getLandlordConfirm())){
                            CustomerEntityNew.setLandlordConfirm(houseTransferDTO.getLandlordConfirm());
                        }
                        //业主签字日期
                        if(houseTransferDTO.getConfirmDate()!=null){
                            cal.setTime(houseTransferDTO.getConfirmDate());
                            CustomerEntityNew.setConfirmDate(cal.getTime());
                        }
                        //交房人员
                        if(!StringUtil.isEmpty(houseTransferDTO.getDeliverer())){
                            CustomerEntityNew.setDeliverer(houseTransferDTO.getDeliverer());
                        }
                        //交房人员签字日期
                        if(houseTransferDTO.getDelivererConfirmDate()!=null){
                            cal.setTime(houseTransferDTO.getDelivererConfirmDate());
                            CustomerEntityNew.setDelivererConfirmDate(cal.getTime());
                        }
                        // 中水表 MEDIUM_WATER_METER
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterMeter())){
                            CustomerEntityNew.setMediumWaterMeter(houseTransferDTO.getMediumWaterMeter());
                        }
                        // 中水表底数	MEDIUM_WATER_BASE
                        if(!StringUtil.isEmpty(houseTransferDTO.getMediumWaterBase())){
                            CustomerEntityNew.setMediumWaterBase(houseTransferDTO.getMediumWaterBase());
                        }
                        //	new_customername	业主姓名	可选
                        if(!StringUtil.isEmpty(houseTransferDTO.getCustomerName())){
                            CustomerEntityNew.setCustomerName(houseTransferDTO.getCustomerName());
                        }
                        houseTransferRepository.saveCustomerDelivery(CustomerEntityNew);
                    }
                }
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("调用交房单数据接口:更新/创建数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("正式交房！house_deliver/house_transfer");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            System.out.println("调用：报修接口成功！");
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            //调用日志
            InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("调用交房单数据接口失败:更新/创建数据！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("3个模块交房单！");
            interfaceLogEntity.setErrorDate(new Date());
            interfaceLogRepository.create(interfaceLogEntity);
            System.out.println("调用：交房单接口失败！");
            return "500";
        }
    }
}
