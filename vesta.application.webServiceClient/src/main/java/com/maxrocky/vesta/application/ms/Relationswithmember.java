/**
 * Relationswithmember.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class Relationswithmember implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Relationswithmember(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Father = "Father";
    public static final java.lang.String _Mother = "Mother";
    public static final java.lang.String _Son = "Son";
    public static final java.lang.String _Daughter = "Daughter";
    public static final java.lang.String _Spouse = "Spouse";
    public static final java.lang.String _Others = "Others";
    public static final java.lang.String _Renter = "Renter";
    public static final java.lang.String _LivingWith = "LivingWith";
    public static final Relationswithmember Father = new Relationswithmember(_Father);
    public static final Relationswithmember Mother = new Relationswithmember(_Mother);
    public static final Relationswithmember Son = new Relationswithmember(_Son);
    public static final Relationswithmember Daughter = new Relationswithmember(_Daughter);
    public static final Relationswithmember Spouse = new Relationswithmember(_Spouse);
    public static final Relationswithmember Others = new Relationswithmember(_Others);
    public static final Relationswithmember Renter = new Relationswithmember(_Renter);
    public static final Relationswithmember LivingWith = new Relationswithmember(_LivingWith);
    public java.lang.String getValue() { return _value_;}
    public static Relationswithmember fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Relationswithmember enumeration = (Relationswithmember)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Relationswithmember fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(Relationswithmember.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "relationswithmember"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
