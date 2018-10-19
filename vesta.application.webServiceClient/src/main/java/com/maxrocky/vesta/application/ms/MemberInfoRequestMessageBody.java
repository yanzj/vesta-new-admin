/**
 * MemberInfoRequestMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoRequestMessageBody  implements java.io.Serializable {
    private com.maxrocky.vesta.application.ms.Businesssource businesssource;

    private java.lang.String cardNo;

    private java.lang.String IDCardNo;

    private java.lang.String memberID;

    private java.lang.String phoneNo;

    private java.lang.String registerID;

    private java.lang.Boolean inclHouseInfo;

    public MemberInfoRequestMessageBody() {
    }

    public MemberInfoRequestMessageBody(
           com.maxrocky.vesta.application.ms.Businesssource businesssource,
           java.lang.String cardNo,
           java.lang.String IDCardNo,
           java.lang.String memberID,
           java.lang.String phoneNo,
           java.lang.String registerID,
           java.lang.Boolean inclHouseInfo) {
           this.businesssource = businesssource;
           this.cardNo = cardNo;
           this.IDCardNo = IDCardNo;
           this.memberID = memberID;
           this.phoneNo = phoneNo;
           this.registerID = registerID;
           this.inclHouseInfo = inclHouseInfo;
    }


    /**
     * Gets the businesssource value for this MemberInfoRequestMessageBody.
     * 
     * @return businesssource
     */
    public com.maxrocky.vesta.application.ms.Businesssource getBusinesssource() {
        return businesssource;
    }


    /**
     * Sets the businesssource value for this MemberInfoRequestMessageBody.
     * 
     * @param businesssource
     */
    public void setBusinesssource(com.maxrocky.vesta.application.ms.Businesssource businesssource) {
        this.businesssource = businesssource;
    }


    /**
     * Gets the cardNo value for this MemberInfoRequestMessageBody.
     * 
     * @return cardNo
     */
    public java.lang.String getCardNo() {
        return cardNo;
    }


    /**
     * Sets the cardNo value for this MemberInfoRequestMessageBody.
     * 
     * @param cardNo
     */
    public void setCardNo(java.lang.String cardNo) {
        this.cardNo = cardNo;
    }


    /**
     * Gets the IDCardNo value for this MemberInfoRequestMessageBody.
     * 
     * @return IDCardNo
     */
    public java.lang.String getIDCardNo() {
        return IDCardNo;
    }


    /**
     * Sets the IDCardNo value for this MemberInfoRequestMessageBody.
     * 
     * @param IDCardNo
     */
    public void setIDCardNo(java.lang.String IDCardNo) {
        this.IDCardNo = IDCardNo;
    }


    /**
     * Gets the memberID value for this MemberInfoRequestMessageBody.
     * 
     * @return memberID
     */
    public java.lang.String getMemberID() {
        return memberID;
    }


    /**
     * Sets the memberID value for this MemberInfoRequestMessageBody.
     * 
     * @param memberID
     */
    public void setMemberID(java.lang.String memberID) {
        this.memberID = memberID;
    }


    /**
     * Gets the phoneNo value for this MemberInfoRequestMessageBody.
     * 
     * @return phoneNo
     */
    public java.lang.String getPhoneNo() {
        return phoneNo;
    }


    /**
     * Sets the phoneNo value for this MemberInfoRequestMessageBody.
     * 
     * @param phoneNo
     */
    public void setPhoneNo(java.lang.String phoneNo) {
        this.phoneNo = phoneNo;
    }


    /**
     * Gets the registerID value for this MemberInfoRequestMessageBody.
     * 
     * @return registerID
     */
    public java.lang.String getRegisterID() {
        return registerID;
    }


    /**
     * Sets the registerID value for this MemberInfoRequestMessageBody.
     * 
     * @param registerID
     */
    public void setRegisterID(java.lang.String registerID) {
        this.registerID = registerID;
    }


    /**
     * Gets the inclHouseInfo value for this MemberInfoRequestMessageBody.
     * 
     * @return inclHouseInfo
     */
    public java.lang.Boolean getInclHouseInfo() {
        return inclHouseInfo;
    }


    /**
     * Sets the inclHouseInfo value for this MemberInfoRequestMessageBody.
     * 
     * @param inclHouseInfo
     */
    public void setInclHouseInfo(java.lang.Boolean inclHouseInfo) {
        this.inclHouseInfo = inclHouseInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoRequestMessageBody)) return false;
        MemberInfoRequestMessageBody other = (MemberInfoRequestMessageBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.businesssource==null && other.getBusinesssource()==null) || 
             (this.businesssource!=null &&
              this.businesssource.equals(other.getBusinesssource()))) &&
            ((this.cardNo==null && other.getCardNo()==null) || 
             (this.cardNo!=null &&
              this.cardNo.equals(other.getCardNo()))) &&
            ((this.IDCardNo==null && other.getIDCardNo()==null) || 
             (this.IDCardNo!=null &&
              this.IDCardNo.equals(other.getIDCardNo()))) &&
            ((this.memberID==null && other.getMemberID()==null) || 
             (this.memberID!=null &&
              this.memberID.equals(other.getMemberID()))) &&
            ((this.phoneNo==null && other.getPhoneNo()==null) || 
             (this.phoneNo!=null &&
              this.phoneNo.equals(other.getPhoneNo()))) &&
            ((this.registerID==null && other.getRegisterID()==null) || 
             (this.registerID!=null &&
              this.registerID.equals(other.getRegisterID()))) &&
            ((this.inclHouseInfo==null && other.getInclHouseInfo()==null) || 
             (this.inclHouseInfo!=null &&
              this.inclHouseInfo.equals(other.getInclHouseInfo())));
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
        if (getBusinesssource() != null) {
            _hashCode += getBusinesssource().hashCode();
        }
        if (getCardNo() != null) {
            _hashCode += getCardNo().hashCode();
        }
        if (getIDCardNo() != null) {
            _hashCode += getIDCardNo().hashCode();
        }
        if (getMemberID() != null) {
            _hashCode += getMemberID().hashCode();
        }
        if (getPhoneNo() != null) {
            _hashCode += getPhoneNo().hashCode();
        }
        if (getRegisterID() != null) {
            _hashCode += getRegisterID().hashCode();
        }
        if (getInclHouseInfo() != null) {
            _hashCode += getInclHouseInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoRequestMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoRequestMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businesssource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Businesssource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "businesssource"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDCardNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "IDCardNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PhoneNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registerID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "RegisterID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inclHouseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "inclHouseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
