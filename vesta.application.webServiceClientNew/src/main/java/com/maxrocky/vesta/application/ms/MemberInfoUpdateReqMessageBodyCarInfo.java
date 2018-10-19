/**
 * MemberInfoUpdateReqMessageBodyCarInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoUpdateReqMessageBodyCarInfo  implements java.io.Serializable {
    private Carpower carPower;

    private Cartype carType;

    private java.lang.String id;

    private java.lang.String licenseId;

    private java.lang.String memberId;

    private State_code state_code;

    public MemberInfoUpdateReqMessageBodyCarInfo() {
    }

    public MemberInfoUpdateReqMessageBodyCarInfo(
           Carpower carPower,
           Cartype carType,
           java.lang.String id,
           java.lang.String licenseId,
           java.lang.String memberId,
           State_code state_code) {
           this.carPower = carPower;
           this.carType = carType;
           this.id = id;
           this.licenseId = licenseId;
           this.memberId = memberId;
           this.state_code = state_code;
    }


    /**
     * Gets the carPower value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @return carPower
     */
    public Carpower getCarPower() {
        return carPower;
    }


    /**
     * Sets the carPower value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @param carPower
     */
    public void setCarPower(Carpower carPower) {
        this.carPower = carPower;
    }


    /**
     * Gets the carType value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @return carType
     */
    public Cartype getCarType() {
        return carType;
    }


    /**
     * Sets the carType value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @param carType
     */
    public void setCarType(Cartype carType) {
        this.carType = carType;
    }


    /**
     * Gets the id value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the licenseId value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @return licenseId
     */
    public java.lang.String getLicenseId() {
        return licenseId;
    }


    /**
     * Sets the licenseId value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @param licenseId
     */
    public void setLicenseId(java.lang.String licenseId) {
        this.licenseId = licenseId;
    }


    /**
     * Gets the memberId value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @return memberId
     */
    public java.lang.String getMemberId() {
        return memberId;
    }


    /**
     * Sets the memberId value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @param memberId
     */
    public void setMemberId(java.lang.String memberId) {
        this.memberId = memberId;
    }


    /**
     * Gets the state_code value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @return state_code
     */
    public State_code getState_code() {
        return state_code;
    }


    /**
     * Sets the state_code value for this MemberInfoUpdateReqMessageBodyCarInfo.
     * 
     * @param state_code
     */
    public void setState_code(State_code state_code) {
        this.state_code = state_code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoUpdateReqMessageBodyCarInfo)) return false;
        MemberInfoUpdateReqMessageBodyCarInfo other = (MemberInfoUpdateReqMessageBodyCarInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.carPower==null && other.getCarPower()==null) || 
             (this.carPower!=null &&
              this.carPower.equals(other.getCarPower()))) &&
            ((this.carType==null && other.getCarType()==null) || 
             (this.carType!=null &&
              this.carType.equals(other.getCarType()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.licenseId==null && other.getLicenseId()==null) || 
             (this.licenseId!=null &&
              this.licenseId.equals(other.getLicenseId()))) &&
            ((this.memberId==null && other.getMemberId()==null) || 
             (this.memberId!=null &&
              this.memberId.equals(other.getMemberId()))) &&
            ((this.state_code==null && other.getState_code()==null) || 
             (this.state_code!=null &&
              this.state_code.equals(other.getState_code())));
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
        if (getCarPower() != null) {
            _hashCode += getCarPower().hashCode();
        }
        if (getCarType() != null) {
            _hashCode += getCarType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLicenseId() != null) {
            _hashCode += getLicenseId().hashCode();
        }
        if (getMemberId() != null) {
            _hashCode += getMemberId().hashCode();
        }
        if (getState_code() != null) {
            _hashCode += getState_code().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoUpdateReqMessageBodyCarInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyCarInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carPower");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarPower"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "carpower"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "cartype"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "LicenseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state_code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "State_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "state_code"));
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
