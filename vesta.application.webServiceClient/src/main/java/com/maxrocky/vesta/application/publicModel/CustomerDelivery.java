/**
 * CustomerDelivery.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

public class CustomerDelivery implements java.io.Serializable {
    private AppointmentTime appointmentTime;

    private java.util.Calendar beginDate;

    private java.util.Calendar confirmDate;

    private String customerName;

    private Boolean deedtax;

    private String deliverer;

    private java.util.Calendar delivererConfirmDate;

    private java.util.Calendar deliveryDate;

    private String deliveryPlan;

    private Double electricMeterReadings;

    private String electricityMeter;

    private java.util.Calendar endDate;

    private String gasMeter;

    private Double gasMeterReading;

    private String houseCode;

    private ImageModel[] imageList;

    private Boolean interimconvention;

    private Boolean invoice;

    private Boolean isSecondary;

    private Boolean landlordConfirm;

    private Double margincompensate;

    private Double measureDarea;

    private String mediumWaterMeter;

    private Double mediumWaterMeterReadings;

    private java.util.Calendar noticedate;

    private Boolean ownershipRegistration;

    private Progress progress;

    private Boolean propertycontract;

    private Boolean propertymanagementFee;

    private Boolean proxy;

    private Boolean publicrepairfund;

    private Double realMoney;

    private Boolean receiptRecover;

    private Boolean registrationfee;

    private Double salesCount;

    private String trustor;

    private Double waterMeteReadings;

    private String waterMeter;

    public CustomerDelivery() {
    }

    public CustomerDelivery(
           AppointmentTime appointmentTime,
           java.util.Calendar beginDate,
           java.util.Calendar confirmDate,
           String customerName,
           Boolean deedtax,
           String deliverer,
           java.util.Calendar delivererConfirmDate,
           java.util.Calendar deliveryDate,
           String deliveryPlan,
           Double electricMeterReadings,
           String electricityMeter,
           java.util.Calendar endDate,
           String gasMeter,
           Double gasMeterReading,
           String houseCode,
           ImageModel[] imageList,
           Boolean interimconvention,
           Boolean invoice,
           Boolean isSecondary,
           Boolean landlordConfirm,
           Double margincompensate,
           Double measureDarea,
           String mediumWaterMeter,
           Double mediumWaterMeterReadings,
           java.util.Calendar noticedate,
           Boolean ownershipRegistration,
           Progress progress,
           Boolean propertycontract,
           Boolean propertymanagementFee,
           Boolean proxy,
           Boolean publicrepairfund,
           Double realMoney,
           Boolean receiptRecover,
           Boolean registrationfee,
           Double salesCount,
           String trustor,
           Double waterMeteReadings,
           String waterMeter) {
           this.appointmentTime = appointmentTime;
           this.beginDate = beginDate;
           this.confirmDate = confirmDate;
           this.customerName = customerName;
           this.deedtax = deedtax;
           this.deliverer = deliverer;
           this.delivererConfirmDate = delivererConfirmDate;
           this.deliveryDate = deliveryDate;
           this.deliveryPlan = deliveryPlan;
           this.electricMeterReadings = electricMeterReadings;
           this.electricityMeter = electricityMeter;
           this.endDate = endDate;
           this.gasMeter = gasMeter;
           this.gasMeterReading = gasMeterReading;
           this.houseCode = houseCode;
           this.imageList = imageList;
           this.interimconvention = interimconvention;
           this.invoice = invoice;
           this.isSecondary = isSecondary;
           this.landlordConfirm = landlordConfirm;
           this.margincompensate = margincompensate;
           this.measureDarea = measureDarea;
           this.mediumWaterMeter = mediumWaterMeter;
           this.mediumWaterMeterReadings = mediumWaterMeterReadings;
           this.noticedate = noticedate;
           this.ownershipRegistration = ownershipRegistration;
           this.progress = progress;
           this.propertycontract = propertycontract;
           this.propertymanagementFee = propertymanagementFee;
           this.proxy = proxy;
           this.publicrepairfund = publicrepairfund;
           this.realMoney = realMoney;
           this.receiptRecover = receiptRecover;
           this.registrationfee = registrationfee;
           this.salesCount = salesCount;
           this.trustor = trustor;
           this.waterMeteReadings = waterMeteReadings;
           this.waterMeter = waterMeter;
    }


    /**
     * Gets the appointmentTime value for this CustomerDelivery.
     * 
     * @return appointmentTime
     */
    public AppointmentTime getAppointmentTime() {
        return appointmentTime;
    }


    /**
     * Sets the appointmentTime value for this CustomerDelivery.
     * 
     * @param appointmentTime
     */
    public void setAppointmentTime(AppointmentTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


    /**
     * Gets the beginDate value for this CustomerDelivery.
     * 
     * @return beginDate
     */
    public java.util.Calendar getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this CustomerDelivery.
     * 
     * @param beginDate
     */
    public void setBeginDate(java.util.Calendar beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the confirmDate value for this CustomerDelivery.
     * 
     * @return confirmDate
     */
    public java.util.Calendar getConfirmDate() {
        return confirmDate;
    }


    /**
     * Sets the confirmDate value for this CustomerDelivery.
     * 
     * @param confirmDate
     */
    public void setConfirmDate(java.util.Calendar confirmDate) {
        this.confirmDate = confirmDate;
    }


    /**
     * Gets the customerName value for this CustomerDelivery.
     * 
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this CustomerDelivery.
     * 
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the deedtax value for this CustomerDelivery.
     * 
     * @return deedtax
     */
    public Boolean getDeedtax() {
        return deedtax;
    }


    /**
     * Sets the deedtax value for this CustomerDelivery.
     * 
     * @param deedtax
     */
    public void setDeedtax(Boolean deedtax) {
        this.deedtax = deedtax;
    }


    /**
     * Gets the deliverer value for this CustomerDelivery.
     * 
     * @return deliverer
     */
    public String getDeliverer() {
        return deliverer;
    }


    /**
     * Sets the deliverer value for this CustomerDelivery.
     * 
     * @param deliverer
     */
    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }


    /**
     * Gets the delivererConfirmDate value for this CustomerDelivery.
     * 
     * @return delivererConfirmDate
     */
    public java.util.Calendar getDelivererConfirmDate() {
        return delivererConfirmDate;
    }


    /**
     * Sets the delivererConfirmDate value for this CustomerDelivery.
     * 
     * @param delivererConfirmDate
     */
    public void setDelivererConfirmDate(java.util.Calendar delivererConfirmDate) {
        this.delivererConfirmDate = delivererConfirmDate;
    }


    /**
     * Gets the deliveryDate value for this CustomerDelivery.
     * 
     * @return deliveryDate
     */
    public java.util.Calendar getDeliveryDate() {
        return deliveryDate;
    }


    /**
     * Sets the deliveryDate value for this CustomerDelivery.
     * 
     * @param deliveryDate
     */
    public void setDeliveryDate(java.util.Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    /**
     * Gets the deliveryPlan value for this CustomerDelivery.
     * 
     * @return deliveryPlan
     */
    public String getDeliveryPlan() {
        return deliveryPlan;
    }


    /**
     * Sets the deliveryPlan value for this CustomerDelivery.
     * 
     * @param deliveryPlan
     */
    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }


    /**
     * Gets the electricMeterReadings value for this CustomerDelivery.
     * 
     * @return electricMeterReadings
     */
    public Double getElectricMeterReadings() {
        return electricMeterReadings;
    }


    /**
     * Sets the electricMeterReadings value for this CustomerDelivery.
     * 
     * @param electricMeterReadings
     */
    public void setElectricMeterReadings(Double electricMeterReadings) {
        this.electricMeterReadings = electricMeterReadings;
    }


    /**
     * Gets the electricityMeter value for this CustomerDelivery.
     * 
     * @return electricityMeter
     */
    public String getElectricityMeter() {
        return electricityMeter;
    }


    /**
     * Sets the electricityMeter value for this CustomerDelivery.
     * 
     * @param electricityMeter
     */
    public void setElectricityMeter(String electricityMeter) {
        this.electricityMeter = electricityMeter;
    }


    /**
     * Gets the endDate value for this CustomerDelivery.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this CustomerDelivery.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the gasMeter value for this CustomerDelivery.
     * 
     * @return gasMeter
     */
    public String getGasMeter() {
        return gasMeter;
    }


    /**
     * Sets the gasMeter value for this CustomerDelivery.
     * 
     * @param gasMeter
     */
    public void setGasMeter(String gasMeter) {
        this.gasMeter = gasMeter;
    }


    /**
     * Gets the gasMeterReading value for this CustomerDelivery.
     * 
     * @return gasMeterReading
     */
    public Double getGasMeterReading() {
        return gasMeterReading;
    }


    /**
     * Sets the gasMeterReading value for this CustomerDelivery.
     * 
     * @param gasMeterReading
     */
    public void setGasMeterReading(Double gasMeterReading) {
        this.gasMeterReading = gasMeterReading;
    }


    /**
     * Gets the houseCode value for this CustomerDelivery.
     * 
     * @return houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }


    /**
     * Sets the houseCode value for this CustomerDelivery.
     * 
     * @param houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }


    /**
     * Gets the imageList value for this CustomerDelivery.
     * 
     * @return imageList
     */
    public ImageModel[] getImageList() {
        return imageList;
    }


    /**
     * Sets the imageList value for this CustomerDelivery.
     * 
     * @param imageList
     */
    public void setImageList(ImageModel[] imageList) {
        this.imageList = imageList;
    }


    /**
     * Gets the interimconvention value for this CustomerDelivery.
     * 
     * @return interimconvention
     */
    public Boolean getInterimconvention() {
        return interimconvention;
    }


    /**
     * Sets the interimconvention value for this CustomerDelivery.
     * 
     * @param interimconvention
     */
    public void setInterimconvention(Boolean interimconvention) {
        this.interimconvention = interimconvention;
    }


    /**
     * Gets the invoice value for this CustomerDelivery.
     * 
     * @return invoice
     */
    public Boolean getInvoice() {
        return invoice;
    }


    /**
     * Sets the invoice value for this CustomerDelivery.
     * 
     * @param invoice
     */
    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }


    /**
     * Gets the isSecondary value for this CustomerDelivery.
     * 
     * @return isSecondary
     */
    public Boolean getIsSecondary() {
        return isSecondary;
    }


    /**
     * Sets the isSecondary value for this CustomerDelivery.
     * 
     * @param isSecondary
     */
    public void setIsSecondary(Boolean isSecondary) {
        this.isSecondary = isSecondary;
    }


    /**
     * Gets the landlordConfirm value for this CustomerDelivery.
     * 
     * @return landlordConfirm
     */
    public Boolean getLandlordConfirm() {
        return landlordConfirm;
    }


    /**
     * Sets the landlordConfirm value for this CustomerDelivery.
     * 
     * @param landlordConfirm
     */
    public void setLandlordConfirm(Boolean landlordConfirm) {
        this.landlordConfirm = landlordConfirm;
    }


    /**
     * Gets the margincompensate value for this CustomerDelivery.
     * 
     * @return margincompensate
     */
    public Double getMargincompensate() {
        return margincompensate;
    }


    /**
     * Sets the margincompensate value for this CustomerDelivery.
     * 
     * @param margincompensate
     */
    public void setMargincompensate(Double margincompensate) {
        this.margincompensate = margincompensate;
    }


    /**
     * Gets the measureDarea value for this CustomerDelivery.
     * 
     * @return measureDarea
     */
    public Double getMeasureDarea() {
        return measureDarea;
    }


    /**
     * Sets the measureDarea value for this CustomerDelivery.
     * 
     * @param measureDarea
     */
    public void setMeasureDarea(Double measureDarea) {
        this.measureDarea = measureDarea;
    }


    /**
     * Gets the mediumWaterMeter value for this CustomerDelivery.
     * 
     * @return mediumWaterMeter
     */
    public String getMediumWaterMeter() {
        return mediumWaterMeter;
    }


    /**
     * Sets the mediumWaterMeter value for this CustomerDelivery.
     * 
     * @param mediumWaterMeter
     */
    public void setMediumWaterMeter(String mediumWaterMeter) {
        this.mediumWaterMeter = mediumWaterMeter;
    }


    /**
     * Gets the mediumWaterMeterReadings value for this CustomerDelivery.
     * 
     * @return mediumWaterMeterReadings
     */
    public Double getMediumWaterMeterReadings() {
        return mediumWaterMeterReadings;
    }


    /**
     * Sets the mediumWaterMeterReadings value for this CustomerDelivery.
     * 
     * @param mediumWaterMeterReadings
     */
    public void setMediumWaterMeterReadings(Double mediumWaterMeterReadings) {
        this.mediumWaterMeterReadings = mediumWaterMeterReadings;
    }


    /**
     * Gets the noticedate value for this CustomerDelivery.
     * 
     * @return noticedate
     */
    public java.util.Calendar getNoticedate() {
        return noticedate;
    }


    /**
     * Sets the noticedate value for this CustomerDelivery.
     * 
     * @param noticedate
     */
    public void setNoticedate(java.util.Calendar noticedate) {
        this.noticedate = noticedate;
    }


    /**
     * Gets the ownershipRegistration value for this CustomerDelivery.
     * 
     * @return ownershipRegistration
     */
    public Boolean getOwnershipRegistration() {
        return ownershipRegistration;
    }


    /**
     * Sets the ownershipRegistration value for this CustomerDelivery.
     * 
     * @param ownershipRegistration
     */
    public void setOwnershipRegistration(Boolean ownershipRegistration) {
        this.ownershipRegistration = ownershipRegistration;
    }


    /**
     * Gets the progress value for this CustomerDelivery.
     * 
     * @return progress
     */
    public Progress getProgress() {
        return progress;
    }


    /**
     * Sets the progress value for this CustomerDelivery.
     * 
     * @param progress
     */
    public void setProgress(Progress progress) {
        this.progress = progress;
    }


    /**
     * Gets the propertycontract value for this CustomerDelivery.
     * 
     * @return propertycontract
     */
    public Boolean getPropertycontract() {
        return propertycontract;
    }


    /**
     * Sets the propertycontract value for this CustomerDelivery.
     * 
     * @param propertycontract
     */
    public void setPropertycontract(Boolean propertycontract) {
        this.propertycontract = propertycontract;
    }


    /**
     * Gets the propertymanagementFee value for this CustomerDelivery.
     * 
     * @return propertymanagementFee
     */
    public Boolean getPropertymanagementFee() {
        return propertymanagementFee;
    }


    /**
     * Sets the propertymanagementFee value for this CustomerDelivery.
     * 
     * @param propertymanagementFee
     */
    public void setPropertymanagementFee(Boolean propertymanagementFee) {
        this.propertymanagementFee = propertymanagementFee;
    }


    /**
     * Gets the proxy value for this CustomerDelivery.
     * 
     * @return proxy
     */
    public Boolean getProxy() {
        return proxy;
    }


    /**
     * Sets the proxy value for this CustomerDelivery.
     * 
     * @param proxy
     */
    public void setProxy(Boolean proxy) {
        this.proxy = proxy;
    }


    /**
     * Gets the publicrepairfund value for this CustomerDelivery.
     * 
     * @return publicrepairfund
     */
    public Boolean getPublicrepairfund() {
        return publicrepairfund;
    }


    /**
     * Sets the publicrepairfund value for this CustomerDelivery.
     * 
     * @param publicrepairfund
     */
    public void setPublicrepairfund(Boolean publicrepairfund) {
        this.publicrepairfund = publicrepairfund;
    }


    /**
     * Gets the realMoney value for this CustomerDelivery.
     * 
     * @return realMoney
     */
    public Double getRealMoney() {
        return realMoney;
    }


    /**
     * Sets the realMoney value for this CustomerDelivery.
     * 
     * @param realMoney
     */
    public void setRealMoney(Double realMoney) {
        this.realMoney = realMoney;
    }


    /**
     * Gets the receiptRecover value for this CustomerDelivery.
     * 
     * @return receiptRecover
     */
    public Boolean getReceiptRecover() {
        return receiptRecover;
    }


    /**
     * Sets the receiptRecover value for this CustomerDelivery.
     * 
     * @param receiptRecover
     */
    public void setReceiptRecover(Boolean receiptRecover) {
        this.receiptRecover = receiptRecover;
    }


    /**
     * Gets the registrationfee value for this CustomerDelivery.
     * 
     * @return registrationfee
     */
    public Boolean getRegistrationfee() {
        return registrationfee;
    }


    /**
     * Sets the registrationfee value for this CustomerDelivery.
     * 
     * @param registrationfee
     */
    public void setRegistrationfee(Boolean registrationfee) {
        this.registrationfee = registrationfee;
    }


    /**
     * Gets the salesCount value for this CustomerDelivery.
     * 
     * @return salesCount
     */
    public Double getSalesCount() {
        return salesCount;
    }


    /**
     * Sets the salesCount value for this CustomerDelivery.
     * 
     * @param salesCount
     */
    public void setSalesCount(Double salesCount) {
        this.salesCount = salesCount;
    }


    /**
     * Gets the trustor value for this CustomerDelivery.
     * 
     * @return trustor
     */
    public String getTrustor() {
        return trustor;
    }


    /**
     * Sets the trustor value for this CustomerDelivery.
     * 
     * @param trustor
     */
    public void setTrustor(String trustor) {
        this.trustor = trustor;
    }


    /**
     * Gets the waterMeteReadings value for this CustomerDelivery.
     * 
     * @return waterMeteReadings
     */
    public Double getWaterMeteReadings() {
        return waterMeteReadings;
    }


    /**
     * Sets the waterMeteReadings value for this CustomerDelivery.
     * 
     * @param waterMeteReadings
     */
    public void setWaterMeteReadings(Double waterMeteReadings) {
        this.waterMeteReadings = waterMeteReadings;
    }


    /**
     * Gets the waterMeter value for this CustomerDelivery.
     * 
     * @return waterMeter
     */
    public String getWaterMeter() {
        return waterMeter;
    }


    /**
     * Sets the waterMeter value for this CustomerDelivery.
     * 
     * @param waterMeter
     */
    public void setWaterMeter(String waterMeter) {
        this.waterMeter = waterMeter;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CustomerDelivery)) return false;
        CustomerDelivery other = (CustomerDelivery) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appointmentTime==null && other.getAppointmentTime()==null) || 
             (this.appointmentTime!=null &&
              this.appointmentTime.equals(other.getAppointmentTime()))) &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.confirmDate==null && other.getConfirmDate()==null) || 
             (this.confirmDate!=null &&
              this.confirmDate.equals(other.getConfirmDate()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.deedtax==null && other.getDeedtax()==null) || 
             (this.deedtax!=null &&
              this.deedtax.equals(other.getDeedtax()))) &&
            ((this.deliverer==null && other.getDeliverer()==null) || 
             (this.deliverer!=null &&
              this.deliverer.equals(other.getDeliverer()))) &&
            ((this.delivererConfirmDate==null && other.getDelivererConfirmDate()==null) || 
             (this.delivererConfirmDate!=null &&
              this.delivererConfirmDate.equals(other.getDelivererConfirmDate()))) &&
            ((this.deliveryDate==null && other.getDeliveryDate()==null) || 
             (this.deliveryDate!=null &&
              this.deliveryDate.equals(other.getDeliveryDate()))) &&
            ((this.deliveryPlan==null && other.getDeliveryPlan()==null) || 
             (this.deliveryPlan!=null &&
              this.deliveryPlan.equals(other.getDeliveryPlan()))) &&
            ((this.electricMeterReadings==null && other.getElectricMeterReadings()==null) || 
             (this.electricMeterReadings!=null &&
              this.electricMeterReadings.equals(other.getElectricMeterReadings()))) &&
            ((this.electricityMeter==null && other.getElectricityMeter()==null) || 
             (this.electricityMeter!=null &&
              this.electricityMeter.equals(other.getElectricityMeter()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.gasMeter==null && other.getGasMeter()==null) || 
             (this.gasMeter!=null &&
              this.gasMeter.equals(other.getGasMeter()))) &&
            ((this.gasMeterReading==null && other.getGasMeterReading()==null) || 
             (this.gasMeterReading!=null &&
              this.gasMeterReading.equals(other.getGasMeterReading()))) &&
            ((this.houseCode==null && other.getHouseCode()==null) || 
             (this.houseCode!=null &&
              this.houseCode.equals(other.getHouseCode()))) &&
            ((this.imageList==null && other.getImageList()==null) || 
             (this.imageList!=null &&
              java.util.Arrays.equals(this.imageList, other.getImageList()))) &&
            ((this.interimconvention==null && other.getInterimconvention()==null) || 
             (this.interimconvention!=null &&
              this.interimconvention.equals(other.getInterimconvention()))) &&
            ((this.invoice==null && other.getInvoice()==null) || 
             (this.invoice!=null &&
              this.invoice.equals(other.getInvoice()))) &&
            ((this.isSecondary==null && other.getIsSecondary()==null) || 
             (this.isSecondary!=null &&
              this.isSecondary.equals(other.getIsSecondary()))) &&
            ((this.landlordConfirm==null && other.getLandlordConfirm()==null) || 
             (this.landlordConfirm!=null &&
              this.landlordConfirm.equals(other.getLandlordConfirm()))) &&
            ((this.margincompensate==null && other.getMargincompensate()==null) || 
             (this.margincompensate!=null &&
              this.margincompensate.equals(other.getMargincompensate()))) &&
            ((this.measureDarea==null && other.getMeasureDarea()==null) || 
             (this.measureDarea!=null &&
              this.measureDarea.equals(other.getMeasureDarea()))) &&
            ((this.mediumWaterMeter==null && other.getMediumWaterMeter()==null) || 
             (this.mediumWaterMeter!=null &&
              this.mediumWaterMeter.equals(other.getMediumWaterMeter()))) &&
            ((this.mediumWaterMeterReadings==null && other.getMediumWaterMeterReadings()==null) || 
             (this.mediumWaterMeterReadings!=null &&
              this.mediumWaterMeterReadings.equals(other.getMediumWaterMeterReadings()))) &&
            ((this.noticedate==null && other.getNoticedate()==null) || 
             (this.noticedate!=null &&
              this.noticedate.equals(other.getNoticedate()))) &&
            ((this.ownershipRegistration==null && other.getOwnershipRegistration()==null) || 
             (this.ownershipRegistration!=null &&
              this.ownershipRegistration.equals(other.getOwnershipRegistration()))) &&
            ((this.progress==null && other.getProgress()==null) || 
             (this.progress!=null &&
              this.progress.equals(other.getProgress()))) &&
            ((this.propertycontract==null && other.getPropertycontract()==null) || 
             (this.propertycontract!=null &&
              this.propertycontract.equals(other.getPropertycontract()))) &&
            ((this.propertymanagementFee==null && other.getPropertymanagementFee()==null) || 
             (this.propertymanagementFee!=null &&
              this.propertymanagementFee.equals(other.getPropertymanagementFee()))) &&
            ((this.proxy==null && other.getProxy()==null) || 
             (this.proxy!=null &&
              this.proxy.equals(other.getProxy()))) &&
            ((this.publicrepairfund==null && other.getPublicrepairfund()==null) || 
             (this.publicrepairfund!=null &&
              this.publicrepairfund.equals(other.getPublicrepairfund()))) &&
            ((this.realMoney==null && other.getRealMoney()==null) || 
             (this.realMoney!=null &&
              this.realMoney.equals(other.getRealMoney()))) &&
            ((this.receiptRecover==null && other.getReceiptRecover()==null) || 
             (this.receiptRecover!=null &&
              this.receiptRecover.equals(other.getReceiptRecover()))) &&
            ((this.registrationfee==null && other.getRegistrationfee()==null) || 
             (this.registrationfee!=null &&
              this.registrationfee.equals(other.getRegistrationfee()))) &&
            ((this.salesCount==null && other.getSalesCount()==null) || 
             (this.salesCount!=null &&
              this.salesCount.equals(other.getSalesCount()))) &&
            ((this.trustor==null && other.getTrustor()==null) || 
             (this.trustor!=null &&
              this.trustor.equals(other.getTrustor()))) &&
            ((this.waterMeteReadings==null && other.getWaterMeteReadings()==null) || 
             (this.waterMeteReadings!=null &&
              this.waterMeteReadings.equals(other.getWaterMeteReadings()))) &&
            ((this.waterMeter==null && other.getWaterMeter()==null) || 
             (this.waterMeter!=null &&
              this.waterMeter.equals(other.getWaterMeter())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAppointmentTime() != null) {
            _hashCode += getAppointmentTime().hashCode();
        }
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getConfirmDate() != null) {
            _hashCode += getConfirmDate().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getDeedtax() != null) {
            _hashCode += getDeedtax().hashCode();
        }
        if (getDeliverer() != null) {
            _hashCode += getDeliverer().hashCode();
        }
        if (getDelivererConfirmDate() != null) {
            _hashCode += getDelivererConfirmDate().hashCode();
        }
        if (getDeliveryDate() != null) {
            _hashCode += getDeliveryDate().hashCode();
        }
        if (getDeliveryPlan() != null) {
            _hashCode += getDeliveryPlan().hashCode();
        }
        if (getElectricMeterReadings() != null) {
            _hashCode += getElectricMeterReadings().hashCode();
        }
        if (getElectricityMeter() != null) {
            _hashCode += getElectricityMeter().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getGasMeter() != null) {
            _hashCode += getGasMeter().hashCode();
        }
        if (getGasMeterReading() != null) {
            _hashCode += getGasMeterReading().hashCode();
        }
        if (getHouseCode() != null) {
            _hashCode += getHouseCode().hashCode();
        }
        if (getImageList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImageList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getImageList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInterimconvention() != null) {
            _hashCode += getInterimconvention().hashCode();
        }
        if (getInvoice() != null) {
            _hashCode += getInvoice().hashCode();
        }
        if (getIsSecondary() != null) {
            _hashCode += getIsSecondary().hashCode();
        }
        if (getLandlordConfirm() != null) {
            _hashCode += getLandlordConfirm().hashCode();
        }
        if (getMargincompensate() != null) {
            _hashCode += getMargincompensate().hashCode();
        }
        if (getMeasureDarea() != null) {
            _hashCode += getMeasureDarea().hashCode();
        }
        if (getMediumWaterMeter() != null) {
            _hashCode += getMediumWaterMeter().hashCode();
        }
        if (getMediumWaterMeterReadings() != null) {
            _hashCode += getMediumWaterMeterReadings().hashCode();
        }
        if (getNoticedate() != null) {
            _hashCode += getNoticedate().hashCode();
        }
        if (getOwnershipRegistration() != null) {
            _hashCode += getOwnershipRegistration().hashCode();
        }
        if (getProgress() != null) {
            _hashCode += getProgress().hashCode();
        }
        if (getPropertycontract() != null) {
            _hashCode += getPropertycontract().hashCode();
        }
        if (getPropertymanagementFee() != null) {
            _hashCode += getPropertymanagementFee().hashCode();
        }
        if (getProxy() != null) {
            _hashCode += getProxy().hashCode();
        }
        if (getPublicrepairfund() != null) {
            _hashCode += getPublicrepairfund().hashCode();
        }
        if (getRealMoney() != null) {
            _hashCode += getRealMoney().hashCode();
        }
        if (getReceiptRecover() != null) {
            _hashCode += getReceiptRecover().hashCode();
        }
        if (getRegistrationfee() != null) {
            _hashCode += getRegistrationfee().hashCode();
        }
        if (getSalesCount() != null) {
            _hashCode += getSalesCount().hashCode();
        }
        if (getTrustor() != null) {
            _hashCode += getTrustor().hashCode();
        }
        if (getWaterMeteReadings() != null) {
            _hashCode += getWaterMeteReadings().hashCode();
        }
        if (getWaterMeter() != null) {
            _hashCode += getWaterMeter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerDelivery.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CustomerDelivery"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appointmentTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "appointmentTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppointmentTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "confirmDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "customerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deedtax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "deedtax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliverer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "deliverer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delivererConfirmDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "delivererConfirmDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "deliveryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryPlan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "deliveryPlan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("electricMeterReadings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "electricMeterReadings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("electricityMeter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "electricityMeter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gasMeter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "gasMeter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gasMeterReading");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "gasMeterReading"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "imageList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ImageModel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ImageModel"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interimconvention");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "interimconvention"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "invoice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSecondary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "isSecondary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("landlordConfirm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "landlordConfirm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margincompensate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "margincompensate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("measureDarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "measureDarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediumWaterMeter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "mediumWaterMeter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediumWaterMeterReadings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "mediumWaterMeterReadings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noticedate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "noticedate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownershipRegistration");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ownershipRegistration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "progress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Progress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertycontract");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "propertycontract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertymanagementFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "propertymanagementFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proxy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "proxy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicrepairfund");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "publicrepairfund"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realMoney");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "realMoney"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiptRecover");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "receiptRecover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationfee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "registrationfee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "salesCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trustor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "trustor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("waterMeteReadings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "waterMeteReadings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("waterMeter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "waterMeter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
