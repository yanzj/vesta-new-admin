/**
 * RepairMode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

import com.maxrocky.vesta.application.ws.WorkTime;

public class RepairMode  implements java.io.Serializable {
    private String label;

    private String value;

    private WorkTime[] workTimeList;

    public RepairMode() {
    }

    public RepairMode(
           String label,
           String value,
           WorkTime[] workTimeList) {
           this.label = label;
           this.value = value;
           this.workTimeList = workTimeList;
    }


    /**
     * Gets the label value for this RepairMode.
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this RepairMode.
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }


    /**
     * Gets the value value for this RepairMode.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }


    /**
     * Sets the value value for this RepairMode.
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }


    /**
     * Gets the workTimeList value for this RepairMode.
     *
     * @return workTimeList
     */
    public WorkTime[] getWorkTimeList() {
        return workTimeList;
    }


    /**
     * Sets the workTimeList value for this RepairMode.
     *
     * @param workTimeList
     */
    public void setWorkTimeList(WorkTime[] workTimeList) {
        this.workTimeList = workTimeList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RepairMode)) return false;
        RepairMode other = (RepairMode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.label==null && other.getLabel()==null) ||
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.value==null && other.getValue()==null) ||
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.workTimeList==null && other.getWorkTimeList()==null) ||
             (this.workTimeList!=null &&
              java.util.Arrays.equals(this.workTimeList, other.getWorkTimeList())));
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
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getWorkTimeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWorkTimeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getWorkTimeList(), i);
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
        new org.apache.axis.description.TypeDesc(RepairMode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairMode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workTimeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "workTimeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "WorkTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "WorkTime"));
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
