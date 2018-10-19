/**
 * HousePropertyResponseBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house;

import com.maxrocky.vesta.application.house.model.*;

public class HousePropertyResponseBody  implements java.io.Serializable {
    private Block[] blockList;

    private Building[] buildingList;

    private City[] cityList;

    private Floor[] floorList;

    private House[] houseList;

    private Project[] projectList;

    private Unit[] unitList;

    public HousePropertyResponseBody() {
    }

    public HousePropertyResponseBody(
           Block[] blockList,
           Building[] buildingList,
           City[] cityList,
           Floor[] floorList,
           House[] houseList,
           Project[] projectList,
           Unit[] unitList) {
           this.blockList = blockList;
           this.buildingList = buildingList;
           this.cityList = cityList;
           this.floorList = floorList;
           this.houseList = houseList;
           this.projectList = projectList;
           this.unitList = unitList;
    }


    /**
     * Gets the blockList value for this HousePropertyResponseBody.
     * 
     * @return blockList
     */
    public Block[] getBlockList() {
        return blockList;
    }


    /**
     * Sets the blockList value for this HousePropertyResponseBody.
     * 
     * @param blockList
     */
    public void setBlockList(Block[] blockList) {
        this.blockList = blockList;
    }


    /**
     * Gets the buildingList value for this HousePropertyResponseBody.
     * 
     * @return buildingList
     */
    public Building[] getBuildingList() {
        return buildingList;
    }


    /**
     * Sets the buildingList value for this HousePropertyResponseBody.
     * 
     * @param buildingList
     */
    public void setBuildingList(Building[] buildingList) {
        this.buildingList = buildingList;
    }


    /**
     * Gets the cityList value for this HousePropertyResponseBody.
     * 
     * @return cityList
     */
    public City[] getCityList() {
        return cityList;
    }


    /**
     * Sets the cityList value for this HousePropertyResponseBody.
     * 
     * @param cityList
     */
    public void setCityList(City[] cityList) {
        this.cityList = cityList;
    }


    /**
     * Gets the floorList value for this HousePropertyResponseBody.
     * 
     * @return floorList
     */
    public Floor[] getFloorList() {
        return floorList;
    }


    /**
     * Sets the floorList value for this HousePropertyResponseBody.
     * 
     * @param floorList
     */
    public void setFloorList(Floor[] floorList) {
        this.floorList = floorList;
    }


    /**
     * Gets the houseList value for this HousePropertyResponseBody.
     * 
     * @return houseList
     */
    public House[] getHouseList() {
        return houseList;
    }


    /**
     * Sets the houseList value for this HousePropertyResponseBody.
     * 
     * @param houseList
     */
    public void setHouseList(House[] houseList) {
        this.houseList = houseList;
    }


    /**
     * Gets the projectList value for this HousePropertyResponseBody.
     * 
     * @return projectList
     */
    public Project[] getProjectList() {
        return projectList;
    }


    /**
     * Sets the projectList value for this HousePropertyResponseBody.
     * 
     * @param projectList
     */
    public void setProjectList(Project[] projectList) {
        this.projectList = projectList;
    }


    /**
     * Gets the unitList value for this HousePropertyResponseBody.
     * 
     * @return unitList
     */
    public Unit[] getUnitList() {
        return unitList;
    }


    /**
     * Sets the unitList value for this HousePropertyResponseBody.
     * 
     * @param unitList
     */
    public void setUnitList(Unit[] unitList) {
        this.unitList = unitList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof HousePropertyResponseBody)) return false;
        HousePropertyResponseBody other = (HousePropertyResponseBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.blockList==null && other.getBlockList()==null) || 
             (this.blockList!=null &&
              java.util.Arrays.equals(this.blockList, other.getBlockList()))) &&
            ((this.buildingList==null && other.getBuildingList()==null) || 
             (this.buildingList!=null &&
              java.util.Arrays.equals(this.buildingList, other.getBuildingList()))) &&
            ((this.cityList==null && other.getCityList()==null) || 
             (this.cityList!=null &&
              java.util.Arrays.equals(this.cityList, other.getCityList()))) &&
            ((this.floorList==null && other.getFloorList()==null) || 
             (this.floorList!=null &&
              java.util.Arrays.equals(this.floorList, other.getFloorList()))) &&
            ((this.houseList==null && other.getHouseList()==null) || 
             (this.houseList!=null &&
              java.util.Arrays.equals(this.houseList, other.getHouseList()))) &&
            ((this.projectList==null && other.getProjectList()==null) || 
             (this.projectList!=null &&
              java.util.Arrays.equals(this.projectList, other.getProjectList()))) &&
            ((this.unitList==null && other.getUnitList()==null) || 
             (this.unitList!=null &&
              java.util.Arrays.equals(this.unitList, other.getUnitList())));
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
        if (getBlockList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBlockList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getBlockList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBuildingList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBuildingList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getBuildingList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCityList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCityList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCityList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFloorList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFloorList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFloorList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHouseList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHouseList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getHouseList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProjectList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProjectList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getProjectList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUnitList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnitList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getUnitList(), i);
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
        new org.apache.axis.description.TypeDesc(HousePropertyResponseBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyResponseBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "blockList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Block"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Block"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildingList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "buildingList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Building"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Building"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cityList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "cityList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "City"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "City"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Floor"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Floor"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "House"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "House"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Project"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Project"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Unit"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Unit"));
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
