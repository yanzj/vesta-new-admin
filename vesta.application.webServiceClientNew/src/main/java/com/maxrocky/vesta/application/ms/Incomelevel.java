/**
 * Incomelevel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class Incomelevel implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Incomelevel(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _lt2k = "lt2k";
    public static final java.lang.String _F2k_5k = "F2k_5k";
    public static final java.lang.String _F5k_8k = "F5k_8k";
    public static final java.lang.String _F8k_12k = "F8k_12k";
    public static final java.lang.String _F12k_20k = "F12k_20k";
    public static final java.lang.String _gt20k = "gt20k";
    public static final Incomelevel lt2k = new Incomelevel(_lt2k);
    public static final Incomelevel F2k_5k = new Incomelevel(_F2k_5k);
    public static final Incomelevel F5k_8k = new Incomelevel(_F5k_8k);
    public static final Incomelevel F8k_12k = new Incomelevel(_F8k_12k);
    public static final Incomelevel F12k_20k = new Incomelevel(_F12k_20k);
    public static final Incomelevel gt20k = new Incomelevel(_gt20k);
    public java.lang.String getValue() { return _value_;}
    public static Incomelevel fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Incomelevel enumeration = (Incomelevel)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Incomelevel fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(Incomelevel.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "incomelevel"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
