/**
 * ComplaintWay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.complaint;

public class ComplaintWay implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ComplaintWay(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _callCenter = "callCenter";
    public static final String _website = "website";
    public static final String _receptionist = "receptionist";
    public static final String _wechat = "wechat";
    public static final String _propertyDocuments = "propertyDocuments";
    public static final String _App = "App";
    public static final String _propertywechat = "propertywechat";
    public static final ComplaintWay callCenter = new ComplaintWay(_callCenter);
    public static final ComplaintWay website = new ComplaintWay(_website);
    public static final ComplaintWay receptionist = new ComplaintWay(_receptionist);
    public static final ComplaintWay wechat = new ComplaintWay(_wechat);
    public static final ComplaintWay propertyDocuments = new ComplaintWay(_propertyDocuments);
    public static final ComplaintWay App = new ComplaintWay(_App);
    public static final ComplaintWay propertywechat = new ComplaintWay(_propertywechat);
    public String getValue() { return _value_;}
    public static ComplaintWay fromValue(String value)
          throws IllegalArgumentException {
        ComplaintWay enumeration = (ComplaintWay)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static ComplaintWay fromString(String value)
          throws IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public String toString() { return _value_;}
    public Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComplaintWay.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintWay"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
