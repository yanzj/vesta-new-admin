/**
 * InternalAcceptance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;


public class InternalAcceptance implements java.io.Serializable {
    private AcceptanceStatus acceptanceStatus;

    private java.util.Calendar acceptancedate;

    private String houseCode;

    private String interdeliveryPla;

    public InternalAcceptance() {
    }

    public InternalAcceptance(
           AcceptanceStatus acceptanceStatus,
           java.util.Calendar acceptancedate,
           String houseCode,
           String interdeliveryPla) {
           this.acceptanceStatus = acceptanceStatus;
           this.acceptancedate = acceptancedate;
           this.houseCode = houseCode;
           this.interdeliveryPla = interdeliveryPla;
    }


    /**
     * Gets the acceptanceStatus value for this InternalAcceptance.
     * 
     * @return acceptanceStatus
     */
    public AcceptanceStatus getAcceptanceStatus() {
        return acceptanceStatus;
    }


    /**
     * Sets the acceptanceStatus value for this InternalAcceptance.
     * 
     * @param acceptanceStatus
     */
    public void setAcceptanceStatus(AcceptanceStatus acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
    }


    /**
     * Gets the acceptancedate value for this InternalAcceptance.
     * 
     * @return acceptancedate
     */
    public java.util.Calendar getAcceptancedate() {
        return acceptancedate;
    }


    /**
     * Sets the acceptancedate value for this InternalAcceptance.
     * 
     * @param acceptancedate
     */
    public void setAcceptancedate(java.util.Calendar acceptancedate) {
        this.acceptancedate = acceptancedate;
    }


    /**
     * Gets the houseCode value for this InternalAcceptance.
     * 
     * @return houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }


    /**
     * Sets the houseCode value for this InternalAcceptance.
     * 
     * @param houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }


    /**
     * Gets the interdeliveryPla value for this InternalAcceptance.
     * 
     * @return interdeliveryPla
     */
    public String getInterdeliveryPla() {
        return interdeliveryPla;
    }


    /**
     * Sets the interdeliveryPla value for this InternalAcceptance.
     * 
     * @param interdeliveryPla
     */
    public void setInterdeliveryPla(String interdeliveryPla) {
        this.interdeliveryPla = interdeliveryPla;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof InternalAcceptance)) return false;
        InternalAcceptance other = (InternalAcceptance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.acceptanceStatus==null && other.getAcceptanceStatus()==null) || 
             (this.acceptanceStatus!=null &&
              this.acceptanceStatus.equals(other.getAcceptanceStatus()))) &&
            ((this.acceptancedate==null && other.getAcceptancedate()==null) || 
             (this.acceptancedate!=null &&
              this.acceptancedate.equals(other.getAcceptancedate()))) &&
            ((this.houseCode==null && other.getHouseCode()==null) || 
             (this.houseCode!=null &&
              this.houseCode.equals(other.getHouseCode()))) &&
            ((this.interdeliveryPla==null && other.getInterdeliveryPla()==null) || 
             (this.interdeliveryPla!=null &&
              this.interdeliveryPla.equals(other.getInterdeliveryPla())));
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
        if (getAcceptanceStatus() != null) {
            _hashCode += getAcceptanceStatus().hashCode();
        }
        if (getAcceptancedate() != null) {
            _hashCode += getAcceptancedate().hashCode();
        }
        if (getHouseCode() != null) {
            _hashCode += getHouseCode().hashCode();
        }
        if (getInterdeliveryPla() != null) {
            _hashCode += getInterdeliveryPla().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InternalAcceptance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InternalAcceptance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acceptanceStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "acceptanceStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AcceptanceStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acceptancedate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "acceptancedate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("interdeliveryPla");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "interdeliveryPla"));
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
