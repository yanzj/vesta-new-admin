/**
 * CarInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class CarInfo  implements java.io.Serializable {
    private java.lang.String carPower;

    private java.lang.String carType;

    private java.lang.String id;

    private java.lang.String licenseId;

    public CarInfo() {
    }

    public CarInfo(
           java.lang.String carPower,
           java.lang.String carType,
           java.lang.String id,
           java.lang.String licenseId) {
           this.carPower = carPower;
           this.carType = carType;
           this.id = id;
           this.licenseId = licenseId;
    }


    /**
     * Gets the carPower value for this CarInfo.
     * 
     * @return carPower
     */
    public java.lang.String getCarPower() {
        return carPower;
    }


    /**
     * Sets the carPower value for this CarInfo.
     * 
     * @param carPower
     */
    public void setCarPower(java.lang.String carPower) {
        this.carPower = carPower;
    }


    /**
     * Gets the carType value for this CarInfo.
     * 
     * @return carType
     */
    public java.lang.String getCarType() {
        return carType;
    }


    /**
     * Sets the carType value for this CarInfo.
     * 
     * @param carType
     */
    public void setCarType(java.lang.String carType) {
        this.carType = carType;
    }


    /**
     * Gets the id value for this CarInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this CarInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the licenseId value for this CarInfo.
     * 
     * @return licenseId
     */
    public java.lang.String getLicenseId() {
        return licenseId;
    }


    /**
     * Sets the licenseId value for this CarInfo.
     * 
     * @param licenseId
     */
    public void setLicenseId(java.lang.String licenseId) {
        this.licenseId = licenseId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CarInfo)) return false;
        CarInfo other = (CarInfo) obj;
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
              this.licenseId.equals(other.getLicenseId())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CarInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carPower");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarPower"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "LicenseId"));
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
