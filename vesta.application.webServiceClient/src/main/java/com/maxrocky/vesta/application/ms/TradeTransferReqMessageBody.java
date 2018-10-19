/**
 * TradeTransferReqMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class TradeTransferReqMessageBody  implements java.io.Serializable {
    private com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfo orderInfo;

    private com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyReceipt receipt;

    public TradeTransferReqMessageBody() {
    }

    public TradeTransferReqMessageBody(
           com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfo orderInfo,
           com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyReceipt receipt) {
           this.orderInfo = orderInfo;
           this.receipt = receipt;
    }


    /**
     * Gets the orderInfo value for this TradeTransferReqMessageBody.
     * 
     * @return orderInfo
     */
    public com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfo getOrderInfo() {
        return orderInfo;
    }


    /**
     * Sets the orderInfo value for this TradeTransferReqMessageBody.
     * 
     * @param orderInfo
     */
    public void setOrderInfo(com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }


    /**
     * Gets the receipt value for this TradeTransferReqMessageBody.
     * 
     * @return receipt
     */
    public com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyReceipt getReceipt() {
        return receipt;
    }


    /**
     * Sets the receipt value for this TradeTransferReqMessageBody.
     * 
     * @param receipt
     */
    public void setReceipt(com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyReceipt receipt) {
        this.receipt = receipt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeTransferReqMessageBody)) return false;
        TradeTransferReqMessageBody other = (TradeTransferReqMessageBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.orderInfo==null && other.getOrderInfo()==null) || 
             (this.orderInfo!=null &&
              this.orderInfo.equals(other.getOrderInfo()))) &&
            ((this.receipt==null && other.getReceipt()==null) || 
             (this.receipt!=null &&
              this.receipt.equals(other.getReceipt())));
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
        if (getOrderInfo() != null) {
            _hashCode += getOrderInfo().hashCode();
        }
        if (getReceipt() != null) {
            _hashCode += getReceipt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeTransferReqMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "OrderInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBodyOrderInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receipt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Receipt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBodyReceipt"));
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
