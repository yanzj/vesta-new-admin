/**
 * Project.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class Project  implements java.io.Serializable {
    private java.util.Calendar beginDate;

    private String businessUnitName;

    private String businessUnitid;

    private String cityCode;

    private String cityId;

    private java.util.Calendar endDate;

    private java.util.Calendar modifiedOn;

    private String owningBusinessUnit;

    private String owningBusinessUnitid;

    private String projectCode;

    private String projectId;

    private String projectName;

    private String projectStage;

    public Project() {
    }

    public Project(
           java.util.Calendar beginDate,
           String businessUnitName,
           String businessUnitid,
           String cityCode,
           String cityId,
           java.util.Calendar endDate,
           java.util.Calendar modifiedOn,
           String owningBusinessUnit,
           String owningBusinessUnitid,
           String projectCode,
           String projectId,
           String projectName,
           String projectStage) {
           this.beginDate = beginDate;
           this.businessUnitName = businessUnitName;
           this.businessUnitid = businessUnitid;
           this.cityCode = cityCode;
           this.cityId = cityId;
           this.endDate = endDate;
           this.modifiedOn = modifiedOn;
           this.owningBusinessUnit = owningBusinessUnit;
           this.owningBusinessUnitid = owningBusinessUnitid;
           this.projectCode = projectCode;
           this.projectId = projectId;
           this.projectName = projectName;
           this.projectStage = projectStage;
    }


    /**
     * Gets the beginDate value for this Project.
     * 
     * @return beginDate
     */
    public java.util.Calendar getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this Project.
     * 
     * @param beginDate
     */
    public void setBeginDate(java.util.Calendar beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the businessUnitName value for this Project.
     * 
     * @return businessUnitName
     */
    public String getBusinessUnitName() {
        return businessUnitName;
    }


    /**
     * Sets the businessUnitName value for this Project.
     * 
     * @param businessUnitName
     */
    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }


    /**
     * Gets the businessUnitid value for this Project.
     * 
     * @return businessUnitid
     */
    public String getBusinessUnitid() {
        return businessUnitid;
    }


    /**
     * Sets the businessUnitid value for this Project.
     * 
     * @param businessUnitid
     */
    public void setBusinessUnitid(String businessUnitid) {
        this.businessUnitid = businessUnitid;
    }


    /**
     * Gets the cityCode value for this Project.
     * 
     * @return cityCode
     */
    public String getCityCode() {
        return cityCode;
    }


    /**
     * Sets the cityCode value for this Project.
     * 
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    /**
     * Gets the cityId value for this Project.
     * 
     * @return cityId
     */
    public String getCityId() {
        return cityId;
    }


    /**
     * Sets the cityId value for this Project.
     * 
     * @param cityId
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }


    /**
     * Gets the endDate value for this Project.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this Project.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the modifiedOn value for this Project.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this Project.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the owningBusinessUnit value for this Project.
     * 
     * @return owningBusinessUnit
     */
    public String getOwningBusinessUnit() {
        return owningBusinessUnit;
    }


    /**
     * Sets the owningBusinessUnit value for this Project.
     * 
     * @param owningBusinessUnit
     */
    public void setOwningBusinessUnit(String owningBusinessUnit) {
        this.owningBusinessUnit = owningBusinessUnit;
    }


    /**
     * Gets the owningBusinessUnitid value for this Project.
     * 
     * @return owningBusinessUnitid
     */
    public String getOwningBusinessUnitid() {
        return owningBusinessUnitid;
    }


    /**
     * Sets the owningBusinessUnitid value for this Project.
     * 
     * @param owningBusinessUnitid
     */
    public void setOwningBusinessUnitid(String owningBusinessUnitid) {
        this.owningBusinessUnitid = owningBusinessUnitid;
    }


    /**
     * Gets the projectCode value for this Project.
     * 
     * @return projectCode
     */
    public String getProjectCode() {
        return projectCode;
    }


    /**
     * Sets the projectCode value for this Project.
     * 
     * @param projectCode
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    /**
     * Gets the projectId value for this Project.
     * 
     * @return projectId
     */
    public String getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this Project.
     * 
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the projectName value for this Project.
     * 
     * @return projectName
     */
    public String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this Project.
     * 
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the projectStage value for this Project.
     * 
     * @return projectStage
     */
    public String getProjectStage() {
        return projectStage;
    }


    /**
     * Sets the projectStage value for this Project.
     * 
     * @param projectStage
     */
    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Project)) return false;
        Project other = (Project) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.businessUnitName==null && other.getBusinessUnitName()==null) || 
             (this.businessUnitName!=null &&
              this.businessUnitName.equals(other.getBusinessUnitName()))) &&
            ((this.businessUnitid==null && other.getBusinessUnitid()==null) || 
             (this.businessUnitid!=null &&
              this.businessUnitid.equals(other.getBusinessUnitid()))) &&
            ((this.cityCode==null && other.getCityCode()==null) || 
             (this.cityCode!=null &&
              this.cityCode.equals(other.getCityCode()))) &&
            ((this.cityId==null && other.getCityId()==null) || 
             (this.cityId!=null &&
              this.cityId.equals(other.getCityId()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.owningBusinessUnit==null && other.getOwningBusinessUnit()==null) || 
             (this.owningBusinessUnit!=null &&
              this.owningBusinessUnit.equals(other.getOwningBusinessUnit()))) &&
            ((this.owningBusinessUnitid==null && other.getOwningBusinessUnitid()==null) || 
             (this.owningBusinessUnitid!=null &&
              this.owningBusinessUnitid.equals(other.getOwningBusinessUnitid()))) &&
            ((this.projectCode==null && other.getProjectCode()==null) || 
             (this.projectCode!=null &&
              this.projectCode.equals(other.getProjectCode()))) &&
            ((this.projectId==null && other.getProjectId()==null) || 
             (this.projectId!=null &&
              this.projectId.equals(other.getProjectId()))) &&
            ((this.projectName==null && other.getProjectName()==null) || 
             (this.projectName!=null &&
              this.projectName.equals(other.getProjectName()))) &&
            ((this.projectStage==null && other.getProjectStage()==null) || 
             (this.projectStage!=null &&
              this.projectStage.equals(other.getProjectStage())));
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
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getBusinessUnitName() != null) {
            _hashCode += getBusinessUnitName().hashCode();
        }
        if (getBusinessUnitid() != null) {
            _hashCode += getBusinessUnitid().hashCode();
        }
        if (getCityCode() != null) {
            _hashCode += getCityCode().hashCode();
        }
        if (getCityId() != null) {
            _hashCode += getCityId().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getModifiedOn() != null) {
            _hashCode += getModifiedOn().hashCode();
        }
        if (getOwningBusinessUnit() != null) {
            _hashCode += getOwningBusinessUnit().hashCode();
        }
        if (getOwningBusinessUnitid() != null) {
            _hashCode += getOwningBusinessUnitid().hashCode();
        }
        if (getProjectCode() != null) {
            _hashCode += getProjectCode().hashCode();
        }
        if (getProjectId() != null) {
            _hashCode += getProjectId().hashCode();
        }
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        if (getProjectStage() != null) {
            _hashCode += getProjectStage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Project.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Project"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessUnitName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "businessUnitName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessUnitid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "businessUnitid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "cityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cityId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "cityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("owningBusinessUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "owningBusinessUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owningBusinessUnitid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "owningBusinessUnitid"));
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
        elemField.setFieldName("projectName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectStage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectStage"));
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
