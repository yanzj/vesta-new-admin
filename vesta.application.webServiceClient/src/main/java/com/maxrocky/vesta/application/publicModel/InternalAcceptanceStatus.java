/**
 * InternalAcceptanceStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

public class InternalAcceptanceStatus implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected InternalAcceptanceStatus(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _start = "start";
    public static final String _finish = "finish";
    public static final String _processing = "processing";
    public static final String _forceClose = "forceClose";
    public static final String _abandoned = "abandoned";
    public static final InternalAcceptanceStatus start = new InternalAcceptanceStatus(_start);
    public static final InternalAcceptanceStatus finish = new InternalAcceptanceStatus(_finish);
    public static final InternalAcceptanceStatus processing = new InternalAcceptanceStatus(_processing);
    public static final InternalAcceptanceStatus forceClose = new InternalAcceptanceStatus(_forceClose);
    public static final InternalAcceptanceStatus abandoned = new InternalAcceptanceStatus(_abandoned);
    public String getValue() { return _value_;}
    public static InternalAcceptanceStatus fromValue(String value)
          throws IllegalArgumentException {
        InternalAcceptanceStatus enumeration = (InternalAcceptanceStatus)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static InternalAcceptanceStatus fromString(String value)
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
        new org.apache.axis.description.TypeDesc(InternalAcceptanceStatus.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InternalAcceptanceStatus"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
