/**
 * Cartype.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class Cartype implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Cartype(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Saloon = "Saloon";
    public static final java.lang.String _SUV = "SUV";
    public static final java.lang.String _MPV = "MPV";
    public static final java.lang.String _Sportscar = "Sportscar";
    public static final java.lang.String _Motorbike = "Motorbike";
    public static final java.lang.String _Others = "Others";
    public static final Cartype Saloon = new Cartype(_Saloon);
    public static final Cartype SUV = new Cartype(_SUV);
    public static final Cartype MPV = new Cartype(_MPV);
    public static final Cartype Sportscar = new Cartype(_Sportscar);
    public static final Cartype Motorbike = new Cartype(_Motorbike);
    public static final Cartype Others = new Cartype(_Others);
    public java.lang.String getValue() { return _value_;}
    public static Cartype fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Cartype enumeration = (Cartype)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Cartype fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(Cartype.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "cartype"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
