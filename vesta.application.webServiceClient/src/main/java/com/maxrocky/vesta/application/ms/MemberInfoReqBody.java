/**
 * MemberInfoReqBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoReqBody  implements java.io.Serializable {
    private java.lang.String messageId;

    private com.maxrocky.vesta.application.ms.MemberInfoRequestMessageBody messagebody;

    private java.lang.String timeStamp;

    public MemberInfoReqBody() {
    }

    public MemberInfoReqBody(
           java.lang.String messageId,
           com.maxrocky.vesta.application.ms.MemberInfoRequestMessageBody messagebody,
           java.lang.String timeStamp) {
           this.messageId = messageId;
           this.messagebody = messagebody;
           this.timeStamp = timeStamp;
    }


    /**
     * Gets the messageId value for this MemberInfoReqBody.
     * 
     * @return messageId
     */
    public java.lang.String getMessageId() {
        return messageId;
    }


    /**
     * Sets the messageId value for this MemberInfoReqBody.
     * 
     * @param messageId
     */
    public void setMessageId(java.lang.String messageId) {
        this.messageId = messageId;
    }


    /**
     * Gets the messagebody value for this MemberInfoReqBody.
     * 
     * @return messagebody
     */
    public com.maxrocky.vesta.application.ms.MemberInfoRequestMessageBody getMessagebody() {
        return messagebody;
    }


    /**
     * Sets the messagebody value for this MemberInfoReqBody.
     * 
     * @param messagebody
     */
    public void setMessagebody(com.maxrocky.vesta.application.ms.MemberInfoRequestMessageBody messagebody) {
        this.messagebody = messagebody;
    }


    /**
     * Gets the timeStamp value for this MemberInfoReqBody.
     * 
     * @return timeStamp
     */
    public java.lang.String getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this MemberInfoReqBody.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(java.lang.String timeStamp) {
        this.timeStamp = timeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoReqBody)) return false;
        MemberInfoReqBody other = (MemberInfoReqBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageId==null && other.getMessageId()==null) || 
             (this.messageId!=null &&
              this.messageId.equals(other.getMessageId()))) &&
            ((this.messagebody==null && other.getMessagebody()==null) || 
             (this.messagebody!=null &&
              this.messagebody.equals(other.getMessagebody()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp())));
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
        if (getMessageId() != null) {
            _hashCode += getMessageId().hashCode();
        }
        if (getMessagebody() != null) {
            _hashCode += getMessagebody().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoReqBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoReqBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MessageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messagebody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Messagebody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoRequestMessageBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TimeStamp"));
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
