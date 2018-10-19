/**
 * SupplierType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.supplier.model;

public class SupplierType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SupplierType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _civilMainContractor = "civilMainContractor";
    public static final String _installMainContractor = "installMainContractor";
    public static final String _decorationEngineering = "decorationEngineering";
    public static final String _weakElectricityEngineering = "weakElectricityEngineering";
    public static final String _coatingEngineering = "coatingEngineering";
    public static final String _steelStructureEngineering = "steelStructureEngineering";
    public static final SupplierType civilMainContractor = new SupplierType(_civilMainContractor);
    public static final SupplierType installMainContractor = new SupplierType(_installMainContractor);
    public static final SupplierType decorationEngineering = new SupplierType(_decorationEngineering);
    public static final SupplierType weakElectricityEngineering = new SupplierType(_weakElectricityEngineering);
    public static final SupplierType coatingEngineering = new SupplierType(_coatingEngineering);
    public static final SupplierType steelStructureEngineering = new SupplierType(_steelStructureEngineering);
    public String getValue() { return _value_;}
    public static SupplierType fromValue(String value)
          throws IllegalArgumentException {
        SupplierType enumeration = (SupplierType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SupplierType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SupplierType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
