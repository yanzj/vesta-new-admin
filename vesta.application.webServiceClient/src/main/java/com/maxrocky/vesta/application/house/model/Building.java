/**
 * Building.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class Building  implements java.io.Serializable {
    private String block;

    private String blockBuildingCode;

    private String blockCode;

    private String blockId;

    private String buildingNum;

    private java.util.Calendar completionTime;

    private ConstructionType constructionType;

    private DecorationStandard decorationStandard;

    private java.util.Calendar deliveryTime;

    private Double floorHeight;

    private String groupCode;

    private String groupName;

    private String housingResourcesId;

    private java.util.Calendar modifiedOn;

    private String projectCode;

    private String projectId;

    private String projectPeriod;

    private PropertyType propertyType;

    private String recordBuildingCode;

    private String recordBuildingName;

    private String saleBuilding;

    private String saleBuildingName;

    private Integer stateCode;

    private Double totalBuildingArea;

    private Integer totalFloor;

    private Double totalGardenArea;

    private Double totalGreenArea;

    private Integer totalHouse;

    private Double totalInternalArea;

    private Integer totalUnit;

    public Building() {
    }

    public Building(
           String block,
           String blockBuildingCode,
           String blockCode,
           String blockId,
           String buildingNum,
           java.util.Calendar completionTime,
           ConstructionType constructionType,
           DecorationStandard decorationStandard,
           java.util.Calendar deliveryTime,
           Double floorHeight,
           String groupCode,
           String groupName,
           String housingResourcesId,
           java.util.Calendar modifiedOn,
           String projectCode,
           String projectId,
           String projectPeriod,
           PropertyType propertyType,
           String recordBuildingCode,
           String recordBuildingName,
           String saleBuilding,
           String saleBuildingName,
           Integer stateCode,
           Double totalBuildingArea,
           Integer totalFloor,
           Double totalGardenArea,
           Double totalGreenArea,
           Integer totalHouse,
           Double totalInternalArea,
           Integer totalUnit) {
           this.block = block;
           this.blockBuildingCode = blockBuildingCode;
           this.blockCode = blockCode;
           this.blockId = blockId;
           this.buildingNum = buildingNum;
           this.completionTime = completionTime;
           this.constructionType = constructionType;
           this.decorationStandard = decorationStandard;
           this.deliveryTime = deliveryTime;
           this.floorHeight = floorHeight;
           this.groupCode = groupCode;
           this.groupName = groupName;
           this.housingResourcesId = housingResourcesId;
           this.modifiedOn = modifiedOn;
           this.projectCode = projectCode;
           this.projectId = projectId;
           this.projectPeriod = projectPeriod;
           this.propertyType = propertyType;
           this.recordBuildingCode = recordBuildingCode;
           this.recordBuildingName = recordBuildingName;
           this.saleBuilding = saleBuilding;
           this.saleBuildingName = saleBuildingName;
           this.stateCode = stateCode;
           this.totalBuildingArea = totalBuildingArea;
           this.totalFloor = totalFloor;
           this.totalGardenArea = totalGardenArea;
           this.totalGreenArea = totalGreenArea;
           this.totalHouse = totalHouse;
           this.totalInternalArea = totalInternalArea;
           this.totalUnit = totalUnit;
    }


    /**
     * Gets the block value for this Building.
     * 
     * @return block
     */
    public String getBlock() {
        return block;
    }


    /**
     * Sets the block value for this Building.
     * 
     * @param block
     */
    public void setBlock(String block) {
        this.block = block;
    }


    /**
     * Gets the blockBuildingCode value for this Building.
     * 
     * @return blockBuildingCode
     */
    public String getBlockBuildingCode() {
        return blockBuildingCode;
    }


    /**
     * Sets the blockBuildingCode value for this Building.
     * 
     * @param blockBuildingCode
     */
    public void setBlockBuildingCode(String blockBuildingCode) {
        this.blockBuildingCode = blockBuildingCode;
    }


    /**
     * Gets the blockCode value for this Building.
     * 
     * @return blockCode
     */
    public String getBlockCode() {
        return blockCode;
    }


    /**
     * Sets the blockCode value for this Building.
     * 
     * @param blockCode
     */
    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }


    /**
     * Gets the blockId value for this Building.
     * 
     * @return blockId
     */
    public String getBlockId() {
        return blockId;
    }


    /**
     * Sets the blockId value for this Building.
     * 
     * @param blockId
     */
    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }


    /**
     * Gets the buildingNum value for this Building.
     * 
     * @return buildingNum
     */
    public String getBuildingNum() {
        return buildingNum;
    }


    /**
     * Sets the buildingNum value for this Building.
     * 
     * @param buildingNum
     */
    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }


    /**
     * Gets the completionTime value for this Building.
     * 
     * @return completionTime
     */
    public java.util.Calendar getCompletionTime() {
        return completionTime;
    }


    /**
     * Sets the completionTime value for this Building.
     * 
     * @param completionTime
     */
    public void setCompletionTime(java.util.Calendar completionTime) {
        this.completionTime = completionTime;
    }


    /**
     * Gets the constructionType value for this Building.
     * 
     * @return constructionType
     */
    public ConstructionType getConstructionType() {
        return constructionType;
    }


    /**
     * Sets the constructionType value for this Building.
     * 
     * @param constructionType
     */
    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }


    /**
     * Gets the decorationStandard value for this Building.
     * 
     * @return decorationStandard
     */
    public DecorationStandard getDecorationStandard() {
        return decorationStandard;
    }


    /**
     * Sets the decorationStandard value for this Building.
     * 
     * @param decorationStandard
     */
    public void setDecorationStandard(DecorationStandard decorationStandard) {
        this.decorationStandard = decorationStandard;
    }


    /**
     * Gets the deliveryTime value for this Building.
     * 
     * @return deliveryTime
     */
    public java.util.Calendar getDeliveryTime() {
        return deliveryTime;
    }


    /**
     * Sets the deliveryTime value for this Building.
     * 
     * @param deliveryTime
     */
    public void setDeliveryTime(java.util.Calendar deliveryTime) {
        this.deliveryTime = deliveryTime;
    }


    /**
     * Gets the floorHeight value for this Building.
     * 
     * @return floorHeight
     */
    public Double getFloorHeight() {
        return floorHeight;
    }


    /**
     * Sets the floorHeight value for this Building.
     * 
     * @param floorHeight
     */
    public void setFloorHeight(Double floorHeight) {
        this.floorHeight = floorHeight;
    }


    /**
     * Gets the groupCode value for this Building.
     * 
     * @return groupCode
     */
    public String getGroupCode() {
        return groupCode;
    }


    /**
     * Sets the groupCode value for this Building.
     * 
     * @param groupCode
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }


    /**
     * Gets the groupName value for this Building.
     * 
     * @return groupName
     */
    public String getGroupName() {
        return groupName;
    }


    /**
     * Sets the groupName value for this Building.
     * 
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    /**
     * Gets the housingResourcesId value for this Building.
     * 
     * @return housingResourcesId
     */
    public String getHousingResourcesId() {
        return housingResourcesId;
    }


    /**
     * Sets the housingResourcesId value for this Building.
     * 
     * @param housingResourcesId
     */
    public void setHousingResourcesId(String housingResourcesId) {
        this.housingResourcesId = housingResourcesId;
    }


    /**
     * Gets the modifiedOn value for this Building.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this Building.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the projectCode value for this Building.
     * 
     * @return projectCode
     */
    public String getProjectCode() {
        return projectCode;
    }


    /**
     * Sets the projectCode value for this Building.
     * 
     * @param projectCode
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    /**
     * Gets the projectId value for this Building.
     * 
     * @return projectId
     */
    public String getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this Building.
     * 
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the projectPeriod value for this Building.
     * 
     * @return projectPeriod
     */
    public String getProjectPeriod() {
        return projectPeriod;
    }


    /**
     * Sets the projectPeriod value for this Building.
     * 
     * @param projectPeriod
     */
    public void setProjectPeriod(String projectPeriod) {
        this.projectPeriod = projectPeriod;
    }


    /**
     * Gets the propertyType value for this Building.
     * 
     * @return propertyType
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this Building.
     * 
     * @param propertyType
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the recordBuildingCode value for this Building.
     * 
     * @return recordBuildingCode
     */
    public String getRecordBuildingCode() {
        return recordBuildingCode;
    }


    /**
     * Sets the recordBuildingCode value for this Building.
     * 
     * @param recordBuildingCode
     */
    public void setRecordBuildingCode(String recordBuildingCode) {
        this.recordBuildingCode = recordBuildingCode;
    }


    /**
     * Gets the recordBuildingName value for this Building.
     * 
     * @return recordBuildingName
     */
    public String getRecordBuildingName() {
        return recordBuildingName;
    }


    /**
     * Sets the recordBuildingName value for this Building.
     * 
     * @param recordBuildingName
     */
    public void setRecordBuildingName(String recordBuildingName) {
        this.recordBuildingName = recordBuildingName;
    }


    /**
     * Gets the saleBuilding value for this Building.
     * 
     * @return saleBuilding
     */
    public String getSaleBuilding() {
        return saleBuilding;
    }


    /**
     * Sets the saleBuilding value for this Building.
     * 
     * @param saleBuilding
     */
    public void setSaleBuilding(String saleBuilding) {
        this.saleBuilding = saleBuilding;
    }


    /**
     * Gets the saleBuildingName value for this Building.
     * 
     * @return saleBuildingName
     */
    public String getSaleBuildingName() {
        return saleBuildingName;
    }


    /**
     * Sets the saleBuildingName value for this Building.
     * 
     * @param saleBuildingName
     */
    public void setSaleBuildingName(String saleBuildingName) {
        this.saleBuildingName = saleBuildingName;
    }


    /**
     * Gets the stateCode value for this Building.
     * 
     * @return stateCode
     */
    public Integer getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this Building.
     * 
     * @param stateCode
     */
    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the totalBuildingArea value for this Building.
     * 
     * @return totalBuildingArea
     */
    public Double getTotalBuildingArea() {
        return totalBuildingArea;
    }


    /**
     * Sets the totalBuildingArea value for this Building.
     * 
     * @param totalBuildingArea
     */
    public void setTotalBuildingArea(Double totalBuildingArea) {
        this.totalBuildingArea = totalBuildingArea;
    }


    /**
     * Gets the totalFloor value for this Building.
     * 
     * @return totalFloor
     */
    public Integer getTotalFloor() {
        return totalFloor;
    }


    /**
     * Sets the totalFloor value for this Building.
     * 
     * @param totalFloor
     */
    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }


    /**
     * Gets the totalGardenArea value for this Building.
     * 
     * @return totalGardenArea
     */
    public Double getTotalGardenArea() {
        return totalGardenArea;
    }


    /**
     * Sets the totalGardenArea value for this Building.
     * 
     * @param totalGardenArea
     */
    public void setTotalGardenArea(Double totalGardenArea) {
        this.totalGardenArea = totalGardenArea;
    }


    /**
     * Gets the totalGreenArea value for this Building.
     * 
     * @return totalGreenArea
     */
    public Double getTotalGreenArea() {
        return totalGreenArea;
    }


    /**
     * Sets the totalGreenArea value for this Building.
     * 
     * @param totalGreenArea
     */
    public void setTotalGreenArea(Double totalGreenArea) {
        this.totalGreenArea = totalGreenArea;
    }


    /**
     * Gets the totalHouse value for this Building.
     * 
     * @return totalHouse
     */
    public Integer getTotalHouse() {
        return totalHouse;
    }


    /**
     * Sets the totalHouse value for this Building.
     * 
     * @param totalHouse
     */
    public void setTotalHouse(Integer totalHouse) {
        this.totalHouse = totalHouse;
    }


    /**
     * Gets the totalInternalArea value for this Building.
     * 
     * @return totalInternalArea
     */
    public Double getTotalInternalArea() {
        return totalInternalArea;
    }


    /**
     * Sets the totalInternalArea value for this Building.
     * 
     * @param totalInternalArea
     */
    public void setTotalInternalArea(Double totalInternalArea) {
        this.totalInternalArea = totalInternalArea;
    }


    /**
     * Gets the totalUnit value for this Building.
     * 
     * @return totalUnit
     */
    public Integer getTotalUnit() {
        return totalUnit;
    }


    /**
     * Sets the totalUnit value for this Building.
     * 
     * @param totalUnit
     */
    public void setTotalUnit(Integer totalUnit) {
        this.totalUnit = totalUnit;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Building)) return false;
        Building other = (Building) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.block==null && other.getBlock()==null) || 
             (this.block!=null &&
              this.block.equals(other.getBlock()))) &&
            ((this.blockBuildingCode==null && other.getBlockBuildingCode()==null) || 
             (this.blockBuildingCode!=null &&
              this.blockBuildingCode.equals(other.getBlockBuildingCode()))) &&
            ((this.blockCode==null && other.getBlockCode()==null) || 
             (this.blockCode!=null &&
              this.blockCode.equals(other.getBlockCode()))) &&
            ((this.blockId==null && other.getBlockId()==null) || 
             (this.blockId!=null &&
              this.blockId.equals(other.getBlockId()))) &&
            ((this.buildingNum==null && other.getBuildingNum()==null) || 
             (this.buildingNum!=null &&
              this.buildingNum.equals(other.getBuildingNum()))) &&
            ((this.completionTime==null && other.getCompletionTime()==null) || 
             (this.completionTime!=null &&
              this.completionTime.equals(other.getCompletionTime()))) &&
            ((this.constructionType==null && other.getConstructionType()==null) || 
             (this.constructionType!=null &&
              this.constructionType.equals(other.getConstructionType()))) &&
            ((this.decorationStandard==null && other.getDecorationStandard()==null) || 
             (this.decorationStandard!=null &&
              this.decorationStandard.equals(other.getDecorationStandard()))) &&
            ((this.deliveryTime==null && other.getDeliveryTime()==null) || 
             (this.deliveryTime!=null &&
              this.deliveryTime.equals(other.getDeliveryTime()))) &&
            ((this.floorHeight==null && other.getFloorHeight()==null) || 
             (this.floorHeight!=null &&
              this.floorHeight.equals(other.getFloorHeight()))) &&
            ((this.groupCode==null && other.getGroupCode()==null) || 
             (this.groupCode!=null &&
              this.groupCode.equals(other.getGroupCode()))) &&
            ((this.groupName==null && other.getGroupName()==null) || 
             (this.groupName!=null &&
              this.groupName.equals(other.getGroupName()))) &&
            ((this.housingResourcesId==null && other.getHousingResourcesId()==null) || 
             (this.housingResourcesId!=null &&
              this.housingResourcesId.equals(other.getHousingResourcesId()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.projectCode==null && other.getProjectCode()==null) || 
             (this.projectCode!=null &&
              this.projectCode.equals(other.getProjectCode()))) &&
            ((this.projectId==null && other.getProjectId()==null) || 
             (this.projectId!=null &&
              this.projectId.equals(other.getProjectId()))) &&
            ((this.projectPeriod==null && other.getProjectPeriod()==null) || 
             (this.projectPeriod!=null &&
              this.projectPeriod.equals(other.getProjectPeriod()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.recordBuildingCode==null && other.getRecordBuildingCode()==null) || 
             (this.recordBuildingCode!=null &&
              this.recordBuildingCode.equals(other.getRecordBuildingCode()))) &&
            ((this.recordBuildingName==null && other.getRecordBuildingName()==null) || 
             (this.recordBuildingName!=null &&
              this.recordBuildingName.equals(other.getRecordBuildingName()))) &&
            ((this.saleBuilding==null && other.getSaleBuilding()==null) || 
             (this.saleBuilding!=null &&
              this.saleBuilding.equals(other.getSaleBuilding()))) &&
            ((this.saleBuildingName==null && other.getSaleBuildingName()==null) || 
             (this.saleBuildingName!=null &&
              this.saleBuildingName.equals(other.getSaleBuildingName()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode()))) &&
            ((this.totalBuildingArea==null && other.getTotalBuildingArea()==null) || 
             (this.totalBuildingArea!=null &&
              this.totalBuildingArea.equals(other.getTotalBuildingArea()))) &&
            ((this.totalFloor==null && other.getTotalFloor()==null) || 
             (this.totalFloor!=null &&
              this.totalFloor.equals(other.getTotalFloor()))) &&
            ((this.totalGardenArea==null && other.getTotalGardenArea()==null) || 
             (this.totalGardenArea!=null &&
              this.totalGardenArea.equals(other.getTotalGardenArea()))) &&
            ((this.totalGreenArea==null && other.getTotalGreenArea()==null) || 
             (this.totalGreenArea!=null &&
              this.totalGreenArea.equals(other.getTotalGreenArea()))) &&
            ((this.totalHouse==null && other.getTotalHouse()==null) || 
             (this.totalHouse!=null &&
              this.totalHouse.equals(other.getTotalHouse()))) &&
            ((this.totalInternalArea==null && other.getTotalInternalArea()==null) || 
             (this.totalInternalArea!=null &&
              this.totalInternalArea.equals(other.getTotalInternalArea()))) &&
            ((this.totalUnit==null && other.getTotalUnit()==null) || 
             (this.totalUnit!=null &&
              this.totalUnit.equals(other.getTotalUnit())));
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
        if (getBlock() != null) {
            _hashCode += getBlock().hashCode();
        }
        if (getBlockBuildingCode() != null) {
            _hashCode += getBlockBuildingCode().hashCode();
        }
        if (getBlockCode() != null) {
            _hashCode += getBlockCode().hashCode();
        }
        if (getBlockId() != null) {
            _hashCode += getBlockId().hashCode();
        }
        if (getBuildingNum() != null) {
            _hashCode += getBuildingNum().hashCode();
        }
        if (getCompletionTime() != null) {
            _hashCode += getCompletionTime().hashCode();
        }
        if (getConstructionType() != null) {
            _hashCode += getConstructionType().hashCode();
        }
        if (getDecorationStandard() != null) {
            _hashCode += getDecorationStandard().hashCode();
        }
        if (getDeliveryTime() != null) {
            _hashCode += getDeliveryTime().hashCode();
        }
        if (getFloorHeight() != null) {
            _hashCode += getFloorHeight().hashCode();
        }
        if (getGroupCode() != null) {
            _hashCode += getGroupCode().hashCode();
        }
        if (getGroupName() != null) {
            _hashCode += getGroupName().hashCode();
        }
        if (getHousingResourcesId() != null) {
            _hashCode += getHousingResourcesId().hashCode();
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
        if (getProjectPeriod() != null) {
            _hashCode += getProjectPeriod().hashCode();
        }
        if (getPropertyType() != null) {
            _hashCode += getPropertyType().hashCode();
        }
        if (getRecordBuildingCode() != null) {
            _hashCode += getRecordBuildingCode().hashCode();
        }
        if (getRecordBuildingName() != null) {
            _hashCode += getRecordBuildingName().hashCode();
        }
        if (getSaleBuilding() != null) {
            _hashCode += getSaleBuilding().hashCode();
        }
        if (getSaleBuildingName() != null) {
            _hashCode += getSaleBuildingName().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        if (getTotalBuildingArea() != null) {
            _hashCode += getTotalBuildingArea().hashCode();
        }
        if (getTotalFloor() != null) {
            _hashCode += getTotalFloor().hashCode();
        }
        if (getTotalGardenArea() != null) {
            _hashCode += getTotalGardenArea().hashCode();
        }
        if (getTotalGreenArea() != null) {
            _hashCode += getTotalGreenArea().hashCode();
        }
        if (getTotalHouse() != null) {
            _hashCode += getTotalHouse().hashCode();
        }
        if (getTotalInternalArea() != null) {
            _hashCode += getTotalInternalArea().hashCode();
        }
        if (getTotalUnit() != null) {
            _hashCode += getTotalUnit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Building.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Building"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("block");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "block"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockBuildingCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "blockBuildingCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("buildingNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "buildingNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "completionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("constructionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "constructionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ConstructionType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decorationStandard");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "decorationStandard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "DecorationStandard"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "deliveryTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorHeight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorHeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "groupCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "groupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("housingResourcesId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "housingResourcesId"));
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
        elemField.setFieldName("projectPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectPeriod"));
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
        elemField.setFieldName("recordBuildingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordBuildingName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saleBuilding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "saleBuilding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saleBuildingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "saleBuildingName"));
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
        elemField.setFieldName("totalBuildingArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalBuildingArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalFloor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalFloor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalGardenArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalGardenArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalGreenArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalGreenArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalHouse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalHouse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalInternalArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalInternalArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "totalUnit"));
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
