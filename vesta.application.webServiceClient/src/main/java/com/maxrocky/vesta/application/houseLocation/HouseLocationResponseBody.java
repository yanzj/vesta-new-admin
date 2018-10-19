/**
 * HouseLocationResponseBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.houseLocation;

import com.maxrocky.vesta.application.classification.model.Classification;
import com.maxrocky.vesta.application.houseLocation.model.HouseLocation;

public class HouseLocationResponseBody  implements java.io.Serializable {
    private HouseLocation[] houseLocationList;

    private Classification[] locationTypeList;

    public HouseLocationResponseBody() {
    }

    public HouseLocationResponseBody(
           HouseLocation[] houseLocationList,
           Classification[] locationTypeList) {
           this.houseLocationList = houseLocationList;
           this.locationTypeList = locationTypeList;
    }


    /**
     * Gets the houseLocationList value for this HouseLocationResponseBody.
     * 
     * @return houseLocationList
     */
    public HouseLocation[] getHouseLocationList() {
        return houseLocationList;
    }


    /**
     * Sets the houseLocationList value for this HouseLocationResponseBody.
     * 
     * @param houseLocationList
     */
    public void setHouseLocationList(HouseLocation[] houseLocationList) {
        this.houseLocationList = houseLocationList;
    }


    /**
     * Gets the locationTypeList value for this HouseLocationResponseBody.
     * 
     * @return locationTypeList
     */
    public Classification[] getLocationTypeList() {
        return locationTypeList;
    }


    /**
     * Sets the locationTypeList value for this HouseLocationResponseBody.
     * 
     * @param locationTypeList
     */
    public void setLocationTypeList(Classification[] locationTypeList) {
        this.locationTypeList = locationTypeList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof HouseLocationResponseBody)) return false;
        HouseLocationResponseBody other = (HouseLocationResponseBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.houseLocationList==null && other.getHouseLocationList()==null) || 
             (this.houseLocationList!=null &&
              java.util.Arrays.equals(this.houseLocationList, other.getHouseLocationList()))) &&
            ((this.locationTypeList==null && other.getLocationTypeList()==null) || 
             (this.locationTypeList!=null &&
              java.util.Arrays.equals(this.locationTypeList, other.getLocationTypeList())));
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
        if (getHouseLocationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHouseLocationList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getHouseLocationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLocationTypeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLocationTypeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getLocationTypeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HouseLocationResponseBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocationResponseBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseLocationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseLocationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocation"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locationTypeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "locationTypeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
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
