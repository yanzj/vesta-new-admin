/**
 * Floor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class Floor  implements java.io.Serializable {
    private String floorId;

    private String floorNum;

    private String floorcode;

    private java.util.Calendar modifiedOn;

    private String name;

    private PropertyType propertyType;

    private String recordFloorName;

    private String recordFloorNum;

    private String recordFloorcode;

    private String recordUnitCode;

    private Integer stateCode;

    private String unitCode;

    private String unitId;

    public Floor() {
    }

    public Floor(
           String floorId,
           String floorNum,
           String floorcode,
           java.util.Calendar modifiedOn,
           String name,
           PropertyType propertyType,
           String recordFloorName,
           String recordFloorNum,
           String recordFloorcode,
           String recordUnitCode,
           Integer stateCode,
           String unitCode,
           String unitId) {
           this.floorId = floorId;
           this.floorNum = floorNum;
           this.floorcode = floorcode;
           this.modifiedOn = modifiedOn;
           this.name = name;
           this.propertyType = propertyType;
           this.recordFloorName = recordFloorName;
           this.recordFloorNum = recordFloorNum;
           this.recordFloorcode = recordFloorcode;
           this.recordUnitCode = recordUnitCode;
           this.stateCode = stateCode;
           this.unitCode = unitCode;
           this.unitId = unitId;
    }


    /**
     * Gets the floorId value for this Floor.
     * 
     * @return floorId
     */
    public String getFloorId() {
        return floorId;
    }


    /**
     * Sets the floorId value for this Floor.
     * 
     * @param floorId
     */
    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }


    /**
     * Gets the floorNum value for this Floor.
     * 
     * @return floorNum
     */
    public String getFloorNum() {
        return floorNum;
    }


    /**
     * Sets the floorNum value for this Floor.
     * 
     * @param floorNum
     */
    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }


    /**
     * Gets the floorcode value for this Floor.
     * 
     * @return floorcode
     */
    public String getFloorcode() {
        return floorcode;
    }


    /**
     * Sets the floorcode value for this Floor.
     * 
     * @param floorcode
     */
    public void setFloorcode(String floorcode) {
        this.floorcode = floorcode;
    }


    /**
     * Gets the modifiedOn value for this Floor.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this Floor.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the name value for this Floor.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Floor.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the propertyType value for this Floor.
     * 
     * @return propertyType
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this Floor.
     * 
     * @param propertyType
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the recordFloorName value for this Floor.
     * 
     * @return recordFloorName
     */
    public String getRecordFloorName() {
        return recordFloorName;
    }


    /**
     * Sets the recordFloorName value for this Floor.
     * 
     * @param recordFloorName
     */
    public void setRecordFloorName(String recordFloorName) {
        this.recordFloorName = recordFloorName;
    }


    /**
     * Gets the recordFloorNum value for this Floor.
     * 
     * @return recordFloorNum
     */
    public String getRecordFloorNum() {
        return recordFloorNum;
    }


    /**
     * Sets the recordFloorNum value for this Floor.
     * 
     * @param recordFloorNum
     */
    public void setRecordFloorNum(String recordFloorNum) {
        this.recordFloorNum = recordFloorNum;
    }


    /**
     * Gets the recordFloorcode value for this Floor.
     * 
     * @return recordFloorcode
     */
    public String getRecordFloorcode() {
        return recordFloorcode;
    }


    /**
     * Sets the recordFloorcode value for this Floor.
     * 
     * @param recordFloorcode
     */
    public void setRecordFloorcode(String recordFloorcode) {
        this.recordFloorcode = recordFloorcode;
    }


    /**
     * Gets the recordUnitCode value for this Floor.
     * 
     * @return recordUnitCode
     */
    public String getRecordUnitCode() {
        return recordUnitCode;
    }


    /**
     * Sets the recordUnitCode value for this Floor.
     * 
     * @param recordUnitCode
     */
    public void setRecordUnitCode(String recordUnitCode) {
        this.recordUnitCode = recordUnitCode;
    }


    /**
     * Gets the stateCode value for this Floor.
     * 
     * @return stateCode
     */
    public Integer getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this Floor.
     * 
     * @param stateCode
     */
    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the unitCode value for this Floor.
     * 
     * @return unitCode
     */
    public String getUnitCode() {
        return unitCode;
    }


    /**
     * Sets the unitCode value for this Floor.
     * 
     * @param unitCode
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }


    /**
     * Gets the unitId value for this Floor.
     * 
     * @return unitId
     */
    public String getUnitId() {
        return unitId;
    }


    /**
     * Sets the unitId value for this Floor.
     * 
     * @param unitId
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Floor)) return false;
        Floor other = (Floor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.floorId==null && other.getFloorId()==null) || 
             (this.floorId!=null &&
              this.floorId.equals(other.getFloorId()))) &&
            ((this.floorNum==null && other.getFloorNum()==null) || 
             (this.floorNum!=null &&
              this.floorNum.equals(other.getFloorNum()))) &&
            ((this.floorcode==null && other.getFloorcode()==null) || 
             (this.floorcode!=null &&
              this.floorcode.equals(other.getFloorcode()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.recordFloorName==null && other.getRecordFloorName()==null) || 
             (this.recordFloorName!=null &&
              this.recordFloorName.equals(other.getRecordFloorName()))) &&
            ((this.recordFloorNum==null && other.getRecordFloorNum()==null) || 
             (this.recordFloorNum!=null &&
              this.recordFloorNum.equals(other.getRecordFloorNum()))) &&
            ((this.recordFloorcode==null && other.getRecordFloorcode()==null) || 
             (this.recordFloorcode!=null &&
              this.recordFloorcode.equals(other.getRecordFloorcode()))) &&
            ((this.recordUnitCode==null && other.getRecordUnitCode()==null) || 
             (this.recordUnitCode!=null &&
              this.recordUnitCode.equals(other.getRecordUnitCode()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode()))) &&
            ((this.unitCode==null && other.getUnitCode()==null) || 
             (this.unitCode!=null &&
              this.unitCode.equals(other.getUnitCode()))) &&
            ((this.unitId==null && other.getUnitId()==null) || 
             (this.unitId!=null &&
              this.unitId.equals(other.getUnitId())));
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
        if (getFloorId() != null) {
            _hashCode += getFloorId().hashCode();
        }
        if (getFloorNum() != null) {
            _hashCode += getFloorNum().hashCode();
        }
        if (getFloorcode() != null) {
            _hashCode += getFloorcode().hashCode();
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
        if (getRecordFloorName() != null) {
            _hashCode += getRecordFloorName().hashCode();
        }
        if (getRecordFloorNum() != null) {
            _hashCode += getRecordFloorNum().hashCode();
        }
        if (getRecordFloorcode() != null) {
            _hashCode += getRecordFloorcode().hashCode();
        }
        if (getRecordUnitCode() != null) {
            _hashCode += getRecordUnitCode().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        if (getUnitCode() != null) {
            _hashCode += getUnitCode().hashCode();
        }
        if (getUnitId() != null) {
            _hashCode += getUnitId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Floor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Floor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorcode"));
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
        elemField.setFieldName("recordFloorName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordFloorName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordFloorNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordFloorNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordFloorcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordFloorcode"));
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
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "stateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
