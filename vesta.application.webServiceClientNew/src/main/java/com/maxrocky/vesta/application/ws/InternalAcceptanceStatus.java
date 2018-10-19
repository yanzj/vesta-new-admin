/**
 * InternalAcceptanceStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ws;

public class InternalAcceptanceStatus implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected InternalAcceptanceStatus(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _start = "start";
    public static final java.lang.String _finish = "finish";
    public static final java.lang.String _processing = "processing";
    public static final java.lang.String _forceClose = "forceClose";
    public static final java.lang.String _abandoned = "abandoned";
    public static final InternalAcceptanceStatus start = new InternalAcceptanceStatus(_start);
    public static final InternalAcceptanceStatus finish = new InternalAcceptanceStatus(_finish);
    public static final InternalAcceptanceStatus processing = new InternalAcceptanceStatus(_processing);
    public static final InternalAcceptanceStatus forceClose = new InternalAcceptanceStatus(_forceClose);
    public static final InternalAcceptanceStatus abandoned = new InternalAcceptanceStatus(_abandoned);
    public java.lang.String getValue() { return _value_;}
    public static InternalAcceptanceStatus fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        InternalAcceptanceStatus enumeration = (InternalAcceptanceStatus)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static InternalAcceptanceStatus fromString(java.lang.String value)
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
