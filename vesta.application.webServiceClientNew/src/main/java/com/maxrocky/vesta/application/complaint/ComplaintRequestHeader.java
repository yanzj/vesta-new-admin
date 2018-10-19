/**
 * ComplaintRequestHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.complaint;

import com.maxrocky.vesta.application.ws.SourceIdentifier;

public class ComplaintRequestHeader  implements java.io.Serializable {
    private SourceIdentifier sourceIdentifier;

    public ComplaintRequestHeader() {
    }

    public ComplaintRequestHeader(
           SourceIdentifier sourceIdentifier) {
           this.sourceIdentifier = sourceIdentifier;
    }


    /**
     * Gets the sourceIdentifier value for this ComplaintRequestHeader.
     * 
     * @return sourceIdentifier
     */
    public SourceIdentifier getSourceIdentifier() {
        return sourceIdentifier;
    }


    /**
     * Sets the sourceIdentifier value for this ComplaintRequestHeader.
     * 
     * @param sourceIdentifier
     */
    public void setSourceIdentifier(SourceIdentifier sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ComplaintRequestHeader)) return false;
        ComplaintRequestHeader other = (ComplaintRequestHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.sourceIdentifier==null && other.getSourceIdentifier()==null) ||
             (this.sourceIdentifier!=null &&
              this.sourceIdentifier.equals(other.getSourceIdentifier())));
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
        if (getSourceIdentifier() != null) {
            _hashCode += getSourceIdentifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComplaintRequestHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintRequestHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "sourceIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SourceIdentifier"));
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
