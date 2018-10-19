/**
 * Quality.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

public class Quality implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Quality(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _highlySatisfied = "highlySatisfied";
    public static final String _satisfied = "satisfied";
    public static final String _qualified = "qualified";
    public static final String _dissatisfied = "dissatisfied";
    public static final String _highlyDissatisfied = "highlyDissatisfied";
    public static final Quality highlySatisfied = new Quality(_highlySatisfied);
    public static final Quality satisfied = new Quality(_satisfied);
    public static final Quality qualified = new Quality(_qualified);
    public static final Quality dissatisfied = new Quality(_dissatisfied);
    public static final Quality highlyDissatisfied = new Quality(_highlyDissatisfied);
    public String getValue() { return _value_;}
    public static Quality fromValue(String value)
          throws IllegalArgumentException {
        Quality enumeration = (Quality)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static Quality fromString(String value)
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
        new org.apache.axis.description.TypeDesc(Quality.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Quality"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
