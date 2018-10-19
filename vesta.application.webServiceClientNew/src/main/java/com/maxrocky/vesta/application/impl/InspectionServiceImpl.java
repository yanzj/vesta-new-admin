package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.inf.InspectionService;
import com.maxrocky.vesta.application.model.CustomerDelivery;
import com.maxrocky.vesta.application.model.ImageModel;
import com.maxrocky.vesta.application.model.InternalAcceptance;
import com.maxrocky.vesta.application.model.OpenHouse;
import com.maxrocky.vesta.application.ws.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dl on 2016/5/9.
 * 校验房
 */
@Service
public class InspectionServiceImpl implements InspectionService {
    @Autowired
    private InspectionCRMRepository inspectionCRMRepository;
    @Autowired
    private HouseOpenCRMRepository openHouseCRMRepository;
    @Autowired
    private CustomerDeliveryCRMRepository customerDeliveryCRMRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Override
    public String getInspection(InternalacceptanceHouseEntity houseEntity,
                                HouseOpenEntity houseOpen,
                                CustomerDeliveryEntity customerDeliveryEntity){
        try {
            InspectionRequest request=new InspectionRequest();
            Calendar calendar = Calendar. getInstance();
            if(houseEntity!=null) {
                InternalAcceptance internalacceptance=new InternalAcceptance();
                internalacceptance.setInterdeliveryPla(houseEntity.getInterdeliveryPla());
                internalacceptance.setHouseCode(houseEntity.getHouseCode());
                if("start".equals(houseEntity.getAcceptanceStatus())){//开始
                    internalacceptance.setAcceptanceStatus(AcceptanceStatus.start);
                }else if("finish".equals(houseEntity.getAcceptanceStatus())){//完成
                    internalacceptance.setAcceptanceStatus(AcceptanceStatus.finish);
                }
                request.setInternalacceptance(internalacceptance);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供内部预验更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("house_acceptance");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            if(houseOpen!=null) {
                OpenHouse openHouse=new OpenHouse();
                openHouse.setDeliveryPlan(houseOpen.getDeliveryPlan());
                openHouse.setHouseCode(houseOpen.getHouseCode());
                openHouse.setDescribe(houseOpen.getDescribes());
                openHouse.setOwnerSign(houseOpen.getCustomerName());//业主签字  业主姓名
                if(houseOpen.getUpdateTime()!=null) {
                    calendar.setTime(houseOpen.getUpdateTime());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    openHouse.setSigningDate(calendar);//签字时间
                }
                openHouse.setAccompanyPerson(houseOpen.getUpdateName());//陪验人
                //保存图片
                List<PropertyImageEntity> imageEntityList = propertyImageRepository.getHouseImageUrl(houseOpen.getHouseCode());
                List<ImageModel> imageList = new ArrayList<ImageModel>();
                ImageModel[] imageModelss = new ImageModel[0];
                if (imageEntityList.size() > 0) {
                    for (PropertyImageEntity propertyImage : imageEntityList) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageId(propertyImage.getImageId());
                        imageModel.setImageUrl(propertyImage.getImagePath());
                        imageModel.setImageStatus(propertyImage.getImageType());
                        imageList.add(imageModel);
                        int size=imageEntityList.size();
                        imageModelss=(ImageModel[])imageList.toArray(new ImageModel[size]);
                    }
                }
                openHouse.setImageList(imageModelss);
                if("start".equals(houseOpen.getCompletedStatus())){//开始
                    openHouse.setCompletedStatus(OpenDayStatus.start);
                }else if("finish".equals(houseOpen.getCompletedStatus())){//完成
                    openHouse.setCompletedStatus(OpenDayStatus.finish);
                }else if("inspectionNot".equals(houseOpen.getCompletedStatus())){
                    openHouse.setCompletedStatus(OpenDayStatus.inspectionNot);

                }
                if(!StringUtil.isEmpty(houseOpen.getQuality())) {
                    if (houseOpen.getQuality().equals("highlySatisfied")) {
                        openHouse.setQuality(Quality.highlySatisfied);//非常满意-5分
                    } else if (houseOpen.getQuality().equals("satisfied")) {
                        openHouse.setQuality(Quality.satisfied);//满意-4分
                    } else if (houseOpen.getQuality().equals("qualified")) {
                        openHouse.setQuality(Quality.qualified);//一般-3分
                    } else if (houseOpen.getQuality().equals("dissatisfied")) {
                        openHouse.setQuality(Quality.dissatisfied);//不满意-2分
                    } else if (houseOpen.getQuality().equals("highlyDissatisfied")) {
                        openHouse.setQuality(Quality.highlyDissatisfied);//非常不满意-1分
                    }
                }
                if(!StringUtil.isEmpty(houseOpen.getSchedulesatisfied())) {
                    if (houseOpen.getSchedulesatisfied().equals("highlySatisfied")) {
                        openHouse.setSchedulesatisfied(Schedulesatisfied.highlySatisfied);//非常满意-5分
                    } else if (houseOpen.getSchedulesatisfied().equals("satisfied")) {
                        openHouse.setSchedulesatisfied(Schedulesatisfied.satisfied);//满意-4分
                    } else if (houseOpen.getSchedulesatisfied().equals("qualified")) {
                        openHouse.setSchedulesatisfied(Schedulesatisfied.qualified);//一般-3分
                    } else if (houseOpen.getSchedulesatisfied().equals("dissatisfied")) {
                        openHouse.setSchedulesatisfied(Schedulesatisfied.dissatisfied);//不满意-2分
                    } else if (houseOpen.getSchedulesatisfied().equals("highlyDissatisfied")) {
                        openHouse.setSchedulesatisfied(Schedulesatisfied.highlyDissatisfied);//非常不满意-1分
                    }
                }
                request.setOpenHouse(openHouse);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供业主开放日更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("house_open");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            if(customerDeliveryEntity!=null) {
                CustomerDelivery customerDelivery = new CustomerDelivery();
                customerDelivery.setDeliveryPlan(customerDeliveryEntity.getInterdeliveryPla());
                customerDelivery.setHouseCode(customerDeliveryEntity.getHouseCode());
                if(customerDeliveryEntity.getNoticedate()!=null) {
                    calendar.setTime(customerDeliveryEntity.getNoticedate());
//                    calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    customerDelivery.setNoticedate(calendar);
                }
                if(customerDeliveryEntity.getDeliveryDate()!=null) {
                    calendar.setTime(customerDeliveryEntity.getDeliveryDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    customerDelivery.setDeliveryDate(calendar);
                }
                if(customerDeliveryEntity.getAppointmentTime()!=null) {
                    if (customerDeliveryEntity.getAppointmentTime().equals("noon")) {
                        customerDelivery.setAppointmentTime(AppointmentTime.noon);//上午
                    } else if (customerDeliveryEntity.getAppointmentTime().equals("afternoon")) {
                        customerDelivery.setAppointmentTime(AppointmentTime.afternoon);//下午}
                    }
                }
                if(customerDeliveryEntity.getNoticedate()!=null) {
                    calendar.setTime(customerDeliveryEntity.getNoticedate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    customerDelivery.setNoticedate(calendar);
                }
                if(customerDeliveryEntity.getBeginDate()!=null) {
                    calendar.setTime(customerDeliveryEntity.getBeginDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    customerDelivery.setBeginDate(calendar);
                }
                if(customerDeliveryEntity.getEndDate()!=null) {
                    calendar.setTime(customerDeliveryEntity.getEndDate());
                    calendar.getTime();
                    customerDelivery.setEndDate(calendar);
                }
                //交房状态
                if(!StringUtil.isEmpty(customerDeliveryEntity.getProgress())) {
                    if (customerDeliveryEntity.getProgress().equals("activated")) {
                        customerDelivery.setProgress(Progress.activated);//开启
                    } else if (customerDeliveryEntity.getProgress().equals("acceptanceBy")) {
                        customerDelivery.setProgress(Progress.acceptanceBy);//验收通过
                    } else if (customerDeliveryEntity.getProgress().equals("acceptanceNotThrough")) {
                        customerDelivery.setProgress(Progress.acceptanceNotThrough);//验收未通过
                    } else if (customerDeliveryEntity.getProgress().equals("absence")) {
                        customerDelivery.setProgress(Progress.absence);//业主未到访
                    }
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getIsSecondary())) {
                    customerDelivery.setIsSecondary(Boolean.valueOf(customerDeliveryEntity.getIsSecondary()));
                }
                customerDelivery.setTrustor(customerDeliveryEntity.getTrustor());
                if(!StringUtil.isEmpty(customerDeliveryEntity.getSalesCount())) {
                    customerDelivery.setSalesCount(Double.valueOf(customerDeliveryEntity.getSalesCount()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getMeasureDarea())) {
                    customerDelivery.setMeasureDarea(Double.valueOf(customerDeliveryEntity.getMeasureDarea()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getMargincompensate())) {
                    customerDelivery.setMargincompensate(Double.valueOf(customerDeliveryEntity.getMargincompensate()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getRealMoney())) {
                    customerDelivery.setRealMoney(Double.valueOf(customerDeliveryEntity.getRealMoney()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getRegistrationfee())) {
                    customerDelivery.setRegistrationfee(Boolean.valueOf(customerDeliveryEntity.getRegistrationfee()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getDeedtax())) {
                    customerDelivery.setDeedtax(Boolean.valueOf(customerDeliveryEntity.getDeedtax()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getPublicrepairfund())) {
                    customerDelivery.setPublicrepairfund(Boolean.valueOf(customerDeliveryEntity.getPublicrepairfund()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getPropertycontract())) {
                    customerDelivery.setPropertycontract(Boolean.valueOf(customerDeliveryEntity.getPropertycontract()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getPropertymanagementFee())) {
                    customerDelivery.setPropertymanagementFee(Boolean.valueOf(customerDeliveryEntity.getPropertymanagementFee()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getInterimconvention())) {
                    customerDelivery.setInterimconvention(Boolean.valueOf(customerDeliveryEntity.getInterimconvention()));
                }
                //水表表号
                if(!StringUtil.isEmpty(customerDeliveryEntity.getWaterMeter())){
                    customerDelivery.setWaterMeter(customerDeliveryEntity.getWaterMeter());
                }
                //水表底数
                if(!StringUtil.isEmpty(customerDeliveryEntity.getWaterMeteReadings())) {
                    customerDelivery.setWaterMeteReadings(Double.valueOf(customerDeliveryEntity.getWaterMeteReadings()));
                }
                //电表表号
                if(!StringUtil.isEmpty(customerDeliveryEntity.getElectricityMeter())){
                    customerDelivery.setElectricityMeter(customerDeliveryEntity.getElectricityMeter());
                }
                //电表底数
                if(!StringUtil.isEmpty(customerDeliveryEntity.getElectricMeterReadings())) {
                    customerDelivery.setElectricMeterReadings(Double.valueOf(customerDeliveryEntity.getElectricMeterReadings()));
                }
                //气表表号
                if(!StringUtil.isEmpty(customerDeliveryEntity.getGasMeter())){
                    customerDelivery.setGasMeter(customerDeliveryEntity.getGasMeter());
                }
                //气表底数
                if(!StringUtil.isEmpty(customerDeliveryEntity.getGasMeterReading())) {
                    customerDelivery.setGasMeterReading(Double.valueOf(customerDeliveryEntity.getGasMeterReading()));
                }
                //中水表号
                if(!StringUtil.isEmpty(customerDeliveryEntity.getMediumWaterMeter())){
                    customerDelivery.setMediumWaterMeter(customerDeliveryEntity.getMediumWaterMeter());
                }
                //中水表底数
                if(!StringUtil.isEmpty(customerDeliveryEntity.getMediumWaterBase())){
                    customerDelivery.setMediumWaterMeterReadings(Double.valueOf(customerDeliveryEntity.getMediumWaterBase()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getOwnershipRegistration())) {
                    customerDelivery.setOwnershipRegistration(Boolean.valueOf(customerDeliveryEntity.getOwnershipRegistration()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getProxy())) {
                    customerDelivery.setProxy(Boolean.valueOf(customerDeliveryEntity.getProxy()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getReceiptRecover())) {
                    customerDelivery.setReceiptRecover(Boolean.valueOf(customerDeliveryEntity.getReceiptRecover()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getInvoice())) {
                    customerDelivery.setInvoice(Boolean.valueOf(customerDeliveryEntity.getInvoice()));
                }
                if(!StringUtil.isEmpty(customerDeliveryEntity.getLandlordConfirm())) {
                    customerDelivery.setLandlordConfirm(Boolean.valueOf(customerDeliveryEntity.getLandlordConfirm()));
                }
                //客户姓名
                if(!StringUtil.isEmpty(customerDeliveryEntity.getCustomerName())) {
                    customerDelivery.setCustomerName(customerDeliveryEntity.getCustomerName());
                }
                //交房人员
                if (!StringUtil.isEmpty(customerDeliveryEntity.getDeliverer())) {
                    customerDelivery.setDeliverer(customerDeliveryEntity.getDeliverer());
                }
                //交房人员签字日期
                if(customerDeliveryEntity.getDelivererConfirmDate()!=null) {
                    calendar.setTime(customerDeliveryEntity.getDelivererConfirmDate());
//                    calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    customerDelivery.setDelivererConfirmDate(calendar);
                }
                //保存图片
                List<PropertyImageEntity> imageEntityList = propertyImageRepository.getHouseTransferImageUrl(customerDeliveryEntity.getId());
                List<ImageModel> imageList = new ArrayList<ImageModel>();
                ImageModel[] imageModelss = new ImageModel[0];
                if (imageEntityList.size() > 0) {
                    for (PropertyImageEntity propertyImage : imageEntityList) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageId(propertyImage.getImageId());
                        imageModel.setImageUrl(propertyImage.getImagePath());
                        imageModel.setImageStatus(propertyImage.getState());
                        imageList.add(imageModel);


                        int size=imageEntityList.size();
                        imageModelss=(ImageModel[])imageList.toArray(new ImageModel[size]);
                        customerDelivery.setImageList(imageModelss);
                    }
                }
                request.setCustomerDelivery(customerDelivery);
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供交房更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("house_deliver");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            InspectionResponse response=iBasicService.houseInspection(request);
            if(response.getHeader().getStatus().equals("0")){//失败
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供内部交房/预验接口:同步数据！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("house_acceptance/house_open/house_deliver");
                interfaceLogEntity.setErrorDate(new Date());
                //interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }else if(response.getHeader().getStatus().equals("1")){//成功
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供内部交房/预验接口:同步数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("house_acceptance/house_open/house_deliver");
                interfaceLogEntity.setErrorDate(new Date());
                //interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "500";
        }
    }
}
