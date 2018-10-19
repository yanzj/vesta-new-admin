/**
 * CustomerComplaints.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

import com.maxrocky.vesta.application.complaint.ComplaintWay;

public class CustomerComplaints implements java.io.Serializable {
    private String accountability;

    private String accountability2;

    private String accountability3;

    private java.util.Calendar alternativeTime;

    private String basicContent;

    private CStatus cStatus;

    private String compairCompany;

    private String complaintClassification;

    private ComplaintWay complaintWay;

    private String complaintsReasonsThree;

    private String complaintsReasonsTwo;

    private java.util.Calendar completionTime;

    private String contactAddress;

    private String createName;

    private String customerName;

    private String customerProperty;

    private String customerVisitOpinion;

    private String dealingPeople;

    private String dealingPeople2;

    private String dealingmobile;

    private String dealingmobile2;

    private String detailProblem;

    private java.util.Calendar esTimatedoTime;

    private java.util.Calendar estimateFinishTime;

    private Boolean ifResponsibility;

    private ImageModel[] imageList;

    private String incomingCall;

    private Boolean issuspende;

    private String location;

    private String maintenanceMode;

    private String maintenanceOwner;

    private String maintenanceTime;

    private String name;

    private String outAgeReason;

    private java.util.Calendar petitionTime;

    private ProblemLevel problemLevel;

    private String processingScheme;

    private ProcessingWay processingWay;

    private java.util.Calendar responsibiliTime;

    private java.util.Calendar responsibilityReturnTime;

    private java.util.Calendar returnTime;

    private String sendName;

    private java.util.Calendar sendTime;

    private ServiceGroup serviceGroup;

    private java.util.Calendar singleTimeWithComplaints;

    private String unithousecode;

    private VisitEvaluation visitEvaluation;

    private VisitType visitType;

    public CustomerComplaints() {
    }

    public CustomerComplaints(
           String accountability,
           String accountability2,
           String accountability3,
           java.util.Calendar alternativeTime,
           String basicContent,
           CStatus cStatus,
           String compairCompany,
           String complaintClassification,
           ComplaintWay complaintWay,
           String complaintsReasonsThree,
           String complaintsReasonsTwo,
           java.util.Calendar completionTime,
           String contactAddress,
           String createName,
           String customerName,
           String customerProperty,
           String customerVisitOpinion,
           String dealingPeople,
           String dealingPeople2,
           String dealingmobile,
           String dealingmobile2,
           String detailProblem,
           java.util.Calendar esTimatedoTime,
           java.util.Calendar estimateFinishTime,
           Boolean ifResponsibility,
           ImageModel[] imageList,
           String incomingCall,
           Boolean issuspende,
           String location,
           String maintenanceMode,
           String maintenanceOwner,
           String maintenanceTime,
           String name,
           String outAgeReason,
           java.util.Calendar petitionTime,
           ProblemLevel problemLevel,
           String processingScheme,
           ProcessingWay processingWay,
           java.util.Calendar responsibiliTime,
           java.util.Calendar responsibilityReturnTime,
           java.util.Calendar returnTime,
           String sendName,
           java.util.Calendar sendTime,
           ServiceGroup serviceGroup,
           java.util.Calendar singleTimeWithComplaints,
           String unithousecode,
           VisitEvaluation visitEvaluation,
           VisitType visitType) {
           this.accountability = accountability;
           this.accountability2 = accountability2;
           this.accountability3 = accountability3;
           this.alternativeTime = alternativeTime;
           this.basicContent = basicContent;
           this.cStatus = cStatus;
           this.compairCompany = compairCompany;
           this.complaintClassification = complaintClassification;
           this.complaintWay = complaintWay;
           this.complaintsReasonsThree = complaintsReasonsThree;
           this.complaintsReasonsTwo = complaintsReasonsTwo;
           this.completionTime = completionTime;
           this.contactAddress = contactAddress;
           this.createName = createName;
           this.customerName = customerName;
           this.customerProperty = customerProperty;
           this.customerVisitOpinion = customerVisitOpinion;
           this.dealingPeople = dealingPeople;
           this.dealingPeople2 = dealingPeople2;
           this.dealingmobile = dealingmobile;
           this.dealingmobile2 = dealingmobile2;
           this.detailProblem = detailProblem;
           this.esTimatedoTime = esTimatedoTime;
           this.estimateFinishTime = estimateFinishTime;
           this.ifResponsibility = ifResponsibility;
           this.imageList = imageList;
           this.incomingCall = incomingCall;
           this.issuspende = issuspende;
           this.location = location;
           this.maintenanceMode = maintenanceMode;
           this.maintenanceOwner = maintenanceOwner;
           this.maintenanceTime = maintenanceTime;
           this.name = name;
           this.outAgeReason = outAgeReason;
           this.petitionTime = petitionTime;
           this.problemLevel = problemLevel;
           this.processingScheme = processingScheme;
           this.processingWay = processingWay;
           this.responsibiliTime = responsibiliTime;
           this.responsibilityReturnTime = responsibilityReturnTime;
           this.returnTime = returnTime;
           this.sendName = sendName;
           this.sendTime = sendTime;
           this.serviceGroup = serviceGroup;
           this.singleTimeWithComplaints = singleTimeWithComplaints;
           this.unithousecode = unithousecode;
           this.visitEvaluation = visitEvaluation;
           this.visitType = visitType;
    }


    /**
     * Gets the accountability value for this CustomerComplaints.
     * 
     * @return accountability
     */
    public String getAccountability() {
        return accountability;
    }


    /**
     * Sets the accountability value for this CustomerComplaints.
     * 
     * @param accountability
     */
    public void setAccountability(String accountability) {
        this.accountability = accountability;
    }


    /**
     * Gets the accountability2 value for this CustomerComplaints.
     * 
     * @return accountability2
     */
    public String getAccountability2() {
        return accountability2;
    }


    /**
     * Sets the accountability2 value for this CustomerComplaints.
     * 
     * @param accountability2
     */
    public void setAccountability2(String accountability2) {
        this.accountability2 = accountability2;
    }


    /**
     * Gets the accountability3 value for this CustomerComplaints.
     * 
     * @return accountability3
     */
    public String getAccountability3() {
        return accountability3;
    }


    /**
     * Sets the accountability3 value for this CustomerComplaints.
     * 
     * @param accountability3
     */
    public void setAccountability3(String accountability3) {
        this.accountability3 = accountability3;
    }


    /**
     * Gets the alternativeTime value for this CustomerComplaints.
     * 
     * @return alternativeTime
     */
    public java.util.Calendar getAlternativeTime() {
        return alternativeTime;
    }


    /**
     * Sets the alternativeTime value for this CustomerComplaints.
     * 
     * @param alternativeTime
     */
    public void setAlternativeTime(java.util.Calendar alternativeTime) {
        this.alternativeTime = alternativeTime;
    }


    /**
     * Gets the basicContent value for this CustomerComplaints.
     * 
     * @return basicContent
     */
    public String getBasicContent() {
        return basicContent;
    }


    /**
     * Sets the basicContent value for this CustomerComplaints.
     * 
     * @param basicContent
     */
    public void setBasicContent(String basicContent) {
        this.basicContent = basicContent;
    }


    /**
     * Gets the cStatus value for this CustomerComplaints.
     * 
     * @return cStatus
     */
    public CStatus getCStatus() {
        return cStatus;
    }


    /**
     * Sets the cStatus value for this CustomerComplaints.
     * 
     * @param cStatus
     */
    public void setCStatus(CStatus cStatus) {
        this.cStatus = cStatus;
    }


    /**
     * Gets the compairCompany value for this CustomerComplaints.
     * 
     * @return compairCompany
     */
    public String getCompairCompany() {
        return compairCompany;
    }


    /**
     * Sets the compairCompany value for this CustomerComplaints.
     * 
     * @param compairCompany
     */
    public void setCompairCompany(String compairCompany) {
        this.compairCompany = compairCompany;
    }


    /**
     * Gets the complaintClassification value for this CustomerComplaints.
     * 
     * @return complaintClassification
     */
    public String getComplaintClassification() {
        return complaintClassification;
    }


    /**
     * Sets the complaintClassification value for this CustomerComplaints.
     * 
     * @param complaintClassification
     */
    public void setComplaintClassification(String complaintClassification) {
        this.complaintClassification = complaintClassification;
    }


    /**
     * Gets the complaintWay value for this CustomerComplaints.
     * 
     * @return complaintWay
     */
    public ComplaintWay getComplaintWay() {
        return complaintWay;
    }


    /**
     * Sets the complaintWay value for this CustomerComplaints.
     * 
     * @param complaintWay
     */
    public void setComplaintWay(ComplaintWay complaintWay) {
        this.complaintWay = complaintWay;
    }


    /**
     * Gets the complaintsReasonsThree value for this CustomerComplaints.
     * 
     * @return complaintsReasonsThree
     */
    public String getComplaintsReasonsThree() {
        return complaintsReasonsThree;
    }


    /**
     * Sets the complaintsReasonsThree value for this CustomerComplaints.
     * 
     * @param complaintsReasonsThree
     */
    public void setComplaintsReasonsThree(String complaintsReasonsThree) {
        this.complaintsReasonsThree = complaintsReasonsThree;
    }


    /**
     * Gets the complaintsReasonsTwo value for this CustomerComplaints.
     * 
     * @return complaintsReasonsTwo
     */
    public String getComplaintsReasonsTwo() {
        return complaintsReasonsTwo;
    }


    /**
     * Sets the complaintsReasonsTwo value for this CustomerComplaints.
     * 
     * @param complaintsReasonsTwo
     */
    public void setComplaintsReasonsTwo(String complaintsReasonsTwo) {
        this.complaintsReasonsTwo = complaintsReasonsTwo;
    }


    /**
     * Gets the completionTime value for this CustomerComplaints.
     * 
     * @return completionTime
     */
    public java.util.Calendar getCompletionTime() {
        return completionTime;
    }


    /**
     * Sets the completionTime value for this CustomerComplaints.
     * 
     * @param completionTime
     */
    public void setCompletionTime(java.util.Calendar completionTime) {
        this.completionTime = completionTime;
    }


    /**
     * Gets the contactAddress value for this CustomerComplaints.
     * 
     * @return contactAddress
     */
    public String getContactAddress() {
        return contactAddress;
    }


    /**
     * Sets the contactAddress value for this CustomerComplaints.
     * 
     * @param contactAddress
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }


    /**
     * Gets the createName value for this CustomerComplaints.
     * 
     * @return createName
     */
    public String getCreateName() {
        return createName;
    }


    /**
     * Sets the createName value for this CustomerComplaints.
     * 
     * @param createName
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }


    /**
     * Gets the customerName value for this CustomerComplaints.
     * 
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this CustomerComplaints.
     * 
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the customerProperty value for this CustomerComplaints.
     * 
     * @return customerProperty
     */
    public String getCustomerProperty() {
        return customerProperty;
    }


    /**
     * Sets the customerProperty value for this CustomerComplaints.
     * 
     * @param customerProperty
     */
    public void setCustomerProperty(String customerProperty) {
        this.customerProperty = customerProperty;
    }


    /**
     * Gets the customerVisitOpinion value for this CustomerComplaints.
     * 
     * @return customerVisitOpinion
     */
    public String getCustomerVisitOpinion() {
        return customerVisitOpinion;
    }


    /**
     * Sets the customerVisitOpinion value for this CustomerComplaints.
     * 
     * @param customerVisitOpinion
     */
    public void setCustomerVisitOpinion(String customerVisitOpinion) {
        this.customerVisitOpinion = customerVisitOpinion;
    }


    /**
     * Gets the dealingPeople value for this CustomerComplaints.
     * 
     * @return dealingPeople
     */
    public String getDealingPeople() {
        return dealingPeople;
    }


    /**
     * Sets the dealingPeople value for this CustomerComplaints.
     * 
     * @param dealingPeople
     */
    public void setDealingPeople(String dealingPeople) {
        this.dealingPeople = dealingPeople;
    }


    /**
     * Gets the dealingPeople2 value for this CustomerComplaints.
     * 
     * @return dealingPeople2
     */
    public String getDealingPeople2() {
        return dealingPeople2;
    }


    /**
     * Sets the dealingPeople2 value for this CustomerComplaints.
     * 
     * @param dealingPeople2
     */
    public void setDealingPeople2(String dealingPeople2) {
        this.dealingPeople2 = dealingPeople2;
    }


    /**
     * Gets the dealingmobile value for this CustomerComplaints.
     * 
     * @return dealingmobile
     */
    public String getDealingmobile() {
        return dealingmobile;
    }


    /**
     * Sets the dealingmobile value for this CustomerComplaints.
     * 
     * @param dealingmobile
     */
    public void setDealingmobile(String dealingmobile) {
        this.dealingmobile = dealingmobile;
    }


    /**
     * Gets the dealingmobile2 value for this CustomerComplaints.
     * 
     * @return dealingmobile2
     */
    public String getDealingmobile2() {
        return dealingmobile2;
    }


    /**
     * Sets the dealingmobile2 value for this CustomerComplaints.
     * 
     * @param dealingmobile2
     */
    public void setDealingmobile2(String dealingmobile2) {
        this.dealingmobile2 = dealingmobile2;
    }


    /**
     * Gets the detailProblem value for this CustomerComplaints.
     * 
     * @return detailProblem
     */
    public String getDetailProblem() {
        return detailProblem;
    }


    /**
     * Sets the detailProblem value for this CustomerComplaints.
     * 
     * @param detailProblem
     */
    public void setDetailProblem(String detailProblem) {
        this.detailProblem = detailProblem;
    }


    /**
     * Gets the esTimatedoTime value for this CustomerComplaints.
     * 
     * @return esTimatedoTime
     */
    public java.util.Calendar getEsTimatedoTime() {
        return esTimatedoTime;
    }


    /**
     * Sets the esTimatedoTime value for this CustomerComplaints.
     * 
     * @param esTimatedoTime
     */
    public void setEsTimatedoTime(java.util.Calendar esTimatedoTime) {
        this.esTimatedoTime = esTimatedoTime;
    }


    /**
     * Gets the estimateFinishTime value for this CustomerComplaints.
     * 
     * @return estimateFinishTime
     */
    public java.util.Calendar getEstimateFinishTime() {
        return estimateFinishTime;
    }


    /**
     * Sets the estimateFinishTime value for this CustomerComplaints.
     * 
     * @param estimateFinishTime
     */
    public void setEstimateFinishTime(java.util.Calendar estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
    }


    /**
     * Gets the ifResponsibility value for this CustomerComplaints.
     * 
     * @return ifResponsibility
     */
    public Boolean getIfResponsibility() {
        return ifResponsibility;
    }


    /**
     * Sets the ifResponsibility value for this CustomerComplaints.
     * 
     * @param ifResponsibility
     */
    public void setIfResponsibility(Boolean ifResponsibility) {
        this.ifResponsibility = ifResponsibility;
    }


    /**
     * Gets the imageList value for this CustomerComplaints.
     * 
     * @return imageList
     */
    public ImageModel[] getImageList() {
        return imageList;
    }


    /**
     * Sets the imageList value for this CustomerComplaints.
     * 
     * @param imageList
     */
    public void setImageList(ImageModel[] imageList) {
        this.imageList = imageList;
    }


    /**
     * Gets the incomingCall value for this CustomerComplaints.
     * 
     * @return incomingCall
     */
    public String getIncomingCall() {
        return incomingCall;
    }


    /**
     * Sets the incomingCall value for this CustomerComplaints.
     * 
     * @param incomingCall
     */
    public void setIncomingCall(String incomingCall) {
        this.incomingCall = incomingCall;
    }


    /**
     * Gets the issuspende value for this CustomerComplaints.
     * 
     * @return issuspende
     */
    public Boolean getIssuspende() {
        return issuspende;
    }


    /**
     * Sets the issuspende value for this CustomerComplaints.
     * 
     * @param issuspende
     */
    public void setIssuspende(Boolean issuspende) {
        this.issuspende = issuspende;
    }


    /**
     * Gets the location value for this CustomerComplaints.
     * 
     * @return location
     */
    public String getLocation() {
        return location;
    }


    /**
     * Sets the location value for this CustomerComplaints.
     * 
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Gets the maintenanceMode value for this CustomerComplaints.
     * 
     * @return maintenanceMode
     */
    public String getMaintenanceMode() {
        return maintenanceMode;
    }


    /**
     * Sets the maintenanceMode value for this CustomerComplaints.
     * 
     * @param maintenanceMode
     */
    public void setMaintenanceMode(String maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }


    /**
     * Gets the maintenanceOwner value for this CustomerComplaints.
     * 
     * @return maintenanceOwner
     */
    public String getMaintenanceOwner() {
        return maintenanceOwner;
    }


    /**
     * Sets the maintenanceOwner value for this CustomerComplaints.
     * 
     * @param maintenanceOwner
     */
    public void setMaintenanceOwner(String maintenanceOwner) {
        this.maintenanceOwner = maintenanceOwner;
    }


    /**
     * Gets the maintenanceTime value for this CustomerComplaints.
     * 
     * @return maintenanceTime
     */
    public String getMaintenanceTime() {
        return maintenanceTime;
    }


    /**
     * Sets the maintenanceTime value for this CustomerComplaints.
     * 
     * @param maintenanceTime
     */
    public void setMaintenanceTime(String maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }


    /**
     * Gets the name value for this CustomerComplaints.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this CustomerComplaints.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the outAgeReason value for this CustomerComplaints.
     * 
     * @return outAgeReason
     */
    public String getOutAgeReason() {
        return outAgeReason;
    }


    /**
     * Sets the outAgeReason value for this CustomerComplaints.
     * 
     * @param outAgeReason
     */
    public void setOutAgeReason(String outAgeReason) {
        this.outAgeReason = outAgeReason;
    }


    /**
     * Gets the petitionTime value for this CustomerComplaints.
     * 
     * @return petitionTime
     */
    public java.util.Calendar getPetitionTime() {
        return petitionTime;
    }


    /**
     * Sets the petitionTime value for this CustomerComplaints.
     * 
     * @param petitionTime
     */
    public void setPetitionTime(java.util.Calendar petitionTime) {
        this.petitionTime = petitionTime;
    }


    /**
     * Gets the problemLevel value for this CustomerComplaints.
     * 
     * @return problemLevel
     */
    public ProblemLevel getProblemLevel() {
        return problemLevel;
    }


    /**
     * Sets the problemLevel value for this CustomerComplaints.
     * 
     * @param problemLevel
     */
    public void setProblemLevel(ProblemLevel problemLevel) {
        this.problemLevel = problemLevel;
    }


    /**
     * Gets the processingScheme value for this CustomerComplaints.
     * 
     * @return processingScheme
     */
    public String getProcessingScheme() {
        return processingScheme;
    }


    /**
     * Sets the processingScheme value for this CustomerComplaints.
     * 
     * @param processingScheme
     */
    public void setProcessingScheme(String processingScheme) {
        this.processingScheme = processingScheme;
    }


    /**
     * Gets the processingWay value for this CustomerComplaints.
     * 
     * @return processingWay
     */
    public ProcessingWay getProcessingWay() {
        return processingWay;
    }


    /**
     * Sets the processingWay value for this CustomerComplaints.
     * 
     * @param processingWay
     */
    public void setProcessingWay(ProcessingWay processingWay) {
        this.processingWay = processingWay;
    }


    /**
     * Gets the responsibiliTime value for this CustomerComplaints.
     * 
     * @return responsibiliTime
     */
    public java.util.Calendar getResponsibiliTime() {
        return responsibiliTime;
    }


    /**
     * Sets the responsibiliTime value for this CustomerComplaints.
     * 
     * @param responsibiliTime
     */
    public void setResponsibiliTime(java.util.Calendar responsibiliTime) {
        this.responsibiliTime = responsibiliTime;
    }


    /**
     * Gets the responsibilityReturnTime value for this CustomerComplaints.
     * 
     * @return responsibilityReturnTime
     */
    public java.util.Calendar getResponsibilityReturnTime() {
        return responsibilityReturnTime;
    }


    /**
     * Sets the responsibilityReturnTime value for this CustomerComplaints.
     * 
     * @param responsibilityReturnTime
     */
    public void setResponsibilityReturnTime(java.util.Calendar responsibilityReturnTime) {
        this.responsibilityReturnTime = responsibilityReturnTime;
    }


    /**
     * Gets the returnTime value for this CustomerComplaints.
     * 
     * @return returnTime
     */
    public java.util.Calendar getReturnTime() {
        return returnTime;
    }


    /**
     * Sets the returnTime value for this CustomerComplaints.
     * 
     * @param returnTime
     */
    public void setReturnTime(java.util.Calendar returnTime) {
        this.returnTime = returnTime;
    }


    /**
     * Gets the sendName value for this CustomerComplaints.
     * 
     * @return sendName
     */
    public String getSendName() {
        return sendName;
    }


    /**
     * Sets the sendName value for this CustomerComplaints.
     * 
     * @param sendName
     */
    public void setSendName(String sendName) {
        this.sendName = sendName;
    }


    /**
     * Gets the sendTime value for this CustomerComplaints.
     * 
     * @return sendTime
     */
    public java.util.Calendar getSendTime() {
        return sendTime;
    }


    /**
     * Sets the sendTime value for this CustomerComplaints.
     * 
     * @param sendTime
     */
    public void setSendTime(java.util.Calendar sendTime) {
        this.sendTime = sendTime;
    }


    /**
     * Gets the serviceGroup value for this CustomerComplaints.
     * 
     * @return serviceGroup
     */
    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }


    /**
     * Sets the serviceGroup value for this CustomerComplaints.
     * 
     * @param serviceGroup
     */
    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }


    /**
     * Gets the singleTimeWithComplaints value for this CustomerComplaints.
     * 
     * @return singleTimeWithComplaints
     */
    public java.util.Calendar getSingleTimeWithComplaints() {
        return singleTimeWithComplaints;
    }


    /**
     * Sets the singleTimeWithComplaints value for this CustomerComplaints.
     * 
     * @param singleTimeWithComplaints
     */
    public void setSingleTimeWithComplaints(java.util.Calendar singleTimeWithComplaints) {
        this.singleTimeWithComplaints = singleTimeWithComplaints;
    }


    /**
     * Gets the unithousecode value for this CustomerComplaints.
     * 
     * @return unithousecode
     */
    public String getUnithousecode() {
        return unithousecode;
    }


    /**
     * Sets the unithousecode value for this CustomerComplaints.
     * 
     * @param unithousecode
     */
    public void setUnithousecode(String unithousecode) {
        this.unithousecode = unithousecode;
    }


    /**
     * Gets the visitEvaluation value for this CustomerComplaints.
     * 
     * @return visitEvaluation
     */
    public VisitEvaluation getVisitEvaluation() {
        return visitEvaluation;
    }


    /**
     * Sets the visitEvaluation value for this CustomerComplaints.
     * 
     * @param visitEvaluation
     */
    public void setVisitEvaluation(VisitEvaluation visitEvaluation) {
        this.visitEvaluation = visitEvaluation;
    }


    /**
     * Gets the visitType value for this CustomerComplaints.
     * 
     * @return visitType
     */
    public VisitType getVisitType() {
        return visitType;
    }


    /**
     * Sets the visitType value for this CustomerComplaints.
     * 
     * @param visitType
     */
    public void setVisitType(VisitType visitType) {
        this.visitType = visitType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CustomerComplaints)) return false;
        CustomerComplaints other = (CustomerComplaints) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountability==null && other.getAccountability()==null) || 
             (this.accountability!=null &&
              this.accountability.equals(other.getAccountability()))) &&
            ((this.accountability2==null && other.getAccountability2()==null) || 
             (this.accountability2!=null &&
              this.accountability2.equals(other.getAccountability2()))) &&
            ((this.accountability3==null && other.getAccountability3()==null) || 
             (this.accountability3!=null &&
              this.accountability3.equals(other.getAccountability3()))) &&
            ((this.alternativeTime==null && other.getAlternativeTime()==null) || 
             (this.alternativeTime!=null &&
              this.alternativeTime.equals(other.getAlternativeTime()))) &&
            ((this.basicContent==null && other.getBasicContent()==null) || 
             (this.basicContent!=null &&
              this.basicContent.equals(other.getBasicContent()))) &&
            ((this.cStatus==null && other.getCStatus()==null) || 
             (this.cStatus!=null &&
              this.cStatus.equals(other.getCStatus()))) &&
            ((this.compairCompany==null && other.getCompairCompany()==null) || 
             (this.compairCompany!=null &&
              this.compairCompany.equals(other.getCompairCompany()))) &&
            ((this.complaintClassification==null && other.getComplaintClassification()==null) || 
             (this.complaintClassification!=null &&
              this.complaintClassification.equals(other.getComplaintClassification()))) &&
            ((this.complaintWay==null && other.getComplaintWay()==null) || 
             (this.complaintWay!=null &&
              this.complaintWay.equals(other.getComplaintWay()))) &&
            ((this.complaintsReasonsThree==null && other.getComplaintsReasonsThree()==null) || 
             (this.complaintsReasonsThree!=null &&
              this.complaintsReasonsThree.equals(other.getComplaintsReasonsThree()))) &&
            ((this.complaintsReasonsTwo==null && other.getComplaintsReasonsTwo()==null) || 
             (this.complaintsReasonsTwo!=null &&
              this.complaintsReasonsTwo.equals(other.getComplaintsReasonsTwo()))) &&
            ((this.completionTime==null && other.getCompletionTime()==null) || 
             (this.completionTime!=null &&
              this.completionTime.equals(other.getCompletionTime()))) &&
            ((this.contactAddress==null && other.getContactAddress()==null) || 
             (this.contactAddress!=null &&
              this.contactAddress.equals(other.getContactAddress()))) &&
            ((this.createName==null && other.getCreateName()==null) || 
             (this.createName!=null &&
              this.createName.equals(other.getCreateName()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.customerProperty==null && other.getCustomerProperty()==null) || 
             (this.customerProperty!=null &&
              this.customerProperty.equals(other.getCustomerProperty()))) &&
            ((this.customerVisitOpinion==null && other.getCustomerVisitOpinion()==null) || 
             (this.customerVisitOpinion!=null &&
              this.customerVisitOpinion.equals(other.getCustomerVisitOpinion()))) &&
            ((this.dealingPeople==null && other.getDealingPeople()==null) || 
             (this.dealingPeople!=null &&
              this.dealingPeople.equals(other.getDealingPeople()))) &&
            ((this.dealingPeople2==null && other.getDealingPeople2()==null) || 
             (this.dealingPeople2!=null &&
              this.dealingPeople2.equals(other.getDealingPeople2()))) &&
            ((this.dealingmobile==null && other.getDealingmobile()==null) || 
             (this.dealingmobile!=null &&
              this.dealingmobile.equals(other.getDealingmobile()))) &&
            ((this.dealingmobile2==null && other.getDealingmobile2()==null) || 
             (this.dealingmobile2!=null &&
              this.dealingmobile2.equals(other.getDealingmobile2()))) &&
            ((this.detailProblem==null && other.getDetailProblem()==null) || 
             (this.detailProblem!=null &&
              this.detailProblem.equals(other.getDetailProblem()))) &&
            ((this.esTimatedoTime==null && other.getEsTimatedoTime()==null) || 
             (this.esTimatedoTime!=null &&
              this.esTimatedoTime.equals(other.getEsTimatedoTime()))) &&
            ((this.estimateFinishTime==null && other.getEstimateFinishTime()==null) || 
             (this.estimateFinishTime!=null &&
              this.estimateFinishTime.equals(other.getEstimateFinishTime()))) &&
            ((this.ifResponsibility==null && other.getIfResponsibility()==null) || 
             (this.ifResponsibility!=null &&
              this.ifResponsibility.equals(other.getIfResponsibility()))) &&
            ((this.imageList==null && other.getImageList()==null) || 
             (this.imageList!=null &&
              java.util.Arrays.equals(this.imageList, other.getImageList()))) &&
            ((this.incomingCall==null && other.getIncomingCall()==null) || 
             (this.incomingCall!=null &&
              this.incomingCall.equals(other.getIncomingCall()))) &&
            ((this.issuspende==null && other.getIssuspende()==null) || 
             (this.issuspende!=null &&
              this.issuspende.equals(other.getIssuspende()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.maintenanceMode==null && other.getMaintenanceMode()==null) || 
             (this.maintenanceMode!=null &&
              this.maintenanceMode.equals(other.getMaintenanceMode()))) &&
            ((this.maintenanceOwner==null && other.getMaintenanceOwner()==null) || 
             (this.maintenanceOwner!=null &&
              this.maintenanceOwner.equals(other.getMaintenanceOwner()))) &&
            ((this.maintenanceTime==null && other.getMaintenanceTime()==null) || 
             (this.maintenanceTime!=null &&
              this.maintenanceTime.equals(other.getMaintenanceTime()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.outAgeReason==null && other.getOutAgeReason()==null) || 
             (this.outAgeReason!=null &&
              this.outAgeReason.equals(other.getOutAgeReason()))) &&
            ((this.petitionTime==null && other.getPetitionTime()==null) || 
             (this.petitionTime!=null &&
              this.petitionTime.equals(other.getPetitionTime()))) &&
            ((this.problemLevel==null && other.getProblemLevel()==null) || 
             (this.problemLevel!=null &&
              this.problemLevel.equals(other.getProblemLevel()))) &&
            ((this.processingScheme==null && other.getProcessingScheme()==null) || 
             (this.processingScheme!=null &&
              this.processingScheme.equals(other.getProcessingScheme()))) &&
            ((this.processingWay==null && other.getProcessingWay()==null) || 
             (this.processingWay!=null &&
              this.processingWay.equals(other.getProcessingWay()))) &&
            ((this.responsibiliTime==null && other.getResponsibiliTime()==null) || 
             (this.responsibiliTime!=null &&
              this.responsibiliTime.equals(other.getResponsibiliTime()))) &&
            ((this.responsibilityReturnTime==null && other.getResponsibilityReturnTime()==null) || 
             (this.responsibilityReturnTime!=null &&
              this.responsibilityReturnTime.equals(other.getResponsibilityReturnTime()))) &&
            ((this.returnTime==null && other.getReturnTime()==null) || 
             (this.returnTime!=null &&
              this.returnTime.equals(other.getReturnTime()))) &&
            ((this.sendName==null && other.getSendName()==null) || 
             (this.sendName!=null &&
              this.sendName.equals(other.getSendName()))) &&
            ((this.sendTime==null && other.getSendTime()==null) || 
             (this.sendTime!=null &&
              this.sendTime.equals(other.getSendTime()))) &&
            ((this.serviceGroup==null && other.getServiceGroup()==null) || 
             (this.serviceGroup!=null &&
              this.serviceGroup.equals(other.getServiceGroup()))) &&
            ((this.singleTimeWithComplaints==null && other.getSingleTimeWithComplaints()==null) || 
             (this.singleTimeWithComplaints!=null &&
              this.singleTimeWithComplaints.equals(other.getSingleTimeWithComplaints()))) &&
            ((this.unithousecode==null && other.getUnithousecode()==null) || 
             (this.unithousecode!=null &&
              this.unithousecode.equals(other.getUnithousecode()))) &&
            ((this.visitEvaluation==null && other.getVisitEvaluation()==null) || 
             (this.visitEvaluation!=null &&
              this.visitEvaluation.equals(other.getVisitEvaluation()))) &&
            ((this.visitType==null && other.getVisitType()==null) || 
             (this.visitType!=null &&
              this.visitType.equals(other.getVisitType())));
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
        if (getAccountability() != null) {
            _hashCode += getAccountability().hashCode();
        }
        if (getAccountability2() != null) {
            _hashCode += getAccountability2().hashCode();
        }
        if (getAccountability3() != null) {
            _hashCode += getAccountability3().hashCode();
        }
        if (getAlternativeTime() != null) {
            _hashCode += getAlternativeTime().hashCode();
        }
        if (getBasicContent() != null) {
            _hashCode += getBasicContent().hashCode();
        }
        if (getCStatus() != null) {
            _hashCode += getCStatus().hashCode();
        }
        if (getCompairCompany() != null) {
            _hashCode += getCompairCompany().hashCode();
        }
        if (getComplaintClassification() != null) {
            _hashCode += getComplaintClassification().hashCode();
        }
        if (getComplaintWay() != null) {
            _hashCode += getComplaintWay().hashCode();
        }
        if (getComplaintsReasonsThree() != null) {
            _hashCode += getComplaintsReasonsThree().hashCode();
        }
        if (getComplaintsReasonsTwo() != null) {
            _hashCode += getComplaintsReasonsTwo().hashCode();
        }
        if (getCompletionTime() != null) {
            _hashCode += getCompletionTime().hashCode();
        }
        if (getContactAddress() != null) {
            _hashCode += getContactAddress().hashCode();
        }
        if (getCreateName() != null) {
            _hashCode += getCreateName().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getCustomerProperty() != null) {
            _hashCode += getCustomerProperty().hashCode();
        }
        if (getCustomerVisitOpinion() != null) {
            _hashCode += getCustomerVisitOpinion().hashCode();
        }
        if (getDealingPeople() != null) {
            _hashCode += getDealingPeople().hashCode();
        }
        if (getDealingPeople2() != null) {
            _hashCode += getDealingPeople2().hashCode();
        }
        if (getDealingmobile() != null) {
            _hashCode += getDealingmobile().hashCode();
        }
        if (getDealingmobile2() != null) {
            _hashCode += getDealingmobile2().hashCode();
        }
        if (getDetailProblem() != null) {
            _hashCode += getDetailProblem().hashCode();
        }
        if (getEsTimatedoTime() != null) {
            _hashCode += getEsTimatedoTime().hashCode();
        }
        if (getEstimateFinishTime() != null) {
            _hashCode += getEstimateFinishTime().hashCode();
        }
        if (getIfResponsibility() != null) {
            _hashCode += getIfResponsibility().hashCode();
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
        if (getIncomingCall() != null) {
            _hashCode += getIncomingCall().hashCode();
        }
        if (getIssuspende() != null) {
            _hashCode += getIssuspende().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getMaintenanceMode() != null) {
            _hashCode += getMaintenanceMode().hashCode();
        }
        if (getMaintenanceOwner() != null) {
            _hashCode += getMaintenanceOwner().hashCode();
        }
        if (getMaintenanceTime() != null) {
            _hashCode += getMaintenanceTime().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getOutAgeReason() != null) {
            _hashCode += getOutAgeReason().hashCode();
        }
        if (getPetitionTime() != null) {
            _hashCode += getPetitionTime().hashCode();
        }
        if (getProblemLevel() != null) {
            _hashCode += getProblemLevel().hashCode();
        }
        if (getProcessingScheme() != null) {
            _hashCode += getProcessingScheme().hashCode();
        }
        if (getProcessingWay() != null) {
            _hashCode += getProcessingWay().hashCode();
        }
        if (getResponsibiliTime() != null) {
            _hashCode += getResponsibiliTime().hashCode();
        }
        if (getResponsibilityReturnTime() != null) {
            _hashCode += getResponsibilityReturnTime().hashCode();
        }
        if (getReturnTime() != null) {
            _hashCode += getReturnTime().hashCode();
        }
        if (getSendName() != null) {
            _hashCode += getSendName().hashCode();
        }
        if (getSendTime() != null) {
            _hashCode += getSendTime().hashCode();
        }
        if (getServiceGroup() != null) {
            _hashCode += getServiceGroup().hashCode();
        }
        if (getSingleTimeWithComplaints() != null) {
            _hashCode += getSingleTimeWithComplaints().hashCode();
        }
        if (getUnithousecode() != null) {
            _hashCode += getUnithousecode().hashCode();
        }
        if (getVisitEvaluation() != null) {
            _hashCode += getVisitEvaluation().hashCode();
        }
        if (getVisitType() != null) {
            _hashCode += getVisitType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerComplaints.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CustomerComplaints"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountability");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "accountability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountability2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "accountability2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountability3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "accountability3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alternativeTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "alternativeTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("basicContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "basicContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "cStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compairCompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "compairCompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintWay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintWay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintWay"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintsReasonsThree");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintsReasonsThree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintsReasonsTwo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintsReasonsTwo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "completionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "contactAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "createName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("customerProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "customerProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerVisitOpinion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "customerVisitOpinion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealingPeople");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "dealingPeople"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealingPeople2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "dealingPeople2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealingmobile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "dealingmobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealingmobile2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "dealingmobile2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detailProblem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "detailProblem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esTimatedoTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "esTimatedoTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estimateFinishTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "estimateFinishTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ifResponsibility");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ifResponsibility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("incomingCall");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "incomingCall"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issuspende");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "issuspende"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maintenanceMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "maintenanceMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maintenanceOwner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "maintenanceOwner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maintenanceTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "maintenanceTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outAgeReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "outAgeReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("petitionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "petitionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "problemLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ProblemLevel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processingScheme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "processingScheme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processingWay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "processingWay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ProcessingWay"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsibiliTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "responsibiliTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsibilityReturnTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "responsibilityReturnTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "returnTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "sendName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "sendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "serviceGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ServiceGroup"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("singleTimeWithComplaints");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "singleTimeWithComplaints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unithousecode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unithousecode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitEvaluation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitEvaluation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "VisitEvaluation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "VisitType"));
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
