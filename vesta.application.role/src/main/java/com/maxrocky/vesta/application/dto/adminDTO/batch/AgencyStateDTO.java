package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2018/1/10.
 */
public class AgencyStateDTO {
    private String delAgencyId;  //项目id
    private String delAgencyName;//项目名称
    private String delIs;   // 1  项目下没有人员 可以删除

    public AgencyStateDTO() {
        this.delAgencyId = "";
        this.delAgencyName = "";
        this.delIs = "";
    }

    public String getDelAgencyId() {
        return delAgencyId;
    }

    public void setDelAgencyId(String delAgencyId) {
        this.delAgencyId = delAgencyId;
    }

    public String getDelAgencyName() {
        return delAgencyName;
    }

    public void setDelAgencyName(String delAgencyName) {
        this.delAgencyName = delAgencyName;
    }

    public String getDelIs() {
        return delIs;
    }

    public void setDelIs(String delIs) {
        this.delIs = delIs;
    }
}
