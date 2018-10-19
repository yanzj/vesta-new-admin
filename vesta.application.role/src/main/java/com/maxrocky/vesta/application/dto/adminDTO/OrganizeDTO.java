package com.maxrocky.vesta.application.dto.adminDTO;

import java.util.List;

/**
 * Created by chen on 2016/6/2.
 */
public class OrganizeDTO {
    private String organizeId;       //组ID
    private String organizeName;     //组名
    private String crmName;          //crm组名
    private String crmId;            //crmID
    private String staffNum;         //组人数
    private String projectNum;       //组项目数
    private String memo;             //备注
    private Integer orderNum;        //排序号
    private String modifyTime;       //最后修改时间
    private String status="1";       //状态
    private String staffIds;         //ID群组关联的人ID列表
    private List<StaffNameDTO> staffDTOList;//组内成员列表
    private List<OrganizeProjectDTO> projectOrganizeList;  //群组关联的项目列表

    public String getCrmName() {
        return crmName;
    }

    public void setCrmName(String crmName) {
        this.crmName = crmName;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StaffNameDTO> getStaffDTOList() {
        return staffDTOList;
    }

    public void setStaffDTOList(List<StaffNameDTO> staffDTOList) {
        this.staffDTOList = staffDTOList;
    }

    public List<OrganizeProjectDTO> getProjectOrganizeList() {
        return projectOrganizeList;
    }

    public void setProjectOrganizeList(List<OrganizeProjectDTO> projectOrganizeList) {
        this.projectOrganizeList = projectOrganizeList;
    }

    public String getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = staffIds;
    }
}
