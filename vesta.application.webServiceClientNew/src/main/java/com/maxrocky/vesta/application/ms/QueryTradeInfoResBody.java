/**
 * QueryTradeInfoResBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class QueryTradeInfoResBody  implements java.io.Serializable {
    private java.lang.String correlationID;

    private QueryTradeInfoResMessageBody[] messageBody;

    public QueryTradeInfoResBody() {
    }

    public QueryTradeInfoResBody(
           java.lang.String correlationID,
           QueryTradeInfoResMessageBody[] messageBody) {
           this.correlationID = correlationID;
           this.messageBody = messageBody;
    }


    /**
     * Gets the correlationID value for this QueryTradeInfoResBody.
     * 
     * @return correlationID
     */
    public java.lang.String getCorrelationID() {
        return correlationID;
    }


    /**
     * Sets the correlationID value for this QueryTradeInfoResBody.
     * 
     * @param correlationID
     */
    public void setCorrelationID(java.lang.String correlationID) {
        this.correlationID = correlationID;
    }


    /**
     * Gets the messageBody value for this QueryTradeInfoResBody.
     * 
     * @return messageBody
     */
    public QueryTradeInfoResMessageBody[] getMessageBody() {
        return messageBody;
    }


    /**
     * Sets the messageBody value for this QueryTradeInfoResBody.
     * 
     * @param messageBody
     */
    public void setMessageBody(QueryTradeInfoResMessageBody[] messageBody) {
        this.messageBody = messageBody;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryTradeInfoResBody)) return false;
        QueryTradeInfoResBody other = (QueryTradeInfoResBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.correlationID==null && other.getCorrelationID()==null) || 
             (this.correlationID!=null &&
              this.correlationID.equals(other.getCorrelationID()))) &&
            ((this.messageBody==null && other.getMessageBody()==null) || 
             (this.messageBody!=null &&
              java.util.Arrays.equals(this.messageBody, other.getMessageBody())));
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
        if (getCorrelationID() != null) {
            _hashCode += getCorrelationID().hashCode();
        }
        if (getMessageBody() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessageBody());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessageBody(), i);
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
        new org.apache.axis.description.TypeDesc(QueryTradeInfoResBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "QueryTradeInfoResBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CorrelationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MessageBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "QueryTradeInfoResMessageBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "QueryTradeInfoResMessageBody"));
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
