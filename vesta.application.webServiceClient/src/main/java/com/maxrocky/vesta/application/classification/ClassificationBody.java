/**
 * ClassificationBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.classification;

import com.maxrocky.vesta.application.classification.model.Classification;

public class ClassificationBody  implements java.io.Serializable {
    private Classification[] describeList;

    private Classification[] firstClassificationList;

    private Classification[] repairModeList;

    private Classification[] secondClassificationList;

    private Classification[] thirdClassificationList;

    private Classification[] workTimeList;

    public ClassificationBody() {
    }

    public ClassificationBody(
           Classification[] describeList,
           Classification[] firstClassificationList,
           Classification[] repairModeList,
           Classification[] secondClassificationList,
           Classification[] thirdClassificationList,
           Classification[] workTimeList) {
           this.describeList = describeList;
           this.firstClassificationList = firstClassificationList;
           this.repairModeList = repairModeList;
           this.secondClassificationList = secondClassificationList;
           this.thirdClassificationList = thirdClassificationList;
           this.workTimeList = workTimeList;
    }


    /**
     * Gets the describeList value for this ClassificationBody.
     * 
     * @return describeList
     */
    public Classification[] getDescribeList() {
        return describeList;
    }


    /**
     * Sets the describeList value for this ClassificationBody.
     * 
     * @param describeList
     */
    public void setDescribeList(Classification[] describeList) {
        this.describeList = describeList;
    }


    /**
     * Gets the firstClassificationList value for this ClassificationBody.
     * 
     * @return firstClassificationList
     */
    public Classification[] getFirstClassificationList() {
        return firstClassificationList;
    }


    /**
     * Sets the firstClassificationList value for this ClassificationBody.
     * 
     * @param firstClassificationList
     */
    public void setFirstClassificationList(Classification[] firstClassificationList) {
        this.firstClassificationList = firstClassificationList;
    }


    /**
     * Gets the repairModeList value for this ClassificationBody.
     * 
     * @return repairModeList
     */
    public Classification[] getRepairModeList() {
        return repairModeList;
    }


    /**
     * Sets the repairModeList value for this ClassificationBody.
     * 
     * @param repairModeList
     */
    public void setRepairModeList(Classification[] repairModeList) {
        this.repairModeList = repairModeList;
    }


    /**
     * Gets the secondClassificationList value for this ClassificationBody.
     * 
     * @return secondClassificationList
     */
    public Classification[] getSecondClassificationList() {
        return secondClassificationList;
    }


    /**
     * Sets the secondClassificationList value for this ClassificationBody.
     * 
     * @param secondClassificationList
     */
    public void setSecondClassificationList(Classification[] secondClassificationList) {
        this.secondClassificationList = secondClassificationList;
    }


    /**
     * Gets the thirdClassificationList value for this ClassificationBody.
     * 
     * @return thirdClassificationList
     */
    public Classification[] getThirdClassificationList() {
        return thirdClassificationList;
    }


    /**
     * Sets the thirdClassificationList value for this ClassificationBody.
     * 
     * @param thirdClassificationList
     */
    public void setThirdClassificationList(Classification[] thirdClassificationList) {
        this.thirdClassificationList = thirdClassificationList;
    }


    /**
     * Gets the workTimeList value for this ClassificationBody.
     * 
     * @return workTimeList
     */
    public Classification[] getWorkTimeList() {
        return workTimeList;
    }


    /**
     * Sets the workTimeList value for this ClassificationBody.
     * 
     * @param workTimeList
     */
    public void setWorkTimeList(Classification[] workTimeList) {
        this.workTimeList = workTimeList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ClassificationBody)) return false;
        ClassificationBody other = (ClassificationBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.describeList==null && other.getDescribeList()==null) ||
             (this.describeList!=null &&
              java.util.Arrays.equals(this.describeList, other.getDescribeList()))) &&
            ((this.firstClassificationList==null && other.getFirstClassificationList()==null) ||
             (this.firstClassificationList!=null &&
              java.util.Arrays.equals(this.firstClassificationList, other.getFirstClassificationList()))) &&
            ((this.repairModeList==null && other.getRepairModeList()==null) ||
             (this.repairModeList!=null &&
              java.util.Arrays.equals(this.repairModeList, other.getRepairModeList()))) &&
            ((this.secondClassificationList==null && other.getSecondClassificationList()==null) ||
             (this.secondClassificationList!=null &&
              java.util.Arrays.equals(this.secondClassificationList, other.getSecondClassificationList()))) &&
            ((this.thirdClassificationList==null && other.getThirdClassificationList()==null) ||
             (this.thirdClassificationList!=null &&
              java.util.Arrays.equals(this.thirdClassificationList, other.getThirdClassificationList()))) &&
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
        if (getDescribeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescribeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getDescribeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFirstClassificationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFirstClassificationList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFirstClassificationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRepairModeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRepairModeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getRepairModeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSecondClassificationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSecondClassificationList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSecondClassificationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getThirdClassificationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getThirdClassificationList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getThirdClassificationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(ClassificationBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("describeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "describeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstClassificationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "firstClassificationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repairModeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "repairModeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondClassificationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "secondClassificationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thirdClassificationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "thirdClassificationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workTimeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "workTimeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
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
