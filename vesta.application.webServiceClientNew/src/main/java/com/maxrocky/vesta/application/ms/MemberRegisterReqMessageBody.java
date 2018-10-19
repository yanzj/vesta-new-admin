/**
 * MemberRegisterReqMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberRegisterReqMessageBody  implements java.io.Serializable {
    private MemberRegisterReqMessageBodyAccountInfo accountInfo;

    private MemberRegisterReqMessageBodyBaseInfo baseInfo;

    private MemberRegisterReqMessageBodyCardInfo cardInfo;

    private MemberRegisterReqMessageBodyContactInfo contactInfo;

    private MemberRegisterReqMessageBodyGradeInfo gradeInfo;

    public MemberRegisterReqMessageBody() {
    }

    public MemberRegisterReqMessageBody(
           MemberRegisterReqMessageBodyAccountInfo accountInfo,
           MemberRegisterReqMessageBodyBaseInfo baseInfo,
           MemberRegisterReqMessageBodyCardInfo cardInfo,
           MemberRegisterReqMessageBodyContactInfo contactInfo,
           MemberRegisterReqMessageBodyGradeInfo gradeInfo) {
           this.accountInfo = accountInfo;
           this.baseInfo = baseInfo;
           this.cardInfo = cardInfo;
           this.contactInfo = contactInfo;
           this.gradeInfo = gradeInfo;
    }


    /**
     * Gets the accountInfo value for this MemberRegisterReqMessageBody.
     * 
     * @return accountInfo
     */
    public MemberRegisterReqMessageBodyAccountInfo getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this MemberRegisterReqMessageBody.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(MemberRegisterReqMessageBodyAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the baseInfo value for this MemberRegisterReqMessageBody.
     * 
     * @return baseInfo
     */
    public MemberRegisterReqMessageBodyBaseInfo getBaseInfo() {
        return baseInfo;
    }


    /**
     * Sets the baseInfo value for this MemberRegisterReqMessageBody.
     * 
     * @param baseInfo
     */
    public void setBaseInfo(MemberRegisterReqMessageBodyBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }


    /**
     * Gets the cardInfo value for this MemberRegisterReqMessageBody.
     * 
     * @return cardInfo
     */
    public MemberRegisterReqMessageBodyCardInfo getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this MemberRegisterReqMessageBody.
     * 
     * @param cardInfo
     */
    public void setCardInfo(MemberRegisterReqMessageBodyCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }


    /**
     * Gets the contactInfo value for this MemberRegisterReqMessageBody.
     * 
     * @return contactInfo
     */
    public MemberRegisterReqMessageBodyContactInfo getContactInfo() {
        return contactInfo;
    }


    /**
     * Sets the contactInfo value for this MemberRegisterReqMessageBody.
     * 
     * @param contactInfo
     */
    public void setContactInfo(MemberRegisterReqMessageBodyContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }


    /**
     * Gets the gradeInfo value for this MemberRegisterReqMessageBody.
     * 
     * @return gradeInfo
     */
    public MemberRegisterReqMessageBodyGradeInfo getGradeInfo() {
        return gradeInfo;
    }


    /**
     * Sets the gradeInfo value for this MemberRegisterReqMessageBody.
     * 
     * @param gradeInfo
     */
    public void setGradeInfo(MemberRegisterReqMessageBodyGradeInfo gradeInfo) {
        this.gradeInfo = gradeInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberRegisterReqMessageBody)) return false;
        MemberRegisterReqMessageBody other = (MemberRegisterReqMessageBody) obj;
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
        if (getCardInfo() != null) {
            _hashCode += getCardInfo().hashCode();
        }
        if (getContactInfo() != null) {
            _hashCode += getContactInfo().hashCode();
        }
        if (getGradeInfo() != null) {
            _hashCode += getGradeInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberRegisterReqMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AccountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBodyAccountInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BaseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBodyBaseInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBodyCardInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ContactInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBodyContactInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradeInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBodyGradeInfo"));
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
