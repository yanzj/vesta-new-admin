/**
 * ReceivedDetailRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.received;

public class ReceivedDetailRequest  implements java.io.Serializable {
    private Integer isPeriod;

    private String receivableId;

    public ReceivedDetailRequest() {
    }

    public ReceivedDetailRequest(
           Integer isPeriod,
           String receivableId) {
           this.isPeriod = isPeriod;
           this.receivableId = receivableId;
    }


    /**
     * Gets the isPeriod value for this ReceivedDetailRequest.
     *
     * @return isPeriod
     */
    public Integer getIsPeriod() {
        return isPeriod;
    }


    /**
     * Sets the isPeriod value for this ReceivedDetailRequest.
     *
     * @param isPeriod
     */
    public void setIsPeriod(Integer isPeriod) {
        this.isPeriod = isPeriod;
    }


    /**
     * Gets the receivableId value for this ReceivedDetailRequest.
     *
     * @return receivableId
     */
    public String getReceivableId() {
        return receivableId;
    }


    /**
     * Sets the receivableId value for this ReceivedDetailRequest.
     *
     * @param receivableId
     */
    public void setReceivableId(String receivableId) {
        this.receivableId = receivableId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ReceivedDetailRequest)) return false;
        ReceivedDetailRequest other = (ReceivedDetailRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.isPeriod==null && other.getIsPeriod()==null) ||
             (this.isPeriod!=null &&
              this.isPeriod.equals(other.getIsPeriod()))) &&
            ((this.receivableId==null && other.getReceivableId()==null) ||
             (this.receivableId!=null &&
              this.receivableId.equals(other.getReceivableId())));
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
        if (getIsPeriod() != null) {
            _hashCode += getIsPeriod().hashCode();
        }
        if (getReceivableId() != null) {
            _hashCode += getReceivableId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReceivedDetailRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivedDetailRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "IsPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivableId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivableId"));
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
