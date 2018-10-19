/**
 * ImageModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.publicModel;

public class ImageModel implements java.io.Serializable {
    private String imageId;

    private String imageStatus;

    private String imageUrl;

    public ImageModel() {
    }

    public ImageModel(
           String imageId,
           String imageStatus,
           String imageUrl) {
           this.imageId = imageId;
           this.imageStatus = imageStatus;
           this.imageUrl = imageUrl;
    }


    /**
     * Gets the imageId value for this ImageModel.
     *
     * @return imageId
     */
    public String getImageId() {
        return imageId;
    }


    /**
     * Sets the imageId value for this ImageModel.
     *
     * @param imageId
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }


    /**
     * Gets the imageStatus value for this ImageModel.
     *
     * @return imageStatus
     */
    public String getImageStatus() {
        return imageStatus;
    }


    /**
     * Sets the imageStatus value for this ImageModel.
     *
     * @param imageStatus
     */
    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }


    /**
     * Gets the imageUrl value for this ImageModel.
     *
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }


    /**
     * Sets the imageUrl value for this ImageModel.
     *
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ImageModel)) return false;
        ImageModel other = (ImageModel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.imageId==null && other.getImageId()==null) ||
             (this.imageId!=null &&
              this.imageId.equals(other.getImageId()))) &&
            ((this.imageStatus==null && other.getImageStatus()==null) ||
             (this.imageStatus!=null &&
              this.imageStatus.equals(other.getImageStatus()))) &&
            ((this.imageUrl==null && other.getImageUrl()==null) ||
             (this.imageUrl!=null &&
              this.imageUrl.equals(other.getImageUrl())));
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
        if (getImageId() != null) {
            _hashCode += getImageId().hashCode();
        }
        if (getImageStatus() != null) {
            _hashCode += getImageStatus().hashCode();
        }
        if (getImageUrl() != null) {
            _hashCode += getImageUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImageModel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ImageModel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "imageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "imageStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "imageUrl"));
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
