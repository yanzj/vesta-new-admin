package com.maxrocky.vesta.application.DTO;

/**
 * Created by Magic on 2017/12/6.
 */
public class AuthAgencyDTO {
    private String agencyId;           //主键
    private String agencyName;         //组织机构名
    private String agencyType;         //机构类型 3100000000：总部 100000001：区域 100000003：城市 100000002：项目
    private String parentId;           //上级ID
    private String modifyTime;         //修改时间
    private String agencyPath;         //路径  eg:  项目一级别   /总部id/区域id/城市id/项目id
    private String status;             //状态 0删除 1正常
    private String businesssource;    //业态

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAgencyPath() {
        return agencyPath;
    }

    public void setAgencyPath(String agencyPath) {
        this.agencyPath = agencyPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinesssource() {
        return businesssource;
    }

    public void setBusinesssource(String businesssource) {
        this.businesssource = businesssource;
    }
}
