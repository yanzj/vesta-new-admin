/**
 * MemberShipBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

import com.maxrocky.vesta.application.classification.model.Classification;

public class MemberShipBody implements java.io.Serializable {
    private Classification[] companyTypeList;

    private Classification[] eduLevelList;

    private Classification[] hobbyList;

    private Classification[] incomeLevelList;

    private Classification[] occupationList;

    private Classification[] relationswithmemberList;

    public MemberShipBody() {
    }

    public MemberShipBody(
           Classification[] companyTypeList,
           Classification[] eduLevelList,
           Classification[] hobbyList,
           Classification[] incomeLevelList,
           Classification[] occupationList,
           Classification[] relationswithmemberList) {
           this.companyTypeList = companyTypeList;
           this.eduLevelList = eduLevelList;
           this.hobbyList = hobbyList;
           this.incomeLevelList = incomeLevelList;
           this.occupationList = occupationList;
           this.relationswithmemberList = relationswithmemberList;
    }


    /**
     * Gets the companyTypeList value for this MemberShipBody.
     * 
     * @return companyTypeList
     */
    public Classification[] getCompanyTypeList() {
        return companyTypeList;
    }


    /**
     * Sets the companyTypeList value for this MemberShipBody.
     * 
     * @param companyTypeList
     */
    public void setCompanyTypeList(Classification[] companyTypeList) {
        this.companyTypeList = companyTypeList;
    }


    /**
     * Gets the eduLevelList value for this MemberShipBody.
     * 
     * @return eduLevelList
     */
    public Classification[] getEduLevelList() {
        return eduLevelList;
    }


    /**
     * Sets the eduLevelList value for this MemberShipBody.
     * 
     * @param eduLevelList
     */
    public void setEduLevelList(Classification[] eduLevelList) {
        this.eduLevelList = eduLevelList;
    }


    /**
     * Gets the hobbyList value for this MemberShipBody.
     * 
     * @return hobbyList
     */
    public Classification[] getHobbyList() {
        return hobbyList;
    }


    /**
     * Sets the hobbyList value for this MemberShipBody.
     * 
     * @param hobbyList
     */
    public void setHobbyList(Classification[] hobbyList) {
        this.hobbyList = hobbyList;
    }


    /**
     * Gets the incomeLevelList value for this MemberShipBody.
     * 
     * @return incomeLevelList
     */
    public Classification[] getIncomeLevelList() {
        return incomeLevelList;
    }


    /**
     * Sets the incomeLevelList value for this MemberShipBody.
     * 
     * @param incomeLevelList
     */
    public void setIncomeLevelList(Classification[] incomeLevelList) {
        this.incomeLevelList = incomeLevelList;
    }


    /**
     * Gets the occupationList value for this MemberShipBody.
     * 
     * @return occupationList
     */
    public Classification[] getOccupationList() {
        return occupationList;
    }


    /**
     * Sets the occupationList value for this MemberShipBody.
     * 
     * @param occupationList
     */
    public void setOccupationList(Classification[] occupationList) {
        this.occupationList = occupationList;
    }


    /**
     * Gets the relationswithmemberList value for this MemberShipBody.
     * 
     * @return relationswithmemberList
     */
    public Classification[] getRelationswithmemberList() {
        return relationswithmemberList;
    }


    /**
     * Sets the relationswithmemberList value for this MemberShipBody.
     * 
     * @param relationswithmemberList
     */
    public void setRelationswithmemberList(Classification[] relationswithmemberList) {
        this.relationswithmemberList = relationswithmemberList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof MemberShipBody)) return false;
        MemberShipBody other = (MemberShipBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.companyTypeList==null && other.getCompanyTypeList()==null) || 
             (this.companyTypeList!=null &&
              java.util.Arrays.equals(this.companyTypeList, other.getCompanyTypeList()))) &&
            ((this.eduLevelList==null && other.getEduLevelList()==null) || 
             (this.eduLevelList!=null &&
              java.util.Arrays.equals(this.eduLevelList, other.getEduLevelList()))) &&
            ((this.hobbyList==null && other.getHobbyList()==null) || 
             (this.hobbyList!=null &&
              java.util.Arrays.equals(this.hobbyList, other.getHobbyList()))) &&
            ((this.incomeLevelList==null && other.getIncomeLevelList()==null) || 
             (this.incomeLevelList!=null &&
              java.util.Arrays.equals(this.incomeLevelList, other.getIncomeLevelList()))) &&
            ((this.occupationList==null && other.getOccupationList()==null) || 
             (this.occupationList!=null &&
              java.util.Arrays.equals(this.occupationList, other.getOccupationList()))) &&
            ((this.relationswithmemberList==null && other.getRelationswithmemberList()==null) || 
             (this.relationswithmemberList!=null &&
              java.util.Arrays.equals(this.relationswithmemberList, other.getRelationswithmemberList())));
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
        if (getCompanyTypeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompanyTypeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCompanyTypeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEduLevelList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEduLevelList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getEduLevelList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHobbyList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHobbyList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getHobbyList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIncomeLevelList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIncomeLevelList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIncomeLevelList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOccupationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOccupationList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getOccupationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRelationswithmemberList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRelationswithmemberList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getRelationswithmemberList(), i);
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
        new org.apache.axis.description.TypeDesc(MemberShipBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "MemberShipBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyTypeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "companyTypeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eduLevelList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "eduLevelList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hobbyList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "hobbyList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incomeLevelList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "incomeLevelList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occupationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "occupationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationswithmemberList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "relationswithmemberList"));
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
