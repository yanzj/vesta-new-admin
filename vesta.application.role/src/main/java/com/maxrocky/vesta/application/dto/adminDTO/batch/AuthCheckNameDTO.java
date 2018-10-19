package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by maxrocky on 2018/4/2.
 */
public class AuthCheckNameDTO {
    private String sId;//组织机构id
    private String sName;//组织结构名称
    private String abbreviationName;//简称
    private String projectId;//项目id

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
