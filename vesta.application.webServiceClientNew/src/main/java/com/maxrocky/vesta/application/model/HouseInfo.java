/**
 * HouseInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class HouseInfo  implements java.io.Serializable {
    private java.math.BigDecimal area;

    private java.util.Calendar buyDate;

    private java.lang.String declareStadanard;

    private java.lang.String houseType;

    private java.lang.String id;

    private java.lang.String productType;

    private java.lang.String projectId;

    private java.lang.String registerno;

    public HouseInfo() {
    }

    public HouseInfo(
           java.math.BigDecimal area,
           java.util.Calendar buyDate,
           java.lang.String declareStadanard,
           java.lang.String houseType,
           java.lang.String id,
           java.lang.String productType,
           java.lang.String projectId,
           java.lang.String registerno) {
           this.area = area;
           this.buyDate = buyDate;
           this.declareStadanard = declareStadanard;
           this.houseType = houseType;
           this.id = id;
           this.productType = productType;
           this.projectId = projectId;
           this.registerno = registerno;
    }


    /**
     * Gets the area value for this HouseInfo.
     * 
     * @return area
     */
    public java.math.BigDecimal getArea() {
        return area;
    }


    /**
     * Sets the area value for this HouseInfo.
     * 
     * @param area
     */
    public void setArea(java.math.BigDecimal area) {
        this.area = area;
    }


    /**
     * Gets the buyDate value for this HouseInfo.
     * 
     * @return buyDate
     */
    public java.util.Calendar getBuyDate() {
        return buyDate;
    }


    /**
     * Sets the buyDate value for this HouseInfo.
     * 
     * @param buyDate
     */
    public void setBuyDate(java.util.Calendar buyDate) {
        this.buyDate = buyDate;
    }


    /**
     * Gets the declareStadanard value for this HouseInfo.
     * 
     * @return declareStadanard
     */
    public java.lang.String getDeclareStadanard() {
        return declareStadanard;
    }


    /**
     * Sets the declareStadanard value for this HouseInfo.
     * 
     * @param declareStadanard
     */
    public void setDeclareStadanard(java.lang.String declareStadanard) {
        this.declareStadanard = declareStadanard;
    }


    /**
     * Gets the houseType value for this HouseInfo.
     * 
     * @return houseType
     */
    public java.lang.String getHouseType() {
        return houseType;
    }


    /**
     * Sets the houseType value for this HouseInfo.
     * 
     * @param houseType
     */
    public void setHouseType(java.lang.String houseType) {
        this.houseType = houseType;
    }


    /**
     * Gets the id value for this HouseInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this HouseInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the productType value for this HouseInfo.
     * 
     * @return productType
     */
    public java.lang.String getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this HouseInfo.
     * 
     * @param productType
     */
    public void setProductType(java.lang.String productType) {
        this.productType = productType;
    }


    /**
     * Gets the projectId value for this HouseInfo.
     * 
     * @return projectId
     */
    public java.lang.String getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this HouseInfo.
     * 
     * @param projectId
     */
    public void setProjectId(java.lang.String projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the registerno value for this HouseInfo.
     * 
     * @return registerno
     */
    public java.lang.String getRegisterno() {
        return registerno;
    }


    /**
     * Sets the registerno value for this HouseInfo.
     * 
     * @param registerno
     */
    public void setRegisterno(java.lang.String registerno) {
        this.registerno = registerno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HouseInfo)) return false;
        HouseInfo other = (HouseInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.area==null && other.getArea()==null) || 
             (this.area!=null &&
              this.area.equals(other.getArea()))) &&
            ((this.buyDate==null && other.getBuyDate()==null) || 
             (this.buyDate!=null &&
              this.buyDate.equals(other.getBuyDate()))) &&
            ((this.declareStadanard==null && other.getDeclareStadanard()==null) || 
             (this.declareStadanard!=null &&
              this.declareStadanard.equals(other.getDeclareStadanard()))) &&
            ((this.houseType==null && other.getHouseType()==null) || 
             (this.houseType!=null &&
              this.houseType.equals(other.getHouseType()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.productType==null && other.getProductType()==null) || 
             (this.productType!=null &&
              this.productType.equals(other.getProductType()))) &&
            ((this.projectId==null && other.getProjectId()==null) || 
             (this.projectId!=null &&
              this.projectId.equals(other.getProjectId()))) &&
            ((this.registerno==null && other.getRegisterno()==null) || 
             (this.registerno!=null &&
              this.registerno.equals(other.getRegisterno())));
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
        if (getArea() != null) {
            _hashCode += getArea().hashCode();
        }
        if (getBuyDate() != null) {
            _hashCode += getBuyDate().hashCode();
        }
        if (getDeclareStadanard() != null) {
            _hashCode += getDeclareStadanard().hashCode();
        }
        if (getHouseType() != null) {
            _hashCode += getHouseType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getProductType() != null) {
            _hashCode += getProductType().hashCode();
        }
        if (getProjectId() != null) {
            _hashCode += getProjectId().hashCode();
        }
        if (getRegisterno() != null) {
            _hashCode += getRegisterno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HouseInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "HouseInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("area");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Area"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buyDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BuyDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("declareStadanard");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "DeclareStadanard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "HouseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ProductType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ProjectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registerno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Registerno"));
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
