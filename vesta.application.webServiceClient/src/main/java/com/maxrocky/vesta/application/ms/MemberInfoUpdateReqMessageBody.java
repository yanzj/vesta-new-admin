/**
 * MemberInfoUpdateReqMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoUpdateReqMessageBody  implements java.io.Serializable {
    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyAccountInfo accountInfo;

    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyBaseInfo baseInfo;

    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCarInfo carInfo;

    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCardInfo cardInfo;

    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyContactInfo contactInfo;

    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyFamilyInfo familyInfo;

    private com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyGradeInfo gradeInfo;

    public MemberInfoUpdateReqMessageBody() {
    }

    public MemberInfoUpdateReqMessageBody(
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyAccountInfo accountInfo,
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyBaseInfo baseInfo,
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCarInfo carInfo,
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCardInfo cardInfo,
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyContactInfo contactInfo,
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyFamilyInfo familyInfo,
           com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyGradeInfo gradeInfo) {
           this.accountInfo = accountInfo;
           this.baseInfo = baseInfo;
           this.carInfo = carInfo;
           this.cardInfo = cardInfo;
           this.contactInfo = contactInfo;
           this.familyInfo = familyInfo;
           this.gradeInfo = gradeInfo;
    }


    /**
     * Gets the accountInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return accountInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyAccountInfo getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the baseInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return baseInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyBaseInfo getBaseInfo() {
        return baseInfo;
    }


    /**
     * Sets the baseInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param baseInfo
     */
    public void setBaseInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }


    /**
     * Gets the carInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return carInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCarInfo getCarInfo() {
        return carInfo;
    }


    /**
     * Sets the carInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param carInfo
     */
    public void setCarInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCarInfo carInfo) {
        this.carInfo = carInfo;
    }


    /**
     * Gets the cardInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return cardInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCardInfo getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param cardInfo
     */
    public void setCardInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }


    /**
     * Gets the contactInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return contactInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyContactInfo getContactInfo() {
        return contactInfo;
    }


    /**
     * Sets the contactInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param contactInfo
     */
    public void setContactInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }


    /**
     * Gets the familyInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return familyInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyFamilyInfo getFamilyInfo() {
        return familyInfo;
    }


    /**
     * Sets the familyInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param familyInfo
     */
    public void setFamilyInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyFamilyInfo familyInfo) {
        this.familyInfo = familyInfo;
    }


    /**
     * Gets the gradeInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @return gradeInfo
     */
    public com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyGradeInfo getGradeInfo() {
        return gradeInfo;
    }


    /**
     * Sets the gradeInfo value for this MemberInfoUpdateReqMessageBody.
     * 
     * @param gradeInfo
     */
    public void setGradeInfo(com.maxrocky.vesta.application.ms.MemberInfoUpdateReqMessageBodyGradeInfo gradeInfo) {
        this.gradeInfo = gradeInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoUpdateReqMessageBody)) return false;
        MemberInfoUpdateReqMessageBody other = (MemberInfoUpdateReqMessageBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountInfo==null && other.getAccountInfo()==null) || 
             (this.accountInfo!=null &&
              this.accountInfo.equals(other.getAccountInfo()))) &&
            ((this.baseInfo==null && other.getBaseInfo()==null) || 
             (this.baseInfo!=null &&
              this.baseInfo.equals(other.getBaseInfo()))) &&
            ((this.carInfo==null && other.getCarInfo()==null) || 
             (this.carInfo!=null &&
              this.carInfo.equals(other.getCarInfo()))) &&
            ((this.cardInfo==null && other.getCardInfo()==null) || 
             (this.cardInfo!=null &&
              this.cardInfo.equals(other.getCardInfo()))) &&
            ((this.contactInfo==null && other.getContactInfo()==null) || 
             (this.contactInfo!=null &&
              this.contactInfo.equals(other.getContactInfo()))) &&
            ((this.familyInfo==null && other.getFamilyInfo()==null) || 
             (this.familyInfo!=null &&
              this.familyInfo.equals(other.getFamilyInfo()))) &&
            ((this.gradeInfo==null && other.getGradeInfo()==null) || 
             (this.gradeInfo!=null &&
              this.gradeInfo.equals(other.getGradeInfo())));
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
        if (getAccountInfo() != null) {
            _hashCode += getAccountInfo().hashCode();
        }
        if (getBaseInfo() != null) {
            _hashCode += getBaseInfo().hashCode();
        }
        if (getCarInfo() != null) {
            _hashCode += getCarInfo().hashCode();
        }
        if (getCardInfo() != null) {
            _hashCode += getCardInfo().hashCode();
        }
        if (getContactInfo() != null) {
            _hashCode += getContactInfo().hashCode();
        }
        if (getFamilyInfo() != null) {
            _hashCode += getFamilyInfo().hashCode();
        }
        if (getGradeInfo() != null) {
            _hashCode += getGradeInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoUpdateReqMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AccountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyAccountInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BaseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyBaseInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyCarInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyCardInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ContactInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyContactInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familyInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "FamilyInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyFamilyInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradeInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyGradeInfo"));
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
