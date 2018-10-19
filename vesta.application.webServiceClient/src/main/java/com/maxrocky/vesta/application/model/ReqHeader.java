/**
 * ReqHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;
public class ReqHeader  implements java.io.Serializable {
    private java.lang.String authorizationId;

    private java.lang.String authorizationPwd;

    private java.lang.String sysId;

    public ReqHeader() {
    }

    public ReqHeader(
           java.lang.String authorizationId,
           java.lang.String authorizationPwd,
           java.lang.String sysId) {
           this.authorizationId = authorizationId;
           this.authorizationPwd = authorizationPwd;
           this.sysId = sysId;
    }


    /**
     * Gets the authorizationId value for this ReqHeader.
     * 
     * @return authorizationId
     */
    public java.lang.String getAuthorizationId() {
        return authorizationId;
    }


    /**
     * Sets the authorizationId value for this ReqHeader.
     * 
     * @param authorizationId
     */
    public void setAuthorizationId(java.lang.String authorizationId) {
        this.authorizationId = authorizationId;
    }


    /**
     * Gets the authorizationPwd value for this ReqHeader.
     * 
     * @return authorizationPwd
     */
    public java.lang.String getAuthorizationPwd() {
        return authorizationPwd;
    }


    /**
     * Sets the authorizationPwd value for this ReqHeader.
     * 
     * @param authorizationPwd
     */
    public void setAuthorizationPwd(java.lang.String authorizationPwd) {
        this.authorizationPwd = authorizationPwd;
    }


    /**
     * Gets the sysId value for this ReqHeader.
     * 
     * @return sysId
     */
    public java.lang.String getSysId() {
        return sysId;
    }


    /**
     * Sets the sysId value for this ReqHeader.
     * 
     * @param sysId
     */
    public void setSysId(java.lang.String sysId) {
        this.sysId = sysId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqHeader)) return false;
        ReqHeader other = (ReqHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authorizationId==null && other.getAuthorizationId()==null) || 
             (this.authorizationId!=null &&
              this.authorizationId.equals(other.getAuthorizationId()))) &&
            ((this.authorizationPwd==null && other.getAuthorizationPwd()==null) || 
             (this.authorizationPwd!=null &&
              this.authorizationPwd.equals(other.getAuthorizationPwd()))) &&
            ((this.sysId==null && other.getSysId()==null) || 
             (this.sysId!=null &&
              this.sysId.equals(other.getSysId())));
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
        if (getAuthorizationId() != null) {
            _hashCode += getAuthorizationId().hashCode();
        }
        if (getAuthorizationPwd() != null) {
            _hashCode += getAuthorizationPwd().hashCode();
        }
        if (getSysId() != null) {
            _hashCode += getSysId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ReqHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AuthorizationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AuthorizationPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sysId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "SysId"));
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
