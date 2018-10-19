/**
 * Edulevel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class Edulevel implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Edulevel(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _JuniorCollegeAndBelow = "JuniorCollegeAndBelow";
    public static final java.lang.String _Unergraduate = "Unergraduate";
    public static final java.lang.String _Postgraduate = "Postgraduate";
    public static final java.lang.String _Overseas = "Overseas";
    public static final java.lang.String _Others = "Others";
    public static final Edulevel JuniorCollegeAndBelow = new Edulevel(_JuniorCollegeAndBelow);
    public static final Edulevel Unergraduate = new Edulevel(_Unergraduate);
    public static final Edulevel Postgraduate = new Edulevel(_Postgraduate);
    public static final Edulevel Overseas = new Edulevel(_Overseas);
    public static final Edulevel Others = new Edulevel(_Others);
    public java.lang.String getValue() { return _value_;}
    public static Edulevel fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Edulevel enumeration = (Edulevel)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Edulevel fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Edulevel.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "edulevel"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
