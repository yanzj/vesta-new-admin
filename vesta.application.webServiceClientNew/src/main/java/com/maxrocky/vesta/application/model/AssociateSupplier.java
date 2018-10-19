/**
 * AssociateSupplier.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class AssociateSupplier  implements java.io.Serializable {
    private java.lang.String classificationThree;

    private java.lang.String id;

    private java.util.Calendar modifiedOn;

    private java.lang.String projectCode;

    private java.lang.String projectId;

    private java.lang.String supplierId;

    public AssociateSupplier() {
    }

    public AssociateSupplier(
           java.lang.String classificationThree,
           java.lang.String id,
           java.util.Calendar modifiedOn,
           java.lang.String projectCode,
           java.lang.String projectId,
           java.lang.String supplierId) {
           this.classificationThree = classificationThree;
           this.id = id;
           this.modifiedOn = modifiedOn;
           this.projectCode = projectCode;
           this.projectId = projectId;
           this.supplierId = supplierId;
    }


    /**
     * Gets the classificationThree value for this AssociateSupplier.
     * 
     * @return classificationThree
     */
    public java.lang.String getClassificationThree() {
        return classificationThree;
    }


    /**
     * Sets the classificationThree value for this AssociateSupplier.
     * 
     * @param classificationThree
     */
    public void setClassificationThree(java.lang.String classificationThree) {
        this.classificationThree = classificationThree;
    }


    /**
     * Gets the id value for this AssociateSupplier.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this AssociateSupplier.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the modifiedOn value for this AssociateSupplier.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this AssociateSupplier.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the projectCode value for this AssociateSupplier.
     * 
     * @return projectCode
     */
    public java.lang.String getProjectCode() {
        return projectCode;
    }


    /**
     * Sets the projectCode value for this AssociateSupplier.
     * 
     * @param projectCode
     */
    public void setProjectCode(java.lang.String projectCode) {
        this.projectCode = projectCode;
    }


    /**
     * Gets the projectId value for this AssociateSupplier.
     * 
     * @return projectId
     */
    public java.lang.String getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this AssociateSupplier.
     * 
     * @param projectId
     */
    public void setProjectId(java.lang.String projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the supplierId value for this AssociateSupplier.
     * 
     * @return supplierId
     */
    public java.lang.String getSupplierId() {
        return supplierId;
    }


    /**
     * Sets the supplierId value for this AssociateSupplier.
     * 
     * @param supplierId
     */
    public void setSupplierId(java.lang.String supplierId) {
        this.supplierId = supplierId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociateSupplier)) return false;
        AssociateSupplier other = (AssociateSupplier) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.classificationThree==null && other.getClassificationThree()==null) || 
             (this.classificationThree!=null &&
              this.classificationThree.equals(other.getClassificationThree()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.projectCode==null && other.getProjectCode()==null) || 
             (this.projectCode!=null &&
              this.projectCode.equals(other.getProjectCode()))) &&
            ((this.projectId==null && other.getProjectId()==null) || 
             (this.projectId!=null &&
              this.projectId.equals(other.getProjectId()))) &&
            ((this.supplierId==null && other.getSupplierId()==null) || 
             (this.supplierId!=null &&
              this.supplierId.equals(other.getSupplierId())));
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
        if (getClassificationThree() != null) {
            _hashCode += getClassificationThree().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getModifiedOn() != null) {
            _hashCode += getModifiedOn().hashCode();
        }
        if (getProjectCode() != null) {
            _hashCode += getProjectCode().hashCode();
        }
        if (getProjectId() != null) {
            _hashCode += getProjectId().hashCode();
        }
        if (getSupplierId() != null) {
            _hashCode += getSupplierId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociateSupplier.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AssociateSupplier"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classificationThree");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "classificationThree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedOn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "modifiedOn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectId"));
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
