/**
 * SynJobtitle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.user;

public class SynJobtitle  implements java.io.Serializable {
    private String in0;

    private String in1;

    public SynJobtitle() {
    }

    public SynJobtitle(
           String in0,
           String in1) {
           this.in0 = in0;
           this.in1 = in1;
    }


    /**
     * Gets the in0 value for this SynJobtitle.
     *
     * @return in0
     */
    public String getIn0() {
        return in0;
    }


    /**
     * Sets the in0 value for this SynJobtitle.
     *
     * @param in0
     */
    public void setIn0(String in0) {
        this.in0 = in0;
    }


    /**
     * Gets the in1 value for this SynJobtitle.
     *
     * @return in1
     */
    public String getIn1() {
        return in1;
    }


    /**
     * Sets the in1 value for this SynJobtitle.
     *
     * @param in1
     */
    public void setIn1(String in1) {
        this.in1 = in1;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SynJobtitle)) return false;
        SynJobtitle other = (SynJobtitle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.in0==null && other.getIn0()==null) ||
             (this.in0!=null &&
              this.in0.equals(other.getIn0()))) &&
            ((this.in1==null && other.getIn1()==null) ||
             (this.in1!=null &&
              this.in1.equals(other.getIn1())));
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
        if (getIn0() != null) {
            _hashCode += getIn0().hashCode();
        }
        if (getIn1() != null) {
            _hashCode += getIn1().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SynJobtitle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/services/HrmService", ">SynJobtitle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("in0");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/services/HrmService", "in0"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("in1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/services/HrmService", "in1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
