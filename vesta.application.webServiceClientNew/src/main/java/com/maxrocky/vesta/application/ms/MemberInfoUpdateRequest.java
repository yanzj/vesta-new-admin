/**
 * MemberInfoUpdateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

import com.maxrocky.vesta.application.model.ReqHeader;

public class MemberInfoUpdateRequest  implements java.io.Serializable {
    private MemberInfoUpdateReqBody reqBody;

    private ReqHeader reqHeader;

    public MemberInfoUpdateRequest() {
    }

    public MemberInfoUpdateRequest(
           MemberInfoUpdateReqBody reqBody,
           ReqHeader reqHeader) {
           this.reqBody = reqBody;
           this.reqHeader = reqHeader;
    }


    /**
     * Gets the reqBody value for this MemberInfoUpdateRequest.
     * 
     * @return reqBody
     */
    public MemberInfoUpdateReqBody getReqBody() {
        return reqBody;
    }


    /**
     * Sets the reqBody value for this MemberInfoUpdateRequest.
     * 
     * @param reqBody
     */
    public void setReqBody(MemberInfoUpdateReqBody reqBody) {
        this.reqBody = reqBody;
    }


    /**
     * Gets the reqHeader value for this MemberInfoUpdateRequest.
     * 
     * @return reqHeader
     */
    public ReqHeader getReqHeader() {
        return reqHeader;
    }


    /**
     * Sets the reqHeader value for this MemberInfoUpdateRequest.
     * 
     * @param reqHeader
     */
    public void setReqHeader(ReqHeader reqHeader) {
        this.reqHeader = reqHeader;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoUpdateRequest)) return false;
        MemberInfoUpdateRequest other = (MemberInfoUpdateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reqBody==null && other.getReqBody()==null) || 
             (this.reqBody!=null &&
              this.reqBody.equals(other.getReqBody()))) &&
            ((this.reqHeader==null && other.getReqHeader()==null) || 
             (this.reqHeader!=null &&
              this.reqHeader.equals(other.getReqHeader())));
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
        if (getReqBody() != null) {
            _hashCode += getReqBody().hashCode();
        }
        if (getReqHeader() != null) {
            _hashCode += getReqHeader().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoUpdateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reqBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ReqBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reqHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ReqHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ReqHeader"));
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
