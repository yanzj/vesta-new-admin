/**
 * ErrorCodeEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class ErrorCodeEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ErrorCodeEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Sucess = "Sucess";
    public static final java.lang.String _Server_internal_error = "Server_internal_error";
    public static final java.lang.String _Connect_Error = "Connect_Error";
    public static final java.lang.String _NetWork_TimeOut = "NetWork_TimeOut";
    public static final java.lang.String _Header_Parameter_invalid = "Header_Parameter_invalid";
    public static final java.lang.String _Basic_authentication_failed = "Basic_authentication_failed";
    public static final java.lang.String _Invalid_SysID = "Invalid_SysID";
    public static final java.lang.String _Missing_authen_info = "Missing_authen_info";
    public static final java.lang.String _MessageBody_empty = "MessageBody_empty";
    public static final java.lang.String _Message_Parameter_Invalid = "Message_Parameter_Invalid";
    public static final ErrorCodeEnum Sucess = new ErrorCodeEnum(_Sucess);
    public static final ErrorCodeEnum Server_internal_error = new ErrorCodeEnum(_Server_internal_error);
    public static final ErrorCodeEnum Connect_Error = new ErrorCodeEnum(_Connect_Error);
    public static final ErrorCodeEnum NetWork_TimeOut = new ErrorCodeEnum(_NetWork_TimeOut);
    public static final ErrorCodeEnum Header_Parameter_invalid = new ErrorCodeEnum(_Header_Parameter_invalid);
    public static final ErrorCodeEnum Basic_authentication_failed = new ErrorCodeEnum(_Basic_authentication_failed);
    public static final ErrorCodeEnum Invalid_SysID = new ErrorCodeEnum(_Invalid_SysID);
    public static final ErrorCodeEnum Missing_authen_info = new ErrorCodeEnum(_Missing_authen_info);
    public static final ErrorCodeEnum MessageBody_empty = new ErrorCodeEnum(_MessageBody_empty);
    public static final ErrorCodeEnum Message_Parameter_Invalid = new ErrorCodeEnum(_Message_Parameter_Invalid);
    public java.lang.String getValue() { return _value_;}
    public static ErrorCodeEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ErrorCodeEnum enumeration = (ErrorCodeEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ErrorCodeEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ErrorCodeEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ErrorCodeEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
