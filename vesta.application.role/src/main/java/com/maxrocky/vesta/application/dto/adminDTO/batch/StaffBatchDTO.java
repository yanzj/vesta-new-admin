package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/12.
 */
public class StaffBatchDTO {

    private String staffIds;
    private String state;
    private String sourceFrom;
    private String agencyId;


    public StaffBatchDTO() {
        this.staffIds = "";
        this.state = "";
        this.sourceFrom = "";
        this.agencyId= "";
    }

    public String getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = staffIds;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
