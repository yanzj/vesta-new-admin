package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.Date;

/**
 * Created by hp on 2017/12/25.
 */
public class OuterAgencyDTO {

    private String agencyId;           //主键 uuid
    private String agencyName;         //组织机构名
    private String status;             //状态 0删除 1正常
    private String memo;         //机构描述


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
