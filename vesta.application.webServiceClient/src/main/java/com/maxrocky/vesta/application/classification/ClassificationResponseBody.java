/**
 * ClassificationResponseBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.classification;

import com.maxrocky.vesta.application.inspection.InspectionBody;
import com.maxrocky.vesta.application.publicModel.MemberShipBody;
import com.maxrocky.vesta.application.repair.RepairBody;

public class ClassificationResponseBody  implements java.io.Serializable {
    private ClassificationBody classificationBody;

    private InspectionBody inspectionBody;

    private MemberShipBody memberShipBody;

    private RepairBody repairBody;

    public ClassificationResponseBody() {
    }

    public ClassificationResponseBody(
           ClassificationBody classificationBody,
           InspectionBody inspectionBody,
           MemberShipBody memberShipBody,
           RepairBody repairBody) {
           this.classificationBody = classificationBody;
           this.inspectionBody = inspectionBody;
           this.memberShipBody = memberShipBody;
           this.repairBody = repairBody;
    }


    /**
     * Gets the classificationBody value for this ClassificationResponseBody.
     * 
     * @return classificationBody
     */
    public ClassificationBody getClassificationBody() {
        return classificationBody;
    }


    /**
     * Sets the classificationBody value for this ClassificationResponseBody.
     * 
     * @param classificationBody
     */
    public void setClassificationBody(ClassificationBody classificationBody) {
        this.classificationBody = classificationBody;
    }


    /**
     * Gets the inspectionBody value for this ClassificationResponseBody.
     * 
     * @return inspectionBody
     */
    public InspectionBody getInspectionBody() {
        return inspectionBody;
    }


    /**
     * Sets the inspectionBody value for this ClassificationResponseBody.
     * 
     * @param inspectionBody
     */
    public void setInspectionBody(InspectionBody inspectionBody) {
        this.inspectionBody = inspectionBody;
    }


    /**
     * Gets the memberShipBody value for this ClassificationResponseBody.
     * 
     * @return memberShipBody
     */
    public MemberShipBody getMemberShipBody() {
        return memberShipBody;
    }


    /**
     * Sets the memberShipBody value for this ClassificationResponseBody.
     * 
     * @param memberShipBody
     */
    public void setMemberShipBody(MemberShipBody memberShipBody) {
        this.memberShipBody = memberShipBody;
    }


    /**
     * Gets the repairBody value for this ClassificationResponseBody.
     * 
     * @return repairBody
     */
    public RepairBody getRepairBody() {
        return repairBody;
    }


    /**
     * Sets the repairBody value for this ClassificationResponseBody.
     * 
     * @param repairBody
     */
    public void setRepairBody(RepairBody repairBody) {
        this.repairBody = repairBody;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ClassificationResponseBody)) return false;
        ClassificationResponseBody other = (ClassificationResponseBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.classificationBody==null && other.getClassificationBody()==null) ||
             (this.classificationBody!=null &&
              this.classificationBody.equals(other.getClassificationBody()))) &&
            ((this.inspectionBody==null && other.getInspectionBody()==null) ||
             (this.inspectionBody!=null &&
              this.inspectionBody.equals(other.getInspectionBody()))) &&
            ((this.memberShipBody==null && other.getMemberShipBody()==null) ||
             (this.memberShipBody!=null &&
              this.memberShipBody.equals(other.getMemberShipBody()))) &&
            ((this.repairBody==null && other.getRepairBody()==null) ||
             (this.repairBody!=null &&
              this.repairBody.equals(other.getRepairBody())));
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
        if (getClassificationBody() != null) {
            _hashCode += getClassificationBody().hashCode();
        }
        if (getInspectionBody() != null) {
            _hashCode += getInspectionBody().hashCode();
        }
        if (getMemberShipBody() != null) {
            _hashCode += getMemberShipBody().hashCode();
        }
        if (getRepairBody() != null) {
            _hashCode += getRepairBody().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClassificationResponseBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationResponseBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classificationBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "classificationBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inspectionBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "inspectionBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberShipBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "memberShipBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "MemberShipBody"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repairBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "repairBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairBody"));
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
