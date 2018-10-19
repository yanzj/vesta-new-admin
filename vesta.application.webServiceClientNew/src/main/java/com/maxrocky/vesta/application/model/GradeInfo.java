/**
 * GradeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class GradeInfo  implements java.io.Serializable {
    private java.lang.String businessSource;

    private java.lang.String memberLevel;

    public GradeInfo() {
    }

    public GradeInfo(
           java.lang.String businessSource,
           java.lang.String memberLevel) {
           this.businessSource = businessSource;
           this.memberLevel = memberLevel;
    }


    /**
     * Gets the businessSource value for this GradeInfo.
     * 
     * @return businessSource
     */
    public java.lang.String getBusinessSource() {
        return businessSource;
    }


    /**
     * Sets the businessSource value for this GradeInfo.
     * 
     * @param businessSource
     */
    public void setBusinessSource(java.lang.String businessSource) {
        this.businessSource = businessSource;
    }


    /**
     * Gets the memberLevel value for this GradeInfo.
     * 
     * @return memberLevel
     */
    public java.lang.String getMemberLevel() {
        return memberLevel;
    }


    /**
     * Sets the memberLevel value for this GradeInfo.
     * 
     * @param memberLevel
     */
    public void setMemberLevel(java.lang.String memberLevel) {
        this.memberLevel = memberLevel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GradeInfo)) return false;
        GradeInfo other = (GradeInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.businessSource==null && other.getBusinessSource()==null) || 
             (this.businessSource!=null &&
              this.businessSource.equals(other.getBusinessSource()))) &&
            ((this.memberLevel==null && other.getMemberLevel()==null) || 
             (this.memberLevel!=null &&
              this.memberLevel.equals(other.getMemberLevel())));
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
        if (getBusinessSource() != null) {
            _hashCode += getBusinessSource().hashCode();
        }
        if (getMemberLevel() != null) {
            _hashCode += getMemberLevel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GradeInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BusinessSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberLevel"));
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
