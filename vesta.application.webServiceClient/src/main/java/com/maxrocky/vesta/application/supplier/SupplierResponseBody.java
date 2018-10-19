/**
 * SupplierResponseBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.supplier;

import com.maxrocky.vesta.application.publicModel.AssociateSupplier;
import com.maxrocky.vesta.application.supplier.model.Supplier;

public class SupplierResponseBody  implements java.io.Serializable {
    private AssociateSupplier[] associateSupplierList;

    private Supplier[] supplierList;

    public SupplierResponseBody() {
    }

    public SupplierResponseBody(
           AssociateSupplier[] associateSupplierList,
           Supplier[] supplierList) {
           this.associateSupplierList = associateSupplierList;
           this.supplierList = supplierList;
    }


    /**
     * Gets the associateSupplierList value for this SupplierResponseBody.
     * 
     * @return associateSupplierList
     */
    public AssociateSupplier[] getAssociateSupplierList() {
        return associateSupplierList;
    }


    /**
     * Sets the associateSupplierList value for this SupplierResponseBody.
     * 
     * @param associateSupplierList
     */
    public void setAssociateSupplierList(AssociateSupplier[] associateSupplierList) {
        this.associateSupplierList = associateSupplierList;
    }


    /**
     * Gets the supplierList value for this SupplierResponseBody.
     * 
     * @return supplierList
     */
    public Supplier[] getSupplierList() {
        return supplierList;
    }


    /**
     * Sets the supplierList value for this SupplierResponseBody.
     * 
     * @param supplierList
     */
    public void setSupplierList(Supplier[] supplierList) {
        this.supplierList = supplierList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SupplierResponseBody)) return false;
        SupplierResponseBody other = (SupplierResponseBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.associateSupplierList==null && other.getAssociateSupplierList()==null) || 
             (this.associateSupplierList!=null &&
              java.util.Arrays.equals(this.associateSupplierList, other.getAssociateSupplierList()))) &&
            ((this.supplierList==null && other.getSupplierList()==null) || 
             (this.supplierList!=null &&
              java.util.Arrays.equals(this.supplierList, other.getSupplierList())));
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
        if (getAssociateSupplierList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssociateSupplierList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getAssociateSupplierList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSupplierList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSupplierList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSupplierList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SupplierResponseBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierResponseBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associateSupplierList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "associateSupplierList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AssociateSupplier"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AssociateSupplier"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supplierList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "supplierList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Supplier"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Supplier"));
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
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
