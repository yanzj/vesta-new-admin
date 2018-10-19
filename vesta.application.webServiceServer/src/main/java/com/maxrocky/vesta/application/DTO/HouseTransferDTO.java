package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by Magic on 2017/2/8.
 */
public class HouseTransferDTO {
    private String interdeliveryPla;//计划
    private String houseCode;//房间
    private Date noticedate;//交房邀请发送日期
    private Date deliveryDate;//合同约定交房日期
    private String appointmentTime;//预约时段
    private Date beginDate;//开始办理时间
    private Date endDate;//办理结束日期
    private String progress;//办理进度
    private String isSecondary;//是否二次验房
    private String trustor;//委托人
    private String salesCount;//合同总价
    private String measureDarea;//实测面积
    private String margincompensate;//差价总额
    private String realMoney;//实际总价
    private String registrationfee;//登记费用
    private String deedtax;//契税收取
    private String publicrepairfund;//公共维修基金缴纳
    private String propertycontract;//前期物业签约
    private String propertymanagementFee;//物业费缴纳
    private String interimconvention;//业主临时公约签署
    private String waterMeter;//水表号码
    private String waterMeteReadings;//水表读数
    private String electricityMeter;//电表号码
    private String electricMeterReadings;//电表读数
    private String gasMeter;//气表号码
    private String gasMeterReading;//气表读数
    private String ownershipRegistration;//产权办理方式
    private String proxy;//产权办理委托书收集
    private String receiptRecover;//收据回收
    private String invoice;//购房合同发票开具
    private String landlordConfirm;//业主签字
    private Date confirmDate;//业主签字日期
    private String deliverer;//交房人员
    private Date delivererConfirmDate;//交房人员签字日期
    private String mediumWaterMeter;      // MEDIUM_WATER_METER
    private String mediumWaterBase;       // 中水表底数	MEDIUM_WATER_BASE

    private String customerName;//	new_customername	业主姓名	可选

    public String getInterdeliveryPla() {
        return interdeliveryPla;
    }

    public void setInterdeliveryPla(String interdeliveryPla) {
        this.interdeliveryPla = interdeliveryPla;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public Date getNoticedate() {
        return noticedate;
    }

    public void setNoticedate(Date noticedate) {
        this.noticedate = noticedate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getIsSecondary() {
        return isSecondary;
    }

    public void setIsSecondary(String isSecondary) {
        this.isSecondary = isSecondary;
    }

    public String getTrustor() {
        return trustor;
    }

    public void setTrustor(String trustor) {
        this.trustor = trustor;
    }

    public String getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(String salesCount) {
        this.salesCount = salesCount;
    }

    public String getMeasureDarea() {
        return measureDarea;
    }

    public void setMeasureDarea(String measureDarea) {
        this.measureDarea = measureDarea;
    }

    public String getMargincompensate() {
        return margincompensate;
    }

    public void setMargincompensate(String margincompensate) {
        this.margincompensate = margincompensate;
    }

    public String getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(String realMoney) {
        this.realMoney = realMoney;
    }

    public String getRegistrationfee() {
        return registrationfee;
    }

    public void setRegistrationfee(String registrationfee) {
        this.registrationfee = registrationfee;
    }

    public String getDeedtax() {
        return deedtax;
    }

    public void setDeedtax(String deedtax) {
        this.deedtax = deedtax;
    }

    public String getPublicrepairfund() {
        return publicrepairfund;
    }

    public void setPublicrepairfund(String publicrepairfund) {
        this.publicrepairfund = publicrepairfund;
    }

    public String getPropertycontract() {
        return propertycontract;
    }

    public void setPropertycontract(String propertycontract) {
        this.propertycontract = propertycontract;
    }

    public String getPropertymanagementFee() {
        return propertymanagementFee;
    }

    public void setPropertymanagementFee(String propertymanagementFee) {
        this.propertymanagementFee = propertymanagementFee;
    }

    public String getInterimconvention() {
        return interimconvention;
    }

    public void setInterimconvention(String interimconvention) {
        this.interimconvention = interimconvention;
    }

    public String getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(String waterMeter) {
        this.waterMeter = waterMeter;
    }

    public String getWaterMeteReadings() {
        return waterMeteReadings;
    }

    public void setWaterMeteReadings(String waterMeteReadings) {
        this.waterMeteReadings = waterMeteReadings;
    }

    public String getElectricityMeter() {
        return electricityMeter;
    }

    public void setElectricityMeter(String electricityMeter) {
        this.electricityMeter = electricityMeter;
    }

    public String getElectricMeterReadings() {
        return electricMeterReadings;
    }

    public void setElectricMeterReadings(String electricMeterReadings) {
        this.electricMeterReadings = electricMeterReadings;
    }

    public String getGasMeter() {
        return gasMeter;
    }

    public void setGasMeter(String gasMeter) {
        this.gasMeter = gasMeter;
    }

    public String getGasMeterReading() {
        return gasMeterReading;
    }

    public void setGasMeterReading(String gasMeterReading) {
        this.gasMeterReading = gasMeterReading;
    }

    public String getOwnershipRegistration() {
        return ownershipRegistration;
    }

    public void setOwnershipRegistration(String ownershipRegistration) {
        this.ownershipRegistration = ownershipRegistration;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getReceiptRecover() {
        return receiptRecover;
    }

    public void setReceiptRecover(String receiptRecover) {
        this.receiptRecover = receiptRecover;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getLandlordConfirm() {
        return landlordConfirm;
    }

    public void setLandlordConfirm(String landlordConfirm) {
        this.landlordConfirm = landlordConfirm;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public Date getDelivererConfirmDate() {
        return delivererConfirmDate;
    }

    public void setDelivererConfirmDate(Date delivererConfirmDate) {
        this.delivererConfirmDate = delivererConfirmDate;
    }

    public String getMediumWaterMeter() {
        return mediumWaterMeter;
    }

    public void setMediumWaterMeter(String mediumWaterMeter) {
        this.mediumWaterMeter = mediumWaterMeter;
    }

    public String getMediumWaterBase() {
        return mediumWaterBase;
    }

    public void setMediumWaterBase(String mediumWaterBase) {
        this.mediumWaterBase = mediumWaterBase;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
