/**
 * TradeTransferReqMessageBodyOrderInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class TradeTransferReqMessageBodyOrderInfo  implements java.io.Serializable {
    private com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderHeader orderHeader;

    private com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderItems[] orderItems;

    public TradeTransferReqMessageBodyOrderInfo() {
    }

    public TradeTransferReqMessageBodyOrderInfo(
           com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderHeader orderHeader,
           com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderItems[] orderItems) {
           this.orderHeader = orderHeader;
           this.orderItems = orderItems;
    }


    /**
     * Gets the orderHeader value for this TradeTransferReqMessageBodyOrderInfo.
     * 
     * @return orderHeader
     */
    public com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderHeader getOrderHeader() {
        return orderHeader;
    }


    /**
     * Sets the orderHeader value for this TradeTransferReqMessageBodyOrderInfo.
     * 
     * @param orderHeader
     */
    public void setOrderHeader(com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }


    /**
     * Gets the orderItems value for this TradeTransferReqMessageBodyOrderInfo.
     * 
     * @return orderItems
     */
    public com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderItems[] getOrderItems() {
        return orderItems;
    }


    /**
     * Sets the orderItems value for this TradeTransferReqMessageBodyOrderInfo.
     * 
     * @param orderItems
     */
    public void setOrderItems(com.maxrocky.vesta.application.ms.TradeTransferReqMessageBodyOrderInfoOrderItems[] orderItems) {
        this.orderItems = orderItems;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeTransferReqMessageBodyOrderInfo)) return false;
        TradeTransferReqMessageBodyOrderInfo other = (TradeTransferReqMessageBodyOrderInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.orderHeader==null && other.getOrderHeader()==null) || 
             (this.orderHeader!=null &&
              this.orderHeader.equals(other.getOrderHeader()))) &&
            ((this.orderItems==null && other.getOrderItems()==null) || 
             (this.orderItems!=null &&
              java.util.Arrays.equals(this.orderItems, other.getOrderItems())));
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
        if (getOrderHeader() != null) {
            _hashCode += getOrderHeader().hashCode();
        }
        if (getOrderItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrderItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrderItems(), i);
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
        new org.apache.axis.description.TypeDesc(TradeTransferReqMessageBodyOrderInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBodyOrderInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "OrderHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBodyOrderInfoOrderHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "OrderItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBodyOrderInfoOrderItems"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeTransferReqMessageBodyOrderInfoOrderItems"));
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
