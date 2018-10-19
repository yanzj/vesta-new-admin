/**
 * AppealRequestHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.appOwnerAppeal;

public class AppealRequestHeader  implements java.io.Serializable {
    private String authorizationId;

    private String authorizationPwd;

    private String sysId;

    public AppealRequestHeader() {
    }

    public AppealRequestHeader(
           String authorizationId,
           String authorizationPwd,
           String sysId) {
           this.authorizationId = authorizationId;
           this.authorizationPwd = authorizationPwd;
           this.sysId = sysId;
    }


    /**
     * Gets the authorizationId value for this AppealRequestHeader.
     *
     * @return authorizationId
     */
    public String getAuthorizationId() {
        return authorizationId;
    }


    /**
     * Sets the authorizationId value for this AppealRequestHeader.
     *
     * @param authorizationId
     */
    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }


    /**
     * Gets the authorizationPwd value for this AppealRequestHeader.
     *
     * @return authorizationPwd
     */
    public String getAuthorizationPwd() {
        return authorizationPwd;
    }


    /**
     * Sets the authorizationPwd value for this AppealRequestHeader.
     *
     * @param authorizationPwd
     */
    public void setAuthorizationPwd(String authorizationPwd) {
        this.authorizationPwd = authorizationPwd;
    }


    /**
     * Gets the sysId value for this AppealRequestHeader.
     *
     * @return sysId
     */
    public String getSysId() {
        return sysId;
    }


    /**
     * Sets the sysId value for this AppealRequestHeader.
     *
     * @param sysId
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof AppealRequestHeader)) return false;
        AppealRequestHeader other = (AppealRequestHeader) obj;
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
        new org.apache.axis.description.TypeDesc(AppealRequestHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealRequestHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "authorizationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "authorizationPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sysId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "sysId"));
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
