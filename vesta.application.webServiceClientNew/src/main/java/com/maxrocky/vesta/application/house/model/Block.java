/**
 * Block.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class Block  implements java.io.Serializable {
    private String blockCode;

    private String blockId;

    private String blockNum;

    private java.util.Calendar modifiedOn;

    private String name;

    private String projectCode;

    private String projectId;

    private PropertyType propertyType;

    private Integer stateCode;

    public Block() {
    }

    public Block(
           String blockCode,
           String blockId,
           String blockNum,
           java.util.Calendar modifiedOn,
           String name,
           String projectCode,
           String projectId,
           PropertyType propertyType,
           Integer stateCode) {
           this.blockCode = blockCode;
           this.blockId = blockId;
           this.blockNum = blockNum;
           this.modifiedOn = modifiedOn;
           this.name = name;
           this.projectCode = projectCode;
           this.projectId = projectId;
           this.propertyType = propertyType;
           this.stateCode = stateCode;
    }


    /**
     * Gets the blockCode value for this Block.
     * 
     * @return blockCode
     */
    public String getBlockCode() {
        return blockCode;
    }


    /**
     * Sets the blockCode value for this Block.
     * 
     * @param blockCode
     */
    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }


    /**
     * Gets the blockId value for this Block.
     * 
     * @return blockId
     */
    public String getBlockId() {
        return blockId;
    }


    /**
     * Sets the blockId value for this Block.
     * 
     * @param blockId
     */
    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }


    /**
     * Gets the blockNum value for this Block.
     * 
     * @return blockNum
     */
    public String getBlockNum() {
        return blockNum;
    }


    /**
     * Sets the blockNum value for this Block.
     * 
     * @param blockNum
     */
    public void setBlockNum(String blockNum) {
        this.blockNum = blockNum;
    }


    /**
     * Gets the modifiedOn value for this Block.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this Block.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the name value for this Block.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Block.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the projectCode value for this Block.
     * 
     * @return projectCode
     */
    public String getProjectCode() {
        return projectCode;
    }


    /**
     * Sets the projectCode value for this Block.
     * 
     * @param projectCode
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    /**
     * Gets the projectId value for this Block.
     * 
     * @return projectId
     */
    public String getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this Block.
     * 
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the propertyType value for this Block.
     * 
     * @return propertyType
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this Block.
     * 
     * @param propertyType
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the stateCode value for this Block.
     * 
     * @return stateCode
     */
    public Integer getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this Block.
     * 
     * @param stateCode
     */
    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Block)) return false;
        Block other = (Block) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.blockCode==null && other.getBlockCode()==null) || 
             (this.blockCode!=null &&
              this.blockCode.equals(other.getBlockCode()))) &&
            ((this.blockId==null && other.getBlockId()==null) || 
             (this.blockId!=null &&
              this.blockId.equals(other.getBlockId()))) &&
            ((this.blockNum==null && other.getBlockNum()==null) || 
             (this.blockNum!=null &&
              this.blockNum.equals(other.getBlockNum()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.projectCode==null && other.getProjectCode()==null) || 
             (this.projectCode!=null &&
              this.projectCode.equals(other.getProjectCode()))) &&
            ((this.projectId==null && other.getProjectId()==null) || 
             (this.projectId!=null &&
              this.projectId.equals(other.getProjectId()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode())));
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
        if (getBlockCode() != null) {
            _hashCode += getBlockCode().hashCode();
        }
        if (getBlockId() != null) {
            _hashCode += getBlockId().hashCode();
        }
        if (getBlockNum() != null) {
            _hashCode += getBlockNum().hashCode();
        }
        if (getModifiedOn() != null) {
            _hashCode += getModifiedOn().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getProjectCode() != null) {
            _hashCode += getProjectCode().hashCode();
        }
        if (getProjectId() != null) {
            _hashCode += getProjectId().hashCode();
        }
        if (getPropertyType() != null) {
            _hashCode += getPropertyType().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Block.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Block"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "blockCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "blockId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "blockNum"));
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("propertyType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "propertyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "PropertyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "stateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
