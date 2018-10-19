/**
 * MemberRegisterResMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberRegisterResMessageBody  implements java.io.Serializable {
    private MemberRegisterResMessageBodyAccountInfo accountInfo;

    private MemberRegisterResMessageBodyBaseInfo baseInfo;

    private MemberRegisterResMessageBodyCardInfo cardInfo;

    private MemberRegisterResMessageBodyContactInfo contactInfo;

    private MemberRegisterResMessageBodyGradeInfo gradeInfo;

    private java.lang.String memberId;

    public MemberRegisterResMessageBody() {
    }

    public MemberRegisterResMessageBody(
           MemberRegisterResMessageBodyAccountInfo accountInfo,
           MemberRegisterResMessageBodyBaseInfo baseInfo,
           MemberRegisterResMessageBodyCardInfo cardInfo,
           MemberRegisterResMessageBodyContactInfo contactInfo,
           MemberRegisterResMessageBodyGradeInfo gradeInfo,
           java.lang.String memberId) {
           this.accountInfo = accountInfo;
           this.baseInfo = baseInfo;
           this.cardInfo = cardInfo;
           this.contactInfo = contactInfo;
           this.gradeInfo = gradeInfo;
           this.memberId = memberId;
    }


    /**
     * Gets the accountInfo value for this MemberRegisterResMessageBody.
     * 
     * @return accountInfo
     */
    public MemberRegisterResMessageBodyAccountInfo getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this MemberRegisterResMessageBody.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(MemberRegisterResMessageBodyAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the baseInfo value for this MemberRegisterResMessageBody.
     * 
     * @return baseInfo
     */
    public MemberRegisterResMessageBodyBaseInfo getBaseInfo() {
        return baseInfo;
    }


    /**
     * Sets the baseInfo value for this MemberRegisterResMessageBody.
     * 
     * @param baseInfo
     */
    public void setBaseInfo(MemberRegisterResMessageBodyBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }


    /**
     * Gets the cardInfo value for this MemberRegisterResMessageBody.
     * 
     * @return cardInfo
     */
    public MemberRegisterResMessageBodyCardInfo getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this MemberRegisterResMessageBody.
     * 
     * @param cardInfo
     */
    public void setCardInfo(MemberRegisterResMessageBodyCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }


    /**
     * Gets the contactInfo value for this MemberRegisterResMessageBody.
     * 
     * @return contactInfo
     */
    public MemberRegisterResMessageBodyContactInfo getContactInfo() {
        return contactInfo;
    }


    /**
     * Sets the contactInfo value for this MemberRegisterResMessageBody.
     * 
     * @param contactInfo
     */
    public void setContactInfo(MemberRegisterResMessageBodyContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }


    /**
     * Gets the gradeInfo value for this MemberRegisterResMessageBody.
     * 
     * @return gradeInfo
     */
    public MemberRegisterResMessageBodyGradeInfo getGradeInfo() {
        return gradeInfo;
    }


    /**
     * Sets the gradeInfo value for this MemberRegisterResMessageBody.
     * 
     * @param gradeInfo
     */
    public void setGradeInfo(MemberRegisterResMessageBodyGradeInfo gradeInfo) {
        this.gradeInfo = gradeInfo;
    }


    /**
     * Gets the memberId value for this MemberRegisterResMessageBody.
     * 
     * @return memberId
     */
    public java.lang.String getMemberId() {
        return memberId;
    }


    /**
     * Sets the memberId value for this MemberRegisterResMessageBody.
     * 
     * @param memberId
     */
    public void setMemberId(java.lang.String memberId) {
        this.memberId = memberId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberRegisterResMessageBody)) return false;
        MemberRegisterResMessageBody other = (MemberRegisterResMessageBody) obj;
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
            ((this.cardInfo==null && other.getCardInfo()==null) || 
             (this.cardInfo!=null &&
              this.cardInfo.equals(other.getCardInfo()))) &&
            ((this.contactInfo==null && other.getContactInfo()==null) || 
             (this.contactInfo!=null &&
              this.contactInfo.equals(other.getContactInfo()))) &&
            ((this.gradeInfo==null && other.getGradeInfo()==null) || 
             (this.gradeInfo!=null &&
              this.gradeInfo.equals(other.getGradeInfo()))) &&
            ((this.memberId==null && other.getMemberId()==null) || 
             (this.memberId!=null &&
              this.memberId.equals(other.getMemberId())));
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
        if (getCardInfo() != null) {
            _hashCode += getCardInfo().hashCode();
        }
        if (getContactInfo() != null) {
            _hashCode += getContactInfo().hashCode();
        }
        if (getGradeInfo() != null) {
            _hashCode += getGradeInfo().hashCode();
        }
        if (getMemberId() != null) {
            _hashCode += getMemberId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberRegisterResMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterResMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AccountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterResMessageBodyAccountInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BaseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterResMessageBodyBaseInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterResMessageBodyCardInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ContactInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterResMessageBodyContactInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradeInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterResMessageBodyGradeInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberId"));
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
