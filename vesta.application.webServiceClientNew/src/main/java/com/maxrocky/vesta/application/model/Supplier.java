/**
 * Supplier.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class Supplier  implements java.io.Serializable {
    private java.util.Calendar modifiedOn;

    private java.lang.String name;

    private java.lang.String supplierId;

    private com.maxrocky.vesta.application.ws.SupplierType type;

    public Supplier() {
    }

    public Supplier(
           java.util.Calendar modifiedOn,
           java.lang.String name,
           java.lang.String supplierId,
           com.maxrocky.vesta.application.ws.SupplierType type) {
           this.modifiedOn = modifiedOn;
           this.name = name;
           this.supplierId = supplierId;
           this.type = type;
    }


    /**
     * Gets the modifiedOn value for this Supplier.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this Supplier.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the name value for this Supplier.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Supplier.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the supplierId value for this Supplier.
     * 
     * @return supplierId
     */
    public java.lang.String getSupplierId() {
        return supplierId;
    }


    /**
     * Sets the supplierId value for this Supplier.
     * 
     * @param supplierId
     */
    public void setSupplierId(java.lang.String supplierId) {
        this.supplierId = supplierId;
    }


    /**
     * Gets the type value for this Supplier.
     * 
     * @return type
     */
    public com.maxrocky.vesta.application.ws.SupplierType getType() {
        return type;
    }


    /**
     * Sets the type value for this Supplier.
     * 
     * @param type
     */
    public void setType(com.maxrocky.vesta.application.ws.SupplierType type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Supplier)) return false;
        Supplier other = (Supplier) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.supplierId==null && other.getSupplierId()==null) || 
             (this.supplierId!=null &&
              this.supplierId.equals(other.getSupplierId()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getModifiedOn() != null) {
            _hashCode += getModifiedOn().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSupplierId() != null) {
            _hashCode += getSupplierId().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Supplier.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Supplier"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedOn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "modifiedOn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supplierId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "supplierId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
