/**
 * Unit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class Unit  implements java.io.Serializable {
    private String buildingCode;

    private String buildingId;

    private java.util.Calendar modifiedOn;

    private String name;

    private PropertyType propertyType;

    private String recordBuildingCode;

    private String recordUnitCode;

    private String recordUnitName;

    private String recordUnitNum;

    private Integer stateCode;

    private String unitId;

    private String unitNum;

    private String unitcode;

    public Unit() {
    }

    public Unit(
           String buildingCode,
           String buildingId,
           java.util.Calendar modifiedOn,
           String name,
           PropertyType propertyType,
           String recordBuildingCode,
           String recordUnitCode,
           String recordUnitName,
           String recordUnitNum,
           Integer stateCode,
           String unitId,
           String unitNum,
           String unitcode) {
           this.buildingCode = buildingCode;
           this.buildingId = buildingId;
           this.modifiedOn = modifiedOn;
           this.name = name;
           this.propertyType = propertyType;
           this.recordBuildingCode = recordBuildingCode;
           this.recordUnitCode = recordUnitCode;
           this.recordUnitName = recordUnitName;
           this.recordUnitNum = recordUnitNum;
           this.stateCode = stateCode;
           this.unitId = unitId;
           this.unitNum = unitNum;
           this.unitcode = unitcode;
    }


    /**
     * Gets the buildingCode value for this Unit.
     * 
     * @return buildingCode
     */
    public String getBuildingCode() {
        return buildingCode;
    }


    /**
     * Sets the buildingCode value for this Unit.
     * 
     * @param buildingCode
     */
    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }


    /**
     * Gets the buildingId value for this Unit.
     * 
     * @return buildingId
     */
    public String getBuildingId() {
        return buildingId;
    }


    /**
     * Sets the buildingId value for this Unit.
     * 
     * @param buildingId
     */
    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }


    /**
     * Gets the modifiedOn value for this Unit.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this Unit.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the name value for this Unit.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Unit.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the propertyType value for this Unit.
     * 
     * @return propertyType
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this Unit.
     * 
     * @param propertyType
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the recordBuildingCode value for this Unit.
     * 
     * @return recordBuildingCode
     */
    public String getRecordBuildingCode() {
        return recordBuildingCode;
    }


    /**
     * Sets the recordBuildingCode value for this Unit.
     * 
     * @param recordBuildingCode
     */
    public void setRecordBuildingCode(String recordBuildingCode) {
        this.recordBuildingCode = recordBuildingCode;
    }


    /**
     * Gets the recordUnitCode value for this Unit.
     * 
     * @return recordUnitCode
     */
    public String getRecordUnitCode() {
        return recordUnitCode;
    }


    /**
     * Sets the recordUnitCode value for this Unit.
     * 
     * @param recordUnitCode
     */
    public void setRecordUnitCode(String recordUnitCode) {
        this.recordUnitCode = recordUnitCode;
    }


    /**
     * Gets the recordUnitName value for this Unit.
     * 
     * @return recordUnitName
     */
    public String getRecordUnitName() {
        return recordUnitName;
    }


    /**
     * Sets the recordUnitName value for this Unit.
     * 
     * @param recordUnitName
     */
    public void setRecordUnitName(String recordUnitName) {
        this.recordUnitName = recordUnitName;
    }


    /**
     * Gets the recordUnitNum value for this Unit.
     * 
     * @return recordUnitNum
     */
    public String getRecordUnitNum() {
        return recordUnitNum;
    }


    /**
     * Sets the recordUnitNum value for this Unit.
     * 
     * @param recordUnitNum
     */
    public void setRecordUnitNum(String recordUnitNum) {
        this.recordUnitNum = recordUnitNum;
    }


    /**
     * Gets the stateCode value for this Unit.
     * 
     * @return stateCode
     */
    public Integer getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this Unit.
     * 
     * @param stateCode
     */
    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the unitId value for this Unit.
     * 
     * @return unitId
     */
    public String getUnitId() {
        return unitId;
    }


    /**
     * Sets the unitId value for this Unit.
     * 
     * @param unitId
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }


    /**
     * Gets the unitNum value for this Unit.
     * 
     * @return unitNum
     */
    public String getUnitNum() {
        return unitNum;
    }


    /**
     * Sets the unitNum value for this Unit.
     * 
     * @param unitNum
     */
    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }


    /**
     * Gets the unitcode value for this Unit.
     * 
     * @return unitcode
     */
    public String getUnitcode() {
        return unitcode;
    }


    /**
     * Sets the unitcode value for this Unit.
     * 
     * @param unitcode
     */
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Unit)) return false;
        Unit other = (Unit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.buildingCode==null && other.getBuildingCode()==null) || 
             (this.buildingCode!=null &&
              this.buildingCode.equals(other.getBuildingCode()))) &&
            ((this.buildingId==null && other.getBuildingId()==null) || 
             (this.buildingId!=null &&
              this.buildingId.equals(other.getBuildingId()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.recordBuildingCode==null && other.getRecordBuildingCode()==null) || 
             (this.recordBuildingCode!=null &&
              this.recordBuildingCode.equals(other.getRecordBuildingCode()))) &&
            ((this.recordUnitCode==null && other.getRecordUnitCode()==null) || 
             (this.recordUnitCode!=null &&
              this.recordUnitCode.equals(other.getRecordUnitCode()))) &&
            ((this.recordUnitName==null && other.getRecordUnitName()==null) || 
             (this.recordUnitName!=null &&
              this.recordUnitName.equals(other.getRecordUnitName()))) &&
            ((this.recordUnitNum==null && other.getRecordUnitNum()==null) || 
             (this.recordUnitNum!=null &&
              this.recordUnitNum.equals(other.getRecordUnitNum()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode()))) &&
            ((this.unitId==null && other.getUnitId()==null) || 
             (this.unitId!=null &&
              this.unitId.equals(other.getUnitId()))) &&
            ((this.unitNum==null && other.getUnitNum()==null) || 
             (this.unitNum!=null &&
              this.unitNum.equals(other.getUnitNum()))) &&
            ((this.unitcode==null && other.getUnitcode()==null) || 
             (this.unitcode!=null &&
              this.unitcode.equals(other.getUnitcode())));
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
        if (getBuildingCode() != null) {
            _hashCode += getBuildingCode().hashCode();
        }
        if (getBuildingId() != null) {
            _hashCode += getBuildingId().hashCode();
        }
        if (getModifiedOn() != null) {
            _hashCode += getModifiedOn().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPropertyType() != null) {
            _hashCode += getPropertyType().hashCode();
        }
        if (getRecordBuildingCode() != null) {
            _hashCode += getRecordBuildingCode().hashCode();
        }
        if (getRecordUnitCode() != null) {
            _hashCode += getRecordUnitCode().hashCode();
        }
        if (getRecordUnitName() != null) {
            _hashCode += getRecordUnitName().hashCode();
        }
        if (getRecordUnitNum() != null) {
            _hashCode += getRecordUnitNum().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        if (getUnitId() != null) {
            _hashCode += getUnitId().hashCode();
        }
        if (getUnitNum() != null) {
            _hashCode += getUnitNum().hashCode();
        }
        if (getUnitcode() != null) {
            _hashCode += getUnitcode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Unit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Unit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildingCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "buildingCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "buildingId"));
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
        elemField.setFieldName("propertyType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "propertyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "PropertyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordBuildingCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordBuildingCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordUnitCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordUnitCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordUnitName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordUnitName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordUnitNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordUnitNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitcode"));
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
