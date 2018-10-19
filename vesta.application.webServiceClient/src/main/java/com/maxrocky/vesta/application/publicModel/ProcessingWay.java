/**
 * ProcessingWay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

public class ProcessingWay implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ProcessingWay(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _interior = "interior";
    public static final String _accountabilityUnit = "accountabilityUnit";
    public static final String _thirdParty = "thirdParty";
    public static final String _mutualProcess = "mutualProcess";
    public static final ProcessingWay interior = new ProcessingWay(_interior);
    public static final ProcessingWay accountabilityUnit = new ProcessingWay(_accountabilityUnit);
    public static final ProcessingWay thirdParty = new ProcessingWay(_thirdParty);
    public static final ProcessingWay mutualProcess = new ProcessingWay(_mutualProcess);
    public String getValue() { return _value_;}
    public static ProcessingWay fromValue(String value)
          throws IllegalArgumentException {
        ProcessingWay enumeration = (ProcessingWay)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static ProcessingWay fromString(String value)
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
        new org.apache.axis.description.TypeDesc(ProcessingWay.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ProcessingWay"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
