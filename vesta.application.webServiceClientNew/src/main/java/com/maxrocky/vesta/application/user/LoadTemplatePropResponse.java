/**
 * LoadTemplatePropResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.user;

import com.maxrocky.vesta.application.model.AnyType2AnyTypeMapEntry;

public class LoadTemplatePropResponse  implements java.io.Serializable {
    private AnyType2AnyTypeMapEntry[] out;

    public LoadTemplatePropResponse() {
    }

    public LoadTemplatePropResponse(
           AnyType2AnyTypeMapEntry[] out) {
           this.out = out;
    }


    /**
     * Gets the out value for this LoadTemplatePropResponse.
     * 
     * @return out
     */
    public AnyType2AnyTypeMapEntry[] getOut() {
        return out;
    }


    /**
     * Sets the out value for this LoadTemplatePropResponse.
     * 
     * @param out
     */
    public void setOut(AnyType2AnyTypeMapEntry[] out) {
        this.out = out;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LoadTemplatePropResponse)) return false;
        LoadTemplatePropResponse other = (LoadTemplatePropResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.out==null && other.getOut()==null) ||
             (this.out!=null &&
              java.util.Arrays.equals(this.out, other.getOut())));
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
        if (getOut() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOut());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getOut(), i);
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
        new org.apache.axis.description.TypeDesc(LoadTemplatePropResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/services/HrmService", ">LoadTemplatePropResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("out");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/services/HrmService", "out"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/services/HrmService", ">anyType2anyTypeMap>entry"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://localhost/services/HrmService", "entry"));
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
