/**
 * MemberInfoQueryResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoQueryResponse  implements java.io.Serializable {
    private com.maxrocky.vesta.application.ms.MemberInfoResBody resBody;

    private com.maxrocky.vesta.application.ms.ResHeader resHeader;

    public MemberInfoQueryResponse() {
    }

    public MemberInfoQueryResponse(
           com.maxrocky.vesta.application.ms.MemberInfoResBody resBody,
           com.maxrocky.vesta.application.ms.ResHeader resHeader) {
           this.resBody = resBody;
           this.resHeader = resHeader;
    }


    /**
     * Gets the resBody value for this MemberInfoQueryResponse.
     * 
     * @return resBody
     */
    public com.maxrocky.vesta.application.ms.MemberInfoResBody getResBody() {
        return resBody;
    }


    /**
     * Sets the resBody value for this MemberInfoQueryResponse.
     * 
     * @param resBody
     */
    public void setResBody(com.maxrocky.vesta.application.ms.MemberInfoResBody resBody) {
        this.resBody = resBody;
    }


    /**
     * Gets the resHeader value for this MemberInfoQueryResponse.
     * 
     * @return resHeader
     */
    public com.maxrocky.vesta.application.ms.ResHeader getResHeader() {
        return resHeader;
    }


    /**
     * Sets the resHeader value for this MemberInfoQueryResponse.
     * 
     * @param resHeader
     */
    public void setResHeader(com.maxrocky.vesta.application.ms.ResHeader resHeader) {
        this.resHeader = resHeader;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoQueryResponse)) return false;
        MemberInfoQueryResponse other = (MemberInfoQueryResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resBody==null && other.getResBody()==null) || 
             (this.resBody!=null &&
              this.resBody.equals(other.getResBody()))) &&
            ((this.resHeader==null && other.getResHeader()==null) || 
             (this.resHeader!=null &&
              this.resHeader.equals(other.getResHeader())));
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
        if (getResBody() != null) {
            _hashCode += getResBody().hashCode();
        }
        if (getResHeader() != null) {
            _hashCode += getResHeader().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoQueryResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoQueryResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ResBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoResBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ResHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ResHeader"));
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
