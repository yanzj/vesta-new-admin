/**
 * ConstructionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class ConstructionType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ConstructionType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _brick = "brick";
    public static final String _steelConcrete = "steelConcrete";
    public static final String _steel = "steel";
    public static final String _steelAndOthers = "steelAndOthers";
    public static final String _others = "others";
    public static final String _unknown = "unknown";
    public static final ConstructionType brick = new ConstructionType(_brick);
    public static final ConstructionType steelConcrete = new ConstructionType(_steelConcrete);
    public static final ConstructionType steel = new ConstructionType(_steel);
    public static final ConstructionType steelAndOthers = new ConstructionType(_steelAndOthers);
    public static final ConstructionType others = new ConstructionType(_others);
    public static final ConstructionType unknown = new ConstructionType(_unknown);
    public String getValue() { return _value_;}
    public static ConstructionType fromValue(String value)
          throws IllegalArgumentException {
        ConstructionType enumeration = (ConstructionType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static ConstructionType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(ConstructionType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ConstructionType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
