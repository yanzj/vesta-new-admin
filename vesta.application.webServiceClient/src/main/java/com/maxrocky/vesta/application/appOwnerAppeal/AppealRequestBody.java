/**
 * AppealRequestBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.appOwnerAppeal;

public class AppealRequestBody  implements java.io.Serializable {
    private String houseCode;

    private String idCardNo;

    private String name;

    private String passportType;

    private String phonenumber;

    public AppealRequestBody() {
    }

    public AppealRequestBody(
           String houseCode,
           String idCardNo,
           String name,
           String passportType,
           String phonenumber) {
           this.houseCode = houseCode;
           this.idCardNo = idCardNo;
           this.name = name;
           this.passportType = passportType;
           this.phonenumber = phonenumber;
    }


    /**
     * Gets the houseCode value for this AppealRequestBody.
     *
     * @return houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }


    /**
     * Sets the houseCode value for this AppealRequestBody.
     *
     * @param houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }


    /**
     * Gets the idCardNo value for this AppealRequestBody.
     *
     * @return idCardNo
     */
    public String getIdCardNo() {
        return idCardNo;
    }


    /**
     * Sets the idCardNo value for this AppealRequestBody.
     *
     * @param idCardNo
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }


    /**
     * Gets the name value for this AppealRequestBody.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this AppealRequestBody.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the passportType value for this AppealRequestBody.
     *
     * @return passportType
     */
    public String getPassportType() {
        return passportType;
    }


    /**
     * Sets the passportType value for this AppealRequestBody.
     *
     * @param passportType
     */
    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }


    /**
     * Gets the phonenumber value for this AppealRequestBody.
     *
     * @return phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }


    /**
     * Sets the phonenumber value for this AppealRequestBody.
     *
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof AppealRequestBody)) return false;
        AppealRequestBody other = (AppealRequestBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.houseCode==null && other.getHouseCode()==null) ||
             (this.houseCode!=null &&
              this.houseCode.equals(other.getHouseCode()))) &&
            ((this.idCardNo==null && other.getIdCardNo()==null) ||
             (this.idCardNo!=null &&
              this.idCardNo.equals(other.getIdCardNo()))) &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.passportType==null && other.getPassportType()==null) ||
             (this.passportType!=null &&
              this.passportType.equals(other.getPassportType()))) &&
            ((this.phonenumber==null && other.getPhonenumber()==null) ||
             (this.phonenumber!=null &&
              this.phonenumber.equals(other.getPhonenumber())));
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
        if (getHouseCode() != null) {
            _hashCode += getHouseCode().hashCode();
        }
        if (getIdCardNo() != null) {
            _hashCode += getIdCardNo().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPassportType() != null) {
            _hashCode += getPassportType().hashCode();
        }
        if (getPhonenumber() != null) {
            _hashCode += getPhonenumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AppealRequestBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealRequestBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCardNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "idCardNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("passportType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "passportType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phonenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "phonenumber"));
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
