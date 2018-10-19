/**
 * HousePropertyRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house;

import com.maxrocky.vesta.application.house.model.QueryType;

public class HousePropertyRequest  implements java.io.Serializable {
    private java.util.Calendar lastModifyTime;

    private String[] projectCodeList;

    private QueryType queryType;

    public HousePropertyRequest() {
    }

    public HousePropertyRequest(
           java.util.Calendar lastModifyTime,
           String[] projectCodeList,
           QueryType queryType) {
           this.lastModifyTime = lastModifyTime;
           this.projectCodeList = projectCodeList;
           this.queryType = queryType;
    }


    /**
     * Gets the lastModifyTime value for this HousePropertyRequest.
     * 
     * @return lastModifyTime
     */
    public java.util.Calendar getLastModifyTime() {
        return lastModifyTime;
    }


    /**
     * Sets the lastModifyTime value for this HousePropertyRequest.
     * 
     * @param lastModifyTime
     */
    public void setLastModifyTime(java.util.Calendar lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }


    /**
     * Gets the projectCodeList value for this HousePropertyRequest.
     * 
     * @return projectCodeList
     */
    public String[] getProjectCodeList() {
        return projectCodeList;
    }


    /**
     * Sets the projectCodeList value for this HousePropertyRequest.
     * 
     * @param projectCodeList
     */
    public void setProjectCodeList(String[] projectCodeList) {
        this.projectCodeList = projectCodeList;
    }


    /**
     * Gets the queryType value for this HousePropertyRequest.
     * 
     * @return queryType
     */
    public QueryType getQueryType() {
        return queryType;
    }


    /**
     * Sets the queryType value for this HousePropertyRequest.
     * 
     * @param queryType
     */
    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof HousePropertyRequest)) return false;
        HousePropertyRequest other = (HousePropertyRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lastModifyTime==null && other.getLastModifyTime()==null) || 
             (this.lastModifyTime!=null &&
              this.lastModifyTime.equals(other.getLastModifyTime()))) &&
            ((this.projectCodeList==null && other.getProjectCodeList()==null) || 
             (this.projectCodeList!=null &&
              java.util.Arrays.equals(this.projectCodeList, other.getProjectCodeList()))) &&
            ((this.queryType==null && other.getQueryType()==null) || 
             (this.queryType!=null &&
              this.queryType.equals(other.getQueryType())));
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
        if (getLastModifyTime() != null) {
            _hashCode += getLastModifyTime().hashCode();
        }
        if (getProjectCodeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProjectCodeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getProjectCodeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getQueryType() != null) {
            _hashCode += getQueryType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HousePropertyRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifyTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "lastModifyTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectCodeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectCodeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "queryType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "QueryType"));
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
