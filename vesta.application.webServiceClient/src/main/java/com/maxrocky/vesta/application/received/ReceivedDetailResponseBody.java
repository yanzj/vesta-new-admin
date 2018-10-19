/**
 * ReceivedDetailResponseBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.received;

public class ReceivedDetailResponseBody  implements java.io.Serializable {
    private ReceivedDetailResponseData[] receivedList;

    public ReceivedDetailResponseBody() {
    }

    public ReceivedDetailResponseBody(
           ReceivedDetailResponseData[] receivedList) {
           this.receivedList = receivedList;
    }


    /**
     * Gets the receivedList value for this ReceivedDetailResponseBody.
     * 
     * @return receivedList
     */
    public ReceivedDetailResponseData[] getReceivedList() {
        return receivedList;
    }


    /**
     * Sets the receivedList value for this ReceivedDetailResponseBody.
     * 
     * @param receivedList
     */
    public void setReceivedList(ReceivedDetailResponseData[] receivedList) {
        this.receivedList = receivedList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ReceivedDetailResponseBody)) return false;
        ReceivedDetailResponseBody other = (ReceivedDetailResponseBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.receivedList==null && other.getReceivedList()==null) || 
             (this.receivedList!=null &&
              java.util.Arrays.equals(this.receivedList, other.getReceivedList())));
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
        if (getReceivedList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReceivedList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getReceivedList(), i);
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
        new org.apache.axis.description.TypeDesc(ReceivedDetailResponseBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivedDetailResponseBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivedList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivedDetailResponseData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivedDetailResponseData"));
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
