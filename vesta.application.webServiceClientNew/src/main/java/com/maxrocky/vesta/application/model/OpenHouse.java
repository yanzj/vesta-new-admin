/**
 * OpenHouse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

import com.maxrocky.vesta.application.ws.*;

public class OpenHouse  implements java.io.Serializable {
    private java.lang.String accompanyPerson;

    private OpenDayStatus completedStatus;

    private java.lang.String deliveryPlan;

    private java.lang.String describe;

    private java.lang.String houseCode;

    private ImageModel[] imageList;

    private java.lang.String ownerSign;

    private Quality quality;

    private Schedulesatisfied schedulesatisfied;

    private java.util.Calendar signingDate;

    public OpenHouse() {
    }

    public OpenHouse(
           java.lang.String accompanyPerson,
           OpenDayStatus completedStatus,
           java.lang.String deliveryPlan,
           java.lang.String describe,
           java.lang.String houseCode,
           ImageModel[] imageList,
           java.lang.String ownerSign,
           Quality quality,
           Schedulesatisfied schedulesatisfied,
           java.util.Calendar signingDate) {
           this.accompanyPerson = accompanyPerson;
           this.completedStatus = completedStatus;
           this.deliveryPlan = deliveryPlan;
           this.describe = describe;
           this.houseCode = houseCode;
           this.imageList = imageList;
           this.ownerSign = ownerSign;
           this.quality = quality;
           this.schedulesatisfied = schedulesatisfied;
           this.signingDate = signingDate;
    }


    /**
     * Gets the accompanyPerson value for this OpenHouse.
     * 
     * @return accompanyPerson
     */
    public java.lang.String getAccompanyPerson() {
        return accompanyPerson;
    }


    /**
     * Sets the accompanyPerson value for this OpenHouse.
     * 
     * @param accompanyPerson
     */
    public void setAccompanyPerson(java.lang.String accompanyPerson) {
        this.accompanyPerson = accompanyPerson;
    }


    /**
     * Gets the completedStatus value for this OpenHouse.
     * 
     * @return completedStatus
     */
    public OpenDayStatus getCompletedStatus() {
        return completedStatus;
    }


    /**
     * Sets the completedStatus value for this OpenHouse.
     * 
     * @param completedStatus
     */
    public void setCompletedStatus(OpenDayStatus completedStatus) {
        this.completedStatus = completedStatus;
    }


    /**
     * Gets the deliveryPlan value for this OpenHouse.
     * 
     * @return deliveryPlan
     */
    public java.lang.String getDeliveryPlan() {
        return deliveryPlan;
    }


    /**
     * Sets the deliveryPlan value for this OpenHouse.
     * 
     * @param deliveryPlan
     */
    public void setDeliveryPlan(java.lang.String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }


    /**
     * Gets the describe value for this OpenHouse.
     * 
     * @return describe
     */
    public java.lang.String getDescribe() {
        return describe;
    }


    /**
     * Sets the describe value for this OpenHouse.
     * 
     * @param describe
     */
    public void setDescribe(java.lang.String describe) {
        this.describe = describe;
    }


    /**
     * Gets the houseCode value for this OpenHouse.
     * 
     * @return houseCode
     */
    public java.lang.String getHouseCode() {
        return houseCode;
    }


    /**
     * Sets the houseCode value for this OpenHouse.
     * 
     * @param houseCode
     */
    public void setHouseCode(java.lang.String houseCode) {
        this.houseCode = houseCode;
    }


    /**
     * Gets the imageList value for this OpenHouse.
     * 
     * @return imageList
     */
    public ImageModel[] getImageList() {
        return imageList;
    }


    /**
     * Sets the imageList value for this OpenHouse.
     * 
     * @param imageList
     */
    public void setImageList(ImageModel[] imageList) {
        this.imageList = imageList;
    }


    /**
     * Gets the ownerSign value for this OpenHouse.
     * 
     * @return ownerSign
     */
    public java.lang.String getOwnerSign() {
        return ownerSign;
    }


    /**
     * Sets the ownerSign value for this OpenHouse.
     * 
     * @param ownerSign
     */
    public void setOwnerSign(java.lang.String ownerSign) {
        this.ownerSign = ownerSign;
    }


    /**
     * Gets the quality value for this OpenHouse.
     * 
     * @return quality
     */
    public Quality getQuality() {
        return quality;
    }


    /**
     * Sets the quality value for this OpenHouse.
     * 
     * @param quality
     */
    public void setQuality(Quality quality) {
        this.quality = quality;
    }


    /**
     * Gets the schedulesatisfied value for this OpenHouse.
     * 
     * @return schedulesatisfied
     */
    public Schedulesatisfied getSchedulesatisfied() {
        return schedulesatisfied;
    }


    /**
     * Sets the schedulesatisfied value for this OpenHouse.
     * 
     * @param schedulesatisfied
     */
    public void setSchedulesatisfied(Schedulesatisfied schedulesatisfied) {
        this.schedulesatisfied = schedulesatisfied;
    }


    /**
     * Gets the signingDate value for this OpenHouse.
     * 
     * @return signingDate
     */
    public java.util.Calendar getSigningDate() {
        return signingDate;
    }


    /**
     * Sets the signingDate value for this OpenHouse.
     * 
     * @param signingDate
     */
    public void setSigningDate(java.util.Calendar signingDate) {
        this.signingDate = signingDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OpenHouse)) return false;
        OpenHouse other = (OpenHouse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accompanyPerson==null && other.getAccompanyPerson()==null) || 
             (this.accompanyPerson!=null &&
              this.accompanyPerson.equals(other.getAccompanyPerson()))) &&
            ((this.completedStatus==null && other.getCompletedStatus()==null) || 
             (this.completedStatus!=null &&
              this.completedStatus.equals(other.getCompletedStatus()))) &&
            ((this.deliveryPlan==null && other.getDeliveryPlan()==null) || 
             (this.deliveryPlan!=null &&
              this.deliveryPlan.equals(other.getDeliveryPlan()))) &&
            ((this.describe==null && other.getDescribe()==null) || 
             (this.describe!=null &&
              this.describe.equals(other.getDescribe()))) &&
            ((this.houseCode==null && other.getHouseCode()==null) || 
             (this.houseCode!=null &&
              this.houseCode.equals(other.getHouseCode()))) &&
            ((this.imageList==null && other.getImageList()==null) || 
             (this.imageList!=null &&
              java.util.Arrays.equals(this.imageList, other.getImageList()))) &&
            ((this.ownerSign==null && other.getOwnerSign()==null) || 
             (this.ownerSign!=null &&
              this.ownerSign.equals(other.getOwnerSign()))) &&
            ((this.quality==null && other.getQuality()==null) || 
             (this.quality!=null &&
              this.quality.equals(other.getQuality()))) &&
            ((this.schedulesatisfied==null && other.getSchedulesatisfied()==null) || 
             (this.schedulesatisfied!=null &&
              this.schedulesatisfied.equals(other.getSchedulesatisfied()))) &&
            ((this.signingDate==null && other.getSigningDate()==null) || 
             (this.signingDate!=null &&
              this.signingDate.equals(other.getSigningDate())));
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
        if (getAccompanyPerson() != null) {
            _hashCode += getAccompanyPerson().hashCode();
        }
        if (getCompletedStatus() != null) {
            _hashCode += getCompletedStatus().hashCode();
        }
        if (getDeliveryPlan() != null) {
            _hashCode += getDeliveryPlan().hashCode();
        }
        if (getDescribe() != null) {
            _hashCode += getDescribe().hashCode();
        }
        if (getHouseCode() != null) {
            _hashCode += getHouseCode().hashCode();
        }
        if (getImageList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImageList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getImageList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOwnerSign() != null) {
            _hashCode += getOwnerSign().hashCode();
        }
        if (getQuality() != null) {
            _hashCode += getQuality().hashCode();
        }
        if (getSchedulesatisfied() != null) {
            _hashCode += getSchedulesatisfied().hashCode();
        }
        if (getSigningDate() != null) {
            _hashCode += getSigningDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OpenHouse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "OpenHouse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accompanyPerson");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "accompanyPerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completedStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "completedStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "OpenDayStatus"));
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
        elemField.setFieldName("describe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "describe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("ownerSign");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ownerSign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quality");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "quality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Quality"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedulesatisfied");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "schedulesatisfied"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Schedulesatisfied"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "signingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
