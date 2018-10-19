/**
 * PointsInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;
public class PointsInfo  implements java.io.Serializable {
    private java.lang.String businessSource;

    private java.lang.String effectiveFreezeBalance;

    private java.lang.String effectivePoints;

    public PointsInfo() {
    }

    public PointsInfo(
           java.lang.String businessSource,
           java.lang.String effectiveFreezeBalance,
           java.lang.String effectivePoints) {
           this.businessSource = businessSource;
           this.effectiveFreezeBalance = effectiveFreezeBalance;
           this.effectivePoints = effectivePoints;
    }


    /**
     * Gets the businessSource value for this PointsInfo.
     * 
     * @return businessSource
     */
    public java.lang.String getBusinessSource() {
        return businessSource;
    }


    /**
     * Sets the businessSource value for this PointsInfo.
     * 
     * @param businessSource
     */
    public void setBusinessSource(java.lang.String businessSource) {
        this.businessSource = businessSource;
    }


    /**
     * Gets the effectiveFreezeBalance value for this PointsInfo.
     * 
     * @return effectiveFreezeBalance
     */
    public java.lang.String getEffectiveFreezeBalance() {
        return effectiveFreezeBalance;
    }


    /**
     * Sets the effectiveFreezeBalance value for this PointsInfo.
     * 
     * @param effectiveFreezeBalance
     */
    public void setEffectiveFreezeBalance(java.lang.String effectiveFreezeBalance) {
        this.effectiveFreezeBalance = effectiveFreezeBalance;
    }


    /**
     * Gets the effectivePoints value for this PointsInfo.
     * 
     * @return effectivePoints
     */
    public java.lang.String getEffectivePoints() {
        return effectivePoints;
    }


    /**
     * Sets the effectivePoints value for this PointsInfo.
     * 
     * @param effectivePoints
     */
    public void setEffectivePoints(java.lang.String effectivePoints) {
        this.effectivePoints = effectivePoints;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PointsInfo)) return false;
        PointsInfo other = (PointsInfo) obj;
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
            ((this.effectiveFreezeBalance==null && other.getEffectiveFreezeBalance()==null) || 
             (this.effectiveFreezeBalance!=null &&
              this.effectiveFreezeBalance.equals(other.getEffectiveFreezeBalance()))) &&
            ((this.effectivePoints==null && other.getEffectivePoints()==null) || 
             (this.effectivePoints!=null &&
              this.effectivePoints.equals(other.getEffectivePoints())));
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
        if (getEffectiveFreezeBalance() != null) {
            _hashCode += getEffectiveFreezeBalance().hashCode();
        }
        if (getEffectivePoints() != null) {
            _hashCode += getEffectivePoints().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PointsInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PointsInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BusinessSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectiveFreezeBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "EffectiveFreezeBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectivePoints");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "EffectivePoints"));
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
