/**
 * Complaint.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

import com.maxrocky.vesta.application.publicModel.ImageModel;

public class Complaint implements java.io.Serializable {
    private String city;

    private String classificationcomplaints;

    private String complaintchannel;

    private String complaintlevel;

    private String complaintphone;

    private String complaintsdescribes;

    private String complaintsource;

    private String complainttype;

    private java.util.Calendar completetime;

    private String createby;

    private String createname;

    private java.util.Calendar createon;

    private java.util.Calendar dispatchtime;

    private String disposal;

    private String documentsstate;

    private String emotion;

    private String housecode;

    private String housedes;

    private ImageModel[] imageList;

    private String importantcomplaintreason;

    private java.util.Calendar lastreturntime;

    private java.util.Calendar limitedreplytime;

    private String majorcomplaintreason;

    private String memberid;

    private String name;

    private String ownerquote;

    private String portalcomplaintperson;

    private String processingresults;

    private String project;

    private String relatednumber;

    private java.util.Calendar replytime;

    private Integer returntime;

    private String revisit;

    private java.util.Calendar submittime;

    private String timeout;

    private String treatmentplan;

    private String upgrade;

    private java.util.Calendar visitdate;

    private String visitopinion;

    private String visitsatisfaction;

    private String whetherswarmsues;

    public Complaint() {
    }

    public Complaint(
           String city,
           String classificationcomplaints,
           String complaintchannel,
           String complaintlevel,
           String complaintphone,
           String complaintsdescribes,
           String complaintsource,
           String complainttype,
           java.util.Calendar completetime,
           String createby,
           String createname,
           java.util.Calendar createon,
           java.util.Calendar dispatchtime,
           String disposal,
           String documentsstate,
           String emotion,
           String housecode,
           String housedes,
           ImageModel[] imageList,
           String importantcomplaintreason,
           java.util.Calendar lastreturntime,
           java.util.Calendar limitedreplytime,
           String majorcomplaintreason,
           String memberid,
           String name,
           String ownerquote,
           String portalcomplaintperson,
           String processingresults,
           String project,
           String relatednumber,
           java.util.Calendar replytime,
           Integer returntime,
           String revisit,
           java.util.Calendar submittime,
           String timeout,
           String treatmentplan,
           String upgrade,
           java.util.Calendar visitdate,
           String visitopinion,
           String visitsatisfaction,
           String whetherswarmsues) {
           this.city = city;
           this.classificationcomplaints = classificationcomplaints;
           this.complaintchannel = complaintchannel;
           this.complaintlevel = complaintlevel;
           this.complaintphone = complaintphone;
           this.complaintsdescribes = complaintsdescribes;
           this.complaintsource = complaintsource;
           this.complainttype = complainttype;
           this.completetime = completetime;
           this.createby = createby;
           this.createname = createname;
           this.createon = createon;
           this.dispatchtime = dispatchtime;
           this.disposal = disposal;
           this.documentsstate = documentsstate;
           this.emotion = emotion;
           this.housecode = housecode;
           this.housedes = housedes;
           this.imageList = imageList;
           this.importantcomplaintreason = importantcomplaintreason;
           this.lastreturntime = lastreturntime;
           this.limitedreplytime = limitedreplytime;
           this.majorcomplaintreason = majorcomplaintreason;
           this.memberid = memberid;
           this.name = name;
           this.ownerquote = ownerquote;
           this.portalcomplaintperson = portalcomplaintperson;
           this.processingresults = processingresults;
           this.project = project;
           this.relatednumber = relatednumber;
           this.replytime = replytime;
           this.returntime = returntime;
           this.revisit = revisit;
           this.submittime = submittime;
           this.timeout = timeout;
           this.treatmentplan = treatmentplan;
           this.upgrade = upgrade;
           this.visitdate = visitdate;
           this.visitopinion = visitopinion;
           this.visitsatisfaction = visitsatisfaction;
           this.whetherswarmsues = whetherswarmsues;
    }


    /**
     * Gets the city value for this Complaint.
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }


    /**
     * Sets the city value for this Complaint.
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Gets the classificationcomplaints value for this Complaint.
     * 
     * @return classificationcomplaints
     */
    public String getClassificationcomplaints() {
        return classificationcomplaints;
    }


    /**
     * Sets the classificationcomplaints value for this Complaint.
     * 
     * @param classificationcomplaints
     */
    public void setClassificationcomplaints(String classificationcomplaints) {
        this.classificationcomplaints = classificationcomplaints;
    }


    /**
     * Gets the complaintchannel value for this Complaint.
     * 
     * @return complaintchannel
     */
    public String getComplaintchannel() {
        return complaintchannel;
    }


    /**
     * Sets the complaintchannel value for this Complaint.
     * 
     * @param complaintchannel
     */
    public void setComplaintchannel(String complaintchannel) {
        this.complaintchannel = complaintchannel;
    }


    /**
     * Gets the complaintlevel value for this Complaint.
     * 
     * @return complaintlevel
     */
    public String getComplaintlevel() {
        return complaintlevel;
    }


    /**
     * Sets the complaintlevel value for this Complaint.
     * 
     * @param complaintlevel
     */
    public void setComplaintlevel(String complaintlevel) {
        this.complaintlevel = complaintlevel;
    }


    /**
     * Gets the complaintphone value for this Complaint.
     * 
     * @return complaintphone
     */
    public String getComplaintphone() {
        return complaintphone;
    }


    /**
     * Sets the complaintphone value for this Complaint.
     * 
     * @param complaintphone
     */
    public void setComplaintphone(String complaintphone) {
        this.complaintphone = complaintphone;
    }


    /**
     * Gets the complaintsdescribes value for this Complaint.
     * 
     * @return complaintsdescribes
     */
    public String getComplaintsdescribes() {
        return complaintsdescribes;
    }


    /**
     * Sets the complaintsdescribes value for this Complaint.
     * 
     * @param complaintsdescribes
     */
    public void setComplaintsdescribes(String complaintsdescribes) {
        this.complaintsdescribes = complaintsdescribes;
    }


    /**
     * Gets the complaintsource value for this Complaint.
     * 
     * @return complaintsource
     */
    public String getComplaintsource() {
        return complaintsource;
    }


    /**
     * Sets the complaintsource value for this Complaint.
     * 
     * @param complaintsource
     */
    public void setComplaintsource(String complaintsource) {
        this.complaintsource = complaintsource;
    }


    /**
     * Gets the complainttype value for this Complaint.
     * 
     * @return complainttype
     */
    public String getComplainttype() {
        return complainttype;
    }


    /**
     * Sets the complainttype value for this Complaint.
     * 
     * @param complainttype
     */
    public void setComplainttype(String complainttype) {
        this.complainttype = complainttype;
    }


    /**
     * Gets the completetime value for this Complaint.
     * 
     * @return completetime
     */
    public java.util.Calendar getCompletetime() {
        return completetime;
    }


    /**
     * Sets the completetime value for this Complaint.
     * 
     * @param completetime
     */
    public void setCompletetime(java.util.Calendar completetime) {
        this.completetime = completetime;
    }


    /**
     * Gets the createby value for this Complaint.
     * 
     * @return createby
     */
    public String getCreateby() {
        return createby;
    }


    /**
     * Sets the createby value for this Complaint.
     * 
     * @param createby
     */
    public void setCreateby(String createby) {
        this.createby = createby;
    }


    /**
     * Gets the createname value for this Complaint.
     * 
     * @return createname
     */
    public String getCreatename() {
        return createname;
    }


    /**
     * Sets the createname value for this Complaint.
     * 
     * @param createname
     */
    public void setCreatename(String createname) {
        this.createname = createname;
    }


    /**
     * Gets the createon value for this Complaint.
     * 
     * @return createon
     */
    public java.util.Calendar getCreateon() {
        return createon;
    }


    /**
     * Sets the createon value for this Complaint.
     * 
     * @param createon
     */
    public void setCreateon(java.util.Calendar createon) {
        this.createon = createon;
    }


    /**
     * Gets the dispatchtime value for this Complaint.
     * 
     * @return dispatchtime
     */
    public java.util.Calendar getDispatchtime() {
        return dispatchtime;
    }


    /**
     * Sets the dispatchtime value for this Complaint.
     * 
     * @param dispatchtime
     */
    public void setDispatchtime(java.util.Calendar dispatchtime) {
        this.dispatchtime = dispatchtime;
    }


    /**
     * Gets the disposal value for this Complaint.
     * 
     * @return disposal
     */
    public String getDisposal() {
        return disposal;
    }


    /**
     * Sets the disposal value for this Complaint.
     * 
     * @param disposal
     */
    public void setDisposal(String disposal) {
        this.disposal = disposal;
    }


    /**
     * Gets the documentsstate value for this Complaint.
     * 
     * @return documentsstate
     */
    public String getDocumentsstate() {
        return documentsstate;
    }


    /**
     * Sets the documentsstate value for this Complaint.
     * 
     * @param documentsstate
     */
    public void setDocumentsstate(String documentsstate) {
        this.documentsstate = documentsstate;
    }


    /**
     * Gets the emotion value for this Complaint.
     * 
     * @return emotion
     */
    public String getEmotion() {
        return emotion;
    }


    /**
     * Sets the emotion value for this Complaint.
     * 
     * @param emotion
     */
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }


    /**
     * Gets the housecode value for this Complaint.
     * 
     * @return housecode
     */
    public String getHousecode() {
        return housecode;
    }


    /**
     * Sets the housecode value for this Complaint.
     * 
     * @param housecode
     */
    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }


    /**
     * Gets the housedes value for this Complaint.
     * 
     * @return housedes
     */
    public String getHousedes() {
        return housedes;
    }


    /**
     * Sets the housedes value for this Complaint.
     * 
     * @param housedes
     */
    public void setHousedes(String housedes) {
        this.housedes = housedes;
    }


    /**
     * Gets the imageList value for this Complaint.
     * 
     * @return imageList
     */
    public ImageModel[] getImageList() {
        return imageList;
    }


    /**
     * Sets the imageList value for this Complaint.
     * 
     * @param imageList
     */
    public void setImageList(ImageModel[] imageList) {
        this.imageList = imageList;
    }


    /**
     * Gets the importantcomplaintreason value for this Complaint.
     * 
     * @return importantcomplaintreason
     */
    public String getImportantcomplaintreason() {
        return importantcomplaintreason;
    }


    /**
     * Sets the importantcomplaintreason value for this Complaint.
     * 
     * @param importantcomplaintreason
     */
    public void setImportantcomplaintreason(String importantcomplaintreason) {
        this.importantcomplaintreason = importantcomplaintreason;
    }


    /**
     * Gets the lastreturntime value for this Complaint.
     * 
     * @return lastreturntime
     */
    public java.util.Calendar getLastreturntime() {
        return lastreturntime;
    }


    /**
     * Sets the lastreturntime value for this Complaint.
     * 
     * @param lastreturntime
     */
    public void setLastreturntime(java.util.Calendar lastreturntime) {
        this.lastreturntime = lastreturntime;
    }


    /**
     * Gets the limitedreplytime value for this Complaint.
     * 
     * @return limitedreplytime
     */
    public java.util.Calendar getLimitedreplytime() {
        return limitedreplytime;
    }


    /**
     * Sets the limitedreplytime value for this Complaint.
     * 
     * @param limitedreplytime
     */
    public void setLimitedreplytime(java.util.Calendar limitedreplytime) {
        this.limitedreplytime = limitedreplytime;
    }


    /**
     * Gets the majorcomplaintreason value for this Complaint.
     * 
     * @return majorcomplaintreason
     */
    public String getMajorcomplaintreason() {
        return majorcomplaintreason;
    }


    /**
     * Sets the majorcomplaintreason value for this Complaint.
     * 
     * @param majorcomplaintreason
     */
    public void setMajorcomplaintreason(String majorcomplaintreason) {
        this.majorcomplaintreason = majorcomplaintreason;
    }


    /**
     * Gets the memberid value for this Complaint.
     * 
     * @return memberid
     */
    public String getMemberid() {
        return memberid;
    }


    /**
     * Sets the memberid value for this Complaint.
     * 
     * @param memberid
     */
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }


    /**
     * Gets the name value for this Complaint.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Complaint.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the ownerquote value for this Complaint.
     * 
     * @return ownerquote
     */
    public String getOwnerquote() {
        return ownerquote;
    }


    /**
     * Sets the ownerquote value for this Complaint.
     * 
     * @param ownerquote
     */
    public void setOwnerquote(String ownerquote) {
        this.ownerquote = ownerquote;
    }


    /**
     * Gets the portalcomplaintperson value for this Complaint.
     * 
     * @return portalcomplaintperson
     */
    public String getPortalcomplaintperson() {
        return portalcomplaintperson;
    }


    /**
     * Sets the portalcomplaintperson value for this Complaint.
     * 
     * @param portalcomplaintperson
     */
    public void setPortalcomplaintperson(String portalcomplaintperson) {
        this.portalcomplaintperson = portalcomplaintperson;
    }


    /**
     * Gets the processingresults value for this Complaint.
     * 
     * @return processingresults
     */
    public String getProcessingresults() {
        return processingresults;
    }


    /**
     * Sets the processingresults value for this Complaint.
     * 
     * @param processingresults
     */
    public void setProcessingresults(String processingresults) {
        this.processingresults = processingresults;
    }


    /**
     * Gets the project value for this Complaint.
     * 
     * @return project
     */
    public String getProject() {
        return project;
    }


    /**
     * Sets the project value for this Complaint.
     * 
     * @param project
     */
    public void setProject(String project) {
        this.project = project;
    }


    /**
     * Gets the relatednumber value for this Complaint.
     * 
     * @return relatednumber
     */
    public String getRelatednumber() {
        return relatednumber;
    }


    /**
     * Sets the relatednumber value for this Complaint.
     * 
     * @param relatednumber
     */
    public void setRelatednumber(String relatednumber) {
        this.relatednumber = relatednumber;
    }


    /**
     * Gets the replytime value for this Complaint.
     * 
     * @return replytime
     */
    public java.util.Calendar getReplytime() {
        return replytime;
    }


    /**
     * Sets the replytime value for this Complaint.
     * 
     * @param replytime
     */
    public void setReplytime(java.util.Calendar replytime) {
        this.replytime = replytime;
    }


    /**
     * Gets the returntime value for this Complaint.
     * 
     * @return returntime
     */
    public Integer getReturntime() {
        return returntime;
    }


    /**
     * Sets the returntime value for this Complaint.
     * 
     * @param returntime
     */
    public void setReturntime(Integer returntime) {
        this.returntime = returntime;
    }


    /**
     * Gets the revisit value for this Complaint.
     * 
     * @return revisit
     */
    public String getRevisit() {
        return revisit;
    }


    /**
     * Sets the revisit value for this Complaint.
     * 
     * @param revisit
     */
    public void setRevisit(String revisit) {
        this.revisit = revisit;
    }


    /**
     * Gets the submittime value for this Complaint.
     * 
     * @return submittime
     */
    public java.util.Calendar getSubmittime() {
        return submittime;
    }


    /**
     * Sets the submittime value for this Complaint.
     * 
     * @param submittime
     */
    public void setSubmittime(java.util.Calendar submittime) {
        this.submittime = submittime;
    }


    /**
     * Gets the timeout value for this Complaint.
     * 
     * @return timeout
     */
    public String getTimeout() {
        return timeout;
    }


    /**
     * Sets the timeout value for this Complaint.
     * 
     * @param timeout
     */
    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }


    /**
     * Gets the treatmentplan value for this Complaint.
     * 
     * @return treatmentplan
     */
    public String getTreatmentplan() {
        return treatmentplan;
    }


    /**
     * Sets the treatmentplan value for this Complaint.
     * 
     * @param treatmentplan
     */
    public void setTreatmentplan(String treatmentplan) {
        this.treatmentplan = treatmentplan;
    }


    /**
     * Gets the upgrade value for this Complaint.
     * 
     * @return upgrade
     */
    public String getUpgrade() {
        return upgrade;
    }


    /**
     * Sets the upgrade value for this Complaint.
     * 
     * @param upgrade
     */
    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }


    /**
     * Gets the visitdate value for this Complaint.
     * 
     * @return visitdate
     */
    public java.util.Calendar getVisitdate() {
        return visitdate;
    }


    /**
     * Sets the visitdate value for this Complaint.
     * 
     * @param visitdate
     */
    public void setVisitdate(java.util.Calendar visitdate) {
        this.visitdate = visitdate;
    }


    /**
     * Gets the visitopinion value for this Complaint.
     * 
     * @return visitopinion
     */
    public String getVisitopinion() {
        return visitopinion;
    }


    /**
     * Sets the visitopinion value for this Complaint.
     * 
     * @param visitopinion
     */
    public void setVisitopinion(String visitopinion) {
        this.visitopinion = visitopinion;
    }


    /**
     * Gets the visitsatisfaction value for this Complaint.
     * 
     * @return visitsatisfaction
     */
    public String getVisitsatisfaction() {
        return visitsatisfaction;
    }


    /**
     * Sets the visitsatisfaction value for this Complaint.
     * 
     * @param visitsatisfaction
     */
    public void setVisitsatisfaction(String visitsatisfaction) {
        this.visitsatisfaction = visitsatisfaction;
    }


    /**
     * Gets the whetherswarmsues value for this Complaint.
     * 
     * @return whetherswarmsues
     */
    public String getWhetherswarmsues() {
        return whetherswarmsues;
    }


    /**
     * Sets the whetherswarmsues value for this Complaint.
     * 
     * @param whetherswarmsues
     */
    public void setWhetherswarmsues(String whetherswarmsues) {
        this.whetherswarmsues = whetherswarmsues;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Complaint)) return false;
        Complaint other = (Complaint) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.classificationcomplaints==null && other.getClassificationcomplaints()==null) || 
             (this.classificationcomplaints!=null &&
              this.classificationcomplaints.equals(other.getClassificationcomplaints()))) &&
            ((this.complaintchannel==null && other.getComplaintchannel()==null) || 
             (this.complaintchannel!=null &&
              this.complaintchannel.equals(other.getComplaintchannel()))) &&
            ((this.complaintlevel==null && other.getComplaintlevel()==null) || 
             (this.complaintlevel!=null &&
              this.complaintlevel.equals(other.getComplaintlevel()))) &&
            ((this.complaintphone==null && other.getComplaintphone()==null) || 
             (this.complaintphone!=null &&
              this.complaintphone.equals(other.getComplaintphone()))) &&
            ((this.complaintsdescribes==null && other.getComplaintsdescribes()==null) || 
             (this.complaintsdescribes!=null &&
              this.complaintsdescribes.equals(other.getComplaintsdescribes()))) &&
            ((this.complaintsource==null && other.getComplaintsource()==null) || 
             (this.complaintsource!=null &&
              this.complaintsource.equals(other.getComplaintsource()))) &&
            ((this.complainttype==null && other.getComplainttype()==null) || 
             (this.complainttype!=null &&
              this.complainttype.equals(other.getComplainttype()))) &&
            ((this.completetime==null && other.getCompletetime()==null) || 
             (this.completetime!=null &&
              this.completetime.equals(other.getCompletetime()))) &&
            ((this.createby==null && other.getCreateby()==null) || 
             (this.createby!=null &&
              this.createby.equals(other.getCreateby()))) &&
            ((this.createname==null && other.getCreatename()==null) || 
             (this.createname!=null &&
              this.createname.equals(other.getCreatename()))) &&
            ((this.createon==null && other.getCreateon()==null) || 
             (this.createon!=null &&
              this.createon.equals(other.getCreateon()))) &&
            ((this.dispatchtime==null && other.getDispatchtime()==null) || 
             (this.dispatchtime!=null &&
              this.dispatchtime.equals(other.getDispatchtime()))) &&
            ((this.disposal==null && other.getDisposal()==null) || 
             (this.disposal!=null &&
              this.disposal.equals(other.getDisposal()))) &&
            ((this.documentsstate==null && other.getDocumentsstate()==null) || 
             (this.documentsstate!=null &&
              this.documentsstate.equals(other.getDocumentsstate()))) &&
            ((this.emotion==null && other.getEmotion()==null) || 
             (this.emotion!=null &&
              this.emotion.equals(other.getEmotion()))) &&
            ((this.housecode==null && other.getHousecode()==null) || 
             (this.housecode!=null &&
              this.housecode.equals(other.getHousecode()))) &&
            ((this.housedes==null && other.getHousedes()==null) || 
             (this.housedes!=null &&
              this.housedes.equals(other.getHousedes()))) &&
            ((this.imageList==null && other.getImageList()==null) || 
             (this.imageList!=null &&
              java.util.Arrays.equals(this.imageList, other.getImageList()))) &&
            ((this.importantcomplaintreason==null && other.getImportantcomplaintreason()==null) || 
             (this.importantcomplaintreason!=null &&
              this.importantcomplaintreason.equals(other.getImportantcomplaintreason()))) &&
            ((this.lastreturntime==null && other.getLastreturntime()==null) || 
             (this.lastreturntime!=null &&
              this.lastreturntime.equals(other.getLastreturntime()))) &&
            ((this.limitedreplytime==null && other.getLimitedreplytime()==null) || 
             (this.limitedreplytime!=null &&
              this.limitedreplytime.equals(other.getLimitedreplytime()))) &&
            ((this.majorcomplaintreason==null && other.getMajorcomplaintreason()==null) || 
             (this.majorcomplaintreason!=null &&
              this.majorcomplaintreason.equals(other.getMajorcomplaintreason()))) &&
            ((this.memberid==null && other.getMemberid()==null) || 
             (this.memberid!=null &&
              this.memberid.equals(other.getMemberid()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.ownerquote==null && other.getOwnerquote()==null) || 
             (this.ownerquote!=null &&
              this.ownerquote.equals(other.getOwnerquote()))) &&
            ((this.portalcomplaintperson==null && other.getPortalcomplaintperson()==null) || 
             (this.portalcomplaintperson!=null &&
              this.portalcomplaintperson.equals(other.getPortalcomplaintperson()))) &&
            ((this.processingresults==null && other.getProcessingresults()==null) || 
             (this.processingresults!=null &&
              this.processingresults.equals(other.getProcessingresults()))) &&
            ((this.project==null && other.getProject()==null) || 
             (this.project!=null &&
              this.project.equals(other.getProject()))) &&
            ((this.relatednumber==null && other.getRelatednumber()==null) || 
             (this.relatednumber!=null &&
              this.relatednumber.equals(other.getRelatednumber()))) &&
            ((this.replytime==null && other.getReplytime()==null) || 
             (this.replytime!=null &&
              this.replytime.equals(other.getReplytime()))) &&
            ((this.returntime==null && other.getReturntime()==null) || 
             (this.returntime!=null &&
              this.returntime.equals(other.getReturntime()))) &&
            ((this.revisit==null && other.getRevisit()==null) || 
             (this.revisit!=null &&
              this.revisit.equals(other.getRevisit()))) &&
            ((this.submittime==null && other.getSubmittime()==null) || 
             (this.submittime!=null &&
              this.submittime.equals(other.getSubmittime()))) &&
            ((this.timeout==null && other.getTimeout()==null) || 
             (this.timeout!=null &&
              this.timeout.equals(other.getTimeout()))) &&
            ((this.treatmentplan==null && other.getTreatmentplan()==null) || 
             (this.treatmentplan!=null &&
              this.treatmentplan.equals(other.getTreatmentplan()))) &&
            ((this.upgrade==null && other.getUpgrade()==null) || 
             (this.upgrade!=null &&
              this.upgrade.equals(other.getUpgrade()))) &&
            ((this.visitdate==null && other.getVisitdate()==null) || 
             (this.visitdate!=null &&
              this.visitdate.equals(other.getVisitdate()))) &&
            ((this.visitopinion==null && other.getVisitopinion()==null) || 
             (this.visitopinion!=null &&
              this.visitopinion.equals(other.getVisitopinion()))) &&
            ((this.visitsatisfaction==null && other.getVisitsatisfaction()==null) || 
             (this.visitsatisfaction!=null &&
              this.visitsatisfaction.equals(other.getVisitsatisfaction()))) &&
            ((this.whetherswarmsues==null && other.getWhetherswarmsues()==null) || 
             (this.whetherswarmsues!=null &&
              this.whetherswarmsues.equals(other.getWhetherswarmsues())));
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
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getClassificationcomplaints() != null) {
            _hashCode += getClassificationcomplaints().hashCode();
        }
        if (getComplaintchannel() != null) {
            _hashCode += getComplaintchannel().hashCode();
        }
        if (getComplaintlevel() != null) {
            _hashCode += getComplaintlevel().hashCode();
        }
        if (getComplaintphone() != null) {
            _hashCode += getComplaintphone().hashCode();
        }
        if (getComplaintsdescribes() != null) {
            _hashCode += getComplaintsdescribes().hashCode();
        }
        if (getComplaintsource() != null) {
            _hashCode += getComplaintsource().hashCode();
        }
        if (getComplainttype() != null) {
            _hashCode += getComplainttype().hashCode();
        }
        if (getCompletetime() != null) {
            _hashCode += getCompletetime().hashCode();
        }
        if (getCreateby() != null) {
            _hashCode += getCreateby().hashCode();
        }
        if (getCreatename() != null) {
            _hashCode += getCreatename().hashCode();
        }
        if (getCreateon() != null) {
            _hashCode += getCreateon().hashCode();
        }
        if (getDispatchtime() != null) {
            _hashCode += getDispatchtime().hashCode();
        }
        if (getDisposal() != null) {
            _hashCode += getDisposal().hashCode();
        }
        if (getDocumentsstate() != null) {
            _hashCode += getDocumentsstate().hashCode();
        }
        if (getEmotion() != null) {
            _hashCode += getEmotion().hashCode();
        }
        if (getHousecode() != null) {
            _hashCode += getHousecode().hashCode();
        }
        if (getHousedes() != null) {
            _hashCode += getHousedes().hashCode();
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
        if (getImportantcomplaintreason() != null) {
            _hashCode += getImportantcomplaintreason().hashCode();
        }
        if (getLastreturntime() != null) {
            _hashCode += getLastreturntime().hashCode();
        }
        if (getLimitedreplytime() != null) {
            _hashCode += getLimitedreplytime().hashCode();
        }
        if (getMajorcomplaintreason() != null) {
            _hashCode += getMajorcomplaintreason().hashCode();
        }
        if (getMemberid() != null) {
            _hashCode += getMemberid().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getOwnerquote() != null) {
            _hashCode += getOwnerquote().hashCode();
        }
        if (getPortalcomplaintperson() != null) {
            _hashCode += getPortalcomplaintperson().hashCode();
        }
        if (getProcessingresults() != null) {
            _hashCode += getProcessingresults().hashCode();
        }
        if (getProject() != null) {
            _hashCode += getProject().hashCode();
        }
        if (getRelatednumber() != null) {
            _hashCode += getRelatednumber().hashCode();
        }
        if (getReplytime() != null) {
            _hashCode += getReplytime().hashCode();
        }
        if (getReturntime() != null) {
            _hashCode += getReturntime().hashCode();
        }
        if (getRevisit() != null) {
            _hashCode += getRevisit().hashCode();
        }
        if (getSubmittime() != null) {
            _hashCode += getSubmittime().hashCode();
        }
        if (getTimeout() != null) {
            _hashCode += getTimeout().hashCode();
        }
        if (getTreatmentplan() != null) {
            _hashCode += getTreatmentplan().hashCode();
        }
        if (getUpgrade() != null) {
            _hashCode += getUpgrade().hashCode();
        }
        if (getVisitdate() != null) {
            _hashCode += getVisitdate().hashCode();
        }
        if (getVisitopinion() != null) {
            _hashCode += getVisitopinion().hashCode();
        }
        if (getVisitsatisfaction() != null) {
            _hashCode += getVisitsatisfaction().hashCode();
        }
        if (getWhetherswarmsues() != null) {
            _hashCode += getWhetherswarmsues().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Complaint.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Complaint"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classificationcomplaints");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "classificationcomplaints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintchannel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintchannel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintlevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintlevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintphone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintsdescribes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintsdescribes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintsource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintsource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complainttype");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complainttype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completetime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "completetime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createby");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "createby"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "createname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "createon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dispatchtime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "dispatchtime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disposal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "disposal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentsstate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "documentsstate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emotion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "emotion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("housecode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "housecode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("housedes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "housedes"));
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
        elemField.setFieldName("importantcomplaintreason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "importantcomplaintreason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastreturntime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "lastreturntime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limitedreplytime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "limitedreplytime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("majorcomplaintreason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "majorcomplaintreason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "memberid"));
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
        elemField.setFieldName("ownerquote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ownerquote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portalcomplaintperson");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "portalcomplaintperson"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processingresults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "processingresults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("project");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "project"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatednumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "relatednumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replytime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "replytime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returntime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "returntime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revisit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "revisit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submittime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "submittime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "timeout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("treatmentplan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "treatmentplan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upgrade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "upgrade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitopinion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitopinion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitsatisfaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitsatisfaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whetherswarmsues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "whetherswarmsues"));
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
